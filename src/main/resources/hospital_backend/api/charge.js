function getChargeList (params) {
  return $axios({
    url: '/charge/page',
    method: 'get',
    params
  })
}


// 新增收费项目
function addCharge (params) {
  return $axios({
    url: '/charge',
    method: 'post',
    data: { ...params }
  })
}

// 修改收费内容
function editCharge (params) {
  return $axios({
    url: '/charge',
    method: 'put',
    data: { ...params }
  })
}

// 删除收费的内容
const deleteCharge = (id) => {
  return $axios({
    url: '/charge',
    method: 'delete',
    params: { id }
  })
}

// 修改页面反查详情接口
function queryChargeById (id) {
  return $axios({
    url: `/charge/${id}`,
    method: 'get'
  })
}

// 获取病房信息列表
const getWardList = () => {
  return $axios({
    url: '/ward/list',
    method: 'get',
  })
}

const getPatientList = () => {
  return $axios({
    url: '/patient/list',
    method: 'get',
  })
}