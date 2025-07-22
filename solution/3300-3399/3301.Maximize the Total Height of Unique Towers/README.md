---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3301.Maximize%20the%20Total%20Height%20of%20Unique%20Towers/README.md
rating: 1448
source: 第 140 场双周赛 Q2
tags:
    - 贪心
    - 数组
    - 排序
---

<!-- problem:start -->

# [3301. 高度互不相同的最大塔高和](https://leetcode.cn/problems/maximize-the-total-height-of-unique-towers)

[English Version](/solution/3300-3399/3301.Maximize%20the%20Total%20Height%20of%20Unique%20Towers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组&nbsp;<code>maximumHeight</code>&nbsp;，其中&nbsp;<code>maximumHeight[i]</code>&nbsp;表示第 <code>i</code>&nbsp;座塔可以达到的 <strong>最大</strong>&nbsp;高度。</p>

<p>你的任务是给每一座塔分别设置一个高度，使得：</p>

<ol>
	<li>第 <code>i</code>&nbsp;座塔的高度是一个正整数，且不超过&nbsp;<code>maximumHeight[i]</code>&nbsp;。</li>
	<li>所有塔的高度互不相同。</li>
</ol>

<p>请你返回设置完所有塔的高度后，可以达到的 <strong>最大</strong>&nbsp;总高度。如果没有合法的设置，返回 <code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><b>输入：</b>maximumHeight<span class="example-io"> = [2,3,4,3]</span></p>

<p><span class="example-io"><b>输出：</b>10</span></p>

<p><strong>解释：</strong></p>

<p>我们可以将塔的高度设置为：<code>[1, 2, 4, 3]</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><b>输入：</b>maximumHeight<span class="example-io"> = [15,10]</span></p>

<p><span class="example-io"><b>输出：</b>25</span></p>

<p><strong>解释：</strong></p>

<p>我们可以将塔的高度设置为：<code>[15, 10]</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><b>输入：</b>maximumHeight<span class="example-io"> = [2,2,1]</span></p>

<p><span class="example-io"><b>输出：</b>-1</span></p>

<p><b>解释：</b></p>

<p>无法设置塔的高度为正整数且高度互不相同。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= maximumHeight.length&nbsp;&lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= maximumHeight[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 贪心

我们可以将塔的最大高度按照从大到小排序，然后从最大高度开始逐个分配高度，用一个变量 $mx$ 记录当前分配的最大高度。

如果当前高度 $x$ 大于 $mx - 1$，则将 $x$ 更新为 $mx - 1$。此时如果 $x$ 小于等于 $0$，说明无法分配高度，直接返回 $-1$。否则，我们将 $x$ 加到答案中，并更新 $mx$ 为 $x$。

最后返回答案即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $\textit{maximumHeight}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumTotalSum(self, maximumHeight: List[int]) -> int:
        maximumHeight.sort()
        ans, mx = 0, inf
        for x in maximumHeight[::-1]:
            x = min(x, mx - 1)
            if x <= 0:
                return -1
            ans += x
            mx = x
        return ans
```

#### Java

```java
class Solution {
    public long maximumTotalSum(int[] maximumHeight) {
        long ans = 0;
        int mx = 1 << 30;
        Arrays.sort(maximumHeight);
        for (int i = maximumHeight.length - 1; i >= 0; --i) {
            int x = Math.min(maximumHeight[i], mx - 1);
            if (x <= 0) {
                return -1;
            }
            ans += x;
            mx = x;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumTotalSum(vector<int>& maximumHeight) {
        ranges::sort(maximumHeight, greater<int>());
        long long ans = 0;
        int mx = 1 << 30;
        for (int x : maximumHeight) {
            x = min(x, mx - 1);
            if (x <= 0) {
                return -1;
            }
            ans += x;
            mx = x;
        }
        return ans;
    }
};
```

#### Go

```go
func maximumTotalSum(maximumHeight []int) int64 {
	slices.SortFunc(maximumHeight, func(a, b int) int { return b - a })
	ans := int64(0)
	mx := 1 << 30
	for _, x := range maximumHeight {
		x = min(x, mx-1)
		if x <= 0 {
			return -1
		}
		ans += int64(x)
		mx = x
	}
	return ans
}
```

#### TypeScript

```ts
function maximumTotalSum(maximumHeight: number[]): number {
    maximumHeight.sort((a, b) => a - b).reverse();
    let ans: number = 0;
    let mx: number = Infinity;
    for (let x of maximumHeight) {
        x = Math.min(x, mx - 1);
        if (x <= 0) {
            return -1;
        }
        ans += x;
        mx = x;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
