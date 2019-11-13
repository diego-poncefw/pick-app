import { createStore, applyMiddleware, compose } from 'redux';
import createSagaMiddleware from 'redux-saga';

import rootReducer from './Reducers';
import rootSaga from './Sagas';

// create the saga middleware
const sagaMiddleware = createSagaMiddleware();

// create the store with reducers and middleware
const middleware = applyMiddleware(sagaMiddleware);

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
const store = createStore(rootReducer, composeEnhancers(middleware));

// run saga
sagaMiddleware.run(rootSaga);

export default store;
