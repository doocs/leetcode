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

**方法一：排序 + 剪枝 + 回溯（两种写法）**

我们可以先对数组进行排序，方便剪枝以及跳过重复的数字。

接下来，我们设计一个函数 $dfs(i, s)$，表示从下标 $i$ 开始搜索，且剩余目标值为 $s$，其中 $i$ 和 $s$ 都是非负整数，当前搜索路径为 $t$，答案为 $ans$。

在函数 $dfs(i, s)$ 中，我们先判断 $s$ 是否为 $0$，如果是，则将当前搜索路径 $t$ 加入答案 $ans$ 中，然后返回。如果 $i \geq n$，或者 $s \lt candidates[i]$，说明当前路径不合法，直接返回。否则，我们从下标 $i$ 开始搜索，搜索的下标范围是 $j \in [i, n)$，其中 $n$ 为数组 $candidates$ 的长度。在搜索的过程中，如果 $j \gt i$ 并且 $candidates[j] = candidates[j - 1]$，说明当前数字与上一个数字相同，我们可以跳过当前数字，因为上一个数字已经搜索过了。否则，我们将当前数字加入搜索路径 $t$ 中，然后递归调用函数 $dfs(j + 1, s - candidates[j])$，然后将当前数字从搜索路径 $t$ 中移除。

我们也可以将函数 $dfs(i, s)$ 的实现逻辑改为另一种写法。如果我们选择当前数字，那么我们将当前数字加入搜索路径 $t$ 中，然后递归调用函数 $dfs(i + 1, s - candidates[i])$，然后将当前数字从搜索路径 $t$ 中移除。如果我们不选择当前数字，那么我们可以跳过与当前数字相同的所有数字，然后递归调用函数 $dfs(j, s)$，其中 $j$ 为第一个与当前数字不同的数字的下标。

在主函数中，我们只要调用函数 $dfs(0, target)$，即可得到答案。

时间复杂度 $O(2^n \times n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $candidates$ 的长度。由于剪枝，实际的时间复杂度要远小于 $O(2^n \times n)$。

相似题目：

-   [39. 组合总和](/solution/0000-0099/0039.Combination%20Sum/README.md)
-   [77. 组合](/solution/0000-0099/0077.Combinations/README.md)
-   [216. 组合总和 III](/solution/0200-0299/0216.Combination%20Sum%20III/README.md)

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        def dfs(i: int, s: int):
            if s == 0:
                ans.append(t[:])
                return
            if i >= len(candidates) or s < candidates[i]:
                return
            for j in range(i, len(candidates)):
                if j > i and candidates[j] == candidates[j - 1]:
                    continue
                t.append(candidates[j])
                dfs(j + 1, s - candidates[j])
                t.pop()

        candidates.sort()
        ans = []
        t = []
        dfs(0, target)
        return ans
```

```python
class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        def dfs(i: int, s: int):
            if s == 0:
                ans.append(t[:])
                return
            if i >= len(candidates) or s < candidates[i]:
                return
            x = candidates[i]
            t.append(x)
            dfs(i + 1, s - x)
            t.pop()
            while i < len(candidates) and candidates[i] == x:
                i += 1
            dfs(i, s)

        candidates.sort()
        ans = []
        t = []
        dfs(0, target)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int[] candidates;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        dfs(0, target);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            ans.add(new ArrayList<>(t));
            return;
        }
        if (i >= candidates.length || s < candidates[i]) {
            return;
        }
        for (int j = i; j < candidates.length; ++j) {
            if (j > i && candidates[j] == candidates[j - 1]) {
                continue;
            }
            t.add(candidates[j]);
            dfs(j + 1, s - candidates[j]);
            t.remove(t.size() - 1);
        }
    }
}
```

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int[] candidates;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        dfs(0, target);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            ans.add(new ArrayList<>(t));
            return;
        }
        if (i >= candidates.length || s < candidates[i]) {
            return;
        }
        int x = candidates[i];
        t.add(x);
        dfs(i + 1, s - x);
        t.remove(t.size() - 1);
        while (i < candidates.length && candidates[i] == x) {
            ++i;
        }
        dfs(i, s);
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
            if (s == 0) {
                ans.emplace_back(t);
                return;
            }
            if (i >= candidates.size() || s < candidates[i]) {
                return;
            }
            for (int j = i; j < candidates.size(); ++j) {
                if (j > i && candidates[j] == candidates[j - 1]) {
                    continue;
                }
                t.emplace_back(candidates[j]);
                dfs(j + 1, s - candidates[j]);
                t.pop_back();
            }
        };
        dfs(0, target);
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        sort(candidates.begin(), candidates.end());
        vector<vector<int>> ans;
        vector<int> t;
        function<void(int, int)> dfs = [&](int i, int s) {
            if (s == 0) {
                ans.emplace_back(t);
                return;
            }
            if (i >= candidates.size() || s < candidates[i]) {
                return;
            }
            int x = candidates[i];
            t.emplace_back(x);
            dfs(i + 1, s - x);
            t.pop_back();
            while (i < candidates.size() && candidates[i] == x) {
                ++i;
            }
            dfs(i, s);
        };
        dfs(0, target);
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
		if s == 0 {
			cp := make([]int, len(t))
			copy(cp, t)
			ans = append(ans, cp)
			return
		}
		if i >= len(candidates) || s < candidates[i] {
			return
		}
		for j := i; j < len(candidates); j++ {
			if j > i && candidates[j] == candidates[j-1] {
				continue
			}
			t = append(t, candidates[j])
			dfs(j+1, s-candidates[j])
			t = t[:len(t)-1]
		}
	}
	dfs(0, target)
	return
}
```

```go
func combinationSum2(candidates []int, target int) (ans [][]int) {
	sort.Ints(candidates)
	t := []int{}
	var dfs func(i, s int)
	dfs = func(i, s int) {
		if s == 0 {
			cp := make([]int, len(t))
			copy(cp, t)
			ans = append(ans, cp)
			return
		}
		if i >= len(candidates) || s < candidates[i] {
			return
		}
		x := candidates[i]
		t = append(t, x)
		dfs(i+1, s-x)
		t = t[:len(t)-1]
		for i < len(candidates) && candidates[i] == x {
			i++
		}
		dfs(i, s)
	}
	dfs(0, target)
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
    const ans = [];
    const t = [];
    const dfs = (i, s) => {
        if (s === 0) {
            ans.push(t.slice());
            return;
        }
        if (i >= candidates.length || s < candidates[i]) {
            return;
        }
        for (let j = i; j < candidates.length; ++j) {
            if (j > i && candidates[j] === candidates[j - 1]) {
                continue;
            }
            t.push(candidates[j]);
            dfs(j + 1, s - candidates[j]);
            t.pop();
        }
    };
    dfs(0, target);
    return ans;
};
```

```js
/**
 * @param {number[]} candidates
 * @param {number} target
 * @return {number[][]}
 */
var combinationSum2 = function (candidates, target) {
    candidates.sort((a, b) => a - b);
    const ans = [];
    const t = [];
    const dfs = (i, s) => {
        if (s === 0) {
            ans.push(t.slice());
            return;
        }
        if (i >= candidates.length || s < candidates[i]) {
            return;
        }
        const x = candidates[i];
        t.push(x);
        dfs(i + 1, s - x);
        t.pop();
        while (i < candidates.length && candidates[i] === x) {
            ++i;
        }
        dfs(i, s);
    };
    dfs(0, target);
    return ans;
};
```

### **TypeScript**

```ts
function combinationSum2(candidates: number[], target: number): number[][] {
    candidates.sort((a, b) => a - b);
    const ans: number[][] = [];
    const t: number[] = [];
    const dfs = (i: number, s: number) => {
        if (s === 0) {
            ans.push(t.slice());
            return;
        }
        if (i >= candidates.length || s < candidates[i]) {
            return;
        }
        for (let j = i; j < candidates.length; j++) {
            if (j > i && candidates[j] === candidates[j - 1]) {
                continue;
            }
            t.push(candidates[j]);
            dfs(j + 1, s - candidates[j]);
            t.pop();
        }
    };
    dfs(0, target);
    return ans;
}
```

```ts
function combinationSum2(candidates: number[], target: number): number[][] {
    candidates.sort((a, b) => a - b);
    const ans: number[][] = [];
    const t: number[] = [];
    const dfs = (i: number, s: number) => {
        if (s === 0) {
            ans.push(t.slice());
            return;
        }
        if (i >= candidates.length || s < candidates[i]) {
            return;
        }
        const x = candidates[i];
        t.push(x);
        dfs(i + 1, s - x);
        t.pop();
        while (i < candidates.length && candidates[i] === x) {
            ++i;
        }
        dfs(i, s);
    };
    dfs(0, target);
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(i: usize, s: i32, candidates: &Vec<i32>, t: &mut Vec<i32>, ans: &mut Vec<Vec<i32>>) {
        if s == 0 {
            ans.push(t.clone());
            return;
        }
        if i >= candidates.len() || s < candidates[i] {
            return;
        }
        for j in i..candidates.len() {
            if j > i && candidates[j] == candidates[j - 1] {
                continue;
            }
            t.push(candidates[j]);
            Self::dfs(j + 1, s - candidates[j], candidates, t, ans);
            t.pop();
        }
    }

    pub fn combination_sum2(mut candidates: Vec<i32>, target: i32) -> Vec<Vec<i32>> {
        candidates.sort();
        let mut ans = Vec::new();
        Self::dfs(0, target, &candidates, &mut vec![], &mut ans);
        ans
    }
}
```

```rust
impl Solution {
    fn dfs(mut i: usize, s: i32, candidates: &Vec<i32>, t: &mut Vec<i32>, ans: &mut Vec<Vec<i32>>) {
        if s == 0 {
            ans.push(t.clone());
            return;
        }
        if i >= candidates.len() || s < candidates[i] {
            return;
        }
        let x = candidates[i];
        t.push(x);
        Self::dfs(i + 1, s - x, candidates, t, ans);
        t.pop();
        while i < candidates.len() && candidates[i] == x {
            i += 1
        }
        Self::dfs(i, s, candidates, t, ans);
    }

    pub fn combination_sum2(mut candidates: Vec<i32>, target: i32) -> Vec<Vec<i32>> {
        candidates.sort();
        let mut ans = Vec::new();
        Self::dfs(0, target, &candidates, &mut vec![], &mut ans);
        ans
    }
}
```

### **C#**

```cs
public class Solution {
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();
    private int[] candidates;

    public IList<IList<int>> CombinationSum2(int[] candidates, int target) {
        Array.Sort(candidates);
        this.candidates = candidates;
        dfs(0, target);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            ans.Add(new List<int>(t));
            return;
        }
        if (i >= candidates.Length || s < candidates[i]) {
            return;
        }
        for (int j = i; j < candidates.Length; ++j) {
            if (j > i && candidates[j] == candidates[j - 1]) {
                continue;
            }
            t.Add(candidates[j]);
            dfs(j + 1, s - candidates[j]);
            t.RemoveAt(t.Count - 1);
        }
    }
}
```

```cs
public class Solution {
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();
    private int[] candidates;

    public IList<IList<int>> CombinationSum2(int[] candidates, int target) {
        Array.Sort(candidates);
        this.candidates = candidates;
        dfs(0, target);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            ans.Add(new List<int>(t));
            return;
        }
        if (i >= candidates.Length || s < candidates[i]) {
            return;
        }
        int x = candidates[i];
        t.Add(x);
        dfs(i + 1, s - x);
        t.RemoveAt(t.Count - 1);
        while (i < candidates.Length && candidates[i] == x) {
            ++i;
        }
        dfs(i, s);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
