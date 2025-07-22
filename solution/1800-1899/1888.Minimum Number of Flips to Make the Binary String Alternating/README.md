---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1888.Minimum%20Number%20of%20Flips%20to%20Make%20the%20Binary%20String%20Alternating/README.md
rating: 2005
source: 第 244 场周赛 Q3
tags:
    - 字符串
    - 动态规划
    - 滑动窗口
---

<!-- problem:start -->

# [1888. 使二进制字符串字符交替的最少反转次数](https://leetcode.cn/problems/minimum-number-of-flips-to-make-the-binary-string-alternating)

[English Version](/solution/1800-1899/1888.Minimum%20Number%20of%20Flips%20to%20Make%20the%20Binary%20String%20Alternating/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二进制字符串 <code>s</code> 。你可以按任意顺序执行以下两种操作任意次：</p>

<ul>
	<li><strong>类型 1 ：删除</strong> 字符串 <code>s</code> 的第一个字符并将它 <strong>添加</strong> 到字符串结尾。</li>
	<li><strong>类型 2 ：选择 </strong>字符串 <code>s</code> 中任意一个字符并将该字符 <strong>反转 </strong>，也就是如果值为 <code>'0'</code> ，则反转得到 <code>'1'</code> ，反之亦然。</li>
</ul>

<p>请你返回使 <code>s</code> 变成 <strong>交替</strong> 字符串的前提下， <strong>类型 2 </strong>的 <strong>最少</strong> 操作次数 。</p>

<p>我们称一个字符串是 <strong>交替</strong> 的，需要满足任意相邻字符都不同。</p>

<ul>
	<li>比方说，字符串 <code>"010"</code> 和 <code>"1010"</code> 都是交替的，但是字符串 <code>"0100"</code> 不是。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "111000"
<b>输出：</b>2
<b>解释：</b>执行第一种操作两次，得到 s = "100011" 。
然后对第三个和第六个字符执行第二种操作，得到 s = "10<strong>1</strong>01<strong>0</strong>" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "010"
<b>输出：</b>0
<strong>解释：</strong>字符串已经是交替的。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>s = "1110"
<b>输出：</b>1
<b>解释：</b>对第二个字符执行第二种操作，得到 s = "1<strong>0</strong>10" 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 要么是 <code>'0'</code> ，要么是 <code>'1'</code> 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滑动窗口

我们注意到，操作 $1$ 的作用实际上是让字符串成为一个环，而操作 $2$ 是使得环中的一段长度为 $n$ 的子串变成交替二进制串。

因此，我们只需要枚举每个长度为 $n$ 的子串，计算将其变成交替二进制串的代价，取最小值即可。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minFlips(self, s: str) -> int:
        n = len(s)
        target = "01"
        cnt = sum(c != target[i & 1] for i, c in enumerate(s))
        ans = min(cnt, n - cnt)
        for i in range(n):
            cnt -= s[i] != target[i & 1]
            cnt += s[i] != target[(i + n) & 1]
            ans = min(ans, cnt, n - cnt)
        return ans
```

#### Java

```java
class Solution {
    public int minFlips(String s) {
        int n = s.length();
        String target = "01";
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) != target.charAt(i & 1)) {
                ++cnt;
            }
        }
        int ans = Math.min(cnt, n - cnt);
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) != target.charAt(i & 1)) {
                --cnt;
            }
            if (s.charAt(i) != target.charAt((i + n) & 1)) {
                ++cnt;
            }
            ans = Math.min(ans, Math.min(cnt, n - cnt));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minFlips(string s) {
        int n = s.size();
        string target = "01";
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (s[i] != target[i & 1]) {
                ++cnt;
            }
        }
        int ans = min(cnt, n - cnt);
        for (int i = 0; i < n; ++i) {
            if (s[i] != target[i & 1]) {
                --cnt;
            }
            if (s[i] != target[(i + n) & 1]) {
                ++cnt;
            }
            ans = min({ans, cnt, n - cnt});
        }
        return ans;
    }
};
```

#### Go

```go
func minFlips(s string) int {
	n := len(s)
	target := "01"
	cnt := 0
	for i := range s {
		if s[i] != target[i&1] {
			cnt++
		}
	}
	ans := min(cnt, n-cnt)
	for i := range s {
		if s[i] != target[i&1] {
			cnt--
		}
		if s[i] != target[(i+n)&1] {
			cnt++
		}
		ans = min(ans, min(cnt, n-cnt))
	}
	return ans
}
```

#### TypeScript

```ts
function minFlips(s: string): number {
    const n = s.length;
    const target = '01';
    let cnt = 0;
    for (let i = 0; i < n; ++i) {
        if (s[i] !== target[i & 1]) {
            ++cnt;
        }
    }
    let ans = Math.min(cnt, n - cnt);
    for (let i = 0; i < n; ++i) {
        if (s[i] !== target[i & 1]) {
            --cnt;
        }
        if (s[i] !== target[(i + n) & 1]) {
            ++cnt;
        }
        ans = Math.min(ans, cnt, n - cnt);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
