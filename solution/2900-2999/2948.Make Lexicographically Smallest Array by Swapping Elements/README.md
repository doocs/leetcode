# [2948. 交换得到字典序最小的数组](https://leetcode.cn/problems/make-lexicographically-smallest-array-by-swapping-elements)

[English Version](/solution/2900-2999/2948.Make%20Lexicographically%20Smallest%20Array%20by%20Swapping%20Elements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0 </strong>开始的 <strong>正整数</strong> 数组 <code>nums</code> 和一个 <strong>正整数</strong> <code>limit</code> 。</p>

<p>在一次操作中，你可以选择任意两个下标 <code>i</code> 和 <code>j</code>，<strong>如果</strong> 满足 <code>|nums[i] - nums[j]| &lt;= limit</code> ，则交换 <code>nums[i]</code> 和 <code>nums[j]</code> 。</p>

<p>返回执行任意次操作后能得到的 <strong>字典序最小的数组</strong><em> </em>。</p>

<p>如果在数组 <code>a</code> 和数组 <code>b</code> 第一个不同的位置上，数组 <code>a</code> 中的对应字符比数组 <code>b</code> 中的对应字符的字典序更小，则认为数组 <code>a</code> 就比数组 <code>b</code> 字典序更小。例如，数组 <code>[2,10,3]</code> 比数组 <code>[10,2,3]</code> 字典序更小，下标 <code>0</code> 处是两个数组第一个不同的位置，且 <code>2 &lt; 10</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,5,3,9,8], limit = 2
<strong>输出：</strong>[1,3,5,8,9]
<strong>解释：</strong>执行 2 次操作：
- 交换 nums[1] 和 nums[2] 。数组变为 [1,3,5,9,8] 。
- 交换 nums[3] 和 nums[4] 。数组变为 [1,3,5,8,9] 。
即便执行更多次操作，也无法得到字典序更小的数组。
注意，执行不同的操作也可能会得到相同的结果。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,7,6,18,2,1], limit = 3
<strong>输出：</strong>[1,6,7,18,1,2]
<strong>解释：</strong>执行 3 次操作：
- 交换 nums[1] 和 nums[2] 。数组变为 [1,6,7,18,2,1] 。
- 交换 nums[0] 和 nums[4] 。数组变为 [2,6,7,18,1,1] 。
- 交换 nums[0] 和 nums[5] 。数组变为 [1,6,7,18,1,2] 。
即便执行更多次操作，也无法得到字典序更小的数组。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,7,28,19,10], limit = 3
<strong>输出：</strong>[1,7,28,19,10]
<strong>解释：</strong>[1,7,28,19,10] 是字典序最小的数组，因为不管怎么选择下标都无法执行操作。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= limit &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def lexicographicallySmallestArray(self, nums: List[int], limit: int) -> List[int]:
        n = len(nums)
        arr = sorted(zip(nums, range(n)))
        ans = [0] * n
        i = 0
        while i < n:
            j = i + 1
            while j < n and arr[j][0] - arr[j - 1][0] <= limit:
                j += 1
            idx = sorted(k for _, k in arr[i:j])
            for k, (x, _) in zip(idx, arr[i:j]):
                ans[k] = x
            i = j
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> nums[i] - nums[j]);
        int[] ans = new int[n];
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && nums[idx[j]] - nums[idx[j - 1]] <= limit) {
                ++j;
            }
            Integer[] t = Arrays.copyOfRange(idx, i, j);
            Arrays.sort(t, (x, y) -> x - y);
            for (int k = i; k < j; ++k) {
                ans[t[k - i]] = nums[idx[k]];
            }
            i = j;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> lexicographicallySmallestArray(vector<int>& nums, int limit) {
        int n = nums.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return nums[i] < nums[j];
        });
        vector<int> ans(n);
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && nums[idx[j]] - nums[idx[j - 1]] <= limit) {
                ++j;
            }
            vector<int> t(idx.begin() + i, idx.begin() + j);
            sort(t.begin(), t.end());
            for (int k = i; k < j; ++k) {
                ans[t[k - i]] = nums[idx[k]];
            }
            i = j;
        }
        return ans;
    }
};
```

### **Go**

```go
func lexicographicallySmallestArray(nums []int, limit int) []int {
	n := len(nums)
	idx := make([]int, n)
	for i := range idx {
		idx[i] = i
	}
	slices.SortFunc(idx, func(i, j int) int { return nums[i] - nums[j] })
	ans := make([]int, n)
	for i := 0; i < n; {
		j := i + 1
		for j < n && nums[idx[j]]-nums[idx[j-1]] <= limit {
			j++
		}
		t := slices.Clone(idx[i:j])
		slices.Sort(t)
		for k := i; k < j; k++ {
			ans[t[k-i]] = nums[idx[k]]
		}
		i = j
	}
	return ans
}
```

### **TypeScript**

```ts
function lexicographicallySmallestArray(nums: number[], limit: number): number[] {
    const n: number = nums.length;
    const idx: number[] = Array.from({ length: n }, (_, i) => i);
    idx.sort((i, j) => nums[i] - nums[j]);
    const ans: number[] = Array(n).fill(0);
    for (let i = 0; i < n; ) {
        let j = i + 1;
        while (j < n && nums[idx[j]] - nums[idx[j - 1]] <= limit) {
            j++;
        }
        const t: number[] = idx.slice(i, j).sort((a, b) => a - b);
        for (let k: number = i; k < j; k++) {
            ans[t[k - i]] = nums[idx[k]];
        }
        i = j;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
