//SIGN IN TYPES
export const START_GOOGLE_SIGN_IN = 'START_GOOGLE_SIGN_IN';
export const START_FACEBOOK_SIGN_IN = 'START_FACEBOOK_SIGN_IN';
export const SIGN_IN_CALL_REQUEST = 'SIGN_IN_CALL_REQUEST';
export const SIGN_IN_CALL_SUCCESS = 'SIGN_IN_CALL_SUCCESS';
export const SIGN_IN_CALL_FAILURE = 'SIGN_IN_CALL_FAILURE';

//SIGN OUT TYPES
export const START_GOOGLE_SIGN_OUT = 'START_GOOGLE_SIGN_OUT';
export const SUCCESS_GOOGLE_SIGN_OUT = 'SUCCESS_GOOGLE_SIGN_OUT';
export const FAILED_GOOGLE_SIGN_OUT = 'FAILED_GOOGLE_SIGN_OUT';

export const START_FACEBOOK_SIGN_OUT = 'START_FACEBOOK_SIGN_OUT';
export const SUCCESS_FACEBOOK_SIGN_OUT = 'SUCCESS_FACEBOOK_SIGN_OUT';
export const FAILED_FACEBOOK_SIGN_OUT = 'FAILED_FACEBOOK_SIGN_OUT';

export const RESET_STATE = 'RESET_STATE';

//actions creator
export const requestCurrentUser = () => ({
    type: SIGN_IN_CALL_REQUEST,
});

export const startGoogleSignIn = () => ({
    type: START_GOOGLE_SIGN_IN,
});

export const startFacebookSignIn = () => ({
    type: START_FACEBOOK_SIGN_IN,
});

export const signInSuccess = (userInfo, provider) => ({
    type: SIGN_IN_CALL_SUCCESS,
    payload: {
        userInfo,
        provider,
    },
});

export const signInFailure = error => ({
    type: SIGN_IN_CALL_FAILURE,
    payload: error,
});

export const startGoogleSignOut = () => ({
    type: START_GOOGLE_SIGN_OUT,
});

export const successGoogleSignOut = () => ({
    type: SUCCESS_GOOGLE_SIGN_OUT,
});

export const failedGoogleSignOut = (error) => ({
    type: FAILED_GOOGLE_SIGN_OUT,
    payload: error,
});

export const startFacebookSignOut = () => ({
    type: START_FACEBOOK_SIGN_OUT,
});

export const successFacebookSignOut = () => ({
    type: SUCCESS_FACEBOOK_SIGN_OUT,
});

export const failedFacebookSignOut = (error) => ({
    type: FAILED_FACEBOOK_SIGN_OUT,
    payload: error,
});

export const resetState = () => ({
    type: RESET_STATE,
});
