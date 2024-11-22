---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3357.Minimize%20the%20Maximum%20Adjacent%20Element%20Difference/README_EN.md
tags:
    - Greedy
    - Array
    - Binary Search
---

<!-- problem:start -->

# [3357. Minimize the Maximum Adjacent Element Difference](https://leetcode.com/problems/minimize-the-maximum-adjacent-element-difference)

[中文文档](/solution/3300-3399/3357.Minimize%20the%20Maximum%20Adjacent%20Element%20Difference/README.md)

## Description

<p>You are given an array of integers <code>nums</code>. Some values in <code>nums</code> are <strong>missing</strong> and are denoted by -1.</p>

<p>You can choose a pair of <strong>positive</strong> integers <code>(x, y)</code> <strong>exactly once</strong> and replace each&nbsp;<strong>missing</strong> element with <em>either</em> <code>x</code> or <code>y</code>.</p>

<p>You need to <strong>minimize</strong><strong> </strong>the<strong> maximum</strong> <strong>absolute difference</strong> between <em>adjacent</em> elements of <code>nums</code> after replacements.</p>

<p>Return the <strong>minimum</strong> possible difference.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,-1,10,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>By choosing the pair as <code>(6, 7)</code>, nums can be changed to <code>[1, 2, 6, 10, 8]</code>.</p>

<p>The absolute differences between adjacent elements are:</p>

<ul>
	<li><code>|1 - 2| == 1</code></li>
	<li><code>|2 - 6| == 4</code></li>
	<li><code>|6 - 10| == 4</code></li>
	<li><code>|10 - 8| == 2</code></li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-1,-1,-1]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>By choosing the pair as <code>(4, 4)</code>, nums can be changed to <code>[4, 4, 4]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-1,10,-1,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>By choosing the pair as <code>(11, 9)</code>, nums can be changed to <code>[11, 10, 9, 8]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> is either -1 or in the range <code>[1, 10<sup>9</sup>]</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

#### Approach:

1. **Greedy Replacement Strategy**:

    - Traverse the array and determine the missing (-1) positions.
    - Identify the potential minimum and maximum values needed to replace the missing positions such that the absolute difference between adjacent elements is minimized.
    - Use binary search to minimize the maximum absolute difference.

2. **Binary Search for Optimization**:
    - Apply binary search to determine the best pair `(x, y)` that minimizes the maximum adjacent difference.

#### Python3

```python
def minimize_max_diff(nums):
    def is_valid(max_diff, x, y):
        prev = nums[0] if nums[0] != -1 else x
        for i in range(1, len(nums)):
            current = nums[i]
            if current == -1:
                current = x if abs(prev - x) <= max_diff else y
            if abs(current - prev) > max_diff:
                return False
            prev = current
        return True

    missing_positions = [i for i, val in enumerate(nums) if val == -1]

    left, right = 0, 10**9
    result = 10**9

    while left <= right:
        mid = (left + right) // 2
        x, y = mid, mid + 1  # Candidates for missing values
        if is_valid(mid, x, y):
            result = mid
            right = mid - 1
        else:
            left = mid + 1

    return result
```

#### Java

```java
public class MinimizeMaxDifference {
    public int minimizeMaxDiff(int[] nums) {
        int left = 0, right = (int) 1e9, result = (int) 1e9;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isValid(nums, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    private boolean isValid(int[] nums, int maxDiff) {
        int prev = nums[0] != -1 ? nums[0] : -1;

        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            if (current == -1) {
                if (prev != -1) {
                    current = Math.max(prev - maxDiff, 1); // Ensure values are positive
                } else {
                    current = 1; // Default to a positive value
                }
            }
            if (prev != -1 && Math.abs(current - prev) > maxDiff) {
                return false;
            }
            prev = current;
        }
        return true;
    }

    public static void main(String[] args) {
        MinimizeMaxDifference solver = new MinimizeMaxDifference();
        int[] nums1 = {1, 2, -1, 10, 8};
        System.out.println(solver.minimizeMaxDiff(nums1)); // Output: 4

        int[] nums2 = {-1, -1, -1};
        System.out.println(solver.minimizeMaxDiff(nums2)); // Output: 0

        int[] nums3 = {-1, 10, -1, 8};
        System.out.println(solver.minimizeMaxDiff(nums3)); // Output: 1
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimizeMaxDifference(vector<int>& nums, int k) {
        int n = nums.size();
        sort(nums.begin(), nums.end());

        int l = 0, r = nums[n - 1] - nums[0];
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (canMinimize(nums, k, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

private:
    bool canMinimize(vector<int>& nums, int k, int target) {
        int ops = 0;
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] - nums[i - 1] > target) {
                ops += (nums[i] - nums[i - 1] - 1) / target;
                if (ops > k) return false;
            }
        }
        return true;
    }
};

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
