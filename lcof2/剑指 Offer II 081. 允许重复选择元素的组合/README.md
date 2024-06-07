---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20081.%20%E5%85%81%E8%AE%B8%E9%87%8D%E5%A4%8D%E9%80%89%E6%8B%A9%E5%85%83%E7%B4%A0%E7%9A%84%E7%BB%84%E5%90%88/README.md
---

<!-- problem:start -->

# [剑指 Offer II 081. 允许重复选择元素的组合](https://leetcode.cn/problems/Ygoe9J)

## 题目描述

<!-- description:start -->

<p>给定一个<strong>无重复元素</strong>的正整数数组&nbsp;<code>candidates</code>&nbsp;和一个正整数&nbsp;<code>target</code>&nbsp;，找出&nbsp;<code>candidates</code>&nbsp;中所有可以使数字和为目标数&nbsp;<code>target</code>&nbsp;的唯一组合。</p>

<p><code>candidates</code>&nbsp;中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。&nbsp;</p>

<p>对于给定的输入，保证和为&nbsp;<code>target</code> 的唯一组合数少于 <code>150</code> 个。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入: </strong>candidates = <code>[2,3,6,7], </code>target = <code>7</code>
<strong>输出: </strong>[[7],[2,2,3]]
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入: </strong>candidates = [2,3,5]<code>, </code>target = 8
<strong>输出: </strong>[[2,2,2,2],[2,3,3],[3,5]]</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入: </strong>candidates = <code>[2], </code>target = <span style="white-space: pre-wrap;">1</span>
<strong>输出: </strong>[]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入: </strong>candidates = <code>[1], </code>target = <code>1</code>
<strong>输出: </strong>[[1]]
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入: </strong>candidates = <code>[1], </code>target = <code>2</code>
<strong>输出: </strong>[[1,1]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= candidates.length &lt;= 30</code></li>
	<li><code>1 &lt;= candidates[i] &lt;= 200</code></li>
	<li><code>candidate</code> 中的每个元素都是独一无二的。</li>
	<li><code>1 &lt;= target &lt;= 500</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 39&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/combination-sum/">https://leetcode.cn/problems/combination-sum/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        ans = []
        n = len(candidates)

        def dfs(s, u, t):
            if s == target:
                ans.append(t.copy())
                return
            if s > target:
                return
            for i in range(u, n):
                c = candidates[i]
                t.append(c)
                dfs(s + c, i, t)
                t.pop()

        dfs(0, 0, [])
        return ans
```

#### Java

```java
class Solution {
    private List<List<Integer>> ans;
    private int target;
    private int[] candidates;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ans = new ArrayList<>();
        this.target = target;
        this.candidates = candidates;
        dfs(0, 0, new ArrayList<>());
        return ans;
    }

    private void dfs(int s, int u, List<Integer> t) {
        if (s == target) {
            ans.add(new ArrayList<>(t));
            return;
        }
        if (s > target) {
            return;
        }
        for (int i = u; i < candidates.length; ++i) {
            int c = candidates[i];
            t.add(c);
            dfs(s + c, i, t);
            t.remove(t.size() - 1);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> ans;
    vector<int> candidates;
    int target;

    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        this->candidates = candidates;
        this->target = target;
        vector<int> t;
        dfs(0, 0, t);
        return ans;
    }

    void dfs(int s, int u, vector<int>& t) {
        if (s == target) {
            ans.push_back(t);
            return;
        }
        if (s > target) return;
        for (int i = u; i < candidates.size(); ++i) {
            int c = candidates[i];
            t.push_back(c);
            dfs(s + c, i, t);
            t.pop_back();
        }
    }
};
```

#### Go

```go
func combinationSum(candidates []int, target int) [][]int {
	var ans [][]int

	var dfs func(s, u int, t []int)
	dfs = func(s, u int, t []int) {
		if s == target {
			ans = append(ans, append([]int(nil), t...))
			return
		}
		if s > target {
			return
		}
		for i := u; i < len(candidates); i++ {
			c := candidates[i]
			t = append(t, c)
			dfs(s+c, i, t)
			t = t[:len(t)-1]
		}
	}

	var t []int
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
    public IList<IList<int>> CombinationSum(int[] candidates, int target)
    {
        Array.Sort(candidates);
        candidates = candidates.Distinct().ToArray();

        var paths = new List<int>[target + 1];
        paths[0] = new List<int>();
        foreach (var c in candidates)
        {
            for (var j = c; j <= target; ++j)
            {
                if (paths[j - c] != null)
                {
                    if (paths[j] == null)
                    {
                        paths[j] = new List<int>();
                    }
                    paths[j].Add(c);
                }
            }
        }

        var results = new List<IList<int>>();
        if (paths[target] != null) GenerateResults(results, new Stack<int>(), paths, target, paths[target].Count - 1);
        return results;
    }

    private void GenerateResults(IList<IList<int>> results, Stack<int> result, List<int>[] paths, int remaining,
        int maxIndex)
    {
        if (remaining == 0)
        {
            results.Add(new List<int>(result));
            return;
        }
        for (var i = maxIndex; i >= 0; --i)
        {
            var value = paths[remaining][i];
            result.Push(value);
            var nextMaxIndex = paths[remaining - value].BinarySearch(value);
            if (nextMaxIndex < 0)
            {
                nextMaxIndex = ~nextMaxIndex - 1;
            }
            GenerateResults(results, result, paths, remaining - value, nextMaxIndex);
            result.Pop();
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
