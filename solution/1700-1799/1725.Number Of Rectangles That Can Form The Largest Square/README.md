---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1725.Number%20Of%20Rectangles%20That%20Can%20Form%20The%20Largest%20Square/README.md
rating: 1229
source: 第 224 场周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [1725. 可以形成最大正方形的矩形数目](https://leetcode.cn/problems/number-of-rectangles-that-can-form-the-largest-square)

[English Version](/solution/1700-1799/1725.Number%20Of%20Rectangles%20That%20Can%20Form%20The%20Largest%20Square/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组 <code>rectangles</code> ，其中 <code>rectangles[i] = [l<sub>i</sub>, w<sub>i</sub>]</code> 表示第 <code>i</code> 个矩形的长度为 <code>l<sub>i</sub></code> 、宽度为 <code>w<sub>i</sub></code> 。</p>

<p>如果存在 <code>k</code> 同时满足 <code>k <= l<sub>i</sub></code> 和 <code>k <= w<sub>i</sub></code> ，就可以将第 <code>i</code> 个矩形切成边长为 <code>k</code> 的正方形。例如，矩形 <code>[4,6]</code> 可以切成边长最大为 <code>4</code> 的正方形。</p>

<p>设 <code>maxLen</code> 为可以从矩形数组 <code>rectangles</code> 切分得到的 <strong>最大正方形</strong> 的边长。</p>

<p>请你统计有多少个矩形能够切出边长为<em> </em><code>maxLen</code> 的正方形，并返回矩形 <strong>数目</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>rectangles = [[5,8],[3,9],[5,12],[16,5]]
<strong>输出：</strong>3
<strong>解释：</strong>能从每个矩形中切出的最大正方形边长分别是 [5,3,5,5] 。
最大正方形的边长为 5 ，可以由 3 个矩形切分得到。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>rectangles = [[2,3],[3,7],[4,3],[3,7]]
<strong>输出：</strong>3
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= rectangles.length <= 1000</code></li>
	<li><code>rectangles[i].length == 2</code></li>
	<li><code>1 <= l<sub>i</sub>, w<sub>i</sub> <= 10<sup>9</sup></code></li>
	<li><code>l<sub>i</sub> != w<sub>i</sub></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

我们定义一个变量 $ans$ 来记录当前最大边长的正方形的个数，定义另一个变量 $mx$ 来记录当前最大的边长。

遍历数组 $rectangles$，对于每个矩形 $[l, w]$，我们取 $x = \min(l, w)$，如果 $mx < x$，说明我们找到了一个更大的边长，此时我们将 $mx$ 更新为 $x$，并将 $ans$ 更新为 $1$；如果 $mx = x$，说明我们找到了一个和当前最大边长相同的边长，此时我们将 $ans$ 增加 $1$。

最后返回 $ans$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $rectangles$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def countGoodRectangles(self, rectangles: List[List[int]]) -> int:
        ans = mx = 0
        for l, w in rectangles:
            x = min(l, w)
            if mx < x:
                ans = 1
                mx = x
            elif mx == x:
                ans += 1
        return ans
```

```java
class Solution {
    public int countGoodRectangles(int[][] rectangles) {
        int ans = 0, mx = 0;
        for (var e : rectangles) {
            int x = Math.min(e[0], e[1]);
            if (mx < x) {
                mx = x;
                ans = 1;
            } else if (mx == x) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countGoodRectangles(vector<vector<int>>& rectangles) {
        int ans = 0, mx = 0;
        for (auto& e : rectangles) {
            int x = min(e[0], e[1]);
            if (mx < x) {
                mx = x;
                ans = 1;
            } else if (mx == x) {
                ++ans;
            }
        }
        return ans;
    }
};
```

```go
func countGoodRectangles(rectangles [][]int) (ans int) {
	mx := 0
	for _, e := range rectangles {
		x := min(e[0], e[1])
		if mx < x {
			mx = x
			ans = 1
		} else if mx == x {
			ans++
		}
	}
	return
}
```

```ts
function countGoodRectangles(rectangles: number[][]): number {
    let [ans, mx] = [0, 0];
    for (const [l, w] of rectangles) {
        const x = Math.min(l, w);
        if (mx < x) {
            mx = x;
            ans = 1;
        } else if (mx === x) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
