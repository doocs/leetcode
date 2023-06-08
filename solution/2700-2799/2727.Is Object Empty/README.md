# [2727. Is Object Empty](https://leetcode.cn/problems/is-object-empty)

[English Version](/solution/2700-2799/2727.Is%20Object%20Empty/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Given an object or an array, return if it is empty.</p>

<ul>
	<li>An empty object contains no key-value pairs.</li>
	<li>An empty array contains no elements.</li>
</ul>

<p>You may assume the object or array is the output of&nbsp;<code>JSON.parse</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> obj = {&quot;x&quot;: 5, &quot;y&quot;: 42}
<strong>Output:</strong> false
<strong>Explanation:</strong> The object has 2 key-value pairs so it is not empty.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> obj = {}
<strong>Output:</strong> true
<strong>Explanation:</strong> The object doesn&#39;t have any key-value pairs so it is empty.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> obj = [null, false, 0]
<strong>Output:</strong> false
<strong>Explanation:</strong> The array has 3 elements so it is not empty.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>&nbsp;<code>2 &lt;= JSON.stringify(obj).length &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Can you solve it in O(1) time?</strong>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：遍历**

我们可以遍历对象或数组，如果遍历到了第一个元素，就返回 `false`，否则返回 `true`。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
function isEmpty(obj: Record<string, any> | any[]): boolean {
    for (const x in obj) {
        return false;
    }
    return true;
}
```

<!-- tabs:end -->
