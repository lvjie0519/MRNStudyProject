import React from 'react';
import {AppRegistry, StyleSheet, Text, View} from 'react-native';

import {RNLog} from '../native/android/index'

export default class MainPage extends React.Component {

    constructor(props){
        super(props);

        RNLog.i('MainPage', 'constructor()...');
    }

    render() {
        return (
            <View style={styles.container}>
                <Text style={styles.hello}>Hello, World</Text>
            </View>
        );
    }
}
var styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
    },
    hello: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
});
