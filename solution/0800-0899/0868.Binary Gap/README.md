---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0868.Binary%20Gap/README.md
tags:
    - 位运算
---

<!-- problem:start -->

# [868. 二进制间距](https://leetcode.cn/problems/binary-gap)

[English Version](/solution/0800-0899/0868.Binary%20Gap/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个正整数 <code>n</code>，找到并返回 <code>n</code> 的二进制表示中两个 <strong>相邻</strong> 1 之间的<strong> 最长距离 </strong>。如果不存在两个相邻的 1，返回 <code>0</code> 。</p>

<p>如果只有 <code>0</code> 将两个 <code>1</code> 分隔开（可能不存在 <code>0</code> ），则认为这两个 1 彼此 <strong>相邻</strong> 。两个 <code>1</code> 之间的距离是它们的二进制表示中位置的绝对差。例如，<code>"1001"</code> 中的两个 <code>1</code> 的距离为 3 。</p>

<p>&nbsp;</p>

<ul>
</ul>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 22
<strong>输出：</strong>2
<strong>解释：</strong>22 的二进制是 "10110" 。
在 22 的二进制表示中，有三个 1，组成两对相邻的 1 。
第一对相邻的 1 中，两个 1 之间的距离为 2 。
第二对相邻的 1 中，两个 1 之间的距离为 1 。
答案取两个距离之中最大的，也就是 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 8
<strong>输出：</strong>0
<strong>解释：</strong>8 的二进制是 "1000" 。
在 8 的二进制表示中没有相邻的两个 1，所以返回 0 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 5
<strong>输出：</strong>2
<strong>解释：</strong>5 的二进制是 "101" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

我们用两个指针 $\textit{pre}$ 和 $\textit{cur}$ 分别表示上一个和当前的 $1$ 的位置，初始时 $\textit{pre} = 100$, $\textit{cur} = 0$，然后遍历 $n$ 的二进制表示，当遇到 $1$ 时，计算当前位置和上一个 $1$ 的位置之间的距离，并更新答案。

时间复杂度 $O(\log n)$，其中 $n$ 是题目给定的整数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def binaryGap(self, n: int) -> int:
        ans = 0
        pre, cur = inf, 0
        while n:
            if n & 1:
                ans = max(ans, cur - pre)
                pre = cur
            cur += 1
            n >>= 1
        return ans
```

#### Java

```java
class Solution {
    public int binaryGap(int n) {
        int ans = 0;
        for (int pre = 100, cur = 0; n != 0; n >>= 1) {
            if (n % 2 == 1) {
                ans = Math.max(ans, cur - pre);
                pre = cur;
            }
            ++cur;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int binaryGap(int n) {
        int ans = 0;
        for (int pre = 100, cur = 0; n != 0; n >>= 1) {
            if (n & 1) {
                ans = max(ans, cur - pre);
                pre = cur;
            }
            ++cur;
        }
        return ans;
    }
};
```

#### Go

```go
func binaryGap(n int) (ans int) {
	for pre, cur := 100, 0; n != 0; n >>= 1 {
		if n&1 == 1 {
			ans = max(ans, cur-pre)
			pre = cur
		}
		cur++
	}
	return
}
```

#### TypeScript

```ts
function binaryGap(n: number): number {
    let ans = 0;
    for (let pre = 100, cur = 0; n; n >>= 1) {
        if (n & 1) {
            ans = Math.max(ans, cur - pre);
            pre = cur;
        }
        ++cur;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn binary_gap(mut n: i32) -> i32 {
        let mut ans = 0;
        let mut pre = 100;
        let mut cur = 0;
        while n != 0 {
            if n % 2 == 1 {
                ans = ans.max(cur - pre);
                pre = cur;
            }
            cur += 1;
            n >>= 1;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
