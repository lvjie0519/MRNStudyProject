import {
    StackNavigator,
} from 'react-navigation';

import MainPage from './page/MainPage'
import ParamsTransTestPage from './page/ParamsTransTestPage'

const PageManage = StackNavigator({
    MainPage: {
        screen: MainPage,
        navigationOptions:{
            header:null,
        },
        mode:'card',
    },
    ParamsTransTestPage: {
        screen: ParamsTransTestPage,
        navigationOptions:{
            header:null,
        },
        mode:'card',
    },
});

export default PageManage;