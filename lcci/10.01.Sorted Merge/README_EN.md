# [10.01. Sorted Merge](https://leetcode-cn.com/problems/sorted-merge-lcci)

[中文文档](/lcci/10.01.Sorted%20Merge/README.md)

## Description

<p>You are given two sorted arrays, A and B, where A has a large enough buffer at the end to hold B. Write a method to merge B into A in sorted order.</p>

<p>Initially the number of elements in A and B are&nbsp;<em>m</em>&nbsp;and&nbsp;<em>n</em> respectively.</p>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:</strong>

A = [1,2,3,0,0,0], m = 3

B = [2,5,6],       n = 3



<strong>Output:</strong>&nbsp;[1,2,2,3,5,6]</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **JavaScript**

```js
/**
 * @param {number[]} A
 * @param {number} m
 * @param {number[]} B
 * @param {number} n
 * @return {void} Do not return anything, modify A in-place instead.
 */
var merge = function(A, m, B, n) {
    let i = m - 1, j = n - 1;
    for (let k = A.length - 1; k >= 0; k--) {
        if (k == i) return;
        if (i < 0 || A[i] <= B[j]) {
            A[k] = B[j];
            j--;
        } else {
            A[k] = A[i];
            i--;
        }
    }
};
```

### **...**

```

```

<!-- tabs:end -->
