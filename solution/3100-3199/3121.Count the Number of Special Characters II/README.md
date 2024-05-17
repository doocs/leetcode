---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3121.Count%20the%20Number%20of%20Special%20Characters%20II/README.md
rating: 1411
source: 第 394 场周赛 Q2
tags:
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [3121. 统计特殊字母的数量 II](https://leetcode.cn/problems/count-the-number-of-special-characters-ii)

[English Version](/solution/3100-3199/3121.Count%20the%20Number%20of%20Special%20Characters%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>word</code>。如果 <code>word</code> 中同时出现某个字母 <code>c</code> 的小写形式和大写形式，并且<strong> 每个 </strong>小写形式的 <code>c</code> 都出现在第一个大写形式的 <code>c</code> 之前，则称字母 <code>c</code> 是一个 <strong>特殊字母</strong> 。</p>

<p>返回 <code>word</code> 中 <strong>特殊字母</strong> 的数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">word = "aaAbcBC"</span></p>

<p><strong>输出：</strong><span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>特殊字母是 <code>'a'</code>、<code>'b'</code> 和 <code>'c'</code>。</p>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">word = "abc"</span></p>

<p><strong>输出：</strong><span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p><code>word</code> 中不存在特殊字母。</p>
</div>

<p><strong class="example">示例 3:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">word = "AbBCab"</span></p>

<p><strong>输出：</strong><span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p><code>word</code> 中不存在特殊字母。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>word</code> 仅由小写和大写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表或数组

我们定义两个哈希表或数组 $\textit{first}$ 和 $\textit{last}$，用于存储每个字母第一次出现的位置和最后一次出现的位置。

然后我们遍历字符串 $\textit{word}$，更新 $\textit{first}$ 和 $\textit{last}$。

最后我们遍历所有的小写字母和大写字母，如果 $\textit{last}[a]$ 存在且 $\textit{first}[b]$ 存在且 $\textit{last}[a] < \textit{first}[b]$，则说明字母 $a$ 是一个特殊字母，将答案加一。

时间复杂度 $O(n + |\Sigma|)$，空间复杂度 $O(|\Sigma|)$。其中 $n$ 为字符串 $\textit{word}$ 的长度；而 $|\Sigma|$ 为字符集大小，本题中 $|\Sigma| \leq 128$。

<!-- tabs:start -->

```python
class Solution:
    def numberOfSpecialChars(self, word: str) -> int:
        first, last = {}, {}
        for i, c in enumerate(word):
            if c not in first:
                first[c] = i
            last[c] = i
        return sum(
            a in last and b in first and last[a] < first[b]
            for a, b in zip(ascii_lowercase, ascii_uppercase)
        )
```

```java
class Solution {
    public int numberOfSpecialChars(String word) {
        int[] first = new int['z' + 1];
        int[] last = new int['z' + 1];
        for (int i = 1; i <= word.length(); ++i) {
            int j = word.charAt(i - 1);
            if (first[j] == 0) {
                first[j] = i;
            }
            last[j] = i;
        }
        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            if (last['a' + i] > 0 && first['A' + i] > 0 && last['a' + i] < first['A' + i]) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numberOfSpecialChars(string word) {
        vector<int> first('z' + 1);
        vector<int> last('z' + 1);
        for (int i = 1; i <= word.size(); ++i) {
            int j = word[i - 1];
            if (first[j] == 0) {
                first[j] = i;
            }
            last[j] = i;
        }
        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            if (last['a' + i] && first['A' + i] && last['a' + i] < first['A' + i]) {
                ++ans;
            }
        }
        return ans;
    }
};
```

```go
func numberOfSpecialChars(word string) (ans int) {
	first := make([]int, 'z'+1)
	last := make([]int, 'z'+1)
	for i, c := range word {
		if first[c] == 0 {
			first[c] = i + 1
		}
		last[c] = i + 1
	}
	for i := 0; i < 26; i++ {
		if last['a'+i] > 0 && first['A'+i] > 0 && last['a'+i] < first['A'+i] {
			ans++
		}
	}
	return
}
```

```ts
function numberOfSpecialChars(word: string): number {
    const first: number[] = Array.from({ length: 'z'.charCodeAt(0) + 1 }, () => 0);
    const last: number[] = Array.from({ length: 'z'.charCodeAt(0) + 1 }, () => 0);
    for (let i = 0; i < word.length; ++i) {
        const j = word.charCodeAt(i);
        if (first[j] === 0) {
            first[j] = i + 1;
        }
        last[j] = i + 1;
    }
    let ans: number = 0;
    for (let i = 0; i < 26; ++i) {
        if (
            last['a'.charCodeAt(0) + i] &&
            first['A'.charCodeAt(0) + i] &&
            last['a'.charCodeAt(0) + i] < first['A'.charCodeAt(0) + i]
        ) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
