# Vue3

## 概述

用来构建用户界面的JavaScript框架。基于标准 HTML、CSS 和 JavaScript 构建，并提供了一套 ==声明的==(不用编写步骤)、==组件化的== (页面拆分)编程模型，从而高效开发用户界面。

MVVM Model View ViewModel(vue)，主要负责VM 视图模型，将视图(页面)和模型(json数据)相关联。

## HelloVue

```html
<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<div id="div1">
  <!-- 若组件中定义了template，则会替换根组件中的所有内容，优先使用其作为模板 -->
  <!-- 若组件中没有定义template，则会选择根组件中的innerHTML作为模板 -->
</div>
<script>
  // 根组件 组件->普通的js对象，用来创建组件实例，组件是组件实例的模板
  // 组件=》组件实例=》虚拟DOM=》DOM 页面显示
  // 1.Root 组件
  const Root = {
    // data 方法，返回的对象，属性自动添加到组件实例中
    data() {
      return {
        message: "Hi,Vue!"
      };
    },
    // 配置模板，组件在页面中的显示内容 其中，可以通过"{{}}"直接访问组件实例中的属性
    // @事件 其中可直接操做属性
    template: `<h1 @click='message + 1>Hello,Vue! {{message}}</h1>`
  };
  // 2.创建应用 app 实例
  const app = Vue.createApp(Root);
  // 3.将实例在页面中挂载 将 app 挂载到 id 为 div1
  // vm 为根组件实例
  const vm = app.mount("#div1");
</script>
```

## 构建工具

需要 node.js 和 yarn 管理工具

yarn 安装时出现 “npm ERR! code EACCES”，添加权限即可

### Vite

初始化项目 `yarn init -y` 

安装开发依赖(打包工具) `yarn add vite -D`

安装vue `yarn add vue` 

编写代码 (需要在组件中引入vue)

配置package.json 

​	"scripts": {

​    	"dev": "vite --open",

​    	"build": "vite build",

​		"preview": "vite preview"

​	}

运行 `yarn dev`

## 组件模块化

1. 创建根组件内容提出(默认导出)/定义子组件

   ```js
   export default {
   	data() {
   		return: {
         // 响应式数据：点击按钮，网页中表现为DOM的变化(我们改变的是变量)
         message: "hello",
         count: 0
   		}
   	},
   	template: `<button @click='count++'>快来点我</button><span>count = {{count}}</span>`
   }
   ```

2. 使用

   `import App from "./App"` App为提出内容的文件名

3. 使用子组件

   ```js
   // 1.引入子组件
   import SonCom from './SonCom.js'
   export default {
   	data() {
   		return: {
         // 响应式数据：点击按钮，网页中表现为DOM的变化(我们改变的是变量)
         message: "hello",
         count: 0
   		}
   	},
     // 2.注册子组件 在哪个组件中使用就在哪里组册
     components: {
       // 属性名: 属性值 对应于 `import SonCom from ...` 中的 “SonCom”
       Son: SonCom // SonCom: SonCom 相当于 SonCom
     },
   	template: `<Son></Son>`// 组件实例
   }
   ```

   子组件之间互不干扰，数据相互不影响

## 自动创建项目

创建项目 `yarn create vue`

\- project

--- public 静态资源目录

--- src

------main.js

------App.vue 根组件

添加依赖 `yarn add vue` 

运行 `yarn dev` 

## 单文件组件，data函数，响应式数据

解决 `template` 编写问题，效率低，体验差，执行时耗时

单文件组件：即为一个 ==.vue== 格式文件

```vue
<script>
  export default {
    data() {
      console.log(this); // data是一个当前组件的实例vm
      this.name = "添加属性"; // 直接向组件实例中添加一个属性 非响应式数据
      return {
        // 响应式数据
        mess: "vue文件"
      }
    }
  }
  // -------------------
  export default {
    data: () => {
      console.log(this); // this = undefined 箭头函数没有this，无法访问组件实例
      return {
        mess: "vue文件"
      }
    }
  }
  // ------------------
  export default {
    data: vm => {
      console.log(vm); // 绑定this，将实例的对象传递到第一个参数
      return {
        mess: "vue文件"
      }
    }
  }
</script>
<template>
	<h1>{{mess}}</h1>
	<h1>{{name}}</h1>
</template>
```

> **组件**
>
> 是一个js对象，一个组件可创建多个组件实例
>
> -----
>
> **响应式数据**
>
> data返回一个对象，vue对其进行代理，从而转换为==响应式数据==(数据与网页中的元素进行了关联，数据变化，网页也随之变化；且响应式数据都可以直接通过组件实例访问)。
>
> 直接向组件中添加的属性，不是响应式数据



```js
import {createApp} from "vue"
import App from "./App.vue"
// 将根组件关联到应用上，返回一个应用实例
const app = createApp(App);
// 将应用挂载到应用中 参数为一个DOM元素 
// 挂载会替换掉DOM原来到内容
// 返回一个根组件实例 view model
const vm = app.mount("#root");
```

>**组件实例**
>
>是一个Proxy对象(代理对象)

---

`yarn dev` <font color='#f40'>后错误，插件问题</font> 

[vite] Internal server error: Failed to parse source for import analysis because the content contains invalid JS syntax. Install @vitejs/plugin-vue to handle .vue files.

**浏览器不能识别vue文件，需要构建工具打包后，才可使用；**

**且在打包时，构建工具直接将template转换为函数，不需要在浏览器中编译**  

解决：<font color='#f40'>安装插件+配置</font> 

安装插件 `yarn add @vitejs/plugin-vue -D` 

根目录下创建 vite.config.js，进行如下配置：

```js
import { defineConfig } from "vite";
import Vue from "@vitejs/plugin-vue";
export default defineConfig({
    plugins: [
        Vue()
    ]
})
```

重新 `yarn dev`

----

## 代理

```js
// 对象
const obj = {
  name: "张三",
  age: 18
};
// 代理行为
const handler = {
  /**
   * 指定读取数据时的行为，返回值为最终读取的值；
   * get在指定后，通过代理读取对象属性时，调用get方法来取值
   * @param target 被代理的对象
   * @param prop 读取的属性
   * @param receiver 代理对象
   */
  get(target, prop, receiver) {
    return target[prop];
  },
  /**
   * 通过代理修改对象时调用
   * @param target 被代理对象
   * @param prop 要修改的属性名
   * @param value 设置的属性值
   * @param receiver 代理属性
   */
  set(target, prop, value, receiver) {
    target[prop] = value;
  }
};
// 代理 原对象Proxy
const proxy = new Proxy(obj, handler);
// 读取被代理对象的属性
console.log(proxy.name, proxy.age); // "张三", 18
// 修改代理对象的属性
proxy.age = 12;
console.log(proxy.age); // 12
```

> 代理对象的读和写都是针对被代理的对象
