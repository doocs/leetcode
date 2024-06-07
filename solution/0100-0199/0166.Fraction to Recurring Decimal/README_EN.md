---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0166.Fraction%20to%20Recurring%20Decimal/README_EN.md
tags:
    - Hash Table
    - Math
    - String
---

<!-- problem:start -->

# [166. Fraction to Recurring Decimal](https://leetcode.com/problems/fraction-to-recurring-decimal)

[中文文档](/solution/0100-0199/0166.Fraction%20to%20Recurring%20Decimal/README.md)

## Description

<!-- description:start -->

<p>Given two integers representing the <code>numerator</code> and <code>denominator</code> of a fraction, return <em>the fraction in string format</em>.</p>

<p>If the fractional part is repeating, enclose the repeating part in parentheses.</p>

<p>If multiple answers are possible, return <strong>any of them</strong>.</p>

<p>It is <strong>guaranteed</strong> that the length of the answer string is less than <code>10<sup>4</sup></code> for all the given inputs.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> numerator = 1, denominator = 2
<strong>Output:</strong> &quot;0.5&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> numerator = 2, denominator = 1
<strong>Output:</strong> &quot;2&quot;
</pre>

<p><strong class="example">Example 3:</strong></p>

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics + Hash Table

First, we check if the $numerator$ is $0$. If it is, we return `"0"` directly.

Next, we check if the $numerator$ and $denominator$ have different signs. If they do, the result is negative, and we set the first character of the result to `"-"`.

Then we take the absolute values of the $numerator$ and $denominator$, denoted as $a$ and $b$. Since the range of the numerator and denominator is $[-2^{31}, 2^{31} - 1]$, taking the absolute value directly may cause overflow, so we convert both $a$ and $b$ to long integers.

Next, we calculate the integer part, which is the integer part of $a$ divided by $b$, convert it to a string, and add it to the result. Then we take the remainder of $a$ divided by $b$, denoted as $a$.

If $a$ is $0$, it means the result is an integer, and we return the result directly.

Next, we calculate the decimal part. We use a hash table $d$ to record the length of the result corresponding to each remainder. We continuously multiply $a$ by $10$, then add the integer part of $a$ divided by $b$ to the result, then take the remainder of $a$ divided by $b$, denoted as $a$. If $a$ is $0$, it means the result is a finite decimal, and we return the result directly. If $a$ has appeared in the hash table, it means the result is a recurring decimal. We find the starting position of the cycle, insert the result into parentheses, and then return the result.

The time complexity is $O(l)$, and the space complexity is $O(l)$, where $l$ is the length of the result. In this problem, $l < 10^4$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def fractionToDecimal(self, numerator: int, denominator: int) -> str:
        if numerator == 0:
            return "0"
        ans = []
        neg = (numerator > 0) ^ (denominator > 0)
        if neg:
            ans.append("-")
        a, b = abs(numerator), abs(denominator)
        ans.append(str(a // b))
        a %= b
        if a == 0:
            return "".join(ans)
        ans.append(".")
        d = {}
        while a:
            d[a] = len(ans)
            a *= 10
            ans.append(str(a // b))
            a %= b
            if a in d:
                ans.insert(d[a], "(")
                ans.append(")")
                break
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        boolean neg = (numerator > 0) ^ (denominator > 0);
        sb.append(neg ? "-" : "");
        long a = Math.abs((long) numerator), b = Math.abs((long) denominator);
        sb.append(a / b);
        a %= b;
        if (a == 0) {
            return sb.toString();
        }
        sb.append(".");
        Map<Long, Integer> d = new HashMap<>();
        while (a != 0) {
            d.put(a, sb.length());
            a *= 10;
            sb.append(a / b);
            a %= b;
            if (d.containsKey(a)) {
                sb.insert(d.get(a), "(");
                sb.append(")");
                break;
            }
        }
        return sb.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        string ans;
        bool neg = (numerator > 0) ^ (denominator > 0);
        if (neg) {
            ans += "-";
        }
        long long a = abs(1LL * numerator), b = abs(1LL * denominator);
        ans += to_string(a / b);
        a %= b;
        if (a == 0) {
            return ans;
        }
        ans += ".";
        unordered_map<long long, int> d;
        while (a) {
            d[a] = ans.size();
            a *= 10;
            ans += to_string(a / b);
            a %= b;
            if (d.contains(a)) {
                ans.insert(d[a], "(");
                ans += ")";
                break;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func fractionToDecimal(numerator int, denominator int) string {
	if numerator == 0 {
		return "0"
	}
	ans := ""
	if (numerator > 0) != (denominator > 0) {
		ans += "-"
	}
	a := int64(numerator)
	b := int64(denominator)
	a = abs(a)
	b = abs(b)
	ans += strconv.FormatInt(a/b, 10)
	a %= b
	if a == 0 {
		return ans
	}
	ans += "."
	d := make(map[int64]int)
	for a != 0 {
		if pos, ok := d[a]; ok {
			ans = ans[:pos] + "(" + ans[pos:] + ")"
			break
		}
		d[a] = len(ans)
		a *= 10
		ans += strconv.FormatInt(a/b, 10)
		a %= b
	}
	return ans
}

func abs(x int64) int64 {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function fractionToDecimal(numerator: number, denominator: number): string {
    if (numerator === 0) {
        return '0';
    }
    const sb: string[] = [];
    const neg: boolean = numerator > 0 !== denominator > 0;
    sb.push(neg ? '-' : '');
    let a: number = Math.abs(numerator),
        b: number = Math.abs(denominator);
    sb.push(Math.floor(a / b).toString());
    a %= b;
    if (a === 0) {
        return sb.join('');
    }
    sb.push('.');
    const d: Map<number, number> = new Map();
    while (a !== 0) {
        d.set(a, sb.length);
        a *= 10;
        sb.push(Math.floor(a / b).toString());
        a %= b;
        if (d.has(a)) {
            sb.splice(d.get(a)!, 0, '(');
            sb.push(')');
            break;
        }
    }
    return sb.join('');
}
```

#### C#

```cs
public class Solution {
    public string FractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        bool neg = (numerator > 0) ^ (denominator > 0);
        sb.Append(neg ? "-" : "");
        long a = Math.Abs((long)numerator), b = Math.Abs((long)denominator);
        sb.Append(a / b);
        a %= b;
        if (a == 0) {
            return sb.ToString();
        }
        sb.Append(".");
        Dictionary<long, int> d = new Dictionary<long, int>();
        while (a != 0) {
            d[a] = sb.Length;
            a *= 10;
            sb.Append(a / b);
            a %= b;
            if (d.ContainsKey(a)) {
                sb.Insert(d[a], "(");
                sb.Append(")");
                break;
            }
        }
        return sb.ToString();
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
