---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3511.Make%20a%20Positive%20Array/README_EN.md
---

<!-- problem:start -->

# [3511. Make a Positive Array 🔒](https://leetcode.com/problems/make-a-positive-array)

[中文文档](/solution/3500-3599/3511.Make%20a%20Positive%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code>. An array is considered <strong>positive</strong> if the sum of all numbers in each <strong><span data-keyword="subarray">subarray</span></strong> with <strong>more than two</strong> elements is positive.</p>

<p>You can perform the following operation any number of times:</p>

<ul>
	<li>Replace <strong>one</strong> element in <code>nums</code> with any integer between -10<sup>18</sup> and 10<sup>18</sup>.</li>
</ul>

<p>Find the <strong>minimum</strong> number of operations needed to make <code>nums</code> <strong>positive</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-10,15,-12]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The only subarray with more than 2 elements is the array itself. The sum of all elements is <code>(-10) + 15 + (-12) = -7</code>. By replacing <code>nums[0]</code> with 0, the new sum becomes <code>0 + 15 + (-12) = 3</code>. Thus, the array is now positive.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-1,-2,3,-1,2,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The only subarrays with more than 2 elements and a non-positive sum are:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Subarray Indices</th>
			<th style="border: 1px solid black;">Subarray</th>
			<th style="border: 1px solid black;">Sum</th>
			<th style="border: 1px solid black;">Subarray After Replacement (Set nums[1] = 1)</th>
			<th style="border: 1px solid black;">New Sum</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[0...2]</td>
			<td style="border: 1px solid black;">[-1, -2, 3]</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">[-1, 1, 3]</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[0...3]</td>
			<td style="border: 1px solid black;">[-1, -2, 3, -1]</td>
			<td style="border: 1px solid black;">-1</td>
			<td style="border: 1px solid black;">[-1, 1, 3, -1]</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[1...3]</td>
			<td style="border: 1px solid black;">[-2, 3, -1]</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">[1, 3, -1]</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
	</tbody>
</table>

<p>Thus, <code>nums</code> is positive after one operation.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The array is already positive, so no operations are needed.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def makeArrayPositive(self, nums: List[int]) -> int:
        l = -1
        ans = pre_mx = s = 0
        for r, x in enumerate(nums):
            s += x
            if r - l > 2 and s <= pre_mx:
                ans += 1
                l = r
                pre_mx = s = 0
            elif r - l >= 2:
                pre_mx = max(pre_mx, s - x - nums[r - 1])
        return ans
```

#### Java

```java
class Solution {
    public int makeArrayPositive(int[] nums) {
        int ans = 0;
        long preMx = 0, s = 0;
        for (int l = -1, r = 0; r < nums.length; r++) {
            int x = nums[r];
            s += x;
            if (r - l > 2 && s <= preMx) {
                ans++;
                l = r;
                preMx = s = 0;
            } else if (r - l >= 2) {
                preMx = Math.max(preMx, s - x - nums[r - 1]);
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
    int makeArrayPositive(vector<int>& nums) {
        int ans = 0;
        long long preMx = 0, s = 0;
        for (int l = -1, r = 0; r < nums.size(); r++) {
            int x = nums[r];
            s += x;
            if (r - l > 2 && s <= preMx) {
                ans++;
                l = r;
                preMx = s = 0;
            } else if (r - l >= 2) {
                preMx = max(preMx, s - x - nums[r - 1]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func makeArrayPositive(nums []int) (ans int) {
	l := -1
	preMx := 0
	s := 0
	for r, x := range nums {
		s += x
		if r-l > 2 && s <= preMx {
			ans++
			l = r
			preMx = 0
			s = 0
		} else if r-l >= 2 {
			preMx = max(preMx, s-x-nums[r-1])
		}
	}
	return
}
```

#### TypeScript

```ts
function makeArrayPositive(nums: number[]): number {
    let l = -1;
    let [ans, preMx, s] = [0, 0, 0];
    for (let r = 0; r < nums.length; r++) {
        const x = nums[r];
        s += x;
        if (r - l > 2 && s <= preMx) {
            ans++;
            l = r;
            preMx = 0;
            s = 0;
        } else if (r - l >= 2) {
            preMx = Math.max(preMx, s - x - nums[r - 1]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
