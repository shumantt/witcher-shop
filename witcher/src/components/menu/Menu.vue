<template>
    <div>
        <ClientMenu v-if="isClient"/>
        <EmployeeMenu v-if="isEmployee"/>
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
            
        }
    },
    computed: {
        isClient() {
            return this.$store.state.user.role == "CLIENT";
        },
        isEmployee() {
            return this.$store.state.user.role == "EMPLOYEE" || this.$store.state.user.role == "MANAGER";
        }
    }
}
</script>

<style>

</style>
