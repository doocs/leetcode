# [3028. Ant on the Boundary](https://leetcode.com/problems/ant-on-the-boundary)

[中文文档](/solution/3000-3099/3028.Ant%20on%20the%20Boundary/README.md)

## Description

<p>An ant is on a boundary. It sometimes goes <strong>left</strong> and sometimes <strong>right</strong>.</p>

<p>You are given an array of <strong>non-zero</strong> integers <code>nums</code>. The ant starts reading <code>nums</code> from the first element of it to its end. At each step, it moves according to the value of the current element:</p>

<ul>
	<li>If <code>nums[i] &lt; 0</code>, it moves <strong>left</strong> by<!-- notionvc: 55fee232-4fc9-445f-952a-f1b979415864 --> <code>-nums[i]</code> units.</li>
	<li>If <code>nums[i] &gt; 0</code>, it moves <strong>right</strong> by <code>nums[i]</code> units.</li>
</ul>

<p>Return <em>the number of times the ant <strong>returns</strong> to the boundary.</em></p>

<p><strong>Notes:</strong></p>

<ul>
	<li>There is an infinite space on both sides of the boundary.</li>
	<li>We check whether the ant is on the boundary only after it has moved <code>|nums[i]|</code> units. In other words, if the ant crosses the boundary during its movement, it does not count.<!-- notionvc: 5ff95338-8634-4d02-a085-1e83c0be6fcd --></li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,-5]
<strong>Output:</strong> 1
<strong>Explanation:</strong> After the first step, the ant is 2 steps to the right of the boundary<!-- notionvc: 61ace51c-559f-4bc6-800f-0a0db2540433 -->.
After the second step, the ant is 5 steps to the right of the boundary<!-- notionvc: 61ace51c-559f-4bc6-800f-0a0db2540433 -->.
After the third step, the ant is on the boundary.
So the answer is 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,-3,-4]
<strong>Output:</strong> 0
<strong>Explanation:</strong> After the first step, the ant is 3 steps to the right of the boundary<!-- notionvc: 61ace51c-559f-4bc6-800f-0a0db2540433 -->.
After the second step, the ant is 5 steps to the right of the boundary<!-- notionvc: 61ace51c-559f-4bc6-800f-0a0db2540433 -->.
After the third step, the ant is 2 steps to the right of the boundary<!-- notionvc: 61ace51c-559f-4bc6-800f-0a0db2540433 -->.
After the fourth step, the ant is 2 steps to the left of the boundary<!-- notionvc: 61ace51c-559f-4bc6-800f-0a0db2540433 -->.
The ant never returned to the boundary, so the answer is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
	<li><code>nums[i] != 0</code></li>
</ul>

## Solutions

### Solution 1: Prefix Sum

Based on the problem description, we only need to calculate how many zeros are in all prefix sums of `nums`.

The time complexity is $O(n)$, where $n$ is the length of `nums`. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def returnToBoundaryCount(self, nums: List[int]) -> int:
        return sum(s == 0 for s in accumulate(nums))
```

```java
class Solution {
    public int returnToBoundaryCount(int[] nums) {
        int ans = 0, s = 0;
        for (int x : nums) {
            s += x;
            if (s == 0) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int returnToBoundaryCount(vector<int>& nums) {
        int ans = 0, s = 0;
        for (int x : nums) {
            s += x;
            ans += s == 0;
        }
        return ans;
    }
};
```

```go
func returnToBoundaryCount(nums []int) (ans int) {
	s := 0
	for _, x := range nums {
		s += x
		if s == 0 {
			ans++
		}
	}
	return
}
```

```ts
function returnToBoundaryCount(nums: number[]): number {
    let [ans, s] = [0, 0];
    for (const x of nums) {
        s += x;
        ans += s === 0 ? 1 : 0;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
