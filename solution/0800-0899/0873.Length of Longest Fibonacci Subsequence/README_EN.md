# [873. Length of Longest Fibonacci Subsequence](https://leetcode.com/problems/length-of-longest-fibonacci-subsequence)

[中文文档](/solution/0800-0899/0873.Length%20of%20Longest%20Fibonacci%20Subsequence/README.md)

## Description

<p>A sequence <code>x<sub>1</sub>, x<sub>2</sub>, ..., x<sub>n</sub></code> is <em>Fibonacci-like</em> if:</p>

<ul>
	<li><code>n &gt;= 3</code></li>
	<li><code>x<sub>i</sub> + x<sub>i+1</sub> == x<sub>i+2</sub></code> for all <code>i + 2 &lt;= n</code></li>
</ul>

<p>Given a <b>strictly increasing</b> array <code>arr</code> of positive integers forming a sequence, return <em>the <strong>length</strong> of the longest Fibonacci-like subsequence of</em> <code>arr</code>. If one does not exist, return <code>0</code>.</p>

<p>A <strong>subsequence</strong> is derived from another sequence <code>arr</code> by deleting any number of elements (including none) from <code>arr</code>, without changing the order of the remaining elements. For example, <code>[3, 5, 8]</code> is a subsequence of <code>[3, 4, 5, 6, 7, 8]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3,4,5,6,7,8]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The longest subsequence that is fibonacci-like: [1,2,3,5,8].</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,3,7,11,12,14,18]
<strong>Output:</strong> 3
<strong>Explanation</strong>:<strong> </strong>The longest subsequence that is fibonacci-like: [1,11,12], [3,11,14] or [7,11,18].</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i] &lt; arr[i + 1] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

Dynamic programming.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def lenLongestFibSubseq(self, arr: List[int]) -> int:
        mp = {v: i for i, v in enumerate(arr)}
        n = len(arr)
        dp = [[0] * n for _ in range(n)]
        for i in range(n):
            for j in range(i):
                dp[j][i] = 2
        ans = 0
        for i in range(n):
            for j in range(i):
                d = arr[i] - arr[j]
                if d in mp and (k := mp[d]) < j:
                    dp[j][i] = max(dp[j][i], dp[k][j] + 1)
                    ans = max(ans, dp[j][i])
        return ans
```

### **Java**

```java
class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            mp.put(arr[i], i);
        }
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                dp[j][i] = 2;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int d = arr[i] - arr[j];
                if (mp.containsKey(d)) {
                    int k = mp.get(d);
                    if (k < j) {
                        dp[j][i] = Math.max(dp[j][i], dp[k][j] + 1);
                        ans = Math.max(ans, dp[j][i]);
                    }
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int lenLongestFibSubseq(vector<int>& arr) {
        unordered_map<int, int> mp;
        int n = arr.size();
        for (int i = 0; i < n; ++i) mp[arr[i]] = i;
        vector<vector<int>> dp(n, vector<int>(n));
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < i; ++j)
                dp[j][i] = 2;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int d = arr[i] - arr[j];
                if (mp.count(d)) {
                    int k = mp[d];
                    if (k < j) {
                        dp[j][i] = max(dp[j][i], dp[k][j] + 1);
                        ans = max(ans, dp[j][i]);
                    }
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func lenLongestFibSubseq(arr []int) int {
	n := len(arr)
	mp := make(map[int]int, n)
	for i, v := range arr {
		mp[v] = i + 1
	}
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, n)
		for j := 0; j < i; j++ {
			dp[j][i] = 2
		}
	}
	ans := 0
	for i := 0; i < n; i++ {
		for j := 0; j < i; j++ {
			d := arr[i] - arr[j]
			k := mp[d] - 1
			if k >= 0 && k < j {
				dp[j][i] = max(dp[j][i], dp[k][j]+1)
				ans = max(ans, dp[j][i])
			}
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {number[]} arr
 * @return {number}
 */
var lenLongestFibSubseq = function (arr) {
    const mp = new Map();
    const n = arr.length;
    const dp = new Array(n).fill(0).map(() => new Array(n).fill(0));
    for (let i = 0; i < n; ++i) {
        mp.set(arr[i], i);
        for (let j = 0; j < i; ++j) {
            dp[j][i] = 2;
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            const d = arr[i] - arr[j];
            if (mp.has(d)) {
                const k = mp.get(d);
                if (k < j) {
                    dp[j][i] = Math.max(dp[j][i], dp[k][j] + 1);
                    ans = Math.max(ans, dp[j][i]);
                }
            }
        }
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
