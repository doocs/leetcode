# [1057. Campus Bikes](https://leetcode.com/problems/campus-bikes)

[中文文档](/solution/1000-1099/1057.Campus%20Bikes/README.md)

## Description

<p>On a campus represented on the X-Y plane, there are <code>n</code> workers and <code>m</code> bikes, with <code>n &lt;= m</code>.</p>

<p>You are given an array <code>workers</code> of length <code>n</code> where <code>workers[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> is the position of the <code>i<sup>th</sup></code> worker. You are also given an array <code>bikes</code> of length <code>m</code> where <code>bikes[j] = [x<sub>j</sub>, y<sub>j</sub>]</code> is the position of the <code>j<sup>th</sup></code> bike. All the given positions are <strong>unique</strong>.</p>

<p>Assign a bike to each worker. Among the available bikes and workers, we choose the <code>(worker<sub>i</sub>, bike<sub>j</sub>)</code> pair with the shortest <strong>Manhattan distance</strong> between each other and assign the bike to that worker.</p>

<p>If there are multiple <code>(worker<sub>i</sub>, bike<sub>j</sub>)</code> pairs with the same shortest <strong>Manhattan distance</strong>, we choose the pair with <strong>the smallest worker index</strong>. If there are multiple ways to do that, we choose the pair with <strong>the smallest bike index</strong>. Repeat this process until there are no available workers.</p>

<p>Return <em>an array </em><code>answer</code><em> of length </em><code>n</code><em>, where </em><code>answer[i]</code><em> is the index (<strong>0-indexed</strong>) of the bike that the </em><code>i<sup>th</sup></code><em> worker is assigned to</em>.</p>

<p>The <strong>Manhattan distance</strong> between two points <code>p1</code> and <code>p2</code> is <code>Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1057.Campus%20Bikes/images/1261_example_1_v2.png" style="width: 376px; height: 366px;" />
<pre>
<strong>Input:</strong> workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
<strong>Output:</strong> [1,0]
<strong>Explanation:</strong> Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1057.Campus%20Bikes/images/1261_example_2_v2.png" style="width: 376px; height: 366px;" />
<pre>
<strong>Input:</strong> workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
<strong>Output:</strong> [0,2,1]
<strong>Explanation:</strong> Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == workers.length</code></li>
	<li><code>m == bikes.length</code></li>
	<li><code>1 &lt;= n &lt;= m &lt;= 1000</code></li>
	<li><code>workers[i].length == bikes[j].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt; 1000</code></li>
	<li><code>0 &lt;= x<sub>j</sub>, y<sub>j</sub> &lt; 1000</code></li>
	<li>All worker and bike locations are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        n, m = len(workers), len(bikes)
        arr = []
        for i, j in product(range(n), range(m)):
            dist = abs(workers[i][0] - bikes[j][0]) + \
                abs(workers[i][1] - bikes[j][1])
            arr.append((dist, i, j))
        arr.sort()
        vis1 = [False] * n
        vis2 = [False] * m
        ans = [0] * n
        for _, i, j in arr:
            if not vis1[i] and not vis2[j]:
                vis1[i] = vis2[j] = True
                ans[i] = j
        return ans
```

### **Java**

```java
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length, m = bikes.length;
        int[][] arr = new int[m * n][3];
        for (int i = 0, k = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                int dist
                    = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                arr[k++] = new int[] {dist, i, j};
            }
        }
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[2] - b[2];
        });
        boolean[] vis1 = new boolean[n];
        boolean[] vis2 = new boolean[m];
        int[] ans = new int[n];
        for (var e : arr) {
            int i = e[1], j = e[2];
            if (!vis1[i] && !vis2[j]) {
                vis1[i] = true;
                vis2[j] = true;
                ans[i] = j;
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
    vector<int> assignBikes(vector<vector<int>>& workers, vector<vector<int>>& bikes) {
        int n = workers.size(), m = bikes.size();
        vector<tuple<int, int, int>> arr(n * m);
        for (int i = 0, k = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                int dist = abs(workers[i][0] - bikes[j][0]) + abs(workers[i][1] - bikes[j][1]);
                arr[k++] = {dist, i, j};
            }
        }
        sort(arr.begin(), arr.end());
        vector<bool> vis1(n), vis2(m);
        vector<int> ans(n);
        for (auto& [_, i, j] : arr) {
            if (!vis1[i] && !vis2[j]) {
                vis1[i] = true;
                vis2[j] = true;
                ans[i] = j;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func assignBikes(workers [][]int, bikes [][]int) []int {
	n, m := len(workers), len(bikes)
	type tuple struct{ d, i, j int }
	arr := make([]tuple, n*m)
	for i, k := 0, 0; i < n; i++ {
		for j := 0; j < m; j++ {
			d := abs(workers[i][0]-bikes[j][0]) + abs(workers[i][1]-bikes[j][1])
			arr[k] = tuple{d, i, j}
			k++
		}
	}
	sort.Slice(arr, func(i, j int) bool {
		if arr[i].d != arr[j].d {
			return arr[i].d < arr[j].d
		}
		if arr[i].i != arr[j].i {
			return arr[i].i < arr[j].i
		}
		return arr[i].j < arr[j].j
	})
	vis1, vis2 := make([]bool, n), make([]bool, m)
	ans := make([]int, n)
	for _, e := range arr {
		i, j := e.i, e.j
		if !vis1[i] && !vis2[j] {
			vis1[i], vis2[j] = true, true
			ans[i] = j
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
