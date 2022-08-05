# [1672. 最富有客户的资产总量](https://leetcode.cn/problems/richest-customer-wealth)

[English Version](/solution/1600-1699/1672.Richest%20Customer%20Wealth/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>m x n</code> 的整数网格 <code>accounts</code> ，其中 <code>accounts[i][j]</code> 是第 <code>i​​​​​<sup>​​​​​​</sup>​</code> 位客户在第 <code>j</code> 家银行托管的资产数量。返回最富有客户所拥有的 <strong>资产总量</strong> 。</p>

<p>客户的 <strong>资产总量</strong> 就是他们在各家银行托管的资产数量之和。最富有客户就是 <strong>资产总量</strong> 最大的客户。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>accounts = [[1,2,3],[3,2,1]]
<strong>输出：</strong>6
<strong>解释：</strong>
<code>第 1 位客户的资产总量 = 1 + 2 + 3 = 6
第 2 位客户的资产总量 = 3 + 2 + 1 = 6
</code>两位客户都是最富有的，资产总量都是 6 ，所以返回 6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>accounts = [[1,5],[7,3],[3,5]]
<strong>输出：</strong>10
<strong>解释：</strong>
<code>第 1 位客户的资产总量</code> = 6
<code>第 2 位客户的资产总量</code> = 10 
<code>第 3 位客户的资产总量</code> = 8
第 2 位客户是最富有的，资产总量是 10</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>accounts = [[2,8,7],[7,1,3],[1,9,5]]
<strong>输出：</strong>17
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == accounts.length</code></li>
	<li><code>n == accounts[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>1 &lt;= accounts[i][j] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumWealth(self, accounts: List[List[int]]) -> int:
        return max(sum(account) for account in accounts)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
