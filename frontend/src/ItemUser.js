import React, {Component} from 'react';

class ItemUser extends Component {
    render() {
        let user = this.props.user;
        return (
            <tr>
                <td>{user.login}</td>
                <td>{user.firstName}</td>
                <td>{user.lastName}</td>
                <td>{user.role}</td>
            </tr>
        );
    }
}

export default ItemUser;
