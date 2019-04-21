import React from 'react';
import {
    StyleSheet,
    View,Image
} from 'react-native';

import {RNLog, RNFile} from '../native/android/index'
import PageHeader from './widget/PageHeader'

export default class SelfDefineViewTestPage extends React.Component {

    constructor(props){
        super(props);

        RNLog.i('SelfDefineViewTestPage', 'constructor()...');

    }

    render() {

        return (
            <View style={styles.container}>
                <PageHeader
                    leftOnclick={(e)=>{this.props.navigation.goBack(null)}}
                    headerCenterText={"自定义View测试"}
                />

                <View style={{flex: 1}}>

                    <Image
                        style={{width:200, height: 80}}
                        source={require("../../res/ic_header_back.png")}
                    />

                </View>

            </View>
        );
    }



}
var styles = StyleSheet.create({
    container: {
        flex: 1,
        flexDirection: 'column',
        justifyContent: 'flex-start',
    },
});
