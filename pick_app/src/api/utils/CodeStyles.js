import { StyleSheet, Platform } from 'react-native';

export const CELL_SIZE = 50;
export const CELL_BORDER_RADIUS = 50;
export const DEFAULT_CELL_BG_COLOR = '#fff';
export const NOT_EMPTY_CELL_BG_COLOR = '#00C191';
export const ACTIVE_CELL_BG_COLOR = '#f7fafe';

export default StyleSheet.create({
    inputWrapper: {
        backgroundColor: 'white',
        justifyContent: 'center',
        marginLeft: 20,
        marginRight: 20,
        alignItems: 'center',
    },

    inputLabel: {
        paddingTop: 120,
        color: '#000',
        fontSize: 25,
        fontWeight: '700',
        textAlign: 'center',
        paddingBottom: 80,
    },

    icon: {
        width: 217 / 2.4,
        height: 158 / 2.4,
        marginLeft: 'auto',
        marginRight: 'auto',
    },
    inputSubLabel: {
        paddingTop: 30,
        marginRight: 20,
        marginLeft: 20,
        color: '#00C191',
        textAlign: 'center',
    },
    inputWrapStyle: {
        height: CELL_SIZE,
        marginTop: 30,
        paddingHorizontal: 10,
        justifyContent: 'space-between',
    },

    input: {
        margin: 0,
        height: CELL_SIZE,
        width: CELL_SIZE,
        lineHeight: 55,
        ...Platform.select({
            web: {
                lineHeight: 65,
            },
        }),
        fontSize: 30,
        borderRadius: CELL_BORDER_RADIUS,
        color: '#00C191',
        backgroundColor: '#fff',

        // IOS
        shadowColor: '#000',
        shadowOffset: {
            width: 0,
            height: 1,
        },
        shadowOpacity: 0.22,
        shadowRadius: 2.22,

        // Android
        elevation: 3,
    },

    nextButton: {
        marginTop: 40,
        backgroundColor: '#00C191',
        height: 80,
        width: 80,
        borderRadius:160,
    },
});