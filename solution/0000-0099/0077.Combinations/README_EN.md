# [77. Combinations](https://leetcode.com/problems/combinations)

[中文文档](/solution/0000-0099/0077.Combinations/README.md)

## Description

<p>Given two integers <code>n</code> and <code>k</code>, return <em>all possible combinations of</em> <code>k</code> <em>numbers chosen from the range</em> <code>[1, n]</code>.</p>

<p>You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 4, k = 2
<strong>Output:</strong> [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
<strong>Explanation:</strong> There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
</pre>

<p><strong class="example">Example 2:</strong></p>

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
        def dfs(i: int):
            if len(t) == k:
                ans.append(t[:])
                return
            if i > n:
                return
            t.append(i)
            dfs(i + 1)
            t.pop()
            dfs(i + 1)

        ans = []
        t = []
        dfs(1)
        return ans
```

```python
class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        def dfs(i: int):
            if len(t) == k:
                ans.append(t[:])
                return
            if i > n:
                return
            for j in range(i, n + 1):
                t.append(j)
                dfs(j + 1)
                t.pop()

        ans = []
        t = []
        dfs(1)
        return ans
```

### **Java**

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int n;
    private int k;

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        dfs(1);
        return ans;
    }

    private void dfs(int i) {
        if (t.size() == k) {
            ans.add(new ArrayList<>(t));
            return;
        }
        if (i > n) {
            return;
        }
        t.add(i);
        dfs(i + 1);
        t.remove(t.size() - 1);
        dfs(i + 1);
    }
}
```

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int n;
    private int k;

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        dfs(1);
        return ans;
    }

    private void dfs(int i) {
        if (t.size() == k) {
            ans.add(new ArrayList<>(t));
            return;
        }
        if (i > n) {
            return;
        }
        for (int j = i; j <= n; ++j) {
            t.add(j);
            dfs(j + 1);
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
        vector<vector<int>> ans;
        vector<int> t;
        function<void(int)> dfs = [&](int i) {
            if (t.size() == k) {
                ans.emplace_back(t);
                return;
            }
            if (i > n) {
                return;
            }
            t.emplace_back(i);
            dfs(i + 1);
            t.pop_back();
            dfs(i + 1);
        };
        dfs(1);
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<vector<int>> combine(int n, int k) {
        vector<vector<int>> ans;
        vector<int> t;
        function<void(int)> dfs = [&](int i) {
            if (t.size() == k) {
                ans.emplace_back(t);
                return;
            }
            if (i > n) {
                return;
            }
            for (int j = i; j <= n; ++j) {
                t.emplace_back(j);
                dfs(j + 1);
                t.pop_back();
            }
        };
        dfs(1);
        return ans;
    }
};
```

### **Go**

```go
func combine(n int, k int) (ans [][]int) {
	t := []int{}
	var dfs func(int)
	dfs = func(i int) {
		if len(t) == k {
			cp := make([]int, len(t))
			copy(cp, t)
			ans = append(ans, cp)
			return
		}
		if i > n {
			return
		}
		t = append(t, i)
		dfs(i + 1)
		t = t[:len(t)-1]
		dfs(i + 1)
	}
	dfs(1)
	return
}
```

```go
func combine(n int, k int) (ans [][]int) {
	t := []int{}
	var dfs func(int)
	dfs = func(i int) {
		if len(t) == k {
			cp := make([]int, len(t))
			copy(cp, t)
			ans = append(ans, cp)
			return
		}
		if i > n {
			return
		}
		for j := i; j <= n; j++ {
			t = append(t, j)
			dfs(j + 1)
			t = t[:len(t)-1]
		}
	}
	dfs(1)
	return
}
```

### **TypeScript**

```ts
function combine(n: number, k: number): number[][] {
    const ans: number[][] = [];
    const t: number[] = [];
    const dfs = (i: number) => {
        if (t.length === k) {
            ans.push(t.slice());
            return;
        }
        if (i > n) {
            return;
        }
        t.push(i);
        dfs(i + 1);
        t.pop();
        dfs(i + 1);
    };
    dfs(1);
    return ans;
}
```

```ts
function combine(n: number, k: number): number[][] {
    const ans: number[][] = [];
    const t: number[] = [];
    const dfs = (i: number) => {
        if (t.length === k) {
            ans.push(t.slice());
            return;
        }
        if (i > n) {
            return;
        }
        for (let j = i; j <= n; ++j) {
            t.push(j);
            dfs(j + 1);
            t.pop();
        }
    };
    dfs(1);
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(i: i32, n: i32, k: i32, t: &mut Vec<i32>, ans: &mut Vec<Vec<i32>>) {
        if t.len() == k as usize {
            ans.push(t.clone());
            return;
        }
        if i > n {
            return;
        }
        t.push(i);
        Self::dfs(i + 1, n, k, t, ans);
        t.pop();
        Self::dfs(i + 1, n, k, t, ans);
    }

    pub fn combine(n: i32, k: i32) -> Vec<Vec<i32>> {
        let mut ans = vec![];
        Self::dfs(1, n, k, &mut vec![], &mut ans);
        ans
    }
}
```

```rust
impl Solution {
    fn dfs(i: i32, n: i32, k: i32, t: &mut Vec<i32>, ans: &mut Vec<Vec<i32>>) {
        if t.len() == k as usize {
            ans.push(t.clone());
            return;
        }
        if i > n {
            return;
        }
        for j in i..=n {
            t.push(j);
            Self::dfs(j + 1, n, k, t, ans);
            t.pop();
        }
    }

    pub fn combine(n: i32, k: i32) -> Vec<Vec<i32>> {
        let mut ans = vec![];
        Self::dfs(1, n, k, &mut vec![], &mut ans);
        ans
    }
}
```

### **C#**

```cs
public class Solution {
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();
    private int n;
    private int k;

    public IList<IList<int>> Combine(int n, int k) {
        this.n = n;
        this.k = k;
        dfs(1);
        return ans;
    }

    private void dfs(int i) {
        if (t.Count == k) {
            ans.Add(new List<int>(t));
            return;
        }
        if (i > n) {
            return;
        }
        t.Add(i);
        dfs(i + 1);
        t.RemoveAt(t.Count - 1);
        dfs(i + 1);
    }
}
```

```cs
public class Solution {
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();
    private int n;
    private int k;

    public IList<IList<int>> Combine(int n, int k) {
        this.n = n;
        this.k = k;
        dfs(1);
        return ans;
    }

    private void dfs(int i) {
        if (t.Count == k) {
            ans.Add(new List<int>(t));
            return;
        }
        if (i > n) {
            return;
        }
        for (int j = i; j <= n; ++j) {
            t.Add(j);
            dfs(j + 1);
            t.RemoveAt(t.Count - 1);
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
