# [752. Open the Lock](https://leetcode.com/problems/open-the-lock)

[中文文档](/solution/0700-0799/0752.Open%20the%20Lock/README.md)

## Description

<p>You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: <code>&#39;0&#39;, &#39;1&#39;, &#39;2&#39;, &#39;3&#39;, &#39;4&#39;, &#39;5&#39;, &#39;6&#39;, &#39;7&#39;, &#39;8&#39;, &#39;9&#39;</code>. The wheels can rotate freely and wrap around: for example we can turn <code>&#39;9&#39;</code> to be <code>&#39;0&#39;</code>, or <code>&#39;0&#39;</code> to be <code>&#39;9&#39;</code>. Each move consists of turning one wheel one slot.</p>

<p>The lock initially starts at <code>&#39;0000&#39;</code>, a string representing the state of the 4 wheels.</p>

<p>You are given a list of <code>deadends</code> dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.</p>

<p>Given a <code>target</code> representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> deadends = [&quot;0201&quot;,&quot;0101&quot;,&quot;0102&quot;,&quot;1212&quot;,&quot;2002&quot;], target = &quot;0202&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong>
A sequence of valid moves would be &quot;0000&quot; -&gt; &quot;1000&quot; -&gt; &quot;1100&quot; -&gt; &quot;1200&quot; -&gt; &quot;1201&quot; -&gt; &quot;1202&quot; -&gt; &quot;0202&quot;.
Note that a sequence like &quot;0000&quot; -&gt; &quot;0001&quot; -&gt; &quot;0002&quot; -&gt; &quot;0102&quot; -&gt; &quot;0202&quot; would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end &quot;0102&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> deadends = [&quot;8888&quot;], target = &quot;0009&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong>
We can turn the last wheel in reverse to move from &quot;0000&quot; -&gt; &quot;0009&quot;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> deadends = [&quot;8887&quot;,&quot;8889&quot;,&quot;8878&quot;,&quot;8898&quot;,&quot;8788&quot;,&quot;8988&quot;,&quot;7888&quot;,&quot;9888&quot;], target = &quot;8888&quot;
<strong>Output:</strong> -1
Explanation:
We can&#39;t reach the target without getting stuck.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> deadends = [&quot;0000&quot;], target = &quot;8888&quot;
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;deadends.length &lt;= 500</code></li>
	<li><code><font face="monospace">deadends[i].length == 4</font></code></li>
	<li><code><font face="monospace">target.length == 4</font></code></li>
	<li>target <strong>will not be</strong> in the list <code>deadends</code>.</li>
	<li><code>target</code> and <code>deadends[i]</code> consist of digits only.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def openLock(self, deadends: List[str], target: str) -> int:
        s = set(deadends)
        if target in s or '0000' in s:
            return -1
        if target == '0000':
            return 0

        def prev(c):
            return '9' if c == '0' else str(int(c) - 1)

        def next(c):
            return '0' if c == '9' else str(int(c) + 1)

        def get(t):
            res = []
            t = list(t)
            for i in range(4):
                c = t[i]
                t[i] = prev(c)
                res.append(''.join(t))
                t[i] = next(c)
                res.append(''.join(t))
                t[i] = c
            return res

        visited = set()
        q = deque([('0000', 0)])
        while q:
            status, step = q.popleft()
            for t in get(status):
                if t in visited or t in s:
                    continue
                if t == target:
                    return step + 1
                q.append((t, step + 1))
                visited.add(t)
        return -1
```

### **Java**

```java
class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> s = new HashSet<>(Arrays.asList(deadends));
        if (s.contains(target) || s.contains("0000")) {
            return -1;
        }
        if (Objects.equals(target, "0000")) {
            return 0;
        }
        Set<String> visited = new HashSet<>();
        Deque<String> q = new ArrayDeque<>();
        q.offerLast("0000");
        int step = 0;
        while (!q.isEmpty()) {
            ++step;
            for (int i = 0, n = q.size(); i < n; ++i) {
                String status = q.pollFirst();
                for (String t : get(status)) {
                    if (visited.contains(t) || s.contains(t)) {
                        continue;
                    }
                    if (Objects.equals(t, target)) {
                        return step;
                    }
                    q.offerLast(t);
                    visited.add(t);
                }
            }
        }
        return -1;
    }

    private char prev(char c)  {
        return c == '0' ? '9' : (char) (c - 1);
    }

    private char next(char c) {
        return c == '9' ? '0' : (char) (c + 1);
    }

    private List<String> get(String t) {
        List res = new ArrayList<>();
        char[] chars = t.toCharArray();
        for (int i = 0; i < 4; ++i) {
            char c = chars[i];
            chars[i] = prev(c);
            res.add(String.valueOf(chars));
            chars[i] = next(c);
            res.add(String.valueOf(chars));
            chars[i] = c;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int openLock(vector<string>& deadends, string target) {
        unordered_set<string> s(deadends.begin(), deadends.end());
        if (s.count(target) || s.count("0000")) return -1;
        if (target == "0000") return 0;
        unordered_set<string> visited;
        queue<string> q;
        q.push("0000");
        int step = 0;
        while (!q.empty())
        {
            ++step;
            for (int i = 0, n = q.size(); i < n; ++i)
            {
                string status = q.front();
                q.pop();
                for (auto t : get(status))
                {
                    if (visited.count(t) || s.count(t)) continue;
                    if (t == target) return step;
                    q.push(t);
                    visited.insert(t);
                }
            }
        }
        return -1;
    }

    char prev(char c) {
        return c == '0' ? '9' : (char) (c - 1);
    }

    char next(char c) {
        return c == '9' ? '0' : (char) (c + 1);
    }

    vector<string> get(string& t) {
        vector<string> res;
        for (int i = 0; i < 4; ++i)
        {
            char c = t[i];
            t[i] = prev(c);
            res.push_back(t);
            t[i] = next(c);
            res.push_back(t);
            t[i] = c;
        }
        return res;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
