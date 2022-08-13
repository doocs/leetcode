# [93. Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses)

[中文文档](/solution/0000-0099/0093.Restore%20IP%20Addresses/README.md)

## Description

<p>A <strong>valid IP address</strong> consists of exactly four integers separated by single dots. Each integer is between <code>0</code> and <code>255</code> (<strong>inclusive</strong>) and cannot have leading zeros.</p>

<ul>
	<li>For example, <code>&quot;0.1.2.201&quot;</code> and <code>&quot;192.168.1.1&quot;</code> are <strong>valid</strong> IP addresses, but <code>&quot;0.011.255.245&quot;</code>, <code>&quot;192.168.1.312&quot;</code> and <code>&quot;192.168@1.1&quot;</code> are <strong>invalid</strong> IP addresses.</li>
</ul>

<p>Given a string <code>s</code> containing only digits, return <em>all possible valid IP addresses that can be formed by inserting dots into </em><code>s</code>. You are <strong>not</strong> allowed to reorder or remove any digits in <code>s</code>. You may return the valid IP addresses in <strong>any</strong> order.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;25525511135&quot;
<strong>Output:</strong> [&quot;255.255.11.135&quot;,&quot;255.255.111.35&quot;]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0000&quot;
<strong>Output:</strong> [&quot;0.0.0.0&quot;]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;101023&quot;
<strong>Output:</strong> [&quot;1.0.10.23&quot;,&quot;1.0.102.3&quot;,&quot;10.1.0.23&quot;,&quot;10.10.2.3&quot;,&quot;101.0.2.3&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 20</code></li>
	<li><code>s</code> consists of digits only.</li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def restoreIpAddresses(self, s: str) -> List[str]:
        def check(s):
            if not (0 <= int(s) <= 255):
                return False
            if s[0] == '0' and len(s) > 1:
                return False
            return True

        def dfs(s, t):
            if len(t) == 4:
                if not s:
                    ans.append('.'.join(t))
                return
            for i in range(1, min(4, len(s) + 1)):
                if check(s[:i]):
                    t.append(s[:i])
                    dfs(s[i:], t)
                    t.pop()

        ans = []
        dfs(s, [])
        return ans
```

### **Java**

```java
class Solution {
    private List<String> ans;

    public List<String> restoreIpAddresses(String s) {
        ans = new ArrayList<>();
        dfs(s, new ArrayList<>());
        return ans;
    }

    private void dfs(String s, List<String> t) {
        if (t.size() == 4) {
            if ("".equals(s)) {
                ans.add(String.join(".", t));
            }
            return;
        }
        for (int i = 1; i < Math.min(4, s.length() + 1); ++i) {
            String c = s.substring(0, i);
            if (check(c)) {
                t.add(c);
                dfs(s.substring(i), t);
                t.remove(t.size() - 1);
            }
        }
    }

    private boolean check(String s) {
        if ("".equals(s)) {
            return false;
        }
        int num = Integer.parseInt(s);
        if (num > 255) {
            return false;
        }
        if (s.charAt(0) == '0' && s.length() > 1) {
            return false;
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> restoreIpAddresses(string s) {
        vector<string> ans;
        vector<string> t;
        dfs(s, t, ans);
        return ans;
    }

    void dfs(string s, vector<string>& t, vector<string>& ans) {
        if (t.size() == 4) {
            if (s == "") {
                string p = "";
                for (auto e : t) p += e + ".";
                p.pop_back();
                ans.push_back(p);
            }
            return;
        }
        for (int i = 1; i < min(4, (int)s.size() + 1); ++i) {
            string c = s.substr(0, i);
            if (check(c)) {
                t.push_back(c);
                dfs(s.substr(i, s.size() - i), t, ans);
                t.pop_back();
            }
        }
    }

    bool check(string s) {
        if (s == "") return false;
        int num = stoi(s);
        if (num > 255) return false;
        if (s[0] == '0' && s.size() > 1) return false;
        return true;
    }
};
```

### **Go**

```go
func restoreIpAddresses(s string) []string {
	check := func(s string) bool {
		if i, _ := strconv.Atoi(s); i > 255 {
			return false
		}
		if s[0] == '0' && len(s) > 1 {
			return false
		}
		return true
	}
	var ans []string
	var dfs func(s string, t []string)
	dfs = func(s string, t []string) {
		if len(t) == 4 {
			if s == "" {
				ans = append(ans, strings.Join(t, "."))
			}
			return
		}
		for i := 1; i < 4 && i < len(s)+1; i++ {
			if check(s[0:i]) {
				t = append(t, s[0:i])
				dfs(s[i:], t)
				t = t[0 : len(t)-1]
			}
		}
	}
	var t []string
	dfs(s, t)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
