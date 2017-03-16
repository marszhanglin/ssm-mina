//依赖于bootstrap.js
//request
(function () {
    //获取URL参数
    function request(paras,urlparam) {
        var url = urlparam?urlparam:location.href;
        var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
        var paraObj = {}
        for (i = 0; j = paraString[i]; i++) {
            paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=") + 1, j.length);
        }
        var returnValue = paraObj[paras.toLowerCase()];
        if (typeof (returnValue) == "undefined") {
            return "";
        } else {
            return returnValue;
        }
    }
    window.request = request;
})();
//codeLock
(function () {
    function codeLock(op) {
        this.wrapId = op.wrapId;

        this.reSendClickHandel = op.reSendClickHandel;
        this.sendcls = op.sendcls || "sendcls"
        this.overtime = op.overtime;
        if (this.overtime === undefined) this.overtime = 60;
    }
    codeLock.prototype.pause = function () {
        var me = this;
        var overtime = me.overtime;
        var tp = "重新获取(xs)";
        var txt = tp.replace("x", overtime);
        var $send = $("#" + me.wrapId).find("." + me.sendcls)
        $send.html(txt).addClass("disabled").unbind("click");

        var h = setInterval(function () {
            overtime = overtime - 1;
            if (overtime <= 0) {
                clearInterval(h);
                txt = tp.replace("(xs)", "");
                $send.html(txt).removeClass("disabled").click(function () {
                    me.reSendClickHandel();
                })
            } else {
                txt = tp.replace("x", overtime);
                $send.html(txt).addClass("disabled");
            }
        }, 1000);

    }
    window.codeLock = codeLock;
})();
//cAlert
(function () {
    //id=custom_alert被占用
    function cutomAlert(op) {
        this.msg = op.msg;
        this.alertType = op.alertType;//w:warnning,e:error,s:sucess

        this.isOnDialog = op.isOnDialog;
        this.closeCallBack = op.closeCallBack;
        this.yesFn = op.yesFn;
        this.noFn = op.noFn;//noFn依赖于yesFn
        this.tp = '<div class="modal warning" id="custom_alert" tabindex="-1" role="dialog"><div class="modal-dialog" role="document"><div class="modal-content"><div class="close"><span>×</span></div><div class="modal-body"><div class="text"></div></div><div class="modal-footer"><button type="button" class="btn btn-primary yes">确定</button><button type="button" class="btn btn-primary no">取消</button></div></div></div></div>';
    }
    cutomAlert.prototype.open = function () {
        var me = this;
        var $wrap = $("#custom_alert");
        if ($wrap.length <= 0) {
            $("body").append(me.tp);
        }
        $wrap = $("#custom_alert");
        if (me.alertType!='w') {
            $wrap.removeClass("warning");
        } else {
            $wrap.addClass("warning");
        }

        var isOnDialog=false;
        if (me.isOnDialog === undefined) {
            //自动判断当前是否是对话框
            $(".modal").each(function () {
                if (this.id != "custom_alert") {
                    if (!$(this).is(":hidden")) {
                        isOnDialog = true;
                        return false;
                    }
                }
            });
        }
        if (isOnDialog) {
            $wrap.css({ "background-color": "rgba(0,0,0,0.9)" });
        } else {
            $wrap.attr("style", "");
        }

        $wrap.find(".modal-body>.text").html(me.msg);
        $wrap.modal();

        if (!me.yesFn) {//没有否（即点任意都可关闭）
            //回调(点击内容也关闭)
            $wrap.unbind("click").click(function () {
                if (me.closeCallBack) me.closeCallBack();
                $wrap.modal('hide');
            });
        } else {
            $wrap.find(".modal-footer>.btn.no").show();
            //no
            $wrap.unbind("click").click(function (e) {
                var $target = $(e.target);
                if ($target.attr("id") == "custom_alert" || $target.hasClass("close") || $target.parent().hasClass("close")) {
                    if (me.noFn) me.noFn();
                    $wrap.modal('hide');
                }
            });
            $wrap.find(".modal-footer>.btn.no").unbind("click").click(function () {
                me.noFn();
                $wrap.modal('hide');
            });
            //yes
            $wrap.find(".modal-footer>.btn.yes").unbind("click").click(function () {
                me.yesFn();
                $wrap.modal('hide');
            });
        }
    }
    
    function _iniOp(msg) {
        var msg = msg;
        var op = {
            msg: msg
        };
        var isOnDialog = false;
        var closeCallBack = null;
        var alertType = 'w';
        if ("object" == typeof msg) {
            var obj = msg;
            op.msg = obj.msg;
            op.isOnDialog = obj.isOnDialog;
            op.closeCallBack = obj.closeCallBack;
            op.alertType = obj.alertType;
            op.yesFn = obj.yesFn;
            op.noFn = obj.noFn;
        }
        return op;
    }
    function cAlert(msg) {
        cAlertSucess(msg);
    }
    function cAlertSucess(msg) {
        var op = _iniOp(msg);
        op.alertType = 's';
        var at = new cutomAlert(op);
        at.open();
    }
    function cAlertError(msg) {
        cAlertWarnning(msg);
    }
    function cAlertWarnning(msg) {
        var op = _iniOp(msg);
        op.alertType = 'w';
        var at = new cutomAlert(op);
        at.open();
    }
    var art = {
        alertSucess: cAlertSucess,
        alertError: cAlertError,
        alertWarnning: cAlertWarnning
    }
    window.art = art;
})();
//Request.fetchData
(function () {
    /**
 *  @脚本功能： 统一ajax请求方法
 *  调用方式:
 *    Request.fetchData({
 *           // 遮罩层对象
 *           target: '.yj_contain',
 *           // 请求地址
 *           url: '/api/port.ashx?jn=appparam',
 *           // 提交方式 POST/GET
 *           type: 'Post',
 *           // 附加参数
 *           data: "name=ie",
 *           // 超时设置
 *           timeout: 20 * 1000,
 *           contentType : 'application/x-www-form-urlencoded;charset=utf-8'(默认类型，对应json或key:value),//'application/json; charset=utf-8'(对应json字符串),
 *           // 成功回调方法(Code==0)
 *           success: function (data) {     
 *           },
 *           // 失败回调方法(Code!=0)
 *           error: function (data) {  
 *           }
 *  });
 *  接口的返回数据格式
 *    {
 *          "Code" : 0
 *          "Msg": "",
 *          "Data" : {}
 *    }
 */
    var Request = {};
    //settings: target,success,error; 其他与$ajax的参数一致; success,error是指Code=0和!=0
    //isProgress默认为false是否使用,afterSysErrorHandle操作异常附加处理
    Request.fetchData = function (settings) {
        var op = settings;
        if (!op.isProgress) op.isProgress = false;
        if (op.isProgress) NProgress.start();
        var ml = null;
        if (op.target) {
            ml = new maskLayer({ target: op.target });
            ml.show()
        }

        //默认为JSON
        if (!op.dataType) op.dataType = "JSON";
        //默认为post
        if (!op.type) op.type = "POST";
        //Token
        op.headers = {
            __RequestVerificationToken: $('div#bnet_token_box input[name=__RequestVerificationToken]').val() || ''
        };

        //customSuccess替换success
        var customSuccess = op.success;
        var customError = op.error ? op.error : function (json) {
            art.alertError(json.Msg)//toastr.error(json.Msg);
        };
        op.success = function (r) {
            // 对返回的数据JSON化处理
            if (op.dataType.toLowerCase() == "json") {
                if (typeof r === "string") {
                    try {
                        r = JSON.parse(r);
                    } catch (s) {
                        r = $.parseJSON(r);
                    }
                }
                // 判断结果码
                if (r.Code != 0) {
                    if (ml) ml.hide();
                    customError(r);
                } else {
                    if (ml) ml.hide();
                    customSuccess(r);
                }
            } else {
                if (ml) ml.hide();
                customSuccess(r);
            }
            if (op.isProgress) NProgress.done();
        }
        op.error = function (w, s) {
            if (ml) ml.hide();
            if (w.status === 0) {
                art.alertError("超时"); //toastr.error("超时"); 
            } else {
                art.alertError("操作异常");// toastr.error("操作异常"); 
            }
            if (op.isProgress) NProgress.done();
            if (op.afterSysErrorHandle) op.afterSysErrorHandle();
        }
        $.ajax(op);
    }

    //maskLayer 遮罩层(Start)
    function maskLayer(op) {
        this.target = op.target;
        this.$mask = null;

        this.ini();
    }
    maskLayer.prototype.ini = function () {
        var me = this;
        var obj = $(me.target).css({
            'position': 'relative'
        });
        //.attr({ 'id': 'mask-id-' + e.target })
        me.$mask = $('<div/>').hide().addClass('bnet-mask').appendTo(obj).css(me.getTargetPosition(obj));
    }
    maskLayer.prototype.getTargetPosition = function ($obj) {
        // 获取目标的位置
        return {
            'position': 'absolute',
            'height': $obj.outerHeight(),
            'width': $obj.outerWidth(),
            'top': 0,
            'left': 0
            /*
            'top': e.offset().top,
            'left': e.offset().left
            */
        };
    }
    maskLayer.prototype.show = function () {
        // 显示遮罩层
        this.$mask.show();
    }
    maskLayer.prototype.hide = function () {
        // 移除遮罩层
        this.$mask.remove();
    }
    //maskLayer 遮罩层(End)

    window.Request = Request;
})();
//validate表单验证
//(function () {
//    function rule(op) {
//        this.dataVal = op.dataVal;
//        this.dataVal = op.dataVal;
//    }
//    function validate(op) {

//    }
//    validate.prototype.checkInput = function (el, rule) {

//    }
//    validate.prototype.checkForm = function (el) {

//    }
//    validate.prototype.check = function (el) {

//    }
//})();
//分页
//pageSize由后台决定
(function () {
    function infiniteScroll(op) {
        this.wrapId = op.wrapId;
        this.url = op.url;
        this.loadFn = op.loadFn;

        this.isByScroll = op.isByScroll;//是否是向上滑动分页，如果为否，则点击更多加载下一页
        this.data = op.data || {};//可以是obj、function
        this.moreCls = op.moreCls || "page_more";
        this.iniText = op.iniText || "更多";
        this.finishText = op.finishText || "已全部加载";
        this.loadingText = op.loadingText || "正在加载中...";

        this._pageIndex = 1;//向下翻页从第1页开始（第0页已经有了）
        this._moreText = "";
        this._isFinish = false;
        this.CAN_CHANGE_PAGE = true;

        this.ini();
    }
    infiniteScroll.prototype.ini = function () {
        var me = this;
        //新增more
        me._addMoreEl();

        //more注册事件
        var $more = $("#" + me.wrapId + " ." + me.moreCls);
        me._moreText = $more.text();
        function moreclick() {
            if (!me.CAN_CHANGE_PAGE) return;
            me.CAN_CHANGE_PAGE = false;
            setTimeout(requst, 300);
            function requst() {
                var data = me.data;
                if (typeof data === "function") {
                    data = data();
                }
                data.PageIndex = me._pageIndex;
                Request.fetchData({
                    url: me.url,
                    data: data,
                    beforeSend: function () {
                        $more.text(me.loadingText).unbind("click").show();
                    },
                    complete: function () {
                        if (!me._isFinish) {//对应的是：beforeSend
                            $more.text(me._moreText).click(function () {
                                moreclick();
                            }).show();
                            if (me.isByScroll) {
                                $more.hide();
                            }
                        }
                        me.CAN_CHANGE_PAGE = true;
                    },
                    success: function (json) {
                        me._isFinish = false;
                        me.loadFn(json);
                        var recordCount = json.Pager.RecordCount;
                        var pageIndex = me._pageIndex;//json.Pager.PageIndex;
                        var pageSize = json.Pager.PageSize;
                        //me._pageIndex = recordCount / pageSize;
                        if ((pageIndex + 1) * pageSize >= recordCount) {//已全部加载
                            me._pageIndex = Math.ceil(recordCount / pageSize);
                            me._isFinish = true;
                            if (me.isByScroll) {
                                $more.show();
                                //document.getElementById(me.wrapId).removeEventListener('touchstart', touchSatrt);
                                //document.getElementById(me.wrapId).removeEventListener('touchmove', touchMove);
                            }
                            $more.text(me.finishText).show();
                            window.setTimeout(function () {
                                $more.fadeOut(800);
                            }, 800);
                        } else {//加载之后拼接
                            me._pageIndex++;
                        }
                    }
                });
            }
           
        }
        if (me.isByScroll === true) {
            document.getElementById(me.wrapId).addEventListener('touchstart', touchSatrt, false);
            document.getElementById(me.wrapId).addEventListener('touchmove', touchMove, false);
            //document.getElementById(me.wrapId).addEventListener('touchend', touchEnd, false);
        }
        $more.click(function () {
            moreclick();
        });

        //滑动
        var startY = 0, y = 0;
        function touchSatrt(e) {//触摸
            y = 0;
            var touch = e.touches[0];
            startY = touch.pageY; //刚触摸时的坐标
        }
        function touchMove(e) {//滑动
            var touch = e.touches[0];
            y = touch.pageY - startY;//滑动的距离

            touchEnd(e);
        }
        function touchEnd(e) {//手指离开屏幕
            if (!me.CAN_CHANGE_PAGE) return;
            if (y < -8) {//向上滑动了8px
                var top = $("body").get(0).scrollTop;
                var sceenHeight = $(window).outerHeight();
                var totalHeight = $("#" + me.wrapId).height();
                if (top + sceenHeight + 10 >= totalHeight) { //滑到底,10额外的
                    moreclick();
                }
            }
        }
    }
    infiniteScroll.prototype._addMoreEl = function () {
        var me = this;
        var $wrap = $("#" + me.wrapId);
        if ($wrap.find("." + me.moreCls).length <= 0) {
            var html = "<div class='" + me.moreCls + "'>" + me.iniText + "<div>";
            $wrap.append(html);
            if (me.isByScroll) {
                $("#" + me.wrapId + " ." + me.moreCls).hide().addClass("byscroll");
            }
        }
    }
    infiniteScroll.prototype.setPageIndex = function (index) {
        if (!index) index = 1;
        this._pageIndex = index;
    }
    window.infiniteScroll = infiniteScroll;
})();

//格式转换
(function () {
    var formate = {};
    formate.formateDate = function (date, fmt) {
        if (!date) return "";
        if (!fmt) {
            fmt = "yyyy/MM/dd";
        }
        var o = {
            "M+": date.getMonth() + 1, //月份 
            "d+": date.getDate(), //日 
            "h+": date.getHours(), //小时 
            "m+": date.getMinutes(), //分 
            "s+": date.getSeconds(), //秒 
            "q+": Math.floor((date.getMonth() + 3) / 3), //季度 
            "S": date.getMilliseconds() //毫秒 
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
    formate.formateDate2 = function (date, fmt) {
        if (!date) return "";
        if (date.indexOf("Date") > 0) {
            date = date.replace(/\/Date\(/i, "").replace(/\)\//i, "");
            date = new Date(parseInt(date));
        } else {
            date = date.replace(/-/g, "/").replace("T", " ");
            var temp = date.split(".");
            date = new Date(temp[0]);
            if (temp[1]) date.setMilliseconds(temp[1]);
        }
        return formate.formateDate(date, fmt);
    }
    window.formate = formate;
})();