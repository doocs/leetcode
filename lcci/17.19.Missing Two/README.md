# [面试题 17.19. 消失的两个数字](https://leetcode.cn/problems/missing-two-lcci)

[English Version](/lcci/17.19.Missing%20Two/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？</p>

<p>以任意顺序返回这两个数字均可。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> [1]
<strong>输出: </strong>[2,3]</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> [2,3]
<strong>输出: </strong>[1,4]</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums.length &lt;=&nbsp;30000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：位运算**

利用位运算的性质：

1. 对于任何数 $x$，都有 $x \oplus x = 0$
1. 异或运算满足结合律，即 $(a \oplus b) \oplus c = a \oplus (b \oplus c)$
1. lowbit 运算获取最低一位的 $1$ 及其后面的所有 $0$，公式为 `lowbit(x) = x & (-x)`

我们将 nums 中所有数进行异或到 $x$，再将 $[1,2..n]$ 的所有数也异或到 $x$。得到的 $x$ 是两个缺失的正整数的异或和。

然后我们运用 lowbit 获取最低一位的 $1$，那么这两个缺失的正整数在这一位上必然一个为 $1$，一个为 $0$。我们据此进行分组异或。最终得到两个缺失的正整数 $a$ 和 $b$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def missingTwo(self, nums: List[int]) -> List[int]:
        n = len(nums) + 2
        xor = 0
        for v in nums:
            xor ^= v
        for i in range(1, n + 1):
            xor ^= i

        diff = xor & (-xor)
        a = 0
        for v in nums:
            if v & diff:
                a ^= v
        for i in range(1, n + 1):
            if i & diff:
                a ^= i
        b = xor ^ a
        return [a, b]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] missingTwo(int[] nums) {
        int n = nums.length + 2;
        int xor = 0;
        for (int v : nums) {
            xor ^= v;
        }
        for (int i = 1; i <= n; ++i) {
            xor ^= i;
        }
        int diff = xor & (-xor);
        int a = 0;
        for (int v : nums) {
            if ((v & diff) != 0) {
                a ^= v;
            }
        }
        for (int i = 1; i <= n; ++i) {
            if ((i & diff) != 0) {
                a ^= i;
            }
        }
        int b = xor ^ a;
        return new int[] {a, b};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> missingTwo(vector<int>& nums) {
        int n = nums.size() + 2;
        int eor = 0;
        for (int v : nums) eor ^= v;
        for (int i = 1; i <= n; ++i) eor ^= i;

        int diff = eor & -eor;
        int a = 0;
        for (int v : nums) if (v & diff) a ^= v;
        for (int i = 1; i <= n; ++i) if (i & diff) a ^= i;
        int b = eor ^ a;
        return {a, b};
    }
};
```

### **Go**

```go
func missingTwo(nums []int) []int {
	n := len(nums) + 2
	xor := 0
	for _, v := range nums {
		xor ^= v
	}
	for i := 1; i <= n; i++ {
		xor ^= i
	}
	diff := xor & -xor
	a := 0
	for _, v := range nums {
		if (v & diff) != 0 {
			a ^= v
		}
	}
	for i := 1; i <= n; i++ {
		if (i & diff) != 0 {
			a ^= i
		}
	}
	b := xor ^ a
	return []int{a, b}
}
```

### **...**

```

```

<!-- tabs:end -->
