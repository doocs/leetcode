---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3814.Maximum%20Capacity%20Within%20Budget/README_EN.md
rating: 1796
source: Weekly Contest 485 Q2
tags:
    - Binary Search
    - Prefix Sum
---

<!-- problem:start -->

# [3814. Maximum Capacity Within Budget](https://leetcode.com/problems/maximum-capacity-within-budget)

[中文文档](/solution/3800-3899/3814.Maximum%20Capacity%20Within%20Budget/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>costs</code> and <code>capacity</code>, both of length <code>n</code>, where <code>costs[i]</code> represents the purchase cost of the <code>i<sup>th</sup></code> machine and <code>capacity[i]</code> represents its performance capacity.</p>

<p>You are also given an integer <code>budget</code>.</p>

<p>You may select <strong>at most two distinct</strong> machines such that the <strong>total cost</strong> of the selected machines is <strong>strictly less</strong> than <code>budget</code>.</p>

<p>Return the <strong>maximum</strong> achievable total capacity of the selected machines.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">costs = [4,8,5,3], capacity = [1,5,2,7], budget = 8</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Choose two machines with <code>costs[0] = 4</code> and <code>costs[3] = 3</code>.</li>
	<li>The total cost is <code>4 + 3 = 7</code>, which is strictly less than <code>budget = 8</code>.</li>
	<li>The maximum total capacity is <code>capacity[0] + capacity[3] = 1 + 7 = 8</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">costs = [3,5,7,4], capacity = [2,4,3,6], budget = 7</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Choose one machine with <code>costs[3] = 4</code>.</li>
	<li>The total cost is 4, which is strictly less than <code>budget = 7</code>.</li>
	<li>The maximum total capacity is <code>capacity[3] = 6</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">costs = [2,2,2], capacity = [3,5,4], budget = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Choose two machines with <code>costs[1] = 2</code> and <code>costs[2] = 2</code>.</li>
	<li>The total cost is <code>2 + 2 = 4</code>, which is strictly less than <code>budget = 5</code>.</li>
	<li>The maximum total capacity is <code>capacity[1] + capacity[2] = 5 + 4 = 9</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == costs.length == capacity.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= costs[i], capacity[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= budget &lt;= 2 * 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Ordered Set

We first filter out all machines with costs less than the budget and sort them by cost in ascending order, recording them in the array $\textit{arr}$, where $\textit{arr}[i] = (\textit{costs}[i], \textit{capacity}[i])$. If $\textit{arr}$ is empty, we cannot buy any machine, so we return $0$.

Otherwise, we can obtain the machine with the maximum capacity in $\textit{arr}$ and initialize the answer with this capacity.

Next, we use a two-pointer approach to iterate through pairs of machines in $\textit{arr}$, using an ordered set $\textit{remain}$ to maintain the capacities of all currently available machines. Initially, $\textit{remain}$ contains the capacities of all machines in $\textit{arr}$.

We use pointers $i$ and $j$ pointing to the beginning and end of $\textit{arr}$, respectively. For each $i$, we remove $\textit{arr}[i]$ from $\textit{remain}$, and then move pointer $j$ until $\textit{arr}[i].\textit{cost} + \textit{arr}[j].\textit{cost} < \textit{budget}$. During this process, we remove the machines that do not satisfy the condition from $\textit{remain}$. At this point, any machine in $\textit{remain}$ can be bought together with $\textit{arr}[i]$. We take the machine with the maximum capacity from $\textit{remain}$, add its capacity to $\textit{arr}[i]$'s capacity, and update the answer. Finally, we return the answer.

The time complexity is $O(n \log n)$, and the space complexity is $O(n)$, where $n$ is the number of machines.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxCapacity(self, costs: List[int], capacity: List[int], budget: int) -> int:
        arr = []
        for a, b in zip(costs, capacity):
            if a < budget:
                arr.append((a, b))
        if not arr:
            return 0
        arr.sort()
        remain = SortedList()
        for i, (_, b) in enumerate(arr):
            remain.add((b, i))
        i, j = 0, len(arr) - 1
        ans = remain[-1][0]
        while i < j:
            remain.discard((arr[i][1], i))
            while i < j and arr[i][0] + arr[j][0] >= budget:
                remain.discard((arr[j][1], j))
                j -= 1
            if remain:
                ans = max(ans, arr[i][1] + remain[-1][0])
            i += 1
        return ans
```

#### Java

```java
class Solution {
    public int maxCapacity(int[] costs, int[] capacity, int budget) {
        List<int[]> arr = new ArrayList<>();
        for (int k = 0; k < costs.length; k++) {
            int a = costs[k], b = capacity[k];
            if (a < budget) {
                arr.add(new int[] {a, b});
            }
        }
        if (arr.isEmpty()) {
            return 0;
        }
        arr.sort(Comparator.comparingInt(o -> o[0]));
        TreeSet<int[]> remain = new TreeSet<>((x, y) -> {
            if (x[0] != y[0]) {
                return x[0] - y[0];
            }
            return x[1] - y[1];
        });
        for (int i = 0; i < arr.size(); i++) {
            remain.add(new int[] {arr.get(i)[1], i});
        }
        int i = 0, j = arr.size() - 1;
        int ans = remain.last()[0];
        while (i < j) {
            remain.remove(new int[] {arr.get(i)[1], i});
            while (i < j && arr.get(i)[0] + arr.get(j)[0] >= budget) {
                remain.remove(new int[] {arr.get(j)[1], j});
                j--;
            }
            if (!remain.isEmpty()) {
                ans = Math.max(ans, arr.get(i)[1] + remain.last()[0]);
            }
            i++;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxCapacity(vector<int>& costs, vector<int>& capacity, int budget) {
        vector<pair<int, int>> arr;
        for (int k = 0; k < costs.size(); k++) {
            int a = costs[k], b = capacity[k];
            if (a < budget) {
                arr.emplace_back(a, b);
            }
        }
        if (arr.empty()) {
            return 0;
        }
        sort(arr.begin(), arr.end());
        multiset<pair<int, int>> remain;
        for (int i = 0; i < arr.size(); i++) {
            remain.insert({arr[i].second, i});
        }
        int i = 0, j = arr.size() - 1;
        int ans = prev(remain.end())->first;
        while (i < j) {
            remain.erase(remain.find({arr[i].second, i}));
            while (i < j && arr[i].first + arr[j].first >= budget) {
                remain.erase(remain.find({arr[j].second, j}));
                j--;
            }
            if (!remain.empty()) {
                ans = max(ans, arr[i].second + prev(remain.end())->first);
            }
            i++;
        }
        return ans;
    }
};
```

#### Go

```go
type Node struct {
	b int
	i int
}

type MaxHeap []Node

func (h MaxHeap) Len() int { return len(h) }
func (h MaxHeap) Less(i, j int) bool {
	if h[i].b != h[j].b {
		return h[i].b > h[j].b
	}
	return h[i].i > h[j].i
}
func (h MaxHeap) Swap(i, j int) { h[i], h[j] = h[j], h[i] }
func (h *MaxHeap) Push(x interface{}) {
	*h = append(*h, x.(Node))
}
func (h *MaxHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[:n-1]
	return x
}

func maxCapacity(costs []int, capacity []int, budget int) int {
	arr := make([][2]int, 0)
	for k := 0; k < len(costs); k++ {
		a, b := costs[k], capacity[k]
		if a < budget {
			arr = append(arr, [2]int{a, b})
		}
	}
	if len(arr) == 0 {
		return 0
	}
	sort.Slice(arr, func(i, j int) bool {
		if arr[i][0] != arr[j][0] {
			return arr[i][0] < arr[j][0]
		}
		return arr[i][1] < arr[j][1]
	})
	alive := make([]bool, len(arr))
	h := &MaxHeap{}
	for i := 0; i < len(arr); i++ {
		alive[i] = true
		heap.Push(h, Node{arr[i][1], i})
	}
	i, j := 0, len(arr)-1
	for h.Len() > 0 && !alive[(*h)[0].i] {
		heap.Pop(h)
	}
	ans := (*h)[0].b
	for i < j {
		alive[i] = false
		for i < j && arr[i][0]+arr[j][0] >= budget {
			alive[j] = false
			j--
		}
		for h.Len() > 0 && !alive[(*h)[0].i] {
			heap.Pop(h)
		}
		if h.Len() > 0 {
			if arr[i][1]+(*h)[0].b > ans {
				ans = arr[i][1] + (*h)[0].b
			}
		}
		i++
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
