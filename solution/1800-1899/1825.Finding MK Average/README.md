# [1825. 求出 MK 平均值](https://leetcode.cn/problems/finding-mk-average)

[English Version](/solution/1800-1899/1825.Finding%20MK%20Average/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数&nbsp;<code>m</code>&nbsp;和&nbsp;<code>k</code>&nbsp;，以及数据流形式的若干整数。你需要实现一个数据结构，计算这个数据流的 <b>MK 平均值</b>&nbsp;。</p>

<p><strong>MK 平均值</strong>&nbsp;按照如下步骤计算：</p>

<ol>
	<li>如果数据流中的整数少于 <code>m</code>&nbsp;个，<strong>MK 平均值</strong>&nbsp;为 <code>-1</code>&nbsp;，否则将数据流中最后 <code>m</code>&nbsp;个元素拷贝到一个独立的容器中。</li>
	<li>从这个容器中删除最小的 <code>k</code>&nbsp;个数和最大的 <code>k</code>&nbsp;个数。</li>
	<li>计算剩余元素的平均值，并 <strong>向下取整到最近的整数</strong>&nbsp;。</li>
</ol>

<p>请你实现&nbsp;<code>MKAverage</code>&nbsp;类：</p>

<ul>
	<li><code>MKAverage(int m, int k)</code>&nbsp;用一个空的数据流和两个整数 <code>m</code>&nbsp;和 <code>k</code>&nbsp;初始化&nbsp;<strong>MKAverage</strong>&nbsp;对象。</li>
	<li><code>void addElement(int num)</code>&nbsp;往数据流中插入一个新的元素&nbsp;<code>num</code>&nbsp;。</li>
	<li><code>int calculateMKAverage()</code>&nbsp;对当前的数据流计算并返回 <strong>MK 平均数</strong>&nbsp;，结果需 <strong>向下取整到最近的整数</strong> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["MKAverage", "addElement", "addElement", "calculateMKAverage", "addElement", "calculateMKAverage", "addElement", "addElement", "addElement", "calculateMKAverage"]
[[3, 1], [3], [1], [], [10], [], [5], [5], [5], []]
<strong>输出：</strong>
[null, null, null, -1, null, 3, null, null, null, 5]

<strong>解释：</strong>
MKAverage obj = new MKAverage(3, 1); 
obj.addElement(3);        // 当前元素为 [3]
obj.addElement(1);        // 当前元素为 [3,1]
obj.calculateMKAverage(); // 返回 -1 ，因为 m = 3 ，但数据流中只有 2 个元素
obj.addElement(10);       // 当前元素为 [3,1,10]
obj.calculateMKAverage(); // 最后 3 个元素为 [3,1,10]
                          // 删除最小以及最大的 1 个元素后，容器为 [3]
                          // [3] 的平均值等于 3/1 = 3 ，故返回 3
obj.addElement(5);        // 当前元素为 [3,1,10,5]
obj.addElement(5);        // 当前元素为 [3,1,10,5,5]
obj.addElement(5);        // 当前元素为 [3,1,10,5,5,5]
obj.calculateMKAverage(); // 最后 3 个元素为 [5,5,5]
                          // 删除最小以及最大的 1 个元素后，容器为 [5]
                          // [5] 的平均值等于 5/1 = 5 ，故返回 5
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= m &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k*2 &lt; m</code></li>
	<li><code>1 &lt;= num &lt;= 10<sup>5</sup></code></li>
	<li><code>addElement</code> 与&nbsp;<code>calculateMKAverage</code>&nbsp;总操作次数不超过 <code>10<sup>5</sup></code> 次。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：有序集合 + 队列**

我们可以维护以下数据结构或变量：

-   一个长度为 $m$ 的队列 $q$，其中队首元素为最早加入的元素，队尾元素为最近加入的元素；
-   三个有序集合，分别为 $lo$, $mid$, $hi$，其中 $lo$ 和 $hi$ 分别存储最小的 $k$ 个元素和最大的 $k$ 个元素，而 $mid$ 存储剩余的元素；
-   一个变量 $s$，维护 $mid$ 中所有元素的和；
-   部分编程语言（如 Java, Go）额外维护两个变量 $size1$ 和 $size3$，分别表示 $lo$ 和 $hi$ 中元素的个数。

调用 $addElement(num)$ 函数时，顺序执行以下操作：

1. 如果 $lo$ 为空，或者 $num \leq max(lo)$，则将 $num$ 加入 $lo$ 中；否则如果 $hi$ 为空，或者 $num \geq min(hi)$，则将 $num$ 加入 $hi$ 中；否则将 $num$ 加入 $mid$ 中，同时将 $num$ 的值加到 $s$ 中。
1. 接下来将 $num$ 加入队列 $q$ 中，如果此时队列 $q$ 的长度大于 $m$，则将队首元素 $x$ 从队列 $q$ 中移除，接下来从 $lo$, $mid$ 或 $hi$ 中选择其中一个包含 $x$ 的集合，将 $x$ 从该集合中移除，如果该集合为 $mid$，则将 $s$ 减去 $x$ 的值。
1. 如果 $lo$ 的长度大于 $k$，则循环将 $lo$ 中的最大值 $max(lo)$ 从 $lo$ 中移除，将 $max(lo)$ 加入 $mid$ 中，同时将 $s$ 加上 $max(lo)$ 的值。
1. 如果 $hi$ 的长度大于 $k$，则循环将 $hi$ 中的最小值 $min(hi)$ 从 $hi$ 中移除，将 $min(hi)$ 加入 $mid$ 中，同时将 $s$ 加上 $min(hi)$ 的值。
1. 如果 $lo$ 的长度小于 $k$，并且 $mid$ 不为空，则循环将 $mid$ 中的最小值 $min(mid)$ 从 $mid$ 中移除，将 $min(mid)$ 加入 $lo$ 中，同时将 $s$ 减去 $min(mid)$ 的值。
1. 如果 $hi$ 的长度小于 $k$，并且 $mid$ 不为空，则循环将 $mid$ 中的最大值 $max(mid)$ 从 $mid$ 中移除，将 $max(mid)$ 加入 $hi$ 中，同时将 $s$ 减去 $max(mid)$ 的值。

调用 $calculateMKAverage()$ 函数时，如果 $q$ 的长度小于 $m$，则返回 $-1$，否则返回 $\frac{s}{m - 2k}$。

时间复杂度方面，每次调用 $addElement(num)$ 函数的时间复杂度为 $O(\log m)$，每次调用 $calculateMKAverage()$ 函数的时间复杂度为 $O(1)$。空间复杂度为 $O(m)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
from sortedcontainers import SortedList


class MKAverage:

    def __init__(self, m: int, k: int):
        self.m = m
        self.k = k
        self.s = 0
        self.q = deque()
        self.lo = SortedList()
        self.mid = SortedList()
        self.hi = SortedList()

    def addElement(self, num: int) -> None:
        if not self.lo or num <= self.lo[-1]:
            self.lo.add(num)
        elif not self.hi or num >= self.hi[0]:
            self.hi.add(num)
        else:
            self.mid.add(num)
            self.s += num
        self.q.append(num)
        if len(self.q) > self.m:
            x = self.q.popleft()
            if x in self.lo:
                self.lo.remove(x)
            elif x in self.hi:
                self.hi.remove(x)
            else:
                self.mid.remove(x)
                self.s -= x
        while len(self.lo) > self.k:
            x = self.lo.pop()
            self.mid.add(x)
            self.s += x
        while len(self.hi) > self.k:
            x = self.hi.pop(0)
            self.mid.add(x)
            self.s += x
        while len(self.lo) < self.k and self.mid:
            x = self.mid.pop(0)
            self.lo.add(x)
            self.s -= x
        while len(self.hi) < self.k and self.mid:
            x = self.mid.pop()
            self.hi.add(x)
            self.s -= x

    def calculateMKAverage(self) -> int:
        return -1 if len(self.q) < self.m else self.s // (self.m - 2 * self.k)


# Your MKAverage object will be instantiated and called as such:
# obj = MKAverage(m, k)
# obj.addElement(num)
# param_2 = obj.calculateMKAverage()
```

```python
from sortedcontainers import SortedList


class MKAverage:

    def __init__(self, m: int, k: int):
        self.m = m
        self.k = k
        self.sl = SortedList()
        self.q = deque()
        self.s = 0

    def addElement(self, num: int) -> None:
        self.q.append(num)
        if len(self.q) == self.m:
            self.sl = SortedList(self.q)
            self.s = sum(self.sl[self.k: -self.k])
        elif len(self.q) > self.m:
            i = self.sl.bisect_left(num)
            if i < self.k:
                self.s += self.sl[self.k - 1]
            elif self.k <= i <= self.m - self.k:
                self.s += num
            else:
                self.s += self.sl[self.m - self.k]
            self.sl.add(num)

            x = self.q.popleft()
            i = self.sl.bisect_left(x)
            if i < self.k:
                self.s -= self.sl[self.k]
            elif self.k <= i <= self.m - self.k:
                self.s -= x
            else:
                self.s -= self.sl[self.m - self.k]
            self.sl.remove(x)

    def calculateMKAverage(self) -> int:
        return -1 if len(self.sl) < self.m else self.s // (self.m - self.k * 2)


# Your MKAverage object will be instantiated and called as such:
# obj = MKAverage(m, k)
# obj.addElement(num)
# param_2 = obj.calculateMKAverage()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class MKAverage {

    private int m, k;
    private long s;
    private int size1, size3;
    private Deque<Integer> q = new ArrayDeque<>();
    private TreeMap<Integer, Integer> lo = new TreeMap<>();
    private TreeMap<Integer, Integer> mid = new TreeMap<>();
    private TreeMap<Integer, Integer> hi = new TreeMap<>();

    public MKAverage(int m, int k) {
        this.m = m;
        this.k = k;
    }

    public void addElement(int num) {
        if (lo.isEmpty() || num <= lo.lastKey()) {
            lo.merge(num, 1, Integer::sum);
            ++size1;
        } else if (hi.isEmpty() || num >= hi.firstKey()) {
            hi.merge(num, 1, Integer::sum);
            ++size3;
        } else {
            mid.merge(num, 1, Integer::sum);
            s += num;
        }
        q.offer(num);
        if (q.size() > m) {
            int x = q.poll();
            if (lo.containsKey(x)) {
                if (lo.merge(x, -1, Integer::sum) == 0) {
                    lo.remove(x);
                }
                --size1;
            } else if (hi.containsKey(x)) {
                if (hi.merge(x, -1, Integer::sum) == 0) {
                    hi.remove(x);
                }
                --size3;
            } else {
                if (mid.merge(x, -1, Integer::sum) == 0) {
                    mid.remove(x);
                }
                s -= x;
            }
        }
        for (; size1 > k; --size1) {
            int x = lo.lastKey();
            if (lo.merge(x, -1, Integer::sum) == 0) {
                lo.remove(x);
            }
            mid.merge(x, 1, Integer::sum);
            s += x;
        }
        for (; size3 > k; --size3) {
            int x = hi.firstKey();
            if (hi.merge(x, -1, Integer::sum) == 0) {
                hi.remove(x);
            }
            mid.merge(x, 1, Integer::sum);
            s += x;
        }
        for (; size1 < k && !mid.isEmpty(); ++size1) {
            int x = mid.firstKey();
            if (mid.merge(x, -1, Integer::sum) == 0) {
                mid.remove(x);
            }
            s -= x;
            lo.merge(x, 1, Integer::sum);
        }
        for (; size3 < k && !mid.isEmpty(); ++size3) {
            int x = mid.lastKey();
            if (mid.merge(x, -1, Integer::sum) == 0) {
                mid.remove(x);
            }
            s -= x;
            hi.merge(x, 1, Integer::sum);
        }
    }

    public int calculateMKAverage() {
        return q.size() < m ? -1 : (int) (s / (q.size() - k * 2));
    }
}

/**
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage obj = new MKAverage(m, k);
 * obj.addElement(num);
 * int param_2 = obj.calculateMKAverage();
 */
```

### **C++**

```cpp
class MKAverage {
public:
    MKAverage(int m, int k) {
        this->m = m;
        this->k = k;
    }

    void addElement(int num) {
        if (lo.empty() || num <= *lo.rbegin()) {
            lo.insert(num);
        } else if (hi.empty() || num >= *hi.begin()) {
            hi.insert(num);
        } else {
            mid.insert(num);
            s += num;
        }

        q.push(num);
        if (q.size() > m) {
            int x = q.front();
            q.pop();
            if (lo.find(x) != lo.end()) {
                lo.erase(lo.find(x));
            } else if (hi.find(x) != hi.end()) {
                hi.erase(hi.find(x));
            } else {
                mid.erase(mid.find(x));
                s -= x;
            }
        }
        while (lo.size() > k) {
            int x = *lo.rbegin();
            lo.erase(prev(lo.end()));
            mid.insert(x);
            s += x;
        }
        while (hi.size() > k) {
            int x = *hi.begin();
            hi.erase(hi.begin());
            mid.insert(x);
            s += x;
        }
        while (lo.size() < k && mid.size()) {
            int x = *mid.begin();
            mid.erase(mid.begin());
            s -= x;
            lo.insert(x);
        }
        while (hi.size() < k && mid.size()) {
            int x = *mid.rbegin();
            mid.erase(prev(mid.end()));
            s -= x;
            hi.insert(x);
        }
    }

    int calculateMKAverage() {
        return q.size() < m ? -1 : s / (q.size() - k * 2);
    }

private:
    int m, k;
    long long s = 0;
    queue<int> q;
    multiset<int> lo, mid, hi;
};

/**
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage* obj = new MKAverage(m, k);
 * obj->addElement(num);
 * int param_2 = obj->calculateMKAverage();
 */
```

### **Go**

```go
type MKAverage struct {
	lo, mid, hi  *redblacktree.Tree
	q            []int
	m, k, s      int
	size1, size3 int
}

func Constructor(m int, k int) MKAverage {
	lo := redblacktree.NewWithIntComparator()
	mid := redblacktree.NewWithIntComparator()
	hi := redblacktree.NewWithIntComparator()
	return MKAverage{lo, mid, hi, []int{}, m, k, 0, 0, 0}
}

func (this *MKAverage) AddElement(num int) {
	merge := func(rbt *redblacktree.Tree, key, value int) {
		if v, ok := rbt.Get(key); ok {
			nxt := v.(int) + value
			if nxt == 0 {
				rbt.Remove(key)
			} else {
				rbt.Put(key, nxt)
			}
		} else {
			rbt.Put(key, value)
		}
	}

	if this.lo.Empty() || num <= this.lo.Right().Key.(int) {
		merge(this.lo, num, 1)
		this.size1++
	} else if this.hi.Empty() || num >= this.hi.Left().Key.(int) {
		merge(this.hi, num, 1)
		this.size3++
	} else {
		merge(this.mid, num, 1)
		this.s += num
	}
	this.q = append(this.q, num)
	if len(this.q) > this.m {
		x := this.q[0]
		this.q = this.q[1:]
		if _, ok := this.lo.Get(x); ok {
			merge(this.lo, x, -1)
			this.size1--
		} else if _, ok := this.hi.Get(x); ok {
			merge(this.hi, x, -1)
			this.size3--
		} else {
			merge(this.mid, x, -1)
			this.s -= x
		}
	}
	for ; this.size1 > this.k; this.size1-- {
		x := this.lo.Right().Key.(int)
		merge(this.lo, x, -1)
		merge(this.mid, x, 1)
		this.s += x
	}
	for ; this.size3 > this.k; this.size3-- {
		x := this.hi.Left().Key.(int)
		merge(this.hi, x, -1)
		merge(this.mid, x, 1)
		this.s += x
	}
	for ; this.size1 < this.k && !this.mid.Empty(); this.size1++ {
		x := this.mid.Left().Key.(int)
		merge(this.mid, x, -1)
		this.s -= x
		merge(this.lo, x, 1)
	}
	for ; this.size3 < this.k && !this.mid.Empty(); this.size3++ {
		x := this.mid.Right().Key.(int)
		merge(this.mid, x, -1)
		this.s -= x
		merge(this.hi, x, 1)
	}
}

func (this *MKAverage) CalculateMKAverage() int {
	if len(this.q) < this.m {
		return -1
	}
	return this.s / (this.m - 2*this.k)
}

/**
 * Your MKAverage object will be instantiated and called as such:
 * obj := Constructor(m, k);
 * obj.AddElement(num);
 * param_2 := obj.CalculateMKAverage();
 */
```

### **...**

```

```

<!-- tabs:end -->
