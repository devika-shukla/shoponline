import React from 'react';
import ReactDOM from 'react-dom';
import { Button } from 'react-bootstrap';

class AddItemForm extends React.Component {

    constructor( props ) {
        super( props );
        this.state = { title: '', text: '', price: '' };
    }
    onTitleChange( e ) {
        this.setState( { title: e.target.value });
    }
    onTextChange( e ) {
        this.setState( { text: e.target.value });
    }
    onPriceChange( e ) {
        this.setState( { price: e.target.value });
    }
    addItem( e ) {
        fetch( '/categories/' + this.props.categoryId + '/items', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify( {
                title: this.state.title,
                text: this.state.text,
                price: this.state.price
            })
        }).then(( response ) => {
            if ( response.ok ) {
                if ( this.props.onAddition ) {
                    this.props.onAddition();
                    ReactDOM.render( <h5></h5>, document.getElementById( 'errorMessage' ) );
                }
            } else {
                console.log( response );
                response.text().then(( responseText ) => {
                    ReactDOM.render( <h5>{responseText}</h5>, document.getElementById( 'errorMessage' ) );
                })

            }


        });
    }

    render() {
        return (
            <tr>
                <td>#</td>
                <td><input type="text" placeholder="enter title" onChange={( e ) => this.onTitleChange( e )} value={this.state.title}></input></td>
                <td><input type="text" placeholder="enter description" onChange={( e ) => this.onTextChange( e )} value={this.state.text}></input></td>
                <td>
                    <input type="text" placeholder="enter price" onChange={( e ) => this.onPriceChange( e )} value={this.state.price}></input>
                    <Button bsStyle="primary" bsSize="small" onClick={( e ) => this.addItem( e )}>Save</Button>
                </td>

            </tr>

        );
    }

}

module.exports = AddItemForm