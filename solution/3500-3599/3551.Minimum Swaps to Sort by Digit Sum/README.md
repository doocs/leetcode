---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3551.Minimum%20Swaps%20to%20Sort%20by%20Digit%20Sum/README.md
rating: 1506
source: 第 450 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 排序
---

<!-- problem:start -->

# [3551. 数位和排序需要的最小交换次数](https://leetcode.cn/problems/minimum-swaps-to-sort-by-digit-sum)

[English Version](/solution/3500-3599/3551.Minimum%20Swaps%20to%20Sort%20by%20Digit%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由 <strong>互不相同</strong>&nbsp;的正整数组成的数组 <code>nums</code>，需要根据每个数字的数位和（即每一位数字相加求和）按&nbsp;<strong>升序&nbsp;</strong>对数组进行排序。如果两个数字的数位和相等，则较小的数字排在前面。</p>

<p>返回将 <code>nums</code> 排列为上述排序顺序所需的&nbsp;<strong>最小&nbsp;</strong>交换次数。</p>

<p>一次&nbsp;<strong>交换&nbsp;</strong>定义为交换数组中两个不同位置的值。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [37,100]</span></p>

<p><strong>输出:</strong> <span class="example-io">1</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>计算每个整数的数位和：<code>[3 + 7 = 10, 1 + 0 + 0 = 1] → [10, 1]</code></li>
	<li>根据数位和排序：<code>[100, 37]</code>。将 <code>37</code> 与 <code>100</code> 交换，得到排序后的数组。</li>
	<li>因此，将 <code>nums</code> 排列为排序顺序所需的最小交换次数为 1。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [22,14,33,7]</span></p>

<p><strong>输出:</strong> <span class="example-io">0</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>计算每个整数的数位和：<code>[2 + 2 = 4, 1 + 4 = 5, 3 + 3 = 6, 7 = 7] → [4, 5, 6, 7]</code></li>
	<li>根据数位和排序：<code>[22, 14, 33, 7]</code>。数组已经是排序好的。</li>
	<li>因此，将 <code>nums</code> 排列为排序顺序所需的最小交换次数为 0。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [18,43,34,16]</span></p>

<p><strong>输出:</strong> <span class="example-io">2</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>计算每个整数的数位和：<code>[1 + 8 = 9, 4 + 3 = 7, 3 + 4 = 7, 1 + 6 = 7] → [9, 7, 7, 7]</code></li>
	<li>根据数位和排序：<code>[16, 34, 43, 18]</code>。将 <code>18</code> 与 <code>16</code> 交换，再将 <code>43</code> 与 <code>34</code> 交换，得到排序后的数组。</li>
	<li>因此，将 <code>nums</code> 排列为排序顺序所需的最小交换次数为 2。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums</code> 由 <strong>互不相同</strong> 的正整数组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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
