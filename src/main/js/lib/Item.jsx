import React from 'react'
class Item extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.item.id}</td>
                <td>{this.props.item.title}</td>
                <td>{this.props.item.text}</td>
                <td>{this.props.item.price}</td>
            </tr>
        );
    }
}

module.exports = Item
