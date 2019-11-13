/**
 * Store and Retrieve persistence data, goes away if you delete the app.
 * */
import { AsyncStorage } from 'react-native';

/**
 * Store data to AsyncStorage like key and value.
 * @param data contain key and value will be stored, the key
 * will be unique, otherwise then new value will replace to old one.
 * */
export const storeData = async (data) => {
    try {
        await AsyncStorage.setItem(data.key, data.value);
    } catch (error) {
        console.log('Error Save Async Storage: ', error);
    }
};

/**
 * Retrieve Data from AsyncStorage.
 * @param key of the retrieve value
 * */
export const retrieveData = async (key) => {
    try {
        return await AsyncStorage.getItem(key);
    } catch (error) {
        // Error retrieving data
        console.log('Error Retrieve Async Storage: ', error);
    }
};

/**
 * Remove Data from AsyncStorage.
 * @param key of the remove value
 * */
export const removeData = async (key) => {
    try {
        return await AsyncStorage.removeItem(key);
    } catch (error) {
        // Error retrieving data
        console.log('Error Remove Async Storage: ', error);
    }
};