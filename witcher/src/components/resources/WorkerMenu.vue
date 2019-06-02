<template>
    <div>
         <md-list>
          
          <BaseMenu/>

          <md-divider></md-divider>

          <md-list-item to="/home/consumption">
            <md-icon>arrow_downward</md-icon>
            <span class="md-list-item-text">Расход</span>
          </md-list-item>

          <md-list-item>
            <md-icon>local_drink</md-icon>
            <span class="md-list-item-text">Зелья</span>
          </md-list-item>

          <md-list-item>
            <md-icon>shopping_cart</md-icon>
            <span class="md-list-item-text">Заказы</span>
          </md-list-item>

        </md-list>
    </div>
</template>

<script>
import BaseMenu from '@/components/BaseMenu.vue';
export default {
    name: 'WorkerMenu',
    components: {
      BaseMenu
    }

}
</script>
