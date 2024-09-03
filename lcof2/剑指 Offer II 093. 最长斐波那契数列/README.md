---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20093.%20%E6%9C%80%E9%95%BF%E6%96%90%E6%B3%A2%E9%82%A3%E5%A5%91%E6%95%B0%E5%88%97/README.md
---

<!-- problem:start -->

# [剑指 Offer II 093. 最长斐波那契数列](https://leetcode.cn/problems/Q91FMA)

## 题目描述

<!-- description:start -->

<p>如果序列&nbsp;<code>X_1, X_2, ..., X_n</code>&nbsp;满足下列条件，就说它是&nbsp;<em>斐波那契式&nbsp;</em>的：</p>

<ul>
	<li><code>n &gt;= 3</code></li>
	<li>对于所有&nbsp;<code>i + 2 &lt;= n</code>，都有&nbsp;<code>X_i + X_{i+1} = X_{i+2}</code></li>
</ul>

<p>给定一个<strong>严格递增</strong>的正整数数组形成序列 <code>arr</code>&nbsp;，找到 <code>arr</code> 中最长的斐波那契式的子序列的长度。如果一个不存在，返回&nbsp;&nbsp;0 。</p>

<p><em>（回想一下，子序列是从原序列&nbsp; <code>arr</code> 中派生出来的，它从 <code>arr</code> 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如，&nbsp;<code>[3, 5, 8]</code>&nbsp;是&nbsp;<code>[3, 4, 5, 6, 7, 8]</code>&nbsp;的一个子序列）</em></p>

<p>&nbsp;</p>

<ul>
</ul>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong>arr =<strong> </strong>[1,2,3,4,5,6,7,8]
<strong>输出: </strong>5
<strong>解释: </strong>最长的斐波那契式子序列为 [1,2,3,5,8] 。
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入: </strong>arr =<strong> </strong>[1,3,7,11,12,14,18]
<strong>输出: </strong>3
<strong>解释</strong>: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= arr.length &lt;= 1000</code></li>
	<li>
	<p><code>1 &lt;= arr[i] &lt; arr[i + 1] &lt;= 10^9</code></p>
	</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 873&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence/">https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

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
                delta = arr[i] - arr[j]
                if delta in mp:
                    k = mp[delta]
                    if k < j:
                        dp[j][i] = dp[k][j] + 1
                        ans = max(ans, dp[j][i])
        return ans
```

#### Java

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
                int delta = arr[i] - arr[j];
                if (mp.containsKey(delta)) {
                    int k = mp.get(delta);
                    if (k < j) {
                        dp[j][i] = dp[k][j] + 1;
                        ans = Math.max(ans, dp[j][i]);
                    }
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int lenLongestFibSubseq(vector<int>& arr) {
        unordered_map<int, int> mp;
        int n = arr.size();
        for (int i = 0; i < n; ++i) mp[arr[i]] = i;
        vector<vector<int>> dp(n, vector<int>(n));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j)
                dp[j][i] = 2;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int delta = arr[i] - arr[j];
                if (mp.count(delta)) {
                    int k = mp[delta];
                    if (k < j) {
                        dp[j][i] = dp[k][j] + 1;
                        ans = max(ans, dp[j][i]);
                    }
                }
            }
        }
        return ans;
    }
};
```

#### Go

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
			delta := arr[i] - arr[j]
			k := mp[delta] - 1
			if k >= 0 && k < j {
				dp[j][i] = dp[k][j] + 1
				ans = max(ans, dp[j][i])
			}
		}
	}
	return ans
}
```

#### Swift

```swift
class Solution {
    func lenLongestFibSubseq(_ arr: [Int]) -> Int {
        let n = arr.count
        var mp = [Int: Int]()
        for i in 0..<n {
            mp[arr[i]] = i
        }
        
        var dp = Array(repeating: Array(repeating: 2, count: n), count: n)
        var ans = 0
        
        for i in 0..<n {
            for j in 0..<i {
                let delta = arr[i] - arr[j]
                if let k = mp[delta], k < j {
                    dp[j][i] = dp[k][j] + 1
                    ans = max(ans, dp[j][i])
                }
            }
        }
        
        return ans > 2 ? ans : 0
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
