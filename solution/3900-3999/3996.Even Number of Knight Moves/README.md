---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3996.Even%20Number%20of%20Knight%20Moves/README.md
---

<!-- problem:start -->

# [3996. 偶数次骑士移动](https://leetcode.cn/problems/even-number-of-knight-moves)

[English Version](/solution/3900-3999/3996.Even%20Number%20of%20Knight%20Moves/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数数组 <code>start</code> 和 <code>target</code>，每个数组的形式均为 <code>[x, y]</code>，表示标准 8 x 8 国际象棋棋盘上的一个格子。</p>

<p>如果骑士可以用<strong>&nbsp;偶数</strong>&nbsp;次移动从 <code>start</code> 到达 <code>target</code>，则返回 <code>true</code>；否则返回 <code>false</code>。</p>

<p><strong>注意：</strong>骑士的一次合法移动是：沿一个方向移动两格，再沿与其垂直的方向移动一格。下图展示了骑士从一个格子出发时所有 8 种可能的移动方式。</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3996.Even%20Number%20of%20Knight%20Moves/images/knight.png" style="height: 200px; width: 200px;" /></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">start = [1,1], target = [2,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<p>一种可行的移动序列为 <code>(1, 1) -&gt; (3, 2) -&gt; (2, 4) -&gt; (4, 3) -&gt; (2, 2)</code>。</p>

<p>骑士经过 4 次移动到达目标位置，4 是偶数。因此答案为 <code>true</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">start = [4,5], target = [6,6]</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<p>骑士无法用偶数次移动从 <code>start = [4, 5]</code> 到达 <code>target = [6, 6]</code>。因此答案为 <code>false</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>start.length == target.length == 2</code></li>
	<li><code>0 &lt;= start[i], target[i] &lt;= 7</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：奇偶性

骑士每次移动的偏移量为 $(\pm 1, \pm 2)$ 或 $(\pm 2, \pm 1)$，因此坐标和 $x + y$ 的变化量恒为奇数，即每次移动都会改变格子颜色（以 $(x + y) \bmod 2$ 区分黑白格）。

由此可得：

- 偶数次移动后，起点与终点颜色相同；
- 奇数次移动后，起点与终点颜色不同。

在 $8 \times 8$ 棋盘上，骑士可以到达任意格子，且到达同色格子的任意路径长度必为偶数。因此，只需判断起点与终点的 $(x + y) \bmod 2$ 是否相等即可。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

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
