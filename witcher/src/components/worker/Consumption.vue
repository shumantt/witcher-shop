<template>
    <div class="consumption-form">
        <div class="md-title">Расход/приход ресурса</div>
        <ResourceSelection ref="selector"
                v-on:quantity-change="quantityChanged"
                v-on:type-change="typeChanged"
                v-on:name-change="nameChanged"
                v-on:id-change="idChanged"
        />
        <div class="buttons-row">
            <md-button class="md-raised left" :disabled="!actionEnabled" @click="makeConsumption()">Записать расход</md-button>
            <md-button class="md-raised right" :disabled="!actionEnabled" @click="addResource()">Записать приход</md-button>
        </div>
        <div v-if="resultMessage">{{resultMessage}}</div>
    </div>
</template>

<script>
import ResourceSelection from "../resources/ResourceSelection";
export default {
    name: 'Consumption',
    components: {ResourceSelection},
    data() {
        return {
            type: "",
            name: "",
            resultMessage: null,
            id: -1,
            number: 1,
        }
    },
    computed: {
        actionEnabled() {
            return this.number > 0 && this.type && this.name;
        }
    },
    methods: {
        clear() {
            this.type = "";
            this.name = "";
            this.number = 0;
            this.id = -1;
            this.resultMessage = null;
            this.$refs.selector.clear();
        },
        quantityChanged(value) {
            this.number = value;
        },

        typeChanged(value) {
            this.type = value;
        },

        nameChanged(value) {
            this.name = value;
        },

        idChanged(value) {
            this.id = value;
        },

        makeConsumption() {
            this.resultMessage = null;
            if(!this.type || !this.name) {
                this.resultMessage = "Укажите категорию и наименование ресурса";
                return;
            }

           /* if(this.number < 0 || this.number > selectedGrass.quantity) {
                this.resultMessage = `Неверное количество. Введите количестов от ${0} до ${this.number}`;
                return;
            }*/

            this.$store.dispatch("consumpt", { id: this.id, type: this.type, amount: this.number, isPlus: false })
                .then(() => {
                    let res = `Успешно записан расход. Наименование: ${this.name}. Количество: ${this.number}.`;
                    this.clear();
                    this.resultMessage = res;
                })
                .catch((error) => {
                    console.log(error);
                    this.resultMessage = "Ошибка";
                });
        },
        
        addResource() {
            this.resultMessage = null;
            if(!this.type || !this.name) {
                this.resultMessage = "Укажите категорию и наименование ресурса";
                return;
            }

            if(this.number < 0) {
                this.resultMessage = `Неверное количество. Введите количестов больше 0`;
                return;
            }

            this.$store.dispatch("consumpt", { id: this.id, type: this.type, amount: this.number, isPlus: true })
                .then(() => {
                    let res = `Успешно записан приход. Наименование: ${this.name}. Количество: ${this.number}.`;
                    this.clear();
                    this.resultMessage = res;
                })
                .catch((error) => {
                    console.log(error);
                    this.resultMessage = "Ошибка";
                });
        }
    }
}
</script>

<style lang="scss">
    .consumption-form {
        max-width: 500px;
        margin-top: 10%;
        margin-left: 15%;
        text-align: center;
    }
    .label {
        margin: auto;
        text-align: left;
        max-width: 200px;
    }
</style>
