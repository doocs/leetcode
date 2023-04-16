# [2628. 完全相等的 JSON 字符串](https://leetcode.cn/problems/json-deep-equal)

[English Version](/solution/2600-2699/2628.JSON%20Deep%20Equal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个对象 <code>o1</code> 和 <code>o2</code> ，请你检查它们是否 <strong>完全相等</strong> 。</p>

<p>对于两个 <strong>完全相等</strong> 的对象，它们必须包含相同的键，并且相关的值也必须 <strong>完全相等</strong> 。如果两个对象通过了 <code>===</code> 相等性检查，它们也被认为是 <strong>完全相等</strong> 的。</p>

<p>你可以假设这两个对象都是 <code>JSON.parse</code> 的输出。换句话说，它们是有效的 <code>JSON</code> 。</p>

<p>请你在不使用 lodash 的 <code>_.isEqual()</code> 函数的前提下解决这个问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>o1 = {"x":1,"y":2}, o2 = {"x":1,"y":2}
<b>输出：</b>true
<b>输入：</b>键和值完全匹配。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>o1 = {"y":2,"x":1}, o2 = {"x":1,"y":2}
<b>输出：</b>true
<b>解释：</b>尽管键的顺序不同，但它们仍然完全匹配。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>o1 = {"x":null,"L":[1,2,3]}, o2 = {"x":null,"L":["1","2","3"]}
<b>输出：</b>false
<b>解释：</b>数字数组不同于字符串数组。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<b>输入：</b>o1 = true, o2 = false
<b>输出：</b>false
<b>解释：</b>true !== false</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= JSON.stringify(o1).length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= JSON.stringify(o2).length &lt;= 10<sup>5</sup></code></li>
	<li><code>maxNestingDepth &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
