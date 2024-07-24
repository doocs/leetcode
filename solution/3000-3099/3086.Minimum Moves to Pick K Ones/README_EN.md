---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3086.Minimum%20Moves%20to%20Pick%20K%20Ones/README_EN.md
rating: 2672
source: Weekly Contest 389 Q4
tags:
    - Greedy
    - Array
    - Prefix Sum
    - Sliding Window
---

<!-- problem:start -->

# [3086. Minimum Moves to Pick K Ones](https://leetcode.com/problems/minimum-moves-to-pick-k-ones)

[中文文档](/solution/3000-3099/3086.Minimum%20Moves%20to%20Pick%20K%20Ones/README.md)

## Description

<!-- description:start -->

<p>You are given a binary array <code>nums</code> of length <code>n</code>, a <strong>positive</strong> integer <code>k</code> and a <strong>non-negative</strong> integer <code>maxChanges</code>.</p>

<p>Alice plays a game, where the goal is for Alice to pick up <code>k</code> ones from <code>nums</code> using the <strong>minimum</strong> number of <strong>moves</strong>. When the game starts, Alice picks up any index <code>aliceIndex</code> in the range <code>[0, n - 1]</code> and stands there. If <code>nums[aliceIndex] == 1</code> , Alice picks up the one and <code>nums[aliceIndex]</code> becomes <code>0</code>(this <strong>does not</strong> count as a move). After this, Alice can make <strong>any</strong> number of <strong>moves</strong> (<strong>including</strong> <strong>zero</strong>) where in each move Alice must perform <strong>exactly</strong> one of the following actions:</p>

<ul>
	<li>Select any index <code>j != aliceIndex</code> such that <code>nums[j] == 0</code> and set <code>nums[j] = 1</code>. This action can be performed <strong>at</strong> <strong>most</strong> <code>maxChanges</code> times.</li>
	<li>Select any two adjacent indices <code>x</code> and <code>y</code> (<code>|x - y| == 1</code>) such that <code>nums[x] == 1</code>, <code>nums[y] == 0</code>, then swap their values (set <code>nums[y] = 1</code> and <code>nums[x] = 0</code>). If <code>y == aliceIndex</code>, Alice picks up the one after this move and <code>nums[y]</code> becomes <code>0</code>.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> number of moves required by Alice to pick <strong>exactly </strong></em><code>k</code> <em>ones</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">nums = [1,1,0,0,0,1,1,0,0,1], k = 3, maxChanges = 1</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">3</span></p>

<p><strong>Explanation:</strong> Alice can pick up <code>3</code> ones in <code>3</code> moves, if Alice performs the following actions in each move when standing at <code>aliceIndex == 1</code>:</p>

<ul>
	<li>At the start of the game Alice picks up the one and <code>nums[1]</code> becomes <code>0</code>. <code>nums</code> becomes <code>[1,<strong><u>0</u></strong>,0,0,0,1,1,0,0,1]</code>.</li>
	<li>Select <code>j == 2</code> and perform an action of the first type. <code>nums</code> becomes <code>[1,<strong><u>0</u></strong>,1,0,0,1,1,0,0,1]</code></li>
	<li>Select <code>x == 2</code> and <code>y == 1</code>, and perform an action of the second type. <code>nums</code> becomes <code>[1,<strong><u>1</u></strong>,0,0,0,1,1,0,0,1]</code>. As <code>y == aliceIndex</code>, Alice picks up the one and <code>nums</code> becomes <code>[1,<strong><u>0</u></strong>,0,0,0,1,1,0,0,1]</code>.</li>
	<li>Select <code>x == 0</code> and <code>y == 1</code>, and perform an action of the second type. <code>nums</code> becomes <code>[0,<strong><u>1</u></strong>,0,0,0,1,1,0,0,1]</code>. As <code>y == aliceIndex</code>, Alice picks up the one and <code>nums</code> becomes <code>[0,<strong><u>0</u></strong>,0,0,0,1,1,0,0,1]</code>.</li>
</ul>

<p>Note that it may be possible for Alice to pick up <code>3</code> ones using some other sequence of <code>3</code> moves.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">nums = [0,0,0,0], k = 2, maxChanges = 3</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">4</span></p>

<p><strong>Explanation:</strong> Alice can pick up <code>2</code> ones in <code>4</code> moves, if Alice performs the following actions in each move when standing at <code>aliceIndex == 0</code>:</p>

<ul>
	<li>Select <code>j == 1</code> and perform an action of the first type. <code>nums</code> becomes <code>[<strong><u>0</u></strong>,1,0,0]</code>.</li>
	<li>Select <code>x == 1</code> and <code>y == 0</code>, and perform an action of the second type. <code>nums</code> becomes <code>[<strong><u>1</u></strong>,0,0,0]</code>. As <code>y == aliceIndex</code>, Alice picks up the one and <code>nums</code> becomes <code>[<strong><u>0</u></strong>,0,0,0]</code>.</li>
	<li>Select <code>j == 1</code> again and perform an action of the first type. <code>nums</code> becomes <code>[<strong><u>0</u></strong>,1,0,0]</code>.</li>
	<li>Select <code>x == 1</code> and <code>y == 0</code> again, and perform an action of the second type. <code>nums</code> becomes <code>[<strong><u>1</u></strong>,0,0,0]</code>. As <code>y == aliceIndex</code>, Alice picks up the one and <code>nums</code> becomes <code>[<strong><u>0</u></strong>,0,0,0]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= maxChanges &lt;= 10<sup>5</sup></code></li>
	<li><code>maxChanges + sum(nums) &gt;= k</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Prefix Sum + Binary Search

We consider enumerating Alice's standing position $i$. For each $i$, we follow the strategy below:

-   First, if the number at position $i$ is $1$, we can directly pick up a $1$ without needing any moves.
-   Then, we pick up the number $1$ from both sides of position $i$, which is action $2$, i.e., move the $1$ from position $i-1$ to position $i$, then pick it up; move the $1$ from position $i+1$ to position $i$, then pick it up. Each pick up of a $1$ requires $1$ move.
-   Next, we maximize the conversion of $0$s at positions $i-1$ or $i+1$ to $1$s using action $1$, then move them to position $i$ using action $2$ to pick them up. This continues until the number of $1$s picked up reaches $k$ or the number of times action $1$ is used reaches $\textit{maxChanges}$. Assuming the number of times action $1$ is used is $c$, then a total of $2c$ moves are needed.
-   After utilizing action $1$, if the number of $1$s picked up has not reached $k$, we need to continue considering moving $1$s to position $i$ from the intervals $[1,..i-2]$ and $[i+2,..n]$ using action $2$ to pick them up. We can use binary search to determine the size of this interval so that the number of $1$s picked up reaches $k$. Specifically, we binary search for an interval size $d$, then within the intervals $[i-d,..i-2]$ and $[i+2,..i+d]$, we perform action $2$ to move $1$s to position $i$ for pickup. If the number of $1$s picked up reaches $k$, we update the answer.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumMoves(self, nums: List[int], k: int, maxChanges: int) -> int:
        n = len(nums)
        cnt = [0] * (n + 1)
        s = [0] * (n + 1)
        for i, x in enumerate(nums, 1):
            cnt[i] = cnt[i - 1] + x
            s[i] = s[i - 1] + i * x
        ans = inf
        max = lambda x, y: x if x > y else y
        min = lambda x, y: x if x < y else y
        for i, x in enumerate(nums, 1):
            t = 0
            need = k - x
            for j in (i - 1, i + 1):
                if need > 0 and 1 <= j <= n and nums[j - 1] == 1:
                    need -= 1
                    t += 1
            c = min(need, maxChanges)
            need -= c
            t += c * 2
            if need <= 0:
                ans = min(ans, t)
                continue
            l, r = 2, max(i - 1, n - i)
            while l <= r:
                mid = (l + r) >> 1
                l1, r1 = max(1, i - mid), max(0, i - 2)
                l2, r2 = min(n + 1, i + 2), min(n, i + mid)
                c1 = cnt[r1] - cnt[l1 - 1]
                c2 = cnt[r2] - cnt[l2 - 1]
                if c1 + c2 >= need:
                    t1 = c1 * i - (s[r1] - s[l1 - 1])
                    t2 = s[r2] - s[l2 - 1] - c2 * i
                    ans = min(ans, t + t1 + t2)
                    r = mid - 1
                else:
                    l = mid + 1
        return ans
```

#### Java

```java
class Solution {
    public long minimumMoves(int[] nums, int k, int maxChanges) {
        int n = nums.length;
        int[] cnt = new int[n + 1];
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            cnt[i] = cnt[i - 1] + nums[i - 1];
            s[i] = s[i - 1] + i * nums[i - 1];
        }
        long ans = Long.MAX_VALUE;
        for (int i = 1; i <= n; ++i) {
            long t = 0;
            int need = k - nums[i - 1];
            for (int j = i - 1; j <= i + 1; j += 2) {
                if (need > 0 && 1 <= j && j <= n && nums[j - 1] == 1) {
                    --need;
                    ++t;
                }
            }
            int c = Math.min(need, maxChanges);
            need -= c;
            t += c * 2;
            if (need <= 0) {
                ans = Math.min(ans, t);
                continue;
            }
            int l = 2, r = Math.max(i - 1, n - i);
            while (l <= r) {
                int mid = (l + r) >> 1;
                int l1 = Math.max(1, i - mid), r1 = Math.max(0, i - 2);
                int l2 = Math.min(n + 1, i + 2), r2 = Math.min(n, i + mid);
                int c1 = cnt[r1] - cnt[l1 - 1];
                int c2 = cnt[r2] - cnt[l2 - 1];
                if (c1 + c2 >= need) {
                    long t1 = 1L * c1 * i - (s[r1] - s[l1 - 1]);
                    long t2 = s[r2] - s[l2 - 1] - 1L * c2 * i;
                    ans = Math.min(ans, t + t1 + t2);
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
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
    long long minimumMoves(vector<int>& nums, int k, int maxChanges) {
        int n = nums.size();
        vector<int> cnt(n + 1, 0);
        vector<long long> s(n + 1, 0);

        for (int i = 1; i <= n; ++i) {
            cnt[i] = cnt[i - 1] + nums[i - 1];
            s[i] = s[i - 1] + 1LL * i * nums[i - 1];
        }

        long long ans = LLONG_MAX;

        for (int i = 1; i <= n; ++i) {
            long long t = 0;
            int need = k - nums[i - 1];

            for (int j = i - 1; j <= i + 1; j += 2) {
                if (need > 0 && 1 <= j && j <= n && nums[j - 1] == 1) {
                    --need;
                    ++t;
                }
            }

            int c = min(need, maxChanges);
            need -= c;
            t += c * 2;

            if (need <= 0) {
                ans = min(ans, t);
                continue;
            }

            int l = 2, r = max(i - 1, n - i);

            while (l <= r) {
                int mid = (l + r) / 2;
                int l1 = max(1, i - mid), r1 = max(0, i - 2);
                int l2 = min(n + 1, i + 2), r2 = min(n, i + mid);

                int c1 = cnt[r1] - cnt[l1 - 1];
                int c2 = cnt[r2] - cnt[l2 - 1];

                if (c1 + c2 >= need) {
                    long long t1 = 1LL * c1 * i - (s[r1] - s[l1 - 1]);
                    long long t2 = s[r2] - s[l2 - 1] - 1LL * c2 * i;
                    ans = min(ans, t + t1 + t2);
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }

        return ans;
    }
};
```

#### Go

```go
func minimumMoves(nums []int, k int, maxChanges int) int64 {
	n := len(nums)
	cnt := make([]int, n+1)
	s := make([]int, n+1)

	for i := 1; i <= n; i++ {
		cnt[i] = cnt[i-1] + nums[i-1]
		s[i] = s[i-1] + i*nums[i-1]
	}

	ans := math.MaxInt64

	for i := 1; i <= n; i++ {
		t := 0
		need := k - nums[i-1]

		for _, j := range []int{i - 1, i + 1} {
			if need > 0 && 1 <= j && j <= n && nums[j-1] == 1 {
				need--
				t++
			}
		}

		c := min(need, maxChanges)
		need -= c
		t += c * 2

		if need <= 0 {
			ans = min(ans, t)
			continue
		}

		l, r := 2, max(i-1, n-i)

		for l <= r {
			mid := (l + r) >> 1
			l1, r1 := max(1, i-mid), max(0, i-2)
			l2, r2 := min(n+1, i+2), min(n, i+mid)

			c1 := cnt[r1] - cnt[l1-1]
			c2 := cnt[r2] - cnt[l2-1]

			if c1+c2 >= need {
				t1 := c1*i - (s[r1] - s[l1-1])
				t2 := s[r2] - s[l2-1] - c2*i
				ans = min(ans, t+t1+t2)
				r = mid - 1
			} else {
				l = mid + 1
			}
		}
	}

	return int64(ans)
}
```

#### TypeScript

```ts
function minimumMoves(nums: number[], k: number, maxChanges: number): number {
    const n = nums.length;
    const cnt = Array(n + 1).fill(0);
    const s = Array(n + 1).fill(0);

    for (let i = 1; i <= n; i++) {
        cnt[i] = cnt[i - 1] + nums[i - 1];
        s[i] = s[i - 1] + i * nums[i - 1];
    }

    let ans = Infinity;
    for (let i = 1; i <= n; i++) {
        let t = 0;
        let need = k - nums[i - 1];

        for (let j of [i - 1, i + 1]) {
            if (need > 0 && 1 <= j && j <= n && nums[j - 1] === 1) {
                need--;
                t++;
            }
        }

        const c = Math.min(need, maxChanges);
        need -= c;
        t += c * 2;

        if (need <= 0) {
            ans = Math.min(ans, t);
            continue;
        }

        let l = 2,
            r = Math.max(i - 1, n - i);

        while (l <= r) {
            const mid = (l + r) >> 1;
            const [l1, r1] = [Math.max(1, i - mid), Math.max(0, i - 2)];
            const [l2, r2] = [Math.min(n + 1, i + 2), Math.min(n, i + mid)];

            const c1 = cnt[r1] - cnt[l1 - 1];
            const c2 = cnt[r2] - cnt[l2 - 1];

            if (c1 + c2 >= need) {
                const t1 = c1 * i - (s[r1] - s[l1 - 1]);
                const t2 = s[r2] - s[l2 - 1] - c2 * i;
                ans = Math.min(ans, t + t1 + t2);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
