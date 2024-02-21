# [41. First Missing Positive](https://leetcode.com/problems/first-missing-positive)

[中文文档](/solution/0000-0099/0041.First%20Missing%20Positive/README.md)

<!-- tags:Array,Hash Table -->

## Description

<p>Given an unsorted integer array <code>nums</code>, return the smallest missing positive integer.</p>

<p>You must implement an algorithm that runs in <code>O(n)</code> time and uses <code>O(1)</code> auxiliary space.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,0]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The numbers in the range [1,2] are all in the array.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,-1,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 1 is in the array but 2 is missing.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [7,8,9,11,12]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The smallest positive integer 1 is missing.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## Solutions

### Solution 1: In-place Swap

We assume the length of the array $nums$ is $n$, then the smallest positive integer must be in the range $[1, .., n + 1]$. We can traverse the array and swap each number $x$ to its correct position, that is, the position $x - 1$. If $x$ is not in the range $[1, n + 1]$, then we can ignore it.

After the traversal, we traverse the array again. If $i+1$ is not equal to $nums[i]$, then $i+1$ is the smallest positive integer we are looking for.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def firstMissingPositive(self, nums: List[int]) -> int:
        def swap(i, j):
            nums[i], nums[j] = nums[j], nums[i]

        n = len(nums)
        for i in range(n):
            while 1 <= nums[i] <= n and nums[i] != nums[nums[i] - 1]:
                swap(i, nums[i] - 1)
        for i in range(n):
            if i + 1 != nums[i]:
                return i + 1
        return n + 1
```

```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            while (nums[i] >= 1 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (i + 1 != nums[i]) {
                return i + 1;
            }
        }
        return n + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

```cpp
class Solution {
public:
    int firstMissingPositive(vector<int>& nums) {
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            while (nums[i] >= 1 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                swap(nums[i], nums[nums[i] - 1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (i + 1 != nums[i]) {
                return i + 1;
            }
        }
        return n + 1;
    }
};
```

```go
func firstMissingPositive(nums []int) int {
	n := len(nums)
	for i := range nums {
		for nums[i] >= 1 && nums[i] <= n && nums[i] != nums[nums[i]-1] {
			nums[i], nums[nums[i]-1] = nums[nums[i]-1], nums[i]
		}
	}
	for i, v := range nums {
		if i+1 != v {
			return i + 1
		}
	}
	return n + 1
}
```

```ts
function firstMissingPositive(nums: number[]): number {
    const n = nums.length;
    let i = 0;
    while (i < n) {
        const j = nums[i] - 1;
        if (j === i || j < 0 || j >= n || nums[i] === nums[j]) {
            i++;
        } else {
            [nums[i], nums[j]] = [nums[j], nums[i]];
        }
    }

    const res = nums.findIndex((v, i) => v !== i + 1);
    return (res === -1 ? n : res) + 1;
}
```

```rust
impl Solution {
    pub fn first_missing_positive(mut nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut i = 0;
        while i < n {
            let j = nums[i] - 1;
            if (i as i32) == j || j < 0 || j >= (n as i32) || nums[i] == nums[j as usize] {
                i += 1;
            } else {
                nums.swap(i, j as usize);
            }
        }
        (
            nums
                .iter()
                .enumerate()
                .position(|(i, &v)| (v as usize) != i + 1)
                .unwrap_or(n) as i32
        ) + 1
    }
}
```

```cs
public class Solution {
    public int FirstMissingPositive(int[] nums) {
        var i = 0;
        while (i < nums.Length)
        {
            if (nums[i] > 0 && nums[i] <= nums.Length)
            {
                var index = nums[i] -1;
                if (index != i && nums[index] != nums[i])
                {
                    var temp = nums[i];
                    nums[i] = nums[index];
                    nums[index] = temp;
                }
                else
                {
                    ++i;
                }
            }
            else
            {
                ++i;
            }
        }

        for (i = 0; i < nums.Length; ++i)
        {
            if (nums[i] != i + 1)
            {
                return i + 1;
            }
        }
        return nums.Length + 1;
    }
}
```

```c
int firstMissingPositive(int* nums, int numsSize) {

    int Max = nums[0], i, *Count;

    for (i = 1; i < numsSize; i++) {
        Max = (Max < nums[i]) ? nums[i] : Max;
    }

    Count = (int*) calloc(Max + 1, sizeof(int));
    for (i = 0; i < numsSize; i++) {
        if (nums[i] > 0) {
            Count[nums[i]]++;
        }
    }

    i = 1;
    while (Count[i] != 0) {
        i++;
    }

    return i;
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```ts
function firstMissingPositive(nums: number[]): number {
    const set = new Set(nums);
    let ans = 1;
    while (true) {
        if (!set.has(ans)) return ans;
        ans++;
    }
}
```

<!-- tabs:end -->

<!-- end -->
