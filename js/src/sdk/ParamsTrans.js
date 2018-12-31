
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

}