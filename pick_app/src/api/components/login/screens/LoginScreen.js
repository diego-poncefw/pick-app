import React, { Component } from 'react';
import {View, Dimensions, StyleSheet } from 'react-native';
import {
    Button, Content, Icon, Spinner, Text,
} from 'native-base';
import { connect } from 'react-redux';
import { startGoogleSignIn, startFacebookSignIn } from '../actions/Actions'
import Animated, {Easing} from 'react-native-reanimated';
import { TapGestureHandler, State} from 'react-native-gesture-handler';
import BackgroundGradientContainer from "../../common/BackgroundGreenGradientContainer";
import Styles from '../../../utils/Styles';
import DefaultSpinner from '../../common/DefaultSpinner';

const {width, height} = Dimensions.get("window");
const {Value, event, block, cond, eq, set, Clock, startClock, stopClock, debug, timing, clockRunning, interpolate, Extrapolate, concat} = Animated;

function runTiming(clock, value, dest) {
    const state = {
        finished: new Value(0),
        position: new Value(0),
        time: new Value(0),
        frameTime: new Value(0),
    };

    const config ={
        duration: 1000,
        toValue: new Value(0),
        easing: Easing.inOut(Easing.ease),
    };

    return block([
        cond(clockRunning(clock), 0 ,[
            set(state.finished, 0),
            set(state.time, 0),
            set(state.position, value),
            set(state.frameTime, 0),
            set(config.toValue, dest),
            startClock(clock),
        ]),
        timing(clock, state, config),
        cond(state.finished, debug('stop clock', stopClock(clock))),
        state.position,
    ]);
}

class LoginScreen extends Component {

    constructor(){
        super();
        this.buttonOpacity = new Value(1);
        this.onStateChange = event ([
            {
                 nativeEvent:({state}) => block([
                     cond(eq(state, State.END), set(this.buttonOpacity,
                         runTiming(new Clock(),1,0)))
                 ])
            }
        ]);

        this.onCloseState = event ([
            {
                nativeEvent:({state}) => block([
                    cond(eq(state, State.END), set(this.buttonOpacity,
                        runTiming(new Clock(),0,1)))
                ])
            }
        ]);

        this.buttonY = interpolate(this.buttonOpacity, {
            inputRange: [0, 1],
            outputRange: [100 , 0],
        });

        this.bgY = interpolate(this.buttonOpacity, {
            inputRange: [0, 1],
            outputRange: [-height / 3, 0],
            extrapolate: Extrapolate.CLAMP,
        });

        this.textInputZindex = interpolate(this.buttonOpacity, {
            inputRange: [0, 1],
            outputRange: [1 , -1],
            extrapolate: Extrapolate.CLAMP,
        });

        this.textInputY = interpolate(this.buttonOpacity, {
            inputRange: [0, 1],
            outputRange: [0 , 100],
            extrapolate: Extrapolate.CLAMP,
        });

        this.textInputOpacity = interpolate(this.buttonOpacity, {
            inputRange: [0, 1],
            outputRange: [1 , 0],
            extrapolate: Extrapolate.CLAMP,
        });

        this.rotateCross = interpolate(this.buttonOpacity, {
            inputRange: [0, 1],
            outputRange: [180 , 360],
            extrapolate: Extrapolate.CLAMP,
        })
    }

    componentWillMount() {
        this.props.login.data &&  this.props.navigation.navigate('MACHINES');
    }

    componentDidUpdate(prevProps, prevState) {
        const {error} = this.props.login;

        if(prevProps.login.error !== this.props.login.error){
            if(error.isError) {
                SimpleAlertMessage('Error', "Error Login");
            } else {
                this.props.navigation.navigate('MACHINES');
            }
        }
    }

    render() {
        return (
            <View style = {{flex: 1, backgroundColor: 'white', justifyContent: 'flex-end'}}>
                <Animated.View style={{ ...StyleSheet.absoluteFill, transform: [{ translateY: this.bgY}]}}>
                    <BackgroundGradientContainer>
                    </BackgroundGradientContainer>
                </Animated.View>
                <TapGestureHandler onHandlerStateChange={this.onStateChange}>
                    <Animated.View style={{...styles.buttonLogIn, opacity: this.buttonOpacity, transform: [{translateY: this.buttonY}]}}>
                        <Text style={styles.buttonLogInText}>LOG IN</Text>
                    </Animated.View>
                </TapGestureHandler>
                {this.props.login.isSingingInProgress ? <DefaultSpinner /> : <Animated.View
                    style={{
                        zIndex: this.textInputZindex,
                        opacity: this.textInputOpacity,
                        transform: [{translateY: this.textInputY}],
                        height: height/3,
                        ...StyleSheet.absoluteFill,
                        top: null,
                        justifyContent: 'center'
                    }}>
                    <TapGestureHandler onHandlerStateChange={this.onCloseState}>
                        <Animated.View style={styles.closeButton}>
                            <Animated.Text style={{fontSize: 15, transform:[{rotate: concat(this.rotateCross, 'deg')}]}}>
                                X
                            </Animated.Text>
                        </Animated.View>
                    </TapGestureHandler>
                    <Button block iconLeft style={Styles.buttonFacebookStyle} onPress={() => this.props.startFacebookSignIn()}>
                        <Icon type='FontAwesome' name='facebook' />
                        <Text>Sign in with Facebook</Text>
                    </Button>
                    <Button block iconLeft style={Styles.buttonGoogleStyle} onPress={() => this.props.startGoogleSignIn()}>
                        <Icon type='Entypo' name='google-'/>
                        <Text>Sign in with Google</Text>
                    </Button>
                </Animated.View>}
            </View>
        );

    }
}

const mapStateToProps = state => ({ login: state.login });

export default connect(mapStateToProps, { startGoogleSignIn, startFacebookSignIn }) (LoginScreen);

const styles = StyleSheet.create({
    buttonLogIn: {
        backgroundColor: 'white',
        height: 70,
        marginHorizontal: 20,
        borderRadius: 10,
        alignItems: 'center',
        justifyContent: 'center',
        marginBottom: 50
    },
    buttonLogInText: {
        fontSize: 20,
        fontWeight: 'bold',
        color: '#00C191'
    },
    closeButton:{
        height: 40,
        width: 40,
        backgroundColor: 'white',
        borderRadius: 20,
        alignItems: 'center',
        justifyContent: 'center',
        position: 'absolute',
        top: -20,
        left: width / 2 - 20,
        shadowOffset: { width: 2, height: 2},
        shadowColor: 'black',
        shadowOpacity: 0.2,
    },
    textInput:{
        height: 50,
        borderRadius: 25,
        borderWidth: 5,
        marginHorizontal: 20,
        paddingLeft: 10,
        marginVertical: 5,
        borderColor: 'rgba(0,0,0,0.2)',
    },

});