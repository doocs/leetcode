# [209. 长度最小的子数组](https://leetcode.cn/problems/minimum-size-subarray-sum)

[English Version](/solution/0200-0299/0209.Minimum%20Size%20Subarray%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个含有 <code>n</code><strong> </strong>个正整数的数组和一个正整数 <code>target</code><strong> 。</strong></p>

<p>找出该数组中满足其和<strong> </strong><code>≥ target</code><strong> </strong>的长度最小的 <strong>连续子数组</strong> <code>[nums<sub>l</sub>, nums<sub>l+1</sub>, ..., nums<sub>r-1</sub>, nums<sub>r</sub>]</code> ，并返回其长度<strong>。</strong>如果不存在符合条件的子数组，返回 <code>0</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>target = 7, nums = [2,3,1,2,4,3]
<strong>输出：</strong>2
<strong>解释：</strong>子数组 <code>[4,3]</code> 是该条件下的长度最小的子数组。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>target = 4, nums = [1,4,4]
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>target = 11, nums = [1,1,1,1,1,1,1,1]
<strong>输出：</strong>0
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= target <= 10<sup>9</sup></code></li>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= 10<sup>5</sup></code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong></p>

<ul>
	<li>如果你已经实现<em> </em><code>O(n)</code> 时间复杂度的解法, 请尝试设计一个 <code>O(n log(n))</code> 时间复杂度的解法。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 二分查找**

先求出数组的前缀和 `s`，然后根据 `s[j] - s[i] >= target` => `s[j] >= s[i] + target`，找出最小的一个 j，使得 `s[j]` 满足大于等于 `s[i] + target`，然后更新最小长度即可。

时间复杂度 $O(NlogN)$。

**方法二：滑动窗口**

使用指针 `left`, `right` 分别表示子数组的开始位置和结束位置，维护变量 `sum` 表示子数组 `nums[left...right]` 元素之和。初始时 `left`, `right` 均指向 0。每一次迭代，将 `nums[right]` 加到 `sum`，如果此时 `sum >= target`，更新最小长度即可。然后将 `sum` 减去 `nums[left]`，接着 `left` 指针右移直至 `sum < target`。每一次迭代最后，将 `right` 指针右移。

时间复杂度 $O(N)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

前缀和 + 二分查找：

```python
class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        s = [0] + list(accumulate(nums))
        n = len(nums)
        ans = n + 1
        for i, v in enumerate(s):
            t = v + target
            j = bisect_left(s, t)
            if j != n + 1:
                ans = min(ans, j - i)
        return 0 if ans == n + 1 else ans
```

滑动窗口：

```python
class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        n = len(nums)
        left = right = 0
        sum, res = 0, n + 1
        while right < n:
            sum += nums[right]
            while sum >= target:
                res = min(res, right - left + 1)
                sum -= nums[left]
                left += 1
            right += 1
        return 0 if res == n + 1 else res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

前缀和 + 二分查找：

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int ans = n + 1;
        for (int i = 0; i < n; ++i) {
            int t = s[i] + target;
            int left = 0, right = n + 1;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (s[mid] >= t) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (left != n + 1) {
                ans = Math.min(ans, left - i);
            }
        }
        return ans == n + 1 ? 0 : ans;
    }
}
```

滑动窗口：

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int left = 0, right = 0;
        int sum = 0, res = n + 1;
        while (right < n) {
            sum += nums[right];
            while (sum >= target) {
                res = Math.min(res, right - left + 1);
                sum -= nums[left++];
            }
            ++right;
        }
        return res == n + 1 ? 0 : res;
    }
}
```

### **C++**

前缀和 + 二分查找：

```cpp
class Solution {
public:
    int minSubArrayLen(int target, vector<int>& nums) {
        int n = nums.size();
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + nums[i];
        int ans = n + 1;
        for (int i = 0; i < n; ++i) {
            int t = s[i] + target;
            auto p = lower_bound(s.begin(), s.end(), t);
            if (p != s.end()) {
                int j = p - s.begin();
                ans = min(ans, j - i);
            }
        }
        return ans == n + 1 ? 0 : ans;
    }
};
```

滑动窗口：

```cpp
class Solution {
public:
    int minSubArrayLen(int target, vector<int>& nums) {
        int left = 0, right;
        int sum = 0;
        int minlen = INT_MAX;

        for (right = 0; right < nums.size(); right++) {
            sum += nums[right];
            while (left <= right && sum >= target) {
                minlen = min(minlen, right - left + 1);
                sum -= nums[left++];
            }
        }

        return minlen == INT_MAX ? 0 : minlen;
    }
};
```

### **Go**

前缀和 + 二分查找：

```go
func minSubArrayLen(target int, nums []int) int {
	n := len(nums)
	s := make([]int, n+1)
	for i, v := range nums {
		s[i+1] = s[i] + v
	}
	ans := n + 1
	for i, v := range s {
		t := v + target
		left, right := 0, n+1
		for left < right {
			mid := (left + right) >> 1
			if s[mid] >= t {
				right = mid
			} else {
				left = mid + 1
			}
		}
		if left != n+1 && ans > left-i {
			ans = left - i
		}
	}
	if ans == n+1 {
		return 0
	}
	return ans
}
```

### **C#**

滑动窗口：

```cs
public class Solution {
    public int MinSubArrayLen(int target, int[] nums) {
        int n = nums.Length;
        int left = 0, right = 0;
        int sum = 0, res = n + 1;
        while (right < n)
        {
            sum += nums[right];
            while (sum >= target)
            {
                res = Math.Min(res, right - left + 1);
                sum -= nums[left++];
            }
            ++right;
        }
        return res == n + 1 ? 0 : res;
    }
}
```

### **TypeScript**

```ts
function minSubArrayLen(target: number, nums: number[]): number {
    const n = nums.length;
    let res = n + 1;
    let sum = 0;
    let i = 0;
    for (let j = 0; j < n; j++) {
        sum += nums[j];
        while (sum >= target) {
            res = Math.min(res, j - i + 1);
            sum -= nums[i];
            i++;
        }
    }

    if (res === n + 1) {
        return 0;
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_sub_array_len(target: i32, nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut res = n + 1;
        let mut sum = 0;
        let mut i = 0;
        for j in 0..n {
            sum += nums[j];

            while sum >= target {
                res = res.min(j - i + 1);
                sum -= nums[i];
                i += 1;
            }
        }
        if res == n + 1 {
            return 0;
        }
        res as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
