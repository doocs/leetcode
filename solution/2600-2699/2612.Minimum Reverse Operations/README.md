# [2612. 最少翻转操作数](https://leetcode.cn/problems/minimum-reverse-operations)

[English Version](/solution/2600-2699/2612.Minimum%20Reverse%20Operations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;和一个在范围 <code>[0, n - 1]</code>&nbsp;以内的整数&nbsp;<code>p</code>&nbsp;，它们表示一个长度为 <code>n</code> 且下标从 <strong>0</strong>&nbsp;开始的数组&nbsp;<code>arr</code>&nbsp;，数组中除了下标为&nbsp;<code>p</code>&nbsp;处是 <code>1</code>&nbsp;以外，其他所有数都是 <code>0</code>&nbsp;。</p>

<p>同时给你一个整数数组&nbsp;<code>banned</code>&nbsp;，它包含数组中的一些位置。<code>banned</code>&nbsp;中第&nbsp;<strong>i</strong>&nbsp;个位置表示&nbsp;<code>arr[banned[i]] = 0</code>&nbsp;，题目保证&nbsp;<code>banned[i] != p</code>&nbsp;。</p>

<p>你可以对 <code>arr</code>&nbsp;进行 <strong>若干次</strong>&nbsp;操作。一次操作中，你选择大小为 <code>k</code>&nbsp;的一个 <strong>子数组</strong>&nbsp;，并将它 <b>翻转</b>&nbsp;。在任何一次翻转操作后，你都需要确保 <code>arr</code>&nbsp;中唯一的 <code>1</code>&nbsp;不会到达任何 <code>banned</code>&nbsp;中的位置。换句话说，<code>arr[banned[i]]</code>&nbsp;始终&nbsp;<strong>保持</strong>&nbsp;<code>0</code>&nbsp;。</p>

<p>请你返回一个数组&nbsp;<code>ans</code>&nbsp;，对于<em>&nbsp;</em><code>[0, n - 1]</code>&nbsp;之间的任意下标&nbsp;<code>i</code>&nbsp;，<code>ans[i]</code>&nbsp;是将&nbsp;<code>1</code>&nbsp;放到位置&nbsp;<code>i</code>&nbsp;处的&nbsp;<strong>最少</strong>&nbsp;翻转操作次数，如果无法放到位置&nbsp;<code>i</code>&nbsp;处，此数为&nbsp;<code>-1</code>&nbsp;。</p>

<ul>
	<li><strong>子数组</strong>&nbsp;指的是一个数组里一段连续 <strong>非空</strong>&nbsp;的元素序列。</li>
	<li>对于所有的 <code>i</code>&nbsp;，<code>ans[i]</code>&nbsp;相互之间独立计算。</li>
	<li>将一个数组中的元素 <strong>翻转</strong> 指的是将数组中的值变成 <strong>相反顺序</strong>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>n = 4, p = 0, banned = [1,2], k = 4
<b>输出：</b>[0,-1,-1,1]
<b>解释：</b><code>k = 4，所以只有一种可行的翻转操作，就是将整个数组翻转。一开始 </code>1<strong> </strong>在位置 0 处，所以将它翻转到位置 0 处需要的操作数为 0 。
我们不能将 1 翻转到 banned 中的位置，所以位置 1 和 2 处的答案都是 -1 。
通过一次翻转操作，可以将 1 放到位置 3 处，所以位置 3 的答案是 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 5, p = 0, banned = [2,4], k = 3
<b>输出：</b>[0,-1,-1,-1,-1]
<b>解释：</b>这个例子中 1 一开始在位置 0 处，所以此下标的答案为 0 。
翻转的子数组长度为 k = 3 ，1 此时在位置 0 处，所以我们可以翻转子数组 [0, 2]，但翻转后的下标 2 在 banned 中，所以不能执行此操作。
由于 1 没法离开位置 0 ，所以其他位置的答案都是 -1 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>n = 4, p = 2, banned = [0,1,3], k = 1
<b>输出：</b>[-1,-1,0,-1]
<b>解释：</b>这个例子中，我们只能对长度为 1 的子数组执行翻转操作，所以 1 无法离开初始位置。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= p &lt;= n - 1</code></li>
	<li><code>0 &lt;= banned.length &lt;= n - 1</code></li>
	<li><code>0 &lt;= banned[i] &lt;= n - 1</code></li>
	<li><code>1 &lt;= k &lt;= n&nbsp;</code></li>
	<li><code>banned[i] != p</code></li>
	<li><code>banned</code>&nbsp;中的值 <strong>互不相同</strong>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：有序集合 + BFS**

我们注意到，对于一个子数组区间 $[l,..r]$ 中的任意一个下标 $i$，翻转后的下标 $j = l + r - i$。

如果子数组向右移动一个位置，那么 $j = l + 1 + r + 1 - i = l + r - i + 2$，即 $j$ 会增加 $2$。

同理，如果子数组向左移动一个位置，那么 $j = l - 1 + r - 1 - i = l + r - i - 2$，即 $j$ 会减少 $2$。

因此，对于一个特定的下标 $i$，其翻转后的所有位置构成了一个公差为 $2$ 的等差数列，也即是说，翻转后的所有下标，奇偶性都是相同的。

接下来，我们考虑下标 $i$ 翻转后的位置 $j$ 的取值范围。

-   如果不考虑边界的情况，那么 $j$ 的取值范围为 $[i - k + 1, i + k - 1]$。
-   如果子数组在最左边，那么 $[l, r] = [0, k - 1]$，因此 $i$ 翻转后的下标 $j = 0 + k - 1 - i$，即 $j = k - i - 1$，因此 $j$ 的左边界 $mi = max(i - k + 1, k - i - 1)$。
-   如果子数组在最右边，那么 $[l, r] = [n - k, n - 1]$，因此 $i$ 翻转后的下标 $j= n - k + n - 1 - i$，即 $j = n \times 2 - k - i - 1$，因此 $j$ 的右边界 $mx = min(i + k - 1, n \times 2 - k - i - 1)$。

我们用两个有序集合分别存储所有待搜索的奇数下标和偶数下标，这里需要排除数组 $banned$ 中的下标，以及下标 $p$。

接下来，我们使用 BFS 搜索，每次搜索当前下标 $i$ 所有翻转后的下标 $j$，即 $j = mi, mi + 2, mi + 4, \dots, mx$，更新下标 $j$ 的答案，并将下标 $j$ 加入到待搜索的队列中，同时将下标 $j$ 从对应的有序集合中移除。

当搜索结束时，即可得到所有下标的答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 题目中给定的数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
from sortedcontainers import SortedSet


class Solution:
    def minReverseOperations(
        self, n: int, p: int, banned: List[int], k: int
    ) -> List[int]:
        ans = [-1] * n
        ans[p] = 0
        ts = [SortedSet() for _ in range(2)]
        for i in range(n):
            ts[i % 2].add(i)
        ts[p % 2].remove(p)
        for i in banned:
            ts[i % 2].remove(i)
        ts[0].add(n)
        ts[1].add(n)
        q = deque([p])
        while q:
            i = q.popleft()
            mi = max(i - k + 1, k - i - 1)
            mx = min(i + k - 1, n * 2 - k - i - 1)
            s = ts[mi % 2]
            j = s.bisect_left(mi)
            while s[j] <= mx:
                q.append(s[j])
                ans[s[j]] = ans[i] + 1
                s.remove(s[j])
                j = s.bisect_left(mi)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        int[] ans = new int[n];
        TreeSet<Integer>[] ts = new TreeSet[] {new TreeSet<>(), new TreeSet<>()};
        for (int i = 0; i < n; ++i) {
            ts[i % 2].add(i);
            ans[i] = i == p ? 0 : -1;
        }
        ts[p % 2].remove(p);
        for (int i : banned) {
            ts[i % 2].remove(i);
        }
        ts[0].add(n);
        ts[1].add(n);
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(p);
        while (!q.isEmpty()) {
            int i = q.poll();
            int mi = Math.max(i - k + 1, k - i - 1);
            int mx = Math.min(i + k - 1, n * 2 - k - i - 1);
            var s = ts[mi % 2];
            for (int j = s.ceiling(mi); j <= mx; j = s.ceiling(mi)) {
                q.offer(j);
                ans[j] = ans[i] + 1;
                s.remove(j);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> minReverseOperations(int n, int p, vector<int>& banned, int k) {
        vector<int> ans(n, -1);
        ans[p] = 0;
        set<int> ts[2];
        for (int i = 0; i < n; ++i) {
            ts[i % 2].insert(i);
        }
        ts[p % 2].erase(p);
        for (int i : banned) {
            ts[i % 2].erase(i);
        }
        ts[0].insert(n);
        ts[1].insert(n);
        queue<int> q{{p}};
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            int mi = max(i - k + 1, k - i - 1);
            int mx = min(i + k - 1, n * 2 - k - i - 1);
            auto& s = ts[mi % 2];
            auto it = s.lower_bound(mi);
            while (*it <= mx) {
                int j = *it;
                ans[j] = ans[i] + 1;
                q.push(j);
                it = s.erase(it);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minReverseOperations(n int, p int, banned []int, k int) []int {
	ans := make([]int, n)
	ts := [2]*redblacktree.Tree{redblacktree.NewWithIntComparator(), redblacktree.NewWithIntComparator()}
	for i := 0; i < n; i++ {
		ts[i%2].Put(i, struct{}{})
		ans[i] = -1
	}
	ans[p] = 0
	ts[p%2].Remove(p)
	for _, i := range banned {
		ts[i%2].Remove(i)
	}
	ts[0].Put(n, struct{}{})
	ts[1].Put(n, struct{}{})
	q := []int{p}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		mi := max(i-k+1, k-i-1)
		mx := min(i+k-1, n*2-k-i-1)
		s := ts[mi%2]
		for x, _ := s.Ceiling(mi); x.Key.(int) <= mx; x, _ = s.Ceiling(mi) {
			j := x.Key.(int)
			s.Remove(j)
			ans[j] = ans[i] + 1
			q = append(q, j)
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
