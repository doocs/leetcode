# [2369. 检查数组是否存在有效划分](https://leetcode.cn/problems/check-if-there-is-a-valid-partition-for-the-array)

[English Version](/solution/2300-2399/2369.Check%20if%20There%20is%20a%20Valid%20Partition%20For%20The%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，你必须将数组划分为一个或多个 <strong>连续</strong> 子数组。</p>

<p>如果获得的这些子数组中每个都能满足下述条件<strong> 之一</strong> ，则可以称其为数组的一种 <strong>有效</strong> 划分：</p>

<ol>
	<li>子数组 <strong>恰</strong> 由 <code>2</code> 个相等元素组成，例如，子数组 <code>[2,2]</code> 。</li>
	<li>子数组 <strong>恰</strong> 由 <code>3</code> 个相等元素组成，例如，子数组 <code>[4,4,4]</code> 。</li>
	<li>子数组 <strong>恰</strong> 由 <code>3</code> 个连续递增元素组成，并且相邻元素之间的差值为 <code>1</code> 。例如，子数组 <code>[3,4,5]</code> ，但是子数组 <code>[1,3,5]</code> 不符合要求。</li>
</ol>

<p>如果数组 <strong>至少</strong> 存在一种有效划分，返回 <code>true</code><em> </em>，否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,4,4,5,6]
<strong>输出：</strong>true
<strong>解释：</strong>数组可以划分成子数组 [4,4] 和 [4,5,6] 。
这是一种有效划分，所以返回 true 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1,2]
<strong>输出：</strong>false
<strong>解释：</strong>该数组不存在有效划分。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

$dfs(i)$ 表示从数组从下标 $i$ 开始到结尾，是否至少存在一个有效的划分。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

**方法二：动态规划**

设 $dp[i]$ 表示数组前 $i$ 个元素是否至少存在一个有效的划分。初始时 $dp[0]=true$, $dp[1]=false$。

根据题意，当 $i \ge 2$ 时，有

$$
dp[i] = \text{OR}
\begin{cases}
dp[i-2]\ \text{AND}\ \textit{nums}[i-1] = \textit{nums}[i-2],&i>1\\
dp[i-3]\ \text{AND}\ \textit{nums}[i-1] = \textit{nums}[i-2] = \textit{nums}[i-3],&i>2\\
dp[i-3]\ \text{AND}\ \textit{nums}[i-1] = \textit{nums}[i-2]+1 = \textit{nums}[i-3]+2,&i>2
\end{cases}
$$

答案为 $dp[n]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def validPartition(self, nums: List[int]) -> bool:
        @cache
        def dfs(i):
            if i == n:
                return True
            res = False
            if i < n - 1 and nums[i] == nums[i + 1]:
                res = res or dfs(i + 2)
            if i < n - 2 and nums[i] == nums[i + 1] and nums[i + 1] == nums[i + 2]:
                res = res or dfs(i + 3)
            if (
                i < n - 2
                and nums[i + 1] - nums[i] == 1
                and nums[i + 2] - nums[i + 1] == 1
            ):
                res = res or dfs(i + 3)
            return res

        n = len(nums)
        return dfs(0)
```

```python
class Solution:
    def validPartition(self, nums: List[int]) -> bool:
        n = len(nums)
        dp = [False] * (n + 1)
        dp[0] = True
        for i in range(2, n + 1):
            if nums[i - 1] == nums[i - 2]:
                dp[i] = dp[i] or dp[i - 2]
            if i > 2 and nums[i - 1] == nums[i - 2] == nums[i - 3]:
                dp[i] = dp[i] or dp[i - 3]
            if i > 2 and nums[i - 1] - nums[i - 2] == 1 and nums[i - 2] - nums[i - 3] == 1:
                dp[i] = dp[i] or dp[i - 3]
        return dp[-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int n;
    private int[] f;
    private int[] nums;

    public boolean validPartition(int[] nums) {
        this.nums = nums;
        n = nums.length;
        f = new int[n];
        Arrays.fill(f, -1);
        return dfs(0);
    }

    private boolean dfs(int i) {
        if (i == n) {
            return true;
        }
        if (f[i] != -1) {
            return f[i] == 1;
        }
        boolean res = false;
        if (i < n - 1 && nums[i] == nums[i + 1]) {
            res = res || dfs(i + 2);
        }
        if (i < n - 2 && nums[i] == nums[i + 1] && nums[i + 1] == nums[i + 2]) {
            res = res || dfs(i + 3);
        }
        if (i < n - 2 && nums[i + 1] - nums[i] == 1 && nums[i + 2] - nums[i + 1] == 1) {
            res = res || dfs(i + 3);
        }
        f[i] = res ? 1 : 0;
        return res;
    }
}
```

```java
class Solution {
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 2; i <= n; ++i) {
            if (nums[i - 1] == nums[i - 2]) {
                dp[i] = dp[i] || dp[i - 2];
            }
            if (i > 2 && nums[i - 1] == nums[i - 2] && nums[i - 2] == nums[i - 3]) {
                dp[i] = dp[i] || dp[i - 3];
            }
            if (i > 2 && nums[i - 1] - nums[i - 2] == 1 && nums[i - 2] - nums[i - 3] == 1) {
                dp[i] = dp[i] || dp[i - 3];
            }
        }
        return dp[n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> f;
    vector<int> nums;
    int n;

    bool validPartition(vector<int>& nums) {
        n = nums.size();
        this->nums = nums;
        f.assign(n, -1);
        return dfs(0);
    }

    bool dfs(int i) {
        if (i == n) return true;
        if (f[i] != -1) return f[i] == 1;
        bool res = false;
        if (i < n - 1 && nums[i] == nums[i + 1]) res = res || dfs(i + 2);
        if (i < n - 2 && nums[i] == nums[i + 1] && nums[i + 1] == nums[i + 2]) res = res || dfs(i + 3);
        if (i < n - 2 && nums[i + 1] - nums[i] == 1 && nums[i + 2] - nums[i + 1] == 1) res = res || dfs(i + 3);
        f[i] = res ? 1 : 0;
        return res;
    }
};
```

```cpp
class Solution {
public:
    bool validPartition(vector<int>& nums) {
        int n = nums.size();
        vector<bool> dp(n + 1);
        dp[0] = true;
        for (int i = 2; i <= n; ++i)
        {
            if (nums[i - 1] == nums[i - 2]) dp[i] = dp[i] || dp[i - 2];
            if (i > 2 && nums[i - 1] == nums[i - 2] && nums[i - 2] == nums[i - 3]) dp[i] = dp[i] || dp[i - 3];
            if (i > 2 && nums[i - 1] - nums[i - 2] == 1 && nums[i - 2] - nums[i - 3] == 1) dp[i] = dp[i] || dp[i - 3];
        }
        return dp[n];
    }
};
```

### **Go**

```go
func validPartition(nums []int) bool {
	n := len(nums)
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) bool
	dfs = func(i int) bool {
		if i == n {
			return true
		}
		if f[i] != -1 {
			return f[i] == 1
		}
		res := false
		f[i] = 0
		if i < n-1 && nums[i] == nums[i+1] {
			res = res || dfs(i+2)
		}
		if i < n-2 && nums[i] == nums[i+1] && nums[i+1] == nums[i+2] {
			res = res || dfs(i+3)
		}
		if i < n-2 && nums[i+1]-nums[i] == 1 && nums[i+2]-nums[i+1] == 1 {
			res = res || dfs(i+3)
		}
		if res {
			f[i] = 1
		}
		return res
	}
	return dfs(0)
}
```

```go
func validPartition(nums []int) bool {
	n := len(nums)
	dp := make([]bool, n+1)
	dp[0] = true
	for i := 2; i <= n; i++ {
		if nums[i-1] == nums[i-2] {
			dp[i] = dp[i] || dp[i-2]
		}
		if i > 2 && nums[i-1] == nums[i-2] && nums[i-2] == nums[i-3] {
			dp[i] = dp[i] || dp[i-3]
		}
		if i > 2 && nums[i-1]-nums[i-2] == 1 && nums[i-2]-nums[i-3] == 1 {
			dp[i] = dp[i] || dp[i-3]
		}
	}
	return dp[n]
}
```

### **TypeScript**

```ts
function validPartition(nums: number[]): boolean {
    const n = nums.length;
    const vis = new Array(n).fill(false);
    const queue = [0];
    while (queue.length !== 0) {
        const i = queue.shift() ?? 0;

        if (i === n) {
            return true;
        }

        if (!vis[i + 2] && i + 2 <= n && nums[i] === nums[i + 1]) {
            queue.push(i + 2);
            vis[i + 2] = true;
        }

        if (
            !vis[i + 3] &&
            i + 3 <= n &&
            ((nums[i] === nums[i + 1] && nums[i + 1] === nums[i + 2]) ||
                (nums[i] === nums[i + 1] - 1 &&
                    nums[i + 1] === nums[i + 2] - 1))
        ) {
            queue.push(i + 3);
            vis[i + 3] = true;
        }
    }
    return false;
}
```

```ts
function validPartition(nums: number[]): boolean {
    const n = nums.length;
    const dp = new Array(n + 1).fill(false);
    dp[0] = true;
    for (let i = 2; i <= n; ++i) {
        if (nums[i - 1] == nums[i - 2]) {
            dp[i] = dp[i] || dp[i - 2];
        }
        if (i > 2 && nums[i - 1] == nums[i - 2] && nums[i - 2] == nums[i - 3]) {
            dp[i] = dp[i] || dp[i - 3];
        }
        if (
            i > 2 &&
            nums[i - 1] - nums[i - 2] == 1 &&
            nums[i - 2] - nums[i - 3] == 1
        ) {
            dp[i] = dp[i] || dp[i - 3];
        }
    }
    return dp[n];
}
```

### **...**

```

```

<!-- tabs:end -->
