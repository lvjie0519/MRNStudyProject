
import {RNParams} from '../native/android/index'

export default {

    /**
     * 获取Java层的constants
     * @returns {*}
     */
    get paramsA(){
        return RNParams.paramsA;
    },

    get paramsB(){
        return RNParams.paramsB;
    },

    /**
     *  callback 回调形式获取结果
     * @param a  int
     * @param b  int
     * @param callback   (result)=>{}
     */
    operateAdd(a, b, callback){
        RNParams.operateAdd(a, b, callback);
    },

    /**
     * 通过promise 获取结果
     * @param {int} a
     * @param {int} b
     * @returns {Promise}
     */
    operateDivide(a, b){
        return RNParams.operateDivide(a, b);
    },

    /**
     *
     * @param a int
     * @param b int
     */
    sendParamsByEvent(a, b){
        RNParams.sendParamsByEvent(a, b);
    },

    /**
     * 参数类型传递测试
     * @param  {bool} isAdd
     * @param  {int} a
     * @param {double} b
     * @param callback
     */
    paramesBoolAndNumber(isAdd, a, b, callback){
        RNParams.paramesBoolAndNumber(isAdd, a, b, callback);
    },

    /**
     *
     * @param map
     * @param array
     * @param callback
     */
    paramesMapAndArray(map, array, callback){
        RNParams.paramesMapAndArray(map, array, callback);
    }

}