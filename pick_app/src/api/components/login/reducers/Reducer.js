import {
    SIGN_IN_CALL_REQUEST,
    SIGN_IN_CALL_SUCCESS,
    SIGN_IN_CALL_FAILURE,
    START_GOOGLE_SIGN_OUT,
    SUCCESS_GOOGLE_SIGN_OUT,
    FAILED_GOOGLE_SIGN_OUT,
} from '../actions/Actions';

const errorDefaultState = { isError: null, data: null };

const SIGN_IN_INITIAL_STATE = {
    isSingingInProgress: false,
    data: null,
    error: errorDefaultState,
};

export default (state = SIGN_IN_INITIAL_STATE, action) => {
    switch (action.type) {
    case SIGN_IN_CALL_REQUEST:
        return {
            ...state,
            isSingingInProgress: true,
            error: errorDefaultState,
        };
    case SIGN_IN_CALL_SUCCESS:
        return {
            ...state,
            isSingingInProgress: false,
            data: action.payload,
            error: { isError: false, data: null },
        };
    case SIGN_IN_CALL_FAILURE:
        return {
            ...state,
            isSingingInProgress: false,
            data: null,
            error: { isError: true, data: action.payload },
        };
    case START_GOOGLE_SIGN_OUT:
        return {
            ...state,
            isSingingInProgress: true,
            error: errorDefaultState,
        };
    case SUCCESS_GOOGLE_SIGN_OUT:
        return {
            ...state,
            isSingingInProgress: false,
            data: null,
            error: errorDefaultState,
        };
    case FAILED_GOOGLE_SIGN_OUT:
        return {
            ...state,
            isSingingInProgress: false,
            error: errorDefaultState,
        };
    default:
        return state;
    }
};

//selectors
export const getUser = (state) => state.login.userInfo.user;
