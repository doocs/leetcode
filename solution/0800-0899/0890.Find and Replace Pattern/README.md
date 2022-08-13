# [890. 查找和替换模式](https://leetcode.cn/problems/find-and-replace-pattern)

[English Version](/solution/0800-0899/0890.Find%20and%20Replace%20Pattern/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有一个单词列表&nbsp;<code>words</code>&nbsp;和一个模式&nbsp;&nbsp;<code>pattern</code>，你想知道 <code>words</code> 中的哪些单词与模式匹配。</p>

<p>如果存在字母的排列 <code>p</code>&nbsp;，使得将模式中的每个字母 <code>x</code> 替换为 <code>p(x)</code> 之后，我们就得到了所需的单词，那么单词与模式是匹配的。</p>

<p><em>（回想一下，字母的排列是从字母到字母的双射：每个字母映射到另一个字母，没有两个字母映射到同一个字母。）</em></p>

<p>返回 <code>words</code> 中与给定模式匹配的单词列表。</p>

<p>你可以按任何顺序返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>words = [&quot;abc&quot;,&quot;deq&quot;,&quot;mee&quot;,&quot;aqq&quot;,&quot;dkd&quot;,&quot;ccc&quot;], pattern = &quot;abb&quot;
<strong>输出：</strong>[&quot;mee&quot;,&quot;aqq&quot;]
<strong>解释：
</strong>&quot;mee&quot; 与模式匹配，因为存在排列 {a -&gt; m, b -&gt; e, ...}。
&quot;ccc&quot; 与模式不匹配，因为 {a -&gt; c, b -&gt; c, ...} 不是排列。
因为 a 和 b 映射到同一个字母。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 50</code></li>
	<li><code>1 &lt;= pattern.length = words[i].length&nbsp;&lt;= 20</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findAndReplacePattern(self, words: List[str], pattern: str) -> List[str]:
        def match(s, t):
            m1, m2 = [0] * 128, [0] * 128
            for i, (a, b) in enumerate(zip(s, t), 1):
                if m1[ord(a)] != m2[ord(b)]:
                    return False
                m1[ord(a)] = m2[ord(b)] = i
            return True

        return [word for word in words if match(word, pattern)]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (match(word, pattern)) {
                ans.add(word);
            }
        }
        return ans;
    }

    private boolean match(String s, String t) {
        int[] m1 = new int[128];
        int[] m2 = new int[128];
        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (m1[c1] != m2[c2]) {
                return false;
            }
            m1[c1] = i + 1;
            m2[c2] = i + 1;
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> findAndReplacePattern(vector<string>& words, string pattern) {
        vector<string> ans;
        for (auto& word : words)
            if (match(word, pattern))
                ans.push_back(word);
        return ans;
    }

    bool match(string s, string t) {
        vector<int> m1(128);
        vector<int> m2(128);
        for (int i = 0; i < s.size(); ++i) {
            if (m1[s[i]] != m2[t[i]]) return 0;
            m1[s[i]] = i + 1;
            m2[t[i]] = i + 1;
        }
        return 1;
    }
};
```

### **Go**

```go
func findAndReplacePattern(words []string, pattern string) []string {
	match := func(s, t string) bool {
		m1, m2 := make([]int, 128), make([]int, 128)
		for i := 0; i < len(s); i++ {
			if m1[s[i]] != m2[t[i]] {
				return false
			}
			m1[s[i]] = i + 1
			m2[t[i]] = i + 1
		}
		return true
	}
	var ans []string
	for _, word := range words {
		if match(word, pattern) {
			ans = append(ans, word)
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function findAndReplacePattern(words: string[], pattern: string): string[] {
    return words.filter(word => {
        const map1 = new Map<string, number>();
        const map2 = new Map<string, number>();
        for (let i = 0; i < word.length; i++) {
            if (map1.get(word[i]) !== map2.get(pattern[i])) {
                return false;
            }
            map1.set(word[i], i);
            map2.set(pattern[i], i);
        }
        return true;
    });
}
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    pub fn find_and_replace_pattern(words: Vec<String>, pattern: String) -> Vec<String> {
        let pattern = pattern.as_bytes();
        let n = pattern.len();
        words
            .into_iter()
            .filter(|word| {
                let word = word.as_bytes();
                let mut map1 = HashMap::new();
                let mut map2 = HashMap::new();
                for i in 0..n {
                    if map1.get(&word[i]).unwrap_or(&n) != map2.get(&pattern[i]).unwrap_or(&n) {
                        return false;
                    }
                    map1.insert(word[i], i);
                    map2.insert(pattern[i], i);
                }
                true
            })
            .collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
