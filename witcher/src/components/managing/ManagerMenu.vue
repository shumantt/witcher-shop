<template>
    <div>
         <md-list>
          
          <BaseMenu/>
          
          <md-divider></md-divider>

          <md-list-item to="/managing/report">
            <md-icon>bar_chart</md-icon>
            <span class="md-list-item-text">Отчеты</span>
          </md-list-item>

          <md-list-item>
            <md-icon>people</md-icon>
            <span class="md-list-item-text">Сотрудники</span>
          </md-list-item>

        </md-list>
    </div>
</template>

<script>
import BaseMenu from "@/components/BaseMenu.vue";

export default {
    name: 'ManagerMenu',
    components: {
        BaseMenu
    }
}
</script>


<style>

</style>
