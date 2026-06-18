function checkPermissionThenLocateAct(dataId,that){
 
  console.log('aaaddd');
  that.setData({testKey:'OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77'});
  that.setData({geocoderUrl:'https://apis.map.qq.com/ws/geocoder/v1/'});
  // 1. 先获取当前权限状态
  wx.getSetting({
    success: (settingRes) => {
      const hasLocationPerm = settingRes.authSetting['scope.userLocation'];
      // 情况1：权限已允许 → 直接定位
      if (hasLocationPerm) {
        doGetLocation(dataId,that);
      }
      // 情况2：权限未授权（首次调用）→ 直接调用getLocation触发弹窗
      else if (hasLocationPerm === undefined) {
        doGetLocation(dataId,that);
      }
      // 情况3：权限已拒绝 → 引导去设置页开启
      else {
        wx.showModal({
          title: '位置权限已关闭',
          content: '你之前拒绝了位置权限，需要手动开启才能定位，是否前往设置页？',
          confirmText: '去设置',
          cancelText: '取消',
          success: (modalRes) => {
            if (modalRes.confirm) {
              // 打开小程序权限设置页（测试号也支持）
              wx.openSetting({
                success: (openRes) => {
                  // 若用户在设置页开启了权限，自动触发定位
                  if (openRes.authSetting['scope.userLocation']) {
                    doGetLocation(dataId,that);
                  } else {
                    that.setData({
                    [dataId+'StatusTips']: '你仍未开启位置权限，无法定位',
                    [dataId+'StatusType']: 'fail'
                    });
                  }
                }
              });
            } else {
              that.setData({
                [dataId+'StatusTips']: '未开启位置权限，定位失败',
                [dataId+'StatusType']: 'fail'
              });
            }
          }
        });
      }
    },
    fail: (err) => {
      console.error('获取权限设置失败：', err);
      that.setData({
        [dataId+'StatusTips']: '权限检测失败，无法定位',
        [dataId+'StatusType']: 'fail'
      });
    }
  });
}

/**
 * 实际执行定位的逻辑（抽离出来，方便复用）
 */
function doGetLocation(dataId,that) {

  that.setData({
   [dataId+'IsLocating']: true,
   [dataId+'StatusTips']: '定位中...',
   [dataId+'StatusType']: 'loading',
   [dataId+'LocationResult']: {}
  });

  wx.getLocation({
    type: 'gcj02',
    altitude: false,
    success: (res) => {
      console.log('定位成功：', res);
      const basicResult = {
        latitude: res.latitude.toFixed(6),
        longitude: res.longitude.toFixed(6),
        accuracy: res.accuracy
      };
      that.setData({
         [dataId+'LocationResult']: basicResult,
         [dataId]:basicResult.longitude+','+basicResult.latitude,
         rangeParams:basicResult.longitude+','+basicResult.latitude,

        });
      getAddressByLatLng(basicResult.latitude, basicResult.longitude,dataId,that);
    },
    fail: (err) => {
      console.error('定位失败：', err);
      that.setData({
        [dataId+'IsLocating']: false,
        [dataId+'StatusTips']: `定位失败：${err.errMsg}`,
        [dataId+'StatusType']: 'fail'
      });
    }
  });
}

// 地址解析（测试接口）
function getAddressByLatLng(lat, lng,dataId,that) {
 
  wx.request({
    url: that.data.geocoderUrl,
    data: {
      location: `${lat},${lng}`,
      key: that.data.testKey,
      get_poi: 0
    },
    success: (res) => {
      that.setData({ [dataId+'IsLocating']: false });
      if (res.data.status === 0) {
        that.setData({
          [dataId+'LocationResult']: { ...that.data[dataId+'LocationResult'], address: res.data.result.address },
          [dataId+'StatusTips']: '定位成功！',
          [dataId+'StatusType']: 'success',
          [dataId]:lng+','+lat,
          rangeParams:lng+','+lat,
        });
      } else {
        that.setData({
          [dataId+'StatusTips']: '定位成功（仅经纬度）：地址解析失败',
          [dataId+'StatusType']: 'success'
        });
      }
    },
    fail: () => {
      that.setData({
        [dataId+'IsLocating']: false,
        [dataId+'StatusTips']: '定位成功（仅经纬度）：地址接口调用失败',
        [dataId+'StatusType']: 'success'
      });
    }
  });
}

module.exports = {
  checkPermissionThenLocateAct,
  doGetLocation,
  getAddressByLatLng
};