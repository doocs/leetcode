---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3612.Process%20String%20with%20Special%20Operations%20I/README_EN.md
tags:
    - String
    - Simulation
---

<!-- problem:start -->

# [3612. Process String with Special Operations I](https://leetcode.com/problems/process-string-with-special-operations-i)

[中文文档](/solution/3600-3699/3612.Process%20String%20with%20Special%20Operations%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of lowercase English letters and the special characters: <code>*</code>, <code>#</code>, and <code>%</code>.</p>

<p>Build a new string <code>result</code> by processing <code>s</code> according to the following rules from left to right:</p>

<ul>
	<li>If the letter is a <strong>lowercase</strong> English letter append it to <code>result</code>.</li>
	<li>A <code>&#39;*&#39;</code> <strong>removes</strong> the last character from <code>result</code>, if it exists.</li>
	<li>A <code>&#39;#&#39;</code> <strong>duplicates</strong> the current <code>result</code> and <strong>appends</strong> it to itself.</li>
	<li>A <code>&#39;%&#39;</code> <strong>reverses</strong> the current <code>result</code>.</li>
</ul>

<p>Return the final string <code>result</code> after processing all characters in <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;a#b%*&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;ba&quot;</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>s[i]</code></th>
			<th style="border: 1px solid black;">Operation</th>
			<th style="border: 1px solid black;">Current <code>result</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;"><code>&#39;a&#39;</code></td>
			<td style="border: 1px solid black;">Append <code>&#39;a&#39;</code></td>
			<td style="border: 1px solid black;"><code>&quot;a&quot;</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>&#39;#&#39;</code></td>
			<td style="border: 1px solid black;">Duplicate <code>result</code></td>
			<td style="border: 1px solid black;"><code>&quot;aa&quot;</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>&#39;b&#39;</code></td>
			<td style="border: 1px solid black;">Append <code>&#39;b&#39;</code></td>
			<td style="border: 1px solid black;"><code>&quot;aab&quot;</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;"><code>&#39;%&#39;</code></td>
			<td style="border: 1px solid black;">Reverse <code>result</code></td>
			<td style="border: 1px solid black;"><code>&quot;baa&quot;</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;"><code>&#39;*&#39;</code></td>
			<td style="border: 1px solid black;">Remove the last character</td>
			<td style="border: 1px solid black;"><code>&quot;ba&quot;</code></td>
		</tr>
	</tbody>
</table>

<p>Thus, the final <code>result</code> is <code>&quot;ba&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;z*#&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;&quot;</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>s[i]</code></th>
			<th style="border: 1px solid black;">Operation</th>
			<th style="border: 1px solid black;">Current <code>result</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;"><code>&#39;z&#39;</code></td>
			<td style="border: 1px solid black;">Append <code>&#39;z&#39;</code></td>
			<td style="border: 1px solid black;"><code>&quot;z&quot;</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>&#39;*&#39;</code></td>
			<td style="border: 1px solid black;">Remove the last character</td>
			<td style="border: 1px solid black;"><code>&quot;&quot;</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>&#39;#&#39;</code></td>
			<td style="border: 1px solid black;">Duplicate the string</td>
			<td style="border: 1px solid black;"><code>&quot;&quot;</code></td>
		</tr>
	</tbody>
</table>

<p>Thus, the final <code>result</code> is <code>&quot;&quot;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 20</code></li>
	<li><code>s</code> consists of only lowercase English letters and special characters <code>*</code>, <code>#</code>, and <code>%</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can directly simulate the operations described in the problem. We use a list $\text{result}$ to store the current result string. For each character in the input string $s$, we perform the corresponding operation based on the character type:

-   If the character is a lowercase English letter, add it to $\text{result}$.
-   If the character is `*`, delete the last character in $\text{result}$ (if it exists).
-   If the character is `#`, copy $\text{result}$ and append it to itself.
-   If the character is `%`, reverse $\text{result}$.

Finally, we convert $\text{result}$ to a string and return it.

The time complexity is $O(2^n)$, where $n$ is the length of string $s$. In the worst case, the `#` operation may cause the length of $\text{result}$ to double each time, resulting in exponential time complexity. Ignoring the space consumption of the answer, the space complexity is $O(1)$.

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
