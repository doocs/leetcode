---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3043.Find%20the%20Length%20of%20the%20Longest%20Common%20Prefix/README_EN.md
rating: 1688
tags:
    - Trie
    - Array
    - Hash Table
    - String
---

# [3043. Find the Length of the Longest Common Prefix](https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix)

[中文文档](/solution/3000-3099/3043.Find%20the%20Length%20of%20the%20Longest%20Common%20Prefix/README.md)

## Description

<p>You are given two arrays with <strong>positive</strong> integers <code>arr1</code> and <code>arr2</code>.</p>

<p>A <strong>prefix</strong> of a positive integer is an integer formed by one or more of its digits, starting from its <strong>leftmost</strong> digit. For example, <code>123</code> is a prefix of the integer <code>12345</code>, while <code>234</code> is <strong>not</strong>.</p>

<p>A <strong>common prefix</strong> of two integers <code>a</code> and <code>b</code> is an integer <code>c</code>, such that <code>c</code> is a prefix of both <code>a</code> and <code>b</code>. For example, <code>5655359</code> and <code>56554</code> have a common prefix <code>565</code> while <code>1223</code> and <code>43456</code> <strong>do not</strong> have a common prefix.</p>

<p>You need to find the length of the <strong>longest common prefix</strong> between all pairs of integers <code>(x, y)</code> such that <code>x</code> belongs to <code>arr1</code> and <code>y</code> belongs to <code>arr2</code>.</p>

<p>Return <em>the length of the <strong>longest</strong> common prefix among all pairs</em>.<em> If no common prefix exists among them</em>, <em>return</em> <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [1,10,100], arr2 = [1000]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 3 pairs (arr1[i], arr2[j]):
- The longest common prefix of (1, 1000) is 1.
- The longest common prefix of (10, 1000) is 10.
- The longest common prefix of (100, 1000) is 100.
The longest common prefix is 100 with a length of 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [1,2,3], arr2 = [4,4,4]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There exists no common prefix for any pair (arr1[i], arr2[j]), hence we return 0.
Note that common prefixes between elements of the same array do not count.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr1.length, arr2.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= arr1[i], arr2[i] &lt;= 10<sup>8</sup></code></li>
</ul>

## Solutions

### Solution 1: Hash Table

We can use a hash table to store all the prefixes of the numbers in `arr1`. Then, we traverse all the numbers $x$ in `arr2`. For each number $x$, we start from the highest bit and gradually decrease, checking whether it exists in the hash table. If it does, we have found a common prefix, and we can update the answer accordingly.

The time complexity is $O(m \times \log M + n \times \log N)$, and the space complexity is $O(m \times \log M)$. Here, $m$ and $n$ are the lengths of `arr1` and `arr2` respectively, and $M$ and $N$ are the maximum values in `arr1` and `arr2` respectively.

<!-- tabs:start -->

```python
class Solution:
    def longestCommonPrefix(self, arr1: List[int], arr2: List[int]) -> int:
        s = set()
        for x in arr1:
            while x:
                s.add(x)
                x //= 10
        ans = 0
        for x in arr2:
            while x:
                if x in s:
                    ans = max(ans, len(str(x)))
                    break
                x //= 10
        return ans
```

```java
class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<Integer> s = new HashSet<>();
        for (int x : arr1) {
            for (; x > 0; x /= 10) {
                s.add(x);
            }
        }
        int ans = 0;
        for (int x : arr2) {
            for (; x > 0; x /= 10) {
                if (s.contains(x)) {
                    ans = Math.max(ans, String.valueOf(x).length());
                    break;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int longestCommonPrefix(vector<int>& arr1, vector<int>& arr2) {
        unordered_set<int> s;
        for (int x : arr1) {
            for (; x; x /= 10) {
                s.insert(x);
            }
        }
        int ans = 0;
        for (int x : arr2) {
            for (; x; x /= 10) {
                if (s.count(x)) {
                    ans = max(ans, (int) log10(x) + 1);
                    break;
                }
            }
        }
        return ans;
    }
};
```

```go
func longestCommonPrefix(arr1 []int, arr2 []int) (ans int) {
	s := map[int]bool{}
	for _, x := range arr1 {
		for ; x > 0; x /= 10 {
			s[x] = true
		}
	}
	for _, x := range arr2 {
		for ; x > 0; x /= 10 {
			if s[x] {
				ans = max(ans, int(math.Log10(float64(x)))+1)
				break
			}
		}
	}
	return
}
```

```ts
function longestCommonPrefix(arr1: number[], arr2: number[]): number {
    const s: Set<number> = new Set<number>();
    for (let x of arr1) {
        for (; x; x = (x / 10) | 0) {
            s.add(x % 10);
        }
    }
    let ans: number = 0;
    for (let x of arr2) {
        for (; x; x = (x / 10) | 0) {
            if (s.has(x % 10)) {
                ans = Math.max(ans, Math.floor(Math.log10(x)) + 1);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
