---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3369.Design%20an%20Array%20Statistics%20Tracker/README.md
tags:
    - 设计
    - 队列
    - 哈希表
    - 二分查找
    - 数据流
    - 有序集合
    - 堆（优先队列）
---

<!-- problem:start -->

# [3369. 设计数组统计跟踪器 🔒](https://leetcode.cn/problems/design-an-array-statistics-tracker)

[English Version](/solution/3300-3399/3369.Design%20an%20Array%20Statistics%20Tracker/README_EN.md)

## 题目描述

<!-- description:start -->

<p>设计一个数据结构来跟踪它其中的值，并回答一些有关其平均值、中位数和众数的询问。</p>

<p>实现&nbsp;<code>StatisticsTracker</code> 类。</p>

<ul>
	<li><code>StatisticsTracker()</code>：用空数组初始化&nbsp;<code>StatisticsTracker</code>&nbsp;对象。</li>
	<li><code>void addNumber(int number)</code>：将&nbsp;<code>number</code>&nbsp;添加到数据结构中。</li>
	<li><code>void removeFirstAddedNumber()</code>：从数据结构删除最早添加的数字。</li>
	<li><code>int getMean()</code>：返回数据结构中数字向下取整的 <strong>平均值</strong>。</li>
	<li><code>int getMedian()</code>：返回数据结构中数字的 <strong>中位数</strong>。</li>
	<li><code>int getMode()</code>：返回数据结构中数字的 <strong>众数</strong>。如果有多个众数，返回最小的那个。</li>
</ul>

<p><b>注意：</b></p>

<ul>
	<li>数组的 <strong>平均值</strong> 是所有值的和除以数组中值的数量。</li>
	<li>数组的 <strong>中位数</strong> 是在非递减顺序排序后数组的中间元素。如果中位数有两个选择，则取两个值中较大的一个。</li>
	<li>数组的 <strong>众数</strong>&nbsp;是数组中出现次数最多的元素。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><br />
<span class="example-io">["StatisticsTracker", "addNumber", "addNumber", "addNumber", "addNumber", "getMean", "getMedian", "getMode", "removeFirstAddedNumber", "getMode"]<br />
[[], [4], [4], [2], [3], [], [], [], [], []]</span></p>

<p><strong>输出：</strong><br />
<span class="example-io">[null, null, null, null, null, 3, 4, 4, null, 2] </span></p>

<p><strong>解释：</strong></p>
StatisticsTracker statisticsTracker = new StatisticsTracker();<br />
statisticsTracker.addNumber(4); // 现在数据结构中有 [4]<br />
statisticsTracker.addNumber(4); // 现在数据结构中有 [4, 4]<br />
statisticsTracker.addNumber(2); // 现在数据结构中有 [4, 4, 2]<br />
statisticsTracker.addNumber(3); // 现在数据结构中有 [4, 4, 2, 3]<br />
statisticsTracker.getMean(); // return 3<br />
statisticsTracker.getMedian(); // return 4<br />
statisticsTracker.getMode(); // return 4<br />
statisticsTracker.removeFirstAddedNumber(); // 现在数据结构中有 [4, 2, 3]<br />
statisticsTracker.getMode(); // return 2</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><br />
<span class="example-io">["StatisticsTracker", "addNumber", "addNumber", "getMean", "removeFirstAddedNumber", "addNumber", "addNumber", "removeFirstAddedNumber", "getMedian", "addNumber", "getMode"]<br />
[[], [9], [5], [], [], [5], [6], [], [], [8], []]</span></p>

<p><strong>输出：</strong><br />
<span class="example-io">[null, null, null, 7, null, null, null, null, 6, null, 5] </span></p>

<p><strong>解释：</strong></p>
StatisticsTracker statisticsTracker = new StatisticsTracker();<br />
statisticsTracker.addNumber(9); // 现在数据结构中有 [9]<br />
statisticsTracker.addNumber(5); // 现在数据结构中有 [9, 5]<br />
statisticsTracker.getMean(); // return 7<br />
statisticsTracker.removeFirstAddedNumber(); // 现在数据结构中有 [5]<br />
statisticsTracker.addNumber(5); // 现在数据结构中有 [5, 5]<br />
statisticsTracker.addNumber(6); // 现在数据结构中有 [5, 5, 6]<br />
statisticsTracker.removeFirstAddedNumber(); // 现在数据结构中有 [5, 6]<br />
statisticsTracker.getMedian(); // return 6<br />
statisticsTracker.addNumber(8); // 现在数据结构中有 [5, 6, 8]<br />
statisticsTracker.getMode(); // return 5</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= number &lt;= 10<sup>9</sup></code></li>
	<li><code>addNumber</code>，<code>removeFirstAddedNumber</code>，<code>getMean</code>，<code>getMedian</code>&nbsp;和&nbsp;<code>getMode</code>&nbsp;的总调用次数最多为&nbsp;<code>10<sup>5</sup></code>。</li>
	<li><code>removeFirstAddedNumber</code>，<code>getMean</code>，<code>getMedian</code>&nbsp;和&nbsp;<code>getMode</code>&nbsp;只会在数据结构中至少有一个元素时被调用。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：队列 + 哈希表 + 有序集合

<!-- thinking:start -->

> **思考**
>
> 需支持入队、删最早元素，以及 $O(\log n)$ 查询均值、中位数与众数。操作次数至 $10^5$。
>
> 队列保序，总和 $s$ 支撑均值；有序表 $\textit{sl}$ 按下标取中位数；另一有序表按 $(-\textit{cnt},\textit{value})$ 取众数。
>
> 更新频率时先删旧对再插新对，保证众数集合与计数一致。

<!-- thinking:end -->

我们定义一个队列 $\textit{q}$，用来存储添加的数字，一个变量 $\textit{s}$，用来存储所有数字的和，一个哈希表 $\textit{cnt}$，用来存储每个数字的出现次数，一个有序集合 $\textit{sl}$，用来存储所有数字，一个有序集合 $\textit{sl2}$，用来存储所有数字及其出现次数，按照出现次数降序、数值升序的顺序。

在 `addNumber` 方法中，我们将数字添加到队列 $\textit{q}$ 中，将数字添加到有序集合 $\textit{sl}$ 中，然后先将数字及其出现次数从有序集合 $\textit{sl2}$ 中删除，再更新数字的出现次数，最后将数字及其出现次数添加到有序集合 $\textit{sl2}$ 中，并更新所有数字的和。时间复杂度为 $O(\log n)$。

在 `removeFirstAddedNumber` 方法中，我们从队列 $\textit{q}$ 中删除最早添加的数字，从有序集合 $\textit{sl}$ 中删除数字，然后先将数字及其出现次数从有序集合 $\textit{sl2}$ 中删除，再更新数字的出现次数，最后将数字及其出现次数添加到有序集合 $\textit{sl2}$ 中，并更新所有数字的和。时间复杂度为 $O(\log n)$。

在 `getMean` 方法中，我们返回所有数字的和除以数字的数量，时间复杂度为 $O(1)$。

在 `getMedian` 方法中，我们返回有序集合 $\textit{sl}$ 中的第 $\textit{len}(\textit{q}) / 2$ 个数字，时间复杂度为 $O(1)$ 或 $O(\log n)$。

在 `getMode` 方法中，我们返回有序集合 $\textit{sl2}$ 中的第一个数字，时间复杂度 $O(1)$。

> 在 Python 中，我们可以直接按下标获取有序集合中的元素，在其它语言中，我们可以通过对顶堆实现。

空间复杂度 $O(n)$，其中 $n$ 为添加的数字的数量。

<!-- tabs:start -->

#### Python3

```python
class StatisticsTracker:

    def __init__(self):
        self.q = deque()
        self.s = 0
        self.cnt = defaultdict(int)
        self.sl = SortedList()
        self.sl2 = SortedList(key=lambda x: (-x[1], x[0]))

    def addNumber(self, number: int) -> None:
        self.q.append(number)
        self.sl.add(number)
        self.sl2.discard((number, self.cnt[number]))
        self.cnt[number] += 1
        self.sl2.add((number, self.cnt[number]))
        self.s += number

    def removeFirstAddedNumber(self) -> None:
        number = self.q.popleft()
        self.sl.remove(number)
        self.sl2.discard((number, self.cnt[number]))
        self.cnt[number] -= 1
        self.sl2.add((number, self.cnt[number]))
        self.s -= number

    def getMean(self) -> int:
        return self.s // len(self.q)

    def getMedian(self) -> int:
        return self.sl[len(self.q) // 2]

    def getMode(self) -> int:
        return self.sl2[0][0]


# Your StatisticsTracker object will be instantiated and called as such:
# obj = StatisticsTracker()
# obj.addNumber(number)
# obj.removeFirstAddedNumber()
# param_3 = obj.getMean()
# param_4 = obj.getMedian()
# param_5 = obj.getMode()
```

#### Java

```java
class MedianFinder {
    private final PriorityQueue<Integer> small = new PriorityQueue<>(Comparator.reverseOrder());
    private final PriorityQueue<Integer> large = new PriorityQueue<>();
    private final Map<Integer, Integer> delayed = new HashMap<>();
    private int smallSize;
    private int largeSize;

    public void addNum(int num) {
        if (small.isEmpty() || num <= small.peek()) {
            small.offer(num);
            ++smallSize;
        } else {
            large.offer(num);
            ++largeSize;
        }
        rebalance();
    }

    public Integer findMedian() {
        return smallSize == largeSize ? large.peek() : small.peek();
    }

    public void removeNum(int num) {
        delayed.merge(num, 1, Integer::sum);
        if (num <= small.peek()) {
            --smallSize;
            if (num == small.peek()) {
                prune(small);
            }
        } else {
            --largeSize;
            if (num == large.peek()) {
                prune(large);
            }
        }
        rebalance();
    }

    private void prune(PriorityQueue<Integer> pq) {
        while (!pq.isEmpty() && delayed.containsKey(pq.peek())) {
            if (delayed.merge(pq.peek(), -1, Integer::sum) == 0) {
                delayed.remove(pq.peek());
            }
            pq.poll();
        }
    }

    private void rebalance() {
        if (smallSize > largeSize + 1) {
            large.offer(small.poll());
            --smallSize;
            ++largeSize;
            prune(small);
        } else if (smallSize < largeSize) {
            small.offer(large.poll());
            --largeSize;
            ++smallSize;
            prune(large);
        }
    }
}

class StatisticsTracker {
    private final Deque<Integer> q = new ArrayDeque<>();
    private long s;
    private final Map<Integer, Integer> cnt = new HashMap<>();
    private final MedianFinder medianFinder = new MedianFinder();
    private final TreeSet<int[]> ts
        = new TreeSet<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : b[1] - a[1]);

    public StatisticsTracker() {
    }

    public void addNumber(int number) {
        q.offerLast(number);
        s += number;
        ts.remove(new int[] {number, cnt.getOrDefault(number, 0)});
        cnt.merge(number, 1, Integer::sum);
        medianFinder.addNum(number);
        ts.add(new int[] {number, cnt.get(number)});
    }

    public void removeFirstAddedNumber() {
        int number = q.pollFirst();
        s -= number;
        ts.remove(new int[] {number, cnt.get(number)});
        cnt.merge(number, -1, Integer::sum);
        medianFinder.removeNum(number);
        ts.add(new int[] {number, cnt.get(number)});
    }

    public int getMean() {
        return (int) (s / q.size());
    }

    public int getMedian() {
        return medianFinder.findMedian();
    }

    public int getMode() {
        return ts.first()[0];
    }
}
```

#### C++

```cpp
class MedianFinder {
public:
    void addNum(int num) {
        if (small.empty() || num <= small.top()) {
            small.push(num);
            ++smallSize;
        } else {
            large.push(num);
            ++largeSize;
        }
        reblance();
    }

    void removeNum(int num) {
        ++delayed[num];
        if (num <= small.top()) {
            --smallSize;
            if (num == small.top()) {
                prune(small);
            }
        } else {
            --largeSize;
            if (num == large.top()) {
                prune(large);
            }
        }
        reblance();
    }

    int findMedian() {
        return smallSize == largeSize ? large.top() : small.top();
    }

private:
    priority_queue<int> small;
    priority_queue<int, vector<int>, greater<int>> large;
    unordered_map<int, int> delayed;
    int smallSize = 0;
    int largeSize = 0;

    template <typename T>
    void prune(T& pq) {
        while (!pq.empty() && delayed[pq.top()]) {
            if (--delayed[pq.top()] == 0) {
                delayed.erase(pq.top());
            }
            pq.pop();
        }
    }

    void reblance() {
        if (smallSize > largeSize + 1) {
            large.push(small.top());
            small.pop();
            --smallSize;
            ++largeSize;
            prune(small);
        } else if (smallSize < largeSize) {
            small.push(large.top());
            large.pop();
            ++smallSize;
            --largeSize;
            prune(large);
        }
    }
};

class StatisticsTracker {
private:
    queue<int> q;
    long long s = 0;
    unordered_map<int, int> cnt;
    MedianFinder medianFinder;
    set<pair<int, int>> ts;

public:
    StatisticsTracker() {}

    void addNumber(int number) {
        q.push(number);
        s += number;
        ts.erase({-cnt[number], number});
        cnt[number]++;
        medianFinder.addNum(number);
        ts.insert({-cnt[number], number});
    }

    void removeFirstAddedNumber() {
        int number = q.front();
        q.pop();
        s -= number;
        ts.erase({-cnt[number], number});
        cnt[number]--;
        if (cnt[number] > 0) {
            ts.insert({-cnt[number], number});
        }
        medianFinder.removeNum(number);
    }

    int getMean() {
        return static_cast<int>(s / q.size());
    }

    int getMedian() {
        return medianFinder.findMedian();
    }

    int getMode() {
        return ts.begin()->second;
    }
};
```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
