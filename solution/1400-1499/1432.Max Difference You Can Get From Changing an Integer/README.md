---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1432.Max%20Difference%20You%20Can%20Get%20From%20Changing%20an%20Integer/README.md
rating: 1426
source: 第 25 场双周赛 Q2
tags:
    - 贪心
    - 数学
---

<!-- problem:start -->

# [1432. 改变一个整数能得到的最大差值](https://leetcode.cn/problems/max-difference-you-can-get-from-changing-an-integer)

[English Version](/solution/1400-1499/1432.Max%20Difference%20You%20Can%20Get%20From%20Changing%20an%20Integer/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数&nbsp;<code>num</code>&nbsp;。你可以对它进行以下步骤共计&nbsp;<strong>两次</strong>：</p>

<ul>
	<li>选择一个数字&nbsp;<code>x (0&nbsp;&lt;= x &lt;= 9)</code>.</li>
	<li>选择另一个数字&nbsp;<code>y (0&nbsp;&lt;= y &lt;= 9)</code>&nbsp;。数字&nbsp;<code>y</code>&nbsp;可以等于&nbsp;<code>x</code>&nbsp;。</li>
	<li>将 <code>num</code>&nbsp;中所有出现 <code>x</code>&nbsp;的数位都用 <code>y</code>&nbsp;替换。</li>
</ul>

<p>令两次对 <code>num</code>&nbsp;的操作得到的结果分别为&nbsp;<code>a</code>&nbsp;和&nbsp;<code>b</code>&nbsp;。</p>

<p>请你返回&nbsp;<code>a</code> 和&nbsp;<code>b</code>&nbsp;的 <strong>最大差值</strong> 。</p>

<p>注意，新的整数（<code>a</code> 或 <code>b</code>）<strong>必须不能</strong> 含有前导 0，并且 <strong>非</strong> 0。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num = 555
<strong>输出：</strong>888
<strong>解释：</strong>第一次选择 x = 5 且 y = 9 ，并把得到的新数字保存在 a 中。
第二次选择 x = 5 且 y = 1 ，并把得到的新数字保存在 b 中。
现在，我们有 a = 999 和 b = 111 ，最大差值为 888
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num = 9
<strong>输出：</strong>8
<strong>解释：</strong>第一次选择 x = 9 且 y = 9 ，并把得到的新数字保存在 a 中。
第二次选择 x = 9 且 y = 1 ，并把得到的新数字保存在 b 中。
现在，我们有 a = 9 和 b = 1 ，最大差值为 8
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>num = 123456
<strong>输出：</strong>820000
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>num = 10000
<strong>输出：</strong>80000
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>num = 9288
<strong>输出：</strong>8700
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10^8</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

要想得到最大差值，那么我们应该拿到最大值与最小值，这样差值最大。

因此，我们先从高到低枚举 $\textit{nums}$ 每个位置上的数，如果数字不为 `9`，就将所有该数字替换为 `9`，得到最大整数 $a$。

接下来，我们再从高到低枚举 $\textit{nums}$ 每个位置上的数，首位不能为 `0`，因此如果首位不为 `1`，我们将其替换为 `1`；如果非首位，且数字不与首位相同，我们将其替换为 `0`，得到最大整数 $b$。

答案为差值 $a - b$。

时间复杂度 $O(\log \textit{num})$，空间复杂度 $O(\log \textit{num})$。其中 $\textit{nums}$ 为给定整数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDiff(self, num: int) -> int:
        a, b = str(num), str(num)
        for c in a:
            if c != "9":
                a = a.replace(c, "9")
                break
        if b[0] != "1":
            b = b.replace(b[0], "1")
        else:
            for c in b[1:]:
                if c not in "01":
                    b = b.replace(c, "0")
                    break
        return int(a) - int(b)
```

#### Java

```java
class Solution {
    public int maxDiff(int num) {
        String a = String.valueOf(num);
        String b = a;
        for (int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) != '9') {
                a = a.replace(a.charAt(i), '9');
                break;
            }
        }
        if (b.charAt(0) != '1') {
            b = b.replace(b.charAt(0), '1');
        } else {
            for (int i = 1; i < b.length(); ++i) {
                if (b.charAt(i) != '0' && b.charAt(i) != '1') {
                    b = b.replace(b.charAt(i), '0');
                    break;
                }
            }
        }
        return Integer.parseInt(a) - Integer.parseInt(b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxDiff(int num) {
        auto replace = [](string& s, char a, char b) {
            for (auto& c : s) {
                if (c == a) {
                    c = b;
                }
            }
        };
        string a = to_string(num);
        string b = a;
        for (int i = 0; i < a.size(); ++i) {
            if (a[i] != '9') {
                replace(a, a[i], '9');
                break;
            }
        }
        if (b[0] != '1') {
            replace(b, b[0], '1');
        } else {
            for (int i = 1; i < b.size(); ++i) {
                if (b[i] != '0' && b[i] != '1') {
                    replace(b, b[i], '0');
                    break;
                }
            }
        }
        return stoi(a) - stoi(b);
    }
};
```

#### Go

```go
func maxDiff(num int) int {
	a, b := num, num
	s := strconv.Itoa(num)
	for i := range s {
		if s[i] != '9' {
			a, _ = strconv.Atoi(strings.ReplaceAll(s, string(s[i]), "9"))
			break
		}
	}
	if s[0] > '1' {
		b, _ = strconv.Atoi(strings.ReplaceAll(s, string(s[0]), "1"))
	} else {
		for i := 1; i < len(s); i++ {
			if s[i] != '0' && s[i] != '1' {
				b, _ = strconv.Atoi(strings.ReplaceAll(s, string(s[i]), "0"))
				break
			}
		}
	}
	return a - b
}
```

#### TypeScript

```ts
function maxDiff(num: number): number {
    let a = num.toString();
    let b = a;
    for (let i = 0; i < a.length; ++i) {
        if (a[i] !== '9') {
            a = a.split(a[i]).join('9');
            break;
        }
    }
    if (b[0] !== '1') {
        b = b.split(b[0]).join('1');
    } else {
        for (let i = 1; i < b.length; ++i) {
            if (b[i] !== '0' && b[i] !== '1') {
                b = b.split(b[i]).join('0');
                break;
            }
        }
    }
    return +a - +b;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_diff(num: i32) -> i32 {
        let a = num.to_string();
        let mut a = a.clone();
        let mut b = a.clone();

        for c in a.chars() {
            if c != '9' {
                a = a.replace(c, "9");
                break;
            }
        }

        let chars: Vec<char> = b.chars().collect();
        if chars[0] != '1' {
            b = b.replace(chars[0], "1");
        } else {
            for &c in &chars[1..] {
                if c != '0' && c != '1' {
                    b = b.replace(c, "0");
                    break;
                }
            }
        }

        a.parse::<i32>().unwrap() - b.parse::<i32>().unwrap()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
