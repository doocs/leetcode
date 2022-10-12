# [1405. Longest Happy String](https://leetcode.com/problems/longest-happy-string)

[中文文档](/solution/1400-1499/1405.Longest%20Happy%20String/README.md)

## Description

<p>A string <code>s</code> is called <strong>happy</strong> if it satisfies the following conditions:</p>

<ul>
	<li><code>s</code> only contains the letters <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code>.</li>
	<li><code>s</code> does not contain any of <code>&quot;aaa&quot;</code>, <code>&quot;bbb&quot;</code>, or <code>&quot;ccc&quot;</code> as a substring.</li>
	<li><code>s</code> contains <strong>at most</strong> <code>a</code> occurrences of the letter <code>&#39;a&#39;</code>.</li>
	<li><code>s</code> contains <strong>at most</strong> <code>b</code> occurrences of the letter <code>&#39;b&#39;</code>.</li>
	<li><code>s</code> contains <strong>at most</strong> <code>c</code> occurrences of the letter <code>&#39;c&#39;</code>.</li>
</ul>

<p>Given three integers <code>a</code>, <code>b</code>, and <code>c</code>, return <em>the <strong>longest possible happy </strong>string</em>. If there are multiple longest happy strings, return <em>any of them</em>. If there is no such string, return <em>the empty string </em><code>&quot;&quot;</code>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = 1, b = 1, c = 7
<strong>Output:</strong> &quot;ccaccbcc&quot;
<strong>Explanation:</strong> &quot;ccbccacc&quot; would also be a correct answer.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = 7, b = 1, c = 0
<strong>Output:</strong> &quot;aabaa&quot;
<strong>Explanation:</strong> It is the only correct answer in this case.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= a, b, c &lt;= 100</code></li>
	<li><code>a + b + c &gt; 0</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **TypeScript**

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

### **Go**

```go
type pair struct {
	c   byte
	num int
}

type hp []pair

func (a hp) Len() int            { return len(a) }
func (a hp) Swap(i, j int)       { a[i], a[j] = a[j], a[i] }
func (a hp) Less(i, j int) bool  { return a[i].num > a[j].num }
func (a *hp) Push(x interface{}) { *a = append(*a, x.(pair)) }
func (a *hp) Pop() interface{}   { l := len(*a); t := (*a)[l-1]; *a = (*a)[:l-1]; return t }

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

### **C++**

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

### **...**

```

```

<!-- tabs:end -->
