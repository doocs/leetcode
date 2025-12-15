---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2483.Minimum%20Penalty%20for%20a%20Shop/README_EN.md
rating: 1494
source: Biweekly Contest 92 Q3
tags:
    - String
    - Prefix Sum
---

<!-- problem:start -->

# [2483. Minimum Penalty for a Shop](https://leetcode.com/problems/minimum-penalty-for-a-shop)

[中文文档](/solution/2400-2499/2483.Minimum%20Penalty%20for%20a%20Shop/README.md)

## Description

<!-- description:start -->

<p>You are given the customer visit log of a shop represented by a <strong>0-indexed</strong> string <code>customers</code> consisting only of characters <code>&#39;N&#39;</code> and <code>&#39;Y&#39;</code>:</p>

<ul>
	<li>if the <code>i<sup>th</sup></code> character is <code>&#39;Y&#39;</code>, it means that customers come at the <code>i<sup>th</sup></code> hour</li>
	<li>whereas <code>&#39;N&#39;</code> indicates that no customers come at the <code>i<sup>th</sup></code> hour.</li>
</ul>

<p>If the shop closes at the <code>j<sup>th</sup></code> hour (<code>0 &lt;= j &lt;= n</code>), the <strong>penalty</strong> is calculated as follows:</p>

<ul>
	<li>For every hour when the shop is open and no customers come, the penalty increases by <code>1</code>.</li>
	<li>For every hour when the shop is closed and customers come, the penalty increases by <code>1</code>.</li>
</ul>

<p>Return<em> the <strong>earliest</strong> hour at which the shop must be closed to incur a <strong>minimum</strong> penalty.</em></p>

<p><strong>Note</strong> that if a shop closes at the <code>j<sup>th</sup></code> hour, it means the shop is closed at the hour <code>j</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> customers = &quot;YYNY&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
- Closing the shop at the 0<sup>th</sup> hour incurs in 1+1+0+1 = 3 penalty.
- Closing the shop at the 1<sup>st</sup> hour incurs in 0+1+0+1 = 2 penalty.
- Closing the shop at the 2<sup>nd</sup> hour incurs in 0+0+0+1 = 1 penalty.
- Closing the shop at the 3<sup>rd</sup> hour incurs in 0+0+1+1 = 2 penalty.
- Closing the shop at the 4<sup>th</sup> hour incurs in 0+0+1+0 = 1 penalty.
Closing the shop at 2<sup>nd</sup> or 4<sup>th</sup> hour gives a minimum penalty. Since 2 is earlier, the optimal closing time is 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> customers = &quot;NNNNN&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> It is best to close the shop at the 0<sup>th</sup> hour as no customers arrive.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> customers = &quot;YYYY&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> It is best to close the shop at the 4<sup>th</sup> hour as customers arrive at each hour.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= customers.length &lt;= 10<sup>5</sup></code></li>
	<li><code>customers</code> consists only of characters <code>&#39;Y&#39;</code> and <code>&#39;N&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

If the shop closes at hour $0$, then the cost is the number of character `'Y'` in $\textit{customers}$. We initialize the answer variable $\textit{ans}$ to $0$, and the cost variable $\textit{cost}$ to the number of character `'Y'` in $\textit{customers}$.

Next, we enumerate the shop closing at hour $j$ ($1 \leq j \leq n$). If $\textit{customers}[j - 1]$ is `'N'`, it means no customer arrived during the open period, and the cost increases by $1$; otherwise, it means a customer arrived during the closed period, and the cost decreases by $1$. If the current cost $\textit{cost}$ is less than the minimum cost $\textit{mn}$, we update the answer variable $\textit{ans}$ to $j$, and update the minimum cost $\textit{mn}$ to the current cost $\textit{cost}$.

After the traversal ends, we return the answer variable $\textit{ans}$.

The time complexity is $O(n)$, where $n$ is the length of the string $\textit{customers}$. The space complexity is $O(1)$.

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
