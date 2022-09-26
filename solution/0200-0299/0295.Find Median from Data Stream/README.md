# [295. 数据流的中位数](https://leetcode.cn/problems/find-median-from-data-stream)

[English Version](/solution/0200-0299/0295.Find%20Median%20from%20Data%20Stream/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。</p>

<p>例如，</p>

<p>[2,3,4]&nbsp;的中位数是 3</p>

<p>[2,3] 的中位数是 (2 + 3) / 2 = 2.5</p>

<p>设计一个支持以下两种操作的数据结构：</p>

<ul>
	<li>void addNum(int num) - 从数据流中添加一个整数到数据结构中。</li>
	<li>double findMedian() - 返回目前所有元素的中位数。</li>
</ul>

<p><strong>示例：</strong></p>

<pre>addNum(1)
addNum(2)
findMedian() -&gt; 1.5
addNum(3) 
findMedian() -&gt; 2</pre>

<p><strong>进阶:</strong></p>

<ol>
	<li>如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？</li>
	<li>如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：优先队列（双堆）**

创建大根堆、小根堆，其中：大根堆存放较小的一半元素，小根堆存放较大的一半元素。

添加元素时，先放入小根堆，然后将小根堆对顶元素弹出并放入大根堆（使得大根堆个数多 $1$）；若大小根堆元素个数差超过 $1$，则将大根堆元素弹出放入小根堆。

取中位数时，若大根堆元素较多，取大根堆堆顶，否则取两堆顶元素和的平均值。

**时间复杂度分析：**

每次添加元素的时间复杂度为 $O(\log n)$，取中位数的时间复杂度为 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class MedianFinder:

    def __init__(self):
        """
        initialize your data structure here.
        """
        self.h1 = []
        self.h2 = []

    def addNum(self, num: int) -> None:
        heappush(self.h1, num)
        heappush(self.h2, -heappop(self.h1))
        if len(self.h2) - len(self.h1) > 1:
            heappush(self.h1, -heappop(self.h2))

    def findMedian(self) -> float:
        if len(self.h2) > len(self.h1):
            return -self.h2[0]
        return (self.h1[0] - self.h2[0]) / 2


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
    private PriorityQueue<Integer> q2 = new PriorityQueue<>(Collections.reverseOrder());

    /** initialize your data structure here. */
    public MedianFinder() {

    }

    public void addNum(int num) {
        q1.offer(num);
        q2.offer(q1.poll());
        if (q2.size() - q1.size() > 1) {
            q1.offer(q2.poll());
        }
    }

    public double findMedian() {
        if (q2.size() > q1.size()) {
            return q2.peek();
        }
        return (q1.peek() + q2.peek()) * 1.0 / 2;
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
        q1.push(num);
        q2.push(q1.top());
        q1.pop();
        if (q2.size() - q1.size() > 1) {
            q1.push(q2.top());
            q2.pop();
        }
    }

    double findMedian() {
        if (q2.size() > q1.size()) {
            return q2.top();
        }
        return (double) (q1.top() + q2.top()) / 2;
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
	q1 hp
	q2 hp
}

/** initialize your data structure here. */
func Constructor() MedianFinder {
	return MedianFinder{hp{}, hp{}}
}

func (this *MedianFinder) AddNum(num int) {
	heap.Push(&this.q1, num)
	heap.Push(&this.q2, -heap.Pop(&this.q1).(int))
	if this.q2.Len()-this.q1.Len() > 1 {
		heap.Push(&this.q1, -heap.Pop(&this.q2).(int))
	}
}

func (this *MedianFinder) FindMedian() float64 {
	if this.q2.Len() > this.q1.Len() {
		return -float64(this.q2.IntSlice[0])
	}
	return float64(this.q1.IntSlice[0]-this.q2.IntSlice[0]) / 2.0
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * obj := Constructor();
 * obj.AddNum(num);
 * param_2 := obj.FindMedian();
 */

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool  { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
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
