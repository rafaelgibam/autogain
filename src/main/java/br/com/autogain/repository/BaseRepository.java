package br.com.autogain.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.annotations.Nullable;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
public abstract class BaseRepository<T> {

    private CollectionReference collectionReference;
    private String collectionName;
    private Class<T> parameterizedType;

    protected BaseRepository(Firestore firestore, String collection) {
        this.collectionReference = firestore.collection(collection);
        this.collectionName = collection;
        this.parameterizedType = getParameterizedType();
    }
    private Class<T> getParameterizedType(){
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>)type.getActualTypeArguments()[0];
    }

    @SneakyThrows
    public T save(T model){
        String documentId = getDocumentId(model);
        ApiFuture<WriteResult> resultApiFuture = collectionReference.document(documentId).set(model);
        try {
            log.info("{}-{} saved at {}", collectionName, documentId, resultApiFuture.get().getUpdateTime());
            return this.get(documentId).get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error saving {}={} {}", collectionName, documentId, e.getMessage());
        }
        return null;
    }

    public void saveAll(List<T> models) {
        models.stream().forEach(model -> {
            String documentId = getDocumentId(model);
            try {
                ApiFuture<WriteResult> resultApiFuture = collectionReference.document(documentId).set(model);
                log.info("{}-{} saved at {}", collectionName, documentId, resultApiFuture.get().getUpdateTime());
            } catch (InterruptedException | ExecutionException e) {
                log.error("Error saving {}={} {}", collectionName, documentId, e.getMessage());
            }
        });
    }

    public Optional<T> findById(String documentId) {
        return this.get(documentId);
    }

    public List<T> findAll() {
        return retrieveAll();
    }

    public void delete(T model){
        String documentId = getDocumentId(model);
        ApiFuture<WriteResult> resultApiFuture = collectionReference.document(documentId).delete();

    }

    public void deleteById(String documentId) {
        ApiFuture<WriteResult> resultApiFuture = collectionReference.document(documentId).delete();
    }

    public List<T> retrieveAll(){
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = collectionReference.get();

        try {
            List<QueryDocumentSnapshot> queryDocumentSnapshots = querySnapshotApiFuture.get().getDocuments();

            return queryDocumentSnapshots.stream()
                    .map(queryDocumentSnapshot -> queryDocumentSnapshot.toObject(parameterizedType))
                    .collect(Collectors.toList());

        } catch (InterruptedException | ExecutionException e) {
            log.error("Exception occurred while retrieving all document for {}", collectionName);
        }
        return Collections.<T>emptyList();

    }


    public Optional<T> get(String documentId){
        DocumentReference documentReference = collectionReference.document(documentId);
        ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = documentReference.get();

        try {
            DocumentSnapshot documentSnapshot = documentSnapshotApiFuture.get();

            if(documentSnapshot.exists()){
                return Optional.ofNullable(documentSnapshot.toObject(parameterizedType));
            }

        } catch (InterruptedException | ExecutionException e) {
            log.error("Exception occurred retrieving: {} {}, {}", collectionName, documentId, e.getMessage());
        }

        return Optional.empty();

    }


    @SneakyThrows
    protected String getDocumentId(T t) {
        Object key;
        Class clzz = t.getClass();
        do{
            key = getKeyFromFields(clzz, t);
            clzz = clzz.getSuperclass();
        } while(key == null && clzz != null);

        if(key==null){
            String uuid = UUID.randomUUID().toString();
            return uuid;
        }
        return String.valueOf(key);
    }

    private Object getKeyFromFields(Class<?> clazz, Object t) {

        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(DocumentId.class))
                .findFirst()
                .map(field -> getValue(t, field))
                .orElse(null);
    }

    @Nullable
    private Object getValue(Object t, java.lang.reflect.Field field) {
        field.setAccessible(true);
        try {
            return field.get(t);
        } catch (IllegalAccessException e) {
            log.error("Error in getting documentId key", e);
        }
        return null;
    }

    protected CollectionReference getCollectionReference(){
        return this.collectionReference;
    }
    protected Class<T> getType(){ return this.parameterizedType; }

}
