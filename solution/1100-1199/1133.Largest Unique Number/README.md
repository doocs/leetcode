# [1133. 最大唯一数](https://leetcode.cn/problems/largest-unique-number)

[English Version](/solution/1100-1199/1133.Largest%20Unique%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>A</code>，请找出并返回在该数组中仅出现一次的最大整数。</p>

<p>如果不存在这个只出现一次的整数，则返回 -1。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[5,7,3,9,4,9,8,3,1]
<strong>输出：</strong>8
<strong>解释： </strong>
数组中最大的整数是 9，但它在数组中重复出现了。而第二大的整数是 8，它只出现了一次，所以答案是 8。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[9,9,8,8]
<strong>输出：</strong>-1
<strong>解释： </strong>
数组中不存在仅出现一次的整数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 2000</code></li>
	<li><code>0 &lt;= A[i] &lt;= 1000</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

计数器实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def largestUniqueNumber(self, A: List[int]) -> int:
        counter = Counter(A)
        for i in range(1000, -1, -1):
            if counter[i] == 1:
                return i
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int largestUniqueNumber(int[] A) {
        int[] counter = new int[1001];
        for (int a : A) {
            ++counter[a];
        }
        for (int i = 1000; i >= 0; --i) {
            if (counter[i] == 1) {
                return i;
            }
        }
        return -1;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} A
 * @return {number}
 */
var largestUniqueNumber = function (A) {
    let counter = {};
    for (const a of A) {
        counter[a] = (counter[a] || 0) + 1;
    }
    for (let i = 1000; i >= 0; --i) {
        if (counter[i] == 1) {
            return i;
        }
    }
    return -1;
};
```

### **...**

```

```

<!-- tabs:end -->
