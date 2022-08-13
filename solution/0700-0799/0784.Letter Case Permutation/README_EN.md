# [784. Letter Case Permutation](https://leetcode.com/problems/letter-case-permutation)

[中文文档](/solution/0700-0799/0784.Letter%20Case%20Permutation/README.md)

## Description

<p>Given a string <code>s</code>, you&nbsp;can transform every letter individually to be lowercase or uppercase to create another string.</p>

<p>Return <em>a list of all possible strings we could create</em>. Return the output in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a1b2&quot;
<strong>Output:</strong> [&quot;a1b2&quot;,&quot;a1B2&quot;,&quot;A1b2&quot;,&quot;A1B2&quot;]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;3z4&quot;
<strong>Output:</strong> [&quot;3z4&quot;,&quot;3Z4&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 12</code></li>
	<li><code>s</code> consists of lowercase English letters, uppercase English letters, and digits.</li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def letterCasePermutation(self, s: str) -> List[str]:
        def dfs(i, t):
            if i == len(t):
                ans.append(''.join(t))
                return
            dfs(i + 1, t)
            if t[i].isalpha():
                t[i] = t[i].upper() if t[i].islower() else t[i].lower()
                dfs(i + 1, t)

        ans = []
        t = list(s)
        dfs(0, t)
        return ans
```

```python
class Solution:
    def letterCasePermutation(self, s: str) -> List[str]:
        def dfs(i, t):
            if i == len(s):
                ans.append(t)
                return
            if s[i].isalpha():
                dfs(i + 1, t + s[i].upper())
                dfs(i + 1, t + s[i].lower())
            else:
                dfs(i + 1, t + s[i])

        ans = []
        dfs(0, '')
        return ans
```

### **Java**

```java
class Solution {
    public List<String> letterCasePermutation(String S) {
        char[] cs = S.toCharArray();
        List<String> res = new ArrayList<>();
        dfs(cs, 0, res);
        return res;
    }

    private void dfs(char[] cs, int i, List<String> res) {
        if (i == cs.length) {
            res.add(String.valueOf(cs));
            return;
        }
        dfs(cs, i + 1, res);
        if (cs[i] >= 'A') {
            cs[i] ^= 32;
            dfs(cs, i + 1, res);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> ans;
    string s;

    vector<string> letterCasePermutation(string s) {
        this->s = s;
        string t = "";
        dfs(0, t);
        return ans;
    }

    void dfs(int i, string t) {
        if (i == s.size()) {
            ans.push_back(t);
            return;
        }
        if (isalpha(s[i])) {
            char c1 = toupper(s[i]);
            char c2 = tolower(s[i]);
            dfs(i + 1, t + c1);
            dfs(i + 1, t + c2);
        } else {
            dfs(i + 1, t + s[i]);
        }
    }
};
```

### **...**

```

```

<!-- tabs:end -->
