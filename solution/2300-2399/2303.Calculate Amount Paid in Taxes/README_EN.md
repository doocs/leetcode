# [2303. Calculate Amount Paid in Taxes](https://leetcode.com/problems/calculate-amount-paid-in-taxes)

[中文文档](/solution/2300-2399/2303.Calculate%20Amount%20Paid%20in%20Taxes/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> 2D integer array <code>brackets</code> where <code>brackets[i] = [upper<sub>i</sub>, percent<sub>i</sub>]</code> means that the <code>i<sup>th</sup></code> tax bracket has an upper bound of <code>upper<sub>i</sub></code> and is taxed at a rate of <code>percent<sub>i</sub></code>. The brackets are <strong>sorted</strong> by upper bound (i.e. <code>upper<sub>i-1</sub> &lt; upper<sub>i</sub></code> for <code>0 &lt; i &lt; brackets.length</code>).</p>

<p>Tax is calculated as follows:</p>

<ul>
	<li>The first <code>upper<sub>0</sub></code> dollars earned are taxed at a rate of <code>percent<sub>0</sub></code>.</li>
	<li>The next <code>upper<sub>1</sub> - upper<sub>0</sub></code> dollars earned are taxed at a rate of <code>percent<sub>1</sub></code>.</li>
	<li>The next <code>upper<sub>2</sub> - upper<sub>1</sub></code> dollars earned are taxed at a rate of <code>percent<sub>2</sub></code>.</li>
	<li>And so on.</li>
</ul>

<p>You are given an integer <code>income</code> representing the amount of money you earned. Return <em>the amount of money that you have to pay in taxes.</em> Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> brackets = [[3,50],[7,10],[12,25]], income = 10
<strong>Output:</strong> 2.65000
<strong>Explanation:</strong>
The first 3 dollars you earn are taxed at 50%. You have to pay $3 * 50% = $1.50 dollars in taxes.
The next 7 - 3 = 4 dollars you earn are taxed at 10%. You have to pay $4 * 10% = $0.40 dollars in taxes.
The final 10 - 7 = 3 dollars you earn are taxed at 25%. You have to pay $3 * 25% = $0.75 dollars in taxes.
You have to pay a total of $1.50 + $0.40 + $0.75 = $2.65 dollars in taxes.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> brackets = [[1,0],[4,25],[5,50]], income = 2
<strong>Output:</strong> 0.25000
<strong>Explanation:</strong>
The first dollar you earn is taxed at 0%. You have to pay $1 * 0% = $0 dollars in taxes.
The second dollar you earn is taxed at 25%. You have to pay $1 * 25% = $0.25 dollars in taxes.
You have to pay a total of $0 + $0.25 = $0.25 dollars in taxes.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> brackets = [[2,50]], income = 0
<strong>Output:</strong> 0.00000
<strong>Explanation:</strong>
You have no income to tax, so you have to pay a total of $0 dollars in taxes.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= brackets.length &lt;= 100</code></li>
	<li><code>1 &lt;= upper<sub>i</sub> &lt;= 1000</code></li>
	<li><code>0 &lt;= percent<sub>i</sub> &lt;= 100</code></li>
	<li><code>0 &lt;= income &lt;= 1000</code></li>
	<li><code>upper<sub>i</sub></code> is sorted in ascending order.</li>
	<li>All the values of <code>upper<sub>i</sub></code> are <strong>unique</strong>.</li>
	<li>The upper bound of the last tax bracket is greater than or equal to <code>income</code>.</li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def calculateTax(self, brackets: List[List[int]], income: int) -> float:
        ans = idx = 0
        prev = 0
        while income:
            a, b = brackets[idx]
            d = a - prev
            ans += min(d, income) * b / 100
            if income <= d:
                break
            income -= d
            idx += 1
            prev = a
        return ans
```

### **Java**

```java
class Solution {
    public double calculateTax(int[][] brackets, int income) {
        double ans = 0;
        int idx = 0, prev = 0;
        while (income > 0) {
            int a = brackets[idx][0], b = brackets[idx][1];
            int d = a - prev;
            ans += Math.min(d, income) * b / 100.0;
            if (income <= d) {
                break;
            }
            income -= d;
            ++idx;
            prev = a;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    double calculateTax(vector<vector<int>>& brackets, int income) {
        double ans = 0;
        int idx = 0, prev = 0;
        while (income)
        {
            int a = brackets[idx][0], b = brackets[idx][1];
            int d = a - prev;
            ans += min(d, income) * b / 100.0;
            if (income <= d) break;
            income -= d;
            ++idx;
            prev = a;
        }
        return ans;
    }
};
```

### **Go**

```go
func calculateTax(brackets [][]int, income int) float64 {
	var ans float64
	idx, prev := 0, 0
	for income > 0 {
		a, b := brackets[idx][0], brackets[idx][1]
		d := a - prev
		ans += float64(min(d, income)*b) / 100.0
		if income <= d {
			break
		}
		income -= d
		idx++
		prev = a
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **Rust**

```rust
impl Solution {
    pub fn calculate_tax(brackets: Vec<Vec<i32>>, income: i32) -> f64 {
        let mut res = 0f64;
        let mut pre = 0i32;
        for bracket in brackets.iter() {
            res += f64::from(income.min(bracket[0]) - pre) * f64::from(bracket[1]) * 0.01;
            if income <= bracket[0] {
                break;
            }
            pre = bracket[0];
        }
        res
    }
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
