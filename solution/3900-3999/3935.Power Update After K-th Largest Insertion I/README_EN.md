---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3935.Power%20Update%20After%20K-th%20Largest%20Insertion%20I/README_EN.md
tags:
    - Segment Tree
    - Array
    - Hash Table
    - Math
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3935. Power Update After K-th Largest Insertion I 🔒](https://leetcode.com/problems/power-update-after-k-th-largest-insertion-i)

[中文文档](/solution/3900-3999/3935.Power%20Update%20After%20K-th%20Largest%20Insertion%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>p</code>.</p>

<p>You are also given a 2D integer array <code>queries</code>, where each <code>queries[i] = [val<sub>i</sub>, k<sub>i</sub>]</code> and the difference between <strong>consecutive</strong> <code>k<sub>i</sub></code> values is always <strong>less</strong> than 10.</p>

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

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
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

<div class="example-block">
<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
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
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 &times; 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>​​​​​​​1 &lt;= p &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 2 &times; 10<sup>4</sup></code></li>
	<li><code><sup>​​​​​​​</sup>1 &lt;= val<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k<sub>i</sub> &lt;= n + i + 1</code></li>
	<li><code>|k<sub>i</sub> - k<sub>i - 1</sub>| &lt; 10</code> for <code>i &gt; 0</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Sorted Sets

We use two sorted sets, $l$ and $r$, to maintain the current array $nums$. All elements in $l$ are less than or equal to those in $r$, and the number of elements in $r$ is equal to $k_i$.

For each query, we insert $val_i$ into $r$, then move the smallest element in $r$ to $l$ until the size of $r$ becomes $k_i$. At this point, the smallest element in $r$ is the $k_i$-th largest element in the current $nums$. We then use fast exponentiation to update $p$ as $p^x \bmod (10^9 + 7)$, and append the updated $p$ to the answer array.

The time complexity is $O((n + m) \log (n + m))$, and the space complexity is $O(n + m)$, where $n$ and $m$ are the lengths of $\textit{nums}$ and $\textit{queries}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def powerUpdate(
        self, nums: list[int], p: int, queries: list[list[int]]
    ) -> list[int]:
        l = SortedList()
        r = SortedList(nums)
        ans = []
        mod = 10**9 + 7
        for val, k in queries:
            r.add(val)
            l.add(r.pop(0))
            while len(r) < k:
                r.add(l.pop())
            while len(r) > k:
                l.add(r.pop(0))
            x = r[0]
            p = pow(p, x, mod)
            ans.append(p)
        return ans
```

#### Java

```java
class Solution {
    private void add(TreeMap<Integer, Integer> t, int x) {
        t.merge(x, 1, Integer::sum);
    }

    private void remove(TreeMap<Integer, Integer> t, int x) {
        int v = t.get(x);

        if (v == 1) {
            t.remove(x);
        } else {
            t.put(x, v - 1);
        }
    }

    private long qpow(long a, int b, int mod) {
        long ans = 1;

        while (b > 0) {
            if ((b & 1) == 1) {
                ans = ans * a % mod;
            }

            a = a * a % mod;
            b >>= 1;
        }

        return ans;
    }

    public List<Integer> powerUpdate(int[] nums, int p, int[][] queries) {
        TreeMap<Integer, Integer> l = new TreeMap<>();
        TreeMap<Integer, Integer> r = new TreeMap<>();

        int sz1 = 0, sz2 = nums.length;

        for (int x : nums) {
            add(r, x);
        }

        int mod = 1_000_000_007;

        List<Integer> ans = new ArrayList<>();

        for (int[] q : queries) {
            int val = q[0];
            int k = q[1];

            add(r, val);
            ++sz2;

            int v = r.firstKey();

            remove(r, v);
            --sz2;

            add(l, v);
            ++sz1;

            while (sz2 < k) {
                v = l.lastKey();

                remove(l, v);
                --sz1;

                add(r, v);
                ++sz2;
            }

            while (sz2 > k) {
                v = r.firstKey();

                remove(r, v);
                --sz2;

                add(l, v);
                ++sz1;
            }

            int x = r.firstKey();

            p = (int) qpow(p, x, mod);

            ans.add(p);
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    using ll = long long;

    void add(map<int, int>& mp, int x) {
        ++mp[x];
    }

    void remove(map<int, int>& mp, int x) {
        if (--mp[x] == 0) {
            mp.erase(x);
        }
    }

    ll qpow(ll a, int b, int mod) {
        ll ans = 1;

        while (b) {
            if (b & 1) {
                ans = ans * a % mod;
            }

            a = a * a % mod;
            b >>= 1;
        }

        return ans;
    }

    vector<int> powerUpdate(vector<int>& nums, int p, vector<vector<int>>& queries) {
        map<int, int> l, r;

        int sz1 = 0, sz2 = nums.size();

        for (int x : nums) {
            add(r, x);
        }

        const int mod = 1e9 + 7;

        vector<int> ans;

        for (auto& q : queries) {
            int val = q[0];
            int k = q[1];

            add(r, val);
            ++sz2;

            int v = r.begin()->first;

            remove(r, v);
            --sz2;

            add(l, v);
            ++sz1;

            while (sz2 < k) {
                v = l.rbegin()->first;

                remove(l, v);
                --sz1;

                add(r, v);
                ++sz2;
            }

            while (sz2 > k) {
                v = r.begin()->first;

                remove(r, v);
                --sz2;

                add(l, v);
                ++sz1;
            }

            int x = r.begin()->first;

            p = qpow(p, x, mod);

            ans.push_back(p);
        }

        return ans;
    }
};
```

#### Go

```go
func powerUpdate(nums []int, p int, queries [][]int) []int {
	l := redblacktree.New[int, int]()
	r := redblacktree.New[int, int]()

	merge := func(st *redblacktree.Tree[int, int], x, v int) {
		c, _ := st.Get(x)

		if c+v == 0 {
			st.Remove(x)
		} else {
			st.Put(x, c+v)
		}
	}

	sz1, sz2 := 0, len(nums)

	for _, x := range nums {
		merge(r, x, 1)
	}

	const mod int = 1e9 + 7

	qpow := func(a, b int) int {
		ans := 1

		for b > 0 {
			if b&1 == 1 {
				ans = ans * a % mod
			}

			a = a * a % mod
			b >>= 1
		}

		return ans
	}

	ans := make([]int, 0, len(queries))

	for _, q := range queries {
		val, k := q[0], q[1]

		merge(r, val, 1)
		sz2++

		node := r.Left()

		merge(r, node.Key, -1)
		sz2--

		merge(l, node.Key, 1)
		sz1++

		for sz2 < k {
			node = l.Right()

			merge(l, node.Key, -1)
			sz1--

			merge(r, node.Key, 1)
			sz2++
		}

		for sz2 > k {
			node = r.Left()

			merge(r, node.Key, -1)
			sz2--

			merge(l, node.Key, 1)
			sz1++
		}

		x := r.Left().Key

		p = qpow(p, x)

		ans = append(ans, p)
	}

	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Sorted List

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
            x = sl[-k]
            p = pow(p, x, mod)
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
