# [1835. 所有数对按位与结果的异或和](https://leetcode.cn/problems/find-xor-sum-of-all-pairs-bitwise-and)

[English Version](/solution/1800-1899/1835.Find%20XOR%20Sum%20of%20All%20Pairs%20Bitwise%20AND/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>列表的 <strong>异或和</strong>（<strong>XOR sum</strong>）指对所有元素进行按位 <code>XOR</code> 运算的结果。如果列表中仅有一个元素，那么其 <strong>异或和</strong> 就等于该元素。</p>

<ul>
	<li>例如，<code>[1,2,3,4]</code> 的 <strong>异或和</strong> 等于 <code>1 XOR 2 XOR 3 XOR 4 = 4</code> ，而 <code>[3]</code> 的 <strong>异或和</strong> 等于 <code>3</code> 。</li>
</ul>

<p>给你两个下标 <strong>从 0 开始</strong> 计数的数组 <code>arr1</code> 和 <code>arr2</code> ，两数组均由非负整数组成。</p>

<p>根据每个 <code>(i, j)</code> 数对，构造一个由 <code>arr1[i] AND arr2[j]</code>（按位 <code>AND</code> 运算）结果组成的列表。其中 <code>0 &lt;= i &lt; arr1.length</code> 且 <code>0 &lt;= j &lt; arr2.length</code> 。</p>

<p>返回上述列表的 <strong>异或和</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr1 = [1,2,3], arr2 = [6,5]
<strong>输出：</strong>0
<strong>解释：</strong>列表 = [1 AND 6, 1 AND 5, 2 AND 6, 2 AND 5, 3 AND 6, 3 AND 5] = [0,1,2,0,2,1] ，
异或和 = 0 XOR 1 XOR 2 XOR 0 XOR 2 XOR 1 = 0 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr1 = [12], arr2 = [4]
<strong>输出：</strong>4
<strong>解释：</strong>列表 = [12 AND 4] = [4] ，异或和 = 4 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr1.length, arr2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= arr1[i], arr2[j] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：位运算**

假设数组 `arr1` 的元素分别为 $a_1, a_2, \cdots, a_n$，数组 `arr2` 的元素分别为 $b_1, b_2, \cdots, b_m$，那么题目答案为：

$$
\begin{aligned}
\text{ans} &= (a_1 \wedge b_1) \oplus (a_1 \wedge b_2) ... (a_1 \wedge b_m) \\
&\quad \oplus (a_2 \wedge b_1) \oplus (a_2 \wedge b_2) ... (a_2 \wedge b_m) \\
&\quad \oplus \cdots \\
&\quad \oplus (a_n \wedge b_1) \oplus (a_n \wedge b_2) ... (a_n \wedge b_m) \\
\end{aligned}
$$

由于布尔代数中，异或运算就是不进位的加法，与运算就是乘法，所以上式可以简化为：

$$
\text{ans} = (a_1 \oplus a_2 \oplus \cdots \oplus a_n) \wedge (b_1 \oplus b_2 \oplus \cdots \oplus b_m)
$$

即，数组 `arr1` 的异或和与数组 `arr2` 的异或和的与运算结果。

时间复杂度 $O(n + m)$，空间复杂度 $O(1)$。其中 $n$ 和 $m$ 分别为数组 `arr1` 和 `arr2` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getXORSum(self, arr1: List[int], arr2: List[int]) -> int:
        a = reduce(xor, arr1)
        b = reduce(xor, arr2)
        return a & b
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int getXORSum(int[] arr1, int[] arr2) {
        int a = 0, b = 0;
        for (int v : arr1) {
            a ^= v;
        }
        for (int v : arr2) {
            b ^= v;
        }
        return a & b;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int getXORSum(vector<int>& arr1, vector<int>& arr2) {
        int a = accumulate(arr1.begin(), arr1.end(), 0, bit_xor<int>());
        int b = accumulate(arr2.begin(), arr2.end(), 0, bit_xor<int>());
        return a & b;
    }
};
```

### **Go**

```go
func getXORSum(arr1 []int, arr2 []int) int {
	var a, b int
	for _, v := range arr1 {
		a ^= v
	}
	for _, v := range arr2 {
		b ^= v
	}
	return a & b
}
```

### **...**

```

```

<!-- tabs:end -->
