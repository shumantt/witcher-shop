<template>
    <div>
        <div class="add-receipt-form">
            <div class="md-title">Добавление рецепта</div>
            <div class="name-input">
                <div class="md-layout">
                    <div class="md-layout-item label">
                        <div class="md-subtitle">Название</div>
                    </div>
                    <div class="md-layout-item">
                        <md-field>
                            <md-input name="name" id="name" v-model="name"></md-input>
                        </md-field>
                    </div>
                </div>
            </div>
            <div class="description-input">
                <div class="md-layout">
                    <div class="md-layout-item label">
                        <div class="md-subtitle">Описание</div>
                    </div>
                    <div class="md-layout-item">
                        <md-field>
                            <md-input name="description" id="description" v-model="description"></md-input>
                        </md-field>
                    </div>
                </div>
            </div>
            <div class="category selection">
                <div class="md-layout">
                    <div class="md-layout-item label">
                        <div class="md-subtitle">Категория</div>
                    </div>
                    <div class="md-layout-item selector">
                        <md-field >
                            <md-select v-model="category" name="category" id="category">
                                <md-option value="Несмертельные">Несмертельные</md-option>
                                <md-option value="Смертельные">Смертельные</md-option>
                                <md-option value="Усиливающие">Усиливающие</md-option>
                            </md-select>
                        </md-field>
                    </div>
                </div>
            </div>
            <div class="instruction-input">
                <div class="md-layout">
                    <div class="md-layout-item label">
                        <div class="md-subtitle">Инструкции</div>
                    </div>
                    <div class="md-layout-item">
                        <md-field>
                            <md-input name="instruction" id="instruction" v-model="instruction"></md-input>
                        </md-field>
                    </div>
                </div>
            </div>
        </div>
        <div class="table-container">
            <div class="label" v-if="ingredients.length > 0">
                <div class="md-subtitle">Ингридиенты</div>
            </div>
            <md-list>
                <md-list-item v-for="(ingredient, index) in ingredients" v-bind:key="index">
                    <span>{{getTypeName(ingredient.type)}}</span>
                    <span>{{ingredient.name}}</span>
                    <span>{{ingredient.requiredQuantity}} шт.</span>
                    <span><md-button @click="removeIngredient(index)"><md-icon>close</md-icon></md-button></span>
                </md-list-item>
            </md-list>
        </div>
        <div>
            <md-button class="md-raised left" @click="addingIngredient = true">Добавить ингредиент</md-button>
        </div>
       
        <div class="buttons-row">
            <md-button class="md-raised left" :disabled="!actionEnabled" @click="addReceipt()">Добавить рецепт</md-button>
            <md-button class="md-raised right" @click="clear()">Очистить</md-button>
        </div>
        <div v-if="resultMessage" class="result">{{resultMessage}}</div>

        <md-dialog :md-active.sync="addingIngredient">
            <div class="modal-content resource-form">
               <ResourceSelection 
                       v-on:quantity-change="quantityChanged"
                       v-on:type-change="typeChanged"
                       v-on:name-change="nameChanged"
                       v-on:id-change="idChanged"
               />
            </div>
            <md-dialog-actions>
                <md-button class="md-primary" @click="cancelAddIngredient()">Отмена</md-button>
                <md-button class="md-primary" @click="finishAddIngredient()" :disabled="!validIngredientForm">Ок</md-button>
            </md-dialog-actions>
        </md-dialog>
    </div>
</template>

<script>
    import ResourceSelection from "./ResourceSelection";
    export default {
        name: "AddReceipt",
        components: {ResourceSelection},
        data() {
            return {
                name: "",
                description: "",
                instruction: "",
                category: "",
                ingredients:[],
                resultMessage: "",
                addingIngredient: false,
                ingredientForm: {
                    type: "",
                    name: "",
                    id: -1,
                    quantity: 1
                }
            }
        },
        
        computed: {
            actionEnabled() {
                return this.category && this.description && this.instruction && this.name && this.ingredients.length > 0;
            },

            validIngredientForm() {
                return this.ingredientForm.type && this.ingredientForm.name && this.ingredientForm.id > 0 && this.ingredientForm.quantity > 0;
            }
        },
        
        methods: {
            clear() {
                this.name= "";
                this.description = "";
                this.instruction = "";
                this.category = "";
                this.ingredients = [];
                this.resultMessage = "";
            },

            finishAddIngredient() {
                this.ingredients.push({
                    type: this.ingredientForm.type,
                    name: this.ingredientForm.name,
                    id: this.ingredientForm.id,
                    requiredQuantity: parseInt(this.ingredientForm.quantity)
                });

                this.addingIngredient = false;
                this.ingredientForm= {
                    type: "",
                    name: "",
                    id: -1,
                    quantity: 1
                };
            },

            cancelAddIngredient() {
                this.addingIngredient = false;
                this.ingredientForm= {
                    type: "",
                    name: "",
                    id: -1,
                    quantity: 1
                };
            },
            
            removeIngredient(index) {
              this.ingredients.splice(index);  
            },
            
            quantityChanged(value) {
                this.ingredientForm.quantity = value;
            },
            
            typeChanged(value) {
                this.ingredientForm.type = value;
            },
            
            nameChanged(value) {
                this.ingredientForm.name = value;    
            },
            
            idChanged(value) {
                this.ingredientForm.id = value;
            },
            
            getTypeName(value) {
                switch (value) {
                    case "animals": return "Животное";
                    case "grass": return "Трава";
                    case "runes": return "Руна";
                }
            },
            
            addReceipt() {
                let request = {
                    name: this.name,
                    description: this.description,
                    instruction: this.instruction,
                    ingredients: this.ingredients
                };

                this.$store.dispatch("addReceipt", request)
                    .then(() => {
                        let res = `Успешно добавлен рецепт ${this.name}.`;
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

<style scoped>
    .add-receipt-form {
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
    
    .buttons-row {
        text-align: right;
    }
    
    .resource-form {
        margin: 10px;
    }
    
    .result {
        max-width: 500px;
        margin-left: 15%;
        text-align: center;
    }
</style>