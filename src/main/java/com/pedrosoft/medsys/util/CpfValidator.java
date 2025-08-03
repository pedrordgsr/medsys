package com.pedrosoft.medsys.util;

public class CpfValidator {

    public static boolean isValid(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma1 = 0, soma2 = 0;
            for (int i = 0; i < 9; i++) {
                int num = Integer.parseInt(cpf.substring(i, i + 1));
                soma1 += num * (10 - i);
                soma2 += num * (11 - i);
            }

            int dig1 = soma1 % 11 < 2 ? 0 : 11 - (soma1 % 11);
            soma2 += dig1 * 2;
            int dig2 = soma2 % 11 < 2 ? 0 : 11 - (soma2 % 11);

            return dig1 == Integer.parseInt(cpf.substring(9, 10)) &&
                    dig2 == Integer.parseInt(cpf.substring(10));
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
