import axios from "axios";
import { useEffect, useState } from "react"
import { BACKEND_URL } from "../config";

export const useBlogs = () => {
    const[loading, setLoading] = useState(true);
    const[blogs, setBlogs] = useState<any[]>([]);

    useEffect(()=>{
        axios.get(`${BACKEND_URL}/api/v1/posts`).then(response => {
            setBlogs(response.data);
            setLoading(false);
        })
    },[])

    return{
        loading,
        blogs
    }
}

export const useBlog = ({ id }: { id: number; }) => {
    const[loading, setLoading] = useState(true);
    const[blog, setBlog] = useState<any[]>([]);

    useEffect(()=>{
        axios.get(`${BACKEND_URL}/api/v1/posts/${id}`).then(response => {
            setBlog(response.data);
            setLoading(false);
        })
    },[id])

    return{
        loading,
        blog
    }
}