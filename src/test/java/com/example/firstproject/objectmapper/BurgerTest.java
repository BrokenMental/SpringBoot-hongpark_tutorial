package com.example.firstproject.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BurgerTest {

    @Test
    public void 자바_객체를_JSON으로_변환() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> ingredients = Arrays.asList("통새우 패티", "순쇠고기 패티", "토마토", "스파이시 어니언 소스");
        Burger burger = new Burger("버거킹", 5000, ingredients);
        String json  = objectMapper.writeValueAsString(burger);

        String expected = "{\"name\":\"버거킹\",\"price\":5000,\"ingredients\":[\"통새우 패티\",\"순쇠고기 패티\",\"토마토\",\"스파이시 어니언 소스\"]}";

        assertEquals(expected, json);

        System.out.println(json);

        //json을 tree로 받아서
        JsonNode jsonNode = objectMapper.readTree(json);

        //이쁜 형식으로 출력
        System.out.println(jsonNode.toPrettyString());
    }

    @Test
    public void JSON을_자바_객체로_변환() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        //String json = "{\"name\":\"버거킹\",\"price\":5000,\"ingredients\":[\"통새우 패티\",\"순쇠고기 패티\",\"토마토\",\"스파이시 어니언 소스\"]}";

        //직접 json 만들기
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("name", "버거킹");
        objectNode.put("price", 5000);

        ArrayNode arrayNode = objectMapper.createArrayNode();
        arrayNode.add("통새우 패티");
        arrayNode.add("순쇠고기 패티");
        arrayNode.add("토마토");
        arrayNode.add("스파이시 어니언 소스");
        //objectNode.put("ingredients", arrayNode); //뒤에 Node를 집어넣는 경우 set을 사용
        objectNode.set("ingredients", arrayNode);
        String json = objectNode.toString();

        Burger burger = objectMapper.readValue(json, Burger.class);

        List<String> ingredients = Arrays.asList("통새우 패티", "순쇠고기 패티", "토마토", "스파이시 어니언 소스");
        Burger expected = new Burger("버거킹", 5000, ingredients);

        assertEquals(expected.toString(), burger.toString());

        System.out.println(json);
        System.out.println(burger.toString());

        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
    }
}