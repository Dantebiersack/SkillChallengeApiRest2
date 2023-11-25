package com.ApiRest.SkillChallengeApiRest.Exception;

public class ProductoNotFoundException extends RuntimeException{

    public ProductoNotFoundException(String message) {
        super(message);
    }
}
