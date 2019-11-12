<template>
  <div class="app-container">
    <div style="display: flex ;flex-direction: row; flex-wrap:wrap; margin-bottom: 1%">
      <el-input v-model="page.name" placeholder="请输入用户名称查询" style="width: 20%;margin-right: 1%" clearable/>
      <!--<el-button type="primary" @click="addShopUser">新增用户</el-button>-->
      <el-button type="primary" @click="searchShopUser">查询</el-button>
    </div>


    <el-dialog :title="addEditName" :visible.sync="dialogFormVisible">
      <el-form :model="shopUser" label-width="100px">
        <el-form-item label="用户名称" >
          <el-input v-model="shopUser.name" placeholder="请输入用户名称" style="width: 90%"></el-input>
        </el-form-item>
        <el-form-item label="用户密码" >
          <el-input v-model="shopUser.password" placeholder="请输入用户密码" style="width: 90%"></el-input>
        </el-form-item>
        <el-form-item label="用户邮箱" >
          <el-input v-model="shopUser.email" placeholder="请输入用户邮箱" style="width: 90%"></el-input>
        </el-form-item>
        <el-form-item label="用户手机" >
          <el-input v-model="shopUser.phone" placeholder="请输入用户手机" style="width: 90%"></el-input>
        </el-form-item>
        <el-form-item label="用户头像" >
          <el-input v-model="shopUser.photo" placeholder="请输入用户头像" style="width: 90%"></el-input>
        </el-form-item>
        <el-form-item label="用户状态" >
          <el-radio v-model="shopUser.status" :label="1">正常</el-radio>
          <el-radio v-model="shopUser.status" :label="0">锁定</el-radio>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="sendAddOrEditShopUser">确 定</el-button>
      </div>
    </el-dialog>

    <el-table
      v-loading="listLoading"
      :data="shopUsers.records"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="ID" width="95">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="用户邮箱" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          {{ scope.row.email }}
        </template>
      </el-table-column>
      <el-table-column label="用户账号" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户密码" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.password }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户手机" align="center">
        <template slot-scope="scope">
          {{ scope.row.phone }}
        </template>
      </el-table-column>
      <el-table-column label="用户头像" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.photo" style="height:90px">
        </template>
      </el-table-column>
      <el-table-column label="用户状态"align="center" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <el-tag type="success" v-if="scope.row.status === 1">正常</el-tag>
          <el-tag type="danger" v-else>锁定</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="130" align="center">
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column>
      <!--<el-table-column class-name="status-col" label="操作" width="220" align="center">-->
        <!--<template slot-scope="scope">-->
          <!--<el-button type="primary" @click="editShopUser(scope.row)">编辑</el-button>-->
        <!--</template>-->
      <!--</el-table-column>-->
    </el-table>
    <el-pagination
      id="page-down"
      style="margin-top: 1%;position: fixed;bottom: 0;height: auto; width: 100%; background-color: #FFF;z-index: 999;"
      :current-page="page.current"
      :page-sizes="[10, 50, 100, 200]"
      :page-size="page.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="shopUsers.total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    ></el-pagination>
  </div>
</template>

<script>
  import {shopUserPage,addShopUser,editShopUser} from '@/api/shop-user'
  import {deepClone} from '../../utils/util'


  export default {
    data() {
      return {
        list: null,
        listLoading: true,
        page: {
          current: 1,
          size: 10
        },
        shopUsers: {},
        addEditName:'新增用户',
        dialogFormVisible:false,
        shopUser:{}
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      fetchData() {
        this.listLoading = true
        shopUserPage(this.page).then(response => {
          console.log(response)
          this.shopUsers = response.data
          this.listLoading = false
        })
      },
      addShopUser(){
        this.shopUser = {}
        this.addEditName = '新增用户'
        this.dialogFormVisible = true
      },
      editShopUser(shopUser){
        this.addEditName='编辑用户'
        this.shopUser = deepClone(shopUser)
        this.dialogFormVisible = true
      },
      sendAddOrEditShopUser(){
        let loading = this.loading();
        console.log(this.shopUser)
        console.log(this.shopUser.id)
        if (this.shopUser.id !== undefined){
          editShopUser(this.shopUser).then(res=>{
            this.dialogFormVisible = !this.dialogFormVisible
            loading.close()
            this.fetchData()
            console.log(res)
          }).catch(err=>{
            console.log(err)
            loading.close()
          })
          return
        }
        addShopUser(this.shopUser).then(res=>{
          this.dialogFormVisible = !this.dialogFormVisible
          loading.close()
          this.fetchData()
          console.log(res)
        }).catch(err=>{
          console.log(err)
          loading.close()
        })
      },
      searchShopUser(){
        this.fetchData();
      },
      handleSizeChange(val) {
        this.page.size = val
        this.fetchData()
      },
      handleCurrentChange(val) {
        this.page.current = val
        this.fetchData()
      },
      loading(){
        const loading = this.$loading({
          lock: true,
          text: 'Loading',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        return loading
      }
    }
  }
</script>
