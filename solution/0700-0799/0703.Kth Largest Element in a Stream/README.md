---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0703.Kth%20Largest%20Element%20in%20a%20Stream/README.md
tags:
    - 树
    - 设计
    - 二叉搜索树
    - 二叉树
    - 数据流
    - 堆（优先队列）
---

<!-- problem:start -->

# [703. 数据流中的第 K 大元素](https://leetcode.cn/problems/kth-largest-element-in-a-stream)

[English Version](/solution/0700-0799/0703.Kth%20Largest%20Element%20in%20a%20Stream/README_EN.md)

## 题目描述

<!-- description:start -->

<p>设计一个找到数据流中第 <code>k</code> 大元素的类（class）。注意是排序后的第 <code>k</code> 大元素，不是第 <code>k</code> 个不同的元素。</p>

<p>请实现 <code>KthLargest</code> 类：</p>

<ul>
	<li><code>KthLargest(int k, int[] nums)</code> 使用整数 <code>k</code> 和整数流 <code>nums</code> 初始化对象。</li>
	<li><code>int add(int val)</code> 将 <code>val</code> 插入数据流 <code>nums</code> 后，返回当前数据流中第 <code>k</code> 大的元素。</li>
</ul>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["KthLargest", "add", "add", "add", "add", "add"]
[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
<strong>输出：</strong>
[null, 4, 5, 5, 8, 8]

<strong>解释：</strong>
KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
kthLargest.add(3);   // return 4
kthLargest.add(5);   // return 5
kthLargest.add(10);  // return 5
kthLargest.add(9);   // return 8
kthLargest.add(4);   // return 8
</pre>

<p> </p>
<strong>提示：</strong>

<ul>
	<li><code>1 <= k <= 10<sup>4</sup></code></li>
	<li><code>0 <= nums.length <= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> <= val <= 10<sup>4</sup></code></li>
	<li>最多调用 <code>add</code> 方法 <code>10<sup>4</sup></code> 次</li>
	<li>题目数据保证，在查找第 <code>k</code> 大元素时，数组中至少有 <code>k</code> 个元素</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：优先队列（小根堆）

我们维护一个优先队列（小根堆）$\textit{minQ}$。

初始化时，我们将数组 $\textit{nums}$ 中的元素依次加入 $\textit{minQ}$，并保持 $\textit{minQ}$ 的大小不超过 $k$。时间复杂度 $O(n \times \log k)$。

每次加入一个新元素时，如果 $\textit{minQ}$ 的大小超过了 $k$，我们就将堆顶元素弹出，保证 $\textit{minQ}$ 的大小为 $k$。时间复杂度 $O(\log k)$。

这样，$\textit{minQ}$ 中的元素就是数组 $\textit{nums}$ 中最大的 $k$ 个元素，堆顶元素就是第 $k$ 大的元素。

空间复杂度 $O(k)$。

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
    #minQ = new MinPriorityQueue();

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
        return this.#minQ.front().element;
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
    return this.minQ.front().element;
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
