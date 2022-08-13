# [剑指 Offer II 059. 数据流的第 K 大数值](https://leetcode.cn/problems/jBjn9C)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个找到数据流中第 <code>k</code> 大元素的类（class）。注意是排序后的第 <code>k</code> 大元素，不是第 <code>k</code> 个不同的元素。</p>

<p>请实现 <code>KthLargest</code>&nbsp;类：</p>

<ul>
	<li><code>KthLargest(int k, int[] nums)</code> 使用整数 <code>k</code> 和整数流 <code>nums</code> 初始化对象。</li>
	<li><code>int add(int val)</code> 将 <code>val</code> 插入数据流 <code>nums</code> 后，返回当前数据流中第 <code>k</code> 大的元素。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
[&quot;KthLargest&quot;, &quot;add&quot;, &quot;add&quot;, &quot;add&quot;, &quot;add&quot;, &quot;add&quot;]
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

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= val &lt;= 10<sup>4</sup></code></li>
	<li>最多调用 <code>add</code> 方法 <code>10<sup>4</sup></code> 次</li>
	<li>题目数据保证，在查找第 <code>k</code> 大元素时，数组中至少有 <code>k</code> 个元素</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 703&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/kth-largest-element-in-a-stream/">https://leetcode.cn/problems/kth-largest-element-in-a-stream/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

小根堆存放最大的 k 个元素，那么堆顶就是第 k 大的元素。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class KthLargest:
    def __init__(self, k: int, nums: List[int]):
        self.q = []
        self.size = k
        for num in nums:
            self.add(num)

    def add(self, val: int) -> int:
        heappush(self.q, val)
        if len(self.q) > self.size:
            heappop(self.q)
        return self.q[0]


# Your KthLargest object will be instantiated and called as such:
# obj = KthLargest(k, nums)
# param_1 = obj.add(val)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class KthLargest {
    private PriorityQueue<Integer> q;
    private int size;

    public KthLargest(int k, int[] nums) {
        q = new PriorityQueue<>(k);
        size = k;
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        q.offer(val);
        if (q.size() > size) {
            q.poll();
        }
        return q.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
```

### **C++**

```cpp
class KthLargest {
public:
    priority_queue<int, vector<int>, greater<int>> q;
    int size;

    KthLargest(int k, vector<int>& nums) {
        size = k;
        for (int num : nums) add(num);
    }

    int add(int val) {
        q.push(val);
        if (q.size() > size) q.pop();
        return q.top();
    }
};

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest* obj = new KthLargest(k, nums);
 * int param_1 = obj->add(val);
 */
```

### **Go**

```go
type KthLargest struct {
	h *IntHeap
	k int
}

func Constructor(k int, nums []int) KthLargest {
	h := &IntHeap{}
	heap.Init(h)
	for _, v := range nums {
		heap.Push(h, v)
	}

	for h.Len() > k {
		heap.Pop(h)
	}

	return KthLargest{
		h: h,
		k: k,
	}
}

func (this *KthLargest) Add(val int) int {
	heap.Push(this.h, val)
	for this.h.Len() > this.k {
		heap.Pop(this.h)
	}

	return this.h.Top()
}

func connectSticks(sticks []int) int {
	h := IntHeap(sticks)
	heap.Init(&h)
	res := 0
	for h.Len() > 1 {
		val := heap.Pop(&h).(int)
		val += heap.Pop(&h).(int)
		res += val
		heap.Push(&h, val)
	}
	return res
}

type IntHeap []int

func (h IntHeap) Len() int           { return len(h) }
func (h IntHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h IntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *IntHeap) Push(x interface{}) {
	*h = append(*h, x.(int))
}
func (h *IntHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func (h *IntHeap) Top() int {
	if (*h).Len() == 0 {
		return 0
	}

	return (*h)[0]
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * obj := Constructor(k, nums);
 * param_1 := obj.Add(val);
 */
```

### **...**

```

```

<!-- tabs:end -->
