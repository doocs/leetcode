# [2108. 找出数组中的第一个回文字符串](https://leetcode.cn/problems/find-first-palindromic-string-in-the-array)

[English Version](/solution/2100-2199/2108.Find%20First%20Palindromic%20String%20in%20the%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串数组 <code>words</code> ，找出并返回数组中的 <strong>第一个回文字符串</strong> 。如果不存在满足要求的字符串，返回一个 <strong>空字符串</strong><em> </em><code>""</code> 。</p>

<p><strong>回文字符串</strong> 的定义为：如果一个字符串正着读和反着读一样，那么该字符串就是一个 <strong>回文字符串</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>words = ["abc","car","ada","racecar","cool"]
<strong>输出：</strong>"ada"
<strong>解释：</strong>第一个回文字符串是 "ada" 。
注意，"racecar" 也是回文字符串，但它不是第一个。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>words = ["notapalindrome","racecar"]
<strong>输出：</strong>"racecar"
<strong>解释：</strong>第一个也是唯一一个回文字符串是 "racecar" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>words = ["def","ghi"]
<strong>输出：</strong>""
<strong>解释：</strong>不存在回文字符串，所以返回一个空字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> 仅由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

遍历数组 `words`，对于每个字符串 `w`，判断其是否为回文字符串，如果是，则返回 `w`，否则继续遍历。

判断一个字符串是否为回文字符串，可以使用双指针，分别指向字符串的首尾，向中间移动，判断对应的字符是否相等。如果遍历完整个字符串，都没有发现不相等的字符，则该字符串为回文字符串。

时间复杂度 $O(L)$，空间复杂度 $O(1)$，其中 $L$ 为数组 `words` 中所有字符串的长度之和。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def firstPalindrome(self, words: List[str]) -> str:
        return next((w for w in words if w == w[::-1]), "")
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String firstPalindrome(String[] words) {
        for (var w : words) {
            boolean ok = true;
            for (int i = 0, j = w.length() - 1; i < j && ok; ++i, --j) {
                if (w.charAt(i) != w.charAt(j)) {
                    ok = false;
                }
            }
            if (ok) {
                return w;
            }
        }
        return "";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string firstPalindrome(vector<string>& words) {
        for (auto& w : words) {
            bool ok = true;
            for (int i = 0, j = w.size() - 1; i < j; ++i, --j) {
                if (w[i] != w[j]) {
                    ok = false;
                }
            }
            if (ok) {
                return w;
            }
        }
        return "";
    }
};
```

### **Go**

```go
func firstPalindrome(words []string) string {
	for _, w := range words {
		ok := true
		for i, j := 0, len(w)-1; i < j && ok; i, j = i+1, j-1 {
			if w[i] != w[j] {
				ok = false
			}
		}
		if ok {
			return w
		}
	}
	return ""
}
```

### **TypeScript**

```ts
function firstPalindrome(words: string[]): string {
    for (const word of words) {
        let left = 0;
        let right = word.length - 1;
        while (left < right) {
            if (word[left] !== word[right]) {
                break;
            }
            left++;
            right--;
        }
        if (left >= right) {
            return word;
        }
    }
    return '';
}
```

### **Rust**

```rust
impl Solution {
    pub fn first_palindrome(words: Vec<String>) -> String {
        for word in words.iter() {
            let s = word.as_bytes();
            let mut left = 0;
            let mut right = s.len() - 1;
            while (left < right) {
                if (s[left] != s[right]) {
                    break;
                }
                left += 1;
                right -= 1;
            }
            if left >= right {
                return word.clone();
            }
        }
        String::new()
    }
}
```

### **C**

```c
char *firstPalindrome(char **words, int wordsSize) {
    for (int i = 0; i < wordsSize; i++) {
        int left = 0;
        int right = strlen(words[i]) - 1;
        while (left < right) {
            if (words[i][left] != words[i][right]) {
                break;
            }
            left++;
            right--;
        }
        if (left >= right) {
            return words[i];
        }
    }
    return "";
}
```

### **...**

```

```

<!-- tabs:end -->
