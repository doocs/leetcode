---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3822.Design%20Order%20Management%20System/README.md
---

<!-- problem:start -->

# [3822. Design Order Management System 🔒](https://leetcode.cn/problems/design-order-management-system)

[English Version](/solution/3800-3899/3822.Design%20Order%20Management%20System/README_EN.md)

## 题目描述

<!-- description:start -->

<p>You are asked to design a simple order management system for a trading platform.</p>

<p>Each order is associated with an <code>orderId</code>, an <code>orderType</code> (<code>&quot;buy&quot;</code> or <code>&quot;sell&quot;</code>), and a <code>price</code>.</p>

<p>An order is considered <strong>active</strong> unless it is canceled.</p>

<p>Implement the <code>OrderManagementSystem</code> class:</p>

<ul>
	<li><code>OrderManagementSystem()</code>: Initializes the order management system.</li>
	<li><code>void addOrder(int orderId, string orderType, int price)</code>: Adds a new <strong>active</strong> order with the given attributes. It is <strong>guaranteed</strong> that <code>orderId</code> is unique.</li>
	<li><code>void modifyOrder(int orderId, int newPrice)</code>: Modifies the <strong>price</strong> of an existing order. It is <strong>guaranteed</strong> that the order exists and is <em>active</em>.</li>
	<li><code>void cancelOrder(int orderId)</code>: Cancels an existing order. It is <strong>guaranteed</strong> that the order exists and is <em>active</em>.</li>
	<li><code>vector&lt;int&gt; getOrdersAtPrice(string orderType, int price)</code>: Returns the <code>orderId</code>s of all <strong>active</strong> orders that match the given <code>orderType</code> and <code>price</code>. If no such orders exist, return an empty list.</li>
</ul>

<p><strong>Note:</strong> The order of returned <code>orderId</code>s does not matter.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong><br />
<span class="example-io">[&quot;OrderManagementSystem&quot;, &quot;addOrder&quot;, &quot;addOrder&quot;, &quot;addOrder&quot;, &quot;getOrdersAtPrice&quot;, &quot;modifyOrder&quot;, &quot;modifyOrder&quot;, &quot;getOrdersAtPrice&quot;, &quot;cancelOrder&quot;, &quot;cancelOrder&quot;, &quot;getOrdersAtPrice&quot;]<br />
[[], [1, &quot;buy&quot;, 1], [2, &quot;buy&quot;, 1], [3, &quot;sell&quot;, 2], [&quot;buy&quot;, 1], [1, 3], [2, 1], [&quot;buy&quot;, 1], [3], [2], [&quot;buy&quot;, 1]]</span></p>

<p><strong>Output:</strong><br />
<span class="example-io">[null, null, null, null, [2, 1], null, null, [2], null, null, []] </span></p>

<p><strong>Explanation</strong></p>
OrderManagementSystem orderManagementSystem = new OrderManagementSystem();<br />
orderManagementSystem.addOrder(1, &quot;buy&quot;, 1); // A buy order with ID 1 is added at price 1.<br />
orderManagementSystem.addOrder(2, &quot;buy&quot;, 1); // A buy order with ID 2 is added at price 1.<br />
orderManagementSystem.addOrder(3, &quot;sell&quot;, 2); // A sell order with ID 3 is added at price 2.<br />
orderManagementSystem.getOrdersAtPrice(&quot;buy&quot;, 1); // Both buy orders (IDs 1 and 2) are active at price 1, so the result is <code>[2, 1]</code>.<br />
orderManagementSystem.modifyOrder(1, 3); // Order 1 is updated: its price becomes 3.<br />
orderManagementSystem.modifyOrder(2, 1); // Order 2 is updated, but its price remains 1.<br />
orderManagementSystem.getOrdersAtPrice(&quot;buy&quot;, 1); // Only order 2 is still an active buy order at price 1, so the result is <code>[2]</code>.<br />
orderManagementSystem.cancelOrder(3); // The sell order with ID 3 is canceled and removed from active orders.<br />
orderManagementSystem.cancelOrder(2); // The buy order with ID 2 is canceled and removed from active orders.<br />
orderManagementSystem.getOrdersAtPrice(&quot;buy&quot;, 1); // There are no active buy orders left at price 1, so the result is <code>[]</code>.</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= orderId &lt;= 2000</code></li>
	<li><code>orderId</code> is <strong>unique</strong> across all orders.</li>
	<li><code>orderType</code> is either <code>&quot;buy&quot;</code> or <code>&quot;sell&quot;</code>.</li>
	<li><code>1 &lt;= price &lt;= 10<sup>9</sup></code></li>
	<li>The total number of calls to <code>addOrder</code>, <code>modifyOrder</code>, <code>cancelOrder</code>, and <code>getOrdersAtPrice</code> does not exceed <font face="monospace">2000</font>.</li>
	<li>For <code>modifyOrder</code> and <code>cancelOrder</code>, the specified <code>orderId</code> is <strong>guaranteed</strong> to exist and be <em>active</em>.</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们用一个哈希表 $\textit{orders}$ 来存储每个订单的类型和价格信息，键为订单 ID，值为一个二元组 $(\textit{orderType}, \textit{price})$。另外，我们用另一个哈希表 $\textit{t}$ 来存储每个 $(\textit{orderType}, \textit{price})$ 对应的订单 ID 列表，键为一个二元组 $(\textit{orderType}, \textit{price})$，值为订单 ID 列表。

调用 $\texttt{addOrder}$ 时，我们将订单信息添加到 $\textit{orders}$ 中，并将订单 ID 添加到 $\textit{t}$ 中对应的列表中。

调用 $\texttt{modifyOrder}$ 时，我们首先从 $\textit{orders}$ 中获取订单的类型和旧价格，然后更新订单的价格信息。接着，我们从 $\textit{t}$ 中对应的列表中移除订单 ID，并将其添加到新价格对应的列表中。

调用 $\texttt{cancelOrder}$ 时，我们从 $\textit{orders}$ 中获取订单的类型和价格信息，然后将订单 ID 从 $\textit{t}$ 中对应的列表中移除，并从 $\textit{orders}$ 中删除该订单。

调用 $\texttt{getOrdersAtPrice}$ 时，我们直接返回 $\textit{t}$ 中对应的订单 ID 列表。

以上操作中，添加和获取订单 ID 列表的时间复杂度为 $O(1)$，而移除订单 ID 列表的时间复杂度为 $O(n)$，其中 $n$ 是对应列表的长度。由于题目中订单总数不超过 $2000$，该方法在实际运行中效率足够高。空间复杂度为 $O(m)$，其中 $m$ 是订单总数。

<!-- tabs:start -->

#### Python3

```python
class OrderManagementSystem:

    def __init__(self):
        self.orders = {}
        self.t = defaultdict(list)

    def addOrder(self, orderId: int, orderType: str, price: int) -> None:
        self.orders[orderId] = (orderType, price)
        self.t[(orderType, price)].append(orderId)

    def modifyOrder(self, orderId: int, newPrice: int) -> None:
        orderType, price = self.orders[orderId]
        self.orders[orderId] = (orderType, newPrice)
        self.t[(orderType, price)].remove(orderId)
        self.t[(orderType, newPrice)].append(orderId)

    def cancelOrder(self, orderId: int) -> None:
        orderType, price = self.orders[orderId]
        del self.orders[orderId]
        self.t[(orderType, price)].remove(orderId)

    def getOrdersAtPrice(self, orderType: str, price: int) -> List[int]:
        return self.t[(orderType, price)]


# Your OrderManagementSystem object will be instantiated and called as such:
# obj = OrderManagementSystem()
# obj.addOrder(orderId,orderType,price)
# obj.modifyOrder(orderId,newPrice)
# obj.cancelOrder(orderId)
# param_4 = obj.getOrdersAtPrice(orderType,price)
```

#### Java

```java
class OrderManagementSystem {

    private record Key(String orderType, int price) {
    }

    private final Map<Integer, String> orderTypeMap;
    private final Map<Integer, Integer> priceMap;
    private final Map<Key, List<Integer>> t;

    public OrderManagementSystem() {
        orderTypeMap = new HashMap<>();
        priceMap = new HashMap<>();
        t = new HashMap<>();
    }

    public void addOrder(int orderId, String orderType, int price) {
        orderTypeMap.put(orderId, orderType);
        priceMap.put(orderId, price);
        var key = new Key(orderType, price);
        t.computeIfAbsent(key, _ -> new ArrayList<>()).add(orderId);
    }

    public void modifyOrder(int orderId, int newPrice) {
        var orderType = orderTypeMap.get(orderId);
        var oldPrice = priceMap.get(orderId);
        priceMap.put(orderId, newPrice);
        t.get(new Key(orderType, oldPrice)).remove((Integer) orderId);
        t.computeIfAbsent(new Key(orderType, newPrice), _ -> new ArrayList<>()).add(orderId);
    }

    public void cancelOrder(int orderId) {
        var orderType = orderTypeMap.remove(orderId);
        var price = priceMap.remove(orderId);
        t.get(new Key(orderType, price)).remove((Integer) orderId);
    }

    public int[] getOrdersAtPrice(String orderType, int price) {
        var list = t.getOrDefault(new Key(orderType, price), List.of());
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}

/**
 * Your OrderManagementSystem object will be instantiated and called as such:
 * OrderManagementSystem obj = new OrderManagementSystem();
 * obj.addOrder(orderId,orderType,price);
 * obj.modifyOrder(orderId,newPrice);
 * obj.cancelOrder(orderId);
 * int[] param_4 = obj.getOrdersAtPrice(orderType,price);
 */
```

#### C++

```cpp
class OrderManagementSystem {
    using Key = pair<string, int>;

    struct KeyHash {
        size_t operator()(const Key& k) const {
            return hash<string>()(k.first) ^ (hash<int>()(k.second) << 1);
        }
    };

    unordered_map<int, string> orderTypeMap;
    unordered_map<int, int> priceMap;
    unordered_map<Key, vector<int>, KeyHash> t;

public:
    OrderManagementSystem() {}

    void addOrder(int orderId, string orderType, int price) {
        orderTypeMap[orderId] = orderType;
        priceMap[orderId] = price;
        t[{orderType, price}].push_back(orderId);
    }

    void modifyOrder(int orderId, int newPrice) {
        string orderType = orderTypeMap[orderId];
        int oldPrice = priceMap[orderId];
        priceMap[orderId] = newPrice;

        auto& oldList = t[{orderType, oldPrice}];
        oldList.erase(find(oldList.begin(), oldList.end(), orderId));

        t[{orderType, newPrice}].push_back(orderId);
    }

    void cancelOrder(int orderId) {
        string orderType = orderTypeMap[orderId];
        int price = priceMap[orderId];

        orderTypeMap.erase(orderId);
        priceMap.erase(orderId);

        auto& list = t[{orderType, price}];
        list.erase(find(list.begin(), list.end(), orderId));
    }

    vector<int> getOrdersAtPrice(string orderType, int price) {
        auto it = t.find({orderType, price});
        if (it == t.end()) return {};
        return it->second;
    }
};

/**
 * Your OrderManagementSystem object will be instantiated and called as such:
 * OrderManagementSystem* obj = new OrderManagementSystem();
 * obj->addOrder(orderId,orderType,price);
 * obj->modifyOrder(orderId,newPrice);
 * obj->cancelOrder(orderId);
 * vector<int> param_4 = obj->getOrdersAtPrice(orderType,price);
 */
```

#### Go

```go
type Key struct {
	orderType string
	price     int
}

type OrderManagementSystem struct {
	orderTypeMap map[int]string
	priceMap     map[int]int
	t            map[Key][]int
}

func Constructor() OrderManagementSystem {
	return OrderManagementSystem{
		orderTypeMap: make(map[int]string),
		priceMap:     make(map[int]int),
		t:            make(map[Key][]int),
	}
}

func (this *OrderManagementSystem) AddOrder(orderId int, orderType string, price int) {
	this.orderTypeMap[orderId] = orderType
	this.priceMap[orderId] = price
	key := Key{orderType, price}
	this.t[key] = append(this.t[key], orderId)
}

func (this *OrderManagementSystem) ModifyOrder(orderId int, newPrice int) {
	orderType := this.orderTypeMap[orderId]
	oldPrice := this.priceMap[orderId]
	this.priceMap[orderId] = newPrice

	oldKey := Key{orderType, oldPrice}
	oldList := this.t[oldKey]
	for i, v := range oldList {
		if v == orderId {
			this.t[oldKey] = append(oldList[:i], oldList[i+1:]...)
			break
		}
	}

	newKey := Key{orderType, newPrice}
	this.t[newKey] = append(this.t[newKey], orderId)
}

func (this *OrderManagementSystem) CancelOrder(orderId int) {
	orderType := this.orderTypeMap[orderId]
	price := this.priceMap[orderId]

	delete(this.orderTypeMap, orderId)
	delete(this.priceMap, orderId)

	key := Key{orderType, price}
	list := this.t[key]
	for i, v := range list {
		if v == orderId {
			this.t[key] = append(list[:i], list[i+1:]...)
			break
		}
	}
}

func (this *OrderManagementSystem) GetOrdersAtPrice(orderType string, price int) []int {
	key := Key{orderType, price}
	return this.t[key]
}

/**
 * Your OrderManagementSystem object will be instantiated and called as such:
 * obj := Constructor();
 * obj.AddOrder(orderId,orderType,price);
 * obj.ModifyOrder(orderId,newPrice);
 * obj.CancelOrder(orderId);
 * param_4 := obj.GetOrdersAtPrice(orderType,price);
 */
```

#### TypeScript

```ts
class OrderManagementSystem {
    private orderTypeMap: Map<number, string>;
    private priceMap: Map<number, number>;
    private t: Map<string, number[]>;

    constructor() {
        this.orderTypeMap = new Map();
        this.priceMap = new Map();
        this.t = new Map();
    }

    private key(orderType: string, price: number): string {
        return `${orderType}#${price}`;
    }

    addOrder(orderId: number, orderType: string, price: number): void {
        this.orderTypeMap.set(orderId, orderType);
        this.priceMap.set(orderId, price);

        const k = this.key(orderType, price);
        if (!this.t.has(k)) {
            this.t.set(k, []);
        }
        this.t.get(k)!.push(orderId);
    }

    modifyOrder(orderId: number, newPrice: number): void {
        const orderType = this.orderTypeMap.get(orderId)!;
        const oldPrice = this.priceMap.get(orderId)!;

        this.priceMap.set(orderId, newPrice);

        const oldKey = this.key(orderType, oldPrice);
        const oldList = this.t.get(oldKey)!;
        const idx = oldList.indexOf(orderId);
        if (idx !== -1) {
            oldList.splice(idx, 1);
        }

        const newKey = this.key(orderType, newPrice);
        if (!this.t.has(newKey)) {
            this.t.set(newKey, []);
        }
        this.t.get(newKey)!.push(orderId);
    }

    cancelOrder(orderId: number): void {
        const orderType = this.orderTypeMap.get(orderId)!;
        const price = this.priceMap.get(orderId)!;

        this.orderTypeMap.delete(orderId);
        this.priceMap.delete(orderId);

        const k = this.key(orderType, price);
        const list = this.t.get(k)!;
        const idx = list.indexOf(orderId);
        if (idx !== -1) {
            list.splice(idx, 1);
        }
    }

    getOrdersAtPrice(orderType: string, price: number): number[] {
        return this.t.get(this.key(orderType, price)) ?? [];
    }
}

/**
 * Your OrderManagementSystem object will be instantiated and called as such:
 * var obj = new OrderManagementSystem()
 * obj.addOrder(orderId,orderType,price)
 * obj.modifyOrder(orderId,newPrice)
 * obj.cancelOrder(orderId)
 * var param_4 = obj.getOrdersAtPrice(orderType,price)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
