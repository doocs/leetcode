# [444. Sequence Reconstruction](https://leetcode.com/problems/sequence-reconstruction)

[中文文档](/solution/0400-0499/0444.Sequence%20Reconstruction/README.md)

## Description

<p>Check whether the original sequence <code>org</code> can be uniquely reconstructed from the sequences in <code>seqs</code>. The <code>org</code> sequence is a permutation of the integers from 1 to n, with 1 &le; n &le; 10<sup>4</sup>. Reconstruction means building a shortest common supersequence of the sequences in <code>seqs</code> (i.e., a shortest sequence so that all sequences in <code>seqs</code> are subsequences of it). Determine whether there is only one sequence that can be reconstructed from <code>seqs</code> and it is the <code>org</code> sequence.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> org = [1,2,3], seqs = [[1,2],[1,3]]
<strong>Output:</strong> false
<strong>Explanation:</strong> [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> org = [1,2,3], seqs = [[1,2]]
<strong>Output:</strong> false
<strong>Explanation:</strong> The reconstructed sequence can only be [1,2].
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> org = [1,2,3], seqs = [[1,2],[1,3],[2,3]]
<strong>Output:</strong> true
<strong>Explanation:</strong> The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]]
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^4</code></li>
	<li><code>org</code> is a permutation of {1,2,...,n}.</li>
	<li><code>1 &lt;= segs[i].length &lt;= 10^5</code></li>
	<li><code>seqs[i][j]</code>&nbsp;fits in a 32-bit signed integer.</li>
</ul>

<p>&nbsp;</p>

<p><b><font color="red">UPDATE (2017/1/8):</font></b><br />
The <i>seqs</i> parameter had been changed to a list of list of strings (instead of a 2d array of strings). Please reload the code definition to get the latest changes.</p>

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

        edges = collections.defaultdict(list)
        indegree = [0] * (n + 1)
        for seq in seqs:
            i = seq[0]
            for j in seq[1:]:
                edges[i].append(j)
                indegree[j] += 1
                i = j
        q = collections.deque()
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
