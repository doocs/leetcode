---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3926.Count%20Valid%20Word%20Occurrences/README_EN.md
rating: 1608
source: Weekly Contest 501 Q2
---

<!-- problem:start -->

# [3926. Count Valid Word Occurrences](https://leetcode.com/problems/count-valid-word-occurrences)

[中文文档](/solution/3900-3999/3926.Count%20Valid%20Word%20Occurrences/README.md)

## Description

<!-- description:start -->

<p>You are given an array of strings <code>chunks</code>. Concatenate all strings in <code>chunks</code> in order to form a string <code>s</code>.</p>

<p>You are also given an array of strings <code>queries</code>.</p>

<p>A <strong>joiner hyphen</strong> is a hyphen character <code>&#39;-&#39;</code> in <code>s</code> whose previous and next characters both exist and are lowercase English letters.</p>

<p>A <strong>word</strong> is a <strong>maximal</strong> <span data-keyword="substring-nonempty">substring</span> of <code>s</code> consisting only of lowercase English letters and <strong>joiner hyphens</strong>.</p>

<p>All other characters, including spaces and hyphens that are not <strong>joiner hyphens</strong>, are treated as separators.</p>

<p>Return an integer array <code>ans</code>, where <code>ans[i]</code> is the number of times <code>queries[i]</code> appears as a word in <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">chunks = [&quot;hello wor&quot;,&quot;ld hello&quot;], queries = [&quot;hello&quot;,&quot;world&quot;,&quot;wor&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,1,0]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>After concatenating all strings in <code>chunks</code>, <code>s = &quot;hello world hello&quot;</code>.</li>
	<li>The words are <code>&quot;hello&quot;</code>, <code>&quot;world&quot;</code>, and <code>&quot;hello&quot;</code>.</li>
	<li>The substring <code>&quot;wor&quot;</code> appears inside <code>&quot;world&quot;</code>, but it is not a full word.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">chunks = [&quot;a-b a--b &quot;,&quot;a-&quot;,&quot;b&quot;], queries = [&quot;a-b&quot;,&quot;a&quot;,&quot;b&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,1,1]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>After concatenating all strings in <code>chunks</code>, <code>s = &quot;a-b a--b a-b&quot;</code>.</li>
	<li>In <code>&quot;a-b&quot;</code>, the hyphen is a joiner hyphen because it is between two lowercase English letters, so <code>&quot;a-b&quot;</code> is one word.</li>
	<li>In <code>&quot;a--b&quot;</code>, neither hyphen is a joiner hyphen, so it is split into the words <code>&quot;a&quot;</code> and <code>&quot;b&quot;</code>.</li>
	<li>Therefore, the words are <code>&quot;a-b&quot;</code>, <code>&quot;a&quot;</code>, <code>&quot;b&quot;</code>, and <code>&quot;a-b&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">chunks = [&quot;-cat dog- mouse&quot;], queries = [&quot;cat&quot;,&quot;dog&quot;,&quot;mouse&quot;,&quot;cat-dog&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,1,1,0]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>After concatenating all strings in <code>chunks</code>, <code>s = &quot;-cat dog- mouse&quot;</code>.</li>
	<li>The leading hyphen before <code>&quot;cat&quot;</code> and the trailing hyphen after <code>&quot;dog&quot;</code> are not joiner hyphens, so they are separators.</li>
	<li>The words are <code>&quot;cat&quot;</code>, <code>&quot;dog&quot;</code>, and <code>&quot;mouse&quot;</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= chunks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= chunks[i].length &lt;= 10<sup>5</sup></code></li>
	<li>The total length of all strings in <code>chunks</code> does not exceed <code>10<sup>5</sup></code>.</li>
	<li><code>chunks[i]</code> consists only of lowercase English letters, spaces, and <code>&#39;-&#39;</code>.</li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries[i].length &lt;= 10<sup>5</sup></code></li>
	<li>The total length of all strings in <code>queries</code> does not exceed <code>10<sup>5</sup></code>.</li>
	<li><code>queries[i]</code> consists only of lowercase English letters and <code>&#39;-&#39;</code>.</li>
	<li><code>queries[i]</code> is a valid word: it does not start or end with <code>&#39;-&#39;</code>, and it does not contain two consecutive hyphens.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

First, we concatenate all strings in $\textit{chunks}$ to obtain a single string $s$.

Since the first character of a valid word must be a lowercase English letter, we scan $s$ from left to right. When we encounter a lowercase English letter, we continue scanning to the right. If we encounter a space or an invalid hyphen, it means we have found a word. We add this word to a hash table and count its occurrences. Finally, we iterate through each string in $\textit{queries}$, look up its count in the hash table, and append the result to the answer array.

The time complexity is $O(n + m)$, where $n$ is the total length of all strings in $\textit{chunks}$, and $m$ is the total length of all strings in $\textit{queries}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countWordOccurrences(self, chunks: list[str], queries: list[str]) -> list[int]:
        s = "".join(chunks)
        n = len(s)
        cnt = defaultdict(int)
        i = 0
        while i < n:
            if s[i] in " -":
                i += 1
                continue
            j = i
            while (
                j < n
                and s[j] != " "
                and (s[j] != "-" or (j + 1 < n and s[j + 1] not in " -"))
            ):
                j += 1
            cnt[s[i:j]] += 1
            i = j
        return [cnt[q] for q in queries]
```

#### Java

```java
class Solution {
    public int[] countWordOccurrences(String[] chunks, String[] queries) {
        StringBuilder sb = new StringBuilder();
        for (String chunk : chunks) {
            sb.append(chunk);
        }
        String s = sb.toString();
        int n = s.length();
        Map<String, Integer> cnt = new HashMap<>();
        int i = 0;
        while (i < n) {
            char c = s.charAt(i);
            if (c == ' ' || c == '-') {
                i++;
                continue;
            }
            int j = i;
            while (j < n) {
                char cj = s.charAt(j);
                if (cj == ' ') {
                    break;
                }
                if (cj == '-') {
                    if (j + 1 < n) {
                        char cnext = s.charAt(j + 1);
                        if (cnext == ' ' || cnext == '-') {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                j++;
            }
            String word = s.substring(i, j);
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
            i = j;
        }
        int[] ans = new int[queries.length];
        for (int k = 0; k < queries.length; k++) {
            ans[k] = cnt.getOrDefault(queries[k], 0);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> countWordOccurrences(vector<string>& chunks, vector<string>& queries) {
        string s = "";
        for (const string& chunk : chunks) {
            s += chunk;
        }
        int n = s.length();
        unordered_map<string, int> cnt;
        int i = 0;
        while (i < n) {
            if (s[i] == ' ' || s[i] == '-') {
                i++;
                continue;
            }
            int j = i;
            while (j < n && s[j] != ' ' && (s[j] != '-' || (j + 1 < n && s[j + 1] != ' ' && s[j + 1] != '-'))) {
                j++;
            }
            cnt[s.substr(i, j - i)]++;
            i = j;
        }
        vector<int> ans;
        ans.reserve(queries.size());
        for (const string& q : queries) {
            ans.push_back(cnt[q]);
        }
        return ans;
    }
};
```

#### Go

```go
func countWordOccurrences(chunks []string, queries []string) []int {
	s := strings.Join(chunks, "")
	n := len(s)
	cnt := make(map[string]int)
	i := 0
	for i < n {
		if s[i] == ' ' || s[i] == '-' {
			i++
			continue
		}
		j := i
		for j < n && s[j] != ' ' && (s[j] != '-' || (j+1 < n && s[j+1] != ' ' && s[j+1] != '-')) {
			j++
		}
		cnt[s[i:j]]++
		i = j
	}
	ans := make([]int, len(queries))
	for k, q := range queries {
		ans[k] = cnt[q]
	}
	return ans
}
```

#### TypeScript

```ts
function countWordOccurrences(chunks: string[], queries: string[]): number[] {
    const s = chunks.join('');
    const n = s.length;
    const cnt = new Map<string, number>();
    let i = 0;
    while (i < n) {
        if (s[i] === ' ' || s[i] === '-') {
            i++;
            continue;
        }
        let j = i;
        while (
            j < n &&
            s[j] !== ' ' &&
            (s[j] !== '-' || (j + 1 < n && s[j + 1] !== ' ' && s[j + 1] !== '-'))
        ) {
            j++;
        }
        const word = s.substring(i, j);
        cnt.set(word, (cnt.get(word) || 0) + 1);
        i = j;
    }
    return queries.map(q => cnt.get(q) || 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
