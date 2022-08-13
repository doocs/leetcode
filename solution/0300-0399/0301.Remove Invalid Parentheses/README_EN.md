# [301. Remove Invalid Parentheses](https://leetcode.com/problems/remove-invalid-parentheses)

[中文文档](/solution/0300-0399/0301.Remove%20Invalid%20Parentheses/README.md)

## Description

<p>Given a string <code>s</code> that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.</p>

<p>Return <em>all the possible results</em>. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;()())()&quot;
<strong>Output:</strong> [&quot;(())()&quot;,&quot;()()()&quot;]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;(a)())()&quot;
<strong>Output:</strong> [&quot;(a())()&quot;,&quot;(a)()()&quot;]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;)(&quot;
<strong>Output:</strong> [&quot;&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 25</code></li>
	<li><code>s</code> consists of lowercase English letters and parentheses <code>&#39;(&#39;</code> and <code>&#39;)&#39;</code>.</li>
	<li>There will be at most <code>20</code> parentheses in <code>s</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def removeInvalidParentheses(self, s: str) -> List[str]:
        def dfs(i, t, lcnt, rcnt, ldel, rdel):
            nonlocal tdel, ans
            if ldel * rdel < 0 or lcnt < rcnt or ldel + rdel > len(s) - i:
                return
            if ldel == 0 and rdel == 0:
                if len(s) - len(t) == tdel:
                    ans.add(t)
            if i == len(s):
                return
            if s[i] == '(':
                dfs(i + 1, t, lcnt, rcnt, ldel - 1, rdel)
                dfs(i + 1, t + '(', lcnt + 1, rcnt, ldel, rdel)
            elif s[i] == ')':
                dfs(i + 1, t, lcnt, rcnt, ldel, rdel - 1)
                dfs(i + 1, t + ')', lcnt, rcnt + 1, ldel, rdel)
            else:
                dfs(i + 1, t + s[i], lcnt, rcnt, ldel, rdel)

        ldel = rdel = 0
        for c in s:
            if c == '(':
                ldel += 1
            elif c == ')':
                if ldel == 0:
                    rdel += 1
                else:
                    ldel -= 1
        tdel = ldel + rdel
        ans = set()
        dfs(0, '', 0, 0, ldel, rdel)
        return list(ans)
```

### **Java**

```java
class Solution {
    private int tdel;
    private String s;
    private Set<String> ans;

    public List<String> removeInvalidParentheses(String s) {
        int ldel = 0, rdel = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                ++ldel;
            } else if (c == ')') {
                if (ldel == 0) {
                    ++rdel;
                } else {
                    --ldel;
                }
            }
        }
        tdel = ldel + rdel;
        this.s = s;
        ans = new HashSet<>();
        dfs(0, "", 0, 0, ldel, rdel);
        return new ArrayList<>(ans);
    }

    private void dfs(int i, String t, int lcnt, int rcnt, int ldel, int rdel) {
        if (ldel * rdel < 0 || lcnt < rcnt || ldel + rdel > s.length() - i) {
            return;
        }
        if (ldel == 0 && rdel == 0) {
            if (s.length() - t.length() == tdel) {
                ans.add(t);
            }
        }
        if (i == s.length()) {
            return;
        }
        char c = s.charAt(i);
        if (c == '(') {
            dfs(i + 1, t, lcnt, rcnt, ldel - 1, rdel);
            dfs(i + 1, t + String.valueOf(c), lcnt + 1, rcnt, ldel, rdel);
        } else if (c == ')') {
            dfs(i + 1, t, lcnt, rcnt, ldel, rdel - 1);
            dfs(i + 1, t + String.valueOf(c), lcnt, rcnt + 1, ldel, rdel);
        } else {
            dfs(i + 1, t + String.valueOf(c), lcnt, rcnt, ldel, rdel);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> removeInvalidParentheses(string s) {
        int ldel = 0, rdel = 0;
        for (char c : s) {
            if (c == '(')
                ++ldel;
            else if (c == ')') {
                if (ldel == 0)
                    ++rdel;
                else
                    --ldel;
            }
        }
        int tdel = ldel + rdel;
        unordered_set<string> ans;
        dfs(0, "", s, 0, 0, ldel, rdel, tdel, ans);
        vector<string> res;
        res.assign(ans.begin(), ans.end());
        return res;
    }

    void dfs(int i, string t, string s, int lcnt, int rcnt, int ldel, int rdel, int tdel, unordered_set<string>& ans) {
        if (ldel * rdel < 0 || lcnt < rcnt || ldel + rdel > s.size() - i) return;
        if (ldel == 0 && rdel == 0) {
            if (s.size() - t.size() == tdel) ans.insert(t);
        }
        if (i == s.size()) return;
        if (s[i] == '(') {
            dfs(i + 1, t, s, lcnt, rcnt, ldel - 1, rdel, tdel, ans);
            dfs(i + 1, t + s[i], s, lcnt + 1, rcnt, ldel, rdel, tdel, ans);
        } else if (s[i] == ')') {
            dfs(i + 1, t, s, lcnt, rcnt, ldel, rdel - 1, tdel, ans);
            dfs(i + 1, t + s[i], s, lcnt, rcnt + 1, ldel, rdel, tdel, ans);
        } else {
            dfs(i + 1, t + s[i], s, lcnt, rcnt, ldel, rdel, tdel, ans);
        }
    }
};
```

### **...**

```

```

<!-- tabs:end -->
