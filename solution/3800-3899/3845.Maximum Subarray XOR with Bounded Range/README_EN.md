---
comments: true
difficulty: Hard
rating: 2347
source: Weekly Contest 489 Q4
tags:
    - Bit Manipulation
    - Trie
    - Queue
    - Array
    - Prefix Sum
    - Sliding Window
    - Monotonic Queue
---

<!-- problem:start -->

# [3845. Maximum Subarray XOR with Bounded Range](https://leetcode.com/problems/maximum-subarray-xor-with-bounded-range)

[中文文档](/solution/3800-3899/3845.Maximum%20Subarray%20XOR%20with%20Bounded%20Range/README.md)

## Description

<!-- description:start -->

<p>You are given a non-negative integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>You must select a <strong><span data-keyword="subarray-nonempty">subarray</span></strong> of <code>nums</code> such that the <strong>difference</strong> between its <strong>maximum</strong> and <strong>minimum</strong> elements is at most <code>k</code>. The <strong>value</strong> of this subarray is the bitwise XOR of all elements in the subarray.</p>

<p>Return an integer denoting the <strong>maximum</strong> possible <strong>value</strong> of the selected subarray.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,4,5,6], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Select the subarray <code>[5, <u><strong>4, 5, 6</strong></u>]</code>.</li>
	<li>The difference between its maximum and minimum elements is <code>6 - 4 = 2 &lt;= k</code>.</li>
	<li>The value is <code>4 XOR 5 XOR 6 = 7</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,4,5,6], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Select the subarray <code>[5, 4, 5, <u><strong>6</strong></u>]</code>.</li>
	<li>The difference between its maximum and minimum elements is <code>6 - 6 = 0 &lt;= k</code>.</li>
	<li>The value is 6.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt; 2<sup>15</sup></code></li>
	<li><code>0 &lt;= k &lt; 2<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> A subarray must satisfy $\max-\min \le k$ while maximizing its XOR. $n \le 4 \times 10^4$ and values lie below $2^{15}$.
>
> A subarray XOR is the XOR of two prefix XORs. For a fixed right end the legal left ends form a $\max-\min$ window, inside which we query the best prefix XOR.
>
> Monotonic deques shrink the window; a binary trie inserts and deletes prefix XORs and greedily takes the opposite bit.
>
> As the right end advances, the window and the trie slide together; each prefix enters and leaves once.

<!-- thinking:end -->
<!-- tabs:start -->

#### Python3

```python
class TrieNode:
    __slots__ = ("children", "count")

    def __init__(self):
        self.children = [None, None]
        self.count = 0


class Solution:
    def maxXor(self, nums: List[int], k: int) -> int:
        root = TrieNode()

        def update(value: int, delta: int) -> None:
            cur = root
            for bit in range(14, -1, -1):
                b = (value >> bit) & 1
                if cur.children[b] is None:
                    cur.children[b] = TrieNode()
                cur = cur.children[b]
                cur.count += delta

        def get_max_xor(value: int) -> int:
            cur = root
            ans = 0
            for bit in range(14, -1, -1):
                b = (value >> bit) & 1
                opp = 1 - b
                if cur.children[opp] is not None and cur.children[opp].count > 0:
                    ans |= 1 << bit
                    cur = cur.children[opp]
                else:
                    cur = cur.children[b]
            return ans

        n = len(nums)
        prefix = [0] * (n + 1)
        for i, x in enumerate(nums):
            prefix[i + 1] = prefix[i] ^ x

        maxq, minq = deque(), deque()
        left = 0
        ans = 0
        update(prefix[0], 1)
        for right, x in enumerate(nums):
            while maxq and nums[maxq[-1]] <= x:
                maxq.pop()
            while minq and nums[minq[-1]] >= x:
                minq.pop()
            maxq.append(right)
            minq.append(right)
            while nums[maxq[0]] - nums[minq[0]] > k:
                if maxq[0] == left:
                    maxq.popleft()
                if minq[0] == left:
                    minq.popleft()
                update(prefix[left], -1)
                left += 1
            ans = max(ans, get_max_xor(prefix[right + 1]))
            update(prefix[right + 1], 1)
        return ans
```

#### Java

```java
class Solution {

    // Trie node for storing prefix XOR values in binary form
    class TrieNode {
        TrieNode[] children = new TrieNode[2]; // 0 and 1 branches
        int count = 0; // number of prefix values passing through this node
    }

    TrieNode root = new TrieNode();

    // Insert or remove a prefix XOR value from the trie
    void updateTrie(int value, int delta) {
        TrieNode current = root;
        for (int bit = 14; bit >= 0; bit--) {
            int currentBit = (value >> bit) & 1;
            if (current.children[currentBit] == null) {
                current.children[currentBit] = new TrieNode();
            }
            current = current.children[currentBit];
            current.count += delta;
        }
    }

    // Find maximum XOR of given value with any value currently in the trie
    int getMaxXor(int value) {
        TrieNode current = root;
        int maxXor = 0;

        for (int bit = 14; bit >= 0; bit--) {
            int currentBit = (value >> bit) & 1;
            int oppositeBit = 1 - currentBit;

            if (current.children[oppositeBit] != null && current.children[oppositeBit].count > 0) {
                maxXor |= (1 << bit);
                current = current.children[oppositeBit];
            } else {
                current = current.children[currentBit];
            }
        }

        return maxXor;
    }

    public int maxXor(int[] nums, int limit) {
        int length = nums.length;

        // Prefix XOR array
        int[] prefixXor = new int[length + 1];
        for (int i = 0; i < length; i++) {
            prefixXor[i + 1] = prefixXor[i] ^ nums[i];
        }

        // Monotonic queues to maintain max and min in sliding window
        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();

        int left = 0;
        int result = 0;

        updateTrie(prefixXor[0], 1);

        for (int right = 0; right < length; right++) {

            // Maintain decreasing deque for maximum
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[right]) {
                maxDeque.pollLast();
            }

            // Maintain increasing deque for minimum
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[right]) {
                minDeque.pollLast();
            }

            maxDeque.addLast(right);
            minDeque.addLast(right);

            // Shrink window if max - min exceeds limit
            while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > limit) {

                if (maxDeque.peekFirst() == left) {
                    maxDeque.pollFirst();
                }

                if (minDeque.peekFirst() == left) {
                    minDeque.pollFirst();
                }

                updateTrie(prefixXor[left], -1);
                left++;
            }

            result = Math.max(result, getMaxXor(prefixXor[right + 1]));
            updateTrie(prefixXor[right + 1], 1);
        }

        return result;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxXor(vector<int>& nums, int k) {
        root = new TrieNode();
        int n = nums.size();
        vector<int> prefix(n + 1);
        for (int i = 0; i < n; ++i) {
            prefix[i + 1] = prefix[i] ^ nums[i];
        }
        deque<int> maxq, minq;
        int left = 0, ans = 0;
        update(prefix[0], 1);
        for (int right = 0; right < n; ++right) {
            while (!maxq.empty() && nums[maxq.back()] <= nums[right]) {
                maxq.pop_back();
            }
            while (!minq.empty() && nums[minq.back()] >= nums[right]) {
                minq.pop_back();
            }
            maxq.push_back(right);
            minq.push_back(right);
            while (nums[maxq.front()] - nums[minq.front()] > k) {
                if (maxq.front() == left) {
                    maxq.pop_front();
                }
                if (minq.front() == left) {
                    minq.pop_front();
                }
                update(prefix[left], -1);
                ++left;
            }
            ans = max(ans, getMaxXor(prefix[right + 1]));
            update(prefix[right + 1], 1);
        }
        return ans;
    }

private:
    struct TrieNode {
        TrieNode* children[2]{};
        int count = 0;
    };

    TrieNode* root;

    void update(int value, int delta) {
        TrieNode* cur = root;
        for (int bit = 14; bit >= 0; --bit) {
            int b = (value >> bit) & 1;
            if (!cur->children[b]) {
                cur->children[b] = new TrieNode();
            }
            cur = cur->children[b];
            cur->count += delta;
        }
    }

    int getMaxXor(int value) {
        TrieNode* cur = root;
        int ans = 0;
        for (int bit = 14; bit >= 0; --bit) {
            int b = (value >> bit) & 1;
            int opp = 1 - b;
            if (cur->children[opp] && cur->children[opp]->count > 0) {
                ans |= 1 << bit;
                cur = cur->children[opp];
            } else {
                cur = cur->children[b];
            }
        }
        return ans;
    }
};
```

#### Go

```go
type xorTrieNode struct {
	children [2]*xorTrieNode
	count    int
}

func updateTrie(root *xorTrieNode, value, delta int) {
	cur := root
	for bit := 14; bit >= 0; bit-- {
		b := (value >> bit) & 1
		if cur.children[b] == nil {
			cur.children[b] = &xorTrieNode{}
		}
		cur = cur.children[b]
		cur.count += delta
	}
}

func getMaxXor(root *xorTrieNode, value int) int {
	cur := root
	ans := 0
	for bit := 14; bit >= 0; bit-- {
		b := (value >> bit) & 1
		opp := 1 - b
		if cur.children[opp] != nil && cur.children[opp].count > 0 {
			ans |= 1 << bit
			cur = cur.children[opp]
		} else {
			cur = cur.children[b]
		}
	}
	return ans
}

func maxXor(nums []int, k int) int {
	n := len(nums)
	prefix := make([]int, n+1)
	for i, x := range nums {
		prefix[i+1] = prefix[i] ^ x
	}
	root := &xorTrieNode{}
	maxq, minq := []int{}, []int{}
	left, ans := 0, 0
	updateTrie(root, prefix[0], 1)
	for right, x := range nums {
		for len(maxq) > 0 && nums[maxq[len(maxq)-1]] <= x {
			maxq = maxq[:len(maxq)-1]
		}
		for len(minq) > 0 && nums[minq[len(minq)-1]] >= x {
			minq = minq[:len(minq)-1]
		}
		maxq = append(maxq, right)
		minq = append(minq, right)
		for nums[maxq[0]]-nums[minq[0]] > k {
			if maxq[0] == left {
				maxq = maxq[1:]
			}
			if minq[0] == left {
				minq = minq[1:]
			}
			updateTrie(root, prefix[left], -1)
			left++
		}
		ans = max(ans, getMaxXor(root, prefix[right+1]))
		updateTrie(root, prefix[right+1], 1)
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
