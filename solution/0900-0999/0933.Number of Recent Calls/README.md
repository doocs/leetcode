# [933. 最近的请求次数](https://leetcode-cn.com/problems/number-of-recent-calls)

[English Version](/solution/0900-0999/0933.Number%20of%20Recent%20Calls/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>写一个&nbsp;<code>RecentCounter</code>&nbsp;类来计算最近的请求。</p>

<p>它只有一个方法：<code>ping(int t)</code>，其中&nbsp;<code>t</code>&nbsp;代表以毫秒为单位的某个时间。</p>

<p>返回从 3000 毫秒前到现在的&nbsp;<code>ping</code>&nbsp;数。</p>

<p>任何处于&nbsp;<code>[t - 3000, t]</code>&nbsp;时间范围之内的 <code>ping</code>&nbsp;都将会被计算在内，包括当前（指 <code>t</code>&nbsp;时刻）的 <code>ping</code>。</p>

<p>保证每次对 <code>ping</code> 的调用都使用比之前更大的 <code>t</code> 值。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>inputs = [&quot;RecentCounter&quot;,&quot;ping&quot;,&quot;ping&quot;,&quot;ping&quot;,&quot;ping&quot;], inputs = [[],[1],[100],[3001],[3002]]
<strong>输出：</strong>[null,1,2,3,3]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>每个测试用例最多调用&nbsp;<code>10000</code>&nbsp;次&nbsp;<code>ping</code>。</li>
	<li>每个测试用例会使用严格递增的 <code>t</code> 值来调用&nbsp;<code>ping</code>。</li>
	<li>每次调用 <code>ping</code>&nbsp;都有&nbsp;<code>1 &lt;= t &lt;= 10^9</code>。</li>
</ol>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

在第 1、100、3001、3002 这四个时间点分别进行了 ping 请求， 在 3001 秒的时候， 它前面的 3000 秒指的是区间 `[1,3001]`， 所以一共是有 `1、100、3001` 三个请求， t = 3002 的前 3000 秒指的是区间 `[2,3002]`, 所以有 `100、3001、3002` 三次请求。

可以用队列实现。每次将 t 进入队尾，同时从队头开始依次移除小于 `t-3000` 的元素。然后返回队列的大小 `q.size()` 即可。

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class RecentCounter:

    def __init__(self):
        self.q = []


    def ping(self, t: int) -> int:
        self.q.append(t)
        while self.q[0] < t - 3000:
            self.q.pop(0)
        return len(self.q)


# Your RecentCounter object will be instantiated and called as such:
# obj = RecentCounter()
# param_1 = obj.ping(t)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class RecentCounter {

    private Deque<Integer> q;

    public RecentCounter() {
        q = new ArrayDeque<>();
    }

    public int ping(int t) {
        q.offerLast(t);
        while (q.peekFirst() < t - 3000) {
            q.pollFirst();
        }
        return q.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
```

### **...**

```

```

<!-- tabs:end -->
