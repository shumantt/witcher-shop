<template>
     <div class="consumption-form">
        <div class="md-title">Оформление заказа</div>
        <div class="category selection">
            <div class="md-layout">
                <div class="md-layout-item label">
                    <div class="md-subtitle">Категория</div>
                </div>
                <div class="md-layout-item selector">
                     <md-field>
                        <md-select v-model="category" name="category" id="category" :md-selected="categorySelected()">
                            <md-option value="Несмертельные">Несмертельные</md-option>
                            <md-option value="Смертельные">Смертельные</md-option>
                            <md-option value="Усиливающие">Усиливающие</md-option>
                        </md-select>
                    </md-field>
                </div>
            </div>
        </div>
        <div class="name selection">
            <div class="md-layout">
                <div class="md-layout-item label">
                    <div class="md-subtitle">Наименование</div>
                </div>
                <div class="md-layout-item selector">
                   <md-autocomplete v-model="name" :md-options="names"> 
                   </md-autocomplete>
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
            category: null,
            name: null,
            number: 0,
            phone: null,
            resultMessage: null,
            recipes:[],
            namesFilterdBy: null
        }
    },
    mounted() {
         this.$store.dispatch("fetchRecipes")
                .then((recipes) => {
                    console.log("fetching recipes");
                    this.recipes = recipes;
                })
                .catch((error) => console.log(error));
    },
    computed: {
        enabledAction() {
            return this.category != null && this.name != null && this.category !== '' && this.name !== '' && this.number > 0;
        },
        names() {
            if(!this.category)
                return [];
            return this.recipes.filter((r) => r.category === this.category).map(r => r.name);
        }
    },
    methods: {
        clear() {
            this.category = null;
            this.name = null;
            this.number = 0;
            this.phone = null;
        },
        categorySelected() {
            if(!this.category || this.namesFilterdBy === this.category)
                return;
            this.namesFilterdBy = this.category;
            this.name = null;
            this.$forceUpdate();
        },

        makeOrder() {
            this.resultMessage = null;
            if(!this.category && !this.name) {
                this.resultMessage = "Укажите категорию и наименование зелья";
                return;
            }

            if(this.number <= 0) {
                this.resultMessage = "Количестов должно быть больше 0";
                return;
            }
            let selectedRecipe = this.recipes.find(r => r.name === this.name && r.category === this.category);
            let order = {
                potionId: selectedRecipe.id,
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
