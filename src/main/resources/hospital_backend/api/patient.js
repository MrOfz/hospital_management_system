function getPatientList (params) {
  return $axios({
    url: '/patient/page',
    method: 'get',
    params
  })
}


// 新增---添加病人
function addPatient (params) {
  return $axios({
    url: '/patient',
    method: 'post',
    data: { ...params }
  })
}

// 修改---添加员工
function editPatient (params) {
  return $axios({
    url: '/patient',
    method: 'put',
    data: { ...params }
  })
}

// 删除病人的相关信息
const deletePatient = (id) => {
  return $axios({
    url: '/patient',
    method: 'delete',
    params: { id }
  })
}

// 修改页面反查详情接口
function queryPatientById (id) {
  return $axios({
    url: `/patient/${id}`,
    method: 'get'
  })
}

// 获取医生信息列表
const getEmployeeList = () => {
  return $axios({
    url: '/employee/list',
    method: 'get',
  })
}

// 获取病房信息列表
const getWardList = () => {
  return $axios({
    url: '/ward/list',
    method: 'get',
  })
}