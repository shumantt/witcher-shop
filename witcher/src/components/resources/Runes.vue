<template>
    <div class="resource-container">
        <div class="text-search">
            <div class="md-layout ">
                <div class="md-layout-item">
                    <md-field>
                        <md-icon >search</md-icon>
                        <md-input v-model="searchText" v-on:keyup.enter="filterRunes()"></md-input>
                    </md-field>
                </div>
                <div class="md-layout-item">
                    <md-button class="md-raised" @click="filterRunes">Искать</md-button>
                </div>
            </div>
        </div>
        <div class="table-container">
            <md-table v-model="filteredRunes" @md-selected="onSelect" class="main-table" >
                <md-table-row slot="md-table-row" slot-scope="{ item }" class="md-primary" md-selectable="single">
                    <md-table-cell md-label="Зачарована">
                        <md-checkbox v-model="item.enchanted" class="md-primary" disabled></md-checkbox>
                    </md-table-cell>
                    <md-table-cell md-label="Название">{{ item.name }}</md-table-cell>
                    <md-table-cell md-label="Категория">{{ item.categories[0] }}</md-table-cell>
                    <md-table-cell md-label="На складе">{{ item.quantity }}</md-table-cell>
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
                    {{selected.description}}
                </md-card-content>

                <md-card-actions>
                    <md-button class="md-raised" @click="enchantClick()" v-if="!selected.enchanted && isEmployee">Зачаровать</md-button>
                    <md-button class="md-raised" @click="moreClick()">Подробнее</md-button>
                </md-card-actions>
            </md-card>
        </div>
        <md-dialog :md-active.sync="successEnchant">
            <div class="modal-content">
                <div>Руна зачарована успешно.</div>
                <div>Ее статус обновился.</div>
            </div>
            <md-dialog-actions>
                <md-button class="md-primary" @click="successEnchant = false">Ок</md-button>
            </md-dialog-actions>
        </md-dialog>
    </div>
</template> 

<script>
    // const fakeData = [{
    //     id: 12,
    //     name: "Руна счастья",
    //     category: "Редкие",
    //     enchanted: false
    // }];
    
    export default {
        name: "Runes",
        data() {
            return {
                selected: null,
                runes: [],
                filteredRunes: [],
                successEnchant: false,
                searchText: "",
                isEmployee: this.$store.getters.isEmployee
            }
        },
        
        mounted() {
            this.fetchRunes();
        },
        
        methods: {
            onSelect(rune) {
                this.selected = rune;
            },
            
            moreClick() {
                this.$router.push(`/info?type=runes &id=${this.selected.id}`);
            },
            
            enchantClick() {
                this.$store.dispatch("enchantRune", this.selected.id)
                    .then(() => {
                        this.successEnchant = true;
                        this.fetchRunes();
                       // fakeData[0].enchanted = true; //TODO удалить
                    })
                    .catch(console.log)
            },
            
            fetchRunes() {
                this.$store.dispatch("fetchRunes")
                    .then((runes) => {
                        this.runes = runes;//fakeData;
                        this.filteredRunes = runes;// fakeData;
                    })
                    .catch(console.log);
            },

            filterRunes() {
                this.filteredRunes = this.runes;
                if(this.searchText) {
                    this.filteredRunes = this.runes.filter(r => r.name.includes(this.searchText));
                }
            }
        }
    }
</script>

<style scoped>
    .main-table {
        height: 480px;
    }
    
    .modal-content {
        margin: 10px;
    }
</style>