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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def firstPalindrome(self, words: List[str]) -> str:
        def check(s):
            i, j = 0, len(s) - 1
            while i < j:
                if s[i] != s[j]:
                    return False
                i += 1
                j -= 1
            return True

        for word in words:
            if check(word):
                return word
        return ''
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String firstPalindrome(String[] words) {
        for (String word : words) {
            if (check(word)) {
                return word;
            }
        }
        return "";
    }

    private boolean check(String s) {
        for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
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
    string firstPalindrome(vector<string>& words) {
        for (auto& word : words)
            if (check(word)) return word;
        return "";
    }

    bool check(string s) {
        for (int i = 0, j = s.size() - 1; i < j; ++i, --j)
            if (s[i] != s[j]) return false;
        return true;
    }
};
```

### **Go**

```go
func firstPalindrome(words []string) string {
	check := func(s string) bool {
		for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
			if s[i] != s[j] {
				return false
			}
		}
		return true
	}

	for _, word := range words {
		if check(word) {
			return word
		}
	}
	return ""
}
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
