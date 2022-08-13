# [678. 有效的括号字符串](https://leetcode.cn/problems/valid-parenthesis-string)

[English Version](/solution/0600-0699/0678.Valid%20Parenthesis%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个只包含三种字符的字符串：<code>（&nbsp;</code>，<code>）</code>&nbsp;和 <code>*</code>，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：</p>

<ol>
	<li>任何左括号 <code>(</code>&nbsp;必须有相应的右括号 <code>)</code>。</li>
	<li>任何右括号 <code>)</code>&nbsp;必须有相应的左括号 <code>(</code>&nbsp;。</li>
	<li>左括号 <code>(</code> 必须在对应的右括号之前 <code>)</code>。</li>
	<li><code>*</code>&nbsp;可以被视为单个右括号 <code>)</code>&nbsp;，或单个左括号 <code>(</code>&nbsp;，或一个空字符串。</li>
	<li>一个空字符串也被视为有效字符串。</li>
</ol>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> &quot;()&quot;
<strong>输出:</strong> True
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> &quot;(*)&quot;
<strong>输出:</strong> True
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> &quot;(*))&quot;
<strong>输出:</strong> True
</pre>

<p><strong>注意:</strong></p>

<ol>
	<li>字符串大小将在 [1，100] 范围内。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

两遍扫描，第一遍从左往右，确定每一个右括号都可以成功配对，第二遍从右往左，确定每一个左括号都可以成功配对

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkValidString(self, s: str) -> bool:
        n = len(s)
        left, asterisk = 0, 0
        for i in range(n):
            if s[i] == "(":
                left += 1
            elif s[i] == ")":
                if left > 0:
                    left -= 1
                elif asterisk > 0:
                    asterisk -= 1
                else:
                    return False
            else:
                asterisk += 1
        right, asterisk = 0, 0
        for i in range(n - 1, -1, -1):
            if s[i] == ")":
                right += 1
            elif s[i] == "(":
                if right > 0:
                    right -= 1
                elif asterisk > 0:
                    asterisk -= 1
                else:
                    return False
            else:
                asterisk += 1
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkValidString(String s) {
        int n = s.length();
        char[] a = s.toCharArray();
        int left = 0, asterisk = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == '(') {
                left++;
            } else if (a[i] == ')') {
                if (left > 0) {
                    left--;
                } else if (asterisk > 0) {
                    asterisk--;
                } else {
                    return false;
                }
            } else {
                asterisk++;
            }
        }
        int right = 0;
        asterisk = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (a[i] == ')') {
                right++;
            } else if (a[i] == '(') {
                if (right > 0) {
                    right--;
                } else if (asterisk > 0) {
                    asterisk--;
                } else {
                    return false;
                }
            } else {
                asterisk++;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkValidString(string s) {
        int n = s.size();
        int left = 0, asterisk = 0;
        for (int i = 0; i < n; ++i) {
            if (s[i] == '(') {
                ++left;
            } else if (s[i] == ')') {
                if (left > 0)
                    --left;
                else if (asterisk > 0)
                    --asterisk;
                else
                    return false;
            } else {
                ++asterisk;
            }
        }
        int right = 0;
        asterisk = 0;
        for (int i = n - 1; i >= 0; --i) {
            if (s[i] == ')') {
                ++right;
            } else if (s[i] == '(') {
                if (right > 0)
                    --right;
                else if (asterisk > 0)
                    --asterisk;
                else
                    return false;
            } else {
                ++asterisk;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func checkValidString(s string) bool {
	n := len(s)
	left, asterisk := 0, 0
	for i := 0; i < n; i++ {
		if s[i] == '(' {
			left++
		} else if s[i] == ')' {
			if left > 0 {
				left--
			} else if asterisk > 0 {
				asterisk--
			} else {
				return false
			}
		} else {
			asterisk++
		}
	}
	asterisk = 0
	right := 0
	for i := n - 1; i >= 0; i-- {
		if s[i] == ')' {
			right++
		} else if s[i] == '(' {
			if right > 0 {
				right--
			} else if asterisk > 0 {
				asterisk--
			} else {
				return false
			}
		} else {
			asterisk++
		}
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
