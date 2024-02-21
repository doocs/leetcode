# [418. 屏幕可显示句子的数量](https://leetcode.cn/problems/sentence-screen-fitting)

[English Version](/solution/0400-0499/0418.Sentence%20Screen%20Fitting/README_EN.md)

<!-- tags:字符串,动态规划,模拟 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>rows x cols</code> 的屏幕和一个用 <strong>非空 </strong>的单词列表组成的句子，请你计算出给定句子可以在屏幕上完整显示的次数。</p>

<p><strong>注意：</strong></p>

<ol>
	<li>一个单词不能拆分成两行。</li>
	<li>单词在句子中的顺序必须保持不变。</li>
	<li><strong>在一行中 </strong>的两个连续单词必须用一个空格符分隔。</li>
	<li>句子中的单词总量不会超过 100。</li>
	<li>每个单词的长度大于 0 且不会超过 10。</li>
	<li>1 &le; <code>rows</code>, <code>cols</code> &le; 20,000.</li>
</ol>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>
rows = 2, cols = 8, 句子 sentence = [&quot;hello&quot;, &quot;world&quot;]

<strong>输出：</strong>
1

<strong>解释：</strong>
hello---
world---

<strong>字符 &#39;-&#39; 表示屏幕上的一个空白位置。</strong>
</pre>

<p>&nbsp;</p>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>
rows = 3, cols = 6, 句子 sentence = [&quot;a&quot;, &quot;bcd&quot;, &quot;e&quot;]

<strong>输出：</strong>
2

<strong>解释：</strong>
a-bcd- 
e-a---
bcd-e-

<strong>字符 &#39;-&#39; 表示屏幕上的一个空白位置。</strong>
</pre>

<p>&nbsp;</p>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>
rows = 4, cols = 5, 句子 sentence = [&quot;I&quot;, &quot;had&quot;, &quot;apple&quot;, &quot;pie&quot;]

<strong>输出：</strong>
1

<strong>解释：</strong>
I-had
apple
pie-I
had--

<strong>字符 &#39;-&#39; 表示屏幕上的一个空白位置。</strong>
</pre>

<p>&nbsp;</p>

## 解法

### 方法一：贪心

我们将句子的每个单词拼接上一个空格，然后把句子拼接起来，得到字符串 $s$。例如，对于句子 `["hello", "world"]`，得到的字符串为 `"hello world "`。记 $s$ 的长度为 $m$。

接下来，我们使用贪心的方法，找到最大的可显示句子数。定义一个变量 $cur$，表示当前已经在屏幕上显示的字符串的长度，初始时 $cur=0$。

我们循环 $rows$ 次，每次循环中，我们首先将 $cur$ 增加 $cols$，如果 $s[cur \bmod m]$ 是一个空格，说明我们可以将完整的若干个单词放置到当前行，因此我们将 $cur$ 增加一个额外的 $1$；否则，说明我们需要回退 $cur$，直到 $cur$ 指向的字符是一个空格为止。然后继续下一次循环。

循环结束，返回 $\lfloor \frac{cur}{m} \rfloor$ 即可。

时间复杂度 $O(rows \times M)$，空间复杂度 $O(L)$。其中 $M$ 是单词的最大长度，而 $L$ 是单词的总长度。

<!-- tabs:start -->

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

<!-- end -->
