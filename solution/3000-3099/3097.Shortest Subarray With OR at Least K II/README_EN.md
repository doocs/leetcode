# [3097. Shortest Subarray With OR at Least K II](https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii)

[中文文档](/solution/3000-3099/3097.Shortest%20Subarray%20With%20OR%20at%20Least%20K%20II/README.md)

<!-- tags: -->

## Description

<p>You are given an array <code>nums</code> of <strong>non-negative</strong> integers and an integer <code>k</code>.</p>

<p>An array is called <strong>special</strong> if the bitwise <code>OR</code> of all of its elements is <strong>at least</strong> <code>k</code>.</p>

<p>Return <em>the length of the <strong>shortest</strong> <strong>special</strong> <strong>non-empty</strong> <span data-keyword="subarray-nonempty">subarray</span> of</em> <code>nums</code>, <em>or return</em> <code>-1</code> <em>if no special subarray exists</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarray <code>[3]</code> has <code>OR</code> value of <code>3</code>. Hence, we return <code>1</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,8], k = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarray <code>[2,1,8]</code> has <code>OR</code> value of <code>11</code>. Hence, we return <code>3</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2], k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarray <code>[1]</code> has <code>OR</code> value of <code>1</code>. Hence, we return <code>1</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def minimumSubarrayLength(self, nums: List[int], k: int) -> int:
        n = len(nums)
        cnt = [0] * 32
        ans = n + 1
        s = i = 0
        for j, x in enumerate(nums):
            s |= x
            for h in range(32):
                if x >> h & 1:
                    cnt[h] += 1
            while s >= k and i <= j:
                ans = min(ans, j - i + 1)
                y = nums[i]
                for h in range(32):
                    if y >> h & 1:
                        cnt[h] -= 1
                        if cnt[h] == 0:
                            s ^= 1 << h
                i += 1
        return -1 if ans > n else ans
```

```java
class Solution {
    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int[] cnt = new int[32];
        int ans = n + 1;
        for (int i = 0, j = 0, s = 0; j < n; ++j) {
            s |= nums[j];
            for (int h = 0; h < 32; ++h) {
                if ((nums[j] >> h & 1) == 1) {
                    ++cnt[h];
                }
            }
            for (; s >= k && i <= j; ++i) {
                ans = Math.min(ans, j - i + 1);
                for (int h = 0; h < 32; ++h) {
                    if ((nums[i] >> h & 1) == 1) {
                        if (--cnt[h] == 0) {
                            s ^= 1 << h;
                        }
                    }
                }
            }
        }
        return ans > n ? -1 : ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumSubarrayLength(vector<int>& nums, int k) {
        int n = nums.size();
        int cnt[32]{};
        int ans = n + 1;
        for (int i = 0, j = 0, s = 0; j < n; ++j) {
            s |= nums[j];
            for (int h = 0; h < 32; ++h) {
                if ((nums[j] >> h & 1) == 1) {
                    ++cnt[h];
                }
            }
            for (; s >= k && i <= j; ++i) {
                ans = min(ans, j - i + 1);
                for (int h = 0; h < 32; ++h) {
                    if ((nums[i] >> h & 1) == 1) {
                        if (--cnt[h] == 0) {
                            s ^= 1 << h;
                        }
                    }
                }
            }
        }
        return ans > n ? -1 : ans;
    }
};
```

```go
func minimumSubarrayLength(nums []int, k int) int {
	n := len(nums)
	cnt := [32]int{}
	ans := n + 1
	s, i := 0, 0
	for j, x := range nums {
		s |= x
		for h := 0; h < 32; h++ {
			if x>>h&1 == 1 {
				cnt[h]++
			}
		}
		for ; s >= k && i <= j; i++ {
			ans = min(ans, j-i+1)
			for h := 0; h < 32; h++ {
				if nums[i]>>h&1 == 1 {
					cnt[h]--
					if cnt[h] == 0 {
						s ^= 1 << h
					}
				}
			}
		}
	}
	if ans == n+1 {
		return -1
	}
	return ans
}
```

```ts
function minimumSubarrayLength(nums: number[], k: number): number {
    const n = nums.length;
    let ans = n + 1;
    const cnt: number[] = new Array<number>(32).fill(0);
    for (let i = 0, j = 0, s = 0; j < n; ++j) {
        s |= nums[j];
        for (let h = 0; h < 32; ++h) {
            if (((nums[j] >> h) & 1) === 1) {
                ++cnt[h];
            }
        }
        for (; s >= k && i <= j; ++i) {
            ans = Math.min(ans, j - i + 1);
            for (let h = 0; h < 32; ++h) {
                if (((nums[i] >> h) & 1) === 1 && --cnt[h] === 0) {
                    s ^= 1 << h;
                }
            }
        }
    }
    return ans === n + 1 ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- end -->
