---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3227.Vowels%20Game%20in%20a%20String/README.md
rating: 1451
source: 第 407 场周赛 Q2
tags:
    - 脑筋急转弯
    - 数学
    - 字符串
    - 博弈
---

<!-- problem:start -->

# [3227. 字符串元音游戏](https://leetcode.cn/problems/vowels-game-in-a-string)

[English Version](/solution/3200-3299/3227.Vowels%20Game%20in%20a%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>小红和小明在玩一个字符串元音游戏。</p>

<p>给你一个字符串 <code>s</code>，小红和小明将轮流参与游戏，小红<strong> 先 </strong>开始：</p>

<ul>
	<li>在小红的回合，她必须移除 <code>s</code> 中包含 <strong>奇数 </strong>个元音的任意 <strong>非空</strong> <span data-keyword="substring">子字符串</span>。</li>
	<li>在小明的回合，他必须移除 <code>s</code> 中包含 <strong>偶数 </strong>个元音的任意 <strong>非空</strong> <span data-keyword="substring">子字符串</span>。</li>
</ul>

<p>第一个无法在其回合内进行移除操作的玩家输掉游戏。假设小红和小明都采取 <strong>最优策略 </strong>。</p>

<p>如果小红赢得游戏，返回 <code>true</code>，否则返回 <code>false</code>。</p>

<p>英文元音字母包括：<code>a</code>, <code>e</code>, <code>i</code>, <code>o</code>, 和 <code>u</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "leetcoder"</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong><br />
小红可以执行如下移除操作来赢得游戏：</p>

<ul>
	<li>小红先手，她可以移除加下划线的子字符串 <code>s = "<u><strong>leetco</strong></u>der"</code>，其中包含 3 个元音。结果字符串为 <code>s = "der"</code>。</li>
	<li>小明接着，他可以移除加下划线的子字符串 <code>s = "<u><strong>d</strong></u>er"</code>，其中包含 0 个元音。结果字符串为 <code>s = "er"</code>。</li>
	<li>小红再次操作，她可以移除整个字符串 <code>s = "<strong><u>er</u></strong>"</code>，其中包含 1 个元音。</li>
	<li>又轮到小明，由于字符串为空，无法执行移除操作，因此小红赢得游戏。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "bbcd"</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong><br />
小红在她的第一回合无法执行移除操作，因此小红输掉了游戏。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：脑筋急转弯

我们不妨记字符串中元音字母的个数为 $k$。

如果 $k = 0$，即字符串中没有元音字母，那么小红无法移除任何子字符串，小明直接获胜。

如果 $k$ 为奇数，那么小红可以移除整个字符串，小红直接获胜。

如果 $k$ 为偶数，那么小红可以移除 $k - 1$ 个元音字母，此时剩下一个元音字母，小明无法移除任何子字符串，小红直接获胜。

综上所述，如果字符串中包含元音字母，那么小红获胜，否则小明获胜。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def doesAliceWin(self, s: str) -> bool:
        vowels = set("aeiou")
        return any(c in vowels for c in s)
```

#### Java

```java
class Solution {
    public boolean doesAliceWin(String s) {
        for (int i = 0; i < s.length(); ++i) {
            if ("aeiou".indexOf(s.charAt(i)) != -1) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool doesAliceWin(string s) {
        string vowels = "aeiou";
        for (char c : s) {
            if (vowels.find(c) != string::npos) {
                return true;
            }
        }
        return false;
    }
};
```

#### Go

```go
func doesAliceWin(s string) bool {
	vowels := "aeiou"
	for _, c := range s {
		if strings.ContainsRune(vowels, c) {
			return true
		}
	}
	return false
}
```

#### TypeScript

```ts
function doesAliceWin(s: string): boolean {
    const vowels = 'aeiou';
    for (const c of s) {
        if (vowels.includes(c)) {
            return true;
        }
    }
    return false;
}
```

#### Rust

```rust
impl Solution {
    pub fn does_alice_win(s: String) -> bool {
        let vowels = "aeiou";
        for c in s.chars() {
            if vowels.contains(c) {
                return true;
            }
        }
        false
    }
}
```

#### C#

```cs
public class Solution {
    public bool DoesAliceWin(string s) {
        string vowels = "aeiou";
        foreach (char c in s) {
            if (vowels.Contains(c)) {
                return true;
            }
        }
        return false;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
