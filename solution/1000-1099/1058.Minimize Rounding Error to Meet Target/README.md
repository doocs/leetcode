# [1058. 最小化舍入误差以满足目标](https://leetcode.cn/problems/minimize-rounding-error-to-meet-target)

[English Version](/solution/1000-1099/1058.Minimize%20Rounding%20Error%20to%20Meet%20Target/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一系列价格&nbsp;<code>[p<sub>1</sub>,p<sub>2</sub>...,p<sub>n</sub>]</code>&nbsp;和一个目标&nbsp;<code>target</code>，将每个价格&nbsp;<code>p<sub>i</sub></code>&nbsp;舍入为&nbsp;<code>Round<sub>i</sub>(p<sub>i</sub>)</code>&nbsp;以使得舍入数组&nbsp;<code>[Round<sub>1</sub>(p<sub>1</sub>),Round<sub>2</sub>(p<sub>2</sub>)...,Round<sub>n</sub>(p<sub>n</sub>)]</code>&nbsp;之和达到给定的目标值&nbsp;<code>target</code>。每次舍入操作&nbsp;<code>Round<sub>i</sub>(p<sub>i</sub>)</code>&nbsp;可以是向下舍&nbsp;<code>Floor(p<sub>i</sub>)</code>&nbsp;也可以是向上入&nbsp;<code>Ceil(p<sub>i</sub>)</code>。</p>

<p>如果舍入数组之和无论如何都无法达到目标值&nbsp;<code>target</code>，就返回字符串&nbsp;<code>"-1"</code>。否则，以保留到<strong>小数点后三位</strong>的字符串格式返回最小的舍入误差，其定义为 <code>Σ |Round<sub>i</sub>(p<sub>i</sub>) - (p<sub>i</sub>)|</code>（&nbsp;i 从 1 到 n ）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>prices = ["0.700","2.800","4.900"], target = 8
<strong>输出：</strong>"1.000"
<strong>解释： </strong>
使用 Floor，Ceil 和 Ceil 操作得到 (0.7 - 0) + (3 - 2.8) + (5 - 4.9) = 0.7 + 0.2 + 0.1 = 1.0 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>prices = ["1.500","2.500","3.500"], target = 10
<strong>输出：</strong>"-1"
<strong>解释：</strong>
达到目标是不可能的。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>prices = ["1.500","2.500","3.500"], target = 9
<strong>输出：</strong>"1.500"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 500</code></li>
	<li>表示价格的每个字符串&nbsp;<code>prices[i]</code>&nbsp;都代表一个介于 <code>[0.0,&nbsp;1000.0]</code> 之间的实数，并且正好有 3 个小数位。</li>
	<li><code>target</code>&nbsp;介于&nbsp;0 和 1000000&nbsp;之间。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 排序**

遍历价格数组 `prices`，先将每个价格 $p$ 向下舍入，累加到 `mi` 中，同时将每个价格的小数点部分添加到数组 `arr` 中。

遍历结束后，判断 `target` 是否在 `mi` 和 `mi + arr.length` 之间，如果不在，直接返回 `"-1"`。

接下来，我们计算 `target - mi`，即需要向上入的价格个数，然后将 `arr` 从大到小排序，从前往后遍历，将前 `target - mi` 个价格向上入，其余价格向下舍入，累计到 `ans` 中。

时间复杂度 $O(n\log n)$。其中 $n$ 为 `prices` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
