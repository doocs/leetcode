---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1078.Occurrences%20After%20Bigram/README_EN.md
rating: 1274
source: Weekly Contest 140 Q1
tags:
    - String
---

<!-- problem:start -->

# [1078. Occurrences After Bigram](https://leetcode.com/problems/occurrences-after-bigram)

[中文文档](/solution/1000-1099/1078.Occurrences%20After%20Bigram/README.md)

## Description

<!-- description:start -->

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
	<li>All the words in <code>text</code> are separated by <strong>a single space</strong>.</li>
	<li><code>1 &lt;= first.length, second.length &lt;= 10</code></li>
	<li><code>first</code> and <code>second</code> consist of lowercase English letters.</li>
	<li><code>text</code> will not have any leading or trailing spaces.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

<!-- solution:end -->

<!-- problem:end -->
