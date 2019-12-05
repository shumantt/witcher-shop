import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '@/components/Home';
import Authorization from '@/components/Authorization';
import Receipts from '@/components/resources/Receipts';
import Consumption from '@/components/resources/Consumption';
import Order from '@/components/client/Order';
import Report from '@/components/managing/Report';
import Employees from '@/components/managing/Employees'
import Grass from '@/components/resources/Grass';
import ClientOrders from '@/components/client/ClientOrders';

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'home',
        component: Home,
        children: [
            {
                name: 'receips',
                path: '/home/receips',
                component: Receipts,
            },
            {
                name: 'grass',
                path: '/home/grass',
                component: Grass,
            },
            {
                name: 'consumption',
                path: '/home/consumption',
                component: Consumption,
            },
            {
                name: 'report',
                path: '/managing/report',
                component: Report,
            },
            {
                name: 'Employees',
                path: '/managing/employees',
                component: Employees,
            },
            {
                name: 'order',
                path: '/client/order',
                component: Order
            },
            {
                name: 'order',
                path: '/client/orders',
                component: ClientOrders,
            }
        ]
      },
      {
          path: '/auth',
          name: 'auth',
          component: Authorization
      }
]

export default new VueRouter({
    routes,
    mode: 'history'
});
