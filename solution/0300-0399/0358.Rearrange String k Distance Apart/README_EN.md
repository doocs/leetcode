# [358. Rearrange String k Distance Apart](https://leetcode.com/problems/rearrange-string-k-distance-apart)

[中文文档](/solution/0300-0399/0358.Rearrange%20String%20k%20Distance%20Apart/README.md)

## Description

<p>Given a string <code>s</code> and an integer <code>k</code>, rearrange <code>s</code> such that the same characters are <strong>at least</strong> distance <code>k</code> from each other. If it is not possible to rearrange the string, return an empty string <code>&quot;&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabbcc&quot;, k = 3
<strong>Output:</strong> &quot;abcabc&quot;
<strong>Explanation:</strong> The same letters are at least a distance of 3 from each other.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaabc&quot;, k = 3
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> It is not possible to rearrange the string.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaadbbcc&quot;, k = 2
<strong>Output:</strong> &quot;abacabcd&quot;
<strong>Explanation:</strong> The same letters are at least a distance of 2 from each other.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
	<li><code>0 &lt;= k &lt;= s.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
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
