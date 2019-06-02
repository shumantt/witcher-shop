<template>
    <div class="consumption-form">
        <div class="md-title">Расход ресурса</div>
        <div class="category selection">
            <div class="md-layout">
                <div class="md-layout-item label">
                    <div class="md-subtitle">Категория</div>
                </div>
                <div class="md-layout-item selector">
                     <md-field>
                        <md-select v-model="category" name="category" id="category" :md-selected="categorySelected()">
                            <md-option value="Травы">Травы</md-option>
                            <md-option value="Животные" :disabled="true">Животные</md-option>
                            <md-option value="Руны" :disabled="true">Руны</md-option>
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
                   <md-autocomplete v-model="name" :md-options="names" :md-selected="selectedName()"> 
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
                        <md-input v-model="number" type="number" :min="0" :max="max"></md-input>
                    </md-field>
                </div>
            </div>
        </div>
        <div class="buttons-row">
            <md-button class="md-raised left" :disabled="!actionEnabled" @click="makeConsumption()">Записать расход</md-button>
            <md-button class="md-raised right" @click="clear()">Очистить</md-button>
        </div>
        <div v-if="resultMessage">{{resultMessage}}</div>
    </div>
</template>

<script>
export default {
    name: 'Consumption',
    data() {
        return {
            category: null,
            resultMessage: null,
            name: null,
            number: 0,
            names: [],
            grass:[],
            max: 0
        }
    },
    computed: {
        actionEnabled() {
            return this.category != null && this.name != null && this.category != '' && this.name != '';
        }
    },
    methods: {
        clear() {
            this.category = null;
            this.name = null;
            this.number = 0;
            this.resultMessage = null;
            this.names = [];
        },
        categorySelected() {
            if(!this.category)
                return;
            this.$store.dispatch("fetchGrass")
                .then((grass) => {
                    this.names = grass.map(g => g.name);
                    this.grass = grass;
                })
                .catch((error) => console.log(error));
        },
        
        selectedName() {
            if(!this.name)
                return;
            let selectedGrass = this.grass.find(g => g.name == this.name);
            this.max = selectedGrass.quantity;
        },

        makeConsumption() {
            this.resultMessage = null;
            if(!this.category || !this.name) {
                this.resultMessage = "Укажите категорию и наименование ресурса";
                return;
            }

            let selectedGrass = this.grass.find(g => g.name == this.name);

            if(this.number < 0 || this.number > selectedGrass.quantity) {
                this.resultMessage = `Неверное количество. Введите количестов от ${0} до ${this.number}`;
                return;
            }

            this.$store.dispatch("consumptGrass", { id: selectedGrass.id, reduceBy: this.number })
                .then(() => {
                    let res = `Успешны записан расход. Категория: ${this.category}. Наименование: ${this.name}. Количество: ${this.number}.`;
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
