# [1403. Minimum Subsequence in Non-Increasing Order](https://leetcode.com/problems/minimum-subsequence-in-non-increasing-order)

[中文文档](/solution/1400-1499/1403.Minimum%20Subsequence%20in%20Non-Increasing%20Order/README.md)

## Description

<p>Given the array <code>nums</code>, obtain a subsequence of the array whose sum of elements is <strong>strictly greater</strong> than the sum of the non&nbsp;included elements in such subsequence.&nbsp;</p>

<p>If there are multiple solutions, return the subsequence with <strong>minimum size</strong> and if there still exist multiple solutions, return the subsequence with the <strong>maximum total sum</strong> of all its elements. A subsequence of an array can be obtained by erasing some (possibly zero) elements from the array.&nbsp;</p>

<p>Note that the solution with the given constraints is guaranteed to be&nbsp;<strong>unique</strong>. Also return the answer sorted in <strong>non-increasing</strong> order.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,3,10,9,8]
<strong>Output:</strong> [10,9] 
<strong>Explanation:</strong> The subsequences [10,9] and [10,8] are minimal such that the sum of their elements is strictly greater than the sum of elements not included. However, the subsequence [10,9] has the maximum total sum of its elements.&nbsp;
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,4,7,6,7]
<strong>Output:</strong> [7,7,6] 
<strong>Explanation:</strong> The subsequence [7,7] has the sum of its elements equal to 14 which is not strictly greater than the sum of elements not included (14 = 4 + 4 + 6). Therefore, the subsequence [7,6,7] is the minimal satisfying the conditions. Note the subsequence has to be returned in non-decreasing order.  
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 500</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minSubsequence(self, nums: List[int]) -> List[int]:
        nums.sort(reverse=True)
        s = sum(nums)
        ans = []
        t = 0
        for v in nums:
            ans.append(v)
            t += v
            if t > s - t:
                break
        return ans
```

### **Java**

```java
class Solution {
    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        int s = 0;
        for (int v : nums) {
            s += v;
        }
        int t = 0;
        for (int i = nums.length - 1; i >= 0; --i) {
            t += nums[i];
            ans.add(nums[i]);
            if (t > s - t) {
                break;
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
    vector<int> minSubsequence(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int s = 0;
        for (int v : nums) s += v;
        int t = 0;
        vector<int> ans;
        for (int i = nums.size() - 1; ~i; --i) {
            t += nums[i];
            ans.push_back(nums[i]);
            if (t > s - t) break;
        }
        return ans;
    }
};
```

### **Go**

```go
func minSubsequence(nums []int) []int {
	sort.Ints(nums)
	s, t := 0, 0
	for _, v := range nums {
		s += v
	}
	ans := []int{}
	for i := len(nums) - 1; i >= 0; i-- {
		t += nums[i]
		ans = append(ans, nums[i])
		if t > s-t {
			break
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function minSubsequence(nums: number[]): number[] {
    nums.sort((a, b) => b - a);
    const sum = nums.reduce((r, c) => r + c);
    const res: number[] = [];
    let t = 0;
    for (const num of nums) {
        t += num;
        res.push(num);
        if (t > sum - t) {
            break;
        }
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_subsequence(mut nums: Vec<i32>) -> Vec<i32> {
        nums.sort_by(|a, b| b.cmp(a));
        let sum = nums.iter().sum::<i32>();
        let mut res = vec![];
        let mut t = 0;
        for num in nums.into_iter() {
            t += num;
            res.push(num);
            if t > sum - t {
                break;
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
