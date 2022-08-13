# [901. Online Stock Span](https://leetcode.com/problems/online-stock-span)

[中文文档](/solution/0900-0999/0901.Online%20Stock%20Span/README.md)

## Description

<p>Design an algorithm that collects daily price quotes for some stock and returns <strong>the span</strong> of that stock&#39;s price for the current day.</p>

<p>The <strong>span</strong> of the stock&#39;s price today is defined as the maximum number of consecutive days (starting from today and going backward) for which the stock price was less than or equal to today&#39;s price.</p>

<ul>
	<li>For example, if the price of a stock over the next <code>7</code> days were <code>[100,80,60,70,60,75,85]</code>, then the stock spans would be <code>[1,1,1,2,1,4,6]</code>.</li>
</ul>

<p>Implement the <code>StockSpanner</code> class:</p>

<ul>
	<li><code>StockSpanner()</code> Initializes the object of the class.</li>
	<li><code>int next(int price)</code> Returns the <strong>span</strong> of the stock&#39;s price given that today&#39;s price is <code>price</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;StockSpanner&quot;, &quot;next&quot;, &quot;next&quot;, &quot;next&quot;, &quot;next&quot;, &quot;next&quot;, &quot;next&quot;, &quot;next&quot;]
[[], [100], [80], [60], [70], [60], [75], [85]]
<strong>Output</strong>
[null, 1, 1, 1, 2, 1, 4, 6]

<strong>Explanation</strong>
StockSpanner stockSpanner = new StockSpanner();
stockSpanner.next(100); // return 1
stockSpanner.next(80);  // return 1
stockSpanner.next(60);  // return 1
stockSpanner.next(70);  // return 2
stockSpanner.next(60);  // return 1
stockSpanner.next(75);  // return 4, because the last 4 prices (including today&#39;s price of 75) were less than or equal to today&#39;s price.
stockSpanner.next(85);  // return 6
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= price &lt;= 10<sup>5</sup></code></li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>next</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Go**

```go
type StockSpanner struct {
	stk [][]int
}

func Constructor() StockSpanner {
	return StockSpanner{
		stk: make([][]int, 0),
	}
}

func (this *StockSpanner) Next(price int) int {
	res := 1
	for len(this.stk) > 0 && this.stk[len(this.stk)-1][0] <= price {
		t := this.stk[len(this.stk)-1]
		res += t[1]
		this.stk = this.stk[:len(this.stk)-1]
	}
	this.stk = append(this.stk, []int{price, res})
	return res
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Next(price);
 */
```

### **...**

```

```

<!-- tabs:end -->
