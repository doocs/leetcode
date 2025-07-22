---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1946.Largest%20Number%20After%20Mutating%20Substring/README.md
rating: 1445
source: 第 251 场周赛 Q2
tags:
    - 贪心
    - 数组
    - 字符串
---

<!-- problem:start -->

# [1946. 子字符串突变后可能得到的最大整数](https://leetcode.cn/problems/largest-number-after-mutating-substring)

[English Version](/solution/1900-1999/1946.Largest%20Number%20After%20Mutating%20Substring/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>num</code> ，该字符串表示一个大整数。另给你一个长度为 <code>10</code> 且 <strong>下标从 0&nbsp; 开始</strong> 的整数数组 <code>change</code> ，该数组将 <code>0-9</code> 中的每个数字映射到另一个数字。更规范的说法是，数字 <code>d</code> 映射为数字 <code>change[d]</code> 。</p>

<p>你可以选择 <strong>突变</strong>&nbsp; <code>num</code> 的任一子字符串。<strong>突变</strong> 子字符串意味着将每位数字 <code>num[i]</code> 替换为该数字在 <code>change</code> 中的映射（也就是说，将 <code>num[i]</code> 替换为 <code>change[num[i]]</code>）。</p>

<p>请你找出在对 <code>num</code> 的任一子字符串执行突变操作（也可以不执行）后，可能得到的 <strong>最大整数</strong> ，并用字符串表示返回。</p>

<p><strong>子字符串</strong> 是字符串中的一个连续序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>num = "<strong><em>1</em></strong>32", change = [9,8,5,0,3,6,4,2,6,8]
<strong>输出：</strong>"<strong><em>8</em></strong>32"
<strong>解释：</strong>替换子字符串 "1"：
- 1 映射为 change[1] = 8 。
因此 "<strong><em>1</em></strong>32" 变为 "<strong><em>8</em></strong>32" 。
"832" 是可以构造的最大整数，所以返回它的字符串表示。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>num = "<strong><em>021</em></strong>", change = [9,4,3,5,7,2,1,9,0,6]
<strong>输出：</strong>"<strong><em>934</em></strong>"
<strong>解释：</strong>替换子字符串 "021"：
- 0 映射为 change[0] = 9 。
- 2 映射为 change[2] = 3 。
- 1 映射为 change[1] = 4 。
因此，"<strong><em>021</em></strong>" 变为 "<strong><em>934</em></strong>" 。
"934" 是可以构造的最大整数，所以返回它的字符串表示。 
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>num = "5", change = [1,4,7,5,3,2,5,6,9,4]
<strong>输出：</strong>"5"
<strong>解释：</strong>"5" 已经是可以构造的最大整数，所以返回它的字符串表示。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 10<sup>5</sup></code></li>
	<li><code>num</code> 仅由数字 <code>0-9</code> 组成</li>
	<li><code>change.length == 10</code></li>
	<li><code>0 &lt;= change[d] &lt;= 9</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

根据题目描述，我们可以从字符串的高位开始，贪心的进行连续的替换操作，直到遇到一个比当前位数字小的数字为止。

我们先将字符串 $\textit{num}$ 转换为字符数组 $\textit{s}$，用一个变量 $\textit{changed}$ 记录是否已经发生过变化，初始时 $\textit{changed} = \text{false}$。

然后我们遍历字符数组 $\textit{s}$，对于每个字符 $\textit{c}$，我们将其转换为数字 $\textit{d} = \text{change}[\text{int}(\textit{c})]$，如果已经发生过变化且 $\textit{d} < \textit{c}$，则说明我们不能再继续变化，直接退出循环；否则，如果 $\textit{d} > \textit{c}$，则说明我们可以将 $\textit{c}$ 替换为 $\textit{d}$，此时我们将 $\textit{changed} = \text{true}$，并将 $\textit{s}[i]$ 替换为 $\textit{d}$。

最后我们将字符数组 $\textit{s}$ 转换为字符串并返回即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $\textit{num}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumNumber(self, num: str, change: List[int]) -> str:
        s = list(num)
        changed = False
        for i, c in enumerate(s):
            d = str(change[int(c)])
            if changed and d < c:
                break
            if d > c:
                changed = True
                s[i] = d
        return "".join(s)
```

#### Java

```java
class Solution {
    public String maximumNumber(String num, int[] change) {
        char[] s = num.toCharArray();
        boolean changed = false;
        for (int i = 0; i < s.length; ++i) {
            char d = (char) (change[s[i] - '0'] + '0');
            if (changed && d < s[i]) {
                break;
            }
            if (d > s[i]) {
                changed = true;
                s[i] = d;
            }
        }
        return new String(s);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string maximumNumber(string num, vector<int>& change) {
        int n = num.size();
        bool changed = false;
        for (int i = 0; i < n; ++i) {
            char d = '0' + change[num[i] - '0'];
            if (changed && d < num[i]) {
                break;
            }
            if (d > num[i]) {
                changed = true;
                num[i] = d;
            }
        }
        return num;
    }
};
```

#### Go

```go
func maximumNumber(num string, change []int) string {
	s := []byte(num)
	changed := false
	for i, c := range num {
		d := byte('0' + change[c-'0'])
		if changed && d < s[i] {
			break
		}
		if d > s[i] {
			s[i] = d
			changed = true
		}
	}
	return string(s)
}
```

#### TypeScript

```ts
function maximumNumber(num: string, change: number[]): string {
    const s = num.split('');
    let changed = false;
    for (let i = 0; i < s.length; ++i) {
        const d = change[+s[i]].toString();
        if (changed && d < s[i]) {
            break;
        }
        if (d > s[i]) {
            s[i] = d;
            changed = true;
        }
    }
    return s.join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_number(num: String, change: Vec<i32>) -> String {
        let mut s: Vec<char> = num.chars().collect();
        let mut changed = false;
        for i in 0..s.len() {
            let d = (change[s[i] as usize - '0' as usize] + '0' as i32) as u8 as char;
            if changed && d < s[i] {
                break;
            }
            if d > s[i] {
                changed = true;
                s[i] = d;
            }
        }
        s.into_iter().collect()
    }
}
```

#### JavaScript

```js
/**
 * @param {string} num
 * @param {number[]} change
 * @return {string}
 */
var maximumNumber = function (num, change) {
    const s = num.split('');
    let changed = false;
    for (let i = 0; i < s.length; ++i) {
        const d = change[+s[i]].toString();
        if (changed && d < s[i]) {
            break;
        }
        if (d > s[i]) {
            s[i] = d;
            changed = true;
        }
    }
    return s.join('');
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
