/**
 * commander的学习
 **/

const program =require('commander');
program
  .usage('[option]', '--type required')   // 描述了参数规则，会在 --help 中打印出来
  .option('--type [typeName]', 'type: dev || build') // 定义了一个参数名和描述
  .parse(process.argv);  // 解析命令之中的参数，根据上面定义好的规则执行相关命令


/**
 * node js/bin/commander-test.js --type dev
 * program对象中的type为'dev'
 *
 * node js/bin/commander-test.js bbb --type dev aaa
 * program对象中的type为'dev'， args为[ 'bbb', 'aaa' ]
 */
try {
  run();
}catch (e) {
  console.log(e.toString())
}

function run(){
  const {type} = program;
  console.log(program)
  if(type == 'dev'){
    console.log('do something', type)
  }else if(type == 'build'){
    console.log('do something', type)
  }else{
    console.log('params error');
    program.help();
  }
}
