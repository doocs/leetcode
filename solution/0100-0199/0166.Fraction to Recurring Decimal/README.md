# [166. 分数到小数](https://leetcode.cn/problems/fraction-to-recurring-decimal)

[English Version](/solution/0100-0199/0166.Fraction%20to%20Recurring%20Decimal/README_EN.md)

<!-- tags:哈希表,数学,字符串 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个整数，分别表示分数的分子&nbsp;<code>numerator</code> 和分母 <code>denominator</code>，以 <strong>字符串形式返回小数</strong> 。</p>

<p>如果小数部分为循环小数，则将循环的部分括在括号内。</p>

<p>如果存在多个答案，只需返回 <strong>任意一个</strong> 。</p>

<p>对于所有给定的输入，<strong>保证</strong> 答案字符串的长度小于 <code>10<sup>4</sup></code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>numerator = 1, denominator = 2
<strong>输出：</strong>"0.5"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>numerator = 2, denominator = 1
<strong>输出：</strong>"2"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>numerator = 4, denominator = 333
<strong>输出：</strong>"0.(012)"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;=&nbsp;numerator, denominator &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>denominator != 0</code></li>
</ul>

## 解法

### 方法一：数学 + 哈希表

我们首先判断 $numerator$ 是否为 $0$，如果是，则直接返回 `"0"`。

接着我们判断 $numerator$ 和 $denominator$ 是否异号，如果是，则结果为负数，我们将结果的第一个字符设为 `"-"`。

然后我们将 $numerator$ 和 $denominator$ 取绝对值，分别记为 $a$ 和 $b$。由于分子和分母的范围为 $[-2^{31}, 2^{31} - 1]$，直接取绝对值可能会溢出，因此我们将 $a$ 和 $b$ 都转换为长整型。

接着我们计算整数部分，即 $a$ 除以 $b$ 的整数部分，将其转换为字符串并添加到结果中。然后我们将 $a$ 取余 $b$，记为 $a$。

如果 $a$ 为 $0$，说明结果为整数，直接返回结果。

接着我们计算小数部分，我们使用哈希表 $d$ 记录每个余数对应的结果的长度。我们不断将 $a$ 乘以 $10$，然后将 $a$ 除以 $b$ 的整数部分添加到结果中，然后将 $a$ 取余 $b$，记为 $a$。如果 $a$ 为 $0$，说明结果为有限小数，直接返回结果。如果 $a$ 在哈希表中出现过，说明结果为循环小数，我们找到循环的起始位置，将结果插入括号中，然后返回结果。

时间复杂度 $O(l)$，空间复杂度 $O(l)$，其中 $l$ 为结果的长度，本题中 $l < 10^4$。

<!-- tabs:start -->

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

<!-- end -->
