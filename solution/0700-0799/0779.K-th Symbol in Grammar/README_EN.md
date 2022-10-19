# [779. K-th Symbol in Grammar](https://leetcode.com/problems/k-th-symbol-in-grammar)

[中文文档](/solution/0700-0799/0779.K-th%20Symbol%20in%20Grammar/README.md)

## Description

<p>We build a table of <code>n</code> rows (<strong>1-indexed</strong>). We start by writing <code>0</code> in the <code>1<sup>st</sup></code> row. Now in every subsequent row, we look at the previous row and replace each occurrence of <code>0</code> with <code>01</code>, and each occurrence of <code>1</code> with <code>10</code>.</p>

<ul>
	<li>For example, for <code>n = 3</code>, the <code>1<sup>st</sup></code> row is <code>0</code>, the <code>2<sup>nd</sup></code> row is <code>01</code>, and the <code>3<sup>rd</sup></code> row is <code>0110</code>.</li>
</ul>

<p>Given two integer <code>n</code> and <code>k</code>, return the <code>k<sup>th</sup></code> (<strong>1-indexed</strong>) symbol in the <code>n<sup>th</sup></code> row of a table of <code>n</code> rows.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1, k = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> row 1: <u>0</u>
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, k = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> 
row 1: 0
row 2: <u>0</u>1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 2, k = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
row 1: 0
row 2: 0<u>1</u>
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 30</code></li>
	<li><code>1 &lt;= k &lt;= 2<sup>n - 1</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
