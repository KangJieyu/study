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

## 工具

### 构建工具 Vite

> node.js 和 yarn 管理工具
>
> yarn 安装时出现 “npm ERR! code EACCES”，添加权限即可

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

### 调试工具

浏览器安装扩展程序“Vue.js devtools”

## 组件模块化(组件 模板 指令)

### 组件

创建根组件内容提出(默认导出)/定义子组件

```js
export default {
	data() {
		return: {
      // 响应式数据：点击按钮，网页中表现为DOM的变化(我们改变的是变量)
      message: "hello",
      count: 0,
      // stu对象中的属性也为响应式 深层响应式
     	stu: {
        name: "zhangsan",
        age: 18
      }
		}
	},
	template: `<button @click='count++'>快来点我</button><span>count = {{count}}</span>`
}
```

1. 使用

   `import App from "./App"` App为提出内容的文件名

2. 使用子组件

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

### template

`{{ }}` 为插值，其中只能使用==表达式== ；

插值实际是修改元素DOM的textContent，若内容中含有HTML标签，标签将被转义显示给用户；

要想HTML标签正常显示，则需使用==指令==。

```vue
<script>
	const msg = "hello,vue";
</script>
<template>
	<!-- 直接访问组件中的变量 -->
	<h1>{{ msg }}</h1>
	<!-- 使用一些全局对象，如Date、Math、NaN -->
	<h1>{{ Math.random }}</h1>
	<!-- 在App对象中配置全局 -->
</template>
```

```js
import {createApp} from "vue";
import App from "App.vue";

const app = createApp(app);

// 配置
app.config.globalProperties.NAME = VALUE;

app.mount("#app");
```

### v-指令

显示 template 中的HTML内容，不需要插值符号了。

- v-text="" 

  将表达式值为textContent插入，同`{{}}`

- v-html=""

  将表达式值为innerHTML插入，但有被xss攻击风险，==不安全==

- v-bind:Prop="VALUE"  ==**绑定class属性**== 

  为标签动态的设置属性值

  `<img v-bind:src="path" alt="">` 为img标签添加路径 同：`<img :src="path" alt=""/>`

  

  `const attrs = {name: "zhangsan", age: "18"};` 

  `<div v-bind="attrs">\</div>` 为div绑定name和age两个属性并设值

  [:class=""绑定HTML](#绑定HTML)

- v-bind:[attrName]="attrValue"

  为标签设置动态参数(动态属性名和属性值)

- v-show="{boolean表达式}"

  设置内容是否显示，表达式true则内容显示，否则相反；

  实质是通过 `display: none` 设置

## 自动创建项目

创建项目 `yarn create vue`

\- project

--- public 静态资源目录

--- src

------main.js

------App.vue 根组件

添加依赖 `yarn add vue` 

运行 `yarn dev` 

## 单文件组件 data 响应式数据 methods computed setup

解决 `template` 编写问题，效率低，体验差，执行时耗时

单文件组件：即为一个 ==.vue== 格式文件

### data

```vue
<script>
  export default {
    // data 指定实例对象中的响应式属性
    data() {
      console.log(this); // data是一个当前组件的实例vm
      this.name = "添加属性"; // 直接向组件实例中添加一个属性 非响应式数据
      // this.$data.hello = "hi"; 动态的添加响应式数据
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

**深层响应式数据**

```vue
<script>
  export default {
    data() {
      return {
        message: "响应式数据",
        stu: {
          "name": "zhangsan",
          "age": 18
        }
      }
    }
  }
</script>
```

**深层——>浅层**

```vue
<script>
  import {shallowReactive} from "vue"
  export default {
    data() {
      // 浅层响应式数据
      return shallowReactive({
        message: "响应式数据",
        stu: {
          "name": "lisi",
          "age": 18
        }
      })
    }
  }
</script>
```

### mthods

指定实例对象中的方法

```vue
<script>
	data() {
    
  },
  methods: {
    // 其中可定义多个方法，最后挂载到组件实例，可通过组件实例调用
    test() {
      // this 会自动绑定到组件实例
      console.log(this);
      // code
    }
  }
</script>
<template>
	<!-- 调用组件实例中的方法 -->
	{{ test() }}
</template>
```



### computed

计算属性 属性名: getter

会对数据缓存

```vue
<script>
  export default {
    data() {
      
    },
    methods: {
      // 组件重新渲染时调用
      getInfo() {
        
      }
    },
    computed: {
      // 会对数据缓存，只有在关联属性发生变化时重新执行
      // 获取
      info: function() {
        // code
        return res;
      };
      // 相当于
      info() {
    		return res;
			};
  		// 属性可读可写,尽量不写
  		info: {
        getInfo() {
          return res;
        },
        setInfo(value) {
          // code
        }
      }
    }
  }
</script>
```

### setup

钩子函数  ==组合式API== 

```vue
<script>
  import {reactive} from "vue"
  export default {
    setup() {
      // 直接声明的变量不是响应式属性
      let name = "张三";
      let age = 20;
      // reactive创建的响应式对象，即 stu 为响应式数据
      const stu = reactive({
        name: "李四",
        age: 18,
        sex: "女"
      });
     	function changeAge(value) {
        // 修改值 可避免 this
        stu.NAME = value;
      }
      // 通过return向外暴露数据，不然不可使用
      return {
        age, // 非响应式数据 在浏览器中可更改
        stu, // 响应式数据
        changeAge
      }
    }
  }
</script>
```

上述代码进一步改进：

`<script setup></script>` 加``setup`为纯组合式API，不需要编写 `return` 向外暴露数据，但响应式数据仍然需要 `reactive`

```vue
<script setup>
  import {creative} from "vue";
  let name = "张三";
  let age = 20;
  const stu = reactive({
    name: "李四",
    age: 18,
    sex: "女"
  });
  function changeAge(value) {
    // code
  }
</script>
```

### reactive

`reactive()` 

返回一个对象的响应式代理；

返回的是一个深层响应式对象；

也可根据 `shallowReactive()` 创建一个浅层的响应式对象。

缺点：只能返回对象的响应式代理，不能处理原始值 ==> `ref()` 转化为对象，再代理

### ref

`const count = ref(0)` 创建了一个对象,将对象进行响应式处理 {value: 0},再返回count

`const person = ref({name: "zhangsan", age: 18})` -> person = {value: {name: "", age: ""}}

ref 处理的原始数据，**在template中会自动解包(ref对象必须是顶层对象)**，不需要调用value。使用：

```vue
<script>
  // 顶层对象
  const person = ref({
  	name: "张三",
  	age: 18
	}); // {value: person} 
  // 非顶层对象
  const obj = {
    name: ref("李四"),
    age: ref(18)
  };
  // 构建->obj自动解包
  const {name, age} = obj;
</script>
<template>
	{{ person.name }}
</template>
```

**在script中支持自动解包**

默认情况不支持，需配置 vite.config.js

plugins: [vue({

​    reactivityTransform: true

  })],

```vue
<script>
  const person = $ref({
    // code
  })
</script>
```

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
    // 获取前，vue中data()返回的对象都会被vue所代理-> 通过代理读取属性，会有一个跟踪操作 tracker()
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
    // 修改后，通知应用位置进行更新 tigger()
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
>
> 代理不会对原对象产生影响

## 样式

### 全局样式

此中的样式为==全局样式== ，作用于整体项目

```vue
<script></script>
<template>
<!-- 单根组件 -->
<h1>我是单根组件</h1>
</template>
<style>
  /* code */
</style>
```

### 局部样式

使用 scoped 属性后，Vue 会为组件中的所有的元素生成一个随机属性==data-v-xxxx== ;

css样式修饰改为**属性选择器**：元素/class选择器/id选择器[data-v-xxxx]；

且生成的随机属性**data-v-xxxx**，会添加到当前组件内到所有元素上，也会添加到当前组件引入其他组件(**单根组件**)的根元素上，不会添加到当前组件引入到多根组件中。

```vue
<script></script>
<template>
<!-- 多根组件 -->
<h1>我是多根组件中的h1</h1>
<div>我是多根组件中的div</div>
</template>
<style scoped>
	/* code */
</style>
```

### 其他样式

```vue
<style>
  /*:deep() 深入选择器在组件中设置被引入组件中h2标签的样式*/
  .app :deep(h2) {
	}
  /*:global() 全局选择器*/
  :global(div) {
  }
</style>
```

### 模块化

自动将类名hash处理，形式为：==.\_类_xxxx_x== 

保证唯一性，且每个模块的样式互不影响

```vue
<template>
<!-- 使用模块化样式 -->
<div :class="$style.box1"></div>
</template>
<style module>
  .box1 {
    
  }
</style>
```

### 绑定HTML

为`class`和`style`的v-bind用法提供了增强方法，除字符串外也可以为对象或数组。

- 绑定对象

  `<div v-bind:class="{active: isActive}"></div>` isActive是个boolean值，active取决于isActive的值

- 绑定数组

  `data() {return {class1: 'active', class2: 'text-danger'}}`

  `<div :class="[class1, class2]"></div>` => `<div class="active text-danger"></div>`

## 传递数据

子组件中的数据通常在创建组件实例时确定，父组件可通过props来向子组件中传递数据。

如何使用prop？

1. 在子组件(Item)中定义

   ```vue
   <script setup>
     const props = defineProps(["a", "b", "c"]); // 通过数组给子组件定义属性名
     console.log("props", props.a);
     const propsMess = defineProps(["objMess"]);
     console.log("name", propsMess.objMess.name);
   </script>
   ```

2. 在父组件中传递

   ```vue
   <script>
     const obj = {
       name: "zhangsan",
       age: 18
     }
   </script>
   <template>
   <Item a="Vue" b="Java" c="C++"></Item>
   <!-- /////////////// -->
   <Item :objMess="obj"></Item>
   </template>
   ```

   

