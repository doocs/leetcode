# [剑指 Offer II 109. 开密码锁](https://leetcode.cn/problems/zlDJc7)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个密码锁由 4&nbsp;个环形拨轮组成，每个拨轮都有 10 个数字： <code>&#39;0&#39;, &#39;1&#39;, &#39;2&#39;, &#39;3&#39;, &#39;4&#39;, &#39;5&#39;, &#39;6&#39;, &#39;7&#39;, &#39;8&#39;, &#39;9&#39;</code> 。每个拨轮可以自由旋转：例如把 <code>&#39;9&#39;</code> 变为&nbsp;<code>&#39;0&#39;</code>，<code>&#39;0&#39;</code> 变为 <code>&#39;9&#39;</code> 。每次旋转都只能旋转一个拨轮的一位数字。</p>

<p>锁的初始数字为 <code>&#39;0000&#39;</code> ，一个代表四个拨轮的数字的字符串。</p>

<p>列表 <code>deadends</code> 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。</p>

<p>字符串 <code>target</code> 代表可以解锁的数字，请给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>deadends = [&quot;0201&quot;,&quot;0101&quot;,&quot;0102&quot;,&quot;1212&quot;,&quot;2002&quot;], target = &quot;0202&quot;
<strong>输出：</strong>6
<strong>解释：</strong>
可能的移动序列为 &quot;0000&quot; -&gt; &quot;1000&quot; -&gt; &quot;1100&quot; -&gt; &quot;1200&quot; -&gt; &quot;1201&quot; -&gt; &quot;1202&quot; -&gt; &quot;0202&quot;。
注意 &quot;0000&quot; -&gt; &quot;0001&quot; -&gt; &quot;0002&quot; -&gt; &quot;0102&quot; -&gt; &quot;0202&quot; 这样的序列是不能解锁的，因为当拨动到 &quot;0102&quot; 时这个锁就会被锁定。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> deadends = [&quot;8888&quot;], target = &quot;0009&quot;
<strong>输出：</strong>1
<strong>解释：</strong>
把最后一位反向旋转一次即可 &quot;0000&quot; -&gt; &quot;0009&quot;。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> deadends = [&quot;8887&quot;,&quot;8889&quot;,&quot;8878&quot;,&quot;8898&quot;,&quot;8788&quot;,&quot;8988&quot;,&quot;7888&quot;,&quot;9888&quot;], target = &quot;8888&quot;
<strong>输出：</strong>-1
<strong>解释：
</strong>无法旋转到目标数字且不被锁定。
</pre>

<p><strong>示例 4:</strong></p>

<pre>
<strong>输入:</strong> deadends = [&quot;0000&quot;], target = &quot;8888&quot;
<strong>输出：</strong>-1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;deadends.length &lt;= 500</code></li>
	<li><code><font face="monospace">deadends[i].length == 4</font></code></li>
	<li><code><font face="monospace">target.length == 4</font></code></li>
	<li><code>target</code> <strong>不在</strong> <code>deadends</code> 之中</li>
	<li><code>target</code> 和 <code>deadends[i]</code> 仅由若干位数字组成</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 752&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/open-the-lock/">https://leetcode.cn/problems/open-the-lock/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

    private char prev(char c) {
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
        while (!q.empty()) {
            ++step;
            for (int i = 0, n = q.size(); i < n; ++i) {
                string status = q.front();
                q.pop();
                for (auto t : get(status)) {
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
        return c == '0' ? '9' : (char)(c - 1);
    }

    char next(char c) {
        return c == '9' ? '0' : (char)(c + 1);
    }

    vector<string> get(string& t) {
        vector<string> res;
        for (int i = 0; i < 4; ++i) {
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
