---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0554.Brick%20Wall/README.md
tags:
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [554. 砖墙](https://leetcode.cn/problems/brick-wall)

[English Version](/solution/0500-0599/0554.Brick%20Wall/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你的面前有一堵矩形的、由 <code>n</code> 行砖块组成的砖墙。这些砖块高度相同（也就是一个单位高）但是宽度不同。每一行砖块的宽度之和相等。</p>

<p>你现在要画一条 <strong>自顶向下 </strong>的、穿过 <strong>最少 </strong>砖块的垂线。如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。<strong>你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。</strong></p>

<p>给你一个二维数组 <code>wall</code> ，该数组包含这堵墙的相关信息。其中，<code>wall[i]</code> 是一个代表从左至右每块砖的宽度的数组。你需要找出怎样画才能使这条线 <strong>穿过的砖块数量最少</strong> ，并且返回 <strong>穿过的砖块数量</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0554.Brick%20Wall/images/a.png" style="width: 400px; height: 384px;" />
<pre>
<strong>输入：</strong>wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>wall = [[1],[1],[1]]
<strong>输出：</strong>3
</pre>

&nbsp;

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == wall.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= wall[i].length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= sum(wall[i].length) &lt;= 2 * 10<sup>4</sup></code></li>
	<li>对于每一行 <code>i</code> ，<code>sum(wall[i])</code> 是相同的</li>
	<li><code>1 &lt;= wall[i][j] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 前缀和

我们可以用一个哈希表 $\textit{cnt}$ 记录每一行除了最后一个砖块以外的前缀和，其中键为前缀和的值，值为该前缀和出现的次数。

遍历每一行，对于当前行的每一个砖块，我们将其加到当前的前缀和上，然后更新 $\textit{cnt}$。

最后我们遍历 $\textit{cnt}$，找出出现次数最多的前缀和，这就是穿过的砖块数量最少的情况。最后答案即为砖墙的行数减去穿过的砖块数量。

时间复杂度 $O(m \times n)$，空间复杂度 $O(n)$。其中 $m$ 和 $n$ 分别是砖墙的行数和砖墙的砖块数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def leastBricks(self, wall: List[List[int]]) -> int:
        cnt = Counter()
        for row in wall:
            s = 0
            for x in row[:-1]:
                s += x
                cnt[s] += 1
        return len(wall) - max(cnt.values(), default=0)
```

#### Java

```java
class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (var row : wall) {
            int s = 0;
            for (int i = 0; i + 1 < row.size(); ++i) {
                s += row.get(i);
                cnt.merge(s, 1, Integer::sum);
            }
        }
        int mx = 0;
        for (var x : cnt.values()) {
            mx = Math.max(mx, x);
        }
        return wall.size() - mx;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int leastBricks(vector<vector<int>>& wall) {
        unordered_map<int, int> cnt;
        for (const auto& row : wall) {
            int s = 0;
            for (int i = 0; i + 1 < row.size(); ++i) {
                s += row[i];
                cnt[s]++;
            }
        }
        int mx = 0;
        for (const auto& [_, x] : cnt) {
            mx = max(mx, x);
        }
        return wall.size() - mx;
    }
};
```

#### Go

```go
func leastBricks(wall [][]int) int {
	cnt := map[int]int{}
	for _, row := range wall {
		s := 0
		for _, x := range row[:len(row)-1] {
			s += x
			cnt[s]++
		}
	}
	mx := 0
	for _, x := range cnt {
		mx = max(mx, x)
	}
	return len(wall) - mx
}
```

#### TypeScript

```ts
function leastBricks(wall: number[][]): number {
    const cnt: Map<number, number> = new Map();
    for (const row of wall) {
        let s = 0;
        for (let i = 0; i + 1 < row.length; ++i) {
            s += row[i];
            cnt.set(s, (cnt.get(s) || 0) + 1);
        }
    }
    const mx = Math.max(...cnt.values(), 0);
    return wall.length - mx;
}
```

#### JavaScript

```js
/**
 * @param {number[][]} wall
 * @return {number}
 */
var leastBricks = function (wall) {
    const cnt = new Map();
    for (const row of wall) {
        let s = 0;
        for (let i = 0; i + 1 < row.length; ++i) {
            s += row[i];
            cnt.set(s, (cnt.get(s) || 0) + 1);
        }
    }
    const mx = Math.max(...cnt.values(), 0);
    return wall.length - mx;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
