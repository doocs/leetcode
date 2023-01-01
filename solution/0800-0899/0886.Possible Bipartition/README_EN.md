# [886. Possible Bipartition](https://leetcode.com/problems/possible-bipartition)

[中文文档](/solution/0800-0899/0886.Possible%20Bipartition/README.md)

## Description

<p>We want to split a group of <code>n</code> people (labeled from <code>1</code> to <code>n</code>) into two groups of <strong>any size</strong>. Each person may dislike some other people, and they should not go into the same group.</p>

<p>Given the integer <code>n</code> and the array <code>dislikes</code> where <code>dislikes[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that the person labeled <code>a<sub>i</sub></code> does not like the person labeled <code>b<sub>i</sub></code>, return <code>true</code> <em>if it is possible to split everyone into two groups in this way</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 4, dislikes = [[1,2],[1,3],[2,4]]
<strong>Output:</strong> true
<strong>Explanation:</strong> The first group has [1,4], and the second group has [2,3].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, dislikes = [[1,2],[1,3],[2,3]]
<strong>Output:</strong> false
<strong>Explanation:</strong> We need at least 3 groups to divide them. We cannot put them in two groups.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2000</code></li>
	<li><code>0 &lt;= dislikes.length &lt;= 10<sup>4</sup></code></li>
	<li><code>dislikes[i].length == 2</code></li>
	<li><code>1 &lt;= a<sub>i</sub> &lt; b<sub>i</sub> &lt;= n</code></li>
	<li>All the pairs of <code>dislikes</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

Union find.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def possibleBipartition(self, n: int, dislikes: List[List[int]]) -> bool:
        def dfs(i, c):
            color[i] = c
            for j in g[i]:
                if color[j] == c:
                    return False
                if color[j] == 0 and not dfs(j, 3 - c):
                    return False
            return True

        g = defaultdict(list)
        color = [0] * n
        for a, b in dislikes:
            a, b = a - 1, b - 1
            g[a].append(b)
            g[b].append(a)
        return all(c or dfs(i, 1) for i, c in enumerate(color))
```

```python
class Solution:
    def possibleBipartition(self, n: int, dislikes: List[List[int]]) -> bool:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        g = defaultdict(list)
        for a, b in dislikes:
            a, b = a - 1, b - 1
            g[a].append(b)
            g[b].append(a)
        p = list(range(n))
        for i in range(n):
            for j in g[i]:
                if find(i) == find(j):
                    return False
                p[find(j)] = find(g[i][0])
        return True
```

### **Java**

```java
class Solution {
    private List<Integer>[] g;
    private int[] color;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        g = new List[n];
        color = new int[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : dislikes) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].add(b);
            g[b].add(a);
        }
        for (int i = 0; i < n; ++i) {
            if (color[i] == 0) {
                if (!dfs(i, 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int i, int c) {
        color[i] = c;
        for (int j : g[i]) {
            if (color[j] == c) {
                return false;
            }
            if (color[j] == 0 && !dfs(j, 3 - c)) {
                return false;
            }
        }
        return true;
    }
}
```

```java
class Solution {
    private int[] p;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        p = new int[n];
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (var e : dislikes) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].add(b);
            g[b].add(a);
        }
        for (int i = 0; i < n; ++i) {
            for (int j : g[i]) {
                if (find(i) == find(j)) {
                    return false;
                }
                p[find(j)] = find(g[i].get(0));
            }
        }
        return true;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        unordered_map<int, vector<int>> g;
        for (auto& e : dislikes) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<int> color(n);
        function<bool(int, int)> dfs = [&](int i, int c) -> bool {
            color[i] = c;
            for (int j : g[i]) {
                if (!color[j] && !dfs(j, 3 - c)) return false;
                if (color[j] == c) return false;
            }
            return true;
        };
        for (int i = 0; i < n; ++i) {
            if (!color[i] && !dfs(i, 1)) return false;
        }
        return true;
    }
};
```

```cpp
class Solution {
public:
    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        unordered_map<int, vector<int>> g;
        for (auto& e : dislikes) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].push_back(b);
            g[b].push_back(a);
        }
        function<int(int)> find = [&](int x) -> int {
            if (p[x] != x) p[x] = find(p[x]);
            return p[x];
        };
        for (int i = 0; i < n; ++i) {
            for (int j : g[i]) {
                if (find(i) == find(j)) return false;
                p[find(j)] = find(g[i][0]);
            }
        }
        return true;
    }
};
```

### **Go**

```go
func possibleBipartition(n int, dislikes [][]int) bool {
	g := make([][]int, n)
	for _, e := range dislikes {
		a, b := e[0]-1, e[1]-1
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	color := make([]int, n)
	var dfs func(int, int) bool
	dfs = func(i, c int) bool {
		color[i] = c
		for _, j := range g[i] {
			if color[j] == c {
				return false
			}
			if color[j] == 0 && !dfs(j, 3-c) {
				return false
			}
		}
		return true
	}
	for i, c := range color {
		if c == 0 && !dfs(i, 1) {
			return false
		}
	}
	return true
}
```

```go
func possibleBipartition(n int, dislikes [][]int) bool {
	p := make([]int, n)
	g := make([][]int, n)
	for i := range p {
		p[i] = i
	}
	for _, e := range dislikes {
		a, b := e[0]-1, e[1]-1
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for i := 0; i < n; i++ {
		for _, j := range g[i] {
			if find(i) == find(j) {
				return false
			}
			p[find(j)] = find(g[i][0])
		}
	}
	return true
}
```

### **TypeScript**

```ts
function possibleBipartition(n: number, dislikes: number[][]): boolean {
    const color = new Array(n + 1).fill(0);
    const g = Array.from({ length: n + 1 }, () => []);
    const dfs = (i: number, v: number) => {
        color[i] = v;
        for (const j of g[i]) {
            if (color[j] === color[i] || (color[j] === 0 && dfs(j, 3 ^ v))) {
                return true;
            }
        }
        return false;
    };
    for (const [a, b] of dislikes) {
        g[a].push(b);
        g[b].push(a);
    }
    for (let i = 1; i <= n; i++) {
        if (color[i] === 0 && dfs(i, 1)) {
            return false;
        }
    }
    return true;
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(i: usize, v: usize, color: &mut Vec<usize>, g: &Vec<Vec<usize>>) -> bool {
        color[i] = v;
        for &j in (*g[i]).iter() {
            if color[j] == color[i] || color[j] == 0 && Self::dfs(j, v ^ 3, color, g) {
                return true;
            }
        }
        false
    }

    pub fn possible_bipartition(n: i32, dislikes: Vec<Vec<i32>>) -> bool {
        let n = n as usize;
        let mut color = vec![0; n + 1];
        let mut g = vec![Vec::new(); n + 1];
        for d in dislikes.iter() {
            let (i, j) = (d[0] as usize, d[1] as usize);
            g[i].push(j);
            g[j].push(i);
        }
        for i in 1..=n {
            if color[i] == 0 && Self::dfs(i, 1, &mut color, &g) {
                return false;
            }
        }
        true
    }
}
```

### **...**

```

```

<!-- tabs:end -->
