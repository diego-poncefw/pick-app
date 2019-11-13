import { createSwitchNavigator, createAppContainer } from 'react-navigation';
import LoggedOutNavigator from './LoggedOutNavigator';
import LoggedInNavigator from './LoggedInNavigator';

const MainNavigator = createSwitchNavigator(
    {
        LoggedIn: {
            screen: LoggedInNavigator,
        },
        LoggedOut: {
            screen: LoggedOutNavigator,
        },
    },
    {
        initialRouteName: 'LoggedOut',
    },
);

export const RootNavigator = createAppContainer(MainNavigator);