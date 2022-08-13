# [1352. 最后 K 个数的乘积](https://leetcode.cn/problems/product-of-the-last-k-numbers)

[English Version](/solution/1300-1399/1352.Product%20of%20the%20Last%20K%20Numbers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你实现一个「数字乘积类」<code>ProductOfNumbers</code>，要求支持下述两种方法：</p>

<p>1.<code>&nbsp;add(int num)</code></p>

<ul>
	<li>将数字&nbsp;<code>num</code>&nbsp;添加到当前数字列表的最后面。</li>
</ul>

<p>2.<code> getProduct(int k)</code></p>

<ul>
	<li>返回当前数字列表中，最后&nbsp;<code>k</code>&nbsp;个数字的乘积。</li>
	<li>你可以假设当前列表中始终 <strong>至少</strong> 包含 <code>k</code> 个数字。</li>
</ul>

<p>题目数据保证：任何时候，任一连续数字序列的乘积都在 32-bit 整数范围内，不会溢出。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>
[&quot;ProductOfNumbers&quot;,&quot;add&quot;,&quot;add&quot;,&quot;add&quot;,&quot;add&quot;,&quot;add&quot;,&quot;getProduct&quot;,&quot;getProduct&quot;,&quot;getProduct&quot;,&quot;add&quot;,&quot;getProduct&quot;]
[[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]

<strong>输出：</strong>
[null,null,null,null,null,null,20,40,0,null,32]

<strong>解释：</strong>
ProductOfNumbers productOfNumbers = new ProductOfNumbers();
productOfNumbers.add(3);        // [3]
productOfNumbers.add(0);        // [3,0]
productOfNumbers.add(2);        // [3,0,2]
productOfNumbers.add(5);        // [3,0,2,5]
productOfNumbers.add(4);        // [3,0,2,5,4]
productOfNumbers.getProduct(2); // 返回 20 。最后 2 个数字的乘积是 5 * 4 = 20
productOfNumbers.getProduct(3); // 返回 40 。最后 3 个数字的乘积是 2 * 5 * 4 = 40
productOfNumbers.getProduct(4); // 返回  0 。最后 4 个数字的乘积是 0 * 2 * 5 * 4 = 0
productOfNumbers.add(8);        // [3,0,2,5,4,8]
productOfNumbers.getProduct(2); // 返回 32 。最后 2 个数字的乘积是 4 * 8 = 32 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>add</code> 和 <code>getProduct</code>&nbsp;两种操作加起来总共不会超过&nbsp;<code>40000</code>&nbsp;次。</li>
	<li><code>0 &lt;= num&nbsp;&lt;=&nbsp;100</code></li>
	<li><code>1 &lt;= k &lt;= 40000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“前缀积”实现。

若遇到 0，则清空前缀积列表。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
