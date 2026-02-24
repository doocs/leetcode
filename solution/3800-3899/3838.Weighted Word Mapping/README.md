---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3838.Weighted%20Word%20Mapping/README.md
rating: 1240
source: 第 176 场双周赛 Q1
tags:
    - 数组
    - 字符串
    - 模拟
---

<!-- problem:start -->

# [3838. 带权单词映射](https://leetcode.cn/problems/weighted-word-mapping)

[English Version](/solution/3800-3899/3838.Weighted%20Word%20Mapping/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串数组 <code>words</code>，其中每个字符串表示一个由小写英文字母组成的单词。</p>

<p>同时给你一个长度为 26 的整数数组 <code>weights</code>，其中 <code>weights[i]</code> 表示第 <code>i</code> 个小写英文字母的权重。</p>

<p>单词的 <strong>权重</strong> 定义为其所有字符权重的 <strong>总和</strong>。</p>

<p>对于每个单词，将其权重对 26 取模，并将结果按字母倒序映射到一个小写英文字母（<code>0 -&gt; 'z', 1 -&gt; 'y', ..., 25 -&gt; 'a'</code>）。</p>

<p>返回一个由所有单词映射后的字符按顺序连接而成的字符串。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">words = ["abcd","def","xyz"], weights = [5,3,12,14,1,2,3,2,10,6,6,9,7,8,7,10,8,9,6,9,9,8,3,7,7,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">"rij"</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>"abcd"</code> 的权重是 <code>5 + 3 + 12 + 14 = 34</code>。对 26 取模的结果是 <code>34 % 26 = 8</code>，映射为 <code>'r'</code>。</li>
	<li><code>"def"</code> 的权重是 <code>14 + 1 + 2 = 17</code>。对 26 取模的结果是 <code>17 % 26 = 17</code>，映射为 <code>'i'</code>。</li>
	<li><code>"xyz"</code> 的权重是 <code>7 + 7 + 2 = 16</code>。对&nbsp;26 取模的结果是 <code>16 % 26 = 16</code>，映射为 <code>'j'</code>。</li>
</ul>

<p>因此，连接映射字符后形成的字符串是 <code>"rij"</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">words = ["a","b","c"], weights = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">"yyy"</span></p>

<p><strong>解释：</strong></p>

<p>每个单词的权重均为 1。对 26 取模的结果是 <code>1 % 26 = 1</code>，映射为 <code>'y'</code>。</p>

<p>因此，连接映射字符后形成的字符串是 <code>"yyy"</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">words = ["abcd"], weights = [7,5,3,4,3,5,4,9,4,2,2,7,10,2,5,10,6,1,2,2,4,1,3,4,4,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">"g"</span></p>

<p><strong>解释：</strong></p>

<p><code>"abcd"</code> 的权重是 <code>7 + 5 + 3 + 4 = 19</code>。对 26 取模的结果是 <code>19 % 26 = 19</code>，映射为 <code>'g'</code>。</p>

<p>因此，连接映射字符后形成的字符串是 <code>"g"</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>weights.length == 26</code></li>
	<li><code>1 &lt;= weights[i] &lt;= 100</code></li>
	<li><code>words[i]</code> 仅由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们遍历 $\textit{words}$ 中的每个单词 $w$，计算其权重 $s$，即单词中每个字符的权重之和。然后计算 $s$ 对 26 取模的结果，并将结果映射到一个小写英文字母上，最后连接所有映射后的字符并返回。

时间复杂度 $O(L)$，其中 $L$ 是 $\textit{words}$ 中所有单词的长度之和。空间复杂度 $O(W)$，其中 $W$ 是 $\textit{words}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def mapWordWeights(self, words: List[str], weights: List[int]) -> str:
        ans = []
        for w in words:
            s = sum(weights[ord(c) - ord('a')] for c in w)
            ans.append(ascii_lowercase[25 - s % 26])
        return ''.join(ans)
```

#### Java

```java
class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        var ans = new StringBuilder();
        for (var w : words) {
            int s = 0;
            for (char c : w.toCharArray()) {
                s = (s + weights[c - 'a']) % 26;
            }
            ans.append((char) ('a' + (25 - s)));
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string mapWordWeights(vector<string>& words, vector<int>& weights) {
        string ans;
        for (const string& w : words) {
            int s = 0;
            for (char c : w) {
                s = (s + weights[c - 'a']) % 26;
            }
            ans.push_back(char('a' + (25 - s)));
        }
        return ans;
    }
};
```

#### Go

```go
func mapWordWeights(words []string, weights []int) string {
	ans := make([]byte, 0, len(words))
	for _, w := range words {
		s := 0
		for i := 0; i < len(w); i++ {
			s = (s + weights[int(w[i]-'a')]) % 26
		}
		ans = append(ans, byte('a'+(25-s)))
	}
	return string(ans)
}
```

#### TypeScript

```ts
function mapWordWeights(words: string[], weights: number[]): string {
    const ans: string[] = [];
    for (const w of words) {
        let s = 0;
        for (const c of w) {
            s = (s + weights[c.charCodeAt(0) - 97]) % 26;
        }
        ans.push(String.fromCharCode(97 + (25 - s)));
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
