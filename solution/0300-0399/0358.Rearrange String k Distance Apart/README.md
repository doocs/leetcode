---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0358.Rearrange%20String%20k%20Distance%20Apart/README.md
tags:
    - 贪心
    - 哈希表
    - 字符串
    - 计数
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [358. K 距离间隔重排字符串 🔒](https://leetcode.cn/problems/rearrange-string-k-distance-apart)

[English Version](/solution/0300-0399/0358.Rearrange%20String%20k%20Distance%20Apart/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 哈希表 + 优先队列（大根堆）

我们用一个哈希表或数组 $\textit{cnt}$ 来统计字符串中每个字母出现的次数，然后用一个大根堆 $\textit{pq}$ 来存储每个字母及其出现次数。堆中的每个元素是一个二元组 $(v, c)$，其中 $v$ 和 $c$ 分别表示字母出现的次数和字母本身。

在重排字符串时，我们每次从堆顶弹出一个元素 $(v, c)$，将字母 $c$ 添加到结果字符串中，并将 $(v-1, c)$ 放入一个队列 $\textit{q}$ 中。当队列 $\textit{q}$ 的长度达到 $k$ 及以上时，弹出队首元素，若此时 $v$ 大于 $0$，则将队首元素重新放入堆中。重复该过程，直到堆为空。

最后，我们判断结果字符串的长度是否与原字符串相等，若相等则返回结果字符串，否则返回空串。

时间复杂度为 $O(n \log n)$，其中 $n$ 是字符串的长度。空间复杂度 $O(|\Sigma|)$，其中 $|\Sigma|$ 是字符集的大小，本题中 $|\Sigma| = 26$。

相似题目：

-   [767. 重构字符串](https://github.com/doocs/leetcode/blob/main/solution/0700-0799/0767.Reorganize%20String/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rearrangeString(self, s: str, k: int) -> str:
        cnt = Counter(s)
        pq = [(-v, c) for c, v in cnt.items()]
        heapify(pq)
        q = deque()
        ans = []
        while pq:
            v, c = heappop(pq)
            ans.append(c)
            q.append((v + 1, c))
            if len(q) >= k:
                e = q.popleft()
                if e[0]:
                    heappush(pq, e)
        return "" if len(ans) < len(s) else "".join(ans)
```

#### Java

```java
class Solution {
    public String rearrangeString(String s, int k) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < cnt.length; ++i) {
            if (cnt[i] > 0) {
                pq.offer(new int[] {cnt[i], i});
            }
        }
        Deque<int[]> q = new ArrayDeque<>();
        StringBuilder ans = new StringBuilder();
        while (!pq.isEmpty()) {
            var p = pq.poll();
            p[0] -= 1;
            ans.append((char) ('a' + p[1]));
            q.offerLast(p);
            if (q.size() >= k) {
                p = q.pollFirst();
                if (p[0] > 0) {
                    pq.offer(p);
                }
            }
        }
        return ans.length() < s.length() ? "" : ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string rearrangeString(string s, int k) {
        vector<int> cnt(26, 0);
        for (char c : s) {
            ++cnt[c - 'a'];
        }

        priority_queue<pair<int, int>> pq;
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] > 0) {
                pq.emplace(cnt[i], i);
            }
        }

        queue<pair<int, int>> q;
        string ans;
        while (!pq.empty()) {
            auto p = pq.top();
            pq.pop();
            p.first -= 1;
            ans.push_back('a' + p.second);
            q.push(p);
            if (q.size() >= k) {
                p = q.front();
                q.pop();
                if (p.first > 0) {
                    pq.push(p);
                }
            }
        }

        return ans.size() < s.size() ? "" : ans;
    }
};
```

#### Go

```go
func rearrangeString(s string, k int) string {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	pq := priorityqueue.NewWith(func(a, b any) int {
		x := a.([2]int)
		y := b.([2]int)
		return y[0] - x[0]
	})

	for i := 0; i < 26; i++ {
		if cnt[i] > 0 {
			pq.Enqueue([2]int{cnt[i], i})
		}
	}

	var q [][2]int
	var ans strings.Builder

	for pq.Size() > 0 {
		p, _ := pq.Dequeue()
		pair := p.([2]int)
		pair[0]--
		ans.WriteByte(byte('a' + pair[1]))
		q = append(q, pair)

		if len(q) >= k {
			front := q[0]
			q = q[1:]
			if front[0] > 0 {
				pq.Enqueue(front)
			}
		}
	}

	if ans.Len() < len(s) {
		return ""
	}
	return ans.String()
}
```

#### TypeScript

```ts
export function rearrangeString(s: string, k: number): string {
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)]++;
    }

    const pq = new PriorityQueue<[number, number]>((a, b) => b[0] - a[0]);
    for (let i = 0; i < 26; i++) {
        if (cnt[i] > 0) {
            pq.enqueue([cnt[i], i]);
        }
    }

    const q: [number, number][] = [];
    const ans: string[] = [];
    while (!pq.isEmpty()) {
        const [count, idx] = pq.dequeue()!;
        const newCount = count - 1;
        ans.push(String.fromCharCode('a'.charCodeAt(0) + idx));
        q.push([newCount, idx]);
        if (q.length >= k) {
            const [frontCount, frontIdx] = q.shift()!;
            if (frontCount > 0) {
                pq.enqueue([frontCount, frontIdx]);
            }
        }
    }
    return ans.length < s.length ? '' : ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
