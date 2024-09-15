import { Link } from "react-router-dom"
import { Avatar } from "./BlogCard"

export const Appbar = () => {
    var str = localStorage.getItem('username');
    return <Link to={"/"}>
        <div className="border-b flex justify-between px-10 ">
            <div className="flex flex-col justify-center cursor-pointer">
                Aidacs
            </div>
            <div>
                <Avatar name={str || "Anonymous"} size={"big"}/>
            </div>
        </div>
    </Link>
}