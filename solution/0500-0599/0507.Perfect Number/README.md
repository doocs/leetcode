# [507. 完美数](https://leetcode.cn/problems/perfect-number)

[English Version](/solution/0500-0599/0507.Perfect%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>对于一个&nbsp;<strong>正整数</strong>，如果它和除了它自身以外的所有 <strong>正因子</strong> 之和相等，我们称它为 <strong>「完美数」</strong>。</p>

<p>给定一个&nbsp;<strong>整数&nbsp;</strong><code>n</code>，&nbsp;如果是完美数，返回 <code>true</code>；否则返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num = 28
<strong>输出：</strong>true
<strong>解释：</strong>28 = 1 + 2 + 4 + 7 + 14
1, 2, 4, 7, 和 14 是 28 的所有正因子。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num = 7
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10<sup>8</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkPerfectNumber(self, num: int) -> bool:
        if num == 1:
            return False
        s, i = 1, 2
        while i * i <= num:
            if num % i == 0:
                s += i
                if i != num // i:
                    s += num // i
            i += 1
        return s == num
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int s = 1;
        for (int i = 2; i * i <= num; ++i) {
            if (num % i == 0) {
                s += i;
                if (i != num / i) {
                    s += num / i;
                }
            }
        }
        return s == num;
    }
}

```

### **C++**

```cpp
class Solution {
public:
    bool checkPerfectNumber(int num) {
        if (num == 1) return false;
        int s = 1;
        for (int i = 2; i * i <= num; ++i) {
            if (num % i == 0) {
                s += i;
                if (i != num / i) s += num / i;
            }
        }
        return s == num;
    }
};
```

### **Go**

```go
func checkPerfectNumber(num int) bool {
	if num == 1 {
		return false
	}
	s := 1
	for i := 2; i*i <= num; i++ {
		if num%i == 0 {
			s += i
			if i != num/i {
				s += num / i
			}
		}
	}
	return s == num
}
```

### **...**

```

```

<!-- tabs:end -->
