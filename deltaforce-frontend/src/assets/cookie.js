import {ref} from "vue";

export const accessToken = ref('')
export const refreshToken = ref('')
export const uid = ref('')

export function getCookie(){
    const cookieArr = document.cookie.split(";");
    cookieArr.forEach(item=>{
        const cookiePair = item.trim().split("=");
        if("Access-Token" === cookiePair[0]){
            accessToken.value = cookiePair[1]
        }
        if("Refresh-Token" === cookiePair[0]){
            refreshToken.value = cookiePair[1]
        }
        if("uid" === cookiePair[0]){
            uid.value = cookiePair[1]
        }
    })
    return [accessToken,refreshToken,uid];
}
export function updateCookie(res){

    const expireDate = new Date();
    expireDate.setTime(expireDate.getTime() + (24*60*60*1000));
    accessToken.value = res.data.data.AccessToken
    refreshToken.value = res.data.data.RefreshToken
    uid.value = res.data.data.uid
    document.cookie = "Access-Token="+accessToken.value+"; expires="+expireDate.toUTCString()
    document.cookie = "Refresh-Token="+refreshToken.value+"; expires="+expireDate.toUTCString()
    document.cookie = "uid="+uid.value+"; expires="+expireDate.toUTCString()
}
export function deleteCookie(){
    document.cookie = "Access-Token=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
    document.cookie = "Refresh-Token=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
    document.cookie = "uid=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
}
export default getCookie;