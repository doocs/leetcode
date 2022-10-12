# [213. House Robber II](https://leetcode.com/problems/house-robber-ii)

[中文文档](/solution/0200-0299/0213.House%20Robber%20II/README.md)

## Description

<p>You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are <strong>arranged in a circle.</strong> That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and&nbsp;<b>it will automatically contact the police if two adjacent houses were broken into on the same night</b>.</p>

<p>Given an integer array <code>nums</code> representing the amount of money of each house, return <em>the maximum amount of money you can rob tonight <strong>without alerting the police</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def rob(self, nums: List[int]) -> int:
        def robRange(nums, l, r):
            a, b = 0, nums[l]
            for num in nums[l + 1 : r + 1]:
                a, b = b, max(num + a, b)
            return b

        n = len(nums)
        if n == 1:
            return nums[0]
        s1, s2 = robRange(nums, 0, n - 2), robRange(nums, 1, n - 1)
        return max(s1, s2)
```

### **Java**

```java
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int s1 = robRange(nums, 0, n - 2);
        int s2 = robRange(nums, 1, n - 1);
        return Math.max(s1, s2);
    }

    private int robRange(int[] nums, int l, int r) {
        int a = 0, b = nums[l];
        for (int i = l + 1; i <= r; ++i) {
            int c = Math.max(nums[i] + a, b);
            a = b;
            b = c;
        }
        return b;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int rob(vector<int>& nums) {
        int n = nums.size();
        if (n == 1) return nums[0];
        int s1 = robRange(nums, 0, n - 2);
        int s2 = robRange(nums, 1, n - 1);
        return max(s1, s2);
    }

    int robRange(vector<int>& nums, int l, int r) {
        int a = 0, b = nums[l];
        for (int i = l + 1; i <= r; ++i) {
            int c = max(nums[i] + a, b);
            a = b;
            b = c;
        }
        return b;
    }
};
```

### **Go**

```go
func rob(nums []int) int {
	n := len(nums)
	if n == 1 {
		return nums[0]
	}
	s1, s2 := robRange(nums, 0, n-2), robRange(nums, 1, n-1)
	return max(s1, s2)
}

func robRange(nums []int, l, r int) int {
	a, b := 0, nums[l]
	for i := l + 1; i <= r; i++ {
		a, b = b, max(nums[i]+a, b)
	}
	return b
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
function rob(nums: number[]): number {
    const n = nums.length;
    if (n === 1) {
        return nums[0];
    }
    const robRange = (left: number, right: number) => {
        const dp = [0, 0];
        for (let i = left; i < right; i++) {
            [dp[0], dp[1]] = [dp[1], Math.max(dp[1], dp[0] + nums[i])];
        }
        return dp[1];
    };
    return Math.max(robRange(0, n - 1), robRange(1, n));
}
```

### **Rust**

```rust
impl Solution {
    pub fn rob(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        if n == 1 {
            return nums[0];
        }
        let rob_range = |left, right| {
            let mut dp = [0, 0];
            for i in left..right {
                dp = [dp[1], dp[1].max(dp[0] + nums[i])];
            }
            dp[1]
        };
        rob_range(0, n - 1).max(rob_range(1, n))
    }
}
```

### **...**

```

```

<!-- tabs:end -->
