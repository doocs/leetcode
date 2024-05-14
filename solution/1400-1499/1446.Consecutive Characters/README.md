---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1446.Consecutive%20Characters/README.md
rating: 1165
tags:
    - 字符串
---

# [1446. 连续字符](https://leetcode.cn/problems/consecutive-characters)

[English Version](/solution/1400-1499/1446.Consecutive%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，字符串的<strong>「能量」</strong>定义为：只包含一种字符的最长非空子字符串的长度。</p>

<p>请你返回字符串 <code>s</code> 的 <strong>能量</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "leetcode"
<strong>输出：</strong>2
<strong>解释：</strong>子字符串 "ee" 长度为 2 ，只包含字符 'e' 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abbcccddddeeeeedcba"
<strong>输出：</strong>5
<strong>解释：</strong>子字符串 "eeeee" 长度为 5 ，只包含字符 'e' 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

### 方法一：遍历计数

我们定义一个变量 $t$，表示当前连续字符的长度，初始时 $t=1$。

接下来，我们从字符串 $s$ 的第二个字符开始遍历，如果当前字符与上一个字符相同，那么 $t = t + 1$，然后更新答案 $ans = \max(ans, t)$；否则，$t = 1$。

最后返回答案 $ans$ 即可。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def maxPower(self, s: str) -> int:
        ans = t = 1
        for a, b in pairwise(s):
            if a == b:
                t += 1
                ans = max(ans, t)
            else:
                t = 1
        return ans
```

```java
class Solution {
    public int maxPower(String s) {
        int ans = 1, t = 1;
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                ans = Math.max(ans, ++t);
            } else {
                t = 1;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxPower(string s) {
        int ans = 1, t = 1;
        for (int i = 1; i < s.size(); ++i) {
            if (s[i] == s[i - 1]) {
                ans = max(ans, ++t);
            } else {
                t = 1;
            }
        }
        return ans;
    }
};
```

```go
func maxPower(s string) int {
	ans, t := 1, 1
	for i := 1; i < len(s); i++ {
		if s[i] == s[i-1] {
			t++
			ans = max(ans, t)
		} else {
			t = 1
		}
	}
	return ans
}
```

```ts
function maxPower(s: string): number {
    let ans = 1;
    let t = 1;
    for (let i = 1; i < s.length; ++i) {
        if (s[i] === s[i - 1]) {
            ans = Math.max(ans, ++t);
        } else {
            t = 1;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
