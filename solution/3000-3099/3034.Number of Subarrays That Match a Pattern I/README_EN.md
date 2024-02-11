# [3034. Number of Subarrays That Match a Pattern I](https://leetcode.com/problems/number-of-subarrays-that-match-a-pattern-i)

[中文文档](/solution/3000-3099/3034.Number%20of%20Subarrays%20That%20Match%20a%20Pattern%20I/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of size <code>n</code>, and a <strong>0-indexed</strong> integer array <code>pattern</code> of size <code>m</code> consisting of integers <code>-1</code>, <code>0</code>, and <code>1</code>.</p>

<p>A <span data-keyword="subarray">subarray</span> <code>nums[i..j]</code> of size <code>m + 1</code> is said to match the <code>pattern</code> if the following conditions hold for each element <code>pattern[k]</code>:</p>

<ul>
	<li><code>nums[i + k + 1] &gt; nums[i + k]</code> if <code>pattern[k] == 1</code>.</li>
	<li><code>nums[i + k + 1] == nums[i + k]</code> if <code>pattern[k] == 0</code>.</li>
	<li><code>nums[i + k + 1] &lt; nums[i + k]</code> if <code>pattern[k] == -1</code>.</li>
</ul>

<p>Return <em>the<strong> count</strong> of subarrays in</em> <code>nums</code> <em>that match the</em> <code>pattern</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5,6], pattern = [1,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The pattern [1,1] indicates that we are looking for strictly increasing subarrays of size 3. In the array nums, the subarrays [1,2,3], [2,3,4], [3,4,5], and [4,5,6] match this pattern.
Hence, there are 4 subarrays in nums that match the pattern.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,4,1,3,5,5,3], pattern = [1,0,-1]
<strong>Output:</strong> 2
<strong>Explanation: </strong>Here, the pattern [1,0,-1] indicates that we are looking for a sequence where the first number is smaller than the second, the second is equal to the third, and the third is greater than the fourth. In the array nums, the subarrays [1,4,4,1], and [3,5,5,3] match this pattern.
Hence, there are 2 subarrays in nums that match the pattern.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m == pattern.length &lt; n</code></li>
	<li><code>-1 &lt;= pattern[i] &lt;= 1</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def countMatchingSubarrays(self, nums: List[int], pattern: List[int]) -> int:
        n = len(nums)
        m = len(pattern)
        count = 0
        for i in range(n - m):
            flag = True
            for j in range(m):
                if (
                    (pattern[j] == 1 and nums[i + j + 1] <= nums[i + j])
                    or (pattern[j] == 0 and nums[i + j + 1] != nums[i + j])
                    or (pattern[j] == -1 and nums[i + j + 1] >= nums[i + j])
                ):
                    flag = False
                    break
            if flag:
                count += 1
        return count
```

```java
class Solution {
    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int n = nums.length;
        int m = pattern.length;
        int count = 0;
        for (int i = 0; i <= n - m - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if ((pattern[j] == 1 && nums[i + j + 1] <= nums[i + j]) ||
                    (pattern[j] == 0 && nums[i + j + 1] != nums[i + j]) ||
                    (pattern[j] == -1 && nums[i + j + 1] >= nums[i + j])) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
            }
        }
        return count;
    }
}
```

```cpp
class Solution {
public:
    int countMatchingSubarrays(vector<int>& nums, vector<int>& pattern) {
        int n = nums.size();
        int m = pattern.size();
        int c = 0;
        for (int i = 0; i <= n - m - 1; i++) {
            bool flag = true;
            for (int j = 0; j < m; j++) {
                if ((pattern[j] == 1 && nums[i + j + 1] <= nums[i + j]) ||
                    (pattern[j] == 0 && nums[i + j + 1] != nums[i + j]) ||
                    (pattern[j] == -1 && nums[i + j + 1] >= nums[i + j])) {
                        flag = false;
                        break;
                }
            }
            if (flag) {
                c++;
            }
        }
        return c;
    }
};
```

```go
func countMatchingSubarrays(nums []int, pattern []int) int {
	n := len(nums)
	m := len(pattern)
	count := 0
	for i := 0; i <= n-m-1; i++ {
		flag := true 
		for j := 0; j < m; j++ {
			if (pattern[j] == 1 && nums[i+j+1] <= nums[i+j]) ||
				(pattern[j] == 0 && nums[i+j+1] != nums[i+j]) ||
				(pattern[j] == -1 && nums[i+j+1] >= nums[i+j]) {
				flag = false
				break
			}
		}
		if flag {
			count++
		}
	}
	return count
}
```

```ts
function countMatchingSubarrays(nums: number[], pattern: number[]): number {
    const n: number = nums.length;
    const m: number = pattern.length;
    let count: number = 0;
    for (let i = 0; i <= n - m - 1; i++) {
        let flag: boolean = true;
        for (let j = 0; j < m; j++) {
            if ((pattern[j] === 1 && nums[i + j + 1] <= nums[i + j]) ||
                (pattern[j] === 0 && nums[i + j + 1] !== nums[i + j]) ||
                (pattern[j] === -1 && nums[i + j + 1] >= nums[i + j])) {
                flag = false;
                break;
            }
        }
        if (flag) {
            count++;
        }
    }
    return count;
}
```

<!-- tabs:end -->

<!-- end -->
