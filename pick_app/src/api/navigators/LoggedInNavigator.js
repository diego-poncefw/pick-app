import React from 'react';
import { createStackNavigator } from 'react-navigation-stack';
import { createBottomTabNavigator } from 'react-navigation-tabs';
import {Icon} from "native-base";
import AccountScreen from '../components/account/screens/AccountScreen';
import MachinesScreen from '../components/machines/screens/MachinesScreen';
import PaymentsScreen from '../components/payments/screens/PaymentsScreen';

const MainNavigator = createBottomTabNavigator(
    {
        ACCOUNT: {
            screen: AccountScreen,
            navigationOptions: () => ({
                tabBarIcon: ({ tintColor }) => (
                    <Icon type="Entypo" style={{ color: tintColor, fontSize: 20 }} name="tools" />
                ),
                style: {
                    backgroundColor: 'red',
                },
            }),
        },
        MACHINES: {
            screen: MachinesScreen,
            navigationOptions: () => ({
                tabBarIcon: ({ tintColor }) => (
                    <Icon type='Ionicons' style={{ color: tintColor, fontSize: 20 }} name='ios-barcode' />
                ),
            }),
        },
        PAYMENTS: {
            screen: PaymentsScreen,
            navigationOptions: () => ({
                tabBarIcon: ({ tintColor }) => (
                    <Icon type='MaterialIcons' style={{ color: tintColor, fontSize: 20 }} name='monetization-on' />
                ),
            }),
        },
    },
    {
        initialRouteName: 'MACHINES',
        tabBarOptions: {
            activeTintColor: 'white',
            inactiveTintColor: '#9DB8DA',
            style: {
                backgroundColor: '#00C191',
                height: 70,
                paddingTop: 10,
                paddingBottom: 10,
                paddingLeft: 15,
                paddingRight: 15,
            },
            labelStyle: {
                fontSize: 10,
            },
        },
    },
);

const LoggedInNavigator = createStackNavigator(
    {
        Main: {
            screen: MainNavigator,
        },
    },
    {
        initialRouteName: 'Main',
        headerMode: 'none',
        mode: 'modal',
    },
);

export default LoggedInNavigator;