---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3763.Maximum%20Total%20Sum%20with%20Threshold%20Constraints/README_EN.md
---

<!-- problem:start -->

# [3763. Maximum Total Sum with Threshold Constraints 🔒](https://leetcode.com/problems/maximum-total-sum-with-threshold-constraints)

[中文文档](/solution/3700-3799/3763.Maximum%20Total%20Sum%20with%20Threshold%20Constraints/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>nums</code> and <code>threshold</code>, both of length <code>n</code>.</p>

<p>Starting at <code>step = 1</code>, you perform the following repeatedly:</p>

<ul>
	<li>Choose an <strong>unused</strong> index <code>i</code> such that <code>threshold[i] &lt;= step</code>.

    <ul>
    	<li>If no such index exists, the process ends.</li>
    </ul>
    </li>
    <li>Add <code>nums[i]</code> to your running total.</li>
    <li>Mark index <code>i</code> as used and increment <code>step</code> by 1.</li>

</ul>

<p>Return the <strong>maximum</strong> <strong>total sum</strong> you can obtain by choosing indices optimally.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,10,4,2,1,6], threshold = [5,1,5,5,2,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">17</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>At <code>step = 1</code>, choose <code>i = 1</code> since <code>threshold[1] &lt;= step</code>. The total sum becomes 10. Mark index 1.</li>
	<li>At <code>step = 2</code>, choose <code>i = 4</code> since <code>threshold[4] &lt;= step</code>. The total sum becomes 11. Mark index 4.</li>
	<li>At <code>step = 3</code>, choose <code>i = 5</code> since <code>threshold[5] &lt;= step</code>. The total sum becomes 17. Mark index 5.</li>
	<li>At <code>step = 4</code>, we cannot choose indices 0, 2, or 3 because their thresholds are <code>&gt; 4</code>, so we end the process.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,1,5,2,3], threshold = [3,3,2,3,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>At <code>step = 1</code> there is no index <code>i</code> with <code>threshold[i] &lt;= 1</code>, so the process ends immediately. Thus, the total sum is 0.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,6,10,13], threshold = [2,1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">31</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>At <code>step = 1</code>, choose <code>i = 3</code> since <code>threshold[3] &lt;= step</code>. The total sum becomes 13. Mark index 3.</li>
	<li>At <code>step = 2</code>, choose <code>i = 2</code> since <code>threshold[2] &lt;= step</code>. The total sum becomes 23. Mark index 2.</li>
	<li>At <code>step = 3</code>, choose <code>i = 1</code> since <code>threshold[1] &lt;= step</code>. The total sum becomes 29. Mark index 1.</li>
	<li>At <code>step = 4</code>, choose <code>i = 0</code> since <code>threshold[0] &lt;= step</code>. The total sum becomes 31. Mark index 0.</li>
	<li>After <code>step = 4</code> all indices have been chosen, so the process ends.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length == threshold.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= threshold[i] &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Sorting

We observe that at each step, we want to select the largest number among those that satisfy the condition to add to the total sum. Therefore, we can use a greedy approach to solve this problem.

We first sort an index array $\textit{idx}$ of length $n$ in ascending order by their corresponding thresholds. Then, we use a sorted set or priority queue (max heap) to maintain the numbers that currently satisfy the condition. At each step, we add all numbers whose thresholds are less than or equal to the current step number into the sorted set or priority queue, and then select the largest number among them to add to the total sum. If the sorted set or priority queue is empty at this point, it means there are no numbers that satisfy the condition, and we end the process.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

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
