---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1352.Product%20of%20the%20Last%20K%20Numbers/README.md
rating: 1473
source: 第 176 场周赛 Q2
tags:
    - 设计
    - 数组
    - 数学
    - 数据流
    - 前缀和
---

<!-- problem:start -->

# [1352. 最后 K 个数的乘积](https://leetcode.cn/problems/product-of-the-last-k-numbers)

[English Version](/solution/1300-1399/1352.Product%20of%20the%20Last%20K%20Numbers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>设计一个算法，该算法接受一个整数流并检索该流中最后 <code>k</code> 个整数的乘积。</p>

<p>实现&nbsp;<code>ProductOfNumbers</code>&nbsp;类：</p>

<ul>
	<li><code>ProductOfNumbers()</code>&nbsp;用一个空的流初始化对象。</li>
	<li><code>void add(int num)</code>&nbsp;将数字&nbsp;<code>num</code>&nbsp;添加到当前数字列表的最后面。</li>
	<li><code>int getProduct(int k)</code>&nbsp;返回当前数字列表中，最后&nbsp;<code>k</code>&nbsp;个数字的乘积。你可以假设当前列表中始终 <strong>至少</strong> 包含 <code>k</code> 个数字。</li>
</ul>

<p>题目数据保证：任何时候，任一连续数字序列的乘积都在 32 位整数范围内，不会溢出。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]
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
	<li><code>0 &lt;= num&nbsp;&lt;=&nbsp;100</code></li>
	<li><code>1 &lt;= k &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>add</code> 和 <code>getProduct</code>&nbsp;最多被调用&nbsp;<code>4 * 10<sup>4</sup></code> 次。</li>
	<li>在任何时间点流的乘积都在 32 位整数范围内。</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>您能否 <strong>同时</strong> 将 <code>GetProduct</code> 和 <code>Add</code> 的实现改为 <code>O(1)</code> 时间复杂度，而不是 <code>O(k)</code> 时间复杂度？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀积

我们初始化一个数组 $s$，其中 $s[i]$ 表示前 $i$ 个数字的乘积。

当调用 `add(num)` 时，我们判断 `num` 是否为 $0$，若是，则将 $s$ 置为 `[1]`，否则将 $s$ 的最后一个元素乘以 `num`，并将结果添加到 $s$ 的末尾。

当调用 `getProduct(k)` 时，此时判断 $s$ 的长度是否小于等于 $k$，若是，则返回 $0$，否则返回 $s$ 的最后一个元素除以 $s$ 的倒数第 $k + 1$ 个元素。即 $s[-1] / s[-k - 1]$。

时间复杂度 $O(1)$，空间复杂度 $O(n)$。其中 $n$ 为调用 `add` 的次数。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

```ts
class ProductOfNumbers {
    s = [1];

    add(num: number): void {
        if (num === 0) {
            this.s = [1];
        } else {
            const i = this.s.length;
            this.s[i] = this.s[i - 1] * num;
        }
    }

    getProduct(k: number): number {
        const i = this.s.length;
        if (k > i - 1) return 0;
        return this.s[i - 1] / this.s[i - k - 1];
    }
}
```

#### JavaScript

```js
class ProductOfNumbers {
    s = [1];

    add(num) {
        if (num === 0) {
            this.s = [1];
        } else {
            const i = this.s.length;
            this.s[i] = this.s[i - 1] * num;
        }
    }

    getProduct(k) {
        const i = this.s.length;
        if (k > i - 1) return 0;
        return this.s[i - 1] / this.s[i - k - 1];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
