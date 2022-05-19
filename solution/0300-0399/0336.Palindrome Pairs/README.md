# [336. 回文对](https://leetcode.cn/problems/palindrome-pairs)

[English Version](/solution/0300-0399/0336.Palindrome%20Pairs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一组<strong> 互不相同</strong> 的单词， 找出所有<strong> 不同<em> </em></strong>的索引对 <code>(i, j)</code>，使得列表中的两个单词， <code>words[i] + words[j]</code> ，可拼接成回文串。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["abcd","dcba","lls","s","sssll"]
<strong>输出：</strong>[[0,1],[1,0],[3,2],[2,4]] 
<strong>解释：</strong>可拼接成的回文串为 <code>["dcbaabcd","abcddcba","slls","llssssll"]</code>
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["bat","tab","cat"]
<strong>输出：</strong>[[0,1],[1,0]] 
<strong>解释：</strong>可拼接成的回文串为 <code>["battab","tabbat"]</code></pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>words = ["a",""]
<strong>输出：</strong>[[0,1],[1,0]]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= words.length <= 5000</code></li>
	<li><code>0 <= words[i].length <= 300</code></li>
	<li><code>words[i]</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：字符串哈希**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int BASE = 131;
    private static final long[] MUL = new long[310];
    private static final int MOD = (int) 1e9 + 7;
    static {
        MUL[0] = 1;
        for (int i = 1; i < MUL.length; ++i) {
            MUL[i] = (MUL[i - 1] * BASE) % MOD; 
        }
    }
    public List<List<Integer>> palindromePairs(String[] words) {
        int n = words.length;
        long[] prefix = new long[n];
        long[] suffix = new long[n];
        for (int i = 0; i < n; ++i) {
            String word = words[i];
            int m = word.length();
            for (int j = 0; j < m; ++j) {
                int t = word.charAt(j) - 'a' + 1;
                int s = word.charAt(m - j - 1) - 'a' + 1;
                prefix[i] = (prefix[i] * BASE) % MOD + t;
                suffix[i] = (suffix[i] * BASE) % MOD + s;
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (check(i, j, words[j].length(), words[i].length(), prefix, suffix)) {
                    ans.add(Arrays.asList(i, j));
                }
                if (check(j, i, words[i].length(), words[j].length(), prefix, suffix)) {
                    ans.add(Arrays.asList(j, i));
                }
            }
        }
        return ans;
    }

    private boolean check(int i, int j, int n, int m, long[] prefix, long[] suffix) {
        long t = ((prefix[i] * MUL[n]) % MOD + prefix[j]) % MOD;
        long s = ((suffix[j] * MUL[m]) % MOD + suffix[i]) % MOD;
        return t == s;
    }
}
```

### **Go**

```go
func palindromePairs(words []string) [][]int {
	base := 131
	mod := int(1e9) + 7
	mul := make([]int, 310)
	mul[0] = 1
	for i := 1; i < len(mul); i++ {
		mul[i] = (mul[i-1] * base) % mod
	}
	n := len(words)
	prefix := make([]int, n)
	suffix := make([]int, n)
	for i, word := range words {
		m := len(word)
		for j, c := range word {
			t := int(c-'a') + 1
			s := int(word[m-j-1]-'a') + 1
			prefix[i] = (prefix[i]*base)%mod + t
			suffix[i] = (suffix[i]*base)%mod + s
		}
	}
	check := func(i, j, n, m int) bool {
		t := ((prefix[i]*mul[n])%mod + prefix[j]) % mod
		s := ((suffix[j]*mul[m])%mod + suffix[i]) % mod
		return t == s
	}
	var ans [][]int
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if check(i, j, len(words[j]), len(words[i])) {
				ans = append(ans, []int{i, j})
			}
			if check(j, i, len(words[i]), len(words[j])) {
				ans = append(ans, []int{j, i})
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
