# [2638. Count the Number of K-Free Subsets](https://leetcode.cn/problems/count-the-number-of-k-free-subsets)

[English Version](/solution/2600-2699/2638.Count%20the%20Number%20of%20K-Free%20Subsets/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：分组 + 动态规划**

我们先将数组 $nums$ 按照升序排序，然后将数组中的元素按照模 $k$ 分组，即 $nums[i] \bmod k$ 相同的元素放在同一组中。那么对于任意两个不同组的元素，它们的差值的绝对值一定不等于 $k$。因此，我们可以求出每一组的子集个数，然后将每一组的子集个数相乘即可得到答案。

对于每一组 $arr$，我们可以使用动态规划求出子集个数。设 $f[i]$ 表示前 $i$ 个元素的子集个数，初始时 $f[0] = 1$，而 $f[1]=2$。当 $i \geq 2$ 时，如果 $arr[i-1]-arr[i-2]=k$，如果我们选择 $arr[i-1]$，那么 $f[i]=f[i-2]$；如果我们不选择 $arr[i-1]$，那么 $f[i]=f[i-1]$。因此，当 $arr[i-1]-arr[i-2]=k$ 时，有 $f[i]=f[i-1]+f[i-2]$；否则 $f[i] = f[i - 1] \times 2$。这一组的子集个数即为 $f[m]$，其中 $m$ 为数组 $arr$ 的长度。

最后，我们将每一组的子集个数相乘即可得到答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
