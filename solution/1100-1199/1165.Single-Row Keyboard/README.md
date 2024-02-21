# [1165. 单行键盘](https://leetcode.cn/problems/single-row-keyboard)

[English Version](/solution/1100-1199/1165.Single-Row%20Keyboard/README_EN.md)

<!-- tags:哈希表,字符串 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>我们定制了一款特殊的键盘，所有的键都 <strong>排列在一行上</strong>&nbsp;。</p>

<p>给定一个长度为 <code>26</code> 的字符串&nbsp;<code>keyboard</code>&nbsp;，来表示键盘的布局(索引从 <code>0</code> 到 <code>25</code> )。一开始，你的手指在索引 <code>0</code> 处。要输入一个字符，你必须把你的手指移动到所需字符的索引处。手指从索引&nbsp;<code>i</code>&nbsp;移动到索引&nbsp;<code>j</code>&nbsp;所需要的时间是&nbsp;<code>|i - j|</code>。</p>

<p>您需要输入一个字符串&nbsp;<code>word</code>&nbsp;。写一个函数来计算用一个手指输入需要多少时间。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>keyboard = "abcdefghijklmnopqrstuvwxyz", word = "cba"
<strong>输出：</strong>4
<strong>解释：</strong>从 0 号键移动到 2 号键来输出 'c'，又移动到 1 号键来输出 'b'，接着移动到 0 号键来输出 'a'。
总用时 = 2 + 1 + 1 = 4. 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>keyboard = "pqrstuvwxyzabcdefghijklmno", word = "leetcode"
<strong>输出：</strong>73
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>keyboard.length == 26</code></li>
	<li><code>keyboard</code>&nbsp;按某种特定顺序排列，并包含每个小写英文字母一次。</li>
	<li><code>1 &lt;= word.length &lt;= 10<sup>4</sup></code></li>
	<li><code>word[i]</code>&nbsp;为小写英文字母</li>
</ul>

## 解法

### 方法一：哈希表或数组

我们可以用哈希表或者一个长度为 $26$ 的数组 $pos$ 来存储每个字符在键盘上的位置，其中 $pos[c]$ 表示字符 $c$ 在键盘上的位置。

然后我们遍历字符串 $word$，用一个变量 $i$ 记录当前手指所在的位置，初始时 $i = 0$。每次计算当前字符 $c$ 在键盘上的位置 $j$，并将答案增加 $|i - j|$，然后将 $i$ 更新为 $j$。继续遍历下一个字符，直到遍历完整个字符串 $word$。

遍历完字符串 $word$ 之后，即可得到答案。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为字符串 $word$ 的长度；而 $C$ 为字符集大小，本题中 $C = 26$。

<!-- tabs:start -->

```python
class Solution:
    def calculateTime(self, keyboard: str, word: str) -> int:
        pos = {c: i for i, c in enumerate(keyboard)}
        ans = i = 0
        for c in word:
            ans += abs(pos[c] - i)
            i = pos[c]
        return ans
```

```java
class Solution {
    public int calculateTime(String keyboard, String word) {
        int[] pos = new int[26];
        for (int i = 0; i < 26; ++i) {
            pos[keyboard.charAt(i) - 'a'] = i;
        }
        int ans = 0, i = 0;
        for (int k = 0; k < word.length(); ++k) {
            int j = pos[word.charAt(k) - 'a'];
            ans += Math.abs(i - j);
            i = j;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int calculateTime(string keyboard, string word) {
        int pos[26];
        for (int i = 0; i < 26; ++i) {
            pos[keyboard[i] - 'a'] = i;
        }
        int ans = 0, i = 0;
        for (char& c : word) {
            int j = pos[c - 'a'];
            ans += abs(i - j);
            i = j;
        }
        return ans;
    }
};
```

```go
func calculateTime(keyboard string, word string) (ans int) {
	pos := [26]int{}
	for i, c := range keyboard {
		pos[c-'a'] = i
	}
	i := 0
	for _, c := range word {
		j := pos[c-'a']
		ans += abs(i - j)
		i = j
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function calculateTime(keyboard: string, word: string): number {
    const pos: number[] = Array(26).fill(0);
    for (let i = 0; i < 26; ++i) {
        pos[keyboard.charCodeAt(i) - 97] = i;
    }
    let ans = 0;
    let i = 0;
    for (const c of word) {
        const j = pos[c.charCodeAt(0) - 97];
        ans += Math.abs(i - j);
        i = j;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
