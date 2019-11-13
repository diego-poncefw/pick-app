/* eslint-disable no-param-reassign */
import {combineReducers} from 'redux';
import helloReducer from '../components/TestFolder/reducers/Reducer';

const appReducers = combineReducers({
    hello: helloReducer,
});

/**
 * Reset all state of all reducers.
 * */
const rootReducer = (state, action) => {
    return appReducers(state, action);
};

export default rootReducer;
