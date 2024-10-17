---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1405.Longest%20Happy%20String/README.md
rating: 1820
source: 第 183 场周赛 Q3
tags:
    - 贪心
    - 字符串
    - 堆（优先队列）
---

<!-- problem:start -->

# [1405. 最长快乐字符串](https://leetcode.cn/problems/longest-happy-string)

[English Version](/solution/1400-1499/1405.Longest%20Happy%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>如果字符串中不含有任何 <code>&#39;aaa&#39;</code>，<code>&#39;bbb&#39;</code> 或 <code>&#39;ccc&#39;</code> 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。</p>

<p>给你三个整数 <code>a</code>，<code>b</code> ，<code>c</code>，请你返回 <strong>任意一个</strong> 满足下列全部条件的字符串 <code>s</code>：</p>

<ul>
	<li><code>s</code> 是一个尽可能长的快乐字符串。</li>
	<li><code>s</code> 中 <strong>最多</strong> 有<code>a</code> 个字母 <code>&#39;a&#39;</code>、<code>b</code>&nbsp;个字母 <code>&#39;b&#39;</code>、<code>c</code> 个字母 <code>&#39;c&#39;</code> 。</li>
	<li><code>s </code>中只含有 <code>&#39;a&#39;</code>、<code>&#39;b&#39;</code> 、<code>&#39;c&#39;</code> 三种字母。</li>
</ul>

<p>如果不存在这样的字符串 <code>s</code> ，请返回一个空字符串 <code>&quot;&quot;</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>a = 1, b = 1, c = 7
<strong>输出：</strong>&quot;ccaccbcc&quot;
<strong>解释：</strong>&quot;ccbccacc&quot; 也是一种正确答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>a = 2, b = 2, c = 1
<strong>输出：</strong>&quot;aabbc&quot;
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>a = 7, b = 1, c = 0
<strong>输出：</strong>&quot;aabaa&quot;
<strong>解释：</strong>这是该测试用例的唯一正确答案。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= a, b, c &lt;= 100</code></li>
	<li><code>a + b + c &gt; 0</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 优先队列

贪心，优先选择剩余最多的字符，通过优先队列或排序，确保每次选到的字符都是剩余最多的（为了避免出现连续 3 个一样的字符，一些情况需要选择剩余第二多的字符）。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestDiverseString(self, a: int, b: int, c: int) -> str:
        h = []
        if a > 0:
            heappush(h, [-a, 'a'])
        if b > 0:
            heappush(h, [-b, 'b'])
        if c > 0:
            heappush(h, [-c, 'c'])

        ans = []
        while len(h) > 0:
            cur = heappop(h)
            if len(ans) >= 2 and ans[-1] == cur[1] and ans[-2] == cur[1]:
                if len(h) == 0:
                    break
                nxt = heappop(h)
                ans.append(nxt[1])
                if -nxt[0] > 1:
                    nxt[0] += 1
                    heappush(h, nxt)
                heappush(h, cur)
            else:
                ans.append(cur[1])
                if -cur[0] > 1:
                    cur[0] += 1
                    heappush(h, cur)

        return ''.join(ans)
```

#### Java

```java
class Solution {
    public String longestDiverseString(int a, int b, int c) {
        Queue<int[]> pq = new PriorityQueue<>((x, y) -> y[1] - x[1]);
        if (a > 0) {
            pq.offer(new int[] {'a', a});
        }
        if (b > 0) {
            pq.offer(new int[] {'b', b});
        }
        if (c > 0) {
            pq.offer(new int[] {'c', c});
        }

        StringBuilder sb = new StringBuilder();
        while (pq.size() > 0) {
            int[] cur = pq.poll();
            int n = sb.length();
            if (n >= 2 && sb.codePointAt(n - 1) == cur[0] && sb.codePointAt(n - 2) == cur[0]) {
                if (pq.size() == 0) {
                    break;
                }
                int[] next = pq.poll();
                sb.append((char) next[0]);
                if (next[1] > 1) {
                    next[1]--;
                    pq.offer(next);
                }
                pq.offer(cur);
            } else {
                sb.append((char) cur[0]);
                if (cur[1] > 1) {
                    cur[1]--;
                    pq.offer(cur);
                }
            }
        }

        return sb.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string longestDiverseString(int a, int b, int c) {
        using pci = pair<char, int>;
        auto cmp = [](pci x, pci y) { return x.second < y.second; };
        priority_queue<pci, vector<pci>, decltype(cmp)> pq(cmp);

        if (a > 0) pq.push({'a', a});
        if (b > 0) pq.push({'b', b});
        if (c > 0) pq.push({'c', c});

        string ans;
        while (!pq.empty()) {
            pci cur = pq.top();
            pq.pop();
            int n = ans.size();
            if (n >= 2 && ans[n - 1] == cur.first && ans[n - 2] == cur.first) {
                if (pq.empty()) break;
                pci nxt = pq.top();
                pq.pop();
                ans.push_back(nxt.first);
                if (--nxt.second > 0) {
                    pq.push(nxt);
                }
                pq.push(cur);
            } else {
                ans.push_back(cur.first);
                if (--cur.second > 0) {
                    pq.push(cur);
                }
            }
        }

        return ans;
    }
};
```

#### Go

```go
type pair struct {
	c   byte
	num int
}

type hp []pair

func (a hp) Len() int           { return len(a) }
func (a hp) Swap(i, j int)      { a[i], a[j] = a[j], a[i] }
func (a hp) Less(i, j int) bool { return a[i].num > a[j].num }
func (a *hp) Push(x any)        { *a = append(*a, x.(pair)) }
func (a *hp) Pop() any          { l := len(*a); t := (*a)[l-1]; *a = (*a)[:l-1]; return t }

func longestDiverseString(a int, b int, c int) string {
	var h hp
	if a > 0 {
		heap.Push(&h, pair{'a', a})
	}
	if b > 0 {
		heap.Push(&h, pair{'b', b})
	}
	if c > 0 {
		heap.Push(&h, pair{'c', c})
	}

	var ans []byte
	for len(h) > 0 {
		cur := heap.Pop(&h).(pair)
		if len(ans) >= 2 && ans[len(ans)-1] == cur.c && ans[len(ans)-2] == cur.c {
			if len(h) == 0 {
				break
			}
			next := heap.Pop(&h).(pair)
			ans = append(ans, next.c)
			if next.num > 1 {
				next.num--
				heap.Push(&h, next)
			}
			heap.Push(&h, cur)
		} else {
			ans = append(ans, cur.c)
			if cur.num > 1 {
				cur.num--
				heap.Push(&h, cur)
			}
		}
	}

	return string(ans)
}
```

#### TypeScript

```ts
function longestDiverseString(a: number, b: number, c: number): string {
    let ans = [];
    let store: Array<[string, number]> = [
        ['a', a],
        ['b', b],
        ['c', c],
    ];
    while (true) {
        store.sort((a, b) => b[1] - a[1]);
        let hasNext = false;
        for (let [i, [ch, ctn]] of store.entries()) {
            if (ctn < 1) {
                break;
            }
            const n = ans.length;
            if (n >= 2 && ans[n - 1] == ch && ans[n - 2] == ch) {
                continue;
            }
            hasNext = true;
            ans.push(ch);
            store[i][1] -= 1;
            break;
        }
        if (!hasNext) {
            break;
        }
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：贪心 + 优先队列（另一种写法）

<!-- tabs:start -->

#### TypeScript

```ts
function longestDiverseString(a: number, b: number, c: number): string {
    let res = '';
    let prev = { ch: '', c: 0 };
    let last = { ch: '', c: 0 };
    const pq = new MaxPriorityQueue({ priority: ({ c }) => c });

    pq.enqueue({ ch: 'a', c: a });
    pq.enqueue({ ch: 'b', c: b });
    pq.enqueue({ ch: 'c', c });

    while (pq.size()) {
        const item = pq.dequeue().element;
        let c = item.c < prev.c ? 1 : 2;

        if (prev.c) pq.enqueue(prev);
        if (last.ch !== item.ch && item.c) last = { ...item, c: 0 };

        while (c-- && item.c && last.c++ < 2) {
            item.c--;
            res += item.ch;
        }
        prev = item;
    }

    return res;
}
```

#### JavaScript

```js
function longestDiverseString(a, b, c) {
    let res = '';
    let prev = { ch: '', c: 0 };
    let last = { ch: '', c: 0 };
    const pq = new MaxPriorityQueue({ priority: ({ c }) => c });

    pq.enqueue({ ch: 'a', c: a });
    pq.enqueue({ ch: 'b', c: b });
    pq.enqueue({ ch: 'c', c });

    while (pq.size()) {
        const item = pq.dequeue().element;
        let c = item.c < prev.c ? 1 : 2;

        if (prev.c) pq.enqueue(prev);
        if (last.ch !== item.ch && item.c) last = { ...item, c: 0 };

        while (c-- && item.c && last.c++ < 2) {
            item.c--;
            res += item.ch;
        }
        prev = item;
    }

    return res;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
