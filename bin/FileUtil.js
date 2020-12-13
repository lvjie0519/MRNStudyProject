
// import StringUtil from './StringUtil'
const path = require('path');
const fs = require("fs");

/**
 * 同步创建文件夹
 * @param filePath
 * @returns {boolean}
 */
function makeDir(filePath) {

  // if (StringUtil.isEmpty(filePath)) {
  //   return false;
  // }
  //
  // if (fs.existsSync(filePath)) {
  //   return true;
  // } else {
  //   this.makeDir(path.dirname(filePath))
  //   fs.mkdirSync(filePath);
  //   return true;
  // }

}

module.exports = {
  makeDir,
}

