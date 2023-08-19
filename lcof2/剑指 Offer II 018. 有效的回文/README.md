# [剑指 Offer II 018. 有效的回文](https://leetcode.cn/problems/XltzEq)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串 <code>s</code> ，验证 <code>s</code>&nbsp;是否是&nbsp;<strong>回文串&nbsp;</strong>，只考虑字母和数字字符，可以忽略字母的大小写。</p>

<p>本题中，将空字符串定义为有效的&nbsp;<strong>回文串&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>s =<strong> </strong>&quot;A man, a plan, a canal: Panama&quot;
<strong>输出:</strong> true
<strong>解释：</strong>&quot;amanaplanacanalpanama&quot; 是回文串</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = &quot;race a car&quot;
<strong>输出:</strong> false
解释：&quot;raceacar&quot; 不是回文串</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li>字符串 <code>s</code> 由 ASCII 字符组成</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 125&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/valid-palindrome/">https://leetcode.cn/problems/valid-palindrome/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

我们定义两个指针 $i$ 和 $j$，初始时分别指向字符串的首尾位置，每次判断两个指针指向的字符是否为数字或字母，如果两个指针指向的字符都为数字或字母时，判断两个指针指向的字符是否相同（忽略大小写），如果不相同则返回 `false`，否则将两个指针向中间移动一位，直到两个指针相遇时返回 `true`。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isPalindrome(self, s: str) -> bool:
        i, j = 0, len(s) - 1
        while i < j:
            while i < j and not s[i].isalnum():
                i += 1
            while i < j and not s[j].isalnum():
                j -= 1
            if s[i].lower() != s[j].lower():
                return False
            i, j = i + 1, j - 1
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                ++i;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                --j;
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            ++i;
            --j;
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isPalindrome(string s) {
        int i = 0, j = s.size() - 1;
        while (i < j) {
            while (i < j && !isalnum(s[i])) {
                ++i;
            }
            while (i < j && !isalnum(s[j])) {
                --j;
            }
            if (tolower(s[i]) != tolower(s[j])) {
                return false;
            }
            ++i;
            --j;
        }
        return true;
    }
};
```

### **Go**

```go
func isPalindrome(s string) bool {
	i, j := 0, len(s)-1
	for i < j {
		for i < j && !isalnum(s[i]) {
			i++
		}
		for i < j && !isalnum(s[j]) {
			j--
		}
		if tolower(s[i]) != tolower(s[j]) {
			return false
		}
		i, j = i+1, j-1
	}
	return true
}

func tolower(b byte) byte {
	if b >= 'A' && b <= 'Z' {
		return b - 'A' + 'a'
	}
	return b
}

func isalnum(b byte) bool {
	return b >= '0' && b <= '9' ||
		b >= 'a' && b <= 'z' ||
		b >= 'A' && b <= 'Z'
}
```

### **TypeScript**

```ts
function isPalindrome(s: string): boolean {
    const str = s.replace(/[^a-zA-Z0-9]/g, '');
    let l = 0;
    let r = str.length - 1;
    while (l < r) {
        if (str[l].toLocaleLowerCase() !== str[r].toLocaleLowerCase()) {
            return false;
        }
        l++;
        r--;
    }
    return true;
}
```

### **Rust**

使用 `is_alphabetic()` 与 `is_numeric()` 过滤字符

```rust
impl Solution {
    pub fn is_palindrome(s: String) -> bool {
        let ss: Vec<char> = s.chars().collect();
        let mut l = 0;
        let mut r = ss.len() - 1;
        while l < r {
            while l < r && !(ss[l].is_alphabetic() || ss[l].is_numeric()) {
                l += 1;
            }
            while l < r && !(ss[r].is_alphabetic() || ss[r].is_numeric()) {
                r -= 1;
            }
            if ss[l].to_ascii_lowercase() != ss[r].to_ascii_lowercase() {
                return false;
            }
            // 防止 usize 破界
            if r == 0 {
                return true;
            }
            l += 1;
            r -= 1;
        }
        true
    }
}
```

### **...**

```

```

<!-- tabs:end -->
