import axios from '../custom-axios/axios';

const LibraryService = {
    fetchBooks: () => {
        return axios.get('/books');
    },
    fetchAuthors: () => {
        return axios.get('/authors');
    },
    fetchCountries: () => {
        return axios.get('/countries');
    },
    fetchCategories: () => {
        return axios.get('/categories');
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    addBook: (name, category, author, availableCopies) => {
        return axios.post('/books/add', {
            "name": name,
            "category": category,
            "author": author,
            "availableCopies": availableCopies
        });
    },
    editBook: (id, name, category, author, availableCopies) => {
        return axios.put(`/books/edit/${id}`, {
            "name": name,
            "category": category,
            "author": author,
            "availableCopies": availableCopies
        });
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    takeBook: (id) => {
        return axios.put(`/books/take/${id}`);
    }
}

export default LibraryService;