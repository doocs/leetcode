---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3318.Find%20X-Sum%20of%20All%20K-Long%20Subarrays%20I/README.md
rating: 1457
source: 第 419 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 滑动窗口
    - 堆（优先队列）
---

<!-- problem:start -->

# [3318. 计算子数组的 x-sum I](https://leetcode.cn/problems/find-x-sum-of-all-k-long-subarrays-i)

[English Version](/solution/3300-3399/3318.Find%20X-Sum%20of%20All%20K-Long%20Subarrays%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由 <code>n</code> 个整数组成的数组 <code>nums</code>，以及两个整数 <code>k</code> 和 <code>x</code>。</p>

<p>数组的 <strong>x-sum</strong> 计算按照以下步骤进行：</p>

<ul>
	<li>统计数组中所有元素的出现次数。</li>
	<li>仅保留出现频率最高的前 <code>x</code> 种元素。如果两种元素的出现次数相同，则数值<strong> 较大 </strong>的元素被认为出现次数更多。</li>
	<li>计算结果数组的和。</li>
</ul>

<p><strong>注意</strong>，如果数组中的不同元素少于 <code>x</code> 个，则其 <strong>x-sum</strong> 是数组的元素总和。</p>

<p>返回一个长度为 <code>n - k + 1</code> 的整数数组 <code>answer</code>，其中 <code>answer[i]</code> 是 <span data-keyword="subarray-nonempty">子数组</span> <code>nums[i..i + k - 1]</code> 的 <strong>x-sum</strong>。</p>

<p><strong>子数组</strong> 是数组内的一个连续<b> 非空</b> 的元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,1,2,2,3,4,2,3], k = 6, x = 2</span></p>

<p><strong>输出：</strong><span class="example-io">[6,10,12]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>对于子数组 <code>[1, 1, 2, 2, 3, 4]</code>，只保留元素 1 和 2。因此，<code>answer[0] = 1 + 1 + 2 + 2</code>。</li>
	<li>对于子数组 <code>[1, 2, 2, 3, 4, 2]</code>，只保留元素 2 和 4。因此，<code>answer[1] = 2 + 2 + 2 + 4</code>。注意 4 被保留是因为其数值大于出现其他出现次数相同的元素（3 和 1）。</li>
	<li>对于子数组 <code>[2, 2, 3, 4, 2, 3]</code>，只保留元素 2 和 3。因此，<code>answer[2] = 2 + 2 + 2 + 3 + 3</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [3,8,7,8,7,5], k = 2, x = 2</span></p>

<p><strong>输出：</strong><span class="example-io">[11,15,15,15,12]</span></p>

<p><strong>解释：</strong></p>

<p>由于 <code>k == x</code>，<code>answer[i]</code> 等于子数组 <code>nums[i..i + k - 1]</code> 的总和。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
	<li><code>1 &lt;= x &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 有序集合

我们用一个哈希表 $\textit{cnt}$ 统计窗口中每个元素的出现次数，用一个有序集合 $\textit{l}$ 存储窗口中出现次数最多的 $x$ 个元素，用另一个有序集合 $\textit{r}$ 存储剩余的元素。

我们维护一个变量 $\textit{s}$ 表示 $\textit{l}$ 中元素的和。初始时，我们将前 $k$ 个元素加入到窗口中，并且更新有序集合 $\textit{l}$ 和 $\textit{r}$，并且计算 $\textit{s}$ 的值。如果 $\textit{l}$ 的大小小于 $x$，并且 $\textit{r}$ 不为空，我们就循环将 $\textit{r}$ 中的最大元素移动到 $\textit{l}$ 中，直到 $\textit{l}$ 的大小等于 $x$，过程中更新 $\textit{s}$ 的值。如果 $\textit{l}$ 的大小大于 $x$，我们就循环将 $\textit{l}$ 中的最小元素移动到 $\textit{r}$ 中，直到 $\textit{l}$ 的大小等于 $x$，过程中更新 $\textit{s}$ 的值。此时，我们就可以计算出当前窗口的 $\textit{x-sum}$，添加到答案数组中。然后我们将窗口的左边界元素移出，更新 $\textit{cnt}$，并且更新有序集合 $\textit{l}$ 和 $\textit{r}$，以及 $\textit{s}$ 的值。继续遍历数组，直到遍历结束。

时间复杂度 $O(n \times \log k)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

相似题目：

- [3013. 将数组分成最小总代价的子数组 II](/solution/3000-3099/3013.Divide%20an%20Array%20Into%20Subarrays%20With%20Minimum%20Cost%20II/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findXSum(self, nums: List[int], k: int, x: int) -> List[int]:
        def add(v: int):
            if cnt[v] == 0:
                return
            p = (cnt[v], v)
            if l and p > l[0]:
                nonlocal s
                s += p[0] * p[1]
                l.add(p)
            else:
                r.add(p)

        def remove(v: int):
            if cnt[v] == 0:
                return
            p = (cnt[v], v)
            if p in l:
                nonlocal s
                s -= p[0] * p[1]
                l.remove(p)
            else:
                r.remove(p)

        l = SortedList()
        r = SortedList()
        cnt = Counter()
        s = 0
        n = len(nums)
        ans = [0] * (n - k + 1)
        for i, v in enumerate(nums):
            remove(v)
            cnt[v] += 1
            add(v)
            j = i - k + 1
            if j < 0:
                continue
            while r and len(l) < x:
                p = r.pop()
                l.add(p)
                s += p[0] * p[1]
            while len(l) > x:
                p = l.pop(0)
                s -= p[0] * p[1]
                r.add(p)
            ans[j] = s

            remove(nums[j])
            cnt[nums[j]] -= 1
            add(nums[j])
        return ans
```

#### Java

```java
class Solution {
    private TreeSet<int[]> l = new TreeSet<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
    private TreeSet<int[]> r = new TreeSet<>(l.comparator());
    private Map<Integer, Integer> cnt = new HashMap<>();
    private int s;

    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            remove(v);
            cnt.merge(v, 1, Integer::sum);
            add(v);
            int j = i - k + 1;
            if (j < 0) {
                continue;
            }
            while (!r.isEmpty() && l.size() < x) {
                var p = r.pollLast();
                s += p[0] * p[1];
                l.add(p);
            }
            while (l.size() > x) {
                var p = l.pollFirst();
                s -= p[0] * p[1];
                r.add(p);
            }
            ans[j] = s;

            remove(nums[j]);
            cnt.merge(nums[j], -1, Integer::sum);
            add(nums[j]);
        }
        return ans;
    }

    private void remove(int v) {
        if (!cnt.containsKey(v)) {
            return;
        }
        var p = new int[] {cnt.get(v), v};
        if (l.contains(p)) {
            l.remove(p);
            s -= p[0] * p[1];
        } else {
            r.remove(p);
        }
    }

    private void add(int v) {
        if (!cnt.containsKey(v)) {
            return;
        }
        var p = new int[] {cnt.get(v), v};
        if (!l.isEmpty() && l.comparator().compare(l.first(), p) < 0) {
            l.add(p);
            s += p[0] * p[1];
        } else {
            r.add(p);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findXSum(vector<int>& nums, int k, int x) {
        using pii = pair<int, int>;
        set<pii> l, r;
        int s = 0;
        unordered_map<int, int> cnt;
        auto add = [&](int v) {
            if (cnt[v] == 0) {
                return;
            }
            pii p = {cnt[v], v};
            if (!l.empty() && p > *l.begin()) {
                s += p.first * p.second;
                l.insert(p);
            } else {
                r.insert(p);
            }
        };
        auto remove = [&](int v) {
            if (cnt[v] == 0) {
                return;
            }
            pii p = {cnt[v], v};
            auto it = l.find(p);
            if (it != l.end()) {
                s -= p.first * p.second;
                l.erase(it);
            } else {
                r.erase(p);
            }
        };
        vector<int> ans;
        for (int i = 0; i < nums.size(); ++i) {
            remove(nums[i]);
            ++cnt[nums[i]];
            add(nums[i]);

            int j = i - k + 1;
            if (j < 0) {
                continue;
            }

            while (!r.empty() && l.size() < x) {
                pii p = *r.rbegin();
                s += p.first * p.second;
                r.erase(p);
                l.insert(p);
            }
            while (l.size() > x) {
                pii p = *l.begin();
                s -= p.first * p.second;
                l.erase(p);
                r.insert(p);
            }
            ans.push_back(s);

            remove(nums[j]);
            --cnt[nums[j]];
            add(nums[j]);
        }
        return ans;
    }
};
```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
