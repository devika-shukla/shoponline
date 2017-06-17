import React from 'react';
import { Nav, NavItem, Navbar, Button } from 'react-bootstrap';
import Category from './Category.jsx';
import AddCategoryForm from './AddCategoryForm.jsx';

class CategoryList extends React.Component {
    constructor( props ) {
        super( props );
        this.state = { categories: [], inAddMode: false };
    }

    componentDidMount() {

        fetch( '/categories' ).then(( response ) => response.json() ).then(( responseJson ) => {
            this.setState( { categories: responseJson })
        });
    }

    showCategoryInput() {
        this.setState( { inAddMode: true });
    }

    onSuccessfullAdition() {
        this.setState( { inAddMode: false });
        fetch( '/categories' ).then(( response ) => response.json() ).then(( responseJson ) => {
            this.setState( { categories: responseJson })
        });

    }
    render() {
        var categories = this.state.categories.map( category =>
            <Category key={category.id} category={category} />
        );

        return (
            <div id="sidebar-menu" className="sideBarMenuContainer">
                <Navbar fluid className="sidebar" inverse>
                    <Navbar.Header>
                        <Navbar.Toggle />
                        <Navbar.Brand>
                            <a href="/">Shop</a>
                        </Navbar.Brand>
                    </Navbar.Header>
                    <Navbar.Collapse>
                        <Nav>
                            {categories}
                            <NavItem id="addCategory"></NavItem>
                            {this.state.inAddMode ?
                                <AddCategoryForm onAddition={() => this.onSuccessfullAdition()} /> :
                                ( <NavItem eventKey="button">
                                    <Button bsStyle="primary" bsSize="small" onClick={( e ) => this.showCategoryInput( e )}>Add Category</Button>
                                </NavItem> )
                            }
                        </Nav>

                    </Navbar.Collapse>
                </Navbar>
            </div>

        );
    }
}

module.exports = CategoryList