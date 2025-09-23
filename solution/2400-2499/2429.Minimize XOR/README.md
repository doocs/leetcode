---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2429.Minimize%20XOR/README.md
rating: 1532
source: 第 313 场周赛 Q3
tags:
    - 贪心
    - 位运算
---

<!-- problem:start -->

# [2429. 最小异或](https://leetcode.cn/problems/minimize-xor)

[English Version](/solution/2400-2499/2429.Minimize%20XOR/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个正整数 <code>num1</code> 和 <code>num2</code> ，找出满足下述条件的正整数 <code>x</code> ：</p>

<ul>
	<li><code>x</code> 的置位数和 <code>num2</code> 相同，且</li>
	<li><code>x XOR num1</code> 的值 <strong>最小</strong></li>
</ul>

<p>注意 <code>XOR</code> 是按位异或运算。</p>

<p>返回整数<em> </em><code>x</code> 。题目保证，对于生成的测试用例， <code>x</code> 是 <strong>唯一确定</strong> 的。</p>

<p>整数的 <strong>置位数</strong> 是其二进制表示中 <code>1</code> 的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num1 = 3, num2 = 5
<strong>输出：</strong>3
<strong>解释：</strong>
num1 和 num2 的二进制表示分别是 0011 和 0101 。
整数 <strong>3</strong> 的置位数与 num2 相同，且 <code>3 XOR 3 = 0</code> 是最小的。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num1 = 1, num2 = 12
<strong>输出：</strong>3
<strong>解释：</strong>
num1 和 num2 的二进制表示分别是 0001 和 1100 。
整数 <strong>3</strong> 的置位数与 num2 相同，且 <code>3 XOR 1 = 2</code> 是最小的。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num1, num2 &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 位运算

根据题目描述，我们先求出 $\textit{nums2}$ 的置位数 $\textit{cnt}$，然后从高位到低位枚举 $\textit{num1}$ 的每一位，如果该位为 $1$，则将 $x$ 的对应位设为 $1$，并将 $\textit{cnt}$ 减 $1$，直到 $\textit{cnt}$ 为 $0$。如果此时 $\textit{cnt}$ 仍不为 $0$，则从低位开始将 $\textit{num1}$ 的每一位为 $0$ 的位置设为 $1$，并将 $\textit{cnt}$ 减 $1$，直到 $\textit{cnt}$ 为 $0$。

时间复杂度 $O(\log n)$，其中 $n$ 为 $\textit{num1}$ 和 $\textit{num2}$ 的最大值。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimizeXor(self, num1: int, num2: int) -> int:
        cnt = num2.bit_count()
        x = 0
        for i in range(30, -1, -1):
            if num1 >> i & 1 and cnt:
                x |= 1 << i
                cnt -= 1
        for i in range(30):
            if num1 >> i & 1 ^ 1 and cnt:
                x |= 1 << i
                cnt -= 1
        return x
```

#### Java

```java
class Solution {
    public int minimizeXor(int num1, int num2) {
        int cnt = Integer.bitCount(num2);
        int x = 0;
        for (int i = 30; i >= 0 && cnt > 0; --i) {
            if ((num1 >> i & 1) == 1) {
                x |= 1 << i;
                --cnt;
            }
        }
        for (int i = 0; cnt > 0; ++i) {
            if ((num1 >> i & 1) == 0) {
                x |= 1 << i;
                --cnt;
            }
        }
        return x;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimizeXor(int num1, int num2) {
        int cnt = __builtin_popcount(num2);
        int x = 0;
        for (int i = 30; ~i && cnt; --i) {
            if (num1 >> i & 1) {
                x |= 1 << i;
                --cnt;
            }
        }
        for (int i = 0; cnt; ++i) {
            if (num1 >> i & 1 ^ 1) {
                x |= 1 << i;
                --cnt;
            }
        }
        return x;
    }
};
```

#### Go

```go
func minimizeXor(num1 int, num2 int) int {
	cnt := bits.OnesCount(uint(num2))
	x := 0
	for i := 30; i >= 0 && cnt > 0; i-- {
		if num1>>i&1 == 1 {
			x |= 1 << i
			cnt--
		}
	}
	for i := 0; cnt > 0; i++ {
		if num1>>i&1 == 0 {
			x |= 1 << i
			cnt--
		}
	}
	return x
}
```

#### TypeScript

```ts
function minimizeXor(num1: number, num2: number): number {
    let cnt = 0;
    while (num2) {
        num2 &= num2 - 1;
        ++cnt;
    }
    let x = 0;
    for (let i = 30; i >= 0 && cnt > 0; --i) {
        if ((num1 >> i) & 1) {
            x |= 1 << i;
            --cnt;
        }
    }
    for (let i = 0; cnt > 0; ++i) {
        if (!((num1 >> i) & 1)) {
            x |= 1 << i;
            --cnt;
        }
    }
    return x;
}
```

#### Rust

```rust
impl Solution {
    pub fn minimize_xor(num1: i32, mut num2: i32) -> i32 {
        let mut cnt = 0;
        while num2 > 0 {
            num2 -= num2 & -num2;
            cnt += 1;
        }
        let mut x = 0;
        let mut c = cnt;
        for i in (0..=30).rev() {
            if c > 0 && (num1 >> i) & 1 == 1 {
                x |= 1 << i;
                c -= 1;
            }
        }
        for i in 0..=30 {
            if c == 0 {
                break;
            }
            if ((num1 >> i) & 1) == 0 {
                x |= 1 << i;
                c -= 1;
            }
        }
        x
    }
}
```

#### C#

```cs
public class Solution {
    public int MinimizeXor(int num1, int num2) {
        int cnt = BitOperations.PopCount((uint)num2);
        int x = 0;
        for (int i = 30; i >= 0 && cnt > 0; --i) {
            if (((num1 >> i) & 1) == 1) {
                x |= 1 << i;
                --cnt;
            }
        }
        for (int i = 0; cnt > 0; ++i) {
            if (((num1 >> i) & 1) == 0) {
                x |= 1 << i;
                --cnt;
            }
        }
        return x;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
