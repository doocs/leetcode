---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3893.Maximum%20Team%20Size%20with%20Overlapping%20Intervals/README.md
---

<!-- problem:start -->

# [3893. 最大重叠区间团队规模 🔒](https://leetcode.cn/problems/maximum-team-size-with-overlapping-intervals)

[English Version](/solution/3800-3899/3893.Maximum%20Team%20Size%20with%20Overlapping%20Intervals/README_EN.md)

## 题目描述

<!-- description:start -->

<p data-end="767" data-start="694">给定两个整数数组 <code>startTime</code> 和 <code>endTime</code>，长度为 <code>n</code>。</p>

<ul>
	<li><code>startTime[i]</code> 表示第 <code>i</code>&nbsp;个员工的开始时间。</li>
	<li><code>endTime[i]</code> 表示第 <code>i</code>&nbsp;个员工的结束时间。</li>
</ul>

<p>如果两个员工 <code>i</code> 和 <code>j</code> 的时间区间&nbsp;<strong>有重叠</strong>，则他们可以进行互动。两个区间只要&nbsp;<strong>至少有一个&nbsp;</strong>公共时间点，就认为是重叠的。</p>

<p>如果一个团队中&nbsp;<strong>至少存在一个&nbsp;</strong>员工，可以与团队中的所有其他成员互动，则该团队是&nbsp;<strong>有效的</strong>。</p>

<p>返回一个整数，表示这样的团队的&nbsp;<strong>最大可能规模</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">startTime = [1,2,3], endTime = [4,5,6]</span></p>

<p><strong>输出：</strong><span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>对于 <code>i = 0</code>，区间为 <code>[1, 4]</code>。</li>
	<li>它与 <code>i = 1</code> 的区间 <code>[2, 5]</code> 和 <code>i = 2</code> 的区间 <code>[3, 6]</code> 都有重叠。</li>
	<li>因此，索引 0 可以与所有其他索引互动，团队规模为 3。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">startTime = [2,5,8], endTime = [3,7,9]</span></p>

<p><strong>输出：</strong><span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>对于 <code>i = 0</code>，区间 <code>[2, 3]</code> 与 <code>[5, 7]</code> 和 <code>[8, 9]</code> 都没有重叠。</li>
	<li>对于 <code>i = 1</code>，区间 <code>[5, 7]</code> 与 <code>[2, 3]</code> 和 <code>[8, 9]</code> 都没有重叠。</li>
	<li>对于 <code>i = 2</code>，区间 <code>[8, 9]</code> 与 <code>[2, 3]</code> 和 <code>[5, 7]</code> 都没有重叠。</li>
	<li>因此，没有任何一个索引可以与其他所有人互动，最大团队规模为 1。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">startTime = [3,4,6], endTime = [8,5,7]</span></p>

<p><strong>输出：</strong><span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>对于 <code>i = 0</code>，区间为 <code>[3, 8]</code>。</li>
	<li>它与 <code>i = 1</code> 的区间 <code>[4, 5]</code> 和 <code>i = 2</code> 的区间 <code>[6, 7]</code> 都有重叠。</li>
	<li>因此，索引 0 可以与所有其他索引互动，团队规模为 3。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == startTime.length == endTime.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= startTime[i] &lt;= endTime[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

我们首先将每个员工的开始时间和结束时间组合成一个区间 $\textit{intervals}$，并分别对所有员工的开始时间和结束时间进行排序。

对于每个员工 $i$，我们可以使用二分查找来计算出有多少员工的结束时间不早于 $i$ 的开始时间，以及有多少员工的开始时间不晚于 $i$ 的结束时间。两者的差值即为与员工 $i$ 有重叠的员工数量。我们遍历所有员工，计算出与每个员工有重叠的员工数量，并取最大值即为答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是员工的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumTeamSize(self, startTime: list[int], endTime: list[int]) -> int:
        intervals = list(zip(startTime, endTime))
        startTime.sort()
        endTime.sort()
        ans = 0
        for l, r in intervals:
            i = bisect_right(endTime, l - 1)
            j = bisect_right(startTime, r)
            ans = max(ans, j - i)
        return ans
```

#### Java

```java
class Solution {
    public int maximumTeamSize(int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[][] intervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            intervals[i][0] = startTime[i];
            intervals[i][1] = endTime[i];
        }
        Arrays.sort(startTime);
        Arrays.sort(endTime);

        int ans = 0;
        for (int[] it : intervals) {
            int l = it[0], r = it[1];

            int i = search(endTime, l - 1);
            int j = search(startTime, r);

            ans = Math.max(ans, j - i);
        }

        return ans;
    }

    private int search(int[] arr, int x) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] > x) {
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
    int maximumTeamSize(vector<int>& startTime, vector<int>& endTime) {
        int n = startTime.size();
        vector<pair<int, int>> intervals(n);
        for (int i = 0; i < n; i++) {
            intervals[i] = {startTime[i], endTime[i]};
        }

        sort(startTime.begin(), startTime.end());
        sort(endTime.begin(), endTime.end());

        int ans = 0;
        for (const auto& [l, r] : intervals) {
            int i = upper_bound(endTime.begin(), endTime.end(), l - 1) - endTime.begin();
            int j = upper_bound(startTime.begin(), startTime.end(), r) - startTime.begin();
            ans = max(ans, j - i);
        }

        return ans;
    }
};

```

#### Go

```go
func maximumTeamSize(startTime []int, endTime []int) int {
	n := len(startTime)
	intervals := make([][2]int, n)
	for i := 0; i < n; i++ {
		intervals[i] = [2]int{startTime[i], endTime[i]}
	}

	sort.Ints(startTime)
	sort.Ints(endTime)

	ans := 0
	for _, it := range intervals {
		l, r := it[0], it[1]

		i := sort.Search(len(endTime), func(k int) bool { return endTime[k] > l-1 })
		j := sort.Search(len(startTime), func(k int) bool { return startTime[k] > r })

		ans = max(ans, j-i)
	}

	return ans
}
```

#### TypeScript

```ts
function maximumTeamSize(startTime: number[], endTime: number[]): number {
    const n = startTime.length;
    const intervals: [number, number][] = Array.from({ length: n }, (_, i) => [
        startTime[i],
        endTime[i],
    ]);

    startTime.sort((a, b) => a - b);
    endTime.sort((a, b) => a - b);

    let ans = 0;
    for (const [l, r] of intervals) {
        const i = search(endTime, l - 1);
        const j = search(startTime, r);

        ans = Math.max(ans, j - i);
    }

    return ans;
}

function search(arr: number[], x: number): number {
    let l = 0;
    let r = arr.length;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (arr[mid] > x) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
