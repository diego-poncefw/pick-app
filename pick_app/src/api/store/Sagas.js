import { all, fork } from 'redux-saga/effects';
import { loginSagas } from '../components/login/sagas';

function* rootSaga() {
    yield all(
        [
            ...Object.values(loginSagas),
        ].map(fork),
    );
}

export default rootSaga;