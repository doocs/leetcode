---
comments: true
difficulty: 困难
rating: 2162
source: 第 502 场周赛 Q4
tags:
    - 数组
    - 哈希表
    - 二分查找
    - 后缀数组
    - 哈希函数
    - 滚动哈希
---

<!-- problem:start -->

# [3934. 最短唯一子数组](https://leetcode.cn/problems/smallest-unique-subarray)

[English Version](/solution/3900-3999/3934.Smallest%20Unique%20Subarray/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 。</p>

<p>找出 <code>nums</code> 中与其他任何 <strong>子数组</strong> 均 <strong>不</strong> <strong>相同</strong> 的 <strong>子数组</strong> 的 <strong>最小 </strong>长度。</p>

<p>返回一个整数，表示此类 <strong>子数组</strong> 的 <strong>最小可能长度</strong> 。</p>

<p><strong>子数组</strong> 是数组中的一个连续的非空元素序列。</p>

<p>如果两个 <strong>子数组</strong> 具有相同的长度，并且对应位置的元素也相同，则认为这两个 <strong>子数组</strong> 是相同的。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,3,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>长度为 1 的子数组：<code>[3]</code> → 出现 3 次</li>
	<li>长度为 2 的子数组：<code>[3, 3]</code> → 出现 2 次</li>
	<li>长度为 3 的子数组：<code>[3, 3, 3]</code> → 出现 1 次</li>
</ul>

<p>子数组 <code>[3, 3, 3]</code> 是唯一的，因此最小唯一子数组的长度为 3。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,1,2,3,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>长度为 1 的子数组：</p>

<ul>
	<li><code>[2]</code> → 出现 2 次</li>
	<li><code>[1]</code> → 出现 1 次</li>
	<li><code>[3]</code> → 出现 2 次</li>
</ul>
子数组 <code>[1]</code> 是唯一的，因此最小唯一子数组的长度为 1。</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,1,2,2,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>长度为 1 的子数组：</p>

<ul>
	<li><code>[1]</code> → 出现 3 次</li>
	<li><code>[2]</code> → 出现 2 次</li>
</ul>

<p>长度为 2 的子数组：</p>

<ul>
	<li><code>[1, 1]</code> → 出现 1 次</li>
	<li><code>[1, 2]</code> → 出现 1 次</li>
	<li><code>[2, 2]</code> → 出现 1 次</li>
	<li><code>[2, 1]</code> → 出现 1 次</li>
</ul>
至少有一个长度为 2 的子数组是唯一的，因此最小唯一子数组的长度为 2。</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滚动哈希 + 二分查找

<!-- thinking:start -->

> **思考**
>
> 若存在长度为 $L$ 的唯一子数组，则更长的子数组必然也存在唯一者（把它扩一格仍只出现一次的那个核）。因此最短唯一长度对 $L$ 单调，可以二分。
>
> 判定「是否存在长度为 $\textit{mid}$ 且全局只出现一次的子数组」时，枚举所有窗口并用哈希计数即可。滚动哈希把窗口比较降为 $O(1)$ 滑动，整次判定 $O(n)$。
>
> 二分 $O(\log n)$ 次判定，总时间 $O(n \log n)$。

<!-- thinking:end -->

在 $\textit{mid}_\textit{len} = \frac{\min_\textit{len} + \max_\textit{len}}{2}$ 下，对于每个候选子数组长度 $\textit{mid}_\textit{len}$，

用滚动哈希遍历所有长度为 $\textit{mid}_\textit{len}$ 的子数组，记录每个哈希值出现的次数。

若存在某个哈希值恰好出现一次，则说明该长度存在唯一子数组，能因此尝试把 $\max_\textit{len}$ 缩小到 $\textit{mid}_\textit{len} - 1$；

否则说明该长度不存在唯一子数组，必须让 $\min_\textit{len}$ 上升到 $\textit{mid}_\textit{len} + 1$。

这是因为一旦长度为 $l$ 的子数组做到了唯一性，长度 $> l$ 的子数组也必然有唯一性。

时间复杂度 $O(n \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

原因是做了 $O(\log n)$ 次的二分查找，每次滚动哈希需要 $O(n)$ 的时间。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestUniqueSubarray(self, nums: list[int]) -> int:
        self.nums = nums

        self.base = 19
        self.modulo = 10**9 + 7
        self.powers = [1] * (len(nums) + 1)

        self.hash_values: dict[int, int] = dict()

        min_possible_len = len(nums)  # Base case.

        min_len, max_len = 1, len(nums)  # For binary search usage.

        while min_len <= max_len:
            mid_len = (min_len + max_len) // 2

            if self._check_uniqueness(mid_len):
                if mid_len < min_possible_len:
                    min_possible_len = mid_len
                max_len = mid_len - 1

            else:
                min_len = mid_len + 1

        return min_possible_len

    def _check_uniqueness(self, subarray_len: int) -> bool:
        # Only need to reset leftmost power to 1 before usage.
        self.powers[0] = 1  # Because powers are calculated by bottom-up.

        for idx in range(1, len(self.nums) + 1):
            self.powers[idx] = (self.powers[idx - 1] * self.base) % self.modulo

        current_hash = 0
        for idx in range(subarray_len):
            current_hash *= self.base
            current_hash += self.nums[idx]
            current_hash %= self.modulo

        self.hash_values.clear()  # Clear before usage.
        self.hash_values[current_hash] = 1

        for idx in range(1, len(self.nums) - subarray_len + 1):
            # Window shifts: deduct leftmost num's share from hash value.
            current_hash -= self.powers[subarray_len - 1] * self.nums[idx - 1]

            # Integrate newly added num's share into hash value.
            current_hash *= self.base
            current_hash += self.nums[idx + subarray_len - 1]
            current_hash %= self.modulo

            if current_hash not in self.hash_values.keys():
                self.hash_values.update({current_hash: 0})
            self.hash_values[current_hash] += 1

        return 1 in self.hash_values.values()
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：二分查找 + 倍增

<!-- thinking:start -->

> **思考**
>
> $n$ 达到 $10^5$，滚动哈希会碰撞。唯一长度仍然单调，二分的对象还是长度，判定时需要精确比较窗口。
>
> 长度为 $2^k$ 的窗口可以由两段长度为 $2^{k-1}$ 的窗口拼出来。任意长度 $L$ 的窗口，也能被它开头和结尾各一段长度为 $2^k$ 的窗口盖住，其中 $k=\lfloor\log_2 L\rfloor$。
>
> 两段编号都相同，整个窗口就相同。先按这个规则从短到长给窗口编号，二分时只统计这一对数出现了几次。

<!-- thinking:end -->

我们把从下标 $i$ 开始、长度为 $2^k$ 的子数组编成 $\textit{names}[k][i]$。第 $0$ 层就是 $\textit{nums}$ 本身。由第 $k$ 层生成下一层时，把 $\textit{names}[k][i]$ 和 $\textit{names}[k][i+2^k]$ 配成一对，相同的配对同一个编号。 $2\cdot\textit{half}>n$ 时停止，更长的块已经放不进数组。

最短唯一长度对长度单调，在 $[1, n]$ 上二分。对于长度 $L$，令 $k=\lfloor\log_2 L\rfloor$。起点为 $i$ 的窗口由 $\textit{names}[k][i]$ 和 $\textit{names}[k][i+L-2^k]$ 确定：这两段分别是窗口的前 $2^k$ 个元素和后 $2^k$ 个元素，合起来盖住整个窗口。统计每个数对出现的次数，有数对只出现一次就说明该长度可行，二分向左收缩，否则向右。

时间复杂度 $O(n\log^2 n)$，空间复杂度 $O(n\log n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestUniqueSubarray(self, nums: list[int]) -> int:
        n = len(nums)
        names = [nums]  # names[k][i] same for equal windows nums[i:i + 2**k]
        for k in range(n.bit_length() - 1):  # blocks of 2, 4, 8, ... while they fit
            ids = {}  # pair of halves -> number of the whole window
            names.append(
                [
                    ids.setdefault(pair, len(ids))
                    for pair in zip(names[k], names[k][2**k :])
                ]
            )

        def found(length):  # is some window of this length unique?
            k = (
                length.bit_length() - 1
            )  # its first 2**k elements and its last 2**k cover it
            return 1 in Counter(zip(names[k], names[k][length - 2**k :])).values()

        return bisect_left(range(1, n), True, key=found) + 1
```

#### Java

```java
class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        List<int[]> names = new ArrayList<>(); // names.get(k)[i] is equal for equal windows of
                                               // length 2^k starting at i
        names.add(nums);
        for (int half = 1; 2 * half <= n; half *= 2) { // blocks of 2, 4, 8, ... while they fit
            long[] pairs = pairs(names.get(names.size() - 1), half);
            long[] ids = Arrays.stream(pairs)
                             .sorted()
                             .distinct()
                             .toArray(); // a pair's number: its place among the different pairs
            names.add(
                Arrays.stream(pairs).mapToInt(pair -> Arrays.binarySearch(ids, pair)).toArray());
        }

        int lo = 1, hi = n; // the first length that works is in lo..hi
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (found(names, mid))
                hi = mid;
            else
                lo = mid + 1;
        }
        return lo;
    }

    private boolean found(List<int[]> names, int length) { // is some window of this length unique?
        int k = 31
            - Integer.numberOfLeadingZeros(
                length); // its first 2^k elements and its last 2^k cover it
        long[] pairs = pairs(names.get(k), length - (1 << k));
        Arrays.sort(pairs); // equal pairs are neighbors now
        for (int i = 0; i < pairs.length; i++)
            if ((i == 0 || pairs[i - 1] != pairs[i])
                && (i + 1 == pairs.length || pairs[i] != pairs[i + 1]))
                return true;
        return false;
    }

    private long[] pairs(int[] block, int shift) { // every (block[i], block[i + shift]) as one long
        long[] pairs = new long[block.length - shift];
        for (int i = shift; i < block.length; i++)
            pairs[i - shift] = (long) block[i - shift] << 32 | block[i];
        return pairs;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestUniqueSubarray(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> names{nums}; // names[k][i] is equal for equal windows of length 2^k starting at i
        for (int half = 1; 2 * half <= n; half *= 2) { // blocks of 2, 4, 8, ... while they fit
            unordered_map<long long, int> ids; // pair of halves -> number of the whole window
            vector<int>& block = names.back();
            vector<int> whole;
            for (int i = half; i < (int) block.size(); ++i) {
                long long key = (long long) block[i - half] << 32 | block[i];
                whole.push_back(ids.try_emplace(key, (int) ids.size()).first->second);
            }
            names.push_back(std::move(whole));
        }

        auto found = [&](int length) { // is some window of this length unique?
            int k = 31 - __builtin_clz(length); // its first 2^k elements and its last 2^k cover it
            vector<int>& block = names[k];
            int shift = length - (1 << k);
            unordered_map<long long, int> count;
            for (int i = shift; i < (int) block.size(); ++i) {
                long long key = (long long) block[i - shift] << 32 | block[i];
                ++count[key];
            }
            for (auto& entry : count) {
                if (entry.second == 1) {
                    return true;
                }
            }
            return false;
        };

        int lo = 1, hi = n; // the first length that works is in lo..hi
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (found(mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
};
```

#### Go

```go
func smallestUniqueSubarray(nums []int) int {
	n := len(nums)
	names := [][]int{nums}                  // names[k][i] is equal for equal windows of length 2^k starting at i
	for half := 1; 2*half <= n; half *= 2 { // blocks of 2, 4, 8, ... while they fit
		ids := map[[2]int]int{} // pair of halves -> number of the whole window
		block := names[len(names)-1]
		whole := make([]int, len(block)-half)
		for i := half; i < len(block); i++ {
			pair := [2]int{block[i-half], block[i]}
			if _, seen := ids[pair]; !seen {
				ids[pair] = len(ids)
			}
			whole[i-half] = ids[pair]
		}
		names = append(names, whole)
	}

	found := func(length int) bool { // is some window of this length unique?
		k := bits.Len(uint(length)) - 1 // its first 2^k elements and its last 2^k cover it
		block, shift := names[k], length-(1<<k)
		count := map[[2]int]int{}
		for i := shift; i < len(block); i++ {
			count[[2]int{block[i-shift], block[i]}]++
		}
		for _, c := range count {
			if c == 1 {
				return true
			}
		}
		return false
	}

	return 1 + sort.Search(n-1, func(i int) bool { return found(i + 1) })
}
```

#### JavaScript

```js
var smallestUniqueSubarray = function (nums) {
    const n = nums.length;
    const key = (a, b) => a * 2 ** 17 + b; // a pair of names as one exact number: every name is below 2^17
    const names = [nums]; // names[k][i] is equal for equal windows of length 2^k starting at i
    for (let half = 1; 2 * half <= n; half *= 2) {
        // blocks of 2, 4, 8, ... while they fit
        const ids = new Map(); // pair of halves -> number of the whole window
        const block = names.at(-1),
            whole = [];
        for (let i = half; i < block.length; i++) {
            const pair = key(block[i - half], block[i]);
            if (!ids.has(pair)) ids.set(pair, ids.size);
            whole.push(ids.get(pair));
        }
        names.push(whole);
    }

    const found = length => {
        // is some window of this length unique?
        const k = 31 - Math.clz32(length); // its first 2^k elements and its last 2^k cover it
        const block = names[k],
            shift = length - 2 ** k,
            count = new Map();
        for (let i = shift; i < block.length; i++) {
            const pair = key(block[i - shift], block[i]);
            count.set(pair, (count.get(pair) ?? 0) + 1);
        }
        return [...count.values()].includes(1);
    };

    let lo = 1,
        hi = n; // the first length that works is in lo..hi
    while (lo < hi) {
        const mid = (lo + hi) >> 1;
        if (found(mid)) hi = mid;
        else lo = mid + 1;
    }
    return lo;
};
```

#### TypeScript

```ts
function smallestUniqueSubarray(nums: number[]): number {
    const n = nums.length;
    const key = (a: number, b: number): number => a * 2 ** 17 + b; // a pair of names as one exact number: every name is below 2^17
    const names: number[][] = [nums]; // names[k][i] is equal for equal windows of length 2^k starting at i
    for (let half = 1; 2 * half <= n; half *= 2) {
        // blocks of 2, 4, 8, ... while they fit
        const ids = new Map<number, number>(); // pair of halves -> number of the whole window
        const block = names.at(-1)!,
            whole: number[] = [];
        for (let i = half; i < block.length; i++) {
            const pair = key(block[i - half], block[i]);
            if (!ids.has(pair)) ids.set(pair, ids.size);
            whole.push(ids.get(pair)!);
        }
        names.push(whole);
    }

    const found = (length: number): boolean => {
        // is some window of this length unique?
        const k = 31 - Math.clz32(length); // its first 2^k elements and its last 2^k cover it
        const block = names[k],
            shift = length - 2 ** k,
            count = new Map<number, number>();
        for (let i = shift; i < block.length; i++) {
            const pair = key(block[i - shift], block[i]);
            count.set(pair, (count.get(pair) ?? 0) + 1);
        }
        return [...count.values()].includes(1);
    };

    let lo = 1,
        hi = n; // the first length that works is in lo..hi
    while (lo < hi) {
        const mid = (lo + hi) >> 1;
        if (found(mid)) hi = mid;
        else lo = mid + 1;
    }
    return lo;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
