# [481. 神奇字符串](https://leetcode.cn/problems/magical-string)

[English Version](/solution/0400-0499/0481.Magical%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>神奇字符串 <code>s</code> 仅由 <code>'1'</code> 和 <code>'2'</code> 组成，并需要遵守下面的规则：</p>

<ul>
	<li>神奇字符串 s 的神奇之处在于，串联字符串中 <code>'1'</code> 和 <code>'2'</code> 的连续出现次数可以生成该字符串。</li>
</ul>

<p><code>s</code> 的前几个元素是 <code>s = "1221121221221121122……"</code> 。如果将 <code>s</code> 中连续的若干 <code>1</code> 和 <code>2</code> 进行分组，可以得到 <code>"1 22 11 2 1 22 1 22 11 2 11 22 ......"</code> 。每组中 <code>1</code> 或者 <code>2</code> 的出现次数分别是 <code>"1 2 2 1 1 2 1 2 2 1 2 2 ......"</code> 。上面的出现次数正是 <code>s</code> 自身。</p>

<p>给你一个整数 <code>n</code> ，返回在神奇字符串 <code>s</code> 的前 <code>n</code> 个数字中 <code>1</code> 的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 6
<strong>输出：</strong>3
<strong>解释：</strong>神奇字符串 s 的前 6 个元素是 “<code>122112</code>”，它包含三个 1，因此返回 3 。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

直接模拟字符串添加。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def magicalString(self, n: int) -> int:
        s = list('1221121')
        i = 5
        while len(s) < n:
            if s[i] == '1':
                s.append('2' if s[-1] == '1' else '1')
            else:
                s.extend(list('22' if s[-1] == '1' else '11'))
            i += 1
        return s[:n].count('1')
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int magicalString(int n) {
        StringBuilder s = new StringBuilder("1221121");
        int i = 5;
        while (s.length() < n) {
            char c = s.charAt(s.length() - 1);
            if (s.charAt(i) == '1') {
                s.append(c == '1' ? '2' : '1');
            } else {
                s.append(c == '1' ? "22" : "11");
            }
            ++i;
        }
        int ans = 0;
        for (i = 0; i < n; ++i) {
            if (s.charAt(i) == '1') {
                ++ans;
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
    int magicalString(int n) {
        string s = "1221121";
        int i = 5;
        while (s.size() < n) {
            if (s[i] == '1') {
                s += s.back() == '1' ? "2" : "1";
            } else {
                s += s.back() == '1' ? "22" : "11";
            }
            ++i;
        }
        return count(s.begin(), s.begin() + n, '1');
    }
};
```

### **Go**

```go
func magicalString(n int) int {
	s := []byte("1221121")
	i := 5
	for len(s) < n {
		c := s[len(s)-1]
		if s[i] == '1' {
			if c == '1' {
				s = append(s, '2')
			} else {
				s = append(s, '1')
			}
		} else {
			if c == '1' {
				s = append(s, '2')
				s = append(s, '2')
			} else {
				s = append(s, '1')
				s = append(s, '1')
			}
		}
		i++
	}
	return bytes.Count(s[:n], []byte("1"))
}
```

### **...**

```

```

<!-- tabs:end -->
