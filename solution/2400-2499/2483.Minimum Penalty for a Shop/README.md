---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2483.Minimum%20Penalty%20for%20a%20Shop/README.md
rating: 1494
source: 第 92 场双周赛 Q3
tags:
    - 字符串
    - 前缀和
---

<!-- problem:start -->

# [2483. 商店的最少代价](https://leetcode.cn/problems/minimum-penalty-for-a-shop)

[English Version](/solution/2400-2499/2483.Minimum%20Penalty%20for%20a%20Shop/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个顾客访问商店的日志，用一个下标从 <strong>0</strong>&nbsp;开始且只包含字符&nbsp;<code>'N'</code> 和&nbsp;<code>'Y'</code>&nbsp;的字符串&nbsp;<code>customers</code>&nbsp;表示：</p>

<ul>
	<li>如果第&nbsp;<code>i</code>&nbsp;个字符是&nbsp;<code>'Y'</code>&nbsp;，它表示第&nbsp;<code>i</code>&nbsp;小时有顾客到达。</li>
	<li>如果第&nbsp;<code>i</code>&nbsp;个字符是&nbsp;<code>'N'</code>&nbsp;，它表示第 <code>i</code>&nbsp;小时没有顾客到达。</li>
</ul>

<p>如果商店在第&nbsp;<code>j</code>&nbsp;小时关门（<code>0 &lt;= j &lt;= n</code>），代价按如下方式计算：</p>

<ul>
	<li>在开门期间，如果某一个小时没有顾客到达，代价增加 <code>1</code>&nbsp;。</li>
	<li>在关门期间，如果某一个小时有顾客到达，代价增加&nbsp;<code>1</code>&nbsp;。</li>
</ul>

<p>请你返回在确保代价 <strong>最小</strong>&nbsp;的前提下，商店的&nbsp;<strong>最早</strong>&nbsp;关门时间。</p>

<p>注意，商店在第 <code>j</code>&nbsp;小时关门表示在第 <code>j</code> 小时以及之后商店处于关门状态。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>customers = "YYNY"
<b>输出：</b>2
<b>解释：</b>
- 第 0 小时关门，总共 1+1+0+1 = 3 代价。
- 第 1 小时关门，总共 0+1+0+1 = 2 代价。
- 第 2 小时关门，总共 0+0+0+1 = 1 代价。
- 第 3 小时关门，总共 0+0+1+1 = 2 代价。
- 第 4 小时关门，总共 0+0+1+0 = 1 代价。
在第 2 或第 4 小时关门代价都最小。由于第 2 小时更早，所以最优关门时间是 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>customers = "NNNNN"
<b>输出：</b>0
<b>解释：</b>最优关门时间是 0 ，因为自始至终没有顾客到达。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>customers = "YYYY"
<b>输出：</b>4
<b>解释：</b>最优关门时间是 4 ，因为每一小时均有顾客到达。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= customers.length &lt;= 10<sup>5</sup></code></li>
	<li><code>customers</code>&nbsp;只包含字符&nbsp;<code>'Y'</code>&nbsp;和&nbsp;<code>'N'</code>&nbsp;。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

如果商店在第 $0$ 小时关门，那么代价为 $\textit{customers}$ 中字符 `'Y'` 的数量，我们初始化答案变量 $\textit{ans}$ 为 $0$，代价变量 $\textit{cost}$ 为 $\textit{customers}$ 中字符 `'Y'` 的数量。

接下来，我们枚举商店在第 $j$ 小时关门（$1 \leq j \leq n$）。如果 $\textit{customers}[j - 1]$ 为 `'N'`，说明在开门期间没有顾客到达，代价增加 $1$；否则说明在关门期间有顾客到达，代价减少 $1$。如果当前代价 $\textit{cost}$ 小于最小代价 $\textit{mn}$，我们将答案变量 $\textit{ans}$ 更新为 $j$，并将最小代价 $\textit{mn}$ 更新为当前代价 $\textit{cost}$。

遍历结束后，返回答案变量 $\textit{ans}$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $\textit{customers}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def bestClosingTime(self, customers: str) -> int:
        ans = 0
        mn = cost = customers.count("Y")
        for j, c in enumerate(customers, 1):
            cost += 1 if c == "N" else -1
            if cost < mn:
                ans, mn = j, cost
        return ans
```

#### Java

```java
class Solution {
    public int bestClosingTime(String customers) {
        int n = customers.length();
        int ans = 0, cost = 0;
        for (int i = 0; i < n; i++) {
            if (customers.charAt(i) == 'Y') {
                cost++;
            }
        }
        int mn = cost;
        for (int j = 1; j <= n; j++) {
            cost += customers.charAt(j - 1) == 'N' ? 1 : -1;
            if (cost < mn) {
                ans = j;
                mn = cost;
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
    int bestClosingTime(string customers) {
        int ans = 0;
        int cost = 0;
        for (char c : customers) {
            if (c == 'Y') {
                cost++;
            }
        }
        int mn = cost;
        for (int j = 1; j <= customers.size(); ++j) {
            cost += customers[j - 1] == 'N' ? 1 : -1;
            if (cost < mn) {
                ans = j;
                mn = cost;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func bestClosingTime(customers string) int {
	ans := 0
	cost := strings.Count(customers, "Y")
	mn := cost
	for j := 1; j <= len(customers); j++ {
		c := customers[j-1]
		if c == 'N' {
			cost++
		} else {
			cost--
		}
		if cost < mn {
			ans = j
			mn = cost
		}
	}
	return ans
}
```

#### TypeScript

```ts
function bestClosingTime(customers: string): number {
    let ans = 0;
    let cost = 0;
    for (const ch of customers) {
        if (ch === 'Y') {
            cost++;
        }
    }
    let mn = cost;

    for (let j = 1; j <= customers.length; j++) {
        const c = customers[j - 1];
        cost += c === 'N' ? 1 : -1;
        if (cost < mn) {
            mn = cost;
            ans = j;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn best_closing_time(customers: String) -> i32 {
        let bytes = customers.as_bytes();

        let mut cost: i32 = bytes.iter().filter(|&&c| c == b'Y').count() as i32;
        let mut mn = cost;
        let mut ans: i32 = 0;

        for j in 1..=bytes.len() {
            let c = bytes[j - 1];
            if c == b'N' {
                cost += 1;
            } else {
                cost -= 1;
            }
            if cost < mn {
                mn = cost;
                ans = j as i32;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
