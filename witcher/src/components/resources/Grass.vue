<template>
    <div class="resource-container">
        <div class="table-container">
            <md-table v-model="grass" class="main-table" >
                <md-table-row slot="md-table-row" slot-scope="{ item }" class="md-primary">
                    <md-table-cell md-label="Название">{{ item.name }}</md-table-cell>
                    <md-table-cell md-label="Количество">{{ item.quantity }}</md-table-cell>
                    <md-table-cell md-label="Описание">{{ item.description }}</md-table-cell>
                </md-table-row>
            </md-table>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Grass',
    data() {
        return {
            grass: []
        }
    },
    mounted() {
        this.$store.dispatch("fetchGrass")
            .then((grass) => this.grass = grass)
            .catch((error) => console.log(error));
    }
}
</script>
