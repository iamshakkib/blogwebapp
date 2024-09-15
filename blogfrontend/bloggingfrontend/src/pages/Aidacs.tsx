import { BlogCard } from '../components/BlogCard';
import { Appbar } from '../components/Appbar';
import { useBlogs } from '../hooks';

export const Aidacs = () => {
    const {loading, blogs} = useBlogs();
    if(loading){
        return <div>
            loading...
        </div>
    }
    console.log(blogs.content[0]);
    blogs.content.forEach((value: any,index: any) => {
        console.log(`Element at index ${index} : ${value}`)
    });
    return (
        <div>
            <Appbar/>
            <div className='flex justify-center'>
                <div className='max-w-xl'>
                    {blogs.content.map(blog => <BlogCard id={blog.postId} name={blog.userDTO === null ? 'Anonymous' : blog.userDTO.name} title={blog.title} content={blog.content} addedDate={''} />)}
                </div>
            </div>
        </div>
    );
};
const getCurrentDate = () => {
    const now = new Date();
    return now.toLocaleDateString(); // You can customize the format as needed
};