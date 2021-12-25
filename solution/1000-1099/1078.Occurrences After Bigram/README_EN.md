# [1078. Occurrences After Bigram](https://leetcode.com/problems/occurrences-after-bigram)

[中文文档](/solution/1000-1099/1078.Occurrences%20After%20Bigram/README.md)

## Description

<p>Given words <code>first</code> and <code>second</code>, consider occurrences in some&nbsp;<code>text</code> of the form &quot;<code>first second third</code>&quot;, where <code>second</code> comes immediately after <code>first</code>, and <code>third</code> comes immediately after <code>second</code>.</p>

<p>For each such occurrence, add &quot;<code>third</code>&quot; to the answer, and return the answer.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>text = <span id="example-input-1-1">&quot;alice is a good girl she is a good student&quot;</span>, first = <span id="example-input-1-2">&quot;a&quot;</span>, second = <span id="example-input-1-3">&quot;good&quot;</span>

<strong>Output: </strong><span id="example-output-1">[&quot;girl&quot;,&quot;student&quot;]</span>

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>text = <span id="example-input-2-1">&quot;we will we will rock you&quot;</span>, first = <span id="example-input-2-2">&quot;we&quot;</span>, second = <span id="example-input-2-3">&quot;will&quot;</span>

<strong>Output: </strong><span id="example-output-2">[&quot;we&quot;,&quot;rock&quot;]</span>

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= text.length &lt;= 1000</code></li>
	<li><code>text</code> consists of space separated words, where each word consists of lowercase English letters.</li>
	<li><code>1 &lt;= first.length, second.length &lt;= 10</code></li>
	<li><code>first</code> and <code>second</code> consist of lowercase English letters.</li>
</ol>

</div>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findOcurrences(self, text: str, first: str, second: str) -> List[str]:
        words = text.split(' ')
        return [words[i + 2] for i in range(len(words) - 2) if words[i] == first and words[i + 1] == second]
```

### **Java**

```java
class Solution {

    public String[] findOcurrences(String text, String first, String second) {
        String[] words = text.split(" ");
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < words.length - 2; ++i) {
            if (first.equals(words[i]) && second.equals(words[i + 1])) {
                ans.add(words[i + 2]);
            }
        }
        return ans.toArray(new String[0]);
    }
}

```

### **C++**

```cpp
class Solution {
public:
    vector<string> findOcurrences(string text, string first, string second) {
        istringstream is(text);
        vector<string> words;
        string word;
        while (is >> word) words.push_back(word);
        vector<string> ans;
        for (int i = 0; i < words.size() - 2; ++i)
            if (words[i] == first && words[i + 1] == second)
                ans.push_back(words[i + 2]);
        return ans;
    }
};
```

### **Go**

```go
func findOcurrences(text string, first string, second string) []string {
	words := strings.Split(text, " ")
	var ans []string
	for i := 0; i < len(words)-2; i++ {
		if words[i] == first && words[i+1] == second {
			ans = append(ans, words[i+2])
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
