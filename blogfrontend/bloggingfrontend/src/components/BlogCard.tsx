import { Link } from "react-router-dom";

interface BlogCardsProps{
    id: number;
    name: string;
    title: string;
    content: string;
    addedDate: string;
}
export const BlogCard = ({
    id,
    name,
    title,
    content,
    addedDate
}:BlogCardsProps) => {
    return <Link to={`/blog/${id}`}>
        <div className="p-4 border-b border-slate-200 pb-4 w-screen max-w-screen-md cursor-pointer">
            <div className="flex">
                    <Avatar name={name} size="small"/>
                    <div className="pl-2 font-extralight text-sm flex justify-center flex-col">
                        {name}
                    </div> 
                    <div className="flex justify-center pl-2 justify-center flex-col">
                        <Circle />
                    </div>
                    <div className="pl-2 font-thin text-slate-500 text-sm flex justify-center flex-col">
                        {addedDate}
                    </div>
            </div>
            <div className="text-xl font-semibold pt-2">
                {title}
            </div>
            <div className="text-md font-thin">
                {content.length > 100 ? content.slice(0,100)+"..." : content}
            </div>
            <div className="text-slate-500 text-sm font-thin pt-4">
                {`${Math.ceil(content.length /100)} mintues(s) read`}
            </div>
        </div>
    </Link> 
}
export function Avatar({name,size="small"}:{name:string,size:"small"|"big"}){
    return <div className={`relative inline-flex items-center justify-center overflow-hidden 
    bg-gray-100 rounded-full ${size=="small" ? "w-6 h-6":"w-10 h-10"} dark:bg-gray-600`}>
    <span className="text-xs font-extralight text-gray-600 dark:text-gray-300">{name[0]}</span>
</div>
}
function Circle() {
    return <div className="h-1 w-1 rounded-full bg-slate-500">
    </div>
}