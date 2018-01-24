import React, {Component} from 'react';
import ItemUser from './ItemUser';

const URL = "http://localhost:8080/admin/0/users";

class ListOfUsers extends Component {
    constructor(props) {
        super(props);
        this.state = {list: null};

        this.loadList = this.loadList.bind(this);
    }

    componentDidMount() {
        this.loadList();
    }

    render() {
        if (!this.state.list) {
            return (
                <p>Loading...</p>
            );
        }

        return (
            <ul>
                {this.state.list.map(user =>
                    <ItemUser key={user.id} user={user}/>)}
            </ul>
        );
    }

    async loadList() {
        let resp = await fetch(URL);
        let data = await resp.text();
        this.setState({list: JSON.parse(data)});
    }
}

export default ListOfUsers;
