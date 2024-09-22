---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3295.Report%20Spam%20Message/README_EN.md
---

<!-- problem:start -->

# [3295. Report Spam Message](https://leetcode.com/problems/report-spam-message)

[中文文档](/solution/3200-3299/3295.Report%20Spam%20Message/README.md)

## Description

<!-- description:start -->

<p>You are given an array of strings <code>message</code> and an array of strings <code>bannedWords</code>.</p>

<p>An array of words is considered <strong>spam</strong> if there are <strong>at least</strong> two words in it that <b>exactly</b> match any word in <code>bannedWords</code>.</p>

<p>Return <code>true</code> if the array <code>message</code> is spam, and <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">message = [&quot;hello&quot;,&quot;world&quot;,&quot;leetcode&quot;], bannedWords = [&quot;world&quot;,&quot;hello&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>The words <code>&quot;hello&quot;</code> and <code>&quot;world&quot;</code> from the <code>message</code> array both appear in the <code>bannedWords</code> array.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">message = [&quot;hello&quot;,&quot;programming&quot;,&quot;fun&quot;], bannedWords = [&quot;world&quot;,&quot;programming&quot;,&quot;leetcode&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>Only one word from the <code>message</code> array (<code>&quot;programming&quot;</code>) appears in the <code>bannedWords</code> array.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= message.length, bannedWords.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= message[i].length, bannedWords[i].length &lt;= 15</code></li>
	<li><code>message[i]</code> and <code>bannedWords[i]</code> consist only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We use a hash table $s$ to store all the words in $\textit{bannedWords}$. Then, we traverse each word in $\textit{message}$. If the word appears in the hash table $s$, we increment the counter $cnt$ by one. If $cnt$ is greater than or equal to $2$, we return $\text{true}$; otherwise, we return $\text{false}$.

The time complexity is $O((n + m) \times |w|)$, and the space complexity is $O(m \times |w|)$. Here, $n$ is the length of the array $\textit{message}$, and $m$ and $|w|$ are the length of the array $\textit{bannedWords}$ and the maximum length of the words in the array, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reportSpam(self, message: List[str], bannedWords: List[str]) -> bool:
        s = set(bannedWords)
        return sum(w in s for w in message) >= 2
```

#### Java

```java
class Solution {
    public boolean reportSpam(String[] message, String[] bannedWords) {
        Set<String> s = new HashSet<>();
        for (var w : bannedWords) {
            s.add(w);
        }
        int cnt = 0;
        for (var w : message) {
            if (s.contains(w) && ++cnt >= 2) {
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
    bool reportSpam(vector<string>& message, vector<string>& bannedWords) {
        unordered_set<string> s(bannedWords.begin(), bannedWords.end());
        int cnt = 0;
        for (const auto& w : message) {
            if (s.contains(w) && ++cnt >= 2) {
                return true;
            }
        }
        return false;
    }
};
```

#### Go

```go
func reportSpam(message []string, bannedWords []string) bool {
	s := map[string]bool{}
	for _, w := range bannedWords {
		s[w] = true
	}
	cnt := 0
	for _, w := range message {
		if s[w] {
			cnt++
			if cnt >= 2 {
				return true
			}
		}
	}
	return false
}
```

#### TypeScript

```ts
function reportSpam(message: string[], bannedWords: string[]): boolean {
    const s = new Set<string>(bannedWords);
    let cnt = 0;
    for (const w of message) {
        if (s.has(w) && ++cnt >= 2) {
            return true;
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
