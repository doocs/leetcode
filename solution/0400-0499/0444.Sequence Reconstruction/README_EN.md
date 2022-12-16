# [444. Sequence Reconstruction](https://leetcode.com/problems/sequence-reconstruction)

[中文文档](/solution/0400-0499/0444.Sequence%20Reconstruction/README.md)

## Description

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

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sequenceReconstruction(
        self, nums: List[int], sequences: List[List[int]]
    ) -> bool:
        g = defaultdict(list)
        indeg = [0] * len(nums)
        for seq in sequences:
            for a, b in pairwise(seq):
                g[a - 1].append(b - 1)
                indeg[b - 1] += 1
        q = deque(i for i, v in enumerate(indeg) if v == 0)
        while q:
            if len(q) > 1:
                return False
            i = q.popleft()
            for j in g[i]:
                indeg[j] -= 1
                if indeg[j] == 0:
                    q.append(j)
        return True
```

### **Java**

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
                indeg[b]++;
            }
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            if (q.size() > 1) {
                return false;
            }
            int i = q.poll();
            for (int j : g[i]) {
                if (--indeg[j] == 0) {
                    q.offer(j);
                }
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool sequenceReconstruction(vector<int>& nums, vector<vector<int>>& sequences) {
        int n = nums.size();
        vector<vector<int>> g(n);
        vector<int> indeg(n);
        for (auto& seq : sequences) {
            for (int i = 1; i < seq.size(); ++i) {
                int a = seq[i - 1] - 1, b = seq[i] - 1;
                g[a].push_back(b);
                ++indeg[b];
            }
        }
        queue<int> q;
        for (int i = 0; i < n; ++i)
            if (indeg[i] == 0) q.push(i);
        while (!q.empty()) {
            if (q.size() > 1) return false;
            int i = q.front();
            q.pop();
            for (int j : g[i])
                if (--indeg[j] == 0) q.push(j);
        }
        return true;
    }
};
```

### **Go**

```go
func sequenceReconstruction(nums []int, sequences [][]int) bool {
	n := len(nums)
	g := make([][]int, n)
	indeg := make([]int, n)
	for _, seq := range sequences {
		for i := 1; i < len(seq); i++ {
			a, b := seq[i-1]-1, seq[i]-1
			g[a] = append(g[a], b)
			indeg[b]++
		}
	}
	q := []int{}
	for i, v := range indeg {
		if v == 0 {
			q = append(q, i)
		}
	}
	for len(q) > 0 {
		if len(q) > 1 {
			return false
		}
		i := q[0]
		q = q[1:]
		for _, j := range g[i] {
			indeg[j]--
			if indeg[j] == 0 {
				q = append(q, j)
			}
		}
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
