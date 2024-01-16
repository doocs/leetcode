# [1927. 求和游戏](https://leetcode.cn/problems/sum-game)

[English Version](/solution/1900-1999/1927.Sum%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Alice 和 Bob 玩一个游戏，两人轮流行动，<strong>Alice 先手</strong> 。</p>

<p>给你一个 <strong>偶数长度</strong> 的字符串 <code>num</code> ，每一个字符为数字字符或者 <code>'?'</code> 。每一次操作中，如果 <code>num</code> 中至少有一个 <code>'?'</code> ，那么玩家可以执行以下操作：</p>

<ol>
	<li>选择一个下标 <code>i</code> 满足 <code>num[i] == '?'</code> 。</li>
	<li>将 <code>num[i]</code> 用 <code>'0'</code> 到 <code>'9'</code> 之间的一个数字字符替代。</li>
</ol>

<p>当 <code>num</code> 中没有<span style=""> </span><code>'?'</code> 时，游戏结束。</p>

<p>Bob 获胜的条件是 <code>num</code> 中前一半数字的和 <strong>等于</strong> 后一半数字的和。Alice 获胜的条件是前一半的和与后一半的和 <strong>不相等</strong> 。</p>

<ul>
	<li>比方说，游戏结束时 <code>num = "243801"</code> ，那么 Bob 获胜，因为 <code>2+4+3 = 8+0+1</code> 。如果游戏结束时 <code>num = "243803"</code> ，那么 Alice 获胜，因为 <code>2+4+3 != 8+0+3</code> 。</li>
</ul>

<p>在 Alice 和 Bob 都采取 <strong>最优</strong> 策略的前提下，如果 Alice 获胜，请返回 <code>true</code> ，如果 Bob 获胜，请返回 <code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>num = "5023"
<b>输出：</b>false
<b>解释：</b>num 中没有 '?' ，没法进行任何操作。
前一半的和等于后一半的和：5 + 0 = 2 + 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>num = "25??"
<b>输出：</b>true
<strong>解释：</strong>Alice 可以将两个 '?' 中的一个替换为 '9' ，Bob 无论如何都无法使前一半的和等于后一半的和。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>num = "?3295???"
<b>输出：</b>false
<b>解释：</b>Bob 总是能赢。一种可能的结果是：
- Alice 将第一个 '?' 用 '9' 替换。num = "93295???" 。
- Bob 将后面一半中的一个 '?' 替换为 '9' 。num = "932959??" 。
- Alice 将后面一半中的一个 '?' 替换为 '2' 。num = "9329592?" 。
- Bob 将后面一半中最后一个 '?' 替换为 '7' 。num = "93295927" 。
Bob 获胜，因为 9 + 3 + 2 + 9 = 5 + 9 + 2 + 7 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= num.length <= 10<sup>5</sup></code></li>
	<li><code>num.length</code> 是 <strong>偶数</strong> 。</li>
	<li><code>num</code> 只包含数字字符和 <code>'?'</code> 。</li>
</ul>

## 解法

### 方法一：分类讨论

如果 `'?'` 的个数为奇数，那么 Alice 一定会获胜，因为她可以选择将最后一个 `'?'` 替换为任何一个数字，使得前一半的和与后一半的和不相等。

如果 `'?'` 的个数为偶数，Alice 为了使得前一半的和与后一半的和不相等，那么会选择在当前和较大的一半数字中放置 $9$，在当前和较小的一半数字中放置 $0$，而 Bob 为了使得前后两半的和相等，那么会选择在 Alice 替换数字的另一半放置一个与 Alice 替换数字相同的数字。

因此，最终会使得剩下的所有偶数个 `'?'` 集中在其中一半。假设当前两半的数字差值为 $d$。

我们先考虑，如果剩下两个 `'?'`，差值为 $x$，此时：

-   如果 $x \lt 9$，那么 Alice 必胜，因为 Alice 可以将其中一个 `'?'` 替换为 $9$，使得前一半的和与后一半的和不相等；
-   如果 $x \gt 9$，那么 Alice 必胜，因为 Alice 可以将其中一个 `'?'` 替换为 $0$，使得前一半的和与后一半的和不相等；
-   如果 $x = 9$，那么 Bob 必胜，假设 Alice 替换的数字为 $a$，那么 Bob 可以将另一个 `'?'` 替换为 $9 - a$，使得前后两半的和相等。

因此，如果两半数字差值为 $d= \frac{9 \times cnt}{2}$，其中 $cnt$ 为剩下的 `'?'` 的个数，那么 Bob 必胜，否则 Alice 必胜。

时间复杂度 $O(n)$，其中 $n$ 为字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def sumGame(self, num: str) -> bool:
        n = len(num)
        cnt1 = num[: n // 2].count("?")
        cnt2 = num[n // 2 :].count("?")
        s1 = sum(int(x) for x in num[: n // 2] if x != "?")
        s2 = sum(int(x) for x in num[n // 2 :] if x != "?")
        return (cnt1 + cnt2) % 2 == 1 or s1 - s2 != 9 * (cnt2 - cnt1) // 2
```

```java
class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int cnt1 = 0, cnt2 = 0;
        int s1 = 0, s2 = 0;
        for (int i = 0; i < n / 2; ++i) {
            if (num.charAt(i) == '?') {
                cnt1++;
            } else {
                s1 += num.charAt(i) - '0';
            }
        }
        for (int i = n / 2; i < n; ++i) {
            if (num.charAt(i) == '?') {
                cnt2++;
            } else {
                s2 += num.charAt(i) - '0';
            }
        }
        return (cnt1 + cnt2) % 2 == 1 || s1 - s2 != 9 * (cnt2 - cnt1) / 2;
    }
}
```

```cpp
class Solution {
public:
    bool sumGame(string num) {
        int n = num.size();
        int cnt1 = 0, cnt2 = 0;
        int s1 = 0, s2 = 0;
        for (int i = 0; i < n / 2; ++i) {
            if (num[i] == '?') {
                cnt1++;
            } else {
                s1 += num[i] - '0';
            }
        }
        for (int i = n / 2; i < n; ++i) {
            if (num[i] == '?') {
                cnt2++;
            } else {
                s2 += num[i] - '0';
            }
        }
        return (cnt1 + cnt2) % 2 == 1 || (s1 - s2) != 9 * (cnt2 - cnt1) / 2;
    }
};
```

```go
func sumGame(num string) bool {
	n := len(num)
	var cnt1, cnt2, s1, s2 int
	for i := 0; i < n/2; i++ {
		if num[i] == '?' {
			cnt1++
		} else {
			s1 += int(num[i] - '0')
		}
	}
	for i := n / 2; i < n; i++ {
		if num[i] == '?' {
			cnt2++
		} else {
			s2 += int(num[i] - '0')
		}
	}
	return (cnt1+cnt2)%2 == 1 || s1-s2 != (cnt2-cnt1)*9/2
}
```

```ts
function sumGame(num: string): boolean {
    const n = num.length;
    let [cnt1, cnt2, s1, s2] = [0, 0, 0, 0];
    for (let i = 0; i < n >> 1; ++i) {
        if (num[i] === '?') {
            ++cnt1;
        } else {
            s1 += num[i].charCodeAt(0) - '0'.charCodeAt(0);
        }
    }
    for (let i = n >> 1; i < n; ++i) {
        if (num[i] === '?') {
            ++cnt2;
        } else {
            s2 += num[i].charCodeAt(0) - '0'.charCodeAt(0);
        }
    }
    return (cnt1 + cnt2) % 2 === 1 || 2 * (s1 - s2) !== 9 * (cnt2 - cnt1);
}
```

<!-- tabs:end -->

<!-- end -->
