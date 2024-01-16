# [901. Online Stock Span](https://leetcode.com/problems/online-stock-span)

[中文文档](/solution/0900-0999/0901.Online%20Stock%20Span/README.md)

## Description

<p>Design an algorithm that collects daily price quotes for some stock and returns <strong>the span</strong> of that stock&#39;s price for the current day.</p>

<p>The <strong>span</strong> of the stock&#39;s price in one day is the maximum number of consecutive days (starting from that day and going backward) for which the stock price was less than or equal to the price of that day.</p>

<ul>
	<li>For example, if the prices of the stock in the last four days is <code>[7,2,1,2]</code> and the price of the stock today is <code>2</code>, then the span of today is <code>4</code> because starting from today, the price of the stock was less than or equal <code>2</code> for <code>4</code> consecutive days.</li>
	<li>Also, if the prices of the stock in the last four days is <code>[7,34,1,2]</code> and the price of the stock today is <code>8</code>, then the span of today is <code>3</code> because starting from today, the price of the stock was less than or equal <code>8</code> for <code>3</code> consecutive days.</li>
</ul>

<p>Implement the <code>StockSpanner</code> class:</p>

<ul>
	<li><code>StockSpanner()</code> Initializes the object of the class.</li>
	<li><code>int next(int price)</code> Returns the <strong>span</strong> of the stock&#39;s price given that today&#39;s price is <code>price</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

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

### Solution 1: Monotonic Stack

Based on the problem description, we know that for the current day's price $price$, we start from this price and look backwards to find the first price that is larger than this price. The difference in indices $cnt$ between these two prices is the span of the current day's price.

This is actually a classic monotonic stack model, where we find the first element larger than the current element on the left.

We maintain a stack where the prices from the bottom to the top of the stack are monotonically decreasing. Each element in the stack is a $(price, cnt)$ data pair, where $price$ represents the price, and $cnt$ represents the span of the current price.

When the price $price$ appears, we compare it with the top element of the stack. If the price of the top element of the stack is less than or equal to $price$, we add the span $cnt$ of the current day's price to the span of the top element of the stack, and then pop the top element of the stack. This continues until the price of the top element of the stack is greater than $price$, or the stack is empty.

Finally, we push $(price, cnt)$ onto the stack and return $cnt$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of times `next(price)` is called.

<!-- tabs:start -->

```python
class StockSpanner:
    def __init__(self):
        self.stk = []

    def next(self, price: int) -> int:
        cnt = 1
        while self.stk and self.stk[-1][0] <= price:
            cnt += self.stk.pop()[1]
        self.stk.append((price, cnt))
        return cnt


# Your StockSpanner object will be instantiated and called as such:
# obj = StockSpanner()
# param_1 = obj.next(price)
```

```java
class StockSpanner {
    private Deque<int[]> stk = new ArrayDeque<>();

    public StockSpanner() {
    }

    public int next(int price) {
        int cnt = 1;
        while (!stk.isEmpty() && stk.peek()[0] <= price) {
            cnt += stk.pop()[1];
        }
        stk.push(new int[] {price, cnt});
        return cnt;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
```

```cpp
class StockSpanner {
public:
    StockSpanner() {
    }

    int next(int price) {
        int cnt = 1;
        while (!stk.empty() && stk.top().first <= price) {
            cnt += stk.top().second;
            stk.pop();
        }
        stk.emplace(price, cnt);
        return cnt;
    }

private:
    stack<pair<int, int>> stk;
};

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner* obj = new StockSpanner();
 * int param_1 = obj->next(price);
 */
```

```go
type StockSpanner struct {
	stk []pair
}

func Constructor() StockSpanner {
	return StockSpanner{[]pair{}}
}

func (this *StockSpanner) Next(price int) int {
	cnt := 1
	for len(this.stk) > 0 && this.stk[len(this.stk)-1].price <= price {
		cnt += this.stk[len(this.stk)-1].cnt
		this.stk = this.stk[:len(this.stk)-1]
	}
	this.stk = append(this.stk, pair{price, cnt})
	return cnt
}

type pair struct{ price, cnt int }

/**
 * Your StockSpanner object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Next(price);
 */
```

```ts
class StockSpanner {
    private stk: number[][];

    constructor() {
        this.stk = [];
    }

    next(price: number): number {
        let cnt = 1;
        while (this.stk.length && this.stk.at(-1)[0] <= price) {
            cnt += this.stk.pop()[1];
        }
        this.stk.push([price, cnt]);
        return cnt;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * var obj = new StockSpanner()
 * var param_1 = obj.next(price)
 */
```

```rust
use std::collections::VecDeque;
struct StockSpanner {
    stk: VecDeque<(i32, i32)>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl StockSpanner {
    fn new() -> Self {
        Self {
            stk: vec![(i32::MAX, -1)].into_iter().collect(),
        }
    }

    fn next(&mut self, price: i32) -> i32 {
        let mut cnt = 1;
        while self.stk.back().unwrap().0 <= price {
            cnt += self.stk.pop_back().unwrap().1;
        }
        self.stk.push_back((price, cnt));
        cnt
    }
}/**
 * Your StockSpanner object will be instantiated and called as such:
 * let obj = StockSpanner::new();
 * let ret_1: i32 = obj.next(price);
 */
```

<!-- tabs:end -->

<!-- end -->
