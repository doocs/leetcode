---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3289.The%20Two%20Sneaky%20Numbers%20of%20Digitville/README_EN.md
rating: 1163
source: Weekly Contest 415 Q1
tags:
    - Array
    - Hash Table
    - Math
---

<!-- problem:start -->

# [3289. The Two Sneaky Numbers of Digitville](https://leetcode.com/problems/the-two-sneaky-numbers-of-digitville)

[中文文档](/solution/3200-3299/3289.The%20Two%20Sneaky%20Numbers%20of%20Digitville/README.md)

## Description

<!-- description:start -->

<p>In the town of Digitville, there was a list of numbers called <code>nums</code> containing integers from <code>0</code> to <code>n - 1</code>. Each number was supposed to appear <strong>exactly once</strong> in the list, however, <strong>two</strong> mischievous numbers sneaked in an <em>additional time</em>, making the list longer than usual.<!-- notionvc: c37cfb04-95eb-4273-85d5-3c52d0525b95 --></p>

<p>As the town detective, your task is to find these two sneaky numbers. Return an array of size <strong>two</strong> containing the two numbers (in <em>any order</em>), so peace can return to Digitville.<!-- notionvc: 345db5be-c788-4828-9836-eefed31c982f --></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,1,1,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>The numbers 0 and 1 each appear twice in the array.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,3,2,1,3,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,3]</span></p>

<p><strong>Explanation: </strong></p>

<p>The numbers 2 and 3 each appear twice in the array.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7,1,5,4,3,4,6,0,9,5,8,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">[4,5]</span></p>

<p><strong>Explanation: </strong></p>

<p>The numbers 4 and 5 each appear twice in the array.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li data-stringify-border="0" data-stringify-indent="1"><code>2 &lt;= n &lt;= 100</code></li>
	<li data-stringify-border="0" data-stringify-indent="1"><code>nums.length == n + 2</code></li>
	<li data-stringify-border="0" data-stringify-indent="1"><code data-stringify-type="code">0 &lt;= nums[i] &lt; n</code></li>
	<li data-stringify-border="0" data-stringify-indent="1">The input is generated such that <code>nums</code> contains <strong>exactly</strong> two repeated elements.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We can use an array $\textit{cnt}$ to record the number of occurrences of each number.

Traverse the array $\textit{nums}$, and when a number appears for the second time, add it to the answer array.

After the traversal, return the answer array.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getSneakyNumbers(self, nums: List[int]) -> List[int]:
        cnt = Counter(nums)
        return [x for x, v in cnt.items() if v == 2]
```

#### Java

```java
class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int[] ans = new int[2];
        int[] cnt = new int[100];
        int k = 0;
        for (int x : nums) {
            if (++cnt[x] == 2) {
                ans[k++] = x;
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
    vector<int> getSneakyNumbers(vector<int>& nums) {
        vector<int> ans;
        int cnt[100]{};
        for (int x : nums) {
            if (++cnt[x] == 2) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func getSneakyNumbers(nums []int) (ans []int) {
	cnt := [100]int{}
	for _, x := range nums {
		cnt[x]++
		if cnt[x] == 2 {
			ans = append(ans, x)
		}
	}
	return
}
```

#### TypeScript

```ts
function getSneakyNumbers(nums: number[]): number[] {
    const ans: number[] = [];
    const cnt: number[] = Array(100).fill(0);
    for (const x of nums) {
        if (++cnt[x] > 1) {
            ans.push(x);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
