import React, {Component} from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  View
} from 'react-native';

import PageManage from './PageManage'

export default class App extends Component {

  constructor(props){
    super(props);
    console.log("app constructor...")
  }



  render() {

    console.log("app render...")

    return (
      <View style={{flex: 1}}>
        <PageManage/>
      </View>

    );
  }

  componentDidMount() {
    console.log("app componentDidMount...")
  }

  componentWillUnmount() {
    console.log("app componentWillUnmount...")
  }

}
