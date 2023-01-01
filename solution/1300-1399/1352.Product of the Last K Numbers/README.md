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

**方法一：前缀积**

我们初始化一个数组 $s$，其中 $s[i]$ 表示前 $i$ 个数字的乘积。

当调用 `add(num)` 时，我们判断 `num` 是否为 $0$，若是，则将 $s$ 置为 `[1]`，否则将 $s$ 的最后一个元素乘以 `num`，并将结果添加到 $s$ 的末尾。

当调用 `getProduct(k)` 时，此时判断 $s$ 的长度是否小于等于 $k$，若是，则返回 $0$，否则返回 $s$ 的最后一个元素除以 $s$ 的倒数第 $k + 1$ 个元素。即 $s[-1] / s[-k - 1]$。

时间复杂度 $O(1)$，空间复杂度 $O(n)$。其中 $n$ 为调用 `add` 的次数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class ProductOfNumbers:

    def __init__(self):
        self.s = [1]

    def add(self, num: int) -> None:
        if num == 0:
            self.s = [1]
            return
        self.s.append(self.s[-1] * num)

    def getProduct(self, k: int) -> int:
        return 0 if len(self.s) <= k else self.s[-1] // self.s[-k - 1]


# Your ProductOfNumbers object will be instantiated and called as such:
# obj = ProductOfNumbers()
# obj.add(num)
# param_2 = obj.getProduct(k)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class ProductOfNumbers {
    private List<Integer> s = new ArrayList<>();

    public ProductOfNumbers() {
        s.add(1);
    }

    public void add(int num) {
        if (num == 0) {
            s.clear();
            s.add(1);
            return;
        }
        s.add(s.get(s.size() - 1) * num);
    }

    public int getProduct(int k) {
        int n = s.size();
        return n <= k ? 0 : s.get(n - 1) / s.get(n - k - 1);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
```

### **C++**

```cpp
class ProductOfNumbers {
public:
    ProductOfNumbers() {
        s.push_back(1);
    }

    void add(int num) {
        if (num == 0) {
            s.clear();
            s.push_back(1);
            return;
        }
        s.push_back(s.back() * num);
    }

    int getProduct(int k) {
        int n = s.size();
        return n <= k ? 0 : s.back() / s[n - k - 1];
    }

private:
    vector<int> s;
};

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers* obj = new ProductOfNumbers();
 * obj->add(num);
 * int param_2 = obj->getProduct(k);
 */
```

### **Go**

```go
type ProductOfNumbers struct {
	s []int
}

func Constructor() ProductOfNumbers {
	return ProductOfNumbers{[]int{1}}
}

func (this *ProductOfNumbers) Add(num int) {
	if num == 0 {
		this.s = []int{1}
		return
	}
	this.s = append(this.s, this.s[len(this.s)-1]*num)
}

func (this *ProductOfNumbers) GetProduct(k int) int {
	n := len(this.s)
	if n <= k {
		return 0
	}
	return this.s[len(this.s)-1] / this.s[len(this.s)-k-1]
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Add(num);
 * param_2 := obj.GetProduct(k);
 */
```

### **...**

```

```

<!-- tabs:end -->
