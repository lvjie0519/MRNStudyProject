import React, { Component } from 'react';
import {
    StyleSheet,
    Dimensions,
    View,
    Text,
    Image,
    Platform,
    TouchableOpacity
} from 'react-native';

import PropTypes from 'prop-types';

const STATUSBAR_HEIGHT = Platform.OS === 'ios' ? 20 : 0;

export default class PageHeader extends Component{

    static propTypes = {
        headerCenterText: PropTypes.string,
        leftOnclick: PropTypes.func.isRequired
    }

    static defaultProps = {
        headerCenterText: "aaa",
    };

    constructor(props){
        super(props);

        this.leftOnclick = this.leftOnclick.bind(this);
    }

    render(){

        return(
            <View>
                <View style={[styles.header, Platform.OS == 'ios' ? {paddingTop: STATUSBAR_HEIGHT} : null]}>
                    <TouchableOpacity
                        onPress={this.leftOnclick}>
                        <Image
                            style={styles.leftPng}
                            source={require("../../../res/ic_header_back.png")}
                        />
                    </TouchableOpacity>
                    <Text style={styles.centerText}>{this.props.headerCenterText}</Text>
                </View>
                <View style={styles.divideLine}/>
            </View>

        );

    }

    leftOnclick(){
        if(this.props.leftOnclick != null){
            this.props.leftOnclick();
        }
    }

}

var screenWidth = Dimensions.get('window').width;

const styles = StyleSheet.create({
    header: {
        backgroundColor:'#f5f5f5',
        width: screenWidth,
        height: 44,
        flexDirection:'row',
        alignItems:'center'
    },
    divideLine:{
        width:screenWidth,
        height:0.5,
        backgroundColor:'#cccccc',
    },
    centerText:{
        flex:1,
        color:'#333333',
        textAlign:'center',
        fontSize:18,
        marginRight:30,
    },
    leftPng:{
        width:30,
        height:30,
    },

});