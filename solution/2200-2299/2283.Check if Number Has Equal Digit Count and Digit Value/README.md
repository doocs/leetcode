---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2283.Check%20if%20Number%20Has%20Equal%20Digit%20Count%20and%20Digit%20Value/README.md
rating: 1253
source: 第 79 场双周赛 Q1
tags:
    - 哈希表
    - 字符串
    - 计数
---

<!-- problem:start -->

# [2283. 判断一个数的数字计数是否等于数位的值](https://leetcode.cn/problems/check-if-number-has-equal-digit-count-and-digit-value)

[English Version](/solution/2200-2299/2283.Check%20if%20Number%20Has%20Equal%20Digit%20Count%20and%20Digit%20Value/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>n</code>&nbsp;的字符串&nbsp;<code>num</code>&nbsp;，它只包含数字。</p>

<p>如果对于 <strong>每个</strong><em>&nbsp;</em><code>0 &lt;= i &lt; n</code>&nbsp;的下标&nbsp;<code>i</code>&nbsp;，都满足数位<em>&nbsp;</em><code>i</code>&nbsp;在 <code>num</code>&nbsp;中出现了&nbsp;<code>num[i]</code>次，那么请你返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>num = "1210"
<b>输出：</b>true
<strong>解释：</strong>
num[0] = '1' 。数字 0 在 num 中出现了一次。
num[1] = '2' 。数字 1 在 num 中出现了两次。
num[2] = '1' 。数字 2 在 num 中出现了一次。
num[3] = '0' 。数字 3 在 num 中出现了零次。
"1210" 满足题目要求条件，所以返回 true 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>num = "030"
<b>输出：</b>false
<strong>解释：</strong>
num[0] = '0' 。数字 0 应该出现 0 次，但是在 num 中出现了两次。
num[1] = '3' 。数字 1 应该出现 3 次，但是在 num 中出现了零次。
num[2] = '0' 。数字 2 在 num 中出现了 0 次。
下标 0 和 1 都违反了题目要求，所以返回 false 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == num.length</code></li>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>num</code>&nbsp;只包含数字。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数 + 枚举

我们可以用一个长度为 $10$ 的数组 $\textit{cnt}$ 统计字符串 $\textit{num}$ 中每个数字出现的次数，然后再枚举字符串 $\textit{num}$ 中的每个数字，判断其出现的次数是否等于该数字本身。如果对于所有的数字都满足这个条件，那么返回 $\text{true}$，否则返回 $\text{false}$。

时间复杂度 $O(n)$，空间复杂度 $O(|\Sigma|)$。其中 $n$ 是字符串 $\textit{num}$ 的长度，而 $|\Sigma|$ 是数字的取值范围，即 $10$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def digitCount(self, num: str) -> bool:
        cnt = Counter(int(x) for x in num)
        return all(cnt[i] == int(x) for i, x in enumerate(num))
```

#### Java

```java
class Solution {
    public boolean digitCount(String num) {
        int[] cnt = new int[10];
        int n = num.length();
        for (int i = 0; i < n; ++i) {
            ++cnt[num.charAt(i) - '0'];
        }
        for (int i = 0; i < n; ++i) {
            if (num.charAt(i) - '0' != cnt[i]) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool digitCount(string num) {
        int cnt[10]{};
        for (char& c : num) {
            ++cnt[c - '0'];
        }
        for (int i = 0; i < num.size(); ++i) {
            if (cnt[i] != num[i] - '0') {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func digitCount(num string) bool {
	cnt := [10]int{}
	for _, c := range num {
		cnt[c-'0']++
	}
	for i, c := range num {
		if int(c-'0') != cnt[i] {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function digitCount(num: string): boolean {
    const cnt: number[] = Array(10).fill(0);
    for (const c of num) {
        ++cnt[+c];
    }
    for (let i = 0; i < num.length; ++i) {
        if (cnt[i] !== +num[i]) {
            return false;
        }
    }
    return true;
}
```

#### Rust

```rust
impl Solution {
    pub fn digit_count(num: String) -> bool {
        let mut cnt = vec![0; 10];
        for c in num.chars() {
            let x = c.to_digit(10).unwrap() as usize;
            cnt[x] += 1;
        }
        for (i, c) in num.chars().enumerate() {
            let x = c.to_digit(10).unwrap() as usize;
            if cnt[i] != x {
                return false;
            }
        }
        true
    }
}
```

#### C

```c
bool digitCount(char* num) {
    int cnt[10] = {0};
    for (int i = 0; num[i] != '\0'; ++i) {
        ++cnt[num[i] - '0'];
    }
    for (int i = 0; num[i] != '\0'; ++i) {
        if (cnt[i] != num[i] - '0') {
            return false;
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
