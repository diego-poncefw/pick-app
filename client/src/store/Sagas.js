import { all, fork } from 'redux-saga/effects';
import { helloSaga } from '../components/TestFolder/sagas';

function* rootSaga() {
    yield all(
        [
            ...Object.values(helloSaga),
        ].map(fork),
    );
}

export default rootSaga;
