# [面试题 05.03. 翻转数位](https://leetcode.cn/problems/reverse-bits-lcci)

[English Version](/lcci/05.03.Reverse%20Bits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个32位整数 <code>num</code>，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。</p>
<p><strong>示例 1：</strong></p>
<pre><strong>输入:</strong> <code>num</code> = 1775(11011101111<sub>2</sub>)
<strong>输出:</strong> 8
</pre>
<p><strong>示例 2：</strong></p>
<pre><strong>输入:</strong> <code>num</code> = 7(0111<sub>2</sub>)
<strong>输出:</strong> 4
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

我们可以使用双指针 $i$ 和 $j$ 维护一个滑动窗口，其中 $i$ 为右指针，$j$ 为左指针。每次右指针 $i$ 向右移动一位，如果此时窗口内的 $0$ 的个数超过 $1$ 个，则左指针 $j$ 向右移动一位，直到窗口内的 $0$ 的个数不超过 $1$ 个为止。然后计算此时窗口的长度，与当前最大长度进行比较，取较大值作为当前最大长度。

最后返回最大长度即可。

时间复杂度 $O(\log M)$，空间复杂度 $O(1)$。其中 $M$ 为 $32$ 位整数的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def reverseBits(self, num: int) -> int:
        ans = cnt = j = 0
        for i in range(32):
            cnt += num >> i & 1 ^ 1
            while cnt > 1:
                cnt -= num >> j & 1 ^ 1
                j += 1
            ans = max(ans, i - j + 1)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int reverseBits(int num) {
        int ans = 0, cnt = 0;
        for (int i = 0, j = 0; i < 32; ++i) {
            cnt += num >> i & 1 ^ 1;
            while (cnt > 1) {
                cnt -= num >> j & 1 ^ 1;
                ++j;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int reverseBits(int num) {
        int ans = 0, cnt = 0;
        for (int i = 0, j = 0; i < 32; ++i) {
            cnt += num >> i & 1 ^ 1;
            while (cnt > 1) {
                cnt -= num >> j & 1 ^ 1;
                ++j;
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

### **Go**

```go
func reverseBits(num int) (ans int) {
	var cnt, j int
	for i := 0; i < 32; i++ {
		cnt += num>>i&1 ^ 1
		for cnt > 1 {
			cnt -= num>>j&1 ^ 1
			j++
		}
		ans = max(ans, i-j+1)
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
