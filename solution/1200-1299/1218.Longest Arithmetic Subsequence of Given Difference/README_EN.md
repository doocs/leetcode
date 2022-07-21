# [1218. Longest Arithmetic Subsequence of Given Difference](https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference)

[中文文档](/solution/1200-1299/1218.Longest%20Arithmetic%20Subsequence%20of%20Given%20Difference/README.md)

## Description

<p>Given an integer array <code>arr</code> and an integer <code>difference</code>, return the length of the longest subsequence in <code>arr</code> which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals <code>difference</code>.</p>

<p>A <strong>subsequence</strong> is a sequence that can be derived from <code>arr</code> by deleting some or no elements without changing the order of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3,4], difference = 1
<strong>Output:</strong> 4
<strong>Explanation: </strong>The longest arithmetic subsequence is [1,2,3,4].</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,3,5,7], difference = 1
<strong>Output:</strong> 1
<strong>Explanation: </strong>The longest arithmetic subsequence is any single element.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,5,7,8,5,3,4,2,1], difference = -2
<strong>Output:</strong> 4
<strong>Explanation: </strong>The longest arithmetic subsequence is [7,5,3,1].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= arr[i], difference &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
