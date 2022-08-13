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
Based on your income, you have 3 dollars in the 1<sup>st</sup> tax bracket, 4 dollars in the 2<sup>nd</sup> tax bracket, and 3 dollars in the 3<sup>rd</sup> tax bracket.
The tax rate for the three tax brackets is 50%, 10%, and 25%, respectively.
In total, you pay $3 * 50% + $4 * 10% + $3 * 25% = $2.65 in taxes.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> brackets = [[1,0],[4,25],[5,50]], income = 2
<strong>Output:</strong> 0.25000
<strong>Explanation:</strong>
Based on your income, you have 1 dollar in the 1<sup>st</sup> tax bracket and 1 dollar in the 2<sup>nd</sup> tax bracket.
The tax rate for the two tax brackets is 0% and 25%, respectively.
In total, you pay $1 * 0% + $1 * 25% = $0.25 in taxes.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> brackets = [[2,50]], income = 0
<strong>Output:</strong> 0.00000
<strong>Explanation:</strong>
You have no income to tax, so you have to pay a total of $0 in taxes.
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
        while (income) {
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
function calculateTax(brackets: number[][], income: number): number {
    let ans = 0;
    let prev = 0;
    for (let [upper, percent] of brackets) {
        if (prev > income) break;
        ans += ((Math.min(upper, income) - prev) * percent) / 100;
        prev = upper;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
