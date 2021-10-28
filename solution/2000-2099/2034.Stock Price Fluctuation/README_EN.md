# [2034. Stock Price Fluctuation](https://leetcode.com/problems/stock-price-fluctuation)

[中文文档](/solution/2000-2099/2034.Stock%20Price%20Fluctuation/README.md)

## Description

<p>You are given a stream of <strong>records</strong> about a particular stock. Each record contains a <strong>timestamp</strong> and the corresponding <strong>price</strong> of the stock at that timestamp.</p>

<p>Unfortunately due to the volatile nature of the stock market, the records do not come in order. Even worse, some records may be incorrect. Another record with the same timestamp may appear later in the stream <strong>correcting</strong> the price of the previous wrong record.</p>

<p>Design an algorithm that:</p>

<ul>
	<li><strong>Updates</strong> the price of the stock at a particular timestamp, <strong>correcting</strong> the price from any previous records at the timestamp.</li>
	<li>Finds the <strong>latest price</strong> of the stock based on the current records. The <strong>latest price</strong> is the price at the latest timestamp recorded.</li>
	<li>Finds the <strong>maximum price</strong> the stock has been based on the current records.</li>
	<li>Finds the <strong>minimum price</strong> the stock has been based on the current records.</li>
</ul>

<p>Implement the <code>StockPrice</code> class:</p>

<ul>
	<li><code>StockPrice()</code> Initializes the object with no price records.</li>
	<li><code>void update(int timestamp, int price)</code> Updates the <code>price</code> of the stock at the given <code>timestamp</code>.</li>
	<li><code>int current()</code> Returns the <strong>latest price</strong> of the stock.</li>
	<li><code>int maximum()</code> Returns the <strong>maximum price</strong> of the stock.</li>
	<li><code>int minimum()</code> Returns the <strong>minimum price</strong> of the stock.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;StockPrice&quot;, &quot;update&quot;, &quot;update&quot;, &quot;current&quot;, &quot;maximum&quot;, &quot;update&quot;, &quot;maximum&quot;, &quot;update&quot;, &quot;minimum&quot;]
[[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
<strong>Output</strong>
[null, null, null, 5, 10, null, 5, null, 2]

<strong>Explanation</strong>
StockPrice stockPrice = new StockPrice();
stockPrice.update(1, 10); // Timestamps are [1] with corresponding prices [10].
stockPrice.update(2, 5);  // Timestamps are [1,2] with corresponding prices [10,5].
stockPrice.current();     // return 5, the latest timestamp is 2 with the price being 5.
stockPrice.maximum();     // return 10, the maximum price is 10 at timestamp 1.
stockPrice.update(1, 3);  // The previous timestamp 1 had the wrong price, so it is updated to 3.
                          // Timestamps are [1,2] with corresponding prices [3,5].
stockPrice.maximum();     // return 5, the maximum price is 5 after the correction.
stockPrice.update(4, 2);  // Timestamps are [1,2,4] with corresponding prices [3,5,2].
stockPrice.minimum();     // return 2, the minimum price is 2 at timestamp 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= timestamp, price &lt;= 10<sup>9</sup></code></li>
	<li>At most <code>10<sup>5</sup></code> calls will be made <strong>in total</strong> to <code>update</code>, <code>current</code>, <code>maximum</code>, and <code>minimum</code>.</li>
	<li><code>current</code>, <code>maximum</code>, and <code>minimum</code> will be called <strong>only after</strong> <code>update</code> has been called <strong>at least once</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class StockPrice:

    def __init__(self):
        self.last_ts = 0
        self.mp = {}
        self.mi = []
        self.mx = []
        self.counter = collections.Counter()

    def update(self, timestamp: int, price: int) -> None:
        if timestamp in self.mp:
            old_price = self.mp[timestamp]
            self.counter[old_price] -= 1
            
        self.mp[timestamp] = price
        self.last_ts = max(self.last_ts, timestamp)
        self.counter[price] += 1
        heapq.heappush(self.mi, price)
        heapq.heappush(self.mx, -price)
        

    def current(self) -> int:
        return self.mp[self.last_ts]

    def maximum(self) -> int:
        while self.counter[-self.mx[0]] == 0:
            heapq.heappop(self.mx)
        return -self.mx[0]

    def minimum(self) -> int:
        while self.counter[self.mi[0]] == 0:
            heapq.heappop(self.mi)
        return self.mi[0]


# Your StockPrice object will be instantiated and called as such:
# obj = StockPrice()
# obj.update(timestamp,price)
# param_2 = obj.current()
# param_3 = obj.maximum()
# param_4 = obj.minimum()
```

### **Java**

```java
class StockPrice {
    private int lastTs;
    private PriorityQueue<Integer> mi = new PriorityQueue<>();
    private PriorityQueue<Integer> mx = new PriorityQueue<>(Collections.reverseOrder());
    private Map<Integer, Integer> mp = new HashMap<>();
    private Map<Integer, Integer> counter = new HashMap<>();

    public StockPrice() {

    }
    
    public void update(int timestamp, int price) {
        if (mp.containsKey(timestamp)) {
            int oldPrice = mp.get(timestamp);
            counter.put(oldPrice, counter.get(oldPrice) - 1);
        }
        mp.put(timestamp, price);
        lastTs = Math.max(lastTs, timestamp);
        counter.put(price, counter.getOrDefault(price, 0) + 1);
        mi.offer(price);
        mx.offer(price);
    }
    
    public int current() {
        return mp.get(lastTs);
    }
    
    public int maximum() {
        while (counter.getOrDefault(mx.peek(), 0) == 0) {
            mx.poll();
        }
        return mx.peek();
    }
    
    public int minimum() {
        while (counter.getOrDefault(mi.peek(), 0) == 0) {
            mi.poll();
        }
        return mi.peek();
    }
}

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */
```

### **C++**

```cpp
class StockPrice {
private:
    int lastTs;
    priority_queue<int> mx;
    priority_queue<int, vector<int>, greater<int>> mi;
    unordered_map<int, int> mp;
    unordered_map<int, int> counter;
public:
    StockPrice() {
        
    }
    
    void update(int timestamp, int price) {
        if (mp.find(timestamp) != mp.end())
        {
            int oldPrice = mp[timestamp];
            --counter[oldPrice];
        }
        mp[timestamp] = price;
        lastTs = max(lastTs, timestamp);
        ++counter[price];
        mi.push(price);
        mx.push(price);
    }
    
    int current() {
        return mp[lastTs];
    }
    
    int maximum() {
        while (!counter[mx.top()]) mx.pop();
        return mx.top();
    }
    
    int minimum() {
        while (!counter[mi.top()]) mi.pop();
        return mi.top();
    }
};

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice* obj = new StockPrice();
 * obj->update(timestamp,price);
 * int param_2 = obj->current();
 * int param_3 = obj->maximum();
 * int param_4 = obj->minimum();
 */
```

### **...**

```

```

<!-- tabs:end -->
