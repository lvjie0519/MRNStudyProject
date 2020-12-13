/**
 * String 的工具类
 **/

export default {

  isEmpty(str) {
    if (typeof str == null || str == "" || str == "undefined") {
      return true;
    } else {
      return false;
    }
  }

}

