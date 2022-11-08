# [198. House Robber](https://leetcode.com/problems/house-robber)

[中文文档](/solution/0100-0199/0198.House%20Robber/README.md)

## Description

<p>You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and <b>it will automatically contact the police if two adjacent houses were broken into on the same night</b>.</p>

<p>Given an integer array <code>nums</code> representing the amount of money of each house, return <em>the maximum amount of money you can rob tonight <b>without alerting the police</b></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,7,9,3,1]
<strong>Output:</strong> 12
<strong>Explanation:</strong> Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 400</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def rob(self, nums: List[int]) -> int:
        @cache
        def dfs(i):
            if i >= len(nums):
                return 0
            return max(nums[i] + dfs(i + 2), dfs(i + 1))

        return dfs(0)
```

```python
class Solution:
    def rob(self, nums: List[int]) -> int:
        n = len(nums)
        dp = [0] * (n + 1)
        dp[1] = nums[0]
        for i in range(2, n + 1):
            dp[i] = max(nums[i - 1] + dp[i - 2], dp[i - 1])
        return dp[n]
```

```python
class Solution:
    def rob(self, nums: List[int]) -> int:
        a, b = 0, nums[0]
        for num in nums[1:]:
            a, b = b, max(num + a, b)
        return b
```

### **Java**

```java
class Solution {
    private int[] f;
    private int[] nums;

    public int rob(int[] nums) {
        this.nums = nums;
        f = new int[nums.length];
        Arrays.fill(f, -1);
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= nums.length) {
            return 0;
        }
        if (f[i] != -1) {
            return f[i];
        }
        f[i] = Math.max(nums[i] + dfs(i + 2), dfs(i + 1));
        return f[i];
    }
}
```

```java
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[1] = nums[0];
        for (int i = 2; i <= n; ++i) {
            dp[i] = Math.max(nums[i - 1] + dp[i - 2], dp[i - 1]);
        }
        return dp[n];
    }
}
```

```java
class Solution {
    public int rob(int[] nums) {
        int a = 0, b = nums[0];
        for (int i = 1; i < nums.length; ++i) {
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
        vector<int> f(n, -1);
        function<int(int)> dfs = [&](int i) -> int {
            if (i >= n) return 0;
            if (f[i] != -1) return f[i];
            f[i] = max(nums[i] + dfs(i + 2), dfs(i + 1));
            return f[i];
        };
        return dfs(0);
    }
};
```

```cpp
class Solution {
public:
    int rob(vector<int>& nums) {
        int n = nums.size();
        vector<int> dp(n + 1);
        dp[1] = nums[0];
        for (int i = 2; i <= n; ++i) {
            dp[i] = max(nums[i - 1] + dp[i - 2], dp[i - 1]);
        }
        return dp[n];
    }
};
```

```cpp
class Solution {
public:
    int rob(vector<int>& nums) {
        int n = nums.size();
        int a = 0, b = nums[0];
        for (int i = 1; i < n; ++i) {
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
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] != -1 {
			return f[i]
		}
		f[i] = max(nums[i]+dfs(i+2), dfs(i+1))
		return f[i]
	}
	return dfs(0)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func rob(nums []int) int {
	n := len(nums)
	dp := make([]int, n+1)
	dp[1] = nums[0]
	for i := 2; i <= n; i++ {
		dp[i] = max(nums[i-1]+dp[i-2], dp[i-1])
	}
	return dp[n]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func rob(nums []int) int {
    a, b, n := 0, nums[0], len(nums)
    for i := 1; i < n; i++ {
        a, b = b, max(nums[i] + a, b)
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
    const f = new Array(n).fill(-1);
    function dfs(i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != -1) {
            return f[i];
        }
        f[i] = Math.max(nums[i] + dfs(i + 2), dfs(i + 1));
        return f[i];
    }
    return dfs(0);
}
```

```ts
function rob(nums: number[]): number {
    const n = nums.length;
    const dp = new Array(n + 1).fill(0);
    dp[1] = nums[0];
    for (let i = 2; i <= n; ++i) {
        dp[i] = Math.max(nums[i - 1] + dp[i - 2], dp[i - 1]);
    }
    return dp[n];
}
```

```ts
function rob(nums: number[]): number {
    const dp = [0, 0];
    for (const num of nums) {
        [dp[0], dp[1]] = [dp[1], Math.max(dp[1], dp[0] + num)];
    }
    return dp[1];
}
```

### **Rust**

```rust
impl Solution {
    pub fn rob(nums: Vec<i32>) -> i32 {
        let mut dp = [0, 0];
        for num in nums {
            dp = [dp[1], dp[1].max(dp[0] + num)]
        }
        dp[1]
    }
}
```

### **...**

```

```

<!-- tabs:end -->
