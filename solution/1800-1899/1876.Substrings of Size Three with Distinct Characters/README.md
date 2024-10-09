---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1876.Substrings%20of%20Size%20Three%20with%20Distinct%20Characters/README.md
rating: 1248
source: 第 53 场双周赛 Q1
tags:
    - 哈希表
    - 字符串
    - 计数
    - 滑动窗口
---

<!-- problem:start -->

# [1876. 长度为三且各字符不同的子字符串](https://leetcode.cn/problems/substrings-of-size-three-with-distinct-characters)

[English Version](/solution/1800-1899/1876.Substrings%20of%20Size%20Three%20with%20Distinct%20Characters/README_EN.md)

## 题目描述

<!-- description:start -->

<p>如果一个字符串不含有任何重复字符，我们称这个字符串为 <strong>好</strong> 字符串。</p>

<p>给你一个字符串 <code>s</code> ，请你返回 <code>s</code> 中长度为 <strong>3</strong> 的 <strong>好子字符串</strong> 的数量。</p>

<p>注意，如果相同的好子字符串出现多次，每一次都应该被记入答案之中。</p>

<p><strong>子字符串</strong> 是一个字符串中连续的字符序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "xyzzaz"
<b>输出：</b>1
<b>解释：</b>总共有 4 个长度为 3 的子字符串："xyz"，"yzz"，"zza" 和 "zaz" 。
唯一的长度为 3 的好子字符串是 "xyz" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "aababcabc"
<b>输出：</b>4
<b>解释：</b>总共有 7 个长度为 3 的子字符串："aab"，"aba"，"bab"，"abc"，"bca"，"cab" 和 "abc" 。
好子字符串包括 "abc"，"bca"，"cab" 和 "abc" 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 100</code></li>
	<li><code>s</code>​​​​​​ 只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滑动窗口

我们可以维护一个滑动窗口，使得窗口内的字符不重复。初始时，我们用一个长度为 $26$ 的二进制整数 $\textit{mask}$ 表示窗口内的字符，其中第 $i$ 位为 $1$ 表示字符 $i$ 在窗口内出现过，否则表示字符 $i$ 在窗口内没有出现过。

然后，我们遍历字符串 $s$，对于每一个位置 $r$，如果 $\textit{s}[r]$ 在窗口内出现过，我们需要将窗口的左边界 $l$ 右移，直到窗口内不再有重复的字符。在这之后，我们将 $\textit{s}[r]$ 加入窗口内，此时如果窗口的长度大于等于 $3$，那么我们就找到了一个以 $\textit{s}[r]$ 结尾的长度为 $3$ 的好子字符串。

遍历结束后，我们就找到了所有的好子字符串的数量。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

> 该解法可以拓展到长度为 $k$ 的好子字符串的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countGoodSubstrings(self, s: str) -> int:
        ans = mask = l = 0
        for r, x in enumerate(map(lambda c: ord(c) - 97, s)):
            while mask >> x & 1:
                y = ord(s[l]) - 97
                mask ^= 1 << y
                l += 1
            mask |= 1 << x
            ans += int(r - l + 1 >= 3)
        return ans
```

#### Java

```java
class Solution {
    public int countGoodSubstrings(String s) {
        int ans = 0;
        int n = s.length();
        for (int l = 0, r = 0, mask = 0; r < n; ++r) {
            int x = s.charAt(r) - 'a';
            while ((mask >> x & 1) == 1) {
                int y = s.charAt(l++) - 'a';
                mask ^= 1 << y;
            }
            mask |= 1 << x;
            ans += r - l + 1 >= 3 ? 1 : 0;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countGoodSubstrings(string s) {
        int ans = 0;
        int n = s.length();
        for (int l = 0, r = 0, mask = 0; r < n; ++r) {
            int x = s[r] - 'a';
            while ((mask >> x & 1) == 1) {
                int y = s[l++] - 'a';
                mask ^= 1 << y;
            }
            mask |= 1 << x;
            ans += r - l + 1 >= 3 ? 1 : 0;
        }
        return ans;
    }
};
```

#### Go

```go
func countGoodSubstrings(s string) (ans int) {
	mask, l := 0, 0
	for r, c := range s {
		x := int(c - 'a')
		for (mask>>x)&1 == 1 {
			y := int(s[l] - 'a')
			l++
			mask ^= 1 << y
		}
		mask |= 1 << x
		if r-l+1 >= 3 {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function countGoodSubstrings(s: string): number {
    let ans = 0;
    const n = s.length;
    for (let l = 0, r = 0, mask = 0; r < n; ++r) {
        const x = s.charCodeAt(r) - 'a'.charCodeAt(0);
        while ((mask >> x) & 1) {
            const y = s.charCodeAt(l++) - 'a'.charCodeAt(0);
            mask ^= 1 << y;
        }
        mask |= 1 << x;
        ans += r - l + 1 >= 3 ? 1 : 0;
    }
    return ans;
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function countGoodSubstrings($s) {
        $ans = 0;
        $n = strlen($s);
        $l = 0;
        $r = 0;
        $mask = 0;

        while ($r < $n) {
            $x = ord($s[$r]) - ord('a');
            while (($mask >> $x) & 1) {
                $y = ord($s[$l++]) - ord('a');
                $mask ^= 1 << $y;
            }
            $mask |= 1 << $x;
            if ($r - $l + 1 >= 3) {
                $ans++;
            }
            $r++;
        }

        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
