---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3440.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20II/README.md
rating: 1997
source: 第 149 场双周赛 Q3
tags:
    - 贪心
    - 数组
    - 枚举
---

<!-- problem:start -->

# [3440. 重新安排会议得到最多空余时间 II](https://leetcode.cn/problems/reschedule-meetings-for-maximum-free-time-ii)

[English Version](/solution/3400-3499/3440.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数&nbsp;<code>eventTime</code>&nbsp;表示一个活动的总时长，这个活动开始于&nbsp;<code>t = 0</code>&nbsp;，结束于&nbsp;<code>t = eventTime</code>&nbsp;。</p>

<p>同时给你两个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>startTime</code> 和&nbsp;<code>endTime</code>&nbsp;。它们表示这次活动中 <code>n</code>&nbsp;个时间&nbsp;<strong>没有重叠</strong>&nbsp;的会议，其中第&nbsp;<code>i</code>&nbsp;个会议的时间为&nbsp;<code>[startTime[i], endTime[i]]</code>&nbsp;。</p>

<p>你可以重新安排 <strong>至多</strong>&nbsp;一个会议，安排的规则是将会议时间平移，且保持原来的 <strong>会议时长</strong>&nbsp;，你的目的是移动会议后 <strong>最大化</strong>&nbsp;相邻两个会议之间的 <strong>最长</strong> 连续空余时间。</p>

<p>请你返回重新安排会议以后，可以得到的 <strong>最大</strong>&nbsp;空余时间。</p>

<p><b>注意</b>，会议 <strong>不能</strong>&nbsp;安排到整个活动的时间以外，且会议之间需要保持互不重叠。</p>

<p><b>注意：</b>重新安排会议以后，会议之间的顺序可以发生改变。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>eventTime = 5, startTime = [1,3], endTime = [2,5]</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3440.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20II/images/example0_rescheduled.png" style="width: 375px; height: 123px;" /></p>

<p>将&nbsp;<code>[1, 2]</code>&nbsp;的会议安排到&nbsp;<code>[2, 3]</code>&nbsp;，得到空余时间&nbsp;<code>[0, 2]</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>eventTime = 10, startTime = [0,7,9], endTime = [1,8,10]</span></p>

<p><span class="example-io"><b>输出：</b>7</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3440.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20II/images/rescheduled_example0.png" style="width: 375px; height: 125px;" /></p>

<p>将&nbsp;<code>[0, 1]</code>&nbsp;的会议安排到&nbsp;<code>[8, 9]</code>&nbsp;，得到空余时间&nbsp;<code>[0, 7]</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>eventTime = 10, startTime = [0,3,7,9], endTime = [1,4,8,10]</span></p>

<p><b>输出：</b>6</p>

<p><b>解释：</b></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3440.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20II/images/image3.png" style="width: 375px; height: 125px;" /></strong></p>

<p>将&nbsp;<code>[3, 4]</code>&nbsp;的会议安排到&nbsp;<code>[8, 9]</code>&nbsp;，得到空余时间&nbsp;<code>[1, 7]</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>eventTime = 5, startTime = [0,1,2,3,4], endTime = [1,2,3,4,5]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><b>解释：</b></p>

<p>活动中的所有时间都被会议安排满了。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= eventTime &lt;= 10<sup>9</sup></code></li>
	<li><code>n == startTime.length == endTime.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= startTime[i] &lt; endTime[i] &lt;= eventTime</code></li>
	<li><code>endTime[i] &lt;= startTime[i + 1]</code> 其中&nbsp;<code>i</code> 在范围&nbsp;<code>[0, n - 2]</code>&nbsp;之间。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

根据题目描述，对于会议 $i$，我们记它左侧非空闲位置为 $l_i$，右侧非空闲位置为 $r_i$，记会议 $i$ 的时长为 $w_i = \text{endTime}[i] - \text{startTime}[i]$，则：

$$
l_i = \begin{cases}
0 & i = 0 \\\\
\text{endTime}[i - 1] & i \gt 0
\end{cases}
$$

$$
r_i = \begin{cases}
\text{eventTime} & i = n - 1 \\\\
\text{startTime}[i + 1] & i \lt n - 1
\end{cases}
$$

那么它可以向左移动，也可以向右移动，此时空闲时间为：

$$
r_i - l_i - w_i
$$

如果左侧存在最大的空闲时间 $\text{pre}_{i - 1}$，满足 $\text{pre}_{i - 1} \geq w_i$，则可以将会议 $i$ 向左移动到该位置，得到新的空闲时间：

$$
r_i - l_i
$$

同理，如果右侧存在最大的空闲时间 $\text{suf}_{i + 1}$，满足 $\text{suf}_{i + 1} \geq w_i$，则可以将会议 $i$ 向右移动到该位置，得到新的空闲时间：

$$
r_i - l_i
$$

因此，我们首先预处理两个数组 $\text{pre}$ 和 $\text{suf}$，其中 $\text{pre}[i]$ 表示 $[0, i]$ 范围内的最大空闲时间，$\text{suf}[i]$ 表示 $[i, n - 1]$ 范围内的最大空闲时间。然后遍历每个会议 $i$，计算它移动后的最大空闲时间，取最大值即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为会议的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxFreeTime(
        self, eventTime: int, startTime: List[int], endTime: List[int]
    ) -> int:
        n = len(startTime)
        pre = [0] * n
        suf = [0] * n
        pre[0] = startTime[0]
        suf[n - 1] = eventTime - endTime[-1]
        for i in range(1, n):
            pre[i] = max(pre[i - 1], startTime[i] - endTime[i - 1])
        for i in range(n - 2, -1, -1):
            suf[i] = max(suf[i + 1], startTime[i + 1] - endTime[i])
        ans = 0
        for i in range(n):
            l = 0 if i == 0 else endTime[i - 1]
            r = eventTime if i == n - 1 else startTime[i + 1]
            w = endTime[i] - startTime[i]
            ans = max(ans, r - l - w)
            if i and pre[i - 1] >= w:
                ans = max(ans, r - l)
            elif i + 1 < n and suf[i + 1] >= w:
                ans = max(ans, r - l)
        return ans
```

#### Java

```java
class Solution {
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[] pre = new int[n];
        int[] suf = new int[n];

        pre[0] = startTime[0];
        suf[n - 1] = eventTime - endTime[n - 1];

        for (int i = 1; i < n; i++) {
            pre[i] = Math.max(pre[i - 1], startTime[i] - endTime[i - 1]);
        }

        for (int i = n - 2; i >= 0; i--) {
            suf[i] = Math.max(suf[i + 1], startTime[i + 1] - endTime[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int l = (i == 0) ? 0 : endTime[i - 1];
            int r = (i == n - 1) ? eventTime : startTime[i + 1];
            int w = endTime[i] - startTime[i];
            ans = Math.max(ans, r - l - w);

            if (i > 0 && pre[i - 1] >= w) {
                ans = Math.max(ans, r - l);
            } else if (i + 1 < n && suf[i + 1] >= w) {
                ans = Math.max(ans, r - l);
            }
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxFreeTime(int eventTime, vector<int>& startTime, vector<int>& endTime) {
        int n = startTime.size();
        vector<int> pre(n), suf(n);

        pre[0] = startTime[0];
        suf[n - 1] = eventTime - endTime[n - 1];

        for (int i = 1; i < n; ++i) {
            pre[i] = max(pre[i - 1], startTime[i] - endTime[i - 1]);
        }

        for (int i = n - 2; i >= 0; --i) {
            suf[i] = max(suf[i + 1], startTime[i + 1] - endTime[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int l = (i == 0) ? 0 : endTime[i - 1];
            int r = (i == n - 1) ? eventTime : startTime[i + 1];
            int w = endTime[i] - startTime[i];
            ans = max(ans, r - l - w);

            if (i > 0 && pre[i - 1] >= w) {
                ans = max(ans, r - l);
            } else if (i + 1 < n && suf[i + 1] >= w) {
                ans = max(ans, r - l);
            }
        }

        return ans;
    }
};
```

#### Go

```go
func maxFreeTime(eventTime int, startTime []int, endTime []int) int {
	n := len(startTime)
	pre := make([]int, n)
	suf := make([]int, n)

	pre[0] = startTime[0]
	suf[n-1] = eventTime - endTime[n-1]

	for i := 1; i < n; i++ {
		pre[i] = max(pre[i-1], startTime[i]-endTime[i-1])
	}

	for i := n - 2; i >= 0; i-- {
		suf[i] = max(suf[i+1], startTime[i+1]-endTime[i])
	}

	ans := 0
	for i := 0; i < n; i++ {
		l := 0
		if i > 0 {
			l = endTime[i-1]
		}
		r := eventTime
		if i < n-1 {
			r = startTime[i+1]
		}
		w := endTime[i] - startTime[i]
		ans = max(ans, r-l-w)

		if i > 0 && pre[i-1] >= w {
			ans = max(ans, r-l)
		} else if i+1 < n && suf[i+1] >= w {
			ans = max(ans, r-l)
		}
	}

	return ans
}
```

#### TypeScript

```ts
function maxFreeTime(eventTime: number, startTime: number[], endTime: number[]): number {
    const n = startTime.length;
    const pre: number[] = Array(n).fill(0);
    const suf: number[] = Array(n).fill(0);

    pre[0] = startTime[0];
    suf[n - 1] = eventTime - endTime[n - 1];

    for (let i = 1; i < n; i++) {
        pre[i] = Math.max(pre[i - 1], startTime[i] - endTime[i - 1]);
    }

    for (let i = n - 2; i >= 0; i--) {
        suf[i] = Math.max(suf[i + 1], startTime[i + 1] - endTime[i]);
    }

    let ans = 0;
    for (let i = 0; i < n; i++) {
        const l = i === 0 ? 0 : endTime[i - 1];
        const r = i === n - 1 ? eventTime : startTime[i + 1];
        const w = endTime[i] - startTime[i];

        ans = Math.max(ans, r - l - w);

        if (i > 0 && pre[i - 1] >= w) {
            ans = Math.max(ans, r - l);
        } else if (i + 1 < n && suf[i + 1] >= w) {
            ans = Math.max(ans, r - l);
        }
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_free_time(event_time: i32, start_time: Vec<i32>, end_time: Vec<i32>) -> i32 {
        let n = start_time.len();
        let mut pre = vec![0; n];
        let mut suf = vec![0; n];

        pre[0] = start_time[0];
        suf[n - 1] = event_time - end_time[n - 1];

        for i in 1..n {
            pre[i] = pre[i - 1].max(start_time[i] - end_time[i - 1]);
        }

        for i in (0..n - 1).rev() {
            suf[i] = suf[i + 1].max(start_time[i + 1] - end_time[i]);
        }

        let mut ans = 0;
        for i in 0..n {
            let l = if i == 0 { 0 } else { end_time[i - 1] };
            let r = if i == n - 1 { event_time } else { start_time[i + 1] };
            let w = end_time[i] - start_time[i];
            ans = ans.max(r - l - w);

            if i > 0 && pre[i - 1] >= w {
                ans = ans.max(r - l);
            } else if i + 1 < n && suf[i + 1] >= w {
                ans = ans.max(r - l);
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
