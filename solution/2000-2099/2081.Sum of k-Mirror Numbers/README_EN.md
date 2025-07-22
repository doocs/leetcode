---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2081.Sum%20of%20k-Mirror%20Numbers/README_EN.md
rating: 2209
source: Weekly Contest 268 Q4
tags:
    - Math
    - Enumeration
---

<!-- problem:start -->

# [2081. Sum of k-Mirror Numbers](https://leetcode.com/problems/sum-of-k-mirror-numbers)

[中文文档](/solution/2000-2099/2081.Sum%20of%20k-Mirror%20Numbers/README.md)

## Description

<!-- description:start -->

<p>A <strong>k-mirror number</strong> is a <strong>positive</strong> integer <strong>without leading zeros</strong> that reads the same both forward and backward in base-10 <strong>as well as</strong> in base-k.</p>

<ul>
	<li>For example, <code>9</code> is a 2-mirror number. The representation of <code>9</code> in base-10 and base-2 are <code>9</code> and <code>1001</code> respectively, which read the same both forward and backward.</li>
	<li>On the contrary, <code>4</code> is not a 2-mirror number. The representation of <code>4</code> in base-2 is <code>100</code>, which does not read the same both forward and backward.</li>
</ul>

<p>Given the base <code>k</code> and the number <code>n</code>, return <em>the <strong>sum</strong> of the</em> <code>n</code> <em><strong>smallest</strong> k-mirror numbers</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> k = 2, n = 5
<strong>Output:</strong> 25
<strong>Explanation:
</strong>The 5 smallest 2-mirror numbers and their representations in base-2 are listed as follows:
  base-10    base-2
    1          1
    3          11
    5          101
    7          111
    9          1001
Their sum = 1 + 3 + 5 + 7 + 9 = 25. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> k = 3, n = 7
<strong>Output:</strong> 499
<strong>Explanation:
</strong>The 7 smallest 3-mirror numbers are and their representations in base-3 are listed as follows:
  base-10    base-3
    1          1
    2          2
    4          11
    8          22
    121        11111
    151        12121
    212        21212
Their sum = 1 + 2 + 4 + 8 + 121 + 151 + 212 = 499.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> k = 7, n = 17
<strong>Output:</strong> 20379000
<strong>Explanation:</strong> The 17 smallest 7-mirror numbers are:
1, 2, 3, 4, 5, 6, 8, 121, 171, 242, 292, 16561, 65656, 2137312, 4602064, 6597956, 6958596
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= k &lt;= 9</code></li>
	<li><code>1 &lt;= n &lt;= 30</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Half Enumeration + Mathematics

For a k-mirror number, we can divide it into two parts: the first half and the second half. For numbers with even length, the first and second halves are exactly the same; for numbers with odd length, the first and second halves are the same, but the middle digit can be any digit.

We can enumerate the numbers in the first half, and then construct the complete k-mirror number based on the first half. The specific steps are as follows:

1. **Enumerate Lengths**: Start enumerating the length of the numbers from 1, until we find enough k-mirror numbers that meet the requirements.
2. **Calculate the Range of the First Half**: For a number of length $l$, the range of the first half is $[10^{(l-1)/2}, 10^{(l+1)/2})$.
3. **Construct k-Mirror Numbers**: For each number $i$ in the first half, if the length is even, use $i$ directly as the first half; if the length is odd, divide $i$ by 10 to get the first half. Then reverse the digits of the first half and append them to form the complete k-mirror number.
4. **Check k-Mirror Numbers**: Convert the constructed number to base $k$ and check whether it is a palindrome.
5. **Accumulate the Result**: If it is a k-mirror number, add it to the result and decrease the counter $n$. When $n$ reaches 0, return the result.

The time complexity mainly depends on the length being enumerated and the range of the first half. Since the maximum value of $n$ is 30, the number of enumerations is limited in practice. The space complexity is $O(1)$, since only a constant amount of extra space is

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kMirror(self, k: int, n: int) -> int:
        def check(x: int, k: int) -> bool:
            s = []
            while x:
                s.append(x % k)
                x //= k
            return s == s[::-1]

        ans = 0
        for l in count(1):
            x = 10 ** ((l - 1) // 2)
            y = 10 ** ((l + 1) // 2)
            for i in range(x, y):
                v = i
                j = i if l % 2 == 0 else i // 10
                while j > 0:
                    v = v * 10 + j % 10
                    j //= 10
                if check(v, k):
                    ans += v
                    n -= 1
                    if n == 0:
                        return ans
```

#### Java

```java
class Solution {
    public long kMirror(int k, int n) {
        long ans = 0;
        for (int l = 1;; l++) {
            int x = (int) Math.pow(10, (l - 1) / 2);
            int y = (int) Math.pow(10, (l + 1) / 2);
            for (int i = x; i < y; i++) {
                long v = i;
                int j = (l % 2 == 0) ? i : i / 10;
                while (j > 0) {
                    v = v * 10 + j % 10;
                    j /= 10;
                }
                if (check(v, k)) {
                    ans += v;
                    n--;
                    if (n == 0) {
                        return ans;
                    }
                }
            }
        }
    }

    private boolean check(long x, int k) {
        List<Integer> s = new ArrayList<>();
        while (x > 0) {
            s.add((int) (x % k));
            x /= k;
        }
        for (int i = 0, j = s.size() - 1; i < j; ++i, --j) {
            if (!s.get(i).equals(s.get(j))) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long kMirror(int k, int n) {
        long long ans = 0;
        for (int l = 1;; ++l) {
            int x = pow(10, (l - 1) / 2);
            int y = pow(10, (l + 1) / 2);
            for (int i = x; i < y; ++i) {
                long long v = i;
                int j = (l % 2 == 0) ? i : i / 10;
                while (j > 0) {
                    v = v * 10 + j % 10;
                    j /= 10;
                }
                if (check(v, k)) {
                    ans += v;
                    if (--n == 0) {
                        return ans;
                    }
                }
            }
        }
    }

private:
    bool check(long long x, int k) {
        vector<int> s;
        while (x > 0) {
            s.push_back(x % k);
            x /= k;
        }
        for (int i = 0, j = s.size() - 1; i < j; ++i, --j) {
            if (s[i] != s[j]) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func kMirror(k int, n int) int64 {
	check := func(x int64, k int) bool {
		s := []int{}
		for x > 0 {
			s = append(s, int(x%int64(k)))
			x /= int64(k)
		}
		for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
			if s[i] != s[j] {
				return false
			}
		}
		return true
	}

	var ans int64 = 0
	for l := 1; ; l++ {
		x := pow10((l - 1) / 2)
		y := pow10((l + 1) / 2)
		for i := x; i < y; i++ {
			v := int64(i)
			j := i
			if l%2 != 0 {
				j = i / 10
			}
			for j > 0 {
				v = v*10 + int64(j%10)
				j /= 10
			}
			if check(v, k) {
				ans += v
				n--
				if n == 0 {
					return ans
				}
			}
		}
	}
}

func pow10(exp int) int {
	res := 1
	for i := 0; i < exp; i++ {
		res *= 10
	}
	return res
}
```

#### TypeScript

```ts
function kMirror(k: number, n: number): number {
    function check(x: number, k: number): boolean {
        const s: number[] = [];
        while (x > 0) {
            s.push(x % k);
            x = Math.floor(x / k);
        }
        for (let i = 0, j = s.length - 1; i < j; i++, j--) {
            if (s[i] !== s[j]) {
                return false;
            }
        }
        return true;
    }

    let ans = 0;
    for (let l = 1; ; l++) {
        const x = Math.pow(10, Math.floor((l - 1) / 2));
        const y = Math.pow(10, Math.floor((l + 1) / 2));
        for (let i = x; i < y; i++) {
            let v = i;
            let j = l % 2 === 0 ? i : Math.floor(i / 10);
            while (j > 0) {
                v = v * 10 + (j % 10);
                j = Math.floor(j / 10);
            }
            if (check(v, k)) {
                ans += v;
                n--;
                if (n === 0) {
                    return ans;
                }
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
