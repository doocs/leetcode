---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3324.Find%20the%20Sequence%20of%20Strings%20Appeared%20on%20the%20Screen/README.md
rating: 1293
source: 第 420 场周赛 Q1
tags:
    - 字符串
    - 模拟
---

<!-- problem:start -->

# [3324. 出现在屏幕上的字符串序列](https://leetcode.cn/problems/find-the-sequence-of-strings-appeared-on-the-screen)

[English Version](/solution/3300-3399/3324.Find%20the%20Sequence%20of%20Strings%20Appeared%20on%20the%20Screen/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>target</code>。</p>

<p>Alice 将会使用一种特殊的键盘在她的电脑上输入 <code>target</code>，这个键盘<strong> 只有两个 </strong>按键：</p>

<ul>
	<li>按键 1：在屏幕上的字符串后追加字符 <code>'a'</code>。</li>
	<li>按键 2：将屏幕上字符串的 <strong>最后一个 </strong>字符更改为英文字母表中的 <strong>下一个</strong> 字符。例如，<code>'c'</code> 变为 <code>'d'</code>，<code>'z'</code> 变为 <code>'a'</code>。</li>
</ul>

<p><strong>注意</strong>，最初屏幕上是一个<em>空</em>字符串 <code>""</code>，所以她<strong> 只能</strong> 按按键 1。</p>

<p>请你考虑按键次数 <strong>最少</strong> 的情况，按字符串出现顺序，返回 Alice 输入 <code>target</code> 时屏幕上出现的所有字符串列表。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">target = "abc"</span></p>

<p><strong>输出：</strong> <span class="example-io">["a","aa","ab","aba","abb","abc"]</span></p>

<p><strong>解释：</strong></p>

<p>Alice 按键的顺序如下：</p>

<ul>
	<li>按下按键 1，屏幕上的字符串变为 <code>"a"</code>。</li>
	<li>按下按键 1，屏幕上的字符串变为 <code>"aa"</code>。</li>
	<li>按下按键 2，屏幕上的字符串变为 <code>"ab"</code>。</li>
	<li>按下按键 1，屏幕上的字符串变为 <code>"aba"</code>。</li>
	<li>按下按键 2，屏幕上的字符串变为 <code>"abb"</code>。</li>
	<li>按下按键 2，屏幕上的字符串变为 <code>"abc"</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">target = "he"</span></p>

<p><strong>输出：</strong> <span class="example-io">["a","b","c","d","e","f","g","h","ha","hb","hc","hd","he"]</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= target.length &lt;= 400</code></li>
	<li><code>target</code> 仅由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以模拟 Alice 按键的过程，从空字符串开始，每次按键后更新字符串，直到得到目标字符串。

时间复杂度 $O(n^2 \times |\Sigma|)$，其中 $n$ 是目标字符串的长度，而 $\Sigma$ 是字符集，这里是小写字母集合，因此 $|\Sigma| = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def stringSequence(self, target: str) -> List[str]:
        ans = []
        for c in target:
            s = ans[-1] if ans else ""
            for a in ascii_lowercase:
                t = s + a
                ans.append(t)
                if a == c:
                    break
        return ans
```

#### Java

```java
class Solution {
    public List<String> stringSequence(String target) {
        List<String> ans = new ArrayList<>();
        for (char c : target.toCharArray()) {
            String s = ans.isEmpty() ? "" : ans.get(ans.size() - 1);
            for (char a = 'a'; a <= c; ++a) {
                String t = s + a;
                ans.add(t);
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
    vector<string> stringSequence(string target) {
        vector<string> ans;
        for (char c : target) {
            string s = ans.empty() ? "" : ans.back();
            for (char a = 'a'; a <= c; ++a) {
                string t = s + a;
                ans.push_back(t);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func stringSequence(target string) (ans []string) {
	for _, c := range target {
		s := ""
		if len(ans) > 0 {
			s = ans[len(ans)-1]
		}
		for a := 'a'; a <= c; a++ {
			t := s + string(a)
			ans = append(ans, t)
		}
	}
	return
}
```

#### TypeScript

```ts
function stringSequence(target: string): string[] {
    const ans: string[] = [];
    for (const c of target) {
        let s = ans.length > 0 ? ans[ans.length - 1] : '';
        for (let a = 'a'.charCodeAt(0); a <= c.charCodeAt(0); a++) {
            const t = s + String.fromCharCode(a);
            ans.push(t);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
