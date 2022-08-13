# [12. 整数转罗马数字](https://leetcode.cn/problems/integer-to-roman)

[English Version](/solution/0000-0099/0012.Integer%20to%20Roman/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>罗马数字包含以下七种字符： <code>I</code>， <code>V</code>， <code>X</code>， <code>L</code>，<code>C</code>，<code>D</code> 和 <code>M</code>。</p>

<pre>
<strong>字符</strong>          <strong>数值</strong>
I             1
V             5
X             10
L             50
C             100
D             500
M             1000</pre>

<p>例如， 罗马数字 2 写做 <code>II</code> ，即为两个并列的 1。12 写做 <code>XII</code> ，即为 <code>X</code> + <code>II</code> 。 27 写做  <code>XXVII</code>, 即为 <code>XX</code> + <code>V</code> + <code>II</code> 。</p>

<p>通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 <code>IIII</code>，而是 <code>IV</code>。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 <code>IX</code>。这个特殊的规则只适用于以下六种情况：</p>

<ul>
	<li><code>I</code> 可以放在 <code>V</code> (5) 和 <code>X</code> (10) 的左边，来表示 4 和 9。</li>
	<li><code>X</code> 可以放在 <code>L</code> (50) 和 <code>C</code> (100) 的左边，来表示 40 和 90。 </li>
	<li><code>C</code> 可以放在 <code>D</code> (500) 和 <code>M</code> (1000) 的左边，来表示 400 和 900。</li>
</ul>

<p>给你一个整数，将其转为罗马数字。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> num = 3
<strong>输出:</strong> "III"</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> num = 4
<strong>输出:</strong> "IV"</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> num = 9
<strong>输出:</strong> "IX"</pre>

<p><strong>示例 4:</strong></p>

<pre>
<strong>输入:</strong> num = 58
<strong>输出:</strong> "LVIII"
<strong>解释:</strong> L = 50, V = 5, III = 3.
</pre>

<p><strong>示例 5:</strong></p>

<pre>
<strong>输入:</strong> num = 1994
<strong>输出:</strong> "MCMXCIV"
<strong>解释:</strong> M = 1000, CM = 900, XC = 90, IV = 4.</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= num <= 3999</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

贪心算法实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def intToRoman(self, num: int) -> str:
        nums = [
            (1000, 'M'),
            (900, 'CM'),
            (500, 'D'),
            (400, 'CD'),
            (100, 'C'),
            (90, 'XC'),
            (50, 'L'),
            (40, 'XL'),
            (10, 'X'),
            (9, 'IX'),
            (5, 'V'),
            (4, 'IV'),
            (1, 'I'),
        ]
        res = []
        for k, v in nums:
            while num >= k:
                num -= k
                res.append(v)
        return ''.join(res)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String intToRoman(int num) {
        int[] nums = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; ++i) {
            while (num >= nums[i]) {
                num -= nums[i];
                sb.append(romans[i]);
            }
        }
        return sb.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string intToRoman(int num) {
        vector<int> nums {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        vector<string> romans {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        string ans;
        for (int i = 0; i < nums.size(); ++i) {
            while (num >= nums[i]) {
                num -= nums[i];
                ans.append(romans[i]);
            }
        }
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
