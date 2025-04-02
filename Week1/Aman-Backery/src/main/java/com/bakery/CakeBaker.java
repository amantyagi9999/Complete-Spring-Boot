package com.bakery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CakeBaker {

    @Autowired
    Frosting frosting;

    @Autowired
    Syrup syrup;

    public String bakeCake(){
        return frosting.getFrostingType() + ".." + syrup.getSyrupType();
    }
}
