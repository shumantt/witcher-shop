
import Vue from "vue";
import Vuex from "vuex";
import {AuthService, RecipesService, GrassService, OrdersService, ManagingService} from "@/services/rest.service";

Vue.use(Vuex);

const state = {
    user: {},
    authError: ''
};

const mutations = {
    setUser(state, user) {
        state.user = user;
    },

    setAuthError(state, error) {
        state.authError = error;
    }
};

const actions = {
    auth(context, userAuthInfo) {
        // eslint-disable-next-line no-unused-vars
        const {login, password} = userAuthInfo;
        return new Promise((resolve, reject) => {
            AuthService.auth(userAuthInfo)
                .then((response) => {
                    context.commit("setUser", response.data);
                    resolve(response.data);
                })
                .catch((error) => reject(error));
        });
    },

    fetchRecipes() {
        return new Promise((resolve, reject) => {
            RecipesService.getAll()
                .then((response) => resolve(response.data))
                .catch(error => reject(error));
        });
    },

    fetchGrass() {
        return new Promise((resolve, reject) => {
            GrassService.getAll()
                .then((response) => resolve(response.data))
                .catch(error => reject(error));
        });
    },

    fetchUsers() {
        return new Promise((resolve, reject) => {
            ManagingService.getUsers()
                .then((response) => resolve(response.data))
                .catch(error => reject(error));
        });
    },

    addUser(context, userRequest) {
        return new Promise((resolve, reject) => {
            ManagingService.createUser(userRequest)
                .then((response) => resolve(response.data))
                .catch(error => reject(error));
        });
    },


    consumptGrass(context, consumptionRequest) {
        return new Promise((resolve, reject) => {
            GrassService.consumptGrass(consumptionRequest)
                .then((response) => resolve(response.data))
                .catch(error => reject(error));
        });
    },

    fetchOrders() {
        return new Promise((resolve, reject) => {
            OrdersService.getAll()
                .then((response) => resolve(response.data))
                .catch(error => reject(error));
        });
    },

    addOrder(context,order) {
        return new Promise((resolve, reject) => {
            OrdersService.addOrder(order)
                .then((response) => resolve(response.data))
                .catch(error => reject(error));
        });
    }
};

const store = new Vuex.Store({
    state: state,
    mutations: mutations,
    actions: actions,
  });

export default store;
