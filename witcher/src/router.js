import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '@/components/Home';
import Authorization from '@/components/Authorization';
import Receipts from '@/components/resources/Receipts';
import Consumption from '@/components/resources/Consumption';
import Order from '@/components/client/Order';
import Report from '@/components/managing/Report';
import Employees from '@/components/managing/Employees'
import WorkerMenu from '@/components/resources/WorkerMenu';
import ClientMenu from '@/components/client/ClientMenu';
import ManagerMenu from '@/components/managing/ManagerMenu';
import Grass from '@/components/resources/Grass';
import ClientOrders from '@/components/client/ClientOrders';

Vue.use(VueRouter);

const routes = [
    {
        path: '/home',
        name: 'home',
        component: Home,
        children: [
            {
                name: 'receips',
                path: '/home/receips',
                components: {
                    default: Receipts,
                    menu: WorkerMenu
                }
            },
            {
                name: 'receips',
                path: '/home/grass',
                components: {
                    default: Grass,
                    menu: WorkerMenu
                }
            },
            {
                name: 'consumption',
                path: '/home/consumption',
                components: {
                    default: Consumption,
                    menu: WorkerMenu
                }
            },
            {
                name: 'report',
                path: '/managing/report',
                components: {
                    default: Report,
                    menu: ManagerMenu
                }
            },
            {
                name: 'Employees',
                path: '/managing/employees',
                components: {
                    default: Employees,
                    menu: ManagerMenu
                }
            },
            {
                name: 'managin_rec',
                path: '/managing/receips',
                components: {
                    default: Receipts,
                    menu: ManagerMenu
                }
            },
            {
                name: 'managin_grass',
                path: '/managing/grass',
                components: {
                    default: Grass,
                    menu: ManagerMenu
                }
            },
            {
                name: 'order',
                path: '/client/order',
                components: {
                    default: Order,
                    menu: ClientMenu
                }
            },
            {
                name: 'order',
                path: '/client/orders',
                components: {
                    default: ClientOrders,
                    menu: ClientMenu
                }
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
    routes
});
