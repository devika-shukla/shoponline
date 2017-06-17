import React from 'react';
import ReactDOM from 'react-dom';
import { NavItem } from 'react-bootstrap';
import ItemList from './ItemList.jsx';

class Category extends React.Component {
    showItems() {

        ReactDOM.render(
            <h2>Showing Items for {this.props.category.title} category</h2>,
            document.getElementById( 'heading' )
        );
        ReactDOM.render(
            <ItemList categoryId={this.props.category.id} state={[]} />,
            document.getElementById( 'itemsList' )
        );
    }
    render() {
        return (
            <NavItem eventKey={this.props.key} onClick={( e ) => this.showItems( e )}>{this.props.category.title}</NavItem>
        );
    }
}
module.exports = Category