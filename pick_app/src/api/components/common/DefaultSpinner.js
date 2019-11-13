import React, { Component } from 'react';
import { Spinner } from 'native-base';

import Colors from '../../utils/Colors';

class DefaultSpinner extends Component {
    render() {
        return (
            <Spinner color={Colors.spinner} />
        );
    }
}

export default DefaultSpinner;