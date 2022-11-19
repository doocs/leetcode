# [1737. Change Minimum Characters to Satisfy One of Three Conditions](https://leetcode.com/problems/change-minimum-characters-to-satisfy-one-of-three-conditions)

[中文文档](/solution/1700-1799/1737.Change%20Minimum%20Characters%20to%20Satisfy%20One%20of%20Three%20Conditions/README.md)

## Description

<p>You are given two strings <code>a</code> and <code>b</code> that consist of lowercase letters. In one operation, you can change any character in <code>a</code> or <code>b</code> to <strong>any lowercase letter</strong>.</p>

<p>Your goal is to satisfy <strong>one</strong> of the following three conditions:</p>

<ul>
	<li><strong>Every</strong> letter in <code>a</code> is <strong>strictly less</strong> than <strong>every</strong> letter in <code>b</code> in the alphabet.</li>
	<li><strong>Every</strong> letter in <code>b</code> is <strong>strictly less</strong> than <strong>every</strong> letter in <code>a</code> in the alphabet.</li>
	<li><strong>Both</strong> <code>a</code> and <code>b</code> consist of <strong>only one</strong> distinct letter.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> number of operations needed to achieve your goal.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;aba&quot;, b = &quot;caa&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> Consider the best way to make each condition true:
1) Change b to &quot;ccc&quot; in 2 operations, then every letter in a is less than every letter in b.
2) Change a to &quot;bbb&quot; and b to &quot;aaa&quot; in 3 operations, then every letter in b is less than every letter in a.
3) Change a to &quot;aaa&quot; and b to &quot;aaa&quot; in 2 operations, then a and b consist of one distinct letter.
The best way was done in 2 operations (either condition 1 or condition 3).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;dabadd&quot;, b = &quot;cda&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The best way is to make condition 1 true by changing b to &quot;eee&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length &lt;= 10<sup>5</sup></code></li>
	<li><code>a</code> and <code>b</code> consist only of lowercase letters.</li>
</ul>

## Solutions

Prefix Sum

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minCharacters(self, a: str, b: str) -> int:
        def f(cnt1, cnt2):
            for i in range(1, 26):
                t = sum(cnt1[i:]) + sum(cnt2[:i])
                nonlocal ans
                ans = min(ans, t)

        m, n = len(a), len(b)
        cnt1 = [0] * 26
        cnt2 = [0] * 26
        for c in a:
            cnt1[ord(c) - ord('a')] += 1
        for c in b:
            cnt2[ord(c) - ord('a')] += 1
        ans = m + n
        for c1, c2 in zip(cnt1, cnt2):
            ans = min(ans, m + n - c1 - c2)
        f(cnt1, cnt2)
        f(cnt2, cnt1)
        return ans
```

### **Java**

```java
class Solution {
    private int ans;

    public int minCharacters(String a, String b) {
        int m = a.length(), n = b.length();
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < m; ++i) {
            ++cnt1[a.charAt(i) - 'a'];
        }
        for (int i = 0; i < n; ++i) {
            ++cnt2[b.charAt(i) - 'a'];
        }
        ans = m + n;
        for (int i = 0; i < 26; ++i) {
            ans = Math.min(ans, m + n - cnt1[i] - cnt2[i]);
        }
        f(cnt1, cnt2);
        f(cnt2, cnt1);
        return ans;
    }

    private void f(int[] cnt1, int[] cnt2) {
        for (int i = 1; i < 26; ++i) {
            int t = 0;
            for (int j = i; j < 26; ++j) {
                t += cnt1[j];
            }
            for (int j = 0; j < i; ++j) {
                t += cnt2[j];
            }
            ans = Math.min(ans, t);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minCharacters(string a, string b) {
        int m = a.size(), n = b.size();
        vector<int> cnt1(26);
        vector<int> cnt2(26);
        for (char& c : a) ++cnt1[c - 'a'];
        for (char& c : b) ++cnt2[c - 'a'];
        int ans = m + n;
        for (int i = 0; i < 26; ++i) ans = min(ans, m + n - cnt1[i] - cnt2[i]);
        auto f = [&](vector<int>& cnt1, vector<int>& cnt2) {
            for (int i = 1; i < 26; ++i) {
                int t = 0;
                for (int j = i; j < 26; ++j) t += cnt1[j];
                for (int j = 0; j < i; ++j) t += cnt2[j];
                ans = min(ans, t);
            }
        };
        f(cnt1, cnt2);
        f(cnt2, cnt1);
        return ans;
    }
};
```

### **Go**

```go
func minCharacters(a string, b string) int {
	cnt1 := [26]int{}
	cnt2 := [26]int{}
	for _, c := range a {
		cnt1[c-'a']++
	}
	for _, c := range b {
		cnt2[c-'a']++
	}
	m, n := len(a), len(b)
	ans := m + n
	for i := 0; i < 26; i++ {
		ans = min(ans, m+n-cnt1[i]-cnt2[i])
	}
	f := func(cnt1, cnt2 [26]int) {
		for i := 1; i < 26; i++ {
			t := 0
			for j := i; j < 26; j++ {
				t += cnt1[j]
			}
			for j := 0; j < i; j++ {
				t += cnt2[j]
			}
			ans = min(ans, t)
		}
	}
	f(cnt1, cnt2)
	f(cnt2, cnt1)
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
function minCharacters(a: string, b: string): number {
    const m = a.length,
        n = b.length;
    let count1 = new Array(26).fill(0);
    let count2 = new Array(26).fill(0);
    const base = 'a'.charCodeAt(0);

    for (let char of a) {
        count1[char.charCodeAt(0) - base]++;
    }
    for (let char of b) {
        count2[char.charCodeAt(0) - base]++;
    }

    let pre1 = 0,
        pre2 = 0;
    let ans = m + n;
    for (let i = 0; i < 25; i++) {
        pre1 += count1[i];
        pre2 += count2[i];
        // case1， case2， case3
        ans = Math.min(
            ans,
            m - pre1 + pre2,
            pre1 + n - pre2,
            m + n - count1[i] - count2[i],
        );
    }
    ans = Math.min(ans, m + n - count1[25] - count2[25]);

    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
