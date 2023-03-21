# [2570. Merge Two 2D Arrays by Summing Values](https://leetcode.com/problems/merge-two-2d-arrays-by-summing-values)

[中文文档](/solution/2500-2599/2570.Merge%20Two%202D%20Arrays%20by%20Summing%20Values/README.md)

## Description

<p>You are given two <strong>2D</strong> integer arrays <code>nums1</code> and <code>nums2.</code></p>

<ul>
	<li><code>nums1[i] = [id<sub>i</sub>, val<sub>i</sub>]</code>&nbsp;indicate that the number with the id <code>id<sub>i</sub></code> has a value equal to <code>val<sub>i</sub></code>.</li>
	<li><code>nums2[i] = [id<sub>i</sub>, val<sub>i</sub>]</code>&nbsp;indicate that the number with the id <code>id<sub>i</sub></code> has a value equal to <code>val<sub>i</sub></code>.</li>
</ul>

<p>Each array contains <strong>unique</strong> ids and is sorted in <strong>ascending</strong> order by id.</p>

<p>Merge the two arrays into one array that is sorted in ascending order by id, respecting the following conditions:</p>

<ul>
	<li>Only ids that appear in at least one of the two arrays should be included in the resulting array.</li>
	<li>Each id should be included <strong>only once</strong> and its value should be the sum of the values of this id in the two arrays. If the id does not exist in one of the two arrays then its value in that array is considered to be <code>0</code>.</li>
</ul>

<p>Return <em>the resulting array</em>. The returned array must be sorted in ascending order by id.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [[1,2],[2,3],[4,5]], nums2 = [[1,4],[3,2],[4,1]]
<strong>Output:</strong> [[1,6],[2,3],[3,2],[4,6]]
<strong>Explanation:</strong> The resulting array contains the following:
- id = 1, the value of this id is 2 + 4 = 6.
- id = 2, the value of this id is 3.
- id = 3, the value of this id is 2.
- id = 4, the value of this id is 5 + 1 = 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [[2,4],[3,6],[5,5]], nums2 = [[1,3],[4,3]]
<strong>Output:</strong> [[1,3],[2,4],[3,6],[4,3],[5,5]]
<strong>Explanation:</strong> There are no common ids, so we just include each id with its value in the resulting list.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 200</code></li>
	<li><code>nums1[i].length == nums2[j].length == 2</code></li>
	<li><code>1 &lt;= id<sub>i</sub>, val<sub>i</sub> &lt;= 1000</code></li>
	<li>Both arrays contain unique ids.</li>
	<li>Both arrays are in&nbsp;strictly ascending order by id.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def mergeArrays(
        self, nums1: List[List[int]], nums2: List[List[int]]
    ) -> List[List[int]]:
        cnt = Counter()
        for i, v in nums1 + nums2:
            cnt[i] += v
        return sorted(cnt.items())
```

### **Java**

```java
class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        int[] cnt = new int[1001];
        for (var x : nums1) {
            cnt[x[0]] += x[1];
        }
        for (var x : nums2) {
            cnt[x[0]] += x[1];
        }
        int n = 0;
        for (int i = 0; i < 1001; ++i) {
            if (cnt[i] > 0) {
                ++n;
            }
        }
        int[][] ans = new int[n][2];
        for (int i = 0, j = 0; i < 1001; ++i) {
            if (cnt[i] > 0) {
                ans[j++] = new int[] {i, cnt[i]};
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> mergeArrays(vector<vector<int>>& nums1, vector<vector<int>>& nums2) {
        int cnt[1001]{};
        for (auto& x : nums1) {
            cnt[x[0]] += x[1];
        }
        for (auto& x : nums2) {
            cnt[x[0]] += x[1];
        }
        vector<vector<int>> ans;
        for (int i = 0; i < 1001; ++i) {
            if (cnt[i]) {
                ans.push_back({i, cnt[i]});
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func mergeArrays(nums1 [][]int, nums2 [][]int) (ans [][]int) {
	cnt := [1001]int{}
	for _, x := range nums1 {
		cnt[x[0]] += x[1]
	}
	for _, x := range nums2 {
		cnt[x[0]] += x[1]
	}
	for i, x := range cnt {
		if x > 0 {
			ans = append(ans, []int{i, x})
		}
	}
	return
}
```

### **TypeScript**

```ts
function mergeArrays(nums1: number[][], nums2: number[][]): number[][] {
    const n = 1001;
    const cnt = new Array(n).fill(0);
    for (const [a, b] of nums1) {
        cnt[a] += b;
    }
    for (const [a, b] of nums2) {
        cnt[a] += b;
    }
    const ans: number[][] = [];
    for (let i = 0; i < n; ++i) {
        if (cnt[i] > 0) {
            ans.push([i, cnt[i]]);
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
