
const BatchedBridge = require('BatchedBridge');

console.log("MyJsDataModule.js is loaded...");

const MyJsDataModule = {
  updateJsData(appKey, appParameters) {
    console.log("MyJsDataModule updateJsData exec ,  data is  ", appKey, appParameters);
    console.log("MyJsDataModule updateJsData,  start sleep... ");
    MyJsDataModule.sleep(2000);
    console.log("MyJsDataModule updateJsData,  end sleep... ");
  },

  sleep(delay) {
    let start = (new Date()).getTime();
    while (((new Date()).getTime() - start) < delay) {
      continue;
    }
  }
};

BatchedBridge.registerCallableModule('MyJsDataModule', MyJsDataModule);
