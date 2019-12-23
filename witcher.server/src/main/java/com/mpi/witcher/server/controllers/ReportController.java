package com.mpi.witcher.server.controllers;

import com.mpi.witcher.server.models.HistoryEvent;
import com.mpi.witcher.server.models.Product;
import com.mpi.witcher.server.models.responses.ReportResponse;
import com.mpi.witcher.server.repositories.GoodsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static org.springframework.http.ResponseEntity.ok;

//TODO тут надо придумать че-то, каккую инфу показывать, на фронте страницы примерно одинаково выглядят, главное подсунуть разные данные, не особо усложнять
@RestController
@RequestMapping("/api/report")
public class ReportController {

    private GoodsRepository goodsRepository = new GoodsRepository();

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
        List<Product> products = goodsRepository.getAllProducts();

        List<ReportResponse.NameValuePair> kvData = new ArrayList<>();
        List<ReportResponse.ChartData> charts = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        int endMonth = calendar.get(Calendar.MONTH);
        calendar.add(Calendar.MONTH, -period);

        List<String> labels = new ArrayList<>();
        int month = calendar.get(Calendar.MONTH);
        while (month < endMonth) {
            labels.add(Integer.toString(month++));
        }
        List<ReportResponse.ChartData.Dataset> datasets = new ArrayList<>();
        for(Product product : products) {
            List<HistoryEvent> history = product.getHistory();
            history.sort(Comparator.comparing(HistoryEvent::getDate).reversed());

            List<Float> values = new ArrayList<>();
            int i = 0;
            int j = 0;
            int prevMonth = -1;
            while ((history.size() > i) && (history.get(i).getDate().compareTo(calendar.getTime()) >= 0)) {
                Calendar date = Calendar.getInstance();
                date.setTime(history.get(i).getDate());
                month = date.get(Calendar.MONTH);
                if(month != prevMonth) {
                    values.add((float) history.get(i).getChange());
                    j++;
                } else {
                    values.set(j, values.get(i) + history.get(i).getChange());
                }
                prevMonth = month;
                i++;
            }
            datasets.add(new ReportResponse.ChartData.Dataset(product.getName(), values));
        }
        charts.add(new ReportResponse.ChartData(labels, datasets));
        kvData.add(new ReportResponse.NameValuePair("Products count", products.size()));

        ReportResponse response = new ReportResponse(kvData, charts);
        return ok(response);
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
