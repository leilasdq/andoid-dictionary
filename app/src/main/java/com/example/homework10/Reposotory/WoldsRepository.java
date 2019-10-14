package com.example.homework10.Reposotory;

public class WoldsRepository {
    private static final WoldsRepository ourInstance = new WoldsRepository();

    public static WoldsRepository getInstance() {
        return ourInstance;
    }

    private WoldsRepository() {
    }
}
