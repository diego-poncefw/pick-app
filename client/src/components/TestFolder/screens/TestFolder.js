import React, {useEffect} from 'react';
import { makeStyles } from '@material-ui/styles/index';
import { requestHello } from '../actions/Actions';

import {
    Typography
} from '@material-ui/core';
import {useDispatch, useSelector} from "react-redux";

const useStyles = makeStyles(theme => ({
    root: {
        padding: theme.spacing(4)
    },
    iframe: {
        width: '100%',
        minHeight: 640,
        border: 0
    }
}));

const TestFolder = () => {
    const classes = useStyles();
    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(requestHello());
    }, []);

    const response = useSelector( state => console.log("entro",state.hello));

    return (
        <div className={classes.root}>
            <Typography
                variant="h2"
            >
                Sign inn
            </Typography>
        </div>
    );
};

export default TestFolder;
