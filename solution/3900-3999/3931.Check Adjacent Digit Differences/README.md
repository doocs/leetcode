---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3931.Check%20Adjacent%20Digit%20Differences/README.md
rating: 1165
source: 第 502 场周赛 Q1
tags:
    - 字符串
---

<!-- problem:start -->

# [3931. 检查相邻数字差](https://leetcode.cn/problems/check-adjacent-digit-differences)

[English Version](/solution/3900-3999/3931.Check%20Adjacent%20Digit%20Differences/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由数字组成的字符串 <code>s</code>。</p>

<p>如果每一对<strong>&nbsp;相邻&nbsp;</strong>数字之间的&nbsp;<strong>绝对差</strong>&nbsp;都至多为 2，则返回 <code>true</code>；否则返回 <code>false</code>。</p>

<p><code>a</code> 和 <code>b</code> 之间的绝对差定义为 <code>abs(a - b)</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "132"</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>s[0]</code> 和 <code>s[1]</code> 处数字的绝对差为 <code>abs(1 - 3) = 2</code>。</li>
	<li><code>s[1]</code> 和 <code>s[2]</code> 处数字的绝对差为 <code>abs(3 - 2) = 1</code>。</li>
	<li>由于两个差值都至多为 2，因此答案为 <code>true</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "129"</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>s[0]</code> 和 <code>s[1]</code> 处数字的绝对差为 <code>abs(1 - 2) = 1</code>。</li>
	<li><code>s[1]</code> 和 <code>s[2]</code> 处数字的绝对差为 <code>abs(2 - 9) = 7</code>，大于 2。</li>
	<li>因此，答案为 <code>false</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 仅由数字组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以模拟题目中描述的过程，遍历字符串中的每一对相邻数字，计算它们之间的绝对差，如果发现有一对数字的绝对差大于 2，则返回 $\text{false}$；如果遍历完成后没有发现任何一对数字的绝对差大于 2，则返回 $\text{true}$。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isAdjacentDiffAtMostTwo(self, s: str) -> bool:
        return all(abs(x - y) <= 2 for x, y in pairwise(map(int, list(s))))
```

#### Java

```java
class Solution {
    public boolean isAdjacentDiffAtMostTwo(String s) {
        for (int i = 1; i < s.length(); ++i) {
            if (Math.abs(s.charAt(i - 1) - s.charAt(i)) > 2) {
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
    bool isAdjacentDiffAtMostTwo(string s) {
        for (int i = 1; i < s.size(); ++i) {
            if (abs(s[i - 1] - s[i]) > 2) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func isAdjacentDiffAtMostTwo(s string) bool {
	for i := 1; i < len(s); i++ {
		if abs(int(s[i-1])-int(s[i])) > 2 {
			return false
		}
	}
	return true
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function isAdjacentDiffAtMostTwo(s: string): boolean {
    for (let i = 1; i < s.length; i++) {
        if (Math.abs(Number(s[i]) - Number(s[i - 1])) > 2) {
            return false;
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
