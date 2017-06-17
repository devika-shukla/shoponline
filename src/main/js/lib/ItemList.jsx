import React from 'react';
import Item from './Item.jsx';
import AddItemForm from './AddItemForm.jsx';

import { Button, Table } from 'react-bootstrap';

class ItemList extends React.Component {
    constructor( props ) {
        super( props );
        if ( !this.props.categoryId ) {
            throw 'Category Not Provided';
        }
        this.state = { items: [], inAddMode: false };
        fetch( '/categories/' + this.props.categoryId + '/items' ).then(( response ) => response.json() ).then(( responseJson ) => {
            this.setState( { items: responseJson })
        });
    }

    /*componentDidUpdate( prevProps, prevState ) {
        if ( prevProps.categoryId != this.props.categoryId ) {
            fetch( '/categories/' + this.props.categoryId + '/items' ).then(( response ) => response.json() ).then(( responseJson ) => {
                this.setState( { items: responseJson })
            });
            return true;
        }
        return false;
    }*/
    componentWillReceiveProps(nextProps){
        if ( nextProps.categoryId != this.props.categoryId ) {
            fetch( '/categories/' + nextProps.categoryId + '/items' ).then(( response ) => response.json() ).then(( responseJson ) => {
                this.setState( { items: responseJson })
            });
        }
    }

    showItemForm( e ) {
        this.setState( { inAddMode: true });
    }

    onSuccessfullAdition() {
        this.setState( { inAddMode: false });
        fetch( '/categories/' + this.props.categoryId + '/items' ).then(( response ) => response.json() ).then(( responseJson ) => {
            this.setState( { items: responseJson })
        });

    }

    render() {
        var items = this.state.items.map( item =>
            <Item key={item.id} item={item} />
        );

        return (
            <div>
                <Table striped bordered condensed hover>
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        {items}
                        {this.state.inAddMode ? <AddItemForm categoryId={this.props.categoryId} onAddition={() => this.onSuccessfullAdition()} /> : null}
                    </tbody>
                </Table>
                {this.state.inAddMode ? null :
                    ( <Button bsStyle="primary" bsSize="small" onClick={( e ) => this.showItemForm( e )}>Add Item</Button> )}
            </div>
        );
    }
}
module.exports = ItemList;