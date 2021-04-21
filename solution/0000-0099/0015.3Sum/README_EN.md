# [15. 3Sum](https://leetcode.com/problems/3sum)

[中文文档](/solution/0000-0099/0015.3Sum/README.md)

## Description

<p>Given an integer array nums, return all the triplets <code>[nums[i], nums[j], nums[k]]</code> such that <code>i != j</code>, <code>i != k</code>, and <code>j != k</code>, and <code>nums[i] + nums[j] + nums[k] == 0</code>.</p>

<p>Notice that the solution set must not contain duplicate triplets.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [-1,0,1,2,-1,-4]
<strong>Output:</strong> [[-1,-1,2],[-1,0,1]]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = []
<strong>Output:</strong> []
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> nums = [0]
<strong>Output:</strong> []
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 3000</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        if nums is None or len(nums) < 3:
            return []
        nums.sort()
        n = len(nums)
        res = []
        for i in range(n - 2):
            if i > 0 and nums[i] == nums[i - 1]:
                continue
            p, q = i + 1, n - 1
            while p < q:
                if p > i + 1 and nums[p] == nums[p - 1]:
                    p += 1
                    continue
                if q < n - 1 and nums[q] == nums[q + 1]:
                    q -= 1
                    continue
                if nums[i] + nums[p] + nums[q] < 0:
                    p += 1
                elif nums[i] + nums[p] + nums[q] > 0:
                    q -= 1
                else:
                    res.append([nums[i], nums[p], nums[q]])
                    p += 1
                    q -= 1
        return res
```

### **Java**

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n;
        if (nums == null || (n = nums.length) < 3) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n - 2; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int p = i + 1, q = n - 1;
            while (p < q) {
                if (p > i + 1 && nums[p] == nums[p - 1]) {
                    ++p;
                    continue;
                }
                if (q < n - 1 && nums[q] == nums[q + 1]) {
                    --q;
                    continue;
                }
                if (nums[p] + nums[q] + nums[i] < 0) {
                    ++p;
                } else if (nums[p] + nums[q] + nums[i] > 0) {
                    --q;
                } else {
                    res.add(Arrays.asList(nums[p], nums[q], nums[i]));
                    ++p;
                    --q;
                }
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
