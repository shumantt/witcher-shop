<template>
    <div class="report-container">
         <md-tabs>
            <md-tab id="tab-base" md-label="Общий" exact>
                <BaseReport/>
            </md-tab>

            <md-tab id="tab-consumptions" md-label="Расходы" >
                Расходы
            </md-tab>

            <md-tab id="tab-workload" md-label="Загруженность" >
                Загруженность
            </md-tab>

            <md-tab id="tab-kpi" md-label="KPI" >
                KPI
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
    }
}
</script>


<style>

</style>