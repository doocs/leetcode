---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3647.Maximum%20Weight%20in%20Two%20Bags/README_EN.md
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3647. Maximum Weight in Two Bags 🔒](https://leetcode.com/problems/maximum-weight-in-two-bags)

[中文文档](/solution/3600-3699/3647.Maximum%20Weight%20in%20Two%20Bags/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>weights</code> and two integers <code>w1</code> and <code>w2</code> representing the <strong>maximum</strong> capacities of two bags.</p>

<p>Each item may be placed in <strong>at most</strong> one bag such that:</p>

<ul>
	<li>Bag 1 holds <strong>at most</strong> <code>w1</code> total weight.</li>
	<li>Bag 2 holds <strong>at most</strong> <code>w2</code> total weight.</li>
</ul>

<p>Return the <strong>maximum</strong> total weight that can be packed into the two bags.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">weights = [1,4,3,2], w1 = 5, w2 = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Bag 1: Place <code>weights[2] = 3</code> and <code>weights[3] = 2</code> as <code>3 + 2 = 5 &lt;= w1</code></li>
	<li>Bag 2: Place <code>weights[1] = 4</code> as <code>4 &lt;= w2</code></li>
	<li>Total weight: <code>5 + 4 = 9</code></li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">weights = [3,6,4,8], w1 = 9, w2 = 7</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Bag 1: Place <code>weights[3] = 8</code> as <code>8 &lt;= w1</code></li>
	<li>Bag 2: Place <code>weights[0] = 3</code> and <code>weights[2] = 4</code> as <code>3 + 4 = 7 &lt;= w2</code></li>
	<li>Total weight: <code>8 + 7 = 15</code></li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">weights = [5,7], w1 = 2, w2 = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>No weight fits in either bag, thus the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= weights.length &lt;= 100</code></li>
	<li><code>1 &lt;= weights[i] &lt;= 100</code></li>
	<li><code>1 &lt;= w1, w2 &lt;= 300</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j][k]$ to represent the maximum total weight when placing the first $i$ items into two bags, where bag 1 has a maximum capacity of $j$ and bag 2 has a maximum capacity of $k$. Initially, $f[0][j][k] = 0$, indicating that no items can be placed in the bags.

The state transition equation is:

$$
f[i][j][k] = \max(f[i-1][j][k], f[i-1][j-w_i][k], f[i-1][j][k-w_i]) \quad (w_i \leq j \text{ or } w_i \leq k)
$$

where $w_i$ represents the weight of the $i$-th item.

The final answer is $f[n][w1][w2]$, where $n$ is the number of items.

We notice that the state transition equation only depends on the previous layer's state, so we can compress the three-dimensional DP array into a two-dimensional DP array. When enumerating $j$ and $k$, we use reverse traversal.

Time complexity $O(n \times w1 \times w2)$, space complexity $O(w1 \times w2)$. Where $n$ is the length of the array $\textit{weights}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxWeight(self, weights: List[int], w1: int, w2: int) -> int:
        f = [[0] * (w2 + 1) for _ in range(w1 + 1)]
        max = lambda a, b: a if a > b else b
        for x in weights:
            for j in range(w1, -1, -1):
                for k in range(w2, -1, -1):
                    if x <= j:
                        f[j][k] = max(f[j][k], f[j - x][k] + x)
                    if x <= k:
                        f[j][k] = max(f[j][k], f[j][k - x] + x)
        return f[w1][w2]
```

#### Java

```java
class Solution {
    public int maxWeight(int[] weights, int w1, int w2) {
        int[][] f = new int[w1 + 1][w2 + 1];
        for (int x : weights) {
            for (int j = w1; j >= 0; --j) {
                for (int k = w2; k >= 0; --k) {
                    if (x <= j) {
                        f[j][k] = Math.max(f[j][k], f[j - x][k] + x);
                    }
                    if (x <= k) {
                        f[j][k] = Math.max(f[j][k], f[j][k - x] + x);
                    }
                }
            }
        }
        return f[w1][w2];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxWeight(vector<int>& weights, int w1, int w2) {
        vector<vector<int>> f(w1 + 1, vector<int>(w2 + 1));
        for (int x : weights) {
            for (int j = w1; j >= 0; --j) {
                for (int k = w2; k >= 0; --k) {
                    if (x <= j) {
                        f[j][k] = max(f[j][k], f[j - x][k] + x);
                    }
                    if (x <= k) {
                        f[j][k] = max(f[j][k], f[j][k - x] + x);
                    }
                }
            }
        }
        return f[w1][w2];
    }
};
```

#### Go

```go
func maxWeight(weights []int, w1 int, w2 int) int {
	f := make([][]int, w1+1)
	for i := range f {
		f[i] = make([]int, w2+1)
	}
	for _, x := range weights {
		for j := w1; j >= 0; j-- {
			for k := w2; k >= 0; k-- {
				if x <= j {
					f[j][k] = max(f[j][k], f[j-x][k]+x)
				}
				if x <= k {
					f[j][k] = max(f[j][k], f[j][k-x]+x)
				}
			}
		}
	}
	return f[w1][w2]
}
```

#### TypeScript

```ts
function maxWeight(weights: number[], w1: number, w2: number): number {
    const f: number[][] = Array.from({ length: w1 + 1 }, () => Array(w2 + 1).fill(0));
    for (const x of weights) {
        for (let j = w1; j >= 0; j--) {
            for (let k = w2; k >= 0; k--) {
                if (x <= j) {
                    f[j][k] = Math.max(f[j][k], f[j - x][k] + x);
                }
                if (x <= k) {
                    f[j][k] = Math.max(f[j][k], f[j][k - x] + x);
                }
            }
        }
    }
    return f[w1][w2];
}
```

#### Rust

```rust
impl Solution {
    pub fn max_weight(weights: Vec<i32>, w1: i32, w2: i32) -> i32 {
        let w1 = w1 as usize;
        let w2 = w2 as usize;
        let mut f = vec![vec![0; w2 + 1]; w1 + 1];
        for &x in &weights {
            let x = x as usize;
            for j in (0..=w1).rev() {
                for k in (0..=w2).rev() {
                    if x <= j {
                        f[j][k] = f[j][k].max(f[j - x][k] + x as i32);
                    }
                    if x <= k {
                        f[j][k] = f[j][k].max(f[j][k - x] + x as i32);
                    }
                }
            }
        }
        f[w1][w2]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
