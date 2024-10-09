---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2405.Optimal%20Partition%20of%20String/README.md
rating: 1355
source: 第 310 场周赛 Q2
tags:
    - 贪心
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [2405. 子字符串的最优划分](https://leetcode.cn/problems/optimal-partition-of-string)

[English Version](/solution/2400-2499/2405.Optimal%20Partition%20of%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> ，请你将该字符串划分成一个或多个 <strong>子字符串</strong> ，并满足每个子字符串中的字符都是 <strong>唯一</strong> 的。也就是说，在单个子字符串中，字母的出现次数都不超过 <strong>一次</strong> 。</p>

<p>满足题目要求的情况下，返回 <strong>最少</strong> 需要划分多少个子字符串<em>。</em></p>

<p>注意，划分后，原字符串中的每个字符都应该恰好属于一个子字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abacaba"
<strong>输出：</strong>4
<strong>解释：</strong>
两种可行的划分方法分别是 ("a","ba","cab","a") 和 ("ab","a","ca","ba") 。
可以证明最少需要划分 4 个子字符串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "ssssss"
<strong>输出：</strong>6
<strong>解释：
</strong>只存在一种可行的划分方法 ("s","s","s","s","s","s") 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

根据题意，每个子字符串应该尽可能长，且包含的字符唯一，因此，我们只需要贪心地进行划分即可。

我们定义一个二进制整数 $\textit{mask}$ 来记录当前子字符串中出现的字符，其中 $\textit{mask}$ 的第 $i$ 位为 $1$ 表示第 $i$ 个字母已经出现过，为 $0$ 表示未出现过。另外，我们还需要一个变量 $\textit{ans}$ 来记录划分的子字符串个数，初始时 $\textit{ans} = 1$。

遍历字符串 $s$ 中的每个字符，对于每个字符 $c$，我们将其转换为 $0$ 到 $25$ 之间的整数 $x$，然后判断 $\textit{mask}$ 的第 $x$ 位是否为 $1$，如果为 $1$，说明当前字符 $c$ 与当前子字符串中的字符有重复，此时 $\textit{ans}$ 需要加 $1$，并将 $\textit{mask}$ 置为 $0$；否则，将 $\textit{mask}$ 的第 $x$ 位置为 $1$。然后，我们将 $\textit{mask}$ 更新为 $\textit{mask}$ 与 $2^x$ 的按位或结果。

最后，返回 $\textit{ans}$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def partitionString(self, s: str) -> int:
        ans, mask = 1, 0
        for x in map(lambda c: ord(c) - ord("a"), s):
            if mask >> x & 1:
                ans += 1
                mask = 0
            mask |= 1 << x
        return ans
```

#### Java

```java
class Solution {
    public int partitionString(String s) {
        int ans = 1, mask = 0;
        for (int i = 0; i < s.length(); ++i) {
            int x = s.charAt(i) - 'a';
            if ((mask >> x & 1) == 1) {
                ++ans;
                mask = 0;
            }
            mask |= 1 << x;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int partitionString(string s) {
        int ans = 1, mask = 0;
        for (char& c : s) {
            int x = c - 'a';
            if (mask >> x & 1) {
                ++ans;
                mask = 0;
            }
            mask |= 1 << x;
        }
        return ans;
    }
};
```

#### Go

```go
func partitionString(s string) int {
	ans, mask := 1, 0
	for _, c := range s {
		x := int(c - 'a')
		if mask>>x&1 == 1 {
			ans++
			mask = 0
		}
		mask |= 1 << x
	}
	return ans
}
```

#### TypeScript

```ts
function partitionString(s: string): number {
    let [ans, mask] = [1, 0];
    for (const c of s) {
        const x = c.charCodeAt(0) - 97;
        if ((mask >> x) & 1) {
            ++ans;
            mask = 0;
        }
        mask |= 1 << x;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn partition_string(s: String) -> i32 {
        let mut ans = 1;
        let mut mask = 0;
        for x in s.chars().map(|c| (c as u8 - b'a') as u32) {
            if mask >> x & 1 == 1 {
                ans += 1;
                mask = 0;
            }
            mask |= 1 << x;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
