<template>
    <div>
        <md-list>
            <md-list-item v-for="order in orders" v-bind:key="order.id">
                <span>Заказ №{{order.id}}</span>
                <span>{{order.potionName}}</span>
                <span>{{order.quantity}} шт.</span>
                <span>{{getStatusName(order.status)}}</span>
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
       this.$store.dispatch("fetchClientOrders")
            .then((orders) => this.orders = orders)
            .catch((error) => console.log(error));
    },
    
    methods: {
        getStatusName(statusId) {
            switch (statusId) {
                case 0: return "Принят";
                case 1: return "Готов к выдаче";
                case 2: return "Закрыт";
            }
        }
    }
}
</script>

<style>

</style>
