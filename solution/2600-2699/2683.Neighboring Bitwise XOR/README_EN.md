---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2683.Neighboring%20Bitwise%20XOR/README_EN.md
rating: 1517
source: Weekly Contest 345 Q2
tags:
    - Bit Manipulation
    - Array
---

<!-- problem:start -->

# [2683. Neighboring Bitwise XOR](https://leetcode.com/problems/neighboring-bitwise-xor)

[中文文档](/solution/2600-2699/2683.Neighboring%20Bitwise%20XOR/README.md)

## Description

<!-- description:start -->

<p>A <strong>0-indexed</strong> array <code>derived</code> with length <code>n</code> is derived by computing the <strong>bitwise XOR</strong>&nbsp;(&oplus;) of adjacent values in a <strong>binary array</strong> <code>original</code> of length <code>n</code>.</p>

<p>Specifically, for each index <code>i</code> in the range <code>[0, n - 1]</code>:</p>

<ul>
	<li>If <code>i = n - 1</code>, then <code>derived[i] = original[i] &oplus; original[0]</code>.</li>
	<li>Otherwise, <code>derived[i] = original[i] &oplus; original[i + 1]</code>.</li>
</ul>

<p>Given an array <code>derived</code>, your task is to determine whether there exists a <strong>valid binary array</strong> <code>original</code> that could have formed <code>derived</code>.</p>

<p>Return <em><strong>true</strong> if such an array exists or <strong>false</strong> otherwise.</em></p>

<ul>
	<li>A binary array is an array containing only <strong>0&#39;s</strong> and <strong>1&#39;s</strong></li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> derived = [1,1,0]
<strong>Output:</strong> true
<strong>Explanation:</strong> A valid original array that gives derived is [0,1,0].
derived[0] = original[0] &oplus; original[1] = 0 &oplus; 1 = 1 
derived[1] = original[1] &oplus; original[2] = 1 &oplus; 0 = 1
derived[2] = original[2] &oplus; original[0] = 0 &oplus; 0 = 0
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> derived = [1,1]
<strong>Output:</strong> true
<strong>Explanation:</strong> A valid original array that gives derived is [0,1].
derived[0] = original[0] &oplus; original[1] = 1
derived[1] = original[1] &oplus; original[0] = 1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> derived = [1,0]
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no valid original array that gives derived.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == derived.length</code></li>
	<li><code>1 &lt;= n&nbsp;&lt;= 10<sup>5</sup></code></li>
	<li>The values in <code>derived</code>&nbsp;are either <strong>0&#39;s</strong> or <strong>1&#39;s</strong></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bit Manipulation

Let's assume the original binary array is $a$, and the derived array is $b$. Then, we have:

$$
b_0 = a_0 \oplus a_1 \\
b_1 = a_1 \oplus a_2 \\
\cdots \\
b_{n-1} = a_{n-1} \oplus a_0
$$

Since the XOR operation is commutative and associative, we get:

$$
b_0 \oplus b_1 \oplus \cdots \oplus b_{n-1} = (a_0 \oplus a_1) \oplus (a_1 \oplus a_2) \oplus \cdots \oplus (a_{n-1} \oplus a_0) = 0
$$

Therefore, as long as the XOR sum of all elements in the derived array is $0$, there must exist an original binary array that meets the requirements.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def doesValidArrayExist(self, derived: List[int]) -> bool:
        return reduce(xor, derived) == 0
```

#### Java

```java
class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        int s = 0;
        for (int x : derived) {
            s ^= x;
        }
        return s == 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool doesValidArrayExist(vector<int>& derived) {
        int s = 0;
        for (int x : derived) {
            s ^= x;
        }
        return s == 0;
    }
};
```

#### Go

```go
func doesValidArrayExist(derived []int) bool {
	s := 0
	for _, x := range derived {
		s ^= x
	}
	return s == 0
}
```

#### TypeScript

```ts
function doesValidArrayExist(derived: number[]): boolean {
    return derived.reduce((acc, x) => acc ^ x) === 0;
}
```

#### Rust

```rust
impl Solution {
    pub fn does_valid_array_exist(derived: Vec<i32>) -> bool {
        derived.iter().fold(0, |acc, &x| acc ^ x) == 0
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} derived
 * @return {boolean}
 */
var doesValidArrayExist = function (derived) {
    return derived.reduce((acc, x) => acc ^ x) === 0;
};
```

#### C#

```cs
public class Solution {
    public bool DoesValidArrayExist(int[] derived) {
        return derived.Aggregate(0, (acc, x) => acc ^ x) == 0;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
