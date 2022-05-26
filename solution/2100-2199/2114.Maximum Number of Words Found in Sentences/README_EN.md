# [2114. Maximum Number of Words Found in Sentences](https://leetcode.com/problems/maximum-number-of-words-found-in-sentences)

[中文文档](/solution/2100-2199/2114.Maximum%20Number%20of%20Words%20Found%20in%20Sentences/README.md)

## Description

<p>A <strong>sentence</strong> is a list of <strong>words</strong> that are separated by a single space&nbsp;with no leading or trailing spaces.</p>

<p>You are given an array of strings <code>sentences</code>, where each <code>sentences[i]</code> represents a single <strong>sentence</strong>.</p>

<p>Return <em>the <strong>maximum number of words</strong> that appear in a single sentence</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> sentences = [&quot;alice and bob love leetcode&quot;, &quot;i think so too&quot;, <u>&quot;this is great thanks very much&quot;</u>]
<strong>Output:</strong> 6
<strong>Explanation:</strong> 
- The first sentence, &quot;alice and bob love leetcode&quot;, has 5 words in total.
- The second sentence, &quot;i think so too&quot;, has 4 words in total.
- The third sentence, &quot;this is great thanks very much&quot;, has 6 words in total.
Thus, the maximum number of words in a single sentence comes from the third sentence, which has 6 words.
</pre>

<p><strong>Example 2:</strong></p>

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
        for (String s : sentences) {
            ans = Math.max(ans, 1 + count(s, ' '));
        }
        return ans;
    }

    private int count(String s, char c) {
        int cnt = 0;
        for (char ch : s.toCharArray()) {
            if (ch == c) {
                ++cnt;
            }
        }
        return cnt;
    }
}

```

### **C++**

```cpp
class Solution {
public:
    int mostWordsFound(vector<string>& sentences) {
        int ans = 0;
        for (string& s : sentences)
            ans = max(ans, 1 + count(s, ' '));
        return ans;
    }

    int count(string s, char c) {
        int cnt = 0;
        for (char& ch : s)
            if (ch == c)
                ++cnt;
        return cnt;
    }
};
```

### **Go**

```go
func mostWordsFound(sentences []string) int {
	count := func(s string, c rune) int {
		cnt := 0
		for _, ch := range s {
			if ch == c {
				cnt++
			}
		}
		return cnt
	}
	ans := 0
	for _, s := range sentences {
		ans = max(ans, 1+count(s, ' '))
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
