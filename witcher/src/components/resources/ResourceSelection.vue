<template>
    <div>
        <div class="category selection">
            <div class="md-layout">
                <div class="md-layout-item label">
                    <div class="md-subtitle">Категория</div>
                </div>
                <div class="md-layout-item selector">
                    <md-field>
                        <md-select v-model="type" name="category" id="category" :md-selected="typeSelected()">
                            <md-option value="grass">Травы</md-option>
                            <md-option value="animals">Животные</md-option>
                            <md-option value="runes">Руны</md-option>
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
                    <md-field>
                        <md-select v-model="name" :md-selected="selectedName()">
                            <md-option v-for="name in names" v-bind:key="name" :value="name">{{name}}</md-option>
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
                        <md-input v-model="quantity" type="number" :min="1" :max="100"></md-input>
                    </md-field>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    const fakeAnimals =
        [{
            id: 2,
            name: "Noga Zver",
            description: "Ogramnaya lapa s kogtyami",
            quantity: 12
        },
            {
                id: 3,
                name: "2Noga Zver",
                description: "2Ogramnaya lapa2 s kogtyami",
                quantity: 5
            }];

    const fakeRunes = [{
        id: 12,
        name: "Руна счастья",
        category: "Редкие",
        enchanted: false
    }];
    export default {
        name: "ResourceSelection",
        data() {
            return {
                type: "",
                prevType: "",
                name: "",
                prevName: "",
                id: -1,
                quantity: 1,
                grass: [],
                animals: [],
                runes: [],
                selectedValues: [],
                names:[]
            }
        },
        watch: {
            quantity: function(value) {
                this.$emit('quantity-change', value);
            }  
        },
        
        mounted() {
            this.$store.dispatch("fetchAnimals")
                .then((animals) => {
                    this.animals = fakeAnimals;
                })
                .catch((error) => console.log(error));

            this.$store.dispatch("fetchGrass")
                .then((grass) => {
                    this.grass = grass;
                })
                .catch((error) => console.log(error));

            this.$store.dispatch("fetchRunes")
                .then((runes) => {
                    this.runes = fakeRunes;
                })
                .catch(console.log);
        },
        
        methods: {
            typeSelected() {
                if (this.type == this.prevType)
                    return;
                this.name = "";
                this.prevName = "";
                this.prevType = this.type;
                this.$emit('type-change', this.type);
                switch (this.type) {
                    case "grass": 
                        this.selectedValues = this.grass.map(g => ({id: g.id, name: g.name}));
                        break;
                    case "animals":
                        this.selectedValues = this.animals.map(g => ({id: g.id, name: g.name}));
                        break;
                    case "runes":
                        this.selectedValues = this.runes.map(g => ({id: g.id, name: g.name}));
                        break;
                }
                this.names = this.selectedValues.map(v => v.name)
            },

            selectedName() {
                if (this.name == this.prevName)
                    return;
                this.prevName = this.name;
                this.$emit('name-change', this.name);
                this.$emit('id-change', this.selectedValues.find(v => v.name == this.name).id);
            },
        }
    }
</script>

<style scoped>
    .label {
        margin: auto;
        text-align: left;
        max-width: 200px;
    }
</style>