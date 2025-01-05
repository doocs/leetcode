---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3386.Button%20with%20Longest%20Push%20Time/README.md
rating: 1255
source: 第 428 场周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [3386. 按下时间最长的按钮](https://leetcode.cn/problems/button-with-longest-push-time)

[English Version](/solution/3300-3399/3386.Button%20with%20Longest%20Push%20Time/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二维数组 <code>events</code>，表示孩子在键盘上按下一系列按钮触发的按钮事件。</p>

<p>每个 <code>events[i] = [index<sub>i</sub>, time<sub>i</sub>]</code> 表示在时间 <code>time<sub>i</sub></code> 时，按下了下标为 <code>index<sub>i</sub></code> 的按钮。</p>

<ul>
	<li>数组按照 <code>time</code> 的递增顺序<strong>排序</strong>。</li>
	<li>按下一个按钮所需的时间是连续两次按钮按下的时间差。按下第一个按钮所需的时间就是其时间戳。</li>
</ul>

<p>返回按下时间&nbsp;<strong>最长&nbsp;</strong>的按钮的 <code>index</code>。如果有多个按钮的按下时间相同，则返回 <code>index</code> 最小的按钮。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">events = [[1,2],[2,5],[3,9],[1,15]]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>下标为 1 的按钮在时间 2 被按下。</li>
	<li>下标为 2 的按钮在时间 5 被按下，因此按下时间为 <code>5 - 2 = 3</code>。</li>
	<li>下标为 3 的按钮在时间 9 被按下，因此按下时间为 <code>9 - 5 = 4</code>。</li>
	<li>下标为 1 的按钮再次在时间 15 被按下，因此按下时间为 <code>15 - 9 = 6</code>。</li>
</ul>

<p>最终，下标为 1 的按钮按下时间最长，为 6。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">events = [[10,5],[1,7]]</span></p>

<p><strong>输出：</strong> <span class="example-io">10</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>下标为 10 的按钮在时间 5 被按下。</li>
	<li>下标为 1 的按钮在时间 7 被按下，因此按下时间为 <code>7 - 5 = 2</code>。</li>
</ul>

<p>最终，下标为 10 的按钮按下时间最长，为 5。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= events.length &lt;= 1000</code></li>
	<li><code>events[i] == [index<sub>i</sub>, time<sub>i</sub>]</code></li>
	<li><code>1 &lt;= index<sub>i</sub>, time<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li>输入保证数组 <code>events</code> 按照 <code>time<sub>i</sub></code> 的递增顺序排序。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

我们定义两个变量 $\textit{ans}$ 和 $t$，分别表示按下时间最长的按钮的索引和按下时间。

接下来，我们从下标 $k = 1$ 开始遍历数组 $\textit{events}$，对于每个 $k$，我们计算当前按钮的按下时间 $d = t2 - t1$，其中 $t2$ 是当前按钮的按下时间，而 $t1$ 是前一个按钮的按下时间。如果 $d > t$ 或者 $d = t$ 且当前按钮的索引 $i$ 小于 $\textit{ans}$，我们更新 $\textit{ans} = i$ 和 $t = d$。

最后，我们返回 $\textit{ans}$。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{events}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def buttonWithLongestTime(self, events: List[List[int]]) -> int:
        ans, t = events[0]
        for (_, t1), (i, t2) in pairwise(events):
            d = t2 - t1
            if d > t or (d == t and i < ans):
                ans, t = i, d
        return ans
```

#### Java

```java
class Solution {
    public int buttonWithLongestTime(int[][] events) {
        int ans = events[0][0], t = events[0][1];
        for (int k = 1; k < events.length; ++k) {
            int i = events[k][0], t2 = events[k][1], t1 = events[k - 1][1];
            int d = t2 - t1;
            if (d > t || (d == t && ans > i)) {
                ans = i;
                t = d;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int buttonWithLongestTime(vector<vector<int>>& events) {
        int ans = events[0][0], t = events[0][1];
        for (int k = 1; k < events.size(); ++k) {
            int i = events[k][0], t2 = events[k][1], t1 = events[k - 1][1];
            int d = t2 - t1;
            if (d > t || (d == t && ans > i)) {
                ans = i;
                t = d;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func buttonWithLongestTime(events [][]int) int {
	ans, t := events[0][0], events[0][1]
	for k, e := range events[1:] {
		i, t2, t1 := e[0], e[1], events[k][1]
		d := t2 - t1
		if d > t || (d == t && i < ans) {
			ans, t = i, d
		}
	}
	return ans
}
```

#### TypeScript

```ts
function buttonWithLongestTime(events: number[][]): number {
    let [ans, t] = events[0];
    for (let k = 1; k < events.length; ++k) {
        const [i, t2] = events[k];
        const d = t2 - events[k - 1][1];
        if (d > t || (d === t && i < ans)) {
            ans = i;
            t = d;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
