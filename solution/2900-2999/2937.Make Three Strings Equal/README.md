---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2937.Make%20Three%20Strings%20Equal/README.md
rating: 1347
source: 第 372 场周赛 Q1
tags:
    - 字符串
---

<!-- problem:start -->

# [2937. 使三个字符串相等](https://leetcode.cn/problems/make-three-strings-equal)

[English Version](/solution/2900-2999/2937.Make%20Three%20Strings%20Equal/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你三个字符串 <code>s1</code>、<code>s2</code> 和 <code>s3</code>。 你可以根据需要对这三个字符串执行以下操作 <strong>任意次数</strong> <!-- notionvc: b5178de7-3318-4129-b7d9-726b47e90621 -->。</p>

<p>在每次操作中，你可以选择其中一个长度至少为 <code>2</code> 的字符串 <!-- notionvc: 3342ac46-33c8-4010-aacd-e58678ce31ef --> 并删除其 <strong>最右位置上</strong> 的字符。</p>

<p>如果存在某种方法能够使这三个字符串相等，请返回使它们相等所需的 <strong>最小</strong> 操作次数；否则，返回 <code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>s1 = "abc"，s2 = "abb"，s3 = "ab"
<strong>输出：</strong>2
<strong>解释：</strong>对 s1 和 s2 进行一次操作后，可以得到三个相等的字符串。
可以证明，不可能用少于两次操作使它们相等。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>s1 = "dac"，s2 = "bac"，s3 = "cac"
<strong>输出：</strong>-1
<strong>解释：</strong>因为 s1 和 s2 的最左位置上的字母<!-- notionvc: 47239f7c-eec1-49f8-af79-c206ec88cb07 -->不相等，所以无论进行多少次操作，它们都不可能相等。因此答案是 -1 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length, s3.length &lt;= 100</code></li>
	<li><code>s1</code>、<code>s2</code> 和 <code>s3</code> 仅由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

根据题目描述，我们知道，如果删除字符后的三个字符串相等，那么它们存在一个长度大于 $1$ 的公共前缀。因此，我们可以枚举公共前缀的位置 $i$，如果当前下标 $i$ 对应的三个字符不完全相等，那么公共前缀长度为 $i$，此时，我们判断 $i$ 是否为 $0$，若是，返回 $-1$，否则返回 $s - 3 \times i$，其中 $s$ 为三个字符串的长度和。

时间复杂度 $O(n)$，其中 $n$ 为三个字符串的最小长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def findMinimumOperations(self, s1: str, s2: str, s3: str) -> int:
        s = len(s1) + len(s2) + len(s3)
        n = min(len(s1), len(s2), len(s3))
        for i in range(n):
            if not s1[i] == s2[i] == s3[i]:
                return -1 if i == 0 else s - 3 * i
        return s - 3 * n
```

```java
class Solution {
    public int findMinimumOperations(String s1, String s2, String s3) {
        int s = s1.length() + s2.length() + s3.length();
        int n = Math.min(Math.min(s1.length(), s2.length()), s3.length());
        for (int i = 0; i < n; ++i) {
            if (!(s1.charAt(i) == s2.charAt(i) && s2.charAt(i) == s3.charAt(i))) {
                return i == 0 ? -1 : s - 3 * i;
            }
        }
        return s - 3 * n;
    }
}
```

```cpp
class Solution {
public:
    int findMinimumOperations(string s1, string s2, string s3) {
        int s = s1.size() + s2.size() + s3.size();
        int n = min({s1.size(), s2.size(), s3.size()});
        for (int i = 0; i < n; ++i) {
            if (!(s1[i] == s2[i] && s2[i] == s3[i])) {
                return i == 0 ? -1 : s - 3 * i;
            }
        }
        return s - 3 * n;
    }
};
```

```go
func findMinimumOperations(s1 string, s2 string, s3 string) int {
	s := len(s1) + len(s2) + len(s3)
	n := min(len(s1), len(s2), len(s3))
	for i := range s1[:n] {
		if !(s1[i] == s2[i] && s2[i] == s3[i]) {
			if i == 0 {
				return -1
			}
			return s - 3*i
		}
	}
	return s - 3*n
}
```

```ts
function findMinimumOperations(s1: string, s2: string, s3: string): number {
    const s = s1.length + s2.length + s3.length;
    const n = Math.min(s1.length, s2.length, s3.length);
    for (let i = 0; i < n; ++i) {
        if (!(s1[i] === s2[i] && s2[i] === s3[i])) {
            return i === 0 ? -1 : s - 3 * i;
        }
    }
    return s - 3 * n;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
