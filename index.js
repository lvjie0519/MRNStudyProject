import React from 'react';
import {AppRegistry, StyleSheet, Text, View} from 'react-native';

import App from "./js/src/App"

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
