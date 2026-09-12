---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3822.Design%20Order%20Management%20System/README.md
tags:
    - 设计
    - 哈希表
---

<!-- problem:start -->

# [3822. 设计订单管理系统 🔒](https://leetcode.cn/problems/design-order-management-system)

[English Version](/solution/3800-3899/3822.Design%20Order%20Management%20System/README_EN.md)

## 题目描述

<!-- description:start -->

<p>请设计一个简单的交易平台订单管理系统。</p>

<p>每个订单都有一个关联的&nbsp;<code>orderId</code>，一个&nbsp;<code>orderType</code>（<code>"buy"</code> 或&nbsp;<code>"sell"</code>）和一个&nbsp;<code>price</code>。</p>

<p>订单除非被取消，否则被视为 <strong>有效</strong>。</p>

<p>实现&nbsp;<code>OrderManagementSystem</code> 类：</p>

<ul>
	<li><code>OrderManagementSystem()</code>：初始化订单管理系统。</li>
	<li><code>void addOrder(int orderId, string orderType, int price)</code>：添加一个具有给定属性的新 <strong>有效</strong> 订单。<strong>保证</strong>&nbsp;<code>orderId</code>&nbsp;互不相同。</li>
	<li><code>void modifyOrder(int orderId, int newPrice)</code>：修改现有订单的 <strong>价格</strong>。<strong>保证</strong> 该订单存在且有效。</li>
	<li><code>void cancelOrder(int orderId)</code>：取消一个现有的订单。<strong>保证</strong> 该订单存在且有效。</li>
	<li><code>vector&lt;int&gt; getOrdersAtPrice(string orderType, int price)</code>：返回所有匹配给定&nbsp;<code>orderType</code>&nbsp;和&nbsp;<code>price</code> 的 <strong>有效</strong> 订单的 <code>orderId</code>。如果不存在此类订单，则返回空列表。</li>
</ul>

<p><b>注意：</b>可以按任意顺序返回&nbsp;<code>orderId</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><br />
<span class="example-io">["OrderManagementSystem", "addOrder", "addOrder", "addOrder", "getOrdersAtPrice", "modifyOrder", "modifyOrder", "getOrdersAtPrice", "cancelOrder", "cancelOrder", "getOrdersAtPrice"]<br />
[[], [1, "buy", 1], [2, "buy", 1], [3, "sell", 2], ["buy", 1], [1, 3], [2, 1], ["buy", 1], [3], [2], ["buy", 1]]</span></p>

<p><strong>输出：</strong><br />
<span class="example-io">[null, null, null, null, [2, 1], null, null, [2], null, null, []] </span></p>

<p><strong>解释：</strong></p>
OrderManagementSystem orderManagementSystem = new OrderManagementSystem();<br />
orderManagementSystem.addOrder(1, "buy", 1); // 一个 ID 为 1 的买入订单以价格 1 添加。<br />
orderManagementSystem.addOrder(2, "buy", 1); // 一个 ID 为 2 的买入订单以价格 1 添加。<br />
orderManagementSystem.addOrder(3, "sell", 2); // 一个 ID 为 3 的买入订单以价格 2 添加。<br />
orderManagementSystem.getOrdersAtPrice("buy", 1); // 两个买入订单（ID 1 和 2）在价格 1 是有效的，所以结果是&nbsp;<code>[2, 1]</code>。<br />
orderManagementSystem.modifyOrder(1, 3); // 更新订单 1：价格变为 3。<br />
orderManagementSystem.modifyOrder(2, 1); // 更新订单 2，但价格依然是 1。<br />
orderManagementSystem.getOrdersAtPrice("buy", 1); // 在价格 1 只有订单 2 还有效，所以结果是&nbsp;<code>[2]</code>。<br />
orderManagementSystem.cancelOrder(3); // ID为 3 的卖出订单已被取消并从有效订单中移除。<br />
orderManagementSystem.cancelOrder(2); // ID为 2 的卖出订单已被取消并从有效订单中移除。<br />
orderManagementSystem.getOrdersAtPrice("buy", 1); // 在价格 1 没有剩余的有效订单，所以结果是&nbsp;<code>[]</code>。</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= orderId &lt;= 2000</code></li>
	<li><code>orderId</code>&nbsp;在所有订单中是 <strong>互不相同</strong>&nbsp;的。</li>
	<li><code>orderType</code> 是&nbsp;<code>"buy"</code> 或&nbsp;<code>"sell"</code>。</li>
	<li><code>1 &lt;= price &lt;= 10<sup>9</sup></code></li>
	<li>调用 <code>addOrder</code>，<code>modifyOrder</code>，<code>cancelOrder</code>&nbsp;和&nbsp;<code>getOrdersAtPrice</code>&nbsp;的总次数不超过 2000。</li>
	<li>对于&nbsp;<code>modifyOrder</code> 或&nbsp;<code>cancelOrder</code>，指定的 <code>orderId</code> <strong>保证</strong> 存在且有效。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

<!-- thinking:start -->

> **思考**
>
> 订单总量不超过 $2000$，需按类型与价格取出仍有效的 $\textit{orderId}$。每次查询扫描全部订单虽可通过，但改价与取消后仍要快速定位。
>
> 查询键是 $(\textit{orderType},\textit{price})$，同时 $\textit{orderId}$ 必须能找到当前类型与价格以便修改、取消。
>
> 用 $\textit{orders}$ 存编号到 $(\textit{type},\textit{price})$，用 $\textit{t}$ 存该键下的编号列表。添加即双写；改价先从旧列表删除再插入新列表。
>
> 列表删除为线性，但 $n \le 2000$ 足够。查询直接返回对应列表。

<!-- thinking:end -->
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
