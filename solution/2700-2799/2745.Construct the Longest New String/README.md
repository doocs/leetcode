---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2745.Construct%20the%20Longest%20New%20String/README.md
rating: 1607
source: 第 107 场双周赛 Q2
tags:
    - 贪心
    - 脑筋急转弯
    - 数学
    - 动态规划
---

<!-- problem:start -->

# [2745. 构造最长的新字符串](https://leetcode.cn/problems/construct-the-longest-new-string)

[English Version](/solution/2700-2799/2745.Construct%20the%20Longest%20New%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你三个整数&nbsp;<code>x</code>&nbsp;，<code>y</code>&nbsp;和&nbsp;<code>z</code>&nbsp;。</p>

<p>这三个整数表示你有&nbsp;<code>x</code>&nbsp;个&nbsp;<code>"AA"</code>&nbsp;字符串，<code>y</code>&nbsp;个&nbsp;<code>"BB"</code>&nbsp;字符串，和&nbsp;<code>z</code>&nbsp;个&nbsp;<code>"AB"</code>&nbsp;字符串。你需要选择这些字符串中的部分字符串（可以全部选择也可以一个都不选择），将它们按顺序连接得到一个新的字符串。新字符串不能包含子字符串&nbsp;<code>"AAA"</code>&nbsp;或者&nbsp;<code>"BBB"</code>&nbsp;。</p>

<p>请你返回 <em>新字符串的最大可能长度。</em></p>

<p><strong>子字符串</strong>&nbsp;是一个字符串中一段连续 <strong>非空</strong>&nbsp;的字符序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>x = 2, y = 5, z = 1
<b>输出：</b>12
<strong>解释： </strong>我们可以按顺序连接 "BB" ，"AA" ，"BB" ，"AA" ，"BB" 和 "AB" ，得到新字符串 "BBAABBAABBAB" 。
字符串长度为 12 ，无法得到一个更长的符合题目要求的字符串。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>x = 3, y = 2, z = 2
<b>输出：</b>14
<b>解释：</b>我们可以按顺序连接 "AB" ，"AB" ，"AA" ，"BB" ，"AA" ，"BB" 和 "AA" ，得到新字符串 "ABABAABBAABBAA" 。
字符串长度为 14 ，无法得到一个更长的符合题目要求的字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= x, y, z &lt;= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分类讨论

我们观察发现，字符串 `'AA'` 之后只能跟 `'BB'`，而字符串 `'AB'` 可以放在字符串开头或结尾。因此：

-   如果 $x \lt y$，那么我们可以先交替放置 `'BBAABBAA..BB'`，一共放置 $x$ 个 `'AA'` 和 $x+1$ 个 `'BB'`，然后放置剩余的 $z$ 个 `'AB'`，总长度为 $(x \times 2 + z + 1) \times 2$；
-   如果 $x \gt y$，那么我们可以先交替放置 `'AABBAABB..AA'`，一共放置 $y$ 个 `'BB'` 和 $y+1$ 个 `'AA'`，然后放置剩余的 $z$ 个 `'AB'`，总长度为 $(y \times 2 + z + 1) \times 2$；
-   如果 $x = y$，我们只需要交替放置 `'AABB'`，一共放置 $x$ 个 `'AA'` 和 $y$ 个 `'BB'`，然后放置剩余的 $z$ 个 `'AB'`，总长度为 $(x + y + z) \times 2$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestString(self, x: int, y: int, z: int) -> int:
        if x < y:
            return (x * 2 + z + 1) * 2
        if x > y:
            return (y * 2 + z + 1) * 2
        return (x + y + z) * 2
```

#### Java

```java
class Solution {
    public int longestString(int x, int y, int z) {
        if (x < y) {
            return (x * 2 + z + 1) * 2;
        }
        if (x > y) {
            return (y * 2 + z + 1) * 2;
        }
        return (x + y + z) * 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestString(int x, int y, int z) {
        if (x < y) {
            return (x * 2 + z + 1) * 2;
        }
        if (x > y) {
            return (y * 2 + z + 1) * 2;
        }
        return (x + y + z) * 2;
    }
};
```

#### Go

```go
func longestString(x int, y int, z int) int {
	if x < y {
		return (x*2 + z + 1) * 2
	}
	if x > y {
		return (y*2 + z + 1) * 2
	}
	return (x + y + z) * 2
}
```

#### TypeScript

```ts
function longestString(x: number, y: number, z: number): number {
    if (x < y) {
        return (x * 2 + z + 1) * 2;
    }
    if (x > y) {
        return (y * 2 + z + 1) * 2;
    }
    return (x + y + z) * 2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
