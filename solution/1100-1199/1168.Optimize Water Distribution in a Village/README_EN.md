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
        for i, j, c in pipes:
            pa, pb = find(i), find(j)
            if pa == pb:
                continue
            p[pa] = pb
            ans += c
            n -= 1
            if n == 0:
                break
        return ans
```

### **Java**

```java
class Solution {
    private int[] p;

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        var nums = new int[n + pipes.length][0];
        int j = 0;
        for (var pipe : pipes) {
            nums[j++] = pipe;
        }
        for (int i = 0; i < n; ++i) {
            nums[j++] = new int[] {0, i + 1, wells[i]};
        }
        Arrays.sort(nums, (a, b) -> a[2] - b[2]);
        p = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            p[i] = i;
        }
        int ans = 0;
        for (var x : nums) {
            int pa = find(x[0]), pb = find(x[1]);
            if (pa == pb) {
                continue;
            }
            ans += x[2];
            p[pa] = pb;
            if (--n == 0) {
                break;
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

### **TypeScript**

```ts
function minCostToSupplyWater(n: number, wells: number[], pipes: number[][]): number {
    for (let i = 0; i < n; ++i) {
        pipes.push([0, i + 1, wells[i]]);
    }
    pipes.sort((a, b) => a[2] - b[2]);
    const p = new Array(n + 1).fill(0).map((_, i) => i);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    let ans = 0;
    for (const [i, j, c] of pipes) {
        const pa = find(i);
        const pb = find(j);
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

### **...**

```

```

<!-- tabs:end -->
