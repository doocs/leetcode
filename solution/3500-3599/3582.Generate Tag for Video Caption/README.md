---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3582.Generate%20Tag%20for%20Video%20Caption/README.md
---

<!-- problem:start -->

# [3582. 为视频标题生成标签](https://leetcode.cn/problems/generate-tag-for-video-caption)

[English Version](/solution/3500-3599/3582.Generate%20Tag%20for%20Video%20Caption/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code><font face="monospace">caption</font></code>，表示一个视频的标题。</p>

<p>需要按照以下步骤&nbsp;<strong>按顺序&nbsp;</strong>生成一个视频的&nbsp;<strong>有效标签&nbsp;</strong>：</p>

<ol>
	<li>
	<p>将 <strong>所有单词&nbsp;</strong>组合为单个&nbsp;<strong>驼峰命名字符串</strong> ，并在前面加上 <code>'#'</code>。<strong>驼峰命名字符串&nbsp;</strong>指的是除第一个单词外，其余单词的首字母大写，且每个单词的首字母之后的字符必须是小写。</p>
	</li>
	<li>
	<p><b>移除&nbsp;</b>所有不是英文字母的字符，但<strong> 保留&nbsp;</strong>第一个字符 <code>'#'</code>。</p>
	</li>
	<li>
	<p>将结果&nbsp;<strong>截断&nbsp;</strong>为最多 100 个字符。</p>
	</li>
</ol>

<p>对 <code>caption</code> 执行上述操作后，返回生成的&nbsp;<strong>标签&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">caption = "Leetcode daily streak achieved"</span></p>

<p><strong>输出：</strong> <span class="example-io">"#leetcodeDailyStreakAchieved"</span></p>

<p><strong>解释：</strong></p>

<p>除了 <code>"leetcode"</code> 以外的所有单词的首字母需要大写。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">caption = "can I Go There"</span></p>

<p><strong>输出：</strong> <span class="example-io">"#canIGoThere"</span></p>

<p><strong>解释：</strong></p>

<p>除了 <code>"can"</code> 以外的所有单词的首字母需要大写。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">caption = "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"</span></p>

<p><strong>输出：</strong> <span class="example-io">"#hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"</span></p>

<p><strong>解释：</strong></p>

<p>由于第一个单词长度为 101，因此需要从单词末尾截去最后两个字符。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= caption.length &lt;= 150</code></li>
	<li><code>caption</code> 仅由英文字母和 <code>' '</code> 组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们首先将标题字符串分割成单词，然后对每个单词进行处理。第一个单词需要全部小写，后续的单词首字母大写，其余部分小写。接着，我们将所有处理后的单词连接起来，并在前面加上 `#` 符号。最后，如果生成的标签长度超过 100 个字符，则截断为前 100 个字符。

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 是标题字符串的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def generateTag(self, caption: str) -> str:
        words = [s.capitalize() for s in caption.split()]
        if words:
            words[0] = words[0].lower()
        return "#" + "".join(words)[:99]
```

#### Java

```java
class Solution {
    public String generateTag(String caption) {
        String[] words = caption.trim().split("\\s+");
        StringBuilder sb = new StringBuilder("#");

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.isEmpty()) {
                continue;
            }

            word = word.toLowerCase();
            if (i == 0) {
                sb.append(word);
            } else {
                sb.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    sb.append(word.substring(1));
                }
            }

            if (sb.length() >= 100) {
                break;
            }
        }

        return sb.length() > 100 ? sb.substring(0, 100) : sb.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string generateTag(string caption) {
        istringstream iss(caption);
        string word;
        ostringstream oss;
        oss << "#";
        bool first = true;
        while (iss >> word) {
            transform(word.begin(), word.end(), word.begin(), ::tolower);
            if (first) {
                oss << word;
                first = false;
            } else {
                word[0] = toupper(word[0]);
                oss << word;
            }
            if (oss.str().length() >= 100) {
                break;
            }
        }

        string ans = oss.str();
        if (ans.length() > 100) {
            ans = ans.substr(0, 100);
        }
        return ans;
    }
};
```

#### Go

```go
func generateTag(caption string) string {
	words := strings.Fields(caption)
	var builder strings.Builder
	builder.WriteString("#")

	for i, word := range words {
		word = strings.ToLower(word)
		if i == 0 {
			builder.WriteString(word)
		} else {
			runes := []rune(word)
			if len(runes) > 0 {
				runes[0] = unicode.ToUpper(runes[0])
			}
			builder.WriteString(string(runes))
		}
		if builder.Len() >= 100 {
			break
		}
	}

	ans := builder.String()
	if len(ans) > 100 {
		ans = ans[:100]
	}
	return ans
}
```

#### TypeScript

```ts
function generateTag(caption: string): string {
    const words = caption.trim().split(/\s+/);
    let ans = '#';
    for (let i = 0; i < words.length; i++) {
        const word = words[i].toLowerCase();
        if (i === 0) {
            ans += word;
        } else {
            ans += word.charAt(0).toUpperCase() + word.slice(1);
        }
        if (ans.length >= 100) {
            ans = ans.slice(0, 100);
            break;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
