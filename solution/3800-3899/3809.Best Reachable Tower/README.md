---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3809.Best%20Reachable%20Tower/README.md
rating: 1358
source: 第 174 场双周赛 Q1
---

<!-- problem:start -->

# [3809. 最好可到达的塔](https://leetcode.cn/problems/best-reachable-tower)

[English Version](/solution/3800-3899/3809.Best%20Reachable%20Tower/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二维整数数组 <code>towers</code>，其中 <code>towers[i] = [x<sub>i</sub>, y<sub>i</sub>, q<sub>i</sub>]</code> 表示第 <code>i</code> 座塔的坐标 <code>(x<sub>i</sub>, y<sub>i</sub>)</code> 和质量因子 <code>q<sub>i</sub></code>。</p>

<p>另外给你一个整数数组 <code>center = [cx, cy]</code> 表示你的位置，以及一个整数 <code>radius</code>。</p>

<p>如果一座塔与 <code>center</code> 之间的 <strong>曼哈顿距离</strong><strong>小于或等于</strong> <code>radius</code>，则称该塔是 <strong>可到达的</strong>。</p>

<p>在所有可到达的塔中：</p>

<ul>
	<li>返回质量因子 <strong>最大</strong> 的塔的坐标。</li>
	<li>如果存在并列的塔，返回坐标 <strong>字典序最小</strong> 的塔。如果没有塔是可到达的，返回 <code>[-1, -1]</code>。</li>
</ul>
两点 <code>(x<sub>i</sub>, y<sub>i</sub>)</code> 和 <code>(x<sub>j</sub>, y<sub>j</sub>)</code> 之间的 <strong>曼哈顿距离</strong> 为 <code>|x<sub>i</sub> - x<sub>j</sub>| + |y<sub>i</sub> - y<sub>j</sub>|</code>。

<p>坐标 <code>[x<sub>i</sub>, y<sub>i</sub>]</code> <strong>字典序小于</strong> <code>[x<sub>j</sub>, y<sub>j</sub>]</code> 是指：<code>x<sub>i</sub> &lt; x<sub>j</sub></code>，或者 <code>x<sub>i</sub> == x<sub>j</sub></code> 且 <code>y<sub>i</sub> &lt; y<sub>j</sub></code>。</p>

<p><code>|x|</code> 表示 <code>x</code> 的 <strong>绝对值</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">towers = [[1,2,5], [2,1,7], [3,1,9]], center = [1,1], radius = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">[3,1]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>塔 <code>[1, 2, 5]</code>：曼哈顿距离 = <code>|1 - 1| + |2 - 1| = 1</code>，可到达。</li>
	<li>塔 <code>[2, 1, 7]</code>：曼哈顿距离 = <code>|2 - 1| + |1 - 1| = 1</code>，可到达。</li>
	<li>塔 <code>[3, 1, 9]</code>：曼哈顿距离 = <code>|3 - 1| + |1 - 1| = 2</code>，可到达。</li>
</ul>

<p>所有塔都是可到达的。最大质量因子为 9，对应塔 <code>[3, 1]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">towers = [[1,3,4], [2,2,4], [4,4,7]], center = [0,0], radius = 5</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,3]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>塔 <code>[1, 3, 4]</code>：曼哈顿距离 = <code>|1 - 0| + |3 - 0| = 4</code>，可到达。</li>
	<li>塔 <code>[2, 2, 4]</code>：曼哈顿距离 = <code>|2 - 0| + |2 - 0| = 4</code>，可到达。</li>
	<li>塔 <code>[4, 4, 7]</code>：曼哈顿距离 = <code>|4 - 0| + |4 - 0| = 8</code>，不可到达。</li>
</ul>

<p>在可到达的塔中，最大质量因子为 4。<code>[1, 3]</code> 和 <code>[2, 2]</code> 的质量因子相同，因此返回字典序较小的坐标 <code>[1, 3]</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">towers = [[5,6,8], [0,3,5]], center = [1,2], radius = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">[-1,-1]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>塔 <code>[5, 6, 8]</code>：曼哈顿距离 = <code>|5 - 1| + |6 - 2| = 8</code>，不可到达。</li>
	<li>塔 <code>[0, 3, 5]</code>：曼哈顿距离 = <code>|0 - 1| + |3 - 2| = 2</code>，不可到达。</li>
</ul>

<p>在给定半径内没有可到达的塔，故返回 <code>[-1, -1]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= towers.length &lt;= 10<sup>5</sup></code></li>
	<li><code>towers[i] = [x<sub>i</sub>, y<sub>i</sub>, q<sub>i</sub>]</code></li>
	<li><code>center = [cx, cy]</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub>, q<sub>i</sub>, cx, cy &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= radius &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

我们定义一个变量 $\textit{idx}$ 来记录当前最佳塔的下标，初始时 $\textit{idx} = -1$。然后我们遍历每一座塔，计算其与 $\textit{center}$ 之间的曼哈顿距离 $\textit{dist}$：

$$
\textit{dist} = |x_i - cx| + |y_i - cy|
$$

如果 $\textit{dist} > \textit{radius}$，则该塔不可到达，跳过该塔。否则，我们比较当前塔与最佳塔的质量因子 $q$：

- 如果 $\textit{idx} = -1$，说明当前还没有可到达的塔，我们将 $\textit{idx}$ 更新为当前塔的下标。
- 如果当前塔的质量因子 $q_i$ 大于最佳塔的质量因子 $q_{\textit{idx}}$，则将 $\textit{idx}$ 更新为当前塔的下标。
- 如果当前塔的质量因子 $q_i$ 等于最佳塔的质量因子 $q_{\textit{idx}}$，则比较两座塔的坐标，选择字典序较小的塔。

遍历结束后，如果 $\textit{idx} = -1$，说明没有可到达的塔，返回 $[-1, -1]$。否则，返回最佳塔的坐标。

时间复杂度 $O(n)$，其中 $n$ 是塔的数量。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def bestTower(
        self, towers: List[List[int]], center: List[int], radius: int
    ) -> List[int]:
        cx, cy = center
        idx = -1
        for i, (x, y, q) in enumerate(towers):
            dist = abs(x - cx) + abs(y - cy)
            if dist > radius:
                continue
            if (
                idx == -1
                or towers[idx][2] < q
                or (towers[idx][2] == q and towers[i][:2] < towers[idx][:2])
            ):
                idx = i
        return [-1, -1] if idx == -1 else towers[idx][:2]
```

#### Java

```java
class Solution {
    public int[] bestTower(int[][] towers, int[] center, int radius) {
        int cx = center[0], cy = center[1];
        int idx = -1;
        for (int i = 0; i < towers.length; i++) {
            int x = towers[i][0], y = towers[i][1], q = towers[i][2];
            int dist = Math.abs(x - cx) + Math.abs(y - cy);
            if (dist > radius) {
                continue;
            }
            if (idx == -1 || towers[idx][2] < q
                || (towers[idx][2] == q
                    && (x < towers[idx][0] || (x == towers[idx][0] && y < towers[idx][1])))) {
                idx = i;
            }
        }
        return idx == -1 ? new int[] {-1, -1} : new int[] {towers[idx][0], towers[idx][1]};
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> bestTower(vector<vector<int>>& towers, vector<int>& center, int radius) {
        int cx = center[0], cy = center[1];
        int idx = -1;
        for (int i = 0; i < towers.size(); ++i) {
            int x = towers[i][0], y = towers[i][1], q = towers[i][2];
            int dist = abs(x - cx) + abs(y - cy);
            if (dist > radius) {
                continue;
            }
            if (
                idx == -1
                || towers[idx][2] < q
                || (towers[idx][2] == q && (x < towers[idx][0] || (x == towers[idx][0] && y < towers[idx][1])))) {
                idx = i;
            }
        }
        if (idx == -1) {
            return {-1, -1};
        }
        return {towers[idx][0], towers[idx][1]};
    }
};
```

#### Go

```go
func bestTower(towers [][]int, center []int, radius int) []int {
	cx, cy := center[0], center[1]
	idx := -1
	for i, a := range towers {
		x, y, q := a[0], a[1], a[2]
		dist := abs(x-cx) + abs(y-cy)
		if dist > radius {
			continue
		}
		if idx == -1 ||
			towers[idx][2] < q ||
			(towers[idx][2] == q &&
				(x < towers[idx][0] ||
					(x == towers[idx][0] && y < towers[idx][1]))) {
			idx = i
		}
	}
	if idx == -1 {
		return []int{-1, -1}
	}
	return []int{towers[idx][0], towers[idx][1]}
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
function bestTower(towers: number[][], center: number[], radius: number): number[] {
    const [cx, cy] = center;
    let idx = -1;
    for (let i = 0; i < towers.length; i++) {
        const [x, y, q] = towers[i];
        const dist = Math.abs(x - cx) + Math.abs(y - cy);
        if (dist > radius) {
            continue;
        }
        if (
            idx === -1 ||
            towers[idx][2] < q ||
            (towers[idx][2] === q &&
                (x < towers[idx][0] || (x === towers[idx][0] && y < towers[idx][1])))
        ) {
            idx = i;
        }
    }
    return idx === -1 ? [-1, -1] : [towers[idx][0], towers[idx][1]];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
