---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2788.Split%20Strings%20by%20Separator/README.md
rating: 1239
source: 第 355 场周赛 Q1
tags:
    - 数组
    - 字符串
---

<!-- problem:start -->

# [2788. 按分隔符拆分字符串](https://leetcode.cn/problems/split-strings-by-separator)

[English Version](/solution/2700-2799/2788.Split%20Strings%20by%20Separator/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串数组 <code>words</code> 和一个字符 <code>separator</code> ，请你按 <code>separator</code> 拆分 <code>words</code> 中的每个字符串。</p>

<p>返回一个由拆分后的新字符串组成的字符串数组，<strong>不包括空字符串</strong> 。</p>

<p><strong>注意</strong></p>

<ul>
	<li><code>separator</code> 用于决定拆分发生的位置，但它不包含在结果字符串中。</li>
	<li>拆分可能形成两个以上的字符串。</li>
	<li>结果字符串必须保持初始相同的先后顺序。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["one.two.three","four.five","six"], separator = "."
<strong>输出：</strong>["one","two","three","four","five","six"]
<strong>解释：</strong>在本示例中，我们进行下述拆分：

"one.two.three" 拆分为 "one", "two", "three"
"four.five" 拆分为 "four", "five"
"six" 拆分为 "six" 

因此，结果数组为 ["one","two","three","four","five","six"] 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["$easy$","$problem$"], separator = "$"
<strong>输出：</strong>["easy","problem"]
<strong>解释：</strong>在本示例中，我们进行下述拆分：

"$easy$" 拆分为 "easy"（不包括空字符串）
"$problem$" 拆分为 "problem"（不包括空字符串）

因此，结果数组为 ["easy","problem"] 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>words = ["|||"], separator = "|"
<strong>输出：</strong>[]
<strong>解释：</strong>在本示例中，"|||" 的拆分结果将只包含一些空字符串，所以我们返回一个空数组 [] 。 </pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 20</code></li>
	<li><code>words[i]</code> 中的字符要么是小写英文字母，要么就是字符串 <code>".,|$#@"</code> 中的字符（不包括引号）</li>
	<li><code>separator</code> 是字符串 <code>".,|$#@"</code> 中的某个字符（不包括引号）</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们遍历字符串数组 $words$，对于每个字符串 $w$，我们使用 `separator` 作为分隔符进行拆分，如果拆分后的字符串不为空，则将其加入答案数组。

时间复杂度 $O(n \times m)$，空间复杂度 $O(m)$，其中 $n$ 是字符串数组 $words$ 的长度，而 $m$ 是字符串数组 $words$ 中字符串的最大长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def splitWordsBySeparator(self, words: List[str], separator: str) -> List[str]:
        return [s for w in words for s in w.split(separator) if s]
```

#### Java

```java
import java.util.regex.Pattern;

class Solution {
    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> ans = new ArrayList<>();
        for (var w : words) {
            for (var s : w.split(Pattern.quote(String.valueOf(separator)))) {
                if (s.length() > 0) {
                    ans.add(s);
                }
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
    vector<string> splitWordsBySeparator(vector<string>& words, char separator) {
        vector<string> ans;
        for (const auto& w : words) {
            istringstream ss(w);
            string s;
            while (getline(ss, s, separator)) {
                if (!s.empty()) {
                    ans.push_back(s);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func splitWordsBySeparator(words []string, separator byte) (ans []string) {
	for _, w := range words {
		for _, s := range strings.Split(w, string(separator)) {
			if s != "" {
				ans = append(ans, s)
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function splitWordsBySeparator(words: string[], separator: string): string[] {
    return words.flatMap(w => w.split(separator).filter(s => s.length > 0));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
