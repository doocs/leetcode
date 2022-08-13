# [444. 序列重建](https://leetcode.cn/problems/sequence-reconstruction)

[English Version](/solution/0400-0499/0444.Sequence%20Reconstruction/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个长度为 <code>n</code> 的整数数组 <code>nums</code> ，其中 <code>nums</code> 是范围为 <code>[1，n]</code> 的整数的排列。还提供了一个 2D 整数数组&nbsp;<code>sequences</code>&nbsp;，其中&nbsp;<code>sequences[i]</code>&nbsp;是&nbsp;<code>nums</code>&nbsp;的子序列。<br />
检查 <code>nums</code> 是否是唯一的最短&nbsp;<strong>超序列</strong> 。最短 <strong>超序列</strong> 是 <strong>长度最短</strong> 的序列，并且所有序列&nbsp;<code>sequences[i]</code>&nbsp;都是它的子序列。对于给定的数组&nbsp;<code>sequences</code>&nbsp;，可能存在多个有效的 <strong>超序列</strong> 。</p>

<ul>
	<li>例如，对于&nbsp;<code>sequences = [[1,2],[1,3]]</code>&nbsp;，有两个最短的 <strong>超序列</strong> ，<code>[1,2,3]</code> 和 <code>[1,3,2]</code> 。</li>
	<li>而对于&nbsp;<code>sequences = [[1,2],[1,3],[1,2,3]]</code>&nbsp;，唯一可能的最短 <strong>超序列</strong> 是 <code>[1,2,3]</code> 。<code>[1,2,3,4]</code> 是可能的超序列，但不是最短的。</li>
</ul>

<p><em>如果 <code>nums</code> 是序列的唯一最短 <strong>超序列</strong> ，则返回 <code>true</code> ，否则返回 <code>false</code> 。</em><br />
<strong>子序列</strong> 是一个可以通过从另一个序列中删除一些元素或不删除任何元素，而不改变其余元素的顺序的序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3], sequences = [[1,2],[1,3]]
<strong>输出：</strong>false
<strong>解释：</strong>有两种可能的超序列：[1,2,3]和[1,3,2]。
序列 [1,2] 是[<u><strong>1,2</strong></u>,3]和[<u><strong>1</strong></u>,3,<u><strong>2</strong></u>]的子序列。
序列 [1,3] 是[<u><strong>1</strong></u>,2,<u><strong>3</strong></u>]和[<u><strong>1,3</strong></u>,2]的子序列。
因为 nums 不是唯一最短的超序列，所以返回false。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3], sequences = [[1,2]]
<strong>输出：</strong>false
<strong>解释：</strong>最短可能的超序列为 [1,2]。
序列 [1,2] 是它的子序列：[<u><strong>1,2</strong></u>]。
因为 nums 不是最短的超序列，所以返回false。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3], sequences = [[1,2],[1,3],[2,3]]
<strong>输出：</strong>true
<strong>解释：</strong>最短可能的超序列为[1,2,3]。
序列 [1,2] 是它的一个子序列：[<strong>1,2</strong>,3]。
序列 [1,3] 是它的一个子序列：[<u><strong>1</strong></u>,2,<u><strong>3</strong></u>]。
序列 [2,3] 是它的一个子序列：[1,<u><strong>2,3</strong></u>]。
因为 nums 是唯一最短的超序列，所以返回true。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code>&nbsp;是&nbsp;<code>[1, n]</code>&nbsp;范围内所有整数的排列</li>
	<li><code>1 &lt;= sequences.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= sequences[i].length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= sum(sequences[i].length) &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= sequences[i][j] &lt;= n</code></li>
	<li><code>sequences</code>&nbsp;的所有数组都是 <strong>唯一 </strong>的</li>
	<li><code>sequences[i]</code>&nbsp;是&nbsp;<code>nums</code> 的一个子序列</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：拓扑排序**

BFS 实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
        q = deque([i for i, v in enumerate(indeg) if v == 0])
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

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        int n = nums.length;
        int[] indeg = new int[n];
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
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
