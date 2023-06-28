# [2638. Count the Number of K-Free Subsets](https://leetcode.com/problems/count-the-number-of-k-free-subsets)

[中文文档](/solution/2600-2699/2638.Count%20the%20Number%20of%20K-Free%20Subsets/README.md)

## Description

<p>You are given an integer array <code>nums</code>,&nbsp;which contains <strong>distinct</strong> elements and an integer <code>k</code>.</p>

<p>A subset is called a <strong>k-Free</strong> subset if it contains <strong>no</strong> two elements with an absolute difference equal to <code>k</code>. Notice that the empty set is a <strong>k-Free</strong> subset.</p>

<p>Return <em>the number of <strong>k-Free</strong> subsets of </em><code>nums</code>.</p>

<p>A <b>subset</b> of an array is a selection of elements (possibly none) of the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,4,6], k = 1
<strong>Output:</strong> 5
<strong>Explanation:</strong> There are 5 valid subsets: {}, {5}, {4}, {6} and {4, 6}.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,5,8], k = 5
<strong>Output:</strong> 12
<strong>Explanation:</strong> There are 12 valid subsets: {}, {2}, {3}, {5}, {8}, {2, 3}, {2, 3, 5}, {2, 5}, {2, 5, 8}, {2, 8}, {3, 5} and {5, 8}.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,5,9,11], k = 20
<strong>Output:</strong> 16
<strong>Explanation:</strong> All subsets are valid. Since the total count of subsets is 2<sup>4 </sup>= 16, so the answer is 16. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= 1000</code></li>
</ul>

## Solutions

**Solution 1: Grouping + Dynamic Programming**

First, sort the array $nums$ in ascending order, and then group the elements in the array according to the remainder modulo $k$, that is, the elements $nums[i] \bmod k$ with the same remainder are in the same group. Then for any two elements in different groups, their absolute difference is not equal to $k$. Therefore, we can obtain the number of subsets in each group, and then multiply the number of subsets in each group to obtain the answer.

For each group $arr$, we can use dynamic programming to obtain the number of subsets. Let $f[i]$ denote the number of subsets of the first $i$ elements, and initially $f[0] = 1$, and $f[1]=2$. When $i \geq 2$, if $arr[i-1]-arr[i-2]=k$, if we choose $arr[i-1]$, then $f[i]=f[i-2]$; If we do not choose $arr[i-1]$, then $f[i]=f[i-1]$. Therefore, when $arr[i-1]-arr[i-2]=k$, we have $f[i]=f[i-1]+f[i-2]$; otherwise $f[i] = f[i - 1] \times 2$. The number of subsets of this group is $f[m]$, where $m$ is the length of the array $arr$.

Finally, we multiply the number of subsets of each group to obtain the answer.

The time complexity is $O(n \times \log n)$ and the space complexity is $O(n)$, where $n$ is the length of the array $nums$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countTheNumOfKFreeSubsets(self, nums: List[int], k: int) -> int:
        nums.sort()
        g = defaultdict(list)
        for x in nums:
            g[x % k].append(x)
        ans = 1
        for arr in g.values():
            m = len(arr)
            f = [0] * (m + 1)
            f[0] = 1
            f[1] = 2
            for i in range(2, m + 1):
                if arr[i - 1] - arr[i - 2] == k:
                    f[i] = f[i - 1] + f[i - 2]
                else:
                    f[i] = f[i - 1] * 2
            ans *= f[m]
        return ans
```

### **Java**

```java
class Solution {
    public long countTheNumOfKFreeSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            g.computeIfAbsent(nums[i] % k, x -> new ArrayList<>()).add(nums[i]);
        }
        long ans = 1;
        for (var arr : g.values()) {
            int m = arr.size();
            long[] f = new long[m + 1];
            f[0] = 1;
            f[1] = 2;
            for (int i = 2; i <= m; ++i) {
                if (arr.get(i - 1) - arr.get(i - 2) == k) {
                    f[i] = f[i - 1] + f[i - 2];
                } else {
                    f[i] = f[i - 1] * 2;
                }
            }
            ans *= f[m];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long countTheNumOfKFreeSubsets(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        unordered_map<int, vector<int>> g;
        for (int i = 0; i < nums.size(); ++i) {
            g[nums[i] % k].push_back(nums[i]);
        }
        long long ans = 1;
        for (auto& [_, arr] : g) {
            int m = arr.size();
            long long f[m + 1];
            f[0] = 1;
            f[1] = 2;
            for (int i = 2; i <= m; ++i) {
                if (arr[i - 1] - arr[i - 2] == k) {
                    f[i] = f[i - 1] + f[i - 2];
                } else {
                    f[i] = f[i - 1] * 2;
                }
            }
            ans *= f[m];
        }
        return ans;
    }
};
```

### **Go**

```go
func countTheNumOfKFreeSubsets(nums []int, k int) int64 {
	sort.Ints(nums)
	g := map[int][]int{}
	for _, x := range nums {
		g[x%k] = append(g[x%k], x)
	}
	ans := int64(1)
	for _, arr := range g {
		m := len(arr)
		f := make([]int64, m+1)
		f[0] = 1
		f[1] = 2
		for i := 2; i <= m; i++ {
			if arr[i-1]-arr[i-2] == k {
				f[i] = f[i-1] + f[i-2]
			} else {
				f[i] = f[i-1] * 2
			}
		}
		ans *= f[m]
	}
	return ans
}
```

### **TypeScript**

```ts
function countTheNumOfKFreeSubsets(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const g: Map<number, number[]> = new Map();
    for (const x of nums) {
        const y = x % k;
        if (!g.has(y)) {
            g.set(y, []);
        }
        g.get(y)!.push(x);
    }
    let ans: number = 1;
    for (const [_, arr] of g) {
        const m = arr.length;
        const f: number[] = new Array(m + 1).fill(1);
        f[1] = 2;
        for (let i = 2; i <= m; ++i) {
            if (arr[i - 1] - arr[i - 2] === k) {
                f[i] = f[i - 1] + f[i - 2];
            } else {
                f[i] = f[i - 1] * 2;
            }
        }
        ans *= f[m];
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
