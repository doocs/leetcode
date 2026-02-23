---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3847.Find%20the%20Score%20Difference%20in%20a%20Game/README_EN.md
---

<!-- problem:start -->

# [3847. Find the Score Difference in a Game](https://leetcode.com/problems/find-the-score-difference-in-a-game)

[中文文档](/solution/3800-3899/3847.Find%20the%20Score%20Difference%20in%20a%20Game/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>, where <code>nums[i]</code> represents the points scored in the <code>i<sup>th</sup></code> game.</p>

<p>There are <strong>exactly </strong>two players. Initially, the first player is <strong>active</strong> and the second player is <strong>inactive</strong>.</p>

<p>The following rules apply <strong>sequentially</strong> for each game <code>i</code>:</p>

<ul>
	<li>If <code>nums[i]</code> is odd, the active and inactive players swap roles.</li>
	<li>In every 6th game (that is, game indices <code>5, 11, 17, ...</code>), the active and inactive players swap roles.</li>
	<li>The active player plays the <code>i<sup>th</sup></code> game and gains <code>nums[i]</code> points.</li>
</ul>

<p>Return the <strong>score difference</strong>, defined as the first player&#39;s <strong>total</strong> score <strong>minus</strong> the second player&#39;s <strong>total</strong> score.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>Game 0: Since the points are odd, the second player becomes active and gains <code>nums[0] = 1</code> point.</li>
	<li>Game 1: No swap occurs. The second player gains <code>nums[1] = 2</code> points.</li>
	<li>Game 2: Since the points are odd, the first player becomes active and gains <code>nums[2] = 3</code> points.</li>
	<li>The score difference is <code>3 - 3 = 0</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4,2,1,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Games 0 to 2: The first player gains <code>2 + 4 + 2 = 8</code> points.</li>
	<li>Game 3: Since the points are odd, the second player is now active and gains <code>nums[3] = 1</code> point.</li>
	<li>Game 4: The second player gains <code>nums[4] = 2</code> points.</li>
	<li>Game 5: Since the points are odd, the players swap roles. Then, because this is the 6th game, the players swap again. The second player gains <code>nums[5] = 1</code> point.</li>
	<li>The score difference is <code>8 - 4 = 4</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Game 0: Since the points are odd, the second player is now active and gains <code>nums[0] = 1</code> point.</li>
	<li>The score difference is <code>0 - 1 = -1</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We use a variable $k$ to represent the role of the current player. Initially $k = 1$, when $k = 1$ it means the first player is the active player, and when $k = -1$ it means the second player is the active player. For each game, we update the value of $k$ according to the problem description, and add the score of the current game multiplied by $k$ to the answer. Finally, we return the answer.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def scoreDifference(self, nums: List[int]) -> int:
        ans, k = 0, 1
        for i, x in enumerate(nums):
            if x % 2:
                k *= -1
            if i % 6 == 5:
                k *= -1
            ans += k * x
        return ans
```

#### Java

```java
class Solution {
    public int scoreDifference(int[] nums) {
        int ans = 0;
        int k = 1;
        for (int i = 0; i < nums.length; ++i) {
            int x = nums[i];
            if ((x & 1) == 1) {
                k = -k;
            }
            if (i % 6 == 5) {
                k = -k;
            }
            ans += k * x;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int scoreDifference(vector<int>& nums) {
        int ans = 0;
        int k = 1;
        for (int i = 0; i < nums.size(); ++i) {
            int x = nums[i];
            if (x & 1) {
                k = -k;
            }
            if (i % 6 == 5) {
                k = -k;
            }
            ans += k * x;
        }
        return ans;
    }
};
```

#### Go

```go
func scoreDifference(nums []int) int {
	ans := 0
	k := 1
	for i, x := range nums {
		if x%2 != 0 {
			k = -k
		}
		if i%6 == 5 {
			k = -k
		}
		ans += k * x
	}
	return ans
}
```

#### TypeScript

```ts
function scoreDifference(nums: number[]): number {
    let ans = 0;
    let k = 1;

    nums.forEach((x, i) => {
        if (x % 2 !== 0) {
            k = -k;
        }
        if (i % 6 === 5) {
            k = -k;
        }
        ans += k * x;
    });

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
