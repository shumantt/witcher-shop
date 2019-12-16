<template>
    <div >
        <div>
            <md-button @click="back()"><md-icon>arrow_back</md-icon></md-button>
            <h2 class="header">Информация о ресурсе</h2>
        </div>
        <span></span>
        <div class="container">
            <div class="label">
                Название
            </div>
            <div class="value">
                {{info.name}}
            </div>
        </div>
        <div class="container">
            <div class="label">
                Описание
            </div>
            <div class="value">
                {{info.description}}
            </div>
        </div>
        <div class="container">
            <div class="label">
                Количество на складе
            </div>
            <div class="value">
                {{info.quantity}}
            </div>
        </div>
        <div class="table-container">
            <md-table v-model="info.history" class="main-table" >
                <md-table-row slot="md-table-row" slot-scope="{ item }" class="md-primary">
                    <md-table-cell md-label="Кто добыл">{{ item.name }}</md-table-cell>
                    <md-table-cell md-label="Количество">{{ item.quantity }}</md-table-cell>
                    <md-table-cell md-label="Дата">{{ item.date }}</md-table-cell>
                </md-table-row>
            </md-table>
        </div>
    </div>
</template>

<script>
    // const fakeData = {
    //   name: "Драконьи слезы",
    //   description: "Слезы страшного дракона",
    //   quantity: 5,
    //   history: [{
    //       name: 'Иванов Сергей Михайлович',
    //       quantity: 5,
    //       date: "01.10.2019"
    //   }]  
    // };
    export default {
        name: "ResourceInfo",
        data() {
            return {
                info: {
                    name: "",
                    description: "",
                    quantity: 0,
                    history: []
                },
            }
        },
        
        mounted() {
            let type = this.$route.query.type;
            let id = this.$route.query.id;
            this.$store.dispatch("getResourceInfo", {type, id})
                .then(info => {
                    this.info = info;//fakeData;
                })
                .catch(console.log);
        },
        
        methods: {
            back() {
                this.$router.go(-1);
            }
        }
    }
</script>

<style scoped>
    .header {
        text-align: center;
    }
    
    .label {
        font-weight: bold;
        padding-top: 10px;
        padding-bottom: 10px;
        display: inline-block;
        width: 300px;
    }
    
    .value {
        display: inline-block;
    }
    
    .container {
        max-width: 100%;
    }
    
    .back {
        text-align: left;
    }
    
    
</style>