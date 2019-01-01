import React from 'react';
import {AppRegistry, StyleSheet, Text, View, Button, TouchableOpacity} from 'react-native';

import {RNLog} from '../native/android/index'

export default class MainPage extends React.Component {

    constructor(props){
        super(props);

        RNLog.i('MainPage', 'constructor()...');
        this.goParamsTransTestPage = this.goParamsTransTestPage.bind(this);
        this.goPropsAndStatePage = this.goPropsAndStatePage.bind(this);
    }

    render() {
        return (
            <View style={styles.container}>

                <TouchableOpacity
                    style={styles.btnStyle}
                    onPress={this.goParamsTransTestPage}
                >
                    <Text style={{textAlign: 'center',  }}>goParamsTransTestPage</Text>
                </TouchableOpacity>

                <View style={styles.btnStyle1}>
                    <Button
                        title="goPropsAndStatePage"
                        onPress={this.goPropsAndStatePage} />
                </View>

            </View>
        );
    }

    // 参数传递测试页面
    goParamsTransTestPage(){
        console.log("goParamsTransTestPage...");
        this.props.navigation.navigate('ParamsTransTestPage');
    }

    // PropsAndStatePage测试页面
    goPropsAndStatePage(){
        console.log("goPropsAndStatePage...");
        this.props.navigation.navigate('PropsAndStatePage');
    }

}
var styles = StyleSheet.create({
    container: {
        flex: 1,
        flexDirection: 'column',
        justifyContent: 'flex-start',
    },
    btnStyle:{
        flexDirection: 'row',
        justifyContent: 'center',
        alignItems: 'center',
        marginTop: 10,
        marginLeft: 30,
        marginRight: 30,
        height: 40,
        backgroundColor: '#4a4'
    },
    btnStyle1: {
        marginTop:10,
        marginLeft: 30,
        marginRight:30,
    },
    hello: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
});
