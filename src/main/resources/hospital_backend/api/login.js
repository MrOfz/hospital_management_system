//登录接口
function loginApi(data) {
  return $axios({
    'url': '/employee/login',
    'method': 'post',
    data
  })
}

//退出接口
function logoutApi(){
  return $axios({
    'url': '/employee/logout',
    'method': 'post',
  })
}
