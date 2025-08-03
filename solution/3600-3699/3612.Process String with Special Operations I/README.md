---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3612.Process%20String%20with%20Special%20Operations%20I/README.md
rating: 1185
source: 第 458 场周赛 Q1
tags:
    - 字符串
    - 模拟
---

<!-- problem:start -->

# [3612. 用特殊操作处理字符串 I](https://leetcode.cn/problems/process-string-with-special-operations-i)

[English Version](/solution/3600-3699/3612.Process%20String%20with%20Special%20Operations%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code>，它由小写英文字母和特殊字符：<code>*</code>、<code>#</code> 和 <code>%</code> 组成。</p>

<p>请根据以下规则从左到右处理 <code>s</code>&nbsp;中的字符，构造一个新的字符串 <code>result</code>：</p>

<ul>
	<li>如果字符是 <strong>小写</strong> 英文字母，则将其添加到 <code>result</code> 中。</li>
	<li>字符 <code>'*'</code> 会&nbsp;<strong>删除</strong> <code>result</code> 中的最后一个字符（如果存在）。</li>
	<li>字符 <code>'#'</code> 会&nbsp;<strong>复制&nbsp;</strong>当前的 <code>result</code> 并&nbsp;<strong>追加&nbsp;</strong>到其自身后面。</li>
	<li>字符 <code>'%'</code> 会&nbsp;<strong>反转&nbsp;</strong>当前的 <code>result</code>。</li>
</ul>

<p>在处理完 <code>s</code> 中的所有字符后，返回最终的字符串 <code>result</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "a#b%*"</span></p>

<p><strong>输出：</strong> <span class="example-io">"ba"</span></p>

<p><strong>解释：</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>s[i]</code></th>
			<th style="border: 1px solid black;">操作</th>
			<th style="border: 1px solid black;">当前 <code>result</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;"><code>'a'</code></td>
			<td style="border: 1px solid black;">添加 <code>'a'</code></td>
			<td style="border: 1px solid black;"><code>"a"</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>'#'</code></td>
			<td style="border: 1px solid black;">复制 <code>result</code></td>
			<td style="border: 1px solid black;"><code>"aa"</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>'b'</code></td>
			<td style="border: 1px solid black;">添加 <code>'b'</code></td>
			<td style="border: 1px solid black;"><code>"aab"</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;"><code>'%'</code></td>
			<td style="border: 1px solid black;">反转 <code>result</code></td>
			<td style="border: 1px solid black;"><code>"baa"</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;"><code>'*'</code></td>
			<td style="border: 1px solid black;">删除最后一个字符</td>
			<td style="border: 1px solid black;"><code>"ba"</code></td>
		</tr>
	</tbody>
</table>

<p>因此，最终的 <code>result</code> 是 <code>"ba"</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "z*#"</span></p>

<p><strong>输出：</strong> <span class="example-io">""</span></p>

<p><strong>解释：</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>s[i]</code></th>
			<th style="border: 1px solid black;">操作</th>
			<th style="border: 1px solid black;">当前 <code>result</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;"><code>'z'</code></td>
			<td style="border: 1px solid black;">添加 <code>'z'</code></td>
			<td style="border: 1px solid black;"><code>"z"</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>'*'</code></td>
			<td style="border: 1px solid black;">删除最后一个字符</td>
			<td style="border: 1px solid black;"><code>""</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>'#'</code></td>
			<td style="border: 1px solid black;">复制字符串</td>
			<td style="border: 1px solid black;"><code>""</code></td>
		</tr>
	</tbody>
</table>

<p>因此，最终的 <code>result</code> 是 <code>""</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 20</code></li>
	<li><code>s</code> 只包含小写英文字母和特殊字符 <code>*</code>、<code>#</code> 和 <code>%</code>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们直接模拟题目中的操作即可。我们使用一个列表 $\text{result}$ 来存储当前的结果字符串。遍历输入字符串 $s$ 中的每个字符，根据字符的类型执行相应的操作：

-   如果字符是小写英文字母，则将其添加到 $\text{result}$ 中。
-   如果字符是 `*`，则删除 $\text{result}$ 中的最后一个字符（如果存在）。
-   如果字符是 `#`，则将 $\text{result}$ 复制一遍并追加到其自身后面。
-   如果字符是 `%`，则反转 $\text{result}$。

最后，我们将 $\text{result}$ 转换为字符串并返回。

时间复杂度 $O(2^n)$，其中 $n$ 是字符串 $s$ 的长度。最坏情况下，可能会因为 `#` 操作导致 $\text{result}$ 的长度每次翻倍，因此时间复杂度是指数级的。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def processStr(self, s: str) -> str:
        result = []
        for c in s:
            if c.isalpha():
                result.append(c)
            elif c == "*" and result:
                result.pop()
            elif c == "#":
                result.extend(result)
            elif c == "%":
                result.reverse()
        return "".join(result)
```

#### Java

```java
class Solution {
    public String processStr(String s) {
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                result.append(c);
            } else if (c == '*') {
                result.setLength(Math.max(0, result.length() - 1));
            } else if (c == '#') {
                result.append(result);
            } else if (c == '%') {
                result.reverse();
            }
        }
        return result.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string processStr(string s) {
        string result;
        for (char c : s) {
            if (isalpha(c)) {
                result += c;
            } else if (c == '*') {
                if (!result.empty()) {
                    result.pop_back();
                }
            } else if (c == '#') {
                result += result;
            } else if (c == '%') {
                ranges::reverse(result);
            }
        }
        return result;
    }
};
```

#### Go

```go
func processStr(s string) string {
	var result []rune
	for _, c := range s {
		if unicode.IsLetter(c) {
			result = append(result, c)
		} else if c == '*' {
			if len(result) > 0 {
				result = result[:len(result)-1]
			}
		} else if c == '#' {
			result = append(result, result...)
		} else if c == '%' {
			slices.Reverse(result)
		}
	}
	return string(result)
}
```

#### TypeScript

```ts
function processStr(s: string): string {
    const result: string[] = [];
    for (const c of s) {
        if (/[a-zA-Z]/.test(c)) {
            result.push(c);
        } else if (c === '*') {
            if (result.length > 0) {
                result.pop();
            }
        } else if (c === '#') {
            result.push(...result);
        } else if (c === '%') {
            result.reverse();
        }
    }
    return result.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
