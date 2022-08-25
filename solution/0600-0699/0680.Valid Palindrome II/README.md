# [680. 验证回文串 II](https://leetcode.cn/problems/valid-palindrome-ii)

[English Version](/solution/0600-0699/0680.Valid%20Palindrome%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>，<strong>最多</strong> 可以从中删除一个字符。</p>

<p>请你判断 <code>s</code> 是否能成为回文字符串：如果能，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aba"
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abca"
<strong>输出：</strong>true
<strong>解释：</strong>你可以删除字符 'c' 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "abc"
<strong>输出：</strong>false</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

双指针，当 `s[i]` 不等于 `s[j]` 时，分别尝试跳过 `i` 或跳过 `j`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def validPalindrome(self, s: str) -> bool:
        def check(i, j):
            while i < j:
                if s[i] != s[j]:
                    return False
                i, j = i + 1, j - 1
            return True

        i, j = 0, len(s) - 1
        while i < j:
            if s[i] != s[j]:
                return check(i, j - 1) or check(i + 1, j)
            i, j = i + 1, j - 1
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean validPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return check(s, i + 1, j) || check(s, i, j - 1);
            }
        }
        return true;
    }

    private boolean check(String s, int i, int j) {
        for (; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
```

### **TypeScript**

```ts
function validPalindrome(s: string): boolean {
    for (let i: number = 0, j = s.length - 1; i < j; ++i, --j) {
        if (s.charAt(i) != s.charAt(j)) {
            return (
                isPalinddrome(s.slice(i, j)) ||
                isPalinddrome(s.slice(i + 1, j + 1))
            );
        }
    }
    return true;
}

function isPalinddrome(s: string): boolean {
    for (let i: number = 0, j = s.length - 1; i < j; ++i, --j) {
        if (s.charAt(i) != s.charAt(j)) {
            return false;
        }
    }
    return true;
}
```

### **C++**

```cpp
class Solution {
public:
    bool validPalindrome(string s) {
        for (int i = 0, j = s.size() - 1; i < j; ++i, --j)
            if (s[i] != s[j])
                return check(s, i + 1, j) || check(s, i, j - 1);
        return 1;
    }

    bool check(string s, int i, int j) {
        for (; i < j; ++i, --j)
            if (s[i] != s[j])
                return 0;
        return 1;
    }
};
```

### **Go**

```go
func validPalindrome(s string) bool {
	check := func(i, j int) bool {
		for ; i < j; i, j = i+1, j-1 {
			if s[i] != s[j] {
				return false
			}
		}
		return true
	}
	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		if s[i] != s[j] {
			return check(i+1, j) || check(i, j-1)
		}
	}
	return true
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {boolean}
 */
var validPalindrome = function (s) {
    let check = function (i, j) {
        for (; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    };
    for (let i = 0, j = s.length - 1; i < j; ++i, --j) {
        if (s.charAt(i) != s.charAt(j)) {
            return check(i + 1, j) || check(i, j - 1);
        }
    }
    return true;
};
```

### **...**

```

```

<!-- tabs:end -->
