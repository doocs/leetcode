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

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Binary Search

We can first sort the array $arr2$, then for each element $a$ in array $arr1$, use binary search to find the first element in array $arr2$ that is greater than or equal to $a-d$. If such an element exists and is less than or equal to $a+d$, it means that it does not meet the distance requirement. Otherwise, it meets the distance requirement. We accumulate the number of elements that meet the distance requirement, which is the answer.

The time complexity is $O((m + n) \times \log n)$, and the space complexity is $O(\log n)$. Where $m$ and $n$ are the lengths of arrays $arr1$ and $arr2$, respectively.

<!-- tabs:start -->

```python
class Solution:
    def findTheDistanceValue(self, arr1: List[int], arr2: List[int], d: int) -> int:
        def check(a: int) -> bool:
            i = bisect_left(arr2, a - d)
            return i == len(arr2) or arr2[i] > a + d

        arr2.sort()
        return sum(check(a) for a in arr1)
```

```java
class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int ans = 0;
        for (int a : arr1) {
            if (check(arr2, a, d)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(int[] arr, int a, int d) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (arr[mid] >= a - d) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l >= arr.length || arr[l] > a + d;
    }
}
```

```cpp
class Solution {
public:
    int findTheDistanceValue(vector<int>& arr1, vector<int>& arr2, int d) {
        auto check = [&](int a) -> bool {
            auto it = lower_bound(arr2.begin(), arr2.end(), a - d);
            return it == arr2.end() || *it > a + d;
        };
        sort(arr2.begin(), arr2.end());
        int ans = 0;
        for (int& a : arr1) {
            ans += check(a);
        }
        return ans;
    }
};
```

```go
func findTheDistanceValue(arr1 []int, arr2 []int, d int) (ans int) {
	sort.Ints(arr2)
	for _, a := range arr1 {
		i := sort.SearchInts(arr2, a-d)
		if i == len(arr2) || arr2[i] > a+d {
			ans++
		}
	}
	return
}
```

```ts
function findTheDistanceValue(arr1: number[], arr2: number[], d: number): number {
    const check = (a: number) => {
        let l = 0;
        let r = arr2.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (arr2[mid] >= a - d) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l === arr2.length || arr2[l] > a + d;
    };
    arr2.sort((a, b) => a - b);
    let ans = 0;
    for (const a of arr1) {
        if (check(a)) {
            ++ans;
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn find_the_distance_value(arr1: Vec<i32>, mut arr2: Vec<i32>, d: i32) -> i32 {
        arr2.sort();
        let n = arr2.len();
        let mut res = 0;
        for &num in arr1.iter() {
            let mut left = 0;
            let mut right = n - 1;
            while left < right {
                let mid = left + (right - left) / 2;
                if arr2[mid] <= num {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if
                i32::abs(num - arr2[left]) <= d ||
                (left != 0 && i32::abs(num - arr2[left - 1]) <= d)
            {
                continue;
            }
            res += 1;
        }
        res
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
