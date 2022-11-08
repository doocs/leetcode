# [198. 打家劫舍](https://leetcode.cn/problems/house-robber)

[English Version](/solution/0100-0199/0198.House%20Robber/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，<strong>如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警</strong>。</p>

<p>给定一个代表每个房屋存放金额的非负整数数组，计算你<strong> 不触动警报装置的情况下 </strong>，一夜之内能够偷窃到的最高金额。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>[1,2,3,1]
<strong>输出：</strong>4
<strong>解释：</strong>偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>[2,7,9,3,1]
<strong>输出：</strong>12
<strong>解释：</strong>偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 100</code></li>
	<li><code>0 <= nums[i] <= 400</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

我们设计函数 $dfs(i)$ 表示从第 $i$ 间房屋开始偷窃，能偷窃到的最高金额。答案为 $dfs(0)$。

对于第 $i$ 间房屋，有偷窃和不偷窃两种选择。如果偷窃，那么下一间房屋就不能偷窃，偷窃总金额为当前房屋金额加上下下间房屋开始的偷窃最高金额，即 $nums[i] + dfs(i + 2)$。如果不偷窃，那么下一间房屋就可以偷窃，偷窃总金额为下一间房屋开始的偷窃最高金额，即 $dfs(i + 1)$。两种选择取最大值作为函数 $dfs(i)$ 的返回值。

我们可以使用记忆化搜索，避免重复计算。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为房屋数量。

**方法二：动态规划**

我们也可以将记忆化搜索改成动态规划。

定义 $dp[i]$ 表示偷窃前 $i$ 个房屋能得到的最高金额。答案为 $dp[n]$。

状态转移方程为 $dp[i] = max(dp[i - 1], dp[i - 2] + nums[i - 1])$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为房屋数量。

**方法三：动态规划（空间优化）**

注意到方法二中的状态转移方程只和 $dp[i - 1]$ 和 $dp[i - 2]$ 有关，因此我们可以只用两个变量来维护这两个状态，从而将空间复杂度优化到 $O(1)$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为房屋数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    public : int rob(vector<int>& nums) {
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
