---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3996.Even%20Number%20of%20Knight%20Moves/README_EN.md
---

<!-- problem:start -->

# [3996. Even Number of Knight Moves](https://leetcode.com/problems/even-number-of-knight-moves)

[中文文档](/solution/3900-3999/3996.Even%20Number%20of%20Knight%20Moves/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>start</code> and <code>target</code>, where each array is of the form <code>[x, y]</code> representing a cell on a standard 8 x 8 chessboard.</p>

<p>Return <code>true</code> if a knight can move from <code>start</code> to <code>target</code> in an <strong>even</strong> number of moves. Otherwise, return <code>false</code>.</p>

<p><strong>Note:</strong> A valid knight move consists of moving two squares in one direction and one square perpendicular to it. The figure below illustrates all eight possible moves from a cell.</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3996.Even%20Number%20of%20Knight%20Moves/images/knight.png" style="height: 200px; width: 200px;" /></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">start = [1,1], target = [2,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>One possible sequence of moves is <code>(1, 1) -&gt; (3, 2) -&gt; (2, 4) -&gt; (4, 3) -&gt; (2, 2)</code>.</p>

<p>The knight reaches the target in 4 moves, which is even. Thus, the answer is <code>true</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">start = [4,5], target = [6,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<p>It is impossible to reach <code>target = [6, 6]</code> from <code>start = [4, 5]</code> in an even number of moves. Thus, the answer is <code>false</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>start.length == target.length == 2</code></li>
	<li><code>0 &lt;= start[i], target[i] &lt;= 7</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Parity

Each knight move has an offset of $(\pm 1, \pm 2)$ or $(\pm 2, \pm 1)$, so the change in the coordinate sum $x + y$ is always odd. In other words, every move flips the color of the square (black/white distinguished by $(x + y) \bmod 2$).

Therefore:

- After an even number of moves, the start and target have the same color;
- After an odd number of moves, the start and target have different colors.

On an $8 \times 8$ chessboard, a knight can reach any square, and any path to a same-color square must have even length. Hence, it suffices to check whether $(x + y) \bmod 2$ is equal for the start and the target.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canReach(self, start: list[int], target: list[int]) -> bool:
        return (start[0] + start[1]) % 2 == (target[0] + target[1]) % 2
```

#### Java

```java
class Solution {
    public boolean canReach(int[] start, int[] target) {
        return (start[0] + start[1]) % 2 == (target[0] + target[1]) % 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canReach(vector<int>& start, vector<int>& target) {
        return (start[0] + start[1]) % 2 == (target[0] + target[1]) % 2;
    }
};
```

#### Go

```go
func canReach(start []int, target []int) bool {
	return (start[0]+start[1])%2 == (target[0]+target[1])%2
}
```

#### TypeScript

```ts
function canReach(start: number[], target: number[]): boolean {
    return (start[0] + start[1]) % 2 === (target[0] + target[1]) % 2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
