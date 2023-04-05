# [2429. 最小 XOR](https://leetcode.cn/problems/minimize-xor)

[English Version](/solution/2400-2499/2429.Minimize%20XOR/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个正整数 <code>num1</code> 和 <code>num2</code> ，找出满足下述条件的整数 <code>x</code> ：</p>

<ul>
	<li><code>x</code> 的置位数和 <code>num2</code> 相同，且</li>
	<li><code>x XOR num1</code> 的值 <strong>最小</strong></li>
</ul>

<p>注意 <code>XOR</code> 是按位异或运算。</p>

<p>返回整数<em> </em><code>x</code> 。题目保证，对于生成的测试用例， <code>x</code> 是 <strong>唯一确定</strong> 的。</p>

<p>整数的 <strong>置位数</strong> 是其二进制表示中 <code>1</code> 的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>num1 = 3, num2 = 5
<strong>输出：</strong>3
<strong>解释：</strong>
num1 和 num2 的二进制表示分别是 0011 和 0101 。
整数 <strong>3</strong> 的置位数与 num2 相同，且 <code>3 XOR 3 = 0</code> 是最小的。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>num1 = 1, num2 = 12
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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 位运算**

根据题目描述，我们先求出 $num2$ 的置位数 $cnt$，然后从高位到低位枚举 $num1$ 的每一位，如果该位为 $1$，则将 $x$ 的对应位设为 $1$，并将 $cnt$ 减 $1$，直到 $cnt$ 为 $0$。如果此时 $cnt$ 仍不为 $0$，则从低位开始将 $num1$ 的每一位为 $0$ 的位置设为 $1$，并将 $cnt$ 减 $1$，直到 $cnt$ 为 $0$。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。其中 $n$ 为 $num1$ 和 $num2$ 的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

```python
class Solution:
    def minimizeXor(self, num1: int, num2: int) -> int:
        cnt1 = num1.bit_count()
        cnt2 = num2.bit_count()
        while cnt1 > cnt2:
            num1 &= (num1 - 1)
            cnt1 -= 1
        while cnt1 < cnt2:
            num1 |= (num1 + 1)
            cnt1 += 1
        return num1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

```java
class Solution {
    public int minimizeXor(int num1, int num2) {
        int cnt1 = Integer.bitCount(num1);
        int cnt2 = Integer.bitCount(num2);
        for (; cnt1 > cnt2; --cnt1) {
            num1 &= (num1 - 1);
        }
        for (; cnt1 < cnt2; ++cnt1) {
            num1 |= (num1 + 1);
        }
        return num1;
    }
}
```

### **C++**

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

```cpp
class Solution {
public:
    int minimizeXor(int num1, int num2) {
        int cnt1 = __builtin_popcount(num1);
        int cnt2 = __builtin_popcount(num2);
        for (; cnt1 > cnt2; --cnt1) {
            num1 &= (num1 - 1);
        }
        for (; cnt1 < cnt2; ++cnt1) {
            num1 |= (num1 + 1);
        }
        return num1;
    }
};
```

### **Go**

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

```go
func minimizeXor(num1 int, num2 int) int {
	cnt1 := bits.OnesCount(uint(num1))
	cnt2 := bits.OnesCount(uint(num2))
	for ; cnt1 > cnt2; cnt1-- {
		num1 &= (num1 - 1)
	}
	for ; cnt1 < cnt2; cnt1++ {
		num1 |= (num1 + 1)
	}
	return num1
}
```

### **TypeScript**

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

```ts
function minimizeXor(num1: number, num2: number): number {
    let cnt1 = bitCount(num1);
    let cnt2 = bitCount(num2);
    for (; cnt1 > cnt2; --cnt1) {
        num1 &= num1 - 1;
    }
    for (; cnt1 < cnt2; ++cnt1) {
        num1 |= num1 + 1;
    }
    return num1;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

### **...**

```

```

<!-- tabs:end -->
