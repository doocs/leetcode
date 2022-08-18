# [77. Combinations](https://leetcode.com/problems/combinations)

[中文文档](/solution/0000-0099/0077.Combinations/README.md)

## Description

<p>Given two integers <code>n</code> and <code>k</code>, return <em>all possible combinations of</em> <code>k</code> <em>numbers chosen from the range</em> <code>[1, n]</code>.</p>

<p>You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 4, k = 2
<strong>Output:</strong> [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
<strong>Explanation:</strong> There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1, k = 1
<strong>Output:</strong> [[1]]
<strong>Explanation:</strong> There is 1 choose 1 = 1 total combination.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 20</code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        res = []

        def dfs(i, n, k, t):
            if len(t) == k:
                res.append(t.copy())
                return
            for j in range(i, n + 1):
                t.append(j)
                dfs(j + 1, n, k, t)
                t.pop()

        dfs(1, n, k, [])
        return res
```

### **Java**

```java
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(1, n, k, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int i, int n, int k, List<Integer> t, List<List<Integer>> res) {
        if (t.size() == k) {
            res.add(new ArrayList<>(t));
            return;
        }
        for (int j = i; j <= n; ++j) {
            t.add(j);
            dfs(j + 1, n, k, t, res);
            t.remove(t.size() - 1);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> combine(int n, int k) {
        vector<vector<int>> res;
        vector<int> t;
        dfs(1, n, k, t, res);
        return res;
    }

    void dfs(int i, int n, int k, vector<int> t, vector<vector<int>>& res) {
        if (t.size() == k) {
            res.push_back(t);
            return;
        }
        for (int j = i; j <= n; ++j) {
            t.push_back(j);
            dfs(j + 1, n, k, t, res);
            t.pop_back();
        }
    }
};
```

### **Go**

```go
func combine(n int, k int) [][]int {
	var res [][]int
	var t []int
	dfs(1, n, k, t, &res)
	return res
}

func dfs(i, n, k int, t []int, res *[][]int) {
	if len(t) == k {
		cp := make([]int, k)
		copy(cp, t)
		*res = append(*res, cp)
		return
	}
	for j := i; j <= n; j++ {
		t = append(t, j)
		dfs(j+1, n, k, t, res)
		t = t[:len(t)-1]
	}
}
```

### **TypeScript**

```ts
function combine(n: number, k: number): number[][] {
    const res: number[][] = [];
    const dfs = (i: number, t: number[]) => {
        if (t.length == k) {
            res.push(t);
            return;
        }
        // pruning
        if (t.length + n - i + 1 < k) {
            return;
        }
        for (let j = i; j <= n; j++) {
            dfs(j + 1, [...t, j]);
        }
    };
    dfs(1, []);
    return res;
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(i: i32, n: i32, k: i32, t: &mut Vec<i32>, res: &mut Vec<Vec<i32>>) {
        if k == 0 {
            res.push(t.clone());
            return;
        }
        // pruning
        if n - i + 1 < k {
            return;
        }
        for j in i..=n {
            t.push(j);
            Self::dfs(j + 1, n, k - 1, t, res);
            t.pop();
        }
    }

    pub fn combine(n: i32, k: i32) -> Vec<Vec<i32>> {
        let mut res = vec![];
        Self::dfs(1, n, k, &mut vec![], &mut res);
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
