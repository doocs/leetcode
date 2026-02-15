---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3838.Weighted%20Word%20Mapping/README_EN.md
---

<!-- problem:start -->

# [3838. Weighted Word Mapping](https://leetcode.com/problems/weighted-word-mapping)

[中文文档](/solution/3800-3899/3838.Weighted%20Word%20Mapping/README.md)

## Description

<!-- description:start -->

<p>You are given an array of strings <code>words</code>, where each string represents a word containing lowercase English letters.</p>

<p>You are also given an integer array <code>weights</code> of length 26, where <code>weights[i]</code> represents the weight of the <code>i<sup>th</sup></code> lowercase English letter.</p>

<p>The <strong>weight</strong> of a word is defined as the <strong>sum</strong> of the weights of its characters.</p>

<p>For each word, take its weight modulo 26 and map the result to a lowercase English letter using reverse alphabetical order (<code>0 -&gt; &#39;z&#39;, 1 -&gt; &#39;y&#39;, ..., 25 -&gt; &#39;a&#39;</code>).</p>

<p>Return a string formed by concatenating the mapped characters for all words in order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;abcd&quot;,&quot;def&quot;,&quot;xyz&quot;], weights = [5,3,12,14,1,2,3,2,10,6,6,9,7,8,7,10,8,9,6,9,9,8,3,7,7,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;rij&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The weight of <code>&quot;abcd&quot;</code> is <code>5 + 3 + 12 + 14 = 34</code>. The result modulo 26 is <code>34 % 26 = 8</code>, which maps to <code>&#39;r&#39;</code>.</li>
	<li>The weight of <code>&quot;def&quot;</code> is <code>14 + 1 + 2 = 17</code>. The result modulo 26 is <code>17 % 26 = 17</code>, which maps to <code>&#39;i&#39;</code>.</li>
	<li>The weight of <code>&quot;xyz&quot;</code> is <code>7 + 7 + 2 = 16</code>. The result modulo 26 is <code>16 % 26 = 16</code>, which maps to <code>&#39;j&#39;</code>.</li>
</ul>

<p>Thus, the string formed by concatenating the mapped characters is <code>&quot;rij&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;], weights = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;yyy&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Each word has weight 1. The result modulo 26 is <code>1 % 26 = 1</code>, which maps to <code>&#39;y&#39;</code>.</p>

<p>Thus, the string formed by concatenating the mapped characters is <code>&quot;yyy&quot;</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;abcd&quot;], weights = [7,5,3,4,3,5,4,9,4,2,2,7,10,2,5,10,6,1,2,2,4,1,3,4,4,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;g&quot;</span></p>

<p><strong>Explanation:​​​​​​​</strong></p>

<p>The weight of <code>&quot;abcd&quot;</code> is <code>7 + 5 + 3 + 4 = 19</code>. The result modulo 26 is <code>19 % 26 = 19</code>, which maps to <code>&#39;g&#39;</code>.</p>

<p>Thus, the string formed by concatenating the mapped characters is <code>&quot;g&quot;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>weights.length == 26</code></li>
	<li><code>1 &lt;= weights[i] &lt;= 100</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

### Solution 1: Simulation

We iterate through each word $w$ in $\textit{words}$, calculate its weight $s$, which is the sum of the weights of all characters in the word. Then we calculate $s$ modulo 26, map the result to a lowercase English letter, and finally concatenate all the mapped characters and return.

The time complexity is $O(L)$, where $L$ is the sum of the lengths of all words in $\textit{words}$. The space complexity is $O(W)$, where $W$ is the length of $\textit{words}$.

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def mapWordWeights(self, words: List[str], weights: List[int]) -> str:
        ans = []
        for w in words:
            s = sum(weights[ord(c) - ord('a')] for c in w)
            ans.append(ascii_lowercase[25 - s % 26])
        return ''.join(ans)
```

#### Java

```java
class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        var ans = new StringBuilder();
        for (var w : words) {
            int s = 0;
            for (char c : w.toCharArray()) {
                s = (s + weights[c - 'a']) % 26;
            }
            ans.append((char) ('a' + (25 - s)));
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string mapWordWeights(vector<string>& words, vector<int>& weights) {
        string ans;
        for (const string& w : words) {
            int s = 0;
            for (char c : w) {
                s = (s + weights[c - 'a']) % 26;
            }
            ans.push_back(char('a' + (25 - s)));
        }
        return ans;
    }
};
```

#### Go

```go
func mapWordWeights(words []string, weights []int) string {
	ans := make([]byte, 0, len(words))
	for _, w := range words {
		s := 0
		for i := 0; i < len(w); i++ {
			s = (s + weights[int(w[i]-'a')]) % 26
		}
		ans = append(ans, byte('a'+(25-s)))
	}
	return string(ans)
}
```

#### TypeScript

```ts
function mapWordWeights(words: string[], weights: number[]): string {
    const ans: string[] = [];
    for (const w of words) {
        let s = 0;
        for (const c of w) {
            s = (s + weights[c.charCodeAt(0) - 97]) % 26;
        }
        ans.push(String.fromCharCode(97 + (25 - s)));
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
