import {
    StackNavigator,
} from 'react-navigation';

import MainPage from './page/MainPage'
import ParamsTransTestPage from './page/ParamsTransTestPage'
import PropsAndStatePage from './page/PropsAndStatePage'
import DialogTestPage from './page/DialogTestPage'
import FileTestPage from './page/FileTestPage'

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
    DialogTestPage: {
        screen: DialogTestPage,
        navigationOptions:{
            header:null,
        },
        mode:'card',
    },
    FileTestPage: {
        screen: FileTestPage,
        navigationOptions:{
            header:null,
        },
        mode:'card',
    },
});

export default PageManage;