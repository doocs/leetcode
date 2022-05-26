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
第二行: 0<u>1</u>
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

### **...**

```

```

<!-- tabs:end -->
