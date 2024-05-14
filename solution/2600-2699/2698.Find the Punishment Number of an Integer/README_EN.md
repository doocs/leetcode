# [2698. Find the Punishment Number of an Integer](https://leetcode.com/problems/find-the-punishment-number-of-an-integer)

[中文文档](/solution/2600-2699/2698.Find%20the%20Punishment%20Number%20of%20an%20Integer/README.md)

<!-- tags:Math,Backtracking -->

<!-- difficulty:Medium -->

## Description

<p>Given a positive integer <code>n</code>, return <em>the <strong>punishment number</strong></em> of <code>n</code>.</p>

<p>The <strong>punishment number</strong> of <code>n</code> is defined as the sum of the squares of all integers <code>i</code> such that:</p>

<ul>
	<li><code>1 &lt;= i &lt;= n</code></li>
	<li>The decimal representation of <code>i * i</code> can be partitioned into contiguous substrings such that the sum of the integer values of these substrings equals <code>i</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> 182
<strong>Explanation:</strong> There are exactly 3 integers i that satisfy the conditions in the statement:
- 1 since 1 * 1 = 1
- 9 since 9 * 9 = 81 and 81 can be partitioned into 8 + 1.
- 10 since 10 * 10 = 100 and 100 can be partitioned into 10 + 0.
Hence, the punishment number of 10 is 1 + 81 + 100 = 182
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 37
<strong>Output:</strong> 1478
<strong>Explanation:</strong> There are exactly 4 integers i that satisfy the conditions in the statement:
- 1 since 1 * 1 = 1. 
- 9 since 9 * 9 = 81 and 81 can be partitioned into 8 + 1. 
- 10 since 10 * 10 = 100 and 100 can be partitioned into 10 + 0. 
- 36 since 36 * 36 = 1296 and 1296 can be partitioned into 1 + 29 + 6.
Hence, the punishment number of 37 is 1 + 81 + 100 + 1296 = 1478
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## Solutions

### Solution 1: Enumeration + DFS

We enumerate $i$, where $1 \leq i \leq n$. For each $i$, we split the decimal representation string of $x = i^2$, and then check whether it meets the requirements of the problem. If it does, we add $x$ to the answer.

After the enumeration ends, we return the answer.

The time complexity is $O(n^{1 + 2 \log_{10}^2})$, and the space complexity is $O(\log n)$, where $n$ is the given positive integer.

<!-- tabs:start -->

```python
class Solution:
    def punishmentNumber(self, n: int) -> int:
        def check(s: str, i: int, x: int) -> bool:
            m = len(s)
            if i >= m:
                return x == 0
            y = 0
            for j in range(i, m):
                y = y * 10 + int(s[j])
                if y > x:
                    break
                if check(s, j + 1, x - y):
                    return True
            return False

        ans = 0
        for i in range(1, n + 1):
            x = i * i
            if check(str(x), 0, i):
                ans += x
        return ans
```

```java
class Solution {
    public int punishmentNumber(int n) {
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            int x = i * i;
            if (check(x + "", 0, i)) {
                ans += x;
            }
        }
        return ans;
    }

    private boolean check(String s, int i, int x) {
        int m = s.length();
        if (i >= m) {
            return x == 0;
        }
        int y = 0;
        for (int j = i; j < m; ++j) {
            y = y * 10 + (s.charAt(j) - '0');
            if (y > x) {
                break;
            }
            if (check(s, j + 1, x - y)) {
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
    int punishmentNumber(int n) {
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            int x = i * i;
            string s = to_string(x);
            if (check(s, 0, i)) {
                ans += x;
            }
        }
        return ans;
    }

    bool check(const string& s, int i, int x) {
        int m = s.size();
        if (i >= m) {
            return x == 0;
        }
        int y = 0;
        for (int j = i; j < m; ++j) {
            y = y * 10 + s[j] - '0';
            if (y > x) {
                break;
            }
            if (check(s, j + 1, x - y)) {
                return true;
            }
        }
        return false;
    }
};
```

```go
func punishmentNumber(n int) (ans int) {
	var check func(string, int, int) bool
	check = func(s string, i, x int) bool {
		m := len(s)
		if i >= m {
			return x == 0
		}
		y := 0
		for j := i; j < m; j++ {
			y = y*10 + int(s[j]-'0')
			if y > x {
				break
			}
			if check(s, j+1, x-y) {
				return true
			}
		}
		return false
	}
	for i := 1; i <= n; i++ {
		x := i * i
		s := strconv.Itoa(x)
		if check(s, 0, i) {
			ans += x
		}
	}
	return
}
```

```ts
function punishmentNumber(n: number): number {
    const check = (s: string, i: number, x: number): boolean => {
        const m = s.length;
        if (i >= m) {
            return x === 0;
        }
        let y = 0;
        for (let j = i; j < m; ++j) {
            y = y * 10 + Number(s[j]);
            if (y > x) {
                break;
            }
            if (check(s, j + 1, x - y)) {
                return true;
            }
        }
        return false;
    };
    let ans = 0;
    for (let i = 1; i <= n; ++i) {
        const x = i * i;
        const s = x.toString();
        if (check(s, 0, i)) {
            ans += x;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
