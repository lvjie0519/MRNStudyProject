import React from 'react';
import {
    AppRegistry,
    StyleSheet,
    Text,
    TouchableOpacity,
    View
} from 'react-native';

import {MessageDialog} from '../native/android/ui/index'

export default class DialogTestPage extends React.Component{

    constructor(props){
        super(props);

        this.state = {
            visible: false,
            title: "title",
            message: "message",
            clickCount: 0
        }
    }

    render(){
        console.log("render", this.state)
        return (
            <View style={styles.container}>
                <TouchableOpacity
                    style={styles.btnStyle}
                    onPress={()=>{this.onClickMessageDialog()}}>
                    <Text style={styles.textStyle}>点击显示MessageDialog</Text>
                </TouchableOpacity>
                <MessageDialog
                    visible={this.state.visible}
                    title={this.state.title}
                    message={this.state.message}
                    onCancel={(msg)=>{console.log("onCancel", msg)}}
                    onConfirm={(msg)=>{console.log("onConfirm", msg)}}
                    onDismiss={(msg)=>{console.log("onDismiss", msg); this.setState({visible: false})}}
                />
            </View>
        );
    }

    onClickMessageDialog() {

        let clickCount = this.state.clickCount+1;
        this.setState({
            visible: true,
            title: "title  "+clickCount,
            message: "message  "+clickCount,
            clickCount: clickCount
        });

        // this.setState({visible: true })
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
        backgroundColor: '#999999'
    },
    textStyle:{
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    }

})