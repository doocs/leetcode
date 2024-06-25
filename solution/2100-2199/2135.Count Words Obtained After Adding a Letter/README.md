---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2135.Count%20Words%20Obtained%20After%20Adding%20a%20Letter/README.md
rating: 1828
source: 第 275 场周赛 Q3
tags:
    - 位运算
    - 数组
    - 哈希表
    - 字符串
    - 排序
---

<!-- problem:start -->

# [2135. 统计追加字母可以获得的单词数](https://leetcode.cn/problems/count-words-obtained-after-adding-a-letter)

[English Version](/solution/2100-2199/2135.Count%20Words%20Obtained%20After%20Adding%20a%20Letter/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个下标从 <strong>0</strong> 开始的字符串数组 <code>startWords</code> 和 <code>targetWords</code> 。每个字符串都仅由 <strong>小写英文字母</strong> 组成。</p>

<p>对于 <code>targetWords</code> 中的每个字符串，检查是否能够从 <code>startWords</code> 中选出一个字符串，执行一次 <strong>转换操作</strong>&nbsp;，得到的结果与当前&nbsp;<code>targetWords</code> 字符串相等。</p>

<p><strong>转换操作</strong> 如下面两步所述：</p>

<ol>
	<li><strong>追加</strong> 任何 <strong>不存在</strong> 于当前字符串的任一小写字母到当前字符串的末尾。

    <ul>
    	<li>例如，如果字符串为 <code>"abc"</code> ，那么字母 <code>'d'</code>、<code>'e'</code> 或 <code>'y'</code> 都可以加到该字符串末尾，但 <code>'a'</code> 就不行。如果追加的是 <code>'d'</code> ，那么结果字符串为 <code>"abcd"</code> 。</li>
    </ul>
    </li>
    <li><strong>重排</strong> 新字符串中的字母，可以按 <strong>任意</strong> 顺序重新排布字母。
    <ul>
    	<li>例如，<code>"abcd"</code> 可以重排为 <code>"acbd"</code>、<code>"bacd"</code>、<code>"cbda"</code>，以此类推。注意，它也可以重排为 <code>"abcd"</code> 自身。</li>
    </ul>
    </li>

</ol>

<p>找出 <code>targetWords</code> 中有多少字符串能够由&nbsp;<code>startWords</code> 中的 <strong>任一</strong> 字符串执行上述转换操作获得。返回<em> </em><code>targetWords</code><em> </em>中这类 <strong>字符串的数目</strong> 。</p>

<p><strong>注意：</strong>你仅能验证 <code>targetWords</code> 中的字符串是否可以由 <code>startWords</code> 中的某个字符串经执行操作获得。<code>startWords</code>&nbsp; 中的字符串在这一过程中 <strong>不</strong> 发生实际变更。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>startWords = ["ant","act","tack"], targetWords = ["tack","act","acti"]
<strong>输出：</strong>2
<strong>解释：</strong>
- 为了形成 targetWords[0] = "tack" ，可以选用 startWords[1] = "act" ，追加字母 'k' ，并重排 "actk" 为 "tack" 。
- startWords 中不存在可以用于获得 targetWords[1] = "act" 的字符串。
  注意 "act" 确实存在于 startWords ，但是 <strong>必须</strong> 在重排前给这个字符串追加一个字母。
- 为了形成 targetWords[2] = "acti" ，可以选用 startWords[1] = "act" ，追加字母 'i' ，并重排 "acti" 为 "acti" 自身。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>startWords = ["ab","a"], targetWords = ["abc","abcd"]
<strong>输出：</strong>1
<strong>解释：</strong>
- 为了形成 targetWords[0] = "abc" ，可以选用 startWords[0] = "ab" ，追加字母 'c' ，并重排为 "abc" 。
- startWords 中不存在可以用于获得 targetWords[1] = "abcd" 的字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= startWords.length, targetWords.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= startWords[i].length, targetWords[j].length &lt;= 26</code></li>
	<li><code>startWords</code> 和 <code>targetWords</code> 中的每个字符串都仅由小写英文字母组成</li>
	<li>在 <code>startWords</code> 或 <code>targetWords</code> 的任一字符串中，每个字母至多出现一次</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 位运算

我们注意到，题目中给定的字符串只包含小写字母，并且每个字符串的字母至多出现一次。因此，我们可以用一个长度为 $26$ 的二进制数表示一个字符串，其中第 $i$ 位为 $1$ 表示字符串中包含第 $i$ 个小写字母，为 $0$ 表示字符串中不包含第 $i$ 个小写字母。

我们可以将字符串数组 $\text{startWords}$ 中的每个字符串转换为一个二进制数，并将这些二进制数存储到一个集合 $\text{s}$ 中。对于字符串数组 $\text{targetWords}$ 中的每个字符串，我们首先将其转换为一个二进制数，然后枚举这个字符串中的每个字母，将这个字母从二进制数中去掉，再检查是否存在一个二进制数在集合 $\text{s}$ 中，使得这个二进制数与去掉的字母的二进制数的异或结果在集合 $\text{s}$ 中，如果存在，那么这个字符串可以由 $\text{startWords}$ 中的某个字符串执行转换操作获得，答案加一。然后我们跳过这个字符串，继续处理下一个字符串。

时间复杂度 $O(n \times |\Sigma|)$，空间复杂度 $O(n)$。其中 $n$ 为字符串数组 $\text{targetWords}$ 的长度，而 $|\Sigma|$ 为字符串中的字符集大小，本题中 $|\Sigma| = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def wordCount(self, startWords: List[str], targetWords: List[str]) -> int:
        s = {sum(1 << (ord(c) - 97) for c in w) for w in startWords}
        ans = 0
        for w in targetWords:
            x = sum(1 << (ord(c) - 97) for c in w)
            for c in w:
                if x ^ (1 << (ord(c) - 97)) in s:
                    ans += 1
                    break
        return ans
```

#### Java

```java
class Solution {
    public int wordCount(String[] startWords, String[] targetWords) {
        Set<Integer> s = new HashSet<>();
        for (var w : startWords) {
            int x = 0;
            for (var c : w.toCharArray()) {
                x |= 1 << (c - 'a');
            }
            s.add(x);
        }
        int ans = 0;
        for (var w : targetWords) {
            int x = 0;
            for (var c : w.toCharArray()) {
                x |= 1 << (c - 'a');
            }
            for (var c : w.toCharArray()) {
                if (s.contains(x ^ (1 << (c - 'a')))) {
                    ++ans;
                    break;
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
    int wordCount(vector<string>& startWords, vector<string>& targetWords) {
        unordered_set<int> s;
        for (auto& w : startWords) {
            int x = 0;
            for (char c : w) {
                x |= 1 << (c - 'a');
            }
            s.insert(x);
        }
        int ans = 0;
        for (auto& w : targetWords) {
            int x = 0;
            for (char c : w) {
                x |= 1 << (c - 'a');
            }
            for (char c : w) {
                if (s.contains(x ^ (1 << (c - 'a')))) {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func wordCount(startWords []string, targetWords []string) (ans int) {
	s := map[int]bool{}
	for _, w := range startWords {
		x := 0
		for _, c := range w {
			x |= 1 << (c - 'a')
		}
		s[x] = true
	}
	for _, w := range targetWords {
		x := 0
		for _, c := range w {
			x |= 1 << (c - 'a')
		}
		for _, c := range w {
			if s[x^(1<<(c-'a'))] {
				ans++
				break
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function wordCount(startWords: string[], targetWords: string[]): number {
    const s = new Set<number>();
    for (const w of startWords) {
        let x = 0;
        for (const c of w) {
            x ^= 1 << (c.charCodeAt(0) - 97);
        }
        s.add(x);
    }
    let ans = 0;
    for (const w of targetWords) {
        let x = 0;
        for (const c of w) {
            x ^= 1 << (c.charCodeAt(0) - 97);
        }
        for (const c of w) {
            if (s.has(x ^ (1 << (c.charCodeAt(0) - 97)))) {
                ++ans;
                break;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
