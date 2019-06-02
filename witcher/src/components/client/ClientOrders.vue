<template>
    <div>
        <md-list>
            <md-list-item v-for="order in orders" v-bind:key="order.id">
                <span>Заказ №{{order.id}}</span>
                <span>{{order.category}}</span>
                <span>{{order.name}}</span>
                <span>{{order.quantity}} шт.</span>
            </md-list-item>
        </md-list>
    </div>
</template>

<script>
export default {
    name: "ClientOrders",
    data() {
        return {
            orders: []
        }
    },
    mounted() {
       this.$store.dispatch("fetchOrders")
            .then((orders) => this.orders = orders)
            .catch((error) => console.log(error));
    }
}
</script>

<style>

</style>
