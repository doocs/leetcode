# [2785. 将字符串中的元音字母排序](https://leetcode.cn/problems/sort-vowels-in-a-string)

[English Version](/solution/2700-2799/2785.Sort%20Vowels%20in%20a%20String/README_EN.md)

<!-- tags:字符串,排序 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串&nbsp;<code>s</code>&nbsp;，将&nbsp;<code>s</code>&nbsp;中的元素重新 <b>排列</b>&nbsp;得到新的字符串&nbsp;<code>t</code>&nbsp;，它满足：</p>

<ul>
	<li>所有辅音字母都在原来的位置上。更正式的，如果满足&nbsp;<code>0 &lt;= i &lt; s.length</code>&nbsp;的下标 <code>i</code>&nbsp;处的&nbsp;<code>s[i]</code>&nbsp;是个辅音字母，那么&nbsp;<code>t[i] = s[i]</code>&nbsp;。</li>
	<li>元音字母都必须以他们的 <strong>ASCII</strong>&nbsp;值按 <strong>非递减</strong>&nbsp;顺序排列。更正式的，对于满足&nbsp;<code>0 &lt;= i &lt; j &lt; s.length</code>&nbsp;的下标 <code>i</code>&nbsp;和 <code>j</code>&nbsp; ，如果&nbsp;<code>s[i]</code> 和&nbsp;<code>s[j]</code>&nbsp;都是元音字母，那么&nbsp;<code>t[i]</code>&nbsp;的 ASCII 值不能大于&nbsp;<code>t[j]</code>&nbsp;的 ASCII 值。</li>
</ul>

<p>请你返回结果字母串。</p>

<p>元音字母为&nbsp;<code>'a'</code>&nbsp;，<code>'e'</code>&nbsp;，<code>'i'</code>&nbsp;，<code>'o'</code>&nbsp;和&nbsp;<code>'u'</code>&nbsp;，它们可能是小写字母也可能是大写字母，辅音字母是除了这 5 个字母以外的所有字母。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "lEetcOde"
<b>输出：</b>"lEOtcede"
<b>解释：</b>'E' ，'O' 和 'e' 是 s 中的元音字母，'l' ，'t' ，'c' 和 'd' 是所有的辅音。将元音字母按照 ASCII 值排序，辅音字母留在原地。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "lYmpH"
<b>输出：</b>"lYmpH"
<b>解释：</b>s 中没有元音字母（s 中都为辅音字母），所以我们返回 "lYmpH" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含英语字母表中的 <strong>大写&nbsp;</strong>和 <strong>小写&nbsp;</strong>字母。</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def sortVowels(self, s: str) -> str:
        vs = [c for c in s if c.lower() in "aeiou"]
        vs.sort()
        cs = list(s)
        j = 0
        for i, c in enumerate(cs):
            if c.lower() in "aeiou":
                cs[i] = vs[j]
                j += 1
        return "".join(cs)
```

```java
class Solution {
    public String sortVowels(String s) {
        List<Character> vs = new ArrayList<>();
        char[] cs = s.toCharArray();
        for (char c : cs) {
            char d = Character.toLowerCase(c);
            if (d == 'a' || d == 'e' || d == 'i' || d == 'o' || d == 'u') {
                vs.add(c);
            }
        }
        Collections.sort(vs);
        for (int i = 0, j = 0; i < cs.length; ++i) {
            char d = Character.toLowerCase(cs[i]);
            if (d == 'a' || d == 'e' || d == 'i' || d == 'o' || d == 'u') {
                cs[i] = vs.get(j++);
            }
        }
        return String.valueOf(cs);
    }
}
```

```cpp
class Solution {
public:
    string sortVowels(string s) {
        string vs;
        for (auto c : s) {
            char d = tolower(c);
            if (d == 'a' || d == 'e' || d == 'i' || d == 'o' || d == 'u') {
                vs.push_back(c);
            }
        }
        sort(vs.begin(), vs.end());
        for (int i = 0, j = 0; i < s.size(); ++i) {
            char d = tolower(s[i]);
            if (d == 'a' || d == 'e' || d == 'i' || d == 'o' || d == 'u') {
                s[i] = vs[j++];
            }
        }
        return s;
    }
};
```

```go
func sortVowels(s string) string {
	cs := []byte(s)
	vs := []byte{}
	for _, c := range cs {
		d := c | 32
		if d == 'a' || d == 'e' || d == 'i' || d == 'o' || d == 'u' {
			vs = append(vs, c)
		}
	}
	sort.Slice(vs, func(i, j int) bool { return vs[i] < vs[j] })
	j := 0
	for i, c := range cs {
		d := c | 32
		if d == 'a' || d == 'e' || d == 'i' || d == 'o' || d == 'u' {
			cs[i] = vs[j]
			j++
		}
	}
	return string(cs)
}
```

```ts
function sortVowels(s: string): string {
    const vowels = ['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'];
    const vs = s
        .split('')
        .filter(c => vowels.includes(c))
        .sort();
    const ans: string[] = [];
    let j = 0;
    for (const c of s) {
        ans.push(vowels.includes(c) ? vs[j++] : c);
    }
    return ans.join('');
}
```

```cs
public class Solution {
    public string SortVowels(string s) {
        List<char> vs = new List<char>();
        char[] cs = s.ToCharArray();
        foreach (char c in cs) {
            if (IsVowel(c)) {
                vs.Add(c);
            }
        }
        vs.Sort();
        for (int i = 0, j = 0; i < cs.Length; ++i) {
            if (IsVowel(cs[i])) {
                cs[i] = vs[j++];
            }
        }
        return new string(cs);
    }

    public bool IsVowel(char c) {
        c = char.ToLower(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
```

<!-- tabs:end -->

<!-- end -->
