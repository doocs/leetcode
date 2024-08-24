---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3146.Permutation%20Difference%20between%20Two%20Strings/README.md
rating: 1152
source: 第 397 场周赛 Q1
tags:
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [3146. 两个字符串的排列差](https://leetcode.cn/problems/permutation-difference-between-two-strings)

[English Version](/solution/3100-3199/3146.Permutation%20Difference%20between%20Two%20Strings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个字符串 <code>s</code> 和 <code>t</code>，每个字符串中的字符都不重复，且 <code>t</code> 是 <code>s</code> 的一个排列。</p>

<p><strong>排列差</strong> 定义为 <code>s</code> 和 <code>t</code> 中每个字符在两个字符串中位置的绝对差值之和。</p>

<p>返回 <code>s</code> 和 <code>t</code> 之间的<strong> 排列差 </strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "abc", t = "bac"</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>对于 <code>s = "abc"</code> 和 <code>t = "bac"</code>，排列差是：</p>

<ul>
	<li><code>"a"</code> 在 <code>s</code> 中的位置与在 <code>t</code> 中的位置之差的绝对值。</li>
	<li><code>"b"</code> 在 <code>s</code> 中的位置与在 <code>t</code> 中的位置之差的绝对值。</li>
	<li><code>"c"</code> 在 <code>s</code> 中的位置与在 <code>t</code> 中的位置之差的绝对值。</li>
</ul>

<p>即，<code>s</code> 和 <code>t</code> 的排列差等于 <code>|0 - 1| + |2 - 2| + |1 - 0| = 2</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "abcde", t = "edbac"</span></p>

<p><strong>输出：</strong><span class="example-io">12</span></p>

<p><strong>解释：</strong> <code>s</code> 和 <code>t</code> 的排列差等于 <code>|0 - 3| + |1 - 2| + |2 - 4| + |3 - 1| + |4 - 0| = 12</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 26</code></li>
	<li>每个字符在 <code>s</code> 中最多出现一次。</li>
	<li><code>t</code> 是 <code>s</code> 的一个排列。</li>
	<li><code>s</code> 仅由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表或数组

我们可以使用哈希表或者一个长度为 $26$ 的数组 $\textit{d}$ 来存储字符串 $\textit{s}$ 中每个字符的位置。

然后遍历字符串 $\textit{t}$，计算每个字符在字符串 $\textit{t}$ 中的位置与在字符串 $\textit{s}$ 中的位置之差的绝对值之和即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $\textit{s}$ 的长度。空间复杂度 $O(|\Sigma|)$，其中 $\Sigma$ 为字符集，这里是小写英文字母，所以 $|\Sigma| \leq 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findPermutationDifference(self, s: str, t: str) -> int:
        d = {c: i for i, c in enumerate(s)}
        return sum(abs(d[c] - i) for i, c in enumerate(t))
```

#### Java

```java
class Solution {
    public int findPermutationDifference(String s, String t) {
        int[] d = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            d[s.charAt(i) - 'a'] = i;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.abs(d[t.charAt(i) - 'a'] - i);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findPermutationDifference(string s, string t) {
        int d[26]{};
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            d[s[i] - 'a'] = i;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += abs(d[t[i] - 'a'] - i);
        }
        return ans;
    }
};
```

#### Go

```go
func findPermutationDifference(s string, t string) (ans int) {
	d := [26]int{}
	for i, c := range s {
		d[c-'a'] = i
	}
	for i, c := range t {
		ans += max(d[c-'a']-i, i-d[c-'a'])
	}
	return
}
```

#### TypeScript

```ts
function findPermutationDifference(s: string, t: string): number {
    const d: number[] = Array(26).fill(0);
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        d[s.charCodeAt(i) - 97] = i;
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans += Math.abs(d[t.charCodeAt(i) - 97] - i);
    }
    return ans;
}
```

#### C#

```cs
public class Solution {
    public int FindPermutationDifference(string s, string t) {
        int[] d = new int[26];
        int n = s.Length;
        for (int i = 0; i < n; ++i) {
            d[s[i] - 'a'] = i;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.Abs(d[t[i] - 'a'] - i);
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
