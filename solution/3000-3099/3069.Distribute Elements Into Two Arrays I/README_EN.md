---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3069.Distribute%20Elements%20Into%20Two%20Arrays%20I/README_EN.md
rating: 1203
source: Weekly Contest 387 Q1
tags:
    - Array
    - Simulation
---

<!-- problem:start -->

# [3069. Distribute Elements Into Two Arrays I](https://leetcode.com/problems/distribute-elements-into-two-arrays-i)

[中文文档](/solution/3000-3099/3069.Distribute%20Elements%20Into%20Two%20Arrays%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>1-indexed</strong> array of <strong>distinct</strong> integers <code>nums</code> of length <code>n</code>.</p>

<p>You need to distribute all the elements of <code>nums</code> between two arrays <code>arr1</code> and <code>arr2</code> using <code>n</code> operations. In the first operation, append <code>nums[1]</code> to <code>arr1</code>. In the second operation, append <code>nums[2]</code> to <code>arr2</code>. Afterwards, in the <code>i<sup>th</sup></code> operation:</p>

<ul>
	<li>If the last element of <code>arr1</code> is<strong> greater</strong> than the last element of <code>arr2</code>, append <code>nums[i]</code> to <code>arr1</code>. Otherwise, append <code>nums[i]</code> to <code>arr2</code>.</li>
</ul>

<p>The array <code>result</code> is formed by concatenating the arrays <code>arr1</code> and <code>arr2</code>. For example, if <code>arr1 == [1,2,3]</code> and <code>arr2 == [4,5,6]</code>, then <code>result = [1,2,3,4,5,6]</code>.</p>

<p>Return <em>the array</em> <code>result</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,3]
<strong>Output:</strong> [2,3,1]
<strong>Explanation:</strong> After the first 2 operations, arr1 = [2] and arr2 = [1].
In the 3<sup>rd</sup> operation, as the last element of arr1 is greater than the last element of arr2 (2 &gt; 1), append nums[3] to arr1.
After 3 operations, arr1 = [2,3] and arr2 = [1].
Hence, the array result formed by concatenation is [2,3,1].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,4,3,8]
<strong>Output:</strong> [5,3,4,8]
<strong>Explanation:</strong> After the first 2 operations, arr1 = [5] and arr2 = [4].
In the 3<sup>rd</sup> operation, as the last element of arr1 is greater than the last element of arr2 (5 &gt; 4), append nums[3] to arr1, hence arr1 becomes [5,3].
In the 4<sup>th</sup> operation, as the last element of arr2 is greater than the last element of arr1 (4 &gt; 3), append nums[4] to arr2, hence arr2 becomes [4,8].
After 4 operations, arr1 = [5,3] and arr2 = [4,8].
Hence, the array result formed by concatenation is [5,3,4,8].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li>All elements in <code>nums</code> are distinct.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We create two arrays `arr1` and `arr2`, which store the elements in `nums`. Initially, `arr1` only contains `nums[0]`, and `arr2` only contains `nums[1]`.

Then we traverse the elements of `nums` starting from index 2. If the last element of `arr1` is greater than the last element of `arr2`, we append the current element to `arr1`, otherwise we append it to `arr2`.

Finally, we append the elements in `arr2` to `arr1` and return `arr1`.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array `nums`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def resultArray(self, nums: List[int]) -> List[int]:
        arr1 = [nums[0]]
        arr2 = [nums[1]]
        for x in nums[2:]:
            if arr1[-1] > arr2[-1]:
                arr1.append(x)
            else:
                arr2.append(x)
        return arr1 + arr2
```

#### Java

```java
class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        arr1[0] = nums[0];
        arr2[0] = nums[1];
        int i = 0, j = 0;
        for (int k = 2; k < n; ++k) {
            if (arr1[i] > arr2[j]) {
                arr1[++i] = nums[k];
            } else {
                arr2[++j] = nums[k];
            }
        }
        for (int k = 0; k <= j; ++k) {
            arr1[++i] = arr2[k];
        }
        return arr1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> resultArray(vector<int>& nums) {
        int n = nums.size();
        vector<int> arr1 = {nums[0]};
        vector<int> arr2 = {nums[1]};
        for (int k = 2; k < n; ++k) {
            if (arr1.back() > arr2.back()) {
                arr1.push_back(nums[k]);
            } else {
                arr2.push_back(nums[k]);
            }
        }
        arr1.insert(arr1.end(), arr2.begin(), arr2.end());
        return arr1;
    }
};
```

#### Go

```go
func resultArray(nums []int) []int {
	arr1 := []int{nums[0]}
	arr2 := []int{nums[1]}
	for _, x := range nums[2:] {
		if arr1[len(arr1)-1] > arr2[len(arr2)-1] {
			arr1 = append(arr1, x)
		} else {
			arr2 = append(arr2, x)
		}
	}
	return append(arr1, arr2...)
}
```

#### TypeScript

```ts
function resultArray(nums: number[]): number[] {
    const arr1: number[] = [nums[0]];
    const arr2: number[] = [nums[1]];
    for (const x of nums.slice(2)) {
        if (arr1.at(-1)! > arr2.at(-1)!) {
            arr1.push(x);
        } else {
            arr2.push(x);
        }
    }
    return arr1.concat(arr2);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
