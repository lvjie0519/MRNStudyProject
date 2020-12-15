/**
 * 自己编写打包脚本
 * node bin/buildBundle.js index
 * https://blog.csdn.net/sinat_17775997/article/details/110563714
 **/

//init react native  node_modules/react-native/local-cli/cli.js
require('graceful-fs').gracefulify(require('fs'));
require('react-native/local-cli/server/checkNodeVersion')();
require('react-native/setupBabel')();

const path = require('path');
const fs = require("fs");
const commander = require('commander');
const VERSION = '1.0.0';

commander
  .version(`version:${ VERSION }`)
  .usage("[options] <packagePath>")
  .option("-a, --android", "android版本")
  .option("-i, --ios", "ios版本")
  .option("-d, --debug", "debug版本")
  // .option("-t, --target [dir]", "输出的目标文件夹")
  // .option("-r, --reset-cache", "清除缓存")
  .description("生成Bundle")
  .parse(process.argv);

try {
  buildBundle();
}catch (e) {
  console.log(e);
  process.exit(1000);
}

function buildBundle() {

  if(commander.args == null || commander.args.length <1){
    commander.outputHelp();
    process.exit(1001);
  }

  let platform = commander.ios ? 'ios' : 'android';
  let buildPath = getBuildPath(commander.args[0], platform);
  console.log('111', platform, buildPath)
  makeDir(buildPath);

  let outputBundle = path.join(buildPath, "temp.bundle");
  if (fs.existsSync(outputBundle)) {
    fs.unlinkSync(outputBundle);
  }

  processOnExit();

  let bundleOutputPath = path.join(buildPath, 'index.android.bundle');
  let assetOutputPath = buildPath;
  console.log('bundleOutputPath='+bundleOutputPath, 'assetOutputPath='+assetOutputPath);

  process.argv = ["", "", "bundle",
    "--entry-file", 'index.js',
    "--bundle-output", bundleOutputPath,
    "--assets-dest", assetOutputPath,
    "--platform", 'android',
    "--dev", "false",
    "--minify", "false",      // 是否进行压缩   最后的bundle文件 大小会发生很大的变化, 看起来主要是去掉换行之类的
    "--max-workers", 1,
    "--verbose"
  ];

  require('react-native/cli').run();
}

function processOnExit() {
  process.on("exit", (code) => {

    console.log('processOnExit code is '+code);

    if (code != 0) {
      return;
    };



  });
}

/**
 * 获取产生bundle文件的根路径
 * @param argv
 * @returns {string}
 */
function getBuildPath(argv, platform) {
  let arg = (argv || "").trim();
  let buildPath = '';
  if(arg === 'index'){
    buildPath = path.join(__dirname, '..', 'build', platform);
  }else{
    buildPath = path.join(__dirname, arg, 'build', platform);
  }
  return buildPath;
}

/**
 * 同步创建文件夹
 * @param filePath
 * @returns {boolean}
 */
function makeDir(filePath) {

  if (stringIsEmpty(filePath)) {
    return false;
  }

  if (fs.existsSync(filePath)) {
    return true;
  } else {
    makeDir(path.dirname(filePath))
    fs.mkdirSync(filePath);
    return true;
  }

}

/**
 * 判断字符串是否为空
 * @param str
 * @returns {boolean}
 */
function stringIsEmpty(str) {
  if (typeof str == null || str == "" || str == "undefined") {
    return true;
  } else {
    return false;
  }
}
