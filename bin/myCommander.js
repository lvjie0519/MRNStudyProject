const commander = require('commander');

/**
 * https://www.cnblogs.com/1wen/p/10142210.html
 * program 已经是一个实例，
 * .usage  仅仅描述了参数规则，会在 --help 中打印出来。
 * .option 定义了一个参数名和描述，  parse 会解析命令之中的参数，根据上面定义好的规则执行相关命令。
 * 比如上面的代码定义了 option 类型的参数 --type，执行 .parse 的时候，parse 根据 process.argv 之中的参数，
 * 获取到 --type，并把参数命和参数值存储在内部 commander 实例的属性之中，
 * 因此后面的代码就能从 program 之中取到 type，如果 type 不存在或者不是我们约定的值，
 * 最后我们打印参数错误，并执行help方法打印了 --help。  如下截图，我们 node 执行 cli-test，
 * 因为没有约定参数，所以执行了 else 的程序。（因为这里是本地的demo程序，所以直接使用node命令）
 */
commander
  .usage('[option]', '--type required')
  .option('--type [typeName]', 'type: dev && build')
  .parse(process.argv);

function run(){

  console.log('commander.args', commander.args)

  let {type} = commander;
  if(type == 'dev'){
    console.log('do something', type)
  }else if(type == 'build'){
    console.log('do something', type)
  }else{
    console.log('params error, type is ', type);
    commander.help();
  }

}

try {
  run();
} catch (err) {
  console.log(err);
}
