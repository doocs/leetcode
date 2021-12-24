# [1310. XOR Queries of a Subarray](https://leetcode.com/problems/xor-queries-of-a-subarray)

[中文文档](/solution/1300-1399/1310.XOR%20Queries%20of%20a%20Subarray/README.md)

## Description

Given the array <code>arr</code> of positive integers and the array <code>queries</code> where <code>queries[i] = [L<sub>i,&nbsp;</sub>R<sub>i</sub>]</code>,&nbsp;for each query <code>i</code> compute the <strong>XOR</strong> of elements from <code>L<sub>i</sub></code> to <code>Ri</code> (that is, <code>arr[L<sub>i</sub>] <strong>xor</strong> arr[L<sub>i+1</sub>] <strong>xor</strong> ... <strong>xor</strong> arr[R<sub>i</sub>]</code> ). Return an array containing the result for the given <code>queries</code>.

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]

<strong>Output:</strong> [2,7,14,8] 

<strong>Explanation:</strong> 

The binary representation of the elements in the array are:

1 = 0001 

3 = 0011 

4 = 0100 

8 = 1000 

The XOR values for queries are:

[0,1] = 1 xor 3 = 2 

[1,2] = 3 xor 4 = 7 

[0,3] = 1 xor 3 xor 4 xor 8 = 14 

[3,3] = 8

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]

<strong>Output:</strong> [8,0,4,4]

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 3 *&nbsp;10^4</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10^9</code></li>
	<li><code>1 &lt;= queries.length &lt;= 3 * 10^4</code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= queries[i][0] &lt;= queries[i][1] &lt; arr.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def xorQueries(self, arr: List[int], queries: List[List[int]]) -> List[int]:
        pre_xor = [0] * (len(arr) + 1)
        for i in range(1, len(arr) + 1):
            pre_xor[i] = pre_xor[i - 1] ^ arr[i - 1]
        return [pre_xor[l] ^ pre_xor[r + 1] for l, r in queries]
```

### **Java**

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
