# [1310. XOR Queries of a Subarray](https://leetcode.com/problems/xor-queries-of-a-subarray)

[中文文档](/solution/1300-1399/1310.XOR%20Queries%20of%20a%20Subarray/README.md)

## Description

<p>You are given an array <code>arr</code> of positive integers. You are also given the array <code>queries</code> where <code>queries[i] = [left<sub>i, </sub>right<sub>i</sub>]</code>.</p>

<p>For each query <code>i</code> compute the <strong>XOR</strong> of elements from <code>left<sub>i</sub></code> to <code>right<sub>i</sub></code> (that is, <code>arr[left<sub>i</sub>] XOR arr[left<sub>i</sub> + 1] XOR ... XOR arr[right<sub>i</sub>]</code> ).</p>

<p>Return an array <code>answer</code> where <code>answer[i]</code> is the answer to the <code>i<sup>th</sup></code> query.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

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

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
<strong>Output:</strong> [8,0,4,4]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length, queries.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= left<sub>i</sub> &lt;= right<sub>i</sub> &lt; arr.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def xorQueries(self, arr: List[int], queries: List[List[int]]) -> List[int]:
        xors = [0]
        for v in arr:
            xors.append(xors[-1] ^ v)
        return [xors[l] ^ xors[r + 1] for l, r in queries]
```

### **Java**

```java
class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] xors = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            xors[i + 1] = xors[i] ^ arr[i];
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int l = queries[i][0];
            int r = queries[i][1];
            ans[i] = xors[l] ^ xors[r + 1];
        }
        return ans;
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
    for (let [l, r] of queries) {
        res.push(xors[l] ^ xors[r + 1]);
    }
    return res;
};
```

### **C++**

```cpp
class Solution {
public:
    vector<int> xorQueries(vector<int>& arr, vector<vector<int>>& queries) {
        int n = arr.size();
        vector<int> xors(n + 1);
        for (int i = 0; i < n; ++i) xors[i + 1] = xors[i] ^ arr[i];
        vector<int> ans;
        for (auto& q : queries) {
            int l = q[0], r = q[1];
            ans.push_back(xors[l] ^ xors[r + 1]);
        }
        return ans;
    }
};
```

### **Go**

```go
func xorQueries(arr []int, queries [][]int) []int {
	xors := make([]int, len(arr)+1)
	for i, v := range arr {
		xors[i+1] = xors[i] ^ v
	}
	var ans []int
	for _, q := range queries {
		l, r := q[0], q[1]
		ans = append(ans, xors[l]^xors[r+1])
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
