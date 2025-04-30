---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3502.Minimum%20Cost%20to%20Reach%20Every%20Position/README.md
rating: 1243
source: 第 443 场周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [3502. 到达每个位置的最小费用](https://leetcode.cn/problems/minimum-cost-to-reach-every-position)

[English Version](/solution/3500-3599/3502.Minimum%20Cost%20to%20Reach%20Every%20Position/README_EN.md)

## 题目描述

<!-- description:start -->

<p data-end="438" data-start="104">给你一个长度为 <code>n</code> 的整数数组 <code data-end="119" data-start="113">cost</code> 。当前你位于位置 <code data-end="166" data-start="163">n</code>（队伍的末尾），队伍中共有 <code data-end="187" data-start="180">n + 1</code> 人，编号从 0 到 <code>n</code> 。</p>

<p data-end="438" data-start="104">你希望在队伍中向前移动，但队伍中每个人都会收取一定的费用才能与你 <strong>交换</strong>位置。与编号 <code data-end="375" data-start="372">i</code> 的人交换位置的费用为 <code data-end="397" data-start="388">cost[i]</code> 。</p>

<p data-end="487" data-start="440">你可以按照以下规则与他人交换位置：</p>

<ul data-end="632" data-start="488">
	<li data-end="572" data-start="488">如果对方在你前面，你 <strong>必须</strong> 支付 <code data-end="546" data-start="537">cost[i]</code> 费用与他们交换位置。</li>
	<li data-end="632" data-start="573">如果对方在你后面，他们可以免费与你交换位置。</li>
</ul>

<p data-end="755" data-start="634">返回一个大小为 <code>n</code> 的数组 <code>answer</code>，其中 <code>answer[i]</code> 表示到达队伍中每个位置 <code>i</code> 所需的 <strong data-end="680" data-start="664">最小</strong> 总费用。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">cost = [5,3,4,1,3,2]</span></p>

<p><strong>输出:</strong> <span class="example-io">[5,3,3,1,1,1]</span></p>

<p><strong>解释:</strong></p>

<p>我们可以通过以下方式到达每个位置：</p>

<ul>
	<li><code>i = 0</code>。可以花费 5 费用与编号 0 的人交换位置。</li>
	<li><span class="example-io"><code>i = 1</code>。可以花费 3 费用与编号 1 的人交换位置。</span></li>
	<li><span class="example-io"><code>i = 2</code>。可以花费 3 费用与编号 1 的人交换位置，然后免费与编号 2 的人交换位置。</span></li>
	<li><span class="example-io"><code>i = 3</code>。可以花费 1 费用与编号 3 的人交换位置。</span></li>
	<li><span class="example-io"><code>i = 4</code>。可以花费 1 费用与编号 3 的人交换位置，然后免费与编号 4 的人交换位置。</span></li>
	<li><span class="example-io"><code>i = 5</code>。可以花费 1 费用与编号 3 的人交换位置，然后免费与编号 5 的人交换位置。</span></li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">cost = [1,2,4,6,7]</span></p>

<p><strong>输出:</strong> <span class="example-io">[1,1,1,1,1]</span></p>

<p><strong>解释:</strong></p>

<p>可以花费 1 费用与编号 0 的人交换位置，然后可以免费到达队伍中的任何位置 <code>i</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示</strong></p>

<ul>
	<li><code>1 &lt;= n == cost.length &lt;= 100</code></li>
	<li><code>1 &lt;= cost[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：脑筋急转弯

根据题目描述，每个位置 $i$ 的最小费用，就是从 $0$ 到 $i$ 的最小费用。我们可以用一个变量 $\textit{mi}$ 来记录从 $0$ 到 $i$ 的最小费用。

我们从 $0$ 开始遍历每个位置 $i$，每次更新 $\textit{mi}$ 为 $\text{min}(\textit{mi}, \text{cost}[i])$，然后将 $\textit{mi}$ 赋值给答案数组的第 $i$ 个位置。

最后返回答案数组即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{cost}$ 的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCosts(self, cost: List[int]) -> List[int]:
        n = len(cost)
        ans = [0] * n
        mi = cost[0]
        for i, c in enumerate(cost):
            mi = min(mi, c)
            ans[i] = mi
        return ans
```

#### Java

```java
class Solution {
    public int[] minCosts(int[] cost) {
        int n = cost.length;
        int[] ans = new int[n];
        int mi = cost[0];
        for (int i = 0; i < n; ++i) {
            mi = Math.min(mi, cost[i]);
            ans[i] = mi;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> minCosts(vector<int>& cost) {
        int n = cost.size();
        vector<int> ans(n);
        int mi = cost[0];
        for (int i = 0; i < n; ++i) {
            mi = min(mi, cost[i]);
            ans[i] = mi;
        }
        return ans;
    }
};
```

#### Go

```go
func minCosts(cost []int) []int {
	n := len(cost)
	ans := make([]int, n)
	mi := cost[0]
	for i, c := range cost {
		mi = min(mi, c)
		ans[i] = mi
	}
	return ans
}
```

#### TypeScript

```ts
function minCosts(cost: number[]): number[] {
    const n = cost.length;
    const ans: number[] = Array(n).fill(0);
    let mi = cost[0];
    for (let i = 0; i < n; ++i) {
        mi = Math.min(mi, cost[i]);
        ans[i] = mi;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
