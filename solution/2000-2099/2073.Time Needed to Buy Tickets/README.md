---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2073.Time%20Needed%20to%20Buy%20Tickets/README.md
rating: 1325
source: 第 267 场周赛 Q1
tags:
    - 队列
    - 数组
    - 模拟
---

<!-- problem:start -->

# [2073. 买票需要的时间](https://leetcode.cn/problems/time-needed-to-buy-tickets)

[English Version](/solution/2000-2099/2073.Time%20Needed%20to%20Buy%20Tickets/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有 <code>n</code> 个人前来排队买票，其中第 <code>0</code> 人站在队伍 <strong>最前方</strong> ，第 <code>(n - 1)</code> 人站在队伍 <strong>最后方</strong> 。</p>

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>tickets</code> ，数组长度为 <code>n</code> ，其中第 <code>i</code> 人想要购买的票数为 <code>tickets[i]</code> 。</p>

<p>每个人买票都需要用掉 <strong>恰好 1 秒</strong> 。一个人 <strong>一次只能买一张票</strong> ，如果需要购买更多票，他必须走到&nbsp; <strong>队尾</strong> 重新排队（<strong>瞬间 </strong>发生，不计时间）。如果一个人没有剩下需要买的票，那他将会 <strong>离开</strong> 队伍。</p>

<p>返回位于位置 <code>k</code>（下标从 <strong>0</strong> 开始）的人完成买票需要的时间（以秒为单位）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong>tickets = [2,3,2], k = 2</p>

<p><strong>输出：</strong>6</p>

<p><strong>解释：</strong></p>

<ul>
	<li>队伍一开始为 [2,3,2]，第 k 个人以下划线标识。</li>
	<li>在最前面的人买完票后，队伍在第 1 秒变成 [3,<u>2</u>,1]。</li>
	<li>继续这个过程，队伍在第 2 秒变为[<u>2</u>,1,2]。</li>
	<li>继续这个过程，队伍在第 3 秒变为[1,2,<u>1</u>]。</li>
	<li>继续这个过程，队伍在第 4 秒变为[2,<u>1</u>]。</li>
	<li>继续这个过程，队伍在第 5 秒变为[<u>1</u>,1]。</li>
	<li>继续这个过程，队伍在第 6 秒变为[1]。第 k 个人完成买票，所以返回 6。</li>
</ul>
</div>

<p><strong>示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong>tickets = [5,1,1,1], k = 0</p>

<p><strong>输出：</strong>8</p>

<p><strong>解释：</strong></p>

<ul>
	<li>队伍一开始为 [<u>5</u>,1,1,1]，第 k 个人以下划线标识。</li>
	<li>在最前面的人买完票后，队伍在第 1 秒变成 [1,1,1,<u>4</u>]。</li>
	<li>继续这个过程 3 秒，队伍在第 4&nbsp;秒变为[<u>4</u>]。</li>
	<li>继续这个过程 4 秒，队伍在第 8&nbsp;秒变为[]。第 k 个人完成买票，所以返回 8。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == tickets.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= tickets[i] &lt;= 100</code></li>
	<li><code>0 &lt;= k &lt; n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

根据题目描述，当第 $k$ 个人完成购票时，在第 $k$ 个人前面的所有人，购买的票数都不会超过第 $k$ 个人购买的票数，而在第 $k$ 个人后面的所有人，购买的票数都不会超过第 $k$ 个人购买的票数减 $1$。

因此，我们可以遍历整个队伍，对于第 $i$ 个人，如果 $i \leq k$，购票时间为 $\min(\textit{tickets}[i], \textit{tickets}[k])$，否则购票时间为 $\min(\textit{tickets}[i], \textit{tickets}[k] - 1)$。我们将所有人的购票时间相加即可。

时间复杂度 $O(n)$，其中 $n$ 为队伍的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def timeRequiredToBuy(self, tickets: List[int], k: int) -> int:
        ans = 0
        for i, x in enumerate(tickets):
            ans += min(x, tickets[k] if i <= k else tickets[k] - 1)
        return ans
```

#### Java

```java
class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int ans = 0;
        for (int i = 0; i < tickets.length; ++i) {
            ans += Math.min(tickets[i], i <= k ? tickets[k] : tickets[k] - 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int timeRequiredToBuy(vector<int>& tickets, int k) {
        int ans = 0;
        for (int i = 0; i < tickets.size(); ++i) {
            ans += min(tickets[i], i <= k ? tickets[k] : tickets[k] - 1);
        }
        return ans;
    }
};
```

#### Go

```go
func timeRequiredToBuy(tickets []int, k int) (ans int) {
	for i, x := range tickets {
		t := tickets[k]
		if i > k {
			t--
		}
		ans += min(x, t)
	}
	return
}
```

#### TypeScript

```ts
function timeRequiredToBuy(tickets: number[], k: number): number {
    let ans = 0;
    const n = tickets.length;
    for (let i = 0; i < n; ++i) {
        ans += Math.min(tickets[i], i <= k ? tickets[k] : tickets[k] - 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
