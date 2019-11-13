import {
    START_HELLO_REQUEST,
    HELLO_SUCCESS,
    HELLO_FAILED,
} from '../actions/Actions';

const errorDefaultState = { isError: false, data: {} };

const HELLO_INITIAL_STATE = {
    loading: false,
    data: {},
    error: errorDefaultState,
};

export default (state = HELLO_INITIAL_STATE, action) => {
    switch (action.type) {
        case START_HELLO_REQUEST:
            return {
                ...state,
                loading: true,
                data: {},
                error: errorDefaultState,
            };
        case HELLO_SUCCESS:
            return {
                ...state,
                loading: false,
                data: action.payload.hello,
                error: errorDefaultState,
            };
        case HELLO_FAILED:
            return {
                ...state,
                loading: false,
                data: {},
                error: { isError: true, data: action.payload },
            };
        default:
            return state;
    }
};


