<template>
    <div>
        <md-table v-model="users" class="main-table" md-card>
            <md-table-toolbar>
                <h1 class="md-title">Сотрудники</h1>
            </md-table-toolbar>
            <md-table-row slot="md-table-row" slot-scope="{ item }" >
                <md-table-cell md-label="Имя">{{ item.name }}</md-table-cell>
                <md-table-cell md-label="Должность">{{ item.role }}</md-table-cell>
            </md-table-row>
        </md-table>
        <div>
            <md-field>
                <label for="name">Имя</label>
                <md-input v-model="name" id="name"></md-input>
            </md-field>
            <md-field >
                <label for="role">Должность</label>
                <md-select v-model="role" name="role" id="role">
                    <md-option value="MANAGER">Менеджер</md-option>
                    <md-option value="EMPLOYEE">Сотрудник</md-option>
                </md-select>
            </md-field>
            <md-button class="md-raised" @click="addUser">Добавить</md-button>
        </div>
    </div>
</template>


<script>
export default {
    name: 'Employees',
    mounted() {
        this.$store.dispatch("fetchUsers")
            .then((users) => this.users = users)
            .catch(console.log);
    },
    data() {
        return {
            users: [],
            role: undefined,
            name: undefined
        }
    },
    methods: {
        addUser() {
            this.$store.dispatch("addUser", { login: this.name, role: this.role, password: 123 })
        }
    }
}
</script>
