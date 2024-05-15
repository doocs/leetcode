---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1331.Rank%20Transform%20of%20an%20Array/README_EN.md
rating: 1355
tags:
    - Array
    - Hash Table
    - Sorting
---

# [1331. Rank Transform of an Array](https://leetcode.com/problems/rank-transform-of-an-array)

[中文文档](/solution/1300-1399/1331.Rank%20Transform%20of%20an%20Array/README.md)

## Description

<p>Given an array of integers&nbsp;<code>arr</code>, replace each element with its rank.</p>

<p>The rank represents how large the element is. The rank has the following rules:</p>

<ul>
	<li>Rank is an integer starting from 1.</li>
	<li>The larger the element, the larger the rank. If two elements are equal, their rank must be the same.</li>
	<li>Rank should be as small as possible.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [40,10,20,30]
<strong>Output:</strong> [4,1,2,3]
<strong>Explanation</strong>: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [100,100,100]
<strong>Output:</strong> [1,1,1]
<strong>Explanation</strong>: Same elements share the same rank.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [37,12,28,9,100,56,80,5,12]
<strong>Output:</strong> [5,3,4,2,8,6,7,1,3]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def arrayRankTransform(self, arr: List[int]) -> List[int]:
        t = sorted(set(arr))
        return [bisect_right(t, x) for x in arr]
```

```java
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        int[] t = arr.clone();
        Arrays.sort(t);
        int m = 0;
        for (int i = 0; i < n; ++i) {
            if (i == 0 || t[i] != t[i - 1]) {
                t[m++] = t[i];
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = Arrays.binarySearch(t, 0, m, arr[i]) + 1;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> arrayRankTransform(vector<int>& arr) {
        vector<int> t = arr;
        sort(t.begin(), t.end());
        t.erase(unique(t.begin(), t.end()), t.end());
        vector<int> ans;
        for (int x : arr) {
            ans.push_back(upper_bound(t.begin(), t.end(), x) - t.begin());
        }
        return ans;
    }
};
```

```go
func arrayRankTransform(arr []int) (ans []int) {
	t := make([]int, len(arr))
	copy(t, arr)
	sort.Ints(t)
	m := 0
	for i, x := range t {
		if i == 0 || x != t[i-1] {
			t[m] = x
			m++
		}
	}
	t = t[:m]
	for _, x := range arr {
		ans = append(ans, sort.SearchInts(t, x)+1)
	}
	return
}
```

```ts
function arrayRankTransform(arr: number[]): number[] {
    const t = [...arr].sort((a, b) => a - b);
    let m = 0;
    for (let i = 0; i < t.length; ++i) {
        if (i === 0 || t[i] !== t[i - 1]) {
            t[m++] = t[i];
        }
    }
    const search = (t: number[], right: number, x: number) => {
        let left = 0;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (t[mid] > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    };
    const ans: number[] = [];
    for (const x of arr) {
        ans.push(search(t, m, x));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
