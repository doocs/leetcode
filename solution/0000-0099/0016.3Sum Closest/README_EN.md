# [16. 3Sum Closest](https://leetcode.com/problems/3sum-closest)

[中文文档](/solution/0000-0099/0016.3Sum%20Closest/README.md)

<!-- tags:Array,Two Pointers,Sorting -->

## Description

<p>Given an integer array <code>nums</code> of length <code>n</code> and an integer <code>target</code>, find three integers in <code>nums</code> such that the sum is closest to <code>target</code>.</p>

<p>Return <em>the sum of the three integers</em>.</p>

<p>You may assume that each input would have exactly one solution.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,2,1,-4], target = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,0], target = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 500</code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

### Solution 1: Sorting + Two Pointers

We sort the array first, then traverse the array. For each element $nums[i]$, we use pointers $j$ and $k$ to point to $i+1$ and $n-1$ respectively, calculate the sum of the three numbers. If the sum of the three numbers equals $target$, we directly return $target$. Otherwise, we update the answer based on the difference from $target$. If the sum of the three numbers is greater than $target$, we move $k$ one place to the left, otherwise, we move $j$ one place to the right.

The time complexity is $O(n^2)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

```python
class Solution:
    def threeSumClosest(self, nums: List[int], target: int) -> int:
        nums.sort()
        n = len(nums)
        ans = inf
        for i, v in enumerate(nums):
            j, k = i + 1, n - 1
            while j < k:
                t = v + nums[j] + nums[k]
                if t == target:
                    return t
                if abs(t - target) < abs(ans - target):
                    ans = t
                if t > target:
                    k -= 1
                else:
                    j += 1
        return ans
```

```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 1 << 30;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                int t = nums[i] + nums[j] + nums[k];
                if (t == target) {
                    return t;
                }
                if (Math.abs(t - target) < Math.abs(ans - target)) {
                    ans = t;
                }
                if (t > target) {
                    --k;
                } else {
                    ++j;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());
        int ans = 1 << 30;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                int t = nums[i] + nums[j] + nums[k];
                if (t == target) return t;
                if (abs(t - target) < abs(ans - target)) ans = t;
                if (t > target)
                    --k;
                else
                    ++j;
            }
        }
        return ans;
    }
};
```

```go
func threeSumClosest(nums []int, target int) int {
	sort.Ints(nums)
	ans := 1 << 30
	n := len(nums)
	for i, v := range nums {
		j, k := i+1, n-1
		for j < k {
			t := v + nums[j] + nums[k]
			if t == target {
				return t
			}
			if abs(t-target) < abs(ans-target) {
				ans = t
			}
			if t > target {
				k--
			} else {
				j++
			}
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function threeSumClosest(nums: number[], target: number): number {
    nums.sort((a, b) => a - b);
    let ans: number = 1 << 30;
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        let j = i + 1;
        let k = n - 1;
        while (j < k) {
            const t: number = nums[i] + nums[j] + nums[k];
            if (t === target) {
                return t;
            }
            if (Math.abs(t - target) < Math.abs(ans - target)) {
                ans = t;
            }
            if (t > target) {
                --k;
            } else {
                ++j;
            }
        }
    }
    return ans;
}
```

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var threeSumClosest = function (nums, target) {
    nums.sort((a, b) => a - b);
    let ans = 1 << 30;
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        let j = i + 1;
        let k = n - 1;
        while (j < k) {
            const t = nums[i] + nums[j] + nums[k];
            if (t === target) {
                return t;
            }
            if (Math.abs(t - target) < Math.abs(ans - target)) {
                ans = t;
            }
            if (t > target) {
                --k;
            } else {
                ++j;
            }
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- end -->
