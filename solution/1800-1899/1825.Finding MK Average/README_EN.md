# [1825. Finding MK Average](https://leetcode.com/problems/finding-mk-average)

[中文文档](/solution/1800-1899/1825.Finding%20MK%20Average/README.md)

## Description

<p>You are given two integers, <code>m</code> and <code>k</code>, and a stream of integers. You are tasked to implement a data structure that calculates the <strong>MKAverage</strong> for the stream.</p>

<p>The <strong>MKAverage</strong> can be calculated using these steps:</p>

<ol>
	<li>If the number of the elements in the stream is less than <code>m</code> you should consider the <strong>MKAverage</strong> to be <code>-1</code>. Otherwise, copy the last <code>m</code> elements of the stream to a separate container.</li>
	<li>Remove the smallest <code>k</code> elements and the largest <code>k</code> elements from the container.</li>
	<li>Calculate the average value for the rest of the elements <strong>rounded down to the nearest integer</strong>.</li>
</ol>

<p>Implement the <code>MKAverage</code> class:</p>

<ul>
	<li><code>MKAverage(int m, int k)</code> Initializes the <strong>MKAverage</strong> object with an empty stream and the two integers <code>m</code> and <code>k</code>.</li>
	<li><code>void addElement(int num)</code> Inserts a new element <code>num</code> into the stream.</li>
	<li><code>int calculateMKAverage()</code> Calculates and returns the <strong>MKAverage</strong> for the current stream <strong>rounded down to the nearest integer</strong>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;MKAverage&quot;, &quot;addElement&quot;, &quot;addElement&quot;, &quot;calculateMKAverage&quot;, &quot;addElement&quot;, &quot;calculateMKAverage&quot;, &quot;addElement&quot;, &quot;addElement&quot;, &quot;addElement&quot;, &quot;calculateMKAverage&quot;]
[[3, 1], [3], [1], [], [10], [], [5], [5], [5], []]
<strong>Output</strong>
[null, null, null, -1, null, 3, null, null, null, 5]

<strong>Explanation</strong>
<code>MKAverage obj = new MKAverage(3, 1); 
obj.addElement(3);        // current elements are [3]
obj.addElement(1);        // current elements are [3,1]
obj.calculateMKAverage(); // return -1, because m = 3 and only 2 elements exist.
obj.addElement(10);       // current elements are [3,1,10]
obj.calculateMKAverage(); // The last 3 elements are [3,1,10].
                          // After removing smallest and largest 1 element the container will be [3].
                          // The average of [3] equals 3/1 = 3, return 3
obj.addElement(5);        // current elements are [3,1,10,5]
obj.addElement(5);        // current elements are [3,1,10,5,5]
obj.addElement(5);        // current elements are [3,1,10,5,5,5]
obj.calculateMKAverage(); // The last 3 elements are [5,5,5].
                          // After removing smallest and largest 1 element the container will be [5].
                          // The average of [5] equals 5/1 = 5, return 5
</code></pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= m &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k*2 &lt; m</code></li>
	<li><code>1 &lt;= num &lt;= 10<sup>5</sup></code></li>
	<li>At most <code>10<sup>5</sup></code> calls will be made to <code>addElement</code> and <code>calculateMKAverage</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
