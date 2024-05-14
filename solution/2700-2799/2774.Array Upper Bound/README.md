# [2774. 数组的上界 🔒](https://leetcode.cn/problems/array-upper-bound)

[English Version](/solution/2700-2799/2774.Array%20Upper%20Bound/README_EN.md)

<!-- tags: -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

### 方法一

<!-- tabs:start -->

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

### 方法二

<!-- tabs:start -->

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

<!-- end -->
