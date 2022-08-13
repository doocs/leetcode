# [269. Alien Dictionary](https://leetcode.com/problems/alien-dictionary)

[中文文档](/solution/0200-0299/0269.Alien%20Dictionary/README.md)

## Description

<p>There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.</p>

<p>You are given a list of strings <code>words</code> from the alien language&#39;s dictionary, where the strings in <code>words</code> are <strong>sorted lexicographically</strong> by the rules of this new language.</p>

<p>Return <em>a string of the unique letters in the new alien language sorted in <strong>lexicographically increasing order</strong> by the new language&#39;s rules. If there is no solution, return </em><code>&quot;&quot;</code><em>. If there are multiple solutions, return <strong>any of them</strong></em>.</p>

<p>A string <code>s</code> is <strong>lexicographically smaller</strong> than a string <code>t</code> if at the first letter where they differ, the letter in <code>s</code> comes before the letter in <code>t</code> in the alien language. If the first <code>min(s.length, t.length)</code> letters are the same, then <code>s</code> is smaller if and only if <code>s.length &lt; t.length</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;wrt&quot;,&quot;wrf&quot;,&quot;er&quot;,&quot;ett&quot;,&quot;rftt&quot;]
<strong>Output:</strong> &quot;wertf&quot;
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;z&quot;,&quot;x&quot;]
<strong>Output:</strong> &quot;zx&quot;
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;z&quot;,&quot;x&quot;,&quot;z&quot;]
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> The order is invalid, so return <code>&quot;&quot;</code>.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> consists of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def alienOrder(self, words: List[str]) -> str:
        g = [[False] * 26 for _ in range(26)]
        s = [False] * 26
        cnt = 0
        n = len(words)
        for i in range(n - 1):
            for c in words[i]:
                if cnt == 26:
                    break
                o = ord(c) - ord('a')
                if not s[o]:
                    cnt += 1
                    s[o] = True
            m = len(words[i])
            for j in range(m):
                if j >= len(words[i + 1]):
                    return ''
                c1, c2 = words[i][j], words[i + 1][j]
                if c1 == c2:
                    continue
                o1, o2 = ord(c1) - ord('a'), ord(c2) - ord('a')
                if g[o2][o1]:
                    return ''
                g[o1][o2] = True
                break
        for c in words[n - 1]:
            if cnt == 26:
                break
            o = ord(c) - ord('a')
            if not s[o]:
                cnt += 1
                s[o] = True

        indegree = [0] * 26
        for i in range(26):
            for j in range(26):
                if i != j and s[i] and s[j] and g[i][j]:
                    indegree[j] += 1
        q = deque()
        ans = []
        for i in range(26):
            if s[i] and indegree[i] == 0:
                q.append(i)
        while q:
            t = q.popleft()
            ans.append(chr(t + ord('a')))
            for i in range(26):
                if s[i] and i != t and g[t][i]:
                    indegree[i] -= 1
                    if indegree[i] == 0:
                        q.append(i)
        return '' if len(ans) < cnt else ''.join(ans)
```

### **Java**

```java
class Solution {

    public String alienOrder(String[] words) {
        boolean[][] g = new boolean[26][26];
        boolean[] s = new boolean[26];
        int cnt = 0;
        int n = words.length;
        for (int i = 0; i < n - 1; ++i) {
            for (char c : words[i].toCharArray()) {
                if (cnt == 26) {
                    break;
                }
                c -= 'a';
                if (!s[c]) {
                    ++cnt;
                    s[c] = true;
                }
            }
            int m = words[i].length();
            for (int j = 0; j < m; ++j) {
                if (j >= words[i + 1].length()) {
                    return "";
                }
                char c1 = words[i].charAt(j), c2 = words[i + 1].charAt(j);
                if (c1 == c2) {
                    continue;
                }
                if (g[c2 - 'a'][c1 - 'a']) {
                    return "";
                }
                g[c1 - 'a'][c2 - 'a'] = true;
                break;
            }
        }
        for (char c : words[n - 1].toCharArray()) {
            if (cnt == 26) {
                break;
            }
            c -= 'a';
            if (!s[c]) {
                ++cnt;
                s[c] = true;
            }
        }

        int[] indegree = new int[26];
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < 26; ++j) {
                if (i != j && s[i] && s[j] && g[i][j]) {
                    ++indegree[j];
                }
            }
        }
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < 26; ++i) {
            if (s[i] && indegree[i] == 0) {
                q.offerLast(i);
            }
        }
        StringBuilder ans = new StringBuilder();
        while (!q.isEmpty()) {
            int t = q.pollFirst();
            ans.append((char) (t + 'a'));
            for (int i = 0; i < 26; ++i) {
                if (i != t && s[i] && g[t][i]) {
                    if (--indegree[i] == 0) {
                        q.offerLast(i);
                    }
                }
            }
        }
        return ans.length() < cnt ? "" : ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string alienOrder(vector<string>& words) {
        vector<vector<bool>> g(26, vector<bool>(26));
        vector<bool> s(26);
        int cnt = 0;
        int n = words.size();
        for (int i = 0; i < n - 1; ++i) {
            for (char c : words[i]) {
                if (cnt == 26) break;
                c -= 'a';
                if (!s[c]) {
                    ++cnt;
                    s[c] = true;
                }
            }
            int m = words[i].size();
            for (int j = 0; j < m; ++j) {
                if (j >= words[i + 1].size()) return "";
                char c1 = words[i][j], c2 = words[i + 1][j];
                if (c1 == c2) continue;
                if (g[c2 - 'a'][c1 - 'a']) return "";
                g[c1 - 'a'][c2 - 'a'] = true;
                break;
            }
        }
        for (char c : words[n - 1]) {
            if (cnt == 26) break;
            c -= 'a';
            if (!s[c]) {
                ++cnt;
                s[c] = true;
            }
        }
        vector<int> indegree(26);
        for (int i = 0; i < 26; ++i)
            for (int j = 0; j < 26; ++j)
                if (i != j && s[i] && s[j] && g[i][j])
                    ++indegree[j];
        queue<int> q;
        for (int i = 0; i < 26; ++i)
            if (s[i] && indegree[i] == 0)
                q.push(i);
        string ans = "";
        while (!q.empty()) {
            int t = q.front();
            ans += (t + 'a');
            q.pop();
            for (int i = 0; i < 26; ++i)
                if (i != t && s[i] && g[t][i])
                    if (--indegree[i] == 0)
                        q.push(i);
        }
        return ans.size() < cnt ? "" : ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
