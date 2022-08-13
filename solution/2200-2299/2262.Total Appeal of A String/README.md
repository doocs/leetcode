# [2262. 字符串的总引力](https://leetcode.cn/problems/total-appeal-of-a-string)

[English Version](/solution/2200-2299/2262.Total%20Appeal%20of%20A%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>字符串的 <strong>引力</strong> 定义为：字符串中 <strong>不同</strong> 字符的数量。</p>

<ul>
	<li>例如，<code>"abbca"</code> 的引力为 <code>3</code> ，因为其中有 <code>3</code> 个不同字符 <code>'a'</code>、<code>'b'</code> 和 <code>'c'</code> 。</li>
</ul>

<p>给你一个字符串 <code>s</code> ，返回 <strong>其所有子字符串的总引力</strong> <strong>。</strong></p>

<p><strong>子字符串</strong> 定义为：字符串中的一个连续字符序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "abbca"
<strong>输出：</strong>28
<strong>解释：</strong>"abbca" 的子字符串有：
- 长度为 1 的子字符串："a"、"b"、"b"、"c"、"a" 的引力分别为 1、1、1、1、1，总和为 5 。
- 长度为 2 的子字符串："ab"、"bb"、"bc"、"ca" 的引力分别为 2、1、2、2 ，总和为 7 。
- 长度为 3 的子字符串："abb"、"bbc"、"bca" 的引力分别为 2、2、3 ，总和为 7 。
- 长度为 4 的子字符串："abbc"、"bbca" 的引力分别为 3、3 ，总和为 6 。
- 长度为 5 的子字符串："abbca" 的引力为 3 ，总和为 3 。
引力总和为 5 + 7 + 7 + 6 + 3 = 28 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = "code"
<strong>输出：</strong>20
<strong>解释：</strong>"code" 的子字符串有：
- 长度为 1 的子字符串："c"、"o"、"d"、"e" 的引力分别为 1、1、1、1 ，总和为 4 。
- 长度为 2 的子字符串："co"、"od"、"de" 的引力分别为 2、2、2 ，总和为 6 。
- 长度为 3 的子字符串："cod"、"ode" 的引力分别为 3、3 ，总和为 6 。
- 长度为 4 的子字符串："code" 的引力为 4 ，总和为 4 。
引力总和为 4 + 6 + 6 + 4 = 20 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

遍历字符串 s 每个字符 `s[i]`, 维护以 `s[i]` 结尾的子串的引力值之和 t，遍历过程中累加 t 得到结果。

若当前遍历到字符 `s[i]`，对应的引力值 t 的计算逻辑为：

1. 如果 `s[i]` 在之前没出现过，那么以 `s[i-1]` 结尾的每个子串的引力值都会加上 1，引力值之和会增加 i，再加上 1（`s[i]` 单独组成的子串的引力值），得到 `s[i]` 的引力值 t。
1. 如果 `s[i]` 在之前出现过，定义最近一次出现的下标为 j，那么向子串 `s[0..i-1], s[1..i-1], ..., s[j..i-1]` 的末尾添加 `s[i]`，引力值不变。而 `s[j+1..i-1], s[j+2..i=1], ..., s[i-1..i-1]` 由于不包含 s[i]，这些子串的引力值增加 1，因此有 i-j-1 个子串的引力值会增加 1，引力值之和增加 i-j-1，再加上 1，得到 `s[i]` 的引力值 t。

此过程中，我们用 pos 记录每个字符最近一次出现的位置。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def appealSum(self, s: str) -> int:
        ans = t = 0
        pos = [-1] * 26
        for i, c in enumerate(s):
            c = ord(c) - ord('a')
            t += i - pos[c]
            ans += t
            pos[c] = i
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long appealSum(String s) {
        long ans = 0;
        long t = 0;
        int[] pos = new int[26];
        Arrays.fill(pos, -1);
        for (int i = 0; i < s.length(); ++i) {
            int c = s.charAt(i) - 'a';
            t += i - pos[c];
            ans += t;
            pos[c] = i;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long appealSum(string s) {
        long long ans = 0, t = 0;
        vector<int> pos(26, -1);
        for (int i = 0; i < s.size(); ++i) {
            int c = s[i] - 'a';
            t += i - pos[c];
            ans += t;
            pos[c] = i;
        }
        return ans;
    }
};
```

### **Go**

```go
func appealSum(s string) int64 {
	var ans, t int64
	pos := make([]int, 26)
	for i := range pos {
		pos[i] = -1
	}
	for i, c := range s {
		c -= 'a'
		t += int64(i - pos[c])
		ans += t
		pos[c] = i
	}
	return ans
}
```

### **TypeScript**

```ts
function appealSum(s: string): number {
    const n = s.length;
    let dp = new Array(n + 1).fill(0);
    const hashMap = new Map();
    for (let i = 0; i < n; i++) {
        const c = s.charAt(i);
        dp[i + 1] = dp[i] + i + 1 - (hashMap.get(c) || 0);
        hashMap.set(c, i + 1);
    }
    return dp.reduce((a, c) => a + c, 0);
}
```

### **...**

```

```

<!-- tabs:end -->
