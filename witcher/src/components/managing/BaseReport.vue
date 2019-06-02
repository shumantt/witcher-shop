<template>
    <div>
        <md-table v-model="reportData" class="main-table" md-card>
            <md-table-toolbar>
                <h1 class="md-title">Общие данные</h1>
            </md-table-toolbar>
            <md-table-row slot="md-table-row" slot-scope="{ item }" >
                <md-table-cell md-label="Параметр">{{ item.name }}</md-table-cell>
                <md-table-cell md-label="Значение">{{ item.value }}</md-table-cell>
            </md-table-row>
        </md-table>
        <h1 class="md-title">Графики изменения показателей</h1>
        <div class="charts">
            <div class="employees-chart report-chart">
                <line-chart :chart-data="employeesChartData"></line-chart>
            </div>
            <div class="runes-chart report-chart">
                <line-chart :chart-data="runesChartData"></line-chart>
            </div>
        </div>
    </div>
</template>


<script>
import LineChart from '@/charts/LineChart.js'
export default {
    name: 'BaseReport',
    components: {
      LineChart
    },
    mounted() {
        this.renderCharts();
    },
    data() {
        return {
            months: ['Январь', 'Февраль', 'Март', 'Апрель', 'Май'],
            employeesChartData: null,
            runesChartData: null,
            reportData: [
                {
                    name: 'Всего сотрудников',
                    value: 5
                },
                {
                    name: 'Всего видов зелий',
                    value: 5
                },
                {
                    name: 'Всего видов трав',
                    value: 5
                },
                {
                    name: 'Всего видов животных',
                    value: 14
                },
                {
                    name: 'Всего видов рун',
                    value: 3
                }]
        }
    },
    methods: {
        renderCharts() {
            this.renderEmployeesChart();
            this.renderRunesChart();
        },
        renderEmployeesChart() {
            this.employeesChartData = {
                labels: this.months,
                datasets: [
                    {
                        label: 'Количество сотрудников',
                        backgroundColor: '#0f52bf',
                        data: [1,2,3,3,5]
                    }
                ]
            };
        },
        renderRunesChart() {
            this.runesChartData = {
                labels: this.months,
                datasets: [
                    {
                        label: 'Количество видов рун',
                        backgroundColor: '#95a014',
                        data: [1,3,10,7,3]
                    }
                ]
            };
        }
    }
}
</script>

<style>
.report-chart {
    max-width: 500px;
}
</style>
