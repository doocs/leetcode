# [1475. Final Prices With a Special Discount in a Shop](https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop)

[中文文档](/solution/1400-1499/1475.Final%20Prices%20With%20a%20Special%20Discount%20in%20a%20Shop/README.md)

## Description

<p>You are given an integer array <code>prices</code> where <code>prices[i]</code> is the price of the <code>i<sup>th</sup></code> item in a shop.</p>

<p>There is a special discount for items in the shop. If you buy the <code>i<sup>th</sup></code> item, then you will receive a discount equivalent to <code>prices[j]</code> where <code>j</code> is the minimum index such that <code>j &gt; i</code> and <code>prices[j] &lt;= prices[i]</code>. Otherwise, you will not receive any discount at all.</p>

<p>Return an integer array <code>answer</code> where <code>answer[i]</code> is the final price you will pay for the <code>i<sup>th</sup></code> item of the shop, considering the special discount.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [8,4,6,2,3]
<strong>Output:</strong> [4,2,4,2,3]
<strong>Explanation:</strong> 
For item 0 with price[0]=8 you will receive a discount equivalent to prices[1]=4, therefore, the final price you will pay is 8 - 4 = 4.
For item 1 with price[1]=4 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 4 - 2 = 2.
For item 2 with price[2]=6 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 6 - 2 = 4.
For items 3 and 4 you will not receive any discount at all.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [1,2,3,4,5]
<strong>Output:</strong> [1,2,3,4,5]
<strong>Explanation:</strong> In this case, for all items, you will not receive any discount at all.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> prices = [10,1,1,6]
<strong>Output:</strong> [9,0,1,6]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 500</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
