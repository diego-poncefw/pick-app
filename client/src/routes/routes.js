import React from 'react';
import { Switch, Redirect } from 'react-router-dom';

import { RouteWithLayout } from '../components';
import { Main as MainLayout, Minimal as MinimalLayout } from '../layouts';

import {
    Dashboard as DashboardView,
    TestFolder as TestView,
} from '../components';

const Routes = () => {
    return (
        <Switch>
            <Redirect
                exact
                from="/"
                to="/dashboard"
            />
            <RouteWithLayout
                component={DashboardView}
                exact
                layout={MainLayout}
                path="/dashboard"
            />
            <RouteWithLayout
                component={TestView}
                exact
                layout={MainLayout}
                path="/test"
            />
            <Redirect to="/not-found" />
        </Switch>
    );
};

export default Routes;
