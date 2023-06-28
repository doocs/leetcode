# [2591. Distribute Money to Maximum Children](https://leetcode.com/problems/distribute-money-to-maximum-children)

[中文文档](/solution/2500-2599/2591.Distribute%20Money%20to%20Maximum%20Children/README.md)

## Description

<p>You are given an integer <code>money</code> denoting the amount of money (in dollars) that you have and another integer <code>children</code> denoting the number of children that you must distribute the money to.</p>

<p>You have to distribute the money according to the following rules:</p>

<ul>
	<li>All money must be distributed.</li>
	<li>Everyone must receive at least <code>1</code> dollar.</li>
	<li>Nobody receives <code>4</code> dollars.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of children who may receive <strong>exactly</strong> </em><code>8</code> <em>dollars if you distribute the money according to the aforementioned rules</em>. If there is no way to distribute the money, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> money = 20, children = 3
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
The maximum number of children with 8 dollars will be 1. One of the ways to distribute the money is:
- 8 dollars to the first child.
- 9 dollars to the second child. 
- 3 dollars to the third child.
It can be proven that no distribution exists such that number of children getting 8 dollars is greater than 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> money = 16, children = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> Each child can be given 8 dollars.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= money &lt;= 200</code></li>
	<li><code>2 &lt;= children &lt;= 30</code></li>
</ul>

## Solutions

**Solution 1: Case analysis**

If $money \lt children$, then there must be a child who did not receive money, return $-1$.

If $money \gt 8 \times children$, then there are $children-1$ children who received $8$ dollars, and the remaining child received $money - 8 \times (children-1)$ dollars, return $children-1$.

If $money = 8 \times children - 4$, then there are $children-2$ children who received $8$ dollars, and the remaining two children shared the remaining $12$ dollars (as long as it is not $4$, $8$ dollars is fine), return $children-2$.

If we assume that there are $x$ children who received $8$ dollars, then the remaining money is $money- 8 \times x$, as long as it is greater than or equal to the number of remaining children $children-x$, it can meet the requirements. Therefore, we only need to find the maximum value of $x$, which is the answer.

Time complexity $O(1)$, space complexity $O(1)$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def distMoney(self, money: int, children: int) -> int:
        if money < children:
            return -1
        if money > 8 * children:
            return children - 1
        if money == 8 * children - 4:
            return children - 2
        # money-8x >= children-x, x <= (money-children)/7
        return (money - children) // 7
```

### **Java**

```java
class Solution {
    public int distMoney(int money, int children) {
        if (money < children) {
            return -1;
        }
        if (money > 8 * children) {
            return children - 1;
        }
        if (money == 8 * children - 4) {
            return children - 2;
        }
        // money-8x >= children-x, x <= (money-children)/7
        return (money - children) / 7;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int distMoney(int money, int children) {
        if (money < children) {
            return -1;
        }
        if (money > 8 * children) {
            return children - 1;
        }
        if (money == 8 * children - 4) {
            return children - 2;
        }
        // money-8x >= children-x, x <= (money-children)/7
        return (money - children) / 7;
    }
};
```

### **Go**

```go
func distMoney(money int, children int) int {
	if money < children {
		return -1
	}
	if money > 8*children {
		return children - 1
	}
	if money == 8*children-4 {
		return children - 2
	}
	// money-8x >= children-x, x <= (money-children)/7
	return (money - children) / 7
}
```

### **TypeScript**

```ts
function distMoney(money: number, children: number): number {
    if (money < children) {
        return -1;
    }
    if (money > 8 * children) {
        return children - 1;
    }
    if (money === 8 * children - 4) {
        return children - 2;
    }
    return Math.floor((money - children) / 7);
}
```

### **...**

```

```

<!-- tabs:end -->
