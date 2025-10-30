---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3711.Maximum%20Transactions%20Without%20Negative%20Balance/README.md
tags:
    - 贪心
    - 数组
    - 堆（优先队列）
---

<!-- problem:start -->

# [3711. 不出现负余额的最大交易额 🔒](https://leetcode.cn/problems/maximum-transactions-without-negative-balance)

[English Version](/solution/3700-3799/3711.Maximum%20Transactions%20Without%20Negative%20Balance/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code>transactions</code>，其中&nbsp;<code>transactions[i]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;笔交易的总额：</p>

<ul>
	<li>正值表示 <strong>收到</strong> 的钱。</li>
	<li>负值表示 <strong>支付</strong>&nbsp;的钱。</li>
</ul>

<p>账户初始余额为 0，且余额 <strong>必须始终保持非负</strong>。交易必须按照给定的顺序进行处理，但你可以跳过一些交易。</p>

<p>返回一个整数，表示在不使余额变为负数的情况下可以执行的 <strong>最大交易次数</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>transactions = [2,-5,3,-1,-2]</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p>一个最优序列是 <code>[2, 3, -1, -2]</code>，余额：<code>0 → 2 → 5 → 4 → 2</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>transactions = [-1,-2,-3]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<p>所有交易均为负数。进行任何一项交易都会使余额变为负数。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>transactions = [3,-2,3,-2,1,-1]</span></p>

<p><span class="example-io"><b>输出：</b>6</span></p>

<p><strong>解释：</strong></p>

<p>所有交易都可以按顺序进行，余额：<code>0 → 3 → 1 → 4 → 2 → 3 → 2</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= transactions.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= transactions[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 有序集合

我们使用一个有序集合（如 C++ 的 multiset，Java 的 TreeMap，Python 的 SortedList）来存储已经选择的交易金额，并维护一个变量 $s$ 来记录当前的余额。初始时 $s=0$，答案 $\textit{ans}$ 初始化为交易次数。

然后我们遍历每笔交易金额 $x$：

1. 将 $x$ 加到余额 $s$ 中，并将 $x$ 添加到有序集合中。
2. 如果此时余额 $s$ 变为负数，说明当前选择的交易金额中有一些负数金额导致余额不足。为了尽可能保留更多的交易，我们应该移除当前选择的交易金额中最小的那个金额（因为移除最小的金额可以最大化余额）。我们从有序集合中移除最小的金额 $y$，并将 $y$ 从余额 $s$ 中减去，同时将答案 $\textit{ans}$ 减 $1$。
3. 重复步骤 2，直到余额 $s$ 不再为负数。

遍历完成后，答案 $\textit{ans}$ 即为最多可以进行的交易次数。

时间复杂度 $O(n \times \log n)$，其中 $n$ 是交易次数。空间复杂度 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxTransactions(self, transactions: List[int]) -> int:
        st = SortedList()
        s = 0
        ans = len(transactions)
        for x in transactions:
            s += x
            st.add(x)
            while s < 0:
                y = st.pop(0)
                s -= y
                ans -= 1
        return ans
```

#### Java

```java
class Solution {
    public int maxTransactions(int[] transactions) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int ans = transactions.length;
        long s = 0;
        for (int x : transactions) {
            s += x;
            tm.merge(x, 1, Integer::sum);
            while (s < 0) {
                int y = tm.firstKey();
                s -= y;
                --ans;
                if (tm.merge(y, -1, Integer::sum) == 0) {
                    tm.remove(y);
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
    int maxTransactions(vector<int>& transactions) {
        multiset<int> st;
        int ans = transactions.size();
        long long s = 0;
        for (int x : transactions) {
            s += x;
            st.insert(x);
            while (s < 0) {
                auto it = st.begin();
                int y = *it;
                st.erase(it);
                s -= y;
                --ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxTransactions(transactions []int) int {
	tm := redblacktree.New[int, int]()
	ans := len(transactions)
	var s int64

	for _, x := range transactions {
		s += int64(x)
		if cnt, ok := tm.Get(x); ok {
			tm.Put(x, cnt+1)
		} else {
			tm.Put(x, 1)
		}

		for s < 0 {
			it := tm.Iterator()
			it.Begin()
			it.Next()
			y := it.Key()
			s -= int64(y)
			ans--

			cnt, _ := tm.Get(y)
			if cnt == 1 {
				tm.Remove(y)
			} else {
				tm.Put(y, cnt-1)
			}
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
