# [873. 最长的斐波那契子序列的长度](https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence)

[English Version](/solution/0800-0899/0873.Length%20of%20Longest%20Fibonacci%20Subsequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果序列 <code>X_1, X_2, ..., X_n</code> 满足下列条件，就说它是 <em>斐波那契式 </em>的：</p>

<ul>
	<li><code>n >= 3</code></li>
	<li>对于所有 <code>i + 2 <= n</code>，都有 <code>X_i + X_{i+1} = X_{i+2}</code></li>
</ul>

<p>给定一个<strong>严格递增</strong>的正整数数组形成序列 arr ，找到 <font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.600000381469727px"><span style="caret-color:#c7254e"><span style="background-color:#f9f2f4">arr</span></span></span></font></font> 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。</p>

<p><em>（回想一下，子序列是从原序列 <font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.600000381469727px"><span style="caret-color:#c7254e"><span style="background-color:#f9f2f4">arr</span></span></span></font></font> 中派生出来的，它从 <font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.600000381469727px"><span style="caret-color:#c7254e"><span style="background-color:#f9f2f4">arr</span></span></span></font></font> 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， <code>[3, 5, 8]</code> 是 <code>[3, 4, 5, 6, 7, 8]</code> 的一个子序列）</em></p>

<p> </p>

<ul>
</ul>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong>arr =<strong> </strong>[1,2,3,4,5,6,7,8]
<strong>输出: </strong>5
<strong>解释: </strong>最长的斐波那契式子序列为 [1,2,3,5,8] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入: </strong>arr =<strong> </strong>[1,3,7,11,12,14,18]
<strong>输出: </strong>3
<strong>解释</strong>: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 <= arr.length <= 1000</code></li>
	<li>
	<p><code>1 <= arr[i] < arr[i + 1] <= 10^9</code></p>
	</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

-   状态表示：`dp[j][i]` 表示斐波那契式最后两项为 `arr[j]`, `arr[i]` 时的最大子序列长度。
-   状态计算：`dp[j][i] = dp[k][j] + 1`（当且仅当 `k < j < i`，并且 `arr[k] + arr[j] == arr[i]`）, `ans = max(ans, dp[j][i])`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
