<template>
  <div class="app-container">
    <div style="display: flex ;flex-direction: row; flex-wrap:wrap; margin-bottom: 1%">
      <el-input v-model="page.name" placeholder="请输入商品名称查询" style="width: 20%;margin-right: 1%" clearable/>
      <el-button type="primary" @click="addShopInfo">新增商品</el-button>
      <el-button type="primary" @click="searchShopInfo">查询</el-button>
    </div>


    <el-dialog :title="addEditName" :visible.sync="dialogFormVisible">
      <el-form :model="shopInfo" label-width="100px">
        <el-form-item label="商品名称" >
          <el-input v-model="shopInfo.name" placeholder="请输入商品名称" style="width: 90%"></el-input>
        </el-form-item>
        <el-form-item label="商品品牌" >
          <el-input v-model="shopInfo.brand" placeholder="请输入商品品牌" style="width: 90%"></el-input>
        </el-form-item>
        <el-form-item label="商品价格" >
          <el-input v-model="shopInfo.price" placeholder="请输入商品价格" style="width: 90%"></el-input>
        </el-form-item>
        <el-form-item label="商品折扣价" >
          <el-input v-model="shopInfo.discountPrice" placeholder="请输入商品折扣价" style="width: 90%"></el-input>
        </el-form-item>
        <el-form-item label="商品大图" >
          <el-input v-model="shopInfo.titlePhoto" placeholder="请输入titlePhoto" style="width: 90%"></el-input>
        </el-form-item>
        <el-form-item label="商品详情图" >
          <el-input type="textarea" v-model="shopInfo.detailPhoto" placeholder="请输入商品详情图，最多六张，用英文逗号隔开" style="width: 90%"></el-input>
        </el-form-item>
        <el-form-item label="商品说明" >
          <el-input type="textarea" v-model="shopInfo.description" placeholder="请输入商品说明" style="width: 90%"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="sendAddOrEditShopInfo">确 定</el-button>
      </div>
    </el-dialog>
    <el-table
      v-loading="listLoading"
      :data="shopInfos.records"
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
      <el-table-column label="商品名称" width="150" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="商品品牌" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.brand }}</span>
        </template>
      </el-table-column>
      <el-table-column label="商品价格" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.price }}
        </template>
      </el-table-column>
      <el-table-column label="商品折扣价" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.discountPrice }}
        </template>
      </el-table-column>
      <el-table-column label="商品大图" width="150" align="center" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <img
            style="width: 90px; height: 100px"
            :src="scope.row.titlePhoto"
            >
        </template>
      </el-table-column>
      <el-table-column label="商品详情图" width="150" align="center">
        <template slot-scope="scope">
          <div  style="display: flex ;flex-direction: row;flex-flow: wrap">
            <div style="align-self: auto"
                 v-if="scope.row.detailPhoto !== undefined && scope.row.detailPhoto != null"
                 v-for="item in scope.row.detailPhoto.split(',')">
              <img :src="item" style="width: 50px;height: 50px;">
            </div>
          </div>
          <!--{{ scope.row.detailPhoto }}-->
        </template>
      </el-table-column>
      <el-table-column label="商品说明" align="center" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          {{ scope.row.description }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="130" align="center">
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="操作" width="220" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="editShopInfo(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      id="page-down"
      style="margin-top: 1%;position: fixed;bottom: 0;height: auto; width: 100%; background-color: #FFF;z-index: 999;"
      :current-page="page.current"
      :page-sizes="[10, 50, 100, 200]"
      :page-size="page.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="shopInfos.total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    ></el-pagination>
  </div>
</template>

<script>
  import {shopInfoPage,addShopInfo,editShopInfo} from '@/api/shop-info'
  import {deepClone} from '../../utils/util'


  export default {
    filters: {
      statusFilter(status) {
        const statusMap = {
          published: 'success',
          draft: 'gray',
          deleted: 'danger'
        }
        return statusMap[status]
      }
    },
    data() {
      return {
        list: null,
        listLoading: true,
        page: {
          current: 1,
          size: 10
        },
        shopInfos: {},
        addEditName:'新增商品',
        dialogFormVisible:false,
        shopInfo:{}
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      fetchData() {
        this.listLoading = true
        shopInfoPage(this.page).then(response => {
          this.shopInfos = response.data
          this.listLoading = false
        })
      },
      addShopInfo(){
        this.shopInfo = {}
        this.addEditName = '新增商品'
        this.dialogFormVisible = true
      },
      editShopInfo(shopInfo){
        this.addEditName='编辑商品'
        this.shopInfo = deepClone(shopInfo)
        this.dialogFormVisible = true
      },
      sendAddOrEditShopInfo(){
        let loading = this.loading();
        if (this.shopInfo.id !== undefined){
          editShopInfo(this.shopInfo).then(res=>{
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
        addShopInfo(this.shopInfo).then(res=>{
          this.dialogFormVisible = !this.dialogFormVisible
          loading.close()
          this.fetchData()
          console.log(res)
        }).catch(err=>{
          console.log(err)
          loading.close()
        })
      },
      searchShopInfo(){
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
