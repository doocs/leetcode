# [436. 寻找右区间](https://leetcode.cn/problems/find-right-interval)

[English Version](/solution/0400-0499/0436.Find%20Right%20Interval/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个区间数组 <code>intervals</code> ，其中&nbsp;<code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> ，且每个&nbsp;<code>start<sub>i</sub></code> 都 <strong>不同</strong> 。</p>

<p>区间 <code>i</code> 的 <strong>右侧区间</strong> 可以记作区间 <code>j</code> ，并满足 <code>start<sub>j</sub></code><code>&nbsp;&gt;= end<sub>i</sub></code> ，且 <code>start<sub>j</sub></code> <strong>最小化 </strong>。</p>

<p>返回一个由每个区间 <code>i</code> 的 <strong>右侧区间</strong> 在&nbsp;<code>intervals</code> 中对应下标组成的数组。如果某个区间 <code>i</code> 不存在对应的 <strong>右侧区间</strong> ，则下标 <code>i</code> 处的值设为 <code>-1</code> 。</p>
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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findRightInterval(self, intervals: List[List[int]]) -> List[int]:
        for i, v in enumerate(intervals):
            v.append(i)
        intervals.sort()
        n = len(intervals)
        ans = [-1] * n
        for _, e, i in intervals:
            j = bisect_left(intervals, [e])
            if j < n:
                ans[i] = intervals[j][2]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        List<int[]> starts = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            starts.add(new int[] {intervals[i][0], i});
        }
        starts.sort(Comparator.comparingInt(a -> a[0]));
        int[] res = new int[n];
        int i = 0;
        for (int[] interval : intervals) {
            int left = 0, right = n - 1;
            int end = interval[1];
            while (left < right) {
                int mid = (left + right) >> 1;
                if (starts.get(mid)[0] >= end) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            res[i++] = starts.get(left)[0] < end ? -1 : starts.get(left)[1];
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findRightInterval(vector<vector<int>>& intervals) {
        int n = intervals.size();
        vector<pair<int, int>> starts;
        for (int i = 0; i < n; ++i) {
            starts.emplace_back(make_pair(intervals[i][0], i));
        }
        sort(starts.begin(), starts.end());
        vector<int> res;
        for (auto interval : intervals) {
            int left = 0, right = n - 1;
            int end = interval[1];
            while (left < right) {
                int mid = left + right >> 1;
                if (starts[mid].first >= end)
                    right = mid;
                else
                    left = mid + 1;
            }
            res.push_back(starts[left].first < end ? -1 : starts[left].second);
        }
        return res;
    }
};
```

### **Go**

```go
func findRightInterval(intervals [][]int) []int {
	n := len(intervals)
	starts := make([][]int, n)
	for i := 0; i < n; i++ {
		starts[i] = make([]int, 2)
		starts[i][0] = intervals[i][0]
		starts[i][1] = i
	}
	sort.Slice(starts, func(i, j int) bool {
		return starts[i][0] < starts[j][0]
	})
	var res []int
	for _, interval := range intervals {
		left, right, end := 0, n-1, interval[1]
		for left < right {
			mid := (left + right) >> 1
			if starts[mid][0] >= end {
				right = mid
			} else {
				left = mid + 1
			}
		}
		val := -1
		if starts[left][0] >= end {
			val = starts[left][1]
		}
		res = append(res, val)
	}
	return res
}
```

### **TypeScript**

```ts
function findRightInterval(intervals: number[][]): number[] {
    const n = intervals.length;
    const starts = Array.from({ length: n }).map(() => new Array<number>(2));
    for (let i = 0; i < n; i++) {
        starts[i][0] = intervals[i][0];
        starts[i][1] = i;
    }
    starts.sort((a, b) => a[0] - b[0]);

    return intervals.map(([_, target]) => {
        let left = 0;
        let right = n;
        while (left < right) {
            const mid = (left + right) >>> 1;
            if (starts[mid][0] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (left >= n) {
            return -1;
        }
        return starts[left][1];
    });
}
```

### **...**

```

```

<!-- tabs:end -->
