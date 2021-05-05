# [1720. 解码异或后的数组](https://leetcode-cn.com/problems/decode-xored-array)

[English Version](/solution/1700-1799/1720.Decode%20XORed%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>未知</strong> 整数数组 <code>arr</code> 由 <code>n</code> 个非负整数组成。</p>

<p>经编码后变为长度为 <code>n - 1</code> 的另一个整数数组 <code>encoded</code> ，其中 <code>encoded[i] = arr[i] XOR arr[i + 1]</code> 。例如，<code>arr = [1,0,2,1]</code> 经编码后得到 <code>encoded = [1,2,3]</code> 。</p>

<p>给你编码后的数组 <code>encoded</code> 和原数组 <code>arr</code> 的第一个元素 <code>first</code>（<code>arr[0]</code>）。</p>

<p>请解码返回原数组 <code>arr</code> 。可以证明答案存在并且是唯一的。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>encoded = [1,2,3], first = 1
<strong>输出：</strong>[1,0,2,1]
<strong>解释：</strong>若 arr = [1,0,2,1] ，那么 first = 1 且 encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>encoded = [6,2,7,3], first = 4
<strong>输出：</strong>[4,2,0,7,4]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= n <= 10<sup>4</sup></code></li>
	<li><code>encoded.length == n - 1</code></li>
	<li><code>0 <= encoded[i] <= 10<sup>5</sup></code></li>
	<li><code>0 <= first <= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

异或运算。

`a = b ^ c` => `a ^ b = b ^ c ^ b` => `c = a ^ b`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def decode(self, encoded: List[int], first: int) -> List[int]:
        res = [first]
        for e in encoded:
            first ^= e
            res.append(first)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] decode(int[] encoded, int first) {
        int[] res = new int[encoded.length + 1];
        res[0] = first;
        for (int i = 0; i < encoded.length; ++i) {
            res[i + 1] = res[i] ^ encoded[i];
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
