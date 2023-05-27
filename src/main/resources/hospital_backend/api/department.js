function getDepartmentList (params) {
  return $axios({
    url: '/department/page',
    method: 'get',
    params
  })
}

// 删除当前列的接口
const deleteDepartment = (id) => {
  return $axios({
    url: '/department',
    method: 'delete',
    params: { id }
  })
}

// 新增科室
function addDepartment (params) {
  return $axios({
    url: '/department',
    method: 'post',
    data: { ...params }
  })
}

// 修改科室
function editDepartment (params) {
  return $axios({
    url: '/department',
    method: 'put',
    data: { ...params }
  })
}

// 根据Id查询科室信息
function queryDepartmentById (id) {
  return $axios({
    url: `/department/${id}`,
    method: 'get'
  })
}