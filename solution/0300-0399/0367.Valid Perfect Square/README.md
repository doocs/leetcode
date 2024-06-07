---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0367.Valid%20Perfect%20Square/README.md
tags:
    - 数学
    - 二分查找
---

<!-- problem:start -->

# [367. 有效的完全平方数](https://leetcode.cn/problems/valid-perfect-square)

[English Version](/solution/0300-0399/0367.Valid%20Perfect%20Square/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数 <code>num</code> 。如果 <code>num</code> 是一个完全平方数，则返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p><strong>完全平方数</strong> 是一个可以写成某个整数的平方的整数。换句话说，它可以写成某个整数和自身的乘积。</p>

<p>不能使用任何内置的库函数，如&nbsp; <code>sqrt</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>num = 16
<strong>输出：</strong>true
<strong>解释：</strong>返回 true ，因为 4 * 4 = 16 且 4 是一个整数。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>num = 14
<strong>输出：</strong>false
<strong>解释：</strong>返回 false ，因为 3.742 * 3.742 = 14 但 3.742 不是一个整数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

我们可以使用二分查找来解决这个问题。定义二分查找的左边界 $l = 1$，右边界 $r = num$，然后在 $[l, r]$ 的范围内查找满足 $x^2 \geq num$ 的最小整数 $x$。最后，如果 $x^2 = num$，则说明 $num$ 是一个完全平方数。

时间复杂度 $O(\log n)$，其中 $n$ 是给定的数字。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPerfectSquare(self, num: int) -> bool:
        l = bisect_left(range(1, num + 1), num, key=lambda x: x * x) + 1
        return l * l == num
```

#### Java

```java
class Solution {
    public boolean isPerfectSquare(int num) {
        int l = 1, r = num;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (1L * mid * mid >= num) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l * l == num;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPerfectSquare(int num) {
        int l = 1, r = num;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (1LL * mid * mid >= num) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return 1LL * l * l == num;
    }
};
```

#### Go

```go
func isPerfectSquare(num int) bool {
	l := sort.Search(num, func(i int) bool { return i*i >= num })
	return l*l == num
}
```

#### TypeScript

```ts
function isPerfectSquare(num: number): boolean {
    let [l, r] = [1, num];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (mid >= num / mid) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l * l === num;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_perfect_square(num: i32) -> bool {
        let mut l = 1;
        let mut r = num as i64;
        while l < r {
            let mid = (l + r) / 2;
            if mid * mid >= (num as i64) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        l * l == (num as i64)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：数学

由于 $1 + 3 + 5 + \cdots + (2n - 1) = n^2$，我们可以将 $num$ 逐渐减去 $1, 3, 5, \cdots$，如果最后 $num = 0$，则说明 $num$ 是一个完全平方数。

时间复杂度 $O(\sqrt n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPerfectSquare(self, num: int) -> bool:
        i = 1
        while num > 0:
            num -= i
            i += 2
        return num == 0
```

#### Java

```java
class Solution {
    public boolean isPerfectSquare(int num) {
        for (int i = 1; num > 0; i += 2) {
            num -= i;
        }
        return num == 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPerfectSquare(int num) {
        for (int i = 1; num > 0; i += 2) {
            num -= i;
        }
        return num == 0;
    }
};
```

#### Go

```go
func isPerfectSquare(num int) bool {
	for i := 1; num > 0; i += 2 {
		num -= i
	}
	return num == 0
}
```

#### TypeScript

```ts
function isPerfectSquare(num: number): boolean {
    let i = 1;
    while (num > 0) {
        num -= i;
        i += 2;
    }
    return num === 0;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_perfect_square(mut num: i32) -> bool {
        let mut i = 1;
        while num > 0 {
            num -= i;
            i += 2;
        }
        num == 0
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
