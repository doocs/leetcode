# [901. 股票价格跨度](https://leetcode.cn/problems/online-stock-span)

[English Version](/solution/0900-0999/0901.Online%20Stock%20Span/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>编写一个 <code>StockSpanner</code> 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。</p>

<p>今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。</p>

<p>例如，如果未来7天股票的价格是 <code>[100, 80, 60, 70, 60, 75, 85]</code>，那么股票跨度将是 <code>[1, 1, 1, 2, 1, 4, 6]</code>。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>[&quot;StockSpanner&quot;,&quot;next&quot;,&quot;next&quot;,&quot;next&quot;,&quot;next&quot;,&quot;next&quot;,&quot;next&quot;,&quot;next&quot;], [[],[100],[80],[60],[70],[60],[75],[85]]
<strong>输出：</strong>[null,1,1,1,2,1,4,6]
<strong>解释：</strong>
首先，初始化 S = StockSpanner()，然后：
S.next(100) 被调用并返回 1，
S.next(80) 被调用并返回 1，
S.next(60) 被调用并返回 1，
S.next(70) 被调用并返回 2，
S.next(60) 被调用并返回 1，
S.next(75) 被调用并返回 4，
S.next(85) 被调用并返回 6。

注意 (例如) S.next(75) 返回 4，因为截至今天的最后 4 个价格
(包括今天的价格 75) 小于或等于今天的价格。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>调用&nbsp;<code>StockSpanner.next(int price)</code>&nbsp;时，将有&nbsp;<code>1 &lt;= price &lt;= 10^5</code>。</li>
	<li>每个测试用例最多可以调用&nbsp; <code>10000</code> 次 <code>StockSpanner.next</code>。</li>
	<li>在所有测试用例中，最多调用&nbsp;<code>150000</code>&nbsp;次&nbsp;<code>StockSpanner.next</code>。</li>
	<li>此问题的总时间限制减少了 50%。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

单调栈。

单调栈常见模型：找出每个数左/右边**离它最近的**且**比它大/小的数**。模板：

```python
stk = []
for i in range(n):
    while stk and check(stk[-1], i):
        stk.pop()
    stk.append(i)
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class StockSpanner:

    def __init__(self):
        self.stk = []

    def next(self, price: int) -> int:
        res = 1
        while self.stk and self.stk[-1][0] <= price:
            _, t = self.stk.pop()
            res += t
        self.stk.append([price, res])
        return res

# Your StockSpanner object will be instantiated and called as such:
# obj = StockSpanner()
# param_1 = obj.next(price)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class StockSpanner {
    private Deque<int[]> stk;

    public StockSpanner() {
        stk = new ArrayDeque<>();
    }

    public int next(int price) {
        int res = 1;
        while (!stk.isEmpty() && stk.peek()[0] <= price) {
            int[] t = stk.pop();
            res += t[1];
        }
        stk.push(new int[]{price, res});
        return res;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
```

### **TypeScript**

```ts
class StockSpanner {
    stack: number[][];
    constructor() {
        this.stack = [];
    }

    next(price: number): number {
        let ans = 1;
        while (this.stack.length > 0 && this.stack[0][0] <= price) {
            let [p, c] = this.stack.shift();
            ans += c;
        }
        this.stack.unshift([price, ans]);
        return ans;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * var obj = new StockSpanner()
 * var param_1 = obj.next(price)
 */
```

### **C++**

```cpp
class StockSpanner {
public:
    stack<pair<int, int>> stk;

    StockSpanner() {
    }

    int next(int price) {
        int res = 1;
        while (!stk.empty() && stk.top().first <= price) {
            pair<int, int> t = stk.top();
            stk.pop();
            res += t.second;
        }
        stk.push({price, res});
        return res;
    }
};

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner* obj = new StockSpanner();
 * int param_1 = obj->next(price);
 */
```

### **...**

```

```

<!-- tabs:end -->
