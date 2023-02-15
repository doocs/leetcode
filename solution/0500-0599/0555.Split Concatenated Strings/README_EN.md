# [555. Split Concatenated Strings](https://leetcode.com/problems/split-concatenated-strings)

[中文文档](/solution/0500-0599/0555.Split%20Concatenated%20Strings/README.md)

## Description

<p>You are given an array of strings <code>strs</code>. You could concatenate these strings together into a loop, where for each string, you could choose to reverse it or not. Among all the possible loops</p>

<p>Return <em>the lexicographically largest string after cutting the loop, which will make the looped string into a regular one</em>.</p>

<p>Specifically, to find the lexicographically largest string, you need to experience two phases:</p>

<ol>
	<li>Concatenate all the strings into a loop, where you can reverse some strings or not and connect them in the same order as given.</li>
	<li>Cut and make one breakpoint in any place of the loop, which will make the looped string into a regular one starting from the character at the cutpoint.</li>
</ol>

<p>And your job is to find the lexicographically largest one among all the possible regular strings.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;abc&quot;,&quot;xyz&quot;]
<strong>Output:</strong> &quot;zyxcba&quot;
<strong>Explanation:</strong> You can get the looped string &quot;-abcxyz-&quot;, &quot;-abczyx-&quot;, &quot;-cbaxyz-&quot;, &quot;-cbazyx-&quot;, where &#39;-&#39; represents the looped status. 
The answer string came from the fourth looped one, where you could cut from the middle character &#39;a&#39; and get &quot;zyxcba&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;abc&quot;]
<strong>Output:</strong> &quot;cba&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 1000</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 1000</code></li>
	<li><code>1 &lt;= sum(strs[i].length) &lt;= 1000</code></li>
	<li><code>strs[i]</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def splitLoopedString(self, strs: List[str]) -> str:
        strs = [s[::-1] if s[::-1] > s else s for s in strs]
        ans = ''.join(strs)
        for i, s in enumerate(strs):
            t = ''.join(strs[i + 1:]) + ''.join(strs[: i])
            for j in range(len(s)):
                a = s[j:]
                b = s[:j]
                ans = max(ans, a + t + b)
                ans = max(ans, b[::-1] + t + a[::-1])
        return ans
```

### **Java**

```java
class Solution {
    public String splitLoopedString(String[] strs) {
        int n = strs.length;
        for (int i = 0; i < n; ++i) {
            String s = strs[i];
            String t = new StringBuilder(s).reverse().toString();
            if (s.compareTo(t) < 0) {
                strs[i] = t;
            }
        }
        String ans = "";
        for (int i = 0; i < n; ++i) {
            String s = strs[i];
            StringBuilder sb = new StringBuilder();
            for (int j = i + 1; j < n; ++j) {
                sb.append(strs[j]);
            }
            for (int j = 0; j < i; ++j) {
                sb.append(strs[j]);
            }
            String t = sb.toString();
            for (int j = 0; j < s.length(); ++j) {
                String a = s.substring(j);
                String b = s.substring(0, j);
                String cur = a + t + b;
                if (ans.compareTo(cur) < 0) {
                    ans = cur;
                }
                cur = new StringBuilder(b)
                          .reverse()
                          .append(t)
                          .append(new StringBuilder(a).reverse().toString())
                          .toString();
                if (ans.compareTo(cur) < 0) {
                    ans = cur;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string splitLoopedString(vector<string>& strs) {
        for (auto& s : strs) {
            string t{s.rbegin(), s.rend()};
            s = max(s, t);
        }
        int n = strs.size();
        string ans = "";
        for (int i = 0; i < strs.size(); ++i) {
            auto& s = strs[i];
            string t;
            for (int j = i + 1; j < n; ++j) {
                t += strs[j];
            }
            for (int j = 0; j < i; ++j) {
                t += strs[j];
            }
            for (int j = 0; j < s.size(); ++j) {
                auto a = s.substr(j);
                auto b = s.substr(0, j);
                auto cur = a + t + b;
                if (ans < cur) {
                    ans = cur;
                }
                reverse(a.begin(), a.end());
                reverse(b.begin(), b.end());
                cur = b + t + a;
                if (ans < cur) {
                    ans = cur;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func splitLoopedString(strs []string) (ans string) {
	for i, s := range strs {
		t := reverse(s)
		if s < t {
			strs[i] = t
		}
	}
	for i, s := range strs {
		sb := &strings.Builder{}
		for _, w := range strs[i+1:] {
			sb.WriteString(w)
		}
		for _, w := range strs[:i] {
			sb.WriteString(w)
		}
		t := sb.String()
		for j := 0; j < len(s); j++ {
			a, b := s[j:], s[0:j]
			cur := a + t + b
			if ans < cur {
				ans = cur
			}
			cur = reverse(b) + t + reverse(a)
			if ans < cur {
				ans = cur
			}
		}
	}
	return ans
}

func reverse(s string) string {
	t := []byte(s)
	for i, j := 0, len(t)-1; i < j; i, j = i+1, j-1 {
		t[i], t[j] = t[j], t[i]
	}
	return string(t)
}
```

### **...**

```

```

<!-- tabs:end -->
