---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2345.Finding%20the%20Number%20of%20Visible%20Mountains/README.md
tags:
    - 栈
    - 数组
    - 排序
    - 单调栈
---

<!-- problem:start -->

# [2345. 寻找可见山的数量 🔒](https://leetcode.cn/problems/finding-the-number-of-visible-mountains)

[English Version](/solution/2300-2399/2345.Finding%20the%20Number%20of%20Visible%20Mountains/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个 <strong>下标从 0 开始&nbsp;</strong>的二维整数数组 <code>peaks</code>，其中 <code>peaks[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 表示山 <code>i</code> 在坐标 <code>(x<sub>i</sub>, y<sub>i</sub>)</code>&nbsp;处有一个峰值。山可以被描述为一个直角等腰三角形，它的底部沿着 <code>x</code>&nbsp;轴，山峰处有一个直角。更正式地说，上山和下山的&nbsp;<strong>梯度&nbsp;</strong>分别为 <code>1</code>&nbsp;和 <code>-1</code>。</p>

<p>一座山如果它的顶峰不在另一座山 (包括其他山的边界) 之内，那么它被认为是&nbsp;<strong>可见&nbsp;</strong>的。</p>

<p data-group="1-1">返回<em>可见山的数量。</em></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2345.Finding%20the%20Number%20of%20Visible%20Mountains/images/ex1.png" style="width: 402px; height: 210px;" />
<pre>
<strong>输入:</strong> peaks = [[2,2],[6,3],[5,4]]
<strong>输出:</strong> 2
<strong>解释:</strong> 上面的图表显示了山脉。
- 山 0 是可见的，因为它的峰值不在另一座山的里面或另一座山的边界。
- 山 1 是不可见的，因为它的顶峰在山 2 的边界。
- 山 2 是可见的，因为它的峰值不在另一座山的里面或另一座山的边界。
有 2 座山是可见的。</pre>

<p><strong class="example">示例 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2345.Finding%20the%20Number%20of%20Visible%20Mountains/images/ex2new1.png" style="width: 300px; height: 180px;" />
<pre>
<strong>输入:</strong> peaks = [[1,3],[1,3]]
<strong>输出:</strong> 0
<strong>解释:</strong> 上面的图表显示了这些山 (它们完全重叠)。
两座山都看不见，因为它们的山峰在彼此里面。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= peaks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>peaks[i].length == 2</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：区间排序 + 遍历

我们先将每座山 $(x, y)$ 转换成横坐标的区间 $(x - y, x + y)$，然后对区间按照左端点升序排序，右端点降序排序。

接下来，初始化当前区间的右端点为 $-\infty$，遍历每座山，如果当前山的右端点小于等于当前区间的右端点，则跳过该山，否则更新当前区间的右端点为当前山的右端点，如果当前山的区间只出现一次，则答案加一。

遍历结束后返回答案即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为山的数量。

<!-- tabs:start -->

```python
class Solution:
    def visibleMountains(self, peaks: List[List[int]]) -> int:
        arr = [(x - y, x + y) for x, y in peaks]
        cnt = Counter(arr)
        arr.sort(key=lambda x: (x[0], -x[1]))
        ans, cur = 0, -inf
        for l, r in arr:
            if r <= cur:
                continue
            cur = r
            if cnt[(l, r)] == 1:
                ans += 1
        return ans
```

```java
class Solution {
    public int visibleMountains(int[][] peaks) {
        int n = peaks.length;
        int[][] arr = new int[n][2];
        Map<String, Integer> cnt = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int x = peaks[i][0], y = peaks[i][1];
            arr[i] = new int[] {x - y, x + y};
            cnt.merge((x - y) + "" + (x + y), 1, Integer::sum);
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int ans = 0;
        int cur = Integer.MIN_VALUE;
        for (int[] e : arr) {
            int l = e[0], r = e[1];
            if (r <= cur) {
                continue;
            }
            cur = r;
            if (cnt.get(l + "" + r) == 1) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int visibleMountains(vector<vector<int>>& peaks) {
        vector<pair<int, int>> arr;
        for (auto& e : peaks) {
            int x = e[0], y = e[1];
            arr.emplace_back(x - y, -(x + y));
        }
        sort(arr.begin(), arr.end());
        int n = arr.size();
        int ans = 0, cur = INT_MIN;
        for (int i = 0; i < n; ++i) {
            int l = arr[i].first, r = -arr[i].second;
            if (r <= cur) {
                continue;
            }
            cur = r;
            ans += i == n - 1 || (i < n - 1 && arr[i] != arr[i + 1]);
        }
        return ans;
    }
};
```

```go
func visibleMountains(peaks [][]int) (ans int) {
	n := len(peaks)
	type pair struct{ l, r int }
	arr := make([]pair, n)
	for _, p := range peaks {
		x, y := p[0], p[1]
		arr = append(arr, pair{x - y, x + y})
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i].l < arr[j].l || (arr[i].l == arr[j].l && arr[i].r > arr[j].r) })
	cur := math.MinInt32
	for i, e := range arr {
		l, r := e.l, e.r
		if r <= cur {
			continue
		}
		cur = r
		if !(i < n-1 && l == arr[i+1].l && r == arr[i+1].r) {
			ans++
		}
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

```java
class Solution {
    public int visibleMountains(int[][] peaks) {
        int n = peaks.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            int x = peaks[i][0], y = peaks[i][1];
            arr[i] = new int[] {x - y, x + y};
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int ans = 0;
        int cur = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            int l = arr[i][0], r = arr[i][1];
            if (r <= cur) {
                continue;
            }
            cur = r;
            if (!(i < n - 1 && arr[i][0] == arr[i + 1][0] && arr[i][1] == arr[i + 1][1])) {
                ++ans;
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
