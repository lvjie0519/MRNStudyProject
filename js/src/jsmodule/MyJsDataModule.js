
const BatchedBridge = require('BatchedBridge');

console.log("MyJsDataModule.js is loaded...");

const MyJsDataModule = {
  updateJsData(appKey, appParameters) {
    console.log("MyJsDataModule updateJsData exec ,  data is  ", appKey, appParameters);
    console.log("MyJsDataModule updateJsData,  start sleep... ");
    MyJsDataModule.sleep(2000);
    console.log("MyJsDataModule updateJsData,  end sleep... ");
  },

  getJsData(){
    console.log("MyJsDataModule getJsData,  start sleep... ");
    MyJsDataModule.sleep(2000);
    console.log("MyJsDataModule getJsData,  end sleep... ");
    return 'abc-abc'
  },

  sleep(delay) {
    let start = (new Date()).getTime();
    while (((new Date()).getTime() - start) < delay) {
      continue;
    }
  }
};

BatchedBridge.registerCallableModule('MyJsDataModule', MyJsDataModule);
