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
<p><strong class="example">Example 1:</strong></p>

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

**Solution 1: Hash Table + Ordered Set**

We define the following data structures or variables:

-   `d`: a hash table that stores the timestamp and the corresponding price;
-   `ls`: an ordered set that stores all prices;
-   `last`: the timestamp of the last update.

Then, we can perform the following operations:

-   `update(timestamp, price)`: update the price corresponding to the timestamp `timestamp` to `price`. If `timestamp` already exists, we need to first remove its corresponding price from the ordered set, and then update it to `price`. Otherwise, we directly update it to `price`. Then, we need to update `last` to `max(last, timestamp)`. The time complexity is O(log n).
-   `current()`: return the price corresponding to `last`. The time complexity is $O(1)$.
-   `maximum()`: return the maximum value in the ordered set. The time complexity is $O(\log n)$.
-   `minimum()`: return the minimum value in the ordered set. The time complexity is $O(\log n)$.

The space complexity is $O(n)$, where $n$ is the number of `update` operations.

<!-- tabs:start -->

### **Python3**

```python
from sortedcontainers import SortedList


class StockPrice:
    def __init__(self):
        self.d = {}
        self.ls = SortedList()
        self.last = 0

    def update(self, timestamp: int, price: int) -> None:
        if timestamp in self.d:
            self.ls.remove(self.d[timestamp])
        self.d[timestamp] = price
        self.ls.add(price)
        self.last = max(self.last, timestamp)

    def current(self) -> int:
        return self.d[self.last]

    def maximum(self) -> int:
        return self.ls[-1]

    def minimum(self) -> int:
        return self.ls[0]


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
    private Map<Integer, Integer> d = new HashMap<>();
    private TreeMap<Integer, Integer> ls = new TreeMap<>();
    private int last;

    public StockPrice() {

    }

    public void update(int timestamp, int price) {
        if (d.containsKey(timestamp)) {
            int old = d.get(timestamp);
            if (ls.merge(old, -1, Integer::sum) == 0) {
                ls.remove(old);
            }
        }
        d.put(timestamp, price);
        ls.merge(price, 1, Integer::sum);
        last = Math.max(last, timestamp);
    }

    public int current() {
        return d.get(last);
    }

    public int maximum() {
        return ls.lastKey();
    }

    public int minimum() {
        return ls.firstKey();
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
    StockPrice() {

    }

    void update(int timestamp, int price) {
        if (d.count(timestamp)) {
            ls.erase(ls.find(d[timestamp]));
        }
        d[timestamp] = price;
        ls.insert(price);
        last = max(last, timestamp);
    }

    int current() {
        return d[last];
    }

    int maximum() {
        return *ls.rbegin();
    }

    int minimum() {
        return *ls.begin();
    }

private:
    unordered_map<int, int> d;
    multiset<int> ls;
    int last = 0;
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
	d    map[int]int
	ls   *redblacktree.Tree
	last int
}

func Constructor() StockPrice {
	return StockPrice{
		d:    make(map[int]int),
		ls:   redblacktree.NewWithIntComparator(),
		last: 0,
	}
}

func (this *StockPrice) Update(timestamp int, price int) {
	merge := func(rbt *redblacktree.Tree, key, value int) {
		if v, ok := rbt.Get(key); ok {
			nxt := v.(int) + value
			if nxt == 0 {
				rbt.Remove(key)
			} else {
				rbt.Put(key, nxt)
			}
		} else {
			rbt.Put(key, value)
		}
	}
	if v, ok := this.d[timestamp]; ok {
		merge(this.ls, v, -1)
	}
	this.d[timestamp] = price
	merge(this.ls, price, 1)
	this.last = max(this.last, timestamp)
}

func (this *StockPrice) Current() int {
	return this.d[this.last]
}

func (this *StockPrice) Maximum() int {
	return this.ls.Right().Key.(int)
}

func (this *StockPrice) Minimum() int {
	return this.ls.Left().Key.(int)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
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
