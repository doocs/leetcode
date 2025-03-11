---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3477.Fruits%20Into%20Baskets%20II/README_EN.md
tags:
    - Segment Tree
    - Array
    - Binary Search
    - Simulation
---

<!-- problem:start -->

# [3477. Fruits Into Baskets II](https://leetcode.com/problems/fruits-into-baskets-ii)

[中文文档](/solution/3400-3499/3477.Fruits%20Into%20Baskets%20II/README.md)

## Description

<!-- description:start -->

<p>You are given two arrays of integers, <code>fruits</code> and <code>baskets</code>, each of length <code>n</code>, where <code>fruits[i]</code> represents the <strong>quantity</strong> of the <code>i<sup>th</sup></code> type of fruit, and <code>baskets[j]</code> represents the <strong>capacity</strong> of the <code>j<sup>th</sup></code> basket.</p>

<p>From left to right, place the fruits according to these rules:</p>

<ul>
	<li>Each fruit type must be placed in the <strong>leftmost available basket</strong> with a capacity <strong>greater than or equal</strong> to the quantity of that fruit type.</li>
	<li>Each basket can hold <b>only one</b> type of fruit.</li>
	<li>If a fruit type <b>cannot be placed</b> in any basket, it remains <b>unplaced</b>.</li>
</ul>

<p>Return the number of fruit types that remain unplaced after all possible allocations are made.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">fruits = [4,2,5], baskets = [3,5,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>fruits[0] = 4</code> is placed in <code>baskets[1] = 5</code>.</li>
	<li><code>fruits[1] = 2</code> is placed in <code>baskets[0] = 3</code>.</li>
	<li><code>fruits[2] = 5</code> cannot be placed in <code>baskets[2] = 4</code>.</li>
</ul>

<p>Since one fruit type remains unplaced, we return 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">fruits = [3,6,1], baskets = [6,4,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>fruits[0] = 3</code> is placed in <code>baskets[0] = 6</code>.</li>
	<li><code>fruits[1] = 6</code> cannot be placed in <code>baskets[1] = 4</code> (insufficient capacity) but can be placed in the next available basket, <code>baskets[2] = 7</code>.</li>
	<li><code>fruits[2] = 1</code> is placed in <code>baskets[1] = 4</code>.</li>
</ul>

<p>Since all fruits are successfully placed, we return 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == fruits.length == baskets.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= fruits[i], baskets[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We use a boolean array $\textit{vis}$ of length $n$ to record the baskets that have already been used, and a variable $\textit{ans}$ to record the number of fruits that have not been placed, initially $\textit{ans} = n$.

Next, we traverse each fruit $x$. For the current fruit, we traverse all the baskets to find the first unused basket $i$ with a capacity greater than or equal to $x$. If found, we decrement $\textit{ans}$ by $1$.

After traversing, we return the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{fruits}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numOfUnplacedFruits(self, fruits: List[int], baskets: List[int]) -> int:
        n = len(fruits)
        vis = [False] * n
        ans = n
        for x in fruits:
            for i, y in enumerate(baskets):
                if y >= x and not vis[i]:
                    vis[i] = True
                    ans -= 1
                    break
        return ans
```

#### Java

```java
class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        boolean[] vis = new boolean[n];
        int ans = n;
        for (int x : fruits) {
            for (int i = 0; i < n; ++i) {
                if (baskets[i] >= x && !vis[i]) {
                    vis[i] = true;
                    --ans;
                    break;
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numOfUnplacedFruits(vector<int>& fruits, vector<int>& baskets) {
        int n = fruits.size();
        vector<bool> vis(n);
        int ans = n;
        for (int x : fruits) {
            for (int i = 0; i < n; ++i) {
                if (baskets[i] >= x && !vis[i]) {
                    vis[i] = true;
                    --ans;
                    break;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func numOfUnplacedFruits(fruits []int, baskets []int) int {
	n := len(fruits)
	ans := n
	vis := make([]bool, n)
	for _, x := range fruits {
		for i, y := range baskets {
			if y >= x && !vis[i] {
				vis[i] = true
				ans--
				break
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function numOfUnplacedFruits(fruits: number[], baskets: number[]): number {
    const n = fruits.length;
    const vis: boolean[] = Array(n).fill(false);
    let ans = n;
    for (const x of fruits) {
        for (let i = 0; i < n; ++i) {
            if (baskets[i] >= x && !vis[i]) {
                vis[i] = true;
                --ans;
                break;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
