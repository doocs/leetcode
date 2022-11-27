# [587. Erect the Fence](https://leetcode.com/problems/erect-the-fence)

[中文文档](/solution/0500-0599/0587.Erect%20the%20Fence/README.md)

## Description

<p>You are given an array <code>trees</code> where <code>trees[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> represents the location of a tree in the garden.</p>

<p>Fence the entire garden using the minimum length of rope, as it is expensive. The garden is well-fenced only if <strong>all the trees are enclosed</strong>.</p>

<p>Return <em>the coordinates of trees that are exactly located on the fence perimeter</em>. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0587.Erect%20the%20Fence/images/erect2-plane.jpg" style="width: 400px; height: 393px;" />
<pre>
<strong>Input:</strong> trees = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
<strong>Output:</strong> [[1,1],[2,0],[4,2],[3,3],[2,4]]
<strong>Explanation:</strong> All the trees will be on the perimeter of the fence except the tree at [2, 2], which will be inside the fence.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0587.Erect%20the%20Fence/images/erect1-plane.jpg" style="width: 400px; height: 393px;" />
<pre>
<strong>Input:</strong> trees = [[1,2],[2,2],[4,2]]
<strong>Output:</strong> [[4,2],[2,2],[1,2]]
<strong>Explanation:</strong> The fence forms a line that passes through all the trees.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= trees.length &lt;= 3000</code></li>
	<li><code>trees[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 100</code></li>
	<li>All the given positions are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def outerTrees(self, trees: List[List[int]]) -> List[List[int]]:
        def cross(i, j, k):
            a, b, c = trees[i], trees[j], trees[k]
            return (b[0] - a[0]) * (c[1] - b[1]) - (b[1] - a[1]) * (c[0] - b[0])

        n = len(trees)
        if n < 4:
            return trees
        trees.sort()
        vis = [False] * n
        stk = [0]
        for i in range(1, n):
            while len(stk) > 1 and cross(stk[-2], stk[-1], i) < 0:
                vis[stk.pop()] = False
            vis[i] = True
            stk.append(i)
        m = len(stk)
        for i in range(n - 2, -1, -1):
            if vis[i]:
                continue
            while len(stk) > m and cross(stk[-2], stk[-1], i) < 0:
                stk.pop()
            stk.append(i)
        stk.pop()
        return [trees[i] for i in stk]
```

### **Java**

```java
class Solution {
    public int[][] outerTrees(int[][] trees) {
        int n = trees.length;
        if (n < 4) {
            return trees;
        }
        Arrays.sort(trees, (a, b) -> { return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]; });
        boolean[] vis = new boolean[n];
        int[] stk = new int[n + 10];
        int cnt = 1;
        for (int i = 1; i < n; ++i) {
            while (cnt > 1 && cross(trees[stk[cnt - 1]], trees[stk[cnt - 2]], trees[i]) < 0) {
                vis[stk[--cnt]] = false;
            }
            vis[i] = true;
            stk[cnt++] = i;
        }
        int m = cnt;
        for (int i = n - 1; i >= 0; --i) {
            if (vis[i]) {
                continue;
            }
            while (cnt > m && cross(trees[stk[cnt - 1]], trees[stk[cnt - 2]], trees[i]) < 0) {
                --cnt;
            }
            stk[cnt++] = i;
        }
        int[][] ans = new int[cnt - 1][2];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = trees[stk[i]];
        }
        return ans;
    }

    private int cross(int[] a, int[] b, int[] c) {
        return (b[0] - a[0]) * (c[1] - b[1]) - (b[1] - a[1]) * (c[0] - b[0]);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> outerTrees(vector<vector<int>>& trees) {
        int n = trees.size();
        if (n < 4) return trees;
        sort(trees.begin(), trees.end());
        vector<int> vis(n);
        vector<int> stk(n + 10);
        int cnt = 1;
        for (int i = 1; i < n; ++i) {
            while (cnt > 1 && cross(trees[stk[cnt - 1]], trees[stk[cnt - 2]], trees[i]) < 0) vis[stk[--cnt]] = false;
            vis[i] = true;
            stk[cnt++] = i;
        }
        int m = cnt;
        for (int i = n - 1; i >= 0; --i) {
            if (vis[i]) continue;
            while (cnt > m && cross(trees[stk[cnt - 1]], trees[stk[cnt - 2]], trees[i]) < 0) --cnt;
            stk[cnt++] = i;
        }
        vector<vector<int>> ans;
        for (int i = 0; i < cnt - 1; ++i) ans.push_back(trees[stk[i]]);
        return ans;
    }

    int cross(vector<int>& a, vector<int>& b, vector<int>& c) {
        return (b[0] - a[0]) * (c[1] - b[1]) - (b[1] - a[1]) * (c[0] - b[0]);
    }
};
```

### **Go**

```go
func outerTrees(trees [][]int) [][]int {
	n := len(trees)
	if n < 4 {
		return trees
	}
	sort.Slice(trees, func(i, j int) bool {
		if trees[i][0] == trees[j][0] {
			return trees[i][1] < trees[j][1]
		}
		return trees[i][0] < trees[j][0]
	})
	cross := func(i, j, k int) int {
		a, b, c := trees[i], trees[j], trees[k]
		return (b[0]-a[0])*(c[1]-b[1]) - (b[1]-a[1])*(c[0]-b[0])
	}
	vis := make([]bool, n)
	stk := []int{0}
	for i := 1; i < n; i++ {
		for len(stk) > 1 && cross(stk[len(stk)-1], stk[len(stk)-2], i) < 0 {
			vis[stk[len(stk)-1]] = false
			stk = stk[:len(stk)-1]
		}
		vis[i] = true
		stk = append(stk, i)
	}
	m := len(stk)
	for i := n - 1; i >= 0; i-- {
		if vis[i] {
			continue
		}
		for len(stk) > m && cross(stk[len(stk)-1], stk[len(stk)-2], i) < 0 {
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, i)
	}
	var ans [][]int
	for i := 0; i < len(stk)-1; i++ {
		ans = append(ans, trees[stk[i]])
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
