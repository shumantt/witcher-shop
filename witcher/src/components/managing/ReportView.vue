<template>
    <div>
        <div>
            <md-button @click="back()"><md-icon>arrow_back</md-icon></md-button>
        </div>
        <div>
            <BaseReport :type="type" :employee="employeeId"/>
        </div>
    </div>
    
</template>

<script>
    import BaseReport from "./BaseReport";
    export default {
        name: "ReportView",
        components: {BaseReport},
        data() {
            return {
                type: this.$route.query.type,
                employeeId: this.$route.query.employeeId
            }
        },
        
        methods:{
            back() {
                this.$router.go(-1);
            }
        }
    }
</script>

<style scoped>

</style>