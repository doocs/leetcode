---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0967.Numbers%20With%20Same%20Consecutive%20Differences/README_EN.md
tags:
    - Breadth-First Search
    - Backtracking
---

<!-- problem:start -->

# [967. Numbers With Same Consecutive Differences](https://leetcode.com/problems/numbers-with-same-consecutive-differences)

[中文文档](/solution/0900-0999/0967.Numbers%20With%20Same%20Consecutive%20Differences/README.md)

## Description

<!-- description:start -->

<p>Given two integers n and k, return <em>an array of all the integers of length </em><code>n</code><em> where the difference between every two consecutive digits is </em><code>k</code>. You may return the answer in <strong>any order</strong>.</p>

<p>Note that the integers should not have leading zeros. Integers as <code>02</code> and <code>043</code> are not allowed.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 7
<strong>Output:</strong> [181,292,707,818,929]
<strong>Explanation:</strong> Note that 070 is not a valid number, because it has leading zeroes.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, k = 1
<strong>Output:</strong> [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 9</code></li>
	<li><code>0 &lt;= k &lt;= 9</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

We can enumerate the first digit of all numbers of length $n$, and then use the depth-first search method to recursively construct all numbers that meet the conditions.

Specifically, we first define a boundary value $\textit{boundary} = 10^{n-1}$, which represents the minimum value of the number we need to construct. Then, we enumerate the first digit from $1$ to $9$. For each digit $i$, we recursively construct the number of length $n$ with $i$ as the first digit.

The time complexity is $(n \times 2^n \times |\Sigma|)$, where $|\Sigma|$ represents the set of digits, and in this problem $|\Sigma| = 9$. The space complexity is $O(2^n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numsSameConsecDiff(self, n: int, k: int) -> List[int]:
        def dfs(x: int):
            if x >= boundary:
                ans.append(x)
                return
            last = x % 10
            if last + k <= 9:
                dfs(x * 10 + last + k)
            if last - k >= 0 and k != 0:
                dfs(x * 10 + last - k)

        ans = []
        boundary = 10 ** (n - 1)
        for i in range(1, 10):
            dfs(i)
        return ans
```

#### Java

```java
class Solution {
    private List<Integer> ans = new ArrayList<>();
    private int boundary;
    private int k;

    public int[] numsSameConsecDiff(int n, int k) {
        this.k = k;
        boundary = (int) Math.pow(10, n - 1);
        for (int i = 1; i < 10; ++i) {
            dfs(i);
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }

    private void dfs(int x) {
        if (x >= boundary) {
            ans.add(x);
            return;
        }
        int last = x % 10;
        if (last + k < 10) {
            dfs(x * 10 + last + k);
        }
        if (k != 0 && last - k >= 0) {
            dfs(x * 10 + last - k);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> numsSameConsecDiff(int n, int k) {
        vector<int> ans;
        int boundary = pow(10, n - 1);
        auto dfs = [&](auto&& dfs, int x) {
            if (x >= boundary) {
                ans.push_back(x);
                return;
            }
            int last = x % 10;
            if (last + k < 10) {
                dfs(dfs, x * 10 + last + k);
            }
            if (k != 0 && last - k >= 0) {
                dfs(dfs, x * 10 + last - k);
            }
        };
        for (int i = 1; i < 10; ++i) {
            dfs(dfs, i);
        }
        return ans;
    }
};
```

#### Go

```go
func numsSameConsecDiff(n int, k int) (ans []int) {
	bounary := int(math.Pow10(n - 1))
	var dfs func(int)
	dfs = func(x int) {
		if x >= bounary {
			ans = append(ans, x)
			return
		}
		last := x % 10
		if last+k < 10 {
			dfs(x*10 + last + k)
		}
		if k > 0 && last-k >= 0 {
			dfs(x*10 + last - k)
		}
	}
	for i := 1; i < 10; i++ {
		dfs(i)
	}
	return
}
```

#### TypeScript

```ts
function numsSameConsecDiff(n: number, k: number): number[] {
    const ans: number[] = [];
    const boundary = 10 ** (n - 1);
    const dfs = (x: number) => {
        if (x >= boundary) {
            ans.push(x);
            return;
        }
        const last = x % 10;
        if (last + k < 10) {
            dfs(x * 10 + last + k);
        }
        if (k > 0 && last - k >= 0) {
            dfs(x * 10 + last - k);
        }
    };
    for (let i = 1; i < 10; i++) {
        dfs(i);
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @param {number} k
 * @return {number[]}
 */
var numsSameConsecDiff = function (n, k) {
    const ans = [];
    const boundary = 10 ** (n - 1);
    const dfs = x => {
        if (x >= boundary) {
            ans.push(x);
            return;
        }
        const last = x % 10;
        if (last + k < 10) {
            dfs(x * 10 + last + k);
        }
        if (k > 0 && last - k >= 0) {
            dfs(x * 10 + last - k);
        }
    };
    for (let i = 1; i < 10; i++) {
        dfs(i);
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
