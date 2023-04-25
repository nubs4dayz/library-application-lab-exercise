import React from "react";
import BookTerm from "../BookTerm/bookTerm";
import {Link} from 'react-router-dom';
import ReactPaginate from "react-paginate";

class Books extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            page: 0,
            size: 5
        }
    }

    render() {

        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.books.length / this.state.size);

        const books = this.getBooksPage(offset, nextPageOffset);

        return (
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className="col mb-4">
                        <div className="row">
                            <div className="col-sm-12 col-md-12">
                                <Link className={"btn btn-block btn-success"} to={"/books/add"}>
                                    Add new book
                                </Link>
                            </div>
                        </div>
                    </div>
                    <div className={"row"}>
                        <table className={"table table-striped table-hover text-center"}>
                            <thead>
                            <tr className={"bg-dark text-light"}>
                                <th scope={"col"}>Name</th>
                                <th scope={"col"}>Category</th>
                                <th scope={"col"}>Author</th>
                                <th scope={"col"}>Available Copies</th>
                                <th scope={"col"}></th>
                            </tr>
                            </thead>
                            <tbody>
                                {books}
                            </tbody>
                        </table>
                    </div>
                </div>
                <ReactPaginate previousLabel={"Back"}
                               nextLabel={"Next"}
                               breakLabel={<a href="/#">...</a>}
                               breakClassName={"break-me"}
                               pageClassName={"m-2 btn"}
                               pageCount={pageCount}
                               marginPagesDisplayed={2}
                               pageRangeDisplayed={5}
                               onPageChange={this.handlePageClick}
                               containerClassName={"pagination m-3 justify-content-center text-center align-items-center"}
                               activeClassName={"active"}/>
            </div>
        );
    }

    handlePageClick = (data) => {
        let selected = data.selected;
        this.setState({
            page: selected
        })
    }

    getBooksPage = (offset, nextPageOffset) => {
        return this.props.books.map((term) => {
            return (
                <BookTerm term={term}
                          onDelete={this.props.onDelete}
                          onEdit={this.props.onEdit}
                          onTake={this.props.onTake} />
            );
        }).filter((book, index) => {
            return index >= offset && index < nextPageOffset;
        })
    }
}

export default Books;