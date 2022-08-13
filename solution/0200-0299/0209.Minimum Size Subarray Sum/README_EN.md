# [209. Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum)

[中文文档](/solution/0200-0299/0209.Minimum%20Size%20Subarray%20Sum/README.md)

## Description

<p>Given an array of positive integers <code>nums</code> and a positive integer <code>target</code>, return the minimal length of a <strong>contiguous subarray</strong> <code>[nums<sub>l</sub>, nums<sub>l+1</sub>, ..., nums<sub>r-1</sub>, nums<sub>r</sub>]</code> of which the sum is greater than or equal to <code>target</code>. If there is no such subarray, return <code>0</code> instead.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = 7, nums = [2,3,1,2,4,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The subarray [4,3] has the minimal length under the problem constraint.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = 4, nums = [1,4,4]
<strong>Output:</strong> 1
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> target = 11, nums = [1,1,1,1,1,1,1,1]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= target &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> If you have figured out the <code>O(n)</code> solution, try coding another solution of which the time complexity is <code>O(n log(n))</code>.

## Solutions

**Method 1: PreSum & Binary search**

**Method 2: Slide window**

<!-- tabs:start -->

### **Python3**

PreSum & Binary search:

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

Slide window:

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

Presum & binary search:

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

Slide window:

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

Presum & binary search:

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

Slide window:

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

Presum & binary search:

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

Slide window:

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
