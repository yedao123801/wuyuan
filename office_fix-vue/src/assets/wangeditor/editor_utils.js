import $ from 'jquery';
import WangEditor from 'wangeditor';
var wangList=[];
export function initAllEditor(requestUri, domId) {
    // 1. 清除全局实例记录（暴力清空相关实例）
    for (let i = wangList.length - 1; i >= 0; i--) {
        if (wangList[i].id === domId) {
            // 强制销毁并移除
            try {
                wangList[i].edit.destroy();
            } catch (e) {
                console.log('旧实例已自动销毁，忽略错误');
            }
            wangList.splice(i, 1);
        }
    }
    // 2. 找到原始容器并彻底删除重建（核心中的核心）
    const oldNode = document.querySelector(`.editorDiv[data-id="${domId}"]`);
    if (!oldNode) return;
    // 记录容器的父节点和位置
    const parentNode = oldNode.parentNode;
    const nextSibling = oldNode.nextSibling; // 用于保持位置
    // 彻底删除旧节点（连父级引用都清除）
    parentNode.removeChild(oldNode);
    // 3. 创建全新的容器节点（100%干净）
    const newNode = document.createElement('div');
    newNode.className = 'editorDiv'; // 保持原类名
    newNode.setAttribute('data-id', domId); // 保持原ID
    newNode.style.width = '100%'; // 保持原样式
    newNode.style.minHeight = '200px';
    // 插入到原来的位置
    if (nextSibling) {
        parentNode.insertBefore(newNode, nextSibling);
    } else {
        parentNode.appendChild(newNode);
    }
    // 4. 用全新节点创建编辑器（此时绝对无残留）
    const editor = new WangEditor(newNode);
    // 原有配置不变
    editor.config.uploadImgServer = requestUri + '/imgUpload/imgUploadForWangEditor';
    editor.config.uploadFileName = 'files';
    editor.config.uploadImgMaxSize = 3 * 1024 * 1024;
    editor.config.uploadImgMaxLength = 5;
    editor.config.uploadImgTimeout = 30000;
    editor.config.zIndex = 500;
    editor.config.menus = ['head', 'bold', 'italic', 'underline', 'strikeThrough', 'foreColor', 'backColor', 'link', 'list', 'justify', 'quote', 'table', 'code', 'undo', 'redo', "image"];
    // 强制创建（此时节点绝对干净，不可能报错）
    editor.create();
    // 5. 存入新实例
    wangList.push({ id: domId, edit: editor });
}
export function getEditVal(domId){
    for(var i=0;i<wangList.length;i++){
        if(wangList[i].id==domId){
            var editor = wangList[i].edit;
            return editor.txt.html();
        }
    }
    return "";
}
export function setEditorHtml(domId,htmlCode){
    for(var i=0;i<wangList.length;i++){
        if(wangList[i].id==domId){
            var editor = wangList[i].edit;
            editor.txt.clear();
            editor.txt.append(htmlCode);
        }
    }
}
