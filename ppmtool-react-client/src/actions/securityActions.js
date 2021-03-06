import axios from 'axios';
import { GET_ERRORS, SET_CURRENT_USER } from './types';
import setJWTToken from '../securityUtils/setJWTToken';
import jwt_decode from "jwt-decode"


export const createNewUser = (newUser, history) => async dispatch => {
    try {
        await axios.post("/api/users/register", newUser)
        history.push("/login");
        dispatch({
            type: GET_ERRORS,
            payload: {}
        })
    }
    catch (error) {
        dispatch({
            type: GET_ERRORS,
            payload: error.response.data
        })
    }
};

export const login = LoginRequest => async dispatch => {

    try {

        const res = await axios.post("/api/users/login", LoginRequest)
        const { token } = res.data;
        localStorage.setItem("jwtToken", token);
        //set token in header
        setJWTToken(token)
        //decode token

        const decoded = jwt_decode(token);

        //dispatch to securityReducer
        dispatch({
            type: SET_CURRENT_USER,
            payload: decoded
        })


    }
    catch (err) {
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data
        })

    }


}



export const logout = () => dispatch => {
    localStorage.removeItem("jwtToken")
    setJWTToken(false)
    dispatch({
        type: SET_CURRENT_USER,
        payload: {}
    })
}