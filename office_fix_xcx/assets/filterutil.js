const locationUtils = require('../assets/location.js');

/**
 * 初始化筛选基础数据（确保所有维度的selectedRs和Filter变量初始化为空字符串）
 * @param {*} that 页面实例
 */
function initFilterAllMsg(that) {
  if (that.data.filterDimensionList.length > 0) {
    // 深拷贝初始化，避免原数据引用污染
    const initFilterDimensionList = JSON.parse(JSON.stringify(that.data.filterDimensionList));
    // 强制初始化所有维度的selectedRs为空字符串（核心：避免初始值异常导致单选）
    initFilterDimensionList.forEach(dimension => {
      dimension.selectedRs = dimension.selectedRs || "";
      // 强制重置所有选项的checked为false
      dimension.list.forEach(item => {
        item.checked = false;
      });
    });

    const firstDimension = initFilterDimensionList[0];
    that.setData({
      activeFilterTab: firstDimension.attrName,
      currentFilterDimension: JSON.parse(JSON.stringify(firstDimension)),
      filterDimensionList: initFilterDimensionList // 覆盖原数据，确保初始值干净
    }, () => {
      // 初始化Filter变量（必须在filterDimensionList更新后执行）
      initFilterResult(that);
    });
  }
}

/**
 * 初始化所有维度的Filter变量（proTypeFilter、priceFilter等）
 * @param {*} that 页面实例
 */
function initFilterResult(that) {
  const filterResult = {};
  // 遍历所有维度，确保每个维度的Filter变量都初始化为空字符串
  that.data.filterDimensionList.forEach(dimension => {
    const fieldName = `${dimension.attrName}Filter`;
    filterResult[fieldName] = dimension.selectedRs || "";
  });
  // 强制setData，确保Filter变量存在且为空
  that.setData({ filterResult }, () => {
    console.log("Filter变量初始化完成：", that.data.filterResult);
  });
}

/**
 * 核心：选择筛选标签（全维度通用多选逻辑，无维度专属限制）
 * @param {string} attrName 维度名（如proType、price）
 * @param {string} itemId 选项ID
 * @param {*} that 页面实例
 */
function selectFilterItemAct(attrName, itemId, that) {
  itemId = itemId+'';
  try {
    console.log(`处理${attrName}维度选中，ID：${itemId}`);

    // 1. 基础参数校验
    if (!attrName || !itemId || !that.data.filterDimensionList) {
      wx.showToast({ title: "参数异常", icon: "none" });
      return;
    }

    // 2. 深度拷贝筛选维度列表（彻底避免引用污染，核心修复点）
    const newFilterDimensionList = JSON.parse(JSON.stringify(that.data.filterDimensionList));
    // 找到当前操作的维度（通用逻辑，无维度专属判断）
    const targetDimension = newFilterDimensionList.find(item => item.attrName === attrName);
    if (!targetDimension) {
      wx.showToast({ title: `未找到${attrName}筛选维度`, icon: "none" });
      return;
    }

    // 3. 处理多选逻辑（全维度通用，无任何单选限制）
    // 初始化选中ID数组：过滤空字符串，确保数组干净
    let selectedIds = targetDimension.selectedRs 
      ? targetDimension.selectedRs.split(",").filter(id => id.trim() && id) 
      : [];
    
    // 切换选中态：存在则移除，不存在则添加
    const idIndex = selectedIds.indexOf((itemId+''));
    if (idIndex > -1) {
      selectedIds.splice(idIndex, 1); // 取消选中
    } else {
      selectedIds.push(itemId); // 新增选中
    }
console.log('bfff');
    // 4. 更新维度的selectedRs（英文逗号分隔，空数组则为""）
    targetDimension.selectedRs = selectedIds.join(",");
    console.log(`${attrName}维度最新selectedRs：`, targetDimension.selectedRs);

    // 5. 同步更新选项的checked状态（全维度通用）
    targetDimension.list.forEach(item => {
      var isChecked = false;
     
      for(var i=0;i<selectedIds.length;i++){
           if((selectedIds[i]+'')==(item.id+'')){
            isChecked=true;
            break;
           }
      }
      item.checked = isChecked;
    });

    // 6. 同步更新对应Filter变量（proTypeFilter/priceFilter等）
    const fieldName = `${attrName}Filter`;
    const newFilterResult = {
      ...(that.data.filterResult || {}), // 继承原有Filter变量
      [fieldName]: targetDimension.selectedRs // 赋值最新选中ID串
    };

    // 7. 找到更新后的当前维度（从新数组中取，确保数据一致）
    const newCurrentDimension = JSON.parse(JSON.stringify(targetDimension));

    // 8. 批量更新页面数据（确保所有关联数据同步）
    that.setData({
      [fieldName]: targetDimension.selectedRs,
      rangeParams: '',
      filterDimensionList: newFilterDimensionList, // 更新总维度列表
      currentFilterDimension: newCurrentDimension, // 更新当前选中维度
      filterResult: newFilterResult // 核心：更新Filter变量
    }, () => {
      // 数据更新完成后，计算总选中数 + 打印验证日志
      calcTotalSelectedCount(that);
      console.log(`✅ ${fieldName}变量值：`, that.data.filterResult[fieldName]);
      console.log(`✅ 所有Filter变量：`, that.data.filterResult);
    });

  } catch (error) {
    console.error("选择筛选项出错：", error);
    wx.showToast({ title: "筛选操作失败", icon: "none" });
  }
}

/**
 * 计算所有维度的总选中项数（通用逻辑）
 * @param {*} that 页面实例
 */
function calcTotalSelectedCount(that) {
  let total = 0;
  if (!that.data.filterDimensionList) {
    that.setData({ totalSelectedCount: 0 });
    return;
  }
  that.data.filterDimensionList.forEach(dimension => {
    if (dimension.selectedRs) {
      const selectedIds = dimension.selectedRs.split(",").filter(id => id);
      total += selectedIds.length;
    }
  });
  that.setData({ totalSelectedCount: total });
}

/**
 * 重置当前选中维度的筛选条件
 * @param {*} that 页面实例
 */
function resetCurrentFilter(that) {
  const attrName = that.data.activeFilterTab;
  if (!attrName || !that.data.filterDimensionList) return;

  const newFilterDimensionList = JSON.parse(JSON.stringify(that.data.filterDimensionList));
  const targetDimension = newFilterDimensionList.find(item => item.attrName === attrName);
  
  // 清空当前维度的选中状态
  targetDimension.selectedRs = "";
  targetDimension.list.forEach(item => {
    item.checked = false;
  });

  // 同步清空对应Filter变量
  const fieldName = `${attrName}Filter`;
  const newFilterResult = {
    ...(that.data.filterResult || {}),
    [fieldName]: ""
  };

  that.setData({
    filterDimensionList: newFilterDimensionList,
    currentFilterDimension: JSON.parse(JSON.stringify(targetDimension)),
    filterResult: newFilterResult
  }, () => {
    calcTotalSelectedCount(that);
  });
}

/**
 * 重置所有筛选条件（全维度）
 * @param {*} that 页面实例
 */
function resetAllFilter(that) {
  if (!that.data.filterDimensionList) return;

  const newFilterDimensionList = JSON.parse(JSON.stringify(that.data.filterDimensionList));
  const newFilterResult = {};

  var fildNames=[];
  // 清空所有维度的选中状态 + 对应Filter变量
  newFilterDimensionList.forEach(dimension => {
    dimension.selectedRs = "";
    dimension.list.forEach(item => {
      item.checked = false;
    });
    console.log('aavvv');
    // 清空对应Filter变量
    const fieldName = `${dimension.attrName}Filter`;
    newFilterResult[fieldName] = "";
    fildNames.push(fieldName);
  });
  for(var i=0;i<fildNames.length;i++){
    that.setData({
      [fildNames[i]]:''
    });
  }

  that.setData({
     rangeParams:'',
    filterDimensionList: newFilterDimensionList,
    activeTab: 'all',
    currentFilterDimension: newFilterDimensionList.find(item => item.attrName === that.data.activeFilterTab),
    filterResult: newFilterResult
  }, () => {
    calcTotalSelectedCount(that);
    that.refreshList && that.refreshList(); // 兼容无refreshList的情况
  });
}

/**
 * 按属性排序（如附近商品）
 * @param {string} rangeName 排序名
 * @param {number} isLocation 是否定位
 * @param {*} that 页面实例
 */
function rangeByAttrAct(rangeName, isLocation, that) {
   // 调用外部函数（参数不变）
   that.setData({
    rangeParams: ''
  });
  const continueProcess = () => { 
    var rangeParams3 = that.data.rangeParams;
    resetAllFilter(that);
    that.setData({
      rangeParams:rangeParams3,
      activeTab: rangeName
    }, () => {
      calcTotalSelectedCount(that);
     that.refreshList();
    });
   };

  if (isLocation === '1') {
   
    locationUtils.checkPermissionThenLocateAct(rangeName, that);

    // 关键：轮询监听——直到定位完成的状态出现，再执行后续逻辑
    const checkLocateDone = () => {
      // 替换成实际的“定位完成标识”（比如that.data.locateSuccess/that.locateDone）
      if (that.data.rangeParams!='') {
        console.log('定位完成:'+that.data.rangeParams);
        continueProcess(); // 执行后续逻辑
        return;
      }
      // 没完成就继续轮询（100ms查一次，可调整）
      setTimeout(checkLocateDone, 100);
    };
    // 启动轮询
    checkLocateDone();
  } else {
    that.setData({rangeParams:`${rangeName} desc`});
    continueProcess();
  }
}

/**
 * 确认筛选（输出最终Filter变量）
 * @param {*} that 页面实例
 */
function confirmFilter(that) {
  that.hideFilterModal && that.hideFilterModal();
  
  // 打印最终筛选结果（验证Filter变量）
  console.log("📌 最终筛选结果（Filter变量）：", that.data.filterResult);
  
  // 整理易读提示
  let tipText = "筛选条件：\n";
  that.data.filterDimensionList.forEach(dimension => {
    if (dimension.selectedRs) {
      const selectedIds = dimension.selectedRs.split(",").filter(id => id);
      const selectedNames = dimension.list
        .filter(item => selectedIds.includes(item.id))
        .map(item => item.name)
        .join("、");
      tipText += `${dimension.zhName}：${selectedNames}\n`;
    }
  });
  console.log("📌 筛选结果（易读）：", tipText);
  
  // 刷新列表
  that.refreshList && that.refreshList();
}

// 导出所有方法
module.exports = {
  initFilterAllMsg,
  selectFilterItemAct,
  resetCurrentFilter,
  resetAllFilter,
  confirmFilter,
  rangeByAttrAct
};