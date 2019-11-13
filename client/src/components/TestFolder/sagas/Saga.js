import {call, put, takeLatest, all} from 'redux-saga/effects';
import api from '../../../utils/Api';
import {
    failedRequestHello,
    START_HELLO_REQUEST,
    successRequestHello,
} from '../actions/Actions';

import { HELLO_WORLD_URL } from '../../../utils/Constants';

const fetchHello = () => {
    return api.get(HELLO_WORLD_URL);
};

export function* requestHelloSaga() {
    try {
        const response = yield call(fetchHello);
        yield put(successRequestHello(response.data));
        console.log('Success: ', 'requestHelloSaga');
    } catch (error) {
        yield put(failedRequestHello(error));
        console.log('Error status: ', error);
    }
}

export function* watchRequestHello() {
    yield takeLatest(START_HELLO_REQUEST, requestHelloSaga);
}
