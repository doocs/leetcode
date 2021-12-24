# [1310. 子数组异或查询](https://leetcode-cn.com/problems/xor-queries-of-a-subarray)

[English Version](/solution/1300-1399/1310.XOR%20Queries%20of%20a%20Subarray/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个正整数数组&nbsp;<code>arr</code>，现给你一个对应的查询数组&nbsp;<code>queries</code>，其中&nbsp;<code>queries[i] = [L<sub>i,&nbsp;</sub>R<sub>i</sub>]</code>。</p>

<p>对于每个查询&nbsp;<code>i</code>，请你计算从&nbsp;<code>L<sub>i</sub></code>&nbsp;到&nbsp;<code>R<sub>i</sub></code>&nbsp;的&nbsp;<strong>XOR</strong>&nbsp;值（即&nbsp;<code>arr[L<sub>i</sub>] <strong>xor</strong> arr[L<sub>i+1</sub>] <strong>xor</strong> ... <strong>xor</strong> arr[R<sub>i</sub>]</code>）作为本次查询的结果。</p>

<p>并返回一个包含给定查询&nbsp;<code>queries</code>&nbsp;所有结果的数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
<strong>输出：</strong>[2,7,14,8] 
<strong>解释：</strong>
数组中元素的二进制表示形式是：
1 = 0001 
3 = 0011 
4 = 0100 
8 = 1000 
查询的 XOR 值为：
[0,1] = 1 xor 3 = 2 
[1,2] = 3 xor 4 = 7 
[0,3] = 1 xor 3 xor 4 xor 8 = 14 
[3,3] = 8
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
<strong>输出：</strong>[8,0,4,4]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 3 *&nbsp;10^4</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10^9</code></li>
	<li><code>1 &lt;= queries.length &lt;= 3 * 10^4</code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= queries[i][0] &lt;= queries[i][1] &lt; arr.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

由于 `A ^ B = C` => `A ^ A ^ B = A ^ C` => `B = A ^ C`。因此，我们求解 `arr[l] ^ ... ^ arr[r]`，可以转换为求解 `arr[0] ^ ... ^ arr[l - 1]` ^ `arr[0] ^ ... ^ ... ^ arr[r]`。

所以，我们先求解前缀异或，再进行两数异或即可求得每一个 query 的结果。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def xorQueries(self, arr: List[int], queries: List[List[int]]) -> List[int]:
        pre_xor = [0] * (len(arr) + 1)
        for i in range(1, len(arr) + 1):
            pre_xor[i] = pre_xor[i - 1] ^ arr[i - 1]
        return [pre_xor[l] ^ pre_xor[r + 1] for l, r in queries]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] preXor = new int[arr.length + 1];
        for (int i = 1; i <= arr.length; ++i) {
            preXor[i] = preXor[i - 1] ^ arr[i - 1];
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int l = queries[i][0], r = queries[i][1];
            res[i] = preXor[l] ^ preXor[r + 1];
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} arr
 * @param {number[][]} queries
 * @return {number[]}
 */
var xorQueries = function (arr, queries) {
    let n = arr.length;
    let xors = new Array(n + 1).fill(0);
    for (let i = 0; i < n; i++) {
        xors[i + 1] = xors[i] ^ arr[i];
    }
    let res = [];
    for (let query of queries) {
        let [start, end] = query;
        res.push(xors[start] ^ xors[end + 1]);
    }
    return res;
};
```

### **...**

```

```

<!-- tabs:end -->
