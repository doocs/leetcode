---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3134.Find%20the%20Median%20of%20the%20Uniqueness%20Array/README_EN.md
rating: 2451
source: Weekly Contest 395 Q4
tags:
    - Array
    - Hash Table
    - Binary Search
    - Sliding Window
---

<!-- problem:start -->

# [3134. Find the Median of the Uniqueness Array](https://leetcode.com/problems/find-the-median-of-the-uniqueness-array)

[中文文档](/solution/3100-3199/3134.Find%20the%20Median%20of%20the%20Uniqueness%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>. The <strong>uniqueness array</strong> of <code>nums</code> is the sorted array that contains the number of distinct elements of all the <span data-keyword="subarray-nonempty">subarrays</span> of <code>nums</code>. In other words, it is a sorted array consisting of <code>distinct(nums[i..j])</code>, for all <code>0 &lt;= i &lt;= j &lt; nums.length</code>.</p>

<p>Here, <code>distinct(nums[i..j])</code> denotes the number of distinct elements in the subarray that starts at index <code>i</code> and ends at index <code>j</code>.</p>

<p>Return the <strong>median</strong> of the <strong>uniqueness array</strong> of <code>nums</code>.</p>

<p><strong>Note</strong> that the <strong>median</strong> of an array is defined as the middle element of the array when it is sorted in non-decreasing order. If there are two choices for a median, the <strong>smaller</strong> of the two values is taken.<!-- notionvc: 7e0f5178-4273-4a82-95ce-3395297921dc --></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The uniqueness array of <code>nums</code> is <code>[distinct(nums[0..0]), distinct(nums[1..1]), distinct(nums[2..2]), distinct(nums[0..1]), distinct(nums[1..2]), distinct(nums[0..2])]</code> which is equal to <code>[1, 1, 1, 2, 2, 3]</code>. The uniqueness array has a median of 1. Therefore, the answer is 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,4,3,4,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The uniqueness array of <code>nums</code> is <code>[1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3]</code>. The uniqueness array has a median of 2. Therefore, the answer is 2.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,3,5,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The uniqueness array of <code>nums</code> is <code>[1, 1, 1, 1, 2, 2, 2, 3, 3, 3]</code>. The uniqueness array has a median of 2. Therefore, the answer is 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search + Two Pointers

Let the length of the array $\textit{nums}$ be $n$. The length of the uniqueness array is $m = \frac{(1 + n) \times n}{2}$, and the median of the uniqueness array is the $\frac{m + 1}{2}$-th smallest number among these $m$ numbers.

Consider how many numbers in the uniqueness array are less than or equal to $x$. As $x$ increases, there will be more and more numbers less than or equal to $x$. This property is monotonic, so we can use binary search to enumerate $x$ and find the first $x$ such that the number of elements in the uniqueness array less than or equal to $x$ is greater than or equal to $\frac{m + 1}{2}$. This $x$ is the median of the uniqueness array.

We define the left boundary of the binary search as $l = 0$ and the right boundary as $r = n$. Then we perform binary search. For each $\textit{mid}$, we check whether the number of elements in the uniqueness array less than or equal to $\textit{mid}$ is greater than or equal to $\frac{m + 1}{2}$. We achieve this through the function $\text{check}(mx)$.

The implementation idea of the function $\text{check}(mx)$ is as follows:

Since the longer the subarray, the more different elements it contains, we can use two pointers to maintain a sliding window such that the number of different elements in the window does not exceed $mx$. Specifically, we maintain a hash table $\textit{cnt}$, where $\textit{cnt}[x]$ represents the number of occurrences of element $x$ in the window. We use two pointers $l$ and $r$, where $l$ represents the left boundary of the window and $r$ represents the right boundary. Initially, $l = r = 0$.

We enumerate $r$. For each $r$, we add $\textit{nums}[r]$ to the window and update $\textit{cnt}[\textit{nums}[r]]$. If the number of different elements in the window exceeds $mx$, we need to move $l$ to the right until the number of different elements in the window does not exceed $mx$. At this point, the subarrays with the right endpoint $r$ and left endpoints in the range $[l, .., r]$ all meet the condition, and there are $r - l + 1$ such subarrays. We accumulate this count into $k$. If $k$ is greater than or equal to $\frac{m + 1}{2}$, it means that the number of elements in the uniqueness array less than or equal to $\textit{mid}$ is greater than or equal to $\frac{m + 1}{2}$, and we return $\text{true}$; otherwise, we return $\text{false}$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def medianOfUniquenessArray(self, nums: List[int]) -> int:
        def check(mx: int) -> bool:
            cnt = defaultdict(int)
            k = l = 0
            for r, x in enumerate(nums):
                cnt[x] += 1
                while len(cnt) > mx:
                    y = nums[l]
                    cnt[y] -= 1
                    if cnt[y] == 0:
                        cnt.pop(y)
                    l += 1
                k += r - l + 1
                if k >= (m + 1) // 2:
                    return True
            return False

        n = len(nums)
        m = (1 + n) * n // 2
        return bisect_left(range(n), True, key=check)
```

#### Java

```java
class Solution {
    private long m;
    private int[] nums;

    public int medianOfUniquenessArray(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        m = (1L + n) * n / 2;
        int l = 0, r = n;
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

    private boolean check(int mx) {
        Map<Integer, Integer> cnt = new HashMap<>();
        long k = 0;
        for (int l = 0, r = 0; r < nums.length; ++r) {
            int x = nums[r];
            cnt.merge(x, 1, Integer::sum);
            while (cnt.size() > mx) {
                int y = nums[l++];
                if (cnt.merge(y, -1, Integer::sum) == 0) {
                    cnt.remove(y);
                }
            }
            k += r - l + 1;
            if (k >= (m + 1) / 2) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int medianOfUniquenessArray(vector<int>& nums) {
        int n = nums.size();
        using ll = long long;
        ll m = (1LL + n) * n / 2;
        int l = 0, r = n;
        auto check = [&](int mx) -> bool {
            unordered_map<int, int> cnt;
            ll k = 0;
            for (int l = 0, r = 0; r < n; ++r) {
                int x = nums[r];
                ++cnt[x];
                while (cnt.size() > mx) {
                    int y = nums[l++];
                    if (--cnt[y] == 0) {
                        cnt.erase(y);
                    }
                }
                k += r - l + 1;
                if (k >= (m + 1) / 2) {
                    return true;
                }
            }
            return false;
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
func medianOfUniquenessArray(nums []int) int {
	n := len(nums)
	m := (1 + n) * n / 2
	return sort.Search(n, func(mx int) bool {
		cnt := map[int]int{}
		l, k := 0, 0
		for r, x := range nums {
			cnt[x]++
			for len(cnt) > mx {
				y := nums[l]
				cnt[y]--
				if cnt[y] == 0 {
					delete(cnt, y)
				}
				l++
			}
			k += r - l + 1
			if k >= (m+1)/2 {
				return true
			}
		}
		return false
	})
}
```

#### TypeScript

```ts
function medianOfUniquenessArray(nums: number[]): number {
    const n = nums.length;
    const m = Math.floor(((1 + n) * n) / 2);
    let [l, r] = [0, n];
    const check = (mx: number): boolean => {
        const cnt = new Map<number, number>();
        let [l, k] = [0, 0];
        for (let r = 0; r < n; ++r) {
            const x = nums[r];
            cnt.set(x, (cnt.get(x) || 0) + 1);
            while (cnt.size > mx) {
                const y = nums[l++];
                cnt.set(y, cnt.get(y)! - 1);
                if (cnt.get(y) === 0) {
                    cnt.delete(y);
                }
            }
            k += r - l + 1;
            if (k >= Math.floor((m + 1) / 2)) {
                return true;
            }
        }
        return false;
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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
