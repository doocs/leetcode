---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3205.Maximum%20Array%20Hopping%20Score%20I/README.md
tags:
    - 栈
    - 贪心
    - 数组
    - 动态规划
    - 单调栈
---

<!-- problem:start -->

# [3205. 最大数组跳跃得分 I 🔒](https://leetcode.cn/problems/maximum-array-hopping-score-i)

[English Version](/solution/3200-3299/3205.Maximum%20Array%20Hopping%20Score%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个数组&nbsp;<code>nums</code>，你必须从索引 0 开始跳跃，直到到达数组的最后一个元素，使得获取 <strong>最大</strong> 分数。</p>

<p>每一次 <strong>跳跃</strong> 中，你可以从下标&nbsp;<code>i</code>&nbsp;跳到一个&nbsp;<code>j &gt; i</code>&nbsp;的下标，并且可以得到&nbsp;<code>(j - i) * nums[j]</code>&nbsp;的分数。</p>

<p>返回你能够取得的最大分数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b></span><span class="example-io">nums = [1,5,8]</span></p>

<p><span class="example-io"><b>输出：</b>16</span></p>

<p><strong>解释：</strong></p>

<p>有两种可能的方法可以到达最后一个元素：</p>

<ul>
	<li><code>0 -&gt; 1 -&gt; 2</code> 得分为&nbsp;<code>(1 - 0) * 5 + (2 - 1) * 8 = 13</code>。</li>
	<li><code>0 -&gt; 2</code> 得分为&nbsp;<code>(2 - 0) * 8 =&nbsp;16</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [4,5,2,8,9,1,3]</span></p>

<p><span class="example-io"><b>输出：</b>42</span></p>

<p><strong>解释：</strong></p>

<p>我们可以按&nbsp;<code>0 -&gt; 4 -&gt; 6</code>&nbsp;进行跳跃，得分为&nbsp;<code>(4 - 0) * 9 + (6 - 4) * 3 = 42</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

<!-- thinking:start -->

> **思考**
>
> 从下标 $0$ 出发每次跳到右侧某下标 $j$，得分为 $(j-i)\times\textit{nums}[j]$，求最大总分。$n\le 10^3$，穷举跳跃序列的分支数为指数级，但 $O(n^2)$ 的状态数可以接受。
>
> 子问题只依赖起点 $i$：一旦到达 $i$，后续最优与如何到达无关。因此定义 $\textit{dfs}(i)$ 为从 $i$ 出发的最大得分，枚举 $j>i$ 取 $(j-i)\times\textit{nums}[j]+\textit{dfs}(j)$ 的最大值，并用记忆化避免重复。

<!-- thinking:end -->

我们设计一个函数 $\textit{dfs}(i)$，表示从下标 $i$ 出发，能够获得的最大分数。那么答案就是 $\textit{dfs}(0)$。

函数 $\textit{dfs}(i)$ 的执行过程如下：

我们枚举下一个跳跃的位置 $j$，那么从下标 $i$ 出发，能够获得的分数就是 $(j - i) \times \textit{nums}[j]$，加上从下标 $j$ 出发，能够获得的最大分数，总分数就是 $(j - i) \times \textit{nums}[j] + \textit{dfs}(j)$。我们枚举所有的 $j$，取分数的最大值即可。

为了避免重复计算，我们使用记忆化搜索的方法，将已经计算过的 $\textit{dfs}(i)$ 的值保存起来，下次直接返回即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, nums: List[int]) -> int:
        @cache
        def dfs(i: int) -> int:
            return max(
                [(j - i) * nums[j] + dfs(j) for j in range(i + 1, len(nums))] or [0]
            )

        return dfs(0)
```

#### Java

```java
class Solution {
    private Integer[] f;
    private int[] nums;
    private int n;

    public int maxScore(int[] nums) {
        n = nums.length;
        f = new Integer[n];
        this.nums = nums;
        return dfs(0);
    }

    private int dfs(int i) {
        if (f[i] != null) {
            return f[i];
        }
        f[i] = 0;
        for (int j = i + 1; j < n; ++j) {
            f[i] = Math.max(f[i], (j - i) * nums[j] + dfs(j));
        }
        return f[i];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxScore(vector<int>& nums) {
        int n = nums.size();
        vector<int> f(n);
        auto dfs = [&](this auto&& dfs, int i) -> int {
            if (f[i]) {
                return f[i];
            }
            for (int j = i + 1; j < n; ++j) {
                f[i] = max(f[i], (j - i) * nums[j] + dfs(j));
            }
            return f[i];
        };
        return dfs(0);
    }
};
```

#### Go

```go
func maxScore(nums []int) int {
	n := len(nums)
	f := make([]int, n)
	var dfs func(int) int
	dfs = func(i int) int {
		if f[i] > 0 {
			return f[i]
		}
		for j := i + 1; j < n; j++ {
			f[i] = max(f[i], (j-i)*nums[j]+dfs(j))
		}
		return f[i]
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function maxScore(nums: number[]): number {
    const n = nums.length;
    const f: number[] = Array(n).fill(0);
    const dfs = (i: number): number => {
        if (f[i]) {
            return f[i];
        }
        for (let j = i + 1; j < n; ++j) {
            f[i] = Math.max(f[i], (j - i) * nums[j] + dfs(j));
        }
        return f[i];
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划

<!-- thinking:start -->

> **思考**
>
> 方法一的转移已经是最优子结构，递归栈与哈希缓存并非必要。将同一递推改成自底向上：$f[j]$ 表示从 $0$ 走到 $j$ 的最大得分，对每个 $j$ 枚举前驱 $i<j$ 即可，时间仍为 $O(n^2)$，实现变为迭代。

<!-- thinking:end -->

我们可以将方法一中的记忆化搜索转换为动态规划。

定义 $f[j]$ 表示从下标 $0$ 出发，到下标 $j$ 结束，能够获得的最大分数。那么答案就是 $f[n - 1]$。

状态转移方程为：

$$
f[j] = \max_{0 \leq i < j} \{ f[i] + (j - i) \times \textit{nums}[j] \}
$$

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, nums: List[int]) -> int:
        n = len(nums)
        f = [0] * n
        for j in range(1, n):
            for i in range(j):
                f[j] = max(f[j], f[i] + (j - i) * nums[j])
        return f[n - 1]
```

#### Java

```java
class Solution {
    public int maxScore(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        for (int j = 1; j < n; ++j) {
            for (int i = 0; i < j; ++i) {
                f[j] = Math.max(f[j], f[i] + (j - i) * nums[j]);
            }
        }
        return f[n - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxScore(vector<int>& nums) {
        int n = nums.size();
        vector<int> f(n);
        for (int j = 1; j < n; ++j) {
            for (int i = 0; i < j; ++i) {
                f[j] = max(f[j], f[i] + (j - i) * nums[j]);
            }
        }
        return f[n - 1];
    }
};
```

#### Go

```go
func maxScore(nums []int) int {
	n := len(nums)
	f := make([]int, n)
	for j := 1; j < n; j++ {
		for i := 0; i < j; i++ {
			f[j] = max(f[j], f[i]+(j-i)*nums[j])
		}
	}
	return f[n-1]
}
```

#### TypeScript

```ts
function maxScore(nums: number[]): number {
    const n = nums.length;
    const f: number[] = Array(n).fill(0);
    for (let j = 1; j < n; ++j) {
        for (let i = 0; i < j; ++i) {
            f[j] = Math.max(f[j], f[i] + (j - i) * nums[j]);
        }
    }
    return f[n - 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三：单调栈

<!-- thinking:start -->

> **思考**
>
> 方法二仍花费平方时间。注意到从 $i$ 出发，若 $j$ 不是其后第一个不小于当前值的位置，则中间插入一次跳跃不会更优，最优路径只经过单调递减的「下一更大」下标。为此用单调栈扫出这条下标序列，再按相邻跳跃累加分数，时间降为线性。

<!-- thinking:end -->

我们观察发现，对于当前位置 $i$，我们应该跳到下一个值最大的位置 $j$，这样才能获得最大的分数。

因此，我们遍历数组 $\textit{nums}$，维护一个从栈底到栈顶单调递减的栈 $\textit{stk}$。对于当前遍历到的位置 $i$，如果栈顶元素对应的值小于等于 $\textit{nums}[i]$，我们就不断地弹出栈顶元素，直到栈为空或者栈顶元素对应的值大于 $\textit{nums}[i]$，然后将 $i$ 入栈。

然后，我们初始化答案 $\textit{ans}$ 和当前位置 $i = 0$，遍历栈中的元素，每次取出栈顶元素 $j$，更新答案 $\textit{ans} += \textit{nums}[j] \times (j - i)$，然后更新 $i = j$。

最后返回答案 $\textit{ans}$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, nums: List[int]) -> int:
        stk = []
        for i, x in enumerate(nums):
            while stk and nums[stk[-1]] <= x:
                stk.pop()
            stk.append(i)
        ans = i = 0
        for j in stk:
            ans += nums[j] * (j - i)
            i = j
        return ans
```

#### Java

```java
class Solution {
    public int maxScore(int[] nums) {
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < nums.length; ++i) {
            while (!stk.isEmpty() && nums[stk.peek()] <= nums[i]) {
                stk.pop();
            }
            stk.push(i);
        }
        int ans = 0, i = 0;
        while (!stk.isEmpty()) {
            int j = stk.pollLast();
            ans += (j - i) * nums[j];
            i = j;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxScore(vector<int>& nums) {
        vector<int> stk;
        for (int i = 0; i < nums.size(); ++i) {
            while (stk.size() && nums[stk.back()] <= nums[i]) {
                stk.pop_back();
            }
            stk.push_back(i);
        }
        int ans = 0, i = 0;
        for (int j : stk) {
            ans += (j - i) * nums[j];
            i = j;
        }
        return ans;
    }
};
```

#### Go

```go
func maxScore(nums []int) (ans int) {
	stk := []int{}
	for i, x := range nums {
		for len(stk) > 0 && nums[stk[len(stk)-1]] <= x {
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, i)
	}
	i := 0
	for _, j := range stk {
		ans += (j - i) * nums[j]
		i = j
	}
	return
}
```

#### TypeScript

```ts
function maxScore(nums: number[]): number {
    const stk: number[] = [];
    for (let i = 0; i < nums.length; ++i) {
        while (stk.length && nums[stk.at(-1)!] <= nums[i]) {
            stk.pop();
        }
        stk.push(i);
    }
    let ans = 0;
    let i = 0;
    for (const j of stk) {
        ans += (j - i) * nums[j];
        i = j;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
