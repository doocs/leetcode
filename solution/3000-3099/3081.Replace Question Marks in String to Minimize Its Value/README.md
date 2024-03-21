# [3081. 替换字符串中的问号使分数最小](https://leetcode.cn/problems/replace-question-marks-in-string-to-minimize-its-value)

[English Version](/solution/3000-3099/3081.Replace%20Question%20Marks%20in%20String%20to%20Minimize%20Its%20Value/README_EN.md)

<!-- tags:贪心,哈希表,字符串,计数,排序,堆（优先队列） -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;。<code>s[i]</code>&nbsp;要么是小写英文字母，要么是问号&nbsp;<code>'?'</code>&nbsp;。</p>

<p>对于长度为 <code>m</code>&nbsp;且 <strong>只</strong>&nbsp;含有小写英文字母的字符串 <code>t</code>&nbsp;，我们定义函数&nbsp;<code>cost(i)</code>&nbsp;为下标 <code>i</code>&nbsp;之前（也就是范围 <code>[0, i - 1]</code>&nbsp;中）出现过与&nbsp;<code>t[i]</code>&nbsp;<strong>相同</strong>&nbsp;字符出现的次数。</p>

<p>字符串 <code>t</code>&nbsp;的&nbsp;<strong>分数</strong>&nbsp;为所有下标&nbsp;<code>i</code>&nbsp;的&nbsp;<code>cost(i)</code>&nbsp;之 <strong>和</strong>&nbsp;。</p>

<p>比方说，字符串&nbsp;<code>t = "aab"</code>&nbsp;：</p>

<ul>
	<li><code>cost(0) = 0</code></li>
	<li><code>cost(1) = 1</code></li>
	<li><code>cost(2) = 0</code></li>
	<li>所以，字符串&nbsp;<code>"aab"</code>&nbsp;的分数为&nbsp;<code>0 + 1 + 0 = 1</code>&nbsp;。</li>
</ul>

<p>你的任务是用小写英文字母&nbsp;<strong>替换</strong> <code>s</code>&nbsp;中 <strong>所有</strong> 问号，使 <code>s</code>&nbsp;的 <strong>分数</strong><strong>最小&nbsp;</strong>。</p>

<p>请你返回替换所有问号<em>&nbsp;</em><code>'?'</code>&nbsp;之后且分数最小的字符串。如果有多个字符串的&nbsp;<strong>分数最小</strong>&nbsp;，那么返回字典序最小的一个。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">s = "???" </span></p>

<p><strong>输出：</strong>&nbsp;<span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">"abc" </span></p>

<p><strong>解释：</strong>这个例子中，我们将 <code>s</code>&nbsp;中的问号&nbsp;<code>'?'</code>&nbsp;替换得到&nbsp;<code>"abc"</code>&nbsp;。</p>

<p>对于字符串&nbsp;<code>"abc"</code>&nbsp;，<code>cost(0) = 0</code>&nbsp;，<code>cost(1) = 0</code>&nbsp;和&nbsp;<code>cost(2) = 0</code>&nbsp;。</p>

<p><code>"abc"</code>&nbsp;的分数为&nbsp;<code>0</code>&nbsp;。</p>

<p>其他修改 <code>s</code>&nbsp;得到分数 <code>0</code>&nbsp;的字符串为&nbsp;<code>"cba"</code>&nbsp;，<code>"abz"</code>&nbsp;和&nbsp;<code>"hey"</code>&nbsp;。</p>

<p>这些字符串中，我们返回字典序最小的。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">s = "a?a?"</span></p>

<p><strong>输出：</strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">"abac"</span></p>

<p><strong>解释：</strong>这个例子中，我们将&nbsp;<code>s</code>&nbsp;中的问号&nbsp;<code>'?'</code>&nbsp;替换得到&nbsp;<code>"abac"</code>&nbsp;。</p>

<p>对于字符串&nbsp;<code>"abac"</code>&nbsp;，<code>cost(0) = 0</code>&nbsp;，<code>cost(1) = 0</code>&nbsp;，<code>cost(2) = 1</code>&nbsp;和&nbsp;<code>cost(3) = 0</code>&nbsp;。</p>

<p><code>"abac"</code>&nbsp;的分数为&nbsp;<code>1</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code>&nbsp;要么是小写英文字母，要么是&nbsp;<code>'?'</code> 。</li>
</ul>

## 解法

### 方法一：贪心 + 优先队列

根据题目，我们可以发现，如果一个字母 $c$ 出现的次数为 $v$，那么它对答案贡献的分数为 $1 + 2 + \cdots + (v - 1) = \frac{v \times (v - 1)}{2}$。为了使得答案尽可能小，我们应该尽量替换问号为那些出现次数较少的字母。

因此，我们可以使用优先队列（小根堆）来维护每个字母的出现次数，每次取出出现次数最少的字母，将其记录到数组 $t$ 中，然后将其出现次数加一，再放回优先队列中。最后，我们将数组 $t$ 排序，然后遍历字符串 $s$，将每个问号依次替换为数组 $t$ 中的字母即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

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
