function getDrugList (params) {
  return $axios({
    url: '/drug/page',
    method: 'get',
    params
  })
}

// 修改---启用禁用接口
function enableOrDisableDrug (params) {
  return $axios({
    url: '/drug',
    method: 'put',
    data: { ...params }
  })
}

// 新增药品
function addDrug (params) {
  return $axios({
    url: '/drug',
    method: 'post',
    data: { ...params }
  })
}

// 修改药品
function editDrug (params) {
  return $axios({
    url: '/drug',
    method: 'put',
    data: { ...params }
  })
}

// 根据id查询药品
function queryDrugById (id) {
  return $axios({
    url: `/drug/${id}`,
    method: 'get'
  })
}