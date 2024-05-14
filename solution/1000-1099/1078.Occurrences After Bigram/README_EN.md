# [1078. Occurrences After Bigram](https://leetcode.com/problems/occurrences-after-bigram)

[中文文档](/solution/1000-1099/1078.Occurrences%20After%20Bigram/README.md)

<!-- tags:String -->

<!-- difficulty:Easy -->

## Description

<p>Given two strings <code>first</code> and <code>second</code>, consider occurrences in some text of the form <code>&quot;first second third&quot;</code>, where <code>second</code> comes immediately after <code>first</code>, and <code>third</code> comes immediately after <code>second</code>.</p>

<p>Return <em>an array of all the words</em> <code>third</code> <em>for each occurrence of</em> <code>&quot;first second third&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> text = "alice is a good girl she is a good student", first = "a", second = "good"
<strong>Output:</strong> ["girl","student"]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> text = "we will we will rock you", first = "we", second = "will"
<strong>Output:</strong> ["we","rock"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 1000</code></li>
	<li><code>text</code> consists of lowercase English letters and spaces.</li>
	<li>All the words in <code>text</code> a separated by <strong>a single space</strong>.</li>
	<li><code>1 &lt;= first.length, second.length &lt;= 10</code></li>
	<li><code>first</code> and <code>second</code> consist of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def findOcurrences(self, text: str, first: str, second: str) -> List[str]:
        words = text.split()
        ans = []
        for i in range(len(words) - 2):
            a, b, c = words[i : i + 3]
            if a == first and b == second:
                ans.append(c)
        return ans
```

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

```cpp
class Solution {
public:
    vector<string> findOcurrences(string text, string first, string second) {
        istringstream is(text);
        vector<string> words;
        string word;
        while (is >> word) {
            words.emplace_back(word);
        }
        vector<string> ans;
        int n = words.size();
        for (int i = 0; i < n - 2; ++i) {
            if (words[i] == first && words[i + 1] == second) {
                ans.emplace_back(words[i + 2]);
            }
        }
        return ans;
    }
};
```

```go
func findOcurrences(text string, first string, second string) (ans []string) {
	words := strings.Split(text, " ")
	n := len(words)
	for i := 0; i < n-2; i++ {
		if words[i] == first && words[i+1] == second {
			ans = append(ans, words[i+2])
		}
	}
	return
}
```

```ts
function findOcurrences(text: string, first: string, second: string): string[] {
    const words = text.split(' ');
    const n = words.length;
    const ans: string[] = [];
    for (let i = 0; i < n - 2; i++) {
        if (words[i] === first && words[i + 1] === second) {
            ans.push(words[i + 2]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
