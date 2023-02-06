# [854. 相似度为 K 的字符串](https://leetcode.cn/problems/k-similar-strings)

[English Version](/solution/0800-0899/0854.K-Similar%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>对于某些非负整数 <code>k</code> ，如果交换 <code>s1</code> 中两个字母的位置恰好 <code>k</code> 次，能够使结果字符串等于 <code>s2</code> ，则认为字符串 <code>s1</code> 和 <code>s2</code> 的<strong> 相似度为 </strong><code>k</code><strong> </strong><strong>。</strong></p>

<p>给你两个字母异位词 <code>s1</code> 和 <code>s2</code> ，返回 <code>s1</code> 和 <code>s2</code> 的相似度 <code>k</code><strong> </strong>的最小值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s1 = "ab", s2 = "ba"
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s1 = "abc", s2 = "bca"
<strong>输出：</strong>2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s1.length &lt;= 20</code></li>
	<li><code>s2.length == s1.length</code></li>
	<li><code>s1</code>&nbsp;和&nbsp;<code>s2</code>&nbsp;&nbsp;只包含集合&nbsp;<code>{'a', 'b', 'c', 'd', 'e', 'f'}</code>&nbsp;中的小写字母</li>
	<li><code>s2</code> 是 <code>s1</code> 的一个字母异位词</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

本题实际上是一类经典的问题：求解最小操作次数。从一个初始状态 $s_1$，经过最少 $k$ 次状态转换，变成目标状态 $s_2$。字符串长度不超过 $20$，我们考虑使用 BFS 搜索来求解。

首先将初始状态 $s_1$ 入队，用哈希表 `vis` 记录所有访问过的状态。

接下来每一轮，都是将队列中的所有状态转换到下一个状态，当遇到目标状态 $s_2$ 时，当前状态转换的轮数就是答案。

我们发现，题目的重点在于如何进行状态转换。对于本题，转换操作就是交换一个字符串中两个位置的字符。如果当前字符串 $s[i]$ 与 $s_2[i]$ 不相等，那么我们应该在 $s$ 中找到一个位置 $j$，满足 $s[j] = s_2[i]$ 并且 $s[j] \neq s_2[j]$，然后交换 $s[i]$ 和 $s[j]$。这样可以使得状态最接近于目标状态。这里的状态转换可以参考以下代码中的 `next()` 函数。

复杂度分析：BFS 剪枝不讨论时空复杂度。

**方法二：A\* 算法（进阶）**

A\* 搜索算法（A\* 读作 A-star），简称 A\* 算法，是一种在图形平面上，对于有多个节点的路径求出最低通过成本的算法。它属于图遍历和最佳优先搜索算法（英文：Best-first search），亦是 BFS 的改进。

A\* 算法主要步骤如下：

1. 将方法一中的 BFS 队列转换为优先队列（小根堆）；
1. 队列中的每个元素为 `(dist[s] + f(s), s)`，`dist[s]` 表示从初始状态 $s_1$ 到当前状态 $s$ 的距离，`f(s)` 表示从当前状态 $s$ 到目标状态 $s_2$ 的估计距离，这两个距离之和作为堆排序的依据；
1. 当终点第一次出队时，说明找到了从起点 $s_1$ 到终点 $s_2$ 的最短路径，直接返回对应的距离；
1. `f(s)` 是估价函数，并且估价函数要满足 `f(s) <= g(s)`，其中 `g(s)` 表示 $s$ 到终点 $s_2$ 的真实距离；

需要注意的是，A\* 算法只能保证终点第一次出队时，即找到了一条从起点到终点的最小路径，不能保证其他点出队时也是从起点到当前点的最短路径。

复杂度分析：启发式搜索不讨论时空复杂度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
