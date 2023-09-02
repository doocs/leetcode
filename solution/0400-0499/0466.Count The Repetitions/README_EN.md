# [466. Count The Repetitions](https://leetcode.com/problems/count-the-repetitions)

[中文文档](/solution/0400-0499/0466.Count%20The%20Repetitions/README.md)

## Description

<p>We define <code>str = [s, n]</code> as the string <code>str</code> which consists of the string <code>s</code> concatenated <code>n</code> times.</p>

<ul>
	<li>For example, <code>str == [&quot;abc&quot;, 3] ==&quot;abcabcabc&quot;</code>.</li>
</ul>

<p>We define that string <code>s1</code> can be obtained from string <code>s2</code> if we can remove some characters from <code>s2</code> such that it becomes <code>s1</code>.</p>

<ul>
	<li>For example, <code>s1 = &quot;abc&quot;</code> can be obtained from <code>s2 = &quot;ab<strong><u>dbe</u></strong>c&quot;</code> based on our definition by removing the bolded underlined characters.</li>
</ul>

<p>You are given two strings <code>s1</code> and <code>s2</code> and two integers <code>n1</code> and <code>n2</code>. You have the two strings <code>str1 = [s1, n1]</code> and <code>str2 = [s2, n2]</code>.</p>

<p>Return <em>the maximum integer </em><code>m</code><em> such that </em><code>str = [str2, m]</code><em> can be obtained from </em><code>str1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> s1 = "acb", n1 = 4, s2 = "ab", n2 = 2
<strong>Output:</strong> 2
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> s1 = "acb", n1 = 1, s2 = "acb", n2 = 1
<strong>Output:</strong> 1
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 100</code></li>
	<li><code>s1</code> and <code>s2</code> consist of lowercase English letters.</li>
	<li><code>1 &lt;= n1, n2 &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getMaxRepetitions(self, s1: str, n1: int, s2: str, n2: int) -> int:
        n = len(s2)
        d = {}
        for i in range(n):
            cnt = 0
            j = i
            for c in s1:
                if c == s2[j]:
                    j += 1
                if j == n:
                    cnt += 1
                    j = 0
            d[i] = (cnt, j)

        ans = 0
        j = 0
        for _ in range(n1):
            cnt, j = d[j]
            ans += cnt
        return ans // n2
```

### **Java**

```java
class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int m = s1.length(), n = s2.length();
        int[][] d = new int[n][0];
        for (int i = 0; i < n; ++i) {
            int j = i;
            int cnt = 0;
            for (int k = 0; k < m; ++k) {
                if (s1.charAt(k) == s2.charAt(j)) {
                    if (++j == n) {
                        j = 0;
                        ++cnt;
                    }
                }
            }
            d[i] = new int[] {cnt, j};
        }
        int ans = 0;
        for (int j = 0; n1 > 0; --n1) {
            ans += d[j][0];
            j = d[j][1];
        }
        return ans / n2;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int getMaxRepetitions(string s1, int n1, string s2, int n2) {
        int m = s1.size(), n = s2.size();
        vector<pair<int, int>> d;
        for (int i = 0; i < n; ++i) {
            int j = i;
            int cnt = 0;
            for (int k = 0; k < m; ++k) {
                if (s1[k] == s2[j]) {
                    if (++j == n) {
                        ++cnt;
                        j = 0;
                    }
                }
            }
            d.emplace_back(cnt, j);
        }
        int ans = 0;
        for (int j = 0; n1; --n1) {
            ans += d[j].first;
            j = d[j].second;
        }
        return ans / n2;
    }
};
```

### **Go**

```go
func getMaxRepetitions(s1 string, n1 int, s2 string, n2 int) (ans int) {
	n := len(s2)
	d := make([][2]int, n)
	for i := 0; i < n; i++ {
		j := i
		cnt := 0
		for k := range s1 {
			if s1[k] == s2[j] {
				j++
				if j == n {
					cnt++
					j = 0
				}
			}
		}
		d[i] = [2]int{cnt, j}
	}
	for j := 0; n1 > 0; n1-- {
		ans += d[j][0]
		j = d[j][1]
	}
	ans /= n2
	return
}
```

### **TypeScript**

```ts
function getMaxRepetitions(s1: string, n1: number, s2: string, n2: number): number {
    const n = s2.length;
    const d: number[][] = new Array(n).fill(0).map(() => new Array(2).fill(0));
    for (let i = 0; i < n; ++i) {
        let j = i;
        let cnt = 0;
        for (const c of s1) {
            if (c === s2[j]) {
                if (++j === n) {
                    j = 0;
                    ++cnt;
                }
            }
        }
        d[i] = [cnt, j];
    }
    let ans = 0;
    for (let j = 0; n1 > 0; --n1) {
        ans += d[j][0];
        j = d[j][1];
    }
    return Math.floor(ans / n2);
}
```

### **...**

```

```

<!-- tabs:end -->
