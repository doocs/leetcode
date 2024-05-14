---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2655.Find%20Maximal%20Uncovered%20Ranges/README.md
tags:
    - 数组
    - 排序
---

# [2655. 寻找最大长度的未覆盖区间 🔒](https://leetcode.cn/problems/find-maximal-uncovered-ranges)

[English Version](/solution/2600-2699/2655.Find%20Maximal%20Uncovered%20Ranges/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现给你一个长度为 n 的 <strong>索引从 0 开始的</strong>&nbsp;数组 <code>nums</code> 和一个 <strong>索引从 0 开始的</strong> 2 维数组 <code>ranges</code> ，<strong>ranges</strong> 是 <strong>nums</strong> 的子区间列表（子区间可能 <strong>重叠</strong> ）。</p>

<p>每行 <code>ranges[i]</code> 恰好有两个元素：</p>

<ul>
	<li><code>ranges[i][0]</code> 表示第i个区间的起始位置（包含）</li>
	<li><code>ranges[i][1]</code> 表示第i个区间的结束位置（包含）</li>
</ul>

<p>这些区间覆盖了 <code>nums</code> 的一些元素，并留下了一些 <strong>未覆盖</strong> 的元素。你的任务是找到所有 <strong>最大长度</strong> 的未覆盖区间。</p>

<p>返回按起始点 <strong>升序排序</strong> 的未覆盖区间的二维数组 <code>answer</code> 。</p>

<p>所有 <strong>最大长度</strong> 的 <strong>未覆盖</strong> 区间指满足两个条件：</p>

<ul>
	<li>每个未覆盖的元素应该属于 <strong>恰好</strong> 一个子区间。</li>
	<li><strong>不存在</strong>两个区间 (l1,r1) 和 (l2,r2) 使得 r1+1=l2 。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1 ：</strong></p>

<pre>
<b>输入：</b>n = 10, ranges = [[3,5],[7,8]]
<b>输出：</b>[[0,2],[6,6],[9,9]]
<b>解释：</b>区间 (3,5) 和 (7,8) 都被覆盖，因此如果我们将 nums 简化为一个二进制数组，其中 0 表示未覆盖的元素，1 表示覆盖的元素，则数组变为[0,0,0,1,1,1,0,1,1,0]，在其中我们可以观察到区间 (0,2)，(6,6)和(9,9)未被覆盖。
</pre>

<p><strong class="example">示例 2&nbsp;：</strong></p>

<pre>
<b>输入：</b>n = 3, ranges = [[0,2]]
<b>输出：</b>[]
<strong>解释：</strong>在这个例子中，整个 nums 数组都被覆盖，没有未覆盖的元素，所以输出是一个空数组。
</pre>

<p><strong class="example">示例 3 ：</strong></p>

<pre>
<b>输入：</b>n = 7, ranges = [[2,4],[0,3]]
<b>输出：</b>[[5,6]]
<b>解释：</b>区间 (0,3) 和 (2,4) 都被覆盖，因此如果我们将 nums 简化为一个二进制数组，其中 0 表示未覆盖的元素，1 表示覆盖的元素，则数组变为[1,1,1,1,1,0,0]，在其中我们可以观察到区间 (5,6) 未被覆盖。</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= n &lt;=&nbsp;10<sup>9</sup></code></li>
	<li><code>0 &lt;= ranges.length &lt;= 10<sup>6</sup></code></li>
	<li><code>ranges[i].length = 2</code></li>
	<li><code>0 &lt;= ranges[i][j] &lt;= n - 1</code></li>
	<li><code>ranges[i][0] &lt;=&nbsp;ranges[i][1]</code></li>
</ul>

## 解法

### 方法一：排序

我们将所有的区间按照左端点从小到大排序，然后从左到右遍历所有的区间，维护一个变量 $last$ 表示当前已经被覆盖的最右端点，初始时 $last=-1$。如果当前区间的左端点大于 $last+1$，那么说明 $[last+1, l-1]$ 是一个未被覆盖的区间，我们将其加入答案数组中。然后我们更新 $last$ 为当前区间的右端点，继续遍历下一个区间。当遍历完所有的区间后，如果 $last+1 \lt n$，那么说明 $[last+1, n-1]$ 是一个未被覆盖的区间，我们将其加入答案数组中。

最后我们将答案数组返回即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $ranges$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def findMaximalUncoveredRanges(
        self, n: int, ranges: List[List[int]]
    ) -> List[List[int]]:
        ranges.sort()
        last = -1
        ans = []
        for l, r in ranges:
            if last + 1 < l:
                ans.append([last + 1, l - 1])
            last = max(last, r)
        if last + 1 < n:
            ans.append([last + 1, n - 1])
        return ans
```

```java
class Solution {
    public int[][] findMaximalUncoveredRanges(int n, int[][] ranges) {
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);
        int last = -1;
        List<int[]> ans = new ArrayList<>();
        for (int[] range : ranges) {
            int l = range[0], r = range[1];
            if (last + 1 < l) {
                ans.add(new int[] {last + 1, l - 1});
            }
            last = Math.max(last, r);
        }
        if (last + 1 < n) {
            ans.add(new int[] {last + 1, n - 1});
        }
        return ans.toArray(new int[0][]);
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> findMaximalUncoveredRanges(int n, vector<vector<int>>& ranges) {
        sort(ranges.begin(), ranges.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[0] < b[0];
        });
        int last = -1;
        vector<vector<int>> ans;
        for (auto& range : ranges) {
            int l = range[0], r = range[1];
            if (last + 1 < l) {
                ans.push_back({last + 1, l - 1});
            }
            last = max(last, r);
        }
        if (last + 1 < n) {
            ans.push_back({last + 1, n - 1});
        }
        return ans;
    }
};
```

```go
func findMaximalUncoveredRanges(n int, ranges [][]int) (ans [][]int) {
	sort.Slice(ranges, func(i, j int) bool { return ranges[i][0] < ranges[j][0] })
	last := -1
	for _, r := range ranges {
		if last+1 < r[0] {
			ans = append(ans, []int{last + 1, r[0] - 1})
		}
		last = max(last, r[1])
	}
	if last+1 < n {
		ans = append(ans, []int{last + 1, n - 1})
	}
	return
}
```

<!-- tabs:end -->

<!-- end -->
