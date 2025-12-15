---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0342.Power%20of%20Four/README.md
tags:
    - 位运算
    - 递归
    - 数学
---

<!-- problem:start -->

# [342. 4的幂](https://leetcode.cn/problems/power-of-four)

[English Version](/solution/0300-0399/0342.Power%20of%20Four/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>整数 <code>n</code> 是 4 的幂次方需满足：存在整数 <code>x</code> 使得 <code>n == 4<sup>x</sup></code></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 16
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 5
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能不使用循环或者递归来完成本题吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

如果一个数是 $4$ 的幂次方，那么这个数必须是大于 $0$ 的。不妨假设这个数是 $4^x$，即 $2^{2x}$，那么这个数的二进制表示中有且仅有一个 $1$，且这个 $1$ 出现在偶数位上。

因此，我们首先判断这个数是否大于 $0$，然后判断这个数是否是 $2^{2x}$，即 $n$ 与 $n-1$ 的按位与结果是否为 $0$，最后判断这个数的 $1$ 是否出现在偶数位上，即 $n$ 与 $\textit{0xAAAAAAAA}$ 的按位与结果是否为 $0$。如果这三个条件都满足，那么这个数就是 $4$ 的幂次方。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPowerOfFour(self, n: int) -> bool:
        return n > 0 and (n & (n - 1)) == 0 and (n & 0xAAAAAAAA) == 0
```

#### Java

```java
class Solution {
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }
};
```

#### Go

```go
func isPowerOfFour(n int) bool {
	return n > 0 && (n&(n-1)) == 0 && (n&0xaaaaaaaa) == 0
}
```

#### TypeScript

```ts
function isPowerOfFour(n: number): boolean {
    return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_power_of_four(n: i32) -> bool {
        n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa_u32 as i32) == 0
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {boolean}
 */
var isPowerOfFour = function (n) {
    return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
};
```

#### C#

```cs
public class Solution {
    public bool IsPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
