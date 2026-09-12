---
comments: true
difficulty: Easy
tags:
    - JavaScript
---

<!-- problem:start -->

# [2774. Array Upper Bound 🔒](https://leetcode.com/problems/array-upper-bound)

[中文文档](/solution/2700-2799/2774.Array%20Upper%20Bound/README.md)

## Description

<!-- description:start -->

<p>Write code that enhances all arrays such that you can call the <code>upperBound()</code>&nbsp;method on any array and it will return the last index of a given <code>target</code> number.&nbsp;<code>nums</code>&nbsp;is a sorted ascending array of numbers that may contain duplicates. If the <code>target</code> number is not found in the array, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,5], target = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> Last index of target value is 2
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,5], target = 2
<strong>Output:</strong> -1
<strong>Explanation:</strong> Because there is no digit 2 in the array, return -1.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,6,6,6,6,7], target = 6
<strong>Output:</strong> 5
<strong>Explanation:</strong> Last index of target value is 5
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code><font face="monospace">-10<sup>4</sup>&nbsp;&lt;= nums[i], target &lt;= 10<sup>4</sup></font></code></li>
	<li><code>nums</code>&nbsp;is sorted in ascending order.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up: </strong>Can you write an algorithm with&nbsp;O(log n)&nbsp;runtime complexity?

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

<!-- thinking:start -->

> **Thinking**
>
> Find the last index of $target$ in a sorted array. A right-to-left scan is correct, but a logarithmic bound is available.
>
> Binary-search the first index greater than $target$; the previous index is the rightmost hit if it equals $target$, otherwise the value is absent.

<!-- thinking:end -->

The array is sorted in non-decreasing order. Binary search for the first index greater than $\textit{target}$, then check whether the previous element equals $\textit{target}$. If it does, that index is the last occurrence; otherwise return $-1$.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$, where $n$ is the length of the array.

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

### Solution 2: Linear Scan

<!-- thinking:start -->

> **Thinking**
>
> Binary search buys a logarithm at the cost of endpoint logic. $lastIndexOf$ scans once from the right and is shorter, though linear in the worst case.

<!-- thinking:end -->

Call `lastIndexOf` to scan from right to left and return the last index of the target, or $-1$ if it does not exist.

The time complexity is $O(n)$, and the space complexity is $O(1)$, where $n$ is the length of the array.

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
