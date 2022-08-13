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

-   创建大根堆、小根堆，其中：大根堆存放较小的一半元素，小根堆存放较大的一半元素。
-   添加元素时，若两堆元素个数相等，放入小根堆（使得小根堆个数多 1）；若不等，放入大根堆（使得大小根堆元素个数相等）
-   取中位数时，若两堆元素个数相等，取两堆顶求平均值；若不等，取小根堆堆顶。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class MedianFinder:
    def __init__(self):
        """
        initialize your data structure here.
        """
        self.max_heap = []
        self.min_heap = []

    def addNum(self, num: int) -> None:
        if len(self.max_heap) == len(self.min_heap):
            heappush(self.min_heap, -heappushpop(self.max_heap, -num))
        else:
            heappush(self.max_heap, -heappushpop(self.min_heap, num))

    def findMedian(self) -> float:
        return (
            (-self.max_heap[0] + self.min_heap[0]) / 2
            if len(self.max_heap) == len(self.min_heap)
            else self.min_heap[0]
        )


# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class MedianFinder {
    Queue<Integer> minHeap;
    Queue<Integer> maxHeap;

    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
    }

    public void addNum(int num) {
        if (maxHeap.size() == minHeap.size()) {
            maxHeap.offer(num);
            // 放入小根堆(小根堆多1)
            minHeap.offer(maxHeap.poll());
        } else {
            minHeap.offer(num);
            // 放入大根堆(大小堆数量相等)
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (((maxHeap.size() + minHeap.size()) & 1) == 0) {
            // 偶数个，取两个堆顶平均值
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return minHeap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
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

### **C++**

```cpp
class MedianFinder {
public:
    /** initialize your data structure here. */
    MedianFinder() {
    }

    void addNum(int num) {
        if (maxHeap.size() == minHeap.size()) {
            maxHeap.push(num);
            int temp = maxHeap.top();
            maxHeap.pop();
            minHeap.push(temp);
        } else {
            minHeap.push(num);
            int temp = minHeap.top();
            minHeap.pop();
            maxHeap.push(temp);
        }
    }

    double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.top() + minHeap.top()) / 2.0;
        }
        return minHeap.top();
    }

private:
    priority_queue<int> maxHeap;
    priority_queue<int, vector<int>, greater<int>> minHeap;
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
