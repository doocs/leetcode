# [568. Maximum Vacation Days](https://leetcode.com/problems/maximum-vacation-days)

[中文文档](/solution/0500-0599/0568.Maximum%20Vacation%20Days/README.md)

## Description

<p>LeetCode wants to give one of its best employees the option to travel among <code>n</code> cities to collect algorithm problems. But all work and no play makes Jack a dull boy, you could take vacations in some particular cities and weeks. Your job is to schedule the traveling to maximize the number of vacation days you could take, but there are certain rules and restrictions you need to follow.</p>

<p>Rules and restrictions:</p>

<ol>
	<li>You can only travel among <code>n</code> cities, represented by indexes from <code>0</code> to <code>n - 1</code>. Initially, you are in the city indexed <code>0</code> on <strong>Monday</strong>.</li>
	<li>The cities are connected by flights. The flights are represented as an <code>n x n</code> matrix (not necessarily symmetrical), called <code>flights</code> representing the airline status from the city <code>i</code> to the city <code>j</code>. If there is no flight from the city <code>i</code> to the city <code>j</code>, <code>flights[i][j] == 0</code>; Otherwise, <code>flights[i][j] == 1</code>. Also, <code>flights[i][i] == 0</code> for all <code>i</code>.</li>
	<li>You totally have <code>k</code> weeks (each week has <strong>seven days</strong>) to travel. You can only take flights at most once per day and can only take flights on each week&#39;s Monday morning. Since flight time is so short, we do not consider the impact of flight time.</li>
	<li>For each city, you can only have restricted vacation days in different weeks, given an <code>n x k</code> matrix called <code>days</code> representing this relationship. For the value of <code>days[i][j]</code>, it represents the maximum days you could take a vacation in the city <code>i</code> in the week <code>j</code>.</li>
	<li>You could stay in a city beyond the number of vacation days, but you should work on the extra days, which will not be counted as vacation days.</li>
	<li>If you fly from city <code>A</code> to city <code>B</code> and take the vacation on that day, the deduction towards vacation days will count towards the vacation days of city <code>B</code> in that week.</li>
	<li>We do not consider the impact of flight hours on the calculation of vacation days.</li>
</ol>

<p>Given the two matrices <code>flights</code> and <code>days</code>, return <em>the maximum vacation days you could take during </em><code>k</code><em> weeks</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[1,3,1],[6,0,3],[3,3,3]]
<strong>Output:</strong> 12
<strong>Explanation:</strong>
One of the best strategies is:
1st week : fly from city 0 to city 1 on Monday, and play 6 days and work 1 day.
(Although you start at city 0, we could also fly to and start at other cities since it is Monday.)
2nd week : fly from city 1 to city 2 on Monday, and play 3 days and work 4 days.
3rd week : stay at city 2, and play 3 days and work 4 days.
Ans = 6 + 3 + 3 = 12.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> flights = [[0,0,0],[0,0,0],[0,0,0]], days = [[1,1,1],[7,7,7],[7,7,7]]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
Since there are no flights that enable you to move to another city, you have to stay at city 0 for the whole 3 weeks. 
For each week, you only have one day to play and six days to work.
So the maximum number of vacation days is 3.
Ans = 1 + 1 + 1 = 3.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[7,0,0],[0,7,0],[0,0,7]]
<strong>Output:</strong> 21
<strong>Explanation:</strong>
One of the best strategies is:
1st week : stay at city 0, and play 7 days.
2nd week : fly from city 0 to city 1 on Monday, and play 7 days.
3rd week : fly from city 1 to city 2 on Monday, and play 7 days.
Ans = 7 + 7 + 7 = 21
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == flights.length</code></li>
	<li><code>n == flights[i].length</code></li>
	<li><code>n == days.length</code></li>
	<li><code>k == days[i].length</code></li>
	<li><code>1 &lt;= n, k &lt;= 100</code></li>
	<li><code>flights[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
	<li><code>0 &lt;= days[i][j] &lt;= 7</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxVacationDays(self, flights: List[List[int]], days: List[List[int]]) -> int:
        n = len(flights)
        K = len(days[0])
        f = [[-inf] * n for _ in range(K + 1)]
        f[0][0] = 0
        for k in range(1, K + 1):
            for j in range(n):
                f[k][j] = f[k - 1][j]
                for i in range(n):
                    if flights[i][j]:
                        f[k][j] = max(f[k][j], f[k - 1][i])
                f[k][j] += days[j][k - 1]
        return max(f[-1][j] for j in range(n))
```

### **Java**

```java
class Solution {
    public int maxVacationDays(int[][] flights, int[][] days) {
        int n = flights.length;
        int K = days[0].length;
        final int inf = 1 << 30;
        int[][] f = new int[K + 1][n];
        for (var g : f) {
            Arrays.fill(g, -inf);
        }
        f[0][0] = 0;
        for (int k = 1; k <= K; ++k) {
            for (int j = 0; j < n; ++j) {
                f[k][j] = f[k - 1][j];
                for (int i = 0; i < n; ++i) {
                    if (flights[i][j] == 1) {
                        f[k][j] = Math.max(f[k][j], f[k - 1][i]);
                    }
                }
                f[k][j] += days[j][k - 1];
            }
        }
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            ans = Math.max(ans, f[K][j]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxVacationDays(vector<vector<int>>& flights, vector<vector<int>>& days) {
        int n = flights.size();
        int K = days[0].size();
        int f[K + 1][n];
        memset(f, -0x3f, sizeof(f));
        f[0][0] = 0;
        for (int k = 1; k <= K; ++k) {
            for (int j = 0; j < n; ++j) {
                f[k][j] = f[k - 1][j];
                for (int i = 0; i < n; ++i) {
                    if (flights[i][j] == 1) {
                        f[k][j] = max(f[k][j], f[k - 1][i]);
                    }
                }
                f[k][j] += days[j][k - 1];
            }
        }
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            ans = max(ans, f[K][j]);
        }
        return ans;
    }
};
```

### **Go**

```go
func maxVacationDays(flights [][]int, days [][]int) (ans int) {
	n, K := len(flights), len(days[0])
	f := make([][]int, K+1)
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = -(1 << 30)
		}
	}
	f[0][0] = 0
	for k := 1; k <= K; k++ {
		for j := 0; j < n; j++ {
			f[k][j] = f[k-1][j]
			for i := 0; i < n; i++ {
				if flights[i][j] == 1 {
					f[k][j] = max(f[k][j], f[k-1][i])
				}
			}
			f[k][j] += days[j][k-1]
		}
	}
	for j := 0; j < n; j++ {
		ans = max(ans, f[K][j])
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
