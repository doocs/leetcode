---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0768.Max%20Chunks%20To%20Make%20Sorted%20II/README_EN.md
tags:
    - Stack
    - Greedy
    - Array
    - Sorting
    - Monotonic Stack
---

<!-- problem:start -->

# [768. Max Chunks To Make Sorted II](https://leetcode.com/problems/max-chunks-to-make-sorted-ii)

[中文文档](/solution/0700-0799/0768.Max%20Chunks%20To%20Make%20Sorted%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>arr</code>.</p>

<p>We split <code>arr</code> into some number of <strong>chunks</strong> (i.e., partitions), and individually sort each chunk. After concatenating them, the result should equal the sorted array.</p>

<p>Return <em>the largest number of chunks we can make to sort the array</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [5,4,3,2,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
Splitting into two or more chunks will not return the required result.
For example, splitting into [5, 4], [3, 2, 1] will result in [4, 5, 1, 2, 3], which isn&#39;t sorted.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,1,3,4,4]
<strong>Output:</strong> 4
<strong>Explanation:</strong>
We can split into two chunks, such as [2, 1], [3, 4, 4].
However, splitting into [2, 1], [3], [4], [4] is the highest number of chunks possible.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 2000</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>8</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Monotonic Stack

According to the problem, we can find that from left to right, each chunk has a maximum value, and these maximum values are monotonically increasing (non-strictly increasing). We can use a stack to store these maximum values of the chunks. The size of the final stack is the maximum number of chunks that can be sorted.

Time complexity is $O(n)$, where $n$ represents the length of $\textit{arr}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxChunksToSorted(self, arr: List[int]) -> int:
        stk = []
        for v in arr:
            if not stk or v >= stk[-1]:
                stk.append(v)
            else:
                mx = stk.pop()
                while stk and stk[-1] > v:
                    stk.pop()
                stk.append(mx)
        return len(stk)
```

#### Java

```java
class Solution {
    public int maxChunksToSorted(int[] arr) {
        Deque<Integer> stk = new ArrayDeque<>();
        for (int v : arr) {
            if (stk.isEmpty() || stk.peek() <= v) {
                stk.push(v);
            } else {
                int mx = stk.pop();
                while (!stk.isEmpty() && stk.peek() > v) {
                    stk.pop();
                }
                stk.push(mx);
            }
        }
        return stk.size();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxChunksToSorted(vector<int>& arr) {
        stack<int> stk;
        for (int& v : arr) {
            if (stk.empty() || stk.top() <= v)
                stk.push(v);
            else {
                int mx = stk.top();
                stk.pop();
                while (!stk.empty() && stk.top() > v) stk.pop();
                stk.push(mx);
            }
        }
        return stk.size();
    }
};
```

#### Go

```go
func maxChunksToSorted(arr []int) int {
	var stk []int
	for _, v := range arr {
		if len(stk) == 0 || stk[len(stk)-1] <= v {
			stk = append(stk, v)
		} else {
			mx := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			for len(stk) > 0 && stk[len(stk)-1] > v {
				stk = stk[:len(stk)-1]
			}
			stk = append(stk, mx)
		}
	}
	return len(stk)
}
```

#### TypeScript

```ts
function maxChunksToSorted(arr: number[]): number {
    const stk: number[] = [];
    for (let v of arr) {
        if (stk.length === 0 || v >= stk[stk.length - 1]) {
            stk.push(v);
        } else {
            let mx = stk.pop()!;
            while (stk.length > 0 && stk[stk.length - 1] > v) {
                stk.pop();
            }
            stk.push(mx);
        }
    }
    return stk.length;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_chunks_to_sorted(arr: Vec<i32>) -> i32 {
        let mut stk = Vec::new();
        for &v in arr.iter() {
            if stk.is_empty() || v >= *stk.last().unwrap() {
                stk.push(v);
            } else {
                let mut mx = stk.pop().unwrap();
                while let Some(&top) = stk.last() {
                    if top > v {
                        stk.pop();
                    } else {
                        break;
                    }
                }
                stk.push(mx);
            }
        }
        stk.len() as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Prefix Maximums + Suffix Minimums

We would like to partition the array of length $n$ into several chunks such that
after sorting each chunk individually, the entire array remains sorted.

Consider two adjacent chunks:

* left chunk: `left_chunk`
* right chunk: `right_chunk`

If the following condition holds:

`max(left_chunk)` <= `min(right_chunk)`

it means:
* every element in the left chunk is less than or equal to every element in the right chunk
* therefore, after sorting both chunks independently, they can still be concatenated into a globally sorted array

Thus, for every index $i$ satisfying:

$$
1 \le i < n
$$

we check whether:

$$
\max(arr[:i]) \le \min(arr[i:])
$$

holds.

If true, then index $i$ can serve as a valid partition point.

---

To efficiently evaluate the above condition, we preprocess:

* `prefix_maxs[j]`

which represents:

$$
\max(arr[:j + 1])
$$

i.e. the prefix maximum.

* `suffix_min[j]`

which represents:

$$
\min(arr[j:])
$$

i.e. the suffix minimum.

Next:

1. Traverse the array from left to right to compute all prefix maximums
2. Traverse the array from right to left to compute all suffix minimums
3. For each index $i$, check whether:

`prefix_maxs[i - 1]` <= `suffix_min[i]`

holds.

If true, then:

* every element on the left side is less than or equal to every element on the right side
* therefore, the array can be partitioned at index $i$

Finally, count all valid partition points.

Note that:

Even if the array is strictly decreasing, the entire array itself can still be treated as one valid chunk.

Therefore, our final answer is equal to all valid partition points + $1$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ represents the length of $\textit{arr}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxChunksToSorted(self, arr: list[int]) -> int:
        prefix_maxs = []  # Max of each arr[:i + 1] where 0 <= i < len(arr).

        for num in arr:
            if not prefix_maxs:
                prefix_maxs.append(num)
                continue

            prefix_maxs.append(max(num, prefix_maxs[-1]))

        max_chunks = 1  # Base case.
        suffix_min = arr[-1]  # Min of arr[i:] where 0 <= i < len(arr).

        for idx in range(len(arr) - 1, 0, -1):
            if arr[idx] < suffix_min:
                suffix_min = arr[idx]

            if prefix_maxs[idx - 1] <= suffix_min:
                max_chunks += 1

        return max_chunks
```

#### C++

```cpp
class Solution {
public:
    int maxChunksToSorted(vector<int>& arr) {
        vector<int> prefixMaxs; // Max of each arr[:i + 1]. 0 <= i < arr.size().

        for (const auto& num : arr) {
            if (prefixMaxs.empty()) {
                prefixMaxs.push_back(num);
                continue;
            }

            prefixMaxs.push_back(max(prefixMaxs.back(), num));
        }

        int maxChunks = 1; // Base case.
        int suffixMin = arr.back(); // Min of arr[i:]. 0 <= i < arr.size().

        for (int idx = arr.size() - 1; idx >= 1; idx--) {
            if (arr[idx] < suffixMin)
                suffixMin = arr[idx];

            if (prefixMaxs[idx - 1] <= suffixMin)
                maxChunks++;
        }

        return maxChunks;
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
