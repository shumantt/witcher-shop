<template>
    <div>
        <md-list>
            <md-list-item to="/receipts">
                <md-icon >receipt</md-icon>
                <span class="md-list-item-text">Рецепты</span>
            </md-list-item>
    
            <md-list-item to="/grass">
                <md-icon>spa</md-icon>
                <span class="md-list-item-text">Травы</span>
            </md-list-item>
    
            <md-list-item to="/animals">
                <md-icon>adb</md-icon>
                <span class="md-list-item-text">Животные</span>
            </md-list-item>
    
            <md-list-item to="/runes">
                <md-icon>photo_filter</md-icon>
                <span class="md-list-item-text">Руны</span>
            </md-list-item>
        </md-list>
        <md-divider></md-divider>
        <WorkerMenu v-if="isWorker" />
        <ManagerMenu v-if="isManager"/>
    </div>
</template>

<script>
import WorkerMenu from './WorkerMenu'
import ManagerMenu from "./ManagerMenu";
export default {
    name: 'EmployeeMenu',
    components: {
        ManagerMenu,
        WorkerMenu
    },
    data() {
        return {
            isWorker: this.$store.getters.isWorker,
            isManager: this.$store.getters.isManager
        }
    }
}
</script>

<style scoped>

</style>