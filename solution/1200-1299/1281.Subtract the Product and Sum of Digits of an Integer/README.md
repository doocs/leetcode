---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1281.Subtract%20the%20Product%20and%20Sum%20of%20Digits%20of%20an%20Integer/README.md
rating: 1141
source: 第 166 场周赛 Q1
tags:
    - 数学
---

<!-- problem:start -->

# [1281. 整数的各位积和之差](https://leetcode.cn/problems/subtract-the-product-and-sum-of-digits-of-an-integer)

[English Version](/solution/1200-1299/1281.Subtract%20the%20Product%20and%20Sum%20of%20Digits%20of%20an%20Integer/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数&nbsp;<code>n</code>，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 234
<strong>输出：</strong>15
<strong>解释：</strong>
各位数之积 = 2 * 3 * 4 = 24
各位数之和 = 2 + 3 + 4 = 9
结果 = 24 - 9 = 15
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 4421
<strong>输出：</strong>21
<strong>解释：
</strong>各位数之积 = 4 * 4 * 2 * 1 = 32
各位数之和 = 4 + 4 + 2 + 1 = 11
结果 = 32 - 11 = 21
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们用两个变量 $x$ 和 $y$ 分别记录各位数之积、各位数之和，初始时 $x=1,y=0$。

当 $n \gt 0$ 时，每次将 $n$ 对 $10$ 取模得到当前位的数字 $v$，并将 $n$ 除以 $10$ 后继续进行下一次循环。在每次循环中，我们更新 $x = x \times v$, $y = y + v$。

最终，我们返回 $x - y$ 即可。

时间复杂度 $O(\log n)$，其中 $n$ 是题目给定的整数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def subtractProductAndSum(self, n: int) -> int:
        nums = list(map(int, str(n)))
        return prod(nums) - sum(nums)
```

#### Java

```java
class Solution {
    public int subtractProductAndSum(int n) {
        int x = 1, y = 0;
        for (; n > 0; n /= 10) {
            int v = n % 10;
            x *= v;
            y += v;
        }
        return x - y;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int subtractProductAndSum(int n) {
        int x = 1, y = 0;
        for (; n; n /= 10) {
            int v = n % 10;
            x *= v;
            y += v;
        }
        return x - y;
    }
};
```

#### Go

```go
func subtractProductAndSum(n int) int {
	x, y := 1, 0
	for ; n > 0; n /= 10 {
		v := n % 10
		x *= v
		y += v
	}
	return x - y
}
```

#### TypeScript

```ts
function subtractProductAndSum(n: number): number {
    let [x, y] = [1, 0];
    for (; n > 0; n = Math.floor(n / 10)) {
        const v = n % 10;
        x *= v;
        y += v;
    }
    return x - y;
}
```

#### Rust

```rust
impl Solution {
    pub fn subtract_product_and_sum(mut n: i32) -> i32 {
        let mut x = 1;
        let mut y = 0;
        while n != 0 {
            let v = n % 10;
            n /= 10;
            x *= v;
            y += v;
        }
        x - y
    }
}
```

#### C#

```cs
public class Solution {
    public int SubtractProductAndSum(int n) {
        int x = 1;
        int y = 0;
        for (; n > 0; n /= 10) {
            int v = n % 10;
            x *= v;
            y += v;
        }
        return x - y;
    }
}
```

#### C

```c
int subtractProductAndSum(int n) {
    int x = 1;
    int y = 0;
    for (; n > 0; n /= 10) {
        int v = n % 10;
        x *= v;
        y += v;
    }
    return x - y;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
