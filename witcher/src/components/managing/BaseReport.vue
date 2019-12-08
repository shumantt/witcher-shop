<template>
    <div>
        <md-table v-model="data.reportData" class="main-table" md-card>
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
            <div v-for="(chart,index) in data.charts" v-bind:key="index">
                <div class="employees-chart report-chart">
                    <line-chart :chart-data="chart"></line-chart>
                </div>
            </div>
        </div>
    </div>
</template>


<script>
import LineChart from '@/charts/LineChart.js'

const fakeData = {
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
        }],
    charts: [
        {
            labels: ['Январь', 'Февраль', 'Март', 'Апрель', 'Май'],
            datasets: [
                {
                    label: 'Количество сотрудников',
                    backgroundColor: '#0f52bf',
                    data: [1,2,3,3,5]
                }]
        },
        {
            labels: ['Январь', 'Февраль', 'Март', 'Апрель', 'Май'],
            datasets: [
                {
                    label: 'Количество видов рун',
                    backgroundColor: '#95a014',
                    data: [1,3,10,7,3]
                }
            ]
        }
    ],
    
};

export default {
    name: 'BaseReport',
    props:['type'],
    components: {
      LineChart
    },
    mounted() {
        this.fetchData();
    },
    data() {
        return {
           data: {
               reportData: [],
               charts: []
           }
        }
    },
    methods: {
        fetchData() {
               this.$store.dispatch("getBaseReport", this.type)
                   .then((data) => {
                       this.data = fakeData;
                   })
                   .catch(console.log)
        }
    }
}
</script>

<style>
.report-chart {
    max-width: 500px;
}
</style>
