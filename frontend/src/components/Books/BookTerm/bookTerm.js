import React from "react";
import {Link} from 'react-router-dom';

const bookTerm = (props) => {
    return (
        <tr className={"text-center align-middle"}>
            <td scope={"col"}>{props.term.name}</td>
            <td scope={"col"}>{props.term.category}</td>
            <td scope={"col"}>{props.term.author.name + " " + props.term.author.surname}</td>
            <td scope={"col"}>{props.term.availableCopies}</td>
            <td scope={"col"} className={"text-right"}>

                <a title={"Take"} className={`btn btn-warning m-1 ${props.term.availableCopies <= 0 ? 'disabled' : ''}`}
                   onClick={() => props.onTake(props.term.id)}>
                    Take
                </a>

                <Link className={"btn btn-info text-white"}
                      onClick={() => props.onEdit(props.term.id)}
                      to={`/books/edit/${props.term.id}`}>
                    Edit
                </Link>

                <a title={"Delete"} className={"btn btn-danger m-1"}
                   onClick={() => props.onDelete(props.term.id)}>
                    Delete
                </a>
            </td>
        </tr>
    );
}

export default bookTerm;