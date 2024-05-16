---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1172.Dinner%20Plate%20Stacks/README_EN.md
rating: 2109
source: Weekly Contest 151 Q4
tags:
    - Stack
    - Design
    - Hash Table
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [1172. Dinner Plate Stacks](https://leetcode.com/problems/dinner-plate-stacks)

[中文文档](/solution/1100-1199/1172.Dinner%20Plate%20Stacks/README.md)

## Description

<p>You have an infinite number of stacks arranged in a row and numbered (left to right) from <code>0</code>, each of the stacks has the same maximum capacity.</p>

<p>Implement the <code>DinnerPlates</code> class:</p>

<ul>
	<li><code>DinnerPlates(int capacity)</code> Initializes the object with the maximum capacity of the stacks <code>capacity</code>.</li>
	<li><code>void push(int val)</code> Pushes the given integer <code>val</code> into the leftmost stack with a size less than <code>capacity</code>.</li>
	<li><code>int pop()</code> Returns the value at the top of the rightmost non-empty stack and removes it from that stack, and returns <code>-1</code> if all the stacks are empty.</li>
	<li><code>int popAtStack(int index)</code> Returns the value at the top of the stack with the given index <code>index</code> and removes it from that stack or returns <code>-1</code> if the stack with that given index is empty.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;DinnerPlates&quot;, &quot;push&quot;, &quot;push&quot;, &quot;push&quot;, &quot;push&quot;, &quot;push&quot;, &quot;popAtStack&quot;, &quot;push&quot;, &quot;push&quot;, &quot;popAtStack&quot;, &quot;popAtStack&quot;, &quot;pop&quot;, &quot;pop&quot;, &quot;pop&quot;, &quot;pop&quot;, &quot;pop&quot;]
[[2], [1], [2], [3], [4], [5], [0], [20], [21], [0], [2], [], [], [], [], []]
<strong>Output</strong>
[null, null, null, null, null, null, 2, null, null, 20, 21, 5, 4, 3, 1, -1]

<strong>Explanation:</strong> 
DinnerPlates D = DinnerPlates(2);  // Initialize with capacity = 2
D.push(1);
D.push(2);
D.push(3);
D.push(4);
D.push(5);         // The stacks are now:  2  4
                                           1  3  5
                                           ﹈ ﹈ ﹈
D.popAtStack(0);   // Returns 2.  The stacks are now:     4
                                                       1  3  5
                                                       ﹈ ﹈ ﹈
D.push(20);        // The stacks are now: 20  4
                                           1  3  5
                                           ﹈ ﹈ ﹈
D.push(21);        // The stacks are now: 20  4 21
                                           1  3  5
                                           ﹈ ﹈ ﹈
D.popAtStack(0);   // Returns 20.  The stacks are now:     4 21
                                                        1  3  5
                                                        ﹈ ﹈ ﹈
D.popAtStack(2);   // Returns 21.  The stacks are now:     4
                                                        1  3  5
                                                        ﹈ ﹈ ﹈ 
D.pop()            // Returns 5.  The stacks are now:      4
                                                        1  3 
                                                        ﹈ ﹈  
D.pop()            // Returns 4.  The stacks are now:   1  3 
                                                        ﹈ ﹈   
D.pop()            // Returns 3.  The stacks are now:   1 
                                                        ﹈   
D.pop()            // Returns 1.  There are no stacks.
D.pop()            // Returns -1.  There are still no stacks.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= capacity &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= val &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= index &lt;= 10<sup>5</sup></code></li>
	<li>At most <code>2 * 10<sup>5</sup></code> calls will be made to <code>push</code>, <code>pop</code>, and <code>popAtStack</code>.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Stack Array + Ordered Set

We define the following data structures or variables:

-   `capacity`: The capacity of each stack;
-   `stacks`: Stack array, used to store all stacks, each with a maximum capacity of `capacity`;
-   `not_full`: Ordered set, used to store the indices of all non-full stacks in the stack array.

For the `push(val)` operation:

-   We first check if `not_full` is empty. If it is, it means there are no non-full stacks, so we need to create a new stack and push `val` into it. At this point, we check if the capacity `capacity` is greater than $1$. If it is, we add the index of this stack to `not_full`.
-   If `not_full` is not empty, it means there are non-full stacks. We take out the smallest index `index` from `not_full`, and push `val` into `stacks[index]`. At this point, if the capacity of `stacks[index]` equals `capacity`, we remove `index` from `not_full`.

For the `popAtStack(index)` operation:

-   We first check if `index` is within the index range of `stacks`. If it is not, we directly return $-1$. If `stacks[index]` is empty, we also directly return $-1$.
-   If `stacks[index]` is not empty, we pop the top element `val` from `stacks[index]`. If `index` equals the length of `stacks` minus $1$, it means `stacks[index]` is the last stack. If it is empty, we loop to remove the index of the last stack from `not_full`, and remove the last stack from the stack array `stacks`, until the last stack is not empty, or the stack array `stacks` is empty. Otherwise, if `stacks[index]` is not the last stack, we add `index` to `not_full`.
-   Finally, return `val`.

For the `pop()` operation:

-   We directly call `popAtStack(stacks.length - 1)`.

The time complexity is $(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the number of operations.

<!-- tabs:start -->

```python
from sortedcontainers import SortedSet


class DinnerPlates:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.stacks = []
        self.not_full = SortedSet()

    def push(self, val: int) -> None:
        if not self.not_full:
            self.stacks.append([val])
            if self.capacity > 1:
                self.not_full.add(len(self.stacks) - 1)
        else:
            index = self.not_full[0]
            self.stacks[index].append(val)
            if len(self.stacks[index]) == self.capacity:
                self.not_full.discard(index)

    def pop(self) -> int:
        return self.popAtStack(len(self.stacks) - 1)

    def popAtStack(self, index: int) -> int:
        if index < 0 or index >= len(self.stacks) or not self.stacks[index]:
            return -1
        val = self.stacks[index].pop()
        if index == len(self.stacks) - 1 and not self.stacks[-1]:
            while self.stacks and not self.stacks[-1]:
                self.not_full.discard(len(self.stacks) - 1)
                self.stacks.pop()
        else:
            self.not_full.add(index)
        return val


# Your DinnerPlates object will be instantiated and called as such:
# obj = DinnerPlates(capacity)
# obj.push(val)
# param_2 = obj.pop()
# param_3 = obj.popAtStack(index)
```

```java
class DinnerPlates {
    private int capacity;
    private List<Deque<Integer>> stacks = new ArrayList<>();
    private TreeSet<Integer> notFull = new TreeSet<>();

    public DinnerPlates(int capacity) {
        this.capacity = capacity;
    }

    public void push(int val) {
        if (notFull.isEmpty()) {
            stacks.add(new ArrayDeque<>());
            stacks.get(stacks.size() - 1).push(val);
            if (capacity > 1) {
                notFull.add(stacks.size() - 1);
            }
        } else {
            int index = notFull.first();
            stacks.get(index).push(val);
            if (stacks.get(index).size() == capacity) {
                notFull.pollFirst();
            }
        }
    }

    public int pop() {
        return popAtStack(stacks.size() - 1);
    }

    public int popAtStack(int index) {
        if (index < 0 || index >= stacks.size() || stacks.get(index).isEmpty()) {
            return -1;
        }
        int val = stacks.get(index).pop();
        if (index == stacks.size() - 1 && stacks.get(stacks.size() - 1).isEmpty()) {
            while (!stacks.isEmpty() && stacks.get(stacks.size() - 1).isEmpty()) {
                notFull.remove(stacks.size() - 1);
                stacks.remove(stacks.size() - 1);
            }
        } else {
            notFull.add(index);
        }
        return val;
    }
}

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * DinnerPlates obj = new DinnerPlates(capacity);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAtStack(index);
 */
```

```cpp
class DinnerPlates {
public:
    DinnerPlates(int capacity) {
        this->capacity = capacity;
    }

    void push(int val) {
        if (notFull.empty()) {
            stacks.emplace_back(stack<int>());
            stacks.back().push(val);
            if (capacity > 1) {
                notFull.insert(stacks.size() - 1);
            }
        } else {
            int index = *notFull.begin();
            stacks[index].push(val);
            if (stacks[index].size() == capacity) {
                notFull.erase(index);
            }
        }
    }

    int pop() {
        return popAtStack(stacks.size() - 1);
    }

    int popAtStack(int index) {
        if (index < 0 || index >= stacks.size() || stacks[index].empty()) {
            return -1;
        }
        int val = stacks[index].top();
        stacks[index].pop();
        if (index == stacks.size() - 1 && stacks[index].empty()) {
            while (!stacks.empty() && stacks.back().empty()) {
                notFull.erase(stacks.size() - 1);
                stacks.pop_back();
            }
        } else {
            notFull.insert(index);
        }
        return val;
    }

private:
    int capacity;
    vector<stack<int>> stacks;
    set<int> notFull;
};

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * DinnerPlates* obj = new DinnerPlates(capacity);
 * obj->push(val);
 * int param_2 = obj->pop();
 * int param_3 = obj->popAtStack(index);
 */
```

```go
type DinnerPlates struct {
	capacity int
	stacks   [][]int
	notFull  *redblacktree.Tree
}

func Constructor(capacity int) DinnerPlates {
	return DinnerPlates{capacity: capacity, notFull: redblacktree.NewWithIntComparator()}
}

func (this *DinnerPlates) Push(val int) {
	if this.notFull.Size() == 0 {
		this.stacks = append(this.stacks, []int{val})
		if this.capacity > 1 {
			this.notFull.Put(len(this.stacks)-1, nil)
		}
	} else {
		index, _ := this.notFull.Left().Key.(int)
		this.stacks[index] = append(this.stacks[index], val)
		if len(this.stacks[index]) == this.capacity {
			this.notFull.Remove(index)
		}
	}
}

func (this *DinnerPlates) Pop() int {
	return this.PopAtStack(len(this.stacks) - 1)
}

func (this *DinnerPlates) PopAtStack(index int) int {
	if index < 0 || index >= len(this.stacks) || len(this.stacks[index]) == 0 {
		return -1
	}
	val := this.stacks[index][len(this.stacks[index])-1]
	this.stacks[index] = this.stacks[index][:len(this.stacks[index])-1]
	if index == len(this.stacks)-1 && len(this.stacks[index]) == 0 {
		for len(this.stacks) > 0 && len(this.stacks[len(this.stacks)-1]) == 0 {
			this.notFull.Remove(len(this.stacks) - 1)
			this.stacks = this.stacks[:len(this.stacks)-1]
		}
	} else {
		this.notFull.Put(index, nil)
	}
	return val
}

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * obj := Constructor(capacity);
 * obj.Push(val);
 * param_2 := obj.Pop();
 * param_3 := obj.PopAtStack(index);
 */
```

```ts
class DinnerPlates {
    capacity: number;
    stacks: number[][];
    notFull: TreeSet<number>;

    constructor(capacity: number) {
        this.capacity = capacity;
        this.stacks = [];
        this.notFull = new TreeSet<number>();
    }

    push(val: number): void {
        if (this.notFull.size() === 0) {
            this.stacks.push([val]);
            if (this.capacity > 1) {
                this.notFull.add(this.stacks.length - 1);
            }
        } else {
            const index = this.notFull.first()!;
            this.stacks[index].push(val);
            if (this.stacks[index].length === this.capacity) {
                this.notFull.delete(index);
            }
        }
    }

    pop(): number {
        return this.popAtStack(this.stacks.length - 1);
    }

    popAtStack(index: number): number {
        if (index < 0 || index >= this.stacks.length || this.stacks[index].length === 0) {
            return -1;
        }
        const val = this.stacks[index].pop()!;
        if (index === this.stacks.length - 1 && this.stacks[index].length === 0) {
            while (this.stacks.length > 0 && this.stacks[this.stacks.length - 1].length === 0) {
                this.notFull.delete(this.stacks.length - 1);
                this.stacks.pop();
            }
        } else {
            this.notFull.add(index);
        }
        return val;
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
 * Your DinnerPlates object will be instantiated and called as such:
 * var obj = new DinnerPlates(capacity)
 * obj.push(val)
 * var param_2 = obj.pop()
 * var param_3 = obj.popAtStack(index)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
