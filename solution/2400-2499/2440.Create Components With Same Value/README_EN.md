# [2440. Create Components With Same Value](https://leetcode.com/problems/create-components-with-same-value)

[中文文档](/solution/2400-2499/2440.Create%20Components%20With%20Same%20Value/README.md)

## Description

<p>There is an undirected tree with <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>.</p>

<p>You are given a <strong>0-indexed</strong> integer array <code><font face="monospace">nums</font></code> of length <code>n</code> where <code>nums[i]</code> represents the value of the <code>i<sup>th</sup></code> node. You are also given a 2D integer array <code>edges</code> of length <code>n - 1</code> where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree.</p>

<p>You are allowed to <strong>delete</strong> some edges, splitting the tree into multiple connected components. Let the <strong>value</strong> of a component be the sum of <strong>all</strong> <code>nums[i]</code> for which node <code>i</code> is in the component.</p>

<p>Return<em> the <strong>maximum</strong> number of edges you can delete, such that every connected component in the tree has the same value.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2440.Create%20Components%20With%20Same%20Value/images/diagramdrawio.png" style="width: 441px; height: 351px;" />
<pre>
<strong>Input:</strong> nums = [6,2,2,2,6], edges = [[0,1],[1,2],[1,3],[3,4]] 
<strong>Output:</strong> 2 
<strong>Explanation:</strong> The above figure shows how we can delete the edges [0,1] and [3,4]. The created components are nodes [0], [1,2,3] and [4]. The sum of the values in each component equals 6. It can be proven that no better deletion exists, so the answer is 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2], edges = []
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no edges to be deleted.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>nums.length == n</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= edges[i][0], edges[i][1] &lt;= n - 1</code></li>
	<li><code>edges</code> represents a valid tree.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def componentValue(self, nums: List[int], edges: List[List[int]]) -> int:
        def dfs(i, fa):
            x = nums[i]
            for j in g[i]:
                if j != fa:
                    y = dfs(j, i)
                    if y == -1:
                        return -1
                    x += y
            if x > t:
                return -1
            return x if x < t else 0

        n = len(nums)
        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        s = sum(nums)
        mx = max(nums)
        for k in range(min(n, s // mx), 1, -1):
            if s % k == 0:
                t = s // k
                if dfs(0, -1) == 0:
                    return k - 1
        return 0
```

### **Java**

```java
class Solution {
    private List<Integer>[] g;
    private int[] nums;
    private int t;

    public int componentValue(int[] nums, int[][] edges) {
        int n = nums.length;
        g = new List[n];
        this.nums = nums;
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        int s = sum(nums), mx = max(nums);
        for (int k = Math.min(n, s / mx); k > 1; --k) {
            if (s % k == 0) {
                t = s / k;
                if (dfs(0, -1) == 0) {
                    return k - 1;
                }
            }
        }
        return 0;
    }

    private int dfs(int i, int fa) {
        int x = nums[i];
        for (int j : g[i]) {
            if (j != fa) {
                int y = dfs(j, i);
                if (y == -1) {
                    return -1;
                }
                x += y;
            }
        }
        if (x > t) {
            return -1;
        }
        return x < t ? x : 0;
    }

    private int sum(int[] arr) {
        int x = 0;
        for (int v : arr) {
            x += v;
        }
        return x;
    }

    private int max(int[] arr) {
        int x = arr[0];
        for (int v : arr) {
            x = Math.max(x, v);
        }
        return x;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int componentValue(vector<int>& nums, vector<vector<int>>& edges) {
        int n = nums.size();
        int s = accumulate(nums.begin(), nums.end(), 0);
        int mx = *max_element(nums.begin(), nums.end());
        int t = 0;
        unordered_map<int, vector<int>> g;
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        function<int(int, int)> dfs = [&](int i, int fa) -> int {
            int x = nums[i];
            for (int j : g[i]) {
                if (j != fa) {
                    int y = dfs(j, i);
                    if (y == -1) return -1;
                    x += y;
                }
            }
            if (x > t) return -1;
            return x < t ? x : 0;
        };
        for (int k = min(n, s / mx); k > 1; --k) {
            if (s % k == 0) {
                t = s / k;
                if (dfs(0, -1) == 0) {
                    return k - 1;
                }
            }
        }
        return 0;
    }
};
```

### **Go**

```go
func componentValue(nums []int, edges [][]int) int {
	s, mx := 0, 0
	for _, x := range nums {
		s += x
		mx = max(mx, x)
	}
	n := len(nums)
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	t := 0
	var dfs func(int, int) int
	dfs = func(i, fa int) int {
		x := nums[i]
		for _, j := range g[i] {
			if j != fa {
				y := dfs(j, i)
				if y == -1 {
					return -1
				}
				x += y
			}
		}
		if x > t {
			return -1
		}
		if x < t {
			return x
		}
		return 0
	}
	for k := min(n, s/mx); k > 1; k-- {
		if s%k == 0 {
			t = s / k
			if dfs(0, -1) == 0 {
				return k - 1
			}
		}
	}
	return 0
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
