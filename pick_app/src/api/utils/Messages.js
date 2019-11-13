import { Alert } from 'react-native';

/**
 * Show a Simple Message without actions buttons.
 * @param title of message
 * @param message content
 * @return {Alert} component
 * */
const SimpleAlertMessage = (title, message) => (
    Alert.alert(title, message, [
        { text: 'OK', onPress: () => console.log('OK Pressed') },
    ])
);

export { SimpleAlertMessage };
