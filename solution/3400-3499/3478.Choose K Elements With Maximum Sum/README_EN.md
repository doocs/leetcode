---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3478.Choose%20K%20Elements%20With%20Maximum%20Sum/README_EN.md
rating: 1753
source: Weekly Contest 440 Q2
tags:
    - Array
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3478. Choose K Elements With Maximum Sum](https://leetcode.com/problems/choose-k-elements-with-maximum-sum)

[中文文档](/solution/3400-3499/3478.Choose%20K%20Elements%20With%20Maximum%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays, <code>nums1</code> and <code>nums2</code>, both of length <code>n</code>, along with a positive integer <code>k</code>.</p>

<p>For each index <code>i</code> from <code>0</code> to <code>n - 1</code>, perform the following:</p>

<ul>
	<li>Find <strong>all</strong> indices <code>j</code> where <code>nums1[j]</code> is less than <code>nums1[i]</code>.</li>
	<li>Choose <strong>at most</strong> <code>k</code> values of <code>nums2[j]</code> at these indices to <strong>maximize</strong> the total sum.</li>
</ul>

<p>Return an array <code>answer</code> of size <code>n</code>, where <code>answer[i]</code> represents the result for the corresponding index <code>i</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [4,2,1,5,3], nums2 = [10,20,30,40,50], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[80,30,0,80,50]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>i = 0</code>: Select the 2 largest values from <code>nums2</code> at indices <code>[1, 2, 4]</code> where <code>nums1[j] &lt; nums1[0]</code>, resulting in <code>50 + 30 = 80</code>.</li>
	<li>For <code>i = 1</code>: Select the 2 largest values from <code>nums2</code> at index <code>[2]</code> where <code>nums1[j] &lt; nums1[1]</code>, resulting in 30.</li>
	<li>For <code>i = 2</code>: No indices satisfy <code>nums1[j] &lt; nums1[2]</code>, resulting in 0.</li>
	<li>For <code>i = 3</code>: Select the 2 largest values from <code>nums2</code> at indices <code>[0, 1, 2, 4]</code> where <code>nums1[j] &lt; nums1[3]</code>, resulting in <code>50 + 30 = 80</code>.</li>
	<li>For <code>i = 4</code>: Select the 2 largest values from <code>nums2</code> at indices <code>[1, 2]</code> where <code>nums1[j] &lt; nums1[4]</code>, resulting in <code>30 + 20 = 50</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [2,2,2,2], nums2 = [3,1,2,3], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,0,0,0]</span></p>

<p><strong>Explanation:</strong></p>

<p>Since all elements in <code>nums1</code> are equal, no indices satisfy the condition <code>nums1[j] &lt; nums1[i]</code> for any <code>i</code>, resulting in 0 for all positions.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Priority Queue (Min-Heap)

We can convert the array $\textit{nums1}$ into an array $\textit{arr}$, where each element is a tuple $(x, i)$, representing the value $x$ at index $i$ in $\textit{nums1}$. Then, we sort the array $\textit{arr}$ in ascending order by $x$.

We use a min-heap $\textit{pq}$ to maintain the elements from the array $\textit{nums2}$. Initially, $\textit{pq}$ is empty. We use a variable $\textit{s}$ to record the sum of the elements in $\textit{pq}$. Additionally, we use a pointer $j$ to maintain the current position in the array $\textit{arr}$ that needs to be added to $\textit{pq}$.

We traverse the array $\textit{arr}$. For the $h$-th element $(x, i)$, we add all elements $\textit{nums2}[\textit{arr}[j][1]]$ to $\textit{pq}$ that satisfy $j < h$ and $\textit{arr}[j][0] < x$, and add these elements to $\textit{s}$. If the size of $\textit{pq}$ exceeds $k$, we pop the smallest element from $\textit{pq}$ and subtract it from $\textit{s}$. Then, we update the value of $\textit{ans}[i]$ to $\textit{s}$.

After traversing, we return the answer array $\textit{ans}$.

The time complexity is $O(n \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMaxSum(self, nums1: List[int], nums2: List[int], k: int) -> List[int]:
        arr = [(x, i) for i, x in enumerate(nums1)]
        arr.sort()
        pq = []
        s = j = 0
        n = len(arr)
        ans = [0] * n
        for h, (x, i) in enumerate(arr):
            while j < h and arr[j][0] < x:
                y = nums2[arr[j][1]]
                heappush(pq, y)
                s += y
                if len(pq) > k:
                    s -= heappop(pq)
                j += 1
            ans[i] = s
        return ans
```

#### Java

```java
class Solution {
    public long[] findMaxSum(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int[][] arr = new int[n][0];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {nums1[i], i};
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long s = 0;
        long[] ans = new long[n];
        int j = 0;
        for (int h = 0; h < n; ++h) {
            int x = arr[h][0], i = arr[h][1];
            while (j < h && arr[j][0] < x) {
                int y = nums2[arr[j][1]];
                pq.offer(y);
                s += y;
                if (pq.size() > k) {
                    s -= pq.poll();
                }
                ++j;
            }
            ans[i] = s;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<long long> findMaxSum(vector<int>& nums1, vector<int>& nums2, int k) {
        int n = nums1.size();
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = {nums1[i], i};
        }
        ranges::sort(arr);
        priority_queue<int, vector<int>, greater<int>> pq;
        long long s = 0;
        int j = 0;
        vector<long long> ans(n);
        for (int h = 0; h < n; ++h) {
            auto [x, i] = arr[h];
            while (j < h && arr[j].first < x) {
                int y = nums2[arr[j].second];
                pq.push(y);
                s += y;
                if (pq.size() > k) {
                    s -= pq.top();
                    pq.pop();
                }
                ++j;
            }
            ans[i] = s;
        }
        return ans;
    }
};
```

#### Go

```go
func findMaxSum(nums1 []int, nums2 []int, k int) []int64 {
	n := len(nums1)
	arr := make([][2]int, n)
	for i, x := range nums1 {
		arr[i] = [2]int{x, i}
	}
	ans := make([]int64, n)
	sort.Slice(arr, func(i, j int) bool { return arr[i][0] < arr[j][0] })
	pq := hp{}
	var s int64
	j := 0
	for h, e := range arr {
		x, i := e[0], e[1]
		for j < h && arr[j][0] < x {
			y := nums2[arr[j][1]]
			heap.Push(&pq, y)
			s += int64(y)
			if pq.Len() > k {
				s -= int64(heap.Pop(&pq).(int))
			}
			j++
		}
		ans[i] = s
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

#### TypeScript

```ts
function findMaxSum(nums1: number[], nums2: number[], k: number): number[] {
    const n = nums1.length;
    const arr = nums1.map((x, i) => [x, i]).sort((a, b) => a[0] - b[0]);
    const pq = new MinPriorityQueue<number>();
    let [s, j] = [0, 0];
    const ans: number[] = Array(k).fill(0);
    for (let h = 0; h < n; ++h) {
        const [x, i] = arr[h];
        while (j < h && arr[j][0] < x) {
            const y = nums2[arr[j++][1]];
            pq.enqueue(y);
            s += y;
            if (pq.size() > k) {
                s -= pq.dequeue();
            }
        }
        ans[i] = s;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
