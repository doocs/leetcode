# [854. K-Similar Strings](https://leetcode.com/problems/k-similar-strings)

[中文文档](/solution/0800-0899/0854.K-Similar%20Strings/README.md)

## Description

<p>Strings <code>s1</code> and <code>s2</code> are <code>k</code><strong>-similar</strong> (for some non-negative integer <code>k</code>) if we can swap the positions of two letters in <code>s1</code> exactly <code>k</code> times so that the resulting string equals <code>s2</code>.</p>

<p>Given two anagrams <code>s1</code> and <code>s2</code>, return the smallest <code>k</code> for which <code>s1</code> and <code>s2</code> are <code>k</code><strong>-similar</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;ab&quot;, s2 = &quot;ba&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> The two string are 1-similar because we can use one swap to change s1 to s2: &quot;ab&quot; --&gt; &quot;ba&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;abc&quot;, s2 = &quot;bca&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The two strings are 2-similar because we can use two swaps to change s1 to s2: &quot;abc&quot; --&gt; &quot;bac&quot; --&gt; &quot;bca&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s1.length &lt;= 20</code></li>
	<li><code>s2.length == s1.length</code></li>
	<li><code>s1</code> and <code>s2</code> contain only lowercase letters from the set <code>{&#39;a&#39;, &#39;b&#39;, &#39;c&#39;, &#39;d&#39;, &#39;e&#39;, &#39;f&#39;}</code>.</li>
	<li><code>s2</code> is an anagram of <code>s1</code>.</li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def kSimilarity(self, s1: str, s2: str) -> int:
        def next(s):
            i = 0
            while s[i] == s2[i]:
                i += 1
            res = []
            for j in range(i + 1, n):
                if s[j] == s2[i] and s[j] != s2[j]:
                    res.append(s2[: i + 1] + s[i + 1 : j] + s[i] + s[j + 1 :])
            return res

        q = deque([s1])
        vis = {s1}
        ans, n = 0, len(s1)
        while 1:
            for _ in range(len(q)):
                s = q.popleft()
                if s == s2:
                    return ans
                for nxt in next(s):
                    if nxt not in vis:
                        vis.add(nxt)
                        q.append(nxt)
            ans += 1
```

```python
class Solution:
    def kSimilarity(self, s1: str, s2: str) -> int:
        def f(s):
            cnt = sum(c != s2[i] for i, c in enumerate(s))
            return (cnt + 1) >> 1

        def next(s):
            i = 0
            while s[i] == s2[i]:
                i += 1
            res = []
            for j in range(i + 1, n):
                if s[j] == s2[i] and s[j] != s2[j]:
                    res.append(s2[: i + 1] + s[i + 1 : j] + s[i] + s[j + 1 :])
            return res

        q = [(f(s1), s1)]
        dist = {s1: 0}
        n = len(s1)
        while 1:
            _, s = heappop(q)
            if s == s2:
                return dist[s]
            for nxt in next(s):
                if nxt not in dist or dist[nxt] > dist[s] + 1:
                    dist[nxt] = dist[s] + 1
                    heappush(q, (dist[nxt] + f(nxt), nxt))
```

### **Java**

```java
class Solution {
    public int kSimilarity(String s1, String s2) {
        Deque<String> q = new ArrayDeque<>();
        Set<String> vis = new HashSet<>();
        q.offer(s1);
        vis.add(s1);
        int ans = 0;
        while (true) {
            for (int i = q.size(); i > 0; --i) {
                String s = q.pollFirst();
                if (s.equals(s2)) {
                    return ans;
                }
                for (String nxt : next(s, s2)) {
                    if (!vis.contains(nxt)) {
                        vis.add(nxt);
                        q.offer(nxt);
                    }
                }
            }
            ++ans;
        }
    }

    private List<String> next(String s, String s2) {
        int i = 0, n = s.length();
        char[] cs = s.toCharArray();
        for (; cs[i] == s2.charAt(i); ++i) {
        }

        List<String> res = new ArrayList<>();
        for (int j = i + 1; j < n; ++j) {
            if (cs[j] == s2.charAt(i) && cs[j] != s2.charAt(j)) {
                swap(cs, i, j);
                res.add(new String(cs));
                swap(cs, i, j);
            }
        }
        return res;
    }

    private void swap(char[] cs, int i, int j) {
        char t = cs[i];
        cs[i] = cs[j];
        cs[j] = t;
    }
}
```

```java
class Solution {
    public int kSimilarity(String s1, String s2) {
        PriorityQueue<Pair<Integer, String>> q
            = new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));
        q.offer(new Pair<>(f(s1, s2), s1));
        Map<String, Integer> dist = new HashMap<>();
        dist.put(s1, 0);
        while (true) {
            String s = q.poll().getValue();
            if (s.equals(s2)) {
                return dist.get(s);
            }
            for (String nxt : next(s, s2)) {
                if (!dist.containsKey(nxt) || dist.get(nxt) > dist.get(s) + 1) {
                    dist.put(nxt, dist.get(s) + 1);
                    q.offer(new Pair<>(dist.get(nxt) + f(nxt, s2), nxt));
                }
            }
        }
    }

    private int f(String s, String s2) {
        int cnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != s2.charAt(i)) {
                ++cnt;
            }
        }
        return (cnt + 1) >> 1;
    }

    private List<String> next(String s, String s2) {
        int i = 0, n = s.length();
        char[] cs = s.toCharArray();
        for (; cs[i] == s2.charAt(i); ++i) {
        }

        List<String> res = new ArrayList<>();
        for (int j = i + 1; j < n; ++j) {
            if (cs[j] == s2.charAt(i) && cs[j] != s2.charAt(j)) {
                swap(cs, i, j);
                res.add(new String(cs));
                swap(cs, i, j);
            }
        }
        return res;
    }

    private void swap(char[] cs, int i, int j) {
        char t = cs[i];
        cs[i] = cs[j];
        cs[j] = t;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int kSimilarity(string s1, string s2) {
        queue<string> q{{s1}};
        unordered_set<string> vis{{s1}};
        int ans = 0;
        while (1) {
            for (int i = q.size(); i; --i) {
                auto s = q.front();
                q.pop();
                if (s == s2) {
                    return ans;
                }
                for (auto& nxt : next(s, s2)) {
                    if (!vis.count(nxt)) {
                        vis.insert(nxt);
                        q.push(nxt);
                    }
                }
            }
            ++ans;
        }
    }

    vector<string> next(string& s, string& s2) {
        int i = 0, n = s.size();
        for (; s[i] == s2[i]; ++i) {}
        vector<string> res;
        for (int j = i + 1; j < n; ++j) {
            if (s[j] == s2[i] && s[j] != s2[j]) {
                swap(s[i], s[j]);
                res.push_back(s);
                swap(s[i], s[j]);
            }
        }
        return res;
    }
};
```

```cpp
using pis = pair<int, string>;

class Solution {
public:
    int kSimilarity(string s1, string s2) {
        priority_queue<pis, vector<pis>, greater<pis>> q;
        q.push({f(s1, s2), s1});
        unordered_map<string, int> dist;
        dist[s1] = 0;
        while (1) {
            auto [_, s] = q.top();
            q.pop();
            if (s == s2) {
                return dist[s];
            }
            for (auto& nxt : next(s, s2)) {
                if (!dist.count(nxt) || dist[nxt] > dist[s] + 1) {
                    dist[nxt] = dist[s] + 1;
                    q.push({dist[nxt] + f(nxt, s2), nxt});
                }
            }
        }
    }

    int f(string& s, string& s2) {
        int cnt = 0;
        for (int i = 0; i < s.size(); ++i) {
            cnt += s[i] != s2[i];
        }
        return (cnt + 1) >> 1;
    }

    vector<string> next(string& s, string& s2) {
        int i = 0, n = s.size();
        for (; s[i] == s2[i]; ++i) {}
        vector<string> res;
        for (int j = i + 1; j < n; ++j) {
            if (s[j] == s2[i] && s[j] != s2[j]) {
                swap(s[i], s[j]);
                res.push_back(s);
                swap(s[i], s[j]);
            }
        }
        return res;
    }
};
```

### **Go**

```go
func kSimilarity(s1 string, s2 string) int {
	next := func(s string) []string {
		i := 0
		res := []string{}
		for ; s[i] == s2[i]; i++ {
		}
		for j := i + 1; j < len(s1); j++ {
			if s[j] == s2[i] && s[j] != s2[j] {
				res = append(res, s[:i]+string(s[j])+s[i+1:j]+string(s[i])+s[j+1:])
			}
		}
		return res
	}

	q := []string{s1}
	vis := map[string]bool{s1: true}
	ans := 0
	for {
		for i := len(q); i > 0; i-- {
			s := q[0]
			q = q[1:]
			if s == s2 {
				return ans
			}
			for _, nxt := range next(s) {
				if !vis[nxt] {
					vis[nxt] = true
					q = append(q, nxt)
				}
			}
		}
		ans++
	}
}
```

```go
func kSimilarity(s1 string, s2 string) int {
	next := func(s string) []string {
		i := 0
		res := []string{}
		for ; s[i] == s2[i]; i++ {
		}
		for j := i + 1; j < len(s1); j++ {
			if s[j] == s2[i] && s[j] != s2[j] {
				res = append(res, s[:i]+string(s[j])+s[i+1:j]+string(s[i])+s[j+1:])
			}
		}
		return res
	}

	f := func(s string) int {
		cnt := 0
		for i := range s {
			if s[i] != s2[i] {
				cnt++
			}
		}
		return (cnt + 1) >> 1
	}

	q := hp{pair{f(s1), s1}}
	dist := map[string]int{s1: 0}
	for {
		s := heap.Pop(&q).(pair).s
		if s == s2 {
			return dist[s]
		}
		for _, nxt := range next(s) {
			if v, ok := dist[nxt]; !ok || v > dist[s]+1 {
				dist[nxt] = dist[s] + 1
				heap.Push(&q, pair{dist[nxt] + f(nxt), nxt})
			}
		}
	}
}

type pair struct {
	v int
	s string
}
type hp []pair

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	a, b := h[i], h[j]
	return a.v < b.v
}
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

### **...**

```

```

<!-- tabs:end -->
