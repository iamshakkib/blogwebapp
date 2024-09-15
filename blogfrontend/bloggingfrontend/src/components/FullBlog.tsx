import { Appbar } from "./Appbar"
import { Avatar } from "./BlogCard"

export const FullBlog = ({blog}:any) => {
    const str = localStorage.getItem('username');
    return <div>
        <Appbar/>
        <div className="flex justify-center">
            <div className="grid grid-cols-12 px-10 w-full pt-200 max-h-2xl pt-10">
                <div className="col-span-8">
                    <div className="text-3xl font-extrabold">
                        {blog.title}
                    </div>
                    <div className="text-slate-500 pt-4">
                        Post on 12th Jan 2024
                    </div>
                    <div className="">
                        {blog.content}
                    </div>
                </div>
                <div className="col-span-4">
                    <div className="text-slate-600 text-lg">Author</div>
                    <div className="flex">
                        <div className="pr-2 flex flex-col justify-center">
                        <Avatar size="big" name = {str || "Anonymous"} />
                        </div>
                        <div>
                            <div className="text-xl font-bold">
                                {blog.name || "Anonymous"}
                            </div>
                            <div className="pt-2 text-slate-500">
                                Details about author's ability to grab attention
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
}