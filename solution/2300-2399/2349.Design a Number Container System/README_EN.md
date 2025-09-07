---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2349.Design%20a%20Number%20Container%20System/README_EN.md
rating: 1540
source: Biweekly Contest 83 Q3
tags:
    - Design
    - Hash Table
    - Ordered Set
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2349. Design a Number Container System](https://leetcode.com/problems/design-a-number-container-system)

[中文文档](/solution/2300-2399/2349.Design%20a%20Number%20Container%20System/README.md)

## Description

<!-- description:start -->

<p>Design a number container system that can do the following:</p>

<ul>
	<li><strong>Insert </strong>or <strong>Replace</strong> a number at the given index in the system.</li>
	<li><strong>Return </strong>the smallest index for the given number in the system.</li>
</ul>

<p>Implement the <code>NumberContainers</code> class:</p>

<ul>
	<li><code>NumberContainers()</code> Initializes the number container system.</li>
	<li><code>void change(int index, int number)</code> Fills the container at <code>index</code> with the <code>number</code>. If there is already a number at that <code>index</code>, replace it.</li>
	<li><code>int find(int number)</code> Returns the smallest index for the given <code>number</code>, or <code>-1</code> if there is no index that is filled by <code>number</code> in the system.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;NumberContainers&quot;, &quot;find&quot;, &quot;change&quot;, &quot;change&quot;, &quot;change&quot;, &quot;change&quot;, &quot;find&quot;, &quot;change&quot;, &quot;find&quot;]
[[], [10], [2, 10], [1, 10], [3, 10], [5, 10], [10], [1, 20], [10]]
<strong>Output</strong>
[null, -1, null, null, null, null, 1, null, 2]

<strong>Explanation</strong>
NumberContainers nc = new NumberContainers();
nc.find(10); // There is no index that is filled with number 10. Therefore, we return -1.
nc.change(2, 10); // Your container at index 2 will be filled with number 10.
nc.change(1, 10); // Your container at index 1 will be filled with number 10.
nc.change(3, 10); // Your container at index 3 will be filled with number 10.
nc.change(5, 10); // Your container at index 5 will be filled with number 10.
nc.find(10); // Number 10 is at the indices 1, 2, 3, and 5. Since the smallest index that is filled with 10 is 1, we return 1.
nc.change(1, 20); // Your container at index 1 will be filled with number 20. Note that index 1 was filled with 10 and then replaced with 20. 
nc.find(10); // Number 10 is at the indices 2, 3, and 5. The smallest index that is filled with 10 is 2. Therefore, we return 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= index, number &lt;= 10<sup>9</sup></code></li>
	<li>At most <code>10<sup>5</sup></code> calls will be made <strong>in total</strong> to <code>change</code> and <code>find</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Ordered Set

We use a hash table $d$ to record the mapping relationship between indices and numbers, and another hash table $g$ to record the set of indices corresponding to each number. Here, we can use an ordered set to store the indices, which allows us to conveniently find the smallest index.

When calling the `change` method, we first check if the index already exists. If it does, we remove the original number from its corresponding index set and then add the new number to the corresponding index set. The time complexity is $O(\log n)$.

When calling the `find` method, we simply return the first element of the index set corresponding to the number. The time complexity is $O(1)$.

The space complexity is $O(n)$, where $n$ is the number of numbers.

<!-- tabs:start -->

#### Python3

```python
class NumberContainers:

    def __init__(self):
        self.d = {}
        self.g = defaultdict(SortedSet)

    def change(self, index: int, number: int) -> None:
        if index in self.d:
            old_number = self.d[index]
            self.g[old_number].remove(index)
        self.d[index] = number
        self.g[number].add(index)

    def find(self, number: int) -> int:
        ids = self.g[number]
        return ids[0] if ids else -1


# Your NumberContainers object will be instantiated and called as such:
# obj = NumberContainers()
# obj.change(index,number)
# param_2 = obj.find(number)
```

#### Java

```java
class NumberContainers {
    private Map<Integer, Integer> d = new HashMap<>();
    private Map<Integer, TreeSet<Integer>> g = new HashMap<>();

    public NumberContainers() {
    }

    public void change(int index, int number) {
        if (d.containsKey(index)) {
            int oldNumber = d.get(index);
            g.get(oldNumber).remove(index);
        }
        d.put(index, number);
        g.computeIfAbsent(number, k -> new TreeSet<>()).add(index);
    }

    public int find(int number) {
        var ids = g.get(number);
        return ids == null || ids.isEmpty() ? -1 : ids.first();
    }
}

/**
 * Your NumberContainers object will be instantiated and called as such:
 * NumberContainers obj = new NumberContainers();
 * obj.change(index,number);
 * int param_2 = obj.find(number);
 */
```

#### C++

```cpp
class NumberContainers {
public:
    NumberContainers() {
    }

    void change(int index, int number) {
        if (d.contains(index)) {
            int oldNumber = d[index];
            g[oldNumber].erase(index);
            if (g[oldNumber].empty()) {
                g.erase(oldNumber);
            }
        }
        d[index] = number;
        g[number].insert(index);
    }

    int find(int number) {
        return g.contains(number) ? *g[number].begin() : -1;
    }

private:
    unordered_map<int, int> d;
    unordered_map<int, set<int>> g;
};

/**
 * Your NumberContainers object will be instantiated and called as such:
 * NumberContainers* obj = new NumberContainers();
 * obj->change(index,number);
 * int param_2 = obj->find(number);
 */
```

#### Go

```go
type NumberContainers struct {
	d map[int]int
	g map[int]*redblacktree.Tree
}

func Constructor() NumberContainers {
	return NumberContainers{map[int]int{}, map[int]*redblacktree.Tree{}}
}

func (this *NumberContainers) Change(index int, number int) {
	if oldNumber, ok := this.d[index]; ok {
		this.g[oldNumber].Remove(index)
	}
	this.d[index] = number
	if _, ok := this.g[number]; !ok {
		this.g[number] = redblacktree.NewWithIntComparator()
	}
	this.g[number].Put(index, nil)
}

func (this *NumberContainers) Find(number int) int {
	if ids, ok := this.g[number]; ok && ids.Size() > 0 {
		return ids.Left().Key.(int)
	}
	return -1
}

/**
 * Your NumberContainers object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Change(index,number);
 * param_2 := obj.Find(number);
 */
```

#### TypeScript

```ts
class NumberContainers {
    private d = new Map<number, number>();
    private g = new Map<number, TreeSet<number>>();
    constructor() {}

    change(index: number, number: number): void {
        if (this.d.has(index)) {
            const oldNumber = this.d.get(index)!;
            this.g.get(oldNumber)!.delete(index);
            if (!this.g.get(oldNumber)!.size()) {
                this.g.delete(oldNumber);
            }
        }
        this.d.set(index, number);
        if (!this.g.has(number)) {
            this.g.set(number, new TreeSet());
        }
        this.g.get(number)!.add(index);
    }

    find(number: number): number {
        return this.g.has(number) ? this.g.get(number)!.first()! : -1;
    }
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

class TreeSet<T = number> {
    _size: number;
    tree: RBTree<T>;
    compare: Compare<T>;
    constructor(
        collection: T[] | Compare<T> = [],
        compare: Compare<T> = (l: T, r: T) => (l < r ? -1 : l > r ? 1 : 0),
    ) {
        if (typeof collection === 'function') {
            compare = collection;
            collection = [];
        }
        this._size = 0;
        this.compare = compare;
        this.tree = new RBTree(compare);
        for (const val of collection) this.add(val);
    }

    size(): number {
        return this._size;
    }

    has(val: T): boolean {
        return !!this.tree.find(val);
    }

    add(val: T): boolean {
        const successful = this.tree.insert(val);
        this._size += successful ? 1 : 0;
        return successful;
    }

    delete(val: T): boolean {
        const deleted = this.tree.deleteAll(val);
        this._size -= deleted ? 1 : 0;
        return deleted;
    }

    ceil(val: T): T | undefined {
        let p = this.tree.root;
        let higher = null;
        while (p) {
            if (this.compare(p.data, val) >= 0) {
                higher = p;
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return higher?.data;
    }

    floor(val: T): T | undefined {
        let p = this.tree.root;
        let lower = null;
        while (p) {
            if (this.compare(val, p.data) >= 0) {
                lower = p;
                p = p.right;
            } else {
                p = p.left;
            }
        }
        return lower?.data;
    }

    higher(val: T): T | undefined {
        let p = this.tree.root;
        let higher = null;
        while (p) {
            if (this.compare(val, p.data) < 0) {
                higher = p;
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return higher?.data;
    }

    lower(val: T): T | undefined {
        let p = this.tree.root;
        let lower = null;
        while (p) {
            if (this.compare(p.data, val) < 0) {
                lower = p;
                p = p.right;
            } else {
                p = p.left;
            }
        }
        return lower?.data;
    }

    first(): T | undefined {
        return this.tree.inOrder().next().value;
    }

    last(): T | undefined {
        return this.tree.reverseInOrder().next().value;
    }

    shift(): T | undefined {
        const first = this.first();
        if (first === undefined) return undefined;
        this.delete(first);
        return first;
    }

    pop(): T | undefined {
        const last = this.last();
        if (last === undefined) return undefined;
        this.delete(last);
        return last;
    }

    *[Symbol.iterator](): Generator<T, void, void> {
        for (const val of this.values()) yield val;
    }

    *keys(): Generator<T, void, void> {
        for (const val of this.values()) yield val;
    }

    *values(): Generator<T, undefined, void> {
        for (const val of this.tree.inOrder()) yield val;
        return undefined;
    }

    /**
     * Return a generator for reverse order traversing the set
     */
    *rvalues(): Generator<T, undefined, void> {
        for (const val of this.tree.reverseInOrder()) yield val;
        return undefined;
    }
}

/**
 * Your NumberContainers object will be instantiated and called as such:
 * var obj = new NumberContainers()
 * obj.change(index,number)
 * var param_2 = obj.find(number)
 */
```

#### Rust

```rust
use std::collections::{HashMap, BTreeSet};

struct NumberContainers {
    d: HashMap<i32, i32>,
    g: HashMap<i32, BTreeSet<i32>>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl NumberContainers {
    fn new() -> Self {
        Self {
            d: HashMap::new(),
            g: HashMap::new(),
        }
    }

    fn change(&mut self, index: i32, number: i32) {
        if let Some(&old_number) = self.d.get(&index) {
            if let Some(set) = self.g.get_mut(&old_number) {
                set.remove(&index);
            }
        }
        self.d.insert(index, number);
        self.g.entry(number).or_insert_with(BTreeSet::new).insert(index);
    }

    fn find(&self, number: i32) -> i32 {
        match self.g.get(&number) {
            Some(set) if !set.is_empty() => *set.iter().next().unwrap(),
            _ => -1,
        }
    }
}
```

#### C#

```cs
public class NumberContainers {
    private Dictionary<int, int> d = new Dictionary<int, int>();
    private Dictionary<int, SortedSet<int>> g = new Dictionary<int, SortedSet<int>>();

    public NumberContainers() {
    }

    public void Change(int index, int number) {
        if (d.TryGetValue(index, out int oldNumber)) {
            g[oldNumber].Remove(index);
        }
        d[index] = number;
        if (!g.ContainsKey(number)) {
            g[number] = new SortedSet<int>();
        }
        g[number].Add(index);
    }

    public int Find(int number) {
        if (!g.ContainsKey(number) || g[number].Count == 0) {
            return -1;
        }
        return g[number].Min;
    }
}

/**
 * Your NumberContainers object will be instantiated and called as such:
 * NumberContainers obj = new NumberContainers();
 * obj.Change(index, number);
 * int param_2 = obj.Find(number);
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
