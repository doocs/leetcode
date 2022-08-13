# [剑指 Offer II 082. 含有重复元素集合的组合](https://leetcode.cn/problems/4sjJUc)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个可能有重复数字的整数数组&nbsp;<code>candidates</code>&nbsp;和一个目标数&nbsp;<code>target</code>&nbsp;，找出&nbsp;<code>candidates</code>&nbsp;中所有可以使数字和为&nbsp;<code>target</code>&nbsp;的组合。</p>

<p><code>candidates</code>&nbsp;中的每个数字在每个组合中只能使用一次，解集不能包含重复的组合。&nbsp;</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> candidates =&nbsp;<code>[10,1,2,7,6,1,5]</code>, target =&nbsp;<code>8</code>,
<strong>输出:</strong>
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> candidates =&nbsp;[2,5,2,1,2], target =&nbsp;5,
<strong>输出:</strong>
[
[1,2,2],
[5]
]</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;candidates.length &lt;= 100</code></li>
	<li><code>1 &lt;=&nbsp;candidates[i] &lt;= 50</code></li>
	<li><code>1 &lt;= target &lt;= 30</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 40&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/combination-sum-ii/">https://leetcode.cn/problems/combination-sum-ii/</a></p>

## 解法

DFS 回溯法。需要先对 candidates 数组进行排序。

去重技巧：

```python
if i > u and candidates[i] == candidates[i - 1]:
    continue
```

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        def dfs(u, s, t):
            if s > target:
                return
            if s == target:
                ans.append(t[:])
                return
            for i in range(u, len(candidates)):
                if i > u and candidates[i] == candidates[i - 1]:
                    continue
                t.append(candidates[i])
                dfs(i + 1, s + candidates[i], t)
                t.pop()

        ans = []
        candidates.sort()
        dfs(0, 0, [])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private List<List<Integer>> ans;
    private int[] candidates;
    private int target;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        ans = new ArrayList<>();
        Arrays.sort(candidates);
        this.target = target;
        this.candidates = candidates;
        dfs(0, 0, new ArrayList<>());
        return ans;
    }

    private void dfs(int u, int s, List<Integer> t) {
        if (s > target) {
            return;
        }
        if (s == target) {
            ans.add(new ArrayList<>(t));
            return;
        }
        for (int i = u; i < candidates.length; ++i) {
            if (i > u && candidates[i] == candidates[i - 1]) {
                continue;
            }
            t.add(candidates[i]);
            dfs(i + 1, s + candidates[i], t);
            t.remove(t.size() - 1);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> candidates;
    vector<vector<int>> ans;
    vector<int> t;
    int target;

    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        sort(candidates.begin(), candidates.end());
        this->candidates = candidates;
        this->target = target;
        vector<int> t;
        dfs(0, 0, t);
        return ans;
    }

    void dfs(int u, int s, vector<int>& t) {
        if (s > target) return;
        if (s == target) {
            ans.push_back(t);
            return;
        }
        for (int i = u; i < candidates.size(); ++i) {
            if (i > u && candidates[i] == candidates[i - 1]) continue;
            t.push_back(candidates[i]);
            dfs(i + 1, s + candidates[i], t);
            t.pop_back();
        }
    }
};
```

### **Go**

```go
func combinationSum2(candidates []int, target int) [][]int {
	var ans [][]int
	var t []int
	sort.Ints(candidates)
	var dfs func(u, s int, t []int)
	dfs = func(u, s int, t []int) {
		if s > target {
			return
		}
		if s == target {
			cp := make([]int, len(t))
			copy(cp, t)
			ans = append(ans, cp)
			return
		}
		for i := u; i < len(candidates); i++ {
			if i > u && candidates[i] == candidates[i-1] {
				continue
			}
			t = append(t, candidates[i])
			dfs(i+1, s+candidates[i], t)
			t = t[:len(t)-1]
		}
	}

	dfs(0, 0, t)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
