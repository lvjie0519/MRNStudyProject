import React from 'react';
import {AppRegistry, StyleSheet, Text, View, DeviceEventEmitter} from 'react-native';

import App from "./js/src/App"

import  "./js/src/jsmodule/MyJsDataModule"

DeviceEventEmitter.addListener('onJsDataUpdate', (data) => {
  console.log("receive event onJsDataUpdate,  data is  ", data);
  console.log("receive event onJsDataUpdate,  start sleep... ");
  sleep(2000);
  console.log("receive event onJsDataUpdate,  end sleep... ");
});

function sleep(delay) {
  var start = (new Date()).getTime();
  while(((new Date()).getTime() - start) < delay) {
    continue;
  }
}

class HelloWorld extends React.Component {
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.hello}>Hello, World</Text>
      </View>
    );
  }
}
var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
});

console.log("AppRegistry.registerComponent...")
AppRegistry.registerComponent('MyReactNativeApp', () => App);
