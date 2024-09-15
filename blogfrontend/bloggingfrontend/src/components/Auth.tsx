import axios from "axios";
import { ChangeEvent, useState } from "react";
import {Link, useNavigate} from "react-router-dom";
import { BACKEND_URL } from "../config";
export const Auth = ({type}:{type: "signin" | "signup"}) => {
    const navigate = useNavigate();
    const [postInputs, setPostInputs] = useState({
        name: "",
        email:"",
        password:"",
        username:"",
        about:""
    });

    async function sendRequest(){
        try{
            const response = await axios.post(`${BACKEND_URL}/api/v1/auth/${type === "signin" ? "login" : "register"}`,postInputs);
            if(type==="signin"){
                localStorage.setItem("token",response.data.token);
                localStorage.setItem("username",response.data.user.name);
                navigate("/aidacs");
            }else{
                navigate("/signin");
            }
        }catch(e){
            alert("Error while signing up")
            console.log(e);
        }
    }

    return <div className="h-screen flex justify-center flex-col">

            <div className="flex justify-center">
                <div>
                <div className="px-10 ">
                    <div className="text-4xl font-extrabold">
                        {type==="signup" ? "Create an Account" : "SignIn To Your Account"}
                    </div>
                    <div className="pl-10 mt-4 text-slate-500 ">
                        {type==="signin"?"Don't have an account ?":"Already have an account ?"}
                        <Link className="pl-2 underline" to={type === "signin"?"/signup":"/signin"}>{type==="signin" ? "Sign up":"Sign in"}</Link>
                    </div>
                </div>
                <div className="pt-4">
                    <div hidden={type==="signin"}>
                    <LabelledInput label="Name" placeholder="name" onChange={(e)=>{
                        setPostInputs(c=>({
                            ...postInputs,
                            name:e.target.value, //take input from user
                        }))
                    }} />
                    </div>
                    <div hidden={type==="signin"}>
                    <LabelledInput label="Email" placeholder="email" onChange={(e)=>{
                        setPostInputs(c=>({
                            ...postInputs,
                            email:e.target.value, //take input from user
                        }))
                    }} />
                    </div>
                    <div hidden={type==="signup"}>
                    <LabelledInput label="Email" placeholder="email" onChange={(e)=>{
                        setPostInputs(c=>({
                            ...postInputs,
                            username:e.target.value, //take input from user
                        }))
                    }} />
                    </div>
                    <div hidden={type==="signin"}>
                    <LabelledInput label="Bio" placeholder="bio" onChange={(e)=>{
                        setPostInputs(c=>({
                            ...postInputs,
                            about:e.target.value, //take input from user
                        }))
                    }} />
                    </div>
                    <LabelledInput label="Password" type={"password"} placeholder="password" onChange={(e)=>{
                        setPostInputs(c=>({
                            ...postInputs,
                            password:e.target.value, //take input from user
                        }))
                    }} />
                    <button onClick={sendRequest} type="button" className="mt-8 w-full text-white bg-gray-800 hover:bg-gray-900 focus:outline-none 
                    focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-gray-800 
                    dark:hover:bg-gray-700 dark:focus:ring-gray-700 dark:border-gray-700">{type === "signup" ? "Sign Up": "Sign In"}</button>
                </div>
            </div>
        </div>
    </div>
}

interface LabelledInputTypes{
    label: string;
    placeholder: string;
    onChange: (e: ChangeEvent<HTMLInputElement>) => void;
    type?: string;
}

function LabelledInput({label,placeholder,onChange,type}: LabelledInputTypes){
    return <div>
    <label className="block mb-2 text-sm font-semibold text-black pt-4">{label}</label>
    <input onChange={onChange}type={type||"text"} id="first_name" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm 
    rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5" placeholder={placeholder} required />
</div>
}