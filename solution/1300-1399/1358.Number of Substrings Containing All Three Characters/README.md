---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1358.Number%20of%20Substrings%20Containing%20All%20Three%20Characters/README.md
rating: 1646
source: 第 20 场双周赛 Q3
tags:
    - 哈希表
    - 字符串
    - 滑动窗口
---

<!-- problem:start -->

# [1358. 包含所有三种字符的子字符串数目](https://leetcode.cn/problems/number-of-substrings-containing-all-three-characters)

[English Version](/solution/1300-1399/1358.Number%20of%20Substrings%20Containing%20All%20Three%20Characters/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code>&nbsp;，它只包含三种字符 a, b 和 c 。</p>

<p>请你返回 a，b 和 c 都&nbsp;<strong>至少&nbsp;</strong>出现过一次的子字符串数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;abcabc&quot;
<strong>输出：</strong>10
<strong>解释：</strong>包含 a，b 和 c 各至少一次的子字符串为<em> &quot;</em>abc<em>&quot;, &quot;</em>abca<em>&quot;, &quot;</em>abcab<em>&quot;, &quot;</em>abcabc<em>&quot;, &quot;</em>bca<em>&quot;, &quot;</em>bcab<em>&quot;, &quot;</em>bcabc<em>&quot;, &quot;</em>cab<em>&quot;, &quot;</em>cabc<em>&quot; </em>和<em> &quot;</em>abc<em>&quot; </em>(<strong>相同</strong><strong>字符串算多次</strong>)<em>。</em>
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;aaacb&quot;
<strong>输出：</strong>3
<strong>解释：</strong>包含 a，b 和 c 各至少一次的子字符串为<em> &quot;</em>aaacb<em>&quot;, &quot;</em>aacb<em>&quot; </em>和<em> &quot;</em>acb<em>&quot; 。</em>
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;abc&quot;
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 5 x 10^4</code></li>
	<li><code>s</code>&nbsp;只包含字符 a，b 和 c 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

我们用一个长度为 $3$ 的数组 $d$ 记录三种字符最近一次出现的位置，初始时均为 $-1$。

遍历字符串 $s$，对于当前位置 $i$，我们先更新 $d[s[i]]=i$，然后合法的字符串个数为 $\min(d[0], d[1], d[2]) + 1$，累加到答案中。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def numberOfSubstrings(self, s: str) -> int:
        d = {"a": -1, "b": -1, "c": -1}
        ans = 0
        for i, c in enumerate(s):
            d[c] = i
            ans += min(d["a"], d["b"], d["c"]) + 1
        return ans
```

```java
class Solution {
    public int numberOfSubstrings(String s) {
        int[] d = new int[] {-1, -1, -1};
        int ans = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            d[c - 'a'] = i;
            ans += Math.min(d[0], Math.min(d[1], d[2])) + 1;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numberOfSubstrings(string s) {
        int d[3] = {-1, -1, -1};
        int ans = 0;
        for (int i = 0; i < s.size(); ++i) {
            d[s[i] - 'a'] = i;
            ans += min(d[0], min(d[1], d[2])) + 1;
        }
        return ans;
    }
};
```

```go
func numberOfSubstrings(s string) (ans int) {
	d := [3]int{-1, -1, -1}
	for i, c := range s {
		d[c-'a'] = i
		ans += min(d[0], min(d[1], d[2])) + 1
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
