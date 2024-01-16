# [1871. 跳跃游戏 VII](https://leetcode.cn/problems/jump-game-vii)

[English Version](/solution/1800-1899/1871.Jump%20Game%20VII/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0 </strong>开始的二进制字符串 <code>s</code> 和两个整数 <code>minJump</code> 和 <code>maxJump</code> 。一开始，你在下标 <code>0</code> 处，且该位置的值一定为 <code>'0'</code> 。当同时满足如下条件时，你可以从下标 <code>i</code> 移动到下标 <code>j</code> 处：</p>

<ul>
	<li><code>i + minJump <= j <= min(i + maxJump, s.length - 1)</code> 且</li>
	<li><code>s[j] == '0'</code>.</li>
</ul>

<p>如果你可以到达 <code>s</code> 的下标<i> </i><code>s.length - 1</code> 处，请你返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "<strong>0</strong>11<strong>0</strong>1<strong>0</strong>", minJump = 2, maxJump = 3
<b>输出：</b>true
<strong>解释：</strong>
第一步，从下标 0 移动到下标 3 。
第二步，从下标 3 移动到下标 5 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "01101110", minJump = 2, maxJump = 3
<b>输出：</b>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= s.length <= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 要么是 <code>'0'</code> ，要么是 <code>'1'</code></li>
	<li><code>s[0] == '0'</code></li>
	<li><code>1 <= minJump <= maxJump < s.length</code></li>
</ul>

## 解法

### 方法一：前缀和 + 动态规划

我们定义一个长度为 $n+1$ 的前缀和数组 $pre$，其中 $pre[i]$ 表示 $s$ 的前 $i$ 个位置中能够到达的个数。定义一个长度为 $n$ 的布尔数组 $f$，其中 $f[i]$ 表示 $s[i]$ 是否能够到达。初始时 $pre[1] = 1$，而 $f[0] = true$。

考虑 $i \in [1, n)$，如果 $s[i] = 0$，那么我们需要判断 $s$ 的前 $i$ 个位置中是否存在一个位置 $j$，满足 $j$ 能够到达且 $j$ 到 $i$ 的距离在 $[minJump, maxJump]$ 之间。如果存在这样的位置 $j$，那么我们就有 $f[i] = true$，否则 $f[i] = false$。在判断 $j$ 是否存在时，我们可以通过前缀和数组 $pre$ 在 $O(1)$ 的时间内判断是否存在这样的位置 $j$。

最终答案即为 $f[n-1]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def canReach(self, s: str, minJump: int, maxJump: int) -> bool:
        n = len(s)
        pre = [0] * (n + 1)
        pre[1] = 1
        f = [True] + [False] * (n - 1)
        for i in range(1, n):
            if s[i] == "0":
                l, r = max(0, i - maxJump), i - minJump
                f[i] = l <= r and pre[r + 1] - pre[l] > 0
            pre[i + 1] = pre[i] + f[i]
        return f[-1]
```

```java
class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        int[] pre = new int[n + 1];
        pre[1] = 1;
        boolean[] f = new boolean[n];
        f[0] = true;
        for (int i = 1; i < n; ++i) {
            if (s.charAt(i) == '0') {
                int l = Math.max(0, i - maxJump);
                int r = i - minJump;
                f[i] = l <= r && pre[r + 1] - pre[l] > 0;
            }
            pre[i + 1] = pre[i] + (f[i] ? 1 : 0);
        }
        return f[n - 1];
    }
}
```

```cpp
class Solution {
public:
    bool canReach(string s, int minJump, int maxJump) {
        int n = s.size();
        int pre[n + 1];
        memset(pre, 0, sizeof(pre));
        pre[1] = 1;
        bool f[n];
        memset(f, 0, sizeof(f));
        f[0] = true;
        for (int i = 1; i < n; ++i) {
            if (s[i] == '0') {
                int l = max(0, i - maxJump);
                int r = i - minJump;
                f[i] = l <= r && pre[r + 1] - pre[l];
            }
            pre[i + 1] = pre[i] + f[i];
        }
        return f[n - 1];
    }
};
```

```go
func canReach(s string, minJump int, maxJump int) bool {
	n := len(s)
	pre := make([]int, n+1)
	pre[1] = 1
	f := make([]bool, n)
	f[0] = true
	for i := 1; i < n; i++ {
		if s[i] == '0' {
			l, r := max(0, i-maxJump), i-minJump
			f[i] = l <= r && pre[r+1]-pre[l] > 0
		}
		pre[i+1] = pre[i]
		if f[i] {
			pre[i+1]++
		}
	}
	return f[n-1]
}
```

```ts
function canReach(s: string, minJump: number, maxJump: number): boolean {
    const n = s.length;
    const pre: number[] = Array(n + 1).fill(0);
    pre[1] = 1;
    const f: boolean[] = Array(n).fill(false);
    f[0] = true;
    for (let i = 1; i < n; ++i) {
        if (s[i] === '0') {
            const [l, r] = [Math.max(0, i - maxJump), i - minJump];
            f[i] = l <= r && pre[r + 1] - pre[l] > 0;
        }
        pre[i + 1] = pre[i] + (f[i] ? 1 : 0);
    }
    return f[n - 1];
}
```

```js
/**
 * @param {string} s
 * @param {number} minJump
 * @param {number} maxJump
 * @return {boolean}
 */
var canReach = function (s, minJump, maxJump) {
    const n = s.length;
    const pre = Array(n + 1).fill(0);
    pre[1] = 1;
    const f = Array(n).fill(false);
    f[0] = true;
    for (let i = 1; i < n; ++i) {
        if (s[i] === '0') {
            const [l, r] = [Math.max(0, i - maxJump), i - minJump];
            f[i] = l <= r && pre[r + 1] - pre[l] > 0;
        }
        pre[i + 1] = pre[i] + (f[i] ? 1 : 0);
    }
    return f[n - 1];
};
```

<!-- tabs:end -->

<!-- end -->
