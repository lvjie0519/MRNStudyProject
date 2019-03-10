import React from 'react';
import {
    StyleSheet,
    View,
    Button,
    ART
} from 'react-native';

const { Surface, Shape, Path, Text, Group } = ART;
import {RNLog, RNFile} from '../native/android/index'
import PageHeader from './widget/PageHeader'

export default class FileTestPage extends React.Component {

    constructor(props){
        super(props);

        RNLog.i('FileTestPage', 'constructor()...');

        this.onClickScreenShot = this.onClickScreenShot.bind(this);

    }

    render() {

        const reactPath = Path().moveTo(1, 1).lineTo(1, 100).lineTo(100, 100).lineTo(100,1).close();

        return (
            <View style={styles.container}>
                <PageHeader
                    leftOnclick={(e)=>{this.props.navigation.goBack(null)}}
                    headerCenterText={"屏幕截屏"}
                />

                <View style={{backgroundColor: '#a34', marginTop: 20}}>
                    <View style={styles.btnStyle}>
                        <Button
                            title='测试屏幕截屏'
                            onPress={(e)=>{this.onClickScreenShot()}}>
                        </Button>
                    </View>

                    <Surface width={ 200 } height={ 200 } style={{ marginTop: 20 }}>
                        <Shape d={ reactPath } stroke="#000000" fill="#FF0" strokeWidth={1} />
                    </Surface>

                    <Surface width={100} height={100} style={{ marginTop: 20 }}>
                        <Text strokeWidth={1} stroke="#000" font="bold 35px Heiti SC">空白界面</Text>
                    </Surface>

                </View>

            </View>
        );
    }

    onClickScreenShot(){
        let fileName = Date.now();
        fileName = "pic_"+fileName+".png";
        console.log("RNFile", RNFile, fileName);
        RNFile.screenShot(fileName);
    }

}
var styles = StyleSheet.create({
    container: {
        flex: 1,
        flexDirection: 'column',
        justifyContent: 'flex-start',

    },
    btnStyle: {
        marginTop:10,
        marginLeft: 30,
        marginRight:30,
    },
    hello: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
});
