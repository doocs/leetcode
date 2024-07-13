---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3169.Count%20Days%20Without%20Meetings/README.md
rating: 1483
source: 第 400 场周赛 Q2
tags:
    - 数组
    - 排序
---

<!-- problem:start -->

# [3169. 无需开会的工作日](https://leetcode.cn/problems/count-days-without-meetings)

[English Version](/solution/3100-3199/3169.Count%20Days%20Without%20Meetings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数 <code>days</code>，表示员工可工作的总天数（从第 1 天开始）。另给你一个二维数组 <code>meetings</code>，长度为 <code>n</code>，其中 <code>meetings[i] = [start_i, end_i]</code> 表示第 <code>i</code> 次会议的开始和结束天数（包含首尾）。</p>

<p>返回员工可工作且没有安排会议的天数。</p>

<p><strong>注意：</strong>会议时间可能会有重叠。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">days = 10, meetings = [[5,7],[1,3],[9,10]]</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>第 4 天和第 8 天没有安排会议。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">days = 5, meetings = [[2,4],[1,3]]</span></p>

<p><strong>输出：</strong><span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>第 5 天没有安排会议。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">days = 6, meetings = [[1,6]]</span></p>

<p><strong>输出：</strong>0</p>

<p><strong>解释：</strong></p>

<p>所有工作日都安排了会议。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= days &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= meetings.length &lt;= 10<sup>5</sup></code></li>
	<li><code>meetings[i].length == 2</code></li>
	<li><code>1 &lt;= meetings[i][0] &lt;= meetings[i][1] &lt;= days</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

我们不妨将所有会议按照开始时间排序，用一个变量 $\text{last}$ 记录此前会议的最晚结束时间。

然后我们遍历所有会议，对于每一个会议 $(\text{st}, \text{ed})$，如果 $\text{last} < \text{st}$，说明 $\text{last}$ 到 $\text{st}$ 之间的时间段是员工可以工作且没有安排会议的时间，我们将这段时间加入答案。然后我们更新 $\text{last} = \max(\text{last}, \text{ed})$。

最后，如果 $\text{last} < \text{days}$，说明最后一次会议结束后还有时间段是员工可以工作且没有安排会议的时间，我们将这段时间加入答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为会议的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countDays(self, days: int, meetings: List[List[int]]) -> int:
        meetings.sort()
        ans = last = 0
        for st, ed in meetings:
            if last < st:
                ans += st - last - 1
            last = max(last, ed)
        ans += days - last
        return ans
```

#### Java

```java
class Solution {
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        int ans = 0, last = 0;
        for (var e : meetings) {
            int st = e[0], ed = e[1];
            if (last < st) {
                ans += st - last - 1;
            }
            last = Math.max(last, ed);
        }
        ans += days - last;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countDays(int days, vector<vector<int>>& meetings) {
        sort(meetings.begin(), meetings.end());
        int ans = 0, last = 0;
        for (auto& e : meetings) {
            int st = e[0], ed = e[1];
            if (last < st) {
                ans += st - last - 1;
            }
            last = max(last, ed);
        }
        ans += days - last;
        return ans;
    }
};
```

#### Go

```go
func countDays(days int, meetings [][]int) (ans int) {
	sort.Slice(meetings, func(i, j int) bool { return meetings[i][0] < meetings[j][0] })
	last := 0
	for _, e := range meetings {
		st, ed := e[0], e[1]
		if last < st {
			ans += st - last - 1
		}
		last = max(last, ed)
	}
	ans += days - last
	return
}
```

#### TypeScript

```ts
function countDays(days: number, meetings: number[][]): number {
    meetings.sort((a, b) => a[0] - b[0]);
    let [ans, last] = [0, 0];
    for (const [st, ed] of meetings) {
        if (last < st) {
            ans += st - last - 1;
        }
        last = Math.max(last, ed);
    }
    ans += days - last;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
