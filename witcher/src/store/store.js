
import Vue from "vue";
import Vuex from "vuex";
import {AuthService, RecipesService, GrassService, OrdersService, ManagingService} from "@/services/rest.service";
import {AnimalsService, ReportService, ResourceService, RunesService} from "../services/rest.service";

Vue.use(Vuex);


let user = null;
if (localStorage.user) {
    user = JSON.parse(localStorage.user)
}

const state = {
    user: user,
    authError: ''
};

const getters = {
    isLoggedIn: state => !!state.user,
    isEmployee: state =>  state.user && state.user.role == "employee" || state.user.role == "manager",
    isManager: state => state.user && state.user.role == "manager",
    isWorker: state => state.user && state.user.role == "employee",
    isClient: state => state.user && state.user.role == "client"
}

const mutations = {
    setUser(state, user) {
        state.user = user;
        localStorage.user = JSON.stringify(user);
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

    logout(context) {
        context.commit("setUser", null);
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


    consumpt(context, consumptionRequest) {
        return new Promise((resolve, reject) => {
            ResourceService.consumpt({
                ...consumptionRequest,
                userLogin: context.state.user.login
            })
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

    fetchClientOrders(context) {
        return new Promise((resolve, reject) => {
            OrdersService.getClientOrders(context.state.user.login)
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
    },

    cookPotion(context, recipeId) {
        return new Promise((resolve, reject) => {
           RecipesService.cookByRecipe({
               recipeId: recipeId,
               userLogin: context.state.user.login
           })
               .then((response) => resolve(response.data))
               .catch(error => reject(error))
        });
    },

    updateOrderStatus(context, updateRequest) {
        return new Promise((resolve, reject) => {
            OrdersService.updateStatus(updateRequest)
                .then(() => resolve())
                .catch(error => reject(error))
        });
    },

    fetchRunes() {
        return new Promise((resolve, reject) => {
            RunesService.getAll()
                .then((response) => resolve(response.data))
                .catch(error => reject(error))
        });
    },

    enchantRune(context, runeId) {
        return new Promise((resolve, reject) => {
            RunesService.enchantRune(runeId,context.state.user.login)
                .then(() => resolve())
                .catch(error => reject(error))
        });
    },

    fetchAnimals() {
        return new Promise((resolve, reject) => {
            AnimalsService.getAll()
                .then((response) => resolve(response.data))
                .catch(error => reject(error))
        });
    },

    getResourceInfo(context, {type, id}) {
        return new Promise((resolve, reject) => {
            ResourceService.getResourceInfo(type, id)
                .then((response) => resolve(response.data))
                .catch(error => reject(error))
        });
    },

    addReceipt(context, request) {
        return new Promise((resolve, reject) => {
            RecipesService.addReceipt({
                ...request,
                userLogin: context.state.user.login
            })
                .then(() => resolve())
                .catch(error => reject(error))
        });
    },

    getBaseReport(context, {type, employeeId, period}) {
        return new Promise((resolve, reject) => {
            switch (type) {
                case "base":
                    return ReportService.getBaseReport()
                        .then((response) => resolve(response.data))
                        .catch(error => reject(error));
                case "consumption":
                    return ReportService.getConsumptionReport(period)
                        .then((response) => resolve(response.data))
                        .catch(error => reject(error));
                case "workload":
                    return ReportService.getWorkloadReport()
                        .then((response) => resolve(response.data))
                        .catch(error => reject(error));
                case "employee-workload":
                    return ReportService.getEmployeeWorkloadReport(employeeId)
                        .then((response) => resolve(response.data))
                        .catch(error => reject(error));
                case "employee-kpi":
                    return ReportService.getEmployeeKpiReport(employeeId)
                        .then((response) => resolve(response.data))
                        .catch(error => reject(error));
            }
        });
    },
};

const store = new Vuex.Store({
    state: state,
    mutations: mutations,
    actions: actions,
    getters: getters,
  });

export default store;
