---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3491.Phone%20Number%20Prefix/README.md
tags:
    - 字典树
    - 数组
    - 字符串
    - 排序
---

<!-- problem:start -->

# [3491. 电话号码前缀 🔒](https://leetcode.cn/problems/phone-number-prefix)

[English Version](/solution/3400-3499/3491.Phone%20Number%20Prefix/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个字符串数组&nbsp;<code>numbers</code>&nbsp;表示电话号码。如果没有电话号码是任何其他电话号码的前缀，则返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">numbers = ["1","2","4","3"]</span></p>

<p><span class="example-io"><b>输出：</b>true</span></p>

<p><strong>解释：</strong></p>

<p>没有数字是其它数字的前缀，所以输出为&nbsp;<code>true</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>numbers = ["001","007","15","00153"]</span></p>

<p><span class="example-io"><b>输出：</b>false</span></p>

<p><b>解释：</b></p>

<p>字符串&nbsp;<code>"001"</code>&nbsp;是字符串&nbsp;<code>"00153"</code>&nbsp;的前缀。因此，输出是&nbsp;<code>false</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= numbers.length &lt;= 50</code></li>
	<li><code>1 &lt;= numbers[i].length &lt;= 50</code></li>
	<li>所有数字只包含&nbsp;<code>'0'</code> 到&nbsp;<code>'9'</code>&nbsp;的数位。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 前缀判断

<!-- thinking:start -->

> **思考**
>
> 判断是否有号码是另一号码的前缀。至多 $50$ 个串，长度 $50$，排序后两两检查即可。
>
> 短串才可能成为长串的前缀，先按长度排序，检查时只需看它左边更短的串。
>
> 对每个 $s$ 判断 $\textit{numbers}[:i]$ 中是否有 $t$ 使 $s.\textit{startswith}(t)$。有则返回假。

<!-- thinking:end -->

我们可以先对 $\textit{numbers}$ 数组按照字符串长度进行排序，然后遍历数组中的每一个字符串 $\textit{s}$，判断此前是否有字符串 $\textit{t}$ 是 $\textit{s}$ 的前缀，如果有，说明存在一个字符串是另一个字符串的前缀，返回 $\textit{false}$。如果遍历完所有字符串都没有找到前缀关系，返回 $\textit{true}$。

时间复杂度 $(n^2 \times m + n \times \log n)$，空间复杂度 $(m + \log n)$，其中 $n$ 是 $\textit{numbers}$ 数组的长度，而 $m$ 是 $\textit{numbers}$ 数组中字符串的平均长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def phonePrefix(self, numbers: List[str]) -> bool:
        numbers.sort(key=len)
        for i, s in enumerate(numbers):
            if any(s.startswith(t) for t in numbers[:i]):
                return False
        return True
```

#### Java

```java
class Solution {
    public boolean phonePrefix(String[] numbers) {
        Arrays.sort(numbers, (a, b) -> Integer.compare(a.length(), b.length()));
        for (int i = 0; i < numbers.length; i++) {
            String s = numbers[i];
            for (int j = 0; j < i; j++) {
                if (s.startsWith(numbers[j])) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

#### C++

```cpp
#include <ranges>

class Solution {
public:
    bool phonePrefix(vector<string>& numbers) {
        ranges::sort(numbers, [](const string& a, const string& b) {
            return a.size() < b.size();
        });
        for (int i = 0; i < numbers.size(); i++) {
            if (ranges::any_of(numbers | views::take(i), [&](const string& t) {
                    return numbers[i].starts_with(t);
                })) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func phonePrefix(numbers []string) bool {
	sort.Slice(numbers, func(i, j int) bool {
		return len(numbers[i]) < len(numbers[j])
	})
	for i, s := range numbers {
		for _, t := range numbers[:i] {
			if strings.HasPrefix(s, t) {
				return false
			}
		}
	}
	return true
}
```

#### TypeScript

```ts
function phonePrefix(numbers: string[]): boolean {
    numbers.sort((a, b) => a.length - b.length);
    for (let i = 0; i < numbers.length; i++) {
        for (let j = 0; j < i; j++) {
            if (numbers[i].startsWith(numbers[j])) {
                return false;
            }
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
