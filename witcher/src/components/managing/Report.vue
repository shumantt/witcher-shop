<template>
    <div class="report-container">
         <md-tabs>
            <!--<md-tab id="tab-base" md-label="Общий" exact>
                <BaseReport type="base"/>
            </md-tab>-->

            <md-tab id="tab-consumptions" md-label="Расходы" exact>
                <div class="md-layout">
                    <div class="md-layout-item label">
                        <div class="md-subtitle">Период отчета (последних месяцев)</div>
                    </div>
                    <div class="md-layout-item selector">
                        <md-field>
                            <md-select v-model="period" name="period" id="period" :md-selected="periodSelected()">
                                <md-option v-for="p in [1,2,3,6,9,12]" v-bind:key="p" :value="p">{{p}}</md-option>
                            </md-select>
                        </md-field>
                    </div>
                </div>
                <BaseReport type="consumption" :period="selectedPeriod"/>
            </md-tab>

            <md-tab id="tab-workload" md-label="Загруженность" >
                <div class="md-layout">
                    <div class="md-layout-item label">
                        <div class="md-subtitle">Сотрудник</div>
                    </div>
                    <div class="md-layout-item selector">
                        <md-field>
                            <md-select v-model="workloadEmployeeId" name="workloadEmployeeId" id="workloadEmployeeId" :md-selected="workloadEmployeeIdSelected()">
                                <md-option value="-1">Все</md-option>
                                <md-option v-for="employee in employees" v-bind:key="employee.login" :value="employee.login">{{employee.name}}</md-option>
                            </md-select>
                        </md-field>
                    </div>
                </div>
                <BaseReport v-if="showBaseWorkloadReport" type="workload"/>
                <BaseReport v-if="!showBaseWorkloadReport" type="employee-workload" :employee="selectedWorkloadEmployeeId"></BaseReport>
            </md-tab>

            <md-tab id="tab-kpi" md-label="KPI" >
                <div class="md-layout">
                    <div class="md-layout-item label">
                        <div class="md-subtitle">Сотрудник</div>
                    </div>
                    <div class="md-layout-item selector">
                        <md-field>
                            <md-select v-model="kpiEmployeeId" name="kpiEmployeeId" id="kpiEmployeeId" :md-selected="kpiEmployeeIdSelected()">
                                
                                <md-option v-for="employee in employees" v-bind:key="employee.login" :value="employee.login">{{employee.name}}</md-option>
                            </md-select>
                        </md-field>
                    </div>
                </div>
                <BaseReport v-if="showKpiReport" type="employee-kpi" :employee="selectedKpiEmployeeId"></BaseReport>
            </md-tab>
        </md-tabs>
    </div>
</template>

<script>
import BaseReport from './BaseReport';
export default {
    name: 'Report',
    components: {
        BaseReport
    },
    data() {
        return {
            consumptionPeriod: 1,
            workloadEmployeeId: "-1",
            selectedWorkloadEmployeeId: "-1",
            prevWorkloadEmployeeId: "",
            kpiEmployeeId: "-1",
            selectedKpiEmployeeId: "-1",
            prevKpiEmployeeId: "",
            employees: [],
            period: 1,
            prevPeriod: 1,
            selectedPeriod: 1
        }
    },

    mounted() {
        this.$store.dispatch("fetchUsers")
            .then((users) => {
                this.employees = users.filter(u => u.role == 'employee');
                this.employees.push({login: "asdf", name: "masfas", role: 'employee'})
            })
            .catch(console.log);
    },

    computed: {
        showBaseWorkloadReport() {
            return this.selectedWorkloadEmployeeId == "-1";
        },

        showKpiReport() {
            return this.selectedKpiEmployeeId != "-1";
        }
    },

    methods: {
        workloadEmployeeIdSelected() {
            if(this.prevWorkloadEmployeeId == this.workloadEmployeeId)
                return;
            this.prevWorkloadEmployeeId = this.workloadEmployeeId;
            this.selectedWorkloadEmployeeId = this.workloadEmployeeId;
        },

        kpiEmployeeIdSelected() {
            if(this.prevKpiEmployeeId == this.kpiEmployeeId)
                return;
            this.prevKpiEmployeeId = this.kpiEmployeeId;
            this.selectedKpiEmployeeId = this.kpiEmployeeId;
        },

        periodSelected() {
            if(this.period == this.prevPeriod)
                return;
            this.prevPeriod = this.period;
            this.selectedPeriod = this.period;
        }
    }
}

</script>


<style>

</style>
