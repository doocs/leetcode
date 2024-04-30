# [2843. Count Symmetric Integers](https://leetcode.com/problems/count-symmetric-integers)

[中文文档](/solution/2800-2899/2843.Count%20Symmetric%20Integers/README.md)

<!-- tags:Math,Enumeration -->

## Description

<p>You are given two positive integers <code>low</code> and <code>high</code>.</p>

<p>An integer <code>x</code> consisting of <code>2 * n</code> digits is <strong>symmetric</strong> if the sum of the first <code>n</code> digits of <code>x</code> is equal to the sum of the last <code>n</code> digits of <code>x</code>. Numbers with an odd number of digits are never symmetric.</p>

<p>Return <em>the <strong>number of symmetric</strong> integers in the range</em> <code>[low, high]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> low = 1, high = 100
<strong>Output:</strong> 9
<strong>Explanation:</strong> There are 9 symmetric integers between 1 and 100: 11, 22, 33, 44, 55, 66, 77, 88, and 99.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> low = 1200, high = 1230
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 4 symmetric integers between 1200 and 1230: 1203, 1212, 1221, and 1230.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= low &lt;= high &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

### Solution 1: Enumeration

We enumerate each integer $x$ in the range $[low, high]$, and check whether it is a palindromic number. If it is, then the answer $ans$ is increased by $1$.

The time complexity is $O(n \times \log m)$, and the space complexity is $O(\log m)$. Here, $n$ is the number of integers in the range $[low, high]$, and $m$ is the maximum integer given in the problem.

<!-- tabs:start -->

```python
class Solution:
    def countSymmetricIntegers(self, low: int, high: int) -> int:
        def f(x: int) -> bool:
            s = str(x)
            if len(s) & 1:
                return False
            n = len(s) // 2
            return sum(map(int, s[:n])) == sum(map(int, s[n:]))

        return sum(f(x) for x in range(low, high + 1))
```

```java
class Solution {
    public int countSymmetricIntegers(int low, int high) {
        int ans = 0;
        for (int x = low; x <= high; ++x) {
            ans += f(x);
        }
        return ans;
    }

    private int f(int x) {
        String s = "" + x;
        int n = s.length();
        if (n % 2 == 1) {
            return 0;
        }
        int a = 0, b = 0;
        for (int i = 0; i < n / 2; ++i) {
            a += s.charAt(i) - '0';
        }
        for (int i = n / 2; i < n; ++i) {
            b += s.charAt(i) - '0';
        }
        return a == b ? 1 : 0;
    }
}
```

```cpp
class Solution {
public:
    int countSymmetricIntegers(int low, int high) {
        int ans = 0;
        auto f = [](int x) {
            string s = to_string(x);
            int n = s.size();
            if (n & 1) {
                return 0;
            }
            int a = 0, b = 0;
            for (int i = 0; i < n / 2; ++i) {
                a += s[i] - '0';
                b += s[n / 2 + i] - '0';
            }
            return a == b ? 1 : 0;
        };
        for (int x = low; x <= high; ++x) {
            ans += f(x);
        }
        return ans;
    }
};
```

```go
func countSymmetricIntegers(low int, high int) (ans int) {
	f := func(x int) int {
		s := strconv.Itoa(x)
		n := len(s)
		if n&1 == 1 {
			return 0
		}
		a, b := 0, 0
		for i := 0; i < n/2; i++ {
			a += int(s[i] - '0')
			b += int(s[n/2+i] - '0')
		}
		if a == b {
			return 1
		}
		return 0
	}
	for x := low; x <= high; x++ {
		ans += f(x)
	}
	return
}
```

```ts
function countSymmetricIntegers(low: number, high: number): number {
    let ans = 0;
    const f = (x: number): number => {
        const s = x.toString();
        const n = s.length;
        if (n & 1) {
            return 0;
        }
        let a = 0;
        let b = 0;
        for (let i = 0; i < n >> 1; ++i) {
            a += Number(s[i]);
            b += Number(s[(n >> 1) + i]);
        }
        return a === b ? 1 : 0;
    };
    for (let x = low; x <= high; ++x) {
        ans += f(x);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
