---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1274.Number%20of%20Ships%20in%20a%20Rectangle/README.md
rating: 1997
tags:
    - 数组
    - 分治
    - 交互
---

# [1274. 矩形内船只的数目 🔒](https://leetcode.cn/problems/number-of-ships-in-a-rectangle)

[English Version](/solution/1200-1299/1274.Number%20of%20Ships%20in%20a%20Rectangle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><em>(此题是 <strong>交互式问题&nbsp;</strong>)</em></p>

<p>在用笛卡尔坐标系表示的二维海平面上，有一些船。每一艘船都在一个整数点上，且每一个整数点最多只有 1 艘船。</p>

<p>有一个函数&nbsp;<code>Sea.hasShips(topRight, bottomLeft)</code>&nbsp;，输入参数为右上角和左下角两个点的坐标，当且仅当这两个点所表示的矩形区域（包含边界）内至少有一艘船时，这个函数才返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code> 。</p>

<p>给你矩形的右上角&nbsp;<code>topRight</code> 和左下角&nbsp;<code>bottomLeft</code> 的坐标，请你返回此矩形内船只的数目。题目保证矩形内&nbsp;<strong>至多只有 10 艘船</strong>。</p>

<p>调用函数&nbsp;<code>hasShips</code>&nbsp;<strong>超过400次&nbsp;</strong>的提交将被判为&nbsp;<em>错误答案（Wrong Answer）</em>&nbsp;。同时，任何尝试绕过评测系统的行为都将被取消比赛资格。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1274.Number%20of%20Ships%20in%20a%20Rectangle/images/1445_example_1.png" style="height: 500px; width: 496px;" /></p>

<pre>
<strong>输入：</strong>
ships = [[1,1],[2,2],[3,3],[5,5]], topRight = [4,4], bottomLeft = [0,0]
<strong>输出：</strong>3
<strong>解释：</strong>在 [0,0] 到 [4,4] 的范围内总共有 3 艘船。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入：</strong>ans = [[1,1],[2,2],[3,3]], topRight = [1000,1000], bottomLeft = [0,0]
<strong>输出：</strong>3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>ships</code>&nbsp;数组只用于评测系统内部初始化。你必须“蒙着眼睛”解决这个问题。你无法得知&nbsp;<code>ships</code>&nbsp;的信息，所以只能通过调用&nbsp;<code>hasShips</code>&nbsp;接口来求解。</li>
	<li><code>0 &lt;=&nbsp;bottomLeft[0]&nbsp;&lt;= topRight[0]&nbsp;&lt;= 1000</code></li>
	<li><code>0 &lt;=&nbsp;bottomLeft[1]&nbsp;&lt;= topRight[1]&nbsp;&lt;= 1000</code></li>
	<li><code>topRight != bottomLeft</code></li>
</ul>

<p>&nbsp;</p>

## 解法

### 方法一：递归 + 分治

由于矩形内最多只有 $10$ 艘船，所以我们可以将矩形划分为四个子矩形，分别求出每个子矩形内船只的数目 🔒，然后将四个子矩形内船只的数目 🔒 相加即可。如果一个子矩形内没有船只，那么就不需要再继续划分了。

时间复杂度 $O(C \times \log \max(m, n))$，空间复杂度 $O(\log \max(m, n))$。其中 $C$ 为船只的数目，而 $m$ 和 $n$ 分别为矩形的长和宽。

<!-- tabs:start -->

```python
# """
# This is Sea's API interface.
# You should not implement it, or speculate about its implementation
# """
# class Sea:
#    def hasShips(self, topRight: 'Point', bottomLeft: 'Point') -> bool:
#
# class Point:
# 	def __init__(self, x: int, y: int):
# 		self.x = x
# 		self.y = y


class Solution:
    def countShips(self, sea: "Sea", topRight: "Point", bottomLeft: "Point") -> int:
        def dfs(topRight, bottomLeft):
            x1, y1 = bottomLeft.x, bottomLeft.y
            x2, y2 = topRight.x, topRight.y
            if x1 > x2 or y1 > y2:
                return 0
            if not sea.hasShips(topRight, bottomLeft):
                return 0
            if x1 == x2 and y1 == y2:
                return 1
            midx = (x1 + x2) >> 1
            midy = (y1 + y2) >> 1
            a = dfs(topRight, Point(midx + 1, midy + 1))
            b = dfs(Point(midx, y2), Point(x1, midy + 1))
            c = dfs(Point(midx, midy), bottomLeft)
            d = dfs(Point(x2, midy), Point(midx + 1, y1))
            return a + b + c + d

        return dfs(topRight, bottomLeft)
```

```java
/**
 * // This is Sea's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Sea {
 *     public boolean hasShips(int[] topRight, int[] bottomLeft);
 * }
 */

class Solution {
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        int x1 = bottomLeft[0], y1 = bottomLeft[1];
        int x2 = topRight[0], y2 = topRight[1];
        if (x1 > x2 || y1 > y2) {
            return 0;
        }
        if (!sea.hasShips(topRight, bottomLeft)) {
            return 0;
        }
        if (x1 == x2 && y1 == y2) {
            return 1;
        }
        int midx = (x1 + x2) >> 1;
        int midy = (y1 + y2) >> 1;
        int a = countShips(sea, topRight, new int[] {midx + 1, midy + 1});
        int b = countShips(sea, new int[] {midx, y2}, new int[] {x1, midy + 1});
        int c = countShips(sea, new int[] {midx, midy}, bottomLeft);
        int d = countShips(sea, new int[] {x2, midy}, new int[] {midx + 1, y1});
        return a + b + c + d;
    }
}
```

```cpp
/**
 * // This is Sea's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Sea {
 *   public:
 *     bool hasShips(vector<int> topRight, vector<int> bottomLeft);
 * };
 */

class Solution {
public:
    int countShips(Sea sea, vector<int> topRight, vector<int> bottomLeft) {
        int x1 = bottomLeft[0], y1 = bottomLeft[1];
        int x2 = topRight[0], y2 = topRight[1];
        if (x1 > x2 || y1 > y2) {
            return 0;
        }
        if (!sea.hasShips(topRight, bottomLeft)) {
            return 0;
        }
        if (x1 == x2 && y1 == y2) {
            return 1;
        }
        int midx = (x1 + x2) >> 1;
        int midy = (y1 + y2) >> 1;
        int a = countShips(sea, topRight, {midx + 1, midy + 1});
        int b = countShips(sea, {midx, y2}, {x1, midy + 1});
        int c = countShips(sea, {midx, midy}, bottomLeft);
        int d = countShips(sea, {x2, midy}, {midx + 1, y1});
        return a + b + c + d;
    }
};
```

```go
/**
 * // This is Sea's API interface.
 * // You should not implement it, or speculate about its implementation
 * type Sea struct {
 *     func hasShips(topRight, bottomLeft []int) bool {}
 * }
 */

func countShips(sea Sea, topRight, bottomLeft []int) int {
	x1, y1 := bottomLeft[0], bottomLeft[1]
	x2, y2 := topRight[0], topRight[1]
	if x1 > x2 || y1 > y2 {
		return 0
	}
	if !sea.hasShips(topRight, bottomLeft) {
		return 0
	}
	if x1 == x2 && y1 == y2 {
		return 1
	}
	midx := (x1 + x2) >> 1
	midy := (y1 + y2) >> 1
	a := countShips(sea, topRight, []int{midx + 1, midy + 1})
	b := countShips(sea, []int{midx, y2}, []int{x1, midy + 1})
	c := countShips(sea, []int{midx, midy}, bottomLeft)
	d := countShips(sea, []int{x2, midy}, []int{midx + 1, y1})
	return a + b + c + d
}
```

```ts
/**
 * // This is the Sea's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Sea {
 *      hasShips(topRight: number[], bottomLeft: number[]): boolean {}
 * }
 */

function countShips(sea: Sea, topRight: number[], bottomLeft: number[]): number {
    const [x1, y1] = bottomLeft;
    const [x2, y2] = topRight;
    if (x1 > x2 || y1 > y2 || !sea.hasShips(topRight, bottomLeft)) {
        return 0;
    }
    if (x1 === x2 && y1 === y2) {
        return 1;
    }
    const midx = (x1 + x2) >> 1;
    const midy = (y1 + y2) >> 1;
    const a = countShips(sea, topRight, [midx + 1, midy + 1]);
    const b = countShips(sea, [midx, y2], [x1, midy + 1]);
    const c = countShips(sea, [midx, midy], bottomLeft);
    const d = countShips(sea, [x2, midy], [midx + 1, y1]);
    return a + b + c + d;
}
```

<!-- tabs:end -->

<!-- end -->
