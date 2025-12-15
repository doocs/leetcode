---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3769.Sort%20Integers%20by%20Binary%20Reflection/README_EN.md
rating: 1363
source: Weekly Contest 479 Q1
tags:
    - Array
    - Sorting
---

<!-- problem:start -->

# [3769. Sort Integers by Binary Reflection](https://leetcode.com/problems/sort-integers-by-binary-reflection)

[中文文档](/solution/3700-3799/3769.Sort%20Integers%20by%20Binary%20Reflection/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>The <strong>binary reflection</strong> of a <strong>positive</strong> integer is defined as the number obtained by reversing the order of its <strong>binary</strong> digits (ignoring any leading zeros) and interpreting the resulting binary number as a decimal.</p>

<p>Sort the array in <strong>ascending</strong> order based on the binary reflection of each element. If two different numbers have the same binary reflection, the <strong>smaller</strong> original number should appear first.</p>

<p>Return the resulting sorted array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,5,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">[4,4,5]</span></p>

<p><strong>Explanation:</strong></p>

<p>Binary reflections are:</p>

<ul>
	<li>4 -&gt; (binary) <code>100</code> -&gt; (reversed) <code>001</code> -&gt; 1</li>
	<li>5 -&gt; (binary) <code>101</code> -&gt; (reversed) <code>101</code> -&gt; 5</li>
	<li>4 -&gt; (binary) <code>100</code> -&gt; (reversed) <code>001</code> -&gt; 1</li>
</ul>
Sorting by the reflected values gives <code>[4, 4, 5]</code>.</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,6,5,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">[8,3,6,5]</span></p>

<p><strong>Explanation:</strong></p>

<p>Binary reflections are:</p>

<ul>
	<li>3 -&gt; (binary) <code>11</code> -&gt; (reversed) <code>11</code> -&gt; 3</li>
	<li>6 -&gt; (binary) <code>110</code> -&gt; (reversed) <code>011</code> -&gt; 3</li>
	<li>5 -&gt; (binary) <code>101</code> -&gt; (reversed) <code>101</code> -&gt; 5</li>
	<li>8 -&gt; (binary) <code>1000</code> -&gt; (reversed) <code>0001</code> -&gt; 1</li>
</ul>
Sorting by the reflected values gives <code>[8, 3, 6, 5]</code>.<br />
Note that 3 and 6 have the same reflection, so we arrange them in increasing order of original value.</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Custom Sorting

We define a function $f(x)$ to calculate the binary reflection value of integer $x$. Specifically, we continuously extract the lowest bit of $x$ and add it to the end of the result $y$ until $x$ becomes $0$.

Then, we sort the array $\textit{nums}$ with the sorting key being the tuple $(f(x), x)$ of each element's binary reflection value and original value. This ensures that when two elements have the same binary reflection value, the smaller original value will be placed first.

Finally, we return the sorted array.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sortByReflection(self, nums: List[int]) -> List[int]:
        def f(x: int) -> int:
            y = 0
            while x:
                y = y << 1 | (x & 1)
                x >>= 1
            return y

        nums.sort(key=lambda x: (f(x), x))
        return nums
```

#### Java

```java
class Solution {
    public int[] sortByReflection(int[] nums) {
        int n = nums.length;
        Integer[] a = new Integer[n];
        Arrays.setAll(a, i -> nums[i]);

        Arrays.sort(a, (u, v) -> {
            int fu = f(u);
            int fv = f(v);
            if (fu != fv) {
                return Integer.compare(fu, fv);
            }
            return Integer.compare(u, v);
        });

        for (int i = 0; i < n; i++) nums[i] = a[i];
        return nums;
    }

    private int f(int x) {
        int y = 0;
        while (x != 0) {
            y = (y << 1) | (x & 1);
            x >>= 1;
        }
        return y;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> sortByReflection(vector<int>& nums) {
        auto f = [](int x) {
            int y = 0;
            while (x) {
                y = (y << 1) | (x & 1);
                x >>= 1;
            }
            return y;
        };

        sort(nums.begin(), nums.end(), [&](int a, int b) {
            int fa = f(a);
            int fb = f(b);
            if (fa != fb) {
                return fa < fb;
            }
            return a < b;
        });

        return nums;
    }
};
```

#### Go

```go
func sortByReflection(nums []int) []int {
	f := func(x int) int {
		y := 0
		for x != 0 {
			y = (y << 1) | (x & 1)
			x >>= 1
		}
		return y
	}

	sort.Slice(nums, func(i, j int) bool {
		fi := f(nums[i])
		fj := f(nums[j])
		if fi != fj {
			return fi < fj
		}
		return nums[i] < nums[j]
	})

	return nums
}
```

#### TypeScript

```ts
function sortByReflection(nums: number[]): number[] {
    const f = (x: number): number => {
        let y = 0;
        for (; x; x >>= 1) {
            y = (y << 1) | (x & 1);
        }
        return y;
    };

    nums.sort((a, b) => {
        const fa = f(a);
        const fb = f(b);
        if (fa !== fb) {
            return fa - fb;
        }
        return a - b;
    });

    return nums;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
