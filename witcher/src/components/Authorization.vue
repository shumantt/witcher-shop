<template>
    <div class="form">
        <h1 class="md-title">Вход</h1>
        <md-field md-clearable>
            <label>Логин</label>
            <md-input v-model="login" v-on:keyup.enter="auth()"></md-input>
        </md-field>
        <md-field md-clearable>
            <label>Пароль</label>
            <md-input v-model="password" type="password" v-on:keyup.enter="auth()"></md-input>
        </md-field>
        <div class="buttons-row">
            <md-button class="md-raised left" @click="auth()">Войти</md-button>
            <md-button class="right">Забыли пароль?</md-button>
        </div>
        <div v-if="error">
            <p>{{error}}</p>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Authorization',
    data() {
        return {
            login: null,
            password: null,
            error: ''
        }
    },
    methods: {

        auth() {
            this.error = null;
            if(!this.login) {
                this.error = "Заполните поле Логин";
                return;
            }

            if(!this.password) {
                this.error = "Заполните поле Пароль";
                return;
            }
            this.$store.dispatch("auth", { login: this.login, password: this.password })
                .then((user) => this.processAuth(user))
                .catch((error) => {
                    this.error = "Пользователь или пароль неверные";
                });
        },

        processAuth(user) {
            console.log(user);
            console.log("user");
            switch(user.role) {
                case "employee":
                    this.$router.push("/receipts");
                    break;
                case "manager":
                    this.$router.push("/report");
                    break;
                case "client":
                    this.$router.push("/client-orders");
                    break;
            }
            console.log(user.role);
        }
    }
}
</script>

<style>
.form {
    border: solid 1px;
    text-align: center;
    width: 500px;
    padding: 15px;
    margin: auto;
}

/* .left {
    float: left;
}*/

</style>
