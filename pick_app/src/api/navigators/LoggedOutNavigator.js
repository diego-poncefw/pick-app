import { createStackNavigator } from 'react-navigation-stack';
import LoginScreen from '../components/login/screens/LoginScreen';

const LoggedOutNavigator = createStackNavigator({
    Login: {
        screen: LoginScreen,
        navigationOptions: {
            header: null,
        },
    },
});

export default LoggedOutNavigator;
