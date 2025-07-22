---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3403.Find%20the%20Lexicographically%20Largest%20String%20From%20the%20Box%20I/README.md
rating: 1761
source: 第 430 场周赛 Q2
tags:
    - 双指针
    - 字符串
    - 枚举
---

<!-- problem:start -->

# [3403. 从盒子中找出字典序最大的字符串 I](https://leetcode.cn/problems/find-the-lexicographically-largest-string-from-the-box-i)

[English Version](/solution/3400-3499/3403.Find%20the%20Lexicographically%20Largest%20String%20From%20the%20Box%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>word</code> 和一个整数 <code>numFriends</code>。</p>

<p>Alice 正在为她的 <code>numFriends</code> 位朋友组织一个游戏。游戏分为多个回合，在每一回合中：</p>

<ul>
	<li><code>word</code> 被分割成 <code>numFriends</code> 个&nbsp;<strong>非空&nbsp;</strong>字符串，且该分割方式与之前的任意回合所采用的都 <strong>不完全相同&nbsp;</strong>。</li>
	<li>所有分割出的字符串都会被放入一个盒子中。</li>
</ul>

<p>在所有回合结束后，找出盒子中&nbsp;<span data-keyword="lexicographically-smaller-string">字典序最大的&nbsp;</span>字符串。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">word = "dbca", numFriends = 2</span></p>

<p><strong>输出:</strong> <span class="example-io">"dbc"</span></p>

<p><strong>解释:</strong>&nbsp;</p>

<p>所有可能的分割方式为：</p>

<ul>
	<li><code>"d"</code> 和 <code>"bca"</code>。</li>
	<li><code>"db"</code> 和 <code>"ca"</code>。</li>
	<li><code>"dbc"</code> 和 <code>"a"</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">word = "gggg", numFriends = 4</span></p>

<p><strong>输出:</strong> <span class="example-io">"g"</span></p>

<p><strong>解释:</strong>&nbsp;</p>

<p>唯一可能的分割方式为：<code>"g"</code>, <code>"g"</code>, <code>"g"</code>, 和 <code>"g"</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 5&nbsp;* 10<sup>3</sup></code></li>
	<li><code>word</code> 仅由小写英文字母组成。</li>
	<li><code>1 &lt;= numFriends &lt;= word.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举子串左端点

如果我们固定子字符串的左端点，那么子字符串越长，字典序越大。假设子字符串左端点为 $i$，剩余子字符串的最小长度为 $\text{numFriends} - 1$，那么子字符串的右端点可以取到 $\min(n, i + n - (\text{numFriends} - 1))$，其中 $n$ 为字符串的长度。注意我们说的是左开右闭。

我们枚举所有可能的左端点，取出对应的子字符串，比较字典序，最终得到字典序最大的子字符串。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为字符串的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def answerString(self, word: str, numFriends: int) -> str:
        if numFriends == 1:
            return word
        n = len(word)
        return max(word[i : i + n - (numFriends - 1)] for i in range(n))
```

#### Java

```java
class Solution {
    public String answerString(String word, int numFriends) {
        if (numFriends == 1) {
            return word;
        }
        int n = word.length();
        String ans = "";
        for (int i = 0; i < n; ++i) {
            String t = word.substring(i, Math.min(n, i + n - (numFriends - 1)));
            if (ans.compareTo(t) < 0) {
                ans = t;
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
    string answerString(string word, int numFriends) {
        if (numFriends == 1) {
            return word;
        }
        int n = word.length();
        string ans = "";
        for (int i = 0; i < n; ++i) {
            string t = word.substr(i, min(n - i, n - (numFriends - 1)));
            if (ans < t) {
                ans = t;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func answerString(word string, numFriends int) (ans string) {
	if numFriends == 1 {
		return word
	}
	n := len(word)
	for i := 0; i < n; i++ {
		t := word[i:min(n, i+n-(numFriends-1))]
		ans = max(ans, t)
	}
	return
}
```

#### TypeScript

```ts
function answerString(word: string, numFriends: number): string {
    if (numFriends === 1) {
        return word;
    }
    const n = word.length;
    let ans = '';
    for (let i = 0; i < n; i++) {
        const t = word.slice(i, Math.min(n, i + n - (numFriends - 1)));
        ans = t > ans ? t : ans;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
