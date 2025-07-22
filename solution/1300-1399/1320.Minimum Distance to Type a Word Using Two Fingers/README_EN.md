---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1320.Minimum%20Distance%20to%20Type%20a%20Word%20Using%20Two%20Fingers/README_EN.md
rating: 2027
source: Weekly Contest 171 Q4
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [1320. Minimum Distance to Type a Word Using Two Fingers](https://leetcode.com/problems/minimum-distance-to-type-a-word-using-two-fingers)

[中文文档](/solution/1300-1399/1320.Minimum%20Distance%20to%20Type%20a%20Word%20Using%20Two%20Fingers/README.md)

## Description

<!-- description:start -->

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1320.Minimum%20Distance%20to%20Type%20a%20Word%20Using%20Two%20Fingers/images/leetcode_keyboard.png" style="width: 349px; height: 209px;" />
<p>You have a keyboard layout as shown above in the <strong>X-Y</strong> plane, where each English uppercase letter is located at some coordinate.</p>

<ul>
	<li>For example, the letter <code>&#39;A&#39;</code> is located at coordinate <code>(0, 0)</code>, the letter <code>&#39;B&#39;</code> is located at coordinate <code>(0, 1)</code>, the letter <code>&#39;P&#39;</code> is located at coordinate <code>(2, 3)</code> and the letter <code>&#39;Z&#39;</code> is located at coordinate <code>(4, 1)</code>.</li>
</ul>

<p>Given the string <code>word</code>, return <em>the minimum total <strong>distance</strong> to type such string using only two fingers</em>.</p>

<p>The <strong>distance</strong> between coordinates <code>(x<sub>1</sub>, y<sub>1</sub>)</code> and <code>(x<sub>2</sub>, y<sub>2</sub>)</code> is <code>|x<sub>1</sub> - x<sub>2</sub>| + |y<sub>1</sub> - y<sub>2</sub>|</code>.</p>

<p><strong>Note</strong> that the initial positions of your two fingers are considered free so do not count towards your total distance, also your two fingers do not have to start at the first letter or the first two letters.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;CAKE&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> Using two fingers, one optimal way to type &quot;CAKE&quot; is: 
Finger 1 on letter &#39;C&#39; -&gt; cost = 0 
Finger 1 on letter &#39;A&#39; -&gt; cost = Distance from letter &#39;C&#39; to letter &#39;A&#39; = 2 
Finger 2 on letter &#39;K&#39; -&gt; cost = 0 
Finger 2 on letter &#39;E&#39; -&gt; cost = Distance from letter &#39;K&#39; to letter &#39;E&#39; = 1 
Total distance = 3
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;HAPPY&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> Using two fingers, one optimal way to type &quot;HAPPY&quot; is:
Finger 1 on letter &#39;H&#39; -&gt; cost = 0
Finger 1 on letter &#39;A&#39; -&gt; cost = Distance from letter &#39;H&#39; to letter &#39;A&#39; = 2
Finger 2 on letter &#39;P&#39; -&gt; cost = 0
Finger 2 on letter &#39;P&#39; -&gt; cost = Distance from letter &#39;P&#39; to letter &#39;P&#39; = 0
Finger 1 on letter &#39;Y&#39; -&gt; cost = Distance from letter &#39;A&#39; to letter &#39;Y&#39; = 4
Total distance = 6
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= word.length &lt;= 300</code></li>
	<li><code>word</code> consists of uppercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j][k]$ to represent the minimum distance after typing $\textit{word}[i]$, with finger 1 at position $j$ and finger 2 at position $k$. Here, positions $j$ and $k$ represent the numbers corresponding to the letters, ranging from $[0,..25]$. Initially, $f[i][j][k] = \infty$.

We implement a function $\textit{dist}(a, b)$ to represent the distance between positions $a$ and $b$, i.e., $\textit{dist}(a, b) = |\frac{a}{6} - \frac{b}{6}| + |a \bmod 6 - b \bmod 6|$.

Next, we consider typing $\textit{word}[0]$, i.e., the case with only one letter. There are two choices:

-   Finger 1 is at the position of $\textit{word}[0]$, and finger 2 is at any position. In this case, $f[0][\textit{word}[0]][k] = 0$, where $k \in [0,..25]$.
-   Finger 2 is at the position of $\textit{word}[0]$, and finger 1 is at any position. In this case, $f[0][k][\textit{word}[0]] = 0$, where $k \in [0,..25]$.

Then we consider typing $\textit{word}[1,..n-1]$. Let the positions of the previous letter and the current letter be $a$ and $b$, respectively. Next, we discuss the following cases:

If the current finger 1 is at position $b$, we enumerate the position $j$ of finger 2. If the previous position $a$ was also the position of finger 1, then $f[i][b][j] = \min(f[i][b][j], f[i-1][a][j] + \textit{dist}(a, b))$. If the position of finger 2 is the same as the previous position $a$, i.e., $j = a$, then we enumerate the position $k$ of finger 1 in the previous position. In this case, $f[i][b][j] = \min(f[i][b][j], f[i-1][k][a] + \textit{dist}(k, b))$.

Similarly, if the current finger 2 is at position $b$, we enumerate the position $j$ of finger 1. If the previous position $a$ was also the position of finger 2, then $f[i][j][b] = \min(f[i][j][b], f[i-1][j][a] + \textit{dist}(a, b))$. If the position of finger 1 is the same as the previous position $a$, i.e., $j = a$, then we enumerate the position $k$ of finger 2 in the previous position. In this case, $f[i][j][b] = \min(f[i][j][b], f[i-1][a][k] + \textit{dist}(k, b))$.

Finally, we enumerate the positions of finger 1 and finger 2 at the last position and take the minimum value as the answer.

The time complexity is $O(n \times |\Sigma|^2)$, and the space complexity is $O(n \times |\Sigma|^2)$. Here, $n$ is the length of the string $\textit{word}$, and $|\Sigma|$ is the size of the alphabet, which is $26$ in this problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumDistance(self, word: str) -> int:
        def dist(a: int, b: int) -> int:
            x1, y1 = divmod(a, 6)
            x2, y2 = divmod(b, 6)
            return abs(x1 - x2) + abs(y1 - y2)

        n = len(word)
        f = [[[inf] * 26 for _ in range(26)] for _ in range(n)]
        for j in range(26):
            f[0][ord(word[0]) - ord('A')][j] = 0
            f[0][j][ord(word[0]) - ord('A')] = 0
        for i in range(1, n):
            a, b = ord(word[i - 1]) - ord('A'), ord(word[i]) - ord('A')
            d = dist(a, b)
            for j in range(26):
                f[i][b][j] = min(f[i][b][j], f[i - 1][a][j] + d)
                f[i][j][b] = min(f[i][j][b], f[i - 1][j][a] + d)
                if j == a:
                    for k in range(26):
                        t = dist(k, b)
                        f[i][b][j] = min(f[i][b][j], f[i - 1][k][a] + t)
                        f[i][j][b] = min(f[i][j][b], f[i - 1][a][k] + t)
        a = min(f[n - 1][ord(word[-1]) - ord('A')])
        b = min(f[n - 1][j][ord(word[-1]) - ord('A')] for j in range(26))
        return int(min(a, b))
```

#### Java

```java
class Solution {
    public int minimumDistance(String word) {
        int n = word.length();
        final int inf = 1 << 30;
        int[][][] f = new int[n][26][26];
        for (int[][] g : f) {
            for (int[] h : g) {
                Arrays.fill(h, inf);
            }
        }
        for (int j = 0; j < 26; ++j) {
            f[0][word.charAt(0) - 'A'][j] = 0;
            f[0][j][word.charAt(0) - 'A'] = 0;
        }
        for (int i = 1; i < n; ++i) {
            int a = word.charAt(i - 1) - 'A';
            int b = word.charAt(i) - 'A';
            int d = dist(a, b);
            for (int j = 0; j < 26; ++j) {
                f[i][b][j] = Math.min(f[i][b][j], f[i - 1][a][j] + d);
                f[i][j][b] = Math.min(f[i][j][b], f[i - 1][j][a] + d);
                if (j == a) {
                    for (int k = 0; k < 26; ++k) {
                        int t = dist(k, b);
                        f[i][b][j] = Math.min(f[i][b][j], f[i - 1][k][a] + t);
                        f[i][j][b] = Math.min(f[i][j][b], f[i - 1][a][k] + t);
                    }
                }
            }
        }
        int ans = inf;
        for (int j = 0; j < 26; ++j) {
            ans = Math.min(ans, f[n - 1][j][word.charAt(n - 1) - 'A']);
            ans = Math.min(ans, f[n - 1][word.charAt(n - 1) - 'A'][j]);
        }
        return ans;
    }

    private int dist(int a, int b) {
        int x1 = a / 6, y1 = a % 6;
        int x2 = b / 6, y2 = b % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumDistance(string word) {
        int n = word.size();
        const int inf = 1 << 30;
        vector<vector<vector<int>>> f(n, vector<vector<int>>(26, vector<int>(26, inf)));
        for (int j = 0; j < 26; ++j) {
            f[0][word[0] - 'A'][j] = 0;
            f[0][j][word[0] - 'A'] = 0;
        }
        for (int i = 1; i < n; ++i) {
            int a = word[i - 1] - 'A';
            int b = word[i] - 'A';
            int d = dist(a, b);
            for (int j = 0; j < 26; ++j) {
                f[i][b][j] = min(f[i][b][j], f[i - 1][a][j] + d);
                f[i][j][b] = min(f[i][j][b], f[i - 1][j][a] + d);
                if (j == a) {
                    for (int k = 0; k < 26; ++k) {
                        int t = dist(k, b);
                        f[i][b][j] = min(f[i][b][j], f[i - 1][k][a] + t);
                        f[i][j][b] = min(f[i][j][b], f[i - 1][a][k] + t);
                    }
                }
            }
        }
        int ans = inf;
        for (int j = 0; j < 26; ++j) {
            ans = min(ans, f[n - 1][word[n - 1] - 'A'][j]);
            ans = min(ans, f[n - 1][j][word[n - 1] - 'A']);
        }
        return ans;
    }

    int dist(int a, int b) {
        int x1 = a / 6, y1 = a % 6;
        int x2 = b / 6, y2 = b % 6;
        return abs(x1 - x2) + abs(y1 - y2);
    }
};
```

#### Go

```go
func minimumDistance(word string) int {
	n := len(word)
	f := make([][26][26]int, n)
	const inf = 1 << 30
	for i := range f {
		for j := range f[i] {
			for k := range f[i][j] {
				f[i][j][k] = inf
			}
		}
	}
	for j := range f[0] {
		f[0][word[0]-'A'][j] = 0
		f[0][j][word[0]-'A'] = 0
	}
	dist := func(a, b int) int {
		x1, y1 := a/6, a%6
		x2, y2 := b/6, b%6
		return abs(x1-x2) + abs(y1-y2)
	}
	for i := 1; i < n; i++ {
		a, b := int(word[i-1]-'A'), int(word[i]-'A')
		d := dist(a, b)
		for j := 0; j < 26; j++ {
			f[i][b][j] = min(f[i][b][j], f[i-1][a][j]+d)
			f[i][j][b] = min(f[i][j][b], f[i-1][j][a]+d)
			if j == a {
				for k := 0; k < 26; k++ {
					t := dist(k, b)
					f[i][b][j] = min(f[i][b][j], f[i-1][k][a]+t)
					f[i][j][b] = min(f[i][j][b], f[i-1][a][k]+t)
				}
			}
		}
	}
	ans := inf
	for j := 0; j < 26; j++ {
		ans = min(ans, f[n-1][word[n-1]-'A'][j])
		ans = min(ans, f[n-1][j][word[n-1]-'A'])
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
