---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4034.Minimum%20Bishop%20Moves%20to%20Reach%20Target/README.md
rating: 1244
source: 第 190 场双周赛 Q1
---

<!-- problem:start -->

# [4034. 象到达目标格子的最少移动步数](https://leetcode.cn/problems/minimum-bishop-moves-to-reach-target)

[English Version](/solution/4000-4099/4034.Minimum%20Bishop%20Moves%20to%20Reach%20Target/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <code>8 x 8</code> 的棋盘，行和列的<strong>下标从 1 开始</strong>。</p>

<p>给你一个数组 <code>source = [sr, sc]</code>，表示<strong> </strong><strong>象&nbsp;</strong>的起始位置，以及一个数组 <code>target = [tr, tc]</code>。在一步移动中，象可以在棋盘范围内沿着单个&nbsp;<strong>对角线&nbsp;</strong>方向移动任意数量的格子。</p>

<p>返回象 <strong>恰好&nbsp;</strong>到达&nbsp;<code>target</code> 位置所需的&nbsp;<strong>最少&nbsp;</strong>移动次数。如果它永远无法到达 <code>target</code>，则返回 -1。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">source = [8,1], target = [1,8]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4034.Minimum%20Bishop%20Moves%20to%20Reach%20Target/images/image.png" style="width: 300px; height: 307px;" /></p>

<p>一步对角线移动即可将象直接从 <code>(8, 1)</code> 送达 <code>(1, 8)</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">source = [4,2], target = [1,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4034.Minimum%20Bishop%20Moves%20to%20Reach%20Target/images/screenshot-2026-07-23-at-23625am.png" style="width: 300px; height: 305px;" /></p>

<p>象从 <code>(4, 2)</code> 移动到 <code>(3, 1)</code>，然后再从 <code>(3, 1)</code> 移动到 <code>(1, 3)</code>，经过 2 步移动到达目标位置。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">source = [1,1], target = [3,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>无论进行多少次对角线移动，从 <code>(1, 1)</code> 出发的象都永远无法到达 <code>(3, 4)</code>。因此，答案是 -1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>source.length == target.length == 2</code></li>
	<li><code>1 &lt;= sr, sc, tr, tc &lt;= 8</code></li>
	<li><code>source != target</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分类讨论

象每次只能沿着对角线移动，一次移动会使行、列同时增减相同的数量，因此 $(r + c) \bmod 2$ 始终保持不变，即象只能停留在与起点同色的格子上。若 $(sr + sc)$ 与 $(tr + tc)$ 奇偶性不同，象永远无法到达目标，返回 $-1$。

否则，若起点与终点位于同一条对角线上，即 $|sr - tr| = |sc - tc|$，那么一步即可到达，返回 $1$。

其余情况下，两个格子同色但不共线，题目保证 $\textit{source} \neq \textit{target}$，而 $8 \times 8$ 棋盘上任意两个同色格子之间都存在一个可以中转的格子，因此答案为 $2$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

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
