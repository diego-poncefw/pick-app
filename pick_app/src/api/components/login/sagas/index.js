import { watchGoogleSignIn, watchFacebookSignIn, watchGoogleSignOut, watchFacebookSignOut } from './Saga';

export const loginSagas = [
    watchGoogleSignIn,
    watchFacebookSignIn,
    watchGoogleSignOut,
    watchFacebookSignOut,
];