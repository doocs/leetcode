---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3930.Power%20Update%20After%20K-th%20Largest%20Insertion%20II/README_EN.md
---

<!-- problem:start -->

# [3930. Power Update After K-th Largest Insertion II 🔒](https://leetcode.com/problems/power-update-after-k-th-largest-insertion-ii)

[中文文档](/solution/3900-3999/3930.Power%20Update%20After%20K-th%20Largest%20Insertion%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>p</code>.</p>

<p>You are also given a 2D integer array <code>queries</code>, where each <code>queries[i] = [val<sub>i</sub>, k<sub>i</sub>]</code>.</p>

<p>For each query:</p>

<ul>
	<li>Insert <code>val<sub>i</sub></code> into <code>nums</code>.</li>
	<li>Let <code>x</code> be the <code>k<sub>i</sub><sup>th</sup></code> <strong>largest</strong> element in the current <code>nums</code>.</li>
	<li><strong>Update</strong> <code>p</code> to <code>p<sup>x</sup> % (10<sup>9</sup> + 7)</code>.</li>
</ul>

<p>Return an array <code>ans</code> where the <code>ans[i]</code> represents the value of <code>p</code> after processing the <code>i<sup>th</sup></code> query.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2], p = 4, queries = [[3,1],[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[64,4096]</span></p>

<p><strong>Explanation:</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0">
	<thead>
		<tr>
			<th><code>i</code></th>
			<th><code>val<sub>i</sub></code></th>
			<th>Current<br />
			<code>nums</code></th>
			<th><code>k<sub>i</sub></code></th>
			<th><code>k<sub>i</sub><sup>th</sup></code><br />
			largest</th>
			<th>p</th>
			<th>New <code>p = p<sup>k</sup> % (10<sup>9</sup> + 7)</code></th>
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

<p>Thus, <code>ans = [64, 4096]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7,5], p = 6, queries = [[4,3],[7,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1296,220296870]</span></p>

<p><strong>Explanation:</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0">
	<thead>
		<tr>
			<th><code>i</code></th>
			<th><code>val<sub>i</sub></code></th>
			<th>Current​​​​​​​<br />
			<code>nums</code></th>
			<th><code>k<sub>i</sub></code></th>
			<th><code>k<sub>i</sub><sup>th</sup></code><br />
			largest</th>
			<th><code>p</code></th>
			<th>New <code>p = p<sup>k</sup> % (10<sup>9</sup> + 7)</code></th>
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

<p>Thus, <code>ans = [1296, 220296870]</code></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>​​​​​​​1 &lt;= p &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code><sup>​​​​​​​</sup>1 &lt;= val<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k<sub>i</sub> &lt;= n + i + 1</code>​​​​​​​</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorted List

We use a sorted list $\textit{sl}$ to maintain the current array $nums$. For each query, we insert $val_i$ into $\textit{sl}$, then find the $k_i$-th largest element $x$ in $\textit{sl}$. Using fast exponentiation, we update $p$ to $p^x \bmod (10^9 + 7)$, and append the updated $p$ to the answer array.

The time complexity is $O((n + m) \log (n + m))$, and the space complexity is $O(n + m)$, where $n$ and $m$ are the lengths of $\textit{nums}$ and $\textit{queries}$, respectively.

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
