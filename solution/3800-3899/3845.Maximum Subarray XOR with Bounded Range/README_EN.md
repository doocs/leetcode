---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3845.Maximum%20Subarray%20XOR%20with%20Bounded%20Range/README_EN.md
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

<!-- tabs:start -->

#### Python3

```python

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

            if (current.children[oppositeBit] != null &&
                current.children[oppositeBit].count > 0) {
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
            while (!maxDeque.isEmpty() &&
                   nums[maxDeque.peekLast()] <= nums[right]) {
                maxDeque.pollLast();
            }

            // Maintain increasing deque for minimum
            while (!minDeque.isEmpty() &&
                   nums[minDeque.peekLast()] >= nums[right]) {
                minDeque.pollLast();
            }

            maxDeque.addLast(right);
            minDeque.addLast(right);

            // Shrink window if max - min exceeds limit
            while (nums[maxDeque.peekFirst()] -
                   nums[minDeque.peekFirst()] > limit) {

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

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
