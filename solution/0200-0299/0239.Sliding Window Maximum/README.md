---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0239.Sliding%20Window%20Maximum/README.md
tags:
    - 队列
    - 数组
    - 滑动窗口
    - 单调队列
    - 堆（优先队列）
---

<!-- problem:start -->

# [239. 滑动窗口最大值](https://leetcode.cn/problems/sliding-window-maximum)

[English Version](/solution/0200-0299/0239.Sliding%20Window%20Maximum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>，有一个大小为&nbsp;<code>k</code><em>&nbsp;</em>的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 <code>k</code>&nbsp;个数字。滑动窗口每次只向右移动一位。</p>

<p>返回 <em>滑动窗口中的最大值 </em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,3,-1,-3,5,3,6,7], k = 3
<b>输出：</b>[3,3,5,5,6,7]
<b>解释：</b>
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       <strong>3</strong>
 1 [3  -1  -3] 5  3  6  7       <strong>3</strong>
 1  3 [-1  -3  5] 3  6  7      <strong> 5</strong>
 1  3  -1 [-3  5  3] 6  7       <strong>5</strong>
 1  3  -1  -3 [5  3  6] 7       <strong>6</strong>
 1  3  -1  -3  5 [3  6  7]      <strong>7</strong>
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1], k = 1
<b>输出：</b>[1]
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：优先队列（大根堆）

我们可以使用优先队列（大根堆）来维护滑动窗口中的最大值。

先将前 $k-1$ 个元素加入优先队列，接下来从第 $k$ 个元素开始，将新元素加入优先队列，同时判断堆顶元素是否滑出窗口，如果滑出窗口则将堆顶元素弹出。然后我们将堆顶元素加入结果数组。

时间复杂度 $O(n \times \log k)$，空间复杂度 $O(k)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

```python
class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        q = [(-v, i) for i, v in enumerate(nums[: k - 1])]
        heapify(q)
        ans = []
        for i in range(k - 1, len(nums)):
            heappush(q, (-nums[i], i))
            while q[0][1] <= i - k:
                heappop(q)
            ans.append(-q[0][0])
        return ans
```

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<int[]> q
            = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        int n = nums.length;
        for (int i = 0; i < k - 1; ++i) {
            q.offer(new int[] {nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        for (int i = k - 1, j = 0; i < n; ++i) {
            q.offer(new int[] {nums[i], i});
            while (q.peek()[1] <= i - k) {
                q.poll();
            }
            ans[j++] = q.peek()[0];
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        priority_queue<pair<int, int>> q;
        int n = nums.size();
        for (int i = 0; i < k - 1; ++i) {
            q.push({nums[i], -i});
        }
        vector<int> ans;
        for (int i = k - 1; i < n; ++i) {
            q.push({nums[i], -i});
            while (-q.top().second <= i - k) {
                q.pop();
            }
            ans.emplace_back(q.top().first);
        }
        return ans;
    }
};
```

```go
func maxSlidingWindow(nums []int, k int) (ans []int) {
	q := hp{}
	for i, v := range nums[:k-1] {
		heap.Push(&q, pair{v, i})
	}
	for i := k - 1; i < len(nums); i++ {
		heap.Push(&q, pair{nums[i], i})
		for q[0].i <= i-k {
			heap.Pop(&q)
		}
		ans = append(ans, q[0].v)
	}
	return
}

type pair struct{ v, i int }

type hp []pair

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	a, b := h[i], h[j]
	return a.v > b.v || (a.v == b.v && i < j)
}
func (h hp) Swap(i, j int) { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)   { *h = append(*h, v.(pair)) }
func (h *hp) Pop() any     { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

```rust
use std::collections::VecDeque;

impl Solution {
    #[allow(dead_code)]
    pub fn max_sliding_window(nums: Vec<i32>, k: i32) -> Vec<i32> {
        // The deque contains the index of `nums`
        let mut q: VecDeque<usize> = VecDeque::new();
        let mut ans_vec: Vec<i32> = Vec::new();

        for i in 0..nums.len() {
            // Check the first element of queue, if it's out of bound
            if !q.is_empty() && (i as i32) - k + 1 > (*q.front().unwrap() as i32) {
                // Pop it out
                q.pop_front();
            }
            // Pop back elements out until either the deque is empty
            // Or the back element is greater than the current traversed element
            while !q.is_empty() && nums[*q.back().unwrap()] <= nums[i] {
                q.pop_back();
            }
            // Push the current index in queue
            q.push_back(i);
            // Check if the condition is satisfied
            if i >= ((k - 1) as usize) {
                ans_vec.push(nums[*q.front().unwrap()]);
            }
        }

        ans_vec
    }
}
```

```js
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number[]}
 */
var maxSlidingWindow = function (nums, k) {
    let ans = [];
    let q = [];
    for (let i = 0; i < nums.length; ++i) {
        if (q && i - k + 1 > q[0]) {
            q.shift();
        }
        while (q && nums[q[q.length - 1]] <= nums[i]) {
            q.pop();
        }
        q.push(i);
        if (i >= k - 1) {
            ans.push(nums[q[0]]);
        }
    }
    return ans;
};
```

```cs
using System.Collections.Generic;

public class Solution {
    public int[] MaxSlidingWindow(int[] nums, int k) {
        if (nums.Length == 0) return new int[0];
        var result = new int[nums.Length - k + 1];
        var descOrderNums = new LinkedList<int>();
        for (var i = 0; i < nums.Length; ++i)
        {
            if (i >= k && nums[i - k] == descOrderNums.First.Value)
            {
                descOrderNums.RemoveFirst();
            }
            while (descOrderNums.Count > 0 && nums[i] > descOrderNums.Last.Value)
            {
                descOrderNums.RemoveLast();
            }
            descOrderNums.AddLast(nums[i]);
            if (i >= k - 1)
            {
                result[i - k + 1] = descOrderNums.First.Value;
            }
        }
        return result;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：单调队列

这道题也可以使用单调队列来解决。时间复杂度 $O(n)$，空间复杂度 $O(k)$。

单调队列常见模型：找出滑动窗口中的最大值/最小值。模板：

```python
q = deque()
for i in range(n):
    # 判断队头是否滑出窗口
    while q and checkout_out(q[0]):
        q.popleft()
    while q and check(q[-1]):
        q.pop()
    q.append(i)
```

<!-- tabs:start -->

```python
class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        q = deque()
        ans = []
        for i, v in enumerate(nums):
            if q and i - k + 1 > q[0]:
                q.popleft()
            while q and nums[q[-1]] <= v:
                q.pop()
            q.append(i)
            if i >= k - 1:
                ans.append(nums[q[0]])
        return ans
```

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0, j = 0; i < n; ++i) {
            if (!q.isEmpty() && i - k + 1 > q.peekFirst()) {
                q.pollFirst();
            }
            while (!q.isEmpty() && nums[q.peekLast()] <= nums[i]) {
                q.pollLast();
            }
            q.offer(i);
            if (i >= k - 1) {
                ans[j++] = nums[q.peekFirst()];
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        deque<int> q;
        vector<int> ans;
        for (int i = 0; i < nums.size(); ++i) {
            if (!q.empty() && i - k + 1 > q.front()) {
                q.pop_front();
            }
            while (!q.empty() && nums[q.back()] <= nums[i]) {
                q.pop_back();
            }
            q.push_back(i);
            if (i >= k - 1) {
                ans.emplace_back(nums[q.front()]);
            }
        }
        return ans;
    }
};
```

```go
func maxSlidingWindow(nums []int, k int) (ans []int) {
	q := []int{}
	for i, v := range nums {
		if len(q) > 0 && i-k+1 > q[0] {
			q = q[1:]
		}
		for len(q) > 0 && nums[q[len(q)-1]] <= v {
			q = q[:len(q)-1]
		}
		q = append(q, i)
		if i >= k-1 {
			ans = append(ans, nums[q[0]])
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
