export const START_HELLO_REQUEST = 'START_HELLO_REQUEST';
export const HELLO_SUCCESS = 'HELLO_SUCCESS';
export const HELLO_FAILED = 'HELLO_FAILED';

export const requestHello = () => ({
    type: START_HELLO_REQUEST,
});

export const successRequestHello = (hello) => ({
    type: HELLO_SUCCESS,
    payload: {
        hello,
    },
});

export const failedRequestHello = (error) => ({
    type: HELLO_FAILED,
    payload: error,
});
