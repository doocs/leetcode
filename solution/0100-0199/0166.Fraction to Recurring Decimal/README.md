# [166. 分数到小数](https://leetcode.cn/problems/fraction-to-recurring-decimal)

[English Version](/solution/0100-0199/0166.Fraction%20to%20Recurring%20Decimal/README_EN.md)

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

### 方法一

<!-- tabs:start -->

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

```cs
﻿// https://leetcode.com/problems/fraction-to-recurring-decimal/

using System.Collections.Generic;
using System.Text;

public partial class Solution
{
    public string FractionToDecimal(int numerator, int denominator)
    {
        var n = (long)numerator;
        var d = (long)denominator;
        var sb = new StringBuilder();
        if (n < 0)
        {
            n = -n;
            if (d < 0)
            {
                d = -d;
            }
            else
            {
                sb.Append('-');
            }
        }
        else if (n > 0 && d < 0)
        {
            d = -d;
            sb.Append('-');
        }

        sb.Append(n / d);
        n = n % d;
        if (n != 0)
        {
            sb.Append('.');
            var dict = new Dictionary<long, int>();
            while (n != 0)
            {
                int index;
                if (dict.TryGetValue(n, out index))
                {
                    sb.Insert(index, '(');
                    sb.Append(')');
                    break;
                }
                else
                {
                    dict.Add(n, sb.Length);
                    n *= 10;
                    sb.Append(n / d);
                    n %= d;
                }
            }
        }
        return sb.ToString();
    }
}
```

<!-- tabs:end -->

<!-- end -->
