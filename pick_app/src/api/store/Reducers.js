/* eslint-disable no-param-reassign */
import { combineReducers } from 'redux';
import loginReducer from '../components/login/reducers/Reducer';
import { RESET_STATE } from '../components/login/actions/Actions';

const appReducers = combineReducers({
    login: loginReducer,
});

/**
 * Reset all state of all reducers.
 * */
const rootReducer = (state, action) => {
    if (action.type === RESET_STATE) {
        state = undefined;
    }
    return appReducers(state, action);
};

export default rootReducer;
