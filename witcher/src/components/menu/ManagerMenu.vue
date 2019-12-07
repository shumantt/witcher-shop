<template>
    <div>
         <md-list>
          <md-list-item to="/report">
            <md-icon>bar_chart</md-icon>
            <span class="md-list-item-text">Отчеты</span>
          </md-list-item>

          <md-list-item to="/employees">
            <md-icon>people</md-icon>
            <span class="md-list-item-text">Сотрудники</span>
          </md-list-item>

        </md-list>
    </div>
</template>

<script>

export default {
    name: 'ManagerMenu'
}
</script>


<style>

</style>
