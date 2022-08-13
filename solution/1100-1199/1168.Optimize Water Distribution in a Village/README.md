# [1168. 水资源分配优化](https://leetcode.cn/problems/optimize-water-distribution-in-a-village)

[English Version](/solution/1100-1199/1168.Optimize%20Water%20Distribution%20in%20a%20Village/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>村里面一共有 <code>n</code> 栋房子。我们希望通过建造水井和铺设管道来为所有房子供水。</p>

<p>对于每个房子&nbsp;<code>i</code>，我们有两种可选的供水方案：一种是直接在房子内建造水井，成本为&nbsp;<code>wells[i - 1]</code>&nbsp;（注意 <code>-1</code> ，因为 <strong>索引从0开始</strong> ）；另一种是从另一口井铺设管道引水，数组&nbsp;<code>pipes</code>&nbsp;给出了在房子间铺设管道的成本，其中每个&nbsp;<code>pipes[j] = [house1<sub>j</sub>, house2<sub>j</sub>, cost<sub>j</sub>]</code>&nbsp;代表用管道将&nbsp;<code>house1<sub>j</sub></code>&nbsp;和&nbsp;<code>house2<sub>j</sub></code>连接在一起的成本。连接是双向的。</p>

<p>请返回 <em>为所有房子都供水的最低总成本</em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1168.Optimize%20Water%20Distribution%20in%20a%20Village/images/1359_ex1.png" /></strong></p>

<pre>
<strong>输入：</strong>n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
<strong>输出：</strong>3
<strong>解释： </strong>
上图展示了铺设管道连接房屋的成本。
最好的策略是在第一个房子里建造水井（成本为 1），然后将其他房子铺设管道连起来（成本为 2），所以总成本为 3。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2, wells = [1,1], pipes = [[1,2,1]]
<strong>输出：</strong>2
<strong>解释：</strong>我们可以用以下三种方法中的一种来提供低成本的水:
选项1:
在1号房子里面建一口井，成本为1
在房子2内建造井，成本为1
总成本是2。
选项2:
在1号房子里面建一口井，成本为1
-花费1连接房子2和房子1。
总成本是2。
选项3:
在房子2内建造井，成本为1
-花费1连接房子1和房子2。
总成本是2。
注意，我们可以用cost 1或cost 2连接房子1和房子2，但我们总是选择最便宜的选项。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>wells.length == n</code></li>
	<li><code>0 &lt;= wells[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= pipes.length &lt;= 10<sup>4</sup></code></li>
	<li><code>pipes[j].length == 3</code></li>
	<li><code>1 &lt;= house1<sub>j</sub>, house2<sub>j</sub>&nbsp;&lt;= n</code></li>
	<li><code>0 &lt;= cost<sub>j</sub>&nbsp;&lt;= 10<sup>5</sup></code></li>
	<li><code>house1<sub>j</sub>&nbsp;!= house2<sub>j</sub></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

最小生成树问题。

**方法一：Kruskal 算法**

对于本题，可以将节点 0 视为水库，水库到房子间的成本等于房子内建造水井的成本。因此此题可以转换为最小生成树问题。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minCostToSupplyWater(
        self, n: int, wells: List[int], pipes: List[List[int]]
    ) -> int:
        for i, w in enumerate(wells):
            pipes.append([0, i + 1, w])
        pipes.sort(key=lambda x: x[2])

        p = list(range(n + 1))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        res = 0
        for u, v, w in pipes:
            if find(u) == find(v):
                continue
            p[find(u)] = find(v)
            res += w
            n -= 1
            if n == 0:
                break
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        int[][] all = new int[pipes.length + n][3];
        int idx = 0;
        for (int[] pipe : pipes) {
            all[idx++] = pipe;
        }
        for (int j = 0; j < n; ++j) {
            all[idx++] = new int[]{0, j + 1, wells[j]};
        }
        p = new int[n + 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        Arrays.sort(all, Comparator.comparingInt(a -> a[2]));
        int res = 0;
        for (int[] e : all) {
            if (find(e[0]) == find(e[1])) {
                continue;
            }
            p[find(e[0])] = find(e[1]);
            res += e[2];
            --n;
            if (n == 0) {
                break;
            }
        }
        return res;
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

    int minCostToSupplyWater(int n, vector<int>& wells, vector<vector<int>>& pipes) {
        p.resize(n + 1);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        for (int i = 0; i < n; ++i) pipes.push_back({0, i + 1, wells[i]});
        sort(pipes.begin(), pipes.end(), [](const auto& a, const auto& b) {
            return a[2] < b[2];
        });
        int res = 0;
        for (auto e : pipes) {
            if (find(e[0]) == find(e[1])) continue;
            p[find(e[0])] = find(e[1]);
            res += e[2];
            --n;
            if (n == 0) break;
        }
        return res;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
var p []int

func minCostToSupplyWater(n int, wells []int, pipes [][]int) int {
	p = make([]int, n+1)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	for i, w := range wells {
		pipes = append(pipes, []int{0, i + 1, w})
	}
	sort.Slice(pipes, func(i, j int) bool {
		return pipes[i][2] < pipes[j][2]
	})
	res := 0
	for _, e := range pipes {
		if find(e[0]) == find(e[1]) {
			continue
		}
		p[find(e[0])] = find(e[1])
		res += e[2]
		n--
		if n == 0 {
			break
		}
	}
	return res
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}
```

### **...**

```

```

<!-- tabs:end -->
