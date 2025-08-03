---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2106.Maximum%20Fruits%20Harvested%20After%20at%20Most%20K%20Steps/README_EN.md
rating: 2062
source: Weekly Contest 271 Q4
tags:
    - Array
    - Binary Search
    - Prefix Sum
    - Sliding Window
---

<!-- problem:start -->

# [2106. Maximum Fruits Harvested After at Most K Steps](https://leetcode.com/problems/maximum-fruits-harvested-after-at-most-k-steps)

[中文文档](/solution/2100-2199/2106.Maximum%20Fruits%20Harvested%20After%20at%20Most%20K%20Steps/README.md)

## Description

<!-- description:start -->

<p>Fruits are available at some positions on an infinite x-axis. You are given a 2D integer array <code>fruits</code> where <code>fruits[i] = [position<sub>i</sub>, amount<sub>i</sub>]</code> depicts <code>amount<sub>i</sub></code> fruits at the position <code>position<sub>i</sub></code>. <code>fruits</code> is already <strong>sorted</strong> by <code>position<sub>i</sub></code> in <strong>ascending order</strong>, and each <code>position<sub>i</sub></code> is <strong>unique</strong>.</p>

<p>You are also given an integer <code>startPos</code> and an integer <code>k</code>. Initially, you are at the position <code>startPos</code>. From any position, you can either walk to the <strong>left or right</strong>. It takes <strong>one step</strong> to move <strong>one unit</strong> on the x-axis, and you can walk <strong>at most</strong> <code>k</code> steps in total. For every position you reach, you harvest all the fruits at that position, and the fruits will disappear from that position.</p>

<p>Return <em>the <strong>maximum total number</strong> of fruits you can harvest</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2106.Maximum%20Fruits%20Harvested%20After%20at%20Most%20K%20Steps/images/1.png" style="width: 472px; height: 115px;" />
<pre>
<strong>Input:</strong> fruits = [[2,8],[6,3],[8,6]], startPos = 5, k = 4
<strong>Output:</strong> 9
<strong>Explanation:</strong> 
The optimal way is to:
- Move right to position 6 and harvest 3 fruits
- Move right to position 8 and harvest 6 fruits
You moved 3 steps and harvested 3 + 6 = 9 fruits in total.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2106.Maximum%20Fruits%20Harvested%20After%20at%20Most%20K%20Steps/images/2.png" style="width: 512px; height: 129px;" />
<pre>
<strong>Input:</strong> fruits = [[0,9],[4,1],[5,7],[6,2],[7,4],[10,9]], startPos = 5, k = 4
<strong>Output:</strong> 14
<strong>Explanation:</strong> 
You can move at most k = 4 steps, so you cannot reach position 0 nor 10.
The optimal way is to:
- Harvest the 7 fruits at the starting position 5
- Move left to position 4 and harvest 1 fruit
- Move right to position 6 and harvest 2 fruits
- Move right to position 7 and harvest 4 fruits
You moved 1 + 3 = 4 steps and harvested 7 + 1 + 2 + 4 = 14 fruits in total.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2106.Maximum%20Fruits%20Harvested%20After%20at%20Most%20K%20Steps/images/3.png" style="width: 476px; height: 100px;" />
<pre>
<strong>Input:</strong> fruits = [[0,3],[6,4],[8,5]], startPos = 3, k = 2
<strong>Output:</strong> 0
<strong>Explanation:</strong>
You can move at most k = 2 steps and cannot reach any position with fruits.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= fruits.length &lt;= 10<sup>5</sup></code></li>
	<li><code>fruits[i].length == 2</code></li>
	<li><code>0 &lt;= startPos, position<sub>i</sub> &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>position<sub>i-1</sub> &lt; position<sub>i</sub></code> for any <code>i &gt; 0</code>&nbsp;(<strong>0-indexed</strong>)</li>
	<li><code>1 &lt;= amount<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= k &lt;= 2 * 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

Let's assume the movement range is $[l, r]$ and the starting position is $\textit{startPos}$. We need to calculate the minimum number of steps required. Based on the position of $\textit{startPos}$, we can divide this into three cases:

1. If $\textit{startPos} \leq l$, then we move right from $\textit{startPos}$ to $r$. The minimum number of steps is $r - \textit{startPos}$;
2. If $\textit{startPos} \geq r$, then we move left from $\textit{startPos}$ to $l$. The minimum number of steps is $\textit{startPos} - l$;
3. If $l < \textit{startPos} < r$, we can either move left from $\textit{startPos}$ to $l$ then right to $r$, or move right from $\textit{startPos}$ to $r$ then left to $l$. The minimum number of steps is $r - l + \min(\lvert \textit{startPos} - l \rvert, \lvert r - \textit{startPos} \rvert)$.

All three cases can be unified by the formula $r - l + \min(\lvert \textit{startPos} - l \rvert, \lvert r - \textit{startPos} \rvert)$.

Suppose we fix the right endpoint $r$ of the interval and move the left endpoint $l$ to the right. Let's see how the minimum number of steps changes:

1. If $\textit{startPos} \leq l$, as $l$ increases, the minimum number of steps remains unchanged.
2. If $\textit{startPos} > l$, as $l$ increases, the minimum number of steps decreases.

Therefore, as $l$ increases, the minimum number of steps is non-strictly monotonically decreasing. Based on this, we can use the two-pointer approach to find all qualifying maximum intervals, then take the one with the maximum total fruits among all qualifying intervals as the answer.

Specifically, we use two pointers $i$ and $j$ to point to the left and right indices of the interval, initially $i = j = 0$. We also use a variable $s$ to record the total number of fruits in the interval, initially $s = 0$.

Each time we include $j$ in the interval, then update $s = s + \textit{fruits}[j][1]$. If the minimum number of steps in the current interval $\textit{fruits}[j][0] - \textit{fruits}[i][0] + \min(\lvert \textit{startPos} - \textit{fruits}[i][0] \rvert, \lvert \textit{startPos} - \textit{fruits}[j][0] \rvert)$ is greater than $k$, we move $i$ to the right in a loop until $i > j$ or the minimum number of steps in the interval is less than or equal to $k$. At this point, we update the answer $\textit{ans} = \max(\textit{ans}, s)$. Continue moving $j$ until $j$ reaches the end of the array.

Finally, return the answer.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxTotalFruits(self, fruits: List[List[int]], startPos: int, k: int) -> int:
        ans = i = s = 0
        for j, (pj, fj) in enumerate(fruits):
            s += fj
            while (
                i <= j
                and pj
                - fruits[i][0]
                + min(abs(startPos - fruits[i][0]), abs(startPos - fruits[j][0]))
                > k
            ):
                s -= fruits[i][1]
                i += 1
            ans = max(ans, s)
        return ans
```

#### Java

```java
class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int ans = 0, s = 0;
        for (int i = 0, j = 0; j < fruits.length; ++j) {
            int pj = fruits[j][0], fj = fruits[j][1];
            s += fj;
            while (i <= j
                && pj - fruits[i][0]
                        + Math.min(Math.abs(startPos - fruits[i][0]), Math.abs(startPos - pj))
                    > k) {
                s -= fruits[i++][1];
            }
            ans = Math.max(ans, s);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxTotalFruits(vector<vector<int>>& fruits, int startPos, int k) {
        int ans = 0, s = 0;
        for (int i = 0, j = 0; j < fruits.size(); ++j) {
            int pj = fruits[j][0], fj = fruits[j][1];
            s += fj;
            while (i <= j && pj - fruits[i][0] + min(abs(startPos - fruits[i][0]), abs(startPos - pj)) > k) {
                s -= fruits[i++][1];
            }
            ans = max(ans, s);
        }
        return ans;
    }
};
```

#### Go

```go
func maxTotalFruits(fruits [][]int, startPos int, k int) (ans int) {
	var s, i int
	for j, f := range fruits {
		s += f[1]
		for i <= j && f[0]-fruits[i][0]+min(abs(startPos-fruits[i][0]), abs(startPos-f[0])) > k {
			s -= fruits[i][1]
			i += 1
		}
		ans = max(ans, s)
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function maxTotalFruits(fruits: number[][], startPos: number, k: number): number {
    let ans = 0;
    let s = 0;
    for (let i = 0, j = 0; j < fruits.length; ++j) {
        const [pj, fj] = fruits[j];
        s += fj;
        while (
            i <= j &&
            pj -
                fruits[i][0] +
                Math.min(Math.abs(startPos - fruits[i][0]), Math.abs(startPos - pj)) >
                k
        ) {
            s -= fruits[i++][1];
        }
        ans = Math.max(ans, s);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_total_fruits(fruits: Vec<Vec<i32>>, start_pos: i32, k: i32) -> i32 {
        let mut ans = 0;
        let mut s = 0;
        let mut i = 0;
        for j in 0..fruits.len() {
            let pj = fruits[j][0];
            let fj = fruits[j][1];
            s += fj;
            while i <= j && pj - fruits[i][0] + std::cmp::min((start_pos - fruits[i][0]).abs(), (start_pos - pj).abs()) > k {
                s -= fruits[i][1];
                i += 1;
            }
            ans = ans.max(s)
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
