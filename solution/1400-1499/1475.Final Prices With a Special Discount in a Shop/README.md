# [1475. 商品折扣后的最终价格](https://leetcode.cn/problems/final-prices-with-a-special-discount-in-a-shop)

[English Version](/solution/1400-1499/1475.Final%20Prices%20With%20a%20Special%20Discount%20in%20a%20Shop/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组&nbsp;<code>prices</code>&nbsp;，其中&nbsp;<code>prices[i]</code>&nbsp;是商店里第&nbsp;<code>i</code>&nbsp;件商品的价格。</p>

<p>商店里正在进行促销活动，如果你要买第&nbsp;<code>i</code>&nbsp;件商品，那么你可以得到与 <code>prices[j]</code> 相等的折扣，其中&nbsp;<code>j</code>&nbsp;是满足&nbsp;<code>j &gt; i</code>&nbsp;且&nbsp;<code>prices[j] &lt;= prices[i]</code>&nbsp;的&nbsp;<strong>最小下标</strong>&nbsp;，如果没有满足条件的&nbsp;<code>j</code>&nbsp;，你将没有任何折扣。</p>

<p>请你返回一个数组，数组中第&nbsp;<code>i</code>&nbsp;个元素是折扣后你购买商品 <code>i</code>&nbsp;最终需要支付的价格。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>prices = [8,4,6,2,3]
<strong>输出：</strong>[4,2,4,2,3]
<strong>解释：</strong>
商品 0 的价格为 price[0]=8 ，你将得到 prices[1]=4 的折扣，所以最终价格为 8 - 4 = 4 。
商品 1 的价格为 price[1]=4 ，你将得到 prices[3]=2 的折扣，所以最终价格为 4 - 2 = 2 。
商品 2 的价格为 price[2]=6 ，你将得到 prices[3]=2 的折扣，所以最终价格为 6 - 2 = 4 。
商品 3 和 4 都没有折扣。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>prices = [1,2,3,4,5]
<strong>输出：</strong>[1,2,3,4,5]
<strong>解释：</strong>在这个例子中，所有商品都没有折扣。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>prices = [10,1,1,6]
<strong>输出：</strong>[9,0,1,6]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 500</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10^3</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：暴力枚举**

按题意模拟，采用双重循环枚举 `i` 和 `j`。

时间复杂度为 $O(n^2)$，忽略结果数组的空间消耗，空间复杂度 $O(1)$。

**方法二：单调栈**

单调栈常见模型：找出每个数左/右边**离它最近的**且**比它大/小的数**。模板：

```python
stk = []
for i in range(n):
    while stk and check(stk[-1], i):
        stk.pop()
    stk.append(i)
```

本题我们可以采用正序、逆序两种方式遍历数组 `prices`。

时间复杂度 $O(n)$，其中 $n$ 表示数组 `prices` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def finalPrices(self, prices: List[int]) -> List[int]:
        ans = []
        for i, v in enumerate(prices):
            ans.append(v)
            for j in range(i + 1, len(prices)):
                if prices[j] <= v:
                    ans[-1] -= prices[j]
                    break
        return ans
```

```python
class Solution:
    def finalPrices(self, prices: List[int]) -> List[int]:
        stk = []
        ans = prices[:]
        for i, v in enumerate(prices):
            while stk and prices[stk[-1]] >= v:
                ans[stk.pop()] -= v
            stk.append(i)
        return ans
```

```python
class Solution:
    def finalPrices(self, prices: List[int]) -> List[int]:
        stk = []
        ans = prices[:]
        for i in range(len(prices) - 1, -1, -1):
            while stk and prices[stk[-1]] > prices[i]:
                stk.pop()
            if stk:
                ans[i] -= prices[stk[-1]]
            stk.append(i)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = prices[i];
            for (int j = i + 1; j < n; ++j) {
                if (prices[j] <= prices[i]) {
                    ans[i] -= prices[j];
                    break;
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] finalPrices(int[] prices) {
        Deque<Integer> stk = new ArrayDeque<>();
        int n = prices.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = prices[i];
            while (!stk.isEmpty() && prices[stk.peek()] >= prices[i]) {
                ans[stk.pop()] -= prices[i];
            }
            stk.push(i);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] finalPrices(int[] prices) {
        Deque<Integer> stk = new ArrayDeque<>();
        int n = prices.length;
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            ans[i] = prices[i];
            while (!stk.isEmpty() && prices[stk.peek()] > prices[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                ans[i] -= prices[stk.peek()];
            }
            stk.push(i);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> finalPrices(vector<int>& prices) {
        int n = prices.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            ans[i] = prices[i];
            for (int j = i + 1; j < n; ++j) {
                if (prices[j] <= prices[i]) {
                    ans[i] -= prices[j];
                    break;
                }
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> finalPrices(vector<int>& prices) {
        stack<int> stk;
        vector<int> ans = prices;
        for (int i = 0; i < prices.size(); ++i) {
            while (!stk.empty() && prices[stk.top()] >= prices[i]) {
                ans[stk.top()] -= prices[i];
                stk.pop();
            }
            stk.push(i);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> finalPrices(vector<int>& prices) {
        stack<int> stk;
        int n = prices.size();
        vector<int> ans(n);
        for (int i = n - 1; i >= 0; --i) {
            ans[i] = prices[i];
            while (!stk.empty() && prices[stk.top()] > prices[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                ans[i] -= prices[stk.top()];
            }
            stk.push(i);
        }
        return ans;
    }
};
```

### **Go**

```go
func finalPrices(prices []int) []int {
	n := len(prices)
	ans := make([]int, n)
	for i, v := range prices {
		ans[i] = v
		for j := i + 1; j < n; j++ {
			if prices[j] <= v {
				ans[i] -= prices[j]
				break
			}
		}
	}
	return ans
}
```

```go
func finalPrices(prices []int) []int {
	var stk []int
	n := len(prices)
	ans := make([]int, n)
	for i, v := range prices {
		ans[i] = v
		for len(stk) > 0 && prices[stk[len(stk)-1]] >= v {
			ans[stk[len(stk)-1]] -= v
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, i)
	}
	return ans
}
```

```go
func finalPrices(prices []int) []int {
	stk := []int{}
	n := len(prices)
	ans := make([]int, n)
	for i := n - 1; i >= 0; i-- {
		ans[i] = prices[i]
		for len(stk) > 0 && prices[stk[len(stk)-1]] > prices[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			ans[i] -= prices[stk[len(stk)-1]]
		}
		stk = append(stk, i)
	}
	return ans
}
```

### **TypeScript**

```ts
function finalPrices(prices: number[]): number[] {
    const n = prices.length;
    const ans = new Array(n);
    for (let i = 0; i < n; ++i) {
        ans[i] = prices[i];
        for (let j = i + 1; j < n; ++j) {
            if (prices[j] <= prices[i]) {
                ans[i] -= prices[j];
                break;
            }
        }
    }
    return ans;
}
```

```ts
function finalPrices(prices: number[]): number[] {
    const n = prices.length;
    const stk = [];
    const ans = new Array(n);
    for (let i = 0; i < n; ++i) {
        ans[i] = prices[i];
        while (stk.length && prices[stk[stk.length - 1]] >= prices[i]) {
            ans[stk.pop()] -= prices[i];
        }
        stk.push(i);
    }
    return ans;
}
```

```ts
function finalPrices(prices: number[]): number[] {
    const n = prices.length;
    const stack = [];
    const res = new Array(n);
    for (let i = n - 1; i >= 0; i--) {
        const price = prices[i];
        while (stack.length !== 0 && stack[stack.length - 1] > price) {
            stack.pop();
        }
        res[i] = price - (stack[stack.length - 1] ?? 0);
        stack.push(price);
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn final_prices(prices: Vec<i32>) -> Vec<i32> {
        let n = prices.len();
        let mut stack = Vec::new();
        let mut res = vec![0; n];
        for i in (0..n).rev() {
            let price = prices[i];
            while !stack.is_empty() && *stack.last().unwrap() > price {
                stack.pop();
            }
            res[i] = price - stack.last().unwrap_or(&0);
            stack.push(price);
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
