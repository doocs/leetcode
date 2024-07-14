---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0444.Sequence%20Reconstruction/README_EN.md
tags:
    - Graph
    - Topological Sort
    - Array
---

<!-- problem:start -->

# [444. Sequence Reconstruction 🔒](https://leetcode.com/problems/sequence-reconstruction)

[中文文档](/solution/0400-0499/0444.Sequence%20Reconstruction/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> where <code>nums</code> is a permutation of the integers in the range <code>[1, n]</code>. You are also given a 2D integer array <code>sequences</code> where <code>sequences[i]</code> is a subsequence of <code>nums</code>.</p>

<p>Check if <code>nums</code> is the shortest possible and the only <strong>supersequence</strong>. The shortest <strong>supersequence</strong> is a sequence <strong>with the shortest length</strong> and has all <code>sequences[i]</code> as subsequences. There could be multiple valid <strong>supersequences</strong> for the given array <code>sequences</code>.</p>

<ul>
	<li>For example, for <code>sequences = [[1,2],[1,3]]</code>, there are two shortest <strong>supersequences</strong>, <code>[1,2,3]</code> and <code>[1,3,2]</code>.</li>
	<li>While for <code>sequences = [[1,2],[1,3],[1,2,3]]</code>, the only shortest <strong>supersequence</strong> possible is <code>[1,2,3]</code>. <code>[1,2,3,4]</code> is a possible supersequence but not the shortest.</li>
</ul>

<p>Return <code>true</code><em> if </em><code>nums</code><em> is the only shortest <strong>supersequence</strong> for </em><code>sequences</code><em>, or </em><code>false</code><em> otherwise</em>.</p>

<p>A <strong>subsequence</strong> is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3], sequences = [[1,2],[1,3]]
<strong>Output:</strong> false
<strong>Explanation:</strong> There are two possible supersequences: [1,2,3] and [1,3,2].
The sequence [1,2] is a subsequence of both: [<strong><u>1</u></strong>,<strong><u>2</u></strong>,3] and [<strong><u>1</u></strong>,3,<strong><u>2</u></strong>].
The sequence [1,3] is a subsequence of both: [<strong><u>1</u></strong>,2,<strong><u>3</u></strong>] and [<strong><u>1</u></strong>,<strong><u>3</u></strong>,2].
Since nums is not the only shortest supersequence, we return false.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3], sequences = [[1,2]]
<strong>Output:</strong> false
<strong>Explanation:</strong> The shortest possible supersequence is [1,2].
The sequence [1,2] is a subsequence of it: [<strong><u>1</u></strong>,<strong><u>2</u></strong>].
Since nums is not the shortest supersequence, we return false.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3], sequences = [[1,2],[1,3],[2,3]]
<strong>Output:</strong> true
<strong>Explanation:</strong> The shortest possible supersequence is [1,2,3].
The sequence [1,2] is a subsequence of it: [<strong><u>1</u></strong>,<strong><u>2</u></strong>,3].
The sequence [1,3] is a subsequence of it: [<strong><u>1</u></strong>,2,<strong><u>3</u></strong>].
The sequence [2,3] is a subsequence of it: [1,<strong><u>2</u></strong>,<strong><u>3</u></strong>].
Since nums is the only shortest supersequence, we return true.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code> is a permutation of all the integers in the range <code>[1, n]</code>.</li>
	<li><code>1 &lt;= sequences.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= sequences[i].length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= sum(sequences[i].length) &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= sequences[i][j] &lt;= n</code></li>
	<li>All the arrays of <code>sequences</code> are <strong>unique</strong>.</li>
	<li><code>sequences[i]</code> is a subsequence of <code>nums</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Topological Sorting

We can first traverse each subsequence `seq`. For each pair of adjacent elements $a$ and $b$, we establish a directed edge $a \to b$. At the same time, we count the in-degree of each node, and finally add all nodes with an in-degree of $0$ to the queue.

When the number of nodes in the queue is equal to $1$, we take out the head node $i$, remove $i$ from the graph, and decrease the in-degree of all adjacent nodes of $i$ by $1$. If the in-degree of the adjacent nodes becomes $0$ after decreasing, add these nodes to the queue. Repeat the above operation until the length of the queue is not $1$. At this point, check whether the queue is empty. If it is not empty, it means there are multiple shortest supersequences, return `false`; if it is empty, it means there is only one shortest supersequence, return `true`.

The time complexity is $O(n + m)$, and the space complexity is $O(n + m)$. Where $n$ and $m$ are the number of nodes and edges, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sequenceReconstruction(
        self, nums: List[int], sequences: List[List[int]]
    ) -> bool:
        n = len(nums)
        g = [[] for _ in range(n)]
        indeg = [0] * n
        for seq in sequences:
            for a, b in pairwise(seq):
                a, b = a - 1, b - 1
                g[a].append(b)
                indeg[b] += 1
        q = deque(i for i, x in enumerate(indeg) if x == 0)
        while len(q) == 1:
            i = q.popleft()
            for j in g[i]:
                indeg[j] -= 1
                if indeg[j] == 0:
                    q.append(j)
        return len(q) == 0
```

#### Java

```java
class Solution {
    public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        int n = nums.length;
        int[] indeg = new int[n];
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var seq : sequences) {
            for (int i = 1; i < seq.size(); ++i) {
                int a = seq.get(i - 1) - 1, b = seq.get(i) - 1;
                g[a].add(b);
                ++indeg[b];
            }
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }
        while (q.size() == 1) {
            int i = q.poll();
            for (int j : g[i]) {
                if (--indeg[j] == 0) {
                    q.offer(j);
                }
            }
        }
        return q.isEmpty();
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool sequenceReconstruction(vector<int>& nums, vector<vector<int>>& sequences) {
        int n = nums.size();
        vector<int> indeg(n);
        vector<int> g[n];
        for (auto& seq : sequences) {
            for (int i = 1; i < seq.size(); ++i) {
                int a = seq[i - 1] - 1, b = seq[i] - 1;
                g[a].push_back(b);
                ++indeg[b];
            }
        }
        queue<int> q;
        for (int i = 0; i < n; ++i) {
            if (indeg[i] == 0) {
                q.push(i);
            }
        }
        while (q.size() == 1) {
            int i = q.front();
            q.pop();
            for (int j : g[i]) {
                if (--indeg[j] == 0) {
                    q.push(j);
                }
            }
        }
        return q.empty();
    }
};
```

#### Go

```go
func sequenceReconstruction(nums []int, sequences [][]int) bool {
	n := len(nums)
	indeg := make([]int, n)
	g := make([][]int, n)
	for _, seq := range sequences {
		for i, b := range seq[1:] {
			a := seq[i] - 1
			b -= 1
			g[a] = append(g[a], b)
			indeg[b]++
		}
	}
	q := []int{}
	for i, x := range indeg {
		if x == 0 {
			q = append(q, i)
		}
	}
	for len(q) == 1 {
		i := q[0]
		q = q[1:]
		for _, j := range g[i] {
			indeg[j]--
			if indeg[j] == 0 {
				q = append(q, j)
			}
		}
	}
	return len(q) == 0
}
```

#### TypeScript

```ts
function sequenceReconstruction(nums: number[], sequences: number[][]): boolean {
    const n = nums.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    const indeg: number[] = Array(n).fill(0);
    for (const seq of sequences) {
        for (let i = 1; i < seq.length; ++i) {
            const [a, b] = [seq[i - 1] - 1, seq[i] - 1];
            g[a].push(b);
            ++indeg[b];
        }
    }
    const q: number[] = indeg.map((v, i) => (v === 0 ? i : -1)).filter(v => v !== -1);
    while (q.length === 1) {
        const i = q.pop()!;
        for (const j of g[i]) {
            if (--indeg[j] === 0) {
                q.push(j);
            }
        }
    }
    return q.length === 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
