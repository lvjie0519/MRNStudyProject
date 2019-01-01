import React from 'react';
import {
    StyleSheet,
    Text,
    View,
    TouchableOpacity,
} from 'react-native';

import PropTypes from 'prop-types';

class MyButton extends React.Component{

    static propTypes = {
        title: PropTypes.string,
        btnStyleInfo: PropTypes.object,
        onClick: PropTypes.func,
    }

    static defaultProps = {
        headerCenterText: "",
    };

    /**
     * 性能优化正是在此函数中进行，如果返回true，则会执行render，不会执行render
     * @param nextProps
     * @param nextState
     * @param nextContext
     * @returns {boolean}
     */
    shouldComponentUpdate(nextProps, nextState, nextContext) {
        console.log("MyButton--->shouldComponentUpdate...",nextProps, nextState)

        // if(this.props.title === nextProps.title){
        //     return false;
        // }

        return true
    }

    constructor(props){
        super(props);

        console.log("MyButton-->props: ", this.props);
    }

    render() {

        console.log("MyButton-->render()...", this.props);

        /**
         * 注意，此处的props值是无法修改的，即便是对象，也无法修改，虽然不会报错，但是无法改变
         * @type {string}
         */
        this.props.title = "我的按钮二"
        this.props.btnStyleInfo = {
            btnBg:"#999999",
            btnTextStyle: {
                textSize: 20
            },
        }

        console.log("MyButton-->render()...", this.props);

        return (
            <TouchableOpacity
                style={[styles.btnStyle, {backgroundColor:this.props.btnStyleInfo.btnBg}]}
                onPress={(e)=>{if(this.props.onClick){this.props.onClick()}}}>
                <Text style={styles.btnText}>{this.props.title}</Text>
            </TouchableOpacity>
        );
    }
}

/**
 * 生命周期相关
 * 首次进入：componentWillMount->render->componentDidMount
 * state改变：shouldComponentUpdate->componentWillUpdate->render->componentDidUpdate
 *
 */
export default class PropsAndStatePage extends React.Component{

    constructor(props){
        super(props);

        this.state = {isRefresh: false};

        this.onClickBtn = this.onClickBtn.bind(this);
    }

    /**
     * 准备加载组件
     * 这个函数调用时机是在组件创建，并初始化了状态之后，在第一次绘制 render()之前。
     * 可以在这里做一些业务初始化操作，也可以设置组件状态。
     * 这个函数在整个生命周期中只被调用一次。
     */
    componentWillMount() {
        console.log("PropsAndStatePage-->componentWillMount()...");
    }

    componentWillUpdate(nextProps, nextState, nextContext) {
        console.log("PropsAndStatePage-->componentWillUpdate()...");
    }

    /**
     * 当组件接收到新的属性和状态改变的话，都会触发调用
     * 这个函数的返回值决定是否需要更新组件，如果 true表示需要更新，继续走后面的更新流程。否者，则不更新
     * @param nextProps
     * @param nextState
     * @param nextContext
     * @returns {boolean}
     */
    shouldComponentUpdate(nextProps, nextState, nextContext) {
        let result = true;
        console.log("PropsAndStatePage-->shouldComponentUpdate()...result="+result);
        return result;
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        console.log("PropsAndStatePage-->componentDidUpdate()...");
    }

    /**
     * 如果组件收到新的属性（props），就会调用
     * 在这个回调函数里面，你可以根据属性的变化，通过调用 this.setState() 来更新你的组件状态，
     * 这里调用更新状态是安全的，并不会触发额外的 render() 调用。
     * @param nextProps
     * @param nextContext
     */
    componentWillReceiveProps(nextProps, nextContext) {
        console.log("PropsAndStatePage-->componentWillReceiveProps()...");
    }

    render(){

        console.log("PropsAndStatePage-->render()...");

        let btnStyleInfo = {
            btnBg:"#333333",
            btnTextStyle: {
                textSize: 10
            },
        }

        return (
            <View>
                <MyButton
                    title="我的按钮一"
                    btnStyleInfo={btnStyleInfo}
                    onClick={this.onClickBtn}
                />
            </View>
        );
    }

    componentWillUnmount() {
        console.log("PropsAndStatePage-->componentWillUnmount()...");
    }

    componentDidMount() {
        console.log("PropsAndStatePage-->componentDidMount()...");
    }

    onClickBtn(){
        /**
         * 只要每更新一次state的值，都会更新一次UI
          */
        this.setState({isRefresh: true})
    }
}

var styles = StyleSheet.create({
    container: {
        flex: 1,
        flexDirection: 'column',
        justifyContent: 'flex-start',

    },
    btnStyle: {
        flexDirection: "row",
        justifyContent: "center",
        alignItems: "center"
    },
    btnText: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
});