package com.bakery;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component

@ConditionalOnProperty(name = "deploy.flavour", havingValue = "strawberry")
public class StrawberryFrosting implements  Frosting{
    @Override
    public String getFrostingType() {
        return "Strawberry Frosting";
    }
}
