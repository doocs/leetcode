---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3940.Limit%20Occurrences%20in%20Sorted%20Array/README_EN.md
---

<!-- problem:start -->

# [3940. Limit Occurrences in Sorted Array](https://leetcode.com/problems/limit-occurrences-in-sorted-array)

## Description

<!-- description:start -->

<p>You are given a sorted integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>Return an array such that each distinct element appears at most <code>k</code> times, while preserving the relative order of the elements in <code>nums</code>.</p>

<p>Note: If a distinct element appears at least <code>k</code> times, then it must appear exactly <code>k</code> times in the resulting array.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<div class="example-block">

<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1,2,2,3], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,1,2,2,3]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
<li>The element <code>1</code> appears 3 times, so only 2 occurrences are kept.</li>
<li>The element <code>2</code> appears 2 times, so both occurrences are kept.</li>
<li>The element <code>3</code> appears 1 time, so it is kept.</li>
</ul>

<p>Thus, the resulting array is <code>[1,1,2,2,3]</code>.</p>

</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">

<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,3]</span></p>

<p><strong>Explanation:</strong></p>

<p>All elements are distinct and already appear at most once, so the array remains unchanged.</p>

</div>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
<li><code>1 <= nums.length <= 100</code></li>
<li><code>1 <= nums[i] <= 100</code></li>
<li><code>nums</code> is sorted in non-decreasing order.</li>
<li><code>1 <= k <= nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting Consecutive Elements

Since the array is sorted, duplicate elements appear consecutively.

We maintain a counter to track occurrences of the current element.

If the occurrence count is less than or equal to <code>k</code>, we add the element to the result list.

The time complexity is <code>O(n)</code>, where <code>n</code> is the length of the array.

The space complexity is <code>O(n)</code>.

<!-- tabs:start -->

#### Java

```java
class Solution {
    public int[] limitOccurrences(int[] nums, int k) {

        List<Integer> list = new ArrayList<>();

        int count = 1;

        list.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }

            if (count <= k) {
                list.add(nums[i]);
            }
        }

        int[] ans = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
