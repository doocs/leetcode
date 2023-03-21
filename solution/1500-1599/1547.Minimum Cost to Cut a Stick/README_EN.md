# [1547. Minimum Cost to Cut a Stick](https://leetcode.com/problems/minimum-cost-to-cut-a-stick)

[中文文档](/solution/1500-1599/1547.Minimum%20Cost%20to%20Cut%20a%20Stick/README.md)

## Description

<p>Given a wooden stick of length <code>n</code> units. The stick is labelled from <code>0</code> to <code>n</code>. For example, a stick of length <strong>6</strong> is labelled as follows:</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1547.Minimum%20Cost%20to%20Cut%20a%20Stick/images/statement.jpg" style="width: 521px; height: 111px;" />
<p>Given an integer array <code>cuts</code> where <code>cuts[i]</code> denotes a position you should perform a cut at.</p>

<p>You should perform the cuts in order, you can change the order of the cuts as you wish.</p>

<p>The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of all cuts. When you cut a stick, it will be split into two smaller sticks (i.e. the sum of their lengths is the length of the stick before the cut). Please refer to the first example for a better explanation.</p>

<p>Return <em>the minimum total cost</em> of the cuts.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1547.Minimum%20Cost%20to%20Cut%20a%20Stick/images/e1.jpg" style="width: 350px; height: 284px;" />
<pre>
<strong>Input:</strong> n = 7, cuts = [1,3,4,5]
<strong>Output:</strong> 16
<strong>Explanation:</strong> Using cuts order = [1, 3, 4, 5] as in the input leads to the following scenario:
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1547.Minimum%20Cost%20to%20Cut%20a%20Stick/images/e11.jpg" style="width: 350px; height: 284px;" />
The first cut is done to a rod of length 7 so the cost is 7. The second cut is done to a rod of length 6 (i.e. the second part of the first cut), the third is done to a rod of length 4 and the last cut is to a rod of length 3. The total cost is 7 + 6 + 4 + 3 = 20.
Rearranging the cuts to be [3, 5, 1, 4] for example will lead to a scenario with total cost = 16 (as shown in the example photo 7 + 4 + 3 + 2 = 16).</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 9, cuts = [5,6,1,4,2]
<strong>Output:</strong> 22
<strong>Explanation:</strong> If you try the given cuts ordering the cost will be 25.
There are much ordering with total cost &lt;= 25, for example, the order [4, 6, 5, 2, 1] has total cost = 22 which is the minimum possible.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= cuts.length &lt;= min(n - 1, 100)</code></li>
	<li><code>1 &lt;= cuts[i] &lt;= n - 1</code></li>
	<li>All the integers in <code>cuts</code> array are <strong>distinct</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minCost(self, n: int, cuts: List[int]) -> int:
        cuts.extend([0, n])
        cuts.sort()
        m = len(cuts)
        f = [[0] * m for _ in range(m)]
        for l in range(2, m):
            for i in range(m - l):
                j = i + l
                f[i][j] = inf
                for k in range(i + 1, j):
                    f[i][j] = min(f[i][j], f[i][k] + f[k][j] + cuts[j] - cuts[i])
        return f[0][-1]
```

### **Java**

```java
class Solution {
    public int minCost(int n, int[] cuts) {
        List<Integer> nums = new ArrayList<>();
        for (int x : cuts) {
            nums.add(x);
        }
        nums.add(0);
        nums.add(n);
        Collections.sort(nums);
        int m = nums.size();
        int[][] f = new int[m][m];
        for (int l = 2; l < m; ++l) {
            for (int i = 0; i + l < m; ++i) {
                int j = i + l;
                f[i][j] = 1 << 30;
                for (int k = i + 1; k < j; ++k) {
                    f[i][j] = Math.min(f[i][j], f[i][k] + f[k][j] + nums.get(j) - nums.get(i));
                }
            }
        }
        return f[0][m - 1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minCost(int n, vector<int>& cuts) {
        cuts.push_back(0);
        cuts.push_back(n);
        sort(cuts.begin(), cuts.end());
        int m = cuts.size();
        int f[110][110]{};
        for (int l = 2; l < m; ++l) {
            for (int i = 0; i + l < m; ++i) {
                int j = i + l;
                f[i][j] = 1 << 30;
                for (int k = i + 1; k < j; ++k) {
                    f[i][j] = min(f[i][j], f[i][k] + f[k][j] + cuts[j] - cuts[i]);
                }
            }
        }
        return f[0][m - 1];
    }
};
```

### **Go**

```go
func minCost(n int, cuts []int) int {
	cuts = append(cuts, []int{0, n}...)
	sort.Ints(cuts)
	m := len(cuts)
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, m)
	}
	for l := 2; l < m; l++ {
		for i := 0; i+l < m; i++ {
			j := i + l
			f[i][j] = 1 << 30
			for k := i + 1; k < j; k++ {
				f[i][j] = min(f[i][j], f[i][k]+f[k][j]+cuts[j]-cuts[i])
			}
		}
	}
	return f[0][m-1]
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
