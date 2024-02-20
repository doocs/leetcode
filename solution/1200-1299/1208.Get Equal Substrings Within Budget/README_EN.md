# [1208. Get Equal Substrings Within Budget](https://leetcode.com/problems/get-equal-substrings-within-budget)

[中文文档](/solution/1200-1299/1208.Get%20Equal%20Substrings%20Within%20Budget/README.md)

<!-- tags:String,Binary Search,Prefix Sum,Sliding Window -->

## Description

<p>You are given two strings <code>s</code> and <code>t</code> of the same length and an integer <code>maxCost</code>.</p>

<p>You want to change <code>s</code> to <code>t</code>. Changing the <code>i<sup>th</sup></code> character of <code>s</code> to <code>i<sup>th</sup></code> character of <code>t</code> costs <code>|s[i] - t[i]|</code> (i.e., the absolute difference between the ASCII values of the characters).</p>

<p>Return <em>the maximum length of a substring of </em><code>s</code><em> that can be changed to be the same as the corresponding substring of </em><code>t</code><em> with a cost less than or equal to </em><code>maxCost</code>. If there is no substring from <code>s</code> that can be changed to its corresponding substring from <code>t</code>, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;, t = &quot;bcdf&quot;, maxCost = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> &quot;abc&quot; of s can change to &quot;bcd&quot;.
That costs 3, so the maximum length is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;, t = &quot;cdef&quot;, maxCost = 3
<strong>Output:</strong> 1
<strong>Explanation:</strong> Each character in s costs 2 to change to character in t,  so the maximum length is 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;, t = &quot;acde&quot;, maxCost = 0
<strong>Output:</strong> 1
<strong>Explanation:</strong> You cannot make any change, so the maximum length is 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>t.length == s.length</code></li>
	<li><code>0 &lt;= maxCost &lt;= 10<sup>6</sup></code></li>
	<li><code>s</code> and <code>t</code> consist of only lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: Prefix Sum + Binary Search

We can create an array $f$ of length $n + 1$, where $f[i]$ represents the sum of the absolute differences of ASCII values between the first $i$ characters of string $s$ and the first $i$ characters of string $t$. Thus, we can calculate the sum of the absolute differences of ASCII values from the $i$-th character to the $j$-th character of string $s$ by $f[j + 1] - f[i]$, where $0 \leq i \leq j < n$.

Note that the length has monotonicity, i.e., if there exists a substring of length $x$ that satisfies the condition, then a substring of length $x - 1$ must also satisfy the condition. Therefore, we can use binary search to find the maximum length.

We define a function $check(x)$, which indicates whether there exists a substring of length $x$ that satisfies the condition. In this function, we only need to enumerate all substrings of length $x$ and check whether they satisfy the condition. If there exists a substring that satisfies the condition, the function returns `true`, otherwise it returns `false`.

Next, we define the left boundary $l$ of binary search as $0$ and the right boundary $r$ as $n$. In each step, we let $mid = \lfloor \frac{l + r + 1}{2} \rfloor$. If the return value of $check(mid)$ is `true`, we update the left boundary to $mid$, otherwise we update the right boundary to $mid - 1$. After the binary search, the left boundary we get is the answer.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of string $s$.

<!-- tabs:start -->

```python
class Solution:
    def equalSubstring(self, s: str, t: str, maxCost: int) -> int:
        def check(x):
            for i in range(n):
                j = i + mid - 1
                if j < n and f[j + 1] - f[i] <= maxCost:
                    return True
            return False

        n = len(s)
        f = list(accumulate((abs(ord(a) - ord(b)) for a, b in zip(s, t)), initial=0))
        l, r = 0, n
        while l < r:
            mid = (l + r + 1) >> 1
            if check(mid):
                l = mid
            else:
                r = mid - 1
        return l
```

```java
class Solution {
    private int maxCost;
    private int[] f;
    private int n;

    public int equalSubstring(String s, String t, int maxCost) {
        n = s.length();
        f = new int[n + 1];
        this.maxCost = maxCost;
        for (int i = 0; i < n; ++i) {
            int x = Math.abs(s.charAt(i) - t.charAt(i));
            f[i + 1] = f[i] + x;
        }
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r + 1) >>> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int x) {
        for (int i = 0; i + x - 1 < n; ++i) {
            int j = i + x - 1;
            if (f[j + 1] - f[i] <= maxCost) {
                return true;
            }
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    int equalSubstring(string s, string t, int maxCost) {
        int n = s.size();
        int f[n + 1];
        f[0] = 0;
        for (int i = 0; i < n; ++i) {
            f[i + 1] = f[i] + abs(s[i] - t[i]);
        }
        auto check = [&](int x) -> bool {
            for (int i = 0; i + x - 1 < n; ++i) {
                int j = i + x - 1;
                if (f[j + 1] - f[i] <= maxCost) {
                    return true;
                }
            }
            return false;
        };
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};
```

```go
func equalSubstring(s string, t string, maxCost int) int {
	n := len(s)
	f := make([]int, n+1)
	for i, a := range s {
		f[i+1] = f[i] + abs(int(a)-int(t[i]))
	}
	check := func(x int) bool {
		for i := 0; i+x-1 < n; i++ {
			if f[i+x]-f[i] <= maxCost {
				return true
			}
		}
		return false
	}
	l, r := 0, n
	for l < r {
		mid := (l + r + 1) >> 1
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

<!-- tabs:end -->

### Solution 2: Two Pointers

We can maintain two pointers $j$ and $i$, initially $i = j = 0$; maintain a variable $sum$, representing the sum of the absolute differences of ASCII values in the index interval $[i,..j]$. In each step, we move $i$ to the right by one position, then update $sum = sum + |s[i] - t[i]|$. If $sum \gt maxCost$, then we move the pointer $j$ to the right in a loop, and continuously reduce the value of $sum$ during the moving process until $sum \leq maxCost$. Then we update the answer, i.e., $ans = \max(ans, i - j + 1)$.

Finally, return the answer.

The time complexity is $O(n)$, and the space complexity is $O(1)$. Here, $n$ is the length of string $s$.

<!-- tabs:start -->

```python
class Solution:
    def equalSubstring(self, s: str, t: str, maxCost: int) -> int:
        n = len(s)
        sum = j = 0
        ans = 0
        for i in range(n):
            sum += abs(ord(s[i]) - ord(t[i]))
            while sum > maxCost:
                sum -= abs(ord(s[j]) - ord(t[j]))
                j += 1
            ans = max(ans, i - j + 1)
        return ans
```

```java
class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int sum = 0;
        int ans = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            sum += Math.abs(s.charAt(i) - t.charAt(i));
            while (sum > maxCost) {
                sum -= Math.abs(s.charAt(j) - t.charAt(j));
                ++j;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int equalSubstring(string s, string t, int maxCost) {
        int n = s.size();
        int ans = 0, sum = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            sum += abs(s[i] - t[i]);
            while (sum > maxCost) {
                sum -= abs(s[j] - t[j]);
                ++j;
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

```go
func equalSubstring(s string, t string, maxCost int) (ans int) {
	var sum, j int
	for i := range s {
		sum += abs(int(s[i]) - int(t[i]))
		for ; sum > maxCost; j++ {
			sum -= abs(int(s[j]) - int(t[j]))
		}
		if ans < i-j+1 {
			ans = i - j + 1
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

<!-- tabs:end -->

<!-- end -->
