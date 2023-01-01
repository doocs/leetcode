# [855. Exam Room](https://leetcode.com/problems/exam-room)

[中文文档](/solution/0800-0899/0855.Exam%20Room/README.md)

## Description

<p>There is an exam room with <code>n</code> seats in a single row labeled from <code>0</code> to <code>n - 1</code>.</p>

<p>When a student enters the room, they must sit in the seat that maximizes the distance to the closest person. If there are multiple such seats, they sit in the seat with the lowest number. If no one is in the room, then the student sits at seat number <code>0</code>.</p>

<p>Design a class that simulates the mentioned exam room.</p>

<p>Implement the <code>ExamRoom</code> class:</p>

<ul>
	<li><code>ExamRoom(int n)</code> Initializes the object of the exam room with the number of the seats <code>n</code>.</li>
	<li><code>int seat()</code> Returns the label of the seat at which the next student will set.</li>
	<li><code>void leave(int p)</code> Indicates that the student sitting at seat <code>p</code> will leave the room. It is guaranteed that there will be a student sitting at seat <code>p</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;ExamRoom&quot;, &quot;seat&quot;, &quot;seat&quot;, &quot;seat&quot;, &quot;seat&quot;, &quot;leave&quot;, &quot;seat&quot;]
[[10], [], [], [], [], [4], []]
<strong>Output</strong>
[null, 0, 9, 4, 2, null, 5]

<strong>Explanation</strong>
ExamRoom examRoom = new ExamRoom(10);
examRoom.seat(); // return 0, no one is in the room, then the student sits at seat number 0.
examRoom.seat(); // return 9, the student sits at the last seat number 9.
examRoom.seat(); // return 4, the student sits at the last seat number 4.
examRoom.seat(); // return 2, the student sits at the last seat number 2.
examRoom.leave(4);
examRoom.seat(); // return 5, the student sits at the last seat number 5.

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li>It is guaranteed that there is a student sitting at seat <code>p</code>.</li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>seat</code> and <code>leave</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
from sortedcontainers import SortedList


class ExamRoom:

    def __init__(self, n: int):
        def dist(x):
            l, r = x
            return r - l - 1 if l == -1 or r == n else (r - l) >> 1

        self.n = n
        self.ts = SortedList(key=lambda x: (-dist(x), x[0]))
        self.left = {}
        self.right = {}
        self.add((-1, n))

    def seat(self) -> int:
        s = self.ts[0]
        p = (s[0] + s[1]) >> 1
        if s[0] == -1:
            p = 0
        elif s[1] == self.n:
            p = self.n - 1
        self.delete(s)
        self.add((s[0], p))
        self.add((p, s[1]))
        return p

    def leave(self, p: int) -> None:
        l, r = self.left[p], self.right[p]
        self.delete((l, p))
        self.delete((p, r))
        self.add((l, r))

    def add(self, s):
        self.ts.add(s)
        self.left[s[1]] = s[0]
        self.right[s[0]] = s[1]

    def delete(self, s):
        self.ts.remove(s)
        self.left.pop(s[1])
        self.right.pop(s[0])


# Your ExamRoom object will be instantiated and called as such:
# obj = ExamRoom(n)
# param_1 = obj.seat()
# obj.leave(p)
```

### **Java**

```java
class ExamRoom {
    private TreeSet<int[]> ts = new TreeSet<>((a, b) -> {
        int d1 = dist(a), d2 = dist(b);
        return d1 == d2 ? a[0] - b[0] : d2 - d1;
    });
    private Map<Integer, Integer> left = new HashMap<>();
    private Map<Integer, Integer> right = new HashMap<>();
    private int n;

    public ExamRoom(int n) {
        this.n = n;
        add(new int[] {-1, n});
    }

    public int seat() {
        int[] s = ts.first();
        int p = (s[0] + s[1]) >> 1;
        if (s[0] == -1) {
            p = 0;
        } else if (s[1] == n) {
            p = n - 1;
        }
        del(s);
        add(new int[] {s[0], p});
        add(new int[] {p, s[1]});
        return p;
    }

    public void leave(int p) {
        int l = left.get(p), r = right.get(p);
        del(new int[] {l, p});
        del(new int[] {p, r});
        add(new int[] {l, r});
    }

    private int dist(int[] s) {
        int l = s[0], r = s[1];
        return l == -1 || r == n ? r - l - 1 : (r - l) >> 1;
    }

    private void add(int[] s) {
        ts.add(s);
        left.put(s[1], s[0]);
        right.put(s[0], s[1]);
    }

    private void del(int[] s) {
        ts.remove(s);
        left.remove(s[1]);
        right.remove(s[0]);
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(n);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */
```

### **C++**

```cpp
int N;

int dist(const pair<int, int>& p) {
    auto [l, r] = p;
    if (l == -1 || r == N) return r - l - 1;
    return (r - l) >> 1;
}

struct cmp {
    bool operator()(const pair<int, int>& a, const pair<int, int>& b) const {
        int d1 = dist(a), d2 = dist(b);
        return d1 == d2 ? a.first < b.first : d1 > d2;
    };
};

class ExamRoom {
public:
    ExamRoom(int n) {
        N = n;
        this->n = n;
        add({-1, n});
    }

    int seat() {
        auto s = *ts.begin();
        int p = (s.first + s.second) >> 1;
        if (s.first == -1) {
            p = 0;
        } else if (s.second == n) {
            p = n - 1;
        }
        del(s);
        add({s.first, p});
        add({p, s.second});
        return p;
    }

    void leave(int p) {
        int l = left[p], r = right[p];
        del({l, p});
        del({p, r});
        add({l, r});
    }

private:
    set<pair<int, int>, cmp> ts;
    unordered_map<int, int> left;
    unordered_map<int, int> right;
    int n;

    void add(pair<int, int> s) {
        ts.insert(s);
        left[s.second] = s.first;
        right[s.first] = s.second;
    }

    void del(pair<int, int> s) {
        ts.erase(s);
        left.erase(s.second);
        right.erase(s.first);
    }
};

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom* obj = new ExamRoom(n);
 * int param_1 = obj->seat();
 * obj->leave(p);
 */
```

### **Go**

```go
type ExamRoom struct {
	rbt   *redblacktree.Tree
	left  map[int]int
	right map[int]int
	n     int
}

func Constructor(n int) ExamRoom {
	dist := func(s []int) int {
		if s[0] == -1 || s[1] == n {
			return s[1] - s[0] - 1
		}
		return (s[1] - s[0]) >> 1
	}
	cmp := func(a, b interface{}) int {
		x, y := a.([]int), b.([]int)
		d1, d2 := dist(x), dist(y)
		if d1 == d2 {
			return x[0] - y[0]
		}
		return d2 - d1
	}
	this := ExamRoom{redblacktree.NewWith(cmp), map[int]int{}, map[int]int{}, n}
	this.add([]int{-1, n})
	return this
}

func (this *ExamRoom) Seat() int {
	s := this.rbt.Left().Key.([]int)
	p := (s[0] + s[1]) >> 1
	if s[0] == -1 {
		p = 0
	} else if s[1] == this.n {
		p = this.n - 1
	}
	this.del(s)
	this.add([]int{s[0], p})
	this.add([]int{p, s[1]})
	return p
}

func (this *ExamRoom) Leave(p int) {
	l, _ := this.left[p]
	r, _ := this.right[p]
	this.del([]int{l, p})
	this.del([]int{p, r})
	this.add([]int{l, r})
}

func (this *ExamRoom) add(s []int) {
	this.rbt.Put(s, struct{}{})
	this.left[s[1]] = s[0]
	this.right[s[0]] = s[1]
}

func (this *ExamRoom) del(s []int) {
	this.rbt.Remove(s)
	delete(this.left, s[1])
	delete(this.right, s[0])
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * obj := Constructor(n);
 * param_1 := obj.Seat();
 * obj.Leave(p);
 */
```

### **...**

```

```

<!-- tabs:end -->
