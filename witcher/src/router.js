import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '@/components/Home';
import Authorization from '@/components/Authorization';
import Receipts from '@/components/resources/Receipts';
import Consumption from './components/worker/Consumption';
import Order from '@/components/client/Order';
import Report from '@/components/managing/Report';
import Employees from '@/components/managing/Employees'
import Grass from '@/components/resources/Grass';
import ClientOrders from '@/components/client/ClientOrders';
import store from './store/store'
import Orders from './components/worker/Orders';
import Runes from "./components/resources/Runes";
import Animals from './components/resources/Animals';
import ResourceInfo from "./components/resources/ResourceInfo";
import AddReceipt from "./components/resources/AddReceipt";
import ReportView from "./components/managing/ReportView";

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'home',
        component: Home,
        children: [
            {
                name: 'receipts',
                path: '/receipts',
                component: Receipts,
            },
            {
                name: 'grass',
                path: '/grass',
                component: Grass,
            },
            {
                name: 'consumption',
                path: '/consumption',
                component: Consumption,
            },
            {
                name: 'report',
                path: '/report',
                component: Report,
            },
            {
                name: 'Employees',
                path: '/employees',
                component: Employees,
            },
            {
                name: 'order',
                path: '/order',
                component: Order
            },
            {
                name: 'client-orders',
                path: '/client-orders',
                component: ClientOrders,
            },
            {
                name: 'orders',
                path: '/orders',
                component: Orders,
            },
            {
                name: 'runes',
                path: '/runes',
                component: Runes,
            },
            {
                name: 'animals',
                path: '/animals',
                component: Animals
            },
            {
                name: 'info',
                path: '/info',
                component: ResourceInfo
            },
            {
                name: 'addReceipt',
                path: '/receipts/add',
                component: AddReceipt
            },
            {
                name: 'reportView',
                path: 'report/view',
                component: ReportView
            }
        ]
      },
      {
          path: '/auth',
          name: 'auth',
          component: Authorization
      }
]

const router =  new VueRouter({
    routes,
    mode: 'history'
});

const clientPaths = ['auth', 'client-orders', 'order'];

router.beforeEach((to, from, next) => {
    if (store.getters.isLoggedIn || to.name === 'auth') {
        if(store.getters.isClient && !clientPaths.includes(to.name) && to.path != '/auth') {
            next('/order');
            return
        }
        next();
        return
    }
    next('/auth')
  });

export default router
