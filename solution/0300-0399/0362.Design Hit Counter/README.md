# [362. 敲击计数器](https://leetcode.cn/problems/design-hit-counter)

[English Version](/solution/0300-0399/0362.Design%20Hit%20Counter/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

用哈希表作为计数器实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class HitCounter:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.counter = Counter()

    def hit(self, timestamp: int) -> None:
        """
        Record a hit.
        @param timestamp - The current timestamp (in seconds granularity).
        """
        self.counter[timestamp] += 1

    def getHits(self, timestamp: int) -> int:
        """
        Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity).
        """
        return sum([v for t, v in self.counter.items() if t + 300 > timestamp])


# Your HitCounter object will be instantiated and called as such:
# obj = HitCounter()
# obj.hit(timestamp)
# param_2 = obj.getHits(timestamp)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class HitCounter {

    private Map<Integer, Integer> counter;

    /** Initialize your data structure here. */
    public HitCounter() {
        counter = new HashMap<>();
    }

    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        counter.put(timestamp, counter.getOrDefault(timestamp, 0) + 1);
    }

    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int hits = 0;
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if (entry.getKey() + 300 > timestamp) {
                hits += entry.getValue();
            }
        }
        return hits;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
```

### **...**

```

```

<!-- tabs:end -->
