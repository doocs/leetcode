# [1859. Sorting the Sentence](https://leetcode.com/problems/sorting-the-sentence)

[中文文档](/solution/1800-1899/1859.Sorting%20the%20Sentence/README.md)

## Description

<p>A <strong>sentence</strong> is a list of words that are separated by a single space with no leading or trailing spaces. Each word consists of lowercase and uppercase English letters.</p>

<p>A sentence can be <strong>shuffled</strong> by appending the <strong>1-indexed word position</strong> to each word then rearranging the words in the sentence.</p>

<ul>

    <li>For example, the sentence <code>&quot;This is a sentence&quot;</code> can be shuffled as <code>&quot;sentence4 a3 is2 This1&quot;</code> or <code>&quot;is2 sentence4 This1 a3&quot;</code>.</li>

</ul>

<p>Given a <strong>shuffled sentence</strong> <code>s</code> containing no more than <code>9</code> words, reconstruct and return <em>the original sentence</em>.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> s = &quot;is2 sentence4 This1 a3&quot;

<strong>Output:</strong> &quot;This is a sentence&quot;

<strong>Explanation:</strong> Sort the words in s to their original positions &quot;This1 is2 a3 sentence4&quot;, then remove the numbers.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> s = &quot;Myself2 Me1 I4 and3&quot;

<strong>Output:</strong> &quot;Me Myself and I&quot;

<strong>Explanation:</strong> Sort the words in s to their original positions &quot;Me1 Myself2 and3 I4&quot;, then remove the numbers.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>2 &lt;= s.length &lt;= 200</code></li>

    <li><code>s</code> consists of lowercase and uppercase English letters, spaces, and digits from <code>1</code> to <code>9</code>.</li>

    <li>The number of words in <code>s</code> is between <code>1</code> and <code>9</code>.</li>

    <li>The words in <code>s</code> are separated by a single space.</li>

    <li><code>s</code> contains no leading or trailing spaces.</li>

</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sortSentence(self, s: str) -> str:
        words = s.split()
        ans = [None] * len(words)
        for w in words:
            i = int(w[-1]) - 1
            ans[i] = w[:-1]
        return ' '.join(ans)
```

### **Java**

```java
class Solution {
    public String sortSentence(String s) {
        String[] words = s.split(" ");
        String[] ans = new String[words.length];
        for (String w : words) {
            int i = w.charAt(w.length() - 1) - '1';
            ans[i] = w.substring(0, w.length() - 1);
        }
        return String.join(" ", ans);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string sortSentence(string s) {
        istringstream is(s);
        string t;
        vector<string> words;
        while (is >> t) words.push_back(t);
        vector<string> res(words.size());
        for (auto& w : words) {
            int i = w[w.size() - 1] - '1';
            res[i] = w.substr(0, w.size() - 1);
        }
        string ans;
        for (auto& w : res) {
            ans += w + " ";
        }
        ans.pop_back();
        return ans;
    }
};
```

### **Go**

```go
func sortSentence(s string) string {
	words := strings.Split(s, " ")
	ans := make([]string, len(words))
	for _, w := range words {
		i := w[len(w)-1] - '1'
		ans[i] = w[:len(w)-1]
	}
	return strings.Join(ans, " ")
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {string}
 */
var sortSentence = function (s) {
    const words = s.split(' ');
    const ans = new Array(words.length);
    for (const w of words) {
        const i = w.charCodeAt(w.length - 1) - '1'.charCodeAt(0);
        ans[i] = w.slice(0, w.length - 1);
    }
    return ans.join(' ');
};
```

### **TypeScript**

```ts
function sortSentence(s: string): string {
    const words = s.split(' ');
    const ans = new Array(words.length);
    for (const w of words) {
        const i = w.charCodeAt(w.length - 1) - '1'.charCodeAt(0);
        ans[i] = w.slice(0, w.length - 1);
    }
    return ans.join(' ');
}
```

### **...**

```

```

<!-- tabs:end -->
