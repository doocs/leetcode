# [1592. 重新排列单词间的空格](https://leetcode.cn/problems/rearrange-spaces-between-words)

[English Version](/solution/1500-1599/1592.Rearrange%20Spaces%20Between%20Words/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>text</code> ，该字符串由若干被空格包围的单词组成。每个单词由一个或者多个小写英文字母组成，并且两个单词之间至少存在一个空格。题目测试用例保证 <code>text</code> <strong>至少包含一个单词</strong> 。</p>

<p>请你重新排列空格，使每对相邻单词之间的空格数目都 <strong>相等</strong> ，并尽可能 <strong>最大化</strong> 该数目。如果不能重新平均分配所有空格，请 <strong>将多余的空格放置在字符串末尾</strong> ，这也意味着返回的字符串应当与原 <code>text</code> 字符串的长度相等。</p>

<p>返回 <strong>重新排列空格后的字符串</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>text = &quot;  this   is  a sentence &quot;
<strong>输出：</strong>&quot;this   is   a   sentence&quot;
<strong>解释：</strong>总共有 9 个空格和 4 个单词。可以将 9 个空格平均分配到相邻单词之间，相邻单词间空格数为：9 / (4-1) = 3 个。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>text = &quot; practice   makes   perfect&quot;
<strong>输出：</strong>&quot;practice   makes   perfect &quot;
<strong>解释：</strong>总共有 7 个空格和 3 个单词。7 / (3-1) = 3 个空格加上 1 个多余的空格。多余的空格需要放在字符串的末尾。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>text = &quot;hello   world&quot;
<strong>输出：</strong>&quot;hello   world&quot;
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>text = &quot;  walks  udp package   into  bar a&quot;
<strong>输出：</strong>&quot;walks  udp  package  into  bar  a &quot;
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>text = &quot;a&quot;
<strong>输出：</strong>&quot;a&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 100</code></li>
	<li><code>text</code> 由小写英文字母和 <code>&#39; &#39;</code> 组成</li>
	<li><code>text</code> 中至少包含一个单词</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：字符串模拟**

统计字符串 `text` 中的空格数，记为 `cnt`。将 `text` 按空格分割成字符串数组 `words`。然后计算相邻字符串之间需要拼接的空格数，进行拼接。最后将剩余的空格拼接在末尾。

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 表示字符串 `text` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def reorderSpaces(self, text: str) -> str:
        cnt = text.count(' ')
        words = text.split()
        m = len(words) - 1
        if m == 0:
            return words[0] + ' ' * cnt
        return (' ' * (cnt // m)).join(words) + ' ' * (cnt % m)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String reorderSpaces(String text) {
        int cnt = 0;
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                ++cnt;
            }
        }
        String[] words = text.split("\\s+");
        List<String> res = new ArrayList<>();
        for (String w : words) {
            if (!"".equals(w)) {
                res.add(w);
            }
        }
        int m = res.size() - 1;
        if (m == 0) {
            return res.get(0) + " ".repeat(cnt);
        }
        String ans = String.join(" ".repeat(cnt / m), res);
        ans += " ".repeat(cnt % m);
        return ans;
    }
}
```

### **Go**

```go
func reorderSpaces(text string) string {
	cnt := strings.Count(text, " ")
	words := strings.Fields(text)
	m := len(words) - 1
	if m == 0 {
		return words[0] + strings.Repeat(" ", cnt)
	}
	return strings.Join(words, strings.Repeat(" ", cnt/m)) + strings.Repeat(" ", cnt%m)
}
```

### **TypeScript**

```ts
function reorderSpaces(text: string): string {
    let count = 0;
    for (const c of text) {
        if (c === ' ') {
            count++;
        }
    }

    const words = text.trim().split(/\s+/g);
    const n = words.length;
    if (n === 1) {
        return words.join('') + ''.padStart(count);
    }

    const rest = count % (words.length - 1);
    const per = (count - rest) / (words.length - 1);
    return words.join(''.padStart(per)) + ''.padStart(rest);
}
```

### **Rust**

```rust
impl Solution {
    fn create_spaces(n: usize) -> String {
        let mut res = String::new();
        for _ in 0..n {
            res.push(' ');
        }
        res
    }

    pub fn reorder_spaces(text: String) -> String {
        let count = {
            let mut res = 0;
            for c in text.as_bytes() {
                if c == &b' ' {
                    res += 1;
                }
            }
            res
        };

        let works = text.split_whitespace().collect::<Vec<&str>>();
        let n = works.len();
        if n == 1 {
            return works[0].to_string() + &Self::create_spaces(count);
        }
        works.join(&Self::create_spaces((count / (n - 1)))) + &Self::create_spaces(count % (n - 1))
    }
}
```

### **...**

```

```

<!-- tabs:end -->
