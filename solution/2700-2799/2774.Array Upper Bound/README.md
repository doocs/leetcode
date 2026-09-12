---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2774.Array%20Upper%20Bound/README.md
tags:
    - JavaScript
---

<!-- problem:start -->

# [2774. 数组的上界 🔒](https://leetcode.cn/problems/array-upper-bound)

[English Version](/solution/2700-2799/2774.Array%20Upper%20Bound/README_EN.md)

## 题目描述

<!-- description:start -->

<p>请你编写代码实现一个数组方法，任何数组都可以调用&nbsp;<code>upperBound()</code>&nbsp;方法，并返回给定目标数字的最后一个索引。<code>nums</code>&nbsp;是一个可能包含重复数字的按升序排序的数组。如果在数组中找不到目标数字，则返回-1。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<b>输入：</b>nums = [3,4,5], target = 5
<b>输出：</b>2
<b>解释：</b>目标值的最后一个索引是 2
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,4,5], target = 2
<b>输出：</b>-1
<b>解释：</b>因为数组中没有数字 2，所以返回 -1。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [3,4,6,6,6,6,7], target = 6
<b>输出：</b>5
<b>解释：</b>目标值的最后一个索引是 5
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code><font face="monospace">-10<sup>4</sup>&nbsp;&lt;= nums[i], target &lt;= 10<sup>4</sup></font></code></li>
	<li><code>nums</code>&nbsp;按升序排序。</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能编写一个时间复杂度为 O(log n) 的算法吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

<!-- thinking:start -->

> **思考**
>
> 有序数组上求 $target$ 最后一次出现的下标。从右线性扫描可以得到答案，题目同时允许对数时间。
>
> 二分出第一个大于 $target$ 的位置，其前一位若等于 $target$ 即为右边界，否则不存在。

<!-- thinking:end -->

数组已升序，用二分找到第一个大于 $\textit{target}$ 的位置，再判断其前一个元素是否等于 $\textit{target}$。若相等则该下标即为最后一次出现的位置，否则返回 $-1$。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### TypeScript

```ts
declare global {
    interface Array<T> {
        upperBound(target: number): number;
    }
}

Array.prototype.upperBound = function (target: number) {
    let left = 0;
    let right = this.length;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (this[mid] > target) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left > 0 && this[left - 1] == target ? left - 1 : -1;
};

// [3,4,5].upperBound(5); // 2
// [1,4,5].upperBound(2); // -1
// [3,4,6,6,6,6,7].upperBound(6) // 5
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：线性扫描

<!-- thinking:start -->

> **思考**
>
> 二分换来对数时间，却多了边界判断。$lastIndexOf$ 从右一次扫描即可，实现更短，最坏线性。

<!-- thinking:end -->

直接调用 `lastIndexOf` 从右向左扫描，返回目标值的最后一次下标；不存在则返回 $-1$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### TypeScript

```ts
declare global {
    interface Array<T> {
        upperBound(target: number): number;
    }
}

Array.prototype.upperBound = function (target: number) {
    return this.lastIndexOf(target);
};

// [3,4,5].upperBound(5); // 2
// [1,4,5].upperBound(2); // -1
// [3,4,6,6,6,6,7].upperBound(6) // 5
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
