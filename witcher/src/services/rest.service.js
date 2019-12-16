import axios from "axios";

export const AuthService = {
    auth(authInfo) {
        return axios.post("http://localhost:8080/auth/signin", authInfo);
    }
};

export const GrassService = {
    getAll() {
        return axios.get("http://localhost:8080/api/resources/grass");
    },
}

export const RecipesService = {
    getAll() {
        return axios.get("http://localhost:8080/api/resources/recipes");
    },
<<<<<<< HEAD

    cookByRecipe(recipeId) {
        return axios.post("http://localhost:8080/api/resources/recipes/cook", {recipeId});
=======

    cookByRecipe(request) {
        return axios.post("http://localhost:8080/api/resources/recipes/cook", request);
>>>>>>> 3049fc6cc7f14d0c232e33f510c5428330a4618e
    },

    addReceipt(request) {
        return axios.post("http://localhost:8080/api/resources/recipes", request);
    }
}

export const ManagingService = {
    getUsers() {
        return axios.get("http://localhost:8080/api/users");
    },
    createUser({login, password, role}) {
        return axios.post("http://localhost:8080/api/user", { login, password, role })
    }
}

export const OrdersService = {
    getAll() {
        return axios.get("http://localhost:8080/api/orders");
    },

    addOrder(order) {
        return axios.post("http://localhost:8080/api/orders", order);
    },

    getClientOrders(clientLogin) {
        return axios.get("http://localhost:8080/api/orders?clientLogin=" + clientLogin);
    },

    updateStatus(updateRequest) {
        return axios.post("http://localhost:8080/api/orders/status", updateRequest);
    }
}

export const RunesService = {
    getAll() {
        return axios.get("http://localhost:8080/api/resources/runes");
    },

    enchantRune(runeId, userLogin) {
        return axios.post("http://localhost:8080/api/resources/runes/enchant", {recipeId: runeId, userLogin: userLogin});
    }
}

export const AnimalsService = {
    getAll() {
        return axios.get("http://localhost:8080/api/resources/animals");
    }
}

export const ResourceService = {
    getResourceInfo(type, id) {
        return  axios.get(`http://localhost:8080/api/resources/info?type=${type}&id=${id}`);
    },

    consumpt(consumptionInfo) {
        return axios.post("http://localhost:8080/api/resources/update_amount", consumptionInfo);
    }
}

export const ReportService = {
    getBaseReport() {
        return axios.get("http://localhost:8080/api/report/base");
    },

    getConsumptionReport(period) {
        return axios.get("http://localhost:8080/api/report/consumption?period="+period);
    },

    getWorkloadReport() {
        return axios.get("http://localhost:8080/api/report/workload");
    },

    getEmployeeWorkloadReport(employeeId) {
        return axios.get("http://localhost:8080/api/report/workload/employee?login="+employeeId);
    },

    getEmployeeKpiReport(employeeId) {
        return axios.get("http://localhost:8080/api/report/kpi/employee?login="+employeeId);
    }
}
