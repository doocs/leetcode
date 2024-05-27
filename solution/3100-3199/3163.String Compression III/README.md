---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3163.String%20Compression%20III/README.md
---

<!-- problem:start -->

# [3163. 压缩字符串 III](https://leetcode.cn/problems/string-compression-iii)

[English Version](/solution/3100-3199/3163.String%20Compression%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>word</code>，请你使用以下算法进行压缩：</p>

<ul>
	<li>从空字符串 <code>comp</code> 开始。当 <code>word</code> <strong>不为空</strong> 时，执行以下操作：

    <ul>
    	<li>移除 <code>word</code> 的最长单字符前缀，该前缀由单一字符 <code>c</code> 重复多次组成，且该前缀长度 <strong>最多 </strong>为 9 。</li>
    	<li>将前缀的长度和字符 <code>c</code> 追加到 <code>comp</code> 。</li>
    </ul>
    </li>

</ul>

<p>返回字符串 <code>comp</code> 。</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">word = "abcde"</span></p>

<p><strong>输出：</strong><span class="example-io">"1a1b1c1d1e"</span></p>

<p><strong>解释：</strong></p>

<p>初始时，<code>comp = ""</code> 。进行 5 次操作，每次操作分别选择 <code>"a"</code>、<code>"b"</code>、<code>"c"</code>、<code>"d"</code> 和 <code>"e"</code> 作为前缀。</p>

<p>对每个前缀，将 <code>"1"</code> 和对应的字符追加到 <code>comp</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">word = "aaaaaaaaaaaaaabb"</span></p>

<p><strong>输出：</strong><span class="example-io">"9a5a2b"</span></p>

<p><strong>解释：</strong></p>

<p>初始时，<code>comp = ""</code>。进行 3 次操作，每次操作分别选择 <code>"aaaaaaaaa"</code>、<code>"aaaaa"</code> 和 <code>"bb"</code> 作为前缀。</p>

<ul>
	<li>对于前缀 <code>"aaaaaaaaa"</code>，将 <code>"9"</code> 和 <code>"a"</code> 追加到 <code>comp</code>。</li>
	<li>对于前缀 <code>"aaaaa"</code>，将 <code>"5"</code> 和 <code>"a"</code> 追加到 <code>comp</code>。</li>
	<li>对于前缀 <code>"bb"</code>，将 <code>"2"</code> 和 <code>"b"</code> 追加到 <code>comp</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>word</code> 仅由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们可以利用双指针，统计每个字符的连续出现次数。假如当前字符 $c$ 连续出现了 $k$ 次，然后我们将 $k$ 划分成若干个 $x$，每个 $x$ 最大为 $9$，然后将 $x$ 和 $c$ 拼接起来，将每个 $x$ 和 $c$ 拼接起来到结果中。

最后返回结果即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 `word` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def compressedString(self, word: str) -> str:
        g = groupby(word)
        ans = []
        for c, v in g:
            k = len(list(v))
            while k:
                x = min(9, k)
                ans.append(str(x) + c)
                k -= x
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String compressedString(String word) {
        StringBuilder ans = new StringBuilder();
        int n = word.length();
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && word.charAt(j) == word.charAt(i)) {
                ++j;
            }
            int k = j - i;
            while (k > 0) {
                int x = Math.min(9, k);
                ans.append(x).append(word.charAt(i));
                k -= x;
            }
            i = j;
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string compressedString(string word) {
        string ans;
        int n = word.length();
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && word[j] == word[i]) {
                ++j;
            }
            int k = j - i;
            while (k > 0) {
                int x = min(9, k);
                ans.push_back('0' + x);
                ans.push_back(word[i]);
                k -= x;
            }
            i = j;
        }
        return ans;
    }
};
```

#### Go

```go
func compressedString(word string) string {
	ans := []byte{}
	n := len(word)
	for i := 0; i < n; {
		j := i + 1
		for j < n && word[j] == word[i] {
			j++
		}
		k := j - i
		for k > 0 {
			x := min(9, k)
			ans = append(ans, byte('0'+x))
			ans = append(ans, word[i])
			k -= x
		}
		i = j
	}
	return string(ans)
}
```

#### TypeScript

```ts
function compressedString(word: string): string {
    const ans: string[] = [];
    const n = word.length;
    for (let i = 0; i < n; ) {
        let j = i + 1;
        while (j < n && word[j] === word[i]) {
            ++j;
        }
        let k = j - i;
        while (k) {
            const x = Math.min(k, 9);
            ans.push(x + word[i]);
            k -= x;
        }
        i = j;
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
