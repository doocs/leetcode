---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3189.Minimum%20Moves%20to%20Get%20a%20Peaceful%20Board/README.md
tags:
    - 贪心
    - 数组
    - 计数排序
    - 排序
---

<!-- problem:start -->

# [3189. 得到一个和平棋盘的最少步骤 🔒](https://leetcode.cn/problems/minimum-moves-to-get-a-peaceful-board)

[English Version](/solution/3100-3199/3189.Minimum%20Moves%20to%20Get%20a%20Peaceful%20Board/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为&nbsp;<code>n</code>&nbsp;的二维数组&nbsp;<code>rooks</code>，其中&nbsp;<code>rooks[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp;表示 <code>n x n</code>&nbsp;棋盘上一个车的位置。你的任务是每次在垂直或水平方向上移动&nbsp;<strong>1 格</strong>&nbsp;车（到一个相邻的格子）使得棋盘变得&nbsp;<strong>和平</strong>。</p>

<p>如果每行每列都 <strong>只有</strong> 一个车，那么这块棋盘就是和平的。</p>

<p>返回获得和平棋盘所需的 <strong>最少</strong> 步数。</p>

<p><strong>注意 任何时刻&nbsp;</strong>两个车都不能在同一个格子。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">rooks = [[0,0],[1,0],[1,1]]</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3189.Minimum%20Moves%20to%20Get%20a%20Peaceful%20Board/images/1719285456-CnJqJS-ex1-edited.gif" style="width: 200px; height: 200px;" /></div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">rooks = [[0,0],[0,1],[0,2],[0,3]]</span></p>

<p><span class="example-io"><b>输出：</b></span><span class="example-io">6</span></p>

<p><strong>解释：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3189.Minimum%20Moves%20to%20Get%20a%20Peaceful%20Board/images/1719285456-wtGRzv-ex2-edited.gif" style="width: 200px; height: 200px;" /></div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == rooks.length &lt;= 500</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= n - 1</code></li>
	<li>输入保证没有两个车在相同的格子。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

我们可以将所有的车按照横坐标排序，然后将车按顺序分配给每一行，计算每个车到目标位置的距离之和。然后将所有的车按照纵坐标排序，按照同样的方法计算每个车到目标位置的距离之和。最后将两个距离之和相加即为答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为车的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMoves(self, rooks: List[List[int]]) -> int:
        rooks.sort()
        ans = sum(abs(x - i) for i, (x, _) in enumerate(rooks))
        rooks.sort(key=lambda x: x[1])
        ans += sum(abs(y - j) for j, (_, y) in enumerate(rooks))
        return ans
```

#### Java

```java
class Solution {
    public int minMoves(int[][] rooks) {
        Arrays.sort(rooks, (a, b) -> a[0] - b[0]);
        int ans = 0;
        int n = rooks.length;
        for (int i = 0; i < n; ++i) {
            ans += Math.abs(rooks[i][0] - i);
        }
        Arrays.sort(rooks, (a, b) -> a[1] - b[1]);
        for (int j = 0; j < n; ++j) {
            ans += Math.abs(rooks[j][1] - j);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMoves(vector<vector<int>>& rooks) {
        sort(rooks.begin(), rooks.end());
        int ans = 0;
        int n = rooks.size();
        for (int i = 0; i < n; ++i) {
            ans += abs(rooks[i][0] - i);
        }
        sort(rooks.begin(), rooks.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[1] < b[1];
        });
        for (int j = 0; j < n; ++j) {
            ans += abs(rooks[j][1] - j);
        }
        return ans;
    }
};
```

#### Go

```go
func minMoves(rooks [][]int) (ans int) {
	sort.Slice(rooks, func(i, j int) bool { return rooks[i][0] < rooks[j][0] })
	for i, row := range rooks {
		ans += int(math.Abs(float64(row[0] - i)))
	}
	sort.Slice(rooks, func(i, j int) bool { return rooks[i][1] < rooks[j][1] })
	for j, col := range rooks {
		ans += int(math.Abs(float64(col[1] - j)))
	}
	return
}
```

#### TypeScript

```ts
function minMoves(rooks: number[][]): number {
    rooks.sort((a, b) => a[0] - b[0]);
    let ans = rooks.reduce((sum, rook, i) => sum + Math.abs(rook[0] - i), 0);
    rooks.sort((a, b) => a[1] - b[1]);
    ans += rooks.reduce((sum, rook, j) => sum + Math.abs(rook[1] - j), 0);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
