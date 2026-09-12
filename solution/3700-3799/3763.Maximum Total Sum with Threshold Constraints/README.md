---
comments: true
difficulty: 中等
tags:
    - 贪心
    - 数组
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [3763. 带阈值约束的最大总和 🔒](https://leetcode.cn/problems/maximum-total-sum-with-threshold-constraints)

[English Version](/solution/3700-3799/3763.Maximum%20Total%20Sum%20with%20Threshold%20Constraints/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个整数数组&nbsp;<code>nums</code> 和&nbsp;<code>threshold</code>，长度都是&nbsp;<code>n</code>。</p>

<p>从&nbsp;<code>step = 1</code>&nbsp;开始，重复执行下面操作：</p>

<ul>
	<li>找到一个 <strong>未使用</strong>&nbsp;的下标&nbsp;<code>i</code> 使得&nbsp;<code>threshold[i] &lt;= step</code>。

    <ul>
    	<li>如果没有这样的下标存在，流程结束。</li>
    </ul>
    </li>
    <li>将 <code>nums[i]</code> 加到你的累计总和中。</li>
    <li>将索引 <code>i</code> 标记为已使用，并将&nbsp;<code>step</code> 增加 1。</li>

</ul>

<p>返回通过选择索引来获得的 <strong>最大总和</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,10,4,2,1,6], threshold = [5,1,5,5,2,2]</span></p>

<p><span class="example-io"><b>输出：</b>17</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>在&nbsp;<code>step = 1</code>，由于&nbsp;<code>threshold[1] &lt;= step</code>，选择&nbsp;<code>i = 1</code>。总和变为 10。标记下标 1。</li>
	<li>在&nbsp;<code>step = 2</code>，由于&nbsp;<code>threshold[4] &lt;= step</code>，选择&nbsp;<code>i = 4</code>。总和变为 11。标记下标 4。</li>
	<li>在&nbsp;<code>step = 3</code>，由于&nbsp;<code>threshold[5] &lt;= step</code>，选择&nbsp;<code>i = 5</code>。总和变为 17。标记下标 5。</li>
	<li>在&nbsp;<code>step = 4</code>，我们不能选择下标 0，2 或 3 因为它们的阈值&nbsp;<code>&gt; 4</code>，所以我们结束流程。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [4,1,5,2,3], threshold = [3,3,2,3,3]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<p>在&nbsp;<code>step = 1</code>&nbsp;时没有下标&nbsp;<code>i</code> 使得&nbsp;<code>threshold[i] &lt;= 1</code>，所以流程立刻结束。因此，总和为 0。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [2,6,10,13], threshold = [2,1,1,1]</span></p>

<p><span class="example-io"><b>输出：</b>31</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>在&nbsp;<code>step = 1</code>，由于&nbsp;<code>threshold[3] &lt;= step</code>，选择&nbsp;<code>i = 3</code>。总和变为 13。标记下标 3。</li>
	<li>在&nbsp;<code>step = 2</code>，由于&nbsp;<code>threshold[2] &lt;= step</code>，选择&nbsp;<code>i = 2</code>。总和变为 23。标记下标 2。</li>
	<li>在&nbsp;<code>step = 3</code>，由于&nbsp;<code>threshold[1] &lt;= step</code>，选择&nbsp;<code>i = 1</code>。总和变为 29。标记下标 1。</li>
	<li>在&nbsp;<code>step = 4</code>，由于&nbsp;<code>threshold[0] &lt;= step</code>，选择&nbsp;<code>i = 0</code>。总和变为 31。标记下标 0。</li>
	<li>在&nbsp;<code>step = 4</code>&nbsp;后所有下标都已经被选择，所以流程结束。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length == threshold.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= threshold[i] &lt;= n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 排序

<!-- thinking:start -->

> **思考**
>
> 每一步只能选阈值不超过当前步数的未使用下标，且希望选到的 $nums[i]$ 尽量大。把下标按阈值排序，步数增加时把新解锁的数放入有序集合，每次取当前最大者；集合空则停止。

<!-- thinking:end -->

我们注意到，在每一个步骤中，我们都希望选择一个满足条件的数中最大的数加入总和中。因此，我们可以使用贪心的方法来解决这个问题。

我们首先将长度为 $n$ 的下标数组 $\textit{idx}$ 按照对应的阈值从小到大进行排序。然后，我们使用有序集合或优先队列（最大堆）来维护当前满足条件的数。在每一个步骤中，我们将所有阈值小于等于当前步骤数的数加入有序集合或优先队列中，然后选择其中最大的数加入总和中。如果此时有序集合或优先队列为空，说明没有满足条件的数，我们就结束过程。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSum(self, nums: List[int], threshold: List[int]) -> int:
        n = len(nums)
        idx = sorted(range(n), key=lambda i: threshold[i])
        sl = SortedList()
        step = 1
        ans = i = 0
        while True:
            while i < n and threshold[idx[i]] <= step:
                sl.add(nums[idx[i]])
                i += 1
            if not sl:
                break
            ans += sl.pop()
            step += 1
        return ans
```

#### Java

```java
class Solution {
    public long maxSum(int[] nums, int[] threshold) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        Arrays.setAll(idx, i -> i);
        Arrays.sort(idx, Comparator.comparingInt(i -> threshold[i]));
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        long ans = 0;
        for (int i = 0, step = 1;; ++step) {
            while (i < n && threshold[idx[i]] <= step) {
                tm.merge(nums[idx[i]], 1, Integer::sum);
                ++i;
            }
            if (tm.isEmpty()) {
                break;
            }
            int x = tm.lastKey();
            ans += x;
            if (tm.merge(x, -1, Integer::sum) == 0) {
                tm.remove(x);
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
    long long maxSum(vector<int>& nums, vector<int>& threshold) {
        int n = nums.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int a, int b) { return threshold[a] < threshold[b]; });

        multiset<int> ms;
        long long ans = 0;
        int i = 0;

        for (int step = 1;; ++step) {
            while (i < n && threshold[idx[i]] <= step) {
                ms.insert(nums[idx[i]]);
                ++i;
            }
            if (ms.empty()) {
                break;
            }

            auto it = prev(ms.end());
            ans += *it;
            ms.erase(it);
        }
        return ans;
    }
};
```

#### Go

```go
func maxSum(nums []int, threshold []int) int64 {
	n := len(nums)
	idx := make([]int, n)
	for i := 0; i < n; i++ {
		idx[i] = i
	}
	sort.Slice(idx, func(a, b int) bool {
		return threshold[idx[a]] < threshold[idx[b]]
	})

	tree := redblacktree.NewWithIntComparator()
	var ans int64
	i := 0

	for step := 1; ; step++ {
		for i < n && threshold[idx[i]] <= step {
			val := nums[idx[i]]
			if cnt, found := tree.Get(val); found {
				tree.Put(val, cnt.(int)+1)
			} else {
				tree.Put(val, 1)
			}
			i++
		}
		if tree.Empty() {
			break
		}

		node := tree.Right()
		key := node.Key.(int)
		cnt := node.Value.(int)

		ans += int64(key)
		if cnt == 1 {
			tree.Remove(key)
		} else {
			tree.Put(key, cnt-1)
		}
	}

	return ans
}
```

#### TypeScript

```ts
function maxSum(nums: number[], threshold: number[]): number {
    const n = nums.length;
    const idx = Array.from({ length: n }, (_, i) => i).sort((a, b) => threshold[a] - threshold[b]);
    const pq = new MaxPriorityQueue<number>();
    let ans = 0;
    for (let i = 0, step = 1; ; ++step) {
        while (i < n && threshold[idx[i]] <= step) {
            pq.enqueue(nums[idx[i]]);
            ++i;
        }
        if (pq.isEmpty()) {
            break;
        }
        ans += pq.dequeue();
    }
    return ans;
}
```

#### Rust

```rust
use std::cmp::Reverse;
use std::collections::BinaryHeap;

impl Solution {
    pub fn max_sum(nums: Vec<i32>, threshold: Vec<i32>) -> i64 {
        let n = nums.len();
        let mut idx: Vec<usize> = (0..n).collect();
        idx.sort_by_key(|&i| threshold[i]);

        let mut pq = BinaryHeap::new();
        let mut ans: i64 = 0;
        let mut i = 0;
        let mut step = 1;

        loop {
            while i < n && threshold[idx[i]] <= step {
                pq.push(nums[idx[i]]);
                i += 1;
            }
            match pq.pop() {
                Some(x) => {
                    ans += x as i64;
                    step += 1;
                }
                None => break,
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
