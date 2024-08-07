---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2634.Filter%20Elements%20from%20Array/README.md
tags:
    - JavaScript
---

<!-- problem:start -->

# [2634. 过滤数组中的元素](https://leetcode.cn/problems/filter-elements-from-array)

[English Version](/solution/2600-2699/2634.Filter%20Elements%20from%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code>arr</code> 和一个过滤函数 <code>fn</code>，并返回一个过滤后的数组 <code>filteredArr</code> 。</p>

<p><code>fn</code> 函数接受一个或两个参数：</p>

<ul>
	<li><code>arr[i]</code> - <code>arr</code> 中的数字</li>
	<li><code>i</code> - <code>arr[i]</code> 的索引</li>
</ul>

<p><code>filteredArr</code> 应该只包含使表达式 <code>fn(arr[i], i)</code> 的值为 <strong>真值</strong> 的 <code>arr</code> 中的元素。<strong>真值</strong> 是指 <code>Boolean(value)</code>&nbsp;返回参数为&nbsp;<code>true</code> 的值。</p>

<p>请在不使用内置的 <code>Array.filter</code> 方法的情况下解决该问题。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [0,10,20,30], fn = function greaterThan10(n) { return n &gt; 10; }
<b>输出：</b> [20,30]
<b>解释：</b>
const newArray = filter(arr, fn); // [20, 30]
过滤函数过滤掉不大于 10 的值</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>arr = [1,2,3], fn = function firstIndex(n, i) { return i === 0; }
<b>输出：</b>[1]
<strong>解释：</strong>
过滤函数 fn 也可以接受每个元素的索引
在这种情况下，过滤函数删除索引不为 0 的元素
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>arr = [-2,-1,0,1,2], fn = function plusOne(n) { return n + 1 }
<b>输出：</b>[-2,0,1,2]
<strong>解释：</strong>
像 0 这样的假值应被过滤掉
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= arr.length &lt;= 1000</code></li>
	<li><code><font face="monospace">-10<sup>9</sup>&nbsp;&lt;= arr[i] &lt;= 10<sup>9</sup></font></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历

我们遍历数组 $arr$，对于每个元素 $arr[i]$，如果 $fn(arr[i], i)$ 为真，则将其加入答案数组中。最后返回答案数组即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $arr$ 的长度。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### TypeScript

```ts
function filter(arr: number[], fn: (n: number, i: number) => any): number[] {
    const ans: number[] = [];
    for (let i = 0; i < arr.length; ++i) {
        if (fn(arr[i], i)) {
            ans.push(arr[i]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
