package com.mpi.witcher.server.repositories;

import com.mpi.witcher.server.models.Grass;

import java.util.ArrayList;

public class GrassRepository {
    public static ArrayList<Grass> Grass = new ArrayList<Grass>() {
        {
            add(new Grass(1, "Семилистник", 5, "Обыкновенное растение"));
            add(new Grass(2, "Зверобой", 6, "Странное растение"));
            add(new Grass(3, "Подсолнух", 3, "Содержит семечки"));
            add(new Grass(4, "Роза", 1, "Бесполезное растение"));
            add(new Grass(5, "Фикус", 4, "Домашнее растение"));
        }
    };
}
