---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3081.Replace%20Question%20Marks%20in%20String%20to%20Minimize%20Its%20Value/README_EN.md
rating: 1904
tags:
    - Greedy
    - Hash Table
    - String
    - Counting
    - Sorting
    - Heap (Priority Queue)
---

# [3081. Replace Question Marks in String to Minimize Its Value](https://leetcode.com/problems/replace-question-marks-in-string-to-minimize-its-value)

[中文文档](/solution/3000-3099/3081.Replace%20Question%20Marks%20in%20String%20to%20Minimize%20Its%20Value/README.md)

## Description

<p>You are given a string <code>s</code>. <code>s[i]</code> is either a lowercase English letter or <code>&#39;?&#39;</code>.</p>

<p>For a string <code>t</code> having length <code>m</code> containing <strong>only</strong> lowercase English letters, we define the function <code>cost(i)</code> for an index <code>i</code>&nbsp;as the number of characters <strong>equal</strong> to <code>t[i]</code>&nbsp;that appeared before it, i.e. in the range <code>[0, i - 1]</code>.</p>

<p>The <strong>value</strong> of <code>t</code> is the <strong>sum</strong> of <code>cost(i)</code> for all indices <code>i</code>.</p>

<p>For example, for the string <code>t = &quot;aab&quot;</code>:</p>

<ul>
	<li><code>cost(0) = 0</code></li>
	<li><code>cost(1) = 1</code></li>
	<li><code>cost(2) = 0</code></li>
	<li>Hence, the value of <code>&quot;aab&quot;</code> is <code>0 + 1 + 0 = 1</code>.</li>
</ul>

<p>Your task is to <strong>replace all</strong> occurrences of <code>&#39;?&#39;</code> in <code>s</code> with any lowercase English letter so that the <strong>value</strong> of <code>s</code> is <strong>minimized</strong>.</p>

<p>Return <em>a string denoting the modified string with replaced occurrences of </em><code>&#39;?&#39;</code><em>. If there are multiple strings resulting in the <strong>minimum value</strong>, return the <span data-keyword="lexicographically-smaller-string">lexicographically smallest</span> one.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> s = &quot;???&quot; </span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> &quot;abc&quot; </span></p>

<p><strong>Explanation: </strong> In this example, we can replace the occurrences of <code>&#39;?&#39;</code> to make <code>s</code> equal to <code>&quot;abc&quot;</code>.</p>

<p>For <code>&quot;abc&quot;</code>, <code>cost(0) = 0</code>, <code>cost(1) = 0</code>, and <code>cost(2) = 0</code>.</p>

<p>The value of <code>&quot;abc&quot;</code> is <code>0</code>.</p>

<p>Some other modifications of <code>s</code> that have a value of <code>0</code> are <code>&quot;cba&quot;</code>, <code>&quot;abz&quot;</code>, and, <code>&quot;hey&quot;</code>.</p>

<p>Among all of them, we choose the lexicographically smallest.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">s = &quot;a?a?&quot;</span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">&quot;abac&quot;</span></p>

<p><strong>Explanation: </strong> In this example, the occurrences of <code>&#39;?&#39;</code> can be replaced to make <code>s</code> equal to <code>&quot;abac&quot;</code>.</p>

<p>For <code>&quot;abac&quot;</code>, <code>cost(0) = 0</code>, <code>cost(1) = 0</code>, <code>cost(2) = 1</code>, and <code>cost(3) = 0</code>.</p>

<p>The value of <code>&quot;abac&quot;</code> is&nbsp;<code>1</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either a lowercase English letter or <code>&#39;?&#39;</code>.</li>
</ul>

## Solutions

### Solution 1: Greedy + Priority Queue

According to the problem, we can find that if a letter $c$ appears $v$ times, then the score it contributes to the answer is $1 + 2 + \cdots + (v - 1) = \frac{v \times (v - 1)}{2}$. To make the answer as small as possible, we should replace the question marks with those letters that appear less frequently.

Therefore, we can use a priority queue to maintain the occurrence times of each letter, take out the letter with the least occurrence times each time, record it in the array $t$, then increase its occurrence times by one, and put it back into the priority queue. Finally, we sort the array $t$, and then traverse the string $s$, replacing each question mark with the letters in the array $t$ in turn.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the length of the string $s$.

<!-- tabs:start -->

```python
class Solution:
    def minimizeStringValue(self, s: str) -> str:
        cnt = Counter(s)
        pq = [(cnt[c], c) for c in ascii_lowercase]
        heapify(pq)
        t = []
        for _ in range(s.count("?")):
            v, c = pq[0]
            t.append(c)
            heapreplace(pq, (v + 1, c))
        t.sort()
        cs = list(s)
        j = 0
        for i, c in enumerate(s):
            if c == "?":
                cs[i] = t[j]
                j += 1
        return "".join(cs)
```

```java
class Solution {
    public String minimizeStringValue(String s) {
        int[] cnt = new int[26];
        int n = s.length();
        int k = 0;
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (c == '?') {
                ++k;
            } else {
                ++cnt[c - 'a'];
            }
        }
        PriorityQueue<int[]> pq
            = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for (int i = 0; i < 26; ++i) {
            pq.offer(new int[] {cnt[i], i});
        }
        int[] t = new int[k];
        for (int j = 0; j < k; ++j) {
            int[] p = pq.poll();
            t[j] = p[1];
            pq.offer(new int[] {p[0] + 1, p[1]});
        }
        Arrays.sort(t);

        for (int i = 0, j = 0; i < n; ++i) {
            if (cs[i] == '?') {
                cs[i] = (char) (t[j++] + 'a');
            }
        }
        return new String(cs);
    }
}
```

```cpp
class Solution {
public:
    string minimizeStringValue(string s) {
        int cnt[26]{};
        int k = 0;
        for (char& c : s) {
            if (c == '?') {
                ++k;
            } else {
                ++cnt[c - 'a'];
            }
        }
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;
        for (int i = 0; i < 26; ++i) {
            pq.push({cnt[i], i});
        }
        vector<int> t(k);
        for (int i = 0; i < k; ++i) {
            auto [v, c] = pq.top();
            pq.pop();
            t[i] = c;
            pq.push({v + 1, c});
        }
        sort(t.begin(), t.end());
        int j = 0;
        for (char& c : s) {
            if (c == '?') {
                c = t[j++] + 'a';
            }
        }
        return s;
    }
};
```

```go
func minimizeStringValue(s string) string {
	cnt := [26]int{}
	k := 0
	for _, c := range s {
		if c == '?' {
			k++
		} else {
			cnt[c-'a']++
		}
	}
	pq := hp{}
	for i, c := range cnt {
		heap.Push(&pq, pair{c, i})
	}
	t := make([]int, k)
	for i := 0; i < k; i++ {
		p := heap.Pop(&pq).(pair)
		t[i] = p.c
		p.v++
		heap.Push(&pq, p)
	}
	sort.Ints(t)
	cs := []byte(s)
	j := 0
	for i, c := range cs {
		if c == '?' {
			cs[i] = byte(t[j] + 'a')
			j++
		}
	}
	return string(cs)
}

type pair struct{ v, c int }
type hp []pair

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].v < h[j].v || h[i].v == h[j].v && h[i].c < h[j].c }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(pair)) }
func (h *hp) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

<!-- tabs:end -->

<!-- end -->
