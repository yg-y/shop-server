<template>
  <div class="app-container">
    <div style="display: flex ;flex-direction: row; flex-wrap:wrap; margin-bottom: 1%">
      <el-input v-model="page.name" placeholder="请输入订单编号" style="width: 20%;margin-right: 1%" clearable/>
      <el-button type="primary" @click="searchSopOrderInfo">查询</el-button>
    </div>

    <el-dialog :title="addEditName" :visible.sync="dialogFormVisible">
      <el-form :model="shopOrderInfo" label-width="100px">
        <el-form-item label="商品名称" >
          <el-input v-model="shopOrderInfo.name" placeholder="请输入商品名称" style="width: 90%"></el-input>
        </el-form-item>
        <el-form-item label="商品品牌" >
          <el-input v-model="shopOrderInfo.brand" placeholder="请输入商品品牌" style="width: 90%"></el-input>
        </el-form-item>
        <el-form-item label="商品价格" >
          <el-input v-model="shopOrderInfo.price" placeholder="请输入商品价格" style="width: 90%"></el-input>
        </el-form-item>
        <el-form-item label="商品折扣价" >
          <el-input v-model="shopOrderInfo.discountPrice" placeholder="请输入商品折扣价" style="width: 90%"></el-input>
        </el-form-item>
        <el-form-item label="商品大图" >
          <el-input v-model="shopOrderInfo.titlePhoto" placeholder="请输入titlePhoto" style="width: 90%"></el-input>
        </el-form-item>
        <el-form-item label="商品详情图" >
          <el-input type="textarea" v-model="shopOrderInfo.detailPhoto" placeholder="请输入商品详情图，最多六张，用英文逗号隔开" style="width: 90%"></el-input>
        </el-form-item>
        <el-form-item label="商品说明" >
          <el-input type="textarea" v-model="shopOrderInfo.description" placeholder="请输入商品说明" style="width: 90%"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="sendAddOrEditSopOrderInfo">确 定</el-button>
      </div>
    </el-dialog>
    <el-table
      v-loading="listLoading"
      :data="shopOrderInfos.records"
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
      <el-table-column label="商品名称" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          {{ scope.row.shopName }}
        </template>
      </el-table-column>
      <el-table-column label="购买用户" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          {{ scope.row.buyerUser }}
        </template>
      </el-table-column>
      <el-table-column label="订单编号" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.orderNumber }}</span>
        </template>
      </el-table-column>
      <el-table-column label="付款金额" align="center">
        <template slot-scope="scope">
          {{ scope.row.paymentPrice }}
        </template>
      </el-table-column>
      <el-table-column label="订单状态" align="center">
        <template slot-scope="scope">
          <!--订单状态【0 待付款 1 已付款 2 退货中 3 已退货】-->
          <el-tag v-if="scope.row.status === 0">代付款</el-tag>
          <el-tag type="success" v-if="scope.row.status === 1">已付款</el-tag>
          <el-tag type="info" v-if="scope.row.status === 2">退货中</el-tag>
          <el-tag type="danger" v-if="scope.row.status === 3">已退货</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="订单数" align="center" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          {{ scope.row.number }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="130" align="center">
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column>
      <!--<el-table-column class-name="status-col" label="操作" width="220" align="center">-->
        <!--<template slot-scope="scope">-->
          <!--<el-button type="primary" @click="editSopOrderInfo(scope.row)">编辑</el-button>-->
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
      :total="shopOrderInfos.total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    ></el-pagination>
  </div>
</template>

<script>
  import {shopOrderInfoPage,editSopOrderInfo} from '@/api/shop-order-info'
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
        shopOrderInfos: {},
        addEditName:'编辑订单',
        dialogFormVisible:false,
        shopOrderInfo:{}
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      fetchData() {
        this.listLoading = true
        shopOrderInfoPage(this.page).then(response => {
          this.shopOrderInfos = response.data
          this.listLoading = false
        })
      },
      editSopOrderInfo(shopOrderInfo){
        this.addEditName='编辑订单'
        this.shopOrderInfo = deepClone(shopOrderInfo)
        this.dialogFormVisible = true
      },
      sendAddOrEditSopOrderInfo(){
        let loading = this.loading();
        if (this.shopOrderInfo.id !== undefined){
          editSopOrderInfo(this.shopOrderInfo).then(res=>{
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
      },
      searchSopOrderInfo(){
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
