# [1425. Constrained Subsequence Sum](https://leetcode.com/problems/constrained-subsequence-sum)

[中文文档](/solution/1400-1499/1425.Constrained%20Subsequence%20Sum/README.md)

## Description

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, return the maximum sum of a <strong>non-empty</strong> subsequence of that array such that for every two <strong>consecutive</strong> integers in the subsequence, <code>nums[i]</code> and <code>nums[j]</code>, where <code>i &lt; j</code>, the condition <code>j - i &lt;= k</code> is satisfied.</p>

<p>A <em>subsequence</em> of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,2,-10,5,20], k = 2
<strong>Output:</strong> 37
<b>Explanation:</b> The subsequence is [10, 2, 5, 20].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,-2,-3], k = 1
<strong>Output:</strong> -1
<b>Explanation:</b> The subsequence must be non-empty, so we choose the largest number.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,-2,-10,-5,20], k = 2
<strong>Output:</strong> 23
<b>Explanation:</b> The subsequence is [10, -2, -5, 20].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def constrainedSubsetSum(self, nums: List[int], k: int) -> int:
        n = len(nums)
        dp = [0] * n
        ans = -inf
        q = deque()
        for i, v in enumerate(nums):
            if q and i - q[0] > k:
                q.popleft()
            dp[i] = max(0, 0 if not q else dp[q[0]]) + v
            while q and dp[q[-1]] <= dp[i]:
                q.pop()
            q.append(i)
            ans = max(ans, dp[i])
        return ans
```

### **Java**

```java
class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        int ans = Integer.MIN_VALUE;
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (!q.isEmpty() && i - q.peek() > k) {
                q.poll();
            }
            dp[i] = Math.max(0, q.isEmpty() ? 0 : dp[q.peek()]) + nums[i];
            while (!q.isEmpty() && dp[q.peekLast()] <= dp[i]) {
                q.pollLast();
            }
            q.offer(i);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int constrainedSubsetSum(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> dp(n);
        int ans = INT_MIN;
        deque<int> q;
        for (int i = 0; i < n; ++i) {
            if (!q.empty() && i - q.front() > k) q.pop_front();
            dp[i] = max(0, q.empty() ? 0 : dp[q.front()]) + nums[i];
            ans = max(ans, dp[i]);
            while (!q.empty() && dp[q.back()] <= dp[i]) q.pop_back();
            q.push_back(i);
        }
        return ans;
    }
};
```

### **Go**

```go
func constrainedSubsetSum(nums []int, k int) int {
	n := len(nums)
	dp := make([]int, n)
	ans := math.MinInt32
	q := []int{}
	for i, v := range nums {
		if len(q) > 0 && i-q[0] > k {
			q = q[1:]
		}
		dp[i] = v
		if len(q) > 0 && dp[q[0]] > 0 {
			dp[i] += dp[q[0]]
		}
		for len(q) > 0 && dp[q[len(q)-1]] < dp[i] {
			q = q[:len(q)-1]
		}
		q = append(q, i)
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

### **...**

```

```

<!-- tabs:end -->
