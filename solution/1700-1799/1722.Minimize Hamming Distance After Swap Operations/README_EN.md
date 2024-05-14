---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1722.Minimize%20Hamming%20Distance%20After%20Swap%20Operations/README_EN.md
rating: 1892
tags:
    - Depth-First Search
    - Union Find
    - Array
---

# [1722. Minimize Hamming Distance After Swap Operations](https://leetcode.com/problems/minimize-hamming-distance-after-swap-operations)

[中文文档](/solution/1700-1799/1722.Minimize%20Hamming%20Distance%20After%20Swap%20Operations/README.md)

## Description

<p>You are given two integer arrays, <code>source</code> and <code>target</code>, both of length <code>n</code>. You are also given an array <code>allowedSwaps</code> where each <code>allowedSwaps[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that you are allowed to swap the elements at index <code>a<sub>i</sub></code> and index <code>b<sub>i</sub></code> <strong>(0-indexed)</strong> of array <code>source</code>. Note that you can swap elements at a specific pair of indices <strong>multiple</strong> times and in <strong>any</strong> order.</p>

<p>The <strong>Hamming distance</strong> of two arrays of the same length, <code>source</code> and <code>target</code>, is the number of positions where the elements are different. Formally, it is the number of indices <code>i</code> for <code>0 &lt;= i &lt;= n-1</code> where <code>source[i] != target[i]</code> <strong>(0-indexed)</strong>.</p>

<p>Return <em>the <strong>minimum Hamming distance</strong> of </em><code>source</code><em> and </em><code>target</code><em> after performing <strong>any</strong> amount of swap operations on array </em><code>source</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> source = [1,2,3,4], target = [2,1,4,5], allowedSwaps = [[0,1],[2,3]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> source can be transformed the following way:
- Swap indices 0 and 1: source = [<u>2</u>,<u>1</u>,3,4]
- Swap indices 2 and 3: source = [2,1,<u>4</u>,<u>3</u>]
The Hamming distance of source and target is 1 as they differ in 1 position: index 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> source = [1,2,3,4], target = [1,3,2,4], allowedSwaps = []
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are no allowed swaps.
The Hamming distance of source and target is 2 as they differ in 2 positions: index 1 and index 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> source = [5,1,2,4,3], target = [1,5,4,2,3], allowedSwaps = [[0,4],[4,2],[1,3],[1,4]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == source.length == target.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= source[i], target[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= allowedSwaps.length &lt;= 10<sup>5</sup></code></li>
	<li><code>allowedSwaps[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
</ul>

## Solutions

### Solution 1: Union-Find + Hash Table

We can consider each index as a node, and the element corresponding to each index as the value of the node. Then each element `[a_i, b_i]` in the given `allowedSwaps` represents an edge between index `a_i` and `b_i`. Therefore, we can use a union-find set to maintain these connected components.

After obtaining each connected component, we use a two-dimensional hash table $cnt$ to count the number of occurrences of each element in each connected component. Finally, for each element in the array `target`, if its occurrence count in the corresponding connected component is greater than 0, we decrease its count by 1, otherwise, we increase the answer by 1.

The time complexity is $O(n \times \log n)$ or $O(n \times \alpha(n))$, and the space complexity is $O(n)$. Here, $n$ is the length of the array, and $\alpha$ is the inverse Ackermann function.

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

<!-- end -->
