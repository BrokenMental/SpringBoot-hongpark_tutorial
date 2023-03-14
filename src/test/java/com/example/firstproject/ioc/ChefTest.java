package com.example.firstproject.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ChefTest {
    //@Autowired 어노테이션으로 주입하고 싶을 경우 IngredientFactory 가
    //IoC 컨테이너에 Bean(Component) 으로 등록되어 있어야 하기 때문에
    //해당하는 클래스에 @Component 어노테이션을 추가 해야 함
    @Autowired
    IngredientFactory ingredientFactory;

    @Autowired
    Chef chef;

    @Test
    void 돈가스_요리하기() {
        //IngredientFactory ingredientFactory = new IngredientFactory();
        //Chef chef = new Chef(ingredientFactory);
        String menu = "돈가스";
        String expected = "한돈 등심으로 만든 돈가스";

        //일일히 객체를 생성하고 값을 집어넣는 방식(셰프가 직접 재료를 조달하는 방식)
        //Chef chef = new Chef();
        String food = chef.cook(menu);

        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void 스테이크_요리하기() {
        //IngredientFactory ingredientFactory = new IngredientFactory();
        //Chef chef = new Chef(ingredientFactory);
        String menu = "스테이크";
        String expected = "한우 꽃등심으로 만든 스테이크";

        //Chef chef = new Chef();
        String food = chef.cook(menu);

        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void 크리스피_치킨_요리하기() {
        //신규로 메뉴를 생성할 경우 ingredientFactory(재료공장)과 요리사를 연결시켜줌
        //IngredientFactory ingredientFactory = new IngredientFactory();
        //Chef chef = new Chef(ingredientFactory);

        //어떤 메뉴를 요리할 지 정한 뒤
        String menu = "크리스피 치킨";
        String expected = "국내산 10호 닭으로 만든 크리스피 치킨";

        //해당 메뉴에 필요한 재료를 요리사에게 전달 후 조리
        String food = chef.cook(menu);

        assertEquals(expected, food);
        System.out.println(food);
    }
}