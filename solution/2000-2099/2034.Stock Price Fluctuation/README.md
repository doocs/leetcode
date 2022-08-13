# [2034. 股票价格波动](https://leetcode.cn/problems/stock-price-fluctuation)

[English Version](/solution/2000-2099/2034.Stock%20Price%20Fluctuation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一支股票价格的数据流。数据流中每一条记录包含一个 <strong>时间戳</strong>&nbsp;和该时间点股票对应的 <strong>价格</strong>&nbsp;。</p>

<p>不巧的是，由于股票市场内在的波动性，股票价格记录可能不是按时间顺序到来的。某些情况下，有的记录可能是错的。如果两个有相同时间戳的记录出现在数据流中，前一条记录视为错误记录，后出现的记录 <b>更正</b>&nbsp;前一条错误的记录。</p>

<p>请你设计一个算法，实现：</p>

<ul>
	<li><strong>更新 </strong>股票在某一时间戳的股票价格，如果有之前同一时间戳的价格，这一操作将&nbsp;<strong>更正</strong>&nbsp;之前的错误价格。</li>
	<li>找到当前记录里 <b>最新股票价格</b>&nbsp;。<strong>最新股票价格</strong>&nbsp;定义为时间戳最晚的股票价格。</li>
	<li>找到当前记录里股票的 <strong>最高价格</strong>&nbsp;。</li>
	<li>找到当前记录里股票的 <strong>最低价格</strong>&nbsp;。</li>
</ul>

<p>请你实现&nbsp;<code>StockPrice</code>&nbsp;类：</p>

<ul>
	<li><code>StockPrice()</code>&nbsp;初始化对象，当前无股票价格记录。</li>
	<li><code>void update(int timestamp, int price)</code>&nbsp;在时间点 <code>timestamp</code>&nbsp;更新股票价格为 <code>price</code>&nbsp;。</li>
	<li><code>int current()</code>&nbsp;返回股票 <strong>最新价格</strong>&nbsp;。</li>
	<li><code>int maximum()</code>&nbsp;返回股票 <strong>最高价格</strong>&nbsp;。</li>
	<li><code>int minimum()</code>&nbsp;返回股票 <strong>最低价格</strong>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>
["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
[[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
<strong>输出：</strong>
[null, null, null, 5, 10, null, 5, null, 2]

<strong>解释：</strong>
StockPrice stockPrice = new StockPrice();
stockPrice.update(1, 10); // 时间戳为 [1] ，对应的股票价格为 [10] 。
stockPrice.update(2, 5);  // 时间戳为 [1,2] ，对应的股票价格为 [10,5] 。
stockPrice.current();     // 返回 5 ，最新时间戳为 2 ，对应价格为 5 。
stockPrice.maximum();     // 返回 10 ，最高价格的时间戳为 1 ，价格为 10 。
stockPrice.update(1, 3);  // 之前时间戳为 1 的价格错误，价格更新为 3 。
                          // 时间戳为 [1,2] ，对应股票价格为 [3,5] 。
stockPrice.maximum();     // 返回 5 ，更正后最高价格为 5 。
stockPrice.update(4, 2);  // 时间戳为 [1,2,4] ，对应价格为 [3,5,2] 。
stockPrice.minimum();     // 返回 2 ，最低价格时间戳为 4 ，价格为 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= timestamp, price &lt;= 10<sup>9</sup></code></li>
	<li><code>update</code>，<code>current</code>，<code>maximum</code>&nbsp;和&nbsp;<code>minimum</code>&nbsp;<strong>总</strong> 调用次数不超过&nbsp;<code>10<sup>5</sup></code>&nbsp;。</li>
	<li><code>current</code>，<code>maximum</code>&nbsp;和&nbsp;<code>minimum</code>&nbsp;被调用时，<code>update</code>&nbsp;操作 <strong>至少</strong>&nbsp;已经被调用过 <strong>一次</strong>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
