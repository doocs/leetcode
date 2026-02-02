---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3507.Minimum%20Pair%20Removal%20to%20Sort%20Array%20I/README_EN.md
rating: 1348
source: Weekly Contest 444 Q1
tags:
    - Array
    - Hash Table
    - Linked List
    - Doubly-Linked List
    - Ordered Set
    - Simulation
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3507. Minimum Pair Removal to Sort Array I](https://leetcode.com/problems/minimum-pair-removal-to-sort-array-i)

[中文文档](/solution/3500-3599/3507.Minimum%20Pair%20Removal%20to%20Sort%20Array%20I/README.md)

## Description

<!-- description:start -->

<p>Given an array <code>nums</code>, you can perform the following operation any number of times:</p>

<ul>
	<li>Select the <strong>adjacent</strong> pair with the <strong>minimum</strong> sum in <code>nums</code>. If multiple such pairs exist, choose the leftmost one.</li>
	<li>Replace the pair with their sum.</li>
</ul>

<p>Return the <strong>minimum number of operations</strong> needed to make the array <strong>non-decreasing</strong>.</p>

<p>An array is said to be <strong>non-decreasing</strong> if each element is greater than or equal to its previous element (if it exists).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,2,3,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The pair <code>(3,1)</code> has the minimum sum of 4. After replacement, <code>nums = [5,2,4]</code>.</li>
	<li>The pair <code>(2,4)</code> has the minimum sum of 6. After replacement, <code>nums = [5,6]</code>.</li>
</ul>

<p>The array <code>nums</code> became non-decreasing in two operations.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The array <code>nums</code> is already sorted.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We define a function $\text{is\_non\_decreasing}(a)$ to determine whether the array $a$ is a non-decreasing array.

We use a loop until the array $arr$ becomes a non-decreasing array. In each iteration of the loop, we find the minimum sum of adjacent element pairs in the array $arr$ and record the index $k$ of the left element of that pair. Then, we replace the left element with the sum of the pair and remove the right element. Finally, we return the number of operations.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumPairRemoval(self, nums: List[int]) -> int:
        arr = nums[:]
        ans = 0

        def is_non_decreasing(a: List[int]) -> bool:
            for i in range(1, len(a)):
                if a[i] < a[i - 1]:
                    return False
            return True

        while not is_non_decreasing(arr):
            k = 0
            s = arr[0] + arr[1]
            for i in range(1, len(arr) - 1):
                t = arr[i] + arr[i + 1]
                if s > t:
                    s = t
                    k = i
            arr[k] = s
            arr.pop(k + 1)
            ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int minimumPairRemoval(int[] nums) {
        List<Integer> arr = new ArrayList<>();
        for (int x : nums) {
            arr.add(x);
        }
        int ans = 0;
        while (!isNonDecreasing(arr)) {
            int k = 0;
            int s = arr.get(0) + arr.get(1);
            for (int i = 1; i < arr.size() - 1; ++i) {
                int t = arr.get(i) + arr.get(i + 1);
                if (s > t) {
                    s = t;
                    k = i;
                }
            }
            arr.set(k, s);
            arr.remove(k + 1);
            ++ans;
        }
        return ans;
    }

    private boolean isNonDecreasing(List<Integer> arr) {
        for (int i = 1; i < arr.size(); ++i) {
            if (arr.get(i) < arr.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumPairRemoval(vector<int>& nums) {
        vector<int> arr = nums;
        int ans = 0;

        while (!isNonDecreasing(arr)) {
            int k = 0;
            int s = arr[0] + arr[1];

            for (int i = 1; i < arr.size() - 1; ++i) {
                int t = arr[i] + arr[i + 1];
                if (s > t) {
                    s = t;
                    k = i;
                }
            }

            arr[k] = s;
            arr.erase(arr.begin() + (k + 1));
            ++ans;
        }

        return ans;
    }

private:
    bool isNonDecreasing(const vector<int>& arr) {
        for (int i = 1; i < (int) arr.size(); ++i) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func minimumPairRemoval(nums []int) int {
	arr := append([]int(nil), nums...)
	ans := 0

	isNonDecreasing := func(a []int) bool {
		for i := 1; i < len(a); i++ {
			if a[i] < a[i-1] {
				return false
			}
		}
		return true
	}

	for !isNonDecreasing(arr) {
		k := 0
		s := arr[0] + arr[1]

		for i := 1; i < len(arr)-1; i++ {
			t := arr[i] + arr[i+1]
			if s > t {
				s = t
				k = i
			}
		}

		arr[k] = s
		copy(arr[k+1:], arr[k+2:])
		arr = arr[:len(arr)-1]
		ans++
	}

	return ans
}
```

#### TypeScript

```ts
function minimumPairRemoval(nums: number[]): number {
    const arr = nums.slice();
    let ans = 0;
    const isNonDecreasing = (a: number[]): boolean => {
        for (let i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                return false;
            }
        }
        return true;
    };
    while (!isNonDecreasing(arr)) {
        let k = 0;
        let s = arr[0] + arr[1];
        for (let i = 1; i < arr.length - 1; ++i) {
            const t = arr[i] + arr[i + 1];
            if (s > t) {
                s = t;
                k = i;
            }
        }
        arr[k] = s;
        arr.splice(k + 1, 1);
        ans++;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn minimum_pair_removal(nums: Vec<i32>) -> i32 {
        let mut arr: Vec<i32> = nums.clone();
        let mut ans: i32 = 0;

        fn is_non_decreasing(a: &Vec<i32>) -> bool {
            for i in 1..a.len() {
                if a[i] < a[i - 1] {
                    return false;
                }
            }
            true
        }

        while !is_non_decreasing(&arr) {
            let mut k: usize = 0;
            let mut s: i32 = arr[0] + arr[1];
            for i in 1..arr.len() - 1 {
                let t: i32 = arr[i] + arr[i + 1];
                if s > t {
                    s = t;
                    k = i;
                }
            }
            arr[k] = s;
            arr.remove(k + 1);
            ans += 1;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
