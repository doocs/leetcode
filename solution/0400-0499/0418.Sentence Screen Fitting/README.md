---
comments: true
difficulty: 中等
tags:
    - 数组
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [418. 屏幕可显示句子的数量 🔒](https://leetcode.cn/problems/sentence-screen-fitting)

[English Version](/solution/0400-0499/0418.Sentence%20Screen%20Fitting/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <code>rows x cols</code> 的屏幕和一个用 <strong>非空 </strong>的单词列表组成的句子，请你计算出给定句子可以在屏幕上完整显示的次数。</p>

<p>句子中的单词顺序必须保持不变，并且不能将一个单词分成两行。一行中的两个连续单词必须用空白分开。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>sentence = ["hello", "world"], rows = 2, cols = 8
<strong>输出：</strong>1
<strong>解释：</strong>
hello---
world---
字符 '-' 表示屏幕上的一个空白位置。
</pre>

<p>&nbsp;</p>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>sentence = ["a", "bcd", "e"], rows = 3, cols = 6
<strong>输出：</strong>2
<strong>解释：</strong>
a-bcd- 
e-a---
bcd-e-
字符 '-' 表示屏幕上的一个空白位置。
</pre>

<p>&nbsp;</p>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>sentence = ["i", "had", "apple", "pie"], rows = 4, cols = 5
<strong>输出：</strong>1
<strong>解释：</strong>
i-had
apple
pie-i
had--
字符 '-' 表示屏幕上的一个空白位置。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= sentence.length &lt;= 100</code></li>
	<li><code>1 &lt;= sentence[i].length &lt;= 10</code></li>
	<li><code>sentence[i]</code>&nbsp;由小写英文字母组成。</li>
	<li><code>1 &lt;= rows, cols &lt;= 2 * 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

<!-- thinking:start -->

> **思考**
>
> 逐词判断当前行能否放下，行数可达 $10^4$，句子会循环多次，指针在单词间来回移动较繁琐。
>
> 把句子连成带尾空格的环 $s$，用 $\textit{cur}$ 表示已经覆盖的字符数。每一行先加上 $\textit{cols}$：若落点是空格，说明刚好结束一个单词，再前进一格；否则回退到上一个空格，相当于把未写完的单词放到下一行。
>
> 整句出现次数即 $\lfloor \textit{cur}/|s| \rfloor$。尾空格把「单词之间必须有空格」编码进环上，回退保证不会把单词拆开。

<!-- thinking:end -->

我们将句子的每个单词拼接上一个空格，然后把句子拼接起来，得到字符串 $s$。例如，对于句子 `["hello", "world"]`，得到的字符串为 `"hello world "`。记 $s$ 的长度为 $m$。

接下来，我们使用贪心的方法，找到最大的可显示句子数。定义一个变量 $cur$，表示当前已经在屏幕上显示的字符串的长度，初始时 $cur=0$。

我们循环 $rows$ 次，每次循环中，我们首先将 $cur$ 增加 $cols$，如果 $s[cur \bmod m]$ 是一个空格，说明我们可以将完整的若干个单词放置到当前行，因此我们将 $cur$ 增加一个额外的 $1$；否则，说明我们需要回退 $cur$，直到 $cur$ 指向的字符是一个空格为止。然后继续下一次循环。

循环结束，返回 $\lfloor \frac{cur}{m} \rfloor$ 即可。

时间复杂度 $O(rows \times M)$，空间复杂度 $O(L)$。其中 $M$ 是单词的最大长度，而 $L$ 是单词的总长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def wordsTyping(self, sentence: List[str], rows: int, cols: int) -> int:
        s = " ".join(sentence) + " "
        m = len(s)
        cur = 0
        for _ in range(rows):
            cur += cols
            if s[cur % m] == " ":
                cur += 1
            while cur and s[(cur - 1) % m] != " ":
                cur -= 1
        return cur // m
```

#### Java

```java
class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int m = s.length();
        int cur = 0;
        while (rows-- > 0) {
            cur += cols;
            if (s.charAt(cur % m) == ' ') {
                ++cur;
            } else {
                while (cur > 0 && s.charAt((cur - 1) % m) != ' ') {
                    --cur;
                }
            }
        }
        return cur / m;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int wordsTyping(vector<string>& sentence, int rows, int cols) {
        string s;
        for (auto& t : sentence) {
            s += t;
            s += " ";
        }
        int m = s.size();
        int cur = 0;
        while (rows--) {
            cur += cols;
            if (s[cur % m] == ' ') {
                ++cur;
            } else {
                while (cur && s[(cur - 1) % m] != ' ') {
                    --cur;
                }
            }
        }
        return cur / m;
    }
};
```

#### Go

```go
func wordsTyping(sentence []string, rows int, cols int) int {
	s := strings.Join(sentence, " ") + " "
	m := len(s)
	cur := 0
	for i := 0; i < rows; i++ {
		cur += cols
		if s[cur%m] == ' ' {
			cur++
		} else {
			for cur > 0 && s[(cur-1)%m] != ' ' {
				cur--
			}
		}
	}
	return cur / m
}
```

#### TypeScript

```ts
function wordsTyping(sentence: string[], rows: number, cols: number): number {
    const s = sentence.join(' ') + ' ';
    let cur = 0;
    const m = s.length;
    for (let i = 0; i < rows; ++i) {
        cur += cols;
        if (s[cur % m] === ' ') {
            ++cur;
        } else {
            while (cur > 0 && s[(cur - 1) % m] !== ' ') {
                --cur;
            }
        }
    }
    return Math.floor(cur / m);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
