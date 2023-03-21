# [1324. Print Words Vertically](https://leetcode.com/problems/print-words-vertically)

[中文文档](/solution/1300-1399/1324.Print%20Words%20Vertically/README.md)

## Description

<p>Given a string <code>s</code>.&nbsp;Return&nbsp;all the words vertically in the same order in which they appear in <code>s</code>.<br />

Words are returned as a list of strings, complete with&nbsp;spaces when is necessary. (Trailing spaces are not allowed).<br />

Each word would be put on only one column and that in one column there will be only one word.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> s = &quot;HOW ARE YOU&quot;

<strong>Output:</strong> [&quot;HAY&quot;,&quot;ORO&quot;,&quot;WEU&quot;]

<strong>Explanation: </strong>Each word is printed vertically. 

 &quot;HAY&quot;

&nbsp;&quot;ORO&quot;

&nbsp;&quot;WEU&quot;

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> s = &quot;TO BE OR NOT TO BE&quot;

<strong>Output:</strong> [&quot;TBONTB&quot;,&quot;OEROOE&quot;,&quot;   T&quot;]

<strong>Explanation: </strong>Trailing spaces is not allowed. 

&quot;TBONTB&quot;

&quot;OEROOE&quot;

&quot;   T&quot;

</pre>

<p><strong class="example">Example 3:</strong></p>

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
        n = max(len(w) for w in words)
        ans = []
        for j in range(n):
            t = [w[j] if j < len(w) else ' ' for w in words]
            while t[-1] == ' ':
                t.pop()
            ans.append(''.join(t))
        return ans
```

### **Java**

```java
class Solution {
    public List<String> printVertically(String s) {
        String[] words = s.split(" ");
        int n = 0;
        for (var w : words) {
            n = Math.max(n, w.length());
        }
        List<String> ans = new ArrayList<>();
        for (int j = 0; j < n; ++j) {
            StringBuilder t = new StringBuilder();
            for (var w : words) {
                t.append(j < w.length() ? w.charAt(j) : ' ');
            }
            while (t.length() > 0 && t.charAt(t.length() - 1) == ' ') {
                t.deleteCharAt(t.length() - 1);
            }
            ans.add(t.toString());
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> printVertically(string s) {
        stringstream ss(s);
        vector<string> words;
        string word;
        int n = 0;
        while (ss >> word) {
            words.emplace_back(word);
            n = max(n, (int) word.size());
        }
        vector<string> ans;
        for (int j = 0; j < n; ++j) {
            string t;
            for (auto& w : words) {
                t += j < w.size() ? w[j] : ' ';
            }
            while (t.size() && t.back() == ' ') {
                t.pop_back();
            }
            ans.emplace_back(t);
        }
        return ans;
    }
};
```

### **Go**

```go
func printVertically(s string) (ans []string) {
	words := strings.Split(s, " ")
	n := 0
	for _, w := range words {
		n = max(n, len(w))
	}
	for j := 0; j < n; j++ {
		t := []byte{}
		for _, w := range words {
			if j < len(w) {
				t = append(t, w[j])
			} else {
				t = append(t, ' ')
			}
		}
		for len(t) > 0 && t[len(t)-1] == ' ' {
			t = t[:len(t)-1]
		}
		ans = append(ans, string(t))
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
