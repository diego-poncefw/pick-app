import React from 'react';
import { AppLoading } from 'expo';
import * as Font from 'expo-font';
import { View } from 'react-native';
// eslint-disable-next-line import/no-extraneous-dependencies
import { Ionicons, AntDesign } from '@expo/vector-icons';
import { Provider } from 'react-redux';
import store from './src/api/store/Store';
import NavigationService from './src/api/utils/NavigationService';
import { RootNavigator } from "./src/api/navigators/RootNavigator";

export default class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isReady: false,
        };
    }

    async componentDidMount() {
        await Font.loadAsync({
            Roboto: require('native-base/Fonts/Roboto.ttf'),
            Roboto_medium: require('native-base/Fonts/Roboto_medium.ttf'),
            ...Ionicons.font,
            ...AntDesign.font,
        });
        this.setState({ isReady: true });
    }

    render() {
        if (!this.state.isReady) {
            return <AppLoading />;
        }

        return (
            <Provider store={store}>
                <View style={{flex: 1}}>
                    <RootNavigator ref={navigatorRef => { NavigationService.setTopLevelNavigator(navigatorRef); }} />
                </View>
            </Provider>
        );
    }
}
