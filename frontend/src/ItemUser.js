import React, {Component} from 'react';

class ItemUser extends Component {
    render() {
        return (
            <li>{JSON.stringify(this.props.user)}</li>
        );
    }
}

export default ItemUser;
