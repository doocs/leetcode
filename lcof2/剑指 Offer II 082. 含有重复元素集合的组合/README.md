---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20082.%20%E5%90%AB%E6%9C%89%E9%87%8D%E5%A4%8D%E5%85%83%E7%B4%A0%E9%9B%86%E5%90%88%E7%9A%84%E7%BB%84%E5%90%88/README.md
---

<!-- problem:start -->

# [剑指 Offer II 082. 含有重复元素集合的组合](https://leetcode.cn/problems/4sjJUc)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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
			ans = append(ans, slices.Clone(t))
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

#### C#

```cs
using System;
using System.Collections.Generic;
using System.Linq;

public class Solution
{
    public IList<IList<int>> CombinationSum2(int[] candidates, int target)
    {
        var dict = new SortedDictionary<int, int>(candidates.GroupBy(c => c).ToDictionary(g => g.Key, g => g.Count()));
        var paths = new List<Tuple<int, int>>[target + 1];
        paths[0] = new List<Tuple<int, int>>();
        foreach (var pair in dict)
        {
            for (var j = target; j >= 0; --j)
            {
                for (var k = 1; k <= pair.Value && j - pair.Key * k >= 0; ++k)
                {
                    if (paths[j - pair.Key * k] != null)
                    {
                        if (paths[j] == null)
                        {
                            paths[j] = new List<Tuple<int, int>>();
                        }
                        paths[j].Add(Tuple.Create(pair.Key, k));
                    }
                }
            }
        }

        var results = new List<IList<int>>();
        if (paths[target] != null) GenerateResults(results, new Stack<int>(), paths, target, paths[target].Count - 1);
        return results;
    }

    private void GenerateResults(IList<IList<int>> results, Stack<int> result, List<Tuple<int, int>>[] paths, int remaining,
        int maxIndex)
    {
        if (remaining == 0)
        {
            results.Add(new List<int>(result));
            return;
        }
        for (var i = maxIndex; i >= 0; --i)
        {
            var path = paths[remaining][i];
            for (var j = 0; j < path.Item2; ++j)
            {
                result.Push(path.Item1);
            }
            var nextMaxIndex = paths[remaining - path.Item1 * path.Item2].BinarySearch(Tuple.Create(path.Item1, int.MinValue), Comparer.Instance);
            nextMaxIndex = ~nextMaxIndex - 1;
            GenerateResults(results, result, paths, remaining - path.Item1 * path.Item2, nextMaxIndex);
            for (var j = 0; j < path.Item2; ++j)
            {
                result.Pop();
            }
        }
    }
}

class Comparer : IComparer<Tuple<int, int>>
{
    public int Compare(Tuple<int, int> x, Tuple<int, int> y)
    {
        if (x.Item1 < y.Item1) return -1;
        if (x.Item1 > y.Item1) return 1;
        return x.Item2.CompareTo(y.Item2);
    }

    public static Comparer Instance = new Comparer();
}
```

#### Swift

```swift
class Solution {
    private var ans: [[Int]] = []
    private var candidates: [Int] = []
    private var target: Int = 0

    func combinationSum2(_ candidates: [Int], _ target: Int) -> [[Int]] {
        self.ans = []
        self.target = target
        self.candidates = candidates.sorted()
        dfs(0, 0, [])
        return ans
    }

    private func dfs(_ index: Int, _ sum: Int, _ current: [Int]) {
        if sum > target {
            return
        }
        if sum == target {
            ans.append(current)
            return
        }
        for i in index..<candidates.count {
            if i > index && candidates[i] == candidates[i - 1] {
                continue
            }
            dfs(i + 1, sum + candidates[i], current + [candidates[i]])
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
