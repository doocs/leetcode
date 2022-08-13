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
from sortedcontainers import SortedDict


class StockPrice:
    def __init__(self):
        self.last_ts = 0
        self.mp = {}
        self.counter = SortedDict()

    def update(self, timestamp: int, price: int) -> None:
        if timestamp in self.mp:
            old_price = self.mp[timestamp]
            self.counter[old_price] -= 1
            if self.counter[old_price] == 0:
                del self.counter[old_price]
        if price not in self.counter:
            self.counter[price] = 0
        self.counter[price] += 1
        self.mp[timestamp] = price
        self.last_ts = max(self.last_ts, timestamp)

    def current(self) -> int:
        return self.mp[self.last_ts]

    def maximum(self) -> int:
        return self.counter.keys()[-1]

    def minimum(self) -> int:
        return self.counter.keys()[0]


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
    private Map<Integer, Integer> mp = new HashMap<>();
    private TreeMap<Integer, Integer> counter = new TreeMap<>();

    public StockPrice() {

    }

    public void update(int timestamp, int price) {
        if (mp.containsKey(timestamp)) {
            int oldPrice = mp.get(timestamp);
            counter.put(oldPrice, counter.get(oldPrice) - 1);
            if (counter.get(oldPrice) == 0) {
                counter.remove(oldPrice);
            }
        }
        mp.put(timestamp, price);
        counter.put(price, counter.getOrDefault(price, 0) + 1);
        lastTs = Math.max(lastTs, timestamp);
    }

    public int current() {
        return mp.get(lastTs);
    }

    public int maximum() {
        return counter.lastKey();
    }

    public int minimum() {
        return counter.firstKey();
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
public:
    int lastTs;
    unordered_map<int, int> mp;
    map<int, int> counter;

    StockPrice() {
    }

    void update(int timestamp, int price) {
        if (mp.count(timestamp)) {
            int oldPrice = mp[timestamp];
            --counter[oldPrice];
            if (counter[oldPrice] == 0) counter.erase(oldPrice);
        }
        mp[timestamp] = price;
        ++counter[price];
        lastTs = max(lastTs, timestamp);
    }

    int current() {
        return mp[lastTs];
    }

    int maximum() {
        return counter.rbegin()->first;
    }

    int minimum() {
        return counter.begin()->first;
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

### **Go**

```go
type StockPrice struct {
	lastTs  int
	mp      map[int]int
	counter *redblacktree.Tree
}

func Constructor() StockPrice {
	return StockPrice{
		mp:      make(map[int]int),
		counter: redblacktree.NewWithIntComparator(),
	}
}

func (this *StockPrice) Update(timestamp int, price int) {
	if timestamp > this.lastTs {
		this.lastTs = timestamp
	}
	if old, ok := this.mp[timestamp]; ok {
		cnt := getInt(this.counter, old)
		if cnt == 1 {
			this.counter.Remove(old)
		} else {
			this.counter.Put(old, cnt-1)
		}
	}
	this.mp[timestamp] = price
	this.counter.Put(price, getInt(this.counter, price)+1)
}

func (this *StockPrice) Current() int {
	return this.mp[this.lastTs]
}

func (this *StockPrice) Maximum() int {
	return this.counter.Right().Key.(int)
}

func (this *StockPrice) Minimum() int {
	return this.counter.Left().Key.(int)
}

func getInt(rbt *redblacktree.Tree, key int) int {
	val, found := rbt.Get(key)
	if !found {
		return 0
	}
	return val.(int)
}

/**
 * Your StockPrice object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Update(timestamp,price);
 * param_2 := obj.Current();
 * param_3 := obj.Maximum();
 * param_4 := obj.Minimum();
 */
```

### **...**

```

```

<!-- tabs:end -->
