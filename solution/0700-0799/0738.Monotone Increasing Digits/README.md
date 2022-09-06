# [738. 单调递增的数字](https://leetcode.cn/problems/monotone-increasing-digits)

[English Version](/solution/0700-0799/0738.Monotone%20Increasing%20Digits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>当且仅当每个相邻位数上的数字&nbsp;<code>x</code>&nbsp;和&nbsp;<code>y</code>&nbsp;满足&nbsp;<code>x &lt;= y</code>&nbsp;时，我们称这个整数是<strong>单调递增</strong>的。</p>

<p>给定一个整数 <code>n</code> ，返回 <em>小于或等于 <code>n</code> 的最大数字，且数字呈 <strong>单调递增</strong></em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> n = 10
<strong>输出:</strong> 9
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> n = 1234
<strong>输出:</strong> 1234
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> n = 332
<strong>输出:</strong> 299
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

从数字 `n` 的高位开始，找到第一个不满足 $n_{i-1} \le n_i$ 的位置 $i$。

然后，从后往前，只要发现 $n_{i-1} \gt n_i$，就将 $n_{i-1}$ 减 1。

最后将位置 $i$ 之后的所有数字都置为 9 即可。

时间复杂度 $O(\log n)$，空间复杂度 $O(\log n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def monotoneIncreasingDigits(self, n: int) -> int:
        s = list(str(n))
        i = 1
        while i < len(s) and s[i - 1] <= s[i]:
            i += 1
        if i < len(s):
            while i and s[i - 1] > s[i]:
                s[i - 1] = str(int(s[i - 1]) - 1)
                i -= 1
            i += 1
            while i < len(s):
                s[i] = '9'
                i += 1
        return int(''.join(s))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int monotoneIncreasingDigits(int n) {
        char[] s = String.valueOf(n).toCharArray();
        int i = 1;
        for (; i < s.length && s[i - 1] <= s[i]; ++i)
            ;
        if (i < s.length) {
            for (; i > 0 && s[i - 1] > s[i]; --i) {
                --s[i - 1];
            }
            ++i;
            for (; i < s.length; ++i) {
                s[i] = '9';
            }
        }
        return Integer.parseInt(String.valueOf(s));
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int monotoneIncreasingDigits(int n) {
        string s = to_string(n);
        int i = 1;
        for (; i < s.size() && s[i - 1] <= s[i]; ++i);
        if (i < s.size()) {
            for (; i > 0 && s[i - 1] > s[i]; --i) {
                --s[i - 1];
            }
            ++i;
            for (; i < s.size(); ++i) {
                s[i] = '9';
            }
        }
        return stoi(s);
    }
};
```

### **Go**

```go
func monotoneIncreasingDigits(n int) int {
	s := []byte(strconv.Itoa(n))
	i := 1
	for ; i < len(s) && s[i-1] <= s[i]; i++ {
	}
	if i < len(s) {
		for ; i > 0 && s[i-1] > s[i]; i-- {
			s[i-1]--
		}
		i++
		for ; i < len(s); i++ {
			s[i] = '9'
		}
	}
	ans, _ := strconv.Atoi(string(s))
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
