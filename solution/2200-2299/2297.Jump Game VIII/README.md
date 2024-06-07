---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2297.Jump%20Game%20VIII/README.md
tags:
    - 栈
    - 图
    - 数组
    - 动态规划
    - 最短路
    - 单调栈
---

<!-- problem:start -->

# [2297. 跳跃游戏 VIII 🔒](https://leetcode.cn/problems/jump-game-viii)

[English Version](/solution/2200-2299/2297.Jump%20Game%20VIII/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为 n 的下标从 <strong>0</strong>&nbsp;开始的整数数组 <code>nums</code>。初始位置为下标 <code>0</code>。当 <code>i &lt; j</code> 时，你可以从下标 <code>i</code> 跳转到下标 <code>j</code>:</p>

<ul>
	<li>对于在&nbsp;<code>i &lt; k &lt; j</code>&nbsp;范围内的所有下标 <code>k</code> 有&nbsp;<code>nums[i] &lt;= nums[j]</code> 和&nbsp;<code>nums[k] &lt; nums[i]</code> , 或者</li>
	<li>对于在&nbsp;<code>i &lt; k &lt; j</code>&nbsp;范围内的所有下标 <code>k</code>&nbsp;有&nbsp;<code>nums[i] &gt; nums[j]</code> 和&nbsp;<code>nums[k] &gt;= nums[i]</code>&nbsp;。</li>
</ul>

<p>你还得到了一个长度为 <code>n</code> 的整数数组 <code>costs</code>，其中 <code>costs[i]</code> 表示跳转<strong>到</strong>下标 <code>i</code> 的代价。</p>

<p>返回<em>跳转到</em>下标 <em><code>n - 1</code> 的最小代价。</em></p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [3,2,4,4,1], costs = [3,7,6,4,2]
<strong>输出:</strong> 8
<strong>解释:</strong> 从下标 0 开始。
- 以 costs[2]= 6 的代价跳转到下标 2。
- 以 costs[4]= 2 的代价跳转到下标 4。
总代价是 8。可以证明，8 是所需的最小代价。
另外两个可能的路径是:下标 0 -&gt; 1 -&gt; 4 和下标 0 -&gt; 2 -&gt; 3 -&gt; 4。
它们的总代价分别为9和12。
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> nums = [0,1,2], costs = [1,1,1]
<strong>输出:</strong> 2
<strong>解释:</strong> 从下标 0 开始。
- 以 costs[1] = 1 的代价跳转到下标 1。
- 以 costs[2] = 1 的代价跳转到下标 2。
总代价是 2。注意您不能直接从下标 0 跳转到下标 2，因为 nums[0] &lt;= nums[1]。
</pre>

<p>&nbsp;</p>

<p><strong>解释:</strong></p>

<ul>
	<li><code>n == nums.length == costs.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i], costs[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：单调栈 + 动态规划

根据题目描述，我们实际上需要找到 $nums[i]$ 的下一个大于等于 $nums[i]$ 的位置 $j$，以及下一个小于 $nums[i]$ 的位置 $j$。我们利用单调栈可以在 $O(n)$ 的时间内找到这两个位置，然后构建邻接表 $g$，其中 $g[i]$ 表示下标 $i$ 可以跳转到的下标。

然后我们使用动态规划求解最小代价。设 $f[i]$ 表示跳转到下标 $i$ 的最小代价，初始时 $f[0] = 0$，其余 $f[i] = \infty$。我们从小到大枚举下标 $i$，对于每个 $i$，我们枚举 $g[i]$ 中的每个下标 $j$，进行状态转移 $f[j] = \min(f[j], f[i] + costs[j])$。答案为 $f[n - 1]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(self, nums: List[int], costs: List[int]) -> int:
        n = len(nums)
        g = defaultdict(list)
        stk = []
        for i in range(n - 1, -1, -1):
            while stk and nums[stk[-1]] < nums[i]:
                stk.pop()
            if stk:
                g[i].append(stk[-1])
            stk.append(i)

        stk = []
        for i in range(n - 1, -1, -1):
            while stk and nums[stk[-1]] >= nums[i]:
                stk.pop()
            if stk:
                g[i].append(stk[-1])
            stk.append(i)

        f = [inf] * n
        f[0] = 0
        for i in range(n):
            for j in g[i]:
                f[j] = min(f[j], f[i] + costs[j])
        return f[n - 1]
```

#### Java

```java
class Solution {
    public long minCost(int[] nums, int[] costs) {
        int n = nums.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && nums[stk.peek()] < nums[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                g[i].add(stk.peek());
            }
            stk.push(i);
        }
        stk.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && nums[stk.peek()] >= nums[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                g[i].add(stk.peek());
            }
            stk.push(i);
        }
        long[] f = new long[n];
        Arrays.fill(f, 1L << 60);
        f[0] = 0;
        for (int i = 0; i < n; ++i) {
            for (int j : g[i]) {
                f[j] = Math.min(f[j], f[i] + costs[j]);
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
    long long minCost(vector<int>& nums, vector<int>& costs) {
        int n = nums.size();
        vector<int> g[n];
        stack<int> stk;
        for (int i = n - 1; ~i; --i) {
            while (!stk.empty() && nums[stk.top()] < nums[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                g[i].push_back(stk.top());
            }
            stk.push(i);
        }
        stk = stack<int>();
        for (int i = n - 1; ~i; --i) {
            while (!stk.empty() && nums[stk.top()] >= nums[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                g[i].push_back(stk.top());
            }
            stk.push(i);
        }
        vector<long long> f(n, 1e18);
        f[0] = 0;
        for (int i = 0; i < n; ++i) {
            for (int j : g[i]) {
                f[j] = min(f[j], f[i] + costs[j]);
            }
        }
        return f[n - 1];
    }
};
```

#### Go

```go
func minCost(nums []int, costs []int) int64 {
	n := len(nums)
	g := make([][]int, n)
	stk := []int{}
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && nums[stk[len(stk)-1]] < nums[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			g[i] = append(g[i], stk[len(stk)-1])
		}
		stk = append(stk, i)
	}
	stk = []int{}
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && nums[stk[len(stk)-1]] >= nums[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			g[i] = append(g[i], stk[len(stk)-1])
		}
		stk = append(stk, i)
	}
	f := make([]int64, n)
	for i := 1; i < n; i++ {
		f[i] = math.MaxInt64
	}
	for i := 0; i < n; i++ {
		for _, j := range g[i] {
			f[j] = min(f[j], f[i]+int64(costs[j]))
		}
	}
	return f[n-1]
}
```

#### TypeScript

```ts
function minCost(nums: number[], costs: number[]): number {
    const n = nums.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    const stk: number[] = [];
    for (let i = n - 1; i >= 0; --i) {
        while (stk.length && nums[stk[stk.length - 1]] < nums[i]) {
            stk.pop();
        }
        if (stk.length) {
            g[i].push(stk[stk.length - 1]);
        }
        stk.push(i);
    }
    stk.length = 0;
    for (let i = n - 1; i >= 0; --i) {
        while (stk.length && nums[stk[stk.length - 1]] >= nums[i]) {
            stk.pop();
        }
        if (stk.length) {
            g[i].push(stk[stk.length - 1]);
        }
        stk.push(i);
    }
    const f: number[] = Array.from({ length: n }, () => Infinity);
    f[0] = 0;
    for (let i = 0; i < n; ++i) {
        for (const j of g[i]) {
            f[j] = Math.min(f[j], f[i] + costs[j]);
        }
    }
    return f[n - 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
