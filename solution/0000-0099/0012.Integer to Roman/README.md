---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0012.Integer%20to%20Roman/README.md
tags:
    - 哈希表
    - 数学
    - 字符串
---

<!-- problem:start -->

# [12. 整数转罗马数字](https://leetcode.cn/problems/integer-to-roman)

[English Version](/solution/0000-0099/0012.Integer%20to%20Roman/README_EN.md)

## 题目描述

<!-- description:start -->

<p>七个不同的符号代表罗马数字，其值如下：</p>

<table>
	<thead>
		<tr>
			<th>符号</th>
			<th>值</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>I</td>
			<td>1</td>
		</tr>
		<tr>
			<td>V</td>
			<td>5</td>
		</tr>
		<tr>
			<td>X</td>
			<td>10</td>
		</tr>
		<tr>
			<td>L</td>
			<td>50</td>
		</tr>
		<tr>
			<td>C</td>
			<td>100</td>
		</tr>
		<tr>
			<td>D</td>
			<td>500</td>
		</tr>
		<tr>
			<td>M</td>
			<td>1000</td>
		</tr>
	</tbody>
</table>

<p>罗马数字是通过添加从最高到最低的小数位值的转换而形成的。将小数位值转换为罗马数字有以下规则：</p>

<ul>
	<li>如果该值不是以 4 或 9 开头，请选择可以从输入中减去的最大值的符号，将该符号附加到结果，减去其值，然后将其余部分转换为罗马数字。</li>
	<li>如果该值以 4 或 9 开头，使用 <strong>减法形式</strong>，表示从以下符号中减去一个符号，例如&nbsp;4 是 5 (<code>V</code>) 减 1 (<code>I</code>): <code>IV</code>&nbsp;，9 是 10 (<code>X</code>) 减&nbsp;1 (<code>I</code>)：<code>IX</code>。仅使用以下减法形式：4 (<code>IV</code>)，9 (<code>IX</code>)，40 (<code>XL</code>)，90 (<code>XC</code>)，400 (<code>CD</code>) 和&nbsp;900 (<code>CM</code>)。</li>
	<li>只有 10 的次方（<code>I</code>, <code>X</code>, <code>C</code>, <code>M</code>）最多可以连续附加 3 次以代表 10 的倍数。你不能多次附加&nbsp;5&nbsp;(<code>V</code>)，50 (<code>L</code>) 或 500 (<code>D</code>)。如果需要将符号附加4次，请使用 <strong>减法形式</strong>。</li>
</ul>

<p>给定一个整数，将其转换为罗马数字。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">num = 3749</span></p>

<p><strong>输出：</strong>&nbsp;<span class="example-io">"MMMDCCXLIX"</span></p>

<p><strong>解释：</strong></p>

<pre>
3000 = MMM 由于 1000 (M) + 1000 (M) + 1000 (M)
 700 = DCC 由于 500 (D) + 100 (C) + 100 (C)
  40 = XL 由于 50 (L) 减 10 (X)
   9 = IX 由于 10 (X) 减 1 (I)
注意：49 不是 50 (L) 减 1 (I) 因为转换是基于小数位
</pre>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">num = 58</span></p>

<p><strong>输出：</strong><span class="example-io">"LVIII"</span></p>

<p><strong>解释：</strong></p>

<pre>
50 = L
 8 = VIII
</pre>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">num = 1994</span></p>

<p><strong>输出：</strong><span class="example-io">"MCMXCIV"</span></p>

<p><strong>解释：</strong></p>

<pre>
1000 = M
 900 = CM
  90 = XC
   4 = IV
</pre>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 3999</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

我们可以先将所有可能的符号 $cs$ 和对应的数值 $vs$ 列出来，然后从大到小枚举每个数值 $vs[i]$，每次尽可能多地使用该数值对应的符号 $cs[i]$，直到数字 $num$ 变为 $0$。

时间复杂度为 $O(m)$，空间复杂度为 $O(m)$。其中 $m$ 为符号的个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def intToRoman(self, num: int) -> str:
        cs = ('M', 'CM', 'D', 'CD', 'C', 'XC', 'L', 'XL', 'X', 'IX', 'V', 'IV', 'I')
        vs = (1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
        ans = []
        for c, v in zip(cs, vs):
            while num >= v:
                num -= v
                ans.append(c)
        return ''.join(ans)
```

#### Java

```java
class Solution {
    public String intToRoman(int num) {
        List<String> cs
            = List.of("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I");
        List<Integer> vs = List.of(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1);
        StringBuilder ans = new StringBuilder();
        for (int i = 0, n = cs.size(); i < n; ++i) {
            while (num >= vs.get(i)) {
                num -= vs.get(i);
                ans.append(cs.get(i));
            }
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string intToRoman(int num) {
        vector<string> cs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        vector<int> vs = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        string ans;
        for (int i = 0; i < cs.size(); ++i) {
            while (num >= vs[i]) {
                num -= vs[i];
                ans += cs[i];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func intToRoman(num int) string {
	cs := []string{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"}
	vs := []int{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1}
	ans := &strings.Builder{}
	for i, v := range vs {
		for num >= v {
			num -= v
			ans.WriteString(cs[i])
		}
	}
	return ans.String()
}
```

#### TypeScript

```ts
function intToRoman(num: number): string {
    const cs: string[] = ['M', 'CM', 'D', 'CD', 'C', 'XC', 'L', 'XL', 'X', 'IX', 'V', 'IV', 'I'];
    const vs: number[] = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1];
    const ans: string[] = [];
    for (let i = 0; i < vs.length; ++i) {
        while (num >= vs[i]) {
            num -= vs[i];
            ans.push(cs[i]);
        }
    }
    return ans.join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn int_to_roman(num: i32) -> String {
        let cs = [
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I",
        ];
        let vs = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1];
        let mut num = num;
        let mut ans = String::new();

        for (i, &v) in vs.iter().enumerate() {
            while num >= v {
                num -= v;
                ans.push_str(cs[i]);
            }
        }

        ans
    }
}
```

#### C#

```cs
public class Solution {
    public string IntToRoman(int num) {
        List<string> cs = new List<string>{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        List<int> vs = new List<int>{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < cs.Count; i++) {
            while (num >= vs[i]) {
                ans.Append(cs[i]);
                num -= vs[i];
            }
        }
        return ans.ToString();
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer $num
     * @return String
     */
    function intToRoman($num) {
        $cs = ['M', 'CM', 'D', 'CD', 'C', 'XC', 'L', 'XL', 'X', 'IX', 'V', 'IV', 'I'];
        $vs = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1];
        $ans = '';

        foreach ($vs as $i => $v) {
            while ($num >= $v) {
                $num -= $v;
                $ans .= $cs[$i];
            }
        }

        return $ans;
    }
}
```

#### C

```c
static const char* cs[] = {
    "M", "CM", "D", "CD", "C", "XC",
    "L", "XL", "X", "IX", "V", "IV", "I"};

static const int vs[] = {
    1000, 900, 500, 400, 100, 90,
    50, 40, 10, 9, 5, 4, 1};

char* intToRoman(int num) {
    static char ans[20];
    ans[0] = '\0';
    for (int i = 0; i < 13; ++i) {
        while (num >= vs[i]) {
            num -= vs[i];
            strcat(ans, cs[i]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
