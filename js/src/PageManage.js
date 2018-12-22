import {
    StackNavigator,
} from 'react-navigation';

import MainPage from './page/MainPage'

const PageManage = StackNavigator({
    MainPage: {
        screen: MainPage,
        navigationOptions:{
            header:null,
        },
        mode:'card',
    },
});

export default PageManage;