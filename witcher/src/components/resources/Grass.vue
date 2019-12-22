<template>
    <div class="resource-container">
        <div class="text-search">
            <div class="md-layout ">
                <div class="md-layout-item">
                    <md-field>
                        <md-icon >search</md-icon>
                        <md-input v-model="searchText" v-on:keyup.enter="filterGrass()"></md-input>
                    </md-field>
                </div>
                <div class="md-layout-item">
                    <md-button class="md-raised" @click="filterGrass">Искать</md-button>
                </div>
            </div>
        </div>
        <div class="table-container">
            <md-table v-model="filteredGrass" class="main-table"  @md-selected="onSelect" >
                <md-table-row slot="md-table-row" slot-scope="{ item }" class="md-primary" md-selectable="single">
                    <md-table-cell md-label="Название">{{ item.name }}</md-table-cell>
                    <md-table-cell md-label="Количество">{{ item.quantity }}</md-table-cell>
                    <md-table-cell md-label="Описание">{{ item.description }}</md-table-cell>
                </md-table-row>
            </md-table>
        </div>
        <div class="selected-info" v-if="selected">
            <md-card>
                <md-card-header>
                    <md-card-header-text>
                        <div class="md-title">{{selected.name}}</div>
                    </md-card-header-text>
                </md-card-header>

                <md-card-content>
                    {{selected.description}}
                </md-card-content>

                <md-card-actions>
                    <md-button class="md-raised" @click="moreClick()">Подробнее</md-button>
                </md-card-actions>
            </md-card>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Grass',
    data() {
        return {
            grass: [],
            filteredGrass: [],
            selected: null,
            searchText: "",
        }
    },
    mounted() {
        this.$store.dispatch("fetchGrass")
            .then((grass) => {
                this.grass = grass;
                this.filteredGrass = grass;
            })
            .catch((error) => console.log(error));
    },
    
    methods: {
        moreClick() {
            this.$router.push(`/info?type=grass&id=${this.selected.id}`);
        },
        
        onSelect(grass) {
            this.selected = grass;
        },
        
        filterGrass() {
            this.filteredGrass = this.grass;
            if(this.searchText) {
                this.filteredGrass = this.grass.filter(r => r.name.includes(this.searchText));
            }
        }
    } 
}
</script>

<style scoped>
    .main-table {
        height: 480px;
    }
</style>