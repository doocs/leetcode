# [1684. 统计一致字符串的数目](https://leetcode.cn/problems/count-the-number-of-consistent-strings)

[English Version](/solution/1600-1699/1684.Count%20the%20Number%20of%20Consistent%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由不同字符组成的字符串 <code>allowed</code> 和一个字符串数组 <code>words</code> 。如果一个字符串的每一个字符都在 <code>allowed</code> 中，就称这个字符串是 <strong>一致字符串 </strong>。</p>

<p>请你返回 <code>words</code> 数组中 <strong>一致字符串</strong> 的数目。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
<b>输出：</b>2
<b>解释：</b>字符串 "aaab" 和 "baa" 都是一致字符串，因为它们只包含字符 'a' 和 'b' 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
<b>输出：</b>7
<b>解释：</b>所有字符串都是一致的。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
<b>输出：</b>4
<b>解释：</b>字符串 "cc"，"acd"，"ac" 和 "d" 是一致字符串。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= words.length <= 10<sup>4</sup></code></li>
	<li><code>1 <= allowed.length <=<sup> </sup>26</code></li>
	<li><code>1 <= words[i].length <= 10</code></li>
	<li><code>allowed</code> 中的字符 <strong>互不相同</strong> 。</li>
	<li><code>words[i]</code> 和 <code>allowed</code> 只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countConsistentStrings(self, allowed: str, words: List[str]) -> int:
        res = 0
        chars = set(allowed)
        for word in words:
            find = True
            for c in word:
                if c not in chars:
                    find = False
                    break
            if find:
                res += 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        boolean[] chars = new boolean[26];
        for (char c : allowed.toCharArray()) {
            chars[c - 'a'] = true;
        }
        int res = 0;
        for (String word : words) {
            boolean find = true;
            for (char c : word.toCharArray()) {
                if (!chars[c - 'a']) {
                    find = false;
                    break;
                }
            }
            if (find) {
                ++res;
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countConsistentStrings(string allowed, vector<string>& words) {
        vector<bool> chars(26, false);
        for (char c : allowed) {
            chars[c - 'a'] = true;
        }
        int res = 0;
        for (string word : words) {
            bool find = true;
            for (char c : word) {
                if (!chars[c - 'a']) {
                    find = false;
                    break;
                }
            }
            if (find) ++res;
        }
        return res;
    }
};
```

### **Go**

```go
func countConsistentStrings(allowed string, words []string) int {
	chars := [26]bool{}
	for _, c := range allowed {
		chars[c-'a'] = true
	}
	res := 0
	for _, word := range words {
		find := true
		for _, c := range word {
			if !chars[c-'a'] {
				find = false
				break
			}
		}
		if find {
			res++
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
