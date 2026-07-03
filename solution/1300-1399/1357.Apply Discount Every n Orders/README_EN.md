---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1357.Apply%20Discount%20Every%20n%20Orders/README_EN.md
rating: 1429
source: Biweekly Contest 20 Q2
tags:
    - Design
    - Array
    - Hash Table
---

<!-- problem:start -->

# [1357. Apply Discount Every n Orders](https://leetcode.com/problems/apply-discount-every-n-orders)

[中文文档](/solution/1300-1399/1357.Apply%20Discount%20Every%20n%20Orders/README.md)

## Description

<!-- description:start -->

<p>There is a supermarket that is frequented by many customers. The products sold at the supermarket are represented as two parallel integer arrays <code>products</code> and <code>prices</code>, where the <code>i<sup>th</sup></code> product has an ID of <code>products[i]</code> and a price of <code>prices[i]</code>.</p>

<p>When a customer is paying, their bill is represented as two parallel integer arrays <code>product</code> and <code>amount</code>, where the <code>j<sup>th</sup></code> product they purchased has an ID of <code>product[j]</code>, and <code>amount[j]</code> is how much of the product they bought. Their subtotal is calculated as the sum of each <code>amount[j] * (price of the j<sup>th</sup> product)</code>.</p>

<p>The supermarket decided to have a sale. Every <code>n<sup>th</sup></code> customer paying for their groceries will be given a <strong>percentage discount</strong>. The discount amount is given by <code>discount</code>, where they will be given <code>discount</code> percent off their subtotal. More formally, if their subtotal is <code>bill</code>, then they would actually pay <code>bill * ((100 - discount) / 100)</code>.</p>

<p>Implement the <code>Cashier</code> class:</p>

<ul>
	<li><code>Cashier(int n, int discount, int[] products, int[] prices)</code> Initializes the object with <code>n</code>, the <code>discount</code>, and the <code>products</code> and their <code>prices</code>.</li>
	<li><code>double getBill(int[] product, int[] amount)</code> Returns the final total of the bill with the discount applied (if any). Answers within <code>10<sup>-5</sup></code> of the actual value will be accepted.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;Cashier&quot;,&quot;getBill&quot;,&quot;getBill&quot;,&quot;getBill&quot;,&quot;getBill&quot;,&quot;getBill&quot;,&quot;getBill&quot;,&quot;getBill&quot;]
[[3,50,[1,2,3,4,5,6,7],[100,200,300,400,300,200,100]],[[1,2],[1,2]],[[3,7],[10,10]],[[1,2,3,4,5,6,7],[1,1,1,1,1,1,1]],[[4],[10]],[[7,3],[10,10]],[[7,5,3,1,6,4,2],[10,10,10,9,9,9,7]],[[2,3,5],[5,3,2]]]
<strong>Output</strong>
[null,500.0,4000.0,800.0,4000.0,4000.0,7350.0,2500.0]
<strong>Explanation</strong>
Cashier cashier = new Cashier(3,50,[1,2,3,4,5,6,7],[100,200,300,400,300,200,100]);
cashier.getBill([1,2],[1,2]);                        // return 500.0. 1<sup>st</sup> customer, no discount.
                                                     // bill = 1 * 100 + 2 * 200 = 500.
cashier.getBill([3,7],[10,10]);                      // return 4000.0. 2<sup>nd</sup> customer, no discount.
                                                     // bill = 10 * 300 + 10 * 100 = 4000.
cashier.getBill([1,2,3,4,5,6,7],[1,1,1,1,1,1,1]);    // return 800.0. 3<sup>rd</sup> customer, 50% discount.
                                                     // Original bill = 1600
                                                     // Actual bill = 1600 * ((100 - 50) / 100) = 800.
cashier.getBill([4],[10]);                           // return 4000.0. 4<sup>th</sup> customer, no discount.
cashier.getBill([7,3],[10,10]);                      // return 4000.0. 5<sup>th</sup> customer, no discount.
cashier.getBill([7,5,3,1,6,4,2],[10,10,10,9,9,9,7]); // return 7350.0. 6<sup>th</sup> customer, 50% discount.
                                                     // Original bill = 14700, but with
                                                     // Actual bill = 14700 * ((100 - 50) / 100) = 7350.
cashier.getBill([2,3,5],[5,3,2]);                    // return 2500.0.  7<sup>th</sup> customer, no discount.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= discount &lt;= 100</code></li>
	<li><code>1 &lt;= products.length &lt;= 200</code></li>
	<li><code>prices.length == products.length</code></li>
	<li><code>1 &lt;= products[i] &lt;= 200</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 1000</code></li>
	<li>The elements in <code>products</code> are <strong>unique</strong>.</li>
	<li><code>1 &lt;= product.length &lt;= products.length</code></li>
	<li><code>amount.length == product.length</code></li>
	<li><code>product[j]</code> exists in <code>products</code>.</li>
	<li><code>1 &lt;= amount[j] &lt;= 1000</code></li>
	<li>The elements of <code>product</code> are <strong>unique</strong>.</li>
	<li>At most <code>1000</code> calls will be made to <code>getBill</code>.</li>
	<li>Answers within <code>10<sup>-5</sup></code> of the actual value will be accepted.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Simulation

We use a hash table $d$ to store the product ID and unit price, mapping each entry in `products` to the corresponding price in `prices` during initialization.

We also maintain a customer counter $i$, initialized to $0$.

For each `getBill` call:

1. Increment the counter and take modulo: $i = (i + 1) \bmod n$, indicating which customer is checking out;
2. Traverse the purchased product IDs and quantities, calculating the total bill $x = \sum_j d[\textit{product}[j]] \times \textit{amount}[j]$;
3. If $i = 0$, the current customer is the $n$-th customer and the entire bill receives a discount; return $x - \dfrac{\textit{discount} \times x}{100}$. Otherwise, return $x$ directly.

The time complexity of initialization is $O(n)$, where $n$ is the number of product types. Each `getBill` call takes $O(m)$ time, where $m$ is the number of product types in the current purchase. The space complexity is $O(n)$.

<!-- tabs:start -->

#### Python3

```python
class Cashier:

    def __init__(self, n: int, discount: int, products: List[int], prices: List[int]):
        self.i = 0
        self.n = n
        self.discount = discount
        self.d = {a: b for a, b in zip(products, prices)}

    def getBill(self, product: List[int], amount: List[int]) -> float:
        self.i = (self.i + 1) % self.n
        x = sum(self.d[a] * b for a, b in zip(product, amount))
        if self.i == 0:
            return x - (self.discount * x) / 100
        return x


# Your Cashier object will be instantiated and called as such:
# obj = Cashier(n, discount, products, prices)
# param_1 = obj.getBill(product,amount)
```

#### Java

```java
class Cashier {
    private int i;
    private int n;
    private int discount;
    private Map<Integer, Integer> d;

    public Cashier(int n, int discount, int[] products, int[] prices) {
        this.i = 0;
        this.n = n;
        this.discount = discount;
        this.d = new HashMap<>();
        for (int j = 0; j < products.length; j++) {
            this.d.put(products[j], prices[j]);
        }
    }

    public double getBill(int[] product, int[] amount) {
        this.i = (this.i + 1) % this.n;
        double x = 0;
        for (int j = 0; j < product.length; j++) {
            x += this.d.get(product[j]) * amount[j];
        }
        if (this.i == 0) {
            return x - (this.discount * x) / 100.0;
        }
        return x;
    }
}

/**
 * Your Cashier object will be instantiated and called as such:
 * Cashier obj = new Cashier(n, discount, products, prices);
 * double param_1 = obj.getBill(product,amount);
 */
```

#### C++

```cpp
class Cashier {
public:
    int i;
    int n;
    int discount;
    unordered_map<int, int> d;

    Cashier(int n, int discount, vector<int>& products, vector<int>& prices) {
        this->i = 0;
        this->n = n;
        this->discount = discount;
        for (int j = 0; j < products.size(); j++) {
            d[products[j]] = prices[j];
        }
    }

    double getBill(vector<int> product, vector<int> amount) {
        i = (i + 1) % n;
        double x = 0;
        for (int j = 0; j < product.size(); j++) {
            x += d[product[j]] * amount[j];
        }
        if (i == 0) {
            return x - (discount * x) / 100.0;
        }
        return x;
    }
};

/**
 * Your Cashier object will be instantiated and called as such:
 * Cashier* obj = new Cashier(n, discount, products, prices);
 * double param_1 = obj->getBill(product,amount);
 */
```

#### Go

```go
type Cashier struct {
	i        int
	n        int
	discount int
	d        map[int]int
}

func Constructor(n int, discount int, products []int, prices []int) Cashier {
	d := make(map[int]int)
	for i := 0; i < len(products); i++ {
		d[products[i]] = prices[i]
	}
	return Cashier{i: 0, n: n, discount: discount, d: d}
}

func (this *Cashier) GetBill(product []int, amount []int) float64 {
	this.i = (this.i + 1) % this.n
	x := 0
	for i := 0; i < len(product); i++ {
		x += this.d[product[i]] * amount[i]
	}
	if this.i == 0 {
		return float64(x) - float64(this.discount)*float64(x)/100.0
	}
	return float64(x)
}

/**
 * Your Cashier object will be instantiated and called as such:
 * obj := Constructor(n, discount, products, prices);
 * param_1 := obj.GetBill(product,amount);
 */
```

#### TypeScript

```ts
class Cashier {
    i: number;
    n: number;
    discount: number;
    d: Map<number, number>;

    constructor(n: number, discount: number, products: number[], prices: number[]) {
        this.i = 0;
        this.n = n;
        this.discount = discount;
        this.d = new Map();
        for (let j = 0; j < products.length; j++) {
            this.d.set(products[j], prices[j]);
        }
    }

    getBill(product: number[], amount: number[]): number {
        this.i = (this.i + 1) % this.n;
        let x = 0;
        for (let j = 0; j < product.length; j++) {
            x += (this.d.get(product[j]) || 0) * amount[j];
        }
        if (this.i === 0) {
            return x - (this.discount * x) / 100;
        }
        return x;
    }
}

/**
 * Your Cashier object will be instantiated and called as such:
 * var obj = new Cashier(n, discount, products, prices)
 * var param_1 = obj.getBill(product,amount)
 */
```

#### Rust

```rust
use std::cell::Cell;
use std::collections::HashMap;

struct Cashier {
    i: Cell<i32>,
    n: i32,
    discount: i32,
    d: HashMap<i32, i32>,
}

impl Cashier {
    fn new(n: i32, discount: i32, products: Vec<i32>, prices: Vec<i32>) -> Self {
        let mut d = HashMap::new();
        for i in 0..products.len() {
            d.insert(products[i], prices[i]);
        }
        Cashier {
            i: Cell::new(0),
            n,
            discount,
            d,
        }
    }

    fn get_bill(&self, product: Vec<i32>, amount: Vec<i32>) -> f64 {
        let mut x = 0i64;
        let mut i = self.i.get();
        i = (i + 1) % self.n;
        self.i.set(i);

        for j in 0..product.len() {
            x += (self.d[&product[j]] as i64) * (amount[j] as i64);
        }

        if i == 0 {
            return x as f64 - (self.discount as f64) * (x as f64) / 100.0;
        }
        x as f64
    }
}

// Your Cashier object will be instantiated and called as such:
// let obj = Cashier::new(n, discount, products, prices);
// let ret_1: f64 = obj.get_bill(product, amount);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
