
 
export default {
    //常量
    PAGE_SIZE: 7,
    MARKET_TEMPLATE_TYPE: 10,
    QUESTIONNAIRE_TEMPLATE_TYPE: "7100",


    //模版操作类型
    TEMPLATE_OPERATE_EDIT : '1',//配置中
    TEMPLATE_OPERATE_RUNNING : '2',// 流程中
    TEMPLATE_OPERATE_VIEW  : '3',//视图状态


    TRAFFIC_TEMPLATE_TYPE: 801, //流量标识需求单模板
    LAYER_4_PROTOCOL_TYPE: [{ label: "UDP", value: "50" }, { label: "TCP", value: "51" }],
    LAYER_7_PROTOCOL_TYPE: [
        { label: "HTTP", value: "1" },
        { label: "WAP1.x", value: "2" },
        { label: "WAP2.0", value: "3" },
        { label: "SMTP", value: "4" },
        { label: "POP3", value: "5" },
        { label: "IMAP4", value: "6" },
        { label: "FTP（包含动静态FTP）", value: "7" },
        { label: "RTSP流媒体", value: "8" },
        { label: "MMS彩信", value: "9" },
        { label: "HTTPS", value: "10" },
    ],
    COMMON_REGION_ID_BY_JT :  "100008100000",
    GET_OFFER_TYPE_BY_CATALOGTYPE:function(catalog){
        if(catalog  == '738'){
            return '10,11';
        }else if(catalog == '739'){
            return '12';
        }else if(catalog == '740'){
            return '13';
        }else{
            return ''
        }
    },
    PROVINCES:[
        {areaId:'100008100000',provinceName:"中国",nbr:"8100000"},
        {areaId:'100008110000',provinceName:"北京",nbr:"8110000"},
        {areaId:'100008120000',provinceName:'天津',nbr:"8120000"},
        {areaId:'100008310000',provinceName:'上海',nbr:"8310000"},
        {areaId:'100008500000',provinceName:'重庆',nbr:"8500000"},
        {areaId:'100008130000',provinceName:'河北',nbr:"8130000"},
        {areaId:'100008140000',provinceName:'山西',nbr:"8140000"},
        {areaId:'100008210000',provinceName:'辽宁',nbr:"8210000"},
        {areaId:'100008220000',provinceName:'吉林',nbr:"8220000"},
        {areaId:'100008230000',provinceName:'黑龙江',nbr:"8230000"},
        {areaId:'100008320000',provinceName:'江苏',nbr:"8320000"},
        {areaId:'100008330000',provinceName:'浙江',nbr:"8330000"},
        {areaId:'100008340000',provinceName:'安徽',nbr:"8340000"},
        {areaId:'100008350000',provinceName:'福建',nbr:"8350000"},
        {areaId:'100008360000',provinceName:'江西',nbr:"8360000"},
        {areaId:'100008370000',provinceName:'山东',nbr:"8370000"},
        {areaId:'100008410000',provinceName:'河南',nbr:"8410000"},
        {areaId:'100008420000',provinceName:'湖北',nbr:"8420000"},
        {areaId:'100008430000',provinceName:'湖南',nbr:"8430000"},
        {areaId:'100008440000',provinceName:'广东',nbr:"8440000"},
        {areaId:'100008460000',provinceName:'海南',nbr:"8460000"},
        {areaId:'100008510000',provinceName:'四川',nbr:"8510000"},
        {areaId:'100008520000',provinceName:'贵州',nbr:"8520000"},
        {areaId:'100008530000',provinceName:'云南',nbr:"8530000"},
        {areaId:'100008610000',provinceName:'陕西',nbr:"8610000"},
        {areaId:'100008620000',provinceName:'甘肃',nbr:"8620000"},
        {areaId:'100008630000',provinceName:'青海',nbr:"8630000"},
//        {areaId:'26',provinceName:'台湾省'},
        {areaId:'100008150000',provinceName:'内蒙古',nbr:"8150000"},
        {areaId:'100008450000',provinceName:'广西',nbr:"8450000"},
        {areaId:'100008540000',provinceName:'西藏',nbr:"8540000"},
        {areaId:'100008640000',provinceName:'宁夏',nbr:"8640000"},
        {areaId:'100008650000',provinceName:'新疆',nbr:"8650000"},
//        {areaId:'32',provinceName:'香港'},
//        {areaId:'33',provinceName:'澳门'},
    ],


    PROVINCE:[
        {areaId:'100008110000',provinceName:"北京",nbr:"8110000"},
        {areaId:'100008120000',provinceName:'天津',nbr:"8120000"},
        {areaId:'100008130000',provinceName:'河北',nbr:"8130000"},
        {areaId:'100008140000',provinceName:'山西',nbr:"8140000"},
        {areaId:'100008150000',provinceName:'内蒙古',nbr:"8150000"},
        {areaId:'100008210000',provinceName:'辽宁',nbr:"8210000"},
        {areaId:'100008220000',provinceName:'吉林',nbr:"8220000"},
        {areaId:'100008230000',provinceName:'黑龙江',nbr:"8230000"},
        {areaId:'100008310000',provinceName:'上海',nbr:"8310000"},
        {areaId:'100008320000',provinceName:'江苏',nbr:"8320000"},
        {areaId:'100008330000',provinceName:'浙江',nbr:"8330000"},
        {areaId:'100008340000',provinceName:'安徽',nbr:"8340000"},
        {areaId:'100008350000',provinceName:'福建',nbr:"8350000"},
        {areaId:'100008360000',provinceName:'江西',nbr:"8360000"},
        {areaId:'100008370000',provinceName:'山东',nbr:"8370000"},
        {areaId:'100008410000',provinceName:'河南',nbr:"8410000"},
        {areaId:'100008420000',provinceName:'湖北',nbr:"8420000"},
        {areaId:'100008430000',provinceName:'湖南',nbr:"8430000"},
        {areaId:'100008440000',provinceName:'广东',nbr:"8440000"},
        {areaId:'100008450000',provinceName:'广西',nbr:"8450000"},
        {areaId:'100008460000',provinceName:'海南',nbr:"8460000"},
        {areaId:'100008500000',provinceName:'重庆',nbr:"8500000"},
        {areaId:'100008510000',provinceName:'四川',nbr:"8510000"},
        {areaId:'100008520000',provinceName:'贵州',nbr:"8520000"},
        {areaId:'100008530000',provinceName:'云南',nbr:"8530000"},
        {areaId:'100008540000',provinceName:'西藏',nbr:"8540000"},
        {areaId:'100008610000',provinceName:'陕西',nbr:"8610000"},
        {areaId:'100008620000',provinceName:'甘肃',nbr:"8620000"},
        {areaId:'100008630000',provinceName:'青海',nbr:"8630000"},
        {areaId:'100008640000',provinceName:'宁夏',nbr:"8640000"},
        {areaId:'100008650000',provinceName:'新疆',nbr:"8650000"},
    ]
}