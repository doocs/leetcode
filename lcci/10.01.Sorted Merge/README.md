# [面试题 10.01. 合并排序的数组](https://leetcode-cn.com/problems/sorted-merge-lcci)

[English Version](/lcci/10.01.Sorted%20Merge/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。</p>

<p>初始化&nbsp;A 和 B 的元素数量分别为&nbsp;<em>m</em> 和 <em>n</em>。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong>
A = [1,2,3,0,0,0], m = 3
B = [2,5,6],       n = 3

<strong>输出:</strong>&nbsp;[1,2,2,3,5,6]</pre>

<p><strong>说明:</strong></p>

<ul>
	<li><code>A.length == n + m</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
var merge = function (A, m, B, n) {
    let i = m - 1,
        j = n - 1;
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
