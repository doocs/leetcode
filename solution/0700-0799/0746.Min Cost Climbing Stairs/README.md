---
comments: true
difficulty: 简单
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [746. 使用最小花费爬楼梯](https://leetcode.cn/problems/min-cost-climbing-stairs)

[English Version](/solution/0700-0799/0746.Min%20Cost%20Climbing%20Stairs/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>cost</code> ，其中 <code>cost[i]</code> 是从楼梯第 <code>i</code> 个台阶向上爬需要支付的费用。</p>

<p>一旦你支付此费用，即可选择向上爬 <strong>一个</strong> 或者 <strong>两个</strong> 台阶。</p>

<p>你可以选择从下标为 <code>0</code> 或下标为 <code>1</code> 的台阶开始爬楼梯。</p>

<p>请你计算并返回达到楼梯顶部的 <strong>最低</strong> 花费，即最后一步之后的那个位置（下标 <code>cost.length</code>）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>cost = [10,<u>15</u>,20]
<strong>输出：</strong>15
<strong>解释：</strong>你将从下标为 1 的台阶开始。
- 支付 15 ，向上爬两个台阶，到达楼梯顶部。
总花费为 15 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>cost = [<u>1</u>,100,<u>1</u>,1,<u>1</u>,100,<u>1</u>,<u>1</u>,100,<u>1</u>]
<strong>输出：</strong>6
<strong>解释：</strong>你将从下标为 0 的台阶开始。
- 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
- 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
- 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
- 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
- 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
- 支付 1 ，向上爬一个台阶，到达楼梯顶部。
总花费为 6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= cost.length &lt;= 1000</code></li>
	<li><code>0 &lt;= cost[i] &lt;= 999</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

<!-- thinking:start -->

> **思考**
>
> 可从下标 $0$ 或 $1$ 出发，每步跨一或两级并支付当前阶费用。 $n\le 1000$，朴素递归会重复计算同一台阶。
>
> 从 $i$ 出发的最小花费只依赖 $i+1$ 与 $i+2$，越过顶端代价为 $0$。记忆化后每个下标算一次。
>
> 答案为 $\min(dfs(0), dfs(1))$。时间与空间 $O(n)$。

<!-- thinking:end -->

我们设计一个函数 $\textit{dfs}(i)$，表示从第 $i$ 个阶梯开始爬楼梯所需要的最小花费。那么答案为 $\min(\textit{dfs}(0), \textit{dfs}(1))$。

函数 $\textit{dfs}(i)$ 的执行过程如下：

- 如果 $i \ge \textit{len(cost)}$，表示当前位置已经超过了楼梯顶部，不需要再爬楼梯，返回 $0$；
- 否则，我们可以选择爬 $1$ 级楼梯，花费为 $\textit{cost}[i]$，然后递归调用 $\textit{dfs}(i + 1)$；也可以选择爬 $2$ 级楼梯，花费为 $\textit{cost}[i]$，然后递归调用 $\textit{dfs}(i + 2)$；
- 返回两种方案中的最小花费。

为了避免重复计算，我们使用记忆化搜索的方法，将已经计算过的结果保存在数组或哈希表中。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{cost}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= len(cost):
                return 0
            return cost[i] + min(dfs(i + 1), dfs(i + 2))

        return min(dfs(0), dfs(1))
```

#### Java

```java
class Solution {
    private Integer[] f;
    private int[] cost;

    public int minCostClimbingStairs(int[] cost) {
        this.cost = cost;
        f = new Integer[cost.length];
        return Math.min(dfs(0), dfs(1));
    }

    private int dfs(int i) {
        if (i >= cost.length) {
            return 0;
        }
        if (f[i] == null) {
            f[i] = cost[i] + Math.min(dfs(i + 1), dfs(i + 2));
        }
        return f[i];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCostClimbingStairs(vector<int>& cost) {
        int n = cost.size();
        int f[n];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i) -> int {
            if (i >= n) {
                return 0;
            }
            if (f[i] < 0) {
                f[i] = cost[i] + min(dfs(i + 1), dfs(i + 2));
            }
            return f[i];
        };
        return min(dfs(0), dfs(1));
    }
};
```

#### Go

```go
func minCostClimbingStairs(cost []int) int {
	n := len(cost)
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] < 0 {
			f[i] = cost[i] + min(dfs(i+1), dfs(i+2))
		}
		return f[i]
	}
	return min(dfs(0), dfs(1))
}
```

#### TypeScript

```ts
function minCostClimbingStairs(cost: number[]): number {
    const n = cost.length;
    const f: number[] = Array(n).fill(-1);
    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i] < 0) {
            f[i] = cost[i] + Math.min(dfs(i + 1), dfs(i + 2));
        }
        return f[i];
    };
    return Math.min(dfs(0), dfs(1));
}
```

#### Rust

```rust
impl Solution {
    pub fn min_cost_climbing_stairs(cost: Vec<i32>) -> i32 {
        let n = cost.len();
        let mut f = vec![-1; n];

        fn dfs(i: usize, cost: &Vec<i32>, f: &mut Vec<i32>, n: usize) -> i32 {
            if i >= n {
                return 0;
            }
            if f[i] < 0 {
                let next1 = dfs(i + 1, cost, f, n);
                let next2 = dfs(i + 2, cost, f, n);
                f[i] = cost[i] + next1.min(next2);
            }
            f[i]
        }

        dfs(0, &cost, &mut f, n).min(dfs(1, &cost, &mut f, n))
    }
}
```

#### JavaScript

```js
function minCostClimbingStairs(cost) {
    const n = cost.length;
    const f = Array(n).fill(-1);
    const dfs = i => {
        if (i >= n) {
            return 0;
        }
        if (f[i] < 0) {
            f[i] = cost[i] + Math.min(dfs(i + 1), dfs(i + 2));
        }
        return f[i];
    };
    return Math.min(dfs(0), dfs(1));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划

<!-- thinking:start -->

> **思考**
>
> 方法一已经从楼顶向下填好离开每一级的花费。这里的 $f[i]$ 改成到达位置 $i$ 的最小花费，只依赖 $i-1$ 与 $i-2$，因此按 $i$ 从小到大填写，$f[n]$ 就是楼顶。

<!-- thinking:end -->

我们定义 $f[i]$ 表示到达第 $i$ 个阶梯所需要的最小花费，初始时 $f[0] = f[1] = 0$，答案即为 $f[n]$。

当 $i \ge 2$ 时，我们可以从第 $i - 1$ 个阶梯使用 $1$ 步直接到达第 $i$ 个阶梯，或者从第 $i - 2$ 个阶梯使用 $2$ 步到达第 $i$ 个阶梯，因此我们有状态转移方程：

$$
f[i] = \min(f[i - 1] + cost[i - 1], f[i - 2] + cost[i - 2])
$$

最终的答案即为 $f[n]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{cost}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        n = len(cost)
        f = [0] * (n + 1)
        for i in range(2, n + 1):
            f[i] = min(f[i - 2] + cost[i - 2], f[i - 1] + cost[i - 1])
        return f[n]
```

#### Java

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] f = new int[n + 1];
        for (int i = 2; i <= n; ++i) {
            f[i] = Math.min(f[i - 2] + cost[i - 2], f[i - 1] + cost[i - 1]);
        }
        return f[n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCostClimbingStairs(vector<int>& cost) {
        int n = cost.size();
        vector<int> f(n + 1);
        for (int i = 2; i <= n; ++i) {
            f[i] = min(f[i - 2] + cost[i - 2], f[i - 1] + cost[i - 1]);
        }
        return f[n];
    }
};
```

#### Go

```go
func minCostClimbingStairs(cost []int) int {
	n := len(cost)
	f := make([]int, n+1)
	for i := 2; i <= n; i++ {
		f[i] = min(f[i-1]+cost[i-1], f[i-2]+cost[i-2])
	}
	return f[n]
}
```

#### TypeScript

```ts
function minCostClimbingStairs(cost: number[]): number {
    const n = cost.length;
    const f: number[] = Array(n + 1).fill(0);
    for (let i = 2; i <= n; ++i) {
        f[i] = Math.min(f[i - 1] + cost[i - 1], f[i - 2] + cost[i - 2]);
    }
    return f[n];
}
```

#### Rust

```rust
impl Solution {
    pub fn min_cost_climbing_stairs(cost: Vec<i32>) -> i32 {
        let n = cost.len();
        let mut f = vec![0; n + 1];
        for i in 2..=n {
            f[i] = std::cmp::min(f[i - 2] + cost[i - 2], f[i - 1] + cost[i - 1]);
        }
        f[n]
    }
}
```

#### JavaScript

```js
function minCostClimbingStairs(cost) {
    const n = cost.length;
    const f = Array(n + 1).fill(0);
    for (let i = 2; i <= n; ++i) {
        f[i] = Math.min(f[i - 1] + cost[i - 1], f[i - 2] + cost[i - 2]);
    }
    return f[n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三：动态规划（空间优化）

<!-- thinking:start -->

> **思考**
>
> $f[i]$ 只依赖前两项，两个变量滚动即可，空间 $O(1)$。

<!-- thinking:end -->

我们注意到，状态转移方程中的 $f[i]$ 只和 $f[i - 1]$ 与 $f[i - 2]$ 有关，因此我们可以使用两个变量 $f$ 和 $g$ 交替地记录 $f[i - 2]$ 和 $f[i - 1]$ 的值，这样空间复杂度可以优化到 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        f = g = 0
        for i in range(2, len(cost) + 1):
            f, g = g, min(f + cost[i - 2], g + cost[i - 1])
        return g
```

#### Java

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int f = 0, g = 0;
        for (int i = 2; i <= cost.length; ++i) {
            int gg = Math.min(f + cost[i - 2], g + cost[i - 1]);
            f = g;
            g = gg;
        }
        return g;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCostClimbingStairs(vector<int>& cost) {
        int f = 0, g = 0;
        for (int i = 2; i <= cost.size(); ++i) {
            int gg = min(f + cost[i - 2], g + cost[i - 1]);
            f = g;
            g = gg;
        }
        return g;
    }
};
```

#### Go

```go
func minCostClimbingStairs(cost []int) int {
	n := len(cost)
	var f, g int
	for i := 2; i <= n; i++ {
		f, g = g, min(f+cost[i-2], g+cost[i-1])
	}
	return g
}
```

#### TypeScript

```ts
function minCostClimbingStairs(cost: number[]): number {
    let [f, g] = [0, 0];
    for (let i = 1; i < cost.length; ++i) {
        [f, g] = [g, Math.min(f + cost[i - 1], g + cost[i])];
    }
    return g;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_cost_climbing_stairs(cost: Vec<i32>) -> i32 {
        let (mut f, mut g) = (0, 0);
        for i in 2..=cost.len() {
            let gg = std::cmp::min(f + cost[i - 2], g + cost[i - 1]);
            f = g;
            g = gg;
        }
        g
    }
}
```

#### JavaScript

```js
function minCostClimbingStairs(cost) {
    let [f, g] = [0, 0];
    for (let i = 1; i < cost.length; ++i) {
        [f, g] = [g, Math.min(f + cost[i - 1], g + cost[i])];
    }
    return g;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法四：动态规划

<!-- thinking:start -->

> **思考**
>
> 展开每一步的一阶和两阶，路径数是指数级的。台阶数可以到 $1000$，而第一次递归总是走到下一阶，调用深度就是 $n$。离开台阶 $i$ 的最小花费只依赖 $i+1$ 和 $i+2$，越过顶端为 $0$。令 $f[i] = \textit{cost}[i] + \min(f[i+1], f[i+2])$，从 $i = n-1$ 降到 $0$ 填写。答案是 $\min(f[0], f[1])$。

<!-- thinking:end -->

我们令 $f[i]$ 表示从第 $i$ 个阶梯出发爬到楼顶的最小花费。当 $i \ge n$ 时，$f[i] = 0$。对于 $i < n$，

$$
f[i] = \textit{cost}[i] + \min(f[i + 1], f[i + 2])
$$

按 $i$ 从 $n - 1$ 递减到 $0$ 计算。答案为 $\min(f[0], f[1])$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{cost}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        n = len(cost)
        f = [0] * (n + 2)
        for i in range(n - 1, -1, -1):
            f[i] = cost[i] + min(f[i + 1], f[i + 2])
        return min(f[0], f[1])
```

#### Java

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] f = new int[n + 2];
        for (int i = n - 1; i >= 0; --i) {
            f[i] = cost[i] + Math.min(f[i + 1], f[i + 2]);
        }
        return Math.min(f[0], f[1]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCostClimbingStairs(vector<int>& cost) {
        int n = cost.size();
        vector<int> f(n + 2);
        for (int i = n - 1; i >= 0; --i) {
            f[i] = cost[i] + min(f[i + 1], f[i + 2]);
        }
        return min(f[0], f[1]);
    }
};
```

#### Go

```go
func minCostClimbingStairs(cost []int) int {
	n := len(cost)
	f := make([]int, n+2)
	for i := n - 1; i >= 0; i-- {
		f[i] = cost[i] + min(f[i+1], f[i+2])
	}
	return min(f[0], f[1])
}
```

#### TypeScript

```ts
function minCostClimbingStairs(cost: number[]): number {
    const n = cost.length;
    const f: number[] = Array(n + 2).fill(0);
    for (let i = n - 1; i >= 0; --i) {
        f[i] = cost[i] + Math.min(f[i + 1], f[i + 2]);
    }
    return Math.min(f[0], f[1]);
}
```

#### Rust

```rust
impl Solution {
    pub fn min_cost_climbing_stairs(cost: Vec<i32>) -> i32 {
        let n = cost.len();
        let mut f = vec![0; n + 2];
        for i in (0..n).rev() {
            f[i] = cost[i] + f[i + 1].min(f[i + 2]);
        }
        f[0].min(f[1])
    }
}
```

#### JavaScript

```js
function minCostClimbingStairs(cost) {
    const n = cost.length;
    const f = Array(n + 2).fill(0);
    for (let i = n - 1; i >= 0; --i) {
        f[i] = cost[i] + Math.min(f[i + 1], f[i + 2]);
    }
    return Math.min(f[0], f[1]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
