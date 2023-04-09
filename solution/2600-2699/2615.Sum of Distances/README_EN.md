# [2615. Sum of Distances](https://leetcode.com/problems/sum-of-distances)

[中文文档](/solution/2600-2699/2615.Sum%20of%20Distances/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. There exists an array <code>arr</code> of length <code>nums.length</code>, where <code>arr[i]</code> is the sum of <code>|i - j|</code> over all <code>j</code> such that <code>nums[j] == nums[i]</code> and <code>j != i</code>. If there is no such <code>j</code>, set <code>arr[i]</code> to be <code>0</code>.</p>

<p>Return <em>the array </em><code>arr</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,1,1,2]
<strong>Output:</strong> [5,0,3,4,0]
<strong>Explanation:</strong> 
When i = 0, nums[0] == nums[2] and nums[0] == nums[3]. Therefore, arr[0] = |0 - 2| + |0 - 3| = 5. 
When i = 1, arr[1] = 0 because there is no other index with value 3.
When i = 2, nums[2] == nums[0] and nums[2] == nums[3]. Therefore, arr[2] = |2 - 0| + |2 - 3| = 3. 
When i = 3, nums[3] == nums[0] and nums[3] == nums[2]. Therefore, arr[3] = |3 - 0| + |3 - 2| = 4. 
When i = 4, arr[4] = 0 because there is no other index with value 2. 

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,5,3]
<strong>Output:</strong> [0,0,0]
<strong>Explanation:</strong> Since each element in nums is distinct, arr[i] = 0 for all i.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def distance(self, nums: List[int]) -> List[int]:
        d = defaultdict(list)
        for i, x in enumerate(nums):
            d[x].append(i)
        ans = [0] * len(nums)
        for idx in d.values():
            left, right = 0, sum(idx) - len(idx) * idx[0]
            for i in range(len(idx)):
                ans[idx[i]] = left + right
                if i + 1 < len(idx):
                    left += (idx[i + 1] - idx[i]) * (i + 1)
                    right -= (idx[i + 1] - idx[i]) * (len(idx) - i - 1)
        return ans
```

### **Java**

```java
class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] ans = new long[n];
        Map<Integer, List<Integer>> d = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            d.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        for (var idx : d.values()) {
            int m = idx.size();
            long left = 0;
            long right = -1L * m * idx.get(0);
            for (int i : idx) {
                right += i;
            }
            for (int i = 0; i < m; ++i) {
                ans[idx.get(i)] = left + right;
                if (i + 1 < m) {
                    left += (idx.get(i + 1) - idx.get(i)) * (i + 1L);
                    right -= (idx.get(i + 1) - idx.get(i)) * (m - i - 1L);
                }
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
    vector<long long> distance(vector<int>& nums) {
        int n = nums.size();
        vector<long long> ans(n);
        unordered_map<int, vector<int>> d;
        for (int i = 0; i < n; ++i) {
            d[nums[i]].push_back(i);
        }
        for (auto& [_, idx] : d) {
            int m = idx.size();
            long long left = 0;
            long long right = -1LL * m * idx[0];
            for (int i : idx) {
                right += i;
            }
            for (int i = 0; i < m; ++i) {
                ans[idx[i]] = left + right;
                if (i + 1 < m) {
                    left += (idx[i + 1] - idx[i]) * (i + 1);
                    right -= (idx[i + 1] - idx[i]) * (m - i - 1);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func distance(nums []int) []int64 {
	n := len(nums)
	ans := make([]int64, n)
	d := map[int][]int{}
	for i, x := range nums {
		d[x] = append(d[x], i)
	}
	for _, idx := range d {
		m := len(idx)
		left, right := 0, -m*idx[0]
		for _, i := range idx {
			right += i
		}
		for i := range idx {
			ans[idx[i]] = int64(left + right)
			if i+1 < m {
				left += (idx[i+1] - idx[i]) * (i + 1)
				right -= (idx[i+1] - idx[i]) * (m - i - 1)
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
