# [1478. Allocate Mailboxes](https://leetcode.com/problems/allocate-mailboxes)

[中文文档](/solution/1400-1499/1478.Allocate%20Mailboxes/README.md)

## Description

<p>Given the array <code>houses</code> where <code>houses[i]</code> is the location of the <code>i<sup>th</sup></code> house along a street and an integer <code>k</code>, allocate <code>k</code> mailboxes in the street.</p>

<p>Return <em>the <strong>minimum</strong> total distance between each house and its nearest mailbox</em>.</p>

<p>The test cases are generated so that the answer fits in a 32-bit integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1478.Allocate%20Mailboxes/images/sample_11_1816.png" style="width: 454px; height: 154px;" />
<pre>
<strong>Input:</strong> houses = [1,4,8,10,20], k = 3
<strong>Output:</strong> 5
<strong>Explanation:</strong> Allocate mailboxes in position 3, 9 and 20.
Minimum total distance from each houses to nearest mailboxes is |3-1| + |4-3| + |9-8| + |10-9| + |20-20| = 5 
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1478.Allocate%20Mailboxes/images/sample_2_1816.png" style="width: 433px; height: 154px;" />
<pre>
<strong>Input:</strong> houses = [2,3,5,12,18], k = 2
<strong>Output:</strong> 9
<strong>Explanation:</strong> Allocate mailboxes in position 3 and 14.
Minimum total distance from each houses to nearest mailboxes is |2-3| + |3-3| + |5-3| + |12-14| + |18-14| = 9.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= houses.length &lt;= 100</code></li>
	<li><code>1 &lt;= houses[i] &lt;= 10<sup>4</sup></code></li>
	<li>All the integers of <code>houses</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minDistance(self, houses: List[int], k: int) -> int:
        houses.sort()
        n = len(houses)
        g = [[0] * n for _ in range(n)]
        for i in range(n - 2, -1, -1):
            for j in range(i + 1, n):
                g[i][j] = g[i + 1][j - 1] + houses[j] - houses[i]
        f = [[inf] * (k + 1) for _ in range(n)]
        for i in range(n):
            f[i][1] = g[0][i]
            for j in range(2, min(k + 1, i + 2)):
                for p in range(i):
                    f[i][j] = min(f[i][j], f[p][j - 1] + g[p + 1][i])
        return f[-1][k]
```

### **Java**

```java
class Solution {
    public int minDistance(int[] houses, int k) {
        Arrays.sort(houses);
        int n = houses.length;
        int[][] g = new int[n][n];
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = g[i + 1][j - 1] + houses[j] - houses[i];
            }
        }
        int[][] f = new int[n][k + 1];
        final int inf = 1 << 30;
        for (int[] e : f) {
            Arrays.fill(e, inf);
        }
        for (int i = 0; i < n; ++i) {
            f[i][1] = g[0][i];
            for (int j = 2; j <= k && j <= i + 1; ++j) {
                for (int p = 0; p < i; ++p) {
                    f[i][j] = Math.min(f[i][j], f[p][j - 1] + g[p + 1][i]);
                }
            }
        }
        return f[n - 1][k];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minDistance(vector<int>& houses, int k) {
        int n = houses.size();
        sort(houses.begin(), houses.end());
        int g[n][n];
        memset(g, 0, sizeof(g));
        for (int i = n - 2; ~i; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = g[i + 1][j - 1] + houses[j] - houses[i];
            }
        }
        int f[n][k + 1];
        memset(f, 0x3f, sizeof(f));
        for (int i = 0; i < n; ++i) {
            f[i][1] = g[0][i];
            for (int j = 1; j <= k && j <= i + 1; ++j) {
                for (int p = 0; p < i; ++p) {
                    f[i][j] = min(f[i][j], f[p][j - 1] + g[p + 1][i]);
                }
            }
        }
        return f[n - 1][k];
    }
};
```

### **Go**

```go
func minDistance(houses []int, k int) int {
	sort.Ints(houses)
	n := len(houses)
	g := make([][]int, n)
	f := make([][]int, n)
	const inf = 1 << 30
	for i := range g {
		g[i] = make([]int, n)
		f[i] = make([]int, k+1)
		for j := range f[i] {
			f[i][j] = inf
		}
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			g[i][j] = g[i+1][j-1] + houses[j] - houses[i]
		}
	}
	for i := 0; i < n; i++ {
		f[i][1] = g[0][i]
		for j := 2; j <= k && j <= i+1; j++ {
			for p := 0; p < i; p++ {
				f[i][j] = min(f[i][j], f[p][j-1]+g[p+1][i])
			}
		}
	}
	return f[n-1][k]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
