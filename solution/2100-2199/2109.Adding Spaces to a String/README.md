---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2109.Adding%20Spaces%20to%20a%20String/README.md
rating: 1315
source: 第 272 场周赛 Q2
tags:
    - 数组
    - 双指针
    - 字符串
    - 模拟
---

<!-- problem:start -->

# [2109. 向字符串添加空格](https://leetcode.cn/problems/adding-spaces-to-a-string)

[English Version](/solution/2100-2199/2109.Adding%20Spaces%20to%20a%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串 <code>s</code> ，以及一个下标从 <strong>0</strong> 开始的整数数组 <code>spaces</code> 。</p>

<p>数组 <code>spaces</code> 描述原字符串中需要添加空格的下标。每个空格都应该插入到给定索引处的字符值 <strong>之前</strong> 。</p>

<ul>
	<li>例如，<code>s = "EnjoyYourCoffee"</code> 且 <code>spaces = [5, 9]</code> ，那么我们需要在 <code>'Y'</code> 和 <code>'C'</code> 之前添加空格，这两个字符分别位于下标 <code>5</code> 和下标 <code>9</code> 。因此，最终得到 <code>"Enjoy <em><strong>Y</strong></em>our <em><strong>C</strong></em>offee"</code> 。</li>
</ul>

<p>请你添加空格，并返回修改后的字符串<em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "LeetcodeHelpsMeLearn", spaces = [8,13,15]
<strong>输出：</strong>"Leetcode Helps Me Learn"
<strong>解释：</strong>
下标 8、13 和 15 对应 "Leetcode<em><strong>H</strong></em>elps<em><strong>M</strong></em>e<em><strong>L</strong></em>earn" 中加粗斜体字符。
接着在这些字符前添加空格。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "icodeinpython", spaces = [1,5,7,9]
<strong>输出：</strong>"i code in py thon"
<strong>解释：</strong>
下标 1、5、7 和 9 对应 "i<em><strong>c</strong></em>ode<em><strong>i</strong></em>n<em><strong>p</strong></em>y<em><strong>t</strong></em>hon" 中加粗斜体字符。
接着在这些字符前添加空格。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "spacing", spaces = [0,1,2,3,4,5,6]
<strong>输出：</strong>" s p a c i n g"
<strong>解释：</strong>
字符串的第一个字符前可以添加空格。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>s</code> 仅由大小写英文字母组成</li>
	<li><code>1 &lt;= spaces.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>0 &lt;= spaces[i] &lt;= s.length - 1</code></li>
	<li><code>spaces</code> 中的所有值 <strong>严格递增</strong></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们可以用双指针 $i$ 和 $j$ 分别指向字符串 $s$ 和数组 $\textit{spaces}$ 的头部，然后从头到尾遍历字符串 $s$，当 $i$ 等于 $\textit{spaces}[j]$ 时，我们往结果字符串中添加一个空格，然后 $j$ 自增 $1$。接下来，我们将 $s[i]$ 添加到结果字符串中，然后 $i$ 自增 $1$。继续这个过程，直到遍历完字符串 $s$。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是字符串 $s$ 和数组 $spaces$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def addSpaces(self, s: str, spaces: List[int]) -> str:
        ans = []
        j = 0
        for i, c in enumerate(s):
            if j < len(spaces) and i == spaces[j]:
                ans.append(' ')
                j += 1
            ans.append(c)
        return ''.join(ans)
```

#### Java

```java
class Solution {
    public String addSpaces(String s, int[] spaces) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0, j = 0; i < s.length(); ++i) {
            if (j < spaces.length && i == spaces[j]) {
                ans.append(' ');
                ++j;
            }
            ans.append(s.charAt(i));
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string addSpaces(string s, vector<int>& spaces) {
        string ans = "";
        for (int i = 0, j = 0; i < s.size(); ++i) {
            if (j < spaces.size() && i == spaces[j]) {
                ans += ' ';
                ++j;
            }
            ans += s[i];
        }
        return ans;
    }
};
```

#### Go

```go
func addSpaces(s string, spaces []int) string {
	var ans []byte
	for i, j := 0, 0; i < len(s); i++ {
		if j < len(spaces) && i == spaces[j] {
			ans = append(ans, ' ')
			j++
		}
		ans = append(ans, s[i])
	}
	return string(ans)
}
```

#### TypeScript

```ts
function addSpaces(s: string, spaces: number[]): string {
    const ans: string[] = [];
    for (let i = 0, j = 0; i < s.length; i++) {
        if (i === spaces[j]) {
            ans.push(' ');
            j++;
        }
        ans.push(s[i]);
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
