---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3216.Lexicographically%20Smallest%20String%20After%20a%20Swap/README.md
rating: 1242
source: 第 406 场周赛 Q1
tags:
    - 贪心
    - 字符串
---

<!-- problem:start -->

# [3216. 交换后字典序最小的字符串](https://leetcode.cn/problems/lexicographically-smallest-string-after-a-swap)

[English Version](/solution/3200-3299/3216.Lexicographically%20Smallest%20String%20After%20a%20Swap/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个仅由数字组成的字符串 <code>s</code>，在最多交换一次 <strong>相邻 </strong>且具有相同 <strong>奇偶性 </strong>的数字后，返回可以得到的<span data-keyword="lexicographically-smaller-string">字典序最小的字符串</span>。</p>

<p>如果两个数字都是奇数或都是偶数，则它们具有相同的奇偶性。例如，5 和 9、2 和 4 奇偶性相同，而 6 和 9 奇偶性不同。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "45320"</span></p>

<p><strong>输出：</strong> <span class="example-io">"43520"</span></p>

<p><strong>解释：</strong></p>

<p><code>s[1] == '5'</code> 和 <code>s[2] == '3'</code> 都具有相同的奇偶性，交换它们可以得到字典序最小的字符串。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "001"</span></p>

<p><strong>输出：</strong> <span class="example-io">"001"</span></p>

<p><strong>解释：</strong></p>

<p>无需进行交换，因为 <code>s</code> 已经是字典序最小的。</p>
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

### 方法一：贪心 + 模拟

我们可以从左到右遍历字符串 $\textit{s}$，对于每一对相邻的数字，如果它们具有相同的奇偶性且前一个数字大于后一个数字，那么我们就交换这两个数字，使得字符串 $\textit{s}$ 的字典序变小，然后返回交换后的字符串。

遍历结束后，如果没有找到可以交换的数字对，说明字符串 $\textit{s}$ 已经是字典序最小的，直接返回即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $\textit{s}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getSmallestString(self, s: str) -> str:
        for i, (a, b) in enumerate(pairwise(map(ord, s))):
            if (a + b) % 2 == 0 and a > b:
                return s[:i] + s[i + 1] + s[i] + s[i + 2 :]
        return s
```

#### Java

```java
class Solution {
    public String getSmallestString(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = 1; i < n; ++i) {
            char a = cs[i - 1], b = cs[i];
            if (a > b && a % 2 == b % 2) {
                cs[i] = a;
                cs[i - 1] = b;
                return new String(cs);
            }
        }
        return s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string getSmallestString(string s) {
        int n = s.length();
        for (int i = 1; i < n; ++i) {
            char a = s[i - 1], b = s[i];
            if (a > b && a % 2 == b % 2) {
                s[i - 1] = b;
                s[i] = a;
                break;
            }
        }
        return s;
    }
};
```

#### Go

```go
func getSmallestString(s string) string {
	cs := []byte(s)
	n := len(cs)
	for i := 1; i < n; i++ {
		a, b := cs[i-1], cs[i]
		if a > b && a%2 == b%2 {
			cs[i-1], cs[i] = b, a
			return string(cs)
		}
	}
	return s
}
```

#### TypeScript

```ts
function getSmallestString(s: string): string {
    const n = s.length;
    const cs: string[] = s.split('');
    for (let i = 1; i < n; ++i) {
        const a = cs[i - 1];
        const b = cs[i];
        if (a > b && +a % 2 === +b % 2) {
            cs[i - 1] = b;
            cs[i] = a;
            return cs.join('');
        }
    }
    return s;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
