---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3155.Maximum%20Number%20of%20Upgradable%20Servers/README.md
tags:
    - 数组
    - 数学
    - 二分查找
---

<!-- problem:start -->

# [3155. 可升级服务器的最大数量 🔒](https://leetcode.cn/problems/maximum-number-of-upgradable-servers)

[English Version](/solution/3100-3199/3155.Maximum%20Number%20of%20Upgradable%20Servers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你有&nbsp;<code>n</code>&nbsp;个数据中心并且需要升级他们的服务器。</p>

<p>给定四个长度为&nbsp;<code>n</code>&nbsp;的数组&nbsp;<code>count</code>，<code>upgrade</code>，<code>sell</code>&nbsp;和&nbsp;<code>money</code>，分别针对每个数据中心表示：</p>

<ul>
	<li>服务器的数量</li>
	<li>升级单个服务器的成本</li>
	<li>出售服务器获得的钱</li>
	<li>你最初拥有的钱</li>
</ul>

<p>返回一个数组&nbsp;<code>answer</code>，其中对于每个数据中心，<code>answer</code>&nbsp;中相应的元素代表可以升级的 <strong>最大</strong> 服务器数量。</p>

<p>请注意，一个数据中心的资金 <strong>不能</strong> 用于另一个数据中心。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">count = [4,3], upgrade = [3,5], sell = [4,2], money = [8,9]</span></p>

<p><strong>输出：</strong><span class="example-io">[3,2]</span></p>

<p><strong>解释：</strong></p>

<p>对于第一个数据中心，如果我们出售一台服务器，我们将会有&nbsp;<code>8 + 4 = 12</code>&nbsp;单位的钱并且我们能够升级其余的 3 台服务器。</p>

<p>对于第二个数据中心，如果我们出售一台服务器，我们将会有 <code>9 + 2 = 11</code> 单位的钱并且我们能够升级其余的 2&nbsp;台服务器。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">count = [1], upgrade = [2], sell = [1], money = [1]</span></p>

<p><strong>输出：</strong><span class="example-io">[0]</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= count.length == upgrade.length == sell.length == money.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= count[i], upgrade[i], sell[i], money[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

对于每个数据中心，我们假设可以升级 $\text{x}$ 台服务器，那么 $\text{x} \times \text{upgrade[i]} \leq \text{count[i]} \times \text{sell[i]} + \text{money[i]}$。即 $\text{x} \leq \frac{\text{count[i]} \times \text{sell[i]} + \text{money[i]}}{\text{upgrade[i]} + \text{sell[i]}}$。又因为 $\text{x} \leq \text{count[i]}$，所以我们取两者的最小值即可。

时间复杂度 $O(n)$，其中 $n$ 为数组的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxUpgrades(
        self, count: List[int], upgrade: List[int], sell: List[int], money: List[int]
    ) -> List[int]:
        ans = []
        for cnt, cost, income, cash in zip(count, upgrade, sell, money):
            ans.append(min(cnt, (cnt * income + cash) // (cost + income)))
        return ans
```

#### Java

```java
class Solution {
    public int[] maxUpgrades(int[] count, int[] upgrade, int[] sell, int[] money) {
        int n = count.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = Math.min(
                count[i], (int) ((1L * count[i] * sell[i] + money[i]) / (upgrade[i] + sell[i])));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> maxUpgrades(vector<int>& count, vector<int>& upgrade, vector<int>& sell, vector<int>& money) {
        int n = count.size();
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            ans.push_back(min(count[i], (int) ((1LL * count[i] * sell[i] + money[i]) / (upgrade[i] + sell[i]))));
        }
        return ans;
    }
};
```

#### Go

```go
func maxUpgrades(count []int, upgrade []int, sell []int, money []int) (ans []int) {
	for i, cnt := range count {
		ans = append(ans, min(cnt, (cnt*sell[i]+money[i])/(upgrade[i]+sell[i])))
	}
	return
}
```

#### TypeScript

```ts
function maxUpgrades(
    count: number[],
    upgrade: number[],
    sell: number[],
    money: number[],
): number[] {
    const n = count.length;
    const ans: number[] = [];
    for (let i = 0; i < n; ++i) {
        const x = ((count[i] * sell[i] + money[i]) / (upgrade[i] + sell[i])) | 0;
        ans.push(Math.min(x, count[i]));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
