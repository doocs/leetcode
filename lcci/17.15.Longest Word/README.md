---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/17.15.Longest%20Word/README.md
---

<!-- problem:start -->

# [面试题 17.15. 最长单词](https://leetcode.cn/problems/longest-word-lcci)

[English Version](/lcci/17.15.Longest%20Word/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一组单词<code>words</code>，编写一个程序，找出其中的最长单词，且该单词由这组单词中的其他单词组合而成。若有多个长度相同的结果，返回其中字典序最小的一项，若没有符合要求的单词则返回空字符串。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong> ["cat","banana","dog","nana","walk","walker","dogwalker"]
<strong>输出：</strong> "dogwalker"
<strong>解释：</strong> "dogwalker"可由"dog"和"walker"组成。
</pre>
<p><strong>提示：</strong></p>
<ul>
<li><code>0 <= len(words) <= 100</code></li>
<li><code>1 <= len(words[i]) <= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 排序 + DFS

注意，题目中，每个单词实际上允许重复使用。

我们可以用一个哈希表 $\textit{s}$ 存储所有单词，然后对单词按照长度降序排序，如果长度相同，按照字典序升序排序。

接下来，我们遍历排序后的单词列表，对于每个单词 $\textit{w}$，我们先将其从哈希表 $\textit{s}$ 中移除，然后使用深度优先搜索 $\textit{dfs}$ 判断 $\textit{w}$ 是否可以由其他单词组成，如果可以，返回 $\textit{w}$。

函数 $\textit{dfs}$ 的执行逻辑如下：

-   如果 $\textit{w}$ 为空，返回 $\text{true}$；
-   遍历 $\textit{w}$ 的所有前缀，如果前缀在哈希表 $\textit{s}$ 中且 $\textit{dfs}$ 返回 $\text{true}$，则返回 $\text{true}$；
-   如果没有符合条件的前缀，返回 $\text{false}$。

如果没有找到符合条件的单词，返回空字符串。

时间复杂度 $O(m \times n \times \log n + n \times 2^M)$，空间复杂度 $O(m \times n)$。其中 $n$ 和 $m$ 分别为单词列表的长度和单词的平均长度，而 $M$ 为最长单词的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestWord(self, words: List[str]) -> str:
        def dfs(w: str) -> bool:
            if not w:
                return True
            for k in range(1, len(w) + 1):
                if w[:k] in s and dfs(w[k:]):
                    return True
            return False

        s = set(words)
        words.sort(key=lambda x: (-len(x), x))
        for w in words:
            s.remove(w)
            if dfs(w):
                return w
        return ""
```

#### Java

```java
class Solution {
    private Set<String> s = new HashSet<>();

    public String longestWord(String[] words) {
        for (String w : words) {
            s.add(w);
        }
        Arrays.sort(words, (a, b) -> {
            if (a.length() != b.length()) {
                return b.length() - a.length();
            }
            return a.compareTo(b);
        });
        for (String w : words) {
            s.remove(w);
            if (dfs(w)) {
                return w;
            }
        }
        return "";
    }

    private boolean dfs(String w) {
        if (w.length() == 0) {
            return true;
        }
        for (int k = 1; k <= w.length(); ++k) {
            if (s.contains(w.substring(0, k)) && dfs(w.substring(k))) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string longestWord(vector<string>& words) {
        unordered_set<string> s(words.begin(), words.end());
        ranges::sort(words, [&](const string& a, const string& b) {
            return a.size() > b.size() || (a.size() == b.size() && a < b);
        });
        auto dfs = [&](this auto&& dfs, string w) -> bool {
            if (w.empty()) {
                return true;
            }
            for (int k = 1; k <= w.size(); ++k) {
                if (s.contains(w.substr(0, k)) && dfs(w.substr(k))) {
                    return true;
                }
            }
            return false;
        };
        for (const string& w : words) {
            s.erase(w);
            if (dfs(w)) {
                return w;
            }
        }
        return "";
    }
};
```

#### Go

```go
func longestWord(words []string) string {
	s := map[string]bool{}
	for _, w := range words {
		s[w] = true
	}
	sort.Slice(words, func(i, j int) bool {
		return len(words[i]) > len(words[j]) || (len(words[i]) == len(words[j]) && words[i] < words[j])
	})
	var dfs func(string) bool
	dfs = func(w string) bool {
		if len(w) == 0 {
			return true
		}
		for k := 1; k <= len(w); k++ {
			if s[w[:k]] && dfs(w[k:]) {
				return true
			}
		}
		return false
	}
	for _, w := range words {
		s[w] = false
		if dfs(w) {
			return w
		}
	}
	return ""
}
```

#### TypeScript

```ts
function longestWord(words: string[]): string {
    const s = new Set(words);

    words.sort((a, b) => (a.length === b.length ? a.localeCompare(b) : b.length - a.length));

    const dfs = (w: string): boolean => {
        if (w === '') {
            return true;
        }
        for (let k = 1; k <= w.length; ++k) {
            if (s.has(w.substring(0, k)) && dfs(w.substring(k))) {
                return true;
            }
        }
        return false;
    };

    for (const w of words) {
        s.delete(w);
        if (dfs(w)) {
            return w;
        }
    }

    return '';
}
```

#### Rust

```rust
use std::collections::HashSet;

impl Solution {
    pub fn longest_word(words: Vec<String>) -> String {
        let mut s: HashSet<String> = words.iter().cloned().collect();
        let mut words = words;
        words.sort_by(|a, b| b.len().cmp(&a.len()).then(a.cmp(b)));

        fn dfs(w: String, s: &mut HashSet<String>) -> bool {
            if w.is_empty() {
                return true;
            }
            for k in 1..=w.len() {
                if s.contains(&w[0..k]) && dfs(w[k..].to_string(), s) {
                    return true;
                }
            }
            false
        }
        for w in words {
            s.remove(&w);
            if dfs(w.clone(), &mut s) {
                return w;
            }
        }
        String::new()
    }
}
```

#### Swift

```swift
class Solution {
    func longestWord(_ words: [String]) -> String {
        var s: Set<String> = Set(words)
        var words = words
        words.sort { (a, b) -> Bool in
            if a.count == b.count {
                return a < b
            } else {
                return a.count > b.count
            }
        }

        func dfs(_ w: String) -> Bool {
            if w.isEmpty {
                return true
            }
            for k in 1...w.count {
                let prefix = String(w.prefix(k))
                if s.contains(prefix) && dfs(String(w.dropFirst(k))) {
                    return true
                }
            }
            return false
        }

        for w in words {
            s.remove(w)
            if dfs(w) {
                return w
            }
        }

        return ""
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
