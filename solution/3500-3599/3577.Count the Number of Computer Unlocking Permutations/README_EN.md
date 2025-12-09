---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3577.Count%20the%20Number%20of%20Computer%20Unlocking%20Permutations/README_EN.md
rating: 1749
source: Weekly Contest 453 Q2
tags:
    - Brainteaser
    - Array
    - Math
    - Combinatorics
---

<!-- problem:start -->

# [3577. Count the Number of Computer Unlocking Permutations](https://leetcode.com/problems/count-the-number-of-computer-unlocking-permutations)

[中文文档](/solution/3500-3599/3577.Count%20the%20Number%20of%20Computer%20Unlocking%20Permutations/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>complexity</code> of length <code>n</code>.</p>

<p>There are <code>n</code> <strong>locked</strong> computers in a room with labels from 0 to <code>n - 1</code>, each with its own <strong>unique</strong> password. The password of the computer <code>i</code> has a complexity <code>complexity[i]</code>.</p>

<p>The password for the computer labeled 0 is <strong>already</strong> decrypted and serves as the root. All other computers must be unlocked using it or another previously unlocked computer, following this information:</p>

<ul>
	<li>You can decrypt the password for the computer <code>i</code> using the password for computer <code>j</code>, where <code>j</code> is <strong>any</strong> integer less than <code>i</code> with a lower complexity. (i.e. <code>j &lt; i</code> and <code>complexity[j] &lt; complexity[i]</code>)</li>
	<li>To decrypt the password for computer <code>i</code>, you must have already unlocked a computer <code>j</code> such that <code>j &lt; i</code> and <code>complexity[j] &lt; complexity[i]</code>.</li>
</ul>

<p>Find the number of <span data-keyword="permutation-array">permutations</span> of <code>[0, 1, 2, ..., (n - 1)]</code> that represent a valid order in which the computers can be unlocked, starting from computer 0 as the only initially unlocked one.</p>

<p>Since the answer may be large, return it <strong>modulo</strong> 10<sup>9</sup> + 7.</p>

<p><strong>Note</strong> that the password for the computer <strong>with label</strong> 0 is decrypted, and <em>not</em> the computer with the first position in the permutation.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">complexity = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The valid permutations are:</p>

<ul>
	<li>[0, 1, 2]
	<ul>
		<li>Unlock computer 0 first with root password.</li>
		<li>Unlock computer 1 with password of computer 0 since <code>complexity[0] &lt; complexity[1]</code>.</li>
		<li>Unlock computer 2 with password of computer 1 since <code>complexity[1] &lt; complexity[2]</code>.</li>
	</ul>
	</li>
	<li>[0, 2, 1]
	<ul>
		<li>Unlock computer 0 first with root password.</li>
		<li>Unlock computer 2 with password of computer 0 since <code>complexity[0] &lt; complexity[2]</code>.</li>
		<li>Unlock computer 1 with password of computer 0 since <code>complexity[0] &lt; complexity[1]</code>.</li>
	</ul>
	</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">complexity = [3,3,3,4,4,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no possible permutations which can unlock all computers.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= complexity.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= complexity[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Brain Teaser

Since the password for computer number $0$ is already unlocked, for any other computer $i$, if $\text{complexity}[i] \leq \text{complexity}[0]$, it is impossible to unlock computer $i$, so we return $0$. Otherwise, any permutation is valid, and there are exactly $(n - 1)!$ possible permutations.

The time complexity is $O(n)$, where $n$ is the length of the $\text{complexity}$ array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPermutations(self, complexity: List[int]) -> int:
        mod = 10**9 + 7
        ans = 1
        for i in range(1, len(complexity)):
            if complexity[i] <= complexity[0]:
                return 0
            ans = ans * i % mod
        return ans
```

#### Java

```java
class Solution {
    public int countPermutations(int[] complexity) {
        final int mod = (int) 1e9 + 7;
        long ans = 1;
        for (int i = 1; i < complexity.length; ++i) {
            if (complexity[i] <= complexity[0]) {
                return 0;
            }
            ans = ans * i % mod;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countPermutations(vector<int>& complexity) {
        const int mod = 1e9 + 7;
        long long ans = 1;
        for (int i = 1; i < complexity.size(); ++i) {
            if (complexity[i] <= complexity[0]) {
                return 0;
            }
            ans = ans * i % mod;
        }
        return ans;
    }
};
```

#### Go

```go
func countPermutations(complexity []int) int {
	mod := int64(1e9 + 7)
	ans := int64(1)
	for i := 1; i < len(complexity); i++ {
		if complexity[i] <= complexity[0] {
			return 0
		}
		ans = ans * int64(i) % mod
	}
	return int(ans)
}
```

#### TypeScript

```ts
function countPermutations(complexity: number[]): number {
    const mod = 1e9 + 7;
    let ans = 1;
    for (let i = 1; i < complexity.length; i++) {
        if (complexity[i] <= complexity[0]) {
            return 0;
        }
        ans = (ans * i) % mod;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_permutations(complexity: Vec<i32>) -> i32 {
        const MOD: i64 = 1_000_000_007;
        let mut ans = 1i64;
        for i in 1..complexity.len() {
            if complexity[i] <= complexity[0] {
                return 0;
            }
            ans = ans * i as i64 % MOD;
        }
        ans as i32
    }
}
```

#### C#

```cs
public class Solution {
    public int CountPermutations(int[] complexity) {
        const int mod = (int) 1e9 + 7;
        long ans = 1;
        for (int i = 1; i < complexity.Length; ++i) {
            if (complexity[i] <= complexity[0]) {
                return 0;
            }
            ans = ans * i % mod;
        }
        return (int) ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
