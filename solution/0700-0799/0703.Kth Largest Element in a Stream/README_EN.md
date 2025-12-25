---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0703.Kth%20Largest%20Element%20in%20a%20Stream/README_EN.md
tags:
    - Tree
    - Design
    - Binary Search Tree
    - Binary Tree
    - Data Stream
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [703. Kth Largest Element in a Stream](https://leetcode.com/problems/kth-largest-element-in-a-stream)

[中文文档](/solution/0700-0799/0703.Kth%20Largest%20Element%20in%20a%20Stream/README.md)

## Description

<!-- description:start -->

<p>You are part of a university admissions office and need to keep track of the <code>kth</code> highest test score from applicants in real-time. This helps to determine cut-off marks for interviews and admissions dynamically as new applicants submit their scores.</p>

<p>You are tasked to implement a class which, for a given integer&nbsp;<code>k</code>, maintains a stream of test scores and continuously returns the&nbsp;<code>k</code>th highest test score&nbsp;<strong>after</strong>&nbsp;a new score has been submitted. More specifically, we are looking for the <code>k</code>th highest score in the sorted list of all scores.</p>

<p>Implement the&nbsp;<code>KthLargest</code> class:</p>

<ul>
	<li><code>KthLargest(int k, int[] nums)</code> Initializes the object with the integer <code>k</code> and the stream of test scores&nbsp;<code>nums</code>.</li>
	<li><code>int add(int val)</code> Adds a new test score&nbsp;<code>val</code> to the stream and returns the element representing the <code>k<sup>th</sup></code> largest element in the pool of test scores so far.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong><br />
<span class="example-io">[&quot;KthLargest&quot;, &quot;add&quot;, &quot;add&quot;, &quot;add&quot;, &quot;add&quot;, &quot;add&quot;]<br />
[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[null, 4, 5, 5, 8, 8]</span></p>

<p><strong>Explanation:</strong></p>

<p>KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);<br />
kthLargest.add(3); // return 4<br />
kthLargest.add(5); // return 5<br />
kthLargest.add(10); // return 5<br />
kthLargest.add(9); // return 8<br />
kthLargest.add(4); // return 8</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong><br />
<span class="example-io">[&quot;KthLargest&quot;, &quot;add&quot;, &quot;add&quot;, &quot;add&quot;, &quot;add&quot;]<br />
[[4, [7, 7, 7, 7, 8, 3]], [2], [10], [9], [9]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[null, 7, 7, 7, 8]</span></p>

<p><strong>Explanation:</strong></p>
KthLargest kthLargest = new KthLargest(4, [7, 7, 7, 7, 8, 3]);<br />
kthLargest.add(2); // return 7<br />
kthLargest.add(10); // return 7<br />
kthLargest.add(9); // return 7<br />
kthLargest.add(9); // return 8</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length + 1</code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= val &lt;= 10<sup>4</sup></code></li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>add</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Priority Queue (Min Heap)

We maintain a priority queue (min heap) $\textit{minQ}$.

Initially, we add the elements of the array $\textit{nums}$ to $\textit{minQ}$ one by one, ensuring that the size of $\textit{minQ}$ does not exceed $k$. The time complexity is $O(n \times \log k)$.

Each time a new element is added, if the size of $\textit{minQ}$ exceeds $k$, we pop the top element of the heap to ensure that the size of $\textit{minQ}$ is $k$. The time complexity is $O(\log k)$.

In this way, the elements in $\textit{minQ}$ are the largest $k$ elements in the array $\textit{nums}$, and the top element of the heap is the $k^{th}$ largest element.

The space complexity is $O(k)$.

<!-- tabs:start -->

#### Python3

```python
class KthLargest:

    def __init__(self, k: int, nums: List[int]):
        self.k = k
        self.min_q = []
        for x in nums:
            self.add(x)

    def add(self, val: int) -> int:
        heappush(self.min_q, val)
        if len(self.min_q) > self.k:
            heappop(self.min_q)
        return self.min_q[0]


# Your KthLargest object will be instantiated and called as such:
# obj = KthLargest(k, nums)
# param_1 = obj.add(val)
```

#### Java

```java
class KthLargest {
    private PriorityQueue<Integer> minQ;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        minQ = new PriorityQueue<>(k);
        for (int x : nums) {
            add(x);
        }
    }

    public int add(int val) {
        minQ.offer(val);
        if (minQ.size() > k) {
            minQ.poll();
        }
        return minQ.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
```

#### C++

```cpp
class KthLargest {
public:
    KthLargest(int k, vector<int>& nums) {
        this->k = k;
        for (int x : nums) {
            add(x);
        }
    }

    int add(int val) {
        minQ.push(val);
        if (minQ.size() > k) {
            minQ.pop();
        }
        return minQ.top();
    }

private:
    int k;
    priority_queue<int, vector<int>, greater<int>> minQ;
};

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest* obj = new KthLargest(k, nums);
 * int param_1 = obj->add(val);
 */
```

#### Go

```go
type KthLargest struct {
	k    int
	minQ hp
}

func Constructor(k int, nums []int) KthLargest {
	minQ := hp{}
	this := KthLargest{k, minQ}
	for _, x := range nums {
		this.Add(x)
	}
	return this
}

func (this *KthLargest) Add(val int) int {
	heap.Push(&this.minQ, val)
	if this.minQ.Len() > this.k {
		heap.Pop(&this.minQ)
	}
	return this.minQ.IntSlice[0]
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

/**
 * Your KthLargest object will be instantiated and called as such:
 * obj := Constructor(k, nums);
 * param_1 := obj.Add(val);
 */
```

#### TypeScript

```ts
class KthLargest {
    #k: number = 0;
    #minQ = new MinPriorityQueue<number>();

    constructor(k: number, nums: number[]) {
        this.#k = k;
        for (const x of nums) {
            this.add(x);
        }
    }

    add(val: number): number {
        this.#minQ.enqueue(val);
        if (this.#minQ.size() > this.#k) {
            this.#minQ.dequeue();
        }
        return this.#minQ.front();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * var obj = new KthLargest(k, nums)
 * var param_1 = obj.add(val)
 */
```

#### JavaScript

```js
/**
 * @param {number} k
 * @param {number[]} nums
 */
var KthLargest = function (k, nums) {
    this.k = k;
    this.minQ = new MinPriorityQueue();
    for (const x of nums) {
        this.add(x);
    }
};

/**
 * @param {number} val
 * @return {number}
 */
KthLargest.prototype.add = function (val) {
    this.minQ.enqueue(val);
    if (this.minQ.size() > this.k) {
        this.minQ.dequeue();
    }
    return this.minQ.front();
};

/**
 * Your KthLargest object will be instantiated and called as such:
 * var obj = new KthLargest(k, nums)
 * var param_1 = obj.add(val)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
