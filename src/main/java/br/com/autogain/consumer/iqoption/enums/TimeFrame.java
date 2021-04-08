package br.com.autogain.consumer.iqoption.enums;

public enum TimeFrame {
    M1(1),
    M5(5),
    M15(15);

    private int id;

    TimeFrame(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TimeFrame get(int id) {
        for(TimeFrame tf: values()) {
            if(tf.getId() == id) {
                return tf;
            }
        }
        return null;
    }
}
