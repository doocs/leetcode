---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1722.Minimize%20Hamming%20Distance%20After%20Swap%20Operations/README.md
rating: 1892
source: 第 223 场周赛 Q3
tags:
    - 深度优先搜索
    - 并查集
    - 数组
---

<!-- problem:start -->

# [1722. 执行交换操作后的最小汉明距离](https://leetcode.cn/problems/minimize-hamming-distance-after-swap-operations)

[English Version](/solution/1700-1799/1722.Minimize%20Hamming%20Distance%20After%20Swap%20Operations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数数组 <code>source</code> 和 <code>target</code> ，长度都是 <code>n</code> 。还有一个数组 <code>allowedSwaps</code> ，其中每个 <code>allowedSwaps[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 表示你可以交换数组 <code>source</code> 中下标为 <code>a<sub>i</sub></code> 和 <code>b<sub>i</sub></code>（<strong>下标从 0 开始</strong>）的两个元素。注意，你可以按 <strong>任意</strong> 顺序 <strong>多次</strong> 交换一对特定下标指向的元素。</p>

<p>相同长度的两个数组 <code>source</code> 和 <code>target</code> 间的 <strong>汉明距离</strong> 是元素不同的下标数量。形式上，其值等于满足 <code>source[i] != target[i]</code> （<strong>下标从 0 开始</strong>）的下标 <code>i</code>（<code>0 &lt;= i &lt;= n-1</code>）的数量。</p>

<p>在对数组 <code>source</code> 执行 <strong>任意</strong> 数量的交换操作后，返回 <code>source</code> 和 <code>target</code> 间的 <strong>最小汉明距离</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>source = [1,2,3,4], target = [2,1,4,5], allowedSwaps = [[0,1],[2,3]]
<strong>输出：</strong>1
<strong>解释：</strong>source 可以按下述方式转换：
- 交换下标 0 和 1 指向的元素：source = [<strong>2</strong>,<strong>1</strong>,3,4]
- 交换下标 2 和 3 指向的元素：source = [2,1,<strong>4</strong>,<strong>3</strong>]
source 和 target 间的汉明距离是 1 ，二者有 1 处元素不同，在下标 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>source = [1,2,3,4], target = [1,3,2,4], allowedSwaps = []
<strong>输出：</strong>2
<strong>解释：</strong>不能对 source 执行交换操作。
source 和 target 间的汉明距离是 2 ，二者有 2 处元素不同，在下标 1 和下标 2 。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>source = [5,1,2,4,3], target = [1,5,4,2,3], allowedSwaps = [[0,4],[4,2],[1,3],[1,4]]
<strong>输出：</strong>0
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == source.length == target.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= source[i], target[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= allowedSwaps.length &lt;= 10<sup>5</sup></code></li>
	<li><code>allowedSwaps[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：并查集 + 哈希表

我们可以将每个下标看作一个节点，每个下标对应的元素看作节点的值，那么给定的 `allowedSwaps` 中的每个元素 `[a_i, b_i]` 就表示下标 `a_i` 和 `b_i` 之间存在一条边。因此，我们可以使用并查集来维护这些连通分量。

在得到每个连通分量之后，我们再用二维哈希表 $cnt$ 分别统计每个连通分量中每个元素出现的次数，最后对于数组 `target` 中的每个元素，如果其在对应的连通分量中出现的次数大于 0，则将其出现次数减 1，否则将答案加 1。

时间复杂度 $O(n \times \log n)$ 或 $O(n \times \alpha(n))$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度，而 $\alpha$ 是阿克曼函数的反函数。

<!-- tabs:start -->

```python
class Solution:
    def minimumHammingDistance(
        self, source: List[int], target: List[int], allowedSwaps: List[List[int]]
    ) -> int:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        n = len(source)
        p = list(range(n))
        for a, b in allowedSwaps:
            p[find(a)] = find(b)
        cnt = defaultdict(Counter)
        for i, x in enumerate(source):
            j = find(i)
            cnt[j][x] += 1
        ans = 0
        for i, x in enumerate(target):
            j = find(i)
            cnt[j][x] -= 1
            ans += cnt[j][x] < 0
        return ans
```

```java
class Solution {
    private int[] p;

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        for (int[] a : allowedSwaps) {
            p[find(a[0])] = find(a[1]);
        }
        Map<Integer, Map<Integer, Integer>> cnt = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int j = find(i);
            cnt.computeIfAbsent(j, k -> new HashMap<>()).merge(source[i], 1, Integer::sum);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int j = find(i);
            Map<Integer, Integer> t = cnt.get(j);
            if (t.merge(target[i], -1, Integer::sum) < 0) {
                ++ans;
            }
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

```cpp
class Solution {
public:
    int minimumHammingDistance(vector<int>& source, vector<int>& target, vector<vector<int>>& allowedSwaps) {
        int n = source.size();
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        function<int(int)> find = [&](int x) {
            return x == p[x] ? x : p[x] = find(p[x]);
        };
        for (auto& a : allowedSwaps) {
            p[find(a[0])] = find(a[1]);
        }
        unordered_map<int, unordered_map<int, int>> cnt;
        for (int i = 0; i < n; ++i) {
            ++cnt[find(i)][source[i]];
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (--cnt[find(i)][target[i]] < 0) {
                ++ans;
            }
        }
        return ans;
    }
};
```

```go
func minimumHammingDistance(source []int, target []int, allowedSwaps [][]int) (ans int) {
	n := len(source)
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, a := range allowedSwaps {
		p[find(a[0])] = find(a[1])
	}
	cnt := map[int]map[int]int{}
	for i, x := range source {
		j := find(i)
		if cnt[j] == nil {
			cnt[j] = map[int]int{}
		}
		cnt[j][x]++
	}
	for i, x := range target {
		j := find(i)
		cnt[j][x]--
		if cnt[j][x] < 0 {
			ans++
		}
	}
	return
}
```

```ts
function minimumHammingDistance(
    source: number[],
    target: number[],
    allowedSwaps: number[][],
): number {
    const n = source.length;
    const p: number[] = Array.from({ length: n }, (_, i) => i);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    for (const [a, b] of allowedSwaps) {
        p[find(a)] = find(b);
    }
    const cnt: Map<number, Map<number, number>> = new Map();
    for (let i = 0; i < n; ++i) {
        const j = find(i);
        if (!cnt.has(j)) {
            cnt.set(j, new Map());
        }
        const m = cnt.get(j)!;
        m.set(source[i], (m.get(source[i]) ?? 0) + 1);
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        const j = find(i);
        const m = cnt.get(j)!;
        m.set(target[i], (m.get(target[i]) ?? 0) - 1);
        if (m.get(target[i])! < 0) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
