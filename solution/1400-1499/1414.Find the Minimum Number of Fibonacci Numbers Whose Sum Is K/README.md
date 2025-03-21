---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1414.Find%20the%20Minimum%20Number%20of%20Fibonacci%20Numbers%20Whose%20Sum%20Is%20K/README.md
rating: 1465
source: 第 24 场双周赛 Q2
tags:
    - 贪心
    - 数学
---

<!-- problem:start -->

# [1414. 和为 K 的最少斐波那契数字数目](https://leetcode.cn/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k)

[English Version](/solution/1400-1499/1414.Find%20the%20Minimum%20Number%20of%20Fibonacci%20Numbers%20Whose%20Sum%20Is%20K/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你数字 <code>k</code>&nbsp;，请你返回和为&nbsp;<code>k</code>&nbsp;的斐波那契数字的最少数目，其中，每个斐波那契数字都可以被使用多次。</p>

<p>斐波那契数字定义为：</p>

<ul>
	<li>F<sub>1</sub> = 1</li>
	<li>F<sub>2</sub> = 1</li>
	<li>F<sub>n</sub> = F<sub>n-1</sub> + F<sub>n-2</sub>&nbsp;， 其中 n &gt; 2 。</li>
</ul>

<p>数据保证对于给定的 <code>k</code>&nbsp;，一定能找到可行解。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>k = 7
<strong>输出：</strong>2 
<strong>解释：</strong>斐波那契数字为：1，1，2，3，5，8，13，&hellip;&hellip;
对于 k = 7 ，我们可以得到 2 + 5 = 7 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>k = 10
<strong>输出：</strong>2 
<strong>解释：</strong>对于 k = 10 ，我们可以得到 2 + 8 = 10 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>k = 19
<strong>输出：</strong>3 
<strong>解释：</strong>对于 k = 19 ，我们可以得到 1 + 5 + 13 = 19 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10^9</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

我们可以每次贪心地选取一个不超过 $k$ 的最大的斐波那契数，然后将 $k$ 减去该数，答案加一，一直循环，直到 $k = 0$ 为止。

由于每次贪心地选取了最大的不超过 $k$ 的斐波那契数，假设为 $b$，前一个数为 $a$，后一个数为 $c$。将 $k$ 减去 $b$，得到的结果，一定小于 $a$，也即意味着，我们选取了 $b$ 之后，一定不会选到 $a$。这是因为，如果能选上 $a$，那么我们在前面就可以贪心地选上 $b$ 的下一个斐波那契数 $c$，这不符合我们的假设。因此，我们在选取 $b$ 之后，可以贪心地减小斐波那契数。

时间复杂度 $O(\log k)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMinFibonacciNumbers(self, k: int) -> int:
        a = b = 1
        while b <= k:
            a, b = b, a + b
        ans = 0
        while k:
            if k >= b:
                k -= b
                ans += 1
            a, b = b - a, a
        return ans
```

#### Java

```java
class Solution {
    public int findMinFibonacciNumbers(int k) {
        int a = 1, b = 1;
        while (b <= k) {
            int c = a + b;
            a = b;
            b = c;
        }
        int ans = 0;
        while (k > 0) {
            if (k >= b) {
                k -= b;
                ++ans;
            }
            int c = b - a;
            b = a;
            a = c;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findMinFibonacciNumbers(int k) {
        int a = 1, b = 1;
        while (b <= k) {
            int c = a + b;
            a = b;
            b = c;
        }
        int ans = 0;
        while (k > 0) {
            if (k >= b) {
                k -= b;
                ++ans;
            }
            int c = b - a;
            b = a;
            a = c;
        }
        return ans;
    }
};
```

#### Go

```go
func findMinFibonacciNumbers(k int) (ans int) {
	a, b := 1, 1
	for b <= k {
		c := a + b
		a = b
		b = c
	}

	for k > 0 {
		if k >= b {
			k -= b
			ans++
		}
		c := b - a
		b = a
		a = c
	}
	return
}
```

#### TypeScript

```ts
function findMinFibonacciNumbers(k: number): number {
    let [a, b] = [1, 1];
    while (b <= k) {
        let c = a + b;
        a = b;
        b = c;
    }

    let ans = 0;
    while (k > 0) {
        if (k >= b) {
            k -= b;
            ans++;
        }
        let c = b - a;
        b = a;
        a = c;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_min_fibonacci_numbers(mut k: i32) -> i32 {
        let mut a = 1;
        let mut b = 1;
        while b <= k {
            let c = a + b;
            a = b;
            b = c;
        }

        let mut ans = 0;
        while k > 0 {
            if k >= b {
                k -= b;
                ans += 1;
            }
            let c = b - a;
            b = a;
            a = c;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
