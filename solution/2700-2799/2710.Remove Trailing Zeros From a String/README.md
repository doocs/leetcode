---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2710.Remove%20Trailing%20Zeros%20From%20a%20String/README.md
rating: 1164
source: 第 347 场周赛 Q1
tags:
    - 字符串
---

<!-- problem:start -->

# [2710. 移除字符串中的尾随零](https://leetcode.cn/problems/remove-trailing-zeros-from-a-string)

[English Version](/solution/2700-2799/2710.Remove%20Trailing%20Zeros%20From%20a%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个用字符串表示的正整数 <code>num</code> ，请你以字符串形式返回不含尾随零的整数<em> </em><code>num</code><em> </em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>num = "51230100"
<strong>输出：</strong>"512301"
<strong>解释：</strong>整数 "51230100" 有 2 个尾随零，移除并返回整数 "512301" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>num = "123"
<strong>输出：</strong>"123"
<strong>解释：</strong>整数 "123" 不含尾随零，返回整数 "123" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 1000</code></li>
	<li><code>num</code> 仅由数字 <code>0</code> 到 <code>9</code> 组成</li>
	<li><code>num</code> 不含前导零</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历

我们可以从后往前遍历字符串，遇到第一个不是 `0` 的字符时停止遍历，然后返回从头开始到这个字符的子串。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。忽略答案字符串的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def removeTrailingZeros(self, num: str) -> str:
        return num.rstrip("0")
```

#### Java

```java
class Solution {
    public String removeTrailingZeros(String num) {
        int i = num.length() - 1;
        while (num.charAt(i) == '0') {
            --i;
        }
        return num.substring(0, i + 1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string removeTrailingZeros(string num) {
        while (num.back() == '0') {
            num.pop_back();
        }
        return num;
    }
};
```

#### Go

```go
func removeTrailingZeros(num string) string {
	i := len(num) - 1
	for num[i] == '0' {
		i--
	}
	return num[:i+1]
}
```

#### TypeScript

```ts
function removeTrailingZeros(num: string): string {
    let i = num.length - 1;
    while (num[i] === '0') {
        --i;
    }
    return num.substring(0, i + 1);
}
```

#### Rust

```rust
impl Solution {
    pub fn remove_trailing_zeros(num: String) -> String {
        let mut i = num.len() - 1;

        while num.chars().nth(i) == Some('0') {
            i -= 1;
        }

        num[..i + 1].to_string()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Rust

```rust
impl Solution {
    pub fn remove_trailing_zeros(num: String) -> String {
        num.chars()
            .rev()
            .skip_while(|&c| c == '0')
            .collect::<String>()
            .chars()
            .rev()
            .collect::<String>()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
