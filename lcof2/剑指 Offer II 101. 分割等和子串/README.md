---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20101.%20%E5%88%86%E5%89%B2%E7%AD%89%E5%92%8C%E5%AD%90%E4%B8%B2/README.md
---

<!-- problem:start -->

# [剑指 Offer II 101. 分割等和子串](https://leetcode.cn/problems/NUPfPr)

## 题目描述

<!-- description:start -->

<p>给定一个非空的正整数数组 <code>nums</code> ，请判断能否将这些数字分成元素和相等的两部分。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,5,11,5]
<strong>输出：</strong>true
<strong>解释：</strong>nums<strong> </strong>可以分割成 [1, 5, 5] 和 [11] 。</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,5]
<strong>输出：</strong>false
<strong>解释：</strong>nums<strong> </strong>不可以分为和相等的两部分
</pre>

<p>&nbsp;</p>

<p><meta charset="UTF-8" /></p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 416&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/partition-equal-subset-sum/">https://leetcode.cn/problems/partition-equal-subset-sum/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        s = sum(nums)
        if s % 2 != 0:
            return False

        m, n = len(nums), (s >> 1) + 1
        dp = [[False] * n for _ in range(m)]
        for i in range(m):
            dp[i][0] = True
        if nums[0] < n:
            dp[0][nums[0]] = True

        for i in range(1, m):
            for j in range(n):
                dp[i][j] = dp[i - 1][j]
                if not dp[i][j] and nums[i] <= j:
                    dp[i][j] = dp[i - 1][j - nums[i]]
        return dp[-1][-1]
```

#### Java

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int s = 0;
        for (int x : nums) {
            s += x;
        }
        if (s % 2 != 0) {
            return false;
        }
        int m = nums.length, n = (s >> 1) + 1;
        boolean[] dp = new boolean[n];
        dp[0] = true;
        if (nums[0] < n) {
            dp[nums[0]] = true;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = n - 1; j >= nums[i]; --j) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[n - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canPartition(vector<int>& nums) {
        int s = 0;
        for (int x : nums) s += x;
        if (s % 2 != 0) return false;
        int m = nums.size(), n = (s >> 1) + 1;
        vector<bool> dp(n);
        dp[0] = true;
        if (nums[0] < n) dp[nums[0]] = true;
        for (int i = 1; i < m; ++i) {
            for (int j = n - 1; j >= nums[i]; --j) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[n - 1];
    }
};
```

#### Go

```go
func canPartition(nums []int) bool {
	s := 0
	for _, x := range nums {
		s += x
	}
	if s%2 != 0 {
		return false
	}
	m, n := len(nums), (s>>1)+1
	dp := make([]bool, n)
	dp[0] = true
	if nums[0] < n {
		dp[nums[0]] = true
	}
	for i := 1; i < m; i++ {
		for j := n - 1; j >= nums[i]; j-- {
			dp[j] = dp[j] || dp[j-nums[i]]
		}
	}
	return dp[n-1]
}
```

#### Swift

```swift
class Solution {
    func canPartition(_ nums: [Int]) -> Bool {
        let s = nums.reduce(0, +)
        if s % 2 != 0 { return false }
        let target = s / 2
        var dp = Array(repeating: false, count: target + 1)
        dp[0] = true

        for num in nums {
            for j in stride(from: target, through: num, by: -1) {
                dp[j] = dp[j] || dp[j - num]
            }
        }

        return dp[target]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法二

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        s = sum(nums)
        if s % 2 != 0:
            return False

        m, n = len(nums), (s >> 1) + 1
        dp = [False] * n
        dp[0] = True
        if nums[0] < n:
            dp[nums[0]] = True

        for i in range(1, m):
            for j in range(n - 1, nums[i] - 1, -1):
                dp[j] = dp[j] or dp[j - nums[i]]
        return dp[-1]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法三

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        s = sum(nums)
        if s % 2 != 0:
            return False
        target = s >> 1

        @cache
        def dfs(i, s):
            nonlocal target
            if s > target or i >= len(nums):
                return False
            if s == target:
                return True
            return dfs(i + 1, s) or dfs(i + 1, s + nums[i])

        return dfs(0, 0)
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
