---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3822.Design%20Order%20Management%20System/README_EN.md
---

<!-- problem:start -->

# [3822. Design Order Management System 🔒](https://leetcode.com/problems/design-order-management-system)

[中文文档](/solution/3800-3899/3822.Design%20Order%20Management%20System/README.md)

## Description

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

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We use a hash table $\textit{orders}$ to store the type and price information of each order, where the key is the order ID and the value is a tuple $(\textit{orderType}, \textit{price})$. Additionally, we use another hash table $\textit{t}$ to store the list of order IDs corresponding to each $(\textit{orderType}, \textit{price})$, where the key is a tuple $(\textit{orderType}, \textit{price})$ and the value is the list of order IDs.

When calling $\texttt{addOrder}$, we add the order information to $\textit{orders}$ and append the order ID to the corresponding list in $\textit{t}$.

When calling $\texttt{modifyOrder}$, we first retrieve the order type and old price from $\textit{orders}$, then update the order's price information. Next, we remove the order ID from the corresponding list in $\textit{t}$ and add it to the list corresponding to the new price.

When calling $\texttt{cancelOrder}$, we retrieve the order type and price information from $\textit{orders}$, then remove the order ID from the corresponding list in $\textit{t}$ and delete the order from $\textit{orders}$.

When calling $\texttt{getOrdersAtPrice}$, we directly return the list of order IDs corresponding to the query in $\textit{t}$.

In the above operations, the time complexity for adding and retrieving the order ID list is $O(1)$, while the time complexity for removing an order ID from the list is $O(n)$, where $n$ is the length of the corresponding list. Since the total number of orders in the problem does not exceed $2000$, this method is efficient enough in practice. The space complexity is $O(m)$, where $m$ is the total number of orders.

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

#### Rust

```rust
use std::collections::HashMap;

struct OrderManagementSystem {
    orders: HashMap<i32, (String, i32)>,
    t: HashMap<(String, i32), Vec<i32>>,
}

impl OrderManagementSystem {

    fn new() -> Self {
        Self {
            orders: HashMap::new(),
            t: HashMap::new(),
        }
    }

    fn add_order(&mut self, order_id: i32, order_type: String, price: i32) {
        self.orders.insert(order_id, (order_type.clone(), price));
        self.t
            .entry((order_type, price))
            .or_insert_with(Vec::new)
            .push(order_id);
    }

    fn modify_order(&mut self, order_id: i32, new_price: i32) {
        if let Some((order_type, old_price)) = self.orders.get(&order_id).cloned() {
            self.orders.insert(order_id, (order_type.clone(), new_price));

            if let Some(v) = self.t.get_mut(&(order_type.clone(), old_price)) {
                if let Some(pos) = v.iter().position(|&x| x == order_id) {
                    v.remove(pos);
                }
            }

            self.t
                .entry((order_type, new_price))
                .or_insert_with(Vec::new)
                .push(order_id);
        }
    }

    fn cancel_order(&mut self, order_id: i32) {
        if let Some((order_type, price)) = self.orders.remove(&order_id) {
            if let Some(v) = self.t.get_mut(&(order_type, price)) {
                if let Some(pos) = v.iter().position(|&x| x == order_id) {
                    v.remove(pos);
                }
            }
        }
    }

    fn get_orders_at_price(&self, order_type: String, price: i32) -> Vec<i32> {
        self.t
            .get(&(order_type, price))
            .cloned()
            .unwrap_or_default()
    }
}

/**
 * Your OrderManagementSystem object will be instantiated and called as such:
 * let obj = OrderManagementSystem::new();
 * obj.add_order(orderId, orderType, price);
 * obj.modify_order(orderId, newPrice);
 * obj.cancel_order(orderId);
 * let ret_4: Vec<i32> = obj.get_orders_at_price(orderType, price);
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
