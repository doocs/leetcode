# [1324. Print Words Vertically](https://leetcode.com/problems/print-words-vertically)

[中文文档](/solution/1300-1399/1324.Print%20Words%20Vertically/README.md)

## Description

<p>Given a string <code>s</code>.&nbsp;Return&nbsp;all the words vertically in the same order in which they appear in <code>s</code>.<br />

Words are returned as a list of strings, complete with&nbsp;spaces when is necessary. (Trailing spaces are not allowed).<br />

Each word would be put on only one column and that in one column there will be only one word.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> s = &quot;HOW ARE YOU&quot;

<strong>Output:</strong> [&quot;HAY&quot;,&quot;ORO&quot;,&quot;WEU&quot;]

<strong>Explanation: </strong>Each word is printed vertically. 

 &quot;HAY&quot;

&nbsp;&quot;ORO&quot;

&nbsp;&quot;WEU&quot;

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> s = &quot;TO BE OR NOT TO BE&quot;

<strong>Output:</strong> [&quot;TBONTB&quot;,&quot;OEROOE&quot;,&quot;   T&quot;]

<strong>Explanation: </strong>Trailing spaces is not allowed. 

&quot;TBONTB&quot;

&quot;OEROOE&quot;

&quot;   T&quot;

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> s = &quot;CONTEST IS COMING&quot;

<strong>Output:</strong> [&quot;CIC&quot;,&quot;OSO&quot;,&quot;N M&quot;,&quot;T I&quot;,&quot;E N&quot;,&quot;S G&quot;,&quot;T&quot;]

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>1 &lt;= s.length &lt;= 200</code></li>
    <li><code>s</code>&nbsp;contains only upper case English letters.</li>
    <li>It&#39;s guaranteed that there is only one&nbsp;space between 2 words.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def printVertically(self, s: str) -> List[str]:
        words = s.split()
        m, n = len(words), max(len(word) for word in words)
        ans = []
        for j in range(n):
            t = []
            for i in range(m):
                word = words[i]
                t.append(word[j] if j < len(word) else ' ')
            ans.append(''.join(t).rstrip())
        return ans
```

### **Java**

```java
class Solution {
    public List<String> printVertically(String s) {
        String[] words = s.split(" ");
        int m = words.length, n = maxLen(words);
        List<String> ans = new ArrayList<>();
        for (int j = 0; j < n; ++j) {
            StringBuilder t = new StringBuilder();
            for (int i = 0; i < m; ++i) {
                String word = words[i];
                t.append(j < word.length() ? word.charAt(j) : ' ');
            }
            ans.add(rstrip(t));
        }
        return ans;
    }

    private int maxLen(String[] words) {
        int ans = 0;
        for (String word : words) {
            ans = Math.max(ans, word.length());
        }
        return ans;
    }

    private String rstrip(StringBuilder s) {
        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) != ' ') {
                return s.substring(0, i + 1);
            }
        }
        return "";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> printVertically(string s) {
        stringstream in(s);
        vector<string> words;
        string word;
        int n = 0;
        while (in >> word) {
            words.push_back(word);
            n = max(n, (int)word.size());
        }
        int m = words.size();
        vector<string> ans;
        for (int j = 0; j < n; ++j) {
            string t = "";
            for (int i = 0; i < m; ++i) {
                word = words[i];
                t += j < word.size() ? word[j] : ' ';
            }
            while (t.back() == ' ') {
                t.pop_back();
            }
            ans.push_back(t);
        }
        return ans;
    }
};
```

### **Go**

```go
func printVertically(s string) []string {
	words := strings.Split(s, " ")
	m := len(words)
	var n int
	for _, word := range words {
		if n < len(word) {
			n = len(word)
		}
	}
	var ans []string
	for j := 0; j < n; j++ {
		var t []byte
		for i := 0; i < m; i++ {
			word := words[i]
			if j < len(word) {
				t = append(t, word[j])
			} else {
				t = append(t, ' ')
			}
		}
		s = string(t)
		ans = append(ans, rstrip(s))
	}
	return ans
}

func rstrip(s string) string {
	for i := len(s) - 1; i >= 0; i-- {
		if s[i] != ' ' {
			return s[:i+1]
		}
	}
	return s
}
```

### **...**

```

```

<!-- tabs:end -->
