---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0754.Reach%20a%20Number/README_EN.md
tags:
    - Math
    - Binary Search
---

<!-- problem:start -->

# [754. Reach a Number](https://leetcode.com/problems/reach-a-number)

[中文文档](/solution/0700-0799/0754.Reach%20a%20Number/README.md)

## Description

<!-- description:start -->

<p>You are standing at position <code>0</code> on an infinite number line. There is a destination at position <code>target</code>.</p>

<p>You can make some number of moves <code>numMoves</code> so that:</p>

<ul>
	<li>On each move, you can either go left or right.</li>
	<li>During the <code>i<sup>th</sup></code> move (starting from <code>i == 1</code> to <code>i == numMoves</code>), you take <code>i</code> steps in the chosen direction.</li>
</ul>

<p>Given the integer <code>target</code>, return <em>the <strong>minimum</strong> number of moves required (i.e., the minimum </em><code>numMoves</code><em>) to reach the destination</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong>
On the 1<sup>st</sup> move, we step from 0 to 1 (1 step).
On the 2<sup>nd</sup> move, we step from 1 to -1 (2 steps).
On the 3<sup>rd</sup> move, we step from -1 to 2 (3 steps).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong>
On the 1<sup>st</sup> move, we step from 0 to 1 (1 step).
On the 2<sup>nd</sup> move, we step from 1 to 3 (2 steps).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li>
	<li><code>target != 0</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematical Analysis

Due to symmetry, each time we can choose to move left or right, so we can take the absolute value of $\textit{target}$.

Define $s$ as the current position, and use the variable $k$ to record the number of moves. Initially, both $s$ and $k$ are $0$.

We keep adding to $s$ in a loop until $s \ge \textit{target}$ and $(s - \textit{target}) \bmod 2 = 0$. At this point, the number of moves $k$ is the answer, and we return it directly.

Why? Because if $s \ge \textit{target}$ and $(s - \textit{target}) \bmod 2 = 0$, we only need to change the sign of the positive integer $\frac{s - \textit{target}}{2}$ to negative, so that $s$ equals $\textit{target}$. Changing the sign of a positive integer essentially means changing the direction of the move, but the actual number of moves remains the same.

The time complexity is $O(\sqrt{\left | \textit{target} \right | })$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reachNumber(self, target: int) -> int:
        target = abs(target)
        s = k = 0
        while 1:
            if s >= target and (s - target) % 2 == 0:
                return k
            k += 1
            s += k
```

#### Java

```java
class Solution {
    public int reachNumber(int target) {
        target = Math.abs(target);
        int s = 0, k = 0;
        while (true) {
            if (s >= target && (s - target) % 2 == 0) {
                return k;
            }
            ++k;
            s += k;
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int reachNumber(int target) {
        target = abs(target);
        int s = 0, k = 0;
        while (1) {
            if (s >= target && (s - target) % 2 == 0) return k;
            ++k;
            s += k;
        }
    }
};
```

#### Go

```go
func reachNumber(target int) int {
	if target < 0 {
		target = -target
	}
	var s, k int
	for {
		if s >= target && (s-target)%2 == 0 {
			return k
		}
		k++
		s += k
	}
}
```

#### JavaScript

```js
/**
 * @param {number} target
 * @return {number}
 */
var reachNumber = function (target) {
    target = Math.abs(target);
    let [s, k] = [0, 0];
    while (1) {
        if (s >= target && (s - target) % 2 == 0) {
            return k;
        }
        ++k;
        s += k;
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
