---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1422.Maximum%20Score%20After%20Splitting%20a%20String/README.md
rating: 1237
source: 第 186 场周赛 Q1
tags:
    - 字符串
    - 前缀和
---

<!-- problem:start -->

# [1422. 分割字符串的最大得分](https://leetcode.cn/problems/maximum-score-after-splitting-a-string)

[English Version](/solution/1400-1499/1422.Maximum%20Score%20After%20Splitting%20a%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由若干 0 和 1 组成的字符串 <code>s</code> ，请你计算并返回将该字符串分割成两个 <strong>非空</strong> 子字符串（即&nbsp;<strong>左</strong> 子字符串和 <strong>右</strong> 子字符串）所能获得的最大得分。</p>

<p>「分割字符串的得分」为 <strong>左</strong> 子字符串中 <strong>0</strong> 的数量加上 <strong>右</strong> 子字符串中 <strong>1</strong> 的数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;011101&quot;
<strong>输出：</strong>5 
<strong>解释：</strong>
将字符串 s 划分为两个非空子字符串的可行方案有：
左子字符串 = &quot;0&quot; 且 右子字符串 = &quot;11101&quot;，得分 = 1 + 4 = 5 
左子字符串 = &quot;01&quot; 且 右子字符串 = &quot;1101&quot;，得分 = 1 + 3 = 4 
左子字符串 = &quot;011&quot; 且 右子字符串 = &quot;101&quot;，得分 = 1 + 2 = 3 
左子字符串 = &quot;0111&quot; 且 右子字符串 = &quot;01&quot;，得分 = 1 + 1 = 2 
左子字符串 = &quot;01110&quot; 且 右子字符串 = &quot;1&quot;，得分 = 2 + 1 = 3
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;00111&quot;
<strong>输出：</strong>5
<strong>解释：</strong>当 左子字符串 = &quot;00&quot; 且 右子字符串 = &quot;111&quot; 时，我们得到最大得分 = 2 + 3 = 5
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;1111&quot;
<strong>输出：</strong>3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 500</code></li>
	<li>字符串 <code>s</code> 仅由字符 <code>&#39;0&#39;</code> 和 <code>&#39;1&#39;</code> 组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们用两个变量 $l$ 和 $r$ 分别记录左子字符串中 $0$ 的数量和右子字符串中 $1$ 的数量。初始时 $l = 0$，而 $r$ 则等于字符串 $s$ 中 $1$ 的数量。

遍历字符串 $s$ 的前 $n - 1$ 个字符，对于每一个位置 $i$，如果 $s[i] = 0$，则 $l$ 自增 $1$，否则 $r$ 自减 $1$。然后我们更新答案为 $l + r$ 的最大值。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, s: str) -> int:
        l, r = 0, s.count("1")
        ans = 0
        for x in s[:-1]:
            l += int(x) ^ 1
            r -= int(x)
            ans = max(ans, l + r)
        return ans
```

#### Java

```java
class Solution {
    public int maxScore(String s) {
        int l = 0, r = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '1') {
                ++r;
            }
        }
        int ans = 0;
        for (int i = 0; i < n - 1; ++i) {
            l += (s.charAt(i) - '0') ^ 1;
            r -= s.charAt(i) - '0';
            ans = Math.max(ans, l + r);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxScore(string s) {
        int l = 0, r = count(s.begin(), s.end(), '1');
        int ans = 0;
        for (int i = 0; i < s.size() - 1; ++i) {
            l += (s[i] - '0') ^ 1;
            r -= s[i] - '0';
            ans = max(ans, l + r);
        }
        return ans;
    }
};
```

#### Go

```go
func maxScore(s string) (ans int) {
	l, r := 0, strings.Count(s, "1")
	for _, c := range s[:len(s)-1] {
		if c == '0' {
			l++
		} else {
			r--
		}
		ans = max(ans, l+r)
	}
	return
}
```

#### TypeScript

```ts
function maxScore(s: string): number {
    let [l, r] = [0, 0];
    for (const c of s) {
        r += c === '1' ? 1 : 0;
    }
    let ans = 0;
    for (let i = 0; i < s.length - 1; ++i) {
        if (s[i] === '0') {
            ++l;
        } else {
            --r;
        }
        ans = Math.max(ans, l + r);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_score(s: String) -> i32 {
        let mut l = 0;
        let mut r = s.bytes().filter(|&b| b == b'1').count() as i32;
        let mut ans = 0;
        let cs = s.as_bytes();
        for i in 0..s.len() - 1 {
            l += ((cs[i] - b'0') ^ 1) as i32;
            r -= (cs[i] - b'0') as i32;
            ans = ans.max(l + r);
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
