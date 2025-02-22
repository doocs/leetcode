---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3066.Minimum%20Operations%20to%20Exceed%20Threshold%20Value%20II/README.md
rating: 1399
source: 第 125 场双周赛 Q2
tags:
    - 数组
    - 模拟
    - 堆（优先队列）
---

<!-- problem:start -->

# [3066. 超过阈值的最少操作数 II](https://leetcode.cn/problems/minimum-operations-to-exceed-threshold-value-ii)

[English Version](/solution/3000-3099/3066.Minimum%20Operations%20to%20Exceed%20Threshold%20Value%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>你可以对&nbsp;<code>nums</code>&nbsp;执行一些操作，在一次操作中，你可以：</p>

<ul>
	<li>选择 <code>nums</code>&nbsp;中 <strong>最小</strong> 的两个整数&nbsp;<code>x</code> 和&nbsp;<code>y</code>&nbsp;。</li>
	<li>将&nbsp;<code>x</code> 和&nbsp;<code>y</code> 从&nbsp;<code>nums</code>&nbsp;中删除。</li>
	<li>将&nbsp;<code>min(x, y) * 2 + max(x, y)</code>&nbsp;添加到数组中的任意位置。</li>
</ul>

<p><b>注意，</b>只有当&nbsp;<code>nums</code>&nbsp;<strong>至少</strong> 包含两个元素时，你才可以执行以上操作。</p>

<p>你需要使数组中的所有元素都 <strong>大于或等于</strong>&nbsp;<code>k</code>&nbsp;，请你返回需要的&nbsp;<strong>最少</strong>&nbsp;操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><b>输入：</b>nums = [2,11,10,1,3], k = 10</p>

<p><b>输出：</b>2</p>

<p><b>解释：</b></p>

<ol>
	<li>第一次操作中，我们删除元素 1 和 2 ，然后添加 <code>1 * 2 + 2</code> 到 <code>nums</code> 中，<code>nums</code> 变为 <code>[4, 11, 10, 3]</code> 。</li>
	<li>第二次操作中，我们删除元素 3 和 4 ，然后添加 <code>3 * 2 + 4</code> 到 <code>nums</code> 中，<code>nums</code> 变为 <code>[10, 11, 10]</code> 。</li>
</ol>

<p>此时，数组中的所有元素都大于等于 10 ，所以我们停止操作。</p>

<p>可以证明使数组中所有元素都大于等于 10 需要的最少操作次数为 2 。</p>

<p>&nbsp;</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><b>输入：</b>nums = [1,1,2,4,9], k = 20</p>

<p><b>输出：</b>4</p>

<p><b>解释：</b></p>

<ol>
	<li>第一次操作后，<code>nums</code> 变为 <code>[2, 4, 9, 3]</code>。</li>
	<li>第二次操作后，<code>nums</code> 变为 <code>[7, 4, 9]</code>。</li>
	<li>第三次操作后，<code>nums</code> 变为 <code>[15, 9]</code>。</li>
	<li>第四次操作后，<code>nums</code> 变为 <code>[33]</code>。</li>
</ol>

<p>此时，<code>nums</code> 中的所有元素都大于等于 20 ，所以我们停止操作。</p>

<p>可以证明使数组中所有元素都大于等于 20 需要的最少操作次数为 4 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
	<li>输入保证答案一定存在，也就是说，在进行某些次数的操作后，数组中所有元素都大于等于&nbsp;<code>k</code> 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：优先队列（小根堆）

我们可以使用优先队列（小根堆）来模拟这个过程。

具体地，我们先将数组中的元素加入优先队列 $pq$ 中。然后我们不断地从优先队列中取出两个最小的元素 $x$ 和 $y$，将 $\min(x, y) \times 2 + \max(x, y)$ 放回优先队列中。每次操作后，我们将操作次数加一。当队列中的元素个数小于 $2$ 或者队列中的最小元素大于等于 $k$ 时，我们停止操作。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int], k: int) -> int:
        heapify(nums)
        ans = 0
        while len(nums) > 1 and nums[0] < k:
            x, y = heappop(nums), heappop(nums)
            heappush(nums, x * 2 + y)
            ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums, int k) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int x : nums) {
            pq.offer((long) x);
        }
        int ans = 0;
        for (; pq.size() > 1 && pq.peek() < k; ++ans) {
            long x = pq.poll(), y = pq.poll();
            pq.offer(x * 2 + y);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int k) {
        using ll = long long;
        priority_queue<ll, vector<ll>, greater<ll>> pq;
        for (int x : nums) {
            pq.push(x);
        }
        int ans = 0;
        for (; pq.size() > 1 && pq.top() < k; ++ans) {
            ll x = pq.top();
            pq.pop();
            ll y = pq.top();
            pq.pop();
            pq.push(x * 2 + y);
        }
        return ans;
    }
};
```

#### Go

```go
func minOperations(nums []int, k int) (ans int) {
	pq := &hp{nums}
	heap.Init(pq)
	for ; pq.Len() > 1 && pq.IntSlice[0] < k; ans++ {
		x, y := heap.Pop(pq).(int), heap.Pop(pq).(int)
		heap.Push(pq, x*2+y)
	}
	return
}

type hp struct{ sort.IntSlice }

func (h *hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Pop() interface{} {
	old := h.IntSlice
	n := len(old)
	x := old[n-1]
	h.IntSlice = old[0 : n-1]
	return x
}
func (h *hp) Push(x interface{}) {
	h.IntSlice = append(h.IntSlice, x.(int))
}
```

#### TypeScript

```ts
function minOperations(nums: number[], k: number): number {
    const pq = new MinPriorityQueue();
    for (const x of nums) {
        pq.enqueue(x);
    }
    let ans = 0;
    for (; pq.size() > 1 && pq.front().element < k; ++ans) {
        const x = pq.dequeue().element;
        const y = pq.dequeue().element;
        pq.enqueue(x * 2 + y);
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::BinaryHeap;

impl Solution {
    pub fn min_operations(nums: Vec<i32>, k: i32) -> i32 {
        let mut pq = BinaryHeap::new();

        for &x in &nums {
            pq.push(-(x as i64));
        }

        let mut ans = 0;

        while pq.len() > 1 && -pq.peek().unwrap() < k as i64 {
            let x = -pq.pop().unwrap();
            let y = -pq.pop().unwrap();
            pq.push(-(x * 2 + y));
            ans += 1;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
