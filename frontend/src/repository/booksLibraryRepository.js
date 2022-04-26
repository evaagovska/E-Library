import axios from '../custom-axios/axios';

const BooksLibraryService = {
    fetchCategories: () => {
        return axios.get("/books/categories")
    },
    fetchAuthors: () => {
        return axios.get("/authors")
    },
    fetchBooks: () => {
        return axios.get("/books");
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    addBook: (name, category, author, availableCopies) => {
        return axios.post("/books/add", {
            "name": name,
            "category": parseInt(category),
            "author": parseInt(author),
            "availableCopies": parseInt(availableCopies)
        });
    },
    editBook: (id, name, category, author, availableCopies) => {
        return axios.put(`/books/edit/${id}`, {
            "name": name,
            "category": parseInt(category),
            "author": parseInt(author),
            "availableCopies": parseInt(availableCopies)
        });
    },
    markBook: (id) => {
        return axios.post(`/books/mark/${id}`);
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    }
}

export default BooksLibraryService;