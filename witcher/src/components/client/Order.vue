<template>
     <div class="consumption-form">
        <div class="md-title">Оформление заказа</div>
        <div class="name selection">
            <div class="md-layout">
                <div class="md-layout-item label">
                    <div class="md-subtitle">Зелье</div>
                </div>
                 <div class="md-layout-item selector">
                    <md-field>
                        <md-select v-model="id">
                            <md-option v-for="recipe in recipes" v-bind:key="recipe.id" :value="recipe.id">{{recipe.name}}</md-option>
                        </md-select>
                    </md-field>
                </div>
            </div>
        </div>
        <div class="quantity selection">
            <div class="md-layout">
                <div class="md-layout-item label">
                    <div class="md-subtitle">Количество</div>
                </div>
                <div class="md-layout-item selector">
                     <md-field>
                        <md-input v-model="number" type="number" min="0" max="10"></md-input>
                    </md-field>
                </div>
            </div>
        </div>
        <div class="phone">
            <div class="md-layout">
                <div class="md-layout-item label">
                    <div class="md-subtitle">Телефон для связи</div>
                </div>
                <div class="md-layout-item selector">
                     <md-field>
                        <md-input v-model="phone" type="text" placeholder="+79817778394"></md-input>
                    </md-field>
                </div>
            </div>
        </div>
        <div class="buttons-row">
            <md-button class="md-raised left" :disabled="!enabledAction" @click="makeOrder()">Оформить</md-button>
            <md-button class="md-raised right" @click="clear()">Очистить</md-button>
        </div>
        <div v-if="resultMessage">{{resultMessage}}</div>
    </div>
</template>

<script>
export default {
    name: 'Order',
    data() {
        return {
            id: 0,
            number: 0,
            phone: null,
            resultMessage: null,
            recipes:[]
        }
    },
    mounted() {
         this.fetchRecipes();
    },
    computed: {
        enabledAction() {
            return this.id > 0 && this.number > 0 && this.phone;
        }
    },
    methods: {
        fetchRecipes() {
            this.$store.dispatch("fetchRecipes")
                .then((recipes) => {
                    console.log("fetching recipes");
                    this.recipes = recipes;
                })
                .catch((error) => console.log(error));
        },

        clear() {
            this.id = undefined;
            this.number = 0;
            this.phone = undefined;
        },

        makeOrder() {
            this.resultMessage = null;
            if(this.id <= 0) {
                this.resultMessage = "Выберите зелье";
                return;
            }

            if(this.number <= 0) {
                this.resultMessage = "Количестов должно быть больше 0";
                return;
            }
            let order = {
                potionId: this.id,
                quantity: this.number,
                clientLogin: this.$store.state.user.login,
                phone: this.phone
            };
            this.$store.dispatch("addOrder", order)
                .then(() =>  {
                    this.resultMessage = "Заказ оформлен успешно";
                    this.clear();
                })
                .catch((error) => {
                    this.resultMessage = "Ошибка";
                    console.log(error);
                });
        }
    }
}
</script>


<style>

</style>
