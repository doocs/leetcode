# [1168. Optimize Water Distribution in a Village](https://leetcode.com/problems/optimize-water-distribution-in-a-village)

[中文文档](/solution/1100-1199/1168.Optimize%20Water%20Distribution%20in%20a%20Village/README.md)

## Description

<p>There are <code>n</code> houses in a village. We want to supply water for all the houses by building wells and laying pipes.</p>

<p>For each house <code>i</code>, we can either build a well inside it directly with cost <code>wells[i - 1]</code> (note the <code>-1</code> due to <strong>0-indexing</strong>), or pipe in water from another well to it. The costs to lay pipes between houses are given by the array <code>pipes</code> where each <code>pipes[j] = [house1<sub>j</sub>, house2<sub>j</sub>, cost<sub>j</sub>]</code> represents the cost to connect <code>house1<sub>j</sub></code> and <code>house2<sub>j</sub></code> together using a pipe. Connections are bidirectional, and there could be multiple valid connections between the same two houses with different costs.</p>

<p>Return <em>the minimum total cost to supply water to all houses</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1168.Optimize%20Water%20Distribution%20in%20a%20Village/images/1359_ex1.png" style="width: 189px; height: 196px;" />
<pre>
<strong>Input:</strong> n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The image shows the costs of connecting houses using pipes.
The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, wells = [1,1], pipes = [[1,2,1],[1,2,2]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can supply water with cost two using one of the three options:
Option 1:
  - Build a well inside house 1 with cost 1.
  - Build a well inside house 2 with cost 1.
The total cost will be 2.
Option 2:
  - Build a well inside house 1 with cost 1.
  - Connect house 2 with house 1 with cost 1.
The total cost will be 2.
Option 3:
  - Build a well inside house 2 with cost 1.
  - Connect house 1 with house 2 with cost 1.
The total cost will be 2.
Note that we can connect houses 1 and 2 with cost 1 or with cost 2 but we will always choose <strong>the cheapest option</strong>. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>wells.length == n</code></li>
	<li><code>0 &lt;= wells[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= pipes.length &lt;= 10<sup>4</sup></code></li>
	<li><code>pipes[j].length == 3</code></li>
	<li><code>1 &lt;= house1<sub>j</sub>, house2<sub>j</sub> &lt;= n</code></li>
	<li><code>0 &lt;= cost<sub>j</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>house1<sub>j</sub> != house2<sub>j</sub></code></li>
</ul>

## Solutions

**Solution 1: Kruskal's Algorithm (Minimum Spanning Tree)**

We assume that there is a well with the number $0$. Then we can consider the connectivity between each house and the well $0$ as an edge, and the weight of each edge is the cost of building a well for that house. At the same time, we consider the connectivity between each house as an edge, and the weight of each edge is the cost of laying a pipe. In this way, we can transform this problem into finding the minimum spanning tree of an undirected graph.

We can use Kruskal's algorithm to find the minimum spanning tree of the undirected graph. First, we add an edge between the well $0$ and the house to the $pipes$ array, and then sort the $pipes$ array in ascending order of edge weights. Then, we traverse each edge. If this edge connects different connected components, we choose this edge and merge the corresponding connected components. If the current connected component is exactly $1$, then we have found the minimum spanning tree. The answer at this time is the current edge weight, and we return it.

The time complexity is $O((m + n) \times \log (m + n))$, and the space complexity is $O(m + n)$. Here, $m$ and $n$ are the lengths of the $pipes$ array and the $wells$ array, respectively.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minCostToSupplyWater(
        self, n: int, wells: List[int], pipes: List[List[int]]
    ) -> int:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i, w in enumerate(wells, 1):
            pipes.append([0, i, w])
        pipes.sort(key=lambda x: x[2])
        p = list(range(n + 1))
        ans = 0
        for a, b, c in pipes:
            pa, pb = find(a), find(b)
            if pa != pb:
                p[pa] = pb
                n -= 1
                ans += c
                if n == 0:
                    return ans
```

```python
class UnionFind:
    __slots__ = ("p", "size")

    def __init__(self, n):
        self.p = list(range(n))
        self.size = [1] * n

    def find(self, x: int) -> int:
        if self.p[x] != x:
            self.p[x] = self.find(self.p[x])
        return self.p[x]

    def union(self, a: int, b: int) -> bool:
        pa, pb = self.find(a), self.find(b)
        if pa == pb:
            return False
        if self.size[pa] > self.size[pb]:
            self.p[pb] = pa
            self.size[pa] += self.size[pb]
        else:
            self.p[pa] = pb
            self.size[pb] += self.size[pa]
        return True


class Solution:
    def minCostToSupplyWater(
        self, n: int, wells: List[int], pipes: List[List[int]]
    ) -> int:
        for i, w in enumerate(wells, 1):
            pipes.append([0, i, w])
        pipes.sort(key=lambda x: x[2])
        uf = UnionFind(n + 1)
        ans = 0
        for a, b, c in pipes:
            if uf.union(a, b):
                ans += c
                n -= 1
                if n == 0:
                    return ans
```

### **Java**

```java
class Solution {
    private int[] p;

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        int[][] nums = Arrays.copyOf(pipes, pipes.length + n);
        for (int i = 0; i < n; i++) {
            nums[pipes.length + i] = new int[]{0, i + 1, wells[i]};
        }
        Arrays.sort(nums, (a, b) -> a[2] - b[2]);
        p = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            p[i] = i;
        }
        int ans = 0;
        for (var x : nums) {
            int a = x[0], b = x[1], c = x[2];
            int pa = find(a), pb = find(b);
            if (pa != pb) {
                p[pa] = pb;
                ans += c;
                if (--n == 0) {
                    return ans;
                }
            }
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

```java
class UnionFind {
    private int[] p;
    private int[] size;

    public UnionFind(int n) {
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    public boolean union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
    }
}

class Solution {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        int[][] nums = Arrays.copyOf(pipes, pipes.length + n);
        for (int i = 0; i < n; i++) {
            nums[pipes.length + i] = new int[]{0, i + 1, wells[i]};
        }
        Arrays.sort(nums, (a, b) -> a[2] - b[2]);
        UnionFind uf = new UnionFind(n + 1);
        int ans = 0;
        for (var x : nums) {
            int a = x[0], b = x[1], c = x[2];
            if (uf.union(a, b)) {
                ans += c;
                if (--n == 0) {
                    break;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minCostToSupplyWater(int n, vector<int>& wells, vector<vector<int>>& pipes) {
        for (int i = 0; i < n; ++i) {
            pipes.push_back({0, i + 1, wells[i]});
        }
        sort(pipes.begin(), pipes.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[2] < b[2];
        });
        int p[n + 1];
        iota(p, p + n + 1, 0);
        function<int(int)> find = [&](int x) {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        int ans = 0;
        for (const auto& x : pipes) {
            int pa = find(x[0]), pb = find(x[1]);
            if (pa == pb) {
                continue;
            }
            p[pa] = pb;
            ans += x[2];
            if (--n == 0) {
                break;
            }
        }
        return ans;
    }
};
```

```cpp
class UnionFind {
public:
    UnionFind(int n) {
        p = vector<int>(n);
        size = vector<int>(n, 1);
        iota(p.begin(), p.end(), 0);
    }

    bool unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

private:
    vector<int> p, size;
};

class Solution {
public:
    int minCostToSupplyWater(int n, vector<int>& wells, vector<vector<int>>& pipes) {
        for (int i = 0; i < n; ++i) {
            pipes.push_back({0, i + 1, wells[i]});
        }
        sort(pipes.begin(), pipes.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[2] < b[2];
        });
        UnionFind uf(n + 1);
        int ans = 0;
        for (const auto& x : pipes) {
            if (uf.unite(x[0], x[1])) {
                ans += x[2];
                if (--n == 0) {
                    break;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minCostToSupplyWater(n int, wells []int, pipes [][]int) (ans int) {
	for i, w := range wells {
		pipes = append(pipes, []int{0, i + 1, w})
	}
	sort.Slice(pipes, func(i, j int) bool { return pipes[i][2] < pipes[j][2] })
	p := make([]int, n+1)
	for i := range p {
		p[i] = i
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}

	for _, x := range pipes {
		pa, pb := find(x[0]), find(x[1])
		if pa == pb {
			continue
		}
		p[pa] = pb
		ans += x[2]
		n--
		if n == 0 {
			break
		}
	}
	return
}
```

```go
type unionFind struct {
	p, size []int
}

func newUnionFind(n int) *unionFind {
	p := make([]int, n)
	size := make([]int, n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	return &unionFind{p, size}
}

func (uf *unionFind) find(x int) int {
	if uf.p[x] != x {
		uf.p[x] = uf.find(uf.p[x])
	}
	return uf.p[x]
}

func (uf *unionFind) union(a, b int) bool {
	pa, pb := uf.find(a), uf.find(b)
	if pa == pb {
		return false
	}
	if uf.size[pa] > uf.size[pb] {
		uf.p[pb] = pa
		uf.size[pa] += uf.size[pb]
	} else {
		uf.p[pa] = pb
		uf.size[pb] += uf.size[pa]
	}
	return true
}

func minCostToSupplyWater(n int, wells []int, pipes [][]int) (ans int) {
	for i, w := range wells {
		pipes = append(pipes, []int{0, i + 1, w})
	}
	sort.Slice(pipes, func(i, j int) bool { return pipes[i][2] < pipes[j][2] })
	uf := newUnionFind(n + 1)
	for _, x := range pipes {
		if uf.union(x[0], x[1]) {
			ans += x[2]
			n--
			if n == 0 {
				break
			}
		}
	}
	return
}
```

### **TypeScript**

```ts
function minCostToSupplyWater(n: number, wells: number[], pipes: number[][]): number {
    for (let i = 0; i < n; ++i) {
        pipes.push([0, i + 1, wells[i]]);
    }
    pipes.sort((a, b) => a[2] - b[2]);
    const p = Array(n + 1)
        .fill(0)
        .map((_, i) => i);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    let ans = 0;
    for (const [a, b, c] of pipes) {
        const pa = find(a);
        const pb = find(b);
        if (pa === pb) {
            continue;
        }
        p[pa] = pb;
        ans += c;
        if (--n === 0) {
            break;
        }
    }
    return ans;
}
```

```ts
class UnionFind {
    private p: number[];
    private size: number[];

    constructor(n: number) {
        this.p = Array(n)
            .fill(0)
            .map((_, i) => i);
        this.size = Array(n).fill(1);
    }

    find(x: number): number {
        if (this.p[x] !== x) {
            this.p[x] = this.find(this.p[x]);
        }
        return this.p[x];
    }

    union(a: number, b: number): boolean {
        const pa = this.find(a);
        const pb = this.find(b);
        if (pa === pb) {
            return false;
        }
        if (this.size[pa] > this.size[pb]) {
            this.p[pb] = pa;
            this.size[pa] += this.size[pb];
        } else {
            this.p[pa] = pb;
            this.size[pb] += this.size[pa];
        }
        return true;
    }
}

function minCostToSupplyWater(n: number, wells: number[], pipes: number[][]): number {
    for (let i = 0; i < n; ++i) {
        pipes.push([0, i + 1, wells[i]]);
    }
    pipes.sort((a, b) => a[2] - b[2]);
    const uf = new UnionFind(n + 1);
    let ans = 0;
    for (const [a, b, c] of pipes) {
        if (uf.union(a, b)) {
            ans += c;
            if (--n === 0) {
                break;
            }
        }
    }
    return ans;
}
```

### **Rust**

```rust
struct UnionFind {
    p: Vec<usize>,
    size: Vec<usize>,
}

impl UnionFind {
    fn new(n: usize) -> Self {
        let p: Vec<usize> = (0..n).collect();
        let size = vec![1; n];
        UnionFind { p, size }
    }

    fn find(&mut self, x: usize) -> usize {
        if self.p[x] != x {
            self.p[x] = self.find(self.p[x]);
        }
        self.p[x]
    }

    fn union(&mut self, a: usize, b: usize) -> bool {
        let pa = self.find(a);
        let pb = self.find(b);
        if pa == pb {
            false
        } else if self.size[pa] > self.size[pb] {
            self.p[pb] = pa;
            self.size[pa] += self.size[pb];
            true
        } else {
            self.p[pa] = pb;
            self.size[pb] += self.size[pa];
            true
        }
    }
}

impl Solution {
    pub fn min_cost_to_supply_water(n: i32, wells: Vec<i32>, pipes: Vec<Vec<i32>>) -> i32 {
        let n = n as usize;
        let mut pipes = pipes;
        for i in 0..n {
            pipes.push(vec![0, (i + 1) as i32, wells[i]]);
        }
        pipes.sort_by(|a, b| a[2].cmp(&b[2]));
        let mut uf = UnionFind::new(n + 1);
        let mut ans = 0;
        for pipe in pipes {
            let a = pipe[0] as usize;
            let b = pipe[1] as usize;
            let c = pipe[2];
            if uf.union(a, b) {
                ans += c;
                if n == 0 {
                    break;
                }
            }
        }
        ans
    }
}
```

### **...**

```

```

<!-- tabs:end -->
