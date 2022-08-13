# [2287. 重排字符形成目标字符串](https://leetcode.cn/problems/rearrange-characters-to-make-target-string)

[English Version](/solution/2200-2299/2287.Rearrange%20Characters%20to%20Make%20Target%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong> 开始的字符串 <code>s</code> 和 <code>target</code> 。你可以从 <code>s</code> 取出一些字符并将其重排，得到若干新的字符串。</p>

<p>从 <code>s</code> 中取出字符并重新排列，返回可以形成 <code>target</code> 的 <strong>最大</strong> 副本数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "ilovecodingonleetcode", target = "code"
<strong>输出：</strong>2
<strong>解释：</strong>
对于 "code" 的第 1 个副本，选取下标为 4 、5 、6 和 7 的字符。
对于 "code" 的第 2 个副本，选取下标为 17 、18 、19 和 20 的字符。
形成的字符串分别是 "ecod" 和 "code" ，都可以重排为 "code" 。
可以形成最多 2 个 "code" 的副本，所以返回 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = "abcba", target = "abc"
<strong>输出：</strong>1
<strong>解释：</strong>
选取下标为 0 、1 和 2 的字符，可以形成 "abc" 的 1 个副本。 
可以形成最多 1 个 "abc" 的副本，所以返回 1 。
注意，尽管下标 3 和 4 分别有额外的 'a' 和 'b' ，但不能重用下标 2 处的 'c' ，所以无法形成 "abc" 的第 2 个副本。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = "abbaccaddaeea", target = "aaaaa"
<strong>输出：</strong>1
<strong>解释：</strong>
选取下标为 0 、3 、6 、9 和 12 的字符，可以形成 "aaaaa" 的 1 个副本。
可以形成最多 1 个 "aaaaa" 的副本，所以返回 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>1 &lt;= target.length &lt;= 10</code></li>
	<li><code>s</code> 和 <code>target</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def rearrangeCharacters(self, s: str, target: str) -> int:
        cnt = Counter(s)
        cnt2 = Counter(target)
        ans = inf
        for c, v in cnt2.items():
            if cnt[c] < v:
                return 0
            ans = min(ans, cnt[c] // v)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int rearrangeCharacters(String s, String target) {
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt1[c - 'a'];
        }
        for (char c : target.toCharArray()) {
            ++cnt2[c - 'a'];
        }
        int ans = 100;
        for (int i = 0; i < 26; ++i) {
            if (cnt2[i] > 0) {
                if (cnt1[i] < cnt2[i]) {
                    return 0;
                }
                ans = Math.min(ans, cnt1[i] / cnt2[i]);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int rearrangeCharacters(string s, string target) {
        vector<int> cnt1(26);
        vector<int> cnt2(26);
        for (char& c : s) ++cnt1[c - 'a'];
        for (char& c : target) ++cnt2[c - 'a'];
        int ans = 100;
        for (int i = 0; i < 26; ++i) {
            if (cnt2[i] <= 0) continue;
            if (cnt1[i] < cnt2[i]) return 0;
            ans = min(ans, cnt1[i] / cnt2[i]);
        }
        return ans;
    }
};
```

### **Go**

```go
func rearrangeCharacters(s string, target string) int {
	cnt1 := make([]int, 26)
	cnt2 := make([]int, 26)
	for _, c := range s {
		cnt1[c-'a']++
	}
	for _, c := range target {
		cnt2[c-'a']++
	}
	ans := 100
	for i, v := range cnt2 {
		if v <= 0 {
			continue
		}
		if cnt1[i] < v {
			return 0
		}
		ans = min(ans, cnt1[i]/v)
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function rearrangeCharacters(s: string, target: string): number {
    let cnt1 = new Array(128).fill(0),
        cnt2 = new Array(128).fill(0);
    for (let i of target) {
        cnt1[i.charCodeAt(0)]++;
    }
    for (let i of s) {
        cnt2[i.charCodeAt(0)]++;
    }
    let ans = Infinity;
    for (let i = 0; i < 128; i++) {
        if (cnt1[i] === 0) continue;
        ans = Math.min(ans, Math.floor(cnt2[i] / cnt1[i]));
    }
    return ans === Infinity ? 0 : ans;
}
```

### **...**

```

```

<!-- tabs:end -->
