<template>
    <div class="form">
        <h1 class="md-title">Вход</h1>
        <md-field md-clearable>
            <label>Логин</label>
            <md-input v-model="login" ></md-input>
        </md-field>
        <md-field md-clearable>
            <label>Пароль</label>
            <md-input v-model="password" type="password"></md-input>
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
            this.$store.dispatch("auth", { username: this.login, password: this.password })
                .then((authResp) => this.processAuth(authResp.user))
                .catch((error) => {
                    this.error = "Пользователь или пароль неверные";
                });
        },

        processAuth(user) {
            console.log(user);
            switch(user.roles[0]) {
                case "EMPLOYEE":
                    this.$router.push("/home/receips");
                    break;
                case "MANAGER":
                    this.$router.push("/managing/report");
                    break;
                case "CLIENT":
                    this.$router.push("/client/order");
                    break;
            }
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
