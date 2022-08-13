# [2218. Maximum Value of K Coins From Piles](https://leetcode.com/problems/maximum-value-of-k-coins-from-piles)

[中文文档](/solution/2200-2299/2218.Maximum%20Value%20of%20K%20Coins%20From%20Piles/README.md)

## Description

<p>There are <code>n</code> <strong>piles</strong> of coins on a table. Each pile consists of a <strong>positive number</strong> of coins of assorted denominations.</p>

<p>In one move, you can choose any coin on <strong>top</strong> of any pile, remove it, and add it to your wallet.</p>

<p>Given a list <code>piles</code>, where <code>piles[i]</code> is a list of integers denoting the composition of the <code>i<sup>th</sup></code> pile from <strong>top to bottom</strong>, and a positive integer <code>k</code>, return <em>the <strong>maximum total value</strong> of coins you can have in your wallet if you choose <strong>exactly</strong></em> <code>k</code> <em>coins optimally</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2218.Maximum%20Value%20of%20K%20Coins%20From%20Piles/images/e1.png" style="width: 600px; height: 243px;" />
<pre>
<strong>Input:</strong> piles = [[1,100,3],[7,8,9]], k = 2
<strong>Output:</strong> 101
<strong>Explanation:</strong>
The above diagram shows the different ways we can choose k coins.
The maximum total we can obtain is 101.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7
<strong>Output:</strong> 706
<strong>Explanation:
</strong>The maximum total can be obtained if we choose all coins from the last pile.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == piles.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= piles[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= sum(piles[i].length) &lt;= 2000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxValueOfCoins(self, piles: List[List[int]], k: int) -> int:
        presum = [list(accumulate(p, initial=0)) for p in piles]
        n = len(piles)
        dp = [[0] * (k + 1) for _ in range(n + 1)]
        for i, s in enumerate(presum, 1):
            for j in range(k + 1):
                for idx, v in enumerate(s):
                    if j >= idx:
                        dp[i][j] = max(dp[i][j], dp[i - 1][j - idx] + v)
        return dp[-1][-1]
```

```python
class Solution:
    def maxValueOfCoins(self, piles: List[List[int]], k: int) -> int:
        presum = [list(accumulate(p, initial=0)) for p in piles]
        dp = [0] * (k + 1)
        for s in presum:
            for j in range(k, -1, -1):
                for idx, v in enumerate(s):
                    if j >= idx:
                        dp[j] = max(dp[j], dp[j - idx] + v)
        return dp[-1]
```

### **Java**

```java
class Solution {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int n = piles.size();
        List<int[]> presum = new ArrayList<>();
        for (List<Integer> p : piles) {
            int m = p.size();
            int[] s = new int[m + 1];
            for (int i = 0; i < m; ++i) {
                s[i + 1] = s[i] + p.get(i);
            }
            presum.add(s);
        }
        int[] dp = new int[k + 1];
        for (int[] s : presum) {
            for (int j = k; j >= 0; --j) {
                for (int idx = 0; idx < s.length; ++idx) {
                    if (j >= idx) {
                        dp[j] = Math.max(dp[j], dp[j - idx] + s[idx]);
                    }
                }
            }
        }
        return dp[k];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxValueOfCoins(vector<vector<int>>& piles, int k) {
        vector<vector<int>> presum;
        for (auto& p : piles) {
            int m = p.size();
            vector<int> s(m + 1);
            for (int i = 0; i < m; ++i) s[i + 1] = s[i] + p[i];
            presum.push_back(s);
        }
        vector<int> dp(k + 1);
        for (auto& s : presum) {
            for (int j = k; ~j; --j) {
                for (int idx = 0; idx < s.size(); ++idx) {
                    if (j >= idx) dp[j] = max(dp[j], dp[j - idx] + s[idx]);
                }
            }
        }
        return dp[k];
    }
};
```

### **Go**

```go
func maxValueOfCoins(piles [][]int, k int) int {
	var presum [][]int
	for _, p := range piles {
		m := len(p)
		s := make([]int, m+1)
		for i, v := range p {
			s[i+1] = s[i] + v
		}
		presum = append(presum, s)
	}
	dp := make([]int, k+1)
	for _, s := range presum {
		for j := k; j >= 0; j-- {
			for idx, v := range s {
				if j >= idx {
					dp[j] = max(dp[j], dp[j-idx]+v)
				}
			}
		}
	}
	return dp[k]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
