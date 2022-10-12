# [752. Open the Lock](https://leetcode.com/problems/open-the-lock)

[中文文档](/solution/0700-0799/0752.Open%20the%20Lock/README.md)

## Description

<p>You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: <code>&#39;0&#39;, &#39;1&#39;, &#39;2&#39;, &#39;3&#39;, &#39;4&#39;, &#39;5&#39;, &#39;6&#39;, &#39;7&#39;, &#39;8&#39;, &#39;9&#39;</code>. The wheels can rotate freely and wrap around: for example we can turn <code>&#39;9&#39;</code> to be <code>&#39;0&#39;</code>, or <code>&#39;0&#39;</code> to be <code>&#39;9&#39;</code>. Each move consists of turning one wheel one slot.</p>

<p>The lock initially starts at <code>&#39;0000&#39;</code>, a string representing the state of the 4 wheels.</p>

<p>You are given a list of <code>deadends</code> dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.</p>

<p>Given a <code>target</code> representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> deadends = [&quot;0201&quot;,&quot;0101&quot;,&quot;0102&quot;,&quot;1212&quot;,&quot;2002&quot;], target = &quot;0202&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> 
A sequence of valid moves would be &quot;0000&quot; -&gt; &quot;1000&quot; -&gt; &quot;1100&quot; -&gt; &quot;1200&quot; -&gt; &quot;1201&quot; -&gt; &quot;1202&quot; -&gt; &quot;0202&quot;.
Note that a sequence like &quot;0000&quot; -&gt; &quot;0001&quot; -&gt; &quot;0002&quot; -&gt; &quot;0102&quot; -&gt; &quot;0202&quot; would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end &quot;0102&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> deadends = [&quot;8888&quot;], target = &quot;0009&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can turn the last wheel in reverse to move from &quot;0000&quot; -&gt; &quot;0009&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> deadends = [&quot;8887&quot;,&quot;8889&quot;,&quot;8878&quot;,&quot;8898&quot;,&quot;8788&quot;,&quot;8988&quot;,&quot;7888&quot;,&quot;9888&quot;], target = &quot;8888&quot;
<strong>Output:</strong> -1
<strong>Explanation:</strong> We cannot reach the target without getting stuck.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= deadends.length &lt;= 500</code></li>
	<li><code>deadends[i].length == 4</code></li>
	<li><code>target.length == 4</code></li>
	<li>target <strong>will not be</strong> in the list <code>deadends</code>.</li>
	<li><code>target</code> and <code>deadends[i]</code> consist of digits only.</li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

BFS:

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
            for _ in range(len(q)):
                p = q.popleft()
                for t in next(p):
                    if t == target:
                        return ans
                    if t not in s:
                        q.append(t)
                        s.add(t)
        return -1
```

Two-end BFS:

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
            for _ in range(len(q)):
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

BFS:

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

Two-end BFS:

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

A\* search:

```java
class Solution {
    private String target;

    public int openLock(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }
        String start = "0000";
        this.target = target;
        Set<String> s = new HashSet<>();
        for (String d : deadends) {
            s.add(d);
        }
        if (s.contains(start)) {
            return -1;
        }
        PriorityQueue<Pair<Integer, String>> q
            = new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));
        q.offer(new Pair<>(f(start), start));
        Map<String, Integer> dist = new HashMap<>();
        dist.put(start, 0);
        while (!q.isEmpty()) {
            String state = q.poll().getValue();
            int step = dist.get(state);
            if (target.equals(state)) {
                return step;
            }
            for (String t : next(state)) {
                if (s.contains(t)) {
                    continue;
                }
                if (!dist.containsKey(t) || dist.get(t) > step + 1) {
                    dist.put(t, step + 1);
                    q.offer(new Pair<>(step + 1 + f(t), t));
                }
            }
        }
        return -1;
    }

    private int f(String s) {
        int ans = 0;
        for (int i = 0; i < 4; ++i) {
            int a = s.charAt(i) - '0';
            int b = target.charAt(i) - '0';
            if (a > b) {
                int t = a;
                a = b;
                b = a;
            }
            ans += Math.min(b - a, a + 10 - b);
        }
        return ans;
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

BFS:

```cpp
class Solution {
public:
    int openLock(vector<string>& deadends, string target) {
        unordered_set<string> s(deadends.begin(), deadends.end());
        if (s.count("0000")) return -1;
        if (target == "0000") return 0;
        queue<string> q {{"0000"}};
        s.insert("0000");
        int ans = 0;
        while (!q.empty()) {
            ++ans;
            for (int n = q.size(); n > 0; --n) {
                string p = q.front();
                q.pop();
                for (string t : next(p)) {
                    if (target == t) return ans;
                    if (!s.count(t)) {
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
        for (int i = 0; i < 4; ++i) {
            char c = t[i];
            t[i] = c == '0' ? '9' : (char)(c - 1);
            res.push_back(t);
            t[i] = c == '9' ? '0' : (char)(c + 1);
            res.push_back(t);
            t[i] = c;
        }
        return res;
    }
};
```

Two-end BFS:

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

A\* search:

```cpp
class Solution {
public:
    string target;

    int openLock(vector<string>& deadends, string target) {
        if (target == "0000") return 0;
        unordered_set<string> s(deadends.begin(), deadends.end());
        if (s.count("0000")) return -1;
        string start = "0000";
        this->target = target;
        typedef pair<int , string> PIS;
        priority_queue<PIS, vector<PIS>, greater<PIS>> q;
        unordered_map<string, int> dist;
        dist[start] = 0;
        q.push({f(start), start});
        while (!q.empty())
        {
            PIS t = q.top();
            q.pop();
            string state = t.second;
            int step = dist[state];
            if (state == target) return step;
            for (string& t : next(state))
            {
                if (s.count(t)) continue;
                if (!dist.count(t) || dist[t] > step + 1)
                {
                    dist[t] = step + 1;
                    q.push({step + 1 + f(t), t});
                }
            }
        }
        return -1;
    }

    int f(string s) {
        int ans = 0;
        for (int i = 0; i < 4; ++i)
        {
            int a = s[i] - '0';
            int b = target[i] - '0';
            if (a < b)
            {
                int t = a;
                a = b;
                b = t;
            }
            ans += min(b - a, a + 10 - b);
        }
        return ans;
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

BFS:

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

Two-end BFS:

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
