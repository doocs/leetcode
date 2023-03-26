# [1734. 解码异或后的排列](https://leetcode.cn/problems/decode-xored-permutation)

[English Version](/solution/1700-1799/1734.Decode%20XORed%20Permutation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>perm</code> ，它是前 <code>n</code> 个正整数的排列，且 <code>n</code> 是个 <strong>奇数</strong> 。</p>

<p>它被加密成另一个长度为 <code>n - 1</code> 的整数数组 <code>encoded</code> ，满足 <code>encoded[i] = perm[i] XOR perm[i + 1]</code> 。比方说，如果 <code>perm = [1,3,2]</code> ，那么 <code>encoded = [2,1]</code> 。</p>

<p>给你 <code>encoded</code> 数组，请你返回原始数组 <code>perm</code> 。题目保证答案存在且唯一。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>encoded = [3,1]
<b>输出：</b>[1,2,3]
<b>解释：</b>如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>encoded = [6,5,4,6]
<b>输出：</b>[2,4,1,5,3]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n &lt; 10<sup>5</sup></code></li>
	<li><code>n</code> 是奇数。</li>
	<li><code>encoded.length == n - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：位运算**

我们注意到，数组 $perm$ 是前 $n$ 个正整数的排列，因此 $perm$ 的所有元素的异或和为 $1 \oplus 2 \oplus \cdots \oplus n$，记为 $a$。而 $encode[i]=perm[i] \oplus perm[i+1]$，如果我们将 $encode[0],encode[2],\cdots,encode[n-3]$ 的所有元素的异或和记为 $b$，则 $perm[n-1]=a \oplus b$。知道了 $perm$ 的最后一个元素，我们就可以通过逆序遍历数组 $encode$ 求出 $perm$ 的所有元素。

时间复杂度 $O(n)$，其中 $n$ 为数组 $perm$ 的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def decode(self, encoded: List[int]) -> List[int]:
        n = len(encoded) + 1
        a = b = 0
        for i in range(0, n - 1, 2):
            a ^= encoded[i]
        for i in range(1, n + 1):
            b ^= i
        perm = [0] * n
        perm[-1] = a ^ b
        for i in range(n - 2, -1, -1):
            perm[i] = encoded[i] ^ perm[i + 1]
        return perm
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int a = 0, b = 0;
        for (int i = 0; i < n - 1; i += 2) {
            a ^= encoded[i];
        }
        for (int i = 1; i <= n; ++i) {
            b ^= i;
        }
        int[] perm = new int[n];
        perm[n - 1] = a ^ b;
        for (int i = n - 2; i >= 0; --i) {
            perm[i] = encoded[i] ^ perm[i + 1];
        }
        return perm;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> decode(vector<int>& encoded) {
        int n = encoded.size() + 1;
        int a = 0, b = 0;
        for (int i = 0; i < n - 1; i += 2) {
            a ^= encoded[i];
        }
        for (int i = 1; i <= n; ++i) {
            b ^= i;
        }
        vector<int> perm(n);
        perm[n - 1] = a ^ b;
        for (int i = n - 2; ~i; --i) {
            perm[i] = encoded[i] ^ perm[i + 1];
        }
        return perm;
    }
};
```

### **Go**

```go
func decode(encoded []int) []int {
	n := len(encoded) + 1
	a, b := 0, 0
	for i := 0; i < n-1; i += 2 {
		a ^= encoded[i]
	}
	for i := 1; i <= n; i++ {
		b ^= i
	}
	perm := make([]int, n)
	perm[n-1] = a ^ b
	for i := n - 2; i >= 0; i-- {
		perm[i] = encoded[i] ^ perm[i+1]
	}
	return perm
}
```

### **...**

```

```

<!-- tabs:end -->
