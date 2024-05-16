---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2847.Smallest%20Number%20With%20Given%20Digit%20Product/README_EN.md
tags:
    - Greedy
    - Math
---

<!-- problem:start -->

# [2847. Smallest Number With Given Digit Product 🔒](https://leetcode.com/problems/smallest-number-with-given-digit-product)

[中文文档](/solution/2800-2899/2847.Smallest%20Number%20With%20Given%20Digit%20Product/README.md)

## Description

<p>Given a <strong>positive</strong> integer <code>n</code>, return <em>a string representing the <strong>smallest positive</strong> integer such that the product of its digits is equal to</em> <code>n</code><em>, or </em><code>&quot;-1&quot;</code><em> if no such number exists</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 105
<strong>Output:</strong> &quot;357&quot;
<strong>Explanation:</strong> 3 * 5 * 7 = 105. It can be shown that 357 is the smallest number with a product of digits equal to 105. So the answer would be &quot;105&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 7
<strong>Output:</strong> &quot;7&quot;
<strong>Explanation:</strong> Since 7 has only one digit, its product of digits would be 7. We will show that 7 is the smallest number with a product of digits equal to 7. Since the product of numbers 1 to 6 is 1 to 6 respectively, so &quot;7&quot; would be the answer.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 44
<strong>Output:</strong> &quot;-1&quot;
<strong>Explanation:</strong> It can be shown that there is no number such that its product of digits is equal to 44. So the answer would be &quot;-1&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>18</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Prime Factorization + Greedy

We consider prime factorizing the number $n$. If there are prime factors greater than $9$ in $n$, then it is impossible to find a number that meets the conditions, because prime factors greater than $9$ cannot be obtained by multiplying numbers from $1$ to $9$. For example, $11$ cannot be obtained by multiplying numbers from $1$ to $9$. Therefore, we only need to consider whether there are prime factors greater than $9$ in $n$. If there are, return $-1$ directly.

Otherwise, if the prime factors include $7$ and $5$, then the number $n$ can first be decomposed into several $7$s and $5$s. Two $3$s can be combined into a $9$, three $2$s can be combined into an $8$, and a $2$ and a $3$ can be combined into a $6$. Therefore, we only need to decompose the number into numbers from $2$ to $9$. We can use a greedy method, preferentially decomposing into $9$, then decomposing into $8$, and so on.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def smallestNumber(self, n: int) -> str:
        cnt = [0] * 10
        for i in range(9, 1, -1):
            while n % i == 0:
                n //= i
                cnt[i] += 1
        if n > 1:
            return "-1"
        ans = "".join(str(i) * cnt[i] for i in range(2, 10))
        return ans if ans else "1"
```

```java
class Solution {
    public String smallestNumber(long n) {
        int[] cnt = new int[10];
        for (int i = 9; i > 1; --i) {
            while (n % i == 0) {
                ++cnt[i];
                n /= i;
            }
        }
        if (n > 1) {
            return "-1";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < 10; ++i) {
            while (cnt[i] > 0) {
                sb.append(i);
                --cnt[i];
            }
        }
        String ans = sb.toString();
        return ans.isEmpty() ? "1" : ans;
    }
}
```

```cpp
class Solution {
public:
    string smallestNumber(long long n) {
        int cnt[10]{};
        for (int i = 9; i > 1; --i) {
            while (n % i == 0) {
                n /= i;
                ++cnt[i];
            }
        }
        if (n > 1) {
            return "-1";
        }
        string ans;
        for (int i = 2; i < 10; ++i) {
            ans += string(cnt[i], '0' + i);
        }
        return ans == "" ? "1" : ans;
    }
};
```

```go
func smallestNumber(n int64) string {
	cnt := [10]int{}
	for i := 9; i > 1; i-- {
		for n%int64(i) == 0 {
			cnt[i]++
			n /= int64(i)
		}
	}
	if n != 1 {
		return "-1"
	}
	sb := &strings.Builder{}
	for i := 2; i < 10; i++ {
		for j := 0; j < cnt[i]; j++ {
			sb.WriteByte(byte(i) + '0')
		}
	}
	ans := sb.String()
	if len(ans) > 0 {
		return ans
	}
	return "1"
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
