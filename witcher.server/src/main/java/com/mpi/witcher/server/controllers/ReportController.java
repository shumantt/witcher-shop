package com.mpi.witcher.server.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

//TODO тут надо придумать че-то, каккую инфу показывать, на фронте страницы примерно одинаково выглядят, главное подсунуть разные данные, не особо усложнять
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
    public ResponseEntity getConsumptionReport(@RequestParam int period) {
        // TODO отчет расходов ресурсов в лавке за последние period месяцев
        // формат ответа такой же, как в getBaseReport
        return ok(null);
    }

    @GetMapping("/workload")
    public ResponseEntity getWorkloadReport() {
        // TODO общий отчет загруженности по всем сотрудникам
        // формат ответа такой же, как в getBaseReport
        return ok(null);
    }

    @GetMapping("/workload/employee")
    public ResponseEntity getWorkloadReport(@RequestParam String login) {
        // TODO отчет загруженности конкретного сотрудника
        // формат ответа такой же, как в getBaseReport
        return ok(null);
    }

    @GetMapping("/kpi/employee")
    public ResponseEntity getKpiReport(@RequestParam String login) {
        // TODO отчет kpi конкретного сотрудника
        // формат ответа такой же, как в getBaseReport
        return ok(null);
    }
}
