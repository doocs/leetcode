---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0321.Create%20Maximum%20Number/README_EN.md
tags:
    - Stack
    - Greedy
    - Array
    - Two Pointers
    - Monotonic Stack
---

<!-- problem:start -->

# [321. Create Maximum Number](https://leetcode.com/problems/create-maximum-number)

[中文文档](/solution/0300-0399/0321.Create%20Maximum%20Number/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>nums1</code> and <code>nums2</code> of lengths <code>m</code> and <code>n</code> respectively. <code>nums1</code> and <code>nums2</code> represent the digits of two numbers. You are also given an integer <code>k</code>.</p>

<p>Create the maximum number of length <code>k &lt;= m + n</code> from digits of the two numbers. The relative order of the digits from the same array must be preserved.</p>

<p>Return an array of the <code>k</code> digits representing the answer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [3,4,6,5], nums2 = [9,1,2,5,8,3], k = 5
<strong>Output:</strong> [9,8,6,5,3]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [6,7], nums2 = [6,0,4], k = 5
<strong>Output:</strong> [6,7,6,0,4]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [3,9], nums2 = [8,9], k = 3
<strong>Output:</strong> [9,8,9]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == nums1.length</code></li>
	<li><code>n == nums2.length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 9</code></li>
	<li><code>1 &lt;= k &lt;= m + n</code></li>
	<li><code>nums1</code> and <code>nums2</code> do not have leading zeros.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxNumber(self, nums1: List[int], nums2: List[int], k: int) -> List[int]:
        def f(nums: List[int], k: int) -> List[int]:
            n = len(nums)
            stk = [0] * k
            top = -1
            remain = n - k
            for x in nums:
                while top >= 0 and stk[top] < x and remain > 0:
                    top -= 1
                    remain -= 1
                if top + 1 < k:
                    top += 1
                    stk[top] = x
                else:
                    remain -= 1
            return stk

        def compare(nums1: List[int], nums2: List[int], i: int, j: int) -> bool:
            if i >= len(nums1):
                return False
            if j >= len(nums2):
                return True
            if nums1[i] > nums2[j]:
                return True
            if nums1[i] < nums2[j]:
                return False
            return compare(nums1, nums2, i + 1, j + 1)

        def merge(nums1: List[int], nums2: List[int]) -> List[int]:
            m, n = len(nums1), len(nums2)
            i = j = 0
            ans = [0] * (m + n)
            for k in range(m + n):
                if compare(nums1, nums2, i, j):
                    ans[k] = nums1[i]
                    i += 1
                else:
                    ans[k] = nums2[j]
                    j += 1
            return ans

        m, n = len(nums1), len(nums2)
        l, r = max(0, k - n), min(k, m)
        ans = [0] * k
        for x in range(l, r + 1):
            arr1 = f(nums1, x)
            arr2 = f(nums2, k - x)
            arr = merge(arr1, arr2)
            if ans < arr:
                ans = arr
        return ans
```

#### Java

```java
class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int l = Math.max(0, k - n), r = Math.min(k, m);
        int[] ans = new int[k];
        for (int x = l; x <= r; ++x) {
            int[] arr1 = f(nums1, x);
            int[] arr2 = f(nums2, k - x);
            int[] arr = merge(arr1, arr2);
            if (compare(arr, ans, 0, 0)) {
                ans = arr;
            }
        }
        return ans;
    }

    private int[] f(int[] nums, int k) {
        int n = nums.length;
        int[] stk = new int[k];
        int top = -1;
        int remain = n - k;
        for (int x : nums) {
            while (top >= 0 && stk[top] < x && remain > 0) {
                --top;
                --remain;
            }
            if (top + 1 < k) {
                stk[++top] = x;
            } else {
                --remain;
            }
        }
        return stk;
    }

    private int[] merge(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int i = 0, j = 0;
        int[] ans = new int[m + n];
        for (int k = 0; k < m + n; ++k) {
            if (compare(nums1, nums2, i, j)) {
                ans[k] = nums1[i++];
            } else {
                ans[k] = nums2[j++];
            }
        }
        return ans;
    }

    private boolean compare(int[] nums1, int[] nums2, int i, int j) {
        if (i >= nums1.length) {
            return false;
        }
        if (j >= nums2.length) {
            return true;
        }
        if (nums1[i] > nums2[j]) {
            return true;
        }
        if (nums1[i] < nums2[j]) {
            return false;
        }
        return compare(nums1, nums2, i + 1, j + 1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> maxNumber(vector<int>& nums1, vector<int>& nums2, int k) {
        auto f = [](vector<int>& nums, int k) {
            int n = nums.size();
            vector<int> stk(k);
            int top = -1;
            int remain = n - k;
            for (int x : nums) {
                while (top >= 0 && stk[top] < x && remain > 0) {
                    --top;
                    --remain;
                }
                if (top + 1 < k) {
                    stk[++top] = x;
                } else {
                    --remain;
                }
            }
            return stk;
        };
        function<bool(vector<int>&, vector<int>&, int, int)> compare = [&](vector<int>& nums1, vector<int>& nums2, int i, int j) -> bool {
            if (i >= nums1.size()) {
                return false;
            }
            if (j >= nums2.size()) {
                return true;
            }
            if (nums1[i] > nums2[j]) {
                return true;
            }
            if (nums1[i] < nums2[j]) {
                return false;
            }
            return compare(nums1, nums2, i + 1, j + 1);
        };

        auto merge = [&](vector<int>& nums1, vector<int>& nums2) {
            int m = nums1.size(), n = nums2.size();
            int i = 0, j = 0;
            vector<int> ans(m + n);
            for (int k = 0; k < m + n; ++k) {
                if (compare(nums1, nums2, i, j)) {
                    ans[k] = nums1[i++];
                } else {
                    ans[k] = nums2[j++];
                }
            }
            return ans;
        };

        int m = nums1.size(), n = nums2.size();
        int l = max(0, k - n), r = min(k, m);
        vector<int> ans(k);
        for (int x = l; x <= r; ++x) {
            vector<int> arr1 = f(nums1, x);
            vector<int> arr2 = f(nums2, k - x);
            vector<int> arr = merge(arr1, arr2);
            if (ans < arr) {
                ans = move(arr);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxNumber(nums1 []int, nums2 []int, k int) []int {
	m, n := len(nums1), len(nums2)
	l, r := max(0, k-n), min(k, m)
	f := func(nums []int, k int) []int {
		n := len(nums)
		stk := make([]int, k)
		top := -1
		remain := n - k
		for _, x := range nums {
			for top >= 0 && stk[top] < x && remain > 0 {
				top--
				remain--
			}
			if top+1 < k {
				top++
				stk[top] = x
			} else {
				remain--
			}
		}
		return stk
	}

	var compare func(nums1, nums2 []int, i, j int) bool
	compare = func(nums1, nums2 []int, i, j int) bool {
		if i >= len(nums1) {
			return false
		}
		if j >= len(nums2) {
			return true
		}
		if nums1[i] > nums2[j] {
			return true
		}
		if nums1[i] < nums2[j] {
			return false
		}
		return compare(nums1, nums2, i+1, j+1)
	}

	merge := func(nums1, nums2 []int) []int {
		m, n := len(nums1), len(nums2)
		ans := make([]int, m+n)
		i, j := 0, 0
		for k := range ans {
			if compare(nums1, nums2, i, j) {
				ans[k] = nums1[i]
				i++
			} else {
				ans[k] = nums2[j]
				j++
			}
		}
		return ans
	}

	ans := make([]int, k)
	for x := l; x <= r; x++ {
		arr1 := f(nums1, x)
		arr2 := f(nums2, k-x)
		arr := merge(arr1, arr2)
		if compare(arr, ans, 0, 0) {
			ans = arr
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxNumber(nums1: number[], nums2: number[], k: number): number[] {
    const m = nums1.length;
    const n = nums2.length;
    const l = Math.max(0, k - n);
    const r = Math.min(k, m);
    let ans: number[] = Array(k).fill(0);
    for (let x = l; x <= r; ++x) {
        const arr1 = f(nums1, x);
        const arr2 = f(nums2, k - x);
        const arr = merge(arr1, arr2);
        if (compare(arr, ans, 0, 0)) {
            ans = arr;
        }
    }
    return ans;
}

function f(nums: number[], k: number): number[] {
    const n = nums.length;
    const stk: number[] = Array(k).fill(0);
    let top = -1;
    let remain = n - k;
    for (const x of nums) {
        while (top >= 0 && stk[top] < x && remain > 0) {
            --top;
            --remain;
        }
        if (top + 1 < k) {
            stk[++top] = x;
        } else {
            --remain;
        }
    }
    return stk;
}

function compare(nums1: number[], nums2: number[], i: number, j: number): boolean {
    if (i >= nums1.length) {
        return false;
    }
    if (j >= nums2.length) {
        return true;
    }
    if (nums1[i] > nums2[j]) {
        return true;
    }
    if (nums1[i] < nums2[j]) {
        return false;
    }
    return compare(nums1, nums2, i + 1, j + 1);
}

function merge(nums1: number[], nums2: number[]): number[] {
    const m = nums1.length;
    const n = nums2.length;
    const ans: number[] = Array(m + n).fill(0);
    let i = 0;
    let j = 0;
    for (let k = 0; k < m + n; ++k) {
        if (compare(nums1, nums2, i, j)) {
            ans[k] = nums1[i++];
        } else {
            ans[k] = nums2[j++];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
