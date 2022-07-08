# [1218. 最长定差子序列](https://leetcode.cn/problems/longest-arithmetic-subsequence-of-given-difference)

[English Version](/solution/1200-1299/1218.Longest%20Arithmetic%20Subsequence%20of%20Given%20Difference/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>arr</code> 和一个整数 <code>difference</code>，请你找出并返回 <code>arr</code> 中最长等差子序列的长度，该子序列中相邻元素之间的差等于 <code>difference</code> 。</p>

<p><strong>子序列</strong> 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 <code>arr</code> 派生出来的序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,3,4], difference = 1
<strong>输出：</strong>4
<strong>解释：</strong>最长的等差子序列是 [1,2,3,4]。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,3,5,7], difference = 1
<strong>输出：</strong>1
<strong>解释：</strong>最长的等差子序列是任意单个元素。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,5,7,8,5,3,4,2,1], difference = -2
<strong>输出：</strong>4
<strong>解释：</strong>最长的等差子序列是 [7,5,3,1]。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= arr.length <= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> <= arr[i], difference <= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

时间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestSubsequence(self, arr: List[int], difference: int) -> int:
        dp, ans = defaultdict(int), 1
        for num in arr:
            dp[num] = dp[num - difference] + 1
            ans = max(ans, dp[num])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 1;
        for (int num : arr) {
            dp.put(num, dp.getOrDefault(num - difference, 0) + 1);
            ans = Math.max(ans, dp.get(num));
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestSubsequence(vector<int>& arr, int difference) {
        unordered_map<int, int> dp;
        int ans = 1;
        for (int num : arr) {
            dp[num] = dp[num - difference] + 1;
            ans = max(ans, dp[num]);
        }
        return ans;
    }
};
```

### **Go**

```go
func longestSubsequence(arr []int, difference int) int {
	dp, ans := make(map[int]int), 1
	for _, num := range arr {
		dp[num] = dp[num-difference] + 1
		ans = max(ans, dp[num])
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
 * @param {number} difference
 * @return {number}
 */
var longestSubsequence = function (arr, difference) {
    let ans = 1;
    const dp = new Map();
    for (const v of arr) {
        dp.set(v, (dp.get(v - difference) || 0) + 1);
        ans = Math.max(ans, dp.get(v));
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
