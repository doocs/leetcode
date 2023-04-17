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

**方法一：递归**

我们先判断 `o1` 是否为空，或者 `o1` 是否非对象类型。如果是，则直接返回 `o1` 和 `o2` 是否相等。

否则，我们判断 `o1` 和 `o2` 的类型是否相同。如果不同，则返回 `false`。

接下来，我们判断 `o1` 和 `o2` 是否都是数组。如果不是，则返回 `false`。

如果是数组，则判断两个数组的长度是否相同。如果不同，则返回 `false`。否则，我们遍历两个数组对应位置的元素，递归调用 `areDeeplyEqual` 函数，判断两个元素是否相等。如果有一个元素不相等，则返回 `false`。否则，返回 `true`。

如果 `o1` 和 `o2` 都不是数组，则判断两个对象的键的个数是否相同。如果不同，则返回 `false`。否则，我们遍历 `o1` 的所有键，递归调用 `areDeeplyEqual` 函数，判断两个键对应的值是否相等。如果有一个键对应的值不相等，则返回 `false`。否则，返回 `true`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为 `o1` 和 `o2` 的长度。

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
function areDeeplyEqual(o1: any, o2: any): boolean {
    if (o1 === null || typeof o1 !== 'object') {
        return o1 === o2;
    }
    if (typeof o1 !== typeof o2) {
        return false;
    }
    if (Array.isArray(o1) !== Array.isArray(o2)) {
        return false;
    }
    if (Array.isArray(o1)) {
        if (o1.length !== o2.length) {
            return false;
        }
        for (let i = 0; i < o1.length; i++) {
            if (!areDeeplyEqual(o1[i], o2[i])) {
                return false;
            }
        }
        return true;
    } else {
        const keys1 = Object.keys(o1);
        const keys2 = Object.keys(o2);
        if (keys1.length !== keys2.length) {
            return false;
        }
        for (const key of keys1) {
            if (!areDeeplyEqual(o1[key], o2[key])) {
                return false;
            }
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
