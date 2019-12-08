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
                    <md-button class="md-raised" @click="filterRecipes">Искать</md-button>
                    <md-button class="md-raised" @click="addRecipe" v-if="isWorker">Добавить рецепт</md-button>
                </div>
            </div>
        </div>
        <div class="search-options">
            <div class="md-layout ">
                <div class="md-layout-item">
                    <md-checkbox v-model="includeSimple" class="md-primary">Простые</md-checkbox>
                </div>
                <div class="md-layout-item selector">
                    <md-field >
                        <label for="category">Категория</label>
                        <md-select v-model="category" name="category" id="category">
                            <md-option value="">Все</md-option>
                            <md-option value="Несмертельные">Несмертельные</md-option>
                            <md-option value="Смертельные">Смертельные</md-option>
                            <md-option value="Усиливающие">Усиливающие</md-option>
                        </md-select>
                    </md-field>
                </div>
            </div>
            <div class="md-layout">
                <div class="md-layout-item">
                    <md-checkbox v-model="includeEnchanted" class="md-primary">Зачарованные</md-checkbox>
                </div>
                <div class="md-layout-item">
                    <md-checkbox v-model="includeEnoughResources" class="md-primary">Достаточно ресурсов</md-checkbox>
                </div>
            </div>
        </div>
        <div class="table-container">
            <md-table v-model="filteredRecipes" @md-selected="onSelect" class="main-table" >
                <md-table-row slot="md-table-row" slot-scope="{ item }" class="md-primary" md-selectable="single">
                    <md-table-cell md-label="Ресурсы">
                        <md-checkbox v-model="item.enoughResources" class="md-primary" disabled></md-checkbox>
                    </md-table-cell>
                    <md-table-cell md-label="Название">{{ item.name }}</md-table-cell>
                    <md-table-cell md-label="Категоря">{{ item.category }}</md-table-cell>
                    <md-table-cell md-label="Компоненты">{{ item.components }}</md-table-cell>
                    <md-table-cell md-label="На складе">{{item.potionQuantity}}</md-table-cell>
                </md-table-row>
            </md-table>
        </div>
        <div class="selected-info" v-if="selected">
             <md-card>
                <md-card-header>
                    <md-card-header-text>
                        <div class="md-title">{{selected.name}}</div>
                        <div class="md-subhead">{{selected.category}}</div>
                    </md-card-header-text>
                </md-card-header>

                <md-card-content>
                    {{selected.components}}
                </md-card-content>

                <md-card-actions>
                    <md-button class="md-raised" @click="cookClick()">Приготовить</md-button>
                    <md-button class="md-raised" @click="moreClick()">Подробнее</md-button>
                </md-card-actions>
            </md-card>
        </div>
        <md-dialog :md-active.sync="successCook">
            <div class="modal-content">
                <div>Зелье приготовлено успешно.</div>
                <div>Количество зелья на складе обновилось.</div>
            </div>
            <md-dialog-actions>
                <md-button class="md-primary" @click="successCook = false">Ок</md-button>
            </md-dialog-actions>
        </md-dialog>
    </div>
</template>

<script>
export default {
    name: 'Receipts',
    mounted() {
        this.fetchRecipes();
    },
    data() {
        return {
            category: null,
            searchText:  null,
            includeSimple: true,
            includeEnchanted: true,
            includeEnoughResources: true,
            selected: null,
            recipes: [],
            filteredRecipes: [],
            successCook: false,
            isWorker: this.$store.getters.isWorker
        }
    },
    computed: {
    },
    methods: {
        onSelect(recipe) {
            this.selected = recipe;
        },

        filterRecipes() {
            let filtered = this.recipes.slice(0);
            if(this.category) {
                filtered = filtered.filter((recipe) => recipe.category == this.category);
            }

            if(this.searchText) {
                filtered = filtered.filter((recipe) => recipe.name.includes(this.searchText));
            }
            this.filteredRecipes = filtered;
        },

        fetchRecipes() {
            this.$store.dispatch("fetchRecipes")
                .then((recipes) => {
                    this.recipes = recipes;
                    this.filterRecipes();
                })
                .catch(console.log);
        },
        
        cookClick() {
            this.$store.dispatch("cookPotion", this.selected.id)  
                .then((_) => {
                    this.successCook = true;
                    this.fetchRecipes();
                })
                .catch(console.log)
        },
        
        moreClick() {
            this.$router.push(`/info?type=receipts&id=${this.selected.id}`);
        } ,

        addRecipe() {
            this.$router.push('/receipts/add');
        }
    }
}
</script>

<style scoped>
    .search-options .md-layout-item {
        max-width: 300px;
    }
    
    .main-table {
        max-height: 340px;
    } 
    
    .selected-info {
        max-height: 100px;
    }
    
    .modal-content {
        padding: 10px;
    }
</style>
