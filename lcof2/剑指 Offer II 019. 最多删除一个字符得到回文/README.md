# [剑指 Offer II 019. 最多删除一个字符得到回文](https://leetcode.cn/problems/RQku0D)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非空字符串&nbsp;<code>s</code>，请判断如果&nbsp;<strong>最多 </strong>从字符串中删除一个字符能否得到一个回文字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> s = &quot;aba&quot;
<strong>输出:</strong> true
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = &quot;abca&quot;
<strong>输出:</strong> true
<strong>解释:</strong> 可以删除 &quot;c&quot; 字符 或者 &quot;b&quot; 字符
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> s = &quot;abc&quot;
<strong>输出:</strong> false</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 680&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/valid-palindrome-ii/">https://leetcode.cn/problems/valid-palindrome-ii/</a></p>

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
