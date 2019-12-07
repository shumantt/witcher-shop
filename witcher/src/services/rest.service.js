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

    consumptGrass(consumptionInfo) {
        return axios.put("http://localhost:8080/api/resources/grass", consumptionInfo);
    }
}

export const RecipesService = {
    getAll() {
        return axios.get("http://localhost:8080/api/resources/recipes");
    },
    
    cookByRecipe(recipeId) {
        return axios.post("http://localhost:8080/api/resources/recipes/cook", {recipeId});
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
    }
}
