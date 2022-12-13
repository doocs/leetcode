# [1840. 最高建筑高度](https://leetcode.cn/problems/maximum-building-height)

[English Version](/solution/1800-1899/1840.Maximum%20Building%20Height/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一座城市里，你需要建 <code>n</code> 栋新的建筑。这些新的建筑会从 <code>1</code> 到 <code>n</code> 编号排成一列。</p>

<p>这座城市对这些新建筑有一些规定：</p>

<ul>
	<li>每栋建筑的高度必须是一个非负整数。</li>
	<li>第一栋建筑的高度 <strong>必须</strong> 是 <code>0</code> 。</li>
	<li>任意两栋相邻建筑的高度差 <strong>不能超过</strong>  <code>1</code> 。</li>
</ul>

<p>除此以外，某些建筑还有额外的最高高度限制。这些限制会以二维整数数组 <code>restrictions</code> 的形式给出，其中 <code>restrictions[i] = [id<sub>i</sub>, maxHeight<sub>i</sub>]</code> ，表示建筑 <code>id<sub>i</sub></code> 的高度 <strong>不能超过</strong> <code>maxHeight<sub>i</sub></code> 。</p>

<p>题目保证每栋建筑在 <code>restrictions</code> 中<strong> 至多出现一次</strong> ，同时建筑 <code>1</code> <strong>不会</strong> 出现在 <code>restrictions</code> 中。</p>

<p>请你返回 <strong>最高</strong> 建筑能达到的 <strong>最高高度</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1840.Maximum%20Building%20Height/images/ic236-q4-ex1-1.png" style="width: 400px; height: 253px;" />
<pre>
<b>输入：</b>n = 5, restrictions = [[2,1],[4,1]]
<b>输出：</b>2
<b>解释：</b>上图中的绿色区域为每栋建筑被允许的最高高度。
我们可以使建筑高度分别为 [0,1,2,1,2] ，最高建筑的高度为 2 。</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1840.Maximum%20Building%20Height/images/ic236-q4-ex2.png" style="width: 500px; height: 269px;" />
<pre>
<b>输入：</b>n = 6, restrictions = []
<b>输出：</b>5
<b>解释：</b>上图中的绿色区域为每栋建筑被允许的最高高度。
我们可以使建筑高度分别为 [0,1,2,3,4,5] ，最高建筑的高度为 5 。
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1840.Maximum%20Building%20Height/images/ic236-q4-ex3.png" style="width: 500px; height: 187px;" />
<pre>
<b>输入：</b>n = 10, restrictions = [[5,3],[2,5],[7,4],[10,3]]
<b>输出：</b>5
<b>解释：</b>上图中的绿色区域为每栋建筑被允许的最高高度。
我们可以使建筑高度分别为 [0,1,2,3,3,4,4,5,4,3] ，最高建筑的高度为 5 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= n <= 10<sup>9</sup></code></li>
	<li><code>0 <= restrictions.length <= min(n - 1, 10<sup>5</sup>)</code></li>
	<li><code>2 <= id<sub>i</sub> <= n</code></li>
	<li><code>id<sub>i</sub></code> 是 <strong>唯一的</strong> 。</li>
	<li><code>0 <= maxHeight<sub>i</sub> <= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 数学**

首先，我们将所有的限制条件按照建筑物的编号从小到大排序。

然后我们从左到右遍历所有的限制条件，对于每个限制条件，我们可以得到一个最高高度的上界，即 $r_i[1] = min(r_i[1], r_{i-1}[1] + r_i[0] - r_{i-1}[0])$，其中 $r_i$ 表示第 $i$ 个限制条件，而 $r_i[0]$ 和 $r_i[1]$ 分别表示建筑物的编号以及建筑物的最高高度的上界。

然后我们从右到左遍历所有的限制条件，对于每个限制条件，我们可以得到一个最高高度的上界，即 $r_i[1] = min(r_i[1], r_{i+1}[1] + r_{i+1}[0] - r_i[0])$。

这样，我们就得到了每个限制建筑物的最高高度的上界。

题目求的是最高建筑物的高度，我们可以枚举相邻两个限制条件之间的建筑物 $i$ 和 $i+1$，要使得高度最大，那么高度应该是先增大后减小，假设最大高度为 $t$，那么 $t - r_i[1] + t - r_{i+1}[1] \leq r_{i+1}[0] - r_i[0]$，即 $t \leq \frac{r_i[1] + r_{i+1}[1] + r_{i+1}[0] - r_{i}[0]}{2}$，我们取所有的 $t$ 中的最大值即可。

时间复杂度 $O(m \times \log m)$，空间复杂度 $O(m)$。其中 $m$ 为限制条件的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxBuilding(self, n: int, restrictions: List[List[int]]) -> int:
        r = restrictions
        r.append([1, 0])
        r.sort()
        if r[-1][0] != n:
            r.append([n, n - 1])
        m = len(r)
        for i in range(1, m):
            r[i][1] = min(r[i][1], r[i - 1][1] + r[i][0] - r[i - 1][0])
        for i in range(m - 2, 0, -1):
            r[i][1] = min(r[i][1], r[i + 1][1] + r[i + 1][0] - r[i][0])
        ans = 0
        for i in range(m - 1):
            t = (r[i][1] + r[i + 1][1] + r[i + 1][0] - r[i][0]) // 2
            ans = max(ans, t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        List<int[]> r = new ArrayList<>();
        r.addAll(Arrays.asList(restrictions));
        r.add(new int[] {1, 0});
        Collections.sort(r, (a, b) -> a[0] - b[0]);
        if (r.get(r.size() - 1)[0] != n) {
            r.add(new int[] {n, n - 1});
        }
        int m = r.size();
        for (int i = 1; i < m; ++i) {
            int[] a = r.get(i - 1), b = r.get(i);
            b[1] = Math.min(b[1], a[1] + b[0] - a[0]);
        }
        for (int i = m - 2; i > 0; --i) {
            int[] a = r.get(i), b = r.get(i + 1);
            a[1] = Math.min(a[1], b[1] + b[0] - a[0]);
        }
        int ans = 0;
        for (int i = 0; i < m - 1; ++i) {
            int[] a = r.get(i), b = r.get(i + 1);
            int t = (a[1] + b[1] + b[0] - a[0]) / 2;
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxBuilding(int n, vector<vector<int>>& restrictions) {
        auto&& r = restrictions;
        r.push_back({1, 0});
        sort(r.begin(), r.end());
        if (r[r.size() - 1][0] != n) r.push_back({n, n - 1});
        int m = r.size();
        for (int i = 1; i < m; ++i) {
            r[i][1] = min(r[i][1], r[i - 1][1] + r[i][0] - r[i - 1][0]);
        }
        for (int i = m - 2; i > 0; --i) {
            r[i][1] = min(r[i][1], r[i + 1][1] + r[i + 1][0] - r[i][0]);
        }
        int ans = 0;
        for (int i = 0; i < m - 1; ++i) {
            int t = (r[i][1] + r[i + 1][1] + r[i + 1][0] - r[i][0]) / 2;
            ans = max(ans, t);
        }
        return ans;
    }
};
```

### **Go**

```go
func maxBuilding(n int, restrictions [][]int) (ans int) {
	r := restrictions
	r = append(r, []int{1, 0})
	sort.Slice(r, func(i, j int) bool { return r[i][0] < r[j][0] })
	if r[len(r)-1][0] != n {
		r = append(r, []int{n, n - 1})
	}
	m := len(r)
	for i := 1; i < m; i++ {
		r[i][1] = min(r[i][1], r[i-1][1]+r[i][0]-r[i-1][0])
	}
	for i := m - 2; i > 0; i-- {
		r[i][1] = min(r[i][1], r[i+1][1]+r[i+1][0]-r[i][0])
	}
	for i := 0; i < m-1; i++ {
		t := (r[i][1] + r[i+1][1] + r[i+1][0] - r[i][0]) / 2
		ans = max(ans, t)
	}
	return ans
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

### **...**

```

```

<!-- tabs:end -->
