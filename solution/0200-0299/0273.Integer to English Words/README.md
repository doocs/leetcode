---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0273.Integer%20to%20English%20Words/README.md
tags:
    - 递归
    - 数学
    - 字符串
---

<!-- problem:start -->

# [273. 整数转换英文表示](https://leetcode.cn/problems/integer-to-english-words)

[English Version](/solution/0200-0299/0273.Integer%20to%20English%20Words/README_EN.md)

## 题目描述

<!-- description:start -->

<p>将非负整数 <code>num</code> 转换为其对应的英文表示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num = 123
<strong>输出：</strong>"One Hundred Twenty Three"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num = 12345
<strong>输出：</strong>"Twelve Thousand Three Hundred Forty Five"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>num = 1234567
<strong>输出：</strong>"One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= num &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberToWords(self, num: int) -> str:
        if num == 0:
            return 'Zero'

        lt20 = [
            '',
            'One',
            'Two',
            'Three',
            'Four',
            'Five',
            'Six',
            'Seven',
            'Eight',
            'Nine',
            'Ten',
            'Eleven',
            'Twelve',
            'Thirteen',
            'Fourteen',
            'Fifteen',
            'Sixteen',
            'Seventeen',
            'Eighteen',
            'Nineteen',
        ]
        tens = [
            '',
            'Ten',
            'Twenty',
            'Thirty',
            'Forty',
            'Fifty',
            'Sixty',
            'Seventy',
            'Eighty',
            'Ninety',
        ]
        thousands = ['Billion', 'Million', 'Thousand', '']

        def transfer(num):
            if num == 0:
                return ''
            if num < 20:
                return lt20[num] + ' '
            if num < 100:
                return tens[num // 10] + ' ' + transfer(num % 10)
            return lt20[num // 100] + ' Hundred ' + transfer(num % 100)

        res = []
        i, j = 1000000000, 0
        while i > 0:
            if num // i != 0:
                res.append(transfer(num // i))
                res.append(thousands[j])
                res.append(' ')
                num %= i
            j += 1
            i //= 1000
        return ''.join(res).strip()
```

#### Java

```java
class Solution {
    private String[] lt20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
        "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
        "Seventeen", "Eighteen", "Nineteen"};
    private String[] tens
        = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private String[] thousands = {"Billion", "Million", "Thousand", ""};

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1000000000, j = 0; i > 0; i /= 1000, ++j) {
            if (num / i == 0) {
                continue;
            }
            sb.append(transfer(num / i)).append(thousands[j]).append(' ');
            num %= i;
        }
        return sb.toString().trim();
    }

    private String transfer(int num) {
        if (num == 0) {
            return "";
        }
        if (num < 20) {
            return lt20[num] + " ";
        }
        if (num < 100) {
            return tens[num / 10] + " " + transfer(num % 10);
        }
        return lt20[num / 100] + " Hundred " + transfer(num % 100);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> lt20 = {
        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
        "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
        "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    vector<string> tens = {
        "", "Ten", "Twenty", "Thirty", "Forty", "Fifty",
        "Sixty", "Seventy", "Eighty", "Ninety"};

    vector<string> thousands = {"Billion", "Million", "Thousand", ""};

    string numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        string res;
        for (int i = 1000000000, j = 0; i > 0; i /= 1000, ++j) {
            int cur = num / i;
            if (cur == 0) {
                continue;
            }
            if (!res.empty()) {
                res += ' ';
            }
            res += transfer(cur);
            if (!thousands[j].empty()) {
                res += ' ';
                res += thousands[j];
            }
            num %= i;
        }
        return res;
    }

private:
    string transfer(int num) {
        if (num == 0) {
            return "";
        }
        if (num < 20) {
            return lt20[num];
        }
        if (num < 100) {
            if (num % 10 == 0) {
                return tens[num / 10];
            }
            return tens[num / 10] + " " + transfer(num % 10);
        }
        if (num % 100 == 0) {
            return lt20[num / 100] + " Hundred";
        }
        return lt20[num / 100] + " Hundred " + transfer(num % 100);
    }
};
```

#### Go

```go
func numberToWords(num int) string {
	if num == 0 {
		return "Zero"
	}

	lt20 := []string{
		"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
		"Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
		"Sixteen", "Seventeen", "Eighteen", "Nineteen",
	}
	tens := []string{
		"", "Ten", "Twenty", "Thirty", "Forty", "Fifty",
		"Sixty", "Seventy", "Eighty", "Ninety",
	}
	thousands := []string{"Billion", "Million", "Thousand", ""}

	var transfer func(int) string
	transfer = func(num int) string {
		if num == 0 {
			return ""
		}
		if num < 20 {
			return lt20[num]
		}
		if num < 100 {
			if num%10 == 0 {
				return tens[num/10]
			}
			return tens[num/10] + " " + transfer(num%10)
		}
		if num%100 == 0 {
			return lt20[num/100] + " Hundred"
		}
		return lt20[num/100] + " Hundred " + transfer(num%100)
	}

	res := ""
	for i, j := 1000000000, 0; i > 0; i, j = i/1000, j+1 {
		cur := num / i
		if cur == 0 {
			continue
		}
		if res != "" {
			res += " "
		}
		res += transfer(cur)
		if thousands[j] != "" {
			res += " " + thousands[j]
		}
		num %= i
	}
	return res
}
```

#### TypeScript

```ts
function numberToWords(num: number): string {
    if (num === 0) {
        return 'Zero';
    }

    const lt20 = [
        '',
        'One',
        'Two',
        'Three',
        'Four',
        'Five',
        'Six',
        'Seven',
        'Eight',
        'Nine',
        'Ten',
        'Eleven',
        'Twelve',
        'Thirteen',
        'Fourteen',
        'Fifteen',
        'Sixteen',
        'Seventeen',
        'Eighteen',
        'Nineteen',
    ];

    const tens = [
        '',
        'Ten',
        'Twenty',
        'Thirty',
        'Forty',
        'Fifty',
        'Sixty',
        'Seventy',
        'Eighty',
        'Ninety',
    ];

    const thousands = ['Billion', 'Million', 'Thousand', ''];

    const transfer = (n: number): string => {
        if (n === 0) {
            return '';
        }
        if (n < 20) {
            return lt20[n];
        }
        if (n < 100) {
            if (n % 10 === 0) {
                return tens[Math.floor(n / 10)];
            }
            return tens[Math.floor(n / 10)] + ' ' + transfer(n % 10);
        }
        if (n % 100 === 0) {
            return lt20[Math.floor(n / 100)] + ' Hundred';
        }
        return lt20[Math.floor(n / 100)] + ' Hundred ' + transfer(n % 100);
    };

    let res = '';
    for (let i = 1_000_000_000, j = 0; i > 0; i = Math.floor(i / 1000), ++j) {
        const cur = Math.floor(num / i);
        if (cur === 0) {
            continue;
        }
        if (res !== '') {
            res += ' ';
        }
        res += transfer(cur);
        if (thousands[j] !== '') {
            res += ' ' + thousands[j];
        }
        num %= i;
    }
    return res;
}
```

#### JavaScript

```js
/**
 * @param {number} num
 * @return {string}
 */
var numberToWords = function (num) {
    if (num === 0) {
        return 'Zero';
    }

    const lt20 = [
        '',
        'One',
        'Two',
        'Three',
        'Four',
        'Five',
        'Six',
        'Seven',
        'Eight',
        'Nine',
        'Ten',
        'Eleven',
        'Twelve',
        'Thirteen',
        'Fourteen',
        'Fifteen',
        'Sixteen',
        'Seventeen',
        'Eighteen',
        'Nineteen',
    ];

    const tens = [
        '',
        'Ten',
        'Twenty',
        'Thirty',
        'Forty',
        'Fifty',
        'Sixty',
        'Seventy',
        'Eighty',
        'Ninety',
    ];

    const thousands = ['Billion', 'Million', 'Thousand', ''];

    const transfer = n => {
        if (n === 0) {
            return '';
        }
        if (n < 20) {
            return lt20[n];
        }
        if (n < 100) {
            if (n % 10 === 0) {
                return tens[Math.floor(n / 10)];
            }
            return tens[Math.floor(n / 10)] + ' ' + transfer(n % 10);
        }
        if (n % 100 === 0) {
            return lt20[Math.floor(n / 100)] + ' Hundred';
        }
        return lt20[Math.floor(n / 100)] + ' Hundred ' + transfer(n % 100);
    };

    let res = '';
    for (let i = 1_000_000_000, j = 0; i > 0; i = Math.floor(i / 1000), ++j) {
        const cur = Math.floor(num / i);
        if (cur === 0) {
            continue;
        }
        if (res !== '') {
            res += ' ';
        }
        res += transfer(cur);
        if (thousands[j] !== '') {
            res += ' ' + thousands[j];
        }
        num %= i;
    }
    return res;
};
```

#### C#

```cs
public class Solution {
    private readonly string[] lt20 = {
        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
        "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
        "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private readonly string[] tens = {
        "", "Ten", "Twenty", "Thirty", "Forty", "Fifty",
        "Sixty", "Seventy", "Eighty", "Ninety"
    };

    private readonly string[] thousands = { "Billion", "Million", "Thousand", "" };

    public string NumberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        string res = "";
        for (int i = 1000000000, j = 0; i > 0; i /= 1000, ++j) {
            int cur = num / i;
            if (cur == 0) {
                continue;
            }
            if (res.Length > 0) {
                res += " ";
            }
            res += Transfer(cur);
            if (thousands[j].Length > 0) {
                res += " " + thousands[j];
            }
            num %= i;
        }
        return res;
    }

    private string Transfer(int num) {
        if (num == 0) {
            return "";
        }
        if (num < 20) {
            return lt20[num];
        }
        if (num < 100) {
            if (num % 10 == 0) {
                return tens[num / 10];
            }
            return tens[num / 10] + " " + Transfer(num % 10);
        }
        if (num % 100 == 0) {
            return lt20[num / 100] + " Hundred";
        }
        return lt20[num / 100] + " Hundred " + Transfer(num % 100);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
