# [1672. Richest Customer Wealth](https://leetcode.com/problems/richest-customer-wealth)

[中文文档](/solution/1600-1699/1672.Richest%20Customer%20Wealth/README.md)

## Description

<p>You are given an <code>m x n</code> integer grid <code>accounts</code> where <code>accounts[i][j]</code> is the amount of money the <code>i​​​​​<sup>​​​​​​th</sup>​​​​</code> customer has in the <code>j​​​​​<sup>​​​​​​th</sup></code>​​​​ bank. Return<em> the <strong>wealth</strong> that the richest customer has.</em></p>

<p>A customer&#39;s <strong>wealth</strong> is the amount of money they have in all their bank accounts. The richest customer is the customer that has the maximum <strong>wealth</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> accounts = [[1,2,3],[3,2,1]]
<strong>Output:</strong> 6
<strong>Explanation</strong><strong>:</strong>
<code>1st customer has wealth = 1 + 2 + 3 = 6
</code><code>2nd customer has wealth = 3 + 2 + 1 = 6
</code>Both customers are considered the richest with a wealth of 6 each, so return 6.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> accounts = [[1,5],[7,3],[3,5]]
<strong>Output:</strong> 10
<strong>Explanation</strong>: 
1st customer has wealth = 6
2nd customer has wealth = 10 
3rd customer has wealth = 8
The 2nd customer is the richest with a wealth of 10.</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> accounts = [[2,8,7],[7,1,3],[1,9,5]]
<strong>Output:</strong> 17
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m ==&nbsp;accounts.length</code></li>
	<li><code>n ==&nbsp;accounts[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>1 &lt;= accounts[i][j] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumWealth(self, accounts: List[List[int]]) -> int:
        return max(sum(account) for account in accounts)
```

### **Java**

```java
class Solution {
    public int maximumWealth(int[][] accounts) {
        int res = 0;
        for (int[] account : accounts) {
            int t = 0;
            for (int money : account) {
                t += money;
            }
            res = Math.max(res, t);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumWealth(vector<vector<int>>& accounts) {
        int res = 0;
        for (auto& account : accounts)
            res = max(res, accumulate(account.begin(), account.end(), 0));
        return res;
    }
};
```

### **Go**

```go
func maximumWealth(accounts [][]int) int {
	res := 0
	for _, account := range accounts {
		t := 0
		for _, money := range account {
			t += money
		}
		if t > res {
			res = t
		}
	}
	return res
}
```

### **TypeScript**

```ts
function maximumWealth(accounts: number[][]): number {
    return accounts.reduce(
        (res, account) =>
            Math.max(
                res,
                account.reduce((p, v) => p + v),
            ),
        0,
    );
}
```

### **Rust**

```rust
impl Solution {
    pub fn maximum_wealth(accounts: Vec<Vec<i32>>) -> i32 {
        accounts
            .iter()
            .map(|account| account.iter().sum())
            .max()
            .unwrap()
    }
}
```

### **Kotlin**

```kotlin
class Solution {
    fun maximumWealth(accounts: Array<IntArray>): Int {
        var max = 0
        for (account in accounts) {
            val sum = account.sum()
            if (sum > max) {
                max = sum
            }
        }
        return max
    }
}
```

### **...**

```

```

<!-- tabs:end -->
