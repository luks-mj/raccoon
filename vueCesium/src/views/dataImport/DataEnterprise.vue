<template>
    <div>
        <div class='marketing' >
            <div class='marketing-content'>
                <el-row :gutter='10'>
                    <el-col :span='4'>
                        <el-select v-model='year' placeholder='年度'>
                            <el-option
                                v-for='item in years'
                                :key='item.value'
                                :label='item.label'
                                :value='item.value'>
                            </el-option>
                        </el-select>
                    </el-col>
                    <el-col :span='4'>
                        <el-select v-model='month' placeholder='月份'>
                            <el-option
                                    v-for='item in months'
                                    :key='item.value'
                                    :label='item.label'
                                    :value='item.value'>
                            </el-option>
                        </el-select>
                    </el-col>
                    <el-col :span='4'>
                        <el-input v-model='enterpriseName' placeholder='按照企业名称搜索' />
                    </el-col>
                    <el-col :span='4'>
                        <el-button icon="el-icon-search" type="primary" @click='getTableData'></el-button>
                    </el-col>
                </el-row>
                <el-row :gutter='10'>
                    <el-col :span='24'>
                        <div  style="margin-top: 15px;margin-bottom: 2px;"></div>
                    </el-col>
                </el-row>
                <el-row :gutter='10'>
                    <el-col :span='4'>
                        <el-select v-model='scale' placeholder='***显示所有规模***'>
                            <el-option
                                    v-for='item in scales'
                                    :key='item.value'
                                    :label='item.label'
                                    :value='item.value'>
                            </el-option>
                        </el-select>
                    </el-col>
                    <el-col :span='4'>
                        <el-select v-model='netWork' placeholder='***显示所有网格***'>
                            <el-option
                                    v-for='item in netWorks'
                                    :key='item.value'
                                    :label='item.label'
                                    :value='item.value'>
                            </el-option>
                        </el-select>
                    </el-col>
                    <el-col :span='4'>
                        <el-select v-model='state' placeholder='***显示所有状态***'>
                            <el-option
                                    v-for='item in states'
                                    :key='item.value'
                                    :label='item.label'
                                    :value='item.value'>
                            </el-option>
                        </el-select>
                    </el-col>
                    <el-col :span='4'>
                        <el-select v-model='type' placeholder='***显示所有企业类别***'>
                            <el-option
                                    v-for='item in types'
                                    :key='item.value'
                                    :label='item.label'
                                    :value='item.value'>
                            </el-option>
                        </el-select>
                    </el-col>
                    <el-col :span='8' align='right'>
                        <el-button type='success' icon='plus' @click='handleAddLabel' >新增</el-button>
                        <el-button type='primary' @click='batchRemove'>删除</el-button>
                        <el-button type='success' icon='plus' @click='handleAddLabel' >数据导入</el-button>
                        <el-button type='primary' @click='batchRemove'>数据导出</el-button>
                    </el-col>
                </el-row>
                <el-row :gutter='10' style='margin-top: 1%;'>
                    <el-col :span='24' >
                        <el-table :data='tableData' :border=true :stripe=true v-loading='loading' @selection-change="changeFun" element-loading-text='拼命加载中'>
                            <el-table-column type="selection" align='center' width='70' label='序号'></el-table-column>
                            <el-table-column prop='enterPriseName' align='center' label='企业名称' ></el-table-column>
                            <el-table-column prop='enterPriseCode' align='center' label='统一社会信用代码'></el-table-column>
                            <el-table-column prop='enterPriseScale' align='center' label='企业规模'></el-table-column>
                            <el-table-column prop='registerArea' align='center' label='行业类别'></el-table-column>
                            <el-table-column prop='lesseeArea' align='center' label='状态'></el-table-column>
                            <el-table-column prop='leaseArea' align='center' label='企业类别'></el-table-column>
                            <el-table-column prop='createDate' align='center' label='所属网格'></el-table-column>
                            <el-table-column prop='createDate' align='center' label='详细地址'></el-table-column>
                            <el-table-column prop='createDate' align='center' label='联系人'></el-table-column>
                            <el-table-column prop='createDate' align='center' label='联系电话'></el-table-column>
                        </el-table>
                    </el-col>
                </el-row>
                <div class='pagination'>
                    <el-pagination background  layout='total, sizes,prev, pager, next, jumper'
                        :page-size='pageSize'
                        :page-sizes='[7, 10, 20, 50]'
                        :total='total'
                        @current-change='handleCurrentChange'
                        @size-change='handleSizeChange'>
                    </el-pagination>
                </div>
            </div>
        </div>
        <DataEnterPriseAdd ref="dataEnterPriseAdd" :getTableData="getTableData"></DataEnterPriseAdd>
    </div>
</template>
<style scoped lang='less'>
    .separator-line {
        margin-top: 5px;
        border-top: 1px solid #e4eaec;
    }
.title-font{
    font-size: 16px;
    font-weight: 600;
    padding:1em;
}
.marketing {
    margin-right: 10px;
    .top {
        margin-bottom: 20px;
    }
    .marketing-content {
        background-color: #fff;
        padding: 15px;
        .button_submit {
            float: right;
        }
    }
}
</style>
<style lang='less'>
.marketing {
    .marketing-title {
        font-size: 18px;
        line-height: 36px;
        height: 36px;
    }
}
.el-button--info.is-plain {
    background: #fff;
    border-color: #ccd5db;
    color: #62a8ea;
}
</style>
<script>
import DataEnterPriseAdd from './DataEnterPriseAdd'

export default {
    name: 'DataEnterprise',
    data() {
        return {
            years: [{
                value: '2019',
                label: '2017年度'
            }, {
                value: '2020',
                label: '2020年度'
            }],
            months: [{
                value: '1',
                label: '1月份'
            }, {
                value: '2',
                label: '2月份'
            }],
            scales: [{
                value: '1',
                label: '规上企业'
            }, {
                value: '2',
                label: '规下企业'
            }],
            netWorks: [{
                value: '1',
                label: '第一网格'
            }, {
                value: '2',
                label: '第二网格'
            },{
                value: '3',
                label: '第三网格'
            }, {
                value: '4',
                label: '第四网格'
            }],
            states: [{
                value: '1',
                label: '参与评价（正常企业）'
            }, {
                value: '2',
                label: '参与评价（无评价等级）'
            },{
                value: '3',
                label: '不参与评价（新建投产企业）'
            }, {
                value: '4',
                label: '参与评价（其他）'
            }],
            types: [{
                value: '1',
                label: '年度新增企业'
            }, {
                value: '2',
                label: '年度退出企业'
            },{
                value: '3',
                label: '高新技术企业'
            }, {
                value: '4',
                label: '雏鹰计划企业'
            },{
                value: '5',
                label: '小升规企业'
            }, {
                value: '6',
                label: '瞪羚企业'
            }, {
                value: '7',
                label: '降规企业'
            },{
                value: '8',
                label: '蒲公英计划企业'
            }, {
                value: '9',
                label: '不参与评价（其他）'
            }],
            year:'2020',
            month:'1',
            state:'',
            type:'',
            netWork:'',
            scale:'',
            enterpriseName:'',
            loading: false,
            loaded: false,
            loadErr: false,
            total: 0,
            cur_page: 1,
            pageSize: this.$CST.PAGE_SIZE,
            name:'',
            tableData: [],
            selectRow:[],
            defaultProps: {
            },
        }
    },
    created() {
        this.getTableData();
    },
    components: {
        DataEnterPriseAdd
    },
    methods: {
        handleAddLabel() {
            this.$refs.dataEnterPriseAdd.show = true;
        },
        handleCurrentChange(val) {
            this.cur_page = val;
            this.getTableData();
        },
        handleSizeChange(val) {
            this.cur_page = 1;
            this.pageSize = val;
            this.getTableData();
        },
        getTableData() {
            let params = {
                enterpriseName: this.trim(this.enterpriseName),
                year: this.year,
                month: this.month,
                pageNo: this.cur_page,
                pageSize: this.pageSize,
            };
            this.getRequest(this.$CST.LOAD_LAND_DATA, params).then(resp => {
                this.loading = false;
                if (resp) {
                    console.log(resp.info);
                    this.total = resp.info.total;
                    this.tableData = resp.info.data;
                }else{
                    this.alert('企业数据查询失败：' + reason,'error')
                }
            }).catch((reason) => {
                this.alert('企业数据查询失败：' + reason,'error')
            }).finally(() => {
                this.loaded = true;
                this.loading = false;
            })
        },
        changeFun(row){
            this.selectRow = row;
        },
        batchRemove(){
                this.$confirm('此操作将永久删除选中的企业数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let ids = [];
                    for (let i = 0; i < this.selectRow.length; i++) {
                        ids.push(this.selectRow[i].id)
                    }
                    this.deleteRequest(this.$CST.LOAD_LAND_DEL,{ids:ids}).then(resp => {
                        if (resp) {
                            this.total = resp.info.total;
                            this.tableData = resp.info.data;
                            alert('批量删除成功' );
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
        },
        trim(s) {
            return s.replace(/(^\s*)|(\s*$)/g, '');
        }
    }
}
</script>
