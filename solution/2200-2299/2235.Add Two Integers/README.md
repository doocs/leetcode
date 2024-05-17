---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2235.Add%20Two%20Integers/README.md
tags:
    - 数学
---

<!-- problem:start -->

# [2235. 两整数相加](https://leetcode.cn/problems/add-two-integers)

[English Version](/solution/2200-2299/2235.Add%20Two%20Integers/README_EN.md)

## 题目描述

<!-- description:start -->

给你两个整数&nbsp;<code>num1</code> 和 <code>num2</code>，返回这两个整数的和。

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num1 = 12, num2 = 5
<strong>输出：</strong>17
<strong>解释：</strong>num1 是 12，num2 是 5 ，它们的和是 12 + 5 = 17 ，因此返回 17 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num1 = -10, num2 = 4
<strong>输出：</strong>-6
<strong>解释：</strong>num1 + num2 = -6 ，因此返回 -6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-100 &lt;= num1, num2 &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：使用加法运算符

我们可以直接使用加法运算符 `+` 来计算两个整数的和。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def sum(self, num1: int, num2: int) -> int:
        return num1 + num2
```

```java
class Solution {
    public int sum(int num1, int num2) {
        return num1 + num2;
    }
}
```

```cpp
class Solution {
public:
    int sum(int num1, int num2) {
        return num1 + num2;
    }
};
```

```go
func sum(num1 int, num2 int) int {
	return num1 + num2
}
```

```ts
function sum(num1: number, num2: number): number {
    return num1 + num2;
}
```

```rust
impl Solution {
    pub fn sum(num1: i32, num2: i32) -> i32 {
        num1 + num2
    }
}
```

```c
int sum(int num1, int num2) {
    return num1 + num2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：位运算（不使用加法运算符）

我们也可以在不使用加法运算符的前提下，使用位运算来计算两个整数的和。

假设 $num1_i$ 和 $num2_i$ 分别表示 $num1$ 和 $num2$ 的第 $i$ 个二进制位。一共有 $4$ 种情况：

| $num1_i$ | $num2_i$ | 不进位的和 | 进位 |
| -------- | -------- | ---------- | ---- |
| 0        | 0        | 0          | 0    |
| 0        | 1        | 1          | 0    |
| 1        | 0        | 1          | 0    |
| 1        | 1        | 0          | 1    |

观察可以发现，“不进位的和”与“异或运算”有相同规律，而进位则与“与”运算规律相同，并且需要左移一位。

因此：

-   对两数进行按位 `&` 与运算，然后左移一位，得到进位，记为 $carry$；
-   对两数进行按位 `^` 异或运算，得到不进位的和；
-   问题转换为求：“不进位的数 + 进位” 之和；
-   循环，直至第二个数为 $0$，返回第一个数即可（也可以用递归实现）。

时间复杂度 $O(\log M)$，其中 $M$ 为题目中数字的最大值。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def sum(self, num1: int, num2: int) -> int:
        num1, num2 = num1 & 0xFFFFFFFF, num2 & 0xFFFFFFFF
        while num2:
            carry = ((num1 & num2) << 1) & 0xFFFFFFFF
            num1, num2 = num1 ^ num2, carry
        return num1 if num1 < 0x80000000 else ~(num1 ^ 0xFFFFFFFF)
```

```java
class Solution {
    public int sum(int num1, int num2) {
        while (num2 != 0) {
            int carry = (num1 & num2) << 1;
            num1 ^= num2;
            num2 = carry;
        }
        return num1;
    }
}
```

```cpp
class Solution {
public:
    int sum(int num1, int num2) {
        while (num2) {
            unsigned int carry = (unsigned int) (num1 & num2) << 1;
            num1 ^= num2;
            num2 = carry;
        }
        return num1;
    }
};
```

```go
func sum(num1 int, num2 int) int {
	for num2 != 0 {
		carry := (num1 & num2) << 1
		num1 ^= num2
		num2 = carry
	}
	return num1
}
```

```ts
function sum(num1: number, num2: number): number {
    while (num2) {
        const carry = (num1 & num2) << 1;
        num1 ^= num2;
        num2 = carry;
    }
    return num1;
}
```

```rust
impl Solution {
    pub fn sum(num1: i32, num2: i32) -> i32 {
        let mut num1 = num1;
        let mut num2 = num2;
        while num2 != 0 {
            let carry = (num1 & num2) << 1;
            num1 ^= num2;
            num2 = carry;
        }
        num1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
