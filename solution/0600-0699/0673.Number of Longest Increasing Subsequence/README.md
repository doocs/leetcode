# [673. 最长递增子序列的个数](https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence)

[English Version](/solution/0600-0699/0673.Number%20of%20Longest%20Increasing%20Subsequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个未排序的整数数组，找到最长递增子序列的个数。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> [1,3,5,4,7]
<strong>输出:</strong> 2
<strong>解释:</strong> 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> [2,2,2,2,2]
<strong>输出:</strong> 5
<strong>解释:</strong> 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
</pre>

<p><strong>注意:</strong>&nbsp;给定的数组长度不超过 2000 并且结果一定是32位有符号整数。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

[最长递增子序列](/solution/0300-0399/0300.Longest%20Increasing%20Subsequence/README.md)的变形题，除了原有的 `dp` 数组之外，另加了 `cnt` 数组记录以 `nums[i]` 结尾的最长子序列的个数

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findNumberOfLIS(self, nums: List[int]) -> int:
        maxLen, ans, n = 0, 0, len(nums)
        dp, cnt = [1] * n, [1] * n
        for i in range(n):
            for j in range(i):
                if nums[i] > nums[j]:
                    if dp[j] + 1 > dp[i]:
                        dp[i] = dp[j] + 1
                        cnt[i] = cnt[j]
                    elif dp[j] + 1 == dp[i]:
                        cnt[i] += cnt[j]
            if dp[i] > maxLen:
                maxLen = dp[i]
                ans = cnt[i]
            elif dp[i] == maxLen:
                ans += cnt[i]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findNumberOfLIS(int[] nums) {
        int maxLen = 0, ans = 0, n = nums.length;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            cnt[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                ans = cnt[i];
            } else if (dp[i] == maxLen) {
                ans += cnt[i];
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
    int findNumberOfLIS(vector<int>& nums) {
        int maxLen = 0, ans = 0, n = nums.size();
        vector<int> dp(n, 1), cnt(n, 1);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                ans = cnt[i];
            } else if (dp[i] == maxLen) {
                ans += cnt[i];
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findNumberOfLIS(nums []int) int {
	maxLen, ans, n := 0, 0, len(nums)
	dp, cnt := make([]int, n), make([]int, n)
	for i := 0; i < n; i++ {
		dp[i] = 1
		cnt[i] = 1
		for j := 0; j < i; j++ {
			if nums[i] > nums[j] {
				if dp[j]+1 > dp[i] {
					dp[i] = dp[j] + 1
					cnt[i] = cnt[j]
				} else if dp[j]+1 == dp[i] {
					cnt[i] += cnt[j]
				}
			}
		}
		if dp[i] > maxLen {
			maxLen = dp[i]
			ans = cnt[i]
		} else if dp[i] == maxLen {
			ans += cnt[i]
		}
	}
	return ans
}
```

### **Rust**

```rust
impl Solution {
    pub fn find_number_of_lis(nums: Vec<i32>) -> i32 {
        let mut max_len = 0;
        let mut ans = 0;
        let n = nums.len();
        let mut dp = vec![1; n];
        let mut cnt = vec![1; n];
        for i in 0..n {
            for j in 0..i {
                if nums[i] > nums[j] {
                    if dp[j] + 1 > dp[i] {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    } else if dp[j] + 1 == dp[i] {
                        cnt[i] += cnt[j];
                    }
                }
            }
            if dp[i] > max_len {
                max_len = dp[i];
                ans = cnt[i];
            } else if dp[i] == max_len {
                ans += cnt[i];
            }
        }
        ans
    }
}
```

### **...**

```

```

<!-- tabs:end -->
