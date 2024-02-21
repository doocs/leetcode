# [2804. 数组原型的 forEach 方法](https://leetcode.cn/problems/array-prototype-foreach)

[English Version](/solution/2800-2899/2804.Array%20Prototype%20ForEach/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>编写一个数组方法 <code>forEach</code>，使其可以在任何数组上调用 <code>array.forEach(callback, context)</code> 方法，它将在数组的每个元素上执行回调函数。<code>forEach</code> 方法不应该返回任何内容。</p>

<p>回调函数 <code>callback</code> 接受以下参数：</p>

<ul>
	<li><code>value</code> - 表示数组中当前正在处理的元素的值。</li>
	<li><code>index</code> - 表示数组中当前正在处理的元素的索引。</li>
	<li><code>array</code> - 表示数组本身，在回调函数内部可以访问整个数组。</li>
</ul>

<p>上下文 <code>context</code> 应该是作为函数上下文参数传递给回调函数 <code>callback</code> 的对象，确保回调函数 <code>callback</code> 内部的 <code>this</code> 关键字引用此上下文对象。</p>

<p>尝试在不使用内置数组方法的情况下实现这个方法。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<strong>输入：</strong>
arr = [1,2,3], 
callback = (val, i, arr) =&gt; arr[i] = val * 2, 
context = {"context":true}
<strong>输出：</strong>[2,4,6]
<strong>解释：</strong>
arr.forEach(callback, context)&nbsp; 
console.log(arr) // [2,4,6]

回调函数在数组的每个元素上执行。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>
arr = [true, true, false, false], 
callback = (val, i, arr) =&gt; arr[i] = this, 
context = {"context": false}
<strong>输出：</strong>[{"context":false},{"context":false},{"context":false},{"context":false}]
<strong>解释：</strong>
arr.forEach(callback, context)&nbsp;
console.log(arr) // [{"context":false},{"context":false},{"context":false},{"context":false}]

回调函数在数组的每个元素上以正确的上下文执行。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>
arr = [true, true, false, false], 
callback = (val, i, arr) =&gt; arr[i] = !val, 
context = {"context": 5}
<strong>输出：</strong>[false,false,true,true]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>arr</code> 是一个有效的 JSON 数组</li>
	<li><code>context</code> 是一个有效的 JSON 对象</li>
	<li><code>fn</code>&nbsp;是一个函数</li>
	<li><code>0 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```ts
Array.prototype.forEach = function (callback: Function, context: any): void {
    for (let i = 0; i < this.length; ++i) {
        callback.call(context, this[i], i, this);
    }
};

/**
 *  const arr = [1,2,3];
 *  const callback = (val, i, arr) => arr[i] = val * 2;
 *  const context = {"context":true};
 *
 *  arr.forEach(callback, context)
 *
 *  console.log(arr) // [2,4,6]
 */
```

<!-- tabs:end -->

<!-- end -->
