---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2961.Double%20Modular%20Exponentiation/README_EN.md
rating: 1450
source: Weekly Contest 375 Q2
tags:
    - Array
    - Math
    - Simulation
---

<!-- problem:start -->

# [2961. Double Modular Exponentiation](https://leetcode.com/problems/double-modular-exponentiation)

[中文文档](/solution/2900-2999/2961.Double%20Modular%20Exponentiation/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> 2D array <code>variables</code> where <code>variables[i] = [a<sub>i</sub>, b<sub>i</sub>, c<sub>i,</sub> m<sub>i</sub>]</code>, and an integer <code>target</code>.</p>

<p>An index <code>i</code> is <strong>good</strong> if the following formula holds:</p>

<ul>
	<li><code>0 &lt;= i &lt; variables.length</code></li>
	<li><code>((a<sub>i</sub><sup>b<sub>i</sub></sup> % 10)<sup>c<sub>i</sub></sup>) % m<sub>i</sub> == target</code></li>
</ul>

<p>Return <em>an array consisting of <strong>good</strong> indices in <strong>any order</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> variables = [[2,3,3,10],[3,3,3,1],[6,1,1,4]], target = 2
<strong>Output:</strong> [0,2]
<strong>Explanation:</strong> For each index i in the variables array:
1) For the index 0, variables[0] = [2,3,3,10], (2<sup>3</sup> % 10)<sup>3</sup> % 10 = 2.
2) For the index 1, variables[1] = [3,3,3,1], (3<sup>3</sup> % 10)<sup>3</sup> % 1 = 0.
3) For the index 2, variables[2] = [6,1,1,4], (6<sup>1</sup> % 10)<sup>1</sup> % 4 = 2.
Therefore we return [0,2] as the answer.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> variables = [[39,3,1000,1000]], target = 17
<strong>Output:</strong> []
<strong>Explanation:</strong> For each index i in the variables array:
1) For the index 0, variables[0] = [39,3,1000,1000], (39<sup>3</sup> % 10)<sup>1000</sup> % 1000 = 1.
Therefore we return [] as the answer.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= variables.length &lt;= 100</code></li>
	<li><code>variables[i] == [a<sub>i</sub>, b<sub>i</sub>, c<sub>i</sub>, m<sub>i</sub>]</code></li>
	<li><code>1 &lt;= a<sub>i</sub>, b<sub>i</sub>, c<sub>i</sub>, m<sub>i</sub> &lt;= 10<sup>3</sup></code></li>
	<li><code><font face="monospace">0 &lt;= target &lt;= 10<sup>3</sup></font></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation + Fast Power

We can directly simulate according to the problem description. For the power operation modulo, we can use the fast power method to speed up the calculation.

The time complexity is $O(n \times \log M)$, where $n$ is the length of the array $variables$; and $M$ is the maximum value in $b_i$ and $c_i$, in this problem $M \le 10^3$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getGoodIndices(self, variables: List[List[int]], target: int) -> List[int]:
        return [
            i
            for i, (a, b, c, m) in enumerate(variables)
            if pow(pow(a, b, 10), c, m) == target
        ]
```

#### Java

```java
class Solution {
    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < variables.length; ++i) {
            var e = variables[i];
            int a = e[0], b = e[1], c = e[2], m = e[3];
            if (qpow(qpow(a, b, 10), c, m) == target) {
                ans.add(i);
            }
        }
        return ans;
    }

    private int qpow(long a, int n, int mod) {
        long ans = 1;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans = ans * a % mod;
            }
            a = a * a % mod;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> getGoodIndices(vector<vector<int>>& variables, int target) {
        vector<int> ans;
        auto qpow = [&](long long a, int n, int mod) {
            long long ans = 1;
            for (; n; n >>= 1) {
                if (n & 1) {
                    ans = ans * a % mod;
                }
                a = a * a % mod;
            }
            return (int) ans;
        };
        for (int i = 0; i < variables.size(); ++i) {
            auto e = variables[i];
            int a = e[0], b = e[1], c = e[2], m = e[3];
            if (qpow(qpow(a, b, 10), c, m) == target) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func getGoodIndices(variables [][]int, target int) (ans []int) {
	qpow := func(a, n, mod int) int {
		ans := 1
		for ; n > 0; n >>= 1 {
			if n&1 == 1 {
				ans = ans * a % mod
			}
			a = a * a % mod
		}
		return ans
	}
	for i, e := range variables {
		a, b, c, m := e[0], e[1], e[2], e[3]
		if qpow(qpow(a, b, 10), c, m) == target {
			ans = append(ans, i)
		}
	}
	return
}
```

#### TypeScript

```ts
function getGoodIndices(variables: number[][], target: number): number[] {
    const qpow = (a: number, n: number, mod: number) => {
        let ans = 1;
        for (; n; n >>= 1) {
            if (n & 1) {
                ans = Number((BigInt(ans) * BigInt(a)) % BigInt(mod));
            }
            a = Number((BigInt(a) * BigInt(a)) % BigInt(mod));
        }
        return ans;
    };
    const ans: number[] = [];
    for (let i = 0; i < variables.length; ++i) {
        const [a, b, c, m] = variables[i];
        if (qpow(qpow(a, b, 10), c, m) === target) {
            ans.push(i);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
