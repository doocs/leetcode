---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3259.Maximum%20Energy%20Boost%20From%20Two%20Drinks/README_EN.md
---

<!-- problem:start -->

# [3259. Maximum Energy Boost From Two Drinks](https://leetcode.com/problems/maximum-energy-boost-from-two-drinks)

[中文文档](/solution/3200-3299/3259.Maximum%20Energy%20Boost%20From%20Two%20Drinks/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>energyDrinkA</code> and <code>energyDrinkB</code> of the same length <code>n</code> by a futuristic sports scientist. These arrays represent the energy boosts per hour provided by two different energy drinks, A and B, respectively.</p>

<p>You want to <em>maximize</em> your total energy boost by drinking one energy drink <em>per hour</em>. However, if you want to switch from consuming one energy drink to the other, you need to wait for <em>one hour</em> to cleanse your system (meaning you won&#39;t get any energy boost in that hour).</p>

<p>Return the <strong>maximum</strong> total energy boost you can gain in the next <code>n</code> hours.</p>

<p><strong>Note</strong> that you can start consuming <em>either</em> of the two energy drinks.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> energyDrinkA<span class="example-io"> = [1,3,1], </span>energyDrinkB<span class="example-io"> = [3,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>To gain an energy boost of 5, drink only the energy drink A (or only B).</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> energyDrinkA<span class="example-io"> = [4,1,1], </span>energyDrinkB<span class="example-io"> = [1,1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p>To gain an energy boost of 7:</p>

<ul>
	<li>Drink the energy drink A for the first hour.</li>
	<li>Switch to the energy drink B and we lose the energy boost of the second hour.</li>
	<li>Gain the energy boost of the drink B in the third hour.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == energyDrinkA.length == energyDrinkB.length</code></li>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= energyDrinkA[i], energyDrinkB[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][0]$ to represent the maximum boost energy obtained by choosing energy drink A at the $i$-th hour, and $f[i][1]$ to represent the maximum boost energy obtained by choosing energy drink B at the $i$-th hour. Initially, $f[0][0] = \textit{energyDrinkA}[0]$, $f[0][1] = \textit{energyDrinkB}[0]$. The answer is $\max(f[n - 1][0], f[n - 1][1])$.

For $i > 0$, we have the following state transition equations:

$$
\begin{aligned}
f[i][0] & = \max(f[i - 1][0] + \textit{energyDrinkA}[i], f[i - 1][1]) \\
f[i][1] & = \max(f[i - 1][1] + \textit{energyDrinkB}[i], f[i - 1][0])
\end{aligned}
$$

Finally, return $\max(f[n - 1][0], f[n - 1][1])$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxEnergyBoost(self, energyDrinkA: List[int], energyDrinkB: List[int]) -> int:
        n = len(energyDrinkA)
        f = [[0] * 2 for _ in range(n)]
        f[0][0] = energyDrinkA[0]
        f[0][1] = energyDrinkB[0]
        for i in range(1, n):
            f[i][0] = max(f[i - 1][0] + energyDrinkA[i], f[i - 1][1])
            f[i][1] = max(f[i - 1][1] + energyDrinkB[i], f[i - 1][0])
        return max(f[n - 1])
```

#### Java

```java
class Solution {
    public long maxEnergyBoost(int[] energyDrinkA, int[] energyDrinkB) {
        int n = energyDrinkA.length;
        long[][] f = new long[n][2];
        f[0][0] = energyDrinkA[0];
        f[0][1] = energyDrinkB[0];
        for (int i = 1; i < n; ++i) {
            f[i][0] = Math.max(f[i - 1][0] + energyDrinkA[i], f[i - 1][1]);
            f[i][1] = Math.max(f[i - 1][1] + energyDrinkB[i], f[i - 1][0]);
        }
        return Math.max(f[n - 1][0], f[n - 1][1]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxEnergyBoost(vector<int>& energyDrinkA, vector<int>& energyDrinkB) {
        int n = energyDrinkA.size();
        vector<vector<long long>> f(n, vector<long long>(2));
        f[0][0] = energyDrinkA[0];
        f[0][1] = energyDrinkB[0];
        for (int i = 1; i < n; ++i) {
            f[i][0] = max(f[i - 1][0] + energyDrinkA[i], f[i - 1][1]);
            f[i][1] = max(f[i - 1][1] + energyDrinkB[i], f[i - 1][0]);
        }
        return max(f[n - 1][0], f[n - 1][1]);
    }
};
```

#### Go

```go
func maxEnergyBoost(energyDrinkA []int, energyDrinkB []int) int64 {
	n := len(energyDrinkA)
	f := make([][2]int64, n)
	f[0][0] = int64(energyDrinkA[0])
	f[0][1] = int64(energyDrinkB[0])
	for i := 1; i < n; i++ {
		f[i][0] = max(f[i-1][0]+int64(energyDrinkA[i]), f[i-1][1])
		f[i][1] = max(f[i-1][1]+int64(energyDrinkB[i]), f[i-1][0])
	}
	return max(f[n-1][0], f[n-1][1])
}
```

#### TypeScript

```ts
function maxEnergyBoost(energyDrinkA: number[], energyDrinkB: number[]): number {
    const n = energyDrinkA.length;
    const f: number[][] = Array.from({ length: n }, () => [0, 0]);
    f[0][0] = energyDrinkA[0];
    f[0][1] = energyDrinkB[0];
    for (let i = 1; i < n; i++) {
        f[i][0] = Math.max(f[i - 1][0] + energyDrinkA[i], f[i - 1][1]);
        f[i][1] = Math.max(f[i - 1][1] + energyDrinkB[i], f[i - 1][0]);
    }
    return Math.max(...f[n - 1]!);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
