# [2287. Rearrange Characters to Make Target String](https://leetcode.com/problems/rearrange-characters-to-make-target-string)

[中文文档](/solution/2200-2299/2287.Rearrange%20Characters%20to%20Make%20Target%20String/README.md)

## Description

<p>You are given two <strong>0-indexed</strong> strings <code>s</code> and <code>target</code>. You can take some letters from <code>s</code> and rearrange them to form new strings.</p>

<p>Return<em> the <strong>maximum</strong> number of copies of </em><code>target</code><em> that can be formed by taking letters from </em><code>s</code><em> and rearranging them.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ilovecodingonleetcode&quot;, target = &quot;code&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong>
For the first copy of &quot;code&quot;, take the letters at indices 4, 5, 6, and 7.
For the second copy of &quot;code&quot;, take the letters at indices 17, 18, 19, and 20.
The strings that are formed are &quot;ecod&quot; and &quot;code&quot; which can both be rearranged into &quot;code&quot;.
We can make at most two copies of &quot;code&quot;, so we return 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcba&quot;, target = &quot;abc&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong>
We can make one copy of &quot;abc&quot; by taking the letters at indices 0, 1, and 2.
We can make at most one copy of &quot;abc&quot;, so we return 1.
Note that while there is an extra &#39;a&#39; and &#39;b&#39; at indices 3 and 4, we cannot reuse the letter &#39;c&#39; at index 2, so we cannot make a second copy of &quot;abc&quot;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abbaccaddaeea&quot;, target = &quot;aaaaa&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong>
We can make one copy of &quot;aaaaa&quot; by taking the letters at indices 0, 3, 6, 9, and 12.
We can make at most one copy of &quot;aaaaa&quot;, so we return 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>1 &lt;= target.length &lt;= 10</code></li>
	<li><code>s</code> and <code>target</code> consist of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
