# [2490. Circular Sentence](https://leetcode.com/problems/circular-sentence)

[中文文档](/solution/2400-2499/2490.Circular%20Sentence/README.md)

## Description

<p>A <strong>sentence</strong> is a list of words that are separated by a<strong> single</strong> space with no leading or trailing spaces.</p>

<ul>
	<li>For example, <code>&quot;Hello World&quot;</code>, <code>&quot;HELLO&quot;</code>, <code>&quot;hello world hello world&quot;</code> are all sentences.</li>
</ul>

<p>Words consist of <strong>only</strong> uppercase and lowercase English letters. Uppercase and lowercase English letters are considered different.</p>

<p>A sentence is <strong>circular </strong>if:</p>

<ul>
	<li>The last character of a word is equal to the first character of the next word.</li>
	<li>The last character of the last word is equal to the first character of the first word.</li>
</ul>

<p>For example, <code>&quot;leetcode exercises sound delightful&quot;</code>, <code>&quot;eetcode&quot;</code>, <code>&quot;leetcode eats soul&quot; </code>are all circular sentences. However, <code>&quot;Leetcode is cool&quot;</code>, <code>&quot;happy Leetcode&quot;</code>, <code>&quot;Leetcode&quot;</code> and <code>&quot;I like Leetcode&quot;</code> are <strong>not</strong> circular sentences.</p>

<p>Given a string <code>sentence</code>, return <code>true</code><em> if it is circular</em>. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;leetcode exercises sound delightful&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The words in sentence are [&quot;leetcode&quot;, &quot;exercises&quot;, &quot;sound&quot;, &quot;delightful&quot;].
- leetcod<u>e</u>&#39;s&nbsp;last character is equal to <u>e</u>xercises&#39;s first character.
- exercise<u>s</u>&#39;s&nbsp;last character is equal to <u>s</u>ound&#39;s first character.
- soun<u>d</u>&#39;s&nbsp;last character is equal to <u>d</u>elightful&#39;s first character.
- delightfu<u>l</u>&#39;s&nbsp;last character is equal to <u>l</u>eetcode&#39;s first character.
The sentence is circular.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;eetcode&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The words in sentence are [&quot;eetcode&quot;].
- eetcod<u>e</u>&#39;s&nbsp;last character is equal to <u>e</u>etcode&#39;s first character.
The sentence is circular.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;Leetcode is cool&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> The words in sentence are [&quot;Leetcode&quot;, &quot;is&quot;, &quot;cool&quot;].
- Leetcod<u>e</u>&#39;s&nbsp;last character is <strong>not</strong> equal to <u>i</u>s&#39;s first character.
The sentence is <strong>not</strong> circular.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= sentence.length &lt;= 500</code></li>
	<li><code>sentence</code> consist of only lowercase and uppercase English letters and spaces.</li>
	<li>The words in <code>sentence</code> are separated by a single space.</li>
	<li>There are no leading or trailing spaces.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isCircularSentence(self, sentence: str) -> bool:
        sentence = sentence.split()
        return all(s[0] == sentence[i - 1][-1] for i, s in enumerate(sentence))
```

### **Java**

```java
class Solution {
    public boolean isCircularSentence(String sentence) {
        if (sentence.charAt(0) != sentence.charAt(sentence.length() - 1)) {
            return false;
        }
        String[] ss = sentence.split(" ");
        for (int i = 1; i < ss.length; ++i) {
            if (ss[i].charAt(0) != ss[i - 1].charAt(ss[i - 1].length() - 1)) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isCircularSentence(string sentence) {
        if (sentence[0] != sentence[sentence.size() - 1]) return false;
        istringstream is(sentence);
        vector<string> ss;
        string s;
        while (is >> s) ss.emplace_back(s);
        for (int i = 1; i < ss.size(); ++i) {
            if (ss[i][0] != ss[i - 1][ss[i - 1].size() - 1]) {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func isCircularSentence(sentence string) bool {
	if sentence[0] != sentence[len(sentence)-1] {
		return false
	}
	ss := strings.Split(sentence, " ")
	for i := 1; i < len(ss); i++ {
		if ss[i][0] != ss[i-1][len(ss[i-1])-1] {
			return false
		}
	}
	return true
}
```

### **JavaScript**

```js
var isCircularSentence = function (sentence) {
    const words = sentence.split(' ');
    const post = words[0].charCodeAt(0);
    let prev = words[0].charCodeAt(words[0].length - 1);
    const n = words.length;
    for (let i = 1; i < n; i++) {
        let cur = words[i];
        if (cur.charCodeAt(0) !== prev) {
            return false;
        }
        prev = cur.charCodeAt(cur.length - 1);
    }
    return post === prev;
};
```

### **TypeScript**

```ts
function isCircularSentence(sentence: string): boolean {
    const ss = sentence.split(' ');
    const n = ss.length;
    if (ss[0][0] !== ss[n - 1][ss[n - 1].length - 1]) {
        return false;
    }
    for (let i = 0; i < n - 1; i++) {
        if (ss[i][ss[i].length - 1] !== ss[i + 1][0]) {
            return false;
        }
    }
    return true;
}
```

### **Rust**

```rust
impl Solution {
    pub fn is_circular_sentence(sentence: String) -> bool {
        let ss: Vec<String> = sentence.split(' ').map(String::from).collect();
        let n = ss.len();
        if ss[0].as_bytes()[0] != ss[n - 1].as_bytes()[ss[n - 1].len() - 1] {
            return false;
        }
        for i in 1..n {
            if ss[i - 1].as_bytes()[ss[i - 1].len() - 1] != ss[i].as_bytes()[0] {
                return false;
            }
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
