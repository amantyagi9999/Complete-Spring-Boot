package com.bakery;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "deploy.flavour", havingValue = "choco")
public class ChocolateSyrup implements  Syrup{
    @Override
    public String getSyrupType() {
        return "Chocolate Syrup";
    }
}
