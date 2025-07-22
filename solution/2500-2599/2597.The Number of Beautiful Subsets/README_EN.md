---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2597.The%20Number%20of%20Beautiful%20Subsets/README_EN.md
rating: 2023
source: Weekly Contest 337 Q3
tags:
    - Array
    - Hash Table
    - Math
    - Dynamic Programming
    - Backtracking
    - Combinatorics
    - Sorting
---

<!-- problem:start -->

# [2597. The Number of Beautiful Subsets](https://leetcode.com/problems/the-number-of-beautiful-subsets)

[中文文档](/solution/2500-2599/2597.The%20Number%20of%20Beautiful%20Subsets/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> of positive integers and a <strong>positive</strong> integer <code>k</code>.</p>

<p>A subset of <code>nums</code> is <strong>beautiful</strong> if it does not contain two integers with an absolute difference equal to <code>k</code>.</p>

<p>Return <em>the number of <strong>non-empty beautiful </strong>subsets of the array</em> <code>nums</code>.</p>

<p>A <strong>subset</strong> of <code>nums</code> is an array that can be obtained by deleting some (possibly none) elements from <code>nums</code>. Two subsets are different if and only if the chosen indices to delete are different.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,4,6], k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> The beautiful subsets of the array nums are: [2], [4], [6], [2, 6].
It can be proved that there are only 4 beautiful subsets in the array [2,4,6].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1], k = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> The beautiful subset of the array nums is [1].
It can be proved that there is only 1 beautiful subset in the array [1].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 18</code></li>
	<li><code>1 &lt;= nums[i], k &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting + Backtracking

We use a hash table or array $\textit{cnt}$ to record the currently selected numbers and their counts, and use $\textit{ans}$ to record the number of beautiful subsets. Initially, $\textit{ans} = -1$ to exclude the empty set.

For each number $x$ in the array $\textit{nums}$, we have two choices:

-   Do not select $x$, and directly recurse to the next number;
-   Select $x$, and check if $x + k$ and $x - k$ have already appeared in $\textit{cnt}$. If neither has appeared, we can select $x$. In this case, we increment the count of $x$ by one, recurse to the next number, and then decrement the count of $x$ by one.

Finally, we return $\textit{ans}$.

The time complexity is $O(2^n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def beautifulSubsets(self, nums: List[int], k: int) -> int:
        def dfs(i: int) -> None:
            nonlocal ans
            if i >= len(nums):
                ans += 1
                return
            dfs(i + 1)
            if cnt[nums[i] + k] == 0 and cnt[nums[i] - k] == 0:
                cnt[nums[i]] += 1
                dfs(i + 1)
                cnt[nums[i]] -= 1

        ans = -1
        cnt = Counter()
        dfs(0)
        return ans
```

#### Java

```java
class Solution {
    private int[] nums;
    private int[] cnt = new int[1010];
    private int ans = -1;
    private int k;

    public int beautifulSubsets(int[] nums, int k) {
        this.k = k;
        this.nums = nums;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= nums.length) {
            ++ans;
            return;
        }
        dfs(i + 1);
        boolean ok1 = nums[i] + k >= cnt.length || cnt[nums[i] + k] == 0;
        boolean ok2 = nums[i] - k < 0 || cnt[nums[i] - k] == 0;
        if (ok1 && ok2) {
            ++cnt[nums[i]];
            dfs(i + 1);
            --cnt[nums[i]];
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int beautifulSubsets(vector<int>& nums, int k) {
        int ans = -1;
        int cnt[1010]{};
        int n = nums.size();

        auto dfs = [&](this auto&& dfs, int i) {
            if (i >= n) {
                ++ans;
                return;
            }
            dfs(i + 1);
            bool ok1 = nums[i] + k >= 1010 || cnt[nums[i] + k] == 0;
            bool ok2 = nums[i] - k < 0 || cnt[nums[i] - k] == 0;
            if (ok1 && ok2) {
                ++cnt[nums[i]];
                dfs(i + 1);
                --cnt[nums[i]];
            }
        };
        dfs(0);
        return ans;
    }
};
```

#### Go

```go
func beautifulSubsets(nums []int, k int) int {
	ans := -1
	n := len(nums)
	cnt := [1010]int{}
	var dfs func(int)
	dfs = func(i int) {
		if i >= n {
			ans++
			return
		}
		dfs(i + 1)
		ok1 := nums[i]+k >= len(cnt) || cnt[nums[i]+k] == 0
		ok2 := nums[i]-k < 0 || cnt[nums[i]-k] == 0
		if ok1 && ok2 {
			cnt[nums[i]]++
			dfs(i + 1)
			cnt[nums[i]]--
		}
	}
	dfs(0)
	return ans
}
```

#### TypeScript

```ts
function beautifulSubsets(nums: number[], k: number): number {
    let ans: number = -1;
    const cnt: number[] = new Array(1010).fill(0);
    const n: number = nums.length;
    const dfs = (i: number) => {
        if (i >= n) {
            ++ans;
            return;
        }
        dfs(i + 1);
        const ok1: boolean = nums[i] + k >= 1010 || cnt[nums[i] + k] === 0;
        const ok2: boolean = nums[i] - k < 0 || cnt[nums[i] - k] === 0;
        if (ok1 && ok2) {
            ++cnt[nums[i]];
            dfs(i + 1);
            --cnt[nums[i]];
        }
    };
    dfs(0);
    return ans;
}
```

#### C#

```cs
public class Solution {
    public int BeautifulSubsets(int[] nums, int k) {
        int ans = -1;
        int[] cnt = new int[1010];
        int n = nums.Length;

        void Dfs(int i) {
            if (i >= n) {
                ans++;
                return;
            }
            Dfs(i + 1);
            bool ok1 = nums[i] + k >= 1010 || cnt[nums[i] + k] == 0;
            bool ok2 = nums[i] - k < 0 || cnt[nums[i] - k] == 0;
            if (ok1 && ok2) {
                cnt[nums[i]]++;
                Dfs(i + 1);
                cnt[nums[i]]--;
            }
        }

        Dfs(0);
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
