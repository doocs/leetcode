---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/17.15.Longest%20Word/README_EN.md
---

<!-- problem:start -->

# [17.15. Longest Word](https://leetcode.cn/problems/longest-word-lcci)

[中文文档](/lcci/17.15.Longest%20Word/README.md)

## Description

<!-- description:start -->

<p>Given a list of words, write a program to find the longest word made of other words in the list. If there are more than one answer, return the one that has smallest lexicographic order. If no answer, return an empty string.</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong> [&quot;cat&quot;,&quot;banana&quot;,&quot;dog&quot;,&quot;nana&quot;,&quot;walk&quot;,&quot;walker&quot;,&quot;dogwalker&quot;]

<strong>Output: </strong> &quot;dogwalker&quot;

<strong>Explanation: </strong> &quot;dogwalker&quot; can be made of &quot;dog&quot; and &quot;walker&quot;.

</pre>

<p><strong>Note: </strong></p>

<ul>
	<li><code>0 &lt;= len(words) &lt;= 100</code></li>
	<li><code>1 &lt;= len(words[i]) &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Sorting + DFS

Note that in the problem, each word can actually be reused.

We can use a hash table $\textit{s}$ to store all the words, then sort the words in descending order of length, and if the lengths are the same, sort them in ascending lexicographical order.

Next, we iterate through the sorted list of words. For each word $\textit{w}$, we first remove it from the hash table $\textit{s}$, then use depth-first search $\textit{dfs}$ to determine if $\textit{w}$ can be composed of other words. If it can, we return $\textit{w}$.

The execution logic of the function $\textit{dfs}$ is as follows:

- If $\textit{w}$ is empty, return $\text{true}$;
- Iterate through all prefixes of $\textit{w}$. If a prefix is in the hash table $\textit{s}$ and $\textit{dfs}$ returns $\text{true}$, then return $\text{true}$;
- If no prefix meets the condition, return $\text{false}$.

If no word meets the condition, return an empty string.

The time complexity is $O(m \times n \times \log n + n \times 2^M)$, and the space complexity is $O(m \times n)$. Here, $n$ and $m$ are the length of the word list and the average length of the words, respectively, and $M$ is the length of the longest word.

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
