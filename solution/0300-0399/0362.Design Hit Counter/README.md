---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0362.Design%20Hit%20Counter/README.md
tags:
    - 设计
    - 队列
    - 数组
    - 二分查找
    - 数据流
---

<!-- problem:start -->

# [362. 敲击计数器 🔒](https://leetcode.cn/problems/design-hit-counter)

[English Version](/solution/0300-0399/0362.Design%20Hit%20Counter/README_EN.md)

## 题目描述

<!-- description:start -->

<p>设计一个敲击计数器，使它可以统计在过去 <code>5</code> 分钟内被敲击次数。（即过去 <code>300</code> 秒）</p>

<p>您的系统应该接受一个时间戳参数&nbsp;<code>timestamp</code>&nbsp;(单位为 <strong>秒</strong>&nbsp;)，并且您可以假定对系统的调用是按时间顺序进行的(即&nbsp;<code>timestamp</code>&nbsp;是单调递增的)。几次撞击可能同时发生。</p>

<p>实现&nbsp;<code>HitCounter</code>&nbsp;类:</p>

<ul>
	<li><code>HitCounter()</code>&nbsp;初始化命中计数器系统。</li>
	<li><code>void hit(int timestamp)</code>&nbsp;记录在&nbsp;<code>timestamp</code>&nbsp;(&nbsp;<strong>单位为秒</strong>&nbsp;)发生的一次命中。在同一个&nbsp;<code>timestamp</code>&nbsp;中可能会出现几个点击。</li>
	<li><code>int getHits(int timestamp)</code>&nbsp;返回&nbsp;<code>timestamp</code>&nbsp;在过去 5 分钟内(即过去 <code>300</code> 秒)的命中次数。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
["HitCounter", "hit", "hit", "hit", "getHits", "hit", "getHits", "getHits"]
[[], [1], [2], [3], [4], [300], [300], [301]]
<strong>输出：</strong>
[null, null, null, null, 3, null, 4, 3]

<strong>解释：</strong>
HitCounter counter = new HitCounter();
counter.hit(1);// 在时刻 1 敲击一次。
counter.hit(2);// 在时刻 2 敲击一次。
counter.hit(3);// 在时刻 3 敲击一次。
counter.getHits(4);// 在时刻 4 统计过去 5 分钟内的敲击次数, 函数返回 3 。
counter.hit(300);// 在时刻 300 敲击一次。
counter.getHits(300); // 在时刻 300 统计过去 5 分钟内的敲击次数，函数返回 4 。
counter.getHits(301); // 在时刻 301 统计过去 5 分钟内的敲击次数，函数返回 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= timestamp &lt;= 2 * 10<sup>9</sup></code></li>
	<li>所有对系统的调用都是按时间顺序进行的（即&nbsp;<code>timestamp</code>&nbsp;是单调递增的）</li>
	<li><code>hit</code>&nbsp;and&nbsp;<code>getHits&nbsp;</code>最多被调用&nbsp;<code>300</code>&nbsp;次</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶:</strong>&nbsp;如果每秒的敲击次数是一个很大的数字，你的计数器可以应对吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

<!-- thinking:start -->

> **思考**
>
> 统计最近 $300$ 秒的点击。时间戳递增。队列弹出过期击亦可，数组加二分更短。
>
> `hit` 追加时间戳；`getHits` 二分第一个 $\ge timestamp-299$ 的位置，长度为右段元素个数。

<!-- thinking:end -->

由于 `timestamp` 是单调递增的，我们可以使用一个数组 `ts` 来存储所有的 `timestamp`，然后在 `getHits` 方法中使用二分查找找到第一个大于等于 `timestamp - 300 + 1` 的位置，然后返回 `ts` 的长度减去这个位置即可。

时间复杂度方面，`hit` 方法的时间复杂度为 $O(1)$，`getHits` 方法的时间复杂度为 $O(\log n)$。其中 $n$ 为 `ts` 的长度。

<!-- tabs:start -->

#### Python3

```python
class HitCounter:

    def __init__(self):
        self.ts = []

    def hit(self, timestamp: int) -> None:
        self.ts.append(timestamp)

    def getHits(self, timestamp: int) -> int:
        return len(self.ts) - bisect_left(self.ts, timestamp - 300 + 1)


# Your HitCounter object will be instantiated and called as such:
# obj = HitCounter()
# obj.hit(timestamp)
# param_2 = obj.getHits(timestamp)
```

#### Java

```java
class HitCounter {
    private List<Integer> ts = new ArrayList<>();

    public HitCounter() {
    }

    public void hit(int timestamp) {
        ts.add(timestamp);
    }

    public int getHits(int timestamp) {
        int l = search(timestamp - 300 + 1);
        return ts.size() - l;
    }

    private int search(int x) {
        int l = 0, r = ts.size();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (ts.get(mid) >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
```

#### C++

```cpp
class HitCounter {
public:
    HitCounter() {

    }

    void hit(int timestamp) {
        ts.push_back(timestamp);
    }

    int getHits(int timestamp) {
        return ts.end() - lower_bound(ts.begin(), ts.end(), timestamp - 300 + 1);
    }

private:
    vector<int> ts;
};

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter* obj = new HitCounter();
 * obj->hit(timestamp);
 * int param_2 = obj->getHits(timestamp);
 */
```

#### Go

```go
type HitCounter struct {
	ts []int
}

func Constructor() HitCounter {
	return HitCounter{}
}

func (this *HitCounter) Hit(timestamp int) {
	this.ts = append(this.ts, timestamp)
}

func (this *HitCounter) GetHits(timestamp int) int {
	return len(this.ts) - sort.SearchInts(this.ts, timestamp-300+1)
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Hit(timestamp);
 * param_2 := obj.GetHits(timestamp);
 */
```

#### TypeScript

```ts
class HitCounter {
    private ts: number[] = [];

    constructor() {}

    hit(timestamp: number): void {
        this.ts.push(timestamp);
    }

    getHits(timestamp: number): number {
        const search = (x: number) => {
            let [l, r] = [0, this.ts.length];
            while (l < r) {
                const mid = (l + r) >> 1;
                if (this.ts[mid] >= x) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        };
        return this.ts.length - search(timestamp - 300 + 1);
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * var obj = new HitCounter()
 * obj.hit(timestamp)
 * var param_2 = obj.getHits(timestamp)
 */
```

#### Rust

```rust
struct HitCounter {
    ts: Vec<i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl HitCounter {
    fn new() -> Self {
        HitCounter { ts: Vec::new() }
    }

    fn hit(&mut self, timestamp: i32) {
        self.ts.push(timestamp);
    }

    fn get_hits(&self, timestamp: i32) -> i32 {
        let l = self.search(timestamp - 300 + 1);
        (self.ts.len() - l) as i32
    }

    fn search(&self, x: i32) -> usize {
        let (mut l, mut r) = (0, self.ts.len());
        while l < r {
            let mid = (l + r) / 2;
            if self.ts[mid] >= x {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        l
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
