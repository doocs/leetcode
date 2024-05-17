---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3110.Score%20of%20a%20String/README.md
rating: 1152
source: 第 128 场双周赛 Q1
tags:
    - 字符串
---

<!-- problem:start -->

# [3110. 字符串的分数](https://leetcode.cn/problems/score-of-a-string)

[English Version](/solution/3100-3199/3110.Score%20of%20a%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;。一个字符串的&nbsp;<strong>分数</strong>&nbsp;定义为相邻字符 <strong>ASCII</strong>&nbsp;码差值绝对值的和。</p>

<p>请你返回 <code>s</code>&nbsp;的 <strong>分数</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "hello"</span></p>

<p><span class="example-io"><b>输出：</b>13</span></p>

<p><strong>解释：</strong></p>

<p><code>s</code>&nbsp;中字符的 <strong>ASCII </strong>码分别为：<code>'h' = 104</code>&nbsp;，<code>'e' = 101</code>&nbsp;，<code>'l' = 108</code>&nbsp;，<code>'o' = 111</code>&nbsp;。所以&nbsp;<code>s</code>&nbsp;的分数为&nbsp;<code>|104 - 101| + |101 - 108| + |108 - 108| + |108 - 111| = 3 + 7 + 0 + 3 = 13</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "zaz"</span></p>

<p><span class="example-io"><b>输出：</b>50</span></p>

<p><strong>解释：</strong></p>

<p><code>s</code>&nbsp;中字符的 <strong>ASCII&nbsp;</strong>码分别为：<code>'z' = 122</code>&nbsp;，<code>'a' = 97</code>&nbsp;。所以&nbsp;<code>s</code>&nbsp;的分数为&nbsp;<code>|122 - 97| + |97 - 122| = 25 + 25 = 50</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们直接遍历字符串 $s$，计算相邻字符的 ASCII 码差值的绝对值之和即可。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def scoreOfString(self, s: str) -> int:
        return sum(abs(a - b) for a, b in pairwise(map(ord, s)))
```

```java
class Solution {
    public int scoreOfString(String s) {
        int ans = 0;
        for (int i = 1; i < s.length(); ++i) {
            ans += Math.abs(s.charAt(i - 1) - s.charAt(i));
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int scoreOfString(string s) {
        int ans = 0;
        for (int i = 1; i < s.size(); ++i) {
            ans += abs(s[i] - s[i - 1]);
        }
        return ans;
    }
};
```

```go
func scoreOfString(s string) (ans int) {
	for i := 1; i < len(s); i++ {
		ans += abs(int(s[i-1]) - int(s[i]))
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function scoreOfString(s: string): number {
    let ans = 0;
    for (let i = 1; i < s.length; ++i) {
        ans += Math.abs(s.charCodeAt(i) - s.charCodeAt(i - 1));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
