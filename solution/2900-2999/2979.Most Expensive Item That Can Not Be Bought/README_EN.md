# [2979. Most Expensive Item That Can Not Be Bought](https://leetcode.com/problems/most-expensive-item-that-can-not-be-bought)

[中文文档](/solution/2900-2999/2979.Most%20Expensive%20Item%20That%20Can%20Not%20Be%20Bought/README.md)

## Description

<p>You are given two <strong>distinct</strong> <strong>prime</strong> numbers <code>primeOne</code> and <code>primeTwo</code>.</p>

<p>Alice and Bob are visiting a market. The market has an <strong>infinite</strong> number of items, for <strong>any</strong> positive integer <code>x</code> there exists an item whose price is <code>x</code>. Alice wants to buy some items from the market to gift to Bob. She has an <strong>infinite</strong> number of coins in the denomination <code>primeOne</code> and <code>primeTwo</code>. She wants to know the <strong>most expensive</strong> item she can <strong>not</strong> buy to gift to Bob.</p>

<p>Return <em>the price of the <strong>most expensive</strong> item which Alice can not gift to Bob</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> primeOne = 2, primeTwo = 5
<strong>Output:</strong> 3
<strong>Explanation:</strong> The prices of items which cannot be bought are [1,3]. It can be shown that all items with a price greater than 3 can be bought using a combination of coins of denominations 2 and 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> primeOne = 5, primeTwo = 7
<strong>Output:</strong> 23
<strong>Explanation:</strong> The prices of items which cannot be bought are [1,2,3,4,6,8,9,11,13,16,18,23]. It can be shown that all items with a price greater than 23 can be bought.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt; primeOne, primeTwo &lt; 10<sup>4</sup></code></li>
	<li><code>primeOne</code>, <code>primeTwo</code> are prime numbers.</li>
	<li><code>primeOne * primeTwo &lt; 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def mostExpensiveItem(self, primeOne: int, primeTwo: int) -> int:
        return primeOne * primeTwo - primeOne - primeTwo
```

### **Java**

```java
class Solution {
    public int mostExpensiveItem(int primeOne, int primeTwo) {
        return primeOne * primeTwo - primeOne - primeTwo;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int mostExpensiveItem(int primeOne, int primeTwo) {
        return primeOne * primeTwo - primeOne - primeTwo;
    }
};
```

### **Go**

```go
func mostExpensiveItem(primeOne int, primeTwo int) int {
	return primeOne*primeTwo - primeOne - primeTwo
}
```

### **TypeScript**

```ts
function mostExpensiveItem(primeOne: number, primeTwo: number): number {
    return primeOne * primeTwo - primeOne - primeTwo;
}
```

### **Rust**

```rust
impl Solution {
    pub fn most_expensive_item(prime_one: i32, prime_two: i32) -> i32 {
        prime_one * prime_two - prime_one - prime_two
    }
}
```

### **...**

```

```

<!-- tabs:end -->
