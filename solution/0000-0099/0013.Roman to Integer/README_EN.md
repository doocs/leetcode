---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0013.Roman%20to%20Integer/README_EN.md
tags:
    - Hash Table
    - Math
    - String
---

# [13. Roman to Integer](https://leetcode.com/problems/roman-to-integer)

[中文文档](/solution/0000-0099/0013.Roman%20to%20Integer/README.md)

## Description

<p>Roman numerals are represented by seven different symbols:&nbsp;<code>I</code>, <code>V</code>, <code>X</code>, <code>L</code>, <code>C</code>, <code>D</code> and <code>M</code>.</p>

<pre>
<strong>Symbol</strong>       <strong>Value</strong>
I             1
V             5
X             10
L             50
C             100
D             500
M             1000</pre>

<p>For example,&nbsp;<code>2</code> is written as <code>II</code>&nbsp;in Roman numeral, just two ones added together. <code>12</code> is written as&nbsp;<code>XII</code>, which is simply <code>X + II</code>. The number <code>27</code> is written as <code>XXVII</code>, which is <code>XX + V + II</code>.</p>

<p>Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not <code>IIII</code>. Instead, the number four is written as <code>IV</code>. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as <code>IX</code>. There are six instances where subtraction is used:</p>

<ul>
	<li><code>I</code> can be placed before <code>V</code> (5) and <code>X</code> (10) to make 4 and 9.&nbsp;</li>
	<li><code>X</code> can be placed before <code>L</code> (50) and <code>C</code> (100) to make 40 and 90.&nbsp;</li>
	<li><code>C</code> can be placed before <code>D</code> (500) and <code>M</code> (1000) to make 400 and 900.</li>
</ul>

<p>Given a roman numeral, convert it to an integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;III&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> III = 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;LVIII&quot;
<strong>Output:</strong> 58
<strong>Explanation:</strong> L = 50, V= 5, III = 3.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;MCMXCIV&quot;
<strong>Output:</strong> 1994
<strong>Explanation:</strong> M = 1000, CM = 900, XC = 90 and IV = 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 15</code></li>
	<li><code>s</code> contains only&nbsp;the characters <code>(&#39;I&#39;, &#39;V&#39;, &#39;X&#39;, &#39;L&#39;, &#39;C&#39;, &#39;D&#39;, &#39;M&#39;)</code>.</li>
	<li>It is <strong>guaranteed</strong>&nbsp;that <code>s</code> is a valid roman numeral in the range <code>[1, 3999]</code>.</li>
</ul>

## Solutions

### Solution 1: Hash Table + Simulation

First, we use a hash table $d$ to record the numerical value corresponding to each character. Then, we traverse the string $s$ from left to right. If the numerical value corresponding to the current character is less than the numerical value corresponding to the character on the right, we subtract the numerical value corresponding to the current character. Otherwise, we add the numerical value corresponding to the current character.

The time complexity is $O(n)$, and the space complexity is $O(m)$. Here, $n$ and $m$ are the length of the string $s$ and the size of the character set, respectively.

<!-- tabs:start -->

```python
class Solution:
    def romanToInt(self, s: str) -> int:
        d = {'I': 1, 'V': 5, 'X': 10, 'L': 50, 'C': 100, 'D': 500, 'M': 1000}
        return sum((-1 if d[a] < d[b] else 1) * d[a] for a, b in pairwise(s)) + d[s[-1]]
```

```java
class Solution {
    public int romanToInt(String s) {
        String cs = "IVXLCDM";
        int[] vs = {1, 5, 10, 50, 100, 500, 1000};
        Map<Character, Integer> d = new HashMap<>();
        for (int i = 0; i < vs.length; ++i) {
            d.put(cs.charAt(i), vs[i]);
        }
        int n = s.length();
        int ans = d.get(s.charAt(n - 1));
        for (int i = 0; i < n - 1; ++i) {
            int sign = d.get(s.charAt(i)) < d.get(s.charAt(i + 1)) ? -1 : 1;
            ans += sign * d.get(s.charAt(i));
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int romanToInt(string s) {
        unordered_map<char, int> nums{
            {'I', 1},
            {'V', 5},
            {'X', 10},
            {'L', 50},
            {'C', 100},
            {'D', 500},
            {'M', 1000},
        };
        int ans = nums[s.back()];
        for (int i = 0; i < s.size() - 1; ++i) {
            int sign = nums[s[i]] < nums[s[i + 1]] ? -1 : 1;
            ans += sign * nums[s[i]];
        }
        return ans;
    }
};
```

```go
func romanToInt(s string) (ans int) {
	d := map[byte]int{'I': 1, 'V': 5, 'X': 10, 'L': 50, 'C': 100, 'D': 500, 'M': 1000}
	for i := 0; i < len(s)-1; i++ {
		if d[s[i]] < d[s[i+1]] {
			ans -= d[s[i]]
		} else {
			ans += d[s[i]]
		}
	}
	ans += d[s[len(s)-1]]
	return
}
```

```ts
function romanToInt(s: string): number {
    const d: Map<string, number> = new Map([
        ['I', 1],
        ['V', 5],
        ['X', 10],
        ['L', 50],
        ['C', 100],
        ['D', 500],
        ['M', 1000],
    ]);
    let ans: number = d.get(s[s.length - 1])!;
    for (let i = 0; i < s.length - 1; ++i) {
        const sign = d.get(s[i])! < d.get(s[i + 1])! ? -1 : 1;
        ans += sign * d.get(s[i])!;
    }
    return ans;
}
```

```js
const romanToInt = function (s) {
    const d = {
        I: 1,
        V: 5,
        X: 10,
        L: 50,
        C: 100,
        D: 500,
        M: 1000,
    };
    let ans = d[s[s.length - 1]];
    for (let i = 0; i < s.length - 1; ++i) {
        const sign = d[s[i]] < d[s[i + 1]] ? -1 : 1;
        ans += sign * d[s[i]];
    }
    return ans;
};
```

```cs
public class Solution {
    public int RomanToInt(string s) {
        Dictionary<char, int> d = new Dictionary<char, int>();
        d.Add('I', 1);
        d.Add('V', 5);
        d.Add('X', 10);
        d.Add('L', 50);
        d.Add('C', 100);
        d.Add('D', 500);
        d.Add('M', 1000);
        int ans = d[s[s.Length - 1]];
        for (int i = 0; i < s.Length - 1; ++i) {
            int sign = d[s[i]] < d[s[i + 1]] ? -1 : 1;
            ans += sign * d[s[i]];
        }
        return ans;
    }
}
```

```php
class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function romanToInt($s) {
        $hashmap = [
            'I' => 1,
            'V' => 5,
            'X' => 10,
            'L' => 50,
            'C' => 100,
            'D' => 500,
            'M' => 1000,
        ];
        $rs = 0;
        for ($i = 0; $i < strlen($s); $i++) {
            $left = $hashmap[$s[$i]];
            $right = $hashmap[$s[$i + 1]];
            if ($left >= $right) {
                $rs += $left;
            } else {
                $rs -= $left;
            }
        }
        return $rs;
    }
}
```

```rb
# @param {String} s
# @return {Integer}
def roman_to_int(s)
  hash = Hash[
      'I' => 1,
      'V' => 5,
      'X' => 10,
      'L' => 50,
      'C' => 100,
      'D' => 500,
      'M' => 1000,
      'IV' => 4,
      'IX' => 9,
      'XL' => 40,
      'XC' => 90,
      'CD' => 400,
      'CM' => 900
  ]
  res = 0
  i = 0
  while i < s.length
    if i < s.length - 1 && !hash[s[i..i+1]].nil?
      res += hash[s[i..i+1]]
      i += 2
    else
      res += hash[s[i]]
      i += 1
    end
  end

  res
end
```

<!-- tabs:end -->

<!-- end -->
