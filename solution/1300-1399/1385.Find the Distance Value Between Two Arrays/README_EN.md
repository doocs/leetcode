---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1385.Find%20the%20Distance%20Value%20Between%20Two%20Arrays/README_EN.md
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

<p>Given two integer arrays <code>arr1</code> and <code>arr2</code>, and the integer <code>d</code>, <em>return the distance value between the two arrays</em>.</p>

<p>The distance value is defined as the number of elements <code>arr1[i]</code> such that there is not any element <code>arr2[j]</code> where <code>|arr1[i]-arr2[j]| &lt;= d</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
For arr1[0]=4 we have: 
|4-10|=6 &gt; d=2 
|4-9|=5 &gt; d=2 
|4-1|=3 &gt; d=2 
|4-8|=4 &gt; d=2 
For arr1[1]=5 we have: 
|5-10|=5 &gt; d=2 
|5-9|=4 &gt; d=2 
|5-1|=4 &gt; d=2 
|5-8|=3 &gt; d=2
For arr1[2]=8 we have:
<strong>|8-10|=2 &lt;= d=2</strong>
<strong>|8-9|=1 &lt;= d=2</strong>
|8-1|=7 &gt; d=2
<strong>|8-8|=0 &lt;= d=2</strong>
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
<strong>Output:</strong> 1
</pre>

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

We can first sort the array $\textit{arr2}$, and then for each element $x$ in the array $\textit{arr1}$, use binary search to find the first element in the array $\textit{arr2}$ that is greater than or equal to $x - d$. If such an element exists and is less than or equal to $x + d$, it does not meet the distance requirement. Otherwise, it meets the distance requirement. We count the number of elements that meet the distance requirement, which is the answer.

The time complexity is $O((m + n) \times \log n)$, and the space complexity is $O(\log n)$. Here, $m$ and $n$ are the lengths of the arrays $\textit{arr1}$ and $\textit{arr2}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findTheDistanceValue(self, arr1: List[int], arr2: List[int], d: int) -> int:
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
