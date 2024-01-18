# [2754. 将函数绑定到上下文](https://leetcode.cn/problems/bind-function-to-context)

[English Version](/solution/2700-2799/2754.Bind%20Function%20to%20Context/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>编写一个所有函数都支持的方法&nbsp;<code>bindPolyfill</code> 。当 <code>bindPolyfill</code> 方法被调用并传递了一个对象 <code>obj</code> 时，该对象将成为函数的 <code>this</code> 上下文。</p>

<p>例如，如果你有以下代码：</p>

<pre>
function f() {
  console.log('My context is ' + this.ctx);
}
f();
</pre>

<p>&nbsp;它的输出是 <code>"My context is undefined"</code> 。然而，如果你绑定了该函数：</p>

<pre>
function f() {
  console.log('My context is ' + this.ctx);
}
const boundFunc = f.boundPolyfill({ "ctx": "My Object" })
boundFunc();
</pre>

<p>它的输出应为 <code>"My context is My Object"</code> 。</p>

<p>你可以假设传递给 <code>bindPolyfill</code> 方法的是一个非空对象。</p>

<p>请在不使用内置的 <code>Function.bind</code> 方法的情况下解决该问题。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<b>输入：</b>
fn = function f(multiplier) { 
&nbsp; return this.x * multiplier; 
}
obj = {"x": 10}
inputs = [5]
<b>输出：</b>50
<strong>解释：</strong>
const boundFunc = f.bindPolyfill({"x": 10});
boundFunc(5); // 50
传递了一个乘数 5 作为参数。 
上下文设置为 <code>{"x": 10}</code>。 
将这两个数字相乘得到 50。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>
fn = function speak() { 
&nbsp; return "My name is " + this.name; 
}
obj = {"name": "Kathy"}
inputs = []
<b>输出：</b>"My name is Kathy"
<strong>解释：</strong>
const boundFunc = f.bindPolyfill({"name": "Kathy"});
boundFunc(); // "My name is Kathy"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>obj</code> 是一个非空对象</li>
	<li><code>0 &lt;= inputs.length &lt;= 100</code></li>
</ul>

<p>&nbsp;</p>
<b>你能在不使用任何内置方法的情况下解决这个问题吗？</b>

## 解法

### 方法一

<!-- tabs:start -->

```ts
type Fn = (...args) => any;

declare global {
    interface Function {
        bindPolyfill(obj: Record<any, any>): Fn;
    }
}

Function.prototype.bindPolyfill = function (obj) {
    return (...args) => {
        return this.call(obj, ...args);
    };
};
```

<!-- tabs:end -->

<!-- end -->
