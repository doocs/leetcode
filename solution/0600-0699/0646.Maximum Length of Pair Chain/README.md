# [646. 最长数对链](https://leetcode.cn/problems/maximum-length-of-pair-chain)

[English Version](/solution/0600-0699/0646.Maximum%20Length%20of%20Pair%20Chain/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出 <code>n</code> 个数对。 在每一个数对中，第一个数字总是比第二个数字小。</p>

<p>现在，我们定义一种跟随关系，当且仅当 <code>b < c</code> 时，数对<code>(c, d)</code> 才可以跟在 <code>(a, b)</code> 后面。我们用这种形式来构造一个数对链。</p>

<p>给定一个数对集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。</p>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>[[1,2], [2,3], [3,4]]
<strong>输出：</strong>2
<strong>解释：</strong>最长的数对链是 [1,2] -> [3,4]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>给出数对的个数在 <code>[1, 1000]</code> 范围内。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

先将 pairs 按照第一个数字升序排列，然后转换为最长上升子序列问题。

朴素做法，时间复杂度 O(n²)。

**方法二：贪心**

在所有可作为下一个数对的集合中，选择第二个数最小的数对添加到数对链。因此可以按照第二个数升序排列的顺序遍历所有数对，如果当前数能加入链，则加入。

时间复杂度 O(nlogn)。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findLongestChain(self, pairs: List[List[int]]) -> int:
        pairs.sort()
        dp = [1] * len(pairs)
        for i, (c, _) in enumerate(pairs):
            for j, (_, b) in enumerate(pairs[:i]):
                if b < c:
                    dp[i] = max(dp[i], dp[j] + 1)
        return max(dp)
```

```python
class Solution:
    def findLongestChain(self, pairs: List[List[int]]) -> int:
        ans, cur = 0, -inf
        for a, b in sorted(pairs, key=lambda x: x[1]):
            if cur < a:
                cur = b
                ans += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));
        int n = pairs.length;
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            dp[i] = 1;
            int c = pairs[i][0];
            for (int j = 0; j < i; ++j) {
                int b = pairs[j][1];
                if (b < c) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));
        int ans = 0;
        int cur = Integer.MIN_VALUE;
        for (int[] p : pairs) {
            if (cur < p[0]) {
                cur = p[1];
                ++ans;
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
    int findLongestChain(vector<vector<int>>& pairs) {
        sort(pairs.begin(), pairs.end());
        int n = pairs.size();
        vector<int> dp(n, 1);
        for (int i = 0; i < n; ++i) {
            int c = pairs[i][0];
            for (int j = 0; j < i; ++j) {
                int b = pairs[j][1];
                if (b < c) dp[i] = max(dp[i], dp[j] + 1);
            }
        }
        return *max_element(dp.begin(), dp.end());
    }
};
```

```cpp
class Solution {
public:
    int findLongestChain(vector<vector<int>>& pairs) {
        sort(pairs.begin(), pairs.end(), [](vector<int> &a, vector<int>b) {
            return a[1] < b[1];
        });
        int ans = 0, cur = INT_MIN;
        for (auto& p : pairs)
        {
            if (cur < p[0])
            {
                cur = p[1];
                ++ans;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findLongestChain(pairs [][]int) int {
	sort.Slice(pairs, func(i, j int) bool {
		return pairs[i][0] < pairs[j][0]
	})
	n := len(pairs)
	dp := make([]int, n)
	ans := 0
	for i := range pairs {
		dp[i] = 1
		c := pairs[i][0]
		for j := range pairs[:i] {
			b := pairs[j][1]
			if b < c {
				dp[i] = max(dp[i], dp[j]+1)
			}
		}
		ans = max(ans, dp[i])
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

```go
func findLongestChain(pairs [][]int) int {
	sort.Slice(pairs, func(i, j int) bool {
		return pairs[i][1] < pairs[j][1]
	})
	ans, cur := 0, math.MinInt32
	for _, p := range pairs {
		if cur < p[0] {
			cur = p[1]
			ans++
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
