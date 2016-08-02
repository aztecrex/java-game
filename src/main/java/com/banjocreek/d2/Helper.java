package com.banjocreek.d2;

final class Helper {

    public static String show(final Mat3 m) {
        /* @formatter:off */
        return new StringBuilder()
        .append("(")
            .append("(")
                .append(String.format("%8.5f", m.m00())).append(" ")
                .append(String.format("%8.5f", m.m10())).append(" ")
                .append(String.format("%8.5f", m.m20()))
            .append(") ")
            .append("(")
                .append(String.format("%8.5f", m.m01())).append(" ")
                .append(String.format("%8.5f", m.m11())).append(" ")
                .append(String.format("%8.5f", m.m21()))
            .append(") ")
            .append("(")
                .append(String.format("%8.5f", m.m02())).append(" ")
                .append(String.format("%8.5f", m.m12())).append(" ")
                .append(String.format("%8.5f", m.m22()))
                .append(")")
        .append(")")
        .toString();
        /* @formatter:on */
    }

}
