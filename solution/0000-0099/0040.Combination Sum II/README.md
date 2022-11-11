# [40. 组合总和 II](https://leetcode.cn/problems/combination-sum-ii)

[English Version](/solution/0000-0099/0040.Combination%20Sum%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个候选人编号的集合&nbsp;<code>candidates</code>&nbsp;和一个目标数&nbsp;<code>target</code>&nbsp;，找出&nbsp;<code>candidates</code>&nbsp;中所有可以使数字和为&nbsp;<code>target</code>&nbsp;的组合。</p>

<p><code>candidates</code>&nbsp;中的每个数字在每个组合中只能使用&nbsp;<strong>一次</strong>&nbsp;。</p>

<p><strong>注意：</strong>解集不能包含重复的组合。&nbsp;</p>

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

## 解法

**方法一：排序 + 回溯**

题目要求组合不能重复，我们可以先对数组进行排序，方便跳过重复的数字。

然后从左到右遍历数组，每次遍历到一个数，就将其加入到当前组合中，然后继续遍历下一个数，直到当前组合的和等于目标值，此时将当前组合加入到结果集中，然后回溯到上一层，继续遍历下一个数。

时间复杂度 $O(2^n \times n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        def dfs(i, s):
            if s > target:
                return
            if s == target:
                ans.append(t[:])
                return
            for j in range(i, len(candidates)):
                if j > i and candidates[j] == candidates[j - 1]:
                    continue
                t.append(candidates[j])
                dfs(j + 1, s + candidates[j])
                t.pop()

        ans = []
        candidates.sort()
        t = []
        dfs(0, 0)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int[] candidates;
    private int target;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.target = target;
        this.candidates = candidates;
        dfs(0, 0);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s > target) {
            return;
        }
        if (s == target) {
            ans.add(new ArrayList<>(t));
            return;
        }
        for (int j = i; j < candidates.length; ++j) {
            if (j > i && candidates[j] == candidates[j - 1]) {
                continue;
            }
            t.add(candidates[j]);
            dfs(j + 1, s + candidates[j]);
            t.remove(t.size() - 1);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        sort(candidates.begin(), candidates.end());
        vector<vector<int>> ans;
        vector<int> t;
        function<void(int, int)> dfs = [&](int i, int s) {
            if (s > target) return;
            if (s == target) {
                ans.emplace_back(t);
                return;
            }
            for (int j = i; j < candidates.size(); ++j) {
                if (j > i && candidates[j] == candidates[j - 1]) continue;
                t.emplace_back(candidates[j]);
                dfs(j + 1, s + candidates[j]);
                t.pop_back();
            }
        };
        dfs(0, 0);
        return ans;
    }
};
```

### **Go**

```go
func combinationSum2(candidates []int, target int) (ans [][]int) {
	sort.Ints(candidates)
	t := []int{}
	var dfs func(i, s int)
	dfs = func(i, s int) {
		if s > target {
			return
		}
		if s == target {
			cp := make([]int, len(t))
			copy(cp, t)
			ans = append(ans, cp)
			return
		}
		for j := i; j < len(candidates); j++ {
			if j > i && candidates[j] == candidates[j-1] {
				continue
			}
			t = append(t, candidates[j])
			dfs(j+1, s+candidates[j])
			t = t[:len(t)-1]
		}
	}
	dfs(0, 0)
	return
}
```

### **JavaScript**

```js
/**
 * @param {number[]} candidates
 * @param {number} target
 * @return {number[][]}
 */
var combinationSum2 = function (candidates, target) {
    candidates.sort((a, b) => a - b);
    const n = candidates.length;
    const t = [];
    const ans = [];
    const dfs = (i, s) => {
        if (s > target) {
            return;
        }
        if (s === target) {
            ans.push([...t]);
            return;
        }
        for (let j = i; j < n; j++) {
            const num = candidates[j];
            if (j > i && num === candidates[j - 1]) {
                continue;
            }
            t.push(num);
            dfs(j + 1, s + num);
            t.pop();
        }
    };
    dfs(0, 0);
    return ans;
};
```

### **TypeScript**

```ts
function combinationSum2(candidates: number[], target: number): number[][] {
    candidates.sort((a, b) => a - b);
    const n = candidates.length;
    const t: number[] = [];
    const res: number[][] = [];
    const dfs = (i: number, sum: number) => {
        if (sum > target) {
            return;
        }
        if (sum === target) {
            res.push([...t]);
            return;
        }
        for (let j = i; j < n; j++) {
            const num = candidates[j];
            if (j > i && num === candidates[j - 1]) {
                continue;
            }
            t.push(num);
            dfs(j + 1, sum + num);
            t.pop();
        }
    };
    dfs(0, 0);
    return res;
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(i: usize, count: i32, candidates: &Vec<i32>, t: &mut Vec<i32>, res: &mut Vec<Vec<i32>>) {
        if count < 0 {
            return;
        }
        if count == 0 {
            res.push(t.clone());
            return;
        }
        for j in i..candidates.len() {
            if j > i && candidates[j] == candidates[j - 1] {
                continue;
            }
            let num = candidates[j];
            t.push(num);
            Self::dfs(j + 1, count - num, candidates, t, res);
            t.pop();
        }
    }

    pub fn combination_sum2(mut candidates: Vec<i32>, target: i32) -> Vec<Vec<i32>> {
        candidates.sort();
        let mut res = Vec::new();
        Self::dfs(0, target, &candidates, &mut vec![], &mut res);
        res
    }
}
```

### **C#**

```cs
public class Solution {
    public IList<IList<int>> CombinationSum2(int[] candidates, int target) {
        Array.Sort(candidates);
        var ans = new List<IList<int>>();
        var t = new List<int>();
        dfs(candidates, 0, 0, target, t, ans);
        return ans;
    }

    private void dfs(int[] candidates, int i, int s, int target, IList<int> t, IList<IList<int>> ans) {
        if (s > target) {
            return;
        }
        if (s == target) {
            ans.Add(new List<int>(t));
            return;
        }
        for (int j = i; j < candidates.Length; ++j) {
            if (j > i && candidates[j] == candidates[j - 1]) {
                continue;
            }
            t.Add(candidates[j]);
            dfs(candidates, j + 1, s + candidates[j], target, t, ans);
            t.RemoveAt(t.Count - 1);
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
