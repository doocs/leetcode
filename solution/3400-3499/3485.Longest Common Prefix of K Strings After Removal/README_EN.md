---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3485.Longest%20Common%20Prefix%20of%20K%20Strings%20After%20Removal/README_EN.md
tags:
    - Trie
    - Array
    - String
---

<!-- problem:start -->

# [3485. Longest Common Prefix of K Strings After Removal](https://leetcode.com/problems/longest-common-prefix-of-k-strings-after-removal)

[中文文档](/solution/3400-3499/3485.Longest%20Common%20Prefix%20of%20K%20Strings%20After%20Removal/README.md)

## Description

<!-- description:start -->

<p>You are given an array of strings <code>words</code> and an integer <code>k</code>.</p>

<p>For each index <code>i</code> in the range <code>[0, words.length - 1]</code>, find the <strong>length</strong> of the <strong>longest common <span data-keyword="string-prefix">prefix</span></strong> among any <code>k</code> strings (selected at <strong>distinct indices</strong>) from the remaining array after removing the <code>i<sup>th</sup></code> element.</p>

<p>Return an array <code>answer</code>, where <code>answer[i]</code> is the answer for <code>i<sup>th</sup></code> element. If removing the <code>i<sup>th</sup></code> element leaves the array with fewer than <code>k</code> strings, <code>answer[i]</code> is 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;jump&quot;,&quot;run&quot;,&quot;run&quot;,&quot;jump&quot;,&quot;run&quot;], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,4,4,3,4]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Removing index 0 (<code>&quot;jump&quot;</code>):

    <ul>
    	<li><code>words</code> becomes: <code>[&quot;run&quot;, &quot;run&quot;, &quot;jump&quot;, &quot;run&quot;]</code>. <code>&quot;run&quot;</code> occurs 3 times. Choosing any two gives the longest common prefix <code>&quot;run&quot;</code> (length 3).</li>
    </ul>
    </li>
    <li>Removing index 1 (<code>&quot;run&quot;</code>):
    <ul>
    	<li><code>words</code> becomes: <code>[&quot;jump&quot;, &quot;run&quot;, &quot;jump&quot;, &quot;run&quot;]</code>. <code>&quot;jump&quot;</code> occurs twice. Choosing these two gives the longest common prefix <code>&quot;jump&quot;</code> (length 4).</li>
    </ul>
    </li>
    <li>Removing index 2 (<code>&quot;run&quot;</code>):
    <ul>
    	<li><code>words</code> becomes: <code>[&quot;jump&quot;, &quot;run&quot;, &quot;jump&quot;, &quot;run&quot;]</code>. <code>&quot;jump&quot;</code> occurs twice. Choosing these two gives the longest common prefix <code>&quot;jump&quot;</code> (length 4).</li>
    </ul>
    </li>
    <li>Removing index 3 (<code>&quot;jump&quot;</code>):
    <ul>
    	<li><code>words</code> becomes: <code>[&quot;jump&quot;, &quot;run&quot;, &quot;run&quot;, &quot;run&quot;]</code>. <code>&quot;run&quot;</code> occurs 3 times. Choosing any two gives the longest common prefix <code>&quot;run&quot;</code> (length 3).</li>
    </ul>
    </li>
    <li>Removing index 4 (&quot;run&quot;):
    <ul>
    	<li><code>words</code> becomes: <code>[&quot;jump&quot;, &quot;run&quot;, &quot;run&quot;, &quot;jump&quot;]</code>. <code>&quot;jump&quot;</code> occurs twice. Choosing these two gives the longest common prefix <code>&quot;jump&quot;</code> (length 4).</li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;dog&quot;,&quot;racer&quot;,&quot;car&quot;], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,0,0]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Removing any index results in an answer of 0.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= words.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10<sup>4</sup></code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
	<li>The sum of <code>words[i].length</code> is smaller than or equal <code>10<sup>5</sup></code>.</li>
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
    static class TrieNode {
        int count = 0;
        int depth = 0;
        int[] children = new int[26];

        TrieNode() {
            for (int i = 0; i < 26; ++i) children[i] = -1;
        }
    }

    static class SegmentTree {
        int n;
        int[] tree;
        int[] globalCount;

        SegmentTree(int n, int[] globalCount) {
            this.n = n;
            this.globalCount = globalCount;
            this.tree = new int[4 * (n + 1)];
            for (int i = 0; i < tree.length; i++) tree[i] = -1;
            build(1, 1, n);
        }

        void build(int idx, int l, int r) {
            if (l == r) {
                tree[idx] = globalCount[l] > 0 ? l : -1;
                return;
            }
            int mid = (l + r) / 2;
            build(idx * 2, l, mid);
            build(idx * 2 + 1, mid + 1, r);
            tree[idx] = Math.max(tree[idx * 2], tree[idx * 2 + 1]);
        }

        void update(int idx, int l, int r, int pos, int newVal) {
            if (l == r) {
                tree[idx] = newVal > 0 ? l : -1;
                return;
            }
            int mid = (l + r) / 2;
            if (pos <= mid) {
                update(idx * 2, l, mid, pos, newVal);
            } else {
                update(idx * 2 + 1, mid + 1, r, pos, newVal);
            }
            tree[idx] = Math.max(tree[idx * 2], tree[idx * 2 + 1]);
        }

        int query() {
            return tree[1];
        }
    }

    public int[] longestCommonPrefix(String[] words, int k) {
        int n = words.length;
        int[] ans = new int[n];
        if (n - 1 < k) return ans;

        ArrayList<TrieNode> trie = new ArrayList<>();
        trie.add(new TrieNode());

        for (String word : words) {
            int cur = 0;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (trie.get(cur).children[idx] == -1) {
                    trie.get(cur).children[idx] = trie.size();
                    TrieNode node = new TrieNode();
                    node.depth = trie.get(cur).depth + 1;
                    trie.add(node);
                }
                cur = trie.get(cur).children[idx];
                trie.get(cur).count++;
            }
        }

        int maxDepth = 0;
        for (int i = 1; i < trie.size(); ++i) {
            if (trie.get(i).count >= k) {
                maxDepth = Math.max(maxDepth, trie.get(i).depth);
            }
        }

        int[] globalCount = new int[maxDepth + 1];
        for (int i = 1; i < trie.size(); ++i) {
            TrieNode node = trie.get(i);
            if (node.count >= k && node.depth <= maxDepth) {
                globalCount[node.depth]++;
            }
        }

        List<List<Integer>> fragileList = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            fragileList.add(new ArrayList<>());
        }

        for (int i = 0; i < n; ++i) {
            int cur = 0;
            for (char c : words[i].toCharArray()) {
                int idx = c - 'a';
                cur = trie.get(cur).children[idx];
                if (trie.get(cur).count == k) {
                    fragileList.get(i).add(trie.get(cur).depth);
                }
            }
        }

        int segSize = maxDepth;
        if (segSize >= 1) {
            SegmentTree segTree = new SegmentTree(segSize, globalCount);
            for (int i = 0; i < n; ++i) {
                if (n - 1 < k) {
                    ans[i] = 0;
                } else {
                    for (int d : fragileList.get(i)) {
                        segTree.update(1, 1, segSize, d, globalCount[d] - 1);
                    }
                    int res = segTree.query();
                    ans[i] = res == -1 ? 0 : res;
                    for (int d : fragileList.get(i)) {
                        segTree.update(1, 1, segSize, d, globalCount[d]);
                    }
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
    struct TrieNode {
        int count = 0;
        int depth = 0;
        int children[26] = {0};
    };

    class SegmentTree {
    public:
        int n;
        vector<int> tree;
        vector<int>& globalCount;
        SegmentTree(int n, vector<int>& globalCount)
            : n(n)
            , globalCount(globalCount) {
            tree.assign(4 * (n + 1), -1);
            build(1, 1, n);
        }
        void build(int idx, int l, int r) {
            if (l == r) {
                tree[idx] = globalCount[l] > 0 ? l : -1;
                return;
            }
            int mid = (l + r) / 2;
            build(idx * 2, l, mid);
            build(idx * 2 + 1, mid + 1, r);
            tree[idx] = max(tree[idx * 2], tree[idx * 2 + 1]);
        }
        void update(int idx, int l, int r, int pos, int newVal) {
            if (l == r) {
                tree[idx] = newVal > 0 ? l : -1;
                return;
            }
            int mid = (l + r) / 2;
            if (pos <= mid)
                update(idx * 2, l, mid, pos, newVal);
            else
                update(idx * 2 + 1, mid + 1, r, pos, newVal);
            tree[idx] = max(tree[idx * 2], tree[idx * 2 + 1]);
        }
        int query() {
            return tree[1];
        }
    };

    vector<int> longestCommonPrefix(vector<string>& words, int k) {
        int n = words.size();
        vector<int> ans(n, 0);
        if (n - 1 < k) return ans;
        vector<TrieNode> trie(1);
        for (const string& word : words) {
            int cur = 0;
            for (char c : word) {
                int idx = c - 'a';
                if (trie[cur].children[idx] == 0) {
                    trie[cur].children[idx] = trie.size();
                    trie.push_back({0, trie[cur].depth + 1});
                }
                cur = trie[cur].children[idx];
                trie[cur].count++;
            }
        }
        int maxDepth = 0;
        for (int i = 1; i < trie.size(); ++i) {
            if (trie[i].count >= k) {
                maxDepth = max(maxDepth, trie[i].depth);
            }
        }
        vector<int> globalCount(maxDepth + 1, 0);
        for (int i = 1; i < trie.size(); ++i) {
            if (trie[i].count >= k && trie[i].depth <= maxDepth) {
                globalCount[trie[i].depth]++;
            }
        }
        vector<vector<int>> fragileList(n);
        for (int i = 0; i < n; ++i) {
            int cur = 0;
            for (char c : words[i]) {
                int idx = c - 'a';
                cur = trie[cur].children[idx];
                if (trie[cur].count == k) {
                    fragileList[i].push_back(trie[cur].depth);
                }
            }
        }
        int segSize = maxDepth;
        if (segSize >= 1) {
            SegmentTree segTree(segSize, globalCount);
            for (int i = 0; i < n; ++i) {
                if (n - 1 < k) {
                    ans[i] = 0;
                } else {
                    for (int d : fragileList[i]) {
                        segTree.update(1, 1, segSize, d, globalCount[d] - 1);
                    }
                    int res = segTree.query();
                    ans[i] = res == -1 ? 0 : res;
                    for (int d : fragileList[i]) {
                        segTree.update(1, 1, segSize, d, globalCount[d]);
                    }
                }
            }
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
