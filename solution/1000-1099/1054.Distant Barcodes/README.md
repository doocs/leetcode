# [1054. 距离相等的条形码](https://leetcode.cn/problems/distant-barcodes)

[English Version](/solution/1000-1099/1054.Distant%20Barcodes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个仓库里，有一排条形码，其中第 <code>i</code> 个条形码为&nbsp;<code>barcodes[i]</code>。</p>

<p>请你重新排列这些条形码，使其中任意两个相邻的条形码不能相等。 你可以返回任何满足该要求的答案，此题保证存在答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>barcodes = [1,1,1,2,2,2]
<strong>输出：</strong>[2,1,2,1,2,1]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>barcodes = [1,1,1,1,2,2,3,3]
<strong>输出：</strong>[1,3,1,3,2,1,2,1]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= barcodes.length &lt;= 10000</code></li>
	<li><code>1 &lt;= barcodes[i] &lt;= 10000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 哈希表 + 优先队列（大根堆）**

先用哈希表 `cnt` 统计每种条形码的数量，然后将每种条形码及其数量存入优先队列（大根堆） 中，优先队列中的元素按照条形码数量从大到小排序。

重排条形码时，我们每次从堆顶弹出一个元素 `(v, k)`，将 `k` 添加到结果数组中，并将 `(v-1, k)` 放入队列 `q` 中。当队列长度大于 $1$ 时，弹出队首元素 `p`，若此时 `p.v` 大于 $0$，则将 `p` 放入堆中。循环，直至堆为空。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为条形码数组的长度。

相似题目：[767. 重构字符串](/solution/0700-0799/0767.Reorganize%20String/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
