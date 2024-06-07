---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2914.Minimum%20Number%20of%20Changes%20to%20Make%20Binary%20String%20Beautiful/README.md
rating: 1479
source: 第 116 场双周赛 Q2
tags:
    - 字符串
---

<!-- problem:start -->

# [2914. 使二进制字符串变美丽的最少修改次数](https://leetcode.cn/problems/minimum-number-of-changes-to-make-binary-string-beautiful)

[English Version](/solution/2900-2999/2914.Minimum%20Number%20of%20Changes%20to%20Make%20Binary%20String%20Beautiful/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为偶数下标从 <strong>0</strong>&nbsp;开始的二进制字符串&nbsp;<code>s</code>&nbsp;。</p>

<p>如果可以将一个字符串分割成一个或者更多满足以下条件的子字符串，那么我们称这个字符串是 <strong>美丽的</strong>&nbsp;：</p>

<ul>
	<li>每个子字符串的长度都是 <strong>偶数</strong>&nbsp;。</li>
	<li>每个子字符串都 <strong>只</strong>&nbsp;包含 <code>1</code>&nbsp;或 <strong>只</strong>&nbsp;包含 <code>0</code>&nbsp;。</li>
</ul>

<p>你可以将 <code>s</code>&nbsp;中任一字符改成&nbsp;<code>0</code>&nbsp;或者&nbsp;<code>1</code>&nbsp;。</p>

<p>请你返回让字符串 <code>s</code>&nbsp;美丽的<strong>&nbsp;最少</strong>&nbsp;字符修改次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>s = "1001"
<b>输出：</b>2
<b>解释：</b>我们将 s[1] 改为 1 ，且将 s[3] 改为 0 ，得到字符串 "1100" 。
字符串 "1100" 是美丽的，因为我们可以将它分割成 "11|00" 。
将字符串变美丽最少需要 2 次修改。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>s = "10"
<b>输出：</b>1
<b>解释：</b>我们将 s[1] 改为 1 ，得到字符串 "11" 。
字符串 "11" 是美丽的，因为它已经是美丽的。
将字符串变美丽最少需要 1 次修改。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>s = "0000"
<b>输出：</b>0
<b>解释：</b>不需要进行任何修改，字符串 "0000" 已经是美丽字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;的长度为偶数。</li>
	<li><code>s[i]</code>&nbsp;要么是&nbsp;<code>'0'</code>&nbsp;，要么是&nbsp;<code>'1'</code> 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们只需要遍历字符串 $s$ 的所有奇数下标 $1, 3, 5, \cdots$，如果当前奇数下标与前一个下标的字符不同，即 $s[i] \ne s[i - 1]$，那么就需要修改当前字符，使得 $s[i] = s[i - 1]$。因此，此时答案需要加 $1$。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minChanges(self, s: str) -> int:
        return sum(s[i] != s[i - 1] for i in range(1, len(s), 2))
```

#### Java

```java
class Solution {
    public int minChanges(String s) {
        int ans = 0;
        for (int i = 1; i < s.length(); i += 2) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minChanges(string s) {
        int ans = 0;
        int n = s.size();
        for (int i = 1; i < n; i += 2) {
            ans += s[i] != s[i - 1];
        }
        return ans;
    }
};
```

#### Go

```go
func minChanges(s string) (ans int) {
	for i := 1; i < len(s); i += 2 {
		if s[i] != s[i-1] {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function minChanges(s: string): number {
    let ans = 0;
    for (let i = 1; i < s.length; i += 2) {
        if (s[i] !== s[i - 1]) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
