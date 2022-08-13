# [39. Combination Sum](https://leetcode.com/problems/combination-sum)

[中文文档](/solution/0000-0099/0039.Combination%20Sum/README.md)

## Description

<p>Given an array of <strong>distinct</strong> integers <code>candidates</code> and a target integer <code>target</code>, return <em>a list of all <strong>unique combinations</strong> of </em><code>candidates</code><em> where the chosen numbers sum to </em><code>target</code><em>.</em> You may return the combinations in <strong>any order</strong>.</p>

<p>The <strong>same</strong> number may be chosen from <code>candidates</code> an <strong>unlimited number of times</strong>. Two combinations are unique if the frequency of at least one of the chosen numbers is different.</p>

<p>It is <strong>guaranteed</strong> that the number of unique combinations that sum up to <code>target</code> is less than <code>150</code> combinations for the given input.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> candidates = [2,3,6,7], target = 7
<strong>Output:</strong> [[2,2,3],[7]]
<strong>Explanation:</strong>
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> candidates = [2,3,5], target = 8
<strong>Output:</strong> [[2,2,2,2],[2,3,3],[3,5]]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> candidates = [2], target = 1
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= candidates.length &lt;= 30</code></li>
	<li><code>1 &lt;= candidates[i] &lt;= 200</code></li>
	<li>All elements of <code>candidates</code> are <strong>distinct</strong>.</li>
	<li><code>1 &lt;= target &lt;= 500</code></li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        def dfs(s, u, t):
            if s == target:
                ans.append(t[:])
                return
            if s > target:
                return
            for i in range(u, len(candidates)):
                c = candidates[i]
                t.append(c)
                dfs(s + c, i, t)
                t.pop()

        ans = []
        dfs(0, 0, [])
        return ans
```

### **Java**

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

### **C++**

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

### **Go**

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

### **TypeScript**

```ts
function combinationSum(candidates: number[], target: number): number[][] {
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
            t.push(candidates[j]);
            dfs(j, sum + candidates[j]);
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
            let num = candidates[j];
            t.push(num);
            Self::dfs(j, count - num, candidates, t, res);
            t.pop();
        }
    }

    pub fn combination_count(candidates: Vec<i32>, target: i32) -> Vec<Vec<i32>> {
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
