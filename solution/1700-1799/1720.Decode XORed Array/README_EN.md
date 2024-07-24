---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1720.Decode%20XORed%20Array/README_EN.md
rating: 1284
source: Weekly Contest 223 Q1
tags:
    - Bit Manipulation
    - Array
---

<!-- problem:start -->

# [1720. Decode XORed Array](https://leetcode.com/problems/decode-xored-array)

[中文文档](/solution/1700-1799/1720.Decode%20XORed%20Array/README.md)

## Description

<!-- description:start -->

<p>There is a <strong>hidden</strong> integer array <code>arr</code> that consists of <code>n</code> non-negative integers.</p>

<p>It was encoded into another integer array <code>encoded</code> of length <code>n - 1</code>, such that <code>encoded[i] = arr[i] XOR arr[i + 1]</code>. For example, if <code>arr = [1,0,2,1]</code>, then <code>encoded = [1,2,3]</code>.</p>

<p>You are given the <code>encoded</code> array. You are also given an integer <code>first</code>, that is the first element of <code>arr</code>, i.e. <code>arr[0]</code>.</p>

<p>Return <em>the original array</em> <code>arr</code>. It can be proved that the answer exists and is unique.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> encoded = [1,2,3], first = 1
<strong>Output:</strong> [1,0,2,1]
<strong>Explanation:</strong> If arr = [1,0,2,1], then first = 1 and encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> encoded = [6,2,7,3], first = 4
<strong>Output:</strong> [4,2,0,7,4]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>encoded.length == n - 1</code></li>
	<li><code>0 &lt;= encoded[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= first &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bit Manipulation

Based on the problem description, we have:

$$
\textit{encoded}[i] = \textit{arr}[i] \oplus \textit{arr}[i + 1]
$$

If we XOR both sides of the equation with $\textit{arr}[i]$, we get:

$$
\textit{arr}[i] \oplus \textit{arr}[i] \oplus \textit{arr}[i + 1] = \textit{arr}[i] \oplus \textit{encoded}[i]
$$

Which simplifies to:

$$
\textit{arr}[i + 1] = \textit{arr}[i] \oplus \textit{encoded}[i]
$$

Following the derivation above, we can start with $\textit{first}$ and sequentially calculate every element of the array $\textit{arr}$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def decode(self, encoded: List[int], first: int) -> List[int]:
        ans = [first]
        for x in encoded:
            ans.append(ans[-1] ^ x)
        return ans
```

#### Java

```java
class Solution {
    public int[] decode(int[] encoded, int first) {
        int n = encoded.length;
        int[] ans = new int[n + 1];
        ans[0] = first;
        for (int i = 0; i < n; ++i) {
            ans[i + 1] = ans[i] ^ encoded[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> decode(vector<int>& encoded, int first) {
        vector<int> ans = {{first}};
        for (int x : encoded) {
            ans.push_back(ans.back() ^ x);
        }
        return ans;
    }
};
```

#### Go

```go
func decode(encoded []int, first int) []int {
	ans := []int{first}
	for i, x := range encoded {
		ans = append(ans, ans[i]^x)
	}
	return ans
}
```

#### TypeScript

```ts
function decode(encoded: number[], first: number): number[] {
    const ans: number[] = [first];
    for (const x of encoded) {
        ans.push(ans.at(-1)! ^ x);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
