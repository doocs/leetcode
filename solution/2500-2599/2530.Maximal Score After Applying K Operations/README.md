---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2530.Maximal%20Score%20After%20Applying%20K%20Operations/README.md
rating: 1386
source: 第 327 场周赛 Q2
tags:
    - 贪心
    - 数组
    - 堆（优先队列）
---

<!-- problem:start -->

# [2530. 执行 K 次操作后的最大分数](https://leetcode.cn/problems/maximal-score-after-applying-k-operations)

[English Version](/solution/2500-2599/2530.Maximal%20Score%20After%20Applying%20K%20Operations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 和一个整数 <code>k</code> 。你的 <strong>起始分数</strong> 为 <code>0</code> 。</p>

<p>在一步 <strong>操作</strong> 中：</p>

<ol>
	<li>选出一个满足 <code>0 &lt;= i &lt; nums.length</code> 的下标 <code>i</code> ，</li>
	<li>将你的 <strong>分数</strong> 增加 <code>nums[i]</code> ，并且</li>
	<li>将 <code>nums[i]</code> 替换为 <code>ceil(nums[i] / 3)</code> 。</li>
</ol>

<p>返回在 <strong>恰好</strong> 执行 <code>k</code> 次操作后，你可能获得的最大分数。</p>

<p>向上取整函数 <code>ceil(val)</code> 的结果是大于或等于 <code>val</code> 的最小整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [10,10,10,10,10], k = 5
<strong>输出：</strong>50
<strong>解释：</strong>对数组中每个元素执行一次操作。最后分数是 10 + 10 + 10 + 10 + 10 = 50 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,10,3,3,3], k = 3
<strong>输出：</strong>17
<strong>解释：</strong>可以执行下述操作：
第 1 步操作：选中 i = 1 ，nums 变为 [1,<em><strong>4</strong></em>,3,3,3] 。分数增加 10 。
第 2 步操作：选中 i = 1 ，nums 变为 [1,<em><strong>2</strong></em>,3,3,3] 。分数增加 4 。
第 3 步操作：选中 i = 2 ，nums 变为 [1,2,<em><strong>1</strong></em>,3,3] 。分数增加 3 。
最后分数是 10 + 4 + 3 = 17 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, k &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：优先队列（大根堆）

要使得分数最大化，我们需要在每一步操作中，选择元素值最大的元素进行操作。因此，我们可以使用优先队列（大根堆）来维护当前元素值最大的元素。

每次从优先队列中取出元素值最大的元素 $v$，将答案加上 $v$，并将 $v$ 替换为 $\lceil \frac{v}{3} \rceil$，加入优先队列。重复 $k$ 次后，将答案返回即可。

时间复杂度 $O(n + k \times \log n)$，空间复杂度 $O(n)$ 或 $O(1)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxKelements(self, nums: List[int], k: int) -> int:
        h = [-v for v in nums]
        heapify(h)
        ans = 0
        for _ in range(k):
            v = -heappop(h)
            ans += v
            heappush(h, -(ceil(v / 3)))
        return ans
```

#### Java

```java
class Solution {
    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int v : nums) {
            pq.offer(v);
        }
        long ans = 0;
        while (k-- > 0) {
            int v = pq.poll();
            ans += v;
            pq.offer((v + 2) / 3);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxKelements(vector<int>& nums, int k) {
        priority_queue<int> pq(nums.begin(), nums.end());
        long long ans = 0;
        while (k--) {
            int v = pq.top();
            pq.pop();
            ans += v;
            pq.push((v + 2) / 3);
        }
        return ans;
    }
};
```

#### Go

```go
func maxKelements(nums []int, k int) (ans int64) {
	h := &hp{nums}
	heap.Init(h)
	for ; k > 0; k-- {
		v := h.pop()
		ans += int64(v)
		h.push((v + 2) / 3)
	}
	return
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) push(v int) { heap.Push(h, v) }
func (h *hp) pop() int   { return heap.Pop(h).(int) }
```

#### TypeScript

```ts
function maxKelements(nums: number[], k: number): number {
    const pq = new MaxPriorityQueue();
    nums.forEach(num => pq.enqueue(num));
    let ans = 0;
    while (k > 0) {
        const v = pq.dequeue()!.element;
        ans += v;
        pq.enqueue(Math.floor((v + 2) / 3));
        k--;
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::BinaryHeap;

impl Solution {
    pub fn max_kelements(nums: Vec<i32>, k: i32) -> i64 {
        let mut pq = BinaryHeap::from(nums);
        let mut ans = 0;
        let mut k = k;
        while k > 0 {
            if let Some(v) = pq.pop() {
                ans += v as i64;
                pq.push((v + 2) / 3);
                k -= 1;
            }
        }
        ans
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
    def maxKelements(self, nums: List[int], k: int) -> int:
        for i, v in enumerate(nums):
            nums[i] = -v
        heapify(nums)
        ans = 0
        for _ in range(k):
            ans -= heapreplace(nums, -ceil(-nums[0] / 3))
        return ans
```

#### C++

```cpp
class Solution {
public:
    long long maxKelements(vector<int>& nums, int k) {
        make_heap(nums.begin(), nums.end());
        long long ans = 0;
        while (k--) {
            int v = nums[0];
            ans += v;
            pop_heap(nums.begin(), nums.end());
            nums.back() = (v + 2) / 3;
            push_heap(nums.begin(), nums.end());
        }
        return ans;
    }
};
```

#### Go

```go
func maxKelements(nums []int, k int) (ans int64) {
	h := hp{nums}
	heap.Init(&h)
	for ; k > 0; k-- {
		ans += int64(h.IntSlice[0])
		h.IntSlice[0] = (h.IntSlice[0] + 2) / 3
		heap.Fix(&h, 0)
	}
	return
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (hp) Push(any)             {}
func (hp) Pop() (_ any)         { return }
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
