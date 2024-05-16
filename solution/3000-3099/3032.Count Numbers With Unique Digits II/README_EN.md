---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3032.Count%20Numbers%20With%20Unique%20Digits%20II/README_EN.md
tags:
    - Hash Table
    - Math
    - Dynamic Programming
---

<!-- problem:start -->

# [3032. Count Numbers With Unique Digits II 🔒](https://leetcode.com/problems/count-numbers-with-unique-digits-ii)

[中文文档](/solution/3000-3099/3032.Count%20Numbers%20With%20Unique%20Digits%20II/README.md)

## Description

Given two <strong>positive</strong> integers <code>a</code> and <code>b</code>, return <em>the count of numbers having&nbsp;<strong>unique</strong> digits in the range</em> <code>[a, b]</code> <em>(<strong>inclusive</strong>).</em>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = 1, b = 20
<strong>Output:</strong> 19
<strong>Explanation:</strong> All the numbers in the range [1, 20] have unique digits except 11. Hence, the answer is 19.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = 9, b = 19
<strong>Output:</strong> 10
<strong>Explanation:</strong> All the numbers in the range [9, 19] have unique digits except 11. Hence, the answer is 10. 
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> a = 80, b = 120
<strong>Output:</strong> 27
<strong>Explanation:</strong> There are 41 numbers in the range [80, 120], 27 of which have unique digits.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a &lt;= b &lt;= 1000</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: State Compression + Digit DP

The problem asks to count how many numbers in the range $[a, b]$ have unique digits. We can solve this problem using state compression and digit DP.

We can use a function $f(n)$ to count how many numbers in the range $[1, n]$ have unique digits. Then the answer is $f(b) - f(a - 1)$.

In addition, we can use a binary number to record the digits that have appeared in the number. For example, if the digits $1, 3, 5$ have appeared in the number, we can use $10101$ to represent this state.

Next, we use memoization search to implement digit DP. We search from the starting point to the bottom layer to get the number of schemes, return the answer layer by layer and accumulate it, and finally get the final answer from the search starting point.

The basic steps are as follows:

1. We convert the number $n$ into a string $num$, where $num[0]$ is the highest digit and $num[len - 1]$ is the lowest digit.
2. Based on the problem information, we design a function $dfs(pos, mask, limit)$, where $pos$ represents the current processing position, $mask$ represents the digits that have appeared in the current number, and $limit$ represents whether there is a limit at the current position. If $limit$ is true, then the digit at the current position cannot exceed $num[pos]$.

The answer is $dfs(0, 0, true)$.

The time complexity is $O(m \times 2^{10} \times 10)$, and the space complexity is $O(m \times 2^{10})$. Where $m$ is the number of digits in $b$.

<!-- tabs:start -->

```python
class Solution:
    def numberCount(self, a: int, b: int) -> int:
        @cache
        def dfs(pos: int, mask: int, limit: bool) -> int:
            if pos >= len(num):
                return 1 if mask else 0
            up = int(num[pos]) if limit else 9
            ans = 0
            for i in range(up + 1):
                if mask >> i & 1:
                    continue
                nxt = 0 if mask == 0 and i == 0 else mask | 1 << i
                ans += dfs(pos + 1, nxt, limit and i == up)
            return ans

        num = str(a - 1)
        x = dfs(0, 0, True)
        dfs.cache_clear()
        num = str(b)
        y = dfs(0, 0, True)
        return y - x
```

```java
class Solution {
    private String num;
    private Integer[][] f;

    public int numberCount(int a, int b) {
        num = String.valueOf(a - 1);
        f = new Integer[num.length()][1 << 10];
        int x = dfs(0, 0, true);
        num = String.valueOf(b);
        f = new Integer[num.length()][1 << 10];
        int y = dfs(0, 0, true);
        return y - x;
    }

    private int dfs(int pos, int mask, boolean limit) {
        if (pos >= num.length()) {
            return mask > 0 ? 1 : 0;
        }
        if (!limit && f[pos][mask] != null) {
            return f[pos][mask];
        }
        int up = limit ? num.charAt(pos) - '0' : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            if ((mask >> i & 1) == 1) {
                continue;
            }
            int nxt = mask == 0 && i == 0 ? 0 : mask | 1 << i;
            ans += dfs(pos + 1, nxt, limit && i == up);
        }
        if (!limit) {
            f[pos][mask] = ans;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numberCount(int a, int b) {
        string num = to_string(b);
        int f[num.size()][1 << 10];
        memset(f, -1, sizeof(f));
        function<int(int, int, bool)> dfs = [&](int pos, int mask, bool limit) {
            if (pos >= num.size()) {
                return mask ? 1 : 0;
            }
            if (!limit && f[pos][mask] != -1) {
                return f[pos][mask];
            }
            int up = limit ? num[pos] - '0' : 9;
            int ans = 0;
            for (int i = 0; i <= up; ++i) {
                if (mask >> i & 1) {
                    continue;
                }
                int nxt = mask == 0 && i == 0 ? 0 : mask | 1 << i;
                ans += dfs(pos + 1, nxt, limit && i == up);
            }
            if (!limit) {
                f[pos][mask] = ans;
            }
            return ans;
        };

        int y = dfs(0, 0, true);
        num = to_string(a - 1);
        memset(f, -1, sizeof(f));
        int x = dfs(0, 0, true);
        return y - x;
    }
};
```

```go
func numberCount(a int, b int) int {
	num := strconv.Itoa(b)
	f := make([][1 << 10]int, len(num))
	for i := range f {
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(pos, mask int, limit bool) int
	dfs = func(pos, mask int, limit bool) int {
		if pos >= len(num) {
			if mask != 0 {
				return 1
			}
			return 0
		}
		if !limit && f[pos][mask] != -1 {
			return f[pos][mask]
		}
		up := 9
		if limit {
			up = int(num[pos] - '0')
		}
		ans := 0
		for i := 0; i <= up; i++ {
			if mask>>i&1 == 1 {
				continue
			}
			nxt := mask | 1<<i
			if mask == 0 && i == 0 {
				nxt = 0
			}
			ans += dfs(pos+1, nxt, limit && i == up)
		}
		if !limit {
			f[pos][mask] = ans
		}
		return ans
	}
	y := dfs(0, 0, true)
	num = strconv.Itoa(a - 1)
	for i := range f {
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	x := dfs(0, 0, true)
	return y - x
}
```

```ts
function numberCount(a: number, b: number): number {
    let num: string = b.toString();
    const f: number[][] = Array(num.length)
        .fill(0)
        .map(() => Array(1 << 10).fill(-1));
    const dfs: (pos: number, mask: number, limit: boolean) => number = (pos, mask, limit) => {
        if (pos >= num.length) {
            return mask ? 1 : 0;
        }
        if (!limit && f[pos][mask] !== -1) {
            return f[pos][mask];
        }
        const up: number = limit ? +num[pos] : 9;
        let ans: number = 0;
        for (let i = 0; i <= up; i++) {
            if ((mask >> i) & 1) {
                continue;
            }
            let nxt: number = mask | (1 << i);
            if (mask === 0 && i === 0) {
                nxt = 0;
            }
            ans += dfs(pos + 1, nxt, limit && i === up);
        }
        if (!limit) {
            f[pos][mask] = ans;
        }
        return ans;
    };

    const y: number = dfs(0, 0, true);
    num = (a - 1).toString();
    f.forEach(v => v.fill(-1));
    const x: number = dfs(0, 0, true);
    return y - x;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def numberCount(self, a: int, b: int) -> int:
        return sum(len(set(str(num))) == len(str(num)) for num in range(a, b + 1))
```

```java
class Solution {
    public int numberCount(int a, int b) {
        int res = 0;
        for (int i = a; i <= b; ++i) {
            if (isValid(i)) {
                ++res;
            }
        }
        return res;
    }
    private boolean isValid(int n) {
        boolean[] present = new boolean[10];
        Arrays.fill(present, false);
        while (n > 0) {
            int dig = n % 10;
            if (present[dig]) {
                return false;
            }
            present[dig] = true;
            n /= 10;
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    bool isvalid(int n) {
        vector<bool> present(10, false);
        while (n) {
            const int dig = n % 10;
            if (present[dig])
                return false;
            present[dig] = true;
            n /= 10;
        }
        return true;
    }
    int numberCount(int a, int b) {
        int res = 0;
        for (int i = a; i <= b; ++i) {
            if (isvalid(i)) {
                ++res;
            }
        }
        return res;
    }
};
```

```go
func numberCount(a int, b int) int {
	count := 0
	for num := a; num <= b; num++ {
		if hasUniqueDigits(num) {
			count++
		}
	}
	return count
}
func hasUniqueDigits(num int) bool {
	digits := strconv.Itoa(num)
	seen := make(map[rune]bool)
	for _, digit := range digits {
		if seen[digit] {
			return false
		}
		seen[digit] = true
	}
	return true
}
```

```ts
function numberCount(a: number, b: number): number {
    let count: number = 0;
    for (let num = a; num <= b; num++) {
        if (hasUniqueDigits(num)) {
            count++;
        }
    }
    return count;
}
function hasUniqueDigits(num: number): boolean {
    const digits: Set<string> = new Set(num.toString().split(''));
    return digits.size === num.toString().length;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
