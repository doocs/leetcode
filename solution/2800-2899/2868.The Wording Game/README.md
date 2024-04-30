# [2868. 单词游戏 🔒](https://leetcode.cn/problems/the-wording-game)

[English Version](/solution/2800-2899/2868.The%20Wording%20Game/README_EN.md)

<!-- tags:数组,数学,双指针,字符串,博弈 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>Alice 和 Bob 分别拥有一个&nbsp;<strong>按字典序排序&nbsp;</strong>的字符串数组，分别命名为 <code>a</code>&nbsp;和 <code>b</code>。</p>

<p>他们正在玩一个单词游戏，遵循以下规则：</p>

<ul>
	<li>每一轮，当前玩家应该从他的列表中选择一个单词，并且选择的单词比上一个单词 <strong>紧邻大</strong>；然后轮到另一名玩家。</li>
	<li>如果一名玩家在自己的回合中无法选择单词，则输掉比赛。</li>
</ul>

<p>Alice 通过选择在 <strong>字典序最小</strong> 的单词开始游戏。</p>

<p>给定 <code>a</code> 和 <code>b</code>，已知两名玩家都按最佳策略玩游戏，如果 Alice 可以获胜，则返回 <code>true</code>&nbsp;，否则返回 <code>false</code>。</p>

<p>如果满足以下条件，则称一个单词 <code>w</code>&nbsp;比另一个单词 <code>z</code>&nbsp;<strong>紧邻大</strong>：</p>

<ul>
	<li><code>w</code> 在&nbsp;<strong>字典序上大于</strong> <code>z</code>。</li>
	<li>如果 <code>w<sub>1</sub></code> 是 <code>w</code> 的第一个字母，<code>z<sub>1</sub></code> 是 <code>z</code> 的第一个字母，那么 <code>w<sub>1</sub></code> 应该 <strong>等于</strong> <code>z<sub>1</sub></code> 或者是字母表中 <code>z<sub>1</sub></code> <strong>后面相邻&nbsp;</strong>的字母。</li>
	<li>例如，单词 <code>"care"</code>&nbsp;比&nbsp;<code>"book"</code> 和 <code>"car"</code>&nbsp;紧邻大，但不比&nbsp;<code>"ant"</code> 或 <code>"cook"</code>&nbsp;紧邻大。</li>
</ul>

<p>如果在 <code>s</code> 和 <code>t</code> 不同的第一个位置处，字符串 <code>s</code>&nbsp;的字母比字符串 <code>t</code>&nbsp;的字母在字母表中的顺序更靠后，则称为字符串 <code>s</code> 在 <strong>字典序上大于</strong> 字符串 <code>t</code>。如果前 <code>min(s.length, t.length)</code> 个字符没有区别，那么较长的字符串是在字典序上较大的那一个。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> a = ["avokado","dabar"], b = ["brazil"]

<strong>输出:</strong> false

<strong>解释:</strong> Alice 必须从单词 "avokado" 来开始游戏，因为这是她最小的单词，然后 Bob 使用他唯一的单词 "brazil"，他可以使用它因为它的第一个字母 'b' 在 Alice 的单词的第一个字母 'a' 之后。

Alice 无法出牌，因为剩下的唯一单词的第一个字母既不等于 'b' 也不是 'b' 之后的字母 'c'。

所以，Alice 输了，游戏结束。</pre>

<strong>示例 2：</strong>

<pre>
<strong>输入:</strong> a = ["ananas","atlas","banana"], b = ["albatros","cikla","nogomet"]

<strong>输出:</strong> true

<strong>解释:</strong> Alice 必须从单词 "ananas" 来开始游戏。

Bob 无法出牌，因为他唯一拥有的以字母 'a' 或 'b' 开头的单词是 "albatros"，而它比 Alice 的单词小。

所以，Alice 获胜，游戏结束。</pre>

<strong>示例 3：</strong>

<pre>
<strong>输入:</strong> a = ["hrvatska","zastava"], b = ["bijeli","galeb"]

<strong>输出:</strong> true

<strong>解释:</strong> Alice 必须从单词 "hrvatska" 来开始游戏。

Bob 无法出牌，因为他的两个单词的第一个字母都比 Alice 的单词的第一个字母 'h' 小。

所以，Alice 获胜，游戏结束。</pre>

<p>&nbsp;</p>

<p><strong>约束条件：</strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length &lt;= 10<sup>5</sup></code></li>
	<li><code>a[i]</code> 和 <code>b[i]</code> 仅包含小写英文字母。</li>
	<li><code>a</code> 和 <code>b</code> 按 <strong>字典序排序</strong>。</li>
	<li><code>a</code> 和 <code>b</code> 中所有的单词都是&nbsp;<strong>不同的</strong>。</li>
	<li><code>a</code> 和 <code>b</code> 中所有单词的长度之和不超过 <code>10<sup>6</sup></code>。</li>
</ul>

## 解法

### 方法一：模拟

我们记当前轮到 $Alice$ 的回合为 $k=0$，轮到 $Bob$ 的回合为 $k=1$。我们用 $i$ 记录 $Alice$ 的下标，用 $j$ 记录 $Bob$ 的下标，用 $w$ 记录当前轮到的玩家的单词。初始时 $i=1$, $j=0$, $w=a[0]$。

我们不断地进行如下操作：

如果 $k=1$，则我们判断 $j$ 是否等于 $b$ 的长度，如果等于则说明 $Alice$ 获胜，返回 $true$；否则我们判断 $b[j]$ 的第一个字母是否等于 $w$ 的第一个字母，如果等于则我们判断 $b[j]$ 是否大于 $w$，或者 $b[j]$ 的第一个字母是否比 $w$ 的第一个字母大 $1$，如果是则说明 $Bob$ 可以出第 $j$ 个单词，我们令 $w=b[j]$，并将 $k$ 取反；否则说明 $Bob$ 无法出第 $j$ 个单词，我们令 $j$ 加一。

如果 $k=0$，则我们判断 $i$ 是否等于 $a$ 的长度，如果等于则说明 $Bob$ 获胜，返回 $false$；否则我们判断 $a[i]$ 的第一个字母是否等于 $w$ 的第一个字母，如果等于则我们判断 $a[i]$ 是否大于 $w$，或者 $a[i]$ 的第一个字母是否比 $w$ 的第一个字母大 $1$，如果是则说明 $Alice$ 可以出第 $i$ 个单词，我们令 $w=a[i]$，并将 $k$ 取反；否则说明 $Alice$ 无法出第 $i$ 个单词，我们令 $i$ 加一。

时间复杂度 $O(m + n)$，其中 $m$ 和 $n$ 分别是数组 $a$ 和 $b$ 的长度。我们只需要遍历数组一次。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def canAliceWin(self, a: List[str], b: List[str]) -> bool:
        i, j, k = 1, 0, 1
        w = a[0]
        while 1:
            if k:
                if j == len(b):
                    return True
                if (b[j][0] == w[0] and b[j] > w) or ord(b[j][0]) - ord(w[0]) == 1:
                    w = b[j]
                    k ^= 1
                j += 1
            else:
                if i == len(a):
                    return False
                if (a[i][0] == w[0] and a[i] > w) or ord(a[i][0]) - ord(w[0]) == 1:
                    w = a[i]
                    k ^= 1
                i += 1
```

```java
class Solution {
    public boolean canAliceWin(String[] a, String[] b) {
        int i = 1, j = 0;
        boolean k = true;
        String w = a[0];
        while (true) {
            if (k) {
                if (j == b.length) {
                    return true;
                }
                if ((b[j].charAt(0) == w.charAt(0) && w.compareTo(b[j]) < 0)
                    || b[j].charAt(0) - w.charAt(0) == 1) {
                    w = b[j];
                    k = !k;
                }
                ++j;
            } else {
                if (i == a.length) {
                    return false;
                }
                if ((a[i].charAt(0) == w.charAt(0) && w.compareTo(a[i]) < 0)
                    || a[i].charAt(0) - w.charAt(0) == 1) {
                    w = a[i];
                    k = !k;
                }
                ++i;
            }
        }
    }
}
```

```cpp
class Solution {
public:
    bool canAliceWin(vector<string>& a, vector<string>& b) {
        int i = 1, j = 0, k = 1;
        string w = a[0];
        while (1) {
            if (k) {
                if (j == b.size()) {
                    return true;
                }
                if ((b[j][0] == w[0] && w < b[j]) || b[j][0] - w[0] == 1) {
                    w = b[j];
                    k ^= 1;
                }
                ++j;
            } else {
                if (i == a.size()) {
                    return false;
                }
                if ((a[i][0] == w[0] && w < a[i]) || a[i][0] - w[0] == 1) {
                    w = a[i];
                    k ^= 1;
                }
                ++i;
            }
        }
    }
};
```

```go
func canAliceWin(a []string, b []string) bool {
	i, j, k := 1, 0, 1
	w := a[0]
	for {
		if k&1 == 1 {
			if j == len(b) {
				return true
			}
			if (b[j][0] == w[0] && w < b[j]) || b[j][0]-w[0] == 1 {
				w = b[j]
				k ^= 1
			}
			j++
		} else {
			if i == len(a) {
				return false
			}
			if (a[i][0] == w[0] && w < a[i]) || a[i][0]-w[0] == 1 {
				w = a[i]
				k ^= 1
			}
			i++
		}
	}
}
```

```ts
function canAliceWin(a: string[], b: string[]): boolean {
    let [i, j, k] = [1, 0, 1];
    let w = a[0];
    while (1) {
        if (k) {
            if (j === b.length) {
                return true;
            }
            if ((b[j][0] === w[0] && w < b[j]) || b[j].charCodeAt(0) - w.charCodeAt(0) === 1) {
                w = b[j];
                k ^= 1;
            }
            ++j;
        } else {
            if (i === a.length) {
                return false;
            }
            if ((a[i][0] === w[0] && w < a[i]) || a[i].charCodeAt(0) - w.charCodeAt(0) === 1) {
                w = a[i];
                k ^= 1;
            }
            ++i;
        }
    }
}
```

<!-- tabs:end -->

<!-- end -->
