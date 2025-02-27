---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0888.Fair%20Candy%20Swap/README_EN.md
tags:
    - Array
    - Hash Table
    - Binary Search
    - Sorting
---

<!-- problem:start -->

# [888. Fair Candy Swap](https://leetcode.com/problems/fair-candy-swap)

[中文文档](/solution/0800-0899/0888.Fair%20Candy%20Swap/README.md)

## Description

<!-- description:start -->

<p>Alice and Bob have a different total number of candies. You are given two integer arrays <code>aliceSizes</code> and <code>bobSizes</code> where <code>aliceSizes[i]</code> is the number of candies of the <code>i<sup>th</sup></code> box of candy that Alice has and <code>bobSizes[j]</code> is the number of candies of the <code>j<sup>th</sup></code> box of candy that Bob has.</p>

<p>Since they are friends, they would like to exchange one candy box each so that after the exchange, they both have the same total amount of candy. The total amount of candy a person has is the sum of the number of candies in each box they have.</p>

<p>Return a<em>n integer array </em><code>answer</code><em> where </em><code>answer[0]</code><em> is the number of candies in the box that Alice must exchange, and </em><code>answer[1]</code><em> is the number of candies in the box that Bob must exchange</em>. If there are multiple answers, you may <strong>return any</strong> one of them. It is guaranteed that at least one answer exists.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> aliceSizes = [1,1], bobSizes = [2,2]
<strong>Output:</strong> [1,2]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> aliceSizes = [1,2], bobSizes = [2,3]
<strong>Output:</strong> [1,2]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> aliceSizes = [2], bobSizes = [1,3]
<strong>Output:</strong> [2,3]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= aliceSizes.length, bobSizes.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= aliceSizes[i], bobSizes[j] &lt;= 10<sup>5</sup></code></li>
	<li>Alice and Bob have a different total number of candies.</li>
	<li>There will be at least one valid answer for the given input.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We can first calculate the difference in the total number of candies between Alice and Bob, divide it by two to get the difference in the number of candies to be exchanged $\textit{diff}$, and use a hash table $\textit{s}$ to store the number of candies in Bob's candy boxes. Then, we traverse Alice's candy boxes, and for each candy count $\textit{a}$, we check if $\textit{a} - \textit{diff}$ is in the hash table $\textit{s}$. If it exists, it means we have found a valid answer, and we return it.

The time complexity is $O(m + n)$, and the space complexity is $O(n)$. Where $m$ and $n$ are the number of candy boxes Alice and Bob have, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def fairCandySwap(self, aliceSizes: List[int], bobSizes: List[int]) -> List[int]:
        diff = (sum(aliceSizes) - sum(bobSizes)) >> 1
        s = set(bobSizes)
        for a in aliceSizes:
            if (b := (a - diff)) in s:
                return [a, b]
```

#### Java

```java
class Solution {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int s1 = 0, s2 = 0;
        Set<Integer> s = new HashSet<>();
        for (int a : aliceSizes) {
            s1 += a;
        }
        for (int b : bobSizes) {
            s.add(b);
            s2 += b;
        }
        int diff = (s1 - s2) >> 1;
        for (int a : aliceSizes) {
            int b = a - diff;
            if (s.contains(b)) {
                return new int[] {a, b};
            }
        }
        return null;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> fairCandySwap(vector<int>& aliceSizes, vector<int>& bobSizes) {
        int s1 = accumulate(aliceSizes.begin(), aliceSizes.end(), 0);
        int s2 = accumulate(bobSizes.begin(), bobSizes.end(), 0);
        int diff = (s1 - s2) >> 1;
        unordered_set<int> s(bobSizes.begin(), bobSizes.end());
        vector<int> ans;
        for (int& a : aliceSizes) {
            int b = a - diff;
            if (s.count(b)) {
                ans = vector<int>{a, b};
                break;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func fairCandySwap(aliceSizes []int, bobSizes []int) []int {
	s1, s2 := 0, 0
	s := map[int]bool{}
	for _, a := range aliceSizes {
		s1 += a
	}
	for _, b := range bobSizes {
		s2 += b
		s[b] = true
	}
	diff := (s1 - s2) / 2
	for _, a := range aliceSizes {
		if b := a - diff; s[b] {
			return []int{a, b}
		}
	}
	return nil
}
```

#### TypeScript

```ts
function fairCandySwap(aliceSizes: number[], bobSizes: number[]): number[] {
    const s1 = aliceSizes.reduce((acc, cur) => acc + cur, 0);
    const s2 = bobSizes.reduce((acc, cur) => acc + cur, 0);
    const diff = (s1 - s2) >> 1;
    const s = new Set(bobSizes);
    for (const a of aliceSizes) {
        const b = a - diff;
        if (s.has(b)) {
            return [a, b];
        }
    }
    return [];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
