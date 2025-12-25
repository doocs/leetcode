---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2208.Minimum%20Operations%20to%20Halve%20Array%20Sum/README.md
rating: 1550
source: 第 74 场双周赛 Q3
tags:
    - 贪心
    - 数组
    - 堆（优先队列）
---

<!-- problem:start -->

# [2208. 将数组和减半的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-halve-array-sum)

[English Version](/solution/2200-2299/2208.Minimum%20Operations%20to%20Halve%20Array%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数数组&nbsp;<code>nums</code>&nbsp;。每一次操作中，你可以从&nbsp;<code>nums</code>&nbsp;中选择 <strong>任意</strong>&nbsp;一个数并将它减小到 <strong>恰好</strong>&nbsp;一半。（注意，在后续操作中你可以对减半过的数继续执行操作）</p>

<p>请你返回将 <code>nums</code>&nbsp;数组和 <strong>至少</strong>&nbsp;减少一半的 <strong>最少</strong>&nbsp;操作数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [5,19,8,1]
<b>输出：</b>3
<b>解释：</b>初始 nums 的和为 5 + 19 + 8 + 1 = 33 。
以下是将数组和减少至少一半的一种方法：
选择数字 19 并减小为 9.5 。
选择数字 9.5 并减小为 4.75 。
选择数字 8 并减小为 4 。
最终数组为 [5, 4.75, 4, 1] ，和为 5 + 4.75 + 4 + 1 = 14.75 。
nums 的和减小了 33 - 14.75 = 18.25 ，减小的部分超过了初始数组和的一半，18.25 &gt;= 33/2 = 16.5 。
我们需要 3 个操作实现题目要求，所以返回 3 。
可以证明，无法通过少于 3 个操作使数组和减少至少一半。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [3,8,20]
<b>输出：</b>3
<strong>解释：</strong>初始 nums 的和为 3 + 8 + 20 = 31 。
以下是将数组和减少至少一半的一种方法：
选择数字 20 并减小为 10 。
选择数字 10 并减小为 5 。
选择数字 3 并减小为 1.5 。
最终数组为 [1.5, 8, 5] ，和为 1.5 + 8 + 5 = 14.5 。
nums 的和减小了 31 - 14.5 = 16.5 ，减小的部分超过了初始数组和的一半， 16.5 &gt;= 31/2 = 15.5 。
我们需要 3 个操作实现题目要求，所以返回 3 。
可以证明，无法通过少于 3 个操作使数组和减少至少一半。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>7</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 优先队列（大根堆）

根据题目描述，每一次操作，都会将数组中的一个数减半。要使得数组和至少减少一半的操作次数最少，那么每一次操作都应该选择当前数组中的最大值进行减半。

因此，我们先算出数组要减少的总和 $s$，然后用一个优先队列（大根堆）维护数组中的所有数，每次从优先队列中取出最大值 $t$，将其减半，然后将减半后的数重新放入优先队列中，同时更新 $s$，直到 $s \le 0$ 为止。那么此时的操作次数就是答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def halveArray(self, nums: List[int]) -> int:
        s = sum(nums) / 2
        pq = []
        for x in nums:
            heappush(pq, -x)
        ans = 0
        while s > 0:
            t = -heappop(pq) / 2
            s -= t
            heappush(pq, -t)
            ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int halveArray(int[] nums) {
        PriorityQueue<Double> pq = new PriorityQueue<>(Collections.reverseOrder());
        double s = 0;
        for (int x : nums) {
            s += x;
            pq.offer((double) x);
        }
        s /= 2.0;
        int ans = 0;
        while (s > 0) {
            double t = pq.poll() / 2.0;
            s -= t;
            pq.offer(t);
            ++ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int halveArray(vector<int>& nums) {
        priority_queue<double> pq;
        double s = 0;
        for (int x : nums) {
            s += x;
            pq.push((double) x);
        }
        s /= 2.0;
        int ans = 0;
        while (s > 0) {
            double t = pq.top() / 2.0;
            pq.pop();
            s -= t;
            pq.push(t);
            ++ans;
        }
        return ans;
    }
};
```

#### Go

```go
func halveArray(nums []int) (ans int) {
	var s float64
	pq := &hp{}
	for _, x := range nums {
		s += float64(x)
		heap.Push(pq, float64(x))
	}
	s /= 2
	for s > 0 {
		t := heap.Pop(pq).(float64) / 2
		s -= t
		ans++
		heap.Push(pq, t)
	}
	return
}

type hp struct{ sort.Float64Slice }

func (h hp) Less(i, j int) bool { return h.Float64Slice[i] > h.Float64Slice[j] }
func (h *hp) Push(v any)        { h.Float64Slice = append(h.Float64Slice, v.(float64)) }
func (h *hp) Pop() any {
	a := h.Float64Slice
	v := a[len(a)-1]
	h.Float64Slice = a[:len(a)-1]
	return v
}
```

#### TypeScript

```ts
function halveArray(nums: number[]): number {
    let s: number = nums.reduce((a, b) => a + b) / 2;
    const pq = new MaxPriorityQueue<number>();
    for (const x of nums) {
        pq.enqueue(x);
    }
    let ans = 0;
    while (s > 0) {
        const t = pq.dequeue() / 2;
        s -= t;
        ++ans;
        pq.enqueue(t);
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::BinaryHeap;

impl Solution {
    pub fn halve_array(nums: Vec<i32>) -> i32 {
        let mut pq: BinaryHeap<i64> = BinaryHeap::new();
        let mut s: i64 = 0;

        for x in nums {
            let v = (x as i64) << 20;
            s += v;
            pq.push(v);
        }

        s /= 2;
        let mut ans = 0;

        while s > 0 {
            let t = pq.pop().unwrap() / 2;
            s -= t;
            pq.push(t);
            ans += 1;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
