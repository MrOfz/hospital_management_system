function getWardList (params) {
  return $axios({
    url: '/ward/page',
    method: 'get',
    params
  })
}

//查询科室信息
function getDepartmentList (params) {
  return $axios({
    url: '/department/list',
    method: 'get',
    params
  })
}

// 修改病房
function enableOrDisableWard (params) {
  return $axios({
    url: '/ward',
    method: 'put',
    data: { ...params }
  })
}

// 新增病房
function addWard (params) {
  return $axios({
    url: '/ward',
    method: 'post',
    data: { ...params }
  })
}

// 修改病房
function editWard (params) {
  return $axios({
    url: '/ward',
    method: 'put',
    data: { ...params }
  })
}

// 修改页面反查详情接口
function queryWardById (id) {
  return $axios({
    url: `/ward/${id}`,
    method: 'get'
  })
}