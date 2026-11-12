---
comments: true
difficulty: 中等
rating: 1759
source: 第 32 场双周赛 Q3
tags:
    - 栈
    - 贪心
    - 字符串
    - 括号序列
---

<!-- problem:start -->

# [1541. 平衡括号字符串的最少插入次数](https://leetcode.cn/problems/minimum-insertions-to-balance-a-parentheses-string)

[English Version](/solution/1500-1599/1541.Minimum%20Insertions%20to%20Balance%20a%20Parentheses%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个仅包含字符 <code>'('</code> 和 <code>')'</code> 的括号字符串 <code>s</code>。如果一个括号字符串可以通过以下规则构成，则称其为&nbsp;<strong>平衡&nbsp;</strong>的：</p>

<ul>
	<li>空字符串是平衡的。</li>
	<li>如果 <code>A</code> 是平衡的，那么 <code>'('</code> + <code>A</code> + <code>'))'</code> 也是平衡的。</li>
	<li>如果 <code>A</code> 和 <code>B</code> 都是平衡的，那么它们拼接后的字符串也是平衡的。</li>
</ul>

<p>换句话说，每个左括号 <code>'('</code> 都必须与其后面一对连续的右括号 <code>'))'</code> 匹配，并且每个右括号都必须恰好属于这样一对匹配的右括号。</p>

<p>例如，<code>"())"</code>、<code>"())(())))"</code> 和 <code>"(())())))"</code> 是平衡的，而 <code>")()"</code>、<code>"()))"</code> 和 <code>"(()))"</code> 不是平衡的。</p>

<p>你可以在字符串的任意位置插入字符 <code>'('</code> 和 <code>')'</code>，使其变为平衡字符串。</p>

<p>返回使 <code>s</code> 变为平衡字符串所需的&nbsp;<strong>最少&nbsp;</strong>插入次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "(()))"</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>第二个 <code>'('</code> 与两个连续的 <code>')'</code> 匹配，但第一个 <code>'('</code> 只有一个与之匹配的 <code>')'</code>。我们需要在字符串末尾再插入一个 <code>')'</code>，使字符串变为 <code>"(())))"</code>，此时字符串是平衡的。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "())"</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>字符串已经是平衡的，因为其中的 <code>'('</code> 后面有两个连续的 <code>')'</code>，并且这两个右括号恰好构成与该左括号匹配的一对右括号。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "))())("</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>在字符串开头插入一个 <code>'('</code>，与最前面的 <code>'))'</code> 匹配；在字符串末尾插入 <code>'))'</code>，与最后一个 <code>'('</code> 匹配。总共需要插入 3 个括号。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅由 <code>'('</code> 和 <code>')'</code> 组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

<!-- thinking:start -->

> **思考**
>
> 合法括号要求每个 '(' 配两个连续的 ')'。 $n\le 10^5$，反复扫描插入位置不如一次贪心。
>
> 从左到右维护尚未匹配的左括号个数 $x$。遇到 '(' 则 $x$ 加一；遇到 ')' 时，若不是成对则先插入一个右括号。此时若没有左括号可配，再插入一个 '('，否则消耗一个 $x$。扫完后每个剩余的左括号还要补两个 ')'。

<!-- thinking:end -->

我们用 $x$ 表示字符串中待匹配的左括号的数量，初始时为 $0$。遍历字符串 $s$：

如果遇到左括号，则 $x$ 的值加 $1$；如果遇到右括号，我们分情况讨论：

- 如果有两个连续的右括号，那么我们先让指针往后移动一位；否则，我们需要插入一个右括号，使得出现两个连续的右括号，因此插入次数加 $1$；
- 如果 $x = 0$，说明当前没有待匹配的左括号，我们需要插入一个左括号，用于匹配上面准备好的两个连续的右括号，因此插入次数加 $1$；否则，我们让 $x$ 的值减 $1$。

然后指针往后移动一位，继续下一次遍历。

遍历结束后，如果 $x = 0$，说明字符串已经平衡，返回插入次数；否则，说明字符串中有待匹配的左括号，我们需要再插入 $2 \times x$ 个右括号，使得字符串变成平衡字符串，返回插入次数。

贪心策略与上述相同，Rust 实现只改变了扫描方式：用 `Peekable` 迭代器配合 `next_if_eq` 直接消费与当前右括号配对的第二个右括号，省去了下标越界判断。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minInsertions(self, s: str) -> int:
        ans = x = 0
        i, n = 0, len(s)
        while i < n:
            if s[i] == '(':
                x += 1
            else:
                if i < n - 1 and s[i + 1] == ')':
                    i += 1
                else:
                    ans += 1
                if x == 0:
                    ans += 1
                else:
                    x -= 1
            i += 1
        ans += x << 1
        return ans
```

#### Java

```java
class Solution {
    public int minInsertions(String s) {
        int ans = 0, x = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(') {
                ++x;
            } else {
                if (i < n - 1 && s.charAt(i + 1) == ')') {
                    ++i;
                } else {
                    ++ans;
                }
                if (x == 0) {
                    ++ans;
                } else {
                    --x;
                }
            }
        }
        ans += x << 1;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minInsertions(string s) {
        int ans = 0, x = 0;
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            if (s[i] == '(') {
                ++x;
            } else {
                if (i < n - 1 && s[i + 1] == ')') {
                    ++i;
                } else {
                    ++ans;
                }
                if (x == 0) {
                    ++ans;
                } else {
                    --x;
                }
            }
        }
        ans += x << 1;
        return ans;
    }
};
```

#### Go

```go
func minInsertions(s string) int {
	ans, x, n := 0, 0, len(s)
	for i := 0; i < n; i++ {
		if s[i] == '(' {
			x++
		} else {
			if i < n-1 && s[i+1] == ')' {
				i++
			} else {
				ans++
			}
			if x == 0 {
				ans++
			} else {
				x--
			}
		}
	}
	ans += x << 1
	return ans
}
```

#### TypeScript

```ts
function minInsertions(s: string): number {
    let ans = 0;
    let x = 0;
    const n = s.length;
    for (let i = 0; i < n; i++) {
        if (s[i] === '(') {
            x++;
        } else {
            if (i < n - 1 && s[i + 1] === ')') {
                i++;
            } else {
                ans++;
            }
            if (x === 0) {
                ans++;
            } else {
                x--;
            }
        }
    }
    ans += x << 1;
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_insertions(s: String) -> i32 {
        let mut ans = 0;
        let mut x = 0;
        let mut chars = s.bytes().peekable();
        while let Some(c) = chars.next() {
            if c == b'(' {
                x += 1;
            } else {
                if chars.next_if_eq(&b')').is_none() {
                    ans += 1;
                }
                if x == 0 {
                    ans += 1;
                } else {
                    x -= 1;
                }
            }
        }
        ans + x * 2
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
