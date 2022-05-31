package com.movii.hexagonal.infraestructure.out.restricted.enumeration;

public enum UserType {

    NATURAL("0"),
    JURIDICO("1");

    private String code;

    UserType(String pcode) {
        this.code = pcode;
    }

    public static UserType resolve(String code) {
        for (UserType ut : values()) {
            if (ut.code.equals(code)) {
                return ut;
            }
        }
        throw new EnumConstantNotPresentException(UserType.class, code);
    }

    public String value() {
        return code;
    }

}
