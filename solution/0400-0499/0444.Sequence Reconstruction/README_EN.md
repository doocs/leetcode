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
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3], sequences = [[1,2],[1,3]]
<strong>Output:</strong> false
<strong>Explanation:</strong> There are two possible supersequences: [1,2,3] and [1,3,2].
The sequence [1,2] is a subsequence of both: [<strong><u>1</u></strong>,<strong><u>2</u></strong>,3] and [<strong><u>1</u></strong>,3,<strong><u>2</u></strong>].
The sequence [1,3] is a subsequence of both: [<strong><u>1</u></strong>,2,<strong><u>3</u></strong>] and [<strong><u>1</u></strong>,<strong><u>3</u></strong>,2].
Since nums is not the only shortest supersequence, we return false.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3], sequences = [[1,2]]
<strong>Output:</strong> false
<strong>Explanation:</strong> The shortest possible supersequence is [1,2].
The sequence [1,2] is a subsequence of it: [<strong><u>1</u></strong>,<strong><u>2</u></strong>].
Since nums is not the shortest supersequence, we return false.
</pre>

<p><strong>Example 3:</strong></p>

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
    def sequenceReconstruction(self, org: List[int], seqs: List[List[int]]) -> bool:
        n = len(org)
        nums = set()
        for seq in seqs:
            for num in seq:
                if num < 1 or num > n:
                    return False
                nums.add(num)
        if len(nums) < n:
            return False

        edges = defaultdict(list)
        indegree = [0] * (n + 1)
        for seq in seqs:
            i = seq[0]
            for j in seq[1:]:
                edges[i].append(j)
                indegree[j] += 1
                i = j
        q = deque()
        for i in range(1, n + 1):
            if indegree[i] == 0:
                q.append(i)
        cnt = 0
        while q:
            if len(q) > 1 or org[cnt] != q[0]:
                return False
            i = q.popleft()
            cnt += 1
            for j in edges[i]:
                indegree[j] -= 1
                if indegree[j] == 0:
                    q.append(j)
        return cnt == n
```

### **Java**

```java
class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        int n = org.length;
        Set<Integer> nums = new HashSet<>();
        for (List<Integer> seq : seqs) {
            for (int num : seq) {
                if (num < 1 || num > n) {
                    return false;
                }
                nums.add(num);
            }
        }
        if (nums.size() < n) {
            return false;
        }
        List<Integer>[] edges = new List[n + 1];
        for (int i = 0; i < edges.length; ++i) {
            edges[i] = new ArrayList<>();
        }
        int[] indegree = new int[n + 1];
        for (List<Integer> seq : seqs) {
            int i = seq.get(0);
            for (int j = 1; j < seq.size(); ++j) {
                edges[i].add(seq.get(j));
                ++indegree[seq.get(j)];
                i = seq.get(j);
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; ++i) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        int cnt = 0;
        while (!q.isEmpty()) {
            if (q.size() > 1 || q.peek() != org[cnt]) {
                return false;
            }
            ++cnt;
            int i = q.poll();
            for (int j : edges[i]) {
                --indegree[j];
                if (indegree[j] == 0) {
                    q.offer(j);
                }
            }
        }
        return cnt == n;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool sequenceReconstruction(vector<int>& org, vector<vector<int>>& seqs) {
        int n = org.size();
        unordered_set<int> nums;
        for (auto& seq : seqs)
        {
            for (int num : seq)
            {
                if (num < 1 || num > n) return false;
                nums.insert(num);
            }
        }
        if (nums.size() < n) return false;
        vector<vector<int>> edges(n + 1);
        vector<int> indegree(n + 1);
        for (auto& seq : seqs)
        {
            int i = seq[0];
            for (int j = 1; j < seq.size(); ++j)
            {
                edges[i].push_back(seq[j]);
                ++indegree[seq[j]];
                i = seq[j];
            }
        }
        queue<int> q;
        for (int i = 1; i <= n; ++i)
        {
            if (indegree[i] == 0) q.push(i);
        }
        int cnt = 0;
        while (!q.empty())
        {
            if (q.size() > 1 || q.front() != org[cnt]) return false;
            ++cnt;
            int i = q.front();
            q.pop();
            for (int j : edges[i])
            {
                --indegree[j];
                if (indegree[j] == 0) q.push(j);
            }
        }
        return cnt == n;
    }
};
```

### **Go**

```go
func sequenceReconstruction(org []int, seqs [][]int) bool {
	n := len(org)
	nums := make(map[int]bool)
	for _, seq := range seqs {
		for _, num := range seq {
			if num < 1 || num > n {
				return false
			}
			nums[num] = true
		}
	}
	if len(nums) < n {
		return false
	}
	edges := make([][]int, n+1)
	indegree := make([]int, n+1)
	for _, seq := range seqs {
		i := seq[0]
		for _, j := range seq[1:] {
			edges[i] = append(edges[i], j)
			indegree[j]++
			i = j
		}
	}
	var q []int
	for i := 1; i <= n; i++ {
		if indegree[i] == 0 {
			q = append(q, i)
		}
	}
	cnt := 0
	for len(q) > 0 {
		if len(q) > 1 || org[cnt] != q[0] {
			return false
		}
		i := q[0]
		q = q[1:]
		cnt++
		for _, j := range edges[i] {
			indegree[j]--
			if indegree[j] == 0 {
				q = append(q, j)
			}
		}
	}
	return cnt == n
}
```

### **...**

```

```

<!-- tabs:end -->
