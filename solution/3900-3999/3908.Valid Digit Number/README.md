---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3908.Valid%20Digit%20Number/README.md
---

<!-- problem:start -->

# [3908. 有效数字](https://leetcode.cn/problems/valid-digit-number)

[English Version](/solution/3900-3999/3908.Valid%20Digit%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code> 和一个数字 <code>x</code>。</p>

<p>如果一个数字满足以下条件，则认为它是 <strong>有效</strong> 的：</p>

<ul>
	<li>它包含 <strong>至少一个</strong> 数字 <code>x</code>，并且</li>
	<li>它 <strong>不以</strong> 数字 <code>x</code> 开头。</li>
</ul>

<p>如果 <code>n</code> 是 <strong>有效</strong> 的，请返回 <code>true</code>，否则返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 101, x = 0</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<p>该数字在下标 1 处包含数字 0。它不以 0 开头，因此满足两个条件。所以，答案是 <code>true</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 232, x = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<p>该数字以 2 开头，违反了条件。所以，答案是 <code>false</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 5, x = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<p>该数字不包含数字 1。所以，答案是 <code>false</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= x &lt;= 9</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们用一个布尔变量 $\textit{hasX}$ 来记录数字 $x$ 是否出现在 $n$ 中。

我们不断地取 $n$ 的最后一位数字，并与 $x$ 进行比较，如果相等，则将 $\textit{hasX}$ 置为 $\texttt{true}$。同时，我们将 $n$ 除以 $10$ 来去掉最后一位数字。当 $n$ 小于或等于 $9$ 时，说明我们已经检查了所有的数字，此时如果 $\textit{hasX}$ 为 $\texttt{true}$ 且 $n$ 不等于 $x$，则说明 $n$ 是一个有效数字，返回 $\texttt{true}$；否则返回 $\texttt{false}$。

时间复杂度 $O(\log n)$，其中 $n$ 是输入的整数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def validDigit(self, n: int, x: int) -> bool:
        has_x = False
        while n > 9:
            has_x = has_x or n % 10 == x
            n //= 10
        return has_x and n != x
```

#### Java

```java
class Solution {
    public boolean validDigit(int n, int x) {
        boolean hasX = false;
        while (n > 9) {
            hasX = hasX || (n % 10 == x);
            n /= 10;
        }
        return hasX && (n != x);
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool validDigit(int n, int x) {
        bool hasX = false;
        while (n > 9) {
            hasX = hasX || (n % 10 == x);
            n /= 10;
        }
        return hasX && (n != x);
    }
};
```

#### Go

```go
func validDigit(n int, x int) bool {
	hasX := false
	for n > 9 {
		hasX = hasX || (n%10 == x)
		n /= 10
	}
	return hasX && (n != x)
}
```

#### TypeScript

```ts
function validDigit(n: number, x: number): boolean {
    let hasX: boolean = false;
    while (n > 9) {
        hasX = hasX || n % 10 === x;
        n = Math.floor(n / 10);
    }
    return hasX && n !== x;
}
```

#### Rust

```rust
impl Solution {
    pub fn valid_digit(mut n: i32, x: i32) -> bool {
        let mut has_x = false;
        while n > 9 {
            has_x = has_x || (n % 10 == x);
            n /= 10;
        }
        has_x && (n != x)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
