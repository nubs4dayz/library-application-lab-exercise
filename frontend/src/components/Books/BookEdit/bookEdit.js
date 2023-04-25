import React from "react";
import {useNavigate} from 'react-router-dom';

const BookEdit = (props) => {

    const navigate = useNavigate();

    const [formData, updateFormData] = React.useState({
        name: "",
        category: "",
        author: 1,
        availableCopies: 0
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name !== "" ? formData.name : props.book.name;
        const category = formData.category !== "" ? formData.category : props.book.category;
        const authorId = formData.author !== 0 ? formData.author : props.book.author.id;
        const availableCopies = formData.availableCopies !== "" ? formData.availableCopies : props.book.availableCopies;

        props.onEditBook(props.book.id, name, category, authorId, availableCopies);
        navigate("/books");
    }

    return(
        <div className="row m-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>

                    <div className="form-group">
                        <label htmlFor="name">Book name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder={props.book.name}
                               onChange={handleChange}
                        />
                    </div>

                    <div className="form-group mt-3">
                        <label>Category</label>
                        <select name="category" id="category" className="form-control" onChange={handleChange}>
                            {props.categories.map((term) => {
                                if (props.book.category !== undefined && props.book.category === term)
                                    return <option selected={props.book.category}>
                                                 {term}
                                            </option>
                                else
                                    return <option value={term}>
                                                {term}
                                            </option>
                            })}
                        </select>
                    </div>

                    <div className="form-group mt-3">
                        <label>Author</label>
                        <select name="author" id="author" className="form-control" onChange={handleChange}>
                            {props.authors.map((term) => {
                                if (props.book.author !== undefined && props.book.author.id === term.id)
                                    return <option selected={props.book.author.id}>
                                                {term.name + " " + term.surname}
                                            </option>
                                else
                                    return <option value={term.id}>
                                                {term.name + " " + term.surname}
                                            </option>
                            })}
                        </select>
                    </div>

                    <div className="form-group mt-3">
                        <label htmlFor="availableCopies">Available copies</label>
                        <input type="number"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               placeholder={props.book.availableCopies}
                               required
                               onChange={handleChange}
                        />
                    </div>

                    <button id="submit" type="submit" className="btn btn-success mt-3">Submit</button>

                </form>
            </div>
        </div>
    );
}

export default BookEdit;