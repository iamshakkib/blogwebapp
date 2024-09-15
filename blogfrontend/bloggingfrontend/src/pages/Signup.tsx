import { Quote } from "../components/Quote"
import {Auth} from "../components/Auth"
import { Appbar } from "../components/Appbar"
export const Signup = () =>{
    return <div>
        <Appbar/>
        <div className="grid grid-cols-1 lg:grid-cols-2">
            <div>
                <Auth type="signup"/>
            </div>
            <div className="invisible lg:visible">
                <Quote />
            </div>
        </div>
    </div>
}