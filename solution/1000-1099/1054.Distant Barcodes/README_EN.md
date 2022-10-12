# [1054. Distant Barcodes](https://leetcode.com/problems/distant-barcodes)

[中文文档](/solution/1000-1099/1054.Distant%20Barcodes/README.md)

## Description

<p>In a warehouse, there is a row of barcodes, where the <code>i<sup>th</sup></code> barcode is <code>barcodes[i]</code>.</p>

<p>Rearrange the barcodes so that no two adjacent barcodes are equal. You may return any answer, and it is guaranteed an answer exists.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> barcodes = [1,1,1,2,2,2]
<strong>Output:</strong> [2,1,2,1,2,1]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> barcodes = [1,1,1,1,2,2,3,3]
<strong>Output:</strong> [1,3,1,3,1,2,1,2]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= barcodes.length &lt;= 10000</code></li>
	<li><code>1 &lt;= barcodes[i] &lt;= 10000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def rearrangeBarcodes(self, barcodes: List[int]) -> List[int]:
        cnt = Counter(barcodes)
        h = [(-v, k) for k, v in cnt.items()]
        heapify(h)
        q = deque()
        ans = []
        while h:
            v, k = heappop(h)
            ans.append(k)
            q.append((v + 1, k))
            while len(q) > 1:
                p = q.popleft()
                if p[0]:
                    heappush(h, p)
        return ans
```

### **Java**

```java
class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : barcodes) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (var e : cnt.entrySet()) {
            pq.offer(new int[] {e.getKey(), e.getValue()});
        }
        Deque<int[]> q = new ArrayDeque<>();
        int[] ans = new int[barcodes.length];
        int i = 0;
        while (!pq.isEmpty()) {
            var p = pq.poll();
            ans[i++] = p[0];
            p[1]--;
            q.offer(p);
            while (q.size() > 1) {
                p = q.pollFirst();
                if (p[1] > 0) {
                    pq.offer(p);
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
using pii = pair<int, int>;

class Solution {
public:
    vector<int> rearrangeBarcodes(vector<int>& barcodes) {
        unordered_map<int, int> cnt;
        for (auto& v : barcodes) {
            ++cnt[v];
        }
        priority_queue<pii> pq;
        for (auto& [k, v] : cnt) {
            pq.push({v, k});
        }
        vector<int> ans;
        queue<pii> q;
        while (pq.size()) {
            auto p = pq.top();
            pq.pop();
            ans.push_back(p.second);
            p.first--;
            q.push(p);
            while (q.size() > 1) {
                p = q.front();
                q.pop();
                if (p.first) {
                    pq.push(p);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func rearrangeBarcodes(barcodes []int) []int {
	cnt := map[int]int{}
	for _, v := range barcodes {
		cnt[v]++
	}
	pq := hp{}
	for k, v := range cnt {
		heap.Push(&pq, pair{v, k})
	}
	ans := []int{}
	q := []pair{}
	for len(pq) > 0 {
		p := heap.Pop(&pq).(pair)
		v, k := p.v, p.k
		ans = append(ans, k)
		q = append(q, pair{v - 1, k})
		for len(q) > 1 {
			p = q[0]
			q = q[1:]
			if p.v > 0 {
				heap.Push(&pq, p)
			}
		}
	}
	return ans
}

type pair struct {
	v int
	k int
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
