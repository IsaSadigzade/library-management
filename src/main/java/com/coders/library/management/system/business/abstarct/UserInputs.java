package com.coders.library.management.system.business.abstarct;

import java.util.Scanner;

public interface UserInputs {
    default int INPUT_INT() {
        return new Scanner(System.in).nextInt();
    }
    default String  INPUT_STRING() {
        return new Scanner(System.in).nextLine();
    }
}
