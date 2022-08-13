# [40. Combination Sum II](https://leetcode.com/problems/combination-sum-ii)

[中文文档](/solution/0000-0099/0040.Combination%20Sum%20II/README.md)

## Description

<p>Given a collection of candidate numbers (<code>candidates</code>) and a target number (<code>target</code>), find all unique combinations in <code>candidates</code>&nbsp;where the candidate numbers sum to <code>target</code>.</p>

<p>Each number in <code>candidates</code>&nbsp;may only be used <strong>once</strong> in the combination.</p>

<p><strong>Note:</strong>&nbsp;The solution set must not contain duplicate combinations.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> candidates = [10,1,2,7,6,1,5], target = 8
<strong>Output:</strong> 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> candidates = [2,5,2,1,2], target = 5
<strong>Output:</strong> 
[
[1,2,2],
[5]
]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;candidates.length &lt;= 100</code></li>
	<li><code>1 &lt;=&nbsp;candidates[i] &lt;= 50</code></li>
	<li><code>1 &lt;= target &lt;= 30</code></li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

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

### **...**

```

```

<!-- tabs:end -->
