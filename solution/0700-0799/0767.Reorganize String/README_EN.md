# [767. Reorganize String](https://leetcode.com/problems/reorganize-string)

[中文文档](/solution/0700-0799/0767.Reorganize%20String/README.md)

## Description

<p>Given a string <code>s</code>, rearrange the characters of <code>s</code> so that any two adjacent characters are not the same.</p>

<p>Return <em>any possible rearrangement of</em> <code>s</code> <em>or return</em> <code>&quot;&quot;</code> <em>if not possible</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> s = "aab"
<strong>Output:</strong> "aba"
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> s = "aaab"
<strong>Output:</strong> ""
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reorganizeString(self, s: str) -> str:
        n = len(s)
        cnt = Counter(s)
        mx = max(cnt.values())
        if mx > (n + 1) // 2:
            return ''
        i = 0
        ans = [None] * n
        for k, v in cnt.most_common():
            while v:
                ans[i] = k
                v -= 1
                i += 2
                if i >= n:
                    i = 1
        return ''.join(ans)
```

```python
class Solution:
    def reorganizeString(self, s: str) -> str:
        return self.rearrangeString(s, 2)

    def rearrangeString(self, s: str, k: int) -> str:
        h = [(-v, c) for c, v in Counter(s).items()]
        heapify(h)
        q = deque()
        ans = []
        while h:
            v, c = heappop(h)
            v *= -1
            ans.append(c)
            q.append((v - 1, c))
            if len(q) >= k:
                w, c = q.popleft()
                if w:
                    heappush(h, (-w, c))
        return "" if len(ans) != len(s) else "".join(ans)
```

### **Java**

```java
class Solution {
    public String reorganizeString(String s) {
        int[] cnt = new int[26];
        int mx = 0;
        for (char c : s.toCharArray()) {
            int t = c - 'a';
            ++cnt[t];
            mx = Math.max(mx, cnt[t]);
        }
        int n = s.length();
        if (mx > (n + 1) / 2) {
            return "";
        }
        int k = 0;
        for (int v : cnt) {
            if (v > 0) {
                ++k;
            }
        }
        int[][] m = new int[k][2];
        k = 0;
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] > 0) {
                m[k++] = new int[] {cnt[i], i};
            }
        }
        Arrays.sort(m, (a, b) -> b[0] - a[0]);
        k = 0;
        StringBuilder ans = new StringBuilder(s);
        for (int[] e : m) {
            int v = e[0], i = e[1];
            while (v-- > 0) {
                ans.setCharAt(k, (char) ('a' + i));
                k += 2;
                if (k >= n) {
                    k = 1;
                }
            }
        }
        return ans.toString();
    }
}
```

```java
class Solution {
    public String reorganizeString(String s) {
        return rearrangeString(s, 2);
    }

    public String rearrangeString(String s, int k) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] > 0) {
                pq.offer(new int[] {cnt[i], i});
            }
        }
        Deque<int[]> q = new ArrayDeque<>();
        StringBuilder ans = new StringBuilder();
        while (!pq.isEmpty()) {
            var p = pq.poll();
            int v = p[0], c = p[1];
            ans.append((char) ('a' + c));
            q.offer(new int[] {v - 1, c});
            if (q.size() >= k) {
                p = q.pollFirst();
                if (p[0] > 0) {
                    pq.offer(p);
                }
            }
        }
        return ans.length() == n ? ans.toString() : "";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string reorganizeString(string s) {
        vector<int> cnt(26);
        for (char& c : s) ++cnt[c - 'a'];
        int mx = *max_element(cnt.begin(), cnt.end());
        int n = s.size();
        if (mx > (n + 1) / 2) return "";
        vector<vector<int>> m;
        for (int i = 0; i < 26; ++i) {
            if (cnt[i]) m.push_back({cnt[i], i});
        }
        sort(m.begin(), m.end());
        reverse(m.begin(), m.end());
        string ans = s;
        int k = 0;
        for (auto& e : m) {
            int v = e[0], i = e[1];
            while (v--) {
                ans[k] = 'a' + i;
                k += 2;
                if (k >= n) k = 1;
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    string reorganizeString(string s) {
        return rearrangeString(s, 2);
    }

    string rearrangeString(string s, int k) {
        unordered_map<char, int> cnt;
        for (char c : s) ++cnt[c];
        priority_queue<pair<int, char>> pq;
        for (auto& [c, v] : cnt) pq.push({v, c});
        queue<pair<int, char>> q;
        string ans;
        while (!pq.empty()) {
            auto [v, c] = pq.top();
            pq.pop();
            ans += c;
            q.push({v - 1, c});
            if (q.size() >= k) {
                auto p = q.front();
                q.pop();
                if (p.first) {
                    pq.push(p);
                }
            }
        }
        return ans.size() == s.size() ? ans : "";
    }
};
```

### **Go**

```go
func reorganizeString(s string) string {
	cnt := make([]int, 26)
	mx := 0
	for _, c := range s {
		t := c - 'a'
		cnt[t]++
		mx = max(mx, cnt[t])
	}
	n := len(s)
	if mx > (n+1)/2 {
		return ""
	}
	m := [][]int{}
	for i, v := range cnt {
		if v > 0 {
			m = append(m, []int{v, i})
		}
	}
	sort.Slice(m, func(i, j int) bool {
		return m[i][0] > m[j][0]
	})
	ans := make([]byte, n)
	k := 0
	for _, e := range m {
		v, i := e[0], e[1]
		for v > 0 {
			ans[k] = byte('a' + i)
			k += 2
			if k >= n {
				k = 1
			}
			v--
		}
	}
	return string(ans)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func reorganizeString(s string) string {
	return rearrangeString(s, 2)
}

func rearrangeString(s string, k int) string {
	cnt := map[byte]int{}
	for i := range s {
		cnt[s[i]]++
	}
	pq := hp{}
	for c, v := range cnt {
		heap.Push(&pq, pair{v, c})
	}
	ans := []byte{}
	q := []pair{}
	for len(pq) > 0 {
		p := heap.Pop(&pq).(pair)
		v, c := p.v, p.c
		ans = append(ans, c)
		q = append(q, pair{v - 1, c})
		if len(q) >= k {
			p = q[0]
			q = q[1:]
			if p.v > 0 {
				heap.Push(&pq, p)
			}
		}
	}
	if len(ans) == len(s) {
		return string(ans)
	}
	return ""
}

type pair struct {
	v int
	c byte
}

type hp []pair

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	a, b := h[i], h[j]
	return a.v > b.v
}
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

### **...**

```

```

<!-- tabs:end -->
