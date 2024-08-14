---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1310.XOR%20Queries%20of%20a%20Subarray/README.md
rating: 1459
source: 第 170 场周赛 Q2
tags:
    - 位运算
    - 数组
    - 前缀和
---

<!-- problem:start -->

# [1310. 子数组异或查询](https://leetcode.cn/problems/xor-queries-of-a-subarray)

[English Version](/solution/1300-1399/1310.XOR%20Queries%20of%20a%20Subarray/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有一个正整数数组 <code>arr</code>，现给你一个对应的查询数组 <code>queries</code>，其中 <code>queries[i] = [L<sub>i, </sub>R<sub>i</sub>]</code>。</p>

<p>对于每个查询 <code>i</code>，请你计算从 <code>L<sub>i</sub></code> 到 <code>R<sub>i</sub></code> 的 <strong>XOR</strong> 值（即 <code>arr[L<sub>i</sub>] <strong>xor</strong> arr[L<sub>i</sub>+1] <strong>xor</strong> ... <strong>xor</strong> arr[R<sub>i</sub>]</code>）作为本次查询的结果。</p>

<p>并返回一个包含给定查询 <code>queries</code> 所有结果的数组。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
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

<pre>
<strong>输入：</strong>arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
<strong>输出：</strong>[8,0,4,4]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= arr.length <= 3 * 10^4</code></li>
	<li><code>1 <= arr[i] <= 10^9</code></li>
	<li><code>1 <= queries.length <= 3 * 10^4</code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 <= queries[i][0] <= queries[i][1] < arr.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀异或

我们可以用一个长度为 $n+1$ 的前缀异或数组 $s$ 来存储数组 $\textit{arr}$ 的前缀异或结果，其中 $s[i] = s[i-1] \oplus \textit{arr}[i-1]$，即 $s[i]$ 表示 $\textit{arr}$ 的前 $i$ 个元素的异或结果。

那么对于一个查询 $[l,r]$，我们可以得到：

$$
\begin{aligned}
\textit{arr}[l] \oplus \textit{arr}[l+1] \oplus \cdots \oplus \textit{arr}[r] &= (\textit{arr}[0] \oplus \textit{arr}[1] \oplus \cdots \oplus \textit{arr}[l-1]) \oplus (\textit{arr}[0] \oplus \textit{arr}[1] \oplus \cdots \oplus \textit{arr}[r]) \\
&= s[l] \oplus s[r+1]
\end{aligned}
$$

时间复杂度 $O(n+m)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别是数组 $\textit{arr}$ 的长度和查询数组 $\textit{queries}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def xorQueries(self, arr: List[int], queries: List[List[int]]) -> List[int]:
        s = list(accumulate(arr, xor, initial=0))
        return [s[r + 1] ^ s[l] for l, r in queries]
```

#### Java

```java
class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] ^ arr[i - 1];
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int l = queries[i][0], r = queries[i][1];
            ans[i] = s[r + 1] ^ s[l];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> xorQueries(vector<int>& arr, vector<vector<int>>& queries) {
        int n = arr.size();
        int s[n + 1];
        memset(s, 0, sizeof(s));
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] ^ arr[i - 1];
        }
        vector<int> ans;
        for (auto& q : queries) {
            int l = q[0], r = q[1];
            ans.push_back(s[r + 1] ^ s[l]);
        }
        return ans;
    }
};
```

#### Go

```go
func xorQueries(arr []int, queries [][]int) (ans []int) {
	n := len(arr)
	s := make([]int, n+1)
	for i, x := range arr {
		s[i+1] = s[i] ^ x
	}
	for _, q := range queries {
		l, r := q[0], q[1]
		ans = append(ans, s[r+1]^s[l])
	}
	return
}
```

#### TypeScript

```ts
function xorQueries(arr: number[], queries: number[][]): number[] {
    const n = arr.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] ^ arr[i];
    }
    return queries.map(([l, r]) => s[r + 1] ^ s[l]);
}
```

#### JavaScript

```js
/**
 * @param {number[]} arr
 * @param {number[][]} queries
 * @return {number[]}
 */
var xorQueries = function (arr, queries) {
    const n = arr.length;
    const s = Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] ^ arr[i];
    }
    return queries.map(([l, r]) => s[r + 1] ^ s[l]);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
