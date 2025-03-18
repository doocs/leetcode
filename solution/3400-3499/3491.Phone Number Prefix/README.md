---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3491.Phone%20Number%20Prefix/README.md
---

<!-- problem:start -->

# [3491. Phone Number Prefix 🔒](https://leetcode.cn/problems/phone-number-prefix)

[English Version](/solution/3400-3499/3491.Phone%20Number%20Prefix/README_EN.md)

## 题目描述

<!-- description:start -->

<p>You are given a string array <code>numbers</code> that represents phone numbers. Return <code>true</code> if no phone number is a prefix of any other phone number; otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">numbers = [&quot;1&quot;,&quot;2&quot;,&quot;4&quot;,&quot;3&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>No number is a prefix of another number, so the output is <code>true</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">numbers = [&quot;001&quot;,&quot;007&quot;,&quot;15&quot;,&quot;00153&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>The string <code>&quot;001&quot;</code> is a prefix of the string <code>&quot;00153&quot;</code>. Thus, the output is <code>false</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= numbers.length &lt;= 50</code></li>
	<li><code>1 &lt;= numbers[i].length &lt;= 50</code></li>
	<li>All numbers contain only digits <code>&#39;0&#39;</code> to <code>&#39;9&#39;</code>.</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 前缀判断

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
