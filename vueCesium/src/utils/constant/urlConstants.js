import MAIN_CONSTANT from './mainConstants'
const PRODUCT_URL = 'http://10.128.86.73:28001/nppm-service';
const DEV_URL = 'http://localhost:8001/api/service';

let ENVIRONMENT = ""

const CURRENT_URL = function () {
  if (process.env.NODE_ENV === 'development') {
    ENVIRONMENT = 'development'
    return DEV_URL;
  }else if (process.env.NODE_ENV === 'serv') {
    ENVIRONMENT = 'serv'
    return DEV_URL;
  } else {//生产
    ENVIRONMENT = 'product'
    return PRODUCT_URL;
  }
}();


const URLS = {
    /*------------------亩均评价系统begin---------------------------------*/
    LOAD_LAND_DATA:  "/countryLand/queryAll",
    LOAD_LAND_IMPORT:  "/countryLand/batchImport",
    LOAD_LAND_DEL:  "/countryLand/deleteByCode",
    LOAD_TAX_DATA:  "/countryTaxation/queryAll",
    LOAD_TAX_IMPORT:  "/countryTaxation/batchImport",
    LOAD_TAX_DEL:  "/countryTaxation/deleteByCode",
    LOAD_ENTERPRISE_ADD:  "/enterPrise",
    //文件上传
    API_UPLOAD_FILE: DEV_URL + "/countryLand/batchImport?year={year}&month={month}",
    API_TAX_UPLOAD_FILE: DEV_URL + "/countryTaxation/batchImport?year={year}&month={month}",


    /*------------------亩均评价系统end---------------------------------*/
}


export default URLS;

export { CURRENT_URL, ENVIRONMENT, MAIN_CONSTANT}
