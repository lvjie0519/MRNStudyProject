import React, {Component} from 'react';
import {
    requireNativeComponent,
    ViewPropTypes,
} from 'react-native';

import PropTypes from 'prop-types';

const RCTMessageDialog = requireNativeComponent('RCTMessageDialog', null);

export default class MessageDialog extends Component{
    static propTypes = {
        /**
         * 是否可见
         * @member {bool}
         */
        visible: PropTypes.bool,

        /**
         * 标题
         * @member {string}
         */
        title: PropTypes.string,

        /**
         * 消息
         * @member {string}
         */
        message: PropTypes.string,

        /**
         * 确认点击回调
         * @member {func}
         */
        onConfirm: PropTypes.func,

        /**
         * 取消点击回调
         * @member {func}
         */
        onCancel: PropTypes.func,

        /**
         * 取消点击回调
         * @member {func}
         */
        onDismiss: PropTypes.func,

        ...ViewPropTypes,

    }

    render() {
        return <RCTMessageDialog
                {...this.props}
                onConfirm={(event) => {
                    if (this.props.onConfirm) {
                        this.props.onConfirm(event.nativeEvent);
                    }
                }}
                onCancel={(event) => {
                    if (this.props.onCancel) {
                        this.props.onCancel(event.nativeEvent);
                    }
                }}
                onDismiss={(event) => {
                    if (this.props.onDismiss) {
                        this.props.onDismiss(event.nativeEvent);
                    }
                }}
            />
    }
}