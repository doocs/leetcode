# [2677. 分块数组](https://leetcode.cn/problems/chunk-array)

[English Version](/solution/2600-2699/2677.Chunk%20Array/README_EN.md)

<!-- tags: -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数组&nbsp;<code>arr</code>&nbsp;和一个块大小&nbsp;<code>size</code>&nbsp;，返回一个 <strong>分块</strong>&nbsp;的数组。</p>

<p><strong>分块</strong>&nbsp;的数组包含了&nbsp;<code>arr</code>&nbsp;中的原始元素，但是每个子数组的长度都是&nbsp;<code>size</code>&nbsp;。如果&nbsp;<code>arr.length</code>&nbsp;不能被&nbsp;<code>size</code>&nbsp;整除，那么最后一个子数组的长度可能小于&nbsp;<code>size</code>&nbsp;。</p>

<p>你可以假设该数组是&nbsp;<code>JSON.parse</code>&nbsp;的输出结果。换句话说，它是有效的JSON。</p>

<p>请你在不使用 lodash 的函数&nbsp;<code>_.chunk</code>&nbsp;的情况下解决这个问题。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>arr = [1,2,3,4,5], size = 1
<b>输出：</b>[[1],[2],[3],[4],[5]]
<b>解释：</b>数组 <code>arr </code>被分割成了每个只有一个元素的子数组。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>arr = [1,9,6,3,2], size = 3
<b>输出：</b>[[1,9,6],[3,2]]
<b>解释：</b>数组 <code>arr </code>被分割成了每个有三个元素的子数组。然而，第二个子数组只有两个元素。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>arr = [8,5,3,2,6], size = 6
<b>输出：</b>[[8,5,3,2,6]]
<b>解释：</b><code>size </code>大于 <code>arr.length </code>，因此所有元素都在第一个子数组中。
</pre>

<p><strong class="example">示例 4：</strong></p>

<pre>
<b>输入：</b>arr = [], size = 1
<b>输出：</b>[]
<b>解释：</b>没有元素需要分块，因此返回一个空数组。</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>arr</code>&nbsp;是一个有效的 JSON 数组</li>
	<li><code>2 &lt;= JSON.stringify(arr).length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= size &lt;= arr.length + 1</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```ts
function chunk(arr: any[], size: number): any[][] {
    const ans: any[][] = [];
    for (let i = 0, n = arr.length; i < n; i += size) {
        ans.push(arr.slice(i, i + size));
    }
    return ans;
}
```

```js
/**
 * @param {Array} arr
 * @param {number} size
 * @return {Array[]}
 */
var chunk = function (arr, size) {
    const ans = [];
    for (let i = 0, n = arr.length; i < n; i += size) {
        ans.push(arr.slice(i, i + size));
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- end -->
