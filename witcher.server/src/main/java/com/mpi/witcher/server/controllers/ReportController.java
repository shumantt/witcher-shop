package com.mpi.witcher.server.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/report")
public class ReportController {


    @GetMapping("/base")
    public ResponseEntity getBaseReport() {
        // TODO базовый отчет
        // формат ответа
        //
        /*
        * {
    "reportData": [ // массив пар ключ-значение, будет показыватья в виде таблицы
        {
            "name": "Всего сотрудников",
            "value": 5
        }],
    "charts": [ // массив структур графиков (можно несколько нарисовать)
        {
            "labels": ["Январь", "Февраль", "Март", "Апрель", "Май"], //ось X
            "datasets": [
                {
                    "label": "Количество видов рун", //имя графика
                    "backgroundColor": "#95a014", // цвет
                    "data": [1,3,10,7,3] // ось y
                }
            ]
        }
    ]
}
        *
        *
        * */
        return ok(null);
    }

    @GetMapping("/consumption")
    public ResponseEntity getConsumptionReport() {
        // TODO отчет расходов ресурсов в лавке
        // формат ответа такой же, как в getBaseReport
        return ok(null);
    }
}
