---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3223.Minimum%20Length%20of%20String%20After%20Operations/README.md
tags:
    - 哈希表
    - 字符串
    - 计数
---

<!-- problem:start -->

# [3223. 操作后字符串的最短长度](https://leetcode.cn/problems/minimum-length-of-string-after-operations)

[English Version](/solution/3200-3299/3223.Minimum%20Length%20of%20String%20After%20Operations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;。</p>

<p>你需要对 <code>s</code>&nbsp;执行以下操作 <strong>任意</strong>&nbsp;次：</p>

<ul>
	<li>选择一个下标 <code>i</code>&nbsp;，满足 <code>s[i]</code>&nbsp;左边和右边都&nbsp;<strong>至少</strong>&nbsp;有一个字符与它相同。</li>
	<li>删除 <code>s[i]</code>&nbsp;<strong>左边</strong>&nbsp;离它 <strong>最近</strong>&nbsp;且相同的字符。</li>
	<li>删除 <code>s[i]</code>&nbsp;<strong>右边</strong>&nbsp;离它 <strong>最近</strong>&nbsp;且相同的字符。</li>
</ul>

<p>请你返回执行完所有操作后， <code>s</code>&nbsp;的 <strong>最短</strong>&nbsp;长度。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "abaacbcbb"</span></p>

<p><span class="example-io"><b>输出：</b>5</span></p>

<p><strong>解释：</strong><br />
我们执行以下操作：</p>

<ul>
	<li>选择下标 2 ，然后删除下标 0 和 3 处的字符，得到&nbsp;<code>s = "bacbcbb"</code>&nbsp;。</li>
	<li>选择下标 3 ，然后删除下标 0 和 5 处的字符，得到&nbsp;<code>s = "acbcb"</code>&nbsp;。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "aa"</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong><br />
无法对字符串进行任何操作，所以返回初始字符串的长度。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们可以统计字符串中每个字符出现的次数，然后遍历计数数组，如果字符出现的次数为奇数，则最后该字符剩余 $1$ 个，如果字符出现的次数为偶数，则最后该字符剩余 $2$ 个。我们可以将所有字符的剩余个数相加，即为最终字符串的长度。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(|\Sigma|)$，其中 $|\Sigma|$ 为字符集的大小，本题中 $|\Sigma| = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumLength(self, s: str) -> int:
        cnt = Counter(s)
        return sum(1 if x & 1 else 2 for x in cnt.values())
```

#### Java

```java
class Solution {
    public int minimumLength(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        int ans = 0;
        for (int x : cnt) {
            if (x > 0) {
                ans += x % 2 == 1 ? 1 : 2;
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
    int minimumLength(string s) {
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        int ans = 0;
        for (int x : cnt) {
            if (x) {
                ans += x % 2 ? 1 : 2;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minimumLength(s string) (ans int) {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	for _, x := range cnt {
		if x > 0 {
			if x&1 == 1 {
				ans += 1
			} else {
				ans += 2
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function minimumLength(s: string): number {
    const cnt = new Map<string, number>();
    for (const c of s) {
        cnt.set(c, (cnt.get(c) || 0) + 1);
    }
    let ans = 0;
    for (const x of cnt.values()) {
        ans += x & 1 ? 1 : 2;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
