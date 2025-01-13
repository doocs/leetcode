---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0436.Find%20Right%20Interval/README.md
tags:
    - 数组
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [436. 寻找右区间](https://leetcode.cn/problems/find-right-interval)

[English Version](/solution/0400-0499/0436.Find%20Right%20Interval/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个区间数组 <code>intervals</code> ，其中&nbsp;<code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> ，且每个&nbsp;<code>start<sub>i</sub></code> 都 <strong>不同</strong> 。</p>

<p>区间 <code>i</code> 的 <strong>右侧区间</strong>&nbsp;是满足 <code>start<sub>j</sub>&nbsp;&gt;= end<sub>i</sub></code>，且 <code>start<sub>j</sub></code> <strong>最小&nbsp;</strong>的区间 <code>j</code>。注意 <code>i</code> 可能等于 <code>j</code> 。</p>

<p>返回一个由每个区间 <code>i</code>&nbsp;对应的 <strong>右侧区间</strong> 下标组成的数组。如果某个区间 <code>i</code> 不存在对应的 <strong>右侧区间</strong> ，则下标 <code>i</code> 处的值设为 <code>-1</code> 。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,2]]
<strong>输出：</strong>[-1]
<strong>解释：</strong>集合中只有一个区间，所以输出-1。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[3,4],[2,3],[1,2]]
<strong>输出：</strong>[-1,0,1]
<strong>解释：</strong>对于 [3,4] ，没有满足条件的“右侧”区间。
对于 [2,3] ，区间[3,4]具有最小的“右”起点;
对于 [1,2] ，区间[2,3]具有最小的“右”起点。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,4],[2,3],[3,4]]
<strong>输出：</strong>[-1,2,-1]
<strong>解释：</strong>对于区间 [1,4] 和 [3,4] ，没有满足条件的“右侧”区间。
对于 [2,3] ，区间 [3,4] 有最小的“右”起点。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;intervals.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>-10<sup>6</sup> &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li>每个间隔的起点都 <strong>不相同</strong></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 二分查找

我们可以将区间的起点和下标存入数组 `arr` 中，并按照起点进行排序。然后遍历区间数组，对于每个区间 `[_, ed]`，我们可以使用二分查找找到第一个起点大于等于 `ed` 的区间，即为其右侧区间，如果找到了，我们就将其下标存入答案数组中，否则存入 `-1`。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为区间的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findRightInterval(self, intervals: List[List[int]]) -> List[int]:
        n = len(intervals)
        ans = [-1] * n
        arr = sorted((st, i) for i, (st, _) in enumerate(intervals))
        for i, (_, ed) in enumerate(intervals):
            j = bisect_left(arr, (ed, -inf))
            if j < n:
                ans[i] = arr[j][1]
        return ans
```

#### Java

```java
class Solution {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[][] arr = new int[n][0];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {intervals[i][0], i};
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int j = search(arr, intervals[i][1]);
            ans[i] = j < n ? arr[j][1] : -1;
        }
        return ans;
    }

    private int search(int[][] arr, int x) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (arr[mid][0] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findRightInterval(vector<vector<int>>& intervals) {
        int n = intervals.size();
        vector<pair<int, int>> arr;
        for (int i = 0; i < n; ++i) {
            arr.emplace_back(intervals[i][0], i);
        }
        sort(arr.begin(), arr.end());
        vector<int> ans;
        for (auto& e : intervals) {
            int j = lower_bound(arr.begin(), arr.end(), make_pair(e[1], -1)) - arr.begin();
            ans.push_back(j == n ? -1 : arr[j].second);
        }
        return ans;
    }
};
```

#### Go

```go
func findRightInterval(intervals [][]int) (ans []int) {
	arr := make([][2]int, len(intervals))
	for i, v := range intervals {
		arr[i] = [2]int{v[0], i}
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i][0] < arr[j][0] })
	for _, e := range intervals {
		j := sort.Search(len(arr), func(i int) bool { return arr[i][0] >= e[1] })
		if j < len(arr) {
			ans = append(ans, arr[j][1])
		} else {
			ans = append(ans, -1)
		}
	}
	return
}
```

#### TypeScript

```ts
function findRightInterval(intervals: number[][]): number[] {
    const n = intervals.length;
    const arr: number[][] = Array.from({ length: n }, (_, i) => [intervals[i][0], i]);
    arr.sort((a, b) => a[0] - b[0]);
    return intervals.map(([_, ed]) => {
        let [l, r] = [0, n];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (arr[mid][0] >= ed) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l < n ? arr[l][1] : -1;
    });
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
