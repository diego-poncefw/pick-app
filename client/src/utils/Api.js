import axios from 'axios';

/**
 * Global Configurations for Axios, setting BaseURL, Timeout
 * and Interceptors.
 * */
const api = axios.create({
    timeout: 30000,
});

api.interceptors.request.use(config => {
    // Do something before request is sent
    console.log('Catched Request: ', JSON.stringify(config));
    return config;
}, error => {
    // Do something with request error
    console.log('Catched Request Error:', error);
    return Promise.reject(error);
});

api.interceptors.response.use(response => {
    // Do something with response data
    console.log('Catched Response: ', JSON.stringify(response));
    return response;
}, error => {
    // Do something with response error
    console.log('Catched Response Error:', JSON.stringify(error));
    return Promise.reject(error);
});

export default api;
