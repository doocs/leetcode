---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3672.Sum%20of%20Weighted%20Modes%20in%20Subarrays/README_EN.md
tags:
    - Array
    - Hash Table
    - Counting
    - Ordered Set
    - Sliding Window
---

<!-- problem:start -->

# [3672. Sum of Weighted Modes in Subarrays 🔒](https://leetcode.com/problems/sum-of-weighted-modes-in-subarrays)

[中文文档](/solution/3600-3699/3672.Sum%20of%20Weighted%20Modes%20in%20Subarrays/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>For every <strong>subarray</strong> of length <code>k</code>:</p>

<ul>
	<li>The <strong>mode</strong> is defined as the element with the <strong>highest frequency</strong>. If there are multiple choices for a mode, the <strong>smallest</strong> such element is taken.</li>
	<li>The <strong>weight</strong> is defined as <code>mode * frequency(mode)</code>.</li>
</ul>

<p>Return the <strong>sum</strong> of the weights of all <strong>subarrays</strong> of length <code>k</code>.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>A <strong>subarray</strong> is a contiguous <strong>non-empty</strong> sequence of elements within an array.</li>
	<li>The <strong>frequency</strong> of an element <code>x</code> is the number of times it occurs in the array.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,2,3], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<p>Subarrays of length <code>k = 3</code> are:</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Subarray</th>
			<th style="border: 1px solid black;">Frequencies</th>
			<th style="border: 1px solid black;">Mode</th>
			<th style="border: 1px solid black;">Mode<br />
			​​​​​​​Frequency</th>
			<th style="border: 1px solid black;">Weight</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">[1, 2, 2]</td>
			<td style="border: 1px solid black;">1: 1, 2: 2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2 &times; 2 = 4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">[2, 2, 3]</td>
			<td style="border: 1px solid black;">2: 2, 3: 1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2 &times; 2 = 4</td>
		</tr>
	</tbody>
</table>

<p>Thus, the sum of weights is <code>4 + 4 = 8</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,1,2], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Subarrays of length <code>k = 2</code> are:</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Subarray</th>
			<th style="border: 1px solid black;">Frequencies</th>
			<th style="border: 1px solid black;">Mode</th>
			<th style="border: 1px solid black;">Mode<br />
			Frequency</th>
			<th style="border: 1px solid black;">Weight</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">[1, 2]</td>
			<td style="border: 1px solid black;">1: 1, 2: 1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1 &times; 1 = 1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">[2, 1]</td>
			<td style="border: 1px solid black;">2: 1, 1: 1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1 &times; 1 = 1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">[1, 2]</td>
			<td style="border: 1px solid black;">1: 1, 2: 1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1 &times; 1 = 1</td>
		</tr>
	</tbody>
</table>

<p>Thus, the sum of weights is <code>1 + 1 + 1 = 3</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,3,4,3], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">14</span></p>

<p><strong>Explanation:</strong></p>

<p>Subarrays of length <code>k = 3</code> are:</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Subarray</th>
			<th style="border: 1px solid black;">Frequencies</th>
			<th style="border: 1px solid black;">Mode</th>
			<th style="border: 1px solid black;">Mode<br />
			Frequency</th>
			<th style="border: 1px solid black;">Weight</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">[4, 3, 4]</td>
			<td style="border: 1px solid black;">4: 2, 3: 1</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2 &times; 4 = 8</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">[3, 4, 3]</td>
			<td style="border: 1px solid black;">3: 2, 4: 1</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2 &times; 3 = 6</td>
		</tr>
	</tbody>
</table>

<p>Thus, the sum of weights is <code>8 + 6 = 14</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Map + Priority Queue + Sliding Window + Lazy Deletion

We use a hash map $\textit{cnt}$ to record the frequency of each number in the current window. We use a priority queue $\textit{pq}$ to store the frequency and value of each number in the current window, with priority given to higher frequency, and for equal frequency, to smaller numbers.

We design a function $\textit{get_mode()}$ to obtain the mode and its frequency in the current window. Specifically, we repeatedly pop the top element from the priority queue until its frequency matches the frequency recorded in the hash map; at that point, the top element is the mode and its frequency for the current window.

We use a variable $\textit{ans}$ to record the sum of weights for all windows. Initially, we add the first $k$ numbers of the array to the hash map and priority queue, then call $\textit{get_mode()}$ to get the mode and its frequency for the first window, and add its weight to $\textit{ans}$.

Next, starting from the $k$-th number, we add each number to the hash map and priority queue, and decrement the frequency of the leftmost number in the window in the hash map. Then, we call $\textit{get_mode()}$ to get the mode and its frequency for the current window, and add its weight to $\textit{ans}$.

Finally, we return $\textit{ans}$.

The time complexity is $O(n \log k)$, and the space complexity is $O(k)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def modeWeight(self, nums: List[int], k: int) -> int:
        pq = []
        cnt = defaultdict(int)
        for x in nums[:k]:
            cnt[x] += 1
            heappush(pq, (-cnt[x], x))

        def get_mode() -> int:
            while -pq[0][0] != cnt[pq[0][1]]:
                heappop(pq)
            freq, val = -pq[0][0], pq[0][1]
            return freq * val

        ans = 0
        ans += get_mode()

        for i in range(k, len(nums)):
            x, y = nums[i], nums[i - k]
            cnt[x] += 1
            cnt[y] -= 1
            heappush(pq, (-cnt[x], x))
            heappush(pq, (-cnt[y], y))

            ans += get_mode()

        return ans
```

#### Java

```java
class Solution {
    public long modeWeight(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));

        for (int i = 0; i < k; i++) {
            int x = nums[i];
            cnt.merge(x, 1, Integer::sum);
            pq.offer(new int[] {-cnt.get(x), x});
        }

        long ans = 0;

        Supplier<Long> getMode = () -> {
            while (true) {
                int[] top = pq.peek();
                int val = top[1];
                int freq = -top[0];
                if (cnt.getOrDefault(val, 0) == freq) {
                    return 1L * freq * val;
                }
                pq.poll();
            }
        };

        ans += getMode.get();

        for (int i = k; i < nums.length; i++) {
            int x = nums[i], y = nums[i - k];
            cnt.merge(x, 1, Integer::sum);
            pq.offer(new int[] {-cnt.get(x), x});
            cnt.merge(y, -1, Integer::sum);
            pq.offer(new int[] {-cnt.get(y), y});
            ans += getMode.get();
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long modeWeight(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        priority_queue<pair<int, int>> pq; // {freq, -val}

        for (int i = 0; i < k; i++) {
            int x = nums[i];
            cnt[x]++;
            pq.push({cnt[x], -x});
        }

        auto get_mode = [&]() {
            while (true) {
                auto [freq, negVal] = pq.top();
                int val = -negVal;
                if (cnt[val] == freq) {
                    return 1LL * freq * val;
                }
                pq.pop();
            }
        };

        long long ans = 0;
        ans += get_mode();

        for (int i = k; i < nums.size(); i++) {
            int x = nums[i], y = nums[i - k];
            cnt[x]++;
            cnt[y]--;
            pq.push({cnt[x], -x});
            pq.push({cnt[y], -y});
            ans += get_mode();
        }

        return ans;
    }
};
```

#### Go

```go
func modeWeight(nums []int, k int) int64 {
	cnt := make(map[int]int)
	pq := &MaxHeap{}
	heap.Init(pq)

	for i := 0; i < k; i++ {
		x := nums[i]
		cnt[x]++
		heap.Push(pq, pair{cnt[x], x})
	}

	getMode := func() int64 {
		for {
			top := (*pq)[0]
			if cnt[top.val] == top.freq {
				return int64(top.freq) * int64(top.val)
			}
			heap.Pop(pq)
		}
	}

	var ans int64
	ans += getMode()

	for i := k; i < len(nums); i++ {
		x, y := nums[i], nums[i-k]
		cnt[x]++
		cnt[y]--
		heap.Push(pq, pair{cnt[x], x})
		heap.Push(pq, pair{cnt[y], y})
		ans += getMode()
	}

	return ans
}

type pair struct {
	freq int
	val  int
}

type MaxHeap []pair

func (h MaxHeap) Len() int { return len(h) }
func (h MaxHeap) Less(i, j int) bool {
	if h[i].freq != h[j].freq {
		return h[i].freq > h[j].freq
	}
	return h[i].val < h[j].val
}
func (h MaxHeap) Swap(i, j int) { h[i], h[j] = h[j], h[i] }
func (h *MaxHeap) Push(x any) {
	*h = append(*h, x.(pair))
}
func (h *MaxHeap) Pop() any {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[:n-1]
	return x
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
