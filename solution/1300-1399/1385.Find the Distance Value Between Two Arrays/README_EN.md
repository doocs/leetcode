---
comments: true
difficulty: Easy
rating: 1234
source: Biweekly Contest 22 Q1
tags:
    - Array
    - Two Pointers
    - Binary Search
    - Sorting
---

<!-- problem:start -->

# [1385. Find the Distance Value Between Two Arrays](https://leetcode.com/problems/find-the-distance-value-between-two-arrays)

[中文文档](/solution/1300-1399/1385.Find%20the%20Distance%20Value%20Between%20Two%20Arrays/README.md)

## Description

<!-- description:start -->

<p dir="ltr">You are given two integer arrays <code dir="ltr">arr1</code> and <code dir="ltr">arr2</code>, and an integer <code dir="ltr">d</code>.</p>

<p dir="ltr">An element <code dir="ltr">arr1[i]</code> is called <strong>far</strong> when every element <code dir="ltr">arr2[j]</code> satisfies <code dir="ltr">|arr1[i] - arr2[j]| &gt; d</code>. In other words, no element of <code dir="ltr">arr2</code> lies within distance <code dir="ltr">d</code> of it.</p>

<p dir="ltr">Return the <strong>distance</strong> value, which is the number of <strong>far</strong> elements in <code dir="ltr">arr1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>4 is far. Its nearest value in <code>arr2</code> is 1, and <code>|4 - 1| = 3</code>, which is greater than <code>d = 2</code>.</li>
	<li>5 is far. Its nearest value in <code>arr2</code> is 8, and <code>|5 - 8| = 3</code>, which is greater than <code>d = 2</code>.</li>
	<li>8 is not far, because <code>|8 - 8| = 0</code> is at most <code>d = 2</code>.</li>
	<li>Two elements are far, so the distance value is 2.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>1 is far. Its nearest value in <code>arr2</code> is -3, and <code>|1 - (-3)| = 4</code>, which is greater than <code>d = 3</code>.</li>
	<li>2 is far. Its nearest value in <code>arr2</code> is 6, and <code>|2 - 6| = 4</code>, which is greater than <code>d = 3</code>.</li>
	<li>4 is not far, because <code>|4 - 6| = 2</code> is at most <code>d = 3</code>.</li>
	<li>3 is not far, because <code>|3 - 6| = 3</code> is at most <code>d = 3</code>.</li>
	<li>Two elements are far, so the distance value is 2.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>100 is far. Its nearest value in <code>arr2</code> is 10, and <code>|100 - 10| = 90</code>, which is greater than <code>d = 6</code>.</li>
	<li>2 is not far, because <code>|2 - (-2)| = 4</code> is at most <code>d = 6</code>.</li>
	<li>1 is not far, because <code>|1 - (-2)| = 3</code> is at most <code>d = 6</code>.</li>
	<li>3 is not far, because <code>|3 - 7| = 4</code> is at most <code>d = 6</code>.</li>
	<li>One element is far, so the distance value is 1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr1.length, arr2.length &lt;= 500</code></li>
	<li><code>-1000 &lt;= arr1[i], arr2[j] &lt;= 1000</code></li>
	<li><code>0 &lt;= d &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Binary Search

<!-- thinking:start -->

> **Thinking**
>
> Count values in $\textit{arr1}$ whose distance to every entry of $\textit{arr2}$ exceeds $d$. A double scan is $O(mn)$. After sorting $\textit{arr2}$, $x$ is valid iff nothing lies in $[x-d,x+d]$: the first index $\ge x-d$ is either past the end or already greater than $x+d$.

<!-- thinking:end -->

We can first sort the array $\textit{arr2}$, and then for each element $x$ in the array $\textit{arr1}$, use binary search to find the first element in the array $\textit{arr2}$ that is greater than or equal to $x - d$. If such an element exists and is less than or equal to $x + d$, it does not meet the distance requirement. Otherwise, it meets the distance requirement. We count the number of elements that meet the distance requirement, which is the answer.

The time complexity is $O((m + n) \log n)$, and the space complexity is $O(\log n)$, where $m$ and $n$ are the lengths of the arrays $\textit{arr1}$ and $\textit{arr2}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findTheDistanceValue(self, arr1: list[int], arr2: list[int], d: int) -> int:
        arr2.sort()
        ans = 0
        for x in arr1:
            i = bisect_left(arr2, x - d)
            ans += i == len(arr2) or arr2[i] > x + d
        return ans
```

#### Java

```java
class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int ans = 0;
        for (int x : arr1) {
            int i = Arrays.binarySearch(arr2, x - d);
            i = i < 0 ? -i - 1 : i;
            if (i == arr2.length || arr2[i] > x + d) {
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findTheDistanceValue(vector<int>& arr1, vector<int>& arr2, int d) {
        ranges::sort(arr2);
        int ans = 0;
        for (int x : arr1) {
            auto it = ranges::lower_bound(arr2, x - d);
            if (it == arr2.end() || *it > x + d) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findTheDistanceValue(arr1 []int, arr2 []int, d int) (ans int) {
	sort.Ints(arr2)
	for _, x := range arr1 {
		i := sort.SearchInts(arr2, x-d)
		if i == len(arr2) || arr2[i] > x+d {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function findTheDistanceValue(arr1: number[], arr2: number[], d: number): number {
    arr2.sort((a, b) => a - b);
    let ans: number = 0;
    for (const x of arr1) {
        const i = _.sortedIndex(arr2, x - d);
        if (i === arr2.length || arr2[i] > x + d) {
            ++ans;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_the_distance_value(arr1: Vec<i32>, mut arr2: Vec<i32>, d: i32) -> i32 {
        arr2.sort();
        let mut ans = 0;
        for &x in &arr1 {
            let i = match arr2.binary_search(&(x - d)) {
                Ok(j) => j,
                Err(j) => j,
            };
            if i == arr2.len() || arr2[i] > x + d {
                ans += 1;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
