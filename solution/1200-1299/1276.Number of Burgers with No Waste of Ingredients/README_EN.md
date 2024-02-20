# [1276. Number of Burgers with No Waste of Ingredients](https://leetcode.com/problems/number-of-burgers-with-no-waste-of-ingredients)

[中文文档](/solution/1200-1299/1276.Number%20of%20Burgers%20with%20No%20Waste%20of%20Ingredients/README.md)

<!-- tags:Math -->

## Description

<p>Given two integers <code>tomatoSlices</code> and <code>cheeseSlices</code>. The ingredients of different burgers are as follows:</p>

<ul>
	<li><strong>Jumbo Burger:</strong> <code>4</code> tomato slices and <code>1</code> cheese slice.</li>
	<li><strong>Small Burger:</strong> <code>2</code> Tomato slices and <code>1</code> cheese slice.</li>
</ul>

<p>Return <code>[total_jumbo, total_small]</code> so that the number of remaining <code>tomatoSlices</code> equal to <code>0</code> and the number of remaining <code>cheeseSlices</code> equal to <code>0</code>. If it is not possible to make the remaining <code>tomatoSlices</code> and <code>cheeseSlices</code> equal to <code>0</code> return <code>[]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> tomatoSlices = 16, cheeseSlices = 7
<strong>Output:</strong> [1,6]
<strong>Explantion:</strong> To make one jumbo burger and 6 small burgers we need 4*1 + 2*6 = 16 tomato and 1 + 6 = 7 cheese.
There will be no remaining ingredients.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> tomatoSlices = 17, cheeseSlices = 4
<strong>Output:</strong> []
<strong>Explantion:</strong> There will be no way to use all ingredients to make small and jumbo burgers.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> tomatoSlices = 4, cheeseSlices = 17
<strong>Output:</strong> []
<strong>Explantion:</strong> Making 1 jumbo burger there will be 16 cheese remaining and making 2 small burgers there will be 15 cheese remaining.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= tomatoSlices, cheeseSlices &lt;= 10<sup>7</sup></code></li>
</ul>

## Solutions

### Solution 1: Mathematics

We set the number of Jumbo Burgers as $x$ and the number of Small Burgers as $y$, then we have:

$$
\begin{aligned}
4x + 2y &= tomatoSlices \\
x + y &= cheeseSlices
\end{aligned}
$$

Transforming the above two equations, we can get:

$$
\begin{aligned}
y = (4 \times cheeseSlices - tomatoSlices) / 2 \\
x = cheeseSlices - y
\end{aligned}
$$

Where $x$ and $y$ must be non-negative integers.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def numOfBurgers(self, tomatoSlices: int, cheeseSlices: int) -> List[int]:
        k = 4 * cheeseSlices - tomatoSlices
        y = k // 2
        x = cheeseSlices - y
        return [] if k % 2 or y < 0 or x < 0 else [x, y]
```

```java
class Solution {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        int k = 4 * cheeseSlices - tomatoSlices;
        int y = k / 2;
        int x = cheeseSlices - y;
        return k % 2 != 0 || y < 0 || x < 0 ? List.of() : List.of(x, y);
    }
}
```

```cpp
class Solution {
public:
    vector<int> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        int k = 4 * cheeseSlices - tomatoSlices;
        int y = k / 2;
        int x = cheeseSlices - y;
        return k % 2 || x < 0 || y < 0 ? vector<int>{} : vector<int>{x, y};
    }
};
```

```go
func numOfBurgers(tomatoSlices int, cheeseSlices int) []int {
	k := 4*cheeseSlices - tomatoSlices
	y := k / 2
	x := cheeseSlices - y
	if k%2 != 0 || x < 0 || y < 0 {
		return []int{}
	}
	return []int{x, y}
}
```

```ts
function numOfBurgers(tomatoSlices: number, cheeseSlices: number): number[] {
    const k = 4 * cheeseSlices - tomatoSlices;
    const y = k >> 1;
    const x = cheeseSlices - y;
    return k % 2 || y < 0 || x < 0 ? [] : [x, y];
}
```

```rust
impl Solution {
    pub fn num_of_burgers(tomato_slices: i32, cheese_slices: i32) -> Vec<i32> {
        let k = 4 * cheese_slices - tomato_slices;
        let y = k / 2;
        let x = cheese_slices - y;
        if k % 2 != 0 || y < 0 || x < 0 {
            Vec::new()
        } else {
            vec![x, y]
        }
    }
}
```

<!-- tabs:end -->

<!-- end -->
