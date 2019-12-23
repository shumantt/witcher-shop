<template>
    <div class="resource-container">
        <div class="text-search">
            <div class="md-layout ">
                <div class="md-layout-item">
                    <md-field>
                        <md-icon >search</md-icon>
                        <md-input v-model="searchText" ></md-input>
                    </md-field>
                </div>
                <div class="md-layout-item">
                    <md-button class="md-raised" @click="filterOrders">Искать</md-button>
                </div>
            </div>
        </div>
        <div class="md-layout-item selector">
            <div class="md-layout selector">
                <md-field >
                    <label for="status">Статус</label>
                    <md-select v-model="searchStatus" name="status" id="status">
                        <md-option value="-1">Все</md-option>
                        <md-option value="0">Принят</md-option>
                        <md-option value="1">Готов к выдаче</md-option>
                        <md-option value="2">Закрыт</md-option>
                    </md-select>
                </md-field>
            </div>
        </div>
        <div class="table-container">
            <md-table v-model="filteredOrders" class="main-table" >
                <md-table-row slot="md-table-row" slot-scope="{ item }" class="md-primary">
                    <md-table-cell md-label="Заказ №">{{ item.id }}</md-table-cell>
                    <md-table-cell md-label="Зелье">{{ item.potionName }}</md-table-cell>
                    <md-table-cell md-label="Количество">{{ item.quantity }}</md-table-cell>
                    <md-table-cell md-label="Телефон">{{ item.phone }}</md-table-cell>
                    <md-table-cell md-label="Статус">
                        <div class="md-layout-item selector status-field">
                            <md-field>
                                <md-select v-model="item.status" name="status" id="status" :md-selected="updateStatus(item)">
                                    <md-option value="0" disabled>Принят</md-option>
                                    <md-option value="1">Готов к выдаче</md-option>
                                    <md-option value="2">Закрыт</md-option>
                                </md-select>
                            </md-field>
                        </div>
                    </md-table-cell>
                </md-table-row>
            </md-table>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Orders",
        data() {
            return {
                orders: [],
                filteredOrders: [],
                initialStatuses: {},
                searchText: "",
                searchStatus: ""
            }
        },
        
        mounted() {
            this.$store.dispatch("fetchOrders")
                .then((orders) => {
                    for (let i in orders) {
                        this.initialStatuses[orders[i].id] = orders[i].status;
                    }
                    this.orders = orders;
                    this.filteredOrders = orders;
                })
                .catch((error) => console.log(error));
        },
        
        methods: {
            updateStatus(order) {
                if(this.initialStatuses[order.id] == order.status)
                    return;
                let req = {orderId: order.id, newStatus: order.status};
                this.$store.dispatch("updateOrderStatus", req)
                    .catch(console.log);
            },

            filterOrders() {
                let result = this.orders;
                if(this.searchText) {
                    result = this.orders.filter(o => o.potionName.includes(this.searchText));
                }
                
                if(this.searchStatus && this.searchStatus != -1) {
                    result = result.filter(o => o.status == this.searchStatus);
                }
                
                this.filteredOrders = result;
            }
        }
    }
</script>

<style scoped>
    .status-field {
        width: 150px;
    }
    
    .selector {
        width: 150px;
    }
</style>