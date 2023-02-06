# [面试题 41. 数据流中的中位数](https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。</p>

<p>例如，</p>

<p>[2,3,4]&nbsp;的中位数是 3</p>

<p>[2,3] 的中位数是 (2 + 3) / 2 = 2.5</p>

<p>设计一个支持以下两种操作的数据结构：</p>

<ul>
	<li>void addNum(int num) - 从数据流中添加一个整数到数据结构中。</li>
	<li>double findMedian() - 返回目前所有元素的中位数。</li>
</ul>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：
</strong>[&quot;MedianFinder&quot;,&quot;addNum&quot;,&quot;addNum&quot;,&quot;findMedian&quot;,&quot;addNum&quot;,&quot;findMedian&quot;]
[[],[1],[2],[],[3],[]]
<strong>输出：</strong>[null,null,null,1.50000,null,2.00000]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：
</strong>[&quot;MedianFinder&quot;,&quot;addNum&quot;,&quot;findMedian&quot;,&quot;addNum&quot;,&quot;findMedian&quot;]
[[],[2],[],[3],[]]
<strong>输出：</strong>[null,null,2.00000,null,2.50000]</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li>最多会对&nbsp;<code>addNum、findMedian</code> 进行&nbsp;<code>50000</code>&nbsp;次调用。</li>
</ul>

<p>注意：本题与主站 295 题相同：<a href="https://leetcode.cn/problems/find-median-from-data-stream/">https://leetcode.cn/problems/find-median-from-data-stream/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：优先队列（大小根堆）**

我们可以维护两个优先队列，一个大根堆，一个小根堆，大根堆存储较小的一半数，小根堆存储较大的一半数。

当两个堆的元素个数相同时，我们优先往小根堆中添加元素，这样会使得小根堆元素个数比大根堆多 $1$，这样中位数就可以从小根堆中取出。

当两个堆的元素个数不同时，说明此时小根堆元素个数比大根堆多 $1$，我们往大根堆中添加元素，这样会使得两个堆元素个数相同，这样中位数就可以从两个堆中取出。

时间复杂度方面，添加元素的时间复杂度为 $O(\log n)$，查找中位数的时间复杂度为 $O(1)$。空间复杂度为 $O(n)$。其中 $n$ 为数据流中元素的个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class MedianFinder:
    def __init__(self):
        """
        initialize your data structure here.
        """
        self.q1 = []
        self.q2 = []

    def addNum(self, num: int) -> None:
        if len(self.q1) > len(self.q2):
            heappush(self.q2, -heappushpop(self.q1, num))
        else:
            heappush(self.q1, -heappushpop(self.q2, -num))

    def findMedian(self) -> float:
        if len(self.q1) > len(self.q2):
            return self.q1[0]
        return (self.q1[0] - self.q2[0]) / 2


# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()
```

```python
from sortedcontainers import SortedList


class MedianFinder:
    def __init__(self):
        """
        initialize your data structure here.
        """
        self.sl = SortedList()

    def addNum(self, num: int) -> None:
        self.sl.add(num)

    def findMedian(self) -> float:
        n = len(self.sl)
        if n & 1:
            return self.sl[n // 2]
        return (self.sl[(n - 1) // 2] + self.sl[n // 2]) / 2


# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class MedianFinder {
    private PriorityQueue<Integer> q1 = new PriorityQueue<>();
    private PriorityQueue<Integer> q2 = new PriorityQueue<>((a, b) -> b - a);

    /** initialize your data structure here. */
    public MedianFinder() {
    }

    public void addNum(int num) {
        if (q1.size() > q2.size()) {
            q1.offer(num);
            q2.offer(q1.poll());
        } else {
            q2.offer(num);
            q1.offer(q2.poll());
        }
    }

    public double findMedian() {
        if (q1.size() > q2.size()) {
            return q1.peek();
        }
        return (q1.peek() + q2.peek()) / 2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
```

### **C++**

```cpp
class MedianFinder {
public:
    /** initialize your data structure here. */
    MedianFinder() {

    }

    void addNum(int num) {
        if (q1.size() > q2.size()) {
            q1.push(num);
            q2.push(q1.top());
            q1.pop();
        } else {
            q2.push(num);
            q1.push(q2.top());
            q2.pop();
        }
    }

    double findMedian() {
        if (q1.size() > q2.size()) {
            return q1.top();
        }
        return (q1.top() + q2.top()) / 2.0;
    }

private:
    priority_queue<int, vector<int>, greater<int>> q1;
    priority_queue<int> q2;
};

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder* obj = new MedianFinder();
 * obj->addNum(num);
 * double param_2 = obj->findMedian();
 */
```

### **Go**

```go
type MedianFinder struct {
	q1, q2 hp
}

/** initialize your data structure here. */
func Constructor() MedianFinder {
	return MedianFinder{hp{}, hp{}}
}

func (this *MedianFinder) AddNum(num int) {
	if this.q1.Len() > this.q2.Len() {
		heap.Push(&this.q1, num)
		heap.Push(&this.q2, -heap.Pop(&this.q1).(int))
	} else {
		heap.Push(&this.q2, -num)
		heap.Push(&this.q1, -heap.Pop(&this.q2).(int))
	}
}

func (this *MedianFinder) FindMedian() float64 {
	if this.q1.Len() > this.q2.Len() {
		return float64(this.q1.IntSlice[0])
	}
	return float64(this.q1.IntSlice[0]-this.q2.IntSlice[0]) / 2.0
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool  { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * obj := Constructor();
 * obj.AddNum(num);
 * param_2 := obj.FindMedian();
 */
```

### **JavaScript**

```js
/**
 * initialize your data structure here.
 */
var MedianFinder = function () {
    this.val = [];
};

/**
 * @param {number} num
 * @return {void}
 */
MedianFinder.prototype.addNum = function (num) {
    let left = 0;
    let right = this.val.length;
    while (left < right) {
        let mid = left + ~~((right - left) / 2);
        if (num > this.val[mid]) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    this.val.splice(left, 0, num);
};

/**
 * @return {number}
 */
MedianFinder.prototype.findMedian = function () {
    let mid = ~~(this.val.length / 2);
    return this.val.length % 2
        ? this.val[mid]
        : (this.val[mid - 1] + this.val[mid]) / 2;
};
```

### **TypeScript**

```ts
class MedianFinder {
    private nums: number[];

    constructor() {
        this.nums = [];
    }

    addNum(num: number): void {
        const { nums } = this;
        let l = 0;
        let r = nums.length;
        while (l < r) {
            const mid = (l + r) >>> 1;
            if (nums[mid] < num) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        nums.splice(l, 0, num);
    }

    findMedian(): number {
        const { nums } = this;
        const n = nums.length;
        if ((n & 1) === 1) {
            return nums[n >> 1];
        }
        return (nums[n >> 1] + nums[(n >> 1) - 1]) / 2;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * var obj = new MedianFinder()
 * obj.addNum(num)
 * var param_2 = obj.findMedian()
 */
```

### **Rust**

```rust
struct MedianFinder {
    nums: Vec<i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl MedianFinder {
    /** initialize your data structure here. */
    fn new() -> Self {
        Self { nums: Vec::new() }
    }

    fn add_num(&mut self, num: i32) {
        let mut l = 0;
        let mut r = self.nums.len();
        while l < r {
            let mid = l + r >> 1;
            if self.nums[mid] < num {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        self.nums.insert(l, num);
    }

    fn find_median(&self) -> f64 {
        let n = self.nums.len();
        if (n & 1) == 1 {
            return f64::from(self.nums[n >> 1]);
        }
        f64::from(self.nums[n >> 1] + self.nums[(n >> 1) - 1]) / 2.0
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * let obj = MedianFinder::new();
 * obj.add_num(num);
 * let ret_2: f64 = obj.find_median();
 */
```

### **C#**

```cs
public class MedianFinder {
    private List<int> nums;
    private int curIndex;

    /** initialize your data structure here. */
    public MedianFinder() {
        nums = new List<int>();
    }

    private int FindIndex(int val) {
        int left = 0;
        int right = nums.Count - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (val > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public void AddNum(int num) {
        if (nums.Count == 0) {
            nums.Add(num);
            curIndex = 0;
        } else {
            curIndex = FindIndex(num);
            if (curIndex == nums.Count) {
                nums.Add(num);
            } else {
                nums.Insert(curIndex, num);
            }
        }
    }

    public double FindMedian() {
        if (nums.Count % 2 == 1) {
            return (double)nums[nums.Count / 2];
        } else {
            if (nums.Count == 0) {
                return 0;
            } else {
                return (double) (nums[nums.Count / 2 - 1] + nums[nums.Count / 2]) / 2;
            }
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.AddNum(num);
 * double param_2 = obj.FindMedian();
 */
```

### **...**

```

```

<!-- tabs:end -->
