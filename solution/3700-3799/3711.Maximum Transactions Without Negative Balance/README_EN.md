---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3711.Maximum%20Transactions%20Without%20Negative%20Balance/README_EN.md
tags:
    - Greedy
    - Array
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3711. Maximum Transactions Without Negative Balance 🔒](https://leetcode.com/problems/maximum-transactions-without-negative-balance)

[中文文档](/solution/3700-3799/3711.Maximum%20Transactions%20Without%20Negative%20Balance/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>transactions</code>, where <code>transactions[i]</code> represents the amount of the <code>i<sup>th</sup></code> transaction:</p>

<ul>
	<li>A positive value means money is <strong>received</strong>.</li>
	<li>A negative value means money is <strong>sent</strong>.</li>
</ul>

<p>The account starts with a balance of 0, and the balance <strong>must never become negative</strong>. Transactions must be considered in the given order, but you are allowed to skip some transactions.</p>

<p>Return an integer denoting the <strong>maximum number of transactions</strong> that can be performed without the balance ever going negative.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">transactions = [2,-5,3,-1,-2]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal sequence is <code>[2, 3, -1, -2]</code>, balance: <code>0 &rarr; 2 &rarr; 5 &rarr; 4 &rarr; 2</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">transactions = [-1,-2,-3]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>All transactions are negative. Including any would make the balance negative.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">transactions = [3,-2,3,-2,1,-1]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>All transactions can be taken in order, balance: <code>0 &rarr; 3 &rarr; 1 &rarr; 4 &rarr; 2 &rarr; 3 &rarr; 2</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= transactions.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= transactions[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Ordered Set

We use an ordered set (such as C++'s multiset, Java's TreeMap, Python's SortedList) to store the selected transaction amounts, and maintain a variable $s$ to record the current balance. Initially $s=0$, and the answer $\textit{ans}$ is initialized to the number of transactions.

Then we traverse each transaction amount $x$:

1. Add $x$ to the balance $s$ and add $x$ to the ordered set.
2. If the balance $s$ becomes negative at this point, it means some negative amounts among the currently selected transaction amounts have caused insufficient balance. To retain as many transactions as possible, we should remove the smallest amount among the currently selected transaction amounts (because removing the smallest amount can maximize the balance). We remove the smallest amount $y$ from the ordered set, subtract $y$ from the balance $s$, and decrement the answer $\textit{ans}$ by $1$.
3. Repeat step 2 until the balance $s$ is no longer negative.

After traversal is complete, the answer $\textit{ans}$ is the maximum number of transactions that can be performed.

The time complexity is $O(n \times \log n)$, where $n$ is the number of transactions. The space complexity is $O(n)$.

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
