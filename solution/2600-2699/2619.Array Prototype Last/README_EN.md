# [2619. Array Prototype Last](https://leetcode.com/problems/array-prototype-last)

[中文文档](/solution/2600-2699/2619.Array%20Prototype%20Last/README.md)

## Description

Write code that enhances all arrays such that you can call the&nbsp;<code>array.last()</code>&nbsp;method on any array and it will return the last element. If there are no elements in the array, it should return&nbsp;<code>-1</code>.

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Calling nums.last() should return the last element: 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = []
<strong>Output:</strong> -1
<strong>Explanation:</strong> Because there are no elements, return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **TypeScript**

```ts
declare global {
    interface Array<T> {
        last(): T | -1;
    }
}

Array.prototype.last = function () {
    return this[this.length - 1] ?? -1;
};

/**
 * const arr = [1, 2, 3];
 * arr.last(); // 3
 */
```

### **...**

```

```

<!-- tabs:end -->
