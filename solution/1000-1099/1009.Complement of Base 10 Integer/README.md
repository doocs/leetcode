# [1009. 十进制整数的反码](https://leetcode.cn/problems/complement-of-base-10-integer)

[English Version](/solution/1000-1099/1009.Complement%20of%20Base%2010%20Integer/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>每个非负整数&nbsp;<code>N</code>&nbsp;都有其二进制表示。例如，&nbsp;<code>5</code>&nbsp;可以被表示为二进制&nbsp;<code>&quot;101&quot;</code>，<code>11</code> 可以用二进制&nbsp;<code>&quot;1011&quot;</code>&nbsp;表示，依此类推。注意，除&nbsp;<code>N = 0</code>&nbsp;外，任何二进制表示中都不含前导零。</p>

<p>二进制的反码表示是将每个&nbsp;<code>1</code>&nbsp;改为&nbsp;<code>0</code>&nbsp;且每个&nbsp;<code>0</code>&nbsp;变为&nbsp;<code>1</code>。例如，二进制数&nbsp;<code>&quot;101&quot;</code>&nbsp;的二进制反码为&nbsp;<code>&quot;010&quot;</code>。</p>

<p>给你一个十进制数&nbsp;<code>N</code>，请你返回其二进制表示的反码所对应的十进制整数。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>5
<strong>输出：</strong>2
<strong>解释：</strong>5 的二进制表示为 &quot;101&quot;，其二进制反码为 &quot;010&quot;，也就是十进制中的 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>7
<strong>输出：</strong>0
<strong>解释：</strong>7 的二进制表示为 &quot;111&quot;，其二进制反码为 &quot;000&quot;，也就是十进制中的 0 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>10
<strong>输出：</strong>5
<strong>解释：</strong>10 的二进制表示为 &quot;1010&quot;，其二进制反码为 &quot;0101&quot;，也就是十进制中的 5 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>0 &lt;= N &lt; 10^9</code></li>
	<li>本题与 476：<a href="https://leetcode.cn/problems/number-complement/">https://leetcode.cn/problems/number-complement/</a> 相同</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def bitwiseComplement(self, n: int) -> int:
        if n == 0:
            return 1
        ans = 0
        find = False
        for i in range(30, -1, -1):
            b = n & (1 << i)
            if not find and b == 0:
                continue
            find = True
            if b == 0:
                ans |= 1 << i
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int bitwiseComplement(int n) {
        if (n == 0) {
            return 1;
        }
        int ans = 0;
        boolean find = false;
        for (int i = 30; i >= 0; --i) {
            int b = n & (1 << i);
            if (!find && b == 0) {
                continue;
            }
            find = true;
            if (b == 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int bitwiseComplement(int n) {
        if (n == 0) return 1;
        int ans = 0;
        bool find = false;
        for (int i = 30; i >= 0; --i) {
            int b = n & (1 << i);
            if (!find && b == 0) continue;
            find = true;
            if (b == 0) ans |= (1 << i);
        }
        return ans;
    }
};
```

### **Go**

```go
func bitwiseComplement(n int) int {
	if n == 0 {
		return 1
	}
	ans := 0
	find := false
	for i := 30; i >= 0; i-- {
		b := n & (1 << i)
		if !find && b == 0 {
			continue
		}
		find = true
		if b == 0 {
			ans |= (1 << i)
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
