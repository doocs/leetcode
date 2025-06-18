---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2616.Minimize%20the%20Maximum%20Difference%20of%20Pairs/README_EN.md
rating: 2155
source: Weekly Contest 340 Q3
tags:
    - Greedy
    - Array
    - Binary Search
---

<!-- problem:start -->

# [2616. Minimize the Maximum Difference of Pairs](https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs)

[中文文档](/solution/2600-2699/2616.Minimize%20the%20Maximum%20Difference%20of%20Pairs/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and an integer <code>p</code>. Find <code>p</code> pairs of indices of <code>nums</code> such that the <strong>maximum</strong> difference amongst all the pairs is <strong>minimized</strong>. Also, ensure no index appears more than once amongst the <code>p</code> pairs.</p>

<p>Note that for a pair of elements at the index <code>i</code> and <code>j</code>, the difference of this pair is <code>|nums[i] - nums[j]|</code>, where <code>|x|</code> represents the <strong>absolute</strong> <strong>value</strong> of <code>x</code>.</p>

<p>Return <em>the <strong>minimum</strong> <strong>maximum</strong> difference among all </em><code>p</code> <em>pairs.</em> We define the maximum of an empty set to be zero.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,1,2,7,1,3], p = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong> The first pair is formed from the indices 1 and 4, and the second pair is formed from the indices 2 and 5. 
The maximum difference is max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) = max(0, 1) = 1. Therefore, we return 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,2,1,2], p = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> Let the indices 1 and 3 form a pair. The difference of that pair is |2 - 2| = 0, which is the minimum we can attain.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= p &lt;= (nums.length)/2</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search + Greedy

We notice that the maximum difference has monotonicity: if a maximum difference $x$ is feasible, then $x-1$ is also feasible. Therefore, we can use binary search to find the minimal feasible maximum difference.

First, sort the array $\textit{nums}$. Then, for a given maximum difference $x$, check whether it is possible to form $p$ pairs of indices such that the maximum difference in each pair does not exceed $x$. If possible, we can try a smaller $x$; otherwise, we need to increase $x$.

To check whether $p$ such pairs exist with maximum difference at most $x$, we can use a greedy approach. Traverse the sorted array $\textit{nums}$ from left to right. For the current index $i$, if the difference between $\textit{nums}[i+1]$ and $\textit{nums}[i]$ does not exceed $x$, we can form a pair with $i$ and $i+1$, increment the pair count $cnt$, and increase $i$ by $2$. Otherwise, increase $i$ by $1$. After traversing, if $cnt \geq p$, then such $p$ pairs exist; otherwise, they do not.

The time complexity is $O(n \times (\log n + \log m))$, where $n$ is the length of $\textit{nums}$ and $m$ is the difference between the maximum and minimum values in $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimizeMax(self, nums: List[int], p: int) -> int:
        def check(diff: int) -> bool:
            cnt = i = 0
            while i < len(nums) - 1:
                if nums[i + 1] - nums[i] <= diff:
                    cnt += 1
                    i += 2
                else:
                    i += 1
            return cnt >= p

        nums.sort()
        return bisect_left(range(nums[-1] - nums[0] + 1), True, key=check)
```

#### Java

```java
class Solution {
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int n = nums.length;
        int l = 0, r = nums[n - 1] - nums[0] + 1;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (count(nums, mid) >= p) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private int count(int[] nums, int diff) {
        int cnt = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i + 1] - nums[i] <= diff) {
                ++cnt;
                ++i;
            }
        }
        return cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimizeMax(vector<int>& nums, int p) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int l = 0, r = nums[n - 1] - nums[0] + 1;
        auto check = [&](int diff) -> bool {
            int cnt = 0;
            for (int i = 0; i < n - 1; ++i) {
                if (nums[i + 1] - nums[i] <= diff) {
                    ++cnt;
                    ++i;
                }
            }
            return cnt >= p;
        };
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
```

#### Go

```go
func minimizeMax(nums []int, p int) int {
	sort.Ints(nums)
	n := len(nums)
	r := nums[n-1] - nums[0] + 1
	return sort.Search(r, func(diff int) bool {
		cnt := 0
		for i := 0; i < n-1; i++ {
			if nums[i+1]-nums[i] <= diff {
				cnt++
				i++
			}
		}
		return cnt >= p
	})
}
```

#### TypeScript

```ts
function minimizeMax(nums: number[], p: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let l = 0,
        r = nums[n - 1] - nums[0] + 1;
    const check = (diff: number): boolean => {
        let cnt = 0;
        for (let i = 0; i < n - 1; ++i) {
            if (nums[i + 1] - nums[i] <= diff) {
                ++cnt;
                ++i;
            }
        }
        return cnt >= p;
    };
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
```

#### Rust

```rust
impl Solution {
    pub fn minimize_max(mut nums: Vec<i32>, p: i32) -> i32 {
        nums.sort();
        let n = nums.len();
        let (mut l, mut r) = (0, nums[n - 1] - nums[0] + 1);

        let check = |diff: i32| -> bool {
            let mut cnt = 0;
            let mut i = 0;
            while i < n - 1 {
                if nums[i + 1] - nums[i] <= diff {
                    cnt += 1;
                    i += 2;
                } else {
                    i += 1;
                }
            }
            cnt >= p
        };

        while l < r {
            let mid = (l + r) / 2;
            if check(mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        l
    }
}
```

#### C#

```cs
public class Solution {
    public int MinimizeMax(int[] nums, int p) {
        Array.Sort(nums);
        int n = nums.Length;
        int l = 0, r = nums[n - 1] - nums[0] + 1;

        bool check(int diff) {
            int cnt = 0;
            for (int i = 0; i < n - 1; ++i) {
                if (nums[i + 1] - nums[i] <= diff) {
                    ++cnt;
                    ++i;
                }
            }
            return cnt >= p;
        }

        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $p
     * @return Integer
     */
    function minimizeMax($nums, $p) {
        sort($nums);
        $n = count($nums);
        $l = 0;
        $r = $nums[$n - 1] - $nums[0] + 1;

        $check = function ($diff) use ($nums, $n, $p) {
            $cnt = 0;
            for ($i = 0; $i < $n - 1; ++$i) {
                if ($nums[$i + 1] - $nums[$i] <= $diff) {
                    ++$cnt;
                    ++$i;
                }
            }
            return $cnt >= $p;
        };

        while ($l < $r) {
            $mid = intdiv($l + $r, 2);
            if ($check($mid)) {
                $r = $mid;
            } else {
                $l = $mid + 1;
            }
        }

        return $l;
    }
}
```

#### Swift

```swift
class Solution {
    func minimizeMax(_ nums: [Int], _ p: Int) -> Int {
        var nums = nums.sorted()
        let n = nums.count
        var l = 0
        var r = nums[n - 1] - nums[0] + 1

        func check(_ diff: Int) -> Bool {
            var cnt = 0
            var i = 0
            while i < n - 1 {
                if nums[i + 1] - nums[i] <= diff {
                    cnt += 1
                    i += 2
                } else {
                    i += 1
                }
            }
            return cnt >= p
        }

        while l < r {
            let mid = (l + r) >> 1
            if check(mid) {
                r = mid
            } else {
                l = mid + 1
            }
        }

        return l
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
