---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1338.Reduce%20Array%20Size%20to%20The%20Half/README_EN.md
rating: 1303
source: Weekly Contest 174 Q2
tags:
    - Greedy
    - Array
    - Hash Table
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [1338. Reduce Array Size to The Half](https://leetcode.com/problems/reduce-array-size-to-the-half)

[中文文档](/solution/1300-1399/1338.Reduce%20Array%20Size%20to%20The%20Half/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>arr</code>. You can choose a set of integers and remove all the occurrences of these integers in the array.</p>

<p>Return <em>the minimum size of the set so that <strong>at least</strong> half of the integers of the array are removed</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,3,3,3,5,5,5,2,2,7]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
Possible sets of size 2 are {3,5},{3,2},{5,2}.
Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has a size greater than half of the size of the old array.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [7,7,7,7,7,7]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The only possible set you can choose is {7}. This will make the new array empty.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>arr.length</code> is even.</li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting + Sorting

We can use a hash table or an array $\textit{cnt}$ to count the occurrences of each number in the array $\textit{arr}$. Then, we sort the numbers in $\textit{cnt}$ in descending order. We traverse $\textit{cnt}$ from largest to smallest, adding the current number $x$ to the answer and adding $x$ to $m$. If $m \geq \frac{n}{2}$, we return the answer.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{arr}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSetSize(self, arr: List[int]) -> int:
        cnt = Counter(arr)
        ans = m = 0
        for _, v in cnt.most_common():
            m += v
            ans += 1
            if m * 2 >= len(arr):
                break
        return ans
```

#### Java

```java
class Solution {
    public int minSetSize(int[] arr) {
        int mx = 0;
        for (int x : arr) {
            mx = Math.max(mx, x);
        }
        int[] cnt = new int[mx + 1];
        for (int x : arr) {
            ++cnt[x];
        }
        Arrays.sort(cnt);
        int ans = 0;
        int m = 0;
        for (int i = mx;; --i) {
            if (cnt[i] > 0) {
                m += cnt[i];
                ++ans;
                if (m * 2 >= arr.length) {
                    return ans;
                }
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minSetSize(vector<int>& arr) {
        int mx = ranges::max(arr);
        int cnt[mx + 1];
        memset(cnt, 0, sizeof(cnt));
        for (int& x : arr) {
            ++cnt[x];
        }
        sort(cnt, cnt + mx + 1, greater<int>());
        int ans = 0;
        int m = 0;
        for (int& x : cnt) {
            if (x) {
                m += x;
                ++ans;
                if (m * 2 >= arr.size()) {
                    break;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minSetSize(arr []int) (ans int) {
	mx := slices.Max(arr)
	cnt := make([]int, mx+1)
	for _, x := range arr {
		cnt[x]++
	}
	sort.Ints(cnt)
	for i, m := mx, 0; ; i-- {
		if cnt[i] > 0 {
			m += cnt[i]
			ans++
			if m >= len(arr)/2 {
				return
			}
		}
	}
}
```

#### TypeScript

```ts
function minSetSize(arr: number[]): number {
    const cnt = new Map<number, number>();
    for (const v of arr) {
        cnt.set(v, (cnt.get(v) ?? 0) + 1);
    }
    let [ans, m] = [0, 0];
    for (const v of Array.from(cnt.values()).sort((a, b) => b - a)) {
        m += v;
        ++ans;
        if (m * 2 >= arr.length) {
            break;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
