---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3040.Maximum%20Number%20of%20Operations%20With%20the%20Same%20Score%20II/README.md
rating: 1708
tags:
    - 记忆化搜索
    - 数组
    - 动态规划
---

# [3040. 相同分数的最大操作数目 II](https://leetcode.cn/problems/maximum-number-of-operations-with-the-same-score-ii)

[English Version](/solution/3000-3099/3040.Maximum%20Number%20of%20Operations%20With%20the%20Same%20Score%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;，如果&nbsp;<code>nums</code>&nbsp;<strong>至少</strong>&nbsp;包含 <code>2</code>&nbsp;个元素，你可以执行以下操作中的&nbsp;<strong>任意</strong>&nbsp;一个：</p>

<ul>
	<li>选择 <code>nums</code>&nbsp;中最前面两个元素并且删除它们。</li>
	<li>选择 <code>nums</code>&nbsp;中最后两个元素并且删除它们。</li>
	<li>选择 <code>nums</code>&nbsp;中第一个和最后一个元素并且删除它们。</li>
</ul>

<p>一次操作的&nbsp;<strong>分数</strong>&nbsp;是被删除元素的和。</p>

<p>在确保<strong>&nbsp;所有操作分数相同</strong>&nbsp;的前提下，请你求出&nbsp;<strong>最多</strong>&nbsp;能进行多少次操作。</p>

<p>请你返回按照上述要求&nbsp;<strong>最多</strong>&nbsp;可以进行的操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [3,2,1,2,3,4]
<b>输出：</b>3
<b>解释：</b>我们执行以下操作：
- 删除前两个元素，分数为 3 + 2 = 5 ，nums = [1,2,3,4] 。
- 删除第一个元素和最后一个元素，分数为 1 + 4 = 5 ，nums = [2,3] 。
- 删除第一个元素和最后一个元素，分数为 2 + 3 = 5 ，nums = [] 。
由于 nums 为空，我们无法继续进行任何操作。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [3,2,6,1,4]
<b>输出：</b>2
<b>解释：</b>我们执行以下操作：
- 删除前两个元素，分数为 3 + 2 = 5 ，nums = [6,1,4] 。
- 删除最后两个元素，分数为 1 + 4 = 5 ，nums = [6] 。
至多进行 2 次操作。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## 解法

### 方法一：记忆化搜索

分数 $s$ 的取值有三种情况，分别是 $s = nums[0] + nums[1]$, $s = nums[0] + nums[n-1]$, $s = nums[n-1] + nums[n-2]$。我们可以针对这三种情况，分别进行记忆化搜索。

我们设计一个函数 $dfs(i, j)$，表示在分数为 $s$ 的情况下，从下标 $i$ 到下标 $j$ 的最大操作次数。函数 $dfs(i, j)$ 的执行过程如下：

-   如果 $j - i < 1$，表示区间 $[i, j]$ 的长度小于 $2$，无法进行任何操作，返回 $0$。
-   如果 $nums[i] + nums[i+1] = s$，表示可以删除下标 $i$ 和下标 $i+1$ 的元素，此时最大操作次数为 $1 + dfs(i+2, j)$。
-   如果 $nums[i] + nums[j] = s$，表示可以删除下标 $i$ 和下标 $j$ 的元素，此时最大操作次数为 $1 + dfs(i+1, j-1)$。
-   如果 $nums[j-1] + nums[j] = s$，表示可以删除下标 $j-1$ 和下标 $j$ 的元素，此时最大操作次数为 $1 + dfs(i, j-2)$。
-   返回以上的最大值即可。

最后，我们分别计算三种情况的最大操作次数，取最大值返回即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maxOperations(self, nums: List[int]) -> int:
        @cache
        def dfs(i: int, j: int, s: int) -> int:
            if j - i < 1:
                return 0
            ans = 0
            if nums[i] + nums[i + 1] == s:
                ans = max(ans, 1 + dfs(i + 2, j, s))
            if nums[i] + nums[j] == s:
                ans = max(ans, 1 + dfs(i + 1, j - 1, s))
            if nums[j - 1] + nums[j] == s:
                ans = max(ans, 1 + dfs(i, j - 2, s))
            return ans

        n = len(nums)
        a = dfs(2, n - 1, nums[0] + nums[1])
        b = dfs(0, n - 3, nums[-1] + nums[-2])
        c = dfs(1, n - 2, nums[0] + nums[-1])
        return 1 + max(a, b, c)
```

```java
class Solution {
    private Integer[][] f;
    private int[] nums;
    private int s;
    private int n;

    public int maxOperations(int[] nums) {
        this.nums = nums;
        n = nums.length;
        int a = g(2, n - 1, nums[0] + nums[1]);
        int b = g(0, n - 3, nums[n - 2] + nums[n - 1]);
        int c = g(1, n - 2, nums[0] + nums[n - 1]);
        return 1 + Math.max(a, Math.max(b, c));
    }

    private int g(int i, int j, int s) {
        f = new Integer[n][n];
        this.s = s;
        return dfs(i, j);
    }

    private int dfs(int i, int j) {
        if (j - i < 1) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = 0;
        if (nums[i] + nums[i + 1] == s) {
            ans = Math.max(ans, 1 + dfs(i + 2, j));
        }
        if (nums[i] + nums[j] == s) {
            ans = Math.max(ans, 1 + dfs(i + 1, j - 1));
        }
        if (nums[j - 1] + nums[j] == s) {
            ans = Math.max(ans, 1 + dfs(i, j - 2));
        }
        return f[i][j] = ans;
    }
}
```

```cpp
class Solution {
public:
    int maxOperations(vector<int>& nums) {
        int n = nums.size();
        int f[n][n];
        auto g = [&](int i, int j, int s) -> int {
            memset(f, -1, sizeof(f));
            function<int(int, int)> dfs = [&](int i, int j) -> int {
                if (j - i < 1) {
                    return 0;
                }
                if (f[i][j] != -1) {
                    return f[i][j];
                }
                int ans = 0;
                if (nums[i] + nums[i + 1] == s) {
                    ans = max(ans, 1 + dfs(i + 2, j));
                }
                if (nums[i] + nums[j] == s) {
                    ans = max(ans, 1 + dfs(i + 1, j - 1));
                }
                if (nums[j - 1] + nums[j] == s) {
                    ans = max(ans, 1 + dfs(i, j - 2));
                }
                return f[i][j] = ans;
            };
            return dfs(i, j);
        };
        int a = g(2, n - 1, nums[0] + nums[1]);
        int b = g(0, n - 3, nums[n - 2] + nums[n - 1]);
        int c = g(1, n - 2, nums[0] + nums[n - 1]);
        return 1 + max({a, b, c});
    }
};
```

```go
func maxOperations(nums []int) int {
	n := len(nums)
	var g func(i, j, s int) int
	g = func(i, j, s int) int {
		f := make([][]int, n)
		for i := range f {
			f[i] = make([]int, n)
			for j := range f {
				f[i][j] = -1
			}
		}
		var dfs func(i, j int) int
		dfs = func(i, j int) int {
			if j-i < 1 {
				return 0
			}
			if f[i][j] != -1 {
				return f[i][j]
			}
			ans := 0
			if nums[i]+nums[i+1] == s {
				ans = max(ans, 1+dfs(i+2, j))
			}

			if nums[i]+nums[j] == s {
				ans = max(ans, 1+dfs(i+1, j-1))
			}

			if nums[j-1]+nums[j] == s {
				ans = max(ans, 1+dfs(i, j-2))
			}
			f[i][j] = ans
			return ans
		}
		return dfs(i, j)
	}
	a := g(2, n-1, nums[0]+nums[1])
	b := g(0, n-3, nums[n-1]+nums[n-2])
	c := g(1, n-2, nums[0]+nums[n-1])
	return 1 + max(a, b, c)
}
```

```ts
function maxOperations(nums: number[]): number {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n }, () => Array(n));
    const g = (i: number, j: number, s: number): number => {
        f.forEach(row => row.fill(-1));
        const dfs = (i: number, j: number): number => {
            if (j - i < 1) {
                return 0;
            }
            if (f[i][j] !== -1) {
                return f[i][j];
            }
            let ans = 0;
            if (nums[i] + nums[i + 1] === s) {
                ans = Math.max(ans, 1 + dfs(i + 2, j));
            }
            if (nums[i] + nums[j] === s) {
                ans = Math.max(ans, 1 + dfs(i + 1, j - 1));
            }
            if (nums[j - 1] + nums[j] === s) {
                ans = Math.max(ans, 1 + dfs(i, j - 2));
            }
            return (f[i][j] = ans);
        };
        return dfs(i, j);
    };
    const a = g(2, n - 1, nums[0] + nums[1]);
    const b = g(0, n - 3, nums[n - 2] + nums[n - 1]);
    const c = g(1, n - 2, nums[0] + nums[n - 1]);
    return 1 + Math.max(a, b, c);
}
```

<!-- tabs:end -->

<!-- end -->
