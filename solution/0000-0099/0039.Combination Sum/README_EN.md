---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0039.Combination%20Sum/README_EN.md
tags:
    - Array
    - Backtracking
---

<!-- problem:start -->

# [39. Combination Sum](https://leetcode.com/problems/combination-sum)

[中文文档](/solution/0000-0099/0039.Combination%20Sum/README.md)

## Description

<p>Given an array of <strong>distinct</strong> integers <code>candidates</code> and a target integer <code>target</code>, return <em>a list of all <strong>unique combinations</strong> of </em><code>candidates</code><em> where the chosen numbers sum to </em><code>target</code><em>.</em> You may return the combinations in <strong>any order</strong>.</p>

<p>The <strong>same</strong> number may be chosen from <code>candidates</code> an <strong>unlimited number of times</strong>. Two combinations are unique if the <span data-keyword="frequency-array">frequency</span> of at least one of the chosen numbers is different.</p>

<p>The test cases are generated such that the number of unique combinations that sum up to <code>target</code> is less than <code>150</code> combinations for the given input.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> candidates = [2,3,6,7], target = 7
<strong>Output:</strong> [[2,2,3],[7]]
<strong>Explanation:</strong>
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> candidates = [2,3,5], target = 8
<strong>Output:</strong> [[2,2,2,2],[2,3,3],[3,5]]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> candidates = [2], target = 1
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= candidates.length &lt;= 30</code></li>
	<li><code>2 &lt;= candidates[i] &lt;= 40</code></li>
	<li>All elements of <code>candidates</code> are <strong>distinct</strong>.</li>
	<li><code>1 &lt;= target &lt;= 40</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Pruning + Backtracking

We can first sort the array to facilitate pruning.

Next, we design a function $dfs(i, s)$, which means starting the search from index $i$ with a remaining target value of $s$. Here, $i$ and $s$ are both non-negative integers, the current search path is $t$, and the answer is $ans$.

In the function $dfs(i, s)$, we first check whether $s$ is $0$. If it is, we add the current search path $t$ to the answer $ans$, and then return. If $s \lt candidates[i]$, it means that the elements of the current index and the following indices are all greater than the remaining target value $s$, and the path is invalid, so we return directly. Otherwise, we start the search from index $i$, and the search index range is $j \in [i, n)$, where $n$ is the length of the array $candidates$. During the search, we add the element of the current index to the search path $t$, recursively call the function $dfs(j, s - candidates[j])$, and after the recursion ends, we remove the element of the current index from the search path $t$.

In the main function, we just need to call the function $dfs(0, target)$ to get the answer.

The time complexity is $O(2^n \times n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $candidates$. Due to pruning, the actual time complexity is much less than $O(2^n \times n)$.

Similar problems:

-   [40. Combination Sum II](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0040.Combination%20Sum%20II/README_EN.md)
-   [77. Combinations](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0077.Combinations/README_EN.md)
-   [216. Combination Sum III](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0216.Combination%20Sum%20III/README_EN.md)

<!-- tabs:start -->

```python
class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        def dfs(i: int, s: int):
            if s == 0:
                ans.append(t[:])
                return
            if s < candidates[i]:
                return
            for j in range(i, len(candidates)):
                t.append(candidates[j])
                dfs(j, s - candidates[j])
                t.pop()

        candidates.sort()
        t = []
        ans = []
        dfs(0, target)
        return ans
```

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int[] candidates;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        dfs(0, target);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            ans.add(new ArrayList(t));
            return;
        }
        if (s < candidates[i]) {
            return;
        }
        for (int j = i; j < candidates.length; ++j) {
            t.add(candidates[j]);
            dfs(j, s - candidates[j]);
            t.remove(t.size() - 1);
        }
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        sort(candidates.begin(), candidates.end());
        vector<vector<int>> ans;
        vector<int> t;
        function<void(int, int)> dfs = [&](int i, int s) {
            if (s == 0) {
                ans.emplace_back(t);
                return;
            }
            if (s < candidates[i]) {
                return;
            }
            for (int j = i; j < candidates.size(); ++j) {
                t.push_back(candidates[j]);
                dfs(j, s - candidates[j]);
                t.pop_back();
            }
        };
        dfs(0, target);
        return ans;
    }
};
```

```go
func combinationSum(candidates []int, target int) (ans [][]int) {
	sort.Ints(candidates)
	t := []int{}
	var dfs func(i, s int)
	dfs = func(i, s int) {
		if s == 0 {
			ans = append(ans, slices.Clone(t))
			return
		}
		if s < candidates[i] {
			return
		}
		for j := i; j < len(candidates); j++ {
			t = append(t, candidates[j])
			dfs(j, s-candidates[j])
			t = t[:len(t)-1]
		}
	}
	dfs(0, target)
	return
}
```

```ts
function combinationSum(candidates: number[], target: number): number[][] {
    candidates.sort((a, b) => a - b);
    const ans: number[][] = [];
    const t: number[] = [];
    const dfs = (i: number, s: number) => {
        if (s === 0) {
            ans.push(t.slice());
            return;
        }
        if (s < candidates[i]) {
            return;
        }
        for (let j = i; j < candidates.length; ++j) {
            t.push(candidates[j]);
            dfs(j, s - candidates[j]);
            t.pop();
        }
    };
    dfs(0, target);
    return ans;
}
```

```rust
impl Solution {
    fn dfs(i: usize, s: i32, candidates: &Vec<i32>, t: &mut Vec<i32>, ans: &mut Vec<Vec<i32>>) {
        if s == 0 {
            ans.push(t.clone());
            return;
        }
        if s < candidates[i] {
            return;
        }
        for j in i..candidates.len() {
            t.push(candidates[j]);
            Self::dfs(j, s - candidates[j], candidates, t, ans);
            t.pop();
        }
    }

    pub fn combination_sum(mut candidates: Vec<i32>, target: i32) -> Vec<Vec<i32>> {
        candidates.sort();
        let mut ans = Vec::new();
        Self::dfs(0, target, &candidates, &mut vec![], &mut ans);
        ans
    }
}
```

```cs
public class Solution {
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();
    private int[] candidates;

    public IList<IList<int>> CombinationSum(int[] candidates, int target) {
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
        if (s < candidates[i]) {
            return;
        }
        for (int j = i; j < candidates.Length; ++j) {
            t.Add(candidates[j]);
            dfs(j, s - candidates[j]);
            t.RemoveAt(t.Count - 1);
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Sorting + Pruning + Backtracking(Another Form)

We can also change the implementation logic of the function $dfs(i, s)$ to another form. In the function $dfs(i, s)$, we first check whether $s$ is $0$. If it is, we add the current search path $t$ to the answer $ans$, and then return. If $i \geq n$ or $s \lt candidates[i]$, the path is invalid, so we return directly. Otherwise, we consider two situations, one is not selecting the element of the current index, that is, recursively calling the function $dfs(i + 1, s)$, and the other is selecting the element of the current index, that is, recursively calling the function $dfs(i, s - candidates[i])$.

The time complexity is $O(2^n \times n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $candidates$. Due to pruning, the actual time complexity is much less than $O(2^n \times n)$.

<!-- tabs:start -->

```python
class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        def dfs(i: int, s: int):
            if s == 0:
                ans.append(t[:])
                return
            if i >= len(candidates) or s < candidates[i]:
                return
            dfs(i + 1, s)
            t.append(candidates[i])
            dfs(i, s - candidates[i])
            t.pop()

        candidates.sort()
        t = []
        ans = []
        dfs(0, target)
        return ans
```

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int[] candidates;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        dfs(0, target);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            ans.add(new ArrayList(t));
            return;
        }
        if (i >= candidates.length || s < candidates[i]) {
            return;
        }
        dfs(i + 1, s);
        t.add(candidates[i]);
        dfs(i, s - candidates[i]);
        t.remove(t.size() - 1);
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
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
            dfs(i + 1, s);
            t.push_back(candidates[i]);
            dfs(i, s - candidates[i]);
            t.pop_back();
        };
        dfs(0, target);
        return ans;
    }
};
```

```go
func combinationSum(candidates []int, target int) (ans [][]int) {
	sort.Ints(candidates)
	t := []int{}
	var dfs func(i, s int)
	dfs = func(i, s int) {
		if s == 0 {
			ans = append(ans, slices.Clone(t))
			return
		}
		if i >= len(candidates) || s < candidates[i] {
			return
		}
		dfs(i+1, s)
		t = append(t, candidates[i])
		dfs(i, s-candidates[i])
		t = t[:len(t)-1]
	}
	dfs(0, target)
	return
}
```

```ts
function combinationSum(candidates: number[], target: number): number[][] {
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
        dfs(i + 1, s);
        t.push(candidates[i]);
        dfs(i, s - candidates[i]);
        t.pop();
    };
    dfs(0, target);
    return ans;
}
```

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
        Self::dfs(i + 1, s, candidates, t, ans);
        t.push(candidates[i]);
        Self::dfs(i, s - candidates[i], candidates, t, ans);
        t.pop();
    }

    pub fn combination_sum(mut candidates: Vec<i32>, target: i32) -> Vec<Vec<i32>> {
        candidates.sort();
        let mut ans = Vec::new();
        Self::dfs(0, target, &candidates, &mut vec![], &mut ans);
        ans
    }
}
```

```cs
public class Solution {
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();
    private int[] candidates;

    public IList<IList<int>> CombinationSum(int[] candidates, int target) {
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
        dfs(i + 1, s);
        t.Add(candidates[i]);
        dfs(i, s - candidates[i]);
        t.RemoveAt(t.Count - 1);
    }
}
```

```php
class Solution {
    /**
     * @param integer[] $candidates
     * @param integer $target
     * @return integer[][]
     */

    function combinationSum($candidates, $target) {
        $result = [];
        $currentCombination = [];
        $startIndex = 0;

        sort($candidates);
        $this->findCombinations($candidates, $target, $startIndex, $currentCombination, $result);
        return $result;
    }

    function findCombinations($candidates, $target, $startIndex, $currentCombination, &$result) {
        if ($target === 0) {
            $result[] = $currentCombination;
            return;
        }

        for ($i = $startIndex; $i < count($candidates); $i++) {
            $num = $candidates[$i];
            if ($num > $target) {
                break;
            }
            $currentCombination[] = $num;

            $this->findCombinations($candidates, $target - $num, $i, $currentCombination, $result);
            array_pop($currentCombination);
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
