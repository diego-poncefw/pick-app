import React from 'react';
import { LinearGradient } from 'expo-linear-gradient';


/**
 * Common Background gradient container.
 * */
const BackgroundGradientContainer = ({ children }) => (
    <LinearGradient
        colors={['#01BB94','#18C68E' ,'#20CA8C']}
        style={{ flex: 1, flexDirection: 'row' }}
    >
        {children}
    </LinearGradient>
);

export default BackgroundGradientContainer;