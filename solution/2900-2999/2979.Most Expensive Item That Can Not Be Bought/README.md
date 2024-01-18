# [2979. 最贵的无法购买的商品](https://leetcode.cn/problems/most-expensive-item-that-can-not-be-bought)

[English Version](/solution/2900-2999/2979.Most%20Expensive%20Item%20That%20Can%20Not%20Be%20Bought/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个 <strong>不同的质数</strong>&nbsp;<code>primeOne</code>&nbsp;和&nbsp;<code>primeTwo</code>。</p>

<p>Alice 和 Bob 正在逛市场。该市场有 <strong>无数种&nbsp;</strong>商品，对于 <strong>任何</strong> 正整数 <code>x</code>，都存在一个价格为 <code>x</code> 的物品。Alice 想从市场里买一些东西送给 Bob。她有 <b>无数个</b>&nbsp;面值为 <code>primeOne</code> 和 <code>primeTwo</code> 的硬币。她想知道她 <strong>无法</strong>&nbsp;用她拥有的硬币购买的 <strong>最贵</strong> 商品的价格。</p>

<p>返回 <em>Alice 无法买给&nbsp;Bob 的 <strong>最贵</strong> 商品的价格。</em></p>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>

<pre>
<b>输入：</b>primeOne = 2, primeTwo = 5
<b>输出：</b>3
<b>解释：</b>无法购买的商品的价格有 [1,3]。所有价格大于 3 的商品都可以通过组合使用面额为 2 和 5 的硬币购买。
</pre>

<p><b>示例 2:</b></p>

<pre>
<b>输入：</b>primeOne = 5, primeTwo = 7
<b>输出：</b>23
<b>解释：</b>无法购买的商品的价格有 [1,2,3,4,6,8,9,11,13,16,18,23]。所有价格大于 23 的商品都可以购买。
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt; primeOne, primeTwo &lt; 10<sup>4</sup></code></li>
	<li><code>primeOne</code>, <code>primeTwo</code>&nbsp;都是质数。</li>
	<li><code>primeOne * primeTwo &lt; 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：Chicken McNugget 定理

根据 Chicken McNugget 定理，两个互质的正整数 $a$ 和 $b$，最大不能表示的数为 $ab - a - b$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def mostExpensiveItem(self, primeOne: int, primeTwo: int) -> int:
        return primeOne * primeTwo - primeOne - primeTwo
```

```java
class Solution {
    public int mostExpensiveItem(int primeOne, int primeTwo) {
        return primeOne * primeTwo - primeOne - primeTwo;
    }
}
```

```cpp
class Solution {
public:
    int mostExpensiveItem(int primeOne, int primeTwo) {
        return primeOne * primeTwo - primeOne - primeTwo;
    }
};
```

```go
func mostExpensiveItem(primeOne int, primeTwo int) int {
	return primeOne*primeTwo - primeOne - primeTwo
}
```

```ts
function mostExpensiveItem(primeOne: number, primeTwo: number): number {
    return primeOne * primeTwo - primeOne - primeTwo;
}
```

```rust
impl Solution {
    pub fn most_expensive_item(prime_one: i32, prime_two: i32) -> i32 {
        prime_one * prime_two - prime_one - prime_two
    }
}
```

<!-- tabs:end -->

<!-- end -->
