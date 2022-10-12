# [1451. Rearrange Words in a Sentence](https://leetcode.com/problems/rearrange-words-in-a-sentence)

[中文文档](/solution/1400-1499/1451.Rearrange%20Words%20in%20a%20Sentence/README.md)

## Description

<p>Given a sentence&nbsp;<code>text</code> (A&nbsp;<em>sentence</em>&nbsp;is a string of space-separated words) in the following format:</p>

<ul>
	<li>First letter is in upper case.</li>
	<li>Each word in <code>text</code> are separated by a single space.</li>
</ul>

<p>Your task is to rearrange the words in text such that&nbsp;all words are rearranged in an increasing order of their lengths. If two words have the same length, arrange them in their original order.</p>

<p>Return the new text&nbsp;following the format shown above.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;Leetcode is cool&quot;
<strong>Output:</strong> &quot;Is cool leetcode&quot;
<strong>Explanation: </strong>There are 3 words, &quot;Leetcode&quot; of length 8, &quot;is&quot; of length 2 and &quot;cool&quot; of length 4.
Output is ordered by length and the new first word starts with capital letter.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;Keep calm and code on&quot;
<strong>Output:</strong> &quot;On and keep calm code&quot;
<strong>Explanation: </strong>Output is ordered as follows:
&quot;On&quot; 2 letters.
&quot;and&quot; 3 letters.
&quot;keep&quot; 4 letters in case of tie order by position in original text.
&quot;calm&quot; 4 letters.
&quot;code&quot; 4 letters.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;To be or not to be&quot;
<strong>Output:</strong> &quot;To be or to be not&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>text</code> begins with a capital letter and then contains lowercase letters and single space between words.</li>
	<li><code>1 &lt;= text.length &lt;= 10^5</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def arrangeWords(self, text: str) -> str:
        words = text.split()
        words[0] = words[0].lower()
        words.sort(key=len)
        words[0] = words[0].title()
        return " ".join(words)
```

### **Java**

```java
class Solution {
    public String arrangeWords(String text) {
        String[] words = text.split(" ");
        words[0] = words[0].toLowerCase();
        Arrays.sort(words, Comparator.comparingInt(String::length));
        words[0] = words[0].substring(0, 1).toUpperCase() + words[0].substring(1);
        return String.join(" ", words);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string arrangeWords(string text) {
        vector<string> words;
        stringstream ss(text);
        string t;
        while (ss >> t) {
            words.push_back(t);
        }
        words[0][0] = tolower(words[0][0]);
        stable_sort(words.begin(), words.end(), [](const string& a, const string& b) {
            return a.size() < b.size();
        });
        string ans = "";
        for (auto& s : words) {
            ans += s + " ";
        }
        ans.pop_back();
        ans[0] = toupper(ans[0]);
        return ans;
    }
};
```

### **Go**

```go
func arrangeWords(text string) string {
	words := strings.Split(text, " ")
	words[0] = strings.ToLower(words[0])
	sort.SliceStable(words, func(i, j int) bool { return len(words[i]) < len(words[j]) })
	words[0] = strings.Title(words[0])
	return strings.Join(words, " ")
}
```

### **...**

```

```

<!-- tabs:end -->
