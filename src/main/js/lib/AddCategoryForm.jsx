import React from 'react';
import ReactDOM from 'react-dom';
import { Form, Button, FormGroup, FormControl } from 'react-bootstrap';

class AddCategoryForm extends React.Component {
    constructor( props ) {
        super( props );
        this.state = { title: '' };
    }
    onTitleChange( e ) {
        this.setState( { title: e.target.value });
    }
    addCategory( e ) {
        fetch( '/categories', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify( {
                title: this.state.title
            })
        }).then(( response ) => {
            if ( response.ok ) {
                if ( this.props.onAddition ) {
                    ReactDOM.render( <h5></h5>, document.getElementById( 'errorMessage' ) );
                    this.props.onAddition();
                }
            } else {
                response.text().then(( responseText ) => {
                    ReactDOM.render( <h5>{responseText}</h5>, document.getElementById( 'errorMessage' ) );
                })

            }


        });
    }
    render() {
        return (
            <Form >
                <FormGroup bsSize="small">
                    <FormControl type="text" placeholder="enter category name" onChange={( e ) => this.onTitleChange( e )} value={this.state.title} />
                </FormGroup>
                <Button bsStyle="primary" bsSize="small" onClick={( e ) => this.addCategory( e )}>Save</Button>
            </Form>
        );
    }
}

module.exports = AddCategoryForm