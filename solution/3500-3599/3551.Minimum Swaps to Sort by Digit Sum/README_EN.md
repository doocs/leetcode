---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3551.Minimum%20Swaps%20to%20Sort%20by%20Digit%20Sum/README_EN.md
---

<!-- problem:start -->

# [3551. Minimum Swaps to Sort by Digit Sum](https://leetcode.com/problems/minimum-swaps-to-sort-by-digit-sum)

[中文文档](/solution/3500-3599/3551.Minimum%20Swaps%20to%20Sort%20by%20Digit%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> of <strong>distinct</strong> positive integers. You need to sort the array in <strong>increasing</strong> order based on the sum of the digits of each number. If two numbers have the same digit sum, the <strong>smaller</strong> number appears first in the sorted order.</p>

<p>Return the <strong>minimum</strong> number of swaps required to rearrange <code>nums</code> into this sorted order.</p>

<p>A <strong>swap</strong> is defined as exchanging the values at two distinct positions in the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [37,100]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Compute the digit sum for each integer: <code>[3 + 7 = 10, 1 + 0 + 0 = 1] &rarr; [10, 1]</code></li>
	<li>Sort the integers based on digit sum: <code>[100, 37]</code>. Swap <code>37</code> with <code>100</code> to obtain the sorted order.</li>
	<li>Thus, the minimum number of swaps required to rearrange <code>nums</code> is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [22,14,33,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Compute the digit sum for each integer: <code>[2 + 2 = 4, 1 + 4 = 5, 3 + 3 = 6, 7 = 7] &rarr; [4, 5, 6, 7]</code></li>
	<li>Sort the integers based on digit sum: <code>[22, 14, 33, 7]</code>. The array is already sorted.</li>
	<li>Thus, the minimum number of swaps required to rearrange <code>nums</code> is 0.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [18,43,34,16]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Compute the digit sum for each integer: <code>[1 + 8 = 9, 4 + 3 = 7, 3 + 4 = 7, 1 + 6 = 7] &rarr; [9, 7, 7, 7]</code></li>
	<li>Sort the integers based on digit sum: <code>[16, 34, 43, 18]</code>. Swap <code>18</code> with <code>16</code>, and swap <code>43</code> with <code>34</code> to obtain the sorted order.</li>
	<li>Thus, the minimum number of swaps required to rearrange <code>nums</code> is 2.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums</code> consists of <strong>distinct</strong> positive integers.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSwaps(self, nums: List[int]) -> int:
        def f(x: int) -> int:
            s = 0
            while x:
                s += x % 10
                x //= 10
            return s

        n = len(nums)
        arr = sorted((f(x), x) for x in nums)
        d = {a[1]: i for i, a in enumerate(arr)}
        ans = n
        vis = [False] * n
        for i in range(n):
            if not vis[i]:
                ans -= 1
                j = i
                while not vis[j]:
                    vis[j] = True
                    j = d[nums[j]]
        return ans
```

#### Java

```java
class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = f(nums[i]);
            arr[i][1] = nums[i];
        }
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        Map<Integer, Integer> d = new HashMap<>();
        for (int i = 0; i < n; i++) {
            d.put(arr[i][1], i);
        }
        boolean[] vis = new boolean[n];
        int ans = n;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                ans--;
                int j = i;
                while (!vis[j]) {
                    vis[j] = true;
                    j = d.get(nums[j]);
                }
            }
        }
        return ans;
    }

    private int f(int x) {
        int s = 0;
        while (x != 0) {
            s += x % 10;
            x /= 10;
        }
        return s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int f(int x) {
        int s = 0;
        while (x) {
            s += x % 10;
            x /= 10;
        }
        return s;
    }

    int minSwaps(vector<int>& nums) {
        int n = nums.size();
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) arr[i] = {f(nums[i]), nums[i]};
        sort(arr.begin(), arr.end());
        unordered_map<int, int> d;
        for (int i = 0; i < n; ++i) d[arr[i].second] = i;
        vector<char> vis(n, 0);
        int ans = n;
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                --ans;
                int j = i;
                while (!vis[j]) {
                    vis[j] = 1;
                    j = d[nums[j]];
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minSwaps(nums []int) int {
	n := len(nums)
	arr := make([][2]int, n)
	for i := 0; i < n; i++ {
		arr[i][0] = f(nums[i])
		arr[i][1] = nums[i]
	}
	sort.Slice(arr, func(i, j int) bool {
		if arr[i][0] != arr[j][0] {
			return arr[i][0] < arr[j][0]
		}
		return arr[i][1] < arr[j][1]
	})
	d := make(map[int]int, n)
	for i := 0; i < n; i++ {
		d[arr[i][1]] = i
	}
	vis := make([]bool, n)
	ans := n
	for i := 0; i < n; i++ {
		if !vis[i] {
			ans--
			j := i
			for !vis[j] {
				vis[j] = true
				j = d[nums[j]]
			}
		}
	}
	return ans
}

func f(x int) int {
	s := 0
	for x != 0 {
		s += x % 10
		x /= 10
	}
	return s
}
```

#### TypeScript

```ts
function f(x: number): number {
    let s = 0;
    while (x !== 0) {
        s += x % 10;
        x = Math.floor(x / 10);
    }
    return s;
}

function minSwaps(nums: number[]): number {
    const n = nums.length;
    const arr: [number, number][] = new Array(n);
    for (let i = 0; i < n; i++) {
        arr[i] = [f(nums[i]), nums[i]];
    }
    arr.sort((a, b) => (a[0] !== b[0] ? a[0] - b[0] : a[1] - b[1]));
    const d = new Map<number, number>();
    for (let i = 0; i < n; i++) {
        d.set(arr[i][1], i);
    }
    const vis: boolean[] = new Array(n).fill(false);
    let ans = n;
    for (let i = 0; i < n; i++) {
        if (!vis[i]) {
            ans--;
            let j = i;
            while (!vis[j]) {
                vis[j] = true;
                j = d.get(nums[j])!;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
