---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1200.Minimum%20Absolute%20Difference/README_EN.md
rating: 1198
tags:
    - Array
    - Sorting
---

# [1200. Minimum Absolute Difference](https://leetcode.com/problems/minimum-absolute-difference)

[中文文档](/solution/1200-1299/1200.Minimum%20Absolute%20Difference/README.md)

## Description

<p>Given an array of <strong>distinct</strong> integers <code>arr</code>, find all pairs of elements with the minimum absolute difference of any two elements.</p>

<p>Return a list of pairs in ascending order(with respect to pairs), each pair <code>[a, b]</code> follows</p>

<ul>
	<li><code>a, b</code> are from <code>arr</code></li>
	<li><code>a &lt; b</code></li>
	<li><code>b - a</code> equals to the minimum absolute difference of any two elements in <code>arr</code></li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [4,2,1,3]
<strong>Output:</strong> [[1,2],[2,3],[3,4]]
<strong>Explanation: </strong>The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,3,6,10,15]
<strong>Output:</strong> [[1,3]]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,8,-10,23,19,-4,-14,27]
<strong>Output:</strong> [[-14,-10],[19,23],[23,27]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>6</sup> &lt;= arr[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

### Solution 1: Sorting

According to the problem description, we need to find the minimum absolute difference between any two elements in the array $arr$. Therefore, we can first sort the array $arr$, then traverse the adjacent elements to get the minimum absolute difference $mi$.

Finally, we traverse the adjacent elements again to find all pairs of elements where the minimum absolute difference equals $mi$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array $arr$.

<!-- tabs:start -->

```python
class Solution:
    def minimumAbsDifference(self, arr: List[int]) -> List[List[int]]:
        arr.sort()
        mi = min(b - a for a, b in pairwise(arr))
        return [[a, b] for a, b in pairwise(arr) if b - a == mi]
```

```java
class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int mi = 1 << 30;
        for (int i = 0; i < n - 1; ++i) {
            mi = Math.min(mi, arr[i + 1] - arr[i]);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n - 1; ++i) {
            if (arr[i + 1] - arr[i] == mi) {
                ans.add(List.of(arr[i], arr[i + 1]));
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> minimumAbsDifference(vector<int>& arr) {
        sort(arr.begin(), arr.end());
        int mi = 1 << 30;
        int n = arr.size();
        for (int i = 0; i < n - 1; ++i) {
            mi = min(mi, arr[i + 1] - arr[i]);
        }
        vector<vector<int>> ans;
        for (int i = 0; i < n - 1; ++i) {
            if (arr[i + 1] - arr[i] == mi) {
                ans.push_back({arr[i], arr[i + 1]});
            }
        }
        return ans;
    }
};
```

```go
func minimumAbsDifference(arr []int) (ans [][]int) {
	sort.Ints(arr)
	mi := 1 << 30
	n := len(arr)
	for i := 0; i < n-1; i++ {
		if t := arr[i+1] - arr[i]; t < mi {
			mi = t
		}
	}
	for i := 0; i < n-1; i++ {
		if arr[i+1]-arr[i] == mi {
			ans = append(ans, []int{arr[i], arr[i+1]})
		}
	}
	return
}
```

```ts
function minimumAbsDifference(arr: number[]): number[][] {
    arr.sort((a, b) => a - b);
    let mi = 1 << 30;
    const n = arr.length;
    for (let i = 0; i < n - 1; ++i) {
        mi = Math.min(mi, arr[i + 1] - arr[i]);
    }
    const ans: number[][] = [];
    for (let i = 0; i < n - 1; ++i) {
        if (arr[i + 1] - arr[i] === mi) {
            ans.push([arr[i], arr[i + 1]]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
