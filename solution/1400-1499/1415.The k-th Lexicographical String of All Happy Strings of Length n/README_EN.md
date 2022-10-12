# [1415. The k-th Lexicographical String of All Happy Strings of Length n](https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n)

[中文文档](/solution/1400-1499/1415.The%20k-th%20Lexicographical%20String%20of%20All%20Happy%20Strings%20of%20Length%20n/README.md)

## Description

<p>A <strong>happy string</strong> is a string that:</p>

<ul>
	<li>consists only of letters of the set <code>[&#39;a&#39;, &#39;b&#39;, &#39;c&#39;]</code>.</li>
	<li><code>s[i] != s[i + 1]</code> for all values of <code>i</code> from <code>1</code> to <code>s.length - 1</code> (string is 1-indexed).</li>
</ul>

<p>For example, strings <strong>&quot;abc&quot;, &quot;ac&quot;, &quot;b&quot;</strong> and <strong>&quot;abcbabcbcb&quot;</strong> are all happy strings and strings <strong>&quot;aa&quot;, &quot;baa&quot;</strong> and <strong>&quot;ababbc&quot;</strong> are not happy strings.</p>

<p>Given two integers <code>n</code> and <code>k</code>, consider a list of all happy strings of length <code>n</code> sorted in lexicographical order.</p>

<p>Return <em>the kth string</em> of this list or return an <strong>empty string</strong> if there are less than <code>k</code> happy strings of length <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1, k = 3
<strong>Output:</strong> &quot;c&quot;
<strong>Explanation:</strong> The list [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;] contains all happy strings of length 1. The third string is &quot;c&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1, k = 4
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> There are only 3 happy strings of length 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 9
<strong>Output:</strong> &quot;cab&quot;
<strong>Explanation:</strong> There are 12 different happy string of length 3 [&quot;aba&quot;, &quot;abc&quot;, &quot;aca&quot;, &quot;acb&quot;, &quot;bab&quot;, &quot;bac&quot;, &quot;bca&quot;, &quot;bcb&quot;, &quot;cab&quot;, &quot;cac&quot;, &quot;cba&quot;, &quot;cbc&quot;]. You will find the 9<sup>th</sup> string = &quot;cab&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getHappyString(self, n: int, k: int) -> str:
        def dfs(t):
            if len(t) == n:
                ans.append(t)
                return
            for c in 'abc':
                if t and t[-1] == c:
                    continue
                dfs(t + c)

        ans = []
        dfs('')
        return '' if len(ans) < k else ans[k - 1]
```

### **Java**

```java
class Solution {
    private List<String> ans = new ArrayList<>();

    public String getHappyString(int n, int k) {
        dfs("", n);
        return ans.size() < k ? "" : ans.get(k - 1);
    }

    private void dfs(String t, int n) {
        if (t.length() == n) {
            ans.add(t);
            return;
        }
        for (char c : "abc".toCharArray()) {
            if (t.length() > 0 && t.charAt(t.length() - 1) == c) {
                continue;
            }
            dfs(t + c, n);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> ans;
    string getHappyString(int n, int k) {
        dfs("", n);
        return ans.size() < k ? "" : ans[k - 1];
    }

    void dfs(string t, int n) {
        if (t.size() == n) {
            ans.push_back(t);
            return;
        }
        for (int c = 'a'; c <= 'c'; ++c) {
            if (t.size() && t.back() == c) continue;
            t.push_back(c);
            dfs(t, n);
            t.pop_back();
        }
    }
};
```

### **...**

```

```

<!-- tabs:end -->
