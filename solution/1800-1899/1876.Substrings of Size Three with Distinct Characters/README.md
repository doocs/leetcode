# [1876. 长度为三且各字符不同的子字符串](https://leetcode.cn/problems/substrings-of-size-three-with-distinct-characters)

[English Version](/solution/1800-1899/1876.Substrings%20of%20Size%20Three%20with%20Distinct%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果一个字符串不含有任何重复字符，我们称这个字符串为 <strong>好</strong> 字符串。</p>

<p>给你一个字符串 <code>s</code> ，请你返回 <code>s</code> 中长度为 <strong>3</strong> 的 <strong>好子字符串</strong> 的数量。</p>

<p>注意，如果相同的好子字符串出现多次，每一次都应该被记入答案之中。</p>

<p><strong>子字符串</strong> 是一个字符串中连续的字符序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "xyzzaz"
<b>输出：</b>1
<b>解释：</b>总共有 4 个长度为 3 的子字符串："xyz"，"yzz"，"zza" 和 "zaz" 。
唯一的长度为 3 的好子字符串是 "xyz" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "aababcabc"
<b>输出：</b>4
<b>解释：</b>总共有 7 个长度为 3 的子字符串："aab"，"aba"，"bab"，"abc"，"bca"，"cab" 和 "abc" 。
好子字符串包括 "abc"，"bca"，"cab" 和 "abc" 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 100</code></li>
	<li><code>s</code>​​​​​​ 只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countGoodSubstrings(self, s: str) -> int:
        count, n = 0, len(s)
        for i in range(n - 2):
            count += s[i] != s[i + 1] and s[i] != s[i + 2] and s[i + 1] != s[i + 2]
        return count
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countGoodSubstrings(String s) {
        int count = 0, n = s.length();
        for (int i = 0; i < n - 2; ++i) {
            char a = s.charAt(i), b = s.charAt(i + 1), c = s.charAt(i + 2);
            if (a != b && a != c && b != c) {
                ++count;
            }
        }
        return count;
    }
}
```

### **TypeScript**

```ts
function countGoodSubstrings(s: string): number {
    const n: number = s.length;
    let count: number = 0;
    for (let i: number = 0; i < n - 2; ++i) {
        let a: string = s.charAt(i),
            b: string = s.charAt(i + 1),
            c: string = s.charAt(i + 2);
        if (a != b && a != c && b != c) {
            ++count;
        }
    }
    return count;
}
```

### **...**

```

```

<!-- tabs:end -->
