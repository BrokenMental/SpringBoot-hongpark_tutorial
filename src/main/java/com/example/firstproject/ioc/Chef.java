package com.example.firstproject.ioc;

import org.springframework.stereotype.Component;

@Component
public class Chef {
    //식재료 공장을 셰프가 알고 있음
    private IngredientFactory ingredientFactory;

    //셰프가 식재료 공장과 협업하기 위한 DI(외부에서 객체 삽입)
    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public String cook(String menu) {
        /*
        //직접 재료를 만들어 진행하는 방식
        Pork pork = new Pork("한돈 등심");
        return pork.getName() + "으로 만든 " + menu;

        Beef beef = new Beef("한우 꽃등심");
        return beef.getName() + "으로 만든 " + menu;
        */

        //재료 공장에서 재료를 가져와 진행하는 방식
        Ingredient ingredient = ingredientFactory.get(menu);
        return ingredient.getName() + "으로 만든 " + menu;
    }
}
