---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0347.Top%20K%20Frequent%20Elements/README_EN.md
tags:
    - Array
    - Hash Table
    - Divide and Conquer
    - Bucket Sort
    - Counting
    - Quickselect
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements)

[中文文档](/solution/0300-0399/0347.Top%20K%20Frequent%20Elements/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <em>the</em> <code>k</code> <em>most frequent elements</em>. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1,2,2,3], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2]</span></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[1]</span></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,1,2,1,2,3,1,3,2], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>k</code> is in the range <code>[1, the number of unique elements in the array]</code>.</li>
	<li>It is <strong>guaranteed</strong> that the answer is <strong>unique</strong>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Your algorithm&#39;s time complexity must be better than <code>O(n log n)</code>, where n is the array&#39;s size.</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Priority Queue (Min Heap)

We can use a hash table $\textit{cnt}$ to count the occurrence of each element, and then use a min heap (priority queue) to store the top $k$ frequent elements.

First, we traverse the array once to count the occurrence of each element. Then, we iterate through the hash table, storing each element and its count into the min heap. If the size of the min heap exceeds $k$, we pop the top element of the heap to ensure the heap size is always $k$.

Finally, we pop the elements from the min heap one by one and place them into the result array.

The time complexity is $O(n \log k)$, and the space complexity is $O(k)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        cnt = Counter(nums)
        return [x for x, _ in cnt.most_common(k)]
```

#### Java

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq
            = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        for (var e : cnt.entrySet()) {
            pq.offer(e);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.stream().mapToInt(Map.Entry::getKey).toArray();
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        using pii = pair<int, int>;
        for (int x : nums) {
            ++cnt[x];
        }
        priority_queue<pii, vector<pii>, greater<pii>> pq;
        for (auto& [x, c] : cnt) {
            pq.push({c, x});
            if (pq.size() > k) {
                pq.pop();
            }
        }
        vector<int> ans;
        while (!pq.empty()) {
            ans.push_back(pq.top().second);
            pq.pop();
        }
        return ans;
    }
};
```

#### Go

```go
func topKFrequent(nums []int, k int) []int {
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	pq := hp{}
	for x, c := range cnt {
		heap.Push(&pq, pair{x, c})
		if pq.Len() > k {
			heap.Pop(&pq)
		}
	}
	ans := make([]int, k)
	for i := 0; i < k; i++ {
		ans[i] = heap.Pop(&pq).(pair).v
	}
	return ans
}

type pair struct{ v, cnt int }
type hp []pair

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].cnt < h[j].cnt }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(pair)) }
func (h *hp) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

#### TypeScript

```ts
function topKFrequent(nums: number[], k: number): number[] {
    const cnt = new Map<number, number>();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }
    const pq = new MinPriorityQueue();
    for (const [x, c] of cnt) {
        pq.enqueue(x, c);
        if (pq.size() > k) {
            pq.dequeue();
        }
    }
    return pq.toArray().map(x => x.element);
}
```

#### Rust

```rust
use std::cmp::Reverse;
use std::collections::{BinaryHeap, HashMap};

impl Solution {
    pub fn top_k_frequent(nums: Vec<i32>, k: i32) -> Vec<i32> {
        let mut cnt = HashMap::new();
        for x in nums {
            *cnt.entry(x).or_insert(0) += 1;
        }
        let mut pq = BinaryHeap::with_capacity(k as usize);
        for (&x, &c) in cnt.iter() {
            pq.push(Reverse((c, x)));
            if pq.len() > k as usize {
                pq.pop();
            }
        }
        pq.into_iter().map(|Reverse((_, x))| x).collect()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
