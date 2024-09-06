---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0056.Merge%20Intervals/README.md
tags:
    - 数组
    - 排序
---

<!-- problem:start -->

# [56. 合并区间](https://leetcode.cn/problems/merge-intervals)

[English Version](/solution/0000-0099/0056.Merge%20Intervals/README_EN.md)

## 题目描述

<!-- description:start -->

<p>以数组 <code>intervals</code> 表示若干个区间的集合，其中单个区间为 <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> 。请你合并所有重叠的区间，并返回&nbsp;<em>一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间</em>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,3],[2,6],[8,10],[15,18]]
<strong>输出：</strong>[[1,6],[8,10],[15,18]]
<strong>解释：</strong>区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,4],[4,5]]
<strong>输出：</strong>[[1,5]]
<strong>解释：</strong>区间 [1,4] 和 [4,5] 可被视为重叠区间。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 10<sup>4</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 一次遍历

我们可以将区间按照左端点升序排列，然后遍历区间进行合并操作。

具体的合并操作如下。

我们先将第一个区间加入答案，然后依次考虑之后的每个区间：

-   如果答案数组中最后一个区间的右端点小于当前考虑区间的左端点，说明两个区间不会重合，因此我们可以直接将当前区间加入答案数组末尾；
-   否则，说明两个区间重合，我们需要用当前区间的右端点更新答案数组中最后一个区间的右端点，将其置为二者的较大值。

最后，我们返回答案数组即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为区间个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        intervals.sort()
        ans = []
        st, ed = intervals[0]
        for s, e in intervals[1:]:
            if ed < s:
                ans.append([st, ed])
                st, ed = s, e
            else:
                ed = max(ed, e)
        ans.append([st, ed])
        return ans
```

#### Java

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int st = intervals[0][0], ed = intervals[0][1];
        List<int[]> ans = new ArrayList<>();
        for (int i = 1; i < intervals.length; ++i) {
            int s = intervals[i][0], e = intervals[i][1];
            if (ed < s) {
                ans.add(new int[] {st, ed});
                st = s;
                ed = e;
            } else {
                ed = Math.max(ed, e);
            }
        }
        ans.add(new int[] {st, ed});
        return ans.toArray(new int[ans.size()][]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> merge(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end());
        int st = intervals[0][0], ed = intervals[0][1];
        vector<vector<int>> ans;
        for (int i = 1; i < intervals.size(); ++i) {
            if (ed < intervals[i][0]) {
                ans.push_back({st, ed});
                st = intervals[i][0];
                ed = intervals[i][1];
            } else {
                ed = max(ed, intervals[i][1]);
            }
        }
        ans.push_back({st, ed});
        return ans;
    }
};
```

#### Go

```go
func merge(intervals [][]int) (ans [][]int) {
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][0] < intervals[j][0]
	})
	st, ed := intervals[0][0], intervals[0][1]
	for _, e := range intervals[1:] {
		if ed < e[0] {
			ans = append(ans, []int{st, ed})
			st, ed = e[0], e[1]
		} else if ed < e[1] {
			ed = e[1]
		}
	}
	ans = append(ans, []int{st, ed})
	return ans
}
```

#### TypeScript

```ts
function merge(intervals: number[][]): number[][] {
    intervals.sort((a, b) => a[0] - b[0]);
    const ans: number[][] = [];
    let [st, ed] = intervals[0];
    for (const [s, e] of intervals.slice(1)) {
        if (ed < s) {
            ans.push([st, ed]);
            [st, ed] = [s, e];
        } else {
            ed = Math.max(ed, e);
        }
    }
    ans.push([st, ed]);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn merge(mut intervals: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        intervals.sort_unstable_by(|a, b| a[0].cmp(&b[0]));
        let n = intervals.len();
        let mut res = vec![];
        let mut i = 0;
        while i < n {
            let l = intervals[i][0];
            let mut r = intervals[i][1];
            i += 1;
            while i < n && r >= intervals[i][0] {
                r = r.max(intervals[i][1]);
                i += 1;
            }
            res.push(vec![l, r]);
        }
        res
    }
}
```

#### C#

```cs
public class Solution {
    public int[][] Merge(int[][] intervals) {
        intervals = intervals.OrderBy(a => a[0]).ToArray();
        int st = intervals[0][0], ed = intervals[0][1];
        var ans = new List<int[]>();
        for (int i = 1; i < intervals.Length; ++i) {
            if (ed < intervals[i][0]) {
                ans.Add(new int[] { st, ed });
                st = intervals[i][0];
                ed = intervals[i][1];
            } else {
                ed = Math.Max(ed, intervals[i][1]);
            }
        }
        ans.Add(new int[] { st, ed });
        return ans.ToArray();
    }
}
```

### JavaScript

```js
/**
 * @param {number[][]} intervals
 * @return {number[][]}
 */
var merge = function (intervals) {
    intervals.sort((a, b) => a[0] - b[0]);
    const result = [];
    const n = intervals.length;
    let i = 0;
    while (i < n) {
        const left = intervals[i][0];
        let right = intervals[i][1];
        while (true) {
            i++;
            if (i < n && right >= intervals[i][0]) {
                right = Math.max(right, intervals[i][1]);
            } else {
                result.push([left, right]);
                break;
            }
        }
    }
    return result;
};
```

#### Kotlin

```kotlin
class Solution {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortBy { it[0] }
        val result = mutableListOf<IntArray>()
        val n = intervals.size
        var i = 0
        while (i < n) {
            val left = intervals[i][0]
            var right = intervals[i][1]
            while (true) {
                i++
                if (i < n && right >= intervals[i][0]) {
                    right = maxOf(right, intervals[i][1])
                } else {
                    result.add(intArrayOf(left, right))
                    break
                }
            }
        }
        return result.toTypedArray()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        intervals.sort()
        ans = [intervals[0]]
        for s, e in intervals[1:]:
            if ans[-1][1] < s:
                ans.append([s, e])
            else:
                ans[-1][1] = max(ans[-1][1], e)
        return ans
```

#### Java

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> ans = new ArrayList<>();
        ans.add(intervals[0]);
        for (int i = 1; i < intervals.length; ++i) {
            int s = intervals[i][0], e = intervals[i][1];
            if (ans.get(ans.size() - 1)[1] < s) {
                ans.add(intervals[i]);
            } else {
                ans.get(ans.size() - 1)[1] = Math.max(ans.get(ans.size() - 1)[1], e);
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> merge(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end());
        vector<vector<int>> ans;
        ans.emplace_back(intervals[0]);
        for (int i = 1; i < intervals.size(); ++i) {
            if (ans.back()[1] < intervals[i][0]) {
                ans.emplace_back(intervals[i]);
            } else {
                ans.back()[1] = max(ans.back()[1], intervals[i][1]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func merge(intervals [][]int) (ans [][]int) {
	sort.Slice(intervals, func(i, j int) bool { return intervals[i][0] < intervals[j][0] })
	ans = append(ans, intervals[0])
	for _, e := range intervals[1:] {
		if ans[len(ans)-1][1] < e[0] {
			ans = append(ans, e)
		} else {
			ans[len(ans)-1][1] = max(ans[len(ans)-1][1], e[1])
		}
	}
	return
}
```

#### TypeScript

```ts
function merge(intervals: number[][]): number[][] {
    intervals.sort((a, b) => a[0] - b[0]);
    const ans: number[][] = [intervals[0]];
    for (let i = 1; i < intervals.length; ++i) {
        if (ans.at(-1)[1] < intervals[i][0]) {
            ans.push(intervals[i]);
        } else {
            ans.at(-1)[1] = Math.max(ans.at(-1)[1], intervals[i][1]);
        }
    }
    return ans;
}
```

#### C#

```cs
public class Solution {
    public int[][] Merge(int[][] intervals) {
        intervals = intervals.OrderBy(a => a[0]).ToArray();
        var ans = new List<int[]>();
        ans.Add(intervals[0]);
        for (int i = 1; i < intervals.Length; ++i) {
            if (ans[ans.Count - 1][1] < intervals[i][0]) {
                ans.Add(intervals[i]);
            } else {
                ans[ans.Count - 1][1] = Math.Max(ans[ans.Count - 1][1], intervals[i][1]);
            }
        }
        return ans.ToArray();
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三

<!-- tabs:start -->

#### TypeScript

```ts
function merge(intervals: number[][]): number[][] {
    intervals.sort((a, b) => a[0] - b[0]);
    const n = intervals.length;
    const res = [];
    let i = 0;
    while (i < n) {
        let [l, r] = intervals[i];
        i++;
        while (i < n && r >= intervals[i][0]) {
            r = Math.max(r, intervals[i][1]);
            i++;
        }
        res.push([l, r]);
    }
    return res;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
