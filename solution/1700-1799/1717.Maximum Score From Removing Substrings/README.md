---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1717.Maximum%20Score%20From%20Removing%20Substrings/README.md
rating: 1867
source: 第 43 场双周赛 Q2
tags:
    - 栈
    - 贪心
    - 字符串
---

<!-- problem:start -->

# [1717. 删除子字符串的最大得分](https://leetcode.cn/problems/maximum-score-from-removing-substrings)

[English Version](/solution/1700-1799/1717.Maximum%20Score%20From%20Removing%20Substrings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> 和两个整数 <code>x</code> 和 <code>y</code> 。你可以执行下面两种操作任意次。</p>

<ul>
	<li>删除子字符串 <code>"ab"</code> 并得到 <code>x</code> 分。

    <ul>
    	<li>比方说，从 <code>"c<strong>ab</strong>xbae"</code> 删除 <code>ab</code> ，得到 <code>"cxbae"</code> 。</li>
    </ul>
    </li>
    <li>删除子字符串<code>"ba"</code> 并得到 <code>y</code> 分。
    <ul>
    	<li>比方说，从 <code>"cabx<strong>ba</strong>e"</code> 删除 <code>ba</code> ，得到 <code>"cabxe"</code> 。</li>
    </ul>
    </li>

</ul>

<p>请返回对 <code>s</code> 字符串执行上面操作若干次能得到的最大得分。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "cdbcbbaaabab", x = 4, y = 5
<b>输出：</b>19
<strong>解释：</strong>
- 删除 "cdbcbbaaa<strong>ba</strong>b" 中加粗的 "ba" ，得到 s = "cdbcbbaaab" ，加 5 分。
- 删除 "cdbcbbaa<strong>ab</strong>" 中加粗的 "ab" ，得到 s = "cdbcbbaa" ，加 4 分。
- 删除 "cdbcb<strong>ba</strong>a" 中加粗的 "ba" ，得到 s = "cdbcba" ，加 5 分。
- 删除 "cdbc<strong>ba</strong>" 中加粗的 "ba" ，得到 s = "cdbc" ，加 5 分。
总得分为 5 + 4 + 5 + 5 = 19 。</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "aabbaaxybbaabb", x = 5, y = 4
<b>输出：</b>20
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= x, y &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> 只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

我们不妨假设子字符串 "ab" 的得分总是不低于子字符串 "ba" 的得分，如果不是，我们可以交换 "a" 和 "b"，同时交换 $x$ 和 $y$。

接下来，我们只需要考虑字符串中只包含 "a" 和 "b" 的情况。如果字符串中包含其他字符，我们可以将其视为一个分割点，将字符串分割成若干个只包含 "a" 和 "b" 的子字符串，然后分别计算每个子字符串的得分。

我们观察发现，对于一个只包含 "a" 和 "b" 的子字符串，无论采取什么样的操作，最后一定只剩下一种字符，或者空串。由于每次操作都会同时删除一个 "a" 和一个 "b"，因此总的操作次数一定是固定的。我们可以贪心地先删除 "ab"，再删除 "ba"，这样可以保证得分最大。

因此，我们可以使用两个变量 $\textit{cnt1}$ 和 $\textit{cnt2}$ 分别记录 "a" 和 "b" 的数量，然后遍历字符串，根据当前字符的不同情况更新 $\textit{cnt1}$ 和 $\textit{cnt2}$，并计算得分。

对于当前遍历到的字符 $c$：

-   如果 $c$ 是 "a"，由于要先删除 "ab"，因此此时我们不消除该字符，只增加 $\textit{cnt1}$；
-   如果 $c$ 是 "b"，如果此时 $\textit{cnt1} > 0$，我们可以消除一个 "ab"，并增加 $x$ 分，否则我们只能增加 $\textit{cnt2}$；
-   如果 $c$ 是其他字符，那么对于该子字符串，我们剩下了一个 $\textit{cnt2}$ 个 "b" 和 $\textit{cnt1}$ 个 "a"，我们可以消除 $\min(\textit{cnt1}, \textit{cnt2})$ 个 "ab"，并增加 $y$ 分。

遍历结束后，我们还需要额外处理一下剩余的 "ab"，增加若干个 $y$ 分。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumGain(self, s: str, x: int, y: int) -> int:
        a, b = "a", "b"
        if x < y:
            x, y = y, x
            a, b = b, a
        ans = cnt1 = cnt2 = 0
        for c in s:
            if c == a:
                cnt1 += 1
            elif c == b:
                if cnt1:
                    ans += x
                    cnt1 -= 1
                else:
                    cnt2 += 1
            else:
                ans += min(cnt1, cnt2) * y
                cnt1 = cnt2 = 0
        ans += min(cnt1, cnt2) * y
        return ans
```

#### Java

```java
class Solution {
    public int maximumGain(String s, int x, int y) {
        char a = 'a', b = 'b';
        if (x < y) {
            int t = x;
            x = y;
            y = t;
            char c = a;
            a = b;
            b = c;
        }
        int ans = 0, cnt1 = 0, cnt2 = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == a) {
                cnt1++;
            } else if (c == b) {
                if (cnt1 > 0) {
                    ans += x;
                    cnt1--;
                } else {
                    cnt2++;
                }
            } else {
                ans += Math.min(cnt1, cnt2) * y;
                cnt1 = 0;
                cnt2 = 0;
            }
        }
        ans += Math.min(cnt1, cnt2) * y;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumGain(string s, int x, int y) {
        char a = 'a', b = 'b';
        if (x < y) {
            swap(x, y);
            swap(a, b);
        }

        int ans = 0, cnt1 = 0, cnt2 = 0;
        for (char c : s) {
            if (c == a) {
                cnt1++;
            } else if (c == b) {
                if (cnt1) {
                    ans += x;
                    cnt1--;
                } else {
                    cnt2++;
                }
            } else {
                ans += min(cnt1, cnt2) * y;
                cnt1 = 0;
                cnt2 = 0;
            }
        }
        ans += min(cnt1, cnt2) * y;
        return ans;
    }
};
```

#### Go

```go
func maximumGain(s string, x int, y int) (ans int) {
	a, b := 'a', 'b'
	if x < y {
		x, y = y, x
		a, b = b, a
	}

	var cnt1, cnt2 int
	for _, c := range s {
		if c == a {
			cnt1++
		} else if c == b {
			if cnt1 > 0 {
				ans += x
				cnt1--
			} else {
				cnt2++
			}
		} else {
			ans += min(cnt1, cnt2) * y
			cnt1, cnt2 = 0, 0
		}
	}
	ans += min(cnt1, cnt2) * y
	return
}
```

#### TypeScript

```ts
function maximumGain(s: string, x: number, y: number): number {
    let [a, b] = ['a', 'b'];
    if (x < y) {
        [x, y] = [y, x];
        [a, b] = [b, a];
    }

    let [ans, cnt1, cnt2] = [0, 0, 0];
    for (let c of s) {
        if (c === a) {
            cnt1++;
        } else if (c === b) {
            if (cnt1) {
                ans += x;
                cnt1--;
            } else {
                cnt2++;
            }
        } else {
            ans += Math.min(cnt1, cnt2) * y;
            cnt1 = 0;
            cnt2 = 0;
        }
    }
    ans += Math.min(cnt1, cnt2) * y;
    return ans;
}
```

#### JavaScript

```js
function maximumGain(s, x, y) {
    let [a, b] = ['a', 'b'];
    if (x < y) {
        [x, y] = [y, x];
        [a, b] = [b, a];
    }

    let [ans, cnt1, cnt2] = [0, 0, 0];
    for (let c of s) {
        if (c === a) {
            cnt1++;
        } else if (c === b) {
            if (cnt1) {
                ans += x;
                cnt1--;
            } else {
                cnt2++;
            }
        } else {
            ans += Math.min(cnt1, cnt2) * y;
            cnt1 = 0;
            cnt2 = 0;
        }
    }
    ans += Math.min(cnt1, cnt2) * y;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Greedy + Stack

<!-- tabs:start -->

#### TypeScript

```ts
function maximumGain(s: string, x: number, y: number): number {
    const stk: string[] = [];
    const pairs: Record<string, string> = { a: 'b', b: 'a' };
    const pair = x > y ? ['a', 'b'] : ['b', 'a'];
    let str = [...s];
    let ans = 0;
    let havePairs = true;

    while (havePairs) {
        for (const p of pair) {
            havePairs = true;

            for (const ch of str) {
                if (stk.at(-1) === p && ch === pairs[p]) {
                    stk.pop();
                } else stk.push(ch);
            }

            if (str.length === stk.length) havePairs = false;

            const multiplier = p === 'a' ? x : y;
            ans += (multiplier * (str.length - stk.length)) / 2;
            str = [...stk];
            stk.length = 0;
        }
    }

    return ans;
}
```

#### JavaeScript

```js
function maximumGain(s, x, y) {
    const stk = [];
    const pairs = { a: 'b', b: 'a' };
    const pair = x > y ? ['a', 'b'] : ['b', 'a'];
    let str = [...s];
    let ans = 0;
    let havePairs = true;

    while (havePairs) {
        for (const p of pair) {
            havePairs = true;

            for (const ch of str) {
                if (stk.at(-1) === p && ch === pairs[p]) {
                    stk.pop();
                } else stk.push(ch);
            }

            if (str.length === stk.length) havePairs = false;

            const multiplier = p === 'a' ? x : y;
            ans += (multiplier * (str.length - stk.length)) / 2;
            str = [...stk];
            stk.length = 0;
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
