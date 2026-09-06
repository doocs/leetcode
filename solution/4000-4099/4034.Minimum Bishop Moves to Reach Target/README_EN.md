---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4034.Minimum%20Bishop%20Moves%20to%20Reach%20Target/README_EN.md
rating: 1244
source: Biweekly Contest 190 Q1
---

<!-- problem:start -->

# [4034. Minimum Bishop Moves to Reach Target](https://leetcode.com/problems/minimum-bishop-moves-to-reach-target)

[中文文档](/solution/4000-4099/4034.Minimum%20Bishop%20Moves%20to%20Reach%20Target/README.md)

## Description

<!-- description:start -->

<p>There is an <code>8 x 8</code> empty chessboard with <strong>1-indexed</strong> rows and columns.</p>

<p>You are given an array <code>source = [sr, sc]</code> representing the starting position of a <strong>bishop</strong>, and an array <code>target = [tr, tc]</code> representing the target position.</p>

<p>In one move, the bishop travels one or more squares along a single <strong>diagonal</strong> direction, staying within the board.</p>

<p>Return the <strong>minimum</strong> number of moves for the bishop to land <strong>exactly</strong> on <code>target</code>. If it can never reach <code>target</code>, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">source = [8,1], target = [1,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p><strong>​​​​​​​</strong><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4034.Minimum%20Bishop%20Moves%20to%20Reach%20Target/images/image.png" style="width: 300px; height: 307px;" /></p>

<p>A single diagonal move takes the bishop straight from <code>(8, 1)</code> to <code>(1, 8)</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">source = [4,2], target = [1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4034.Minimum%20Bishop%20Moves%20to%20Reach%20Target/images/screenshot-2026-07-23-at-23625am.png" style="width: 300px; height: 305px;" /></p>

<p>The bishop moves from <code>(4, 2)</code> to <code>(3, 1)</code>, then from <code>(3, 1)</code> to <code>(1, 3)</code>, reaching the target in 2 moves.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">source = [1,1], target = [3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>No matter how many diagonal moves it makes, the bishop starting at <code>(1, 1)</code> can never land on <code>(3, 4)</code>. Thus, the answer is -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong>​​​​​​​</p>

<ul>
	<li><code>source.length == target.length == 2</code></li>
	<li><code>1 &lt;= sr, sc, tr, tc &lt;= 8</code></li>
	<li><code>source != target</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Case Analysis

A bishop only moves along diagonals, and each move changes the row and the column by the same amount, so $(r + c) \bmod 2$ never changes. In other words, the bishop can only stand on squares of the same color as its starting square. If $(sr + sc)$ and $(tr + tc)$ have different parities, the bishop can never reach the target, so we return $-1$.

Otherwise, if the source and the target lie on the same diagonal, i.e., $|sr - tr| = |sc - tc|$, a single move is enough, so we return $1$.

In all remaining cases, the two squares share the same color but are not on a common diagonal. Since $\textit{source} \neq \textit{target}$ is guaranteed and any two same-colored squares on an $8 \times 8$ board can be joined through some intermediate square, the answer is $2$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minBishopMoves(self, source: List[int], target: List[int]) -> int:
        sr, sc = source
        tr, tc = target
        if (sr + sc) % 2 != (tr + tc) % 2:
            return -1
        if abs(sr - tr) == abs(sc - tc):
            return 1
        return 2
```

#### Java

```java
class Solution {
    public int minBishopMoves(int[] source, int[] target) {
        int sr = source[0], sc = source[1];
        int tr = target[0], tc = target[1];
        if ((sr + sc) % 2 != (tr + tc) % 2) {
            return -1;
        }
        if (Math.abs(sr - tr) == Math.abs(sc - tc)) {
            return 1;
        }
        return 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minBishopMoves(vector<int>& source, vector<int>& target) {
        int sr = source[0], sc = source[1];
        int tr = target[0], tc = target[1];
        if ((sr + sc) % 2 != (tr + tc) % 2) {
            return -1;
        }
        if (abs(sr - tr) == abs(sc - tc)) {
            return 1;
        }
        return 2;
    }
};
```

#### Go

```go
func minBishopMoves(source []int, target []int) int {
	sr, sc := source[0], source[1]
	tr, tc := target[0], target[1]
	if (sr+sc)%2 != (tr+tc)%2 {
		return -1
	}
	if abs(sr-tr) == abs(sc-tc) {
		return 1
	}
	return 2
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
function minBishopMoves(source: number[], target: number[]): number {
    const [sr, sc] = source;
    const [tr, tc] = target;
    if ((sr + sc) % 2 !== (tr + tc) % 2) {
        return -1;
    }
    if (Math.abs(sr - tr) === Math.abs(sc - tc)) {
        return 1;
    }
    return 2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
