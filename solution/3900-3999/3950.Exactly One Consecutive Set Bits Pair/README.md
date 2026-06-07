---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3950.Exactly%20One%20Consecutive%20Set%20Bits%20Pair/README.md
---

<!-- problem:start -->

# [3950. 恰好一对连续置位](https://leetcode.cn/problems/exactly-one-consecutive-set-bits-pair)

[English Version](/solution/3900-3999/3950.Exactly%20One%20Consecutive%20Set%20Bits%20Pair/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code> 。</p>

<p>如果其二进制表示中 <strong>恰好 </strong>仅包含 <strong>一对</strong> <strong>连续的置位</strong> ，则返回 <code>true</code> ，否则返回 <code>false</code> 。</p>
整数中的 <strong>置位</strong> 是指其 <strong>二进制</strong> 表示中的 <code>1</code> 。

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = 6</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>6 的二进制表示为 <code>110</code> 。</li>
	<li>恰好存在一对连续的置位（<code>"11"</code>）。因此，答案为 <code>true</code> 。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = 5</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>5 的二进制表示为 <code>101</code> 。</li>
	<li>不存在连续的置位。因此，答案为 <code>false</code> 。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们用一个变量 $\textit{pre}$ 记录上一个位的数字，初始时 $\textit{pre} = 0$，用另一个变量 $\textit{vis}$ 记录是否已经找到一对连续置位，初始时 $\textit{vis} = \text{false}$。

遍历 $n$ 的每个二进制位，记当前二进制位为 $\textit{cur}$。如果 $\textit{pre} = \textit{cur} = 1$，此时如果 $\textit{vis} = \text{true}$，说明存在多对连续置位，直接返回 $\text{false}$，否则我们将 $\textit{vis}$ 置为 $\text{true}$。然后我们更新 $\textit{pre} = \textit{cur}$，继续遍历下个二进制位。

遍历结束后，如果 $\textit{vis} = \text{true}$，返回 $\text{true}$，否则返回 $\text{false}$。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def consecutiveSetBits(self, n: int) -> bool:
        pre = 0
        vis = False
        while n:
            cur = n & 1
            if pre == cur == 1:
                if vis:
                    return False
                vis = True
            pre = cur
            n = n >> 1
        return vis
```

#### Java

```java
class Solution {
    public boolean consecutiveSetBits(int n) {
        boolean vis = false;
        for (int pre = 0; n > 0; n >>= 1) {
            int cur = n & 1;
            if (pre == cur && cur == 1) {
                if (vis) {
                    return false;
                }
                vis = true;
            }
            pre = cur;
        }
        return vis;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool consecutiveSetBits(int n) {
        bool vis = false;
        for (int pre = 0; n > 0; n >>= 1) {
            int cur = n & 1;
            if (pre == cur && cur == 1) {
                if (vis) {
                    return false;
                }
                vis = true;
            }
            pre = cur;
        }
        return vis;
    }
};
```

#### Go

```go
func consecutiveSetBits(n int) bool {
	vis := false
	for pre := 0; n > 0; n >>= 1 {
		cur := n & 1
		if pre == cur && cur == 1 {
			if vis {
				return false
			}
			vis = true
		}
		pre = cur
	}
	return vis
}
```

#### TypeScript

```ts
function consecutiveSetBits(n: number): boolean {
    let vis = false;
    for (let pre = 0; n > 0; n >>= 1) {
        const cur = n & 1;
        if (pre === cur && cur === 1) {
            if (vis) {
                return false;
            }
            vis = true;
        }
        pre = cur;
    }
    return vis;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
