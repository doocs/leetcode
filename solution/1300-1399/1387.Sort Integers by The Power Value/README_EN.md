---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1387.Sort%20Integers%20by%20The%20Power%20Value/README_EN.md
rating: 1506
source: Biweekly Contest 22 Q3
tags:
    - Memoization
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [1387. Sort Integers by The Power Value](https://leetcode.com/problems/sort-integers-by-the-power-value)

[中文文档](/solution/1300-1399/1387.Sort%20Integers%20by%20The%20Power%20Value/README.md)

## Description

<!-- description:start -->

<p>The power of an integer <code>x</code> is defined as the number of steps needed to transform <code>x</code> into <code>1</code> using the following steps:</p>

<ul>
	<li>if <code>x</code> is even then <code>x = x / 2</code></li>
	<li>if <code>x</code> is odd then <code>x = 3 * x + 1</code></li>
</ul>

<p>For example, the power of <code>x = 3</code> is <code>7</code> because <code>3</code> needs <code>7</code> steps to become <code>1</code> (<code>3 --&gt; 10 --&gt; 5 --&gt; 16 --&gt; 8 --&gt; 4 --&gt; 2 --&gt; 1</code>).</p>

<p>Given three integers <code>lo</code>, <code>hi</code> and <code>k</code>. The task is to sort all integers in the interval <code>[lo, hi]</code> by the power value in <strong>ascending order</strong>, if two or more integers have <strong>the same</strong> power value sort them by <strong>ascending order</strong>.</p>

<p>Return the <code>k<sup>th</sup></code> integer in the range <code>[lo, hi]</code> sorted by the power value.</p>

<p>Notice that for any integer <code>x</code> <code>(lo &lt;= x &lt;= hi)</code> it is <strong>guaranteed</strong> that <code>x</code> will transform into <code>1</code> using these steps and that the power of <code>x</code> is will <strong>fit</strong> in a 32-bit signed integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> lo = 12, hi = 15, k = 2
<strong>Output:</strong> 13
<strong>Explanation:</strong> The power of 12 is 9 (12 --&gt; 6 --&gt; 3 --&gt; 10 --&gt; 5 --&gt; 16 --&gt; 8 --&gt; 4 --&gt; 2 --&gt; 1)
The power of 13 is 9
The power of 14 is 17
The power of 15 is 17
The interval sorted by the power value [12,13,14,15]. For k = 2 answer is the second element which is 13.
Notice that 12 and 13 have the same power value and we sorted them in ascending order. Same for 14 and 15.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> lo = 7, hi = 11, k = 4
<strong>Output:</strong> 7
<strong>Explanation:</strong> The power array corresponding to the interval [7, 8, 9, 10, 11] is [16, 3, 19, 6, 14].
The interval sorted by power is [8, 10, 11, 7, 9].
The fourth number in the sorted array is 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= lo &lt;= hi &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= hi - lo + 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Custom Sorting

First, we define a function $\textit{f}(x)$, which represents the number of steps required to transform the number $x$ into $1$, i.e., the power value of the number $x$.

Then, we sort all the numbers in the interval $[\textit{lo}, \textit{hi}]$ in ascending order based on their power values. If the power values are the same, we sort them in ascending order based on the numbers themselves.

Finally, we return the $k$-th number in the sorted list.

The time complexity is $O(n \times \log n \times M)$, and the space complexity is $O(n)$. Here, $n$ is the number of numbers in the interval $[\textit{lo}, \textit{hi}]$, and $M$ is the maximum value of $f(x)$, which is at most $178$ in this problem.

<!-- tabs:start -->

#### Python3

```python
@cache
def f(x: int) -> int:
    ans = 0
    while x != 1:
        if x % 2 == 0:
            x //= 2
        else:
            x = 3 * x + 1
        ans += 1
    return ans


class Solution:
    def getKth(self, lo: int, hi: int, k: int) -> int:
        return sorted(range(lo, hi + 1), key=f)[k - 1]
```

#### Java

```java
class Solution {
    public int getKth(int lo, int hi, int k) {
        Integer[] nums = new Integer[hi - lo + 1];
        for (int i = lo; i <= hi; ++i) {
            nums[i - lo] = i;
        }
        Arrays.sort(nums, (a, b) -> {
            int fa = f(a), fb = f(b);
            return fa == fb ? a - b : fa - fb;
        });
        return nums[k - 1];
    }

    private int f(int x) {
        int ans = 0;
        for (; x != 1; ++ans) {
            if (x % 2 == 0) {
                x /= 2;
            } else {
                x = x * 3 + 1;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int getKth(int lo, int hi, int k) {
        auto f = [](int x) {
            int ans = 0;
            for (; x != 1; ++ans) {
                if (x % 2 == 0) {
                    x /= 2;
                } else {
                    x = 3 * x + 1;
                }
            }
            return ans;
        };
        vector<int> nums;
        for (int i = lo; i <= hi; ++i) {
            nums.push_back(i);
        }
        sort(nums.begin(), nums.end(), [&](int x, int y) {
            int fx = f(x), fy = f(y);
            if (fx != fy) {
                return fx < fy;
            } else {
                return x < y;
            }
        });
        return nums[k - 1];
    }
};
```

#### Go

```go
func getKth(lo int, hi int, k int) int {
	f := func(x int) (ans int) {
		for ; x != 1; ans++ {
			if x%2 == 0 {
				x /= 2
			} else {
				x = 3*x + 1
			}
		}
		return
	}
	nums := make([]int, hi-lo+1)
	for i := range nums {
		nums[i] = lo + i
	}
	sort.Slice(nums, func(i, j int) bool {
		fx, fy := f(nums[i]), f(nums[j])
		if fx != fy {
			return fx < fy
		}
		return nums[i] < nums[j]
	})
	return nums[k-1]
}
```

#### TypeScript

```ts
function getKth(lo: number, hi: number, k: number): number {
    const f = (x: number): number => {
        let ans = 0;
        for (; x !== 1; ++ans) {
            if (x % 2 === 0) {
                x >>= 1;
            } else {
                x = x * 3 + 1;
            }
        }
        return ans;
    };
    const nums = new Array(hi - lo + 1).fill(0).map((_, i) => i + lo);
    nums.sort((a, b) => {
        const fa = f(a),
            fb = f(b);
        return fa === fb ? a - b : fa - fb;
    });
    return nums[k - 1];
}
```

#### Rust

```rust
impl Solution {
    pub fn get_kth(lo: i32, hi: i32, k: i32) -> i32 {
        let f = |mut x: i32| -> i32 {
            let mut ans = 0;
            while x != 1 {
                if x % 2 == 0 {
                    x /= 2;
                } else {
                    x = 3 * x + 1;
                }
                ans += 1;
            }
            ans
        };

        let mut nums: Vec<i32> = (lo..=hi).collect();
        nums.sort_by(|&x, &y| f(x).cmp(&f(y)));
        nums[(k - 1) as usize]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
