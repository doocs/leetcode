# [779. 第 K 个语法符号](https://leetcode.cn/problems/k-th-symbol-in-grammar)

[English Version](/solution/0700-0799/0779.K-th%20Symbol%20in%20Grammar/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们构建了一个包含 <code>n</code> 行(&nbsp;<strong>索引从 1&nbsp; 开始&nbsp;</strong>)的表。首先在第一行我们写上一个 <code>0</code>。接下来的每一行，将前一行中的<code>0</code>替换为<code>01</code>，<code>1</code>替换为<code>10</code>。</p>

<ul>
	<li>例如，对于 <code>n = 3</code> ，第 <code>1</code> 行是 <code>0</code> ，第 <code>2</code> 行是 <code>01</code> ，第3行是 <code>0110</code> 。</li>
</ul>

<p>给定行数&nbsp;<code>n</code>&nbsp;和序数 <code>k</code>，返回第 <code>n</code> 行中第 <code>k</code>&nbsp;个字符。（&nbsp;<code>k</code>&nbsp;<strong>从索引 1 开始</strong>）</p>

<p><br />
<strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> n = 1, k = 1
<strong>输出:</strong> 0
<strong>解释: </strong>第一行：<u>0</u>
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> n = 2, k = 1
<strong>输出:</strong> 0
<strong>解释:</strong> 
第一行: 0 
第二行: <u>0</u>1
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> n = 2, k = 2
<strong>输出:</strong> 1
<strong>解释:</strong>
第一行: 0
第二行: 0<u>1</u>
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 30</code></li>
	<li><code>1 &lt;= k &lt;= 2<sup>n - 1</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递归**

我们先来看一下前几行的规律：

```
n = 1: 0
n = 2: 0 1
n = 3: 0 1 1 0
n = 4: 0 1 1 0 1 0 0 1
n = 5: 0 1 1 0 1 0 0 1 1 0 0 1 0 1 1 0
...
```

可以发现，每一行的前半部分和上一行完全一致，后半部分是上一行的反转。注意，这里的“反转”指的是 $0$ 变 $1$, $1$ 变 $0$。

如果 $k$ 在前半部分，那么第 $k$ 个字符就是上一行的第 $k$ 个字符，直接递归 $kthGrammar(n - 1, k)$ 即可。

如果 $k$ 在后半部分，那么第 $k$ 个字符就是上一行的第 $k - 2^{n - 2}$ 个字符的反转，即 $kthGrammar(n - 1, k - 2^{n - 2}) \oplus 1 $。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

**方法二：位运算 + 脑筋急转弯**

题目中索引从 $1$ 开始，我们将 $k$ 改成 $k-1$，将索引转换为从 $0$ 开始。在接下来的讨论中，索引均从 $0$ 开始。

仔细观察，一行中的第 $i$ 个字符，会在第 $2i$ 和第 $2i+1$ 个位置产生两个字符。

```
0 1 1 0 1 0 0 1 1 0 0 1 0 1 1 0
```

如果第 $i$ 个字符是 $0$，那么在位置 $2i$ 和 $2i+1$ 产生的字符分别是 $0$ 和 $1$；如果第 $i$ 个字符是 $1$，产生的字符是 $1$ 和 $0$。

```
0 1 1 0 1 0 0 1 1 0 0 1 0 1 1 0
      ^     * *
```

```
0 1 1 0 1 0 0 1 1 0 0 1 0 1 1 0
        ^       * *
```

可以发现，第 $2i$ （偶数位）个字符总是和第 $i$ 个字符相同，而第 $2i+1$ （奇数位）个字符是第 $i$ 个字符的反转。也就是说，奇数位上的字符总是发生了一次反转而来的。反转偶数次，字符不变；反转奇数次，相当于反转了一次。

因此，我们只需要看 $k$ 这个数字是否是奇数，若是，累计一次反转。然后将 $k$ 除以 $2$，继续判断，并累计反转次数，直至 $k$ 为 $0$。

最后判断反转的次数是否为奇数，是则答案为 $1$，否则为 $0$。

以上累计反转次数的过程，实际上等价于求 $k$ 的二进制表示中，有多少位是 $1$。

时间复杂度 $O(\log k)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def kthGrammar(self, n: int, k: int) -> int:
        if n == 1:
            return 0
        if k <= (1 << (n - 2)):
            return self.kthGrammar(n - 1, k)
        return self.kthGrammar(n - 1, k - (1 << (n - 2))) ^ 1
```

```python
class Solution:
    def kthGrammar(self, n: int, k: int) -> int:
        return (k - 1).bit_count() & 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }
        if (k <= (1 << (n - 2))) {
            return kthGrammar(n - 1, k);
        }
        return kthGrammar(n - 1, k - (1 << (n - 2))) ^ 1;
    }
}
```

```java
class Solution {
    public int kthGrammar(int n, int k) {
        return Integer.bitCount(k - 1) & 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int kthGrammar(int n, int k) {
        if (n == 1) return 0;
        if (k <= (1 << (n - 2))) return kthGrammar(n - 1, k);
        return kthGrammar(n - 1, k - (1 << (n - 2))) ^ 1;
    }
};
```

```cpp
class Solution {
public:
    int kthGrammar(int n, int k) {
        return __builtin_popcount(k - 1) & 1;
    }
};
```

### **Go**

```go
func kthGrammar(n int, k int) int {
	if n == 1 {
		return 0
	}
	if k <= (1 << (n - 2)) {
		return kthGrammar(n-1, k)
	}
	return kthGrammar(n-1, k-(1<<(n-2))) ^ 1
}
```

```go
func kthGrammar(n int, k int) int {
	return bits.OnesCount(uint(k-1)) & 1
}
```

### **...**

```

```

<!-- tabs:end -->
