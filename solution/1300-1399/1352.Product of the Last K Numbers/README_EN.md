# [1352. Product of the Last K Numbers](https://leetcode.com/problems/product-of-the-last-k-numbers)

[中文文档](/solution/1300-1399/1352.Product%20of%20the%20Last%20K%20Numbers/README.md)

## Description

<p>Design an algorithm that accepts a stream of integers and retrieves the product of the last <code>k</code> integers of the stream.</p>

<p>Implement the <code>ProductOfNumbers</code> class:</p>

<ul>
	<li><code>ProductOfNumbers()</code> Initializes the object with an empty stream.</li>
	<li><code>void add(int num)</code> Appends the integer <code>num</code> to the stream.</li>
	<li><code>int getProduct(int k)</code> Returns the product of the last <code>k</code> numbers in the current list. You can assume that always the current list has at least <code>k</code> numbers.</li>
</ul>

<p>The test cases are generated so that, at any time, the product of any contiguous sequence of numbers will fit into a single 32-bit integer without overflowing.</p>

<p>&nbsp;</p>
<p><strong>Example:</strong></p>

<pre>
<strong>Input</strong>
[&quot;ProductOfNumbers&quot;,&quot;add&quot;,&quot;add&quot;,&quot;add&quot;,&quot;add&quot;,&quot;add&quot;,&quot;getProduct&quot;,&quot;getProduct&quot;,&quot;getProduct&quot;,&quot;add&quot;,&quot;getProduct&quot;]
[[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]

<strong>Output</strong>
[null,null,null,null,null,null,20,40,0,null,32]

<strong>Explanation</strong>
ProductOfNumbers productOfNumbers = new ProductOfNumbers();
productOfNumbers.add(3);        // [3]
productOfNumbers.add(0);        // [3,0]
productOfNumbers.add(2);        // [3,0,2]
productOfNumbers.add(5);        // [3,0,2,5]
productOfNumbers.add(4);        // [3,0,2,5,4]
productOfNumbers.getProduct(2); // return 20. The product of the last 2 numbers is 5 * 4 = 20
productOfNumbers.getProduct(3); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
productOfNumbers.getProduct(4); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
productOfNumbers.add(8);        // [3,0,2,5,4,8]
productOfNumbers.getProduct(2); // return 32. The product of the last 2 numbers is 4 * 8 = 32 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= num &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 4 * 10<sup>4</sup></code></li>
	<li>At most <code>4 * 10<sup>4</sup></code> calls will be made to <code>add</code> and <code>getProduct</code>.</li>
	<li>The product of the stream at any point in time will fit in a <strong>32-bit</strong> integer.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class ProductOfNumbers:
    def __init__(self):
        self.pre_product = []

    def add(self, num: int) -> None:
        if num == 0:
            self.pre_product = []
            return
        if not self.pre_product:
            self.pre_product.append(1)
        self.pre_product.append(num * self.pre_product[-1])

    def getProduct(self, k: int) -> int:
        n = len(self.pre_product)
        return 0 if n <= k else self.pre_product[n - 1] // self.pre_product[n - k - 1]


# Your ProductOfNumbers object will be instantiated and called as such:
# obj = ProductOfNumbers()
# obj.add(num)
# param_2 = obj.getProduct(k)
```

### **Java**

```java
class ProductOfNumbers {
    private List<Integer> preProduct;

    public ProductOfNumbers() {
        preProduct = new ArrayList<>();
    }

    public void add(int num) {
        if (num == 0) {
            preProduct.clear();
            return;
        }
        if (preProduct.isEmpty()) {
            preProduct.add(1);
        }
        preProduct.add(num * preProduct.get(preProduct.size() - 1));
    }

    public int getProduct(int k) {
        return preProduct.size() <= k ? 0 : preProduct.get(preProduct.size() - 1) / preProduct.get(preProduct.size() - 1 - k);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
```

### **...**

```

```

<!-- tabs:end -->
