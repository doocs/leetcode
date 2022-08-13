# [166. Fraction to Recurring Decimal](https://leetcode.com/problems/fraction-to-recurring-decimal)

[中文文档](/solution/0100-0199/0166.Fraction%20to%20Recurring%20Decimal/README.md)

## Description

<p>Given two integers representing the <code>numerator</code> and <code>denominator</code> of a fraction, return <em>the fraction in string format</em>.</p>

<p>If the fractional part is repeating, enclose the repeating part in parentheses.</p>

<p>If multiple answers are possible, return <strong>any of them</strong>.</p>

<p>It is <strong>guaranteed</strong> that the length of the answer string is less than <code>10<sup>4</sup></code> for all the given inputs.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> numerator = 1, denominator = 2
<strong>Output:</strong> &quot;0.5&quot;
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> numerator = 2, denominator = 1
<strong>Output:</strong> &quot;2&quot;
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> numerator = 4, denominator = 333
<strong>Output:</strong> &quot;0.(012)&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;=&nbsp;numerator, denominator &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>denominator != 0</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def fractionToDecimal(self, numerator: int, denominator: int) -> str:
        if numerator == 0:
            return '0'
        res = []
        neg = (numerator > 0) ^ (denominator > 0)
        if neg:
            res.append('-')
        num, d = abs(numerator), abs(denominator)
        res.append(str(num // d))
        num %= d
        if num == 0:
            return ''.join(res)
        res.append('.')
        mp = {}
        while num != 0:
            mp[num] = len(res)
            num *= 10
            res.append(str(num // d))
            num %= d
            if num in mp:
                idx = mp[num]
                res.insert(idx, '(')
                res.append(')')
                break
        return ''.join(res)
```

### **Java**

```java
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        boolean neg = (numerator > 0) ^ (denominator > 0);
        sb.append(neg ? "-" : "");
        long num = Math.abs((long) numerator);
        long d = Math.abs((long) denominator);
        sb.append(num / d);
        num %= d;
        if (num == 0) {
            return sb.toString();
        }
        sb.append(".");
        Map<Long, Integer> mp = new HashMap<>();
        while (num != 0) {
            mp.put(num, sb.length());
            num *= 10;
            sb.append(num / d);
            num %= d;
            if (mp.containsKey(num)) {
                int idx = mp.get(num);
                sb.insert(idx, "(");
                sb.append(")");
                break;
            }
        }
        return sb.toString();
    }
}
```

### **C++**

```cpp
using LL = long long;

class Solution {
public:
    string fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        string res = "";
        bool neg = (numerator > 0) ^ (denominator > 0);
        if (neg) res += "-";
        LL num = abs(numerator);
        LL d = abs(denominator);
        res += to_string(num / d);
        num %= d;
        if (num == 0) return res;
        res += ".";
        unordered_map<LL, int> mp;
        while (num) {
            mp[num] = res.size();
            num *= 10;
            res += to_string(num / d);
            num %= d;
            if (mp.count(num)) {
                int idx = mp[num];
                res.insert(idx, "(");
                res += ")";
                break;
            }
        }
        return res;
    }
};
```

### **Go**

```go
func fractionToDecimal(numerator int, denominator int) string {
	if numerator == 0 {
		return "0"
	}
	res := []byte{}
	neg := numerator*denominator < 0
	if neg {
		res = append(res, '-')
	}
	num := abs(numerator)
	d := abs(denominator)
	res = append(res, strconv.Itoa(num/d)...)
	num %= d
	if num == 0 {
		return string(res)
	}
	mp := make(map[int]int)
	res = append(res, '.')
	for num != 0 {
		mp[num] = len(res)
		num *= 10
		res = append(res, strconv.Itoa(num/d)...)
		num %= d
		if mp[num] > 0 {
			idx := mp[num]
			res = append(res[:idx], append([]byte{'('}, res[idx:]...)...)
			res = append(res, ')')
			break
		}
	}

	return string(res)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
