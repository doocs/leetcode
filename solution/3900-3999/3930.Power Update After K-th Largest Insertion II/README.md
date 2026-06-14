---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3930.Power%20Update%20After%20K-th%20Largest%20Insertion%20II/README.md
tags:
    - 线段树
    - 数组
    - 哈希表
    - 数学
    - 排序
---

<!-- problem:start -->

# [3930. 插入后第 K 大更新的幂 II 🔒](https://leetcode.cn/problems/power-update-after-k-th-largest-insertion-ii)

[English Version](/solution/3900-3999/3930.Power%20Update%20After%20K-th%20Largest%20Insertion%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>p</code>。</p>

<p>同时给定一个二维整数数组&nbsp;<code>queries</code>，其中每个&nbsp;<code>queries[i] = [val<sub>i</sub>, k<sub>i</sub>]</code>。</p>

<p>对于每次查询：</p>

<ul>
	<li>将&nbsp;<code>val<sub>i</sub></code>&nbsp;插入到&nbsp;<code>nums</code>。</li>
	<li>令&nbsp;<code>x</code>&nbsp;为当前&nbsp;<code>nums</code>&nbsp;中第&nbsp;<code>k<sub>i</sub></code>&nbsp;个 <strong>最大</strong> 的元素<b>。</b></li>
	<li>将 <code>p</code>&nbsp;<strong>更新</strong> 为&nbsp;<code>p<sup>x</sup> % (10<sup>9</sup> + 7)</code>。</li>
</ul>

<p>返回数组&nbsp;<code>ans</code>，其中&nbsp;<code>ans[i]</code>&nbsp;表示在第 <code>i</code> 次查询后&nbsp;<code>p</code>&nbsp;的值。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [2], p = 4, queries = [[3,1],[1,2]]</span></p>

<p><span class="example-io"><b>输出：</b>[64,4096]</span></p>

<p><strong>解释：</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th><code>i</code></th>
			<th><code>val<sub>i</sub></code></th>
			<th>当前<br />
			<code>nums</code></th>
			<th><code>k<sub>i</sub></code></th>
			<th>第&nbsp;<code>k<sub>i</sub></code><br />
			大</th>
			<th>p</th>
			<th>新的&nbsp;<code>p = p<sup>k</sup> % (10<sup>9</sup> + 7)</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>0</td>
			<td>3</td>
			<td>[2, 3]</td>
			<td>1</td>
			<td>3</td>
			<td>4</td>
			<td>4<sup>3</sup> % (10<sup>9</sup> + 7) = 64</td>
		</tr>
		<tr>
			<td>1</td>
			<td>1</td>
			<td>[2, 3, 1]</td>
			<td>2</td>
			<td>2</td>
			<td>64</td>
			<td>64<sup>2</sup> % (10<sup>9</sup> + 7) = 4096</td>
		</tr>
	</tbody>
</table>

<p>因此，<code>ans = [64, 4096]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [7,5], p = 6, queries = [[4,3],[7,2]]</span></p>

<p><span class="example-io"><b>输出：</b>[1296,220296870]</span></p>

<p><strong>解释：</strong></p>

<div class="example-block">
<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th><code>i</code></th>
			<th><code>val<sub>i</sub></code></th>
			<th>当前<br />
			<code>nums</code></th>
			<th><code>k<sub>i</sub></code></th>
			<th>第&nbsp;<code>k<sub>i</sub></code><br />
			大</th>
			<th><code>p</code></th>
			<th>新的&nbsp;<code>p = p<sup>k</sup> % (10<sup>9</sup> + 7)</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>0</td>
			<td>4</td>
			<td>[7, 5, 4]</td>
			<td>3</td>
			<td>4</td>
			<td>6</td>
			<td>6<sup>4</sup> % (10<sup>9</sup> + 7) = 1296</td>
		</tr>
		<tr>
			<td>1</td>
			<td>7</td>
			<td>[7, 5, 4, 7]</td>
			<td>2</td>
			<td>7</td>
			<td>1296</td>
			<td>1296<sup>7</sup> % (10<sup>9</sup> + 7) = 220296870</td>
		</tr>
	</tbody>
</table>

<p>因此，<code>ans = [1296, 220296870]</code>。</p>
</div>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 *&nbsp;10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= p &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 2 *&nbsp;10<sup>4</sup></code></li>
	<li><code>1 &lt;= val<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k<sub>i</sub> &lt;= n + i + 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：有序列表

我们用一个有序列表 $\textit{sl}$ 来维护当前的数组 $nums$。对于每次查询，我们将 $val_i$ 插入到 $\textit{sl}$ 中，然后找到 $\textit{sl}$ 中第 $k_i$ 个最大的元素 $x$，利用快速幂算法将 $p$ 更新为 $p^x \bmod (10^9 + 7)$，并将更新后的 $p$ 加入答案数组中。

时间复杂度 $O((n + m) \log (n + m))$，空间复杂度 $O(n + m)$，其中 $n$ 和 $m$ 分别是 $\textit{nums}$ 和 $\textit{queries}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def powerUpdate(
        self, nums: list[int], p: int, queries: list[list[int]]
    ) -> list[int]:
        ans = []
        sl = SortedList(nums)
        mod = 10**9 + 7
        for val, k in queries:
            sl.add(val)
            p = pow(p, sl[-k], mod)
            ans.append(p)
        return ans
```

#### C++

```cpp
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>
#include <vector>

using namespace std;
using namespace __gnu_pbds;

template <typename T>
using ordered_multiset = tree<pair<T, int>, null_type, less<pair<T, int>>,
    rb_tree_tag, tree_order_statistics_node_update>;

class Solution {
public:
    vector<int> powerUpdate(vector<int>& nums, int p, vector<vector<int>>& queries) {
        vector<int> ans;
        ordered_multiset<int> sl;
        const int mod = 1e9 + 7;

        for (int i = 0; i < nums.size(); i++) {
            sl.insert({nums[i], i});
        }

        int next_id = nums.size();

        auto mod_pow = [&](long long base, long long exp) -> long long {
            long long result = 1;
            base %= mod;
            while (exp > 0) {
                if (exp & 1) result = (result * base) % mod;
                base = (base * base) % mod;
                exp >>= 1;
            }
            return result;
        };

        for (const auto& query : queries) {
            int val = query[0];
            int k = query[1];

            sl.insert({val, next_id++});

            auto it = sl.find_by_order(sl.size() - k);
            int kth_largest = it->first;

            p = mod_pow(p, kth_largest);
            ans.push_back(p);
        }

        return ans;
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
