App({
onLaunch() {
 wx.setStorageSync('baseHostPort', 'http://localhost:8080/office_fix');
},
  /**
   * 极简通用分享函数（含自动截图）
   * @param {Object} page 当前页面实例（传 this）
   * @param {String} customTitle 自定义标题（可选）
   * @returns {Promise} 分享配置（异步，含截图路径）
   */
  getShareData(page, customTitle = '') {
    return new Promise((resolve) => {
      // 1. 第一步：自动截取当前页面（核心截图逻辑）
      const query = wx.createSelectorQuery().in(page);
      query.select('#pages').boundingClientRect(); // 截取整个页面（#pages 是小程序根节点）
      query.selectViewport().scrollOffset();
      query.exec((res) => {
        // 创建离屏 canvas（不显示在页面，仅用于截图）
        const systemInfo = wx.getSystemInfoSync();
        const pixelRatio = systemInfo.pixelRatio; // 设备像素比，保证截图清晰
        const canvas = wx.createOffscreenCanvas({
          type: '2d',
          width: systemInfo.windowWidth * pixelRatio,
          height: systemInfo.windowHeight * pixelRatio
        });
        const ctx = canvas.getContext('2d');
        // 截图当前页面到 canvas
        wx.canvasToTempFilePath({
          x: 0,
          y: 0,
          width: systemInfo.windowWidth,
          height: systemInfo.windowHeight,
          destWidth: systemInfo.windowWidth * pixelRatio,
          destHeight: systemInfo.windowHeight * pixelRatio,
          canvas: canvas,
          success: (canvasRes) => {
            // 2. 第二步：自动获取页面路径+参数（原有逻辑不变）
            const pages = getCurrentPages();
            const curPage = pages[pages.length - 1];
            const path = `/#####{curPage.route}`;
            const options = curPage.options || {};
            let queryStr = '';
            Object.keys(options).forEach(key => {
              queryStr += `#####{queryStr ? '&' : ''}#####{key}=#####{encodeURIComponent(options[key])}`;
            });
            const fullPath = queryStr ? `#####{path}?#####{queryStr}` : path;
            // 3. 第三步：返回分享配置（含截图作为分享图片）
            resolve({
              title: customTitle || `分享#####{curPage.route.split('/').pop()}页面`,
              path: fullPath,
              imageUrl: canvasRes.tempFilePath, // 关键：用页面截图作为分享图片
              success: () => wx.showToast({ title: '分享成功', icon: 'success' }),
              fail: () => wx.showToast({ title: '分享失败', icon: 'none' })
            });
          },
          // 截图失败：用默认逻辑（无截图）
          fail: () => {
            const pages = getCurrentPages();
            const curPage = pages[pages.length - 1];
            const path = `/#####{curPage.route}`;
            const options = curPage.options || {};
            let queryStr = '';
            Object.keys(options).forEach(key => {
              queryStr += `#####{queryStr ? '&' : ''}#####{key}=#####{encodeURIComponent(options[key])}`;
            });
            const fullPath = queryStr ? `#####{path}?#####{queryStr}` : path;
            resolve({
              title: customTitle || `分享#####{curPage.route.split('/').pop()}页面`,
              path: fullPath,
              success: () => wx.showToast({ title: '分享成功', icon: 'success' }),
              fail: () => wx.showToast({ title: '分享失败', icon: 'none' })
            });
          }
        }, page);
      });
    });
  },
    globalData:{
    }
})
