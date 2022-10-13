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

根据题意，我们要找到一个整数 `x`，使得 `x` 的置位数和 `num2` 相同，且 `x XOR num1` 的值最小。

先求出 `num2` 的置位数，我们记为 `cnt`。

接着从 `num1` 二进制的最高位开始，依次向低位遍历，如果当前位为 `1`，则 `x` 的当前位也应该为 `1`，并且将 `cnt` 减一。如果 `cnt` 为 `0`，则说明 `x` 的置位数已经和 `num2` 相同，此时 `x XOR num1` 的值就是最小的，直接返回即可。

如果遍历完 `num1` 的二进制表示后，`cnt` 不为 `0`，说明 `x` 的置位数还没有和 `num2` 相同，此时我们需要将 `x` 的低位补 `1`，直到 `x` 的置位数和 `num2` 相同为止。

时间复杂度 $O(\log n)$。其中 $n$ 为 `num1` 的值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimizeXor(self, num1: int, num2: int) -> int:
        cnt = num2.bit_count()
        ans = 0
        for i in range(30, -1, -1):
            if (num1 >> i) & 1:
                ans |= 1 << i
                cnt -= 1
                if cnt == 0:
                    return ans
        for i in range(31):
            if (num1 >> i) & 1 == 0:
                ans |= 1 << i
                cnt -= 1
                if cnt == 0:
                    return ans
        return 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimizeXor(int num1, int num2) {
        int cnt = Integer.bitCount(num2);
        int ans = 0;
        for (int i = 30; i >= 0; --i) {
            if (((num1 >> i) & 1) == 1) {
                ans |= 1 << i;
                if (--cnt == 0) {
                    return ans;
                }
            }
        }
        for (int i = 0; i < 31; ++i) {
            if (((num1 >> i) & 1) == 0) {
                ans |= 1 << i;
                if (--cnt == 0) {
                    return ans;
                }
            }
        }
        return 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimizeXor(int num1, int num2) {
        int cnt = __builtin_popcount(num2);
        int ans = 0;
        for (int i = 30; i >= 0; --i) {
            if ((num1 >> i) & 1) {
                ans |= 1 << i;
                if (--cnt == 0) {
                    return ans;
                }
            }
        }
        for (int i = 0; i < 31; ++i) {
            if (((num1 >> i) & 1) == 0) {
                ans |= 1 << i;
                if (--cnt == 0) {
                    return ans;
                }
            }
        }
        return 0;
    }
};
```

### **Go**

```go
func minimizeXor(num1 int, num2 int) int {
	cnt := bits.OnesCount(uint(num2))
	ans := 0
	for i := 30; i >= 0; i-- {
		if num1>>i&1 == 1 {
			ans |= 1 << i
			cnt--
			if cnt == 0 {
				return ans
			}
		}
	}
	for i := 0; i < 31; i++ {
		if num1>>i&1 == 0 {
			ans |= 1 << i
			cnt--
			if cnt == 0 {
				return ans
			}
		}
	}
	return 0
}
```

### **TypeScript**

```ts
function minimizeXor(num1: number, num2: number): number {
    let ans = num1;
    let target = num1.toString(2).split('').reduce((a, c) => a + Number(c), 0);
    let count = num2.toString(2).split('').reduce((a, c) => a + Number(c), 0);
    while (count > target) {
        ans |= ans + 1;
        count -= 1;
    }
    while (count < target) {
        ans &= ans - 1;
        count += 1;
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
