---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0522.Longest%20Uncommon%20Subsequence%20II/README.md
tags:
    - 数组
    - 哈希表
    - 双指针
    - 字符串
    - 排序
---

<!-- problem:start -->

# [522. 最长特殊序列 II](https://leetcode.cn/problems/longest-uncommon-subsequence-ii)

[English Version](/solution/0500-0599/0522.Longest%20Uncommon%20Subsequence%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定字符串列表&nbsp;<code>strs</code> ，返回其中 <strong>最长的特殊序列</strong>&nbsp;的长度。如果最长特殊序列不存在，返回 <code>-1</code> 。</p>

<p><strong>特殊序列</strong> 定义如下：该序列为某字符串 <strong>独有的子序列（即不能是其他字符串的子序列）</strong>。</p>

<p>&nbsp;<code>s</code>&nbsp;的&nbsp;<strong>子序列</strong>可以通过删去字符串&nbsp;<code>s</code>&nbsp;中的某些字符实现。</p>

<ul>
	<li>例如，<code>"abc"</code>&nbsp;是 <code>"aebdc"</code>&nbsp;的子序列，因为您可以删除<code>"a<u>e</u>b<u>d</u>c"</code>中的下划线字符来得到 <code>"abc"</code>&nbsp;。<code>"aebdc"</code>的子序列还包括<code>"aebdc"</code>、 <code>"aeb"</code>&nbsp;和 <font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size: 12.6px; background-color: rgb(249, 242, 244);">""</span></font>&nbsp;(空字符串)。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> strs = ["aba","cdc","eae"]
<strong>输出:</strong> 3
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> strs = ["aaa","aaa","aa"]
<strong>输出:</strong> -1
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>2 &lt;= strs.length &lt;= 50</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 10</code></li>
	<li><code>strs[i]</code>&nbsp;只包含小写英文字母</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：判断子序列

我们定义一个函数 $check(s, t)$，用于判断字符串 $s$ 是否是字符串 $t$ 的子序列。我们可以使用双指针的方法，初始化两个指针 $i$ 和 $j$ 分别指向字符串 $s$ 和字符串 $t$ 的开头，然后不断移动指针 $j$，如果 $s[i]$ 和 $t[j]$ 相等，则移动指针 $i$，最后判断 $i$ 是否等于 $s$ 的长度即可。若 $i$ 等于 $s$ 的长度，则说明 $s$ 是 $t$ 的子序列。

判断字符串 $s$ 是否独有，只需要取字符串 $s$ 本身，与字符串列表的其他字符串比较即可。如果存在 $s$ 是其他字符串的子序列，则 $s$ 不是独有的。否则，字符串 $s$ 是独有的。我们取所有独有字符串中长度最长的字符串即可。

时间复杂度 $O(n^2 \times m)$，其中 $n$ 是字符串列表的长度，而 $m$ 是字符串的平均长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findLUSlength(self, strs: List[str]) -> int:
        def check(s: str, t: str):
            i = j = 0
            while i < len(s) and j < len(t):
                if s[i] == t[j]:
                    i += 1
                j += 1
            return i == len(s)

        ans = -1
        for i, s in enumerate(strs):
            for j, t in enumerate(strs):
                if i != j and check(s, t):
                    break
            else:
                ans = max(ans, len(s))
        return ans
```

#### Java

```java
class Solution {
    public int findLUSlength(String[] strs) {
        int ans = -1;
        int n = strs.length;
        for (int i = 0, j; i < n; ++i) {
            int x = strs[i].length();
            for (j = 0; j < n; ++j) {
                if (i != j && check(strs[i], strs[j])) {
                    x = -1;
                    break;
                }
            }
            ans = Math.max(ans, x);
        }
        return ans;
    }

    private boolean check(String s, String t) {
        int m = s.length(), n = t.length();
        int i = 0;
        for (int j = 0; i < m && j < n; ++j) {
            if (s.charAt(i) == t.charAt(j)) {
                ++i;
            }
        }
        return i == m;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findLUSlength(vector<string>& strs) {
        int ans = -1;
        int n = strs.size();
        auto check = [&](const string& s, const string& t) {
            int m = s.size(), n = t.size();
            int i = 0;
            for (int j = 0; i < m && j < n; ++j) {
                if (s[i] == t[j]) {
                    ++i;
                }
            }
            return i == m;
        };
        for (int i = 0, j; i < n; ++i) {
            int x = strs[i].size();
            for (j = 0; j < n; ++j) {
                if (i != j && check(strs[i], strs[j])) {
                    x = -1;
                    break;
                }
            }
            ans = max(ans, x);
        }
        return ans;
    }
};
```

#### Go

```go
func findLUSlength(strs []string) int {
	ans := -1
	check := func(s, t string) bool {
		m, n := len(s), len(t)
		i := 0
		for j := 0; i < m && j < n; j++ {
			if s[i] == t[j] {
				i++
			}
		}
		return i == m
	}
	for i, s := range strs {
		x := len(s)
		for j, t := range strs {
			if i != j && check(s, t) {
				x = -1
				break
			}
		}
		ans = max(ans, x)
	}
	return ans
}
```

#### TypeScript

```ts
function findLUSlength(strs: string[]): number {
    const n = strs.length;
    let ans = -1;
    const check = (s: string, t: string): boolean => {
        const [m, n] = [s.length, t.length];
        let i = 0;
        for (let j = 0; i < m && j < n; ++j) {
            if (s[i] === t[j]) {
                ++i;
            }
        }
        return i === m;
    };
    for (let i = 0; i < n; ++i) {
        let x = strs[i].length;
        for (let j = 0; j < n; ++j) {
            if (i !== j && check(strs[i], strs[j])) {
                x = -1;
                break;
            }
        }
        ans = Math.max(ans, x);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
