# [752. 打开转盘锁](https://leetcode-cn.com/problems/open-the-lock)

[English Version](/solution/0700-0799/0752.Open%20the%20Lock/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： <code>&#39;0&#39;, &#39;1&#39;, &#39;2&#39;, &#39;3&#39;, &#39;4&#39;, &#39;5&#39;, &#39;6&#39;, &#39;7&#39;, &#39;8&#39;, &#39;9&#39;</code> 。每个拨轮可以自由旋转：例如把 <code>&#39;9&#39;</code> 变为&nbsp; <code>&#39;0&#39;</code><font color="#333333" face="Helvetica Neue, Helvetica, Arial, sans-serif"><span style="background-color:#ffffff; font-size:14px">，</span></font><code>&#39;0&#39;</code> 变为 <code>&#39;9&#39;</code> 。每次旋转都只能旋转一个拨轮的一位数字。</p>

<p>锁的初始数字为 <code>&#39;0000&#39;</code> ，一个代表四个拨轮的数字的字符串。</p>

<p>列表 <code>deadends</code> 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。</p>

<p>字符串 <code>target</code> 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>deadends = [&quot;0201&quot;,&quot;0101&quot;,&quot;0102&quot;,&quot;1212&quot;,&quot;2002&quot;], target = &quot;0202&quot;
<strong>输出：</strong>6
<strong>解释：</strong>
可能的移动序列为 &quot;0000&quot; -&gt; &quot;1000&quot; -&gt; &quot;1100&quot; -&gt; &quot;1200&quot; -&gt; &quot;1201&quot; -&gt; &quot;1202&quot; -&gt; &quot;0202&quot;。
注意 &quot;0000&quot; -&gt; &quot;0001&quot; -&gt; &quot;0002&quot; -&gt; &quot;0102&quot; -&gt; &quot;0202&quot; 这样的序列是不能解锁的，
因为当拨动到 &quot;0102&quot; 时这个锁就会被锁定。
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

<ol>
	<li>死亡列表 <code>deadends</code> 的长度范围为 <code>[1, 500]</code>。</li>
	<li>目标数字 <code>target</code> 不会在 <code>deadends</code> 之中。</li>
	<li>每个 <code>deadends</code> 和 <code>target</code> 中的字符串的数字会在 10,000 个可能的情况 <code>&#39;0000&#39;</code> 到 <code>&#39;9999&#39;</code> 中产生。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

BFS 最小步数模型。本题可以用朴素 BFS，也可以用双向 BFS 优化搜索空间，从而提升效率。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

朴素 BFS：

```python
class Solution:
    def openLock(self, deadends: List[str], target: str) -> int:
        def next(s):
            res = []
            s = list(s)
            for i in range(4):
                c = s[i]
                s[i] = '9' if c == '0' else str(int(c) - 1)
                res.append(''.join(s))
                s[i] = '0' if c == '9' else str(int(c) + 1)
                res.append(''.join(s))
                s[i] = c
            return res

        if target == '0000':
            return 0
        s = set(deadends)
        if '0000' in s:
            return -1
        q = deque([('0000')])
        s.add('0000')
        ans = 0
        while q:
            ans += 1
            for _ in range(len(q), 0, -1):
                p = q.popleft()
                for t in next(p):
                    if t == target:
                        return ans
                    if t not in s:
                        q.append(t)
                        s.add(t)
        return -1
```

双向 BFS 优化搜索：

```python
class Solution:
    def openLock(self, deadends: List[str], target: str) -> int:
        def next(s):
            res = []
            s = list(s)
            for i in range(4):
                c = s[i]
                s[i] = '9' if c == '0' else str(int(c) - 1)
                res.append(''.join(s))
                s[i] = '0' if c == '9' else str(int(c) + 1)
                res.append(''.join(s))
                s[i] = c
            return res
        
        def extend(m1, m2, q):
            for _ in range(len(q), 0, -1):
                p = q.popleft()
                step = m1[p]
                for t in next(p):
                    if t in s or t in m1:
                        continue
                    if t in m2:
                        return step + 1 + m2[t]
                    m1[t] = step + 1
                    q.append(t)
            return -1
        
        def bfs():
            m1, m2 = {"0000": 0}, {target: 0}
            q1, q2 = deque([('0000')]), deque([(target)])
            while q1 and q2:
                t = extend(m1, m2, q1) if len(q1) <= len(q2) else extend(m2, m1, q2)
                if t != -1:
                    return t
            return -1
        
        if target == '0000':
            return 0
        s = set(deadends)
        if '0000' in s:
            return -1
        return bfs()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

朴素 BFS：

```java
class Solution {
    public int openLock(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }
        Set<String> s = new HashSet<>(Arrays.asList(deadends));
        if (s.contains("0000")) {
            return -1;
        }
        Deque<String> q = new ArrayDeque<>();
        q.offer("0000");
        s.add("0000");
        int ans = 0;
        while (!q.isEmpty()) {
            ++ans;
            for (int n = q.size(); n > 0; --n) {
                String p = q.poll();
                for (String t : next(p)) {
                    if (target.equals(t)) {
                        return ans;
                    }
                    if (!s.contains(t)) {
                        q.offer(t);
                        s.add(t);
                    }
                }
            }
        }
        return -1;
    }

    private List<String> next(String t) {
        List res = new ArrayList<>();
        char[] chars = t.toCharArray();
        for (int i = 0; i < 4; ++i) {
            char c = chars[i];
            chars[i] = c == '0' ? '9' : (char) (c - 1);
            res.add(String.valueOf(chars));
            chars[i] = c == '9' ? '0' : (char) (c + 1);
            res.add(String.valueOf(chars));
            chars[i] = c;
        }
        return res;
    }
}
```

双向 BFS 优化搜索：

```java
class Solution {
    private String start;
    private String target;
    private Set<String> s = new HashSet<>();

    public int openLock(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }
        start = "0000";
        this.target = target;
        for (String d : deadends) {
            s.add(d);
        }
        if (s.contains(start)) {
            return -1;
        }
        return bfs();
    }

    private int bfs() {
        Map<String, Integer> m1 = new HashMap<>();
        Map<String, Integer> m2 = new HashMap<>();
        Deque<String> q1 = new ArrayDeque<>();
        Deque<String> q2 = new ArrayDeque<>();
        m1.put(start, 0);
        m2.put(target, 0);
        q1.offer(start);
        q2.offer(target);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            int t = q1.size() <= q2.size() ? extend(m1, m2, q1) : extend(m2, m1, q2);
            if (t != -1) {
                return t;
            }
        }
        return -1;
    }

    private int extend(Map<String, Integer> m1, Map<String, Integer> m2, Deque<String> q) {
        for (int n = q.size(); n > 0; --n) {
            String p = q.poll();
            int step = m1.get(p);
            for (String t : next(p)) {
                if (m1.containsKey(t) || s.contains(t)) {
                    continue;
                }
                if (m2.containsKey(t)) {
                    return step + 1 + m2.get(t);
                }
                m1.put(t, step + 1);
                q.offer(t);
            }
        }
        return -1;
    }

    private List<String> next(String t) {
        List res = new ArrayList<>();
        char[] chars = t.toCharArray();
        for (int i = 0; i < 4; ++i) {
            char c = chars[i];
            chars[i] = c == '0' ? '9' : (char) (c - 1);
            res.add(String.valueOf(chars));
            chars[i] = c == '9' ? '0' : (char) (c + 1);
            res.add(String.valueOf(chars));
            chars[i] = c;
        }
        return res;
    }
}
```

### **C++**

朴素 BFS：

```cpp
class Solution {
public:
    int openLock(vector<string>& deadends, string target) {
        unordered_set<string> s(deadends.begin(), deadends.end());
        if (s.count("0000")) return -1;
        if (target == "0000") return 0;
        queue<string> q{{"0000"}};
        s.insert("0000");
        int ans = 0;
        while (!q.empty())
        {
            ++ans;
            for (int n = q.size(); n > 0; --n)
            {
                string p = q.front();
                q.pop();
                for (string t : next(p))
                {
                    if (target == t) return ans;
                    if (!s.count(t))
                    {
                        q.push(t);
                        s.insert(t);
                    }
                }
            }
        }
        return -1;
    }

    vector<string> next(string& t) {
        vector<string> res;
        for (int i = 0; i < 4; ++i)
        {
            char c = t[i];
            t[i] = c == '0' ? '9' : (char) (c - 1);
            res.push_back(t);
            t[i] = c == '9' ? '0' : (char) (c + 1);
            res.push_back(t);
            t[i] = c;
        }
        return res;
    }
};
```

双向 BFS 优化搜索：

```cpp
class Solution {
public:
    unordered_set<string> s;
    string start;
    string target;

    int openLock(vector<string>& deadends, string target) {
        if (target == "0000") return 0;
        for (auto d : deadends) s.insert(d);
        if (s.count("0000")) return -1;
        this->start = "0000";
        this->target = target;
        return bfs(); 
    }

    int bfs() {
        unordered_map<string, int> m1;
        unordered_map<string, int> m2;
        m1[start] = 0;
        m2[target] = 0;
        queue<string> q1{{start}};
        queue<string> q2{{target}};
        while (!q1.empty() && !q2.empty())
        {
            int t = q1.size() <= q2.size() ? extend(m1, m2, q1) : extend(m2, m1, q2);
            if (t != -1) return t;
        }
        return -1;
    }

    int extend(unordered_map<string, int>& m1, unordered_map<string, int>& m2, queue<string>& q) {
        for (int n = q.size(); n > 0; --n)
        {
            string p = q.front();
            int step = m1[p];
            q.pop();
            for (string t : next(p))
            {
                if (s.count(t) || m1.count(t)) continue;
                if (m2.count(t)) return step + 1 + m2[t];
                m1[t] = step + 1;
                q.push(t);
            }
        }
        return -1;
    }

    vector<string> next(string& t) {
        vector<string> res;
        for (int i = 0; i < 4; ++i)
        {
            char c = t[i];
            t[i] = c == '0' ? '9' : (char) (c - 1);
            res.push_back(t);
            t[i] = c == '9' ? '0' : (char) (c + 1);
            res.push_back(t);
            t[i] = c;
        }
        return res;
    }
};
```

### **Go**

朴素 BFS：

```go
func openLock(deadends []string, target string) int {
	if target == "0000" {
		return 0
	}
	s := make(map[string]bool)
	for _, d := range deadends {
		s[d] = true
	}
	if s["0000"] {
		return -1
	}
	q := []string{"0000"}
	s["0000"] = true
	ans := 0
	next := func(t string) []string {
		s := []byte(t)
		var res []string
		for i, b := range s {
			s[i] = b - 1
			if s[i] < '0' {
				s[i] = '9'
			}
			res = append(res, string(s))
			s[i] = b + 1
			if s[i] > '9' {
				s[i] = '0'
			}
			res = append(res, string(s))
			s[i] = b
		}
		return res
	}
	for len(q) > 0 {
		ans++
		for n := len(q); n > 0; n-- {
			p := q[0]
			q = q[1:]
			for _, t := range next(p) {
				if target == t {
					return ans
				}
				if !s[t] {
					q = append(q, t)
					s[t] = true
				}
			}
		}
	}
	return -1
}
```

双向 BFS 优化搜索：

```go
func openLock(deadends []string, target string) int {
	if target == "0000" {
		return 0
	}
	s := make(map[string]bool)
	for _, d := range deadends {
		s[d] = true
	}
	if s["0000"] {
		return -1
	}
	next := func(t string) []string {
		s := []byte(t)
		var res []string
		for i, b := range s {
			s[i] = b - 1
			if s[i] < '0' {
				s[i] = '9'
			}
			res = append(res, string(s))
			s[i] = b + 1
			if s[i] > '9' {
				s[i] = '0'
			}
			res = append(res, string(s))
			s[i] = b
		}
		return res
	}

	extend := func(m1, m2 map[string]int, q *[]string) int {
		for n := len(*q); n > 0; n-- {
			p := (*q)[0]
			*q = (*q)[1:]
			step, _ := m1[p]
			for _, t := range next(p) {
				if s[t] {
					continue
				}
				if _, ok := m1[t]; ok {
					continue
				}
				if v, ok := m2[t]; ok {
					return step + 1 + v
				}
				m1[t] = step + 1
				*q = append(*q, t)
			}
		}
		return -1
	}

	bfs := func() int {
		q1 := []string{"0000"}
		q2 := []string{target}
		m1 := map[string]int{"0000": 0}
		m2 := map[string]int{target: 0}
		for len(q1) > 0 && len(q2) > 0 {
			t := -1
			if len(q1) <= len(q2) {
				t = extend(m1, m2, &q1)
			} else {
				t = extend(m2, m1, &q2)
			}
			if t != -1 {
				return t
			}
		}
		return -1
	}

	return bfs()
}
```

### **...**

```

```

<!-- tabs:end -->
