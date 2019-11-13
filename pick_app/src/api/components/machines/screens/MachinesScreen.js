import React, { Component } from 'react';
import {View, Text, Icon, Button} from 'native-base';
import { Alert, Animated, ScrollView} from 'react-native';
import BackgroundGradientContainer from "../../common/BackgroundGreenGradientContainer";
import CodeFiled from 'react-native-confirmation-code-field';
import CodeStyles, {
    ACTIVE_CELL_BG_COLOR,
    CELL_BORDER_RADIUS,
    CELL_SIZE,
    DEFAULT_CELL_BG_COLOR,
    NOT_EMPTY_CELL_BG_COLOR,
} from '../../../utils/CodeStyles';
import Styles from "../../../utils/Styles";

const codeLength = 5;

const source = {
    uri:
        'https://user-images.githubusercontent.com/4661784/56352614-4631a680-61d8-11e9-880d-86ecb053413d.png',
};

class MachinesScreen extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isCodeValid: false,
        };
    }

    _animationsColor = [...new Array(codeLength)].map(
        () => new Animated.Value(0),
    );
    _animationsScale = [...new Array(codeLength)].map(
        () => new Animated.Value(1),
    );

    onFinishCheckingCode = code => {
        if (code !== '12345') {
            this.setState({isCodeValid: false});
            return Alert.alert(
                'Confirmation Code',
                'Code not match! Try 12345',
                [{ text: 'OK' }],
                { cancelable: false },
            );
        }
        else {
            this.setState({isCodeValid: true});
        }
    };

    animateCell({ hasValue, index, isFocused }) {
        Animated.parallel([
            Animated.timing(this._animationsColor[index], {
                toValue: isFocused ? 1 : 0,
                duration: 250,
            }),
            Animated.spring(this._animationsScale[index], {
                toValue: hasValue ? 0 : 1,
                duration: hasValue ? 300 : 250,
            }),
        ]).start();
    }

    cellProps = ({ hasValue, index, isFocused }) => {
        const animatedCellStyle = {
            backgroundColor: hasValue
                ? this._animationsScale[index].interpolate({
                    inputRange: [0, 1],
                    outputRange: [NOT_EMPTY_CELL_BG_COLOR, ACTIVE_CELL_BG_COLOR],
                })
                : this._animationsColor[index].interpolate({
                    inputRange: [0, 1],
                    outputRange: [DEFAULT_CELL_BG_COLOR, ACTIVE_CELL_BG_COLOR],
                }),
            borderRadius: this._animationsScale[index].interpolate({
                inputRange: [0, 1],
                outputRange: [CELL_SIZE, CELL_BORDER_RADIUS],
            }),
            transform: [
                {
                    scale: this._animationsScale[index].interpolate({
                        inputRange: [0, 1],
                        outputRange: [0.2, 1],
                    }),
                },
            ],
        };

        // Run animation on next event loop tik
        // Because we need first return new style prop and then animate this value
        setTimeout(() => {
            this.animateCell({ hasValue, index, isFocused });
        }, 0);

        return {
            style: [CodeStyles.input, animatedCellStyle],
        };
    };

    containerProps = { style: CodeStyles.inputWrapStyle };

    render() {
        return (
            <ScrollView style={{flex: 1}}>
                <View style={CodeStyles.inputWrapper}>
                    <Text style={CodeStyles.inputLabel}> </Text>
                    <CodeFiled
                        maskSymbol=" "
                        variant="clear"
                        codeLength={codeLength}
                        keyboardType="numeric"
                        cellProps={this.cellProps}
                        containerProps={this.containerProps}
                        onFulfill={this.onFinishCheckingCode}
                        CellComponent={Animated.Text}
                    />

                    <Text style={CodeStyles.inputSubLabel}>
                        Ingresa el código de la máquina dispensadora para adquirir sus productos
                    </Text>

                    { this.state.isCodeValid &&
                        <View style={Styles.centerView}>
                            <Button block style={CodeStyles.nextButton}>
                                <Icon type='AntDesign' name='right' style={{fontSize: 40}}/>
                            </Button>
                        </View>
                    }
                </View>
            </ScrollView>
        );
    }
}

export default MachinesScreen;
