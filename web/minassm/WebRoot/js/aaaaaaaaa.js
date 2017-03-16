

var mUtil = {

	/**
	 * 打开新窗口
	 */
	openWindow : function(url) {
		window
				.open(
						url,
						'warningInfoWindow',
						'top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
	}

}

var formutil = {
	// 获取指定form中的所有的<input>对象
	getElements : function(formId) {
		var form = document.getElementById(formId);
		var elements = new Array();
		var tagElements = form.getElementsByTagName('input');
		for (var j = 0; j < tagElements.length; j++) {
			elements.push(tagElements[j]);

		}
		return elements;
	},

	// 获取单个input中的【name,value】数组
	inputSelector : function(element) {
		if (element.checked)
			return [element.name, element.value];
	},

	input : function(element) {
		var sel = this;
		switch (element.type.toLowerCase()) {
			case 'submit' :
			case 'hidden' :
			case 'password' :
			case 'text' :
				return [element.name, element.value];
			case 'checkbox' :
			case 'radio' :
				return sel.inputSelector(element);
		}
		return false;
	},

	// 组合URL
	serializeElement : function(element) {
		var sel = this;
		var method = element.tagName.toLowerCase();
		var parameter = sel.input(element);

		if (parameter) {
			var key = encodeURIComponent(parameter[0]);
			if (key.length == 0)
				return;

			if (parameter[1].constructor != Array)
				parameter[1] = [parameter[1]];

			var values = parameter[1];
			var results = [];
			for (var i = 0; i < values.length; i++) {
				results.push(key + '=' + encodeURIComponent(values[i]));
			}
			return results.join('&');
		}
	},

	// 调用方法
	serializeForm : function(formId) {
		var sel = this;
		var elements = sel.getElements(formId);
		var queryComponents = new Array();

		for (var i = 0; i < elements.length; i++) {
			var queryComponent = sel.serializeElement(elements[i]);
			if (queryComponent)
				queryComponents.push(queryComponent);
		}

		return queryComponents.join('&');
	}

}