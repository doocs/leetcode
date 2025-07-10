---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3439.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20I/README.md
rating: 1728
source: 第 149 场双周赛 Q2
tags:
    - 贪心
    - 数组
    - 滑动窗口
---

<!-- problem:start -->

# [3439. 重新安排会议得到最多空余时间 I](https://leetcode.cn/problems/reschedule-meetings-for-maximum-free-time-i)

[English Version](/solution/3400-3499/3439.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数&nbsp;<code>eventTime</code>&nbsp;表示一个活动的总时长，这个活动开始于&nbsp;<code>t = 0</code>&nbsp;，结束于&nbsp;<code>t = eventTime</code>&nbsp;。</p>

<p>同时给你两个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>startTime</code> 和&nbsp;<code>endTime</code>&nbsp;。它们表示这次活动中 <code>n</code>&nbsp;个时间&nbsp;<strong>没有重叠</strong>&nbsp;的会议，其中第&nbsp;<code>i</code>&nbsp;个会议的时间为&nbsp;<code>[startTime[i], endTime[i]]</code>&nbsp;。</p>

<p>你可以重新安排 <strong>至多</strong>&nbsp;<code>k</code>&nbsp;个会议，安排的规则是将会议时间平移，且保持原来的 <strong>会议时长</strong>&nbsp;，你的目的是移动会议后 <strong>最大化</strong>&nbsp;相邻两个会议之间的 <strong>最长</strong> 连续空余时间。</p>

<p>移动前后所有会议之间的 <strong>相对</strong>&nbsp;顺序需要保持不变，而且会议时间也需要保持互不重叠。</p>

<p>请你返回重新安排会议以后，可以得到的 <strong>最大</strong>&nbsp;空余时间。</p>

<p><b>注意</b>，会议 <strong>不能</strong>&nbsp;安排到整个活动的时间以外。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>eventTime = 5, k = 1, startTime = [1,3], endTime = [2,5]</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3439.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20I/images/example0_rescheduled.png" style="width: 375px; height: 123px;" /></p>

<p>将&nbsp;<code>[1, 2]</code>&nbsp;的会议安排到&nbsp;<code>[2, 3]</code>&nbsp;，得到空余时间&nbsp;<code>[0, 2]</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>eventTime = 10, k = 1, startTime = [0,2,9], endTime = [1,4,10]</span></p>

<p><span class="example-io"><b>输出：</b>6</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3439.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20I/images/example1_rescheduled.png" style="width: 375px; height: 125px;" /></p>

<p>将&nbsp;<code>[2, 4]</code>&nbsp;的会议安排到&nbsp;<code>[1, 3]</code>&nbsp;，得到空余时间&nbsp;<code>[3, 9]</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>eventTime = 5, k = 2, startTime = [0,1,2,3,4], endTime = [1,2,3,4,5]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<p>活动中的所有时间都被会议安排满了。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= eventTime &lt;= 10<sup>9</sup></code></li>
	<li><code>n == startTime.length == endTime.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li><code>0 &lt;= startTime[i] &lt; endTime[i] &lt;= eventTime</code></li>
	<li><code>endTime[i] &lt;= startTime[i + 1]</code> 其中&nbsp;<code>i</code>&nbsp;在范围&nbsp;<code>[0, n - 2]</code>&nbsp;之间。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滑动窗口

题目相当于把相邻的空闲时间段合并成一个更长的空闲时间段。一共有 $n + 1$ 个空闲时间段，分别是：

-   第一个空闲时间段是从活动开始到第一个会议开始的时间段；
-   中间的 $n - 1$ 个空闲时间段是相邻两个会议之间的时间段；
-   最后一个空闲时间段是最后一个会议结束到活动结束的时间段。

题目最多可以重新安排 $k$ 个会议，等价于最多可以合并 $k + 1$ 个空闲时间段。我们需要找到这 $k + 1$ 个空闲时间段的最大长度。

我们可以将这些空闲时间段的长度存储在一个数组中 $\textit{nums}$ 中。然后，我们一个长度为 $k + 1$ 的滑动窗口，遍历这个数组，计算每个窗口的和，找到最大的和，即为所求的最大空闲时间。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是会议的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxFreeTime(
        self, eventTime: int, k: int, startTime: List[int], endTime: List[int]
    ) -> int:
        nums = [startTime[0]]
        for i in range(1, len(endTime)):
            nums.append(startTime[i] - endTime[i - 1])
        nums.append(eventTime - endTime[-1])
        ans = s = 0
        for i, x in enumerate(nums):
            s += x
            if i >= k:
                ans = max(ans, s)
                s -= nums[i - k]
        return ans
```

#### Java

```java
class Solution {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = endTime.length;
        int[] nums = new int[n + 1];
        nums[0] = startTime[0];
        for (int i = 1; i < n; ++i) {
            nums[i] = startTime[i] - endTime[i - 1];
        }
        nums[n] = eventTime - endTime[n - 1];
        int ans = 0, s = 0;
        for (int i = 0; i <= n; ++i) {
            s += nums[i];
            if (i >= k) {
                ans = Math.max(ans, s);
                s -= nums[i - k];
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
    int maxFreeTime(int eventTime, int k, vector<int>& startTime, vector<int>& endTime) {
        int n = endTime.size();
        vector<int> nums(n + 1);
        nums[0] = startTime[0];
        for (int i = 1; i < n; ++i) {
            nums[i] = startTime[i] - endTime[i - 1];
        }
        nums[n] = eventTime - endTime[n - 1];

        int ans = 0, s = 0;
        for (int i = 0; i <= n; ++i) {
            s += nums[i];
            if (i >= k) {
                ans = max(ans, s);
                s -= nums[i - k];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxFreeTime(eventTime int, k int, startTime []int, endTime []int) int {
	n := len(endTime)
	nums := make([]int, n+1)
	nums[0] = startTime[0]
	for i := 1; i < n; i++ {
		nums[i] = startTime[i] - endTime[i-1]
	}
	nums[n] = eventTime - endTime[n-1]

	ans, s := 0, 0
	for i := 0; i <= n; i++ {
		s += nums[i]
		if i >= k {
			ans = max(ans, s)
			s -= nums[i-k]
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxFreeTime(eventTime: number, k: number, startTime: number[], endTime: number[]): number {
    const n = endTime.length;
    const nums: number[] = new Array(n + 1);
    nums[0] = startTime[0];
    for (let i = 1; i < n; i++) {
        nums[i] = startTime[i] - endTime[i - 1];
    }
    nums[n] = eventTime - endTime[n - 1];

    let [ans, s] = [0, 0];
    for (let i = 0; i <= n; i++) {
        s += nums[i];
        if (i >= k) {
            ans = Math.max(ans, s);
            s -= nums[i - k];
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_free_time(event_time: i32, k: i32, start_time: Vec<i32>, end_time: Vec<i32>) -> i32 {
        let n = end_time.len();
        let mut nums = vec![0; n + 1];
        nums[0] = start_time[0];
        for i in 1..n {
            nums[i] = start_time[i] - end_time[i - 1];
        }
        nums[n] = event_time - end_time[n - 1];

        let mut ans = 0;
        let mut s = 0;
        for i in 0..=n {
            s += nums[i];
            if i as i32 >= k {
                ans = ans.max(s);
                s -= nums[i - k as usize];
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：滑动窗口（空间优化）

在方法一中，我们使用了一个数组来存储空闲时间段的长度。实际上，我们不需要存储整个数组，可以用一个函数 $f(i)$ 来表示第 $i$ 个空闲时间段的长度。这样可以节省空间。

时间复杂度 $O(n)$，其中 $n$ 是会议的数量。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxFreeTime(
        self, eventTime: int, k: int, startTime: List[int], endTime: List[int]
    ) -> int:
        def f(i: int) -> int:
            if i == 0:
                return startTime[0]
            if i == len(endTime):
                return eventTime - endTime[-1]
            return startTime[i] - endTime[i - 1]

        ans = s = 0
        for i in range(len(endTime) + 1):
            s += f(i)
            if i >= k:
                ans = max(ans, s)
                s -= f(i - k)
        return ans
```

#### Java

```java
class Solution {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = endTime.length;
        IntUnaryOperator f = i -> {
            if (i == 0) {
                return startTime[0];
            }
            if (i == n) {
                return eventTime - endTime[n - 1];
            }
            return startTime[i] - endTime[i - 1];
        };
        int ans = 0, s = 0;
        for (int i = 0; i <= n; i++) {
            s += f.applyAsInt(i);
            if (i >= k) {
                ans = Math.max(ans, s);
                s -= f.applyAsInt(i - k);
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
    int maxFreeTime(int eventTime, int k, vector<int>& startTime, vector<int>& endTime) {
        int n = endTime.size();
        auto f = [&](int i) -> int {
            if (i == 0) {
                return startTime[0];
            }
            if (i == n) {
                return eventTime - endTime[n - 1];
            }
            return startTime[i] - endTime[i - 1];
        };
        int ans = 0, s = 0;
        for (int i = 0; i <= n; ++i) {
            s += f(i);
            if (i >= k) {
                ans = max(ans, s);
                s -= f(i - k);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxFreeTime(eventTime int, k int, startTime []int, endTime []int) int {
	n := len(endTime)
	f := func(i int) int {
		if i == 0 {
			return startTime[0]
		}
		if i == n {
			return eventTime - endTime[n-1]
		}
		return startTime[i] - endTime[i-1]
	}
	ans, s := 0, 0
	for i := 0; i <= n; i++ {
		s += f(i)
		if i >= k {
			ans = max(ans, s)
			s -= f(i - k)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxFreeTime(eventTime: number, k: number, startTime: number[], endTime: number[]): number {
    const n = endTime.length;
    const f = (i: number): number => {
        if (i === 0) {
            return startTime[0];
        }
        if (i === n) {
            return eventTime - endTime[n - 1];
        }
        return startTime[i] - endTime[i - 1];
    };
    let ans = 0;
    let s = 0;
    for (let i = 0; i <= n; i++) {
        s += f(i);
        if (i >= k) {
            ans = Math.max(ans, s);
            s -= f(i - k);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_free_time(event_time: i32, k: i32, start_time: Vec<i32>, end_time: Vec<i32>) -> i32 {
        let n = end_time.len();
        let f = |i: usize| -> i32 {
            if i == 0 {
                start_time[0]
            } else if i == n {
                event_time - end_time[n - 1]
            } else {
                start_time[i] - end_time[i - 1]
            }
        };
        let mut ans = 0;
        let mut s = 0;
        for i in 0..=n {
            s += f(i);
            if i >= k as usize {
                ans = ans.max(s);
                s -= f(i - k as usize);
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
