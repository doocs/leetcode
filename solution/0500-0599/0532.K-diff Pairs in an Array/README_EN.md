# [532. K-diff Pairs in an Array](https://leetcode.com/problems/k-diff-pairs-in-an-array)

[中文文档](/solution/0500-0599/0532.K-diff%20Pairs%20in%20an%20Array/README.md)

## Description

<p>Given an array of integers <code>nums</code> and an integer <code>k</code>, return <em>the number of <b>unique</b> k-diff pairs in the array</em>.</p>

<p>A <strong>k-diff</strong> pair is an integer pair <code>(nums[i], nums[j])</code>, where the following are true:</p>

<ul>
	<li><code>0 &lt;= i, j &lt; nums.length</code></li>
	<li><code>i != j</code></li>
	<li><code>nums[i] - nums[j] == k</code></li>
</ul>

<p><strong>Notice</strong> that <code>|val|</code> denotes the absolute value of <code>val</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,4,1,5], k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of <strong>unique</strong> pairs.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5], k = 1
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,1,5,4], k = 0
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is one 0-diff pair in the array, (1, 1).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>7</sup> &lt;= nums[i] &lt;= 10<sup>7</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>7</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findPairs(self, nums: List[int], k: int) -> int:
        vis, ans = set(), set()
        for v in nums:
            if v - k in vis:
                ans.add(v - k)
            if v + k in vis:
                ans.add(v)
            vis.add(v)
        return len(ans)
```

### **Java**

```java
class Solution {
    public int findPairs(int[] nums, int k) {
        Set<Integer> vis = new HashSet<>();
        Set<Integer> ans = new HashSet<>();
        for (int v : nums) {
            if (vis.contains(v - k)) {
                ans.add(v - k);
            }
            if (vis.contains(v + k)) {
                ans.add(v);
            }
            vis.add(v);
        }
        return ans.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findPairs(vector<int>& nums, int k) {
        unordered_set<int> vis;
        unordered_set<int> ans;
        for (int& v : nums) {
            if (vis.count(v - k)) ans.insert(v - k);
            if (vis.count(v + k)) ans.insert(v);
            vis.insert(v);
        }
        return ans.size();
    }
};
```

### **Go**

```go
func findPairs(nums []int, k int) int {
	vis := map[int]bool{}
	ans := map[int]bool{}
	for _, v := range nums {
		if vis[v-k] {
			ans[v-k] = true
		}
		if vis[v+k] {
			ans[v] = true
		}
		vis[v] = true
	}
	return len(ans)
}
```

### **Rust**

```rust
impl Solution {
    pub fn find_pairs(mut nums: Vec<i32>, k: i32) -> i32 {
        nums.sort();
        let n = nums.len();
        let mut res = 0;
        let mut left = 0;
        let mut right = 1;
        while right < n {
            let num = i32::abs(nums[left] - nums[right]);
            if num == k {
                res += 1;
            }
            if num <= k {
                right += 1;
                while right < n && nums[right - 1] == nums[right] {
                    right += 1;
                }
            } else {
                left += 1;
                while left < right && nums[left - 1] == nums[left] {
                    left += 1;
                }
                if left == right {
                    right += 1;
                }
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
