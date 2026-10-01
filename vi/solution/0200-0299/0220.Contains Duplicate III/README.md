---
comments: true
difficulty: Hard
tags:
    - Array
    - Bucket Sort
    - Ordered Set
    - Sorting
    - Sliding Window
---

<!-- problem:start -->

# [220. Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii)

[中文文档](/solution/0200-0299/0220.Contains%20Duplicate%20III/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên <code>nums</code> và hai số nguyên <code>indexDiff</code> và <code>valueDiff</code>.</p>

<p>Hãy tìm một cặp chỉ số <code>(i, j)</code> sao cho:</p>

<ul>
	<li><code>i != j</code>,</li>
	<li><code>abs(i - j) &lt;= indexDiff</code>.</li>
	<li><code>abs(nums[i] - nums[j]) &lt;= valueDiff</code>, và</li>
</ul>

<p>Trả về <code>true</code><em> nếu tồn tại cặp như vậy, hoặc </em><code>false</code><em> nếu ngược lại</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1,2,3,1], indexDiff = 3, valueDiff = 0
<strong>Đầu ra:</strong> true
<strong>Giải thích:</strong> Ta có thể chọn (i, j) = (0, 3).
Ta thỏa mãn ba điều kiện:
i != j --&gt; 0 != 3
abs(i - j) &lt;= indexDiff --&gt; abs(0 - 3) &lt;= 3
abs(nums[i] - nums[j]) &lt;= valueDiff --&gt; abs(1 - 1) &lt;= 0
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3
<strong>Đầu ra:</strong> false
<strong>Giải thích:</strong> Sau khi thử tất cả các cặp (i, j) có thể, ta không thể thỏa mãn cả ba điều kiện, nên trả về false.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= indexDiff &lt;= nums.length</code></li>
	<li><code>0 &lt;= valueDiff &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Cửa sổ trượt + Ordered Set

<!-- thinking:start -->

> **Tư duy**
>
> Chúng ta cần một cặp có chỉ số chênh lệch không quá $\textit{indexDiff}$ và giá trị chênh lệch không quá $\textit{valueDiff}$. Hash set chỉ kiểm tra được tính bằng nhau, không kiểm tra được các phần tử lân cận gần nhất.
>
> Duy trì một ordered set gồm $\textit{indexDiff}$ giá trị cuối cùng. Với $v$, tìm phần tử nhỏ nhất $\ge v-\textit{valueDiff}$; nếu phần tử đó cũng $\le v+\textit{valueDiff}$ thì chúng ta đã tìm được đáp án. Sau đó chèn $v$ và loại bỏ giá trị rời khỏi cửa sổ.

<!-- thinking:end -->

Chúng ta duy trì một cửa sổ trượt có kích thước $k$, và các phần tử trong cửa sổ được giữ theo thứ tự.

Chúng ta duyệt qua mảng `nums`. Với mỗi phần tử $nums[i]$, chúng ta tìm phần tử đầu tiên trong ordered set lớn hơn hoặc bằng $nums[i] - t$. Nếu phần tử đó tồn tại và nhỏ hơn hoặc bằng $nums[i] + t$, điều đó có nghĩa là chúng ta đã tìm thấy một cặp phần tử thỏa mãn các điều kiện, và trả về `true`. Nếu không, chúng ta chèn $nums[i]$ vào ordered set, và nếu kích thước của ordered set vượt quá $k$, chúng ta cần xóa phần tử được thêm vào sớm nhất khỏi ordered set.

Độ phức tạp thời gian là $O(n \times \log k)$, trong đó $n$ là độ dài của mảng `nums`. Với mỗi phần tử, chúng ta cần $O(\log k)$ thời gian để tìm phần tử trong ordered set, và có tổng cộng $n$ phần tử, nên độ phức tạp thời gian tổng cộng là $O(n \times \log k)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def containsNearbyAlmostDuplicate(
        self, nums: List[int], indexDiff: int, valueDiff: int
    ) -> bool:
        s = SortedSet()
        for i, v in enumerate(nums):
            j = s.bisect_left(v - valueDiff)
            if j < len(s) and s[j] <= v + valueDiff:
                return True
            s.add(v)
            if i >= indexDiff:
                s.remove(nums[i - indexDiff])
        return False
```

#### Java

```java
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> ts = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            Long x = ts.ceiling((long) nums[i] - (long) valueDiff);
            if (x != null && x <= (long) nums[i] + (long) valueDiff) {
                return true;
            }
            ts.add((long) nums[i]);
            if (i >= indexDiff) {
                ts.remove((long) nums[i - indexDiff]);
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool containsNearbyAlmostDuplicate(vector<int>& nums, int indexDiff, int valueDiff) {
        set<long> s;
        for (int i = 0; i < nums.size(); ++i) {
            auto it = s.lower_bound((long) nums[i] - valueDiff);
            if (it != s.end() && *it <= (long) nums[i] + valueDiff) return true;
            s.insert((long) nums[i]);
            if (i >= indexDiff) s.erase((long) nums[i - indexDiff]);
        }
        return false;
    }
};
```

#### Go

```go
func containsNearbyAlmostDuplicate(nums []int, k int, t int) bool {
	n := len(nums)
	left, right := 0, 0
	rbt := redblacktree.NewWithIntComparator()
	for right < n {
		cur := nums[right]
		right++
		if p, ok := rbt.Floor(cur); ok && cur-p.Key.(int) <= t {
			return true
		}
		if p, ok := rbt.Ceiling(cur); ok && p.Key.(int)-cur <= t {
			return true
		}
		rbt.Put(cur, struct{}{})
		if right-left > k {
			rbt.Remove(nums[left])
			left++
		}
	}
	return false
}
```

#### TypeScript

```ts
function containsNearbyAlmostDuplicate(
    nums: number[],
    indexDiff: number,
    valueDiff: number,
): boolean {
    const ts = new TreeSet<number>();
    for (let i = 0; i < nums.length; ++i) {
        const x = ts.ceil(nums[i] - valueDiff);
        if (x != null && x <= nums[i] + valueDiff) {
            return true;
        }
        ts.add(nums[i]);
        if (i >= indexDiff) {
            ts.delete(nums[i - indexDiff]);
        }
    }
    return false;
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
        if (!this.parent) return null; // sibling là null nếu không có parent
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

            /*  Trường hợp: A
                Parent của pt là con trái của Grand-parent của pt */
            if (parent === grandParent?.left) {
                const uncle = grandParent.right;

                /* Trường hợp: 1
                   Uncle của pt cũng có màu đỏ
                   Chỉ cần đổi màu */
                if (uncle && uncle.color === 0) {
                    grandParent.color = 0;
                    parent.color = 1;
                    uncle.color = 1;
                    pt = grandParent;
                } else {
                    /* Trường hợp: 2
                       pt là con phải của parent
                       Cần xoay trái */
                    if (pt === parent.right) {
                        this.rotateLeft(parent);
                        pt = parent;
                        parent = pt.parent;
                    }

                    /* Trường hợp: 3
                       pt là con trái của parent
                       Cần xoay phải */
                    this.rotateRight(grandParent);
                    this.swapColor(parent!, grandParent);
                    pt = parent!;
                }
            } else {
                /* Trường hợp: B
               Parent của pt là con phải của Grand-parent của pt */
                const uncle = grandParent!.left;

                /*  Trường hợp: 1
                    Uncle của pt cũng có màu đỏ
                    Chỉ cần đổi màu */
                if (uncle != null && uncle.color === 0) {
                    grandParent!.color = 0;
                    parent.color = 1;
                    uncle.color = 1;
                    pt = grandParent!;
                } else {
                    /* Trường hợp: 2
                       pt là con trái của parent
                       Cần xoay phải */
                    if (pt === parent.left) {
                        this.rotateRight(parent);
                        pt = parent;
                        parent = pt.parent;
                    }

                    /* Trường hợp: 3
                       pt là con phải của parent
                       Cần xoay trái */
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

        // true khi u và v đều có màu đen
        const uvBlack = (u === null || u.color === 1) && v.color === 1;
        const parent = v.parent!;

        if (!u) {
            // u là null, do đó v là một lá
            if (v === this.root) this.root = null;
            // v là root, khiến root trở thành null
            else {
                if (uvBlack) {
                    // u và v đều có màu đen
                    // v là lá, sửa double black tại v
                    this.fixDoubleBlack(v);
                } else {
                    // u hoặc v có màu đỏ
                    if (v.sibling()) {
                        // sibling không phải null, đổi màu của nó thành đỏ"
                        v.sibling()!.color = 0;
                    }
                }
                // xóa v khỏi cây
                if (v.isOnLeft()) parent.left = null;
                else parent.right = null;
            }
            return;
        }

        if (!v.left || !v.right) {
            // v có 1 con
            if (v === this.root) {
                // v là root, gán giá trị của u cho v, rồi xóa u
                v.data = u.data;
                v.left = v.right = null;
            } else {
                // Tách v khỏi cây và đưa u lên
                if (v.isOnLeft()) parent.left = u;
                else parent.right = u;
                u.parent = parent;
                if (uvBlack) this.fixDoubleBlack(u);
                // u và v đều có màu đen, sửa double black tại u
                else u.color = 1; // u hoặc v có màu đỏ, tô u thành đen
            }
            return;
        }

        // v có 2 con, đổi dữ liệu với successor rồi đệ quy
        this.swapData(u, v);
        this.deleteNode(u);

        // tìm node thay thế cho node đã bị xóa trong BST
        function BSTreplace(x: RBTreeNode<T>): RBTreeNode<T> | null {
            // khi x có 2 con
            if (x.left && x.right) return successor(x.right);
            // khi là lá
            if (!x.left && !x.right) return null;
            // khi có một con
            return x.left ?? x.right;
        }
        // tìm node không có con trái trong
        // cây con của node đã cho
        function successor(x: RBTreeNode<T>): RBTreeNode<T> {
            let temp = x;
            while (temp.left) temp = temp.left;
            return temp;
        }
    }

    fixDoubleBlack(x: RBTreeNode<T>): void {
        if (x === this.root) return; // Đã đến root

        const sibling = x.sibling();
        const parent = x.parent!;
        if (!sibling) {
            // Không có sibling, đẩy double black lên
            this.fixDoubleBlack(parent);
        } else {
            if (sibling.color === 0) {
                // Sibling màu đỏ
                parent.color = 0;
                sibling.color = 1;
                if (sibling.isOnLeft()) this.rotateRight(parent);
                // trường hợp bên trái
                else this.rotateLeft(parent); // trường hợp bên phải
                this.fixDoubleBlack(x);
            } else {
                // Sibling màu đen
                if (sibling.hasRedChild()) {
                    // có ít nhất 1 con màu đỏ
                    if (sibling.left && sibling.left.color === 0) {
                        if (sibling.isOnLeft()) {
                            // trái trái
                            sibling.left.color = sibling.color;
                            sibling.color = parent.color;
                            this.rotateRight(parent);
                        } else {
                            // phải trái
                            sibling.left.color = parent.color;
                            this.rotateRight(sibling);
                            this.rotateLeft(parent);
                        }
                    } else {
                        if (sibling.isOnLeft()) {
                            // trái phải
                            sibling.right!.color = parent.color;
                            this.rotateLeft(sibling);
                            this.rotateRight(parent);
                        } else {
                            // phải phải
                            sibling.right!.color = sibling.color;
                            sibling.color = parent.color;
                            this.rotateLeft(parent);
                        }
                    }
                    parent.color = 1;
                } else {
                    // 2 con màu đen
                    sibling.color = 0;
                    if (parent.color === 1) this.fixDoubleBlack(parent);
                    else parent.color = 1;
                }
            }
        }
    }

    insert(data: T): boolean {
        // tìm vị trí để chèn
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

        // chèn node vào parent
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
     * Trả về một generator để duyệt set theo thứ tự ngược
     */
    *rvalues(): Generator<T, undefined, void> {
        for (const val of this.tree.reverseInOrder()) yield val;
        return undefined;
    }
}

class TreeMultiSet<T = number> {
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
        this._size++;
        return successful;
    }

    delete(val: T): boolean {
        const successful = this.tree.delete(val);
        if (!successful) return false;
        this._size--;
        return true;
    }

    count(val: T): number {
        const node = this.tree.find(val);
        return node ? node.count : 0;
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
        yield* this.values();
    }

    *keys(): Generator<T, void, void> {
        for (const val of this.values()) yield val;
    }

    *values(): Generator<T, undefined, void> {
        for (const val of this.tree.inOrder()) {
            let count = this.count(val);
            while (count--) yield val;
        }
        return undefined;
    }

    /**
     * Trả về một generator để duyệt multi-set theo thứ tự ngược
     */
    *rvalues(): Generator<T, undefined, void> {
        for (const val of this.tree.reverseInOrder()) {
            let count = this.count(val);
            while (count--) yield val;
        }
        return undefined;
    }
}
```

#### C#

```cs
public class Solution {
    public bool ContainsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k <= 0 || t < 0) return false;
        var index = new SortedList<int, object>();
        for (int i = 0; i < nums.Length; ++i) {
            if (index.ContainsKey(nums[i])) {
                return true;
            }
            index.Add(nums[i], null);
            var j = index.IndexOfKey(nums[i]);
            if (j > 0 && (long)nums[i] - index.Keys[j - 1] <= t) {
                return true;
            }
            if (j < index.Count - 1 && (long)index.Keys[j + 1] - nums[i] <= t) {
                return true;
            }
            if (index.Count > k) {
                index.Remove(nums[i - k]);
            }
        }
        return false;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
