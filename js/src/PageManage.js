import {
    StackNavigator,
} from 'react-navigation';

import MainPage from './page/MainPage'
import ParamsTransTestPage from './page/ParamsTransTestPage'
import PropsAndStatePage from './page/PropsAndStatePage'

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
    PropsAndStatePage: {
        screen: PropsAndStatePage,
        navigationOptions:{
            header:null,
        },
        mode:'card',
    },
});

export default PageManage;