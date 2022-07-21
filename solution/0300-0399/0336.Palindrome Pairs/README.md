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

**字符串哈希**是把一个任意长度的字符串映射成一个非负整数，并且其冲突的概率几乎为 $0$。字符串哈希用于计算字符串哈希值，快速判断两个字符串是否相等。

取一固定值 $BASE$，把字符串看作是 $BASE$ 进制数，并分配一个大于 $0$ 的数值，代表每种字符。一般来说，我们分配的数值都远小于 $BASE$。例如，对于小写字母构成的字符串，可以令 $a=1$, $b=2$, ..., $z=26$。取一固定值 $MOD$，求出该 $BASE$ 进制对 $M$ 的余数，作为该字符串的 $hash$ 值。

一般来说，取 $BASE=131$ 或者 $BASE=13331$，此时 $hash$ 值产生的冲突概率极低。只要两个字符串 $hash$ 值相同，我们就认为两个字符串是相等的。通常 $MOD$ 取 $2^{64}$，C++ 里，可以直接使用 `unsigned long long` 类型存储这个 $hash$ 值，在计算时不处理算术溢出问题，产生溢出时相当于自动对 $2^{64}$ 取模，这样可以避免低效取模运算。

除了在极特殊构造的数据上，上述 $hash$ 算法很难产生冲突，一般情况下上述 $hash$ 算法完全可以出现在题目的标准答案中。我们还可以多取一些恰当的 $BASE$ 和 $MOD$ 的值（例如大质数），多进行几组 $hash$ 运算，当结果都相同时才认为原字符串相等，就更加难以构造出使这个 $hash$ 产生错误的数据。

**方法二：前缀树**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def palindromePairs(self, words: List[str]) -> List[List[int]]:
        d = {w: i for i, w in enumerate(words)}
        ans = []
        for i, w in enumerate(words):
            for j in range(len(w) + 1):
                a, b = w[:j], w[j:]
                ra, rb = a[::-1], b[::-1]
                if ra in d and d[ra] != i and b == rb:
                    ans.append([i, d[ra]])
                if j and rb in d and d[rb] != i and a == ra:
                    ans.append([d[rb], i])
        return ans
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

```java
class Trie {
    Trie[] children = new Trie[26];
    Integer v;

    void insert(String w, int i) {
        Trie node = this;
        for (char c : w.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
        }
        node.v = i;
    }

    Integer search(String w, int i, int j) {
        Trie node = this;
        for (int k = j; k >= i; --k) {
            int idx = w.charAt(k) - 'a';
            if (node.children[idx] == null) {
                return null;
            }
            node = node.children[idx];
        }
        return node.v;
    }
}

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        Trie trie = new Trie();
        int n = words.length;
        for (int i = 0; i < n; ++i) {
            trie.insert(words[i], i);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            String w = words[i];
            int m = w.length();
            for (int j = 0; j <= m; ++j) {
                if (isPalindrome(w, j, m - 1)) {
                    Integer k = trie.search(w, 0, j - 1);
                    if (k != null && k != i) {
                        ans.add(Arrays.asList(i, k));
                    }
                }
                if (j != 0 && isPalindrome(w, 0, j - 1)) {
                    Integer k = trie.search(w, j, m - 1);
                    if (k != null && k != i) {
                        ans.add(Arrays.asList(k, i));
                    }
                }
            }
        }
        return ans;
    }

    // TLE
    // private boolean isPalindrome(String w, int i, int j) {
    //     for (; i < j; ++i, --j) {
    //         if (w.charAt(i) != w.charAt(j)) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }

    private boolean isPalindrome(String w, int start, int end) {
        int i = start, j = end;
        for (; i < j; ++i, --j) {
            if (w.charAt(i) != w.charAt(j)) {
                return false;
            }
        }
        return true;
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
