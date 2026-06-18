Component({
  data: {
    selected: 0,
    color: "#7A7E83",
    selectedColor: "#e80c35",
    list: [
	{
"pagePath": "/pages/index/index",
"iconPath": "/assets/icon/index.png",
"selectedIconPath": "/assets/icon/index-active.png",
"text": "首页"
},
{
"pagePath": "/pages/personal/personal",
"iconPath": "/assets/icon/personal.png",
"selectedIconPath": "/assets/icon/personal-active.png",
"text": "个人中心"
}
  ]
  },
  attached() {
  },
  methods: {
    switchTab(e) {
      const data = e.currentTarget.dataset
      const url = data.path
	    this.setData({
        selected: data.index
      })
      if(data.index>=5){
        wx.navigateTo({
          url: url,
        })
      }else{
        wx.switchTab({ url: url});
      }
    }
  }
})
