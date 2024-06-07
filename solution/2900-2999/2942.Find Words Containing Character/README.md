---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2942.Find%20Words%20Containing%20Character/README.md
rating: 1182
source: 第 118 场双周赛 Q1
tags:
    - 数组
    - 字符串
---

<!-- problem:start -->

# [2942. 查找包含给定字符的单词](https://leetcode.cn/problems/find-words-containing-character)

[English Version](/solution/2900-2999/2942.Find%20Words%20Containing%20Character/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串数组&nbsp;<code>words</code>&nbsp;和一个字符&nbsp;<code>x</code>&nbsp;。</p>

<p>请你返回一个 <strong>下标数组</strong>&nbsp;，表示下标在数组中对应的单词包含字符 <code>x</code>&nbsp;。</p>

<p><b>注意</b>&nbsp;，返回的数组可以是&nbsp;<strong>任意</strong>&nbsp;顺序。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>words = ["leet","code"], x = "e"
<b>输出：</b>[0,1]
<b>解释：</b>"e" 在两个单词中都出现了："l<em><strong>ee</strong></em>t" 和 "cod<em><strong>e</strong></em>" 。所以我们返回下标 0 和 1 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>words = ["abc","bcd","aaaa","cbc"], x = "a"
<b>输出：</b>[0,2]
<b>解释：</b>"a" 在 "<em><strong>a</strong></em>bc" 和 "<em><strong>aaaa</strong></em>" 中出现了，所以我们返回下标 0 和 2 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>words = ["abc","bcd","aaaa","cbc"], x = "z"
<b>输出：</b>[]
<b>解释：</b>"z" 没有在任何单词中出现。所以我们返回空数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 50</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 50</code></li>
	<li><code>x</code>&nbsp;是一个小写英文字母。</li>
	<li><code>words[i]</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历

我们直接遍历字符串数组 $words$ 中的每一个字符串 $words[i]$，如果 $x$ 在 $words[i]$ 中出现，就将 $i$ 加入答案数组中。

遍历结束后，返回答案数组即可。

时间复杂度 $O(L)$，其中 $L$ 为字符串数组 $words$ 中所有字符串的长度和。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findWordsContaining(self, words: List[str], x: str) -> List[int]:
        return [i for i, w in enumerate(words) if x in w]
```

#### Java

```java
class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < words.length; ++i) {
            if (words[i].indexOf(x) != -1) {
                ans.add(i);
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
    vector<int> findWordsContaining(vector<string>& words, char x) {
        vector<int> ans;
        for (int i = 0; i < words.size(); ++i) {
            if (words[i].find(x) != string::npos) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findWordsContaining(words []string, x byte) (ans []int) {
	for i, w := range words {
		for _, c := range w {
			if byte(c) == x {
				ans = append(ans, i)
				break
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function findWordsContaining(words: string[], x: string): number[] {
    const ans: number[] = [];
    for (let i = 0; i < words.length; ++i) {
        if (words[i].includes(x)) {
            ans.push(i);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
