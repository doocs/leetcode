# [1404. 将二进制表示减到 1 的步骤数](https://leetcode.cn/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one)

[English Version](/solution/1400-1499/1404.Number%20of%20Steps%20to%20Reduce%20a%20Number%20in%20Binary%20Representation%20to%20One/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个以二进制形式表示的数字 <code>s</code> 。请你返回按下述规则将其减少到 1 所需要的步骤数：</p>

<ul>
	<li>
	<p>如果当前数字为偶数，则将其除以 2 。</p>
	</li>
	<li>
	<p>如果当前数字为奇数，则将其加上 1 。</p>
	</li>
</ul>

<p>题目保证你总是可以按上述规则将测试用例变为 1 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;1101&quot;
<strong>输出：</strong>6
<strong>解释：</strong>&quot;1101&quot; 表示十进制数 13 。
Step 1) 13 是奇数，加 1 得到 14&nbsp;
Step 2) 14 是偶数，除 2 得到 7
Step 3) 7  是奇数，加 1 得到 8
Step 4) 8  是偶数，除 2 得到 4&nbsp; 
Step 5) 4  是偶数，除 2 得到 2&nbsp;
Step 6) 2  是偶数，除 2 得到 1&nbsp; 
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;10&quot;
<strong>输出：</strong>1
<strong>解释：</strong>&quot;10&quot; 表示十进制数 2 。
Step 1) 2 是偶数，除 2 得到 1 
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;1&quot;
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length&nbsp;&lt;= 500</code></li>
	<li><code>s</code> 由字符 <code>&#39;0&#39;</code> 或 <code>&#39;1&#39;</code> 组成。</li>
	<li><code>s[0] == &#39;1&#39;</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟操作**

模拟操作 1/2，同时用 carry 记录进位。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numSteps(self, s: str) -> int:
        carry = False
        ans = 0
        for c in s[:0:-1]:
            if carry:
                if c == '0':
                    c = '1'
                    carry = False
                else:
                    c = '0'
            if c == '1':
                ans += 1
                carry = True
            ans += 1
        if carry:
            ans += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numSteps(String s) {
        boolean carry = false;
        int ans = 0;
        for (int i = s.length() - 1; i > 0; --i) {
            char c = s.charAt(i);
            if (carry) {
                if (c == '0') {
                    c = '1';
                    carry = false;
                } else {
                    c = '0';
                }
            }
            if (c == '1') {
                ++ans;
                carry = true;
            }
            ++ans;
        }
        if (carry) {
            ++ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numSteps(string s) {
        int ans = 0;
        bool carry = false;
        for (int i = s.size() - 1; i; --i) {
            char c = s[i];
            if (carry) {
                if (c == '0') {
                    c = '1';
                    carry = false;
                } else
                    c = '0';
            }
            if (c == '1') {
                ++ans;
                carry = true;
            }
            ++ans;
        }
        if (carry) ++ans;
        return ans;
    }
};
```

### **Go**

```go
func numSteps(s string) int {
	ans := 0
	carry := false
	for i := len(s) - 1; i > 0; i-- {
		c := s[i]
		if carry {
			if c == '0' {
				c = '1'
				carry = false
			} else {
				c = '0'
			}
		}
		if c == '1' {
			ans++
			carry = true
		}
		ans++
	}
	if carry {
		ans++
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
