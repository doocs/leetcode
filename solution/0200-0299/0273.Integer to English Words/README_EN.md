---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0273.Integer%20to%20English%20Words/README_EN.md
tags:
    - Recursion
    - Math
    - String
---

<!-- problem:start -->

# [273. Integer to English Words](https://leetcode.com/problems/integer-to-english-words)

[中文文档](/solution/0200-0299/0273.Integer%20to%20English%20Words/README.md)

## Description

<!-- description:start -->

<p>Convert a non-negative integer <code>num</code> to its English words representation.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 123
<strong>Output:</strong> &quot;One Hundred Twenty Three&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 12345
<strong>Output:</strong> &quot;Twelve Thousand Three Hundred Forty Five&quot;
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = 1234567
<strong>Output:</strong> &quot;One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= num &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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
    private static Map<Integer, String> map;

    static {
        map = new HashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "Ten");
        map.put(11, "Eleven");
        map.put(12, "Twelve");
        map.put(13, "Thirteen");
        map.put(14, "Fourteen");
        map.put(15, "Fifteen");
        map.put(16, "Sixteen");
        map.put(17, "Seventeen");
        map.put(18, "Eighteen");
        map.put(19, "Nineteen");
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");
        map.put(60, "Sixty");
        map.put(70, "Seventy");
        map.put(80, "Eighty");
        map.put(90, "Ninety");
        map.put(100, "Hundred");
        map.put(1000, "Thousand");
        map.put(1000000, "Million");
        map.put(1000000000, "Billion");
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1000000000; i >= 1000; i /= 1000) {
            if (num >= i) {
                sb.append(get3Digits(num / i)).append(' ').append(map.get(i));
                num %= i;
            }
        }
        if (num > 0) {
            sb.append(get3Digits(num));
        }
        return sb.substring(1);
    }

    private String get3Digits(int num) {
        StringBuilder sb = new StringBuilder();
        if (num >= 100) {
            sb.append(' ').append(map.get(num / 100)).append(' ').append(map.get(100));
            num %= 100;
        }
        if (num > 0) {
            if (num < 20 || num % 10 == 0) {
                sb.append(' ').append(map.get(num));
            } else {
                sb.append(' ').append(map.get(num / 10 * 10)).append(' ').append(map.get(num % 10));
            }
        }
        return sb.toString();
    }
}
```

#### C#

```cs
using System.Collections.Generic;
using System.Linq;

public class Solution {
    private string[] bases = { "Thousand", "Million", "Billion" };
    public string NumberToWords(int num) {
        if (num == 0)
        {
            return "Zero";
        }
        var baseIndex = -1;
        var parts = new List<string>();
        while (num > 0)
        {
            var part = NumberToWordsInternal(num % 1000);
            if (part.Length > 0 && baseIndex >= 0)
            {
                part = JoinParts(part, bases[baseIndex]);
            }
            parts.Add(part);
            baseIndex++;
            num /= 1000;
        }
        parts.Reverse();
        return JoinParts(parts);
    }

    private string JoinParts(IEnumerable<string> parts)
    {
        return string.Join(" ", parts.Where(p => p.Length > 0));
    }

    private string JoinParts(params string[] parts)
    {
        return JoinParts((IEnumerable<string>)parts);
    }

    private string NumberToWordsInternal(int num)
    {
        switch(num)
        {
            case 0: return "";
            case 1: return "One";
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
            case 10: return "Ten";
            case 11: return "Eleven";
            case 12: return "Twelve";
            case 13: return "Thirteen";
            case 14: return "Fourteen";
            case 15: return "Fifteen";
            case 16: return "Sixteen";
            case 17: return "Seventeen";
            case 18: return "Eighteen";
            case 19: return "Nineteen";
        }

        if (num < 100)
        {
            string part1;
            switch (num/10)
            {
                case 2: part1 = "Twenty"; break;
                case 3: part1 = "Thirty"; break;
                case 4: part1 = "Forty"; break;
                case 5: part1 = "Fifty"; break;
                case 6: part1 = "Sixty"; break;
                case 7: part1 = "Seventy"; break;
                case 8: part1 = "Eighty"; break;
                case 9: default: part1 = "Ninety"; break;
            }
            var part2 = NumberToWordsInternal(num % 10);
            return JoinParts(part1, part2);
        }

        {
            var part1 = NumberToWordsInternal(num / 100);
            var part2 = NumberToWordsInternal(num % 100);
            return JoinParts(part1, "Hundred", part2);
        }
    }
}
```

#### TypeScript

```ts
function numberToWords(num: number): string {
    if (num === 0) return 'Zero';

    // prettier-ignore
    const f = (x: number): string => {
    const dict1 = ['','One','Two','Three','Four','Five','Six','Seven','Eight','Nine','Ten','Eleven','Twelve','Thirteen','Fourteen','Fifteen','Sixteen','Seventeen','Eighteen','Nineteen',]
    const dict2 = ['','','Twenty','Thirty','Forty','Fifty','Sixty','Seventy','Eighty','Ninety',]
    let ans = ''

    if (x <= 19) ans = dict1[x] ?? ''
    else if (x < 100) ans = `${dict2[Math.floor(x / 10)]} ${f(x % 10)}`
    else if (x < 10 ** 3) ans = `${dict1[Math.floor(x / 100)]} Hundred ${f(x % 100)}`
    else if (x < 10 ** 6) ans = `${f(Math.floor(x / 10 ** 3))} Thousand ${f(x % 10 ** 3)}`
    else if (x < 10 ** 9) ans = `${f(Math.floor(x / 10 ** 6))} Million ${f(x % 10 ** 6)}`
    else ans = `${f(Math.floor(x / 10 ** 9))} Billion ${f(x % 10 ** 9)}`

    return ans.trim()
  }

    return f(num);
}
```

#### JavaScript

```js
function numberToWords(num) {
    if (num === 0) return 'Zero';

    // prettier-ignore
    const f = (x) => {
    const dict1 = ['','One','Two','Three','Four','Five','Six','Seven','Eight','Nine','Ten','Eleven','Twelve','Thirteen','Fourteen','Fifteen','Sixteen','Seventeen','Eighteen','Nineteen',]
    const dict2 = ['','','Twenty','Thirty','Forty','Fifty','Sixty','Seventy','Eighty','Ninety',]
    let ans = ''

    if (x <= 19) ans = dict1[x] ?? ''
    else if (x < 100) ans = `${dict2[Math.floor(x / 10)]} ${f(x % 10)}`
    else if (x < 10 ** 3) ans = `${dict1[Math.floor(x / 100)]} Hundred ${f(x % 100)}`
    else if (x < 10 ** 6) ans = `${f(Math.floor(x / 10 ** 3))} Thousand ${f(x % 10 ** 3)}`
    else if (x < 10 ** 9) ans = `${f(Math.floor(x / 10 ** 6))} Million ${f(x % 10 ** 6)}`
    else ans = `${f(Math.floor(x / 10 ** 9))} Billion ${f(x % 10 ** 9)}`

    return ans.trim()
  }

    return f(num);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
