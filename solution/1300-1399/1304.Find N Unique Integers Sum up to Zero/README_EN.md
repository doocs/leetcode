---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1304.Find%20N%20Unique%20Integers%20Sum%20up%20to%20Zero/README_EN.md
rating: 1167
source: Weekly Contest 169 Q1
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [1304. Find N Unique Integers Sum up to Zero](https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero)

[中文文档](/solution/1300-1399/1304.Find%20N%20Unique%20Integers%20Sum%20up%20to%20Zero/README.md)

## Description

<!-- description:start -->

<p>Given an integer <code>n</code>, return <strong>any</strong> array containing <code>n</code> <strong>unique</strong> integers such that they add up to <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> [-7,-1,1,3,4]
<strong>Explanation:</strong> These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> [-1,0,1]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> [0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Construction

We can start from $1$ and alternately add positive and negative numbers to the result array. We repeat this process $\frac{n}{2}$ times. If $n$ is odd, we add $0$ to the result array at the end.

The time complexity is $O(n)$, where $n$ is the given integer. Ignoring the space used for the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumZero(self, n: int) -> List[int]:
        ans = []
        for i in range(n >> 1):
            ans.append(i + 1)
            ans.append(-(i + 1))
        if n & 1:
            ans.append(0)
        return ans
```

#### Java

```java
class Solution {
    public int[] sumZero(int n) {
        int[] ans = new int[n];
        for (int i = 1, j = 0; i <= n / 2; ++i) {
            ans[j++] = i;
            ans[j++] = -i;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> sumZero(int n) {
        vector<int> ans(n);
        for (int i = 1, j = 0; i <= n / 2; ++i) {
            ans[j++] = i;
            ans[j++] = -i;
        }
        return ans;
    }
};
```

#### Go

```go
func sumZero(n int) []int {
	ans := make([]int, n)
	for i, j := 1, 0; i <= n/2; i, j = i+1, j+1 {
		ans[j] = i
		j++
		ans[j] = -i
	}
	return ans
}
```

#### TypeScript

```ts
function sumZero(n: number): number[] {
    const ans: number[] = Array(n).fill(0);
    for (let i = 1, j = 0; i <= n / 2; ++i) {
        ans[j++] = i;
        ans[j++] = -i;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn sum_zero(n: i32) -> Vec<i32> {
        let mut ans = vec![0; n as usize];
        let mut j = 0;
        for i in 1..=n / 2 {
            ans[j] = i;
            j += 1;
            ans[j] = -i;
            j += 1;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Construction + Mathematics

We can also add all integers from $1$ to $n-1$ to the result array, and finally add the opposite of the sum of the first $n-1$ integers, which is $-\frac{n(n-1)}{2}$, to the result array.

The time complexity is $O(n)$, where $n$ is the given integer. Ignoring the space used for the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumZero(self, n: int) -> List[int]:
        ans = list(range(1, n))
        ans.append(-sum(ans))
        return ans
```

#### Java

```java
class Solution {
    public int[] sumZero(int n) {
        int[] ans = new int[n];
        for (int i = 1; i < n; ++i) {
            ans[i] = i;
        }
        ans[0] = -(n * (n - 1) / 2);
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> sumZero(int n) {
        vector<int> ans(n);
        iota(ans.begin(), ans.end(), 1);
        ans[n - 1] = -(n - 1) * n / 2;
        return ans;
    }
};
```

#### Go

```go
func sumZero(n int) []int {
	ans := make([]int, n)
	for i := 1; i < n; i++ {
		ans[i] = i
	}
	ans[0] = -n * (n - 1) / 2
	return ans
}
```

#### TypeScript

```ts
function sumZero(n: number): number[] {
    const ans = new Array(n).fill(0);
    for (let i = 1; i < n; ++i) {
        ans[i] = i;
    }
    ans[0] = -((n * (n - 1)) / 2);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn sum_zero(n: i32) -> Vec<i32> {
        let mut ans = vec![0; n as usize];
        for i in 1..n {
            ans[i as usize] = i;
        }
        ans[0] = -(n * (n - 1) / 2);
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
