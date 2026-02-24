---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3848.Check%20Digitorial%20Permutation/README_EN.md
tags:
    - Math
    - Counting
---

<!-- problem:start -->

# [3848. Check Digitorial Permutation](https://leetcode.com/problems/check-digitorial-permutation)

[中文文档](/solution/3800-3899/3848.Check%20Digitorial%20Permutation/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>.</p>

<p>A number is called <strong>digitorial</strong> if the sum of the <strong>factorials</strong> of its digits is <strong>equal</strong> to the number itself.</p>

<p>Determine whether <strong>any permutation</strong> of <code>n</code> (including the original order) forms a <strong>digitorial</strong> number.</p>

<p>Return <code>true</code> if such a <strong>permutation</strong> exists, otherwise return <code>false</code>.</p>

<p><strong>Note</strong>:</p>

<ul>
	<li>The <strong>factorial</strong> of a non-negative integer <code>x</code>, denoted as <code>x!</code>, is the <strong>product</strong> of all positive integers <strong>less than or equal</strong> to <code>x</code>, and <code>0! = 1</code>.</li>
	<li>A <strong>permutation</strong> is a rearrangement of all the digits of a number that does <strong>not</strong> start with zero. Any arrangement starting with zero is invalid.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 145</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>The number 145 itself is digitorial since <code>1! + 4! + 5! = 1 + 24 + 120 = 145</code>. Thus, the answer is <code>true</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<p>10 is not digitorial since <code>1! + 0! = 2</code> is not equal to 10, and the permutation <code>&quot;01&quot;</code> is invalid because it starts with zero.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

According to the problem description, no matter how the digits of number $n$ are rearranged, the sum of factorials of the digitorial number remains unchanged. Therefore, we only need to calculate the sum of factorials of each digit of number $n$, and check whether the permutation of digits of this sum equals the permutation of digits of $n$.

The time complexity is $O(\log n)$, where $n$ is the integer given in the problem. The space complexity is $O(d)$, where $d = 10$ is the length of the factorial preprocessing array.

<!-- tabs:start -->

#### Python3

```python
@cache
def f(x: int) -> int:
    if x < 2:
        return 1
    return x * f(x - 1)

class Solution:
    def isDigitorialPermutation(self, n: int) -> bool:
        x, y = 0, n
        while y:
            x += f(y % 10)
            y //= 10
        return sorted(str(x)) == sorted(str(n))
```

#### Java

```java
class Solution {
    private static final int[] f = new int[10];

    static {
        f[0] = 1;
        for (int i = 1; i < 10; i++) {
            f[i] = f[i - 1] * i;
        }
    }

    public boolean isDigitorialPermutation(int n) {
        int x = 0;
        int y = n;

        while (y > 0) {
            x += f[y % 10];
            y /= 10;
        }

        char[] a = String.valueOf(x).toCharArray();
        char[] b = String.valueOf(n).toCharArray();

        Arrays.sort(a);
        Arrays.sort(b);

        return Arrays.equals(a, b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isDigitorialPermutation(int n) {
        static int f[10];
        static bool initialized = false;

        if (!initialized) {
            f[0] = 1;
            for (int i = 1; i < 10; i++) {
                f[i] = f[i - 1] * i;
            }
            initialized = true;
        }

        int x = 0;
        int y = n;

        while (y > 0) {
            x += f[y % 10];
            y /= 10;
        }

        string a = to_string(x);
        string b = to_string(n);

        sort(a.begin(), a.end());
        sort(b.begin(), b.end());

        return a == b;
    }
};
```

#### Go

```go
func isDigitorialPermutation(n int) bool {
	f := make([]int, 10)
	f[0] = 1
	for i := 1; i < 10; i++ {
		f[i] = f[i-1] * i
	}

	x := 0
	y := n

	for y > 0 {
		x += f[y%10]
		y /= 10
	}

	a := []byte(strconv.Itoa(x))
	b := []byte(strconv.Itoa(n))

	sort.Slice(a, func(i, j int) bool { return a[i] < a[j] })
	sort.Slice(b, func(i, j int) bool { return b[i] < b[j] })

	return string(a) == string(b)
}
```

#### TypeScript

```ts
function isDigitorialPermutation(n: number): boolean {
    const f: number[] = new Array(10);
    f[0] = 1;
    for (let i = 1; i < 10; i++) {
        f[i] = f[i - 1] * i;
    }

    let x = 0;
    let y = n;

    while (y > 0) {
        x += f[y % 10];
        y = Math.floor(y / 10);
    }

    const a = x.toString().split('').sort().join('');
    const b = n.toString().split('').sort().join('');

    return a === b;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
