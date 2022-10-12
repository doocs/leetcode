# [2144. Minimum Cost of Buying Candies With Discount](https://leetcode.com/problems/minimum-cost-of-buying-candies-with-discount)

[中文文档](/solution/2100-2199/2144.Minimum%20Cost%20of%20Buying%20Candies%20With%20Discount/README.md)

## Description

<p>A shop is selling candies at a discount. For <strong>every two</strong> candies sold, the shop gives a <strong>third</strong> candy for <strong>free</strong>.</p>

<p>The customer can choose <strong>any</strong> candy to take away for free as long as the cost of the chosen candy is less than or equal to the <strong>minimum</strong> cost of the two candies bought.</p>

<ul>
	<li>For example, if there are <code>4</code> candies with costs <code>1</code>, <code>2</code>, <code>3</code>, and <code>4</code>, and the customer buys candies with costs <code>2</code> and <code>3</code>, they&nbsp;can take the candy with cost <code>1</code> for free, but not the candy with cost <code>4</code>.</li>
</ul>

<p>Given a <strong>0-indexed</strong> integer array <code>cost</code>, where <code>cost[i]</code> denotes the cost of the <code>i<sup>th</sup></code> candy, return <em>the <strong>minimum cost</strong> of buying <strong>all</strong> the candies</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> cost = [1,2,3]
<strong>Output:</strong> 5
<strong>Explanation:</strong> We buy the candies with costs 2 and 3, and take the candy with cost 1 for free.
The total cost of buying all candies is 2 + 3 = 5. This is the <strong>only</strong> way we can buy the candies.
Note that we cannot buy candies with costs 1 and 3, and then take the candy with cost 2 for free.
The cost of the free candy has to be less than or equal to the minimum cost of the purchased candies.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> cost = [6,5,7,9,2,2]
<strong>Output:</strong> 23
<strong>Explanation:</strong> The way in which we can get the minimum cost is described below:
- Buy candies with costs 9 and 7
- Take the candy with cost 6 for free
- We buy candies with costs 5 and 2
- Take the last remaining candy with cost 2 for free
Hence, the minimum cost to buy all candies is 9 + 7 + 5 + 2 = 23.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> cost = [5,5]
<strong>Output:</strong> 10
<strong>Explanation:</strong> Since there are only 2 candies, we buy both of them. There is not a third candy we can take for free.
Hence, the minimum cost to buy all candies is 5 + 5 = 10.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= cost.length &lt;= 100</code></li>
	<li><code>1 &lt;= cost[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumCost(self, cost: List[int]) -> int:
        cost.sort()
        ans, n = 0, len(cost)
        for i in range(n - 1, -1, -3):
            ans += cost[i]
            if i >= 1:
                ans += cost[i - 1]
        return ans
```

### **Java**

```java
class Solution {
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);
        int ans = 0, n = cost.length;
        for (int i = n - 1; i >= 0; i -= 3) {
            ans += cost[i];
            if (i >= 1) {
                ans += cost[i - 1];
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumCost(vector<int>& cost) {
        sort(cost.begin(), cost.end());
        int ans = 0, n = cost.size();
        for (int i = n - 1; i >= 0; i -= 3) {
            ans += cost[i];
            if (i >= 1) ans += cost[i - 1];
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumCost(cost []int) int {
	sort.Ints(cost)
	ans, n := 0, len(cost)
	for i := n - 1; i >= 0; i -= 3 {
		ans += cost[i]
		if i >= 1 {
			ans += cost[i-1]
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
