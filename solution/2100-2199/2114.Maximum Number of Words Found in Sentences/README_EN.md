# [2114. Maximum Number of Words Found in Sentences](https://leetcode.com/problems/maximum-number-of-words-found-in-sentences)

[中文文档](/solution/2100-2199/2114.Maximum%20Number%20of%20Words%20Found%20in%20Sentences/README.md)

## Description

<p>A <strong>sentence</strong> is a list of <strong>words</strong> that are separated by a single space&nbsp;with no leading or trailing spaces.</p>

<p>You are given an array of strings <code>sentences</code>, where each <code>sentences[i]</code> represents a single <strong>sentence</strong>.</p>

<p>Return <em>the <strong>maximum number of words</strong> that appear in a single sentence</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> sentences = [&quot;alice and bob love leetcode&quot;, &quot;i think so too&quot;, <u>&quot;this is great thanks very much&quot;</u>]
<strong>Output:</strong> 6
<strong>Explanation:</strong> 
- The first sentence, &quot;alice and bob love leetcode&quot;, has 5 words in total.
- The second sentence, &quot;i think so too&quot;, has 4 words in total.
- The third sentence, &quot;this is great thanks very much&quot;, has 6 words in total.
Thus, the maximum number of words in a single sentence comes from the third sentence, which has 6 words.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> sentences = [&quot;please wait&quot;, <u>&quot;continue to fight&quot;</u>, <u>&quot;continue to win&quot;</u>]
<strong>Output:</strong> 3
<strong>Explanation:</strong> It is possible that multiple sentences contain the same number of words. 
In this example, the second and third sentences (underlined) have the same number of words.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= sentences.length &lt;= 100</code></li>
	<li><code>1 &lt;= sentences[i].length &lt;= 100</code></li>
	<li><code>sentences[i]</code> consists only of lowercase English letters and <code>&#39; &#39;</code> only.</li>
	<li><code>sentences[i]</code> does not have leading or trailing spaces.</li>
	<li>All the words in <code>sentences[i]</code> are separated by a single space.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def mostWordsFound(self, sentences: List[str]) -> int:
        return 1 + max(s.count(' ') for s in sentences)
```

### **Java**

```java
class Solution {
    public int mostWordsFound(String[] sentences) {
        int ans = 0;
        for (var s : sentences) {
            int cnt = 1;
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == ' ') {
                    ++cnt;
                }
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int mostWordsFound(vector<string>& sentences) {
        int ans = 0;
        for (auto& s : sentences) {
            int cnt = 1 + count(s.begin(), s.end(), ' ');
            ans = max(ans, cnt);
        }
        return ans;
    }
};
```

### **Go**

```go
func mostWordsFound(sentences []string) (ans int) {
	for _, s := range sentences {
		cnt := 1 + strings.Count(s, " ")
		if ans < cnt {
			ans = cnt
		}
	}
	return
}
```

### **TypeScript**

```ts
function mostWordsFound(sentences: string[]): number {
    return sentences.reduce(
        (r, s) =>
            Math.max(
                r,
                [...s].reduce((r, c) => r + (c === ' ' ? 1 : 0), 1),
            ),
        0,
    );
}
```

### **Rust**

```rust
impl Solution {
    pub fn most_words_found(sentences: Vec<String>) -> i32 {
        let mut ans = 0;
        for s in sentences.iter() {
            let mut count = 1;
            for c in s.as_bytes() {
                if *c == b' ' {
                    count += 1;
                }
            }
            ans = ans.max(count);
        }
        ans
    }
}
```

### **C**

```c
#define max(a, b) (((a) > (b)) ? (a) : (b))

int mostWordsFound(char **sentences, int sentencesSize) {
    int ans = 0;
    for (int i = 0; i < sentencesSize; i++) {
        char *s = sentences[i];
        int count = 1;
        for (int j = 0; s[j]; j++) {
            if (s[j] == ' ') {
                count++;
            }
        }
        ans = max(ans, count);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
