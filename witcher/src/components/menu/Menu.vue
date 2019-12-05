<template>
    <div>
        <md-toolbar class="md-large md-primary" md-elevation="0">
          <div>
            <md-avatar class="md-avatar-icon md-large md-accent">
              <img :src="img" alt="Avatar">
            </md-avatar>
          </div>
          <div class="user-name">{{userName}}</div>
        </md-toolbar>
        <div>
            <ClientMenu v-if="isClient"/>
            <EmployeeMenu v-if="isEmployee"/>
            <md-divider></md-divider>
            <md-button v-if="isLoggedIn" @click="logout()">Выйти</md-button>
        </div>
    </div>
</template>

<script>
import ClientMenu from '@/components/menu/ClientMenu'
import EmployeeMenu from '@/components/menu/EmployeeMenu.vue'
export default {
    name: 'Menu',
    components: {
        ClientMenu,
        EmployeeMenu
    },
    data() {
        return {
            img: this.$store.state.user.pictureUrl,
            userName:  this.$store.state.user.name,
            isEmployee: this.$store.state.user.role == "EMPLOYEE" || this.$store.state.user.role == "MANAGER",
            isClient: this.$store.state.user.role == "CLIENT"
        }
    },
    computed: {
        isLoggedIn() {
            return this.$store.getters.isLoggedIn
        }
    },
    methods: {
        logout() {
            this.$store.dispatch('logout')
                .then(() => this.$router.push('/auth'))
        }
    }
}
</script>

<style>

</style>
