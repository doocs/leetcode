---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0186.Reverse%20Words%20in%20a%20String%20II/README.md
tags:
    - 双指针
    - 字符串
---

<!-- problem:start -->

# [186. 反转字符串中的单词 II 🔒](https://leetcode.cn/problems/reverse-words-in-a-string-ii)

[English Version](/solution/0100-0199/0186.Reverse%20Words%20in%20a%20String%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符数组 <code>s</code> ，反转其中 <strong>单词</strong> 的顺序。</p>

<p><strong>单词</strong> 的定义为：单词是一个由非空格字符组成的序列。<code>s</code> 中的单词将会由单个空格分隔。</p>

<div class="original__bRMd">
<div>
<p>必须设计并实现 <strong>原地</strong> 解法来解决此问题，即不分配额外的空间。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
<strong>输出：</strong>["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = ["a"]
<strong>输出：</strong>["a"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 可以是一个英文字母（大写或小写）、数字、或是空格 <code>' '</code> 。</li>
	<li><code>s</code> 中至少存在一个单词</li>
	<li><code>s</code> 不含前导或尾随空格</li>
	<li>题目数据保证：<code>s</code> 中的每个单词都由单个空格分隔</li>
</ul>
</div>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们可以遍历字符数组 $s$，利用双指针 $i$ 和 $j$ 找到每个单词的起始位置和结束位置，然后反转每个单词，最后再反转整个字符数组。

时间复杂度 $O(n)$，其中 $n$ 为字符数组 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def reverseWords(self, s: List[str]) -> None:
        def reverse(i: int, j: int):
            while i < j:
                s[i], s[j] = s[j], s[i]
                i, j = i + 1, j - 1

        i, n = 0, len(s)
        for j, c in enumerate(s):
            if c == " ":
                reverse(i, j - 1)
                i = j + 1
            elif j == n - 1:
                reverse(i, j)
        reverse(0, n - 1)
```

```java
class Solution {
    public void reverseWords(char[] s) {
        int n = s.length;
        for (int i = 0, j = 0; j < n; ++j) {
            if (s[j] == ' ') {
                reverse(s, i, j - 1);
                i = j + 1;
            } else if (j == n - 1) {
                reverse(s, i, j);
            }
        }
        reverse(s, 0, n - 1);
    }

    private void reverse(char[] s, int i, int j) {
        for (; i < j; ++i, --j) {
            char t = s[i];
            s[i] = s[j];
            s[j] = t;
        }
    }
}
```

```cpp
class Solution {
public:
    void reverseWords(vector<char>& s) {
        auto reverse = [&](int i, int j) {
            for (; i < j; ++i, --j) {
                swap(s[i], s[j]);
            }
        };
        int n = s.size();
        for (int i = 0, j = 0; j < n; ++j) {
            if (s[j] == ' ') {
                reverse(i, j - 1);
                i = j + 1;
            } else if (j == n - 1) {
                reverse(i, j);
            }
        }
        reverse(0, n - 1);
    }
};
```

```go
func reverseWords(s []byte) {
	reverse := func(i, j int) {
		for ; i < j; i, j = i+1, j-1 {
			s[i], s[j] = s[j], s[i]
		}
	}
	i, n := 0, len(s)
	for j, c := range s {
		if c == ' ' {
			reverse(i, j-1)
			i = j + 1
		} else if j == n-1 {
			reverse(i, j)
		}
	}
	reverse(0, n-1)
}
```

```ts
/**
 Do not return anything, modify s in-place instead.
 */
function reverseWords(s: string[]): void {
    const n = s.length;
    const reverse = (i: number, j: number): void => {
        for (; i < j; ++i, --j) {
            [s[i], s[j]] = [s[j], s[i]];
        }
    };
    for (let i = 0, j = 0; j <= n; ++j) {
        if (s[j] === ' ') {
            reverse(i, j - 1);
            i = j + 1;
        } else if (j === n - 1) {
            reverse(i, j);
        }
    }
    reverse(0, n - 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
