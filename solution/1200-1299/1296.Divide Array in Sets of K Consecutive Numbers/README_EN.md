---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1296.Divide%20Array%20in%20Sets%20of%20K%20Consecutive%20Numbers/README_EN.md
rating: 1490
source: Weekly Contest 168 Q2
tags:
    - Greedy
    - Array
    - Hash Table
    - Sorting
---

<!-- problem:start -->

# [1296. Divide Array in Sets of K Consecutive Numbers](https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers)

[中文文档](/solution/1200-1299/1296.Divide%20Array%20in%20Sets%20of%20K%20Consecutive%20Numbers/README.md)

## Description

<!-- description:start -->

<p>Given an array of integers <code>nums</code> and a positive integer <code>k</code>, check whether it is possible to divide this array into sets of <code>k</code> consecutive numbers.</p>

<p>Return <code>true</code> <em>if it is possible</em>.<strong> </strong>Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,3,4,4,5,6], k = 4
<strong>Output:</strong> true
<strong>Explanation:</strong> Array can be divided into [1,2,3,4] and [3,4,5,6].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
<strong>Output:</strong> true
<strong>Explanation:</strong> Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4], k = 3
<strong>Output:</strong> false
<strong>Explanation:</strong> Each array should be divided in subarrays of size 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Note:</strong> This question is the same as&nbsp;846:&nbsp;<a href="https://leetcode.com/problems/hand-of-straights/" target="_blank">https://leetcode.com/problems/hand-of-straights/</a>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Sorting

First, we check if the length of the array $\textit{nums}$ is divisible by $\textit{k}$. If it is not divisible, it means the array cannot be divided into subarrays of length $\textit{k}$, and we return $\text{false}$ directly.

Next, we use a hash table $\textit{cnt}$ to count the occurrences of each number in the array $\textit{nums}$, and then we sort the array $\textit{nums}$.

After sorting, we iterate through the array $\textit{nums}$, and for each number $x$, if $\textit{cnt}[x]$ is not $0$, we enumerate each number $y$ from $x$ to $x + \textit{k} - 1$. If $\textit{cnt}[y]$ is $0$, it means we cannot divide the array into subarrays of length $\textit{k}$, and we return $\text{false}$ directly. Otherwise, we decrement $\textit{cnt}[y]$ by $1$.

After the loop, if no issues were encountered, it means we can divide the array into subarrays of length $\textit{k}$, and we return $\text{true}$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPossibleDivide(self, nums: List[int], k: int) -> bool:
        if len(nums) % k:
            return False
        cnt = Counter(nums)
        for x in sorted(nums):
            if cnt[x]:
                for y in range(x, x + k):
                    if cnt[y] == 0:
                        return False
                    cnt[y] -= 1
        return True
```

#### Java

```java
class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        for (int x : nums) {
            if (cnt.getOrDefault(x, 0) > 0) {
                for (int y = x; y < x + k; ++y) {
                    if (cnt.merge(y, -1, Integer::sum) < 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPossibleDivide(vector<int>& nums, int k) {
        if (nums.size() % k) {
            return false;
        }
        ranges::sort(nums);
        unordered_map<int, int> cnt;
        for (int x : nums) {
            ++cnt[x];
        }
        for (int x : nums) {
            if (cnt.contains(x)) {
                for (int y = x; y < x + k; ++y) {
                    if (!cnt.contains(y)) {
                        return false;
                    }
                    if (--cnt[y] == 0) {
                        cnt.erase(y);
                    }
                }
            }
        }
        return true;
    }
};
```

#### Go

```go
func isPossibleDivide(nums []int, k int) bool {
	if len(nums)%k != 0 {
		return false
	}
	sort.Ints(nums)
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	for _, x := range nums {
		if cnt[x] > 0 {
			for y := x; y < x+k; y++ {
				if cnt[y] == 0 {
					return false
				}
				cnt[y]--
			}
		}
	}
	return true
}
```

#### TypeScript

```ts
function isPossibleDivide(nums: number[], k: number): boolean {
    if (nums.length % k !== 0) {
        return false;
    }
    const cnt = new Map<number, number>();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    nums.sort((a, b) => a - b);
    for (const x of nums) {
        if (cnt.get(x)! > 0) {
            for (let y = x; y < x + k; y++) {
                if ((cnt.get(y) || 0) === 0) {
                    return false;
                }
                cnt.set(y, cnt.get(y)! - 1);
            }
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Ordered Set

Similar to Solution 1, we first check if the length of the array $\textit{nums}$ is divisible by $\textit{k}$. If it is not divisible, it means the array cannot be divided into subarrays of length $\textit{k}$, and we return $\text{false}$ directly.

Next, we use an ordered set $\textit{sd}$ to count the occurrences of each number in the array $\textit{nums}$.

Then, we repeatedly extract the smallest value $x$ from the ordered set and enumerate each number $y$ from $x$ to $x + \textit{k} - 1$. If these numbers all appear in the ordered set with non-zero occurrences, we decrement their occurrence count by $1$. If the occurrence count becomes $0$ after the decrement, we remove the number from the ordered set; otherwise, it means we cannot divide the array into subarrays of length $\textit{k}$, and we return $\text{false}$.

If we can successfully divide the array into subarrays of length $\textit{k}$, we return $\text{true}$ after completing the traversal.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPossibleDivide(self, nums: List[int], k: int) -> bool:
        if len(nums) % k:
            return False
        cnt = Counter(nums)
        sd = SortedDict(cnt)
        while sd:
            x = next(iter(sd))
            for y in range(x, x + k):
                if y not in sd:
                    return False
                if sd[y] == 1:
                    del sd[y]
                else:
                    sd[y] -= 1
        return True
```

#### Java

```java
class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) {
            return false;
        }
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int x : nums) {
            tm.merge(x, 1, Integer::sum);
        }
        while (!tm.isEmpty()) {
            int x = tm.firstKey();
            for (int y = x; y < x + k; ++y) {
                int t = tm.merge(y, -1, Integer::sum);
                if (t < 0) {
                    return false;
                }
                if (t == 0) {
                    tm.remove(y);
                }
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPossibleDivide(vector<int>& nums, int k) {
        if (nums.size() % k) {
            return false;
        }
        map<int, int> mp;
        for (int x : nums) {
            ++mp[x];
        }
        while (!mp.empty()) {
            int x = mp.begin()->first;
            for (int y = x; y < x + k; ++y) {
                if (!mp.contains(y)) {
                    return false;
                }
                if (--mp[y] == 0) {
                    mp.erase(y);
                }
            }
        }
        return true;
    }
};
```

#### Go

```go
func isPossibleDivide(nums []int, k int) bool {
	if len(nums)%k != 0 {
		return false
	}
	tm := treemap.NewWithIntComparator()
	for _, x := range nums {
		if v, ok := tm.Get(x); ok {
			tm.Put(x, v.(int)+1)
		} else {
			tm.Put(x, 1)
		}
	}
	for !tm.Empty() {
		x, _ := tm.Min()
		for y := x.(int); y < x.(int)+k; y++ {
			if v, ok := tm.Get(y); ok {
				if v.(int) == 1 {
					tm.Remove(y)
				} else {
					tm.Put(y, v.(int)-1)
				}
			} else {
				return false
			}
		}
	}
	return true
}
```

#### TypeScript

```ts
function isPossibleDivide(nums: number[], k: number): boolean {
    if (nums.length % k !== 0) {
        return false;
    }
    const tm = new TreeMap<number, number>();
    for (const x of nums) {
        tm.set(x, (tm.get(x) || 0) + 1);
    }
    while (tm.size()) {
        const x = tm.first()![0];
        for (let y = x; y < x + k; ++y) {
            if (!tm.has(y)) {
                return false;
            }
            if (tm.get(y)! === 1) {
                tm.delete(y);
            } else {
                tm.set(y, tm.get(y)! - 1);
            }
        }
    }
    return true;
}

type Compare<T> = (lhs: T, rhs: T) => number;

class RBTreeNode<T = number> {
    data: T;
    count: number;
    left: RBTreeNode<T> | null;
    right: RBTreeNode<T> | null;
    parent: RBTreeNode<T> | null;
    color: number;
    constructor(data: T) {
        this.data = data;
        this.left = this.right = this.parent = null;
        this.color = 0;
        this.count = 1;
    }

    sibling(): RBTreeNode<T> | null {
        if (!this.parent) return null; // sibling null if no parent
        return this.isOnLeft() ? this.parent.right : this.parent.left;
    }

    isOnLeft(): boolean {
        return this === this.parent!.left;
    }

    hasRedChild(): boolean {
        return (
            Boolean(this.left && this.left.color === 0) ||
            Boolean(this.right && this.right.color === 0)
        );
    }
}

class RBTree<T> {
    root: RBTreeNode<T> | null;
    lt: (l: T, r: T) => boolean;
    constructor(compare: Compare<T> = (l: T, r: T) => (l < r ? -1 : l > r ? 1 : 0)) {
        this.root = null;
        this.lt = (l: T, r: T) => compare(l, r) < 0;
    }

    rotateLeft(pt: RBTreeNode<T>): void {
        const right = pt.right!;
        pt.right = right.left;

        if (pt.right) pt.right.parent = pt;
        right.parent = pt.parent;

        if (!pt.parent) this.root = right;
        else if (pt === pt.parent.left) pt.parent.left = right;
        else pt.parent.right = right;

        right.left = pt;
        pt.parent = right;
    }

    rotateRight(pt: RBTreeNode<T>): void {
        const left = pt.left!;
        pt.left = left.right;

        if (pt.left) pt.left.parent = pt;
        left.parent = pt.parent;

        if (!pt.parent) this.root = left;
        else if (pt === pt.parent.left) pt.parent.left = left;
        else pt.parent.right = left;

        left.right = pt;
        pt.parent = left;
    }

    swapColor(p1: RBTreeNode<T>, p2: RBTreeNode<T>): void {
        const tmp = p1.color;
        p1.color = p2.color;
        p2.color = tmp;
    }

    swapData(p1: RBTreeNode<T>, p2: RBTreeNode<T>): void {
        const tmp = p1.data;
        p1.data = p2.data;
        p2.data = tmp;
    }

    fixAfterInsert(pt: RBTreeNode<T>): void {
        let parent = null;
        let grandParent = null;

        while (pt !== this.root && pt.color !== 1 && pt.parent?.color === 0) {
            parent = pt.parent;
            grandParent = pt.parent.parent;

            /*  Case : A
                Parent of pt is left child of Grand-parent of pt */
            if (parent === grandParent?.left) {
                const uncle = grandParent.right;

                /* Case : 1
                   The uncle of pt is also red
                   Only Recoloring required */
                if (uncle && uncle.color === 0) {
                    grandParent.color = 0;
                    parent.color = 1;
                    uncle.color = 1;
                    pt = grandParent;
                } else {
                    /* Case : 2
                       pt is right child of its parent
                       Left-rotation required */
                    if (pt === parent.right) {
                        this.rotateLeft(parent);
                        pt = parent;
                        parent = pt.parent;
                    }

                    /* Case : 3
                       pt is left child of its parent
                       Right-rotation required */
                    this.rotateRight(grandParent);
                    this.swapColor(parent!, grandParent);
                    pt = parent!;
                }
            } else {
                /* Case : B
               Parent of pt is right child of Grand-parent of pt */
                const uncle = grandParent!.left;

                /*  Case : 1
                    The uncle of pt is also red
                    Only Recoloring required */
                if (uncle != null && uncle.color === 0) {
                    grandParent!.color = 0;
                    parent.color = 1;
                    uncle.color = 1;
                    pt = grandParent!;
                } else {
                    /* Case : 2
                       pt is left child of its parent
                       Right-rotation required */
                    if (pt === parent.left) {
                        this.rotateRight(parent);
                        pt = parent;
                        parent = pt.parent;
                    }

                    /* Case : 3
                       pt is right child of its parent
                       Left-rotation required */
                    this.rotateLeft(grandParent!);
                    this.swapColor(parent!, grandParent!);
                    pt = parent!;
                }
            }
        }
        this.root!.color = 1;
    }

    delete(val: T): boolean {
        const node = this.find(val);
        if (!node) return false;
        node.count--;
        if (!node.count) this.deleteNode(node);
        return true;
    }

    deleteAll(val: T): boolean {
        const node = this.find(val);
        if (!node) return false;
        this.deleteNode(node);
        return true;
    }

    deleteNode(v: RBTreeNode<T>): void {
        const u = BSTreplace(v);

        // True when u and v are both black
        const uvBlack = (u === null || u.color === 1) && v.color === 1;
        const parent = v.parent!;

        if (!u) {
            // u is null therefore v is leaf
            if (v === this.root) this.root = null;
            // v is root, making root null
            else {
                if (uvBlack) {
                    // u and v both black
                    // v is leaf, fix double black at v
                    this.fixDoubleBlack(v);
                } else {
                    // u or v is red
                    if (v.sibling()) {
                        // sibling is not null, make it red"
                        v.sibling()!.color = 0;
                    }
                }
                // delete v from the tree
                if (v.isOnLeft()) parent.left = null;
                else parent.right = null;
            }
            return;
        }

        if (!v.left || !v.right) {
            // v has 1 child
            if (v === this.root) {
                // v is root, assign the value of u to v, and delete u
                v.data = u.data;
                v.left = v.right = null;
            } else {
                // Detach v from tree and move u up
                if (v.isOnLeft()) parent.left = u;
                else parent.right = u;
                u.parent = parent;
                if (uvBlack) this.fixDoubleBlack(u);
                // u and v both black, fix double black at u
                else u.color = 1; // u or v red, color u black
            }
            return;
        }

        // v has 2 children, swap data with successor and recurse
        this.swapData(u, v);
        this.deleteNode(u);

        // find node that replaces a deleted node in BST
        function BSTreplace(x: RBTreeNode<T>): RBTreeNode<T> | null {
            // when node have 2 children
            if (x.left && x.right) return successor(x.right);
            // when leaf
            if (!x.left && !x.right) return null;
            // when single child
            return x.left ?? x.right;
        }
        // find node that do not have a left child
        // in the subtree of the given node
        function successor(x: RBTreeNode<T>): RBTreeNode<T> {
            let temp = x;
            while (temp.left) temp = temp.left;
            return temp;
        }
    }

    fixDoubleBlack(x: RBTreeNode<T>): void {
        if (x === this.root) return; // Reached root

        const sibling = x.sibling();
        const parent = x.parent!;
        if (!sibling) {
            // No sibiling, double black pushed up
            this.fixDoubleBlack(parent);
        } else {
            if (sibling.color === 0) {
                // Sibling red
                parent.color = 0;
                sibling.color = 1;
                if (sibling.isOnLeft()) this.rotateRight(parent);
                // left case
                else this.rotateLeft(parent); // right case
                this.fixDoubleBlack(x);
            } else {
                // Sibling black
                if (sibling.hasRedChild()) {
                    // at least 1 red children
                    if (sibling.left && sibling.left.color === 0) {
                        if (sibling.isOnLeft()) {
                            // left left
                            sibling.left.color = sibling.color;
                            sibling.color = parent.color;
                            this.rotateRight(parent);
                        } else {
                            // right left
                            sibling.left.color = parent.color;
                            this.rotateRight(sibling);
                            this.rotateLeft(parent);
                        }
                    } else {
                        if (sibling.isOnLeft()) {
                            // left right
                            sibling.right!.color = parent.color;
                            this.rotateLeft(sibling);
                            this.rotateRight(parent);
                        } else {
                            // right right
                            sibling.right!.color = sibling.color;
                            sibling.color = parent.color;
                            this.rotateLeft(parent);
                        }
                    }
                    parent.color = 1;
                } else {
                    // 2 black children
                    sibling.color = 0;
                    if (parent.color === 1) this.fixDoubleBlack(parent);
                    else parent.color = 1;
                }
            }
        }
    }

    insert(data: T): boolean {
        // search for a position to insert
        let parent = this.root;
        while (parent) {
            if (this.lt(data, parent.data)) {
                if (!parent.left) break;
                else parent = parent.left;
            } else if (this.lt(parent.data, data)) {
                if (!parent.right) break;
                else parent = parent.right;
            } else break;
        }

        // insert node into parent
        const node = new RBTreeNode(data);
        if (!parent) this.root = node;
        else if (this.lt(node.data, parent.data)) parent.left = node;
        else if (this.lt(parent.data, node.data)) parent.right = node;
        else {
            parent.count++;
            return false;
        }
        node.parent = parent;
        this.fixAfterInsert(node);
        return true;
    }

    search(predicate: (val: T) => boolean, direction: 'left' | 'right'): T | undefined {
        let p = this.root;
        let result = null;
        while (p) {
            if (predicate(p.data)) {
                result = p;
                p = p[direction];
            } else {
                p = p[direction === 'left' ? 'right' : 'left'];
            }
        }
        return result?.data;
    }

    find(data: T): RBTreeNode<T> | null {
        let p = this.root;
        while (p) {
            if (this.lt(data, p.data)) {
                p = p.left;
            } else if (this.lt(p.data, data)) {
                p = p.right;
            } else break;
        }
        return p ?? null;
    }

    count(data: T): number {
        const node = this.find(data);
        return node ? node.count : 0;
    }

    *inOrder(root: RBTreeNode<T> = this.root!): Generator<T, undefined, void> {
        if (!root) return;
        for (const v of this.inOrder(root.left!)) yield v;
        yield root.data;
        for (const v of this.inOrder(root.right!)) yield v;
    }

    *reverseInOrder(root: RBTreeNode<T> = this.root!): Generator<T, undefined, void> {
        if (!root) return;
        for (const v of this.reverseInOrder(root.right!)) yield v;
        yield root.data;
        for (const v of this.reverseInOrder(root.left!)) yield v;
    }
}

class TreeMap<K = number, V = unknown> {
    _size: number;
    tree: RBTree<K>;
    map: Map<K, V> = new Map();
    compare: Compare<K>;
    constructor(
        collection: Array<[K, V]> | Compare<K> = [],
        compare: Compare<K> = (l: K, r: K) => (l < r ? -1 : l > r ? 1 : 0),
    ) {
        if (typeof collection === 'function') {
            compare = collection;
            collection = [];
        }
        this._size = 0;
        this.compare = compare;
        this.tree = new RBTree(compare);
        for (const [key, val] of collection) this.set(key, val);
    }

    size(): number {
        return this._size;
    }

    has(key: K): boolean {
        return !!this.tree.find(key);
    }

    get(key: K): V | undefined {
        return this.map.get(key);
    }

    set(key: K, val: V): boolean {
        const successful = this.tree.insert(key);
        this._size += successful ? 1 : 0;
        this.map.set(key, val);
        return successful;
    }

    delete(key: K): boolean {
        const deleted = this.tree.deleteAll(key);
        this._size -= deleted ? 1 : 0;
        return deleted;
    }

    ceil(target: K): [K, V] | undefined {
        return this.toKeyValue(this.tree.search(key => this.compare(key, target) >= 0, 'left'));
    }

    floor(target: K): [K, V] | undefined {
        return this.toKeyValue(this.tree.search(key => this.compare(key, target) <= 0, 'right'));
    }

    higher(target: K): [K, V] | undefined {
        return this.toKeyValue(this.tree.search(key => this.compare(key, target) > 0, 'left'));
    }

    lower(target: K): [K, V] | undefined {
        return this.toKeyValue(this.tree.search(key => this.compare(key, target) < 0, 'right'));
    }

    first(): [K, V] | undefined {
        return this.toKeyValue(this.tree.inOrder().next().value);
    }

    last(): [K, V] | undefined {
        return this.toKeyValue(this.tree.reverseInOrder().next().value);
    }

    shift(): [K, V] | undefined {
        const first = this.first();
        if (first === undefined) return undefined;
        this.delete(first[0]);
        return first;
    }

    pop(): [K, V] | undefined {
        const last = this.last();
        if (last === undefined) return undefined;
        this.delete(last[0]);
        return last;
    }

    toKeyValue(key: K): [K, V];
    toKeyValue(key: undefined): undefined;
    toKeyValue(key: K | undefined): [K, V] | undefined;
    toKeyValue(key: K | undefined): [K, V] | undefined {
        return key != null ? [key, this.map.get(key)!] : undefined;
    }

    *[Symbol.iterator](): Generator<[K, V], void, void> {
        for (const key of this.keys()) yield this.toKeyValue(key);
    }

    *keys(): Generator<K, void, void> {
        for (const key of this.tree.inOrder()) yield key;
    }

    *values(): Generator<V, undefined, void> {
        for (const key of this.keys()) yield this.map.get(key)!;
        return undefined;
    }

    *rkeys(): Generator<K, undefined, void> {
        for (const key of this.tree.reverseInOrder()) yield key;
        return undefined;
    }

    *rvalues(): Generator<V, undefined, void> {
        for (const key of this.rkeys()) yield this.map.get(key)!;
        return undefined;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
