# [1058. Minimize Rounding Error to Meet Target](https://leetcode.com/problems/minimize-rounding-error-to-meet-target)

[中文文档](/solution/1000-1099/1058.Minimize%20Rounding%20Error%20to%20Meet%20Target/README.md)

## Description

<p>Given an array of <code>prices</code> <code>[p<sub>1</sub>,p<sub>2</sub>...,p<sub>n</sub>]</code> and a <code>target</code>, round each price <code>p<sub>i</sub></code> to <code>Round<sub>i</sub>(p<sub>i</sub>)</code> so that the rounded array <code>[Round<sub>1</sub>(p<sub>1</sub>),Round<sub>2</sub>(p<sub>2</sub>)...,Round<sub>n</sub>(p<sub>n</sub>)]</code> sums to the given <code>target</code>. Each operation <code>Round<sub>i</sub>(p<sub>i</sub>)</code> could be either <code>Floor(p<sub>i</sub>)</code> or <code>Ceil(p<sub>i</sub>)</code>.</p>

<p>Return the string <code>&quot;-1&quot;</code> if the rounded array is impossible to sum to <code>target</code>. Otherwise, return the smallest rounding error, which is defined as <code>&Sigma; |Round<sub>i</sub>(p<sub>i</sub>) - (p<sub>i</sub>)|</code> for <italic><code>i</code></italic> from <code>1</code> to <italic><code>n</code></italic>, as a string with three places after the decimal.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [&quot;0.700&quot;,&quot;2.800&quot;,&quot;4.900&quot;], target = 8
<strong>Output:</strong> &quot;1.000&quot;
<strong>Explanation:</strong>
Use Floor, Ceil and Ceil operations to get (0.7 - 0) + (3 - 2.8) + (5 - 4.9) = 0.7 + 0.2 + 0.1 = 1.0 .
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [&quot;1.500&quot;,&quot;2.500&quot;,&quot;3.500&quot;], target = 10
<strong>Output:</strong> &quot;-1&quot;
<strong>Explanation:</strong> It is impossible to meet the target.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> prices = [&quot;1.500&quot;,&quot;2.500&quot;,&quot;3.500&quot;], target = 9
<strong>Output:</strong> &quot;1.500&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 500</code></li>
	<li>Each string&nbsp;<code>prices[i]</code> represents a real number in the range <code>[0.0, 1000.0]</code> and has exactly 3 decimal places.</li>
	<li><code>0 &lt;= target &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimizeError(self, prices: List[str], target: int) -> str:
        mi = 0
        arr = []
        for p in prices:
            p = float(p)
            mi += int(p)
            if d := p - int(p):
                arr.append(d)
        if not mi <= target <= mi + len(arr):
            return "-1"
        d = target - mi
        arr.sort(reverse=True)
        ans = d - sum(arr[:d]) + sum(arr[d:])
        return f'{ans:.3f}'
```

### **Java**

```java
class Solution {
    public String minimizeError(String[] prices, int target) {
        int mi = 0;
        List<Double> arr = new ArrayList<>();
        for (String p : prices) {
            double price = Double.valueOf(p);
            mi += (int) price;
            double d = price - (int) price;
            if (d > 0) {
                arr.add(d);
            }
        }
        if (target < mi || target > mi + arr.size()) {
            return "-1";
        }
        int d = target - mi;
        arr.sort(Collections.reverseOrder());
        double ans = d;
        for (int i = 0; i < d; ++i) {
            ans -= arr.get(i);
        }
        for (int i = d; i < arr.size(); ++i) {
            ans += arr.get(i);
        }
        DecimalFormat df = new DecimalFormat("#0.000");
        return df.format(ans);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string minimizeError(vector<string>& prices, int target) {
        int mi = 0;
        vector<double> arr;
        for (auto& p : prices) {
            double price = stod(p);
            mi += (int) price;
            double d = price - (int) price;
            if (d > 0) {
                arr.push_back(d);
            }
        }
        if (target < mi || target > mi + arr.size()) {
            return "-1";
        }
        int d = target - mi;
        sort(arr.rbegin(), arr.rend());
        double ans = d;
        for (int i = 0; i < d; ++i) {
            ans -= arr[i];
        }
        for (int i = d; i < arr.size(); ++i) {
            ans += arr[i];
        }
        string s = to_string(ans);
        return s.substr(0, s.find('.') + 4);
    }
};
```

### **Go**

```go
func minimizeError(prices []string, target int) string {
	arr := []float64{}
	mi := 0
	for _, p := range prices {
		price, _ := strconv.ParseFloat(p, 64)
		mi += int(math.Floor(price))
		d := price - float64(math.Floor(price))
		if d > 0 {
			arr = append(arr, d)
		}
	}
	if target < mi || target > mi+len(arr) {
		return "-1"
	}
	d := target - mi
	sort.Float64s(arr)
	ans := float64(d)
	for i := 0; i < d; i++ {
		ans -= arr[len(arr)-i-1]
	}
	for i := d; i < len(arr); i++ {
		ans += arr[len(arr)-i-1]
	}
	return fmt.Sprintf("%.3f", ans)
}
```

### **...**

```

```

<!-- tabs:end -->
