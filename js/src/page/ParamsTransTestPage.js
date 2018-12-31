import React from 'react';
import {
    StyleSheet,
    Text,
    View,
    Button,
    DeviceEventEmitter
} from 'react-native';

import {RNLog} from '../native/android/index'
import ParamsTrans from '../sdk/ParamsTrans'

export default class MainPage extends React.Component {

    constructor(props){
        super(props);

        RNLog.i('MainPage', 'constructor()...');
        console.log("MainPage  ParamsTrans: ", ParamsTrans)

        this.initState();

        this.onClickCallbackBtn = this.onClickCallbackBtn.bind(this);
        this.onClickPromiseBtn = this.onClickPromiseBtn.bind(this);
        this.getParamsFromEvent = this.getParamsFromEvent.bind(this);

    }

    initState(){
        this.state = {
            callBackResult: 0,
            promiseResult: 0,
            eventResult: 0
        }
    }

    componentDidMount() {
        //注册扫描监听
        DeviceEventEmitter.addListener('eventSendParams', this.getParamsFromEvent);
    }

    getParamsFromEvent(result){
        this.setState({eventResult: result});
    }

    render() {
        return (
            <View style={styles.container}>

                <View style={styles.btnStyle}>
                    <Button
                        title='params Callback test'
                        onPress={this.onClickCallbackBtn}>
                    </Button>
                </View>

                <View style={styles.btnStyle}>
                    <Button
                        title='params promise test'
                        onPress={this.onClickPromiseBtn}>
                    </Button>
                </View>

                <View style={styles.btnStyle}>
                    <Button
                        title='params promise test'
                        onPress={(e)=>{this.onClickEventBtn()}}>
                    </Button>
                </View>

                <Text>{"callBackResult is "+this.state.callBackResult}</Text>
                <Text>{"promiseResult is "+this.state.promiseResult}</Text>
                <Text>{"eventResult is "+this.state.eventResult}</Text>

            </View>
        );
    }

    onClickCallbackBtn(){
        ParamsTrans.operateAdd(10,20, (result)=>{
            console.log("result: "+result);
            this.setState({callBackResult: result});
        });
    }

    onClickPromiseBtn(){
        ParamsTrans.operateDivide(40, 10).then((result)=>{
            console.log("result: "+result);
            this.setState({promiseResult: result});
        }).catch((error)=>{
            console.log("error: "+error);
            this.setState({promiseResult: error.toString()});
        });
    }

    onClickEventBtn(){
        ParamsTrans.sendParamsByEvent(10, 20);
    }

}
var styles = StyleSheet.create({
    container: {
        flex: 1,
        flexDirection: 'column',
        justifyContent: 'flex-start',

    },
    btnStyle: {
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
