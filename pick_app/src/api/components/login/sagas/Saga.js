import { call, put, takeLatest } from 'redux-saga/effects';
import * as Google from 'expo-google-app-auth';
import * as Facebook from 'expo-facebook';
import api from '../../../utils/Api';
import {
    signInFailure,
    signInSuccess,
    START_GOOGLE_SIGN_IN,
    SIGN_IN_CALL_REQUEST,
    successGoogleSignOut,
    failedGoogleSignOut,
    START_FACEBOOK_SIGN_IN,
    START_GOOGLE_SIGN_OUT,
    START_FACEBOOK_SIGN_OUT,
    failedFacebookSignOut,
    successFacebookSignOut,
    resetState,
} from '../actions/Actions';
// import { SIGN_IN_URL } from '../../../../../utils/Constanst';
import { retrieveData, storeData } from '../../../utils/AsyncStorageHelper';
import NavigationService from '../../../utils/NavigationService';

// get token
const fetchAuthUser = (token, provider) => {
  /*  const data = JSON.stringify({ token, provider });
    return api.post(SIGN_IN_URL, data, {
        headers: {
            'Content-Type': 'application/json',
        },
    });*/
};

/**
 * Sign ing with server side after Google Singing, if SING_ING_CALL_REQUEST there are any problem
 * the error is propagated to father function generator.
 * */
function* serverSignIn(token, provider) {
   /* const response = yield call(fetchAuthUser, token, provider);
    api.defaults.headers.common['Authorization'] = `Bearer ${response.data.response}`;
    yield call(storeData, { key: 'token', value: response.data.response });
    yield call(storeData, { key: 'idToken', value: token });
    yield call(storeData, { key: 'acceptedTerms', value: JSON.stringify(response.data.acceptedTerms) });*/
}

/**
 * Signing with Google Expo
 * here is needed iosClientId and androidClientId
 * */
export function* googleSignInSaga() {
    try {
        const googleParameters = {
            iosClientId: '173171479004-c24euqjtkkusm2v5m7rlioohsgch53qk.apps.googleusercontent.com',
            androidClientId: '173171479004-uggftjaff7coaus6ojm9vtg4koavhoan.apps.googleusercontent.com',
            scopes: ['profile', 'email'],
        };
        const userInfo = yield call(Google.logInAsync, googleParameters);
        console.log("USER INFO", userInfo);
        console.log('LOGIN TYPE: ', userInfo.type);
        if (userInfo.type === 'success') {
            //yield call(serverSignIn, userInfo.idToken, 'GOOGLE');
            yield put(signInSuccess(userInfo.user, 'GOOGLE'));
        }
    } catch (error) {
        console.log('ERROR: ', error);
        yield put(signInFailure(error));
    }
}

export function* watchGoogleSignIn() {
    yield takeLatest(START_GOOGLE_SIGN_IN, googleSignInSaga);
}

export function* facebookSignInSaga() {
    try {
        const permissions = {
            permissions: ['public_profile', 'email'],
        };
        const userInfo = yield call(Facebook.logInWithReadPermissionsAsync, '2726185744093446', permissions);
        console.log("USER INFO", userInfo);
        console.log('LOGIN TYPE: ', userInfo.type);
        if (userInfo.type === 'success') {
            //yield call(serverSignIn, userInfo.token, 'FACEBOOK');
            const response = yield fetch(`https://graph.facebook.com/me?access_token=${userInfo.token}&fields=email,name`);
            yield put(signInSuccess(response.json(), 'FACEBOOK'));
        }
    } catch (error) {
        console.log('ERROR: ', error);
        yield put(signInFailure(error));
    }
}

export function* watchFacebookSignIn() {
    yield takeLatest(START_FACEBOOK_SIGN_IN, facebookSignInSaga);
}


export function* googleSignOutSaga() {
    try {
        const accessToken = yield call(retrieveData, 'idToken');
        const googleParameters = {
            accessToken,
            iosClientId: '173171479004-c24euqjtkkusm2v5m7rlioohsgch53qk.apps.googleusercontent.com',
            androidClientId: '173171479004-uggftjaff7coaus6ojm9vtg4koavhoan.apps.googleusercontent.com',
        };
        yield call(Google.logOutAsync, googleParameters);
        yield put(successGoogleSignOut());
        yield put(resetState());
        NavigationService.navigate('Login');
    } catch (error) {
        console.log('ERROR: ', error);
        yield put(failedGoogleSignOut(error));
    }
}

export function* watchGoogleSignOut() {
    yield takeLatest(START_GOOGLE_SIGN_OUT, googleSignOutSaga);
}

export function* facebookSignOutSaga() {
    try {
        const accessToken = yield call(retrieveData, 'idToken');
        yield fetch('https://graph.facebook.com/User_id/permissions',
            { method: 'DELETE', body: `access_token=${accessToken}` });
        yield put(successFacebookSignOut());
        NavigationService.navigate('Login');
    } catch (error) {
        console.log('ERROR: ', error);
        yield put(failedFacebookSignOut(error));
    }
}

export function* watchFacebookSignOut() {
    yield takeLatest(START_FACEBOOK_SIGN_OUT, facebookSignOutSaga);
}

