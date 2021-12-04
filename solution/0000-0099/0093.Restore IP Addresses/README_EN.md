# [93. Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses)

[中文文档](/solution/0000-0099/0093.Restore%20IP%20Addresses/README.md)

## Description

<p>Given a string <code>s</code> containing only digits, return all possible valid IP addresses that can be obtained from <code>s</code>. You can return them in <strong>any</strong> order.</p>

<p>A <strong>valid IP address</strong> consists of exactly four integers, each integer is between <code>0</code> and <code>255</code>, separated by single dots and cannot have leading zeros. For example, &quot;0.1.2.201&quot; and &quot;192.168.1.1&quot; are <strong>valid</strong> IP addresses and &quot;0.011.255.245&quot;, &quot;192.168.1.312&quot; and &quot;192.168@1.1&quot; are <strong>invalid</strong> IP addresses.&nbsp;</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = "25525511135"
<strong>Output:</strong> ["255.255.11.135","255.255.111.35"]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> s = "0000"
<strong>Output:</strong> ["0.0.0.0"]
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> s = "1111"
<strong>Output:</strong> ["1.1.1.1"]
</pre><p><strong>Example 4:</strong></p>
<pre><strong>Input:</strong> s = "010010"
<strong>Output:</strong> ["0.10.0.10","0.100.1.0"]
</pre><p><strong>Example 5:</strong></p>
<pre><strong>Input:</strong> s = "101023"
<strong>Output:</strong> ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 3000</code></li>
	<li><code>s</code> consists of digits only.</li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def restoreIpAddresses(self, s: str) -> List[str]:
        ans = []

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
        if (t.size() == 4)
        {
            if (s == "")
            {
                string p = "";
                for (auto e : t) p += e + ".";
                p.pop_back();
                ans.push_back(p);
            }
            return;
        }
        for (int i = 1; i < min(4, (int) s.size() + 1); ++i)
        {
            string c = s.substr(0, i);
            if (check(c))
            {
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

### **...**

```

```

<!-- tabs:end -->
