---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3155.Maximum%20Number%20of%20Upgradable%20Servers/README.md
---

<!-- problem:start -->

# [3155. Maximum Number of Upgradable Servers 🔒](https://leetcode.cn/problems/maximum-number-of-upgradable-servers)

[English Version](/solution/3100-3199/3155.Maximum%20Number%20of%20Upgradable%20Servers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>You have <code>n</code> data centers and need to upgrade their servers.</p>

<p>You are given four arrays <code>count</code>, <code>upgrade</code>, <code>sell</code>, and <code>money</code> of length <code>n</code>, which show:</p>

<ul>
	<li>The number of servers</li>
	<li>The cost of upgrading a single server</li>
	<li>The money you get by selling a server</li>
	<li>The money you initially have</li>
</ul>

<p>for each data center respectively.</p>

<p>Return an array <code>answer</code>, where for each data center, the corresponding element in <code>answer</code> represents the <strong>maximum</strong> number of servers that can be upgraded.</p>

<p>Note that the money from one data center <strong>cannot</strong> be used for another data center.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">count = [4,3], upgrade = [3,5], sell = [4,2], money = [8,9]</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,2]</span></p>

<p><strong>Explanation:</strong></p>

<p>For the first data center, if we sell one server, we&#39;ll have <code>8 + 4 = 12</code> units of money and we can upgrade the remaining 3 servers.</p>

<p>For the second data center, if we sell one server, we&#39;ll have <code>9 + 2 = 11</code> units of money and we can upgrade the remaining 2 servers.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">count = [1], upgrade = [2], sell = [1], money = [1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

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
            ans[i] = Math.min(count[i], (int) ((1L * count[i] * sell[i] + money[i]) / (upgrade[i] + sell[i])));
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
