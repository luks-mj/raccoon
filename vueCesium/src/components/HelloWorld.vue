<template>
 <div id="cesiumContainer"></div>
</template>
<script>
export default {
 name: 'cesiumPage',
 data () {
 return {
 }
 },
 mounted(){


 var Cesium = this.Cesium;
 var cesiumContainer = document.getElementById("cesiumContainer");
 /*
 var viewer = new Cesium.Viewer("cesiumContainer",{
 //加载在线谷歌地图
 imageryProvider: new Cesium.UrlTemplateImageryProvider({
 url:"http://www.google.cn/maps/vt?lyrs=s&x={x}&y={y}&z={z}"
 })
 })*/
 var modelUrl='../../static/3dtilesdata/tileset.json';
 var demUrl ='../../static/DEM_midu';
 var wgs84=new Cesium.GeographicTilingScheme({
 ellipsoid:Cesium.Ellipsoid.WGS84
 });
 var url = "http://mt1.google.cn/vt/lyrs=s&hl=zh-CN&x={x}&y={y}&z={z}&s=Gali";
 var GoogleMap=new Cesium.UrlTemplateImageryProvider({url:url});
 var terrainLayer = new Cesium.CesiumTerrainProvider({
 url:demUrl
 });
 var viewer = new Cesium.Viewer('cesiumContainer');
//  viewer.entities.add({
//  name : '天然气井#1',
//  code:1,
//  //position : Cesium.Cartesian3.fromDegrees(104.829249,28.156187),
//  position : Cesium.Cartesian3.fromDegrees(100.395937,25.414216),
//  label : { //文字标签
//  text :'弥渡站',
//  font : '14pt Source Han Sans CN',
//  style : Cesium.LabelStyle.FILL,
//  outlineWidth : 2,
//  style: Cesium.LabelStyle.FILL, //label 样式
//  backgroundColor:new Cesium.Color(0, 0, 0, 0.5), //背景颜色
//  showBackground:true,
//  verticalOrigin : Cesium.VerticalOrigin.BOTTOM, //垂直方向以底部来计算标签的位置
//  pixelOffset : new Cesium.Cartesian2( 105,14) //偏移量
//  },
//  billboard : { //图标
//  image :'../maki、icon2.png',
//  width : 34,
//  height : 34,
//  }
//  });
//  var tileset = new Cesium.Cesium3DTileset({ url:modelUrl});
//  viewer.scene.primitives.add(tileset);
//  viewer.zoomTo(tileset);
 }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.container{
 width:100%;
 height:100%
}
#cesiumContainer{
 width: 100%;
 height: 100%
}
</style>