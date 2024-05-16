---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2262.Total%20Appeal%20of%20A%20String/README.md
rating: 2033
source: 第 291 场周赛 Q4
tags:
    - 哈希表
    - 字符串
    - 动态规划
---

# [2262. 字符串的总引力](https://leetcode.cn/problems/total-appeal-of-a-string)

[English Version](/solution/2200-2299/2262.Total%20Appeal%20of%20A%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>字符串的 <strong>引力</strong> 定义为：字符串中 <strong>不同</strong> 字符的数量。</p>

<ul>
	<li>例如，<code>"abbca"</code> 的引力为 <code>3</code> ，因为其中有 <code>3</code> 个不同字符 <code>'a'</code>、<code>'b'</code> 和 <code>'c'</code> 。</li>
</ul>

<p>给你一个字符串 <code>s</code> ，返回 <strong>其所有子字符串的总引力</strong> <strong>。</strong></p>

<p><strong>子字符串</strong> 定义为：字符串中的一个连续字符序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "abbca"
<strong>输出：</strong>28
<strong>解释：</strong>"abbca" 的子字符串有：
- 长度为 1 的子字符串："a"、"b"、"b"、"c"、"a" 的引力分别为 1、1、1、1、1，总和为 5 。
- 长度为 2 的子字符串："ab"、"bb"、"bc"、"ca" 的引力分别为 2、1、2、2 ，总和为 7 。
- 长度为 3 的子字符串："abb"、"bbc"、"bca" 的引力分别为 2、2、3 ，总和为 7 。
- 长度为 4 的子字符串："abbc"、"bbca" 的引力分别为 3、3 ，总和为 6 。
- 长度为 5 的子字符串："abbca" 的引力为 3 ，总和为 3 。
引力总和为 5 + 7 + 7 + 6 + 3 = 28 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = "code"
<strong>输出：</strong>20
<strong>解释：</strong>"code" 的子字符串有：
- 长度为 1 的子字符串："c"、"o"、"d"、"e" 的引力分别为 1、1、1、1 ，总和为 4 。
- 长度为 2 的子字符串："co"、"od"、"de" 的引力分别为 2、2、2 ，总和为 6 。
- 长度为 3 的子字符串："cod"、"ode" 的引力分别为 3、3 ，总和为 6 。
- 长度为 4 的子字符串："code" 的引力为 4 ，总和为 4 。
引力总和为 4 + 6 + 6 + 4 = 20 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

## 解法

### 方法一：枚举

我们可以枚举以每个字符 $s[i]$ 结尾的字符串，计算其引力值之和 $t$，最后将所有 $t$ 相加即可。

考虑遍历到 $s[i]$ 时，即把 $s[i]$ 添加到以 $s[i-1]$ 结尾的子字符串的后面，其引力值之和 $t$ 的变化情况：

1. 如果 $s[i]$ 在之前没出现过，那么所有以 $s[i-1]$ 结尾的子字符串的引力值都会增加 $1$，共有 $i$ 个，所以 $t$ 增加 $i$，再加上 $s[i]$ 自身的引力值 $1$，所以 $t$ 一共增加 $i+1$；
1. 如果 $s[i]$ 在之前出现过，不妨记上次出现的的位置为 $j$，那么我们向子字符串 $s[0..i-1]$, $[1..i-1]$, $s[2..i-1]$, $\cdots$, $s[j..i-1]$ 后面添加 $s[i]$，这些子字符串的引力值不会发生变化，因为 $s[i]$ 已经在这些子字符串中出现过了；而子字符串 $s[j+1..i-1]$, $s[j+2..i-1]$, $\cdots$, $s[i-1]$ 的引力值都会增加 $1$，共有 $i-j-1$ 个，所以 $t$ 增加 $i-j-1$，再加上 $s[i]$ 自身的引力值 $1$，所以 $t$ 一共增加 $i-j$。

综上，我们可以用一个数组 $pos$ 记录每个字符上次出现的位置，初始时所有位置都为 $-1$，

接下来，我们遍历字符串，每一次我们更新以当前字符结尾的子字符串的引力值之和 $t = t + i - pos[c]$，其中 $c$ 是当前字符，累加 $t$ 到答案中。然后我们更新 $pos[c]$ 为当前位置 $i$。继续遍历直到字符串结束。

时间复杂度 $O(n)$，空间复杂度 $O(|\Sigma|)$，其中 $n$ 是字符串 $s$ 的长度；而 $|\Sigma|$ 是字符集的大小，本题中 $|\Sigma| = 26$。

<!-- tabs:start -->

```python
class Solution:
    def appealSum(self, s: str) -> int:
        ans = t = 0
        pos = [-1] * 26
        for i, c in enumerate(s):
            c = ord(c) - ord('a')
            t += i - pos[c]
            ans += t
            pos[c] = i
        return ans
```

```java
class Solution {
    public long appealSum(String s) {
        long ans = 0;
        long t = 0;
        int[] pos = new int[26];
        Arrays.fill(pos, -1);
        for (int i = 0; i < s.length(); ++i) {
            int c = s.charAt(i) - 'a';
            t += i - pos[c];
            ans += t;
            pos[c] = i;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long appealSum(string s) {
        long long ans = 0, t = 0;
        vector<int> pos(26, -1);
        for (int i = 0; i < s.size(); ++i) {
            int c = s[i] - 'a';
            t += i - pos[c];
            ans += t;
            pos[c] = i;
        }
        return ans;
    }
};
```

```go
func appealSum(s string) int64 {
	var ans, t int64
	pos := make([]int, 26)
	for i := range pos {
		pos[i] = -1
	}
	for i, c := range s {
		c -= 'a'
		t += int64(i - pos[c])
		ans += t
		pos[c] = i
	}
	return ans
}
```

```ts
function appealSum(s: string): number {
    const pos: number[] = Array(26).fill(-1);
    const n = s.length;
    let ans = 0;
    let t = 0;
    for (let i = 0; i < n; ++i) {
        const c = s.charCodeAt(i) - 97;
        t += i - pos[c];
        ans += t;
        pos[c] = i;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
