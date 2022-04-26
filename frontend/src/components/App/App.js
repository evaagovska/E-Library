
import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Navigate, Route, Routes} from 'react-router-dom';
import Header from "../Header/header";
import BooksList from "../Books/BooksList/booksList";
import BooksEdit from "../Books/BooksEdit/booksEdit";
import Categories from "../Categories/categories";
import BooksAdd from "../Books/BooksAdd/booksAdd";
import BooksLibraryService from "../../repository/booksLibraryRepository";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            selectedBook: {},
            books: [],
            authors: [],
            categories: []
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Routes>
                            <Route path={"/"} element={<BooksList books={this.state.books}
                                                                  onDelete={this.deleteBook}
                                                                  onEdit={this.getBook}/>}/>
                            <Route path={"/categories"} element={<Categories categories={this.state.categories}/>}/>
                            <Route path={"/books"} element={<BooksList books={this.state.books}
                                                                       onDelete={this.deleteBook}
                                                                       onMark={this.markBook}
                                                                       onEdit={this.getBook}/>}/>
                            <Route path={"/books/add"} element={<BooksAdd categories={this.state.categories}
                                                                          authors={this.state.authors}
                                                                          onAddBook={this.addBook}/>}/>
                            <Route path={"/books/edit/:id"} element={<BooksEdit categories={this.state.categories}
                                                                                authors={this.state.authors}
                                                                                onEditBook={this.editBook}
                                                                                book={this.state.selectedBook}/>}/>


                        </Routes>

                    </div>
                </main>
            </Router>
        );
    }

    componentDidMount() {
        this.fetchData()
    }

    fetchData = () => {
        this.loadCategories();
        this.loadBooks();
        this.loadAuthors();
    }

    loadCategories = () => {
        BooksLibraryService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            })
    }

    loadBooks = () => {
        BooksLibraryService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    deleteBook = (id) => {
        BooksLibraryService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    markBook = (id) => {
        BooksLibraryService.markBook(id)
            .then(() => {
                this.loadBooks();
            })
    }

    addBook = (name, category, author, availableCopies) => {
        BooksLibraryService.addBook(name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        BooksLibraryService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }

    editBook = (id, name, category, author, availableCopies) => {
        BooksLibraryService.editBook(id, name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    loadAuthors = () => {
        BooksLibraryService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            })
    }




}

export default App;