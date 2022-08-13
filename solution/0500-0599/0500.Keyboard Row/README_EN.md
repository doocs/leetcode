# [500. Keyboard Row](https://leetcode.com/problems/keyboard-row)

[中文文档](/solution/0500-0599/0500.Keyboard%20Row/README.md)

## Description

<p>Given an array of strings <code>words</code>, return <em>the words that can be typed using letters of the alphabet on only one row of American keyboard like the image below</em>.</p>

<p>In the <strong>American keyboard</strong>:</p>

<ul>
	<li>the first row consists of the characters <code>&quot;qwertyuiop&quot;</code>,</li>
	<li>the second row consists of the characters <code>&quot;asdfghjkl&quot;</code>, and</li>
	<li>the third row consists of the characters <code>&quot;zxcvbnm&quot;</code>.</li>
</ul>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0500.Keyboard%20Row/images/keyboard.png" style="width: 800px; max-width: 600px; height: 267px;" />
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;Hello&quot;,&quot;Alaska&quot;,&quot;Dad&quot;,&quot;Peace&quot;]
<strong>Output:</strong> [&quot;Alaska&quot;,&quot;Dad&quot;]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;omk&quot;]
<strong>Output:</strong> []
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;adsdf&quot;,&quot;sfd&quot;]
<strong>Output:</strong> [&quot;adsdf&quot;,&quot;sfd&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 20</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> consists of English letters (both lowercase and uppercase).&nbsp;</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findWords(self, words: List[str]) -> List[str]:
        s1 = set('qwertyuiop')
        s2 = set('asdfghjkl')
        s3 = set('zxcvbnm')
        res = []
        for word in words:
            t = set(word.lower())
            if t <= s1 or t <= s2 or t <= s3:
                res.append(word)
        return res
```

### **Java**

```java
class Solution {
    public String[] findWords(String[] words) {
        String s1 = "qwertyuiopQWERTYUIOP";
        String s2 = "asdfghjklASDFGHJKL";
        String s3 = "zxcvbnmZXCVBNM";
        List<String> res = new ArrayList<>();
        for (String word : words) {
            int n1 = 0, n2 = 0, n3 = 0;
            int n = word.length();
            for (int i = 0; i < n; ++i) {
                if (s1.contains(String.valueOf(word.charAt(i)))) {
                    ++n1;
                } else if (s2.contains(String.valueOf(word.charAt(i)))) {
                    ++n2;
                } else {
                    ++n3;
                }
            }
            if (n1 == n || n2 == n || n3 == n) {
                res.add(word);
            }
        }
        return res.toArray(new String[0]);
    }
}
```

```java
class Solution {
    public String[] findWords(String[] words) {
        String s = "12210111011122000010020202";
        List<String> res = new ArrayList<>();
        for (String word : words) {
            Set<Character> t = new HashSet<>();
            for (char c : word.toLowerCase().toCharArray()) {
                t.add(s.charAt(c - 'a'));
            }
            if (t.size() == 1) {
                res.add(word);
            }
        }
        return res.toArray(new String[0]);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> findWords(vector<string>& words) {
        string s = "12210111011122000010020202";
        vector<string> ans;
        for (auto& word : words) {
            unordered_set<char> t;
            for (char c : word) t.insert(s[tolower(c) - 'a']);
            if (t.size() == 1) ans.push_back(word);
        }
        return ans;
    }
};
```

### **Go**

```go
func findWords(words []string) []string {
	s := "12210111011122000010020202"
	var ans []string
	for _, word := range words {
		t := make(map[byte]bool)
		for _, c := range word {
			t[s[unicode.ToLower(c)-'a']] = true
		}
		if len(t) == 1 {
			ans = append(ans, word)
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
