package br.com.autogain.enums;

public enum ManagementOperation {
    MARTINGALE(1),
    SOROS(2),
    FIX(3);

    private Integer id;

    ManagementOperation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static ManagementOperation get(int id) {
        for(ManagementOperation mo: values()) {
            if(mo.getId() == id) {
                return mo;
            }
        }
        return null;
    }
}
