# [917. 仅仅反转字母](https://leetcode.cn/problems/reverse-only-letters)

[English Version](/solution/0900-0999/0917.Reverse%20Only%20Letters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，根据下述规则反转字符串：</p>

<ul>
	<li>所有非英文字母保留在原有位置。</li>
	<li>所有英文字母（小写或大写）位置反转。</li>
</ul>

<p>返回反转后的 <code>s</code><em> 。</em></p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "ab-cd"
<strong>输出：</strong>"dc-ba"
</pre>

<ol>
</ol>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "a-bC-dEf-ghIj"
<strong>输出：</strong>"j-Ih-gfE-dCba"
</pre>

<ol>
</ol>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "Test1ng-Leet=code-Q!"
<strong>输出：</strong>"Qedo1ct-eeLg=ntse-T!"
</pre>

<p>&nbsp;</p>

<p><strong>提示</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 仅由 ASCII 值在范围 <code>[33, 122]</code> 的字符组成</li>
	<li><code>s</code> 不含 <code>'\"'</code> 或 <code>'\\'</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

双指针遍历字符串，交换两个指针指向的字母。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def reverseOnlyLetters(self, s: str) -> str:
        s = list(s)
        i, j = 0, len(s) - 1
        while i < j:
            while i < j and not s[i].isalpha():
                i += 1
            while i < j and not s[j].isalpha():
                j -= 1
            if i < j:
                s[i], s[j] = s[j], s[i]
                i, j = i + 1, j - 1
        return ''.join(s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !Character.isLetter(chars[i])) {
                ++i;
            }
            while (i < j && !Character.isLetter(chars[j])) {
                --j;
            }
            if (i < j) {
                char t = chars[i];
                chars[i] = chars[j];
                chars[j] = t;
                ++i;
                --j;
            }
        }
        return new String(chars);
    }
}
```

### **TypeScript**

```ts
function reverseOnlyLetters(s: string): string {
    const n = s.length;
    let i = 0,
        j = n - 1;
    let ans = [...s];
    while (i < j) {
        while (!/[a-zA-Z]/.test(ans[i]) && i < j) i++;
        while (!/[a-zA-Z]/.test(ans[j]) && i < j) j--;
        [ans[i], ans[j]] = [ans[j], ans[i]];
        i++;
        j--;
    }
    return ans.join('');
}
```

### **C++**

```cpp
class Solution {
public:
    string reverseOnlyLetters(string s) {
        int i = 0, j = s.size() - 1;
        while (i < j) {
            while (i < j && !isalpha(s[i])) ++i;
            while (i < j && !isalpha(s[j])) --j;
            if (i < j) {
                swap(s[i], s[j]);
                ++i;
                --j;
            }
        }
        return s;
    }
};
```

### **Go**

```go
func reverseOnlyLetters(s string) string {
	ans := []rune(s)
	i, j := 0, len(s)-1
	for i < j {
		for i < j && !unicode.IsLetter(ans[i]) {
			i++
		}
		for i < j && !unicode.IsLetter(ans[j]) {
			j--
		}
		if i < j {
			ans[i], ans[j] = ans[j], ans[i]
			i++
			j--
		}
	}
	return string(ans)
}
```

### **Rust**

```rust
impl Solution {
    pub fn reverse_only_letters(s: String) -> String {
        let mut cs: Vec<char> = s.chars().collect();
        let n = cs.len();
        let mut l = 0;
        let mut r = n - 1;
        while l < r {
            if !cs[l].is_ascii_alphabetic() {
                l += 1;
            } else if !cs[r].is_ascii_alphabetic() {
                r -= 1;
            } else {
                cs.swap(l, r);
                l += 1;
                r -= 1;
            }
        }
        cs.iter().collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
