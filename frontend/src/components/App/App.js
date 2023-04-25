import './App.css';
import React, {Component} from "react";
import {Navigate, Route, BrowserRouter as Router, Routes} from "react-router-dom";
import Books from "../Books/BookList/books";
import LibraryService from "../../repository/libraryRepository";
import Header from "../Header/header";
import Authors from "../Authors/authors";
import Countries from "../Countries/countries";
import Categories from "../Categories/categories";
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/bookEdit";

class App extends Component{

    constructor(props) {
        super(props);
        this.state = {
            books: [],
            authors: [],
            countries: [],
            categories: [],
            selectedBook: {}
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="{container}">
                        <Routes>
                            <Route path={"/authors"} exact element={<Authors authors={this.state.authors} />} />
                            <Route path={"/countries"} exact element={<Countries countries={this.state.countries} />} />
                            <Route path={"/categories"} exact element={<Categories categories={this.state.categories} />} />
                            <Route path={"/books/add"} exact element={<BookAdd authors={this.state.authors}
                                                                               categories={this.state.categories}
                                                                               onAddBook={this.addBook} />} />
                            <Route path={"/books/edit/:id"} exact element={<BookEdit authors={this.state.authors}
                                                                                     categories={this.state.categories}
                                                                                     onEditBook={this.editBook}
                                                                                     book={this.state.selectedBook} />} />
                            <Route path={"/books"} exact element={<Books books={this.state.books}
                                                                         onDelete={this.deleteBook}
                                                                         onEdit={this.getBook}
                                                                         onTake={this.takeBook} />} />
                            <Route path={"/"} element={<Navigate replace to="/books" />} />
                        </Routes>
                    </div>
                </main>
            </Router>
        );
    }

    loadBooks = () => {
        LibraryService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    loadAuthors = () => {
        LibraryService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }

    loadCountries = () => {
        LibraryService.fetchCountries()
            .then((data) => {
                this.setState({
                    countries: data.data
                })
            });
    }

    loadCategories = () => {
        LibraryService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }

    deleteBook = (id) => {
        LibraryService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        LibraryService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            });
    }

    addBook = (name, category, author, availableCopies) => {
        LibraryService.addBook(name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    editBook = (id, name, category, author, availableCopies) => {
        LibraryService.editBook(id, name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    takeBook = (id) => {
        LibraryService.takeBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    componentDidMount() {
        this.loadBooks();
        this.loadAuthors();
        this.loadCountries();
        this.loadCategories();
    }
}

export default App;
