<template>
    <div>
        <md-list>
          <md-list-item to="/client/order">
            <md-icon >add_shopping_cart</md-icon>
            <span class="md-list-item-text">Оформить заказ</span>
          </md-list-item>
          <md-list-item to="/client/orders">
            <md-icon >bookmarks</md-icon>
            <span class="md-list-item-text">Мои заказы</span>
          </md-list-item>
        </md-list>
    </div>
</template>


<script>
export default {
    name: 'ClientMenu'
}
</script>


<style>

</style>
