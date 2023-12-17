# [746. Min Cost Climbing Stairs](https://leetcode.com/problems/min-cost-climbing-stairs)

[中文文档](/solution/0700-0799/0746.Min%20Cost%20Climbing%20Stairs/README.md)

## Description

<p>You are given an integer array <code>cost</code> where <code>cost[i]</code> is the cost of <code>i<sup>th</sup></code> step on a staircase. Once you pay the cost, you can either climb one or two steps.</p>

<p>You can either start from the step with index <code>0</code>, or the step with index <code>1</code>.</p>

<p>Return <em>the minimum cost to reach the top of the floor</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> cost = [10,<u>15</u>,20]
<strong>Output:</strong> 15
<strong>Explanation:</strong> You will start at index 1.
- Pay 15 and climb two steps to reach the top.
The total cost is 15.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> cost = [<u>1</u>,100,<u>1</u>,1,<u>1</u>,100,<u>1</u>,<u>1</u>,100,<u>1</u>]
<strong>Output:</strong> 6
<strong>Explanation:</strong> You will start at index 0.
- Pay 1 and climb two steps to reach index 2.
- Pay 1 and climb two steps to reach index 4.
- Pay 1 and climb two steps to reach index 6.
- Pay 1 and climb one step to reach index 7.
- Pay 1 and climb two steps to reach index 9.
- Pay 1 and climb one step to reach the top.
The total cost is 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= cost.length &lt;= 1000</code></li>
	<li><code>0 &lt;= cost[i] &lt;= 999</code></li>
</ul>

## Solutions

**Solution 1: Dynamic Programming**

We define $f[i]$ as the minimum cost required to reach the $i$th step, initially $f[0] = f[1] = 0$. The answer is $f[n]$.

When $i \ge 2$, we can directly reach the $i$th step from the $(i - 1)$th step using $1$ step, or reach the $i$th step from the $(i - 2)$th step using $2$ steps. Therefore, we have the state transition equation:

$$
f[i] = \min(f[i - 1] + cost[i - 1], f[i - 2] + cost[i - 2])
$$

The final answer is $f[n]$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the `cost` array.

We notice that $f[i]$ in the state transition equation is only related to $f[i - 1]$ and $f[i - 2]$. Therefore, we can use two variables $f$ and $g$ to alternately record the values of $f[i - 2]$ and $f[i - 1]$, which optimizes the space complexity to $O(1)$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        n = len(cost)
        f = [0] * (n + 1)
        for i in range(2, n + 1):
            f[i] = min(f[i - 2] + cost[i - 2], f[i - 1] + cost[i - 1])
        return f[n]
```

```python
class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        f = g = 0
        for i in range(2, len(cost) + 1):
            f, g = g, min(f + cost[i - 2], g + cost[i - 1])
        return g
```

### **Java**

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

### **C++**

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

### **Go**

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

```go
func minCostClimbingStairs(cost []int) int {
	var f, g int
	for i := 2; i <= n; i++ {
		f, g = g, min(f+cost[i-2], g+cost[i-1])
	}
	return g
}
```

### **TypeScript**

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

```ts
function minCostClimbingStairs(cost: number[]): number {
    let [f, g] = [0, 0];
    for (let i = 2; i <= cost.length; ++i) {
        [f, g] = [g, Math.min(f + cost[i - 2], g + cost[i - 1])];
    }
    return g;
}
```

### **Rust**

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

### **...**

```

```

<!-- tabs:end -->
