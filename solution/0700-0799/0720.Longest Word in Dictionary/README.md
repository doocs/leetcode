# [720. 词典中最长的单词](https://leetcode.cn/problems/longest-word-in-dictionary)

[English Version](/solution/0700-0799/0720.Longest%20Word%20in%20Dictionary/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出一个字符串数组&nbsp;<code>words</code> 组成的一本英语词典。返回&nbsp;<code>words</code> 中最长的一个单词，该单词是由&nbsp;<code>words</code>&nbsp;词典中其他单词逐步添加一个字母组成。</p>

<p>若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["w","wo","wor","worl", "world"]
<strong>输出：</strong>"world"
<strong>解释：</strong> 单词"world"可由"w", "wo", "wor", 和 "worl"逐步添加一个字母组成。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
<strong>输出：</strong>"apple"
<strong>解释：</strong>"apply" 和 "apple" 都能由词典中的单词组成。但是 "apple" 的字典序小于 "apply" 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 30</code></li>
	<li>所有输入的字符串&nbsp;<code>words[i]</code>&nbsp;都只包含小写字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

用哈希表存放所有单词。遍历这些单词，找出**长度最长且字典序最小**的单词。

**方法二：排序**

优先返回符合条件、**长度最长且字典序最小**的单词，那么可以进行依照该规则，先对 `words` 进行排序，免去多个结果之间的比较。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestWord(self, words: List[str]) -> str:
        cnt, ans = 0, ''
        s = set(words)
        for w in s:
            n = len(w)
            if all(w[:i] in s for i in range(1, n)):
                if cnt < n:
                    cnt, ans = n, w
                elif cnt == n and w < ans:
                    ans = w
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Set<String> s;

    public String longestWord(String[] words) {
        s = new HashSet<>(Arrays.asList(words));
        int cnt = 0;
        String ans = "";
        for (String w : s) {
            int n = w.length();
            if (check(w)) {
                if (cnt < n) {
                    cnt = n;
                    ans = w;
                } else if (cnt == n && w.compareTo(ans) < 0) {
                    ans = w;
                }
            }
        }
        return ans;
    }

    private boolean check(String word) {
        for (int i = 1, n = word.length(); i < n; ++i) {
            if (!s.contains(word.substring(0, i))) {
                return false;
            }
        }
        return true;
    }
}
```

### **TypeScript**

```ts
function longestWord(words: string[]): string {
    words.sort((a, b) => {
        const n = a.length;
        const m = b.length;
        if (n === m) {
            return a < b ? -1 : 1;
        }
        return m - n;
    });
    for (const word of words) {
        let isPass = true;
        for (let i = 1; i <= word.length; i++) {
            if (!words.includes(word.slice(0, i))) {
                isPass = false;
                break;
            }
        }
        if (isPass) {
            return word;
        }
    }
    return '';
}
```

### **Rust**

```rust
impl Solution {
    pub fn longest_word(mut words: Vec<String>) -> String {
        words.sort_unstable_by(|a, b| (b.len(), a).cmp(&(a.len(), b)));
        for word in words.iter() {
            let mut is_pass = true;
            for i in 1..=word.len() {
                if !words.contains(&word[..i].to_string()) {
                    is_pass = false;
                    break;
                }
            }
            if is_pass {
                return word.clone();
            }
        }
        String::new()
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string longestWord(vector<string>& words) {
        unordered_set<string> s(words.begin(), words.end());
        int cnt = 0;
        string ans = "";
        for (auto w : s) {
            int n = w.size();
            if (check(w, s)) {
                if (cnt < n) {
                    cnt = n;
                    ans = w;
                } else if (cnt == n && w < ans)
                    ans = w;
            }
        }
        return ans;
    }

    bool check(string& word, unordered_set<string>& s) {
        for (int i = 1, n = word.size(); i < n; ++i)
            if (!s.count(word.substr(0, i)))
                return false;
        return true;
    }
};
```

### **Go**

```go
func longestWord(words []string) string {
	s := make(map[string]bool)
	for _, w := range words {
		s[w] = true
	}
	cnt := 0
	ans := ""
	check := func(word string) bool {
		for i, n := 1, len(word); i < n; i++ {
			if !s[word[:i]] {
				return false
			}
		}
		return true
	}
	for w, _ := range s {
		n := len(w)
		if check(w) {
			if cnt < n {
				cnt, ans = n, w
			} else if cnt == n && w < ans {
				ans = w
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
