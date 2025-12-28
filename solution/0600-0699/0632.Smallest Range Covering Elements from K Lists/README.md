---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0632.Smallest%20Range%20Covering%20Elements%20from%20K%20Lists/README.md
tags:
    - 贪心
    - 数组
    - 哈希表
    - 排序
    - 滑动窗口
    - 堆（优先队列）
---

<!-- problem:start -->

# [632. 最小区间](https://leetcode.cn/problems/smallest-range-covering-elements-from-k-lists)

[English Version](/solution/0600-0699/0632.Smallest%20Range%20Covering%20Elements%20from%20K%20Lists/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你有&nbsp;<code>k</code>&nbsp;个 <strong>非递减排列</strong> 的整数列表。找到一个 <strong>最小 </strong>区间，使得&nbsp;<code>k</code>&nbsp;个列表中的每个列表至少有一个数包含在其中。</p>

<p>我们定义如果&nbsp;<code>b-a &lt; d-c</code>&nbsp;或者在&nbsp;<code>b-a == d-c</code>&nbsp;时&nbsp;<code>a &lt; c</code>，则区间 <code>[a,b]</code> 比 <code>[c,d]</code> 小。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
<strong>输出：</strong>[20,24]
<strong>解释：</strong> 
列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [[1,2,3],[1,2,3],[1,2,3]]
<strong>输出：</strong>[1,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums.length == k</code></li>
	<li><code>1 &lt;= k &lt;= 3500</code></li>
	<li><code>1 &lt;= nums[i].length &lt;= 50</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> 按非递减顺序排列</li>
</ul>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 滑动窗口

我们将每个数字 $x$ 及其所在的组 $i$，构成数据项 $(x, i)$，存放在一个新的数组 $t$ 中，然后对 $t$ 按照数字的大小进行排序（类似于将多个有序数组合并成一个新的有序数组）。

接下来，我们遍历 $t$ 中的每个数据项，只看其中数字所在的组，用哈希表记录滑动窗口内的数字出现的组，如果组数为 $k$，说明当前窗口满足题目要求，此时算出窗口的起始和结束位置，更新答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为所有数组中数字的个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestRange(self, nums: List[List[int]]) -> List[int]:
        t = [(x, i) for i, v in enumerate(nums) for x in v]
        t.sort()
        cnt = Counter()
        ans = [-inf, inf]
        j = 0
        for i, (b, v) in enumerate(t):
            cnt[v] += 1
            while len(cnt) == len(nums):
                a = t[j][0]
                x = b - a - (ans[1] - ans[0])
                if x < 0 or (x == 0 and a < ans[0]):
                    ans = [a, b]
                w = t[j][1]
                cnt[w] -= 1
                if cnt[w] == 0:
                    cnt.pop(w)
                j += 1
        return ans
```

#### Java

```java
class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        int n = 0;
        for (var v : nums) {
            n += v.size();
        }
        int[][] t = new int[n][2];
        int k = nums.size();
        for (int i = 0, j = 0; i < k; ++i) {
            for (int x : nums.get(i)) {
                t[j++] = new int[] {x, i};
            }
        }
        Arrays.sort(t, (a, b) -> a[0] - b[0]);
        int j = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        int[] ans = new int[] {-1000000, 1000000};
        for (int[] e : t) {
            int b = e[0];
            int v = e[1];
            cnt.merge(v, 1, Integer::sum);
            while (cnt.size() == k) {
                int a = t[j][0];
                int w = t[j][1];
                int x = b - a - (ans[1] - ans[0]);
                if (x < 0 || (x == 0 && a < ans[0])) {
                    ans[0] = a;
                    ans[1] = b;
                }
                if (cnt.merge(w, -1, Integer::sum) == 0) {
                    cnt.remove(w);
                }
                ++j;
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
    vector<int> smallestRange(vector<vector<int>>& nums) {
        int n = 0;
        for (auto& v : nums) n += v.size();
        vector<pair<int, int>> t(n);
        int k = nums.size();
        for (int i = 0, j = 0; i < k; ++i) {
            for (int v : nums[i]) {
                t[j++] = {v, i};
            }
        }
        sort(t.begin(), t.end());
        int j = 0;
        unordered_map<int, int> cnt;
        vector<int> ans = {-1000000, 1000000};
        for (int i = 0; i < n; ++i) {
            int b = t[i].first;
            int v = t[i].second;
            ++cnt[v];
            while (cnt.size() == k) {
                int a = t[j].first;
                int w = t[j].second;
                int x = b - a - (ans[1] - ans[0]);
                if (x < 0 || (x == 0 && a < ans[0])) {
                    ans[0] = a;
                    ans[1] = b;
                }
                if (--cnt[w] == 0) {
                    cnt.erase(w);
                }
                ++j;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func smallestRange(nums [][]int) []int {
	t := [][]int{}
	for i, x := range nums {
		for _, v := range x {
			t = append(t, []int{v, i})
		}
	}
	sort.Slice(t, func(i, j int) bool { return t[i][0] < t[j][0] })
	ans := []int{-1000000, 1000000}
	j := 0
	cnt := map[int]int{}
	for _, x := range t {
		b, v := x[0], x[1]
		cnt[v]++
		for len(cnt) == len(nums) {
			a, w := t[j][0], t[j][1]
			x := b - a - (ans[1] - ans[0])
			if x < 0 || (x == 0 && a < ans[0]) {
				ans[0], ans[1] = a, b
			}
			cnt[w]--
			if cnt[w] == 0 {
				delete(cnt, w)
			}
			j++
		}
	}
	return ans
}
```

#### Rust

```rust
impl Solution {
    pub fn smallest_range(nums: Vec<Vec<i32>>) -> Vec<i32> {
        let mut t = vec![];
        for (i, x) in nums.iter().enumerate() {
            for &v in x {
                t.push((v, i));
            }
        }
        t.sort_unstable();
        let (mut ans, n) = (vec![-1000000, 1000000], nums.len());
        let mut j = 0;
        let mut cnt = std::collections::HashMap::new();

        for (b, v) in t.iter() {
            let (b, v) = (*b, *v);
            if let Some(x) = cnt.get_mut(&v) {
                *x += 1;
            } else {
                cnt.insert(v, 1);
            }
            while cnt.len() == n {
                let (a, w) = t[j];
                let x = b - a - (ans[1] - ans[0]);
                if x < 0 || (x == 0 && a < ans[0]) {
                    ans = vec![a, b];
                }
                if let Some(x) = cnt.get_mut(&w) {
                    *x -= 1;
                }
                if cnt[&w] == 0 {
                    cnt.remove(&w);
                }
                j += 1;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：优先队列（小根堆）

<!-- tabs:start -->

#### TypeScript

```ts
const smallestRange = (nums: number[][]): number[] => {
    const pq = new PriorityQueue<number[]>((a, b) => a[0] - b[0]);
    const n = nums.length;
    let [l, r, max] = [0, Number.POSITIVE_INFINITY, Number.NEGATIVE_INFINITY];

    for (let j = 0; j < n; j++) {
        const x = nums[j][0];
        pq.enqueue([x, j, 0]);
        max = Math.max(max, x);
    }

    while (pq.size() === n) {
        const [min, j, i] = pq.dequeue();

        if (max - min < r - l) {
            [l, r] = [min, max];
        }

        const iNext = i + 1;
        if (iNext < nums[j].length) {
            const next = nums[j][iNext];
            pq.enqueue([next, j, iNext]);
            max = Math.max(max, next);
        }
    }

    return [l, r];
};
```

#### JavaScript

```js
const smallestRange = nums => {
    const pq = new PriorityQueue((a, b) => a[0] - b[0]);
    const n = nums.length;
    let [l, r, max] = [0, Number.POSITIVE_INFINITY, Number.NEGATIVE_INFINITY];

    for (let j = 0; j < n; j++) {
        const x = nums[j][0];
        pq.enqueue([x, j, 0]);
        max = Math.max(max, x);
    }

    while (pq.size() === n) {
        const [min, j, i] = pq.dequeue();

        if (max - min < r - l) {
            [l, r] = [min, max];
        }

        const iNext = i + 1;
        if (iNext < nums[j].length) {
            const next = nums[j][iNext];
            pq.enqueue([next, j, iNext]);
            max = Math.max(max, next);
        }
    }

    return [l, r];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
