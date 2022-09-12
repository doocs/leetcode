# [358. K 距离间隔重排字符串](https://leetcode.cn/problems/rearrange-string-k-distance-apart)

[English Version](/solution/0300-0399/0358.Rearrange%20String%20k%20Distance%20Apart/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个非空的字符串&nbsp;<code>s</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;，你要将这个字符串&nbsp;<code>s</code>&nbsp;中的字母进行重新排列，使得重排后的字符串中相同字母的位置间隔距离 <strong>至少</strong> 为&nbsp;<code>k</code>&nbsp;。如果无法做到，请返回一个空字符串&nbsp;<code>""</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong>s = "aabbcc", k = 3
<strong>输出: </strong>"abcabc" 
<strong>解释: </strong>相同的字母在新的字符串中间隔至少 3 个单位距离。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>s = "aaabc", k = 3
<strong>输出: </strong>"" 
<strong>解释:</strong> 没有办法找到可能的重排结果。
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre>
<strong>输入: </strong>s = "aaadbbcc", k = 2
<strong>输出: </strong>"abacabcd"
<strong>解释:</strong> 相同的字母在新的字符串中间隔至少 2 个单位距离。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;仅由小写英文字母组成</li>
	<li><code>0 &lt;= k &lt;= s.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 哈希表 + 优先队列（大根堆）**

先用哈希表 `cnt` 统计每个字母出现的次数，然后构建一个大根堆 `pq`，其中每个元素是一个 `(v, c)` 的元组，其中 `c` 是字母，`v` 是字母出现的次数。

重排字符串时，我们每次从堆顶弹出一个元素 `(v, c)`，将 `c` 添加到结果字符串中，并将 `(v-1, c)` 放入队列 `q` 中。当队列 `q` 的长度达到 $k$ 及以上时，弹出队首元素，若此时 `v` 大于 0，则将队首元素放入堆中。循环，直至堆为空。

最后判断结果字符串的长度，若与 `s` 长度相等，则返回结果字符串，否则返回空串。

时间复杂度 $O(n\log n)$，其中 $n$ 是字符串 `s` 的长度。

相似题目：[767. 重构字符串](/solution/0700-0799/0767.Reorganize%20String/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
