import React from 'react';
import {
    StyleSheet,
    Text,
    View,
    WebView,
} from 'react-native';

// import testHtml from '../../res/test_page.html'

export default class WebviewTestPage extends React.Component{


    constructor(props){
        super(props);
    }

    render() {

        return (
            <View
                style={{flex:1}}
            >
                <WebView
                  originWhitelist={['*']}
                  source={require('../../res/test_page.html')}
                />
            </View>
        );
    }
}
