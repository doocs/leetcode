---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2731.Movement%20of%20Robots/README_EN.md
rating: 1922
source: Biweekly Contest 106 Q3
tags:
    - Brainteaser
    - Array
    - Prefix Sum
    - Sorting
---

<!-- problem:start -->

# [2731. Movement of Robots](https://leetcode.com/problems/movement-of-robots)

[中文文档](/solution/2700-2799/2731.Movement%20of%20Robots/README.md)

## Description

<p>Some robots are standing on an infinite number line with their initial coordinates given by a <strong>0-indexed</strong> integer array <code>nums</code> and will start moving once given the command to move. The robots will move a unit distance each second.</p>

<p>You are given a string <code>s</code> denoting the direction in which robots will move on command. <code>&#39;L&#39;</code> means the robot will move towards the left side or negative side of the number line, whereas <code>&#39;R&#39;</code> means the robot will move towards the right side or positive side of the number line.</p>

<p>If two robots collide, they will start moving in opposite directions.</p>

<p>Return <em>the sum of distances between all the&nbsp;pairs of robots </em><code>d</code> <em>seconds after&nbsp;the command. </em>Since the sum can be very large, return it modulo <code>10<sup>9</sup> + 7</code>.</p>

<p><b>Note: </b></p>

<ul>
	<li>For two robots at the index <code>i</code> and <code>j</code>, pair <code>(i,j)</code> and pair <code>(j,i)</code> are considered the same pair.</li>
	<li>When robots collide, they <strong>instantly change</strong> their directions without wasting any time.</li>
	<li>Collision happens&nbsp;when two robots share the same place in a&nbsp;moment.
	<ul>
		<li>For example, if a robot is positioned in 0 going to the right and another is positioned in 2 going to the left, the next second they&#39;ll be both in 1 and they will change direction and the next second the first one will be in 0, heading left, and another will be in 2, heading right.</li>
		<li>For example,&nbsp;if a robot is positioned in 0 going to the right and another is positioned in 1&nbsp;going to the left, the next second the first one will be in 0, heading left, and another will be in 1, heading right.</li>
	</ul>
	</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,0,2], s = &quot;RLL&quot;, d = 3
<strong>Output:</strong> 8
<strong>Explanation:</strong> 
After 1 second, the positions are [-1,-1,1]. Now, the robot at index 0 will move left, and the robot at index 1 will move right.
After 2 seconds, the positions are [-2,0,0]. Now, the robot at index 1 will move left, and the robot at index 2 will move right.
After 3 seconds, the positions are [-3,-1,1].
The distance between the robot at index 0 and 1 is abs(-3 - (-1)) = 2.
The distance between the robot at index 0 and 2 is abs(-3 - 1) = 4.
The distance between the robot at index 1 and 2 is abs(-1 - 1) = 2.
The sum of the pairs of all distances = 2 + 4 + 2 = 8.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,0], s = &quot;RL&quot;, d = 2
<strong>Output:</strong> 5
<strong>Explanation:</strong> 
After 1 second, the positions are [2,-1].
After 2 seconds, the positions are [3,-2].
The distance between the two robots is abs(-2 - 3) = 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-2 * 10<sup>9</sup>&nbsp;&lt;= nums[i] &lt;= 2 * 10<sup>9</sup></code></li>
	<li><code>0 &lt;= d &lt;= 10<sup>9</sup></code></li>
	<li><code>nums.length == s.length&nbsp;</code></li>
	<li><code>s</code> consists of &#39;L&#39; and &#39;R&#39; only</li>
	<li><code>nums[i]</code>&nbsp;will be unique.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Quick thinking + Sorting

After two robots collide, they will immediately change direction, which is equivalent to the two robots continuing to move in their original direction. Therefore, we traverse the array $nums$, and according to the instructions in the string $s$, we add or subtract $d$ from the position of each robot, and then sort the array $nums$.

Next, we enumerate the position of each robot from small to large, and calculate the sum of the distances between the current robot and all robots in front, which is the answer.

The time complexity is $O(n \times \log n)$ and the space complexity is $O(n)$, where $n$ is the number of robots.

<!-- tabs:start -->

```python
class Solution:
    def sumDistance(self, nums: List[int], s: str, d: int) -> int:
        mod = 10**9 + 7
        for i, c in enumerate(s):
            nums[i] += d if c == "R" else -d
        nums.sort()
        ans = s = 0
        for i, x in enumerate(nums):
            ans += i * x - s
            s += x
        return ans % mod
```

```java
class Solution {
    public int sumDistance(int[] nums, String s, int d) {
        int n = nums.length;
        long[] arr = new long[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = (long) nums[i] + (s.charAt(i) == 'L' ? -d : d);
        }
        Arrays.sort(arr);
        long ans = 0, sum = 0;
        final int mod = (int) 1e9 + 7;
        for (int i = 0; i < n; ++i) {
            ans = (ans + i * arr[i] - sum) % mod;
            sum += arr[i];
        }
        return (int) ans;
    }
}
```

```cpp
class Solution {
public:
    int sumDistance(vector<int>& nums, string s, int d) {
        int n = nums.size();
        vector<long long> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = 1LL * nums[i] + (s[i] == 'L' ? -d : d);
        }
        sort(arr.begin(), arr.end());
        long long ans = 0;
        long long sum = 0;
        const int mod = 1e9 + 7;
        for (int i = 0; i < n; ++i) {
            ans = (ans + i * arr[i] - sum) % mod;
            sum += arr[i];
        }
        return ans;
    }
};
```

```go
func sumDistance(nums []int, s string, d int) (ans int) {
	for i, c := range s {
		if c == 'R' {
			nums[i] += d
		} else {
			nums[i] -= d
		}
	}
	sort.Ints(nums)
	sum := 0
	const mod int = 1e9 + 7
	for i, x := range nums {
		ans = (ans + i*x - sum) % mod
		sum += x
	}
	return
}
```

```ts
function sumDistance(nums: number[], s: string, d: number): number {
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        nums[i] += s[i] === 'L' ? -d : d;
    }
    nums.sort((a, b) => a - b);
    let ans = 0;
    let sum = 0;
    const mod = 1e9 + 7;
    for (let i = 0; i < n; ++i) {
        ans = (ans + i * nums[i] - sum) % mod;
        sum += nums[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
