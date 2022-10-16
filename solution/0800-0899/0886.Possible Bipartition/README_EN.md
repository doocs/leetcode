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
<strong>Explanation:</strong> group1 [1,4] and group2 [2,3].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, dislikes = [[1,2],[1,3],[2,3]]
<strong>Output:</strong> false
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2000</code></li>
	<li><code>0 &lt;= dislikes.length &lt;= 10<sup>4</sup></code></li>
	<li><code>dislikes[i].length == 2</code></li>
	<li><code>1 &lt;= dislikes[i][j] &lt;= n</code></li>
	<li><code>a<sub>i</sub> &lt; b<sub>i</sub></code></li>
	<li>All the pairs of <code>dislikes</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

Union find.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def possibleBipartition(self, n: int, dislikes: List[List[int]]) -> bool:
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        dis = defaultdict(list)
        for a, b in dislikes:
            a, b = a - 1, b - 1
            dis[a].append(b)
            dis[b].append(a)

        for i in range(n):
            for j in dis[i]:
                if find(i) == find(j):
                    return False
                p[find(j)] = find(dis[i][0])
        return True
```

### **Java**

```java
class Solution {
    private int[] p;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        p = new int[n];
        List<Integer>[] dis = new List[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            dis[i] = new ArrayList<>();
        }
        for (int[] d : dislikes) {
            int a = d[0] - 1, b = d[1] - 1;
            dis[a].add(b);
            dis[b].add(a);
        }
        for (int i = 0; i < n; ++i) {
            for (int j : dis[i]) {
                if (find(i) == find(j)) {
                    return false;
                }
                p[find(j)] = find(dis[i].get(0));
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
    vector<int> p;

    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        unordered_map<int, vector<int>> dis;
        for (auto& d : dislikes) {
            int a = d[0] - 1, b = d[1] - 1;
            dis[a].push_back(b);
            dis[b].push_back(a);
        }
        for (int i = 0; i < n; ++i) {
            for (int j : dis[i]) {
                if (find(i) == find(j)) return false;
                p[find(j)] = find(dis[i][0]);
            }
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
func possibleBipartition(n int, dislikes [][]int) bool {
	p := make([]int, n)
	dis := make([][]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, d := range dislikes {
		a, b := d[0]-1, d[1]-1
		dis[a] = append(dis[a], b)
		dis[b] = append(dis[b], a)
	}
	for i := 0; i < n; i++ {
		for _, j := range dis[i] {
			if find(i) == find(j) {
				return false
			}
			p[find(j)] = find(dis[i][0])
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
