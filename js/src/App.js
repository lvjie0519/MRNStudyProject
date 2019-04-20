import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  View
} from 'react-native';

import PageManage from './PageManage'

export default class App extends Component{
    render() {

        console.log("app render...")

        return (
            <View style={{flex: 1}}>
              <PageManage/>
            </View>

        );
  }
}