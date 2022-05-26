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
        for i in range(n + 1):
            b ^= i
        ans = [a ^ b]
        for e in encoded[::-1]:
            ans.append(ans[-1] ^ e)
        return ans[::-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int[] ans = new int[n];
        int a = 0;
        int b = 0;
        for (int i = 0; i < n - 1; i += 2) {
            a ^= encoded[i];
        }
        for (int i = 0; i < n + 1; ++i) {
            b ^= i;
        }
        ans[n - 1] = a ^ b;
        for (int i = n - 2; i >= 0; --i) {
            ans[i] = ans[i + 1] ^ encoded[i];
        }
        return ans;
    }
}

```

### **C++**

```cpp
class Solution {
public:
    vector<int> decode(vector<int>& encoded) {
        int n = encoded.size() + 1;
        vector<int> ans(n);
        int a = 0, b = 0;
        for (int i = 0; i < n - 1; i += 2) a ^= encoded[i];
        for (int i = 0; i < n + 1; ++i) b ^= i;
        ans[n - 1] = a ^ b;
        for (int i = n - 2; i >= 0; --i) ans[i] = ans[i + 1] ^ encoded[i];
        return ans;
    }
};
```

### **Go**

```go
func decode(encoded []int) []int {
	n := len(encoded) + 1
	ans := make([]int, n)
	a, b := 0, 0
	for i := 0; i < n-1; i += 2 {
		a ^= encoded[i]
	}
	for i := 0; i < n+1; i++ {
		b ^= i
	}
	ans[n-1] = a ^ b
	for i := n - 2; i >= 0; i-- {
		ans[i] = ans[i+1] ^ encoded[i]
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
