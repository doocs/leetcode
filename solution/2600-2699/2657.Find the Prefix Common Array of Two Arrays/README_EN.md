---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2657.Find%20the%20Prefix%20Common%20Array%20of%20Two%20Arrays/README_EN.md
rating: 1304
tags:
    - Bit Manipulation
    - Array
    - Hash Table
---

# [2657. Find the Prefix Common Array of Two Arrays](https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays)

[中文文档](/solution/2600-2699/2657.Find%20the%20Prefix%20Common%20Array%20of%20Two%20Arrays/README.md)

## Description

<p>You are given two <strong>0-indexed </strong>integer<strong> </strong>permutations <code>A</code> and <code>B</code> of length <code>n</code>.</p>

<p>A <strong>prefix common array</strong> of <code>A</code> and <code>B</code> is an array <code>C</code> such that <code>C[i]</code> is equal to the count of numbers that are present at or before the index <code>i</code> in both <code>A</code> and <code>B</code>.</p>

<p>Return <em>the <strong>prefix common array</strong> of </em><code>A</code><em> and </em><code>B</code>.</p>

<p>A sequence of <code>n</code> integers is called a&nbsp;<strong>permutation</strong> if it contains all integers from <code>1</code> to <code>n</code> exactly once.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> A = [1,3,2,4], B = [3,1,2,4]
<strong>Output:</strong> [0,2,3,4]
<strong>Explanation:</strong> At i = 0: no number is common, so C[0] = 0.
At i = 1: 1 and 3 are common in A and B, so C[1] = 2.
At i = 2: 1, 2, and 3 are common in A and B, so C[2] = 3.
At i = 3: 1, 2, 3, and 4 are common in A and B, so C[3] = 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> A = [2,3,1], B = [3,1,2]
<strong>Output:</strong> [0,1,3]
<strong>Explanation:</strong> At i = 0: no number is common, so C[0] = 0.
At i = 1: only 3 is common in A and B, so C[1] = 1.
At i = 2: 1, 2, and 3 are common in A and B, so C[2] = 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= A.length == B.length == n &lt;= 50</code></li>
	<li><code>1 &lt;= A[i], B[i] &lt;= n</code></li>
	<li><code>It is guaranteed that A and B are both a permutation of n integers.</code></li>
</ul>

## Solutions

### Solution 1: Counting

We can use two arrays $cnt1$ and $cnt2$ to record the occurrence times of each element in arrays $A$ and $B$ respectively, and use an array $ans$ to record the answer.

Traverse arrays $A$ and $B$, increment the occurrence times of $A[i]$ in $cnt1$, and increment the occurrence times of $B[i]$ in $cnt2$. Then enumerate $j \in [1,n]$, calculate the minimum occurrence times of each element $j$ in $cnt1$ and $cnt2$, and accumulate them into $ans[i]$.

After the traversal, return the answer array $ans$.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Here, $n$ is the length of arrays $A$ and $B$.

<!-- tabs:start -->

```python
class Solution:
    def findThePrefixCommonArray(self, A: List[int], B: List[int]) -> List[int]:
        ans = []
        cnt1 = Counter()
        cnt2 = Counter()
        for a, b in zip(A, B):
            cnt1[a] += 1
            cnt2[b] += 1
            t = sum(min(v, cnt2[x]) for x, v in cnt1.items())
            ans.append(t)
        return ans
```

```java
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] ans = new int[n];
        int[] cnt1 = new int[n + 1];
        int[] cnt2 = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            ++cnt1[A[i]];
            ++cnt2[B[i]];
            for (int j = 1; j <= n; ++j) {
                ans[i] += Math.min(cnt1[j], cnt2[j]);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> findThePrefixCommonArray(vector<int>& A, vector<int>& B) {
        int n = A.size();
        vector<int> ans(n);
        vector<int> cnt1(n + 1), cnt2(n + 1);
        for (int i = 0; i < n; ++i) {
            ++cnt1[A[i]];
            ++cnt2[B[i]];
            for (int j = 1; j <= n; ++j) {
                ans[i] += min(cnt1[j], cnt2[j]);
            }
        }
        return ans;
    }
};
```

```go
func findThePrefixCommonArray(A []int, B []int) []int {
	n := len(A)
	cnt1 := make([]int, n+1)
	cnt2 := make([]int, n+1)
	ans := make([]int, n)
	for i, a := range A {
		b := B[i]
		cnt1[a]++
		cnt2[b]++
		for j := 1; j <= n; j++ {
			ans[i] += min(cnt1[j], cnt2[j])
		}
	}
	return ans
}
```

```ts
function findThePrefixCommonArray(A: number[], B: number[]): number[] {
    const n = A.length;
    const cnt1: number[] = Array(n + 1).fill(0);
    const cnt2: number[] = Array(n + 1).fill(0);
    const ans: number[] = Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        ++cnt1[A[i]];
        ++cnt2[B[i]];
        for (let j = 1; j <= n; ++j) {
            ans[i] += Math.min(cnt1[j], cnt2[j]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

### Solution 2: Bit Operation (XOR Operation)

We can use an array $vis$ of length $n+1$ to record the occurrence situation of each element in arrays $A$ and $B$, the initial value of array $vis$ is $1$. In addition, we use a variable $s$ to record the current number of common elements.

Next, we traverse arrays $A$ and $B$, update $vis[A[i]] = vis[A[i]] \oplus 1$, and update $vis[B[i]] = vis[B[i]] \oplus 1$, where $\oplus$ represents XOR operation.

If at the current position, the element $A[i]$ has appeared twice (i.e., it has appeared in both arrays $A$ and $B$), then the value of $vis[A[i]]$ will be $1$, and we increment $s$. Similarly, if the element $B[i]$ has appeared twice, then the value of $vis[B[i]]$ will be $1$, and we increment $s$. Then add the value of $s$ to the answer array $ans$.

After the traversal, return the answer array $ans$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of arrays $A$ and $B$.

<!-- tabs:start -->

```python
class Solution:
    def findThePrefixCommonArray(self, A: List[int], B: List[int]) -> List[int]:
        ans = []
        vis = [1] * (len(A) + 1)
        s = 0
        for a, b in zip(A, B):
            vis[a] ^= 1
            s += vis[a]
            vis[b] ^= 1
            s += vis[b]
            ans.append(s)
        return ans
```

```java
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] ans = new int[n];
        int[] vis = new int[n + 1];
        Arrays.fill(vis, 1);
        int s = 0;
        for (int i = 0; i < n; ++i) {
            vis[A[i]] ^= 1;
            s += vis[A[i]];
            vis[B[i]] ^= 1;
            s += vis[B[i]];
            ans[i] = s;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> findThePrefixCommonArray(vector<int>& A, vector<int>& B) {
        int n = A.size();
        vector<int> ans;
        vector<int> vis(n + 1, 1);
        int s = 0;
        for (int i = 0; i < n; ++i) {
            vis[A[i]] ^= 1;
            s += vis[A[i]];
            vis[B[i]] ^= 1;
            s += vis[B[i]];
            ans.push_back(s);
        }
        return ans;
    }
};
```

```go
func findThePrefixCommonArray(A []int, B []int) (ans []int) {
	vis := make([]int, len(A)+1)
	for i := range vis {
		vis[i] = 1
	}
	s := 0
	for i, a := range A {
		b := B[i]
		vis[a] ^= 1
		s += vis[a]
		vis[b] ^= 1
		s += vis[b]
		ans = append(ans, s)
	}
	return
}
```

```ts
function findThePrefixCommonArray(A: number[], B: number[]): number[] {
    const n = A.length;
    const vis: number[] = Array(n + 1).fill(1);
    const ans: number[] = [];
    let s = 0;
    for (let i = 0; i < n; ++i) {
        const [a, b] = [A[i], B[i]];
        vis[a] ^= 1;
        s += vis[a];
        vis[b] ^= 1;
        s += vis[b];
        ans.push(s);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
